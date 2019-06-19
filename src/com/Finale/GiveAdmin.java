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


@WebServlet("/GiveAdmin")
public class GiveAdmin extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			DBConnection dbconnection = new DBConnection();
			Connection connection = dbconnection.getConnection();
			RequestDispatcher view = request.getRequestDispatcher("GiveAdmin.jsp");
			PrintWriter out = response.getWriter();
			
			//get the username of the voter
			String userName1 = request.getParameter("username");
			System.out.println("Username: " + userName1);
			String username11 = null;
			String password11 = null;
			
			//check whether voter username exists
			boolean usernameExist = false;
			
			if(connection == null)
			{
				out.print("Database Connection Error");
			}
			else
			{

				String usernameCheck = "SELECT * FROM voters WHERE username = ?";
				PreparedStatement ps1 = connection.prepareStatement(usernameCheck);
				PreparedStatement ps2 = connection.prepareStatement(usernameCheck);
				ps1.setString(1, userName1);
				ps2.setString(1, userName1);
				
				ResultSet rs1 = ps1.executeQuery();
				ResultSet rs2 = ps2.executeQuery();
				boolean val = rs1.next();
				
				if(val == false)
				{
					String usernameError = "<p style=\"color:white; font-weight:bold; font-size: 20px\"> Invlid Username </p>";
					request.setAttribute("UsernameError", usernameError);
					view.forward(request,  response);
					
				}
				else
				{
					while(rs2.next())
					{
						username11 = rs2.getString("username");
						password11 = rs2.getString("password");
					}
					
					//insert username and password into admins table
					String insertStatement = "INSERT INTO admins(username, password) VALUES(?,?)";
					PreparedStatement ps3 = connection.prepareStatement(insertStatement);
					ps3.setString(1,  username11);
					ps3.setString(2, password11);
					
					ps3.executeUpdate();
					
					System.out.println("Voter's Status Has Been Updated To Administrator");
					response.sendRedirect("AdminUserInterface.jsp");
				}
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

}
