package com.retailer.rewards.repository;

public class DatabaseException extends Exception {
	private static final long serialVersionUID = 1234444444444444456l;
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
