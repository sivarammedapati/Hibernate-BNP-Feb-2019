package com.repository;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Account;


@Repository
@Transactional  
public class AccountRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void deposit(int accId, double amt)
	{
		Session session = sessionFactory.getCurrentSession();
		
		Account acc =  (Account) session.get(Account.class, accId);
		acc.deposit(amt);

	}
	
	public void withdraw(int accId, double amt) throws Exception
	{
		Session session = sessionFactory.getCurrentSession();
		Account acc =  (Account) session.get(Account.class, accId);
		if(acc.getBalance() > amt){
			acc.withdraw(amt);
		}
		else{
			throw new Exception("Withdraw failed");
		}
		

	}
	
}
