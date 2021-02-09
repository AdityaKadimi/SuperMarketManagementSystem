	package com.uncc.ssdi.supermarket_management_system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uncc.ssdi.supermarket_management_system.entity.Admin;
import com.uncc.ssdi.supermarket_management_system.entity.Cashier;
import com.uncc.ssdi.supermarket_management_system.entity.Product;
import com.uncc.ssdi.supermarket_management_system.entity.Transactions;
import com.uncc.ssdi.supermarket_management_system.repository.AdminRepository;
import com.uncc.ssdi.supermarket_management_system.repository.CashierRepository;
import com.uncc.ssdi.supermarket_management_system.repository.ProductRepository;
import com.uncc.ssdi.supermarket_management_system.repository.TransactionsRepository;
import com.uncc.ssdi.supermarket_management_system.service.CashierService;
import com.uncc.ssdi.supermarket_management_system.util.ResourceNotFoundException;
import com.uncc.ssdi.supermarket_management_system.vo.CashierVo;
import com.uncc.ssdi.supermarket_management_system.vo.TransactionsVo;

@Service
public class CashierServiceimpl implements CashierService {

	@Autowired
	CashierRepository cashierRepository ;
	
	@Autowired
	AdminRepository adminRepository ;
	
	@Autowired
	ProductRepository productRepository ;
	
	@Autowired
	TransactionsRepository transactionsRepository ;
	
//	@Autowired
//	LoginService loginService;
	
	private final Logger m_logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Override
	public List<Cashier> findAllCashiers() {
		List<Cashier> listofcashiers = cashierRepository.findAll();
		
		return listofcashiers;
	}

	@Override
	public ResponseEntity<CashierVo> addCashier(CashierVo cashierVo) {
		m_logger.info("saving the user to database");
		HttpStatus status=HttpStatus.OK;
		Cashier cashier =new Cashier();
		
//		ExampleMatcher emailMatcher = ExampleMatcher.matching()
//				  .withIgnorePaths("id") 
//				  .withMatcher("email", new ExampleMatcher.MatcherConfigurer<ExampleMatcher.GenericPropertyMatcher>() {
//	                  @Override
//	                  public void configureMatcher(ExampleMatcher.GenericPropertyMatcher matcher) {
//	                      matcher.ignoreCase();
//	                  }
//	              });
//		
//		
//		Cashier probe = new Cashier();
//		probe.setEmail(cashierVo.getEmail());
//		Example<Cashier> example = Example.of(probe, emailMatcher);
//		boolean emailexists = cashierRepository.exists(example);
//		System.out.println("Email id :"+emailexists);
//		
//		ExampleMatcher usernameMatcher = ExampleMatcher.matching()
//				  .withIgnorePaths("id") 
//				  .withMatcher("username", new ExampleMatcher.MatcherConfigurer<ExampleMatcher.GenericPropertyMatcher>() {
//	                  @Override
//	                  public void configureMatcher(ExampleMatcher.GenericPropertyMatcher matcher) {
//	                      matcher.ignoreCase();
//	                  }
//	              });
//		
//		
//		Cashier usernameprobe = new Cashier();
//		probe.setEmail(cashierVo.getEmail());
//		Example<Cashier> usernameexample = Example.of(usernameprobe, usernameMatcher);
//		boolean usernameexists = cashierRepository.exists(usernameexample);
//		System.out.println("Email id :"+ usernameexists);
//		
//	
		
			try {
				
					Admin admin = adminRepository.getOne(cashierVo.getAdmin_id());
					cashier.setAdmin(admin);
					cashier.setContact(cashierVo.getContact());
					cashier.setName(cashierVo.getName());
					cashier.setPassword(cashierVo.getPassword());
					cashier.setUsername(cashierVo.getUsername());
					cashier.setEmail(cashierVo.getEmail());
				//System.out.println(cashier);
				cashierRepository.save(cashier);
				
		}
		
		catch(Exception e) {
	    	System.out.println(e.toString());
	    	status=HttpStatus.INTERNAL_SERVER_ERROR;
	    }
			m_logger.info("user saved to database");
		
		
	
	    return ResponseEntity.status(status).body(cashierVo);
	}

