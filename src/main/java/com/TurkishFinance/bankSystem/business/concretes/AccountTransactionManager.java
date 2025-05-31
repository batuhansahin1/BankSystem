package com.TurkishFinance.bankSystem.business.concretes;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.TurkishFinance.bankSystem.business.abstracts.AccountTransactionService;
import com.TurkishFinance.bankSystem.business.requests.CreateTransactionRequest;
import com.TurkishFinance.bankSystem.business.responses.GetAllTransactionsResponse;
import com.TurkishFinance.bankSystem.business.responses.GetTransactionResponse;
import com.TurkishFinance.bankSystem.business.rules.AccountBusinessRules;
import com.TurkishFinance.bankSystem.business.rules.AccountTransactionBusinessRules;
import com.TurkishFinance.bankSystem.core.utilities.exceptions.BusinessException;
import com.TurkishFinance.bankSystem.core.utilities.helper.HelperFunctions;
import com.TurkishFinance.bankSystem.core.utilities.mappers.abstracts.ModelMapperService;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.AccountRepository;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.AccountTransactionRepository;
import com.TurkishFinance.bankSystem.entities.Account;
import com.TurkishFinance.bankSystem.entities.AccountTransaction;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.AllArgsConstructor;



@Service
@AllArgsConstructor
public class AccountTransactionManager implements AccountTransactionService{

	private final ModelMapperService modelMapperService;
	private final AccountTransactionRepository accountTransactionRepository;
	private final AccountTransactionBusinessRules accountTransactionBusinessRules;
	private final AccountRepository accountRepository;
	private final AccountBusinessRules accountBusinessRules;
	private final HelperFunctions helperFunctions;
	
	@Override
	public GetTransactionResponse get(String transactionNumber) {
		this.accountTransactionBusinessRules.checkIfTransactionNumberExists( transactionNumber);
		AccountTransaction accountTransaction=this.accountTransactionRepository
				.getByTransactionNumber(transactionNumber);
		GetTransactionResponse getTransactionResponse=this.modelMapperService.forResponse()
				.map(accountTransaction, GetTransactionResponse.class);
		
		return  getTransactionResponse;
	}

	@Override
	public List<GetAllTransactionsResponse> getAll() {
		
		
	     List<AccountTransaction> accountTransactions=this.accountTransactionRepository.findAll();
	     List<GetAllTransactionsResponse> getAllTransactions=accountTransactions.stream()
	    		 .map(transaction->this.modelMapperService.forResponse()
	    				 .map(transaction, GetAllTransactionsResponse.class)).collect(Collectors.toList());
		
		
		return getAllTransactions;
	}

