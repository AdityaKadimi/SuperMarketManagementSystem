//package com.uncc.ssdi.supermarket_management_system.service.impl;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.uncc.ssdi.supermarket_management_system.entity.Cashier;
//import com.uncc.ssdi.supermarket_management_system.repository.CashierRepository;
//import com.uncc.ssdi.supermarket_management_system.service.LoginService;
//import com.uncc.ssdi.supermarket_management_system.vo.CredentialsVO;
//import com.uncc.ssdi.supermarket_management_system.vo.User;
//
//@Service
//public class CashierLoginServiceimpl implements UserDetailsService , LoginService	 {
//
//	@Autowired
//	CashierRepository cashierRepository;
//	
//	@Override
//	public Authentication authenticate(String userName, String password) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public User validatecadshierLogin() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//	Optional<Cashier> cashier = cashierRepository.findByUsername(username);
//		
//	cashier.orElseThrow(() -> new UsernameNotFoundException("Not found"+username));
//		
//		return cashier.map(CredentialsVO::new).get();
//	}
//
//}