	@Override
	public ResponseEntity<?> deleteCashier(int cashierId) {
	
		 Cashier cashier = cashierRepository.findById(cashierId)
		 .orElseThrow(() -> new ResourceNotFoundException("Cashier", "id", cashierId));

		   cashierRepository.delete(cashier);

		    return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<List<TransactionsVo>> saveTransaction(List<TransactionsVo> transactionslist) {
		
		List<TransactionsVo> transactionVolistList = new ArrayList<TransactionsVo>();
		
		HttpStatus status=HttpStatus.OK;
		
		for (TransactionsVo transactionVo : transactionslist) {
			
			System.out.println(transactionVo);
			Product product =productRepository.getOne(transactionVo.getProduct_id());
			
			System.out.println("product with old details"+product);
			transactionVo.setCashier_id(27);
			
			int updatedQuantity = Integer.parseInt(product.getQuantity())-transactionVo.getQuantity();
			
			if(updatedQuantity>0) {
				product.setQuantity(Integer.toString(updatedQuantity));
			}
			
			System.out.println("product with updated details"+product);
			
			productRepository.save(product);
			
			System.out.println("each transaction obj"+transactionVo);
			
			Transactions transaction = new Transactions();
			Cashier cashier = cashierRepository.getOne(transactionVo.getCashier_id());
			System.out.println("cashier obj"+cashier);
			
			transaction.setCashier(cashierRepository.getOne(transactionVo.getCashier_id()));
			System.out.println("cashier object"+cashierRepository.getOne(transactionVo.getCashier_id()));
			transaction.setProduct(productRepository.getOne(transactionVo.getProduct_id()));
			System.out.println("product object"+productRepository.getOne(transactionVo.getProduct_id()));
			
			//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			
			Date date = new Date();
			transaction.setDate(date);
			transaction.setQuantity(transactionVo.getQuantity());
			transaction.setTotal_amount(transactionVo.getTotalprice());
			System.out.println("final transaction"+transaction);
			transactionsRepository.save(transaction);
			
			//transactionsRepository.getOne()
			transactionVo.setDate(date);
			transactionVolistList.add(transactionVo);
			
		}
		
		return ResponseEntity.status(status).body(transactionVolistList);
	}

	@Override
	public ResponseEntity<List<TransactionsVo>> getTransactionsAsPerDate(Date sdate, Date edate) {
		
		List<Transactions> dbtransactionslist = transactionsRepository.findAll();
		List<TransactionsVo> transactionsVolist = new ArrayList<TransactionsVo>();
		
		HttpStatus status=HttpStatus.OK;
		
	for (Transactions transaction : dbtransactionslist) {
		
		if(transaction.getDate().after(sdate) && transaction.getDate().before(edate) ||( transaction.getDate().equals(sdate) || transaction.getDate().equals(edate)) ) {
			
			TransactionsVo transactionsVo = new TransactionsVo(); 
			transactionsVo.setCashier_id(transaction.getCashier().getCashier_id());
			transactionsVo.setProduct_id(transaction.getProduct().getProduct_id());
			transactionsVo.setDate(transaction.getDate());
			transactionsVo.setQuantity(transaction.getQuantity());
			transactionsVo.setProductname(transaction.getProduct().getName());
			transactionsVo.setCashiername(transaction.getCashier().getName());
			transactionsVo.setTotalprice(transaction.getTotal_amount());
			transactionsVo.setPrice(transaction.getProduct().getPrice());
			transactionsVolist.add(transactionsVo);
			
		}
		
	}
		
	return ResponseEntity.status(status).body(transactionsVolist);

	}
	
	


}
