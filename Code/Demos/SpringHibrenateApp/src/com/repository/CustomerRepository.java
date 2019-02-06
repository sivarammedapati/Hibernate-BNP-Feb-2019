package com.repository;

import java.util.List;

import com.entity.Customer;

public interface CustomerRepository {

	void save(Customer customer);
	List<Customer> list();
}