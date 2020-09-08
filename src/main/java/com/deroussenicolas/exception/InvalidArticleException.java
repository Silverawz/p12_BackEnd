package com.deroussenicolas.exception;
/**
 * Throw when article is not valid
 * 
 * @author deroussen nicolas
 *
 */
public class InvalidArticleException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidArticleException(String message) {
		super("InvalidArticleException"+message);
	}

}
