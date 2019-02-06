package com.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.context.internal.ManagedSessionContext;

import com.util.HibernateUtil;

/**
 * Servlet Filter implementation class SessionFilter
 */
@WebFilter("/*")
public class SessionFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			
			ManagedSessionContext.bind(session);
			
			//delegates to the servlet/jsp
			chain.doFilter(request, response);
			
			tx.commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		finally
		{
			ManagedSessionContext.unbind(HibernateUtil.getSessionFactory());
			session.close();
		}
		
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}
	public void destroy() {
		
	}

}
