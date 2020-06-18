package org.spring.boot.msk.mobile.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

@Configuration
public class ApplicationConfig {
	
	@Value("${mobile-service-base-url}")
	private String mobileServiceBaseUrl;
	
	
	@Value("${country-service-base-url}")
	private String countryServiceBaseUrl;
	
	
	@Value("${mobile-accessory-base-url}")
	private String mobileAccessoryBaseUrl;
	
	
	@Bean
	public WebClient mobileServiceWebClient() {
		
		return WebClient
				.builder()
				.baseUrl(mobileServiceBaseUrl)
				.filter(basicAuthentication("MobilesUser", "MobilesUser"))
				.build();
	}
	
	
	@Bean
	public WebClient countryServiceWebClient() {
		
		return WebClient
				.builder()
				.baseUrl(countryServiceBaseUrl)
				.filter(basicAuthentication("country_user", "country_user"))
				.build();
	}
	
	

	@Bean
	public WebClient mobileAccessoryWebClient() {
		
		return WebClient.create(mobileAccessoryBaseUrl);
	}
	
	

}
