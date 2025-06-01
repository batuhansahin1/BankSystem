package com.TurkishFinance.bankSystem.business.concretes.individuals;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.TurkishFinance.bankSystem.business.abstracts.individuals.DepositAccountService;
import com.TurkishFinance.bankSystem.business.requests.CreateDepositAccountRequest;

import com.TurkishFinance.bankSystem.business.responses.GetAllDepositAccountsResponse;

import com.TurkishFinance.bankSystem.business.responses.GetDepositAccountResponse;

import com.TurkishFinance.bankSystem.business.rules.individuals.DepositAccountBusinessRules;

import com.TurkishFinance.bankSystem.business.rules.individuals.IndividualCustomerBusinessRules;
import com.TurkishFinance.bankSystem.core.utilities.exceptions.BusinessException;
import com.TurkishFinance.bankSystem.core.utilities.helper.HelperFunctions;
import com.TurkishFinance.bankSystem.core.utilities.mappers.abstracts.ModelMapperService;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.AccountRepository;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.individuals.DepositAccountRepository;

import com.TurkishFinance.bankSystem.dataAccess.abstracts.individuals.IndividualCustomerRepository;
import com.TurkishFinance.bankSystem.entities.Account;
import com.TurkishFinance.bankSystem.entities.individuals.DepositAccount;
import com.TurkishFinance.bankSystem.entities.individuals.IndividualCustomer;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepositAccountManager implements DepositAccountService {

	
	private final DepositAccountBusinessRules depositAccountBusinessRules;
	private final DepositAccountRepository depositAccountRepository;
	private final AccountRepository accountRepository;
	private final ModelMapperService modelMapperService;
	private final HelperFunctions helperFunctions;
	private final IndividualCustomerBusinessRules individualCustomerBusinessRules;
	private final IndividualCustomerRepository individualCustomerRepository;
	 
	@Override
	public List<GetAllDepositAccountsResponse> getAll() {
	
		List<DepositAccount> depositAccounts=this.depositAccountRepository.findAll();
		List<GetAllDepositAccountsResponse> accountsResponse=depositAccounts.stream()
				.map(account->{
					GetAllDepositAccountsResponse getAllResponse=this.modelMapperService.forResponse().map(account, GetAllDepositAccountsResponse.class);
						getAllResponse.setAccountNumber(account.getAccountNumber());
						return getAllResponse;
				}).collect(Collectors.toList());
		return accountsResponse;
	}


	@Override
	public GetDepositAccountResponse get(String accountNumber) {
		this.depositAccountBusinessRules.checkIfAccountNumberExists(accountNumber);
		DepositAccount individualAccount=this.depositAccountRepository.findByAccountNumber(accountNumber);
		GetDepositAccountResponse accountResponse=this.modelMapperService.forResponse()
				.map(individualAccount,GetDepositAccountResponse.class);
		
		 
		return accountResponse;
	}


	@Override
	public void add(CreateDepositAccountRequest createDepositAccountRequest) {
		
		this.individualCustomerBusinessRules
		.checkIfCustomerNumberExists(createDepositAccountRequest.getIndividualCustomerNumber());
		//this.individualAccountBusinessRules.chekIfCustomerExists(createIndividualAccountRequest.getIndividualCustomerNumber());		
	   //bu individualAccount döndürmek zorunda
		 
		IndividualCustomer individualCustomer=this.individualCustomerRepository.findByCustomerNumber(createDepositAccountRequest.getIndividualCustomerNumber());
		System.out.println(individualCustomer.getCustomerNumber());
		
		String accountNumber=helperFunctions.createAccountNumber();
		
	    String uri=UriComponentsBuilder.fromHttpUrl("http://localhost:9090/api/personaccounts/add")
	    .queryParam("personTcKimlikNo",individualCustomer.getTcKimlikNo())
	    .queryParam("bankName", "ziraat").queryParam("vergiKimlikNo","1111111111")
	    .queryParam("bankCode", "00001").queryParam("accountNo", accountNumber)
	    .queryParam("accountCurrency", createDepositAccountRequest.getAccountCurrency()).toUriString();
		
	    try {
			Map<String,Object> responseObject =helperFunctions.postAccount(uri);
			System.out.println(responseObject);
			if(responseObject==null) {
				throw new Exception("response object is null");
			}
			DepositAccount depositAccount=new DepositAccount();
		    //Account account=new Account();
		    depositAccount.setAccountCurrency(createDepositAccountRequest.getAccountCurrency());
		    depositAccount.setAccountNumber(accountNumber);
		    depositAccount.setIbanNumber(responseObject.get("ibanNumber").toString());
		    depositAccount.setOpenedDate(LocalDate.now());
		    depositAccount.setStatus("active");
		    depositAccount.setTotalAmount(0);
		    accountRepository.save(depositAccount);
			//individualAccount.setAccount(account);
			depositAccount.setCustomer(individualCustomer);
			depositAccountRepository.save(depositAccount);
			
		} catch (Exception e) {
			 System.err.println("İstek sırasında hata oluştu: " + e.getMessage());
			 throw new BusinessException(e.getMessage());
			//e.printStackTrace();
		}
	}
   

	@Override
	public void delete(String customerNumber) {
		this.depositAccountRepository.existsByAccountNumber(customerNumber);
		Account account=accountRepository.findByAccountNumber(customerNumber);
		accountRepository.delete(account);
		
	}
   
}
