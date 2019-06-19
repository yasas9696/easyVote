package com.Finale;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/RemoveCandidate")
public class RemoveCandidate extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			DBConnection dbconnection = new DBConnection();
			Connection connection = dbconnection.getConnection();
			PrintWriter out = response.getWriter();
			
			if(connection == null)
			{
				out.print("Database Connection Error");
			}
			else
			{
				HttpSession session = request.getSession();
				int electionID = (int) session.getAttribute("ElectionID");
				System.out.println("----------------------------------------");
				System.out.println("Source: RemoveCandidate.java");
				System.out.println("-----------------------------------------");
				System.out.println("Selected Election ID: " + electionID);
				String candidateNIC = request.getParameter("selectedCandidate");
				System.out.println("Selected Candidate ID: " + candidateNIC);
				
				String deleteStatementParticipate = "DELETE FROM participate WHERE candidateID = ?";
				PreparedStatement ps1 = connection.prepareStatement(deleteStatementParticipate);
				ps1.setString(1, candidateNIC);
				ps1.executeUpdate();
				
				System.out.println("Candidate is Removed From Participate Table.");
				
				String deleteStatementCandidate = "DELETE FROM candidate WHERE nic = ?";
				PreparedStatement ps2 = connection.prepareStatement(deleteStatementCandidate);
				ps2.setString(1, candidateNIC);
				ps2.executeUpdate();
				
				System.out.println("Candidate is Removed From Candidate Table");
				connection.close();
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
