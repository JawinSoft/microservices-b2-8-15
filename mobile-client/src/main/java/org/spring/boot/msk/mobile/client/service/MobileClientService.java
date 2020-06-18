package org.spring.boot.msk.mobile.client.service;

import java.util.List;

import org.spring.boot.msk.mobile.client.dto.MobileClientResponse;
import org.spring.boot.msk.mobile.client.integration.CountryServiceIntegration;
import org.spring.boot.msk.mobile.client.integration.MobileAccessoryIntegration;
import org.spring.boot.msk.mobile.client.integration.MobileServiceIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.msk.common.dto.MobileAccessory;
import com.spring.boot.msk.common.dto.MobileDto;
import com.spring.boot.msk.common.model.Country;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Service
public class MobileClientService {
	
	@Autowired
	private MobileServiceIntegration  mobileServiceIntegration;
	
	@Autowired
	private CountryServiceIntegration countryService;
	
	
	@Autowired
	private MobileAccessoryIntegration accessoryService;

	public Mono<MobileClientResponse> getMobileById(int mobileId) {
		
		Mono<MobileDto> monoMobileDto = mobileServiceIntegration.getMobileById(mobileId);
		
		return monoMobileDto.flatMap( mobileDto -> {
				
				
		      
			return makeCountryAndAccessoryCall(mobileDto);
		 
				
		});
		
		
		
	}

	public Flux<MobileClientResponse> getAllMobileInfo() {
		
				Flux<MobileDto> allMobiles= mobileServiceIntegration.getAllMobilesFromMobileService();
				
				return allMobiles.flatMap( mobileDto -> {
										return makeCountryAndAccessoryCall(mobileDto);
									});
	}
	
	
	private Mono<MobileClientResponse> makeCountryAndAccessoryCall(MobileDto mobileDto){
		 String countryCode = mobileDto.getCountryCode();
				
		 Mono<Country> monoCountry =  countryService.getCountryByCode(countryCode);
		 Mono<List<MobileAccessory>> monoList = 	accessoryService.getAccessoriesForMobile("ANY");
		 
		 
		 
		Mono<Tuple2<Country,List<MobileAccessory>>> zippedResponse =   Mono.zip(monoCountry, monoList);
		
		return zippedResponse.flatMap(tuple ->{
			
			
			System.out.println("Thread name "+Thread.currentThread().getName());
			
			Country country = 	tuple.getT1();
			List<MobileAccessory> mobileAccessories = tuple.getT2();
			
			
			MobileClientResponse finalResponse = MobileClientResponse
														.builder()
														.price(mobileDto.getPrice())
														.id(mobileDto.getId())
														.name(mobileDto.getName())
														.status(mobileDto.getStatus())
														.lob(mobileDto.getLob())
														.publicateDate(mobileDto.getPublicateDate())
														.build();
			
			
			
			finalResponse.setCountry(country);
			finalResponse.setMobileAccessories(mobileAccessories);
			
			return Mono.just(finalResponse);
			
		});	 
	}

}
