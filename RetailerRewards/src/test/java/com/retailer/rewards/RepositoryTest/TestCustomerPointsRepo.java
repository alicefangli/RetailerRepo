package com.retailer.rewards.RepositoryTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.retailer.rewards.model.Customer;
import com.retailer.rewards.model.Transaction;
import com.retailer.rewards.repository.CustomerPointRepoImpl;
import com.retailer.rewards.repository.CustomerPointsRepo;
import com.retailer.rewards.repository.DatabaseException;

public class TestCustomerPointsRepo {
	CustomerPointsRepo repo = new CustomerPointRepoImpl();
		
	@Test
	public void testSavePoints() throws DatabaseException {
		Customer customer = new Customer();
		customer.setCustomerId(2);
		customer.setFullName("David Hall");
		customer.setEmail("david.hall@example.com");
		customer.setPhoneNum("234567891");
		
		Transaction tran1 = new Transaction();
		Transaction tran2 = new Transaction();
		Transaction tran3 = new Transaction();
		
		tran1.setTransactionId(5);
		tran1.setCustomerId(2);
		tran1.setPurchaseAmout(98.78f);
		
		tran2.setTransactionId(7);
		tran2.setCustomerId(2);
		tran2.setPurchaseAmout(45.32f);	
		
		tran3.setTransactionId(8);
		tran3.setCustomerId(2);
		tran3.setPurchaseAmout(125.86f);
		
		int points = repo.savePoints(tran1);
		Assertions.assertEquals(points, 48);
		points = repo.savePoints(tran2);
		Assertions.assertEquals(points, 0);
		points = repo.savePoints(tran3);
		Assertions.assertEquals(points, 100);

	}
}
