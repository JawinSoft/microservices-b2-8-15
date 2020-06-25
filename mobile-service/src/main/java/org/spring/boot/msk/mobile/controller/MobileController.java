	package org.spring.boot.msk.mobile.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.spring.boot.msk.mobile.dto.GetAllMobileRequest;
import org.spring.boot.msk.mobile.dto.SaveMobileRequest;
import org.spring.boot.msk.mobile.service.MobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.msk.common.aop.LogExecutionTime;
import com.spring.boot.msk.common.dto.MobileDto;
import com.spring.boot.msk.common.dto.Response;


@RestController
@RequestMapping("mobiles")
public class MobileController {
	
	@Autowired
	private MobileService mobileService;
	
	@Autowired
	private Environment environment;
	
	@GetMapping
	public Response<List<MobileDto>> getAllMobiles(GetAllMobileRequest inputRequest){
			List<MobileDto> mobiles =  mobileService.getAllMobiles(inputRequest);
		return Response.<List<MobileDto>>builder().response(mobiles).build();	
	}

	@GetMapping(value="{mobile-id}", produces = {
			MediaType.APPLICATION_JSON_VALUE, 
			//MediaType.APPLICATION_XML_VALUE			
	})
	public Response<MobileDto> getMobileById(@PathVariable("mobile-id") @Min(value = 1, message="{Min.SaveMoblieRequest.price}") int mobileId ) {
		System.out.println("MobileController  :: getMobileById ");
		System.out.println("Server Port : "+environment.getProperty("local.server.port"));
		MobileDto mobile = mobileService.getMobileById(mobileId);
		mobile.setPortNumber(Integer.parseInt(environment.getProperty("local.server.port")));
				
		return Response.<MobileDto>builder().response(mobile).build();
	}
	
	@PostMapping( consumes = {
			MediaType.APPLICATION_JSON_VALUE, 
			MediaType.APPLICATION_XML_VALUE			
	})
	@ResponseStatus(value = HttpStatus.CREATED)
	public Response<String> saveMobile(@RequestBody @Valid SaveMobileRequest saveMobileRequest) {
		System.out.println("mobile : "+saveMobileRequest);
	   mobileService.saveMobile(saveMobileRequest);
	   
	   return Response.<String>builder().response("Mobile Save Successfully").build();
	}
	
	@LogExecutionTime
	@PutMapping
	public Response<MobileDto> updateMobile(@RequestBody MobileDto fromMobile) {
		System.out.println("mobile : "+fromMobile);
		 MobileDto toMobile =  mobileService.updateMobile(fromMobile);
		 return Response.<MobileDto>builder().response(toMobile).build();
	}
	
	@LogExecutionTime
	@DeleteMapping("/{mobileId}")
	public Response<Boolean> deleteMobileById(@PathVariable int mobileId) {
		
		  return Response.<Boolean>builder().response(true).build();
	}
	
	
}
