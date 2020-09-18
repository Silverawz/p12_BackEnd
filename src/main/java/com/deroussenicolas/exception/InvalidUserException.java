package com.deroussenicolas.exception;
/**
 * Throw when user is not valid
 * 
 * @author deroussen nicolas
 *
 */
public class InvalidUserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidUserException(String message) {
		super("InvalidUserException "+message);
	}
}
