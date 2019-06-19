<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Voter User Interface</title>

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
	%>
	
<div class="bg>">
    <br>
    <marquee><h1 align="center" style="color: black;  -webkit-border-radius: 10px;-moz-border-radius: 10px;border-radius: 10px;   text-shadow: 1px 0 #fffb98, 0 1px #fdff87, 1px 0 red, 0 1px red;"> <b> Welcome to Easy Vote Online Voting System</b></h1></marquee>
    <div class="center3">
<ul>
            <li><a>
                <form action="DisplayElection" method="post">

                    <input style="background: #fc1a18;padding-left: 25px;padding-right: 25px;padding-top:20px;padding-bottom: 20px;text-shadow: -0.5px 0 black, 0 0.5px black, 0.5px 0 black, 0 -0.5px white ; border-color:black;font-size:25px; border-radius: 15px" type="submit" value="Set Vote" name="AddCandidate">
                </form>
            </a>

            </li>
            <li>
                <a>
                <form action="DisplayElection" method="post">

                    <input style="background: #77ff7f;padding-left: 25px;padding-right: 25px;padding-top:20px;padding-bottom: 20px;text-shadow: -0.5px 0 black, 0 0.5px black, 0.5px 0 black, 0 -0.5px white ; border-color:black;font-size:25px; border-radius: 15px" type="submit" value="View Election Result" name="AddCandidate">
                </form>
                </a>

            </li>





            <li>
                <a>
                <form action="Logout" method="post">
                    <input style="background: #fd29ff;padding-left: 25px;padding-right: 25px;padding-top:20px;padding-bottom: 20px;text-shadow: -0.5px 0 black, 0 0.5px black, 0.5px 0 black, 0 -0.5px white ; border-color:black;font-size:25px; border-radius: 15px" type="submit" value="Log Out">
                </form>
                </a>

            </li>
    </ul>



        
    </div>

</div>

</body>
</html>