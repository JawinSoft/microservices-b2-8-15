package org.spring.boot.msk.country.controller;

import java.util.List;

import org.spring.boot.msk.country.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.msk.common.dto.Response;
import com.spring.boot.msk.common.model.Country;

@RestController
public class CountryController {

	
	@Autowired
	private CountryService countryService;
	
	@GetMapping("/country/{country-code}")
	public Response<Country> getCountryByCode(@PathVariable("country-code") String countryCode) {
		Country country =  countryService.getCountryByCode(countryCode);		
		return Response.<Country>builder().response(country).build();
	}
	
	@GetMapping("/country/region/{region}")
	public Response<Country> getCountryByRegion(@PathVariable("region") String region) {
		Country country =  countryService.getCountryByRegion(region);
		return Response.<Country>builder().response(country).build();
	}
	
	
	@GetMapping("/country")
	public Response<List<Country>> getAllCountries() {
		List<Country> countries =  countryService.getAllCountries();		
		return Response.<List<Country>>builder().response(countries).build();
	}
	
	//Saving
	
	//update
	
	//Delete
}
