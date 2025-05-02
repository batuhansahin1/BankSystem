package com.TurkishFinance.bankSystem.core.utilities.helper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import com.TurkishFinance.bankSystem.entities.corporates.CorporateCustomer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HelperFunctions {

	private final WebClient webClient;
	
	//gneric olarak post ve get request oluşturacağız
	

	public <T> T get(String url,Class<T> classInstance){
		
		//bu istediğimiz tipte get request yapıcak ama biz bunu dışarıdaki bir apiden yapacağımız için json alsak daha mantıklı return olarak 
		//string dönelim json'a çevirip setleriz
		return  webClient.get().uri(url).retrieve().bodyToMono(classInstance).block();
	}
	public Map<String, Object> get(String url) {
		Map<String, Object>response= webClient.get().uri(url).retrieve().bodyToMono(new ParameterizedTypeReference <Map<String,Object>>(){}).block();
	
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
}
