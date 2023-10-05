package com.retailer.rewards.repository;

public class DatabaseException extends Exception {
	private String message;
	
	DatabaseException (String msg) {
		this.message = msg;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
