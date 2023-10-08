package com.retailer.rewards.RepositoryTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.retailer.rewards.model.Transaction;
import com.retailer.rewards.repository.CustomerPointRepoImpl;
import com.retailer.rewards.repository.CustomerPointsRepo;
import com.retailer.rewards.repository.DatabaseException;

public class TestCustomerPointsRepo {
	private CustomerPointsRepo repo = new CustomerPointRepoImpl();
	private static List<Transaction> tList = new ArrayList<>();
	
	@BeforeAll
	public static void init() {
		
		Transaction tran1 = new Transaction();
		Transaction tran2 = new Transaction();
		Transaction tran3 = new Transaction();
		Transaction tran4 = new Transaction();
		
		tran1.setTransactionId(5);
		tran1.setCustomerId(2);
		tran1.setPurchaseAmout(98.78f);
		
		tran2.setTransactionId(7);
		tran2.setCustomerId(2);
		tran2.setPurchaseAmout(45.32f);	
		
		tran3.setTransactionId(8);
		tran3.setCustomerId(2);
		tran3.setPurchaseAmout(125.86f);
		
		tran4.setTransactionId(9);
		tran4.setCustomerId(2);
		tran4.setPurchaseAmout(50.0f);
		
		tList.add(tran1);
		tList.add(tran2);
		tList.add(tran3);
		tList.add(tran4);
	}
		
	@Test
	public void testSavePointsOver50() throws DatabaseException {
		int points = repo.savePoints(tList.get(0)).getTransPoint();
		Assertions.assertEquals(points, 48);
	}
	
	@Test
	public void testSavePointsUnder50() throws DatabaseException {
		int points = repo.savePoints(tList.get(1)).getTransPoint();
		Assertions.assertEquals(points, 0);
		
	}
	
	@Test
	public void testSavePointsEqual50() throws DatabaseException {
		int points = repo.savePoints(tList.get(3)).getTransPoint();
		Assertions.assertEquals(points, 0);
	}
	
	@Test
	public void testSavePointsOver100() throws DatabaseException {
		int points = repo.savePoints(tList.get(2)).getTransPoint();
		Assertions.assertEquals(points, 100);
	}
}
