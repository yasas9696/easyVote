<%-- 
    Document   : EmailVerification
    Created on : May 21, 2018, 4:20:49 PM
    Author     : pasindu
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrator Privileges</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style type="text/css">
        body{
            background-image: url("9.jpg");
            background-size:cover ;
            background-position: center center;
            background-attachment: fixed ;


        }
         #ui{

            background-color:#333 ;
            padding-top: 20px;
            padding-bottom: 20px;
            padding-left: 250px;
            padding-right: 250px;
            
            margin-top: 60px;
            border-radius: 10px;
            opacity: 0.8;
            margin-right: -600px;
            
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
	
		if((session.getAttribute("VerificationCode") == null))
		{
			response.sendRedirect("Register.jsp");
		}
		
	%>
<diV class="container">
    <diV class="row">
        <div class="col-lg-6">
            <div id="ui">
                <h1 class="text-center"><b>Verify Your E-Mail Address</b></h1><br>

                    <form action="EmailVerification" method="post" class="form-group text-center">
                        <label> Enter The Verification Code  </label>
                        <input type="text" class="form-control" name="verificationCode"  required>
                        ${requestScope["VerificationError"]}
                        <br>
						<br>
						<br>
						<br>
						<br>
						<input type="submit" class="btn btn-primary btn-block btn-lg"  value="Verify E-Mail Address" id="GiveAdmin">
                    
					</form>
					<br> <br> <br> <br>
					 
                    
                    	<a href="Home.jsp"> <input type="button" class="btn btn-primary btn-block btn-lg" value="Home"></a><br>
                    

               		

                    <form action="Logout" method="post">
                        <input type="submit" class="btn btn-primary btn-block btn-lg" value="Log Out">
                    </form>
            </div>
        </div>
    </diV>
</diV>
</body>
</html>