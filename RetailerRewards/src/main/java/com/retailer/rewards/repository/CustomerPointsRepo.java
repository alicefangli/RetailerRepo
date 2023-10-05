package com.retailer.rewards.repository;

import java.util.List;

import com.retailer.rewards.model.*;

public interface CustomerPointsRepo {
	//Get all customers info from customer table
	public List<Customer> getCustomers() throws DatabaseException;
	
	//Find specific customer for given name, phone or email
	public List<Customer> findCustomerByPhoneOrNameOrEmail(String name, String phone, String email) throws DatabaseException;
	
	//Get all transactions for specific customers
	public List<Transaction> getTransactions(Customer customer) throws DatabaseException;
	
	//Save Transaction Points when a transaction happens
	public boolean savePoints(Transaction transaction, Customer customer) throws DatabaseException;
	
	//Get specific month points that the customer accumulated
	public int getMonthlyPoints(Customer customer, int month, int year) throws DatabaseException;
	
	//Get total points the customer accumulated
	public int getTotalPoints(Customer customer) throws DatabaseException;
}
