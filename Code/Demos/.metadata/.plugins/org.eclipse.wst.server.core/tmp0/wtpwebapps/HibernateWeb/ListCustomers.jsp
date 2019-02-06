<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List</title>
</head>
<body>
	<h2>List Customers</h2>
	
	<a href="NewCustomer.jsp">Add New</a>
	
	<table>
		<thead>
			<tr>
				<th>Customer ID</th>
				<th>Name</th>
				<th>Created Date</th>
				<th>City</th>
				<th>State</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="customer" items="${ customers}">
				<tr>
					<td>${customer.id }</td>
					<td>${customer.name }</td>
					<td>${customer.createdDate }</td>
					<td>${customer.billingAddress.city }</td>
					<td>${customer.billingAddress.state }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>