package org.spring.boot.msk.country.exception;

import java.util.Arrays;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.spring.boot.msk.common.dto.ErrorDetail;
import com.spring.boot.msk.common.dto.Response;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Response> hadnleEmptyResultDataAccessException(EmptyResultDataAccessException exception) {
		ErrorDetail error =  ErrorDetail.builder().code(10020)
				.message("No Country Found with Given CountryCode in DataBase "+exception.getMessage()).build();
		
		
		return  ResponseEntity.badRequest().body(Response.builder().errors(Arrays.asList(error)).build());
	}

}
