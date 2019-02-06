package com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.services.AccountService;

@Controller
@RequestMapping("/Accounts")
public class AccountController {

	@Autowired
	private AccountService service;
	
	@ResponseBody
	@RequestMapping("/Execute")
	public String execute(@RequestParam("fromId")int fromId, 
			@RequestParam("toId")int toId, 
			@RequestParam("amt")double amt){
		
		try {
			service.transfer(fromId, toId, amt);
			return "Transfer Completed";
		} catch (Exception e) {
			
			e.printStackTrace();
			return "Transfer Failed";
			
		}
		
		
	}
}
