package com.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.Customer;
import com.repository.CustomerRepository;

@Controller
@RequestMapping("/Customers")
public class CustomerController {

	@Autowired
	private CustomerRepository repository;
	
	@RequestMapping("/Save")
	public String save(Customer customer){
		
		customer.setCreatedDate(new Date());
		repository.save(customer);
		return  "redirect: List";
	}
	
	
	@RequestMapping("/List")
	public String list(Model model){
		List<Customer> customers =  repository.list();
		model.addAttribute("customers", customers);
		return "ListCustomers";
	}
	
}
