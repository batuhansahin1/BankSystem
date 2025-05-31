package com.TurkishFinance.bankSystem.webApi.corporates;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.TurkishFinance.bankSystem.business.abstracts.corporates.DrawingAccountService;
import com.TurkishFinance.bankSystem.business.requests.CreateCorporateAccountRequest;
import com.TurkishFinance.bankSystem.business.requests.UpdateCorporateAccountRequest;
import com.TurkishFinance.bankSystem.business.responses.GetAllCorporateAccountsResponse;
import com.TurkishFinance.bankSystem.business.responses.GetCorporateAccountResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/drawingAccounts")
@AllArgsConstructor
public class DrawingAccountsController {

	private final DrawingAccountService drawingAccountService;
	
	@GetMapping("/get")
	public GetCorporateAccountResponse get(String accountNumber) {

		return this.drawingAccountService.getCorporateAccount(accountNumber);
	}
	
	@GetMapping("/getAll")
	public List<GetAllCorporateAccountsResponse> getAll(){
	
		return this.drawingAccountService.getAll();
	}
	
	@PostMapping("/add")
	public void add(CreateCorporateAccountRequest createCorporateAccountRequest) throws Exception {
		
		this.drawingAccountService.add(createCorporateAccountRequest);
	}
	
	@DeleteMapping("delete")
	public void delete(String accountNumber) {
		
		this.drawingAccountService.delete(accountNumber);
	}
	@PutMapping("update")
	public void update(UpdateCorporateAccountRequest updateCorporateAccountRequest) {
		this.drawingAccountService.update(updateCorporateAccountRequest);
	}
}
