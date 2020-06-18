package org.spring.boot.msk.country.exception;

public class CountryValidationException extends RuntimeException {

	public CountryValidationException(String message) {
		super(message);
	}

}
