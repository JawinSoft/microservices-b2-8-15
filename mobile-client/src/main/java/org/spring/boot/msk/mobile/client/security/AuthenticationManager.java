package org.spring.boot.msk.mobile.client.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.spring.boot.msk.mobile.client.service.JwtTokenService;
import org.spring.boot.msk.mobile.client.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import reactor.core.publisher.Mono;


@Component
public class AuthenticationManager implements ReactiveAuthenticationManager{

	@Autowired
	JwtTokenService jwtTokenService;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	
	@Override
	public Mono<Authentication> authenticate(Authentication authentication) {
		
		String jsonToken = authentication.getCredentials().toString();
		
		String userName = jwtTokenService.extractUsername(jsonToken);
		
		Mono<UserDetails> monoUser = userDetailsService.loadUserFromUser(userName);
		
		return monoUser.flatMap(user -> {
			
			if(!jwtTokenService.validateToken(jsonToken, user.getUsername())) {
				return Mono.empty();
			}
		
			Claims claims =	jwtTokenService.extractAllClaims(jsonToken);			
			List roles = claims.get("roles", List.class);				
			List<GrantedAuthority> authorities = new ArrayList<>();
			for(Object role:roles) {
				Map<String, String> rolesMap = (Map<String, String>) role;
				rolesMap.forEach( (k, v)  ->  {
				GrantedAuthority authority	= new  SimpleGrantedAuthority(v);				 
				 authorities.add(authority);
				
			});
				
				return Mono.just(new UsernamePasswordAuthenticationToken(user.getUsername(), null, authorities));
				 
			}

		 return Mono.empty();
		});
		
		
	}

	
	
	
	
}
