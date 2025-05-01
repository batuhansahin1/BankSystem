package com.TurkishFinance.bankSystem.core.utilities.mappers.abstracts;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {

	
	ModelMapper forRequest();
	ModelMapper forResponse();
}
