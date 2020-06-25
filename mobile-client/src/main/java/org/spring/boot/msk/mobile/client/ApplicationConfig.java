package org.spring.boot.msk.mobile.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancerExchangeFilterFunction;
import org.springframework.cloud.client.loadbalancer.reactive.ReactiveLoadBalancer;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

import java.net.URI;

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
				.baseUrl(mobileServiceBaseUrl+"/msk/mobiles")
				.filter(basicAuthentication("MobilesUser", "MobilesUser"))
				.build();
	}
	
	
	@Bean
	public WebClient countryServiceWebClient() {
		return WebClient
				.builder()
				.baseUrl(countryServiceBaseUrl+"/msk/country")
				.filter(basicAuthentication("country_user", "country_user"))
				.build();
	}
	
	

	@Bean
	public WebClient mobileAccessoryWebClient() {
		return WebClient
				.builder()
				.baseUrl(mobileAccessoryBaseUrl+"/mobile-accessory")
				.build();
	}
	
	

}
