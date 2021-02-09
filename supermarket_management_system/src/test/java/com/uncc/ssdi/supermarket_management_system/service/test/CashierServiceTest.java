package com.uncc.ssdi.supermarket_management_system.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uncc.ssdi.supermarket_management_system.SupermarketManagementSystemApplication;
import com.uncc.ssdi.supermarket_management_system.entity.Admin;
import com.uncc.ssdi.supermarket_management_system.entity.Cashier;
import com.uncc.ssdi.supermarket_management_system.entity.Product;
import com.uncc.ssdi.supermarket_management_system.entity.Transactions;
import com.uncc.ssdi.supermarket_management_system.repository.AdminRepository;
import com.uncc.ssdi.supermarket_management_system.repository.CashierRepository;
import com.uncc.ssdi.supermarket_management_system.repository.ProductRepository;
import com.uncc.ssdi.supermarket_management_system.repository.TransactionsRepository;
import com.uncc.ssdi.supermarket_management_system.service.impl.CashierServiceimpl;
import com.uncc.ssdi.supermarket_management_system.vo.CashierVo;
import com.uncc.ssdi.supermarket_management_system.vo.TransactionsVo;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SupermarketManagementSystemApplication.class)
@Transactional
@ActiveProfiles("test")
public class CashierServiceTest {
	
	
	@Mock
	private CashierRepository cashierRepository; 
	
	@Mock
	private ProductRepository productRepository; 
	
	@Autowired
	private AdminRepository adminRepository; 
	
	@Mock
	private TransactionsRepository transactionRepository; 
	
	@InjectMocks
	private CashierServiceimpl cashierServiceimpl;
	
	
	@Before
	public void setup() throws ParseException {
	
		MockitoAnnotations.initMocks(this);
		List<Cashier> cashiers = new ArrayList<Cashier>();
		
		Admin admin1= new Admin();
		admin1.setAmdin_id(1);
		admin1.setPassword("adminPassword1");
		admin1.setUsername("testAdminUsername1");
		
		Admin admin2= new Admin();
		admin2.setAmdin_id(2);
		admin2.setPassword("adminPassword2");
		admin2.setUsername("testAdminUsername2");
		
		SimpleDateFormat dateformat = new SimpleDateFormat("MM-dd-yyyy");
		
		 Date date1 = dateformat.parse("11-19-2019");
		 Date date2 = dateformat.parse("11-25-2019");
		
		Cashier cashier1 = new Cashier(1, "testUsername1", "testname1", "test1@gmail.com", "testPassword1", "1234567890", admin1);
		Cashier cashier2 = new Cashier(2, "testUsername2", "testname2", "test2@gmail.com", "testPassword2", "2345678901", admin2);
		Cashier cashier3 = new Cashier(3, "testUsername3", "testname3", "test3@gmail.com", "testPassword3", "3456789012", admin2);
		Cashier cashier27 = new Cashier(27, "testUsername27", "testname27", "test27@gmail.com", "testPassword27", "1234567890",admin1);
			
		cashiers.add(cashier1);
		cashiers.add(cashier2);
		cashiers.add(cashier3);
		
		Product product1 = new Product(1, "testproduct1", "20" , "testproduct1desc", "3","lb" );
		Product product2 = new Product(2, "testproduct2", "20" , "testproduct2desc", "2","lb" );
		
		List<Transactions> transactionslist = new ArrayList<Transactions>();
		Date date = new Date();
		Transactions transaction1= new Transactions(1, product1, cashier27, 3, date1, 9); 
		Transactions transaction2= new Transactions(2, product2, cashier27, 4, date2, 8); 
	//	Transactions transaction2= new Transactions(1, product1, cashier27, 4, date2, 8); 
		transactionslist.add(transaction1);
		transactionslist.add(transaction2);
		
		
		when(cashierRepository.findAll()).thenReturn(cashiers);
		when(cashierRepository.getOne(1)).thenReturn(cashier1);
		when(cashierRepository.getOne(27)).thenReturn(cashier27);
		when(cashierRepository.getOne(2)).thenReturn(cashier2);
		when(cashierRepository.getOne(3)).thenReturn(cashier3);
		when(cashierRepository.save(cashier1)).thenReturn(cashier1);
		when(transactionRepository.save(transaction1)).thenReturn(transaction1);
		when(transactionRepository.save(transaction2)).thenReturn(transaction2);
		when(productRepository.getOne(1)).thenReturn(product1);
		when(productRepository.getOne(2)).thenReturn(product2);
		when(transactionRepository.findAll()).thenReturn(transactionslist);
	}
	
	
	@Test
	public void findAllProductsTest() throws Exception{
		
		List<Cashier> actual = cashierServiceimpl.findAllCashiers();
		
		assertEquals("testUsername1",actual.get(0).getUsername());
		assertEquals("testname2",actual.get(1).getName());
		assertEquals("test3@gmail.com",actual.get(2).getEmail());
		assertEquals("testAdminUsername1",actual.get(0).getAdmin().getUsername());
		
		assertNotEquals("adminPassword1", actual.get(2).getAdmin().getPassword());
		assertNotEquals("2345678901", actual.get(0).getContact());
		assertNotEquals("testPassword1", actual.get(2).getPassword());
		
	}
	
