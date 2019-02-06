package com.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="accountId")
public class CurrentAccount extends Account{

	@Basic @Column
	private double overdraftAmount;
	
	
	public CurrentAccount() {
		
	}
	public CurrentAccount(double balance, double overdraftAmt) {
		super(balance);
		this.overdraftAmount = overdraftAmt;
	}
	
	public double getOverdraftAmount() {
		return overdraftAmount;
	}
	public void setOverdraftAmount(double overdraftAmount) {
		this.overdraftAmount = overdraftAmount;
	}
	
}
