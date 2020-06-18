package com.spring.boot.msk.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder 
public class MobileDto {
	
	private int id;
	
	private String name;
	
	private String status;
	
	private String lob;
	
	private String countryCode;
	
	private String publicateDate;
	
	private int price;

}
