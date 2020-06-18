package org.spring.boot.msk.mobile.client.integration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.spring.boot.msk.common.dto.MobileAccessory;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MobileAccessoryIntegration {

	
	@Autowired
	private WebClient mobileAccessoryWebClient;
	
	
	public Mono<List<MobileAccessory>> getAccessoriesForMobile(String mobileType){
		
		System.out.println("Thread name aaaa "+Thread.currentThread().getName());
		
		 return mobileAccessoryWebClient
		    .get()
		    .uri("/find/{mobileType}", mobileType)
		    .accept(MediaType.APPLICATION_JSON)
		    .retrieve()
		    .bodyToFlux(MobileAccessory.class)
		    .collectList();		
	}
	
	
	
}
