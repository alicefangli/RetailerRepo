package com.retailer.rewards.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class PointVO {
	@Schema(description = "Customer Id in Retailer Rewards system", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull
	private int customerId;
	
	@Schema(description = "The specific month for the customer points, can be 0 if asking a customer's totalPoints ", example = "1", requiredMode = Schema.RequiredMode.AUTO)
	@NotNull
	private int month;
	
	@Schema(description = "The points earned in one transaction", example = "5", requiredMode = Schema.RequiredMode.AUTO)
	@NotNull
	private int transPoint;
	
	@Schema(description = "The points that the customer earned in a month. 0 means either no transaction in this month or is getting totalPoints of the customer", example = "15", requiredMode = Schema.RequiredMode.AUTO)
	@NotNull
	private int monthlyPoints;
	
	@Schema(description = "Customer Id in Retailer Rewards system", example = "1", requiredMode = Schema.RequiredMode.AUTO)
	@NotNull
	private int totalPoints;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getTransPoint() {
		return transPoint;
	}

	public void setTransPoint(int transPoint) {
		this.transPoint = transPoint;
	}

	public int getMonthlyPoints() {
		return monthlyPoints;
	}

	public void setMonthlyPoints(int monthlyPoints) {
		this.monthlyPoints = monthlyPoints;
	}

	public int getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}
}
