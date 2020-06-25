package org.spring.boot.msk.mobile.client.dto;

import java.util.List;

import com.spring.boot.msk.common.dto.MobileAccessory;
import com.spring.boot.msk.common.model.Country;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MobileClientResponse {

    private int id;
	
	private String name;
	
	private String status;
	
	private String lob;
	
	private String publicateDate;
	
	private int price;
	
	private int port;
	
	private Country country;
		 
	private List<MobileAccessory> mobileAccessories;
	
	
	
}
