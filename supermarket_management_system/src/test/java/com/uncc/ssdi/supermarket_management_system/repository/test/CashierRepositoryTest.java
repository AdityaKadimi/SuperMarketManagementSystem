package com.uncc.ssdi.supermarket_management_system.repository.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uncc.ssdi.supermarket_management_system.SupermarketManagementSystemApplication;
import com.uncc.ssdi.supermarket_management_system.entity.Admin;
import com.uncc.ssdi.supermarket_management_system.entity.Cashier;
import com.uncc.ssdi.supermarket_management_system.entity.Product;
import com.uncc.ssdi.supermarket_management_system.entity.Transactions;
import com.uncc.ssdi.supermarket_management_system.repository.AdminRepository;
import com.uncc.ssdi.supermarket_management_system.repository.CashierRepository;
import com.uncc.ssdi.supermarket_management_system.repository.TransactionsRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SupermarketManagementSystemApplication.class)
@Transactional
@ActiveProfiles("test")
public class CashierRepositoryTest {

	private static final Logger m_logger = LoggerFactory.getLogger(CashierRepositoryTest.class);
	
	@Autowired
	private CashierRepository cashierRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private TransactionsRepository transactionsRepository;
	
	@Before
	public void setup() throws Exception{
		m_logger.info("Initializing CashierRepository Test");
	}
	
	@Test
	public void addcashierTest(){
		m_logger.info("Add Product Test");
		
		Admin admin1= new Admin();
		admin1.setAmdin_id(1);
		admin1.setPassword("adminPassword1");
		admin1.setUsername("testAdminUsername1");
		adminRepository.save(admin1);
//		Admin admin2= new Admin();
//		admin2.setAmdin_id(2);
//		admin2.setPassword("adminPassword2");
//		admin2.setUsername("testAdminUsername2");
//		adminRepository.save(admin2);
		Cashier cashier1 = new Cashier(1, "testUsername1", "testname1", "test1@gmail.com", "testPassword1", "1234567890", admin1);
	//	Cashier cashier2 = new Cashier(2, "testUsername2", "testname2", "test2@gmail.com", "testPassword2", "2345678901", admin1);
//		Cashier cashier3 = new Cashier(3, "testUsername3", "testname3", "test3@gmail.com", "testPassword3", "3456789012", admin2);
//			
		
		Cashier result = cashierRepository.save(cashier1);
		Assert.assertNotNull(result);
	//	Optional<Cashier> cashier =cashierRepository.findByUsername("testUsername1");
	//	Assert.assertNotNull(cashier);
		Assert.assertTrue(result.getName().equalsIgnoreCase("testname1"));
		
	}
	
