package com.hcl.hackathon;

public class ExceptionResponse {

	private String errorMessage;
	private String errorStatus;
	private String uri;
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorStatus() {
		return errorStatus;
	}
	public void setErrorStatus(String errorStatus) {
		this.errorStatus = errorStatus;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
}
