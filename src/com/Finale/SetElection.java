package com.Finale;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.sun.corba.se.pept.transport.Connection;


@WebServlet("/SetElection")
public class SetElection extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try
		{
			//get http session
			HttpSession session = request.getSession();
			RequestDispatcher view = request.getRequestDispatcher("SetElection.jsp");
			
			String electionName = request.getParameter("electionName");
			String electionScope = request.getParameter("electionScope");
			String setBy = (String) session.getAttribute("Username");
			
			//System.out.println(electionName);
			//System.out.println(electionScope);
			//System.out.println(setBy);
			//System.out.println();
			//set database connection
			DBConnection dbconnection = new DBConnection();
			java.sql.Connection connection = dbconnection.getConnection();
			PrintWriter out = response.getWriter();
			
			if(connection == null)
			{
				out.print("SQL Database Connection Error");
				
			}
			else
			{
				//check if the election name is taken
				String electionCheck = "SELECT * FROM Election WHERE electionName = ?";
				
				PreparedStatement ps1 = connection.prepareStatement(electionCheck);
				ps1.setString(1,  electionName);
				
				ResultSet rs1 = ps1.executeQuery();
				boolean electionNameExist = rs1.next();
				
				if(electionNameExist == true)
				{
					request.setAttribute("ElectionNameError",  "An Election is Already Set for This Name");
					view.forward(request, response);
				}
				else
				{
					String insertStatement = "INSERT INTO Election(electionName, electionScope, setBy) VALUES(?,?,?)";
					PreparedStatement ps2 = connection.prepareStatement(insertStatement);
					
					ps2.setString(1,  electionName);
					ps2.setString(2,  electionScope);
					ps2.setString(3,  setBy);
					
					ps2.executeUpdate();
					
					//request.setAttribute("Success",  "Election is Successfully Set");
					//view.forward(request,  response);
					
					System.out.println("----------------------------------------");
					System.out.println("Source: SetElection.java");
					System.out.println("-----------------------------------------");
					System.out.println("Election Set Successfully");
					System.out.println("Election Name: " + electionName);
					System.out.println("Election Scope: " + electionScope);
					System.out.println("Election Set By: " + setBy);
					System.out.println();
					System.out.println();
					
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
