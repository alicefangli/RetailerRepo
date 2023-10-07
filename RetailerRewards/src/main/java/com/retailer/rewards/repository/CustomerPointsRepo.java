package com.retailer.rewards.repository;

import java.util.List;

import com.retailer.rewards.model.*;

public interface CustomerPointsRepo {
	//Get all customers info from customer table
	public List<Customer> getCustomers() throws DatabaseException;
	
	//Find specific customer for given name, phone or email
	public List<Customer> findCustomerByPhoneOrNameOrEmail(String name, String phone, String email) throws DatabaseException;
	
	//Add New Customer at first transaction
	public int addCustomer(Customer customer) throws DatabaseException;
	
	//Get all transactions for specific customers
	public List<Transaction> getTransactions(Integer custId) throws DatabaseException;
	
	//Save Transaction Points when a transaction happens
	public int savePoints(Transaction tran) throws DatabaseException;
	
	//Get specific month points that the customer accumulated
	public int getMonthlyPoints(Integer custId, int startMonth) throws DatabaseException;
	
	//Get total points the customer accumulated
	public int getTotalPoints(Integer custId) throws DatabaseException;
}
