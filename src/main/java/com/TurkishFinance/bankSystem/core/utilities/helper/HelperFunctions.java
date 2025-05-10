package com.TurkishFinance.bankSystem.core.utilities.helper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.TurkishFinance.bankSystem.entities.corporates.CorporateCustomer;
import com.TurkishFinance.bankSystem.entities.individuals.IndividualCustomer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Component
public class HelperFunctions {

	private final WebClient webClient;
	
	public String createAccountNumber() {
		String accountNumber="";
		for(int i=0;i<16;i++) {
			accountNumber+=Math.round(Math.random()*9);
		}
		return accountNumber;
	}
	public String createIndividualCustomerNumber() {
		String customerNumber="";
		for(int i=0;i<12;i++) {
			customerNumber+=Math.round(Math.random()*9);
		}
		
		return customerNumber;
	}
	public String createCorporateCustomerNumber() {
		String customerNumber="";
		for(int i=0;i<13;i++) {
			customerNumber+=Math.round(Math.random()*9);
		}
		
		return customerNumber;
	}
	//gneric olarak post ve get request oluşturacağız
	public String urlBuilder(String url,String parameter) {
		String url1 = UriComponentsBuilder.fromHttpUrl(url)
		        .queryParam("tcKimlikNo",parameter)
		        .toUriString();
		return url1;
	}

	public <T> T getCustomer(String url,Class<T> classInstance){
		

		
		//bu istediğimiz tipte get request yapıcak ama biz bunu dışarıdaki bir apiden yapacağımız için json alsak daha mantıklı return olarak 
		//string dönelim json'a çevirip setleriz
		return  webClient.get().uri(url).retrieve().bodyToMono(classInstance).block();
	}
	public Map<String, Object> get(String url) {
		Map<String, Object>response= webClient.get().uri(url).retrieve().bodyToMono(new ParameterizedTypeReference <Map<String,Object>>(){}).block();
	
	return response;
	}
	public Map<String,Object> postAccount(String url) throws JsonProcessingException {
		
		//ObjectMapper mapper=new ObjectMapper();
		//String json=mapper.writeValueAsString(requestObject);
		Map<String,Object> response=webClient.post().uri(url).retrieve().bodyToMono(new ParameterizedTypeReference<Map<String,Object>>() {
		}).block();
		
		return response;
	}
	
	//boşuna upraşıyorum post request'i sadece tKimlikNo ile yapıcaz ve gelen response'yi döndürücez yok create requestte istenilen her şeyi göndermemiz lazım
	public void post(String url,CorporateCustomer customer) throws JsonProcessingException {
		ObjectMapper mapper=new ObjectMapper();
		Map<String, Object> map=new HashMap<>();
		//bu alttaki genelde kullanılan alttakileri genelleştiricem bu postu individual'ın kullanabilmesi için de yazıcam 
		map.put("firstName",customer.getCustomer().getFirstName());
		map.put("lastName",customer.getCustomer().getLastName());
		map.put("tcKimlikNo",customer.getCustomer().getTcKimlikNo());
		map.put("birthDate", customer.getCustomer().getBirthDate());
		String json=mapper.writeValueAsString(map);
		Map<String,Object>response=webClient.post().uri(url).bodyValue(json).retrieve().bodyToMono(new ParameterizedTypeReference<Map<String,Object>>() {}).block();
	//response başarılı mı döndü null mu ona görehata yönetimi var
	
	}
	public void postIndividual(String url,IndividualCustomer customer) throws JsonProcessingException {
		ObjectMapper mapper=new ObjectMapper();
		Map<String, Object> map=new HashMap<>();
		//bu alttaki genelde kullanılan alttakileri genelleştiricem bu postu individual'ın kullanabilmesi için de yazıcam 
		map.put("firstName",customer.getCustomer().getFirstName());
		map.put("lastName",customer.getCustomer().getLastName());
		map.put("tcKimlikNo",customer.getCustomer().getTcKimlikNo());
		map.put("birthDate", customer.getCustomer().getBirthDate());
		String json=mapper.writeValueAsString(map);
		Map<String,Object>response=webClient.post().uri(url).bodyValue(json).retrieve().bodyToMono(new ParameterizedTypeReference<Map<String,Object>>() {}).block();
	//response başarılı mı döndü null mu ona görehata yönetimi var
	
	}
}
