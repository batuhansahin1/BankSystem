package com.TurkishFinance.bankSystem.business.concretes.corporates;

import java.util.List;
import java.util.Map;
import java.util.Optional;
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
	public GetCorporateCustomerResponse getCorporateCustomer(String corporateCustomerNumber) {
		
	
			
			this.corporateCustomerBusinessRules.checkIfCorporateCustomerNumberExists(corporateCustomerNumber);

		CorporateCustomer corporateCustomer=this.corporateCustomerReqpository.findByCorporateCustomerNumber(corporateCustomerNumber);
        GetCorporateCustomerResponse customerResponse=this.modelMapperService.forResponse()
        		.map(corporateCustomer, GetCorporateCustomerResponse.class);
                  
		return customerResponse;
	}

	@Override
	public void addCorporateCustomer(CreateCorporateCustomerRequest createCorporateCustomerRequest) throws Exception {
	  //bu tcye ait bir corporatecustomer varsa yeniden oluşturamamyız
	   this.corporateCustomerBusinessRules.checkIfTcKimlikNoExists(createCorporateCustomerRequest.getTcKimlikNo());
	   
	   //customer'da kişi bilgileri var ama corporatede yoksa yani müşteri individual daha sonra 
	   //corporate hesap açıyorsa onu customer tablosundaki veriyle ilişkilendirmemiz lazım yoksa aynı veriyi bir daha 
	   //customer tablosuna eklemiş oluruz
	   Optional<Customer> optionalCustomer=this.customerRepository
			   .findByTcKimlikNo(createCorporateCustomerRequest.getTcKimlikNo());
	   

	   //burada nüfustan veri doğrulayacağız
       //veritabanımızda böyle bir kayıt varsa http request'e gerek yok
	   //güncel veriyi almak için request atıyoruz
	   String url=UriComponentsBuilder.fromHttpUrl("http://localhost:9091/api/person/getPerson")
			   .queryParam("tcKimlikNo", createCorporateCustomerRequest.getTcKimlikNo()).toUriString();
	   
     	   Map<String,Object> responseObj= helperFunctions.get(url);
     	   System.out.println(responseObj);
     	   
		if(!responseObj.get("firstName").equals(createCorporateCustomerRequest.getFirstName())||
		   !responseObj.get("lastName").equals(createCorporateCustomerRequest.getLastName())||
		   !responseObj.get("birthPlace").equals(createCorporateCustomerRequest.getBirthPlace())) {
			//validation exception
			throw new Exception("veriler doğrulanamadı");
		}
		
		CorporateCustomer corporateCustomer=this.modelMapperService.forRequest().map(createCorporateCustomerRequest, CorporateCustomer.class);
		   if(optionalCustomer.isPresent()) {
			corporateCustomer.setCustomer(optionalCustomer.get());   
		   }	
		
		   else {
			   
			   Customer customer=new Customer();
			   customer.setFirstName(createCorporateCustomerRequest.getFirstName());
			   customer.setLastName(createCorporateCustomerRequest.getLastName());
			   customer.setBirthDate(createCorporateCustomerRequest.getBirthDate());
			   customer.setBirthPlace(createCorporateCustomerRequest.getBirthPlace());
			   customer.setTcKimlikNo(createCorporateCustomerRequest.getTcKimlikNo());
			   corporateCustomer.setCustomer(customer);
			   this.customerRepository.save(customer);
		   }
			corporateCustomer.setCompanyType(createCorporateCustomerRequest.getCompanyType());
			corporateCustomer.setCorporateCustomerNumber(helperFunctions.createCorporateCustomerNumber());
			corporateCustomer.setCorporateName(createCorporateCustomerRequest.getCorporateName());
			corporateCustomer.setCorporatePhone(createCorporateCustomerRequest.getCorporatePhone());
			corporateCustomer.setVergiKimlikNo(createCorporateCustomerRequest.getVergiKimlikNo());
			this.corporateCustomerReqpository.save(corporateCustomer);
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
