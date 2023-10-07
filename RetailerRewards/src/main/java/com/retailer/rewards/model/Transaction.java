package com.retailer.rewards.model;

import java.util.Date;

public class Transaction {
	private Integer TransactionId;
	private Integer customerId;
	private Date purchaseDate;
	private float purchaseAmout;
	private Integer points;
	
	public Transaction() {}
	
	public Transaction(Integer transId, Integer custId, Date d, float amount, Integer p) {
		this.TransactionId = transId;
		this.customerId = custId;
		this.purchaseDate = d;
		this.purchaseAmout = amount;
		this.points = p;
	}
	public Integer getTransactionId() {
		return TransactionId;
	}
	public void setTransactionId(Integer transactionId) {
		TransactionId = transactionId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customer_id) {
		this.customerId = customer_id;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public float getPurchaseAmout() {
		return purchaseAmout;
	}
	public void setPurchaseAmout(float purchaseAmout) {
		this.purchaseAmout = purchaseAmout;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	
}
