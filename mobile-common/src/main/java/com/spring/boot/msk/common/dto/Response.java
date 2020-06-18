package com.spring.boot.msk.common.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
	
	private T response;
	
	private List<ErrorDetail> errors;
	

}
