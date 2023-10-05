package com.retailer.rewards.model;

import java.sql.Date;

public class Transaction {
	private Integer TransactionId;
	private Integer customerId;
	private Date purchaseDate;
	private float purchaseAmout;
	private Integer points;
	
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
