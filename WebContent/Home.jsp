<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EasyVote</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   <style type="text/css">
       body{
           background-image: url("g.jpg");
           background-size:cover ;
            background-position: center center;
            background-attachment: fixed ;

       }

        #ai{
            width: 50%;
            text-align: center;
            margin: auto;

            background-color: black;
            border-radius: 20px;



        }

        #aii{
            width: 50%;
            text-align: center;
            margin: auto;
            background-color: black;
            border-radius: 10px;

        }

        #aiii{
            width: 50%;
            text-align: center;
            margin: auto;
            background-color: black;
            border-radius: 10px;

        }

        

    </style>
</head>
<body>
	
	<h1 align="center" type="text" style="font-size:200px;"><b ><i>Easy Vote</i></b></h1><br>
<h5 align="center" ><b>New polls are up and running but they will not be up forever..
    <br>Just login and then go to current polls to vote for your favorite candidates..</b></h5>
<br><br>
<div id="ai">

     
    
  	<a href="Login.jsp"> <input class="btn btn-primary btn-block btn-lg" type="submit" value="View Election Result" name="AddCandidate"></a>
</div>
<br><br>
<div id="aii">

	<a href="Login.jsp">
    <button    class="btn btn-primary btn-block btn-lg" >Login for Vote</button>
    </a>
</div>
<br><br>
<div id="aiii">

    <a href="Register.jsp">
    <button    class="btn btn-primary btn-block btn-lg" >Register for Login</button>
    </a>

</div><br><br>
<DIV align="center">
    <input type="image" src="12.gif"></DIV>
	
	

</body>
</html>