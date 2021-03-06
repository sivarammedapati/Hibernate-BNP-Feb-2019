package com.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

public class Application {

	public static void main(String[] args) {
		
		Configuration configuration = new Configuration();
		//Load the hibernate.cfg.xml
		configuration.configure();
		
		//Open the persistence manager
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		//createMessages(sessionFactory);
		//fetchMessages(sessionFactory);
		//sessionCache(sessionFactory);
		//updateMessage(sessionFactory);
		//updateMessageAcrossSession(sessionFactory);
		
		//pessimisticLocking(sessionFactory);
		//optimisticLocking(sessionFactory);
		
		//CreateCustomers(sessionFactory);
		//createCustomerEmails(sessionFactory);
		//createCustomerAccounts(sessionFactory);
		//queryCustomers(sessionFactory);
		//createTransactions(sessionFactory);
		
		//queryDemo(sessionFactory);
		//criteriaDemo(sessionFactory);
		namedQuery(sessionFactory);
		
		
		//close the session factory
		sessionFactory.close();
	}
	
	private static void namedQuery(SessionFactory sessionFactory) {
		
		Session session = sessionFactory.openSession();
		Query query = session.getNamedQuery("fetchByCity");
		query.setParameter("city", "Mumbai");
		List<Customer> customers = query.list();
		
		for (Customer customer : customers) {
			System.out.println(customer.getId());
			System.out.println(customer.getAddress().getCity());
		}
		
	}

	private static void criteriaDemo(SessionFactory sessionFactory) {
		
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Account.class);
		criteria.add(Restrictions.gt("balance", 40000.00));
		
		List<Account> accounts = criteria.list();
		
