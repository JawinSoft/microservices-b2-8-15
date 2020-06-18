package org.spring.boot.msk.mobile.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SaveMobileRequest {

	@NotBlank(message = "{NotBalnk.SaveMoblieRequest.name}")
	private String name;
	
	@Min(value = 1, message = "{Min.SaveMoblieRequest.price}")
	private int price;
	
	@NotBlank(message = "{NotBalnk.SaveMoblieRequest.status}")
	private String status;
	
	@NotBlank(message = "{NotBalnk.SaveMoblieRequest.lob}")
	private String lob;
	
	@NotBlank(message = "{NotBalnk.SaveMoblieRequest.countryCode}")
	private String countryCode;	
	
	
}
