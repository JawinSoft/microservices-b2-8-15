package org.spring.boot.msk.mobile.client.controller;

import org.spring.boot.msk.mobile.client.dto.MobileClientResponse;
import org.spring.boot.msk.mobile.client.service.MobileClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("mobile-client")
public class MobileClientController {
	
  @Autowired
  private MobileClientService service;

	@GetMapping("{mobile-id}")
	public Mono<MobileClientResponse>  getMobileInfoById(@PathVariable("mobile-id") int mobileId){
		
		return service.getMobileById(mobileId);
	}
	
	@GetMapping
	public Flux<MobileClientResponse>  getAllMobileInfo(){			
		return service.getAllMobileInfo();
	}
	
	
}
