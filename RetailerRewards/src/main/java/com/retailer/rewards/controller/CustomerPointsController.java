package com.retailer.rewards.controller;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retailer.rewards.service.CustomerPointsService;
import com.retailer.rewards.model.*;

@RestController
@RequestMapping(value="/customer")
public class CustomerPointsController {

	@Autowired
	CustomerPointsService service;  
	
	private static final Logger log = LoggerFactory.getLogger(CustomerPointsController.class);
	
	@GetMapping(value="/customers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Customer>> getCustomers() {
		List<Customer> list = new ArrayList<>();
		try {
		list = service.getCustomers();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		ResponseEntity.BodyBuilder builder = ResponseEntity.ok();
		return builder.body(list);
	}

	/*@GetMapping("/transactions")
	public List<Transaction> getTransactions(@RequestBody Customer customer) {
		List<Transaction> list = new ArrayList<>();
		try {
		list = service.getTransactions(customer);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return list;
	}
	
	@GetMapping("/monthlypoints")
	public int getMonthlyPoints(int customerId, int month) {
		int points = 0;
		try {
			points = service.getMonthlyPoints(customerId, month);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return points;
	}
	
	@GetMapping("/totalpoints/{customerId}")
	public int getTotalPoints(@PathVariable Integer customerId) {
		int points = 0;
		try {
			points = service.getTotalPoints(customerId);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return points;
	} */
}
