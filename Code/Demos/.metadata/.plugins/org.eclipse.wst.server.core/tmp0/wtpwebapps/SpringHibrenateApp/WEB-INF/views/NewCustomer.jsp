<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Customer</title>
</head>
<body>
	<h2>New Customer</h2>
	
	<form action="Save" method="post" >
		<fieldset>
			<p>
				Name:
			</p>
			<p>
				<input type="text" name="name"> 
			</p>
			<p>
				City:
			</p>
			<p>
				<input type="text" name="billingAddress.city"> 
			</p>
			<p>
				State:
			</p>
			<p>
				<input type="text" name="billingAddress.state"> 
			</p>
			<p>
				<button>Save</button>
			</p>
		</fieldset>
	</form>
	<a href="List">List Customers</a>
</body>
</html>