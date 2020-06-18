package org.spring.boot.msk.mobile.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor 
public class CreateTokenRequest {
	
	private String userName;
	
	private String password;

}
