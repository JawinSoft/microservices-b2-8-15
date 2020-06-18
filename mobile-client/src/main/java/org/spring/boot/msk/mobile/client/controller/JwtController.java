package org.spring.boot.msk.mobile.client.controller;

import org.spring.boot.msk.mobile.client.dto.CreateTokenRequest;
import org.spring.boot.msk.mobile.client.service.JwtTokenService;
import org.spring.boot.msk.mobile.client.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class JwtController {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	JwtTokenService jwtService;

	@PostMapping("/create-token")
	public Mono<String> createToken(@RequestBody CreateTokenRequest tokenRequest) {
		
		Mono<UserDetails> userDetails = userDetailsService.loadUserFromUser(tokenRequest.getUserName());
		
		return userDetails.flatMap(user -> {
			
			if(passwordEncoder.matches(tokenRequest.getPassword(), user.getPassword())) {
				return jwtService.createToken(user);
			}
			
			return Mono.error(new RuntimeException("Invalid Password..!"));
			
		});
	}
	
	
}
