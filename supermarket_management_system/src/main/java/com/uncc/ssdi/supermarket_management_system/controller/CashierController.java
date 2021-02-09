package com.uncc.ssdi.supermarket_management_system.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uncc.ssdi.supermarket_management_system.entity.Cashier;
import com.uncc.ssdi.supermarket_management_system.service.CashierService;
import com.uncc.ssdi.supermarket_management_system.vo.CashierVo;
import com.uncc.ssdi.supermarket_management_system.vo.TransactionsVo;

@RestController
@RequestMapping({ "/api" })
public class CashierController {

	@Autowired
	private CashierService cashierService;
	
	
	
	@RequestMapping(value="/cashierdetails", method= RequestMethod.GET)
	public List<Cashier> findAllCashiers(){
		
		return cashierService.findAllCashiers();
		
	}
	
	@RequestMapping(value = "/addcashier", method = { RequestMethod.POST })
	public ResponseEntity<CashierVo> addUser(@RequestBody CashierVo cashierVo) {
		return cashierService.addCashier(cashierVo);
	}
	
	@RequestMapping(value = "/deletecashier/{id}", method = { RequestMethod.DELETE })
	public ResponseEntity<?> deleteCashier(@PathVariable(value = "id") int cashierId) {
		
		return cashierService.deleteCashier(cashierId);
	}
	
	@RequestMapping(value = "/savetranscation", method = { RequestMethod.POST })
	public ResponseEntity<List<TransactionsVo>> saveTransaction(@RequestBody List<TransactionsVo> transactionsVo) {
		System.out.println("transactions in controller"+transactionsVo.toString());
		return cashierService.saveTransaction(transactionsVo);
	}
	
	@RequestMapping(value = "/transactions/{sdate}/{edate}", method = { RequestMethod.GET })
	public ResponseEntity<List<TransactionsVo>> getTransactionsAsPerDate(@PathVariable String sdate, @PathVariable String edate) throws ParseException {
		String parsedate1=sdate;  
		String parsedate2=edate;
		
	    Date dateobj1=new SimpleDateFormat("MM-dd-yyyy").parse(parsedate1);  
	    Date dateobj2=new SimpleDateFormat("MM-dd-yyyy").parse(parsedate2);  
	    
		return cashierService.getTransactionsAsPerDate(dateobj1,dateobj2);
	}
	
}
