package com.TurkishFinance.bankSystem.business.concretes;

import org.springframework.stereotype.Service;

import com.TurkishFinance.bankSystem.business.abstracts.CentralBankTransactionService;
import com.TurkishFinance.bankSystem.business.requests.CreateCentralBankTransactionRequest;
import com.TurkishFinance.bankSystem.business.responses.AfterCentralBankTransactionResponse;
import com.TurkishFinance.bankSystem.business.rules.AccountBusinessRules;
import com.TurkishFinance.bankSystem.business.rules.AccountTransactionBusinessRules;
import com.TurkishFinance.bankSystem.core.utilities.mappers.abstracts.ModelMapperService;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.AccountRepository;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.AccountTransactionRepository;
import com.TurkishFinance.bankSystem.entities.Account;
import com.TurkishFinance.bankSystem.entities.AccountTransaction;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CentralBankTransactionManager implements CentralBankTransactionService {
      //merkez bankasının yapacağı requestler ve transaction şekilleri farklı şekilde olacağı için ayrı bir
	//service yazdım
	
	private final AccountTransactionRepository accountTransactionRepository;
	private final AccountTransactionBusinessRules accountTransactionBusinessRules;
	private final AccountBusinessRules accountBusinessRules;
	private final AccountRepository accountRepository;
	private final ModelMapperService modelMapperService;
	
	//merkez bankasından gelen request receiver iban bizim bankada olduğu için
	//bize merkez bankası request gönderiyor ilerde buralar otomatikleştirilebilinir
	//subscriber pattern ile
	@Override
	public AfterCentralBankTransactionResponse addTransaction(CreateCentralBankTransactionRequest createCentralBankTransactionRequest) {
	
		//bu business referance number olarak değiştirilecek
		this.accountTransactionBusinessRules.checkIfTransactionReferanceExists(createCentralBankTransactionRequest.getTransactionReferance());
		this.accountBusinessRules.checkIfIbanExists(createCentralBankTransactionRequest.getReceiverIban());
		
		Account account=this.accountRepository.findByIbanNumber(createCentralBankTransactionRequest.getReceiverIban());
		
		//direkt tc de döndürülebilir oradan bilgileri alırız yani merkez bankası bize direkt tcyi gönderir biz 
		//onu burada bulup account içinden set ederiz ona sonra karar vericem
		AccountTransaction accountTransaction=this.modelMapperService.forRequest().map(createCentralBankTransactionRequest, AccountTransaction.class);
		accountTransaction.setAccount(account);
		accountTransaction.setDirection("RECEVING");
		accountTransaction.setReceiverBankName("ziraat");
		//tamamlandı
		accountTransactionRepository.save(accountTransaction);
	    
		AfterCentralBankTransactionResponse afterCentralBankTransactionResponse=new AfterCentralBankTransactionResponse();
	    afterCentralBankTransactionResponse.setCode(200);
	    afterCentralBankTransactionResponse.setMessage("Request accepted");
	    afterCentralBankTransactionResponse.setStatus("ok");
	    return afterCentralBankTransactionResponse;
	}

}
