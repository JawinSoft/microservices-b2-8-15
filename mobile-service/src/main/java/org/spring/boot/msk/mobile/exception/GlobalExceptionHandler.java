package org.spring.boot.msk.mobile.exception;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.spring.boot.msk.common.dto.ErrorDetail;
import com.spring.boot.msk.common.dto.Response;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(value = MobileNotFoundException.class)
	public ResponseEntity<Response> handleMobileNotFoundException(MobileNotFoundException mnfe){
		ErrorDetail errorDetails = ErrorDetail.builder()
											   .code(10001)
											   .message(mnfe.getMessage()).build();
		
		Response response = Response.builder().errors(Arrays.asList(errorDetails)).build();
		
		return ResponseEntity.badRequest().body(response);
	}

	
	@ExceptionHandler(value=MethodArgumentNotValidException.class)
	public ResponseEntity<Response> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		
		List<ErrorDetail> errrorDetails = ex.getBindingResult().getAllErrors() //List
																.stream() //stream
																.map(error -> ErrorDetail.builder()
																						.code(10001)
																						.message(error.getDefaultMessage()).build())
																.collect(Collectors.toList());
		Response response = Response.builder().errors(errrorDetails).build();
		return ResponseEntity.badRequest().body(response);
		
	}
	
	
	@ExceptionHandler(value = Throwable.class)
	public ResponseEntity<Response> handleThrowable(Throwable t){
		log.error("SOme Exception in  APPlication ",t );
		ErrorDetail errorDetails =  ErrorDetail.builder().code(10009).message(t.getMessage()).build();
		
		Response response = Response.builder().errors(Arrays.asList(errorDetails)).build();
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

}
