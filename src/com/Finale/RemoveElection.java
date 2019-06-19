package com.Finale;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/RemoveElection")
public class RemoveElection extends HttpServlet {

       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try
		{
			int electionID = 0;
			String selectedElection = request.getParameter("selectedElection");
			
			DBConnection dbconnection = new DBConnection();
			Connection connection = dbconnection.getConnection();
			PrintWriter out = response.getWriter();
			
			if(connection == null)
			{
				out.print("Database Connection Error");
			}
			else
			{
				//get the electionID for the selected election
				String getElectionID = "SELECT * FROM election WHERE electionName = ?";
				PreparedStatement ps1 = connection.prepareStatement(getElectionID);
				ps1.setString(1, selectedElection);
				
				ResultSet rs1 = ps1.executeQuery();
				
				
				while(rs1.next()) 
				{
					electionID = rs1.getInt("electionID");
				}
				
				//display Election details
				System.out.println("----------------------------------------");
				System.out.println("Source: RemoveElection.java");
				System.out.println("-----------------------------------------");
				System.out.println("Election Details...");
				System.out.println("Selected Election ID: " + electionID);
				System.out.println("Selected Election Name: " + selectedElection);
				
				//remove the election from the election table
				String deleteStatementElection = "DELETE FROM election WHERE electionID = ?";
				
				PreparedStatement ps2 = connection.prepareStatement(deleteStatementElection);
				ps2.setInt(1,  electionID);
				
				ps2.executeUpdate();
			
				System.out.println("Election Removed From Election Table");
				
				//remove related data to the election from other tables
				String deleteStatementParticipate = "DELETE FROM participate WHERE electionID = ?";
				
				PreparedStatement ps3 = connection.prepareStatement(deleteStatementParticipate);
				ps3.setInt(1,  electionID);
				
				ps3.executeUpdate();
				
				System.out.println("Election Details Removed From Participate Table");
				System.out.println();
				System.out.println();
				
				response.sendRedirect("AdminUserInterface.jsp");
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
