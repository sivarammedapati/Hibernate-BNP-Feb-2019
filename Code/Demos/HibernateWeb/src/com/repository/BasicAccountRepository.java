package com.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entity.Account;
import com.util.HibernateUtil;

public class BasicAccountRepository {
	
	public BasicAccountRepository() {
		System.out.println("BasicAccountRepository.BasicAccountRepository()");
	}

	public void deposit(int accId, double amt)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		System.out.println("Acc session: " + session);
		Account acc =  (Account) session.get(Account.class, accId);
		acc.deposit(amt);
		
		tx.commit();
		session.close();

	}
	
	public void withdraw(int accId, double amt) throws Exception
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		Account acc =  (Account) session.get(Account.class, accId);
		if(acc.getBalance() > amt){
			acc.withdraw(amt);
		}
		else{
			throw new Exception("Withdraw failed");
		}
		
		tx.commit();
		session.close();
		

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
