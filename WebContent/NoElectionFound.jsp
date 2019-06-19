<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>No Election Found</title>
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style type="text/css">
        body{
            background-image: url("z.jpg");
            background-size:cover ;
            background-position: center center;
            background-attachment: fixed ;


        }
        #ui{

            background-color:#333 ;
            padding-top: 20px;
            padding-bottom: 20px;
            padding-left: 150px;
            padding-right: 150px;

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
	
		if((session.getAttribute("Username") == null) || (session.getAttribute("Password") == null))
		{
			response.sendRedirect("Login.jsp");
		}
		
	%>
		<div class="container">
    <div class="row">
        <div class="col-lg-6">
            <div id="ui">


                <h1 style="color: white; align:center; font-size: 70px; "  > <b>  No Election Has Been Set</b> </h1>
                <br><br><br><br><br><br>
                <div align="center">
                	
                    <% 
                    if(session.getAttribute("LoginAs").equals("Voter"))
                    {%>
                    	<a href="VoterUserInterface.jsp"> <input type="button" class="btn btn-primary btn-block btn-lg" value="Voter User Interface"></a><br>
                    <% 	
                    }
                    else if(session.getAttribute("LoginAs").equals("Admin"))
                    {
                    	%>
                    		<a href="AdminUserInterface.jsp"> <input type="button" class="btn btn-primary btn-block btn-lg" value="Admin User Interface"></a><br>
                    
                    <% 
                    }
                    
                    %>

               		

                    <form action="Logout" method="post">
                        <input type="submit" class="btn btn-primary btn-block btn-lg" value="Log Out">
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
</body>
</html>