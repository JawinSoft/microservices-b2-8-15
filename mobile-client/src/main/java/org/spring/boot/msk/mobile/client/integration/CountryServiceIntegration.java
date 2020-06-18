package org.spring.boot.msk.mobile.client.integration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.spring.boot.msk.common.dto.Response;
import com.spring.boot.msk.common.model.Country;

import reactor.core.publisher.Mono;

@Service
public class CountryServiceIntegration {

	
	@Autowired
	private WebClient countryServiceWebClient;
	
	
	public Mono<Country> getCountryByCode(String countryCode){
		
		System.out.println("Thread name bbbbb "+Thread.currentThread().getName());
	
		 Mono<Response<Country>> monoResponse =countryServiceWebClient
									.get()
									.uri("/{country-code}", countryCode)
									.accept(MediaType.APPLICATION_JSON)
									.retrieve()
									.bodyToMono(new ParameterizedTypeReference<Response<Country>>() {});
									
									
		 return monoResponse.flatMap(response ->{
				
				System.out.println("Thread name 2 "+Thread.currentThread().getName());
				return Mono.just(response.getResponse());
			});
			
		
	}
	
	
	
}
