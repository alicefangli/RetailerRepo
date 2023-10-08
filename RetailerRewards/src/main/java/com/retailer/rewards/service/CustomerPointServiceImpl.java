package com.retailer.rewards.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retailer.rewards.model.Customer;
import com.retailer.rewards.model.PointVO;
import com.retailer.rewards.model.Transaction;
import com.retailer.rewards.repository.CustomerPointsRepo;
import com.retailer.rewards.repository.DatabaseException;

@Service
public class CustomerPointServiceImpl implements CustomerPointsService {
	private CustomerPointsRepo repo;
	
	public CustomerPointServiceImpl(CustomerPointsRepo repo) {
		this.repo = repo;
	}
	
	@Override
	public List<Customer> getCustomers() throws DatabaseException {
		return repo.getCustomers(); 
	}
	
	@Override
	public List<Customer> findCustomerByPhoneOrNameOrEmail(String name, String phone, String email) throws DatabaseException {
		// TODO Auto-generated method stub
		return repo.findCustomerByPhoneOrNameOrEmail(name, phone, email);
	}

	@Override
	public List<Transaction> getTransactions(Integer custId) throws DatabaseException {
		// TODO Auto-generated method stub
		return repo.getTransactions(custId);
	}

	@Override
	public PointVO savePoints(Transaction trans) throws DatabaseException {
		// TODO Auto-generated method stub
		return repo.savePoints(trans);
	}

	@Override
	public PointVO getMonthlyPoints(Integer custId, int month) throws DatabaseException {
		// TODO Auto-generated method stub
		return repo.getMonthlyPoints(custId, month);
	}

	@Override
	public PointVO getTotalPoints(Integer custId) throws DatabaseException{
		// TODO Auto-generated method stub
		return repo.getTotalPoints(custId);
	}

}
