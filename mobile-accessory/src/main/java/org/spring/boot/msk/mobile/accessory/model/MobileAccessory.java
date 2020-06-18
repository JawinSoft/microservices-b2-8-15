package org.spring.boot.msk.mobile.accessory.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document
public class MobileAccessory {
	
	@Id
	private String id;
	
	private String name;
	
	private String description;
	
	private String mobileType;

	private boolean isActive;
}
