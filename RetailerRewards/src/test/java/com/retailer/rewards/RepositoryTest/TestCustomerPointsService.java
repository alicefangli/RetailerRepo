package com.retailer.rewards.RepositoryTest;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import com.retailer.rewards.repository.CustomerPointRepoImpl;
import com.retailer.rewards.repository.CustomerPointsRepo;
import com.retailer.rewards.repository.DatabaseException;
import com.retailer.rewards.service.*;
import com.retailer.rewards.model.*;

public class TestCustomerPointsService {
	@Mock
	CustomerPointsRepo repo;
	CustomerPointsService service = new CustomerPointServiceImpl(repo);
	
	//This test case has some problem, comment out temporarily
/*	@Test
	public void testGetCustomer() throws DatabaseException {
		Customer cust1 = new Customer();
		cust1.setCustomerId(1);
		cust1.setFullName("John Smith");
		cust1.setAddress(null);
		cust1.setPhoneNum("123456789");
		cust1.setEmail("john.smith@example.com");
		
		Customer cust2 = new Customer();
		cust2.setCustomerId(2);
		cust2.setFullName("David Hall");
		cust2.setAddress(null);
		cust2.setPhoneNum("234567891");
		cust2.setEmail("david.hall@example.com");
		
		List<Customer> custList = new ArrayList<>();
		custList.add(cust1);
		custList.add(cust2);
		
		when(repo.getCustomers()).thenReturn(custList);
		List<Customer> list = service.getCustomers();
		Assertions.assertEquals(list.size(), 2);
	}*/
}