		for (Account account : accounts) {
			System.out.println("Id, Balance: " + account.getId() + " --- " + account.getBalance());
		}
	}

	private static void queryDemo(SessionFactory sessionFactory) {
		
		Session session = sessionFactory.openSession();
		//Query query = session.createQuery("from Account");
		//Query query = session.createQuery("from Account as a where a.balance > ?");
		Query query = session.createQuery("from Account as a where a.balance > :bal");
		query.setDouble("bal", 20000);
		
		query.setFirstResult(0);
		query.setMaxResults(3);
		
		
		List<Account> accounts = query.list();
		
		for (Account account : accounts) {
			System.out.println("Id, Balance: " + account.getId() + " --- " + account.getBalance());
		}
		
		
	}

	private static void createTransactions(SessionFactory sessionFactory) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Account acc1 = session.get(Account.class, 1);
		Account acc2 = session.get(Account.class, 2);
		
		
		AccTransaction trans1 = new AccTransaction(10000, new Date());
		AccTransaction trans2 = new AccTransaction(40000, new Date());
		
		acc1.getTransactions().add(trans1);
		acc1.getTransactions().add(trans2);
		
		acc2.getTransactions().add(trans1);
		
		tx.commit();
		session.close();
		
	}

	private static void queryCustomers(SessionFactory sessionFactory) {
		
		Session session = sessionFactory.openSession();
		// HQL query
		//Query query =  session.createQuery("from Customer");
		//Query query =  session.createQuery("from Customer as c join fetch c.accounts");
		Query query =  session.createQuery("from Customer as c left join fetch c.accounts");
		
		List<Customer> customers =  query.list();
		Set<Customer> customers2 = new HashSet<>(customers);
		
		
		for (Customer customer : customers2) {
			
			System.out.println("Id: " + customer.getId());
			System.out.println("Name: " + customer.getName());
			System.out.println("CreatedDate: " + customer.getCreatedDate());
			
			Set<Account> accounts = customer.getAccounts();
			for (Account account : accounts) {
				System.out.println("	Account Id: " + account.getId());
				System.out.println("	Balance: " + account.getBalance());
				
			}
			System.out.println();
		}
		
		
	}

	private static void createCustomerAccounts(SessionFactory sessionFactory) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Customer c1 = session.get(Customer.class, 1);
		Customer c2 = session.get(Customer.class, 2);
		
		
		/*Account acc1 = new Account(20000);
		Account acc2 = new Account(50000);*/
		SavingsAccount acc1 = new SavingsAccount(40000, 1000);
		CurrentAccount acc2 = new CurrentAccount(15000, 200000);
		
		
		
		c1.getAccounts().add(acc1);
		c2.getAccounts().add(acc2);
		
		acc1.setCustomer(c2);
		acc2.setCustomer(c1);
		
		tx.commit();
		session.close();
		
	}

	private static void createCustomerEmails(SessionFactory sessionFactory) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Customer c1 = session.get(Customer.class, 1);
		
		/*c1.getEmails().add("service@oracle.com");
		c1.getEmails().add("support@oracle.com");
		c1.getEmails().add("service@oracle.com");*/
		
		Set<String> emails = new HashSet<>();
		emails.add("ceo@oracle.com");
		emails.add("feedback@oracle.com");
		
		c1.setEmails(emails);

		
		tx.commit();
		session.close();
		
	}

	private static void CreateCustomers(SessionFactory sessionFactory) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Customer c1 = new Customer("Oracle", new Date(), new Address("Mumbai", "Maharashtra"));
		Customer c2 = new Customer("Google", new Date(), new Address("Hyderabad", "TL"));

		session.save(c1);
		session.save(c2);
		
		tx.commit();
		session.close();
		
		
	}

	private static void optimisticLocking(SessionFactory sessionFactory) {
			
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			
			Message message = session.get(Message.class, 1);
			System.out.println("Text: " + message.getText());
			
			message.setText(message.getText() + " 100");
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("Press any key to save");
			scanner.nextLine();
			
			
			tx.commit();
			session.close();
			
		}

	private static void pessimisticLocking(SessionFactory sessionFactory) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Message message = session.get(Message.class, 1, LockOptions.UPGRADE);
		System.out.println("Text: " + message.getText());
		
		message.setText(message.getText() + " 100");
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Press any key to save");
		scanner.nextLine();
		
		
		tx.commit();
		session.close();
		
	}

	private static void updateMessageAcrossSession(SessionFactory sessionFactory) {
		
		Session s1 = sessionFactory.openSession();
		Message m1 = s1.get(Message.class, 1);
		s1.close();
		
		m1.setText(m1.getText() + " changed");
		
		
		Session s2 = sessionFactory.openSession();
		Transaction tx = s2.beginTransaction();
		
		Message m0 = s2.get(Message.class, 1);
		m0.setText(m0.getText() + " updated");
		
		
		//s2.update(m1);
		s2.merge(m1);
		
		//m1.setText("abc");
		
		tx.commit();
		s2.close();
		
	}

	private static void updateMessage(SessionFactory sessionFactory) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Message message = session.get(Message.class, 1);
		message.setText(message.getText() + " updated");
		
		tx.commit();
		session.close();
		
	}

	private static void sessionCache(SessionFactory sessionFactory) {
		
		Session session = sessionFactory.openSession();
		Message m1 = session.get(Message.class, 1);
		
		session.evict(m1);
		
		Message m2 = session.get(Message.class, 1);
		
		System.out.println("--------------------------");
		Session s2 = sessionFactory.openSession();
		Message m3 = s2.get(Message.class, 1);
		
		
		session.close();
		s2.close();
		
		
	}

	private static void fetchMessages(SessionFactory sessionFactory) {
		
		Session session = sessionFactory.openSession();
		
		//Message m = session.get(Message.class, 100);
		Message m = session.load(Message.class, 1);
		
		System.out.println("Type of m: " + m.getClass().getName());
		
		System.out.println("Id: " + m.getId());
		System.out.println("Text: " + m.getText());
		System.out.println("Desc: " + m.getDescription());
		
		if(m.getNextMessage() != null) {
			
			System.out.println("NextMessage Type: " + m.getNextMessage().getClass().getName());
			System.out.println("Next Message Text: " + m.getNextMessage().getText());
		}
		
		session.close();
		
	}

	private static void createMessages(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		//Create the entities
		Message m1 = new Message(1, "Hello", "First Message");
		Message m2  = new Message(2, "Hibernate", "Second Message");
		
		//Define an association;
		m1.setNextMessage(m2);
		
		//Persist to DB
		session.save(m1);
		
		
		tx.commit();
		
		//close the session
		session.close();
	}

}
