package com.retailer.rewards.controller;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retailer.rewards.service.CustomerPointsService;

import lombok.extern.slf4j.Slf4j;

//import lombok.extern.slf4j.Slf4j;

import com.retailer.rewards.model.*;

@RestController
@RequestMapping(value="/customer")
//@Slf4j
public class CustomerPointsController {

	@Autowired
	CustomerPointsService service;  
	
	private static Logger log = LoggerFactory.getLogger(CustomerPointsController.class);
	@GetMapping(value="/customers", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Customer> getCustomers() {
		List<Customer> list = new ArrayList<>();
		try {
		list = service.getCustomers();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return list;
	}

	@GetMapping("/transactions")
	public List<Transaction> getTransactions(@RequestBody Customer customer) {
		List<Transaction> list = new ArrayList<>();
		try {
		list = service.getTransactions(customer);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return list;
	}
	
	
	@GetMapping("/monthpoints")
	public int getMonthlyPoints(@RequestBody Customer customer, int month, int year) {
		int points = 0;
		try {
			points = service.getMonthlyPoints(customer, month, year);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return points;
	}
	
	@GetMapping("/totalpoints")
	public int getTotalPoints(@RequestBody Customer customer) {
		int points = 0;
		try {
			points = service.getTotalPoints(customer);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return points;
	}
}
