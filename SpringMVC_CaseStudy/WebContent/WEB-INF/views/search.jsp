<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>


	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	

<style>
	<%@include file="css/main.css"%>	
	
	.search{
		 margin-top: 75px;
		 width: 100%;
    	 text-align:center;
	}
</style>

</head>
<body>
<div>
	<%@include file="bootstrapNavbar.jsp" %>
</div>

	<div class="textcenter">
		${createJobStatus}
	</div>
	
	<div class="search">
	<form action="jobSearchProcess" method="GET">
		Title: <input type="search" name="jobTitle" placeholder="Ex. Software Engineer">
		State: <input type="search" name="jobLocation" placeholder="Ex. New York">
		<div class="small-box2"><br>
			<input type="submit" name="submit" value="Search">
		</div>
	</form>
	
	<form action="createJob" method="POST">
		<div class="small-box2">
			<input type="submit" name="submit" value="Create">
		</div>
	</form>
	</div>
</body>
</html>