package com.TurkishFinance.bankSystem.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetCorporateAddressResponse {

	private String corporateAddressNumber;
	private String addressCity;
	private String addressCounty;
	private String addressNeighborhood;
	private String addressStreet;
	private String addressHouseNumber;
	private String addressGateNumber;
}
