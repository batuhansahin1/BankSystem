package com.TurkishFinance.bankSystem.business.concretes.individuals;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.TurkishFinance.bankSystem.business.abstracts.individuals.IndividualAccountService;
import com.TurkishFinance.bankSystem.business.requests.CreateIndividualAccountRequest;
import com.TurkishFinance.bankSystem.business.responses.GetAllIndividualAccountsResponse;
import com.TurkishFinance.bankSystem.business.responses.GetIndividualAccountResponse;
import com.TurkishFinance.bankSystem.business.rules.individuals.IndividualAccountBusinessRules;
import com.TurkishFinance.bankSystem.business.rules.individuals.IndividualCustomerBusinessRules;
import com.TurkishFinance.bankSystem.core.utilities.helper.HelperFunctions;
import com.TurkishFinance.bankSystem.core.utilities.mappers.abstracts.ModelMapperService;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.AccountRepository;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.individuals.IndividualAccountRepository;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.individuals.IndividualCustomerRepository;
import com.TurkishFinance.bankSystem.entities.Account;
import com.TurkishFinance.bankSystem.entities.individuals.IndividualAccount;
import com.TurkishFinance.bankSystem.entities.individuals.IndividualCustomer;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IndividualAccountManager implements IndividualAccountService {

	
	private final IndividualAccountBusinessRules individualAccountBusinessRules;
	private final IndividualAccountRepository individualAccountRepository;
	private final AccountRepository accountRepository;
	private final ModelMapperService modelMapperService;
	private final HelperFunctions helperFunctions;
	private final IndividualCustomerBusinessRules individualCustomerBusinessRules;
	private final IndividualCustomerRepository individualCustomerRepository;
	 
	@Override
	public List<GetAllIndividualAccountsResponse> getAll() {
	
		List<IndividualAccount> individualAccounts=this.individualAccountRepository.findAll();
		List<GetAllIndividualAccountsResponse> accountsResponse=individualAccounts.stream()
				.map(account->this.modelMapperService.forResponse().map(account, GetAllIndividualAccountsResponse.class))
				.collect(Collectors.toList());
		
		return accountsResponse;
	}


	@Override
	public GetIndividualAccountResponse get(String accountNumber) {
		this.individualAccountBusinessRules.checkIfAccountNumberExists(accountNumber);
		IndividualAccount individualAccount=this.individualAccountRepository.findByAccountAccountNumber(accountNumber);
		GetIndividualAccountResponse accountResponse=this.modelMapperService.forResponse()
				.map(individualAccount,GetIndividualAccountResponse.class);
		
		
		return accountResponse;
	}


	@Override
	public void add(CreateIndividualAccountRequest createIndividualAccountRequest) {
		
		this.individualCustomerBusinessRules
		.checkIfExistsByCustomerNumber(createIndividualAccountRequest.getIndividualCustomerNumber());
		//this.individualAccountBusinessRules.chekIfCustomerExists(createIndividualAccountRequest.getIndividualCustomerNumber());		
	   //bu individualAccount döndürmek zorunda
		 
		IndividualCustomer individualCustomer=this.individualCustomerRepository.findByIndividualCustomerNumber(createIndividualAccountRequest.getIndividualCustomerNumber());
		System.out.println(individualCustomer.getIndividualCustomerNumber());
		
		String accountNumber=helperFunctions.createAccountNumber();
		
	    String uri=UriComponentsBuilder.fromHttpUrl("http://localhost:9090/api/personaccounts/add")
	    .queryParam("personTcKimlikNo",individualCustomer.getCustomer().getTcKimlikNo())
	    .queryParam("bankName", "ziraat").queryParam("vergiKimlikNo","1111111111")
	    .queryParam("bankCode", "00001").queryParam("accountNo", accountNumber)
	    .queryParam("accountCurrency", createIndividualAccountRequest.getAccountCurrency()).toUriString();
		
	    try {
			Map<String,Object> responseObject =helperFunctions.postAccount(uri);
			
			if(responseObject.equals(null)) {
				throw new Exception("response object is null");
			}
		    Account account=new Account();
		    account.setAccountCurrency(createIndividualAccountRequest.getAccountCurrency());
		    account.setAccountNumber(accountNumber);
		    account.setIbanNumber(responseObject.get("ibanNumber").toString());
		    account.setOpenedDate(LocalDate.now());
		    account.setStatus("active");
		    account.setTotalAmount(0);
		    accountRepository.save(account);
			IndividualAccount individualAccount=new IndividualAccount();
			individualAccount.setAccount(account);
			individualAccount.setIndividualCustomer(individualCustomer);
			individualAccountRepository.save(individualAccount);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
   

	@Override
	public void delete(String customerNumber) {
		this.individualAccountRepository.existsByAccountAccountNumber(customerNumber);
		Account account=accountRepository.findByAccountNumber(customerNumber);
		accountRepository.delete(account);
		
	}
   
}
