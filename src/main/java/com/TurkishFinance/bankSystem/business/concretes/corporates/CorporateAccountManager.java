package com.TurkishFinance.bankSystem.business.concretes.corporates;

import java.util.List;

import org.springframework.stereotype.Service;

import com.TurkishFinance.bankSystem.business.abstracts.corporates.CorporateAccountService;
import com.TurkishFinance.bankSystem.business.requests.CreateCorporateAccountRequest;
import com.TurkishFinance.bankSystem.business.requests.UpdateCorporateAccountRequest;
import com.TurkishFinance.bankSystem.business.responses.GetAllCorporateAccountsResponse;
import com.TurkishFinance.bankSystem.business.responses.GetCorporateAccountResponse;
import com.TurkishFinance.bankSystem.business.rules.AccountBusinessRules;
import com.TurkishFinance.bankSystem.business.rules.corporates.CorporateAccountBusinessRules;
import com.TurkishFinance.bankSystem.core.utilities.mappers.abstracts.ModelMapperService;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.AccountRepository;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.corporates.CorporateAccountRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CorporateAccountManager implements CorporateAccountService {

	private final CorporateAccountRepository corporateAccountRepository;
	private final ModelMapperService modelMapperService;
	private final AccountRepository accountRepository;
	private final CorporateAccountBusinessRules corporateAccountBusinessRules;
	private final AccountBusinessRules accountBusinessRules;
	
	
	@Override
	public GetCorporateAccountResponse getCorporateAccount(String accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GetAllCorporateAccountsResponse> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(CreateCorporateAccountRequest createCorporateAccountRequest) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String customerNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(UpdateCorporateAccountRequest updateCorporateAccountRequest) {
		// TODO Auto-generated method stub
		
	}

	
	//createAccountRequestte de fast sistemine request atıcaz
}
