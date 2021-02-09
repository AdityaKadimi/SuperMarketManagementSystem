package com.uncc.ssdi.supermarket_management_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uncc.ssdi.supermarket_management_system.entity.Admin;
import com.uncc.ssdi.supermarket_management_system.entity.Cashier;

@Repository	
public interface CashierRepository extends JpaRepository<Cashier, Integer>{
	
	Optional<Cashier> findByUsername(String username);
}
