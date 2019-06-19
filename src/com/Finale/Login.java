package com.Finale;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Login")
public class Login extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			
			DBConnection dbconnection = new DBConnection();
			Connection connection = dbconnection.getConnection();
			PrintWriter out = response.getWriter();
			
			RequestDispatcher view = request.getRequestDispatcher("Login.jsp");
			
			if(connection == null)
			{
				out.print("Database Connection Error!");
			}
			else
			{
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				String passwordFromDatabase = null;
				boolean passwordMatch = false;
				
				
				//check whether username exists in any of the tables
				String usernameCheckVoters = "SELECT * FROM voters WHERE username = ?";
				String uesrnameCheckAdmins = "SELECT * FROM admins WHERE username = ?";
				
				PreparedStatement ps1 = connection.prepareStatement(usernameCheckVoters);
				ps1.setString(1,  username);
				ResultSet rs1 = ps1.executeQuery();
				boolean usernameInVoters = rs1.next();
				
				PreparedStatement ps2 = connection.prepareStatement(uesrnameCheckAdmins);
				ps2.setString(1,  username);
				ResultSet rs2 = ps2.executeQuery();
				boolean usernameInAdmins = rs2.next();
				
				//get the related password for the username
				if(usernameInVoters)
				{
					passwordFromDatabase = (String) (rs1.getObject("password"));
					passwordMatch = password.equals(passwordFromDatabase);
				}
				
				if(usernameInVoters == false)
				{
					String invalidUsername = "<p style=\"color:red;\" > Invalid Username...</p>";
					request.setAttribute("Error1", invalidUsername);
					view.forward(request, response);
				}
				else if(usernameInVoters == true && passwordMatch == false)
				{
					String invalidPassword = "<p style=\"color:red;\" > Invalid Password...</p>";
					request.setAttribute("Error2",  invalidPassword);
					view.forward(request,  response);
				}
				else if((usernameInVoters == true && usernameInAdmins == false) && passwordMatch == true)
				{
				
					HttpSession session = request.getSession();
					session.setAttribute("Username",  username);
					session.setAttribute("Password", password);
					session.setAttribute("LoginAs",  "Voter");
					
					
					response.sendRedirect("VoterUserInterface.jsp");
				}
				else if((usernameInVoters == true && usernameInAdmins == true) && passwordMatch == true)
				{
					
					HttpSession session = request.getSession();
					session.setAttribute("Username",  username);
					session.setAttribute("Password", password);
					session.setAttribute("LoginAs",  "Admin");
					
					
					response.sendRedirect("AdminUserInterface.jsp");
				}
				else
				{
					response.sendRedirect("Login.jsp");
				}
			}
			
			
			
			
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
		
	
	}

}
