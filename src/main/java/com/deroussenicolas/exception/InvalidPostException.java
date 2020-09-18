package com.deroussenicolas.exception;

public class InvalidPostException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public InvalidPostException(String message) {
		super("InvalidPostException"+message);
	}


}
