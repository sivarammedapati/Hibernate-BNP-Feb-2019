package com.repository;

import org.hibernate.Session;

import com.entity.Account;
import com.util.HibernateUtil;

public class AccountRepository {
	
	public AccountRepository() {
		System.out.println("AccountRepository.AccountRepository()");
	}

	public void deposit(int accId, double amt)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		System.out.println("Acc session: " + session);
		Account acc =  (Account) session.get(Account.class, accId);
		acc.deposit(amt);

	}
	
	public void withdraw(int accId, double amt) throws Exception
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Account acc =  (Account) session.get(Account.class, accId);
		if(acc.getBalance() > amt){
			acc.withdraw(amt);
		}
		else{
			throw new Exception("Withdraw failed");
		}
		

	}
	public void transfer(int fromId, int toId, double amt) throws Exception
	{
		try {
			
			deposit(toId, amt);
			withdraw(fromId, amt);
			
			
		} catch (Exception e) {
			System.out.println("AccountRepository.transfer(): " + e.getMessage());
			throw e;
		}
	}
}
