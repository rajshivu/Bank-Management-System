<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="newBa.css">
<title>Employee login page</title>
</head>
<body class="f1">
<%@include file="Header.jsp"%>
	<form action="emp" method="post">
		
		<h1 class="h1">Welcome to Employee Login</h1>
		
		<div class="d1">
			<h1 class="head">Employee Login</h1>
			<% String error=(String)request.getAttribute("error");%>
		    <%if(error!=null){ %><div class="head"><%= error %></div><%} %><br><br>
			<input type="text" placeholder="Enter EID" name="eid" class="i1"
				required><br>
			<br> <input type="password" placeholder="Enter Password"
				name="epass" class="i1" id="myInput" required><br>
			<br> <input type="checkbox" onclick="myFunction()"><span
				class="h3">Show Password</span><br>
			<br> <input type="submit" Value="Login" class="s1"><br>
			<br>

		</div>
		<div class="d2">
			<a href="login.jsp"><input type="button"
				value="Customer Login" class="e1"></a>
		</div>

	</form>

	<script>
		function myFunction() {
			var x = document.getElementById("myInput");
			if (x.type === "password") {
				x.type = "text";
			} else {
				x.type = "password";
			}
		}
	</script>
	<%@include file="Footer.jsp"%>

</body>
</html>