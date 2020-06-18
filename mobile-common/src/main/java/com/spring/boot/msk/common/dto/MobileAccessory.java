package com.spring.boot.msk.common.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MobileAccessory {
	
	
	private String id;
	
	private String name;
	
	private String description;
	
	private String mobileType;

	private boolean isActive;
}
