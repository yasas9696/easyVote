<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css">
    body{
        background-image: url("l.jpg");
        background-size:cover ;
        background-position: center center;
        background-attachment: fixed ;


    }
    #ui{
        background-color:#333 ;
        padding: 30px;
        margin-top: 50px;
        border-radius: 10px;
        opacity: 0.8;
    }
    #ui label,h1 {
        color: #fff;

    }
</style>



</head>
<body>

	<%

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1
		response.setHeader("Pragma", "no-cahce"); //HTTP 1.0
		response.setHeader("Expires", "0");

	%>

	 <div class="container">
            <div class="row">
                <div class="col-lg-3"></div>
    
                <div class="col-lg-6">
                    <div id="ui">
                        <h1 class="text-center">REGISTRATION FORM</h1><br>
                        <form class="form-group text-center" name="Registration" action="Register" method="post">
                            <div class="row">
                                <div class="col-lg-6">
                                    <label>First Name:</label>
                                    <input type="text" name="firstName" id="firstname" class="form-control" placeholder="Enter your First Name.." required value=${requestScope["firstname"]}>
                                    ${requestScope["error1"]}
                                </div>
    
    
                                <div class="col-lg-6">
                                    <label>Last Name:</label>
                                    <input type="text" name="lastName" id="lastname" class="form-control" placeholder="Enter your Last Name.." required value=${requestScope["lastname"]}>
                                    ${requestScope["error1"]}
                                </div>
                            </div>
			    <br>
			    <label>Middle Name:</label>
                                    <input type="text" name="middleName" id="middleName" class="form-control" placeholder="Enter your Middle Name.." required value=${requestScope["middlename"]}>
                                    ${requestScope["error1"]}	
                            <br>
                            <label>E-mail</label>
                            <input type="email" name="email" id="email" class="form-control" placeholder="Enter your E-mail.." value=${requestScope["email"]}>
                            ${requestScope["error1"]}
    
                            <br>
                            <label>NIC Number:</label>
                            <input type="text" name="nic" id="nic" class="form-control" placeholder="Enter your NIC num.." required value=${requestScope["nic"]}>
                            ${requestScope["error1"]}
                            <br>
    
                            <label>Username:</label>
                            <input type="text" name="username" id="username" class="form-control" placeholder="Enter your user name.." required value=${requestScope["username"]}>
                            ${requestScope["error1"]}
                            ${requestScope["error3"]}
                            <br>
    
    
    
    
                            <div class="row">
                                <div class="col-lg-6">
                                    <label>Password:</label>
                                    <input type="password" name="password1" id="password1" class="form-control" placeholder="Enter your password.." required>
                                    ${requestScope["error1"]}
                                </div>
    
    
                                <div class="col-lg-6">
                                    <label>Confirm Password:</label>
                                    <input type="password" name="password2" id="password2" class="form-control" placeholder="comfirm your password.." required>
                                    ${requestScope["error1"]}
                                    ${requestScope["error2"]}
                                </div>
                            </div>
                            <br>
                            <div class="row">
                            
                              <div class="col-lg-6">
                                <select class="form-control" id="gender">
                                    <option >Choose gender</option>
                                    <option>Male</option>
                                    <option>Female</option>
                                    </select>
                             </div>
                             <div class="col-lg-6">
                              	<label> Age:</label>
								<input class="form-control" type="text" name="age" id="age"  required value=${requestScope["age"]}>
								${requestScope["errorAge"] }
		
                             
                             </div>
                                    
                              
                                
                                
                                </div>
                            <br>
                            <input type="submit"   name="submit" id="Register" value="Submit" class="btn btn-primary btn-block btn-lg" >
    						<br>
    						<label>Already have an account</label>
    						<br><a href="Login.jsp">Login here..</a>
    
                        </form>
                    </div>
                </div>
    
                <div class="col-lg-3"></div>
            </div>
        </div>




</body>
</html>