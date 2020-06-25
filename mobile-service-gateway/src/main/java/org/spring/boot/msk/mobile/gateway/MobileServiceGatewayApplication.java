package org.spring.boot.msk.mobile.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class MobileServiceGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileServiceGatewayApplication.class, args);
	}

}