	@Override
	public void add(CreateTransactionRequest createTransactionRequest) {
	    //burada biz alıcı ibanın bilgilerini topluyoruz 1 accountta birden fazla transaction olabilir
		//transactionlar hesaba özel olduğu için Account içinde accountTransactionList tutabiliriz
		//bir hesap zaten sadece individual ya da corporate olduğu için transactionları onlar içinde ayrıca tutmaya
		//gerek yok ama şöyle bir karışıklık oluyor biri hem corporate hem de individual müşteri ise
		//ikisinin de hareketleri aynı listede tutuluyor bu yüzden buna bir çözüm bulmamız lazım
		//bunu da mı individualAccount transaction corporateAccpuntTranasaction diye ayırıcaz
		//tek nesne accounttransaction olursa patlıyorum patlamıyorum her account ayrı bir nesne individual
		//ya da corporate olması farketmez bir hesap individual accountsa zaten onunla ilişkilendirilmiştir
		//başka bir nesneyle ilişkilendirilmemiştir bu yüzden hem corporate hem de individual'ın transaction'unu
		//listeye ekleyemeyiz hangi hesapsa onun transactionunu ekliyoruz zaten 1 hesap ya individual ya da corporate
		//olabiliyor bu yüzden direkt account içinde transactionlist tutmak sorun çıkarmaz create requestte 
		//accountNumber isteyeceğim olay kapanacak
		accountBusinessRules.checkIfAccountNumberExists(createTransactionRequest.getAccountNumber());
		
		Account account=accountRepository.findByAccountNumber(createTransactionRequest.getAccountNumber());
		//eğer parayı ben gönderiyorsam aşağıdakini yapmam lazım ama bana merkez bankasından
		//createTransaction request gelirse farklı işlemler yapmam lazım bu yüzden bunu ayırmamız lazım
		//veri frontendden gelirse ben merkez bankasına request yapıcam ben şu ibana para göndermek istiyorum diye
		//ama merkez bankası bana createTransaction request atarsa onu farklı şekilde kaydetmem lazım
		//bu da 12.052025'in çözülecek konusu olsun
		//request için
	    String uri=UriComponentsBuilder.fromHttpUrl("http://localhost:9090/api/transfers/add")
	    		.queryParam("senderIban", account.getIbanNumber())
	    		.queryParam("receiverIban",createTransactionRequest.getReceiverIban())
	    		.queryParam("receiverFirstName",createTransactionRequest.getReceiverFirstName())
	    		.queryParam("receiverLastName",createTransactionRequest.getReceiverLastName())
	    		.queryParam("transferAmount", createTransactionRequest.getTransferAmount())
	    		.queryParam("description",createTransactionRequest.getDescription())
	    		.queryParam("senderBankVkn","1111111111")
	    		.toUriString();
	         System.out.println(uri);
		 try {
			Map<String,Object>responseObject=helperFunctions.postAccount(uri);
			AccountTransaction accountTransaction=this.modelMapperService.forRequest()
					.map(createTransactionRequest, AccountTransaction.class);
			//Merkez bankasından afterCreatingTransferResponse'da transactionReferance'yi 1234567890
			//döndürüyorum ama merkez bankası alıcı bankaya api request yaparken referansı 1234567862 olarak
			//yapmış ve responseda gönderenin adı kaydedilmiyor onu banka içinden set etmemiz lazım
			//buradan çünkü central bank'ın gönderdiği requestlerde hepsi set ediliyor
			// mb'ndan responseda  receiver'ı dönmüyoruz o yüzden oluyor receiver'ın bilgileri bizde var zaten
			//bu class için söylüyorum çünkü transferi biz başlatıyoruz
			//boş olan kısım buranın senderFirstName ve senderLastName alanları
			//onu set etmedim çünkü accounttan alınır diye düşündüm
			//22.05.2025 bakılsın
			this.accountTransactionBusinessRules.checkIfTransactionReferanceExists(responseObject
					.get("transferReferance").toString());
			System.out.println(responseObject);
			accountTransaction.setAccount(account); 
		
			
			
			//burada takılmıştım hesap'ı buluyorum ama account'un individual mı değil mi onu bulamıyorum
			//bunun yerine ibanla individual ya da corporate içinde gezip türünü belirleyip setAccount'a
			//onların içindeki ilişkili oldukları accıunt'u vericem
			// bu yüzden genel bulmama gerek yok çünkü account içinde individual
			//corporate diye ayrılıyor  hangisi olduğunu bilmek için ikisinin de içinde
			//arama yapmam lazım responseda da senderName olarak alabilirim bunları yapmak yerine 
			//mb bana dekontla ilgili tüm verileri gönderir
			//22.05.2025 yapılacaklar
			//direkt merkez bankasından döndüreceğim ama account'u bulmak için yine ikisinde de
			//aramam lazım
			//corporate customer olursa bu kullanılmaz burası optional 
			//direkt account ile customer bilgisini bağlasak nasıl olur
			//ama yaratırken ndividualCustomerdan yaratamayız
			//gpt ile hasbihal lazım
			//çözüm aslında iki tabloyu account'un bir alt tablosu gibi yapmak yani isa ilişkisi
			//şöyle ikisinin de pk'i accounttan referans alıcak böylece hangisinin hangisi olduğu karışmayacak
			//ama bunu nasıl yapıcaz
			//bana kalsa type'a göre enum yapıp geçerim ama ileride bu corporate'ye özel kampanyalar sunacağım
			//individual'a özel kampanyalar sunacağım bu yüzden bu ekstra tabloların olması lazım
			//onu individual customer ya da individual account'a bağlıcam mecbur ayrılması lazım ama bu idlerin
			//ana tablodan gelmesi mantıklı bunu orm'de nasıl yapıcam biraz bunu araştıracam
			//if else'i tek tablo yapmadığım sürece yok edemiyorum bu da open-closed prensibine aykırı bi bakıcaz
			
			//direkt individual ya da corporate'e sorgu atıcaz ilişki kaldırıldı
			
			//accountTransaction.setSenderFirstName(account.getIndividualAccount().getIndividualCustomer()
			//		.getCustomer().getFirstName());
			//accountTransaction.setSenderLastName(account.getIndividualAccount()
				//	.getIndividualCustomer().getCustomer().getLastName());
			accountTransaction.setTransferAmount(createTransactionRequest.getTransferAmount());
			account.setTotalAmount(account.getTotalAmount()+(long)createTransactionRequest.getTransferAmount());
			accountTransaction.setCurrency(account.getAccountCurrency());
			accountTransaction.setDescription(createTransactionRequest.getDescription());
			accountTransaction.setDirection("SENDING");
			accountTransaction.setReceiverBankName(responseObject.get("receiverBankName").toString());
			accountTransaction.setTransactionReferance(responseObject.get("transferReferance").toString());
			accountTransaction.setReceiverFirstName(createTransactionRequest.getReceiverFirstName());
			accountTransaction.setReceiverLastName(createTransactionRequest.getReceiverLastName());
			accountTransaction.setSenderIban(account.getIbanNumber());
			//hesaba göre bunu set etmek gerekiyor hangisi olduğunu bilmiyoruz
		   // accountTransaction.setSenderFirstName(account.get)
			accountTransactionRepository.save(accountTransaction);
			//burada patladık hangi müşteri olduğunu nereden bilicez veriyi account
			//tablosundan alıyoruz
			//joinlerle alırız ya da ilk başta accounttan değil ayrı ayrı tablolardan 
			//sorgu atıcaz hangisi doluysa onla devam edicez çünkü individualAccount
			//ve corporate accountların boş mu dolu mu olduğunu bilmiyorum
			//ya da account number'ın length'ine bakıp ona göre sorgu atabilirim
		    //account numberlar farklı bunu kontrol ederek devam edicem
			//accountNumber sender'ın accountNumber'ı
			//aslında giriş yapınca müşterinin kim olduğu belli
			//olay burada çözülüyor girerken doğrulama yaptığım için göndereni biliyorum
			//zaten hesap aktif  bu veriler elimde olur giren kişilerin bilgilerini
			//giriş yaptıklarında front-end'e göndermem lazım müşteri çkış yapana kadar
			// da veriyi önbellekte tutarım böylece  kim olduğunu front-end bana
			//rahatça gönderebilir
			//bunu düşünücem ondan sonra koda devam ederim.
			
			
			//response objectte transactionNumber olucak
			//buradan sonra merkez bankasındaki koda geçip responseObject kodlayacağız
			//onu da akşam yaparım
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			//throw new BusinessException(e.getMessage());
		}
		
	}

}
