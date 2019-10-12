package com.hcl.hackathon;

public class AuthException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AuthException(String errorMessage) {
		super(errorMessage);
	}
}
