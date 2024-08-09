package com.javaweb.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "customerid")
	private CustomerEntity customer;
	
	@ManyToOne
	@JoinColumn(name = "type")
	private TransactionType type;

	
	public CustomerEntity getCustomer() {
		return customer;
	}


	public void setCustomer(CustomerEntity customerEntity) {
		this.customer = customerEntity;
	}


	public Long getId() {
		return id;
	}


	public TransactionType getType() {
		return type;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public void setType(TransactionType type) {
		this.type = type;
	}
	
}

