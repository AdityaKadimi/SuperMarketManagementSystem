package com.uncc.ssdi.supermarket_management_system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uncc.ssdi.supermarket_management_system.entity.Admin;
import com.uncc.ssdi.supermarket_management_system.repository.AdminRepository;
import com.uncc.ssdi.supermarket_management_system.service.AdminService;

@Service
public class AdminServiceimpl implements AdminService {

	
	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public List<Admin> findAllAdmins() {
		
		List<Admin> listofadmins = adminRepository.findAll();
		
		return listofadmins;
	}

}
