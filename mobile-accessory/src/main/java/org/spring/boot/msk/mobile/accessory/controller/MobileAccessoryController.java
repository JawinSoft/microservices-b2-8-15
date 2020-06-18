package org.spring.boot.msk.mobile.accessory.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.spring.boot.msk.mobile.accessory.model.MobileAccessory;
import org.spring.boot.msk.mobile.accessory.repository.MobileAccessoryRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("mobile-accessory")
public class MobileAccessoryController {

	@Autowired
	private MobileAccessoryRepository repository;
	
	@PutMapping
	public Mono<MobileAccessory> updateMobileAccessory(@RequestBody MobileAccessory mobileAccessory){
		mobileAccessory.setActive(true);
		return repository.save(mobileAccessory);
	}
	
	
	@PostMapping
	public Mono<MobileAccessory> saveMobileAccessory(@RequestBody MobileAccessory mobileAccessory){
		UUID uuid = UUID.randomUUID();
		mobileAccessory.setId(uuid.toString());
		mobileAccessory.setActive(true);
		return repository.save(mobileAccessory);
	}
	
	
	@GetMapping("{id}")
	public Mono<MobileAccessory>  getMobileAccessoryById(@PathVariable String id){
		return repository.findById(id);
	}
	
	
	@GetMapping("find/{mobileType}")
	public Flux<MobileAccessory>  getMobileAccessoryByName(@PathVariable String mobileType){
		return repository.findByMobileType(mobileType);
	}
	
	
	@GetMapping
	public Flux<MobileAccessory>  getAllMobileAccessory(){
		return repository.findAll();
	}
	
	
   @DeleteMapping("{id}")
   public Mono<String> deleteMobile(@PathVariable String id) {
	  
	   Mono<MobileAccessory>  mobile = getMobileAccessoryById(id);
	   
	   //block , subscribe
	   
	   Mono<String> result =  mobile.flatMap(mobileAccessory -> {
		  
	    	System.out.println("Expected to be deleted id: "+id);
		   mobileAccessory.setActive(false);
		   
		  Mono<MobileAccessory> updatedMobile = repository.save(mobileAccessory);
		  
		  return updatedMobile.flatMap( upm -> {
		  
		   return Mono.just("Successfully Deleted..!");
		  });
		   
	   });
	    
	   System.out.println("This statement will print before deleted code...!");
	   
	   
	   return result;
	   
   }
	
	
	
}
