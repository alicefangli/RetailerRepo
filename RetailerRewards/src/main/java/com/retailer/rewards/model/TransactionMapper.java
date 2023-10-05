package com.retailer.rewards.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TransactionMapper implements RowMapper<Transaction> {
	public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
		Transaction trans = new Transaction();
		trans.setCustomerId(rs.getInt("customer_id"));
		trans.setPurchaseDate(rs.getDate("purchase_date"));
		trans.setPurchaseAmout(rs.getFloat("purchase_amount"));
		trans.setPoints(rs.getInt("points"));
		return trans;
	}
}
