package com.hcl.hackathon;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OpenBankExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public ExceptionResponse getErrorResponse(final Exception exception, HttpServletRequest request) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorMessage(exception.getMessage());
		response.setErrorStatus(HttpStatus.BAD_REQUEST.name());
		response.setUri(request.getRequestURI());
		return response;
	}
}
