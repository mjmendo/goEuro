package com.goeuro.javatest.mjmendo.service.impl;

/**
 * Use this class to wrap all exceptions while processing requests. 
 * Throw this class outside GoEuro challenge scope to callers to handle.
 * 
 * @author mjmendo.dev@gmail.com
 *
 */
public class GoEuroException extends RuntimeException {
	
	private static final long serialVersionUID = 8876916322357105633L;

	/**
	 * @param message
	 */
	public GoEuroException(String message) {
		super(message);
	}
	
	/**
	 * @param message the detail message (which is saved for later retrieval by the getMessage() method).
	 * @param cause the cause (which is saved for later retrieval by the getCause() method). 
	 * 				(A null value is permitted, and indicates that the cause is nonexistent or unknown.)
	 */
	public GoEuroException(String message, Throwable cause){
		super(message, cause);
	}

}
