package com.TurkishFinance.bankSystem.business.concretes.corporates;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.TurkishFinance.bankSystem.business.abstracts.corporates.CorporateAccountService;
import com.TurkishFinance.bankSystem.business.requests.CreateCorporateAccountRequest;
import com.TurkishFinance.bankSystem.business.requests.UpdateCorporateAccountRequest;
import com.TurkishFinance.bankSystem.business.responses.GetAllCorporateAccountsResponse;
import com.TurkishFinance.bankSystem.business.responses.GetCorporateAccountResponse;
import com.TurkishFinance.bankSystem.business.rules.AccountBusinessRules;
import com.TurkishFinance.bankSystem.business.rules.CustomerBusinessRules;
import com.TurkishFinance.bankSystem.business.rules.corporates.CorporateAccountBusinessRules;
import com.TurkishFinance.bankSystem.business.rules.corporates.CorporateCustomerBusinessRules;
import com.TurkishFinance.bankSystem.core.utilities.exceptions.BusinessException;
import com.TurkishFinance.bankSystem.core.utilities.helper.HelperFunctions;
import com.TurkishFinance.bankSystem.core.utilities.mappers.abstracts.ModelMapperService;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.AccountRepository;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.corporates.CorporateAccountRepository;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.corporates.CorporateCustomerRepository;
import com.TurkishFinance.bankSystem.entities.Account;
import com.TurkishFinance.bankSystem.entities.corporates.CorporateAccount;
import com.TurkishFinance.bankSystem.entities.corporates.CorporateCustomer;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CorporateAccountManager implements CorporateAccountService {

	private final CorporateAccountRepository corporateAccountRepository;
	private final ModelMapperService modelMapperService;
	private final AccountRepository accountRepository;
	private final CorporateAccountBusinessRules corporateAccountBusinessRules;
	private final AccountBusinessRules accountBusinessRules;
	private final CorporateCustomerRepository corporateCustomerRepository;
	private final HelperFunctions helperFunctions;
	private final CorporateCustomerBusinessRules corporateCustomerBusinessRules;
	
	
	
	@Override
	public GetCorporateAccountResponse getCorporateAccount(String accountNumber) {
		
	   this.accountBusinessRules.checkIfAccountNumberExists(accountNumber);
	   CorporateAccount corporateAccount=this.corporateAccountRepository.findByAccountAccountNumber(accountNumber);
	   GetCorporateAccountResponse getCorporateAccountResponse=this.modelMapperService.forResponse().map(corporateAccount, GetCorporateAccountResponse.class);
	   getCorporateAccountResponse.setAccountNumber(corporateAccount.getAccount().getAccountNumber());
		
		return getCorporateAccountResponse;
	}

	@Override
	public List<GetAllCorporateAccountsResponse> getAll() {
		
		//GetAllCorporateAccountsResponse getAllCorporateAccountsResponse=new GetAllCorporateAccountsResponse();
		List<CorporateAccount> corporateAccounts=corporateAccountRepository.findAll();
		List<GetAllCorporateAccountsResponse> corporateAccountList=corporateAccounts.stream()
				.map(account->this.modelMapperService.forResponse()
						.map(account, GetAllCorporateAccountsResponse.class))
				.collect(Collectors.toList());
		
		
		return corporateAccountList;
	}

	@Override
	public void add(CreateCorporateAccountRequest createCorporateAccountRequest) throws Exception {
		// TODO Auto-generated method stub
		//yapılacak adımlar bankaya gelen createCorporateAccountRequestte ilk olarak tcKimlikNo ve 
		//corporateCustomerNumber a ait olan kişiyi ve şirketin bilgilerinin bulucaz
		//sonra account'umuza iban oluşturmak için fast sistemine api request atıcaz createPersonAccount diye
		//oradaki request için istenilen bilgileri json formatına getirip request yapıcaz
		//daha sonra orada girilen bilgilerin doğruluğunu teyit edicez ve girilen bilgilerle sıra sıra
		//bütün tablolarda örnek oluşturucaz
		//yine corporate diye ayırmak zorunda kalabiliriz merkez bankası için söylüyrum bunu
		//corporateCustomerBusinessRules.checkIfCorporateCustomerNumberExists(createCorporateAccountRequest.getCorporateCustomerNumber());
		CorporateCustomer corporateCustomer=this.corporateCustomerRepository.findByCorporateCustomerNumber(createCorporateAccountRequest.getCorporateCustomerNumber());
		//bu customer'a ait bilgilerle request yapıcaz
		//accountNo'yu da biz oluşturucaz 00000000000000001
  
        //buralara gerek yok request için biz zaten fonksiyon yazdık
        //bu post fonksiyonunu genelleştireceğim
         String accountNumber=helperFunctions.createAccountNumber();
         System.out.println(accountNumber);
		String uri= UriComponentsBuilder.fromHttpUrl("http://localhost:9090/api/personaccounts/add")
        		.queryParam("personTcKimlikNo",corporateCustomer.getCustomer().getTcKimlikNo())
        		.queryParam("bankName", "ziraat").queryParam("vergiKimlikNo","1111111111")
        		.queryParam("bankCode", "00001").queryParam("accountNo",accountNumber)
        		.queryParam("accountCurrency",createCorporateAccountRequest.getAccountCurrency()).toUriString();
		System.out.println(uri);
     Map<String, Object> fastSystemResponse=helperFunctions.postAccount(uri);
	System.out.println(fastSystemResponse);	
	if(fastSystemResponse.get("errorCode")!=null&&fastSystemResponse.get("errorMessage").toString()!=null) {
		throw new BusinessException(fastSystemResponse.get("errorMessage").toString());
	}
     
     /*
		 * ObjectMapper mapper=new ObjectMapper(); String
		 * jsonString=mapper.writeValueAsString(requestObj);
		 */
	  //account'u save yapıcam unutmuşum
        Account account=new Account();
        account.setAccountCurrency(createCorporateAccountRequest.getAccountCurrency());
        account.setAccountNumber(accountNumber); 
	   //responseda gelicek
        account.setIbanNumber(fastSystemResponse.get("ibanNumber").toString());
        account.setOpenedDate(LocalDate.now());
        account.setTotalAmount(0);
        accountRepository.save(account);
        CorporateAccount corporateAccount=new CorporateAccount();
        corporateAccount.setAccount(account);
        corporateAccount.setCorporateCustomer(corporateCustomer);
        corporateAccountRepository.save(corporateAccount);
        
	}

	@Override
	public void delete(String accountNumber) {
		// TODO Auto-generated method stub
		//10.05.2025'de yazıcam
		//jpa sorguları kötü delete by ile doğru düzgün  
		this.corporateAccountBusinessRules.checkIfAccountNumberExists(accountNumber);
		//CorporateAccount corporateAccount= corporateAccountRepository.findByAccountAccountNumber(accountNumber);
		//üst türden sildim cascade olduğu için iki tablodan da sildi ama bunun individual mı corporate mı old
		//nasıl anlayacağız yukarıdaki gibi bir businessrules yazarak anlayabiliriz
		Account account=accountRepository.findByAccountNumber(accountNumber);
		//corporateAccountRepository.delete(corporateAccount);
		accountRepository.delete(account);
		
	}

	@Override
	public void update(UpdateCorporateAccountRequest updateCorporateAccountRequest) {
		// TODO Auto-generated method stub
		accountBusinessRules.chickIfAccountIsPassive(updateCorporateAccountRequest.getAccountNumber());
		Account account=accountRepository.findByAccountNumber(updateCorporateAccountRequest.getAccountNumber());
		account.setTotalAmount(account.getTotalAmount()+updateCorporateAccountRequest.getRecievedAmount());
		accountRepository.save(account);
		
		
	}

	
	//createAccountRequestte de fast sistemine request atıcaz
}