	@Test
	public void getAllCashiersTest(){
		m_logger.info("Find all products test");
		
		Admin admin2= new Admin();
		admin2.setAmdin_id(2);
		admin2.setPassword("adminPassword2");
		admin2.setUsername("testAdminUsername2");
		adminRepository.save(admin2);
		
		Admin admin3= new Admin();
		admin3.setAmdin_id(3);
		admin3.setPassword("adminPassword3");
		admin3.setUsername("testAdminUsername3");
		adminRepository.save(admin3);
	
		Cashier cashier2 = new Cashier(2, "testUsername2", "testname2", "test2@gmail.com", "testPassword2", "2345678901", admin2);
		Cashier cashier3 = new Cashier(3, "testUsername3", "testname3", "test3@gmail.com", "testPassword3", "3456789012", admin2);
			
		cashier2 = cashierRepository.save(cashier2);
		cashier3 = cashierRepository.save(cashier3);
		
		List<Cashier> products = cashierRepository.findAll();
		
		assertEquals("testUsername1",products.get(0).getUsername());
		assertEquals("testUsername2",products.get(1).getUsername());
		
	
		Optional<Cashier> result1 =cashierRepository.findByUsername("testProductName2");		
		Assert.assertNotNull(result1);
		Optional<Cashier> result2 =cashierRepository.findByUsername("testProductName3");		
		Assert.assertNotNull(result2);
		
	}
	
	
	@Test
	public void savetransactionTest(){
		m_logger.info("Save Transaction Test");
		
		Admin admin1= new Admin();
		admin1.setAmdin_id(1);
		admin1.setPassword("adminPassword1");
		admin1.setUsername("testAdminUsername1");
		adminRepository.save(admin1);
		
		Product product1 = new Product(1, "testproduct1", "20" , "testproduct1desc", "3","lb" );
		Product product2 = new Product(2, "testproduct2", "20" , "testproduct2desc", "2","lb" );
		
		List<Transactions> transactionslist = new ArrayList<Transactions>();
		Date date = new Date();
		
		Cashier cashier1 = new Cashier(1, "testUsername1", "testname1", "test1@gmail.com", "testPassword1", "1234567890", admin1);
		Cashier cashier2 = new Cashier(2, "testUsername2", "testname2", "test2@gmail.com", "testPassword2", "2345678901", admin1);
		Cashier cashier3 = new Cashier(3, "testUsername3", "testname3", "test3@gmail.com", "testPassword3", "3456789012", admin1);
		Cashier cashier27 = new Cashier(27, "testUsername27", "testname27", "test27@gmail.com", "testPassword27", "1234567890",admin1);
			
		Transactions transaction1= new Transactions(1, product1, cashier27, 3, date, 9); 
		Transactions transaction2= new Transactions(2, product1, cashier27, 4, date, 8); 
		transactionslist.add(transaction1);
		transactionslist.add(transaction2);
		
		Transactions result1 = transactionsRepository.save(transaction1);
		Transactions result2 = transactionsRepository.save(transaction2);
		
		Assert.assertNotNull(result1);
	//	Optional<Cashier> cashier =cashierRepository.findByUsername("testUsername1");
	//	Assert.assertNotNull(cashier);
		Assert.assertTrue(result2.getCashier().getCashier_id() == 27);
		
	}	
	
	@Test
	public void getTransactionsAsPerDateTest(){
		m_logger.info("Save getTransactionsAsPerDateTest");
		
		Admin admin1= new Admin();
		admin1.setAmdin_id(1);
		admin1.setPassword("adminPassword1");
		admin1.setUsername("testAdminUsername1");
		adminRepository.save(admin1);
		
		Product product1 = new Product(1, "testproduct1", "20" , "testproduct1desc", "3","lb" );
		Product product2 = new Product(2, "testproduct2", "20" , "testproduct2desc", "2","lb" );
		
		List<Transactions> transactionslist = new ArrayList<Transactions>();
		Date date = new Date();
		
		Cashier cashier1 = new Cashier(1, "testUsername1", "testname1", "test1@gmail.com", "testPassword1", "1234567890", admin1);
		Cashier cashier2 = new Cashier(2, "testUsername2", "testname2", "test2@gmail.com", "testPassword2", "2345678901", admin1);
		Cashier cashier3 = new Cashier(3, "testUsername3", "testname3", "test3@gmail.com", "testPassword3", "3456789012", admin1);
		Cashier cashier27 = new Cashier(27, "testUsername27", "testname27", "test27@gmail.com", "testPassword27", "1234567890",admin1);
			
		Transactions transaction1= new Transactions(1, product1, cashier27, 3, date, 9); 
		Transactions transaction2= new Transactions(2, product1, cashier27, 4, date, 8); 
		transactionslist.add(transaction1);
		transactionslist.add(transaction2);
		
		Transactions result1 = transactionsRepository.save(transaction1);
		Transactions result2 = transactionsRepository.save(transaction2);
		
		Assert.assertNotNull(result1);
	
		Assert.assertTrue(result2.getCashier().getCashier_id() == 27);
		
	}	
	
	
}
