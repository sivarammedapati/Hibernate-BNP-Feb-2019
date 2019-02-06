package com.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="accountId")
public class SavingsAccount extends Account {

	@Basic @Column
	private double minimumBalance;
	
	public SavingsAccount() {
		
	}
	
	public SavingsAccount(double balance, double minBalance) {
		super(balance);
		this.setMinimumBalance(minBalance);
	}

	public double getMinimumBalance() {
		return minimumBalance;
	}

	public void setMinimumBalance(double minimumBalance) {
		this.minimumBalance = minimumBalance;
	}
	
}
