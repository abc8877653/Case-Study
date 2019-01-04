<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Home</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</head>
	<body>
	
	<div class="container">
	<div class="row text-center mb-4">
		<div class="col-md-12">
		    <h4>Entry Tech Jobs</h4>
		</div>
	</div>
	<div class="row text-center">
	    <div class="col-md-6 offset-md-3">
	        <div class="card">
	            <div class="card-body">
	                <div class="login-title">
	                    <h4>Log In</h4>
	                </div>
	                <div class="login-form mt-4">
	                
	                    <form method = "POST" action="loginProcess">
                        <div class="form-row">
                            <div class="form-group col-md-12">
                              <input type="email" name="email" placeholder="Email Address" class="form-control"/>
                            </div>
                            <div class="form-group col-md-12">
                              <input type="password" name="password" placeholder="Password" class="form-control"/>
                            </div>
                        </div>
                      
                        <div class="form-row">
	                        ${loginError}
							${registerStatus}
                            <button type="submit" class="btn btn-danger btn-block">Submit</button>
                        </div>
                    </form>
                    
	                </div>
	                <div class="logi-forgot text-right mt-2">
	                    <a href="register">Register</a>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
</div>

</body>
</html>