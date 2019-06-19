<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="STYLE.css">
<title>LogIn</title>
</head>
<body>
	<div class="box">
        <h2>LOGIN</h2>
        <form name="login" action="Login" method="post">
            <div class="inputBox">
                <input type="text" id="username" name="username" required >
                <label>User Name</label>
                ${requestScope["Error1"]}
            </div>

            <div class="inputBox">
                <input type="password" id="password" name="password" required>
                <label>Password</label>
                ${requestScope["Error2"]}
            </div>
            <input type="submit"   name="" value="Submit" >
            <br><br>
            <div class="inputBox">
            <label>Not yet registered?</label><br><br> <a style="color:white;" href="Register.jsp">Register here..</a>
            </div>
        </form>
    </div>
		
</body>
</html>