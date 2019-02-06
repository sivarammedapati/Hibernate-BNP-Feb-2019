package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.repository.AccountRepository;
import com.repository.BasicAccountRepository;

/**
 * Servlet implementation class Transfer
 */
@WebServlet("/Transfer")
public class Transfer extends HttpServlet {
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int fromId = Integer.parseInt(request.getParameter("fromId"));
		int toId = Integer.parseInt(request.getParameter("toId"));
		double amt = Double.parseDouble(request.getParameter("amt"));
		
		//BasicAccountRepository repository = new BasicAccountRepository();
		AccountRepository repository = new AccountRepository();
		try {
			repository.transfer(fromId, toId, amt);
			
			response.getWriter().write("Transfer Completed");
		} catch (Exception e) {
			
			e.printStackTrace();
			response.getWriter().write("Transfer Failed");
			throw new ServletException(e.getMessage());
		}
	}

}
