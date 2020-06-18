package org.spring.boot.msk.mobile.client.integration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.spring.boot.msk.common.dto.MobileDto;
import com.spring.boot.msk.common.dto.Response;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MobileServiceIntegration {

	
	@Autowired
	private WebClient mobileServiceWebClient;
	
	
	
	public Flux<MobileDto> getAllMobilesFromMobileService(){
		
		Mono<Response<List<MobileDto>>> monoResponse =  mobileServiceWebClient
							.get()
							.accept(MediaType.APPLICATION_JSON)
							.retrieve()
							.bodyToMono(new ParameterizedTypeReference<Response<List<MobileDto>>>(){});
		
		/*return monoResponse.flatMapMany(response -> {
			List<MobileDto> mobiles = response.getResponse();
			return Flux.fromIterable(mobiles);
		});*/
		
		return monoResponse.
					flatMapMany(response -> 
							Flux.fromIterable(response.getResponse())
								);
	}
	
	
	
	public Mono<MobileDto> getMobileById(int mobileId){
		
		System.out.println("Thread name cccc "+Thread.currentThread().getName());
		
		Mono<Response<MobileDto>> monoResponse =  mobileServiceWebClient
											   .get() //get method
											   .uri("/{id}", mobileId) 
											   .accept(MediaType.APPLICATION_JSON)
											   .retrieve()
											   .bodyToMono(new ParameterizedTypeReference<Response<MobileDto>>(){});		
		
		//Mono[Response[MobileDto]] -> monoResponse
		//Response[MobileDto] -> response 
		
		
		/*return monoResponse.flatMap(response -> {
			
			System.out.println(response.getResponse());
			
			MobileDto mobileDto = (MobileDto)response.getResponse();
			
			
			
			return Mono.just(mobileDto);
			
		});*/
		
		
		return monoResponse.flatMap(response ->{
		
			System.out.println("Thread name 1 "+Thread.currentThread().getName());
			return Mono.just(response.getResponse());
		});
		
	}
	
	
	
	
	
}
