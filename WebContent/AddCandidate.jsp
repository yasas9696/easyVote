<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add CANDIDATE</title>
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
	
		if((session.getAttribute("Username") == null) || (session.getAttribute("Password") == null))
		{
			response.sendRedirect("Login.jsp");
		}
		else if(session.getAttribute("LoginAs").equals("Voter"))
		{
			response.sendRedirect("VoterUserInterface.jsp");
		}
	%>
	
	<div class="container">
    <div class="row">
        <div class="col-lg-3"></div>

        <div class="col-lg-6">
            <div id="ui">
                <h1 class="text-center">ADD CANDIDATE</h1><br>
                <form class="form-group text-center"  action="AddCandidate" method="post">


                            <label>First Name:</label>
                            <input type="text" name="firstName" id="firstName" class="form-control" required>
                        <br>



                            <label>Last Name:</label>
                            <input type="text" name="lastName" class="form-control" id="lastName" required>
                    <br>

                    <label>NIC Number:</label>
                    <input type="text" name="nic" id="nic" class="form-control" required>
                    <br>

                    <label>Date of Birth:</label>
                    <input type="date" name="date" class="form-control" id="date" required>
                    <br>
                    <label> Address:</label>
                    <input type="text" name="address" class="form-control" id="address" required>
                   <br>
		    ${requestScope["Error1"]}
		    	
		    		<label><input type="checkbox" name="checkBox" class="form-control"><br> Update Candidate Details If Already Exists In Database</b></label>
		    		

                    
                    <input type="submit"   name="Submit" id="Register" value="Add Candidate" class="btn btn-primary btn-block btn-lg" >
                    
			
		   


                </form>
                
                 <br>
		    <br>
		    <a href="AdminUserInterface.jsp"> <input class="btn btn-primary btn-block btn-lg" type="button" value="Admin User Interface"></a>
		    
			<br>
		    <form action="Logout" method="post">
		    <input class="btn btn-primary btn-block btn-lg" type="submit" value="Log Out">
		    </form>

		    <br>
            </div>
        </div>

        <div class="col-lg-3"></div>
    </div>
</div>


</body>
</html>