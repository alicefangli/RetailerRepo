package com.retailer.rewards.model;

public class Customer {
	private Integer customerId;
	private String fullName;
	private String address;
	private String phoneNum;
	private String email;
	
	public Customer() {}
	
	public Customer(Integer customerId, String name, String address, String phone, String email) {
		this.customerId = customerId;
		this.fullName = name;
		this.address = address;
		this.phoneNum = phone;
		this.email = email;
	}
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	
	
}
