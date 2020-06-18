package org.spring.boot.msk.country;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.spring.boot.msk.common.config.ApplicationCommonConfiguration;

@SpringBootApplication
@Import(ApplicationCommonConfiguration.class)
public class CountryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CountryServiceApplication.class, args);
	}

	
	
}
