package com.uncc.ssdi.supermarket_management_system.service;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.uncc.ssdi.supermarket_management_system.entity.Cashier;
import com.uncc.ssdi.supermarket_management_system.vo.CashierVo;
import com.uncc.ssdi.supermarket_management_system.vo.TransactionsVo;

public interface CashierService {

	List<Cashier> findAllCashiers();

	ResponseEntity<CashierVo> addCashier(CashierVo cashierVo);

	ResponseEntity<?> deleteCashier(int cashierId);

	ResponseEntity<List<TransactionsVo>> saveTransaction(List<TransactionsVo> transactionsVo);

	ResponseEntity<List<TransactionsVo>> getTransactionsAsPerDate(Date dateobj1,Date dateobj2);
	

}
