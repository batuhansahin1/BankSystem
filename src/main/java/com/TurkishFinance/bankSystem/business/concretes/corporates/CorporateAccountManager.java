package com.TurkishFinance.bankSystem.business.concretes.corporates;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.TurkishFinance.bankSystem.business.abstracts.corporates.CorporateAccountService;
import com.TurkishFinance.bankSystem.business.requests.CreateCorporateAccountRequest;
import com.TurkishFinance.bankSystem.business.requests.UpdateCorporateAccountRequest;
import com.TurkishFinance.bankSystem.business.responses.GetAllCorporateAccountsResponse;
import com.TurkishFinance.bankSystem.business.responses.GetCorporateAccountResponse;
import com.TurkishFinance.bankSystem.business.rules.AccountBusinessRules;
import com.TurkishFinance.bankSystem.business.rules.corporates.CorporateAccountBusinessRules;
import com.TurkishFinance.bankSystem.core.utilities.helper.HelperFunctions;
import com.TurkishFinance.bankSystem.core.utilities.mappers.abstracts.ModelMapperService;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.AccountRepository;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.corporates.CorporateAccountRepository;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.corporates.CorporateCustomerRepository;
import com.TurkishFinance.bankSystem.entities.corporates.CorporateCustomer;
import com.fasterxml.jackson.core.JsonProcessingException;
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
	public void add(CreateCorporateAccountRequest createCorporateAccountRequest) throws JsonProcessingException {
		// TODO Auto-generated method stub
		//yapılacak adımlar bankaya gelen createCorporateAccountRequestte ilk olarak tcKimlikNo ve 
		//corporateCustomerNumber a ait olan kişiyi ve şirketin bilgilerinin bulucaz
		//sonra account'umuza iban oluşturmak için fast sistemine api request atıcaz createPersonAccount diye
		//oradaki request için istenilen bilgileri json formatına getirip request yapıcaz
		//daha sonra orada girilen bilgilerin doğruluğunu teyit edicez ve girilen bilgilerle sıra sıra
		//bütün tablolarda örnek oluşturucaz
		//yine corporate diye ayırmak zorunda kalabiliriz merkez bankası için söylüyrum bunu
		
		CorporateCustomer corporateCustomer=this.corporateCustomerRepository.findByCorporateCustomerNumber(createCorporateAccountRequest.getCorporateCustomerNumber());
		//bu customer'a ait bilgilerle request yapıcaz
		//accountNo'yu da biz oluşturucaz 00000000000000001
		Map<String, Object> requestObj=new HashMap<>();
		
		requestObj.put("personTcKimlikNo", corporateCustomer.getCustomer().getTcKimlikNo());
		requestObj.put("personFirstName", corporateCustomer.getCustomer().getFirstName());
		requestObj.put("personLastName", corporateCustomer.getCustomer().getLastName());
		requestObj.put("personBirthDate", corporateCustomer.getCustomer().getBirthDate());
		requestObj.put("pesonBirthPlace", corporateCustomer.getCustomer().getBirthPlace());
		//bankanın vKimlikNosu
		requestObj.put("vKimlikNo","0000000001");
		
		requestObj.put("bankCode", "00001");
        requestObj.put("accountNo", "0000000000000001");
        requestObj.put("accountCurrency", createCorporateAccountRequest.getAccountCurrency());
        //buralara gerek yok request için biz zaten fonksiyon yazdık
        //bu post fonksiyonunu genelleştireceğim
     Map<String, Object> fastSystemResponse=helperFunctions.postAccount("http://localhost:9090/api/personaccounts/add",requestObj);
		/*
		 * ObjectMapper mapper=new ObjectMapper(); String
		 * jsonString=mapper.writeValueAsString(requestObj);
		 */
	  
        
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
