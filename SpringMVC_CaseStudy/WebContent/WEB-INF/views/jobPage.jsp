<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Job Full Details</title>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	<style>
	
	.sidemargin{
	margin-left: 10%;
	margin-right: 10%;
	}
	</style>
</head>
<body>

	<div>
		<%@include file="bootstrapNavbar.jsp" %>
	</div>

	<div class="sidemargin">
		<h2>${job.getJid()} - ${job.getTitle()}</h2>
		<h3>${job.getCompany()} - ${job.getLocation()}</h3>
		<h3>${job.getJobType()}</h3>
		<hr>
		
		<p> ${job.getDescription()} </p>
		
		<p> Salary: </p>
		<p> $${job.getAnnual_Salary()} </p>
		
		${unwatchStatus}
		<form action="applyToJob" method="GET">
			<input type="submit" name="submit" value="Apply"> 
		</form>
		
		<form action="watchJob" method="GET">
			<input type="submit" name="submit" value="Watch"> 
		</form>
		
		<form action="unwatchJob" method="GET">
			<input type="submit" name="submit" value="Unwatch"> 
		</form>
	</div>
		
</body>
</html>