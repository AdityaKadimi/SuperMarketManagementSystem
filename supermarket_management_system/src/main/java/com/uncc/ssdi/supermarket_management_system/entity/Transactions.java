package com.uncc.ssdi.supermarket_management_system.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="transactions")
public class Transactions {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
	public int transaction_id;
	
	
	@OneToOne
	@JoinColumn(name = "product_id")
	public Product product;
	
	
	@OneToOne
	@JoinColumn(name = "cashier_id")
	public Cashier cashier;
	
	@Column(name = "quantity")
	public int quantity;
	
	@Column(name = "date")
	@Temporal(TemporalType.DATE)
	public Date date;
	
	@Column(name = "total_amount")
	public int total_amount;
	
//	@Column(name = "units")
//	public String units;

	public Transactions() {
		super();
	}

	public Transactions(int transaction_id, Product product, Cashier cashier, int quantity, Date date,
			int total_amount) {
		super();
		this.transaction_id = transaction_id;
		this.product = product;
		this.cashier = cashier;
		this.quantity = quantity;
		this.date = date;
		this.total_amount = total_amount;
	}

	public int getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Cashier getCashier() {
		return cashier;
	}

	public void setCashier(Cashier cashier) {
		this.cashier = cashier;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}

	@Override
	public String toString() {
		return "Transactions [transaction_id=" + transaction_id + ", product=" + product + ", cashier=" + cashier
				+ ", quantity=" + quantity + ", date=" + date + ", total_amount=" + total_amount + "]";
	}
	
	
	
	

}
