package org.spring.boot.msk.mobile.exception;

//@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Mobile Not Found")
public class MobileNotFoundException extends RuntimeException{

	private int errorCode;
	public MobileNotFoundException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
	public int getErrorCode() {
		return errorCode;
	}

	
	
}
