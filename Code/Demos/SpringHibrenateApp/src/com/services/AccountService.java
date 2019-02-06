package com.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.repository.AccountRepository;

@Service
@Transactional
public class AccountService {
	
	@Autowired
	private AccountRepository repository;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor={Exception.class})
	public void transfer(int fromId, int toId, double amt) throws Exception
	{
		try {
			
			repository.deposit(toId, amt);
			repository.withdraw(fromId, amt);
			
			
		} catch (Exception e) {
			System.out.println("AccountRepository.transfer(): " + e.getMessage());
			throw e;
		}
	}
}
