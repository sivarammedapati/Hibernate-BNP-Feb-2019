package com.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.entity.Customer;
import com.util.HibernateUtil;

/**
 * Servlet implementation class ListCustomers
 */
@WebServlet("/ListCustomers")
public class ListCustomers extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Customer> customers = session.createQuery("from Customer").list();
		session.close();
		
		request.setAttribute("customers", customers);;
		getServletContext().getRequestDispatcher("/ListCustomers.jsp").forward(request, response);
		
	}

}









