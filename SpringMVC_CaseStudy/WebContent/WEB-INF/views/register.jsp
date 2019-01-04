<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Sign Up</title>
	<style>
	
	<%@include file="css/main.css"%>
	
	span {color:red;}
	
	</style>

</head>
	<body>
		<form action="registerProcess" method="POST">
			<div class="box">
				<h2>Registration</h2><br><br>
				
				<label for="email">Email<span>*</span></label>
				<input type="email" name="email" placeholder="email"><br><br>
				
				<label>Password<span>*</span></label>
				<input type="Password" name="password" placeholder="password"><br><br>

				<label>First Name<span>*</span></label>
				<input type="text" name="fName" placeholder="first name"><br><br>
				
				<label>Last Name<span>*</span></label>
				<input type="text" name="lName" placeholder="last name"><br><br>

				<div class="small-box2">
					${registerStatus}
					<br>
					<input type="submit" name="submit" value="Submit">
				</div>
				
			</div>
		</form>
	</body>
</html>