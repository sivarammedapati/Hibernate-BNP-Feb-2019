package com.repository;

import java.util.List;



import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Customer;

@Transactional
@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	/* (non-Javadoc)
	 * @see com.repository.CustomerRepository#save(com.entity.Customer)
	 */
	@Override
	public void save(Customer customer){
		
		Session session = sessionFactory.getCurrentSession();
		session.save(customer);
	}

	@Override
	public List<Customer> list() {
		
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Customer");
		
		return query.list();
	}
}






