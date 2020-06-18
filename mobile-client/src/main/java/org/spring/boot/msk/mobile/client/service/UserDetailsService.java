package org.spring.boot.msk.mobile.client.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class UserDetailsService {
	
	
	
	public Mono<UserDetails> loadUserFromUser(String userName) {
		
		//Mongo DB Loads the User Info
		
		return Mono.just(User.builder().username("user").password("$2y$12$Ew9n6.Ia64mfD.M1.JNnCOqxxEBVN4tziAMMv9DsxxbfxRnpDY/De").roles("USER").build());
		
		
		
		
	}

}
