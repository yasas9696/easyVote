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


@WebServlet("/SetVote")
public class SetVote extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try
		{
			DBConnection dbconnection = new DBConnection();
			Connection connection = dbconnection.getConnection();
			PrintWriter out = response.getWriter();
			RequestDispatcher view = request.getRequestDispatcher("VoteError.jsp");
			
			if(connection == null)
			{
				out.print("Database Connection Error");
			}
			else
			{

				HttpSession session = request.getSession();
				int electionID = (int) session.getAttribute("ElectionID");
				System.out.println("----------------------------------------");
				System.out.println("Source: SetVote.java");
				System.out.println("-----------------------------------------");
				System.out.println("Selected Election ID: " + electionID);
				String candidateNIC = request.getParameter("selectedCandidate");
				System.out.println("Selected Candidate ID: " + candidateNIC);
				String username = (String) session.getAttribute("Username");
				System.out.println("Logged In Username: " + username);
				
				//get the voter nic for the loggedin user
				String voterID = null;
				
				String getVoterID = "SELECT nic FROM voters WHERE username = ?";
				PreparedStatement ps1 = connection.prepareStatement(getVoterID);
				ps1.setString(1,  username);
				
				ResultSet rs1 = ps1.executeQuery();
				
				while(rs1.next()) {
					voterID = rs1.getString("nic");
				}
				
				System.out.println("Voter ID: " + voterID);
				
				//check if the vote already set for the selected election by the voter
				String voteExist = "SELECT * FROM votes WHERE voterID = ?  AND electionID = ?";
				PreparedStatement ps2 = connection.prepareStatement(voteExist);
				
				ps2.setString(1, voterID);
				ps2.setInt(2, electionID);
				
				boolean voteExists = false;
				
				ResultSet rs2 = ps2.executeQuery();
				
				voteExists = rs2.next();
				
				if(voteExists == true)
				{
					String error = "<h1 style=\"color:white; font-weight:bold; font-size: 40px\"> You Have Already Voted For This Election</h1><br><br><br>";
					request.setAttribute("Error",  error);
					view.forward(request, response);
				}
				else
				{
					String insertVote = "INSERT INTO votes(voterID, candidateID, electionID) VALUES(?,?,?)";
					PreparedStatement ps3 = connection.prepareCall(insertVote);
					ps3.setString(1, voterID);
					ps3.setString(2, candidateNIC);
					ps3.setInt(3, electionID);
					
					ps3.executeUpdate();
					
					System.out.println("Vote Is Added To The Election");
					System.out.println("Voter ID: " + voterID);
					System.out.println("Candidate ID: " + candidateNIC);
					
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
