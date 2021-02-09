package com.uncc.ssdi.supermarket_management_system.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.uncc.ssdi.supermarket_management_system.entity.Admin;
import com.uncc.ssdi.supermarket_management_system.entity.Cashier;
import com.uncc.ssdi.supermarket_management_system.repository.AdminRepository;
import com.uncc.ssdi.supermarket_management_system.repository.CashierRepository;
import com.uncc.ssdi.supermarket_management_system.vo.CredentialsVO;

@Service
public class LoginServiceimpl implements UserDetailsService{

	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	CashierRepository cashierRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Admin> admin = adminRepository.findByUsername(username);
		
		if(!admin.isPresent())
		{
			Optional<Cashier> cashier = cashierRepository.findByUsername(username);
			
			cashier.orElseThrow(() -> new UsernameNotFoundException("Not found"+username));
			
			return cashier.map(CredentialsVO::new).get();
		}
		
		admin.orElseThrow(() -> new UsernameNotFoundException("Not found"+username));
		
		return admin.map(CredentialsVO::new).get();
	}


}
