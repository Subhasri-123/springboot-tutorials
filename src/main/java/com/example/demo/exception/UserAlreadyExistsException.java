package com.example.demo.exception;

public class UserAlreadyExistsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7248559014869345341L;
	
	public UserAlreadyExistsException(String message) {
		super(message);
	}

}
