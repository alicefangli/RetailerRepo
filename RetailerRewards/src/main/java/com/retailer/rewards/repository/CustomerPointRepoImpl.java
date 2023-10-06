package com.retailer.rewards.repository;

import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.retailer.rewards.model.*;

@Repository
public class CustomerPointRepoImpl implements CustomerPointsRepo {
	
	private JdbcTemplate template;  
	
	/**Get all customers info from customer table
	 * Sql will be: select fullName, address, phone, email from customer_table;
	 * @return
	 */
	public CustomerPointRepoImpl(JdbcTemplate template) {
		this.template = template;
	}
	
	public List<Customer> getCustomers() throws DatabaseException {
		String sql = "select * from customer_table";
		CustomerMapper mapper = new CustomerMapper();
		List<Customer> list = new ArrayList<Customer>();
		try {
			list = template.query(sql, mapper);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
		
		return list;
	}
	
	//Find specific customer for given name, phone or email
	public List<Customer> findCustomerByPhoneOrNameOrEmail(String name, String phone, String email) throws DatabaseException {
		List<Customer> list = new ArrayList<>();
		Map<String, String> params = new HashMap<String, String>();
		if (name != null && !name.equals("")) {
			params.put("fullName", name);
		}
		if (phone != null && !phone.equals("")) {
			params.put("phone", phone);
		}
		if (email != null && !email.equals("")) {
			params.put("email", email);
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from customer_table where");
		if (params.get("fullName") != null) {
			sql.append(" fullName = " + params.get("fullName"));
		}
		if (params.get("phone") != null) {
			sql.append(" phone = " + params.get("phone"));
		}
		if (params.get("email") != null) {
			sql.append(" email = " + params.get("email"));
		}
		
		CustomerMapper mapper = new CustomerMapper();
		try {
			list = template.query(sql.toString(), mapper);
		} catch(Exception e) {
			throw new DatabaseException(e.getMessage());
		}
		
		return list;
	}
	
	//Get all transactions for specific customers
	public List<Transaction> getTransactions(Customer customer) throws DatabaseException {
		List<Transaction> list = new ArrayList<>();
		Integer custId = customer.getCustomerId(); //if input customer info does not contain customerId, try to find it in db 
		if (custId == null || custId == 0) {
			List<Customer> custList = this.findCustomerByPhoneOrNameOrEmail(customer.getFullName(), 
					customer.getPhoneNum(), customer.getEmail());
			if (custList!= null && !custList.isEmpty()) {
				custId = custList.get(0).getCustomerId();
			} else {
				return new ArrayList<Transaction>();
			}
		}
		String sql = "select * from transaction_table where customer_id = " + custId;
		
		TransactionMapper mapper = new TransactionMapper();
		try {
			list = template.query(sql, mapper);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
		
		
		return list;
	}
	
	//Save Transaction Points when a new transaction happens
		public int savePoints(Transaction transaction, Customer customer) throws DatabaseException {
			Integer points = 0;
			float amount = transaction.getPurchaseAmout();
			
			if (amount > 50.0 && amount <= 100.0) {
				points = (int)Math.floor(amount - 50.0);
			} else if (amount > 100.0) {
				points = (int)Math.floor(amount - 50.0) + (int)Math.floor(amount - 100.0);
			}
			Integer custId = customer.getCustomerId();
			if (custId == null || custId == 0) {
				List<Customer> custList = this.findCustomerByPhoneOrNameOrEmail(customer.getFullName(), 
						customer.getPhoneNum(), customer.getEmail());
				if (custList!= null && !custList.isEmpty()) {
					custId = custList.get(0).getCustomerId();
				} else {
					throw new DatabaseException("Customer does not exist!");
				}
			}
			//assume transaction_id is a sequence number in database
			String sql = "insert into transaction_table (customer_id, purchase_date, purchase_amount, points) "
					+ "values(" + custId + ",sysdate, " + amount + ", " + points + ")";
			try {
				template.execute(sql);
			}catch (Exception e) {
				throw new DatabaseException(e.getMessage());
			}
			return points;
		}
		
		
	//Get specific month points that the customer accumulated
	public int getMonthlyPoints(Customer customer, int month, int year) throws DatabaseException {
		Integer custId = customer.getCustomerId(); //if input customer info does not contain customerId, try to find it in db 
		if (custId == null || custId == 0) {
			List<Customer> custList = this.findCustomerByPhoneOrNameOrEmail(customer.getFullName(), 
					customer.getPhoneNum(), customer.getEmail());
			if (custList!= null && !custList.isEmpty()) {
				custId = custList.get(0).getCustomerId();
			} else {
				return 0;
			}
		}
		Date d1 = new GregorianCalendar(year, month+1, 1).getTime();
		int newYear = 0, newMonth =0;
		if (month == 12) {
			newYear = year + 1;
			newMonth = 1;
		}
		Date d2 = new GregorianCalendar(newYear, newMonth, 1).getTime();
		String sql = "select * from transaction_table where customer_id = " + custId + " and purchase_date >= " + 
		parseDate(d1.toString()) + " and purchase_date < " + parseDate(d2.toString());
		TransactionMapper mapper = new TransactionMapper();
		List<Transaction> list = new ArrayList<>();
		try {
			list = template.query(sql, mapper);
		} catch(Exception e) {
			throw new DatabaseException(e.getMessage());
		}
		int totalPoints = 0;
		for(Transaction tran: list) {
			totalPoints+= tran.getPoints();
		}
		return totalPoints;
	}
	
	 private Date parseDate(String date) {
	     try {
	         return new SimpleDateFormat("MM/DD/YY").parse(date);
	     } catch (ParseException e) {
	         return null;
	     }
	  }
	//Get total points the customer accumulated
	public int getTotalPoints(Customer customer) throws DatabaseException {
		List<Transaction> list = this.getTransactions(customer);
		int totalPoints = 0;
		if (list != null && !list.isEmpty()) {
			for(Transaction tran: list) {
				totalPoints+= tran.getPoints();
			}
			
		}
		return totalPoints;
	}
}
