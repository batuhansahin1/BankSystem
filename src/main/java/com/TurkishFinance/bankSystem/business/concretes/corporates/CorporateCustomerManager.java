package com.TurkishFinance.bankSystem.business.concretes.corporates;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.TurkishFinance.bankSystem.business.abstracts.corporates.CorporateCustomerService;
import com.TurkishFinance.bankSystem.business.requests.CreateCorporateCustomerRequest;
import com.TurkishFinance.bankSystem.business.responses.GetAllCorporateCustomersResponse;
import com.TurkishFinance.bankSystem.business.responses.GetCorporateCustomerResponse;
import com.TurkishFinance.bankSystem.business.rules.CustomerBusinessRules;
import com.TurkishFinance.bankSystem.business.rules.corporates.CorporateCustomerBusinessRules;
import com.TurkishFinance.bankSystem.core.utilities.helper.HelperFunctions;
import com.TurkishFinance.bankSystem.core.utilities.mappers.abstracts.ModelMapperService;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.CustomerRepository;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.corporates.CorporateCustomerRepository;
import com.TurkishFinance.bankSystem.entities.Customer;
import com.TurkishFinance.bankSystem.entities.corporates.CorporateCustomer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CorporateCustomerManager implements CorporateCustomerService {

	private final CorporateCustomerRepository corporateCustomerReqpository;
	private final ModelMapperService modelMapperService;
	private final CorporateCustomerBusinessRules corporateCustomerBusinessRules;
	private final CustomerBusinessRules customerBusinessRules;
	private final HelperFunctions helperFunctions;
	private final CustomerRepository customerRepository;
	
	
	@Override
	public GetCorporateCustomerResponse getCorporateCustomer(long corporateCustomerNumber) {
		
		try {
			
			this.corporateCustomerBusinessRules.checkIfCorporateCustomerNumberExists(corporateCustomerNumber);
		}
		catch (Exception e) {
		System.out.println(e.getMessage());
		}
		CorporateCustomer corporateCustomer=this.corporateCustomerReqpository.findByCorporateCustomerNumber(corporateCustomerNumber);
        GetCorporateCustomerResponse customerResponse=this.modelMapperService.forResponse()
        		.map(corporateCustomer, GetCorporateCustomerResponse.class);
                  
		return customerResponse;
	}

	@Override
	public void addCorporateCustomer(CreateCorporateCustomerRequest createCorporateCustomerRequest) throws Exception {
	
	   this.customerBusinessRules.checkIfTcKimlikNoExists(createCorporateCustomerRequest.getTcKimlikNo());
	   //burada nüfustan veri doğrulayacağız
	   String url=UriComponentsBuilder.fromHttpUrl("http://localhost:9091/api/person/getPerson")
			   .queryParam("tcKimlikNo", createCorporateCustomerRequest.getTcKimlikNo()).toUriString();
	   
     	   Map<String,Object> responseObj= helperFunctions.get(url);
     	   System.out.println(responseObj);
     	   
		if(!responseObj.get("firstName").equals(createCorporateCustomerRequest.getFirstName())||
		   !responseObj.get("lastName").equals(createCorporateCustomerRequest.getLastName())||
		   !responseObj.get("birthPlace").equals(createCorporateCustomerRequest.getBirthPlace())) {
			throw new Exception("veriler doğrulanamadı");
		}
		else {
			CorporateCustomer corporateCustomer=this.modelMapperService.forRequest().map(createCorporateCustomerRequest, CorporateCustomer.class);
			Customer customer=new Customer();
			customer.setFirstName(createCorporateCustomerRequest.getFirstName());
			customer.setLastName(createCorporateCustomerRequest.getLastName());
			customer.setBirthDate(createCorporateCustomerRequest.getBirthDate());
			customer.setBirthPlace(createCorporateCustomerRequest.getBirthPlace());
			customer.setTcKimlikNo(createCorporateCustomerRequest.getTcKimlikNo());
			corporateCustomer.setCustomer(customer);
			corporateCustomer.setCompanyType(createCorporateCustomerRequest.getCompanyType());
			corporateCustomer.setCorporateCustomerNumber(0001);
			corporateCustomer.setCorporateName(createCorporateCustomerRequest.getCorporateName());
			corporateCustomer.setCorporatePhone(createCorporateCustomerRequest.getCorporatePhone());
			corporateCustomer.setVergiKimlikNo(createCorporateCustomerRequest.getVergiKimlikNo());
			this.customerRepository.save(customer);
			this.corporateCustomerReqpository.save(corporateCustomer);
			
		}
		
		
	}

	@Override
	public List<GetAllCorporateCustomersResponse> getAll() {
		List<CorporateCustomer> corporateCustomerList=this.corporateCustomerReqpository.findAll();
		List<GetAllCorporateCustomersResponse> corporateCustomersResponse=corporateCustomerList.stream()
				.map(corpCus->this.modelMapperService.forResponse().map(corpCus, GetAllCorporateCustomersResponse.class))
				.collect(Collectors.toList());
		return corporateCustomersResponse;
	}

}
