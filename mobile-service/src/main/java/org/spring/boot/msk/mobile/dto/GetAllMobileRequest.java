package org.spring.boot.msk.mobile.dto;

import lombok.Data;

@Data
public class GetAllMobileRequest {

	private String name;
	
	private Integer price;
	
	private String status;
	
	private String lob;

}
