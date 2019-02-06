package com.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class AccTransaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Basic
	private double amt;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date txDate;
	
	@ManyToMany(targetEntity=Account.class)
	@JoinTable(name="ACC_TRANS", joinColumns= {@JoinColumn(name="txId")},inverseJoinColumns= {@JoinColumn(name="accId")})
	private Set<Account> accounts = new HashSet<>();

	
	
	public AccTransaction() {
		
	}

	public AccTransaction(double amt, Date txDate) {
		super();
		this.amt = amt;
		this.txDate = txDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmt() {
		return amt;
	}

	public void setAmt(double amt) {
		this.amt = amt;
	}

	public Date getTxDate() {
		return txDate;
	}

	public void setTxDate(Date txDate) {
		this.txDate = txDate;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}
	
	

}
