package com.example.demo.exception;

import java.util.Date;

public class CustomErrorDetail {

	private Date timestamp;
	private String message;
	private String errorDetails;
	public CustomErrorDetail(Date timestamp, String message, String errorDetails) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.errorDetails = errorDetails;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getErrorDetails() {
		return errorDetails;
	}
	
}
