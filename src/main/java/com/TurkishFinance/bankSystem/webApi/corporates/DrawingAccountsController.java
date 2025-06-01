package com.TurkishFinance.bankSystem.webApi.corporates;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.TurkishFinance.bankSystem.business.abstracts.corporates.DrawingAccountService;

import com.TurkishFinance.bankSystem.business.requests.CreateDrawingAccountRequest;

import com.TurkishFinance.bankSystem.business.requests.UpdateDrawingAccountRequest;

import com.TurkishFinance.bankSystem.business.responses.GetAllDrawingAccountsResponse;

import com.TurkishFinance.bankSystem.business.responses.GetDrawingAccountResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/drawingAccounts")
@AllArgsConstructor
public class DrawingAccountsController {

	private final DrawingAccountService drawingAccountService;
	
	@GetMapping("/get")
	public GetDrawingAccountResponse get(String accountNumber) {

		return this.drawingAccountService.getCorporateAccount(accountNumber);
	}
	
	@GetMapping("/getAll")
	public List<GetAllDrawingAccountsResponse> getAll(){
	
		return this.drawingAccountService.getAll();
	}
	
	@PostMapping("/add")
	public void add(CreateDrawingAccountRequest createDrawingAccountRequest) throws Exception {
		
		this.drawingAccountService.add(createDrawingAccountRequest);
	}
	
	@DeleteMapping("delete")
	public void delete(String accountNumber) {
		
		this.drawingAccountService.delete(accountNumber);
	}
	@PutMapping("update")
	public void update(UpdateDrawingAccountRequest updateDrawingAccountRequest) {
		this.drawingAccountService.update(updateDrawingAccountRequest);
	}
}