	@Test
	public void addCashierTest() throws Exception{
		
		CashierVo cashier = new CashierVo(1, "testUsername1", "testname1", "test1@gmail.com", "testPassword1", "1234567890", 1);
		
		ResponseEntity<CashierVo> actual = cashierServiceimpl.addCashier(cashier);
		
		assertNotNull(actual.getBody().getName());
		assertTrue(actual.getBody().getCashier_id() == 1);
		assertTrue(actual.getBody().getAdmin_id() == 1);
		assertEquals("testUsername1",actual.getBody().getUsername());
		
	}
	
	@Test
	public void saveTransactionsTest() throws Exception{
		
		List<TransactionsVo> transactionslist = new ArrayList<TransactionsVo>();
		//Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("MM-dd-yyyy");
		
		 Date date1 = dateformat.parse("11-18-2019");
		 Date date2 = dateformat.parse("11-25-2019");
		
		TransactionsVo transaction1= new TransactionsVo(1, 1, null, 27, null, 3, "3","lb", date1, 9); 
		TransactionsVo transaction2= new TransactionsVo(2, 2, null, 27, null, 4, "2","lb", date2, 8); 
		
		transactionslist.add(transaction1);
		transactionslist.add(transaction2);
		
		Admin admin1= new Admin();
		admin1.setAmdin_id(1);
		admin1.setPassword("adminPassword1");
		admin1.setUsername("testAdminUsername1");
		
		adminRepository.save(admin1);
		
		CashierVo cashier1 = new CashierVo(1, "testUsername1", "testname1", "test1@gmail.com", "testPassword1", "1234567890", 1);
		Cashier cashier2 = new Cashier(2, "testUsername2", "testname2", "test2@gmail.com", "testPassword2", "1234567890",admin1);
		
		ResponseEntity<List<TransactionsVo>> actual = cashierServiceimpl.saveTransaction(transactionslist);
		
		assertNotNull(actual.getBody().get(0).getProduct_id());
		assertTrue(actual.getBody().get(0).getQuantity() == 3);
		assertTrue(actual.getBody().get(1).getTotalprice() == 8);
		assertEquals("lb",actual.getBody().get(1).unit);
		
		
	}
	
	@Test
	public void getTransactionsAsPerDateTest() throws Exception{
		
		SimpleDateFormat dateformat = new SimpleDateFormat("MM-dd-yyyy");
		
		 Date date1 = dateformat.parse("11-15-2019");
		 Date date2 = dateformat.parse("11-27-2019");
	
		ResponseEntity<List<TransactionsVo>> actual1 = cashierServiceimpl.getTransactionsAsPerDate(date1,date2);
		System.out.println(actual1.getBody());
		assertNotNull(actual1.getBody().get(0).getProduct_id());
		assertTrue(actual1.getBody().get(0).getQuantity() == 3);
		assertTrue(actual1.getBody().get(1).getTotalprice() == 8);
		assertTrue(actual1.getBody().get(1).getDate().after(date1));
		assertTrue(actual1.getBody().get(1).getDate().before(date2));
	

		
		
	}
	
}
