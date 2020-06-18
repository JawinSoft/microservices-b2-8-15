package org.spring.boot.msk.country.service;

import java.util.List;

import org.spring.boot.msk.country.repository.CountryRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.msk.common.model.Country;

@Service
public class CountryService {

	@Autowired
	private CountryRepositoty countryRepository;
	
	public Country getCountryByCode(String countryCode) {
		//Country.builder().code(countryCode).build().isValidCountryCode();
		return countryRepository.getCountryByCode(countryCode);
		// EmptyResultDataAccessException  -->  Incorrect result size: expected 1, actual 0
		// IncorrectResultSizeDataAccessException: Incorrect result size: expected 1, actual 9
		// CountryValidationException
		// CountryNotFoundException + EmptyResultDataAccessException
	}

	public List<Country> getAllCountries() {
		return countryRepository.getAllCountries();
	}

	//IncorrectResultSizeDataAccessException
	// IND
	public Country getCountryByRegion(String region) {
		return countryRepository.getCountryByReigon(region);
	}

}
