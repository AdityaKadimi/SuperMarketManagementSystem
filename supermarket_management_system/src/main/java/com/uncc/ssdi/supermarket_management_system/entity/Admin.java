package com.uncc.ssdi.supermarket_management_system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admin")
public class Admin {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int amdin_id;
	
	@Column(name = "Username")
	public String username;
	
	@Column(name = "Password")
	public String password;
	
	
	public Admin() {
		
	}

	public Admin(int amdin_id, String username, String password) {
		super();
		this.amdin_id = amdin_id;
		this.username = username;
		this.password = password;
	}



	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public int getAmdin_id() {
		return amdin_id;
	}


	public void setAmdin_id(int amdin_id) {
		this.amdin_id = amdin_id;
	}


	@Override
	public String toString() {
		return "Admin [amdin_id=" + amdin_id + ", username=" + username + ", password=" + password + "]";
	}


	
	
	
}
