package com.hcl.hackathon;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OpenBankExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public ExceptionResponse getErrorResponse(HttpServletRequest request) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorMessage("Exception occured");
		response.setErrorStatus(HttpStatus.BAD_REQUEST.name());
		return response;
	}
}
