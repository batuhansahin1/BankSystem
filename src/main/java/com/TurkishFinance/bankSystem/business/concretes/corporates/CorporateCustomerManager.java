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

		CorporateCustomer corporateCustomer=this.corporateCustomerReqpository.findByCustomerNumber(corporateCustomerNumber);
        GetCorporateCustomerResponse customerResponse=this.modelMapperService.forResponse()
        		.map(corporateCustomer, GetCorporateCustomerResponse.class);
                  
		return customerResponse;
	}

	@Override
	public void addCorporateCustomer(CreateCorporateCustomerRequest createCorporateCustomerRequest) throws Exception {
	  //bu tcye ait bir corporatecustomer varsa yeniden oluşturamamyız
	    //  corporate customerlarda tcKimlik no almıyoruz şimdilik o yok
		//this.corporateCustomerBusinessRules.checkIfTcKimlikNoExists(createCorporateCustomerRequest.getTcKimlikNo());
	   
	   this.corporateCustomerBusinessRules.checkIfVergiKimlikNoExists(createCorporateCustomerRequest.getVergiKimlikNo());
	   //customer'da kişi bilgileri var ama corporatede yoksa yani müşteri individual daha sonra 
	   //corporate hesap açıyorsa onu customer tablosundaki veriyle ilişkilendirmemiz lazım yoksa aynı veriyi bir daha 
	   //customer tablosuna eklemiş oluruz
	   //saçma değil mi zaten customer tablosunda olan bir firmayı ben niye ekliyorum
	   //bu individual'ı bulmak için öamtıklı olabilir ama customer tablosunda artık tcKimlikNo alanı yok
	   //buna gerek yok  çünkü atanmamış müşteri bilgisi olamaz
	  // Optional<Customer> optionalCustomer=this.customerRepository
			 //  .findByTcKimlikNo(createCorporateCustomerRequest.getTcKimlikNo());
	   

	   //burada nüfustan veri doğrulayacağız
       //veritabanımızda böyle bir kayıt varsa http request'e gerek yok
	   //güncel veriyi almak için request atıyoruz
	   //şimdilik buna da gerek yok kişiye ait bir hesap olmayacak
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
		   
		//ilişki yok corporate'da bir customer ayrı tablo değil
//		if(optionalCustomer.isPresent()) {
//			//corporateCustomer.(optionalCustomer.get());   
//		   }	
//		
		///bu if elselerde mantık yoksa oluştur ve ilişkilendir varsa bul ve ilişkilendir
		///bunu düşüneceğim individualdan corporate hesaba ilişki vermek zorunda mıyım ama ilişki verirsem
		///bu her corporate customer bir bireysel müşteriye aittir demek eğer bu bilgileri customer'a çıkarırsam
		///o zaman da sahibi olmayan kurum  olduğunda kamu kurumları bu sefer de customerdaki alanlar boş kalacak
		///yani customer tablosunda null alanlar olucak
//		   else {
//			   //direkt customer bilgilerini belirleyip onu tanımlarken türüyle tanımlayıp önce customer sonra 
//			   //corporate ile ilgili verileri setleyip ilk customer'a save edicez daha sonra da bunu corporate
//			   //customer'a set edicez
//			   
//			   //ortak alanlarda kişisel bilgiler yok maalesef kişisel bilgilerle set edip oluşturamıcaz 
//			   //bir üşteri ya corporate'dir ya da individualdır
////			   Customer customer=new Customer();
////			   customer.setFirstName(createCorporateCustomerRequest.getFirstName());
////			   customer.setLastName(createCorporateCustomerRequest.getLastName());
////			   customer.setBirthDate(createCorporateCustomerRequest.getBirthDate());
////			   customer.setBirthPlace(createCorporateCustomerRequest.getBirthPlace());
////			   customer.setTcKimlikNo(createCorporateCustomerRequest.getTcKimlikNo());
////			   corporateCustomer.setCustomer(customer);
////			   this.customerRepository.save(customer);
//		   }
		  
		    corporateCustomer.setCustomerNumber(helperFunctions.createCustomerNumber());
			corporateCustomer.setCompanyType(createCorporateCustomerRequest.getCompanyType());
			//customerNumber ortak field
			//corporateCustomer.setCorporateCustomerNumber(helperFunctions.createCorporateCustomerNumber());
			corporateCustomer.setCorporateName(createCorporateCustomerRequest.getCorporateName());
			corporateCustomer.setCorporatePhone(createCorporateCustomerRequest.getCorporatePhone());
			corporateCustomer.setVergiKimlikNo(createCorporateCustomerRequest.getVergiKimlikNo());
		    this.customerRepository.save(corporateCustomer);
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
