	 package com.uncc.ssdi.supermarket_management_system.controller.test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.uncc.ssdi.supermarket_management_system.SupermarketManagementSystemApplication;
import com.uncc.ssdi.supermarket_management_system.entity.Admin;
import com.uncc.ssdi.supermarket_management_system.entity.Cashier;
import com.uncc.ssdi.supermarket_management_system.repository.CashierRepository;
import com.uncc.ssdi.supermarket_management_system.service.CashierService;
import com.uncc.ssdi.supermarket_management_system.service.impl.CashierServiceimpl;
import com.uncc.ssdi.supermarket_management_system.util.JSONUtil;
import com.uncc.ssdi.supermarket_management_system.vo.CashierVo;
import com.uncc.ssdi.supermarket_management_system.vo.TransactionsVo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SupermarketManagementSystemApplication.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class CashierControllerTest {
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	private MockMvc mockMvc;

	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private CashierRepository cashierRepository;
	
	@Mock
	private CashierServiceimpl cashierserviceimpl;
	
	@Mock
	private CashierService cashierservice;
	
	
	@Before
    public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		Admin admin1= new Admin();
		admin1.setAmdin_id(1);
		admin1.setPassword("adminPassword1");
		admin1.setUsername("testAdminUsername1");
		Admin admin2= new Admin();
		admin1.setAmdin_id(2);
		admin1.setPassword("adminPassword2");
		admin1.setUsername("testAdminUsername2");
		
		CashierVo cashierVO1 = new CashierVo(1, "testUsername1", "testname1", "test1@gmail.com", "testPassword1", "1234567890", 1);
		Cashier cashier2 = new Cashier(2, "testUsername2", "testname2", "test2@gmail.com", "testPassword2", "1234567890", admin2); cashierservice.findAllCashiers();
		Cashier cashier1 = new Cashier(1, "testUsername1", "testname1", "test1@gmail.com", "testPassword1", "1234567890", admin1);
		ResponseEntity<CashierVo> responseCashier = new ResponseEntity<CashierVo>(cashierVO1, HttpStatus.OK);
		List<Cashier> cashierlist = new ArrayList<Cashier>(); 
		cashierlist.add(cashier2);
		cashierlist.add(cashier1);
		
		CashierVo cashierVO = new CashierVo(1, "testUsername1", "testname1", "test1@gmail.com", "testPassword1", "1234567890", 1); cashierservice.addCashier(cashierVO);
		
		ResponseEntity<CashierVo> responseCashier2 = new ResponseEntity<CashierVo>(cashierVO, HttpStatus.OK);
		
		when(cashierserviceimpl.addCashier(cashierVO)).thenReturn(responseCashier2);
		when(cashierserviceimpl.findAllCashiers()).thenReturn(cashierlist);

      
    }
	
	@Test
	public void findAllCashiersTest() throws Exception{
		
		mockMvc.perform(get("/api/cashierdetails"))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json;charset=UTF-8"));
		
		 verify(cashierservice,times(1)).findAllCashiers();
	}	
	
	@Test
	public void addCashierTest() throws Exception{
		
		CashierVo cashierVO = new CashierVo(1, "testUsername1", "testname1", "test1@gmail.com", "testPassword1", "1234567890", 1); cashierservice.addCashier(cashierVO);
			
		mockMvc.perform(post("/api/addcashier")
				.content(JSONUtil.asJSONString(cashierVO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
			 
			verify(cashierservice, times(1)).addCashier(cashierVO);
			
	}
	
	@Test
	public void saveTransactionTest() throws Exception{
		
		List<TransactionsVo> transactionslist = new ArrayList<TransactionsVo>();
		Date date = new Date();
		TransactionsVo transaction1= new TransactionsVo(1, 1, null, 27, null, 3, "3","lb", date, 9); 
		TransactionsVo transaction2= new TransactionsVo(2, 2, null, 27, null, 4, "2","lb", date, 8); 
		transactionslist.add(transaction1);
		transactionslist.add(transaction2);
		
		ResponseEntity<List<TransactionsVo>> responsetransactions = new ResponseEntity<List<TransactionsVo>>(transactionslist,HttpStatus.OK); cashierservice.saveTransaction(transactionslist);
	
		when(cashierserviceimpl.saveTransaction(transactionslist)).thenReturn(responsetransactions);
		
		mockMvc.perform(post("/api/savetranscation")
				.content(JSONUtil.asJSONString(transactionslist))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		
		verify(cashierservice, times(1)).saveTransaction(transactionslist);
	
	}
       

@Test
@Ignore
public void getTransactionsAsPerDateTest() throws Exception{
		
	SimpleDateFormat dateformat = new SimpleDateFormat("MM-dd-yyyy");

	 Date date1 = dateformat.parse("11-19-2019");
	 Date date2 = dateformat.parse("11-25-2019");
	
   
		List<TransactionsVo> transactionslist = new ArrayList<TransactionsVo>();
		Date date = new Date();
		
		TransactionsVo transaction1= new TransactionsVo(1, 1, null, 27, null, 3, "3","lb", date1, 9); 
		TransactionsVo transaction2= new TransactionsVo(2, 2, null, 27, null, 4, "2","lb", date1, 8); 
		cashierservice.getTransactionsAsPerDate(date1, date1);
		transactionslist.add(transaction1);
		transactionslist.add(transaction2);
		mockMvc.perform(get("/api/transactions/11-19-2019/11-25-2019"))
	    .andExpect(status().isOk())
	    .andExpect(content().contentType("application/json;charset=UTF-8"));
		 
		verify(cashierservice, times(1)).getTransactionsAsPerDate(date1, date2);
		
	}
}
