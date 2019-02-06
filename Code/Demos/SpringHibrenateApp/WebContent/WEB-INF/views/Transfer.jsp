<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transfer</title>
</head>
<body>
	<h2>Transfer</h2>
	
	<form action="Execute" method="post" >
		<fieldset>
			<p>
				From ID:
			</p>
			<p>
				<input type="number" name="fromId"> 
			</p>
			<p>
				To ID:
			</p>
			<p>
				<input type="number" name="toId"> 
			</p>
			<p>
				Amount:
			</p>
			<p>
				<input type="number" name="amt"> 
			</p>
			<p>
				<button>Transfer</button>
			</p>
		</fieldset>
	</form>
	
</body>
</html>