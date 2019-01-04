<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="a" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link rel="stylesheet" href="https://cdn.rawgit.com/Dogfalo/materialize/fc44c862/dist/css/materialize.min.css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.1.min.js"></script>
    <script src="https://cdn.rawgit.com/Dogfalo/materialize/fc44c862/dist/js/materialize.min.js"></script>
    <script type="text/javascript" src="https://cdn.rawgit.com/pinzon1992/materialize_table_pagination/f9a8478f/js/pagination.js"></script>
    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta charset="UTF-8">

<title>Profile</title>

	
	<script>
	$(document).ready(function(){
		$('#searchResults').pageMe({
		pagerSelector:'#myPager',
		activeColor: 'blue',
		prevText:'Anterior',
		nextText:'Siguiente',
		showPrevNext:true,
		hidePageNumbers:false,
		perPage:10
		});
	});
	</script>	
	
	<style>	
	
		body {
			text-align:center;
		}
		form {
    		display: inline-block;
		}
	</style>
	
</head>

<%int count = 1; %>

<body>
	<div>
	<%@include file="bootstrapNavbar.jsp" %>
	</div>
	<div>
		<h4>${user.getfName()} ${user.getlName()}</h4>
	</div>
	
	<div style="text-align:center;">
		${WatchingStatus}
		${ApplicationStatus}
	</div>
	
	<div>
			<form action = "myApplicationList" method="GET" >
		          <input type="submit" name="submit" value="Applications"> 
		    </form>
		    <form action = "myWatchingList" method="GET" >
		          <input type="submit" name="submit" value="Watching"> 
		    </form>
		    <form action = "myJobsList" method="GET" >
		          <input type="submit" name="submit" value="My Posts"> 
		    </form>
	</div>
		<!--------------------------------------------------------------------------------------->
		
		<div>
<a:if test="${not empty jobList}">
	<div class="row">
		<div class="col s12 m12 16 offset-12 center">
			<table class="table table-hover" id="searchResults">
				<thead>
					<tr>
						<th>JobPage</th>
						<th>Title</th>
						<th>Company</th>
						<th>Location</th>
						<th>Description</th>
					</tr>
				</thead>
				<tbody>
					<a:forEach var="job" items="${jobList}">
						<tr>
							<td>
		                        <form action = "jobPage" method="GET">
			                        <input type="number" name="jobNumber" value="<%=count%>" style="display: none">
				                    <input type="submit" name="submit" value="View Page"> 
			                    </form>
	                        </td>
	                        <td>${job.getTitle()}</td>
	                        <td>${job.getCompany()}</td>
	                        <td>${job.getLocation()}</td>
	                        <td>${job.getDescription()}</td>
                        </tr>
                  		<% count++; %>
                	</a:forEach>    	
				</tbody>
			</table>
		</div>
	</div>
</a:if>
</div>
		
</body>
</html>