package com.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
@Inheritance(strategy=InheritanceType.JOINED)
public class Account {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Basic @Column
	private double balance;
	
	@ManyToOne(targetEntity=Customer.class, 
					cascade= {CascadeType.ALL, CascadeType.MERGE}, fetch=FetchType.LAZY)
	@JoinColumn(name="CUST_ID")
	private Customer customer;
	
	/*@ManyToMany(targetEntity=AccTransaction.class, mappedBy="accounts", 
					cascade= {CascadeType.PERSIST, CascadeType.MERGE})*/
	@ManyToMany(targetEntity=AccTransaction.class, cascade= {CascadeType.ALL})
	@JoinTable(name="ACC_TRANS", joinColumns= {@JoinColumn(name="accId")},inverseJoinColumns= {@JoinColumn(name="txId")})
	private Set<AccTransaction> transactions = new HashSet<>();
	
	
	public Account() {
		
	}

	public Account(double balance) {
		
		this.balance = balance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<AccTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<AccTransaction> transactions) {
		this.transactions = transactions;
	}
	

}