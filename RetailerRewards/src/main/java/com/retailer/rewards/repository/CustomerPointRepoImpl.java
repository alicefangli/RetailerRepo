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
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.retailer.rewards.model.Customer;
import com.retailer.rewards.model.PointVO;
//import com.retailer.rewards.model.CustomerMapper;
import com.retailer.rewards.model.Transaction;
//import com.retailer.rewards.model.TransactionMapper;

import jakarta.annotation.PostConstruct;

@Repository
public class CustomerPointRepoImpl implements CustomerPointsRepo {
	//Because database is not available, will use two lists to simulate database
	List<Customer> cList = new ArrayList<>();
	List<Transaction> transList = new ArrayList<>();
	
	@PostConstruct
	public void setUpData() {
		Customer cust1 = new Customer(1,"John Smith", "123 Main St, Palo, CA", "123456789", "john.smith@example.com" );
		Customer cust2 = new Customer(2, "David Hall", "234 Adam Ave, Bell, CA", "234567891","david.hall@example.com");
		Customer cust3 = new Customer(3, "Aaron Sade", "345 Bell St, Carlo, CA", "345678912", "aaron.sade@example.com");
		Customer cust4 = new Customer(4, "Bob Adams", "456 Charlie St, Dell, CA", "456789123", "bob.adams@example.com");
		
		cList.add(cust1);
		cList.add(cust2);
		cList.add(cust3);
		cList.add(cust4);
		
		Date d1 = new GregorianCalendar(2023, 7, 2).getTime();
		Transaction t1 = new Transaction(1, 1, d1, 48.45f, 0);
		d1 = new GregorianCalendar(2023, 7, 8).getTime();
		Transaction t2 = new Transaction(2, 1, d1, 59.31f, 9);
		d1 = new GregorianCalendar(2023, 8, 15).getTime();
		Transaction t3 = new Transaction(3, 1, d1, 111.5f, 72);
		d1 = new GregorianCalendar(2023, 8, 23).getTime();
		Transaction t4 = new Transaction(4, 1, d1, 68.7f, 18);
		transList.add(t1);
		transList.add(t2);
		transList.add(t3);
		transList.add(t4);
	}
	
//	private JdbcTemplate template;  
	
	/**Get all customers info from customer table
	 * Sql will be: select fullName, address, phone, email from customer_table;
	 * @return
	 */
/*	public CustomerPointRepoImpl(JdbcTemplate template) {
		this.template = template;
	}*/
	
	public List<Customer> getCustomers() throws DatabaseException {
		String sql = "select * from customer_table";
	//	CustomerMapper mapper = new CustomerMapper();
		List<Customer> list = new ArrayList<Customer>();
		try {
		//	list = template.query(sql, mapper);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
		list = cList;
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
		
	//	CustomerMapper mapper = new CustomerMapper();
		try {
		//	list = template.query(sql.toString(), mapper);
		} catch(Exception e) {
			throw new DatabaseException(e.getMessage());
		}
		
		return list;
	}
	
	//Add New Customer at first transaction
	public int addCustomer(Customer customer) throws DatabaseException {
		String sql = "insert into customer_table (fullName, address, phone, email) values(" + 
	customer.getFullName() + ", " + customer.getAddress()+ ", " + customer.getPhoneNum() + ", " +
				customer.getEmail() + ")";
		// after insert, try to get the customerId
		int custId = 0;
		try {
		//	template.execute(sql);
			List<Customer> list = this.findCustomerByPhoneOrNameOrEmail(customer.getFullName(), customer.getPhoneNum(), customer.getEmail());
			if (list!= null && !list.isEmpty()) {
				custId = list.get(0).getCustomerId();
			}
		} catch(Exception e) {
			throw new DatabaseException(e.getMessage());
		}
		return custId;
	}
	
	//Get all transactions for specific customers
	public List<Transaction> getTransactions(Integer custId) throws DatabaseException {
		List<Transaction> list = new ArrayList<>();
		String sql = "select * from transaction_table where customer_id = " + custId;
		
//		TransactionMapper mapper = new TransactionMapper();
		try {
		//	list = template.query(sql, mapper);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
		
		for (int i=0; i<transList.size(); i++) {
			Transaction t = transList.get(i);
			if (t.getCustomerId().equals(custId)) {
				list.add(t);
			}		
		}
		return list;
	}
	
	//Save Transaction Points when a new transaction happens
	public PointVO savePoints(Transaction transaction) throws DatabaseException {
			Integer points = 0;
			float amount = transaction.getPurchaseAmout();
			
			if (amount > 50.0 && amount <= 100.0) {
				points = (int)Math.floor(amount - 50.0);
			} else if (amount > 100.0) {
				points = (int)Math.floor(amount - 50.0) + (int)Math.floor(amount - 100.0);
			}
			Integer custId = transaction.getCustomerId();
			
			/*assume transaction_id is a sequence number in database
			String sql = "insert into transaction_table (customer_id, purchase_date, purchase_amount, points) "
					+ "values(" + custId + ",sysdate, " + amount + ", " + points + ")";
			try {
			//	template.execute(sql);
			}catch (Exception e) {
				throw new DatabaseException(e.getMessage());
			} */
			PointVO vo = new PointVO();
			vo.setCustomerId(custId);
			vo.setTransPoint(points);
			return vo;
		}
		
		
	//Get specific month points that the customer accumulated
	public PointVO getMonthlyPoints(Integer custId, int month) throws DatabaseException {
		Date d1 = new GregorianCalendar(2023, month-1, 1).getTime();
		
		Date d2 = new GregorianCalendar(2023, month, 1).getTime();
/*		String sql = "select * from transaction_table where customer_id = " + custId + " and purchase_date >= " + 
		parseDate(d1.toString()) + " and purchase_date < " + parseDate(d2.toString());
		TransactionMapper mapper = new TransactionMapper();*/
		List<Transaction> list = new ArrayList<>();
		try {
			//list = template.query(sql, mapper);
		} catch(Exception e) {
			throw new DatabaseException(e.getMessage());
		}
		int totalPoints = 0;
		for(int i=0; i<transList.size(); i++) {
			Transaction tran = transList.get(i);
			Date d = tran.getPurchaseDate();
			if (tran.getCustomerId().equals(custId) && (d.after(d1) ||d.equals(d1)) && d.before(d2)) {
				totalPoints+= tran.getPoints();
			}
			
		}
		PointVO vo = new PointVO();
		vo.setCustomerId(custId);
		vo.setMonth(month);
		vo.setMonthlyPoints(totalPoints);;
		return vo;
	}
	
	/* private Date parseDate(String date) {
	     try {
	         return new SimpleDateFormat("MM/DD/YYYY").parse(date);
	     } catch (ParseException e) {
	         return null;
	     }
	  } */
	
	//Get total points the customer accumulated
	public PointVO getTotalPoints(Integer custId) throws DatabaseException {
		List<Transaction> list = this.getTransactions(custId);
		int totalPoints = 0;
		if (list != null && !list.isEmpty()) {
			for(Transaction tran: list) {
				totalPoints+= tran.getPoints();
			}
			
		}
		PointVO vo = new PointVO();
		vo.setCustomerId(custId);
		vo.setTotalPoints(totalPoints);;
		return vo;
	}
}
