package com.servlets;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.entity.Customer;
import com.util.HibernateUtil;

/**
 * Servlet implementation class ListCustomers
 */
@WebServlet("/ListCustomers")
public class ListCustomers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListCustomers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Customer> customers =  session.createQuery("from Customer as c join fetch c.accounts").list();
		Set<Customer> customersSet = new HashSet<>(customers);
		
		request.setAttribute("allCustomers", customersSet);
		
		session.close();
		getServletContext().getRequestDispatcher("/list.jsp").forward(request, response);
		
		
		
	}

}







