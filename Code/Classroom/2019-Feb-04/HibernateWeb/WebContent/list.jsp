<%@page import="com.entity.Account"%>
<%@page import="java.util.Set"%>
<%@page import="com.entity.Customer"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customers</title>
</head>
<body>
	<h2>Customers</h2>
	
	<% Set<Customer> customers= (Set<Customer>)request.getAttribute("allCustomers"); %>
	
	<%for(Customer customer: customers){ %>
	
		<div>
			<p>Id: <%=customer.getId() %></p>
			<p>Name: <%=customer.getName() %></p>
		</div>
		
		<% Set<Account> accounts = customer.getAccounts(); %>
		
		<%for(Account account: accounts){ %>
			
			<div>
			<p>Id: <%=account.getId() %></p>
			<p>Balance: <%=account.getBalance() %></p>
		</div>
			
		<%} %>
		
	<%} %>
	
</body>
</html>



