package com.TurkishFinance.bankSystem.business.concretes.individuals;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.TurkishFinance.bankSystem.business.abstracts.individuals.IndividualCustomerService;
import com.TurkishFinance.bankSystem.business.requests.CreateIndividualCustomerRequest;
import com.TurkishFinance.bankSystem.business.responses.GetAllIndividualCustomersResponse;
import com.TurkishFinance.bankSystem.business.responses.GetIndividualCustomerResponse;
import com.TurkishFinance.bankSystem.business.rules.CustomerBusinessRules;
import com.TurkishFinance.bankSystem.business.rules.individuals.IndividualCustomerBusinessRules;
import com.TurkishFinance.bankSystem.core.utilities.config.AppConfig;
import com.TurkishFinance.bankSystem.core.utilities.helper.HelperFunctions;
import com.TurkishFinance.bankSystem.core.utilities.mappers.abstracts.ModelMapperService;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.CustomerRepository;
import com.TurkishFinance.bankSystem.dataAccess.abstracts.individuals.IndividualCustomerRepository;
import com.TurkishFinance.bankSystem.entities.Customer;
import com.TurkishFinance.bankSystem.entities.individuals.IndividualCustomer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IndividualCustomerManager implements IndividualCustomerService {

	private final IndividualCustomerRepository individualCustomerRepository;
	private final ModelMapperService modelMapperService;
	private final IndividualCustomerBusinessRules individualCustomerBusinessRules;
	private final CustomerBusinessRules customerBusinessRules;
	private final CustomerRepository customerRepository;

	
	@Override
	public GetIndividualCustomerResponse getIndividualCustomer(String individualCustomerNumber) {
		//businessruless
		this.individualCustomerBusinessRules.checkIfCustomerNumberExists(individualCustomerNumber);
		
		IndividualCustomer individualCustomer =this.individualCustomerRepository.findByCustomerNumber(individualCustomerNumber);
		GetIndividualCustomerResponse individualCustomerResponse= this.modelMapperService.forResponse().map(individualCustomer, GetIndividualCustomerResponse.class);
		return individualCustomerResponse;
	} 

	@Override
	public void createIndividualCustomer(CreateIndividualCustomerRequest createIndividualCustomerRequest) throws Exception {
		// TODO Auto-generated method stub
		//this.customerBusinessRules.checkIfTcKimlikNoExists(createIndividualCustomerRequest.getTcKimlikNo());
		//bu varsa zaten ekleyemeyiz
		this.individualCustomerBusinessRules.checkIfTcKimlikNoExists(createIndividualCustomerRequest.getTcKimlikNo());
		
		 IndividualCustomer individualCustomer=new IndividualCustomer();
		 //veritabanında customer tablosunda bu tcye ait biri varsa onu direkt individualcustomer ile ilişkilen-
		 //diriyoruz ama artık ikisi de customer'ın bir türü olduğu için ilişkilendirmeye gerek yok
		 //çünkü bir müşteri ya individual ya da corporate olabiliyor
	     //atanmamaış boşta bir customer olamaz ya individual ya da corporate olacak
		 //Optional<Customer> optionalCustomer=this.customerRepository
			//	.findByTcKimlikNo(createIndividualCustomerRequest.getTcKimlikNo());
	
		//customerda varsa onu save etmeyelim
		//nüfusa request atılacak veri doğrulanıp devam edilecek
		
		String url = UriComponentsBuilder.fromHttpUrl("http://localhost:9091/api/person/getPerson")
		        .queryParam("tcKimlikNo",createIndividualCustomerRequest.getTcKimlikNo())
		        .toUriString();
		
		System.out.println(url);
		HelperFunctions helperFunctions=new HelperFunctions(new AppConfig().webClient());
		Map<String, Object> responseObject= helperFunctions.get(url);
		 System.out.println(responseObject);
		 System.out.println(responseObject.get("firstName").equals(createIndividualCustomerRequest.getFirstName()));
		 System.out.println(responseObject.get("lastName").equals(createIndividualCustomerRequest.getLastName()));
		 System.out.println(responseObject.get("tcKimlikNo").equals(createIndividualCustomerRequest.getTcKimlikNo()));
		 //referans tip olduğu için sıkıntı yaratıyor
		 System.out.println(responseObject.get("birthDate").equals(createIndividualCustomerRequest.getCustomerBirthDate()));
		 if(!responseObject.get("firstName").equals(createIndividualCustomerRequest.getFirstName())||
				 !responseObject.get("lastName").equals(createIndividualCustomerRequest.getLastName())||
				 !responseObject.get("tcKimlikNo").equals(createIndividualCustomerRequest.getTcKimlikNo())||
				 !responseObject.get("birthPlace").equals(createIndividualCustomerRequest.getCustomerBirthPlace())) {
			 throw new Exception("girilen veriler ile gerçek veriler eşelşmiyor kontrol edip tekrar deneyin");
			 
		 }
		 
//			if(optionalCustomer.isPresent()) {
//				//olan bir customer'ı set etmemek için bunu yapmam lazım
//				
//				//individualCustomer.setCustomer(optionalCustomer.get());
//			}
//			else {
//				// Customer customer=new Customer();
//			 //individualCustomer.setCustomer(customer);
//			}
		 
		    individualCustomer.setCustomerNumber(helperFunctions.createCustomerNumber());
			individualCustomer.setFirstName(createIndividualCustomerRequest.getFirstName());
			individualCustomer.setLastName(createIndividualCustomerRequest.getLastName());
			individualCustomer.setTcKimlikNo(createIndividualCustomerRequest.getTcKimlikNo());
			individualCustomer.setBirthDate(createIndividualCustomerRequest.getCustomerBirthDate());
			System.out.println(responseObject.get("birthPlace").toString());
			individualCustomer.setBirthPlace(createIndividualCustomerRequest.getCustomerBirthPlace());
			this.customerRepository.save(individualCustomer);
					 //this.modelMapperService.forRequest().map(createIndividualCustomerRequest, IndividualCustomer.class);
		 individualCustomer.setPhoneNumber(createIndividualCustomerRequest.getPhoneNumber());
		
		 
		 //individualCustomer.setIndividualCustomerNumber(helperFunctions.createIndividualCustomerNumber());
		 //boş liste set etmeme gerek var mı bilmiyorum ben hepsini elle set ettim mapper belki null yapmıştır listeleri
		 //sanırım customer nesnesi de oluşturmak gerekiyormuş
		 this.individualCustomerRepository.save(individualCustomer);
		 
		 }
		
	

	@Override
	public List<GetAllIndividualCustomersResponse> getAll() {
		List<IndividualCustomer> individualCustomers=this.individualCustomerRepository.findAll();
		List<GetAllIndividualCustomersResponse> individualCustomersResponse=individualCustomers.stream()
				.map(indCust->this.modelMapperService.forResponse().map(indCust,GetAllIndividualCustomersResponse.class)).collect(Collectors.toList());
		
		
		return individualCustomersResponse;
	}
    
}
