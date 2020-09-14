package com.deroussenicolas.exception;

public class InvalidTopicException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidTopicException(String message) {
		super("InvalidTopicException"+message);
	}


}
