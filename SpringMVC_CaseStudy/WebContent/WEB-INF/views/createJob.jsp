<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Job</title>


	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<style>
		<%@include file="css/main.css"%>
	</style>

</head>
<body>

<div>
<%@include file="bootstrapNavbar.jsp" %>
</div>

	<h2 class="textcenter">Fill the Form</h2>
	<div id="indexBox">
		<form action="createJobProcess" method="POST">
	  
	  
	  	<label>Job Title:<span>*</span></label>
		<input type="text" name="title" placeholder="Job Title"><br><br>
		
		<label>Company:<span>*</span></label>
		<input type="text" name="company" placeholder="Company Name"><br><br>
	  
	    <label>State</label>
	    	<select name="location">
	    		<option value="Alabama">Alabama</option>
	    		<option value="Alaska">Alaska</option>
	    		<option value="Arizona">Arizona</option>
	    		<option value="Arkansas">Arkansas</option>
	    		<option value="California">California</option>
	    		<option value="Colorado">Colorado</option>
	    		<option value="Connecticut">Connecticut</option>
	    		<option value="Delaware">Delaware</option>
	    		<option value="District of Columbia">District of Columbia</option>
	    		<option value="Florida">Florida</option>
	    		<option value="Georgia">Georgia</option>
	    		<option value="Guam">Guam</option>
	    		<option value="Hawaii">Hawaii</option>
	    		<option value="Idaho">Idaho</option>
	    		<option value="Illinois">Illinois</option>
	    		<option value="Indiana">Indiana</option>
	    		<option value="Iowa">Iowa</option>
	    		<option value="Kansas">Kansas</option>
	    		<option value="Kentucky">Kentucky</option>
	    		<option value="Louisiana">Louisiana</option>
	    		<option value="Maine">Maine</option>
	    		<option value="Maryland">Maryland</option>
	    		<option value="Massachusetts">Massachusetts</option>
	    		<option value="Michigan">Michigan</option>
	    		<option value="Minnesota">Minnesota</option>
	    		<option value="Mississippi">Mississippi</option>
	    		<option value="Missouri">Missouri</option>
	    		<option value="Montana">Montana</option>
	    		<option value="Nebraska">Nebraska</option>
	    		<option value="Nevada">Nevada</option>
	    		<option value="New Hampshire">New Hampshire</option>
	    		<option value="New Jersey">New Jersey</option>
	    		<option value="New Mexico">New Mexico</option>
	    		<option value="New York">New York</option>
	    		<option value="North Carolina">North Carolina</option>
	    		<option value="North Dakota">North Dakota</option>
	    		<option value="Northern Marianas Islands">Northern Marianas Islands</option>
	    		<option value="Ohio">Ohio</option>
	    		<option value="Oklahoma">Oklahoma</option>
	    		<option value="Oregon">Oregon</option>
	    		<option value="Pennsylvania">Pennsylvania</option>
	    		<option value="Puerto Rico">Puerto Rico</option>
	    		<option value="Rhode Island">Rhode Island</option>
	    		<option value="South Carolina">South Carolina</option>
	    		<option value="South Dakota">South Dakota</option>
	    		<option value="Tennessee">Tennessee</option>
	    		<option value="Texas">Texas</option>
	    		<option value="Utah">Utah</option>
	    		<option value="Vermont">Vermont</option>
	    		<option value="Virginia">Virginia</option>
	    		<option value="Virgin Islands">Virgin Islands</option>
	    		<option value="Washington">Washington</option>
	    		<option value="West Virginia">West Virginia</option>
	    		<option value="Wisconsin">Wisconsin</option>
	    		<option value="Wyoming">Wyoming</option></select>
	    		<br>
	    		<br>
	    <label>Job Type</label>
	    <br>
		<label > 
			<input type="radio" name="jobType" value="fulltime" checked="checked">
			Full-Time
		</label>
		<br>
		<label> 
			<input type="radio" name="jobType" value="parttime">
			Part-Time
		</label>
		<br>
		<br>
		<label>Annual Salary: <span>*</span></label>
		<input type="number" name="annual_salary" placeholder="Ex: 50,000"><br><br>
		
		<label>Job Description: <span>*</span></label>
		<textarea rows="4" cols="50" name="description"></textarea><br><br>
		
		<div class="small-box2">
			${jobCreateStatus}
			<input type="submit" name="submit" value="Create">
		</div>

	</form>
</div>
</body>
</html>