package com.csw.model;

import java.time.LocalDateTime;

public class ErrorInfo {
	private String errorMessage;
	private LocalDateTime timestamp;
	private Integer errorcode;
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	public Integer getErrorcode() {
		return errorcode;
	}
	
	public void setErrorcode(Integer errorcode) {
		this.errorcode = errorcode;
	}
}