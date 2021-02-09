package com.uncc.ssdi.supermarket_management_system.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.uncc.ssdi.supermarket_management_system.entity.Admin;
import com.uncc.ssdi.supermarket_management_system.entity.Cashier;
import com.uncc.ssdi.supermarket_management_system.entity.Product;
import com.uncc.ssdi.supermarket_management_system.entity.Transactions;
import com.uncc.ssdi.supermarket_management_system.repository.AdminRepository;
import com.uncc.ssdi.supermarket_management_system.repository.CashierRepository;
import com.uncc.ssdi.supermarket_management_system.repository.ProductRepository;
import com.uncc.ssdi.supermarket_management_system.repository.TransactionsRepository;
import com.uncc.ssdi.supermarket_management_system.service.StartupService;

@Profile("dev")
@Service
public class StartupServiceimpl implements StartupService{
	
	@Autowired
	ProductRepository productrepository;
	
	@Autowired
	AdminRepository adminrepository;
	
	@Autowired
	CashierRepository cashierrepository;
	
	@Autowired
	TransactionsRepository transactionrepository;
	
	@Override
	public void initilizeData() {
		
		Admin admin = new Admin();
		admin.setUsername("two123");
		admin.setPassword("password");
		//admin.setAmdin_id(5);
		System.out.println(admin.toString());
		adminrepository.save(admin);
		
		
		Cashier cashier = new Cashier();
		cashier.setAdmin(admin);
		cashier.setContact("8977777886");
		cashier.setEmail("cashier@gmail.com");
		cashier.setName("cashier");
		cashier.setPassword("password");
		cashier.setUsername("cashier123");
		cashierrepository.save(cashier);
		
		Product product1 = new Product();
		product1.setDescription("big bowl");
		product1.setName("bowl");
		product1.setPrice("5");
		product1.setQuantity("50");
		product1.setUnit("piece");
		productrepository.save(product1);
		
		Product product2 = new Product();
		product2.setDescription("black pens");
		product2.setName("pens");
		product2.setPrice("1");
		product2.setQuantity("150");
		product2.setUnit("piece");
		productrepository.save(product2);
		
		Date date = new Date();
		Transactions transaction = new Transactions();
		transaction.setCashier(cashier);
		transaction.setDate(date);
		transaction.setProduct(product1);
		transaction.setQuantity(3);
		transaction.setTotal_amount(15);
		transactionrepository.save(transaction);
		
		

	}

}
