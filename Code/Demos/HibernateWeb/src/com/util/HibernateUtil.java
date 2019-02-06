package com.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private final static SessionFactory SESSION_FACTORY;
	
	static{
		Configuration configuration = new Configuration();
		configuration.configure();
		
		SESSION_FACTORY = configuration.buildSessionFactory();
	}

	public static SessionFactory getSessionFactory()
	{
		return SESSION_FACTORY;
	}
}
