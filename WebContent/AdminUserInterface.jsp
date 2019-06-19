<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin User Interface</title>
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="addin.css">

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
	<div class="bg>">
    <br>
    <marquee><h1 align="center" style="color: black;   -webkit-border-radius: 10px;-moz-border-radius: 10px;border-radius: 10px;   text-shadow: 1px 0 red, 0 1px red, 1px 0 red, 0 1px red;"> <b> Welcome to Admin User Interface</b></h1></marquee>
    <div class="center1">

      <ul>
            <li><a href="SetElection.jsp"> <input  type="button" style="background: red;padding-left: 25px;padding-right: 25px;padding-top:20px;padding-bottom: 20px;text-shadow: -0.5px 0 black, 0 0.5px black, 0.5px 0 black, 0 -0.5px black; border-color:black;font-size:25px; border-radius: 15px" value="Set Election" id="setElection"></a></li>
            <li><a><form action="DisplayElection" method="post">

                <input type="submit" style="background: lawngreen;padding-left: 25px; padding-top:20px;padding-bottom: 20px;text-shadow: -0.5px 0 black, 0 0.5px black, 0.5px 0 black, 0 -0.5px black ; border-color:black;font-size:25px; border-radius: 15px" value="Add Candidate" id="addCandidate" name="AddCandidate">
            </form></a></li>
            <li><a>
                <form action="DisplayElection" method="post">


                    <input style="background: deeppink;padding-left: 25px;padding-right: 25px;padding-top:20px ;padding-bottom: 20px;text-shadow: -0.5px 0 black, 0 0.5px black, 0.5px 0 black, 0 -0.5px black ; border-color:black;font-size:25px; border-radius: 15px" type="submit" value="Remove Election" id="removeElection"name="AddCandidate">
                </form></a>

            </li>
            	<li><a>
                <form action="GiveAdmin" method="post">


                    <input style="background: blue;padding-left: 25px;padding-right: 25px;padding-top:20px ;padding-bottom: 20px;text-shadow: -0.5px 0 black, 0 0.5px black, 0.5px 0 black, 0 -0.5px black ; border-color:black;font-size:25px; border-radius: 15px" type="submit" value="Give Admin Privileges" id="giveAdmin"name="giveAdmin">
                </form></a>

            </li>

</ul></div>
    <div class="center2">
        <ul>




            <li>
                <a>
                <form action="DisplayElection" method="post">

                    <input style="background: red;padding-left: 25px;padding-right: 25px;padding-top:20px;padding-bottom: 20px;text-shadow: -0.5px 0 black, 0 0.5px black, 0.5px 0 black, 0 -0.5px white ; border-color:black;font-size:25px; border-radius: 15px" type="submit" value="Remove Candidate" name="AddCandidate">
                </form>
                </a>


            </li>
            <li><a>
                <form action="DisplayElection" method="post">

                    <input style="background: lawngreen;padding-left: 25px;padding-right: 25px;padding-top:20px;padding-bottom: 20px;text-shadow: -0.5px 0 black, 0 0.5px black, 0.5px 0 black, 0 -0.5px white ; border-color:black;font-size:25px; border-radius: 15px" type="submit" value="Set Vote" name="AddCandidate">
                </form>
            </a>

            </li>
            <li>
                <a>
                <form action="DisplayElection" method="post">

                    <input style="background: deeppink;padding-left: 25px;padding-right: 25px;padding-top:20px;padding-bottom: 20px;text-shadow: -0.5px 0 black, 0 0.5px black, 0.5px 0 black, 0 -0.5px white ; border-color:black;font-size:25px; border-radius: 15px" type="submit" value="View Election Result" name="AddCandidate">
                </form>
                </a>

            </li>





            <li>
                <a>
                <form action="Logout" method="post">
                    <input style="background: blue;padding-left: 25px;padding-right: 25px;padding-top:20px;padding-bottom: 20px;text-shadow: -0.5px 0 black, 0 0.5px black, 0.5px 0 black, 0 -0.5px white ; border-color:black;font-size:25px; border-radius: 15px" type="submit" value="Log Out">
                </form>
                </a>

            </li>
    </ul>













    </div>

</div>
	
	
	
	
</body>
</html>