<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="newBa.css">
<title>Login page</title>
</head>
<body class="f1">
<%@include file="Header.jsp"%>
	<form action="g1" method="post">
		
		<h1 class="h1">Welcome to New Bank</h1>
		<div class="d1">
			<h1 class="head">Bank Login</h1>
			<% String error=(String)request.getAttribute("error");%>
			<%if(error!=null){ %><div class="head"><%= error %></div><%} %><br><br>
			<input type="text" placeholder="Enter your Acc number" name="acc"
				class="but" required><br>
			<br> <input type="password" placeholder="Enter your Password"
				name="pas" class="but" id="myInput" required><br>
			<br> <input type="checkbox" onclick="myFunction()"><span
				class="h3">Show Password</span> <br>
			<br> <input type="submit" value="Login" class="s1"><br>
			<br> <span class="span">Forgot Password? </span><a
				href="forget.html"><span class="span">click here</span></a>
		</div>

		<div class="d2">
			<a href="Elogin.jsp"><input type="button" value="Employee Login"
				class="e1"></a>
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