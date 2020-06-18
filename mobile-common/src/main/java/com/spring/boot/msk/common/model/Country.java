package com.spring.boot.msk.common.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Country {
	
	private String code;
	
	private String name;
	
	private String description;
	
	private String continent;
	
	private String region;
	
	private long population;


}
