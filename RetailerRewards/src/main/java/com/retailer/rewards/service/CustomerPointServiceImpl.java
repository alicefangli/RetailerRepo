package com.retailer.rewards.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retailer.rewards.model.Customer;
import com.retailer.rewards.model.Transaction;
import com.retailer.rewards.repository.CustomerPointsRepo;
import com.retailer.rewards.repository.DatabaseException;

@Service
public class CustomerPointServiceImpl implements CustomerPointsService {
	@Autowired
	CustomerPointsRepo repo;
	
	@Override
	public List<Customer> getCustomers() throws DatabaseException {
		// TODO Auto-generated method stub
		return repo.getCustomers();
	}

	@Override
	public List<Customer> findCustomerByPhoneOrNameOrEmail(String name, String phone, String email) throws DatabaseException {
		// TODO Auto-generated method stub
		return repo.findCustomerByPhoneOrNameOrEmail(name, phone, email);
	}

	@Override
	public List<Transaction> getTransactions(Customer customer) throws DatabaseException {
		// TODO Auto-generated method stub
		return repo.getTransactions(customer);
	}

	@Override
	public boolean savePoints(Transaction transaction, Customer customer) throws DatabaseException {
		// TODO Auto-generated method stub
		return repo.savePoints(transaction, customer);
	}

	@Override
	public int getMonthlyPoints(Customer customer, int month, int year) throws DatabaseException {
		// TODO Auto-generated method stub
		return repo.getMonthlyPoints(customer, month, year);
	}

	@Override
	public int getTotalPoints(Customer customer)  throws DatabaseException{
		// TODO Auto-generated method stub
		return repo.getTotalPoints(customer);
	}

}
