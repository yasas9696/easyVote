package com.Finale;

import java.io.IOException;
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

@WebServlet("/AddCandidate")
public class AddCandidate extends HttpServlet {
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try
		{
			DBConnection dbconnection = new DBConnection();
			java.sql.Connection connection = dbconnection.getConnection();
			HttpSession session = request.getSession();
			RequestDispatcher view = request.getRequestDispatcher("AddCandidate.jsp");
			
			String nic = request.getParameter("nic");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String dateOfBirth = request.getParameter("date");
			String address = request.getParameter("address");
			String checkBox = request.getParameter("checkBox");
			String selectedElection = (String) session.getAttribute("SelectedElection");
			int electionID = 0;
			
			//check whether candidate nic exists in candidate table
			String nicCheck = "SELECT * FROM candidate WHERE nic = ?";
			PreparedStatement ps1 = connection.prepareStatement(nicCheck);
			ps1.setString(1,  nic);
			
			ResultSet rs1 = ps1.executeQuery();
			
			boolean nicExist = rs1.next();
			
			//get the electionID for the selectedElection
			String getElectionID = "SELECT * FROM election WHERE electionName = ?";
			PreparedStatement ps2 = connection.prepareStatement(getElectionID);
			ps2.setString(1, selectedElection);
			
			ResultSet rs2 = ps2.executeQuery();
			
			
			while(rs2.next()) 
			{
				electionID = rs2.getInt("electionID");
			}
			
			//display Candidate details to the console.
			System.out.println("------------------------------------");
			System.out.println("Source: AddCandidate.java");
			System.out.println("------------------------------------");
			System.out.println("Candidate Details");
			System.out.println("NIC Number: " + nic);
			System.out.println("Name: " + firstName + " " + lastName);
			System.out.println("Date of Birth: " + dateOfBirth);
			System.out.println("Address: " + address);
			System.out.println("CheckBox: " + checkBox);
			
			//display Election Details to the console.
			System.out.println("Selected Election ID: " + electionID);
			System.out.println("Selected Election Name: " + selectedElection);
			
			
			
			String electionCheck = "SELECT * FROM participate WHERE electionID=? AND candidateID = ?";
			PreparedStatement ps3 = connection.prepareStatement(electionCheck);
			ps3.setInt(1,  electionID);
			ps3.setString(2,  nic);
			ResultSet rs3 = ps3.executeQuery();
			boolean isOnElection = rs3.next();
			
			
			if(nicExist == false)
			{
				String insertStatementCandidate = "INSERT INTO candidate(nic, firstName, lastName, dob, cAddress) VALUES(?,?,?,?,?)";
				String insertStatementParticipate = "INSERT INTO participate(electionID, candidateID) VALUES(?,?)";
				
				PreparedStatement ps4 = connection.prepareStatement(insertStatementCandidate);
				ps4.setString(1, nic);
				ps4.setString(2, firstName);
				ps4.setString(3, lastName);
				ps4.setString(4,  dateOfBirth);
				ps4.setString(5,  address);
				
				ps4.executeUpdate();
				
				PreparedStatement ps5 = connection.prepareStatement(insertStatementParticipate);
				ps5.setInt(1,  electionID);
				ps5.setString(2, nic);
				
				ps5.executeUpdate();
				
				System.out.println("Candidate is Added to Database");
				System.out.println("Candidate is Added to the Election");
				
				response.sendRedirect("AdminUserInterface.jsp");
			} 
			
			else if(nicExist == true && isOnElection == false && checkBox == null)
			{
				String insertStatementParticipate = "INSERT INTO participate(electionID, candidateID) VALUES(?,?)";
				PreparedStatement ps6 = connection.prepareStatement(insertStatementParticipate);
				ps6.setInt(1, electionID);
				ps6.setString(2,  nic);
				
				ps6.executeUpdate();
				
				System.out.println("Candidate Already Exists In System Database.");
				System.out.println("Candidate is Added to the Selected Election");
				System.out.println();
				
				response.sendRedirect("AdminUserInterface.jsp");
			} 
			else if(nicExist == true && isOnElection == false && checkBox.equals("on"))
			{
				String updateStatement = "UPDATE candidate SET firstName=?, lastName=?, dob=?, cAddress=? WHERE nic=?";
				String insertStatementParticipate = "INSERT INTO participate(electionID, candidateID) VALUES(?,?)";
				PreparedStatement ps7 = connection.prepareStatement(updateStatement);
				ps7.setString(1, firstName);
				ps7.setString(2, lastName);
				ps7.setString(3, dateOfBirth);
				ps7.setString(4, address);
				ps7.setString(5, nic);
				
				ps7.executeUpdate();
				
				PreparedStatement ps8 = connection.prepareStatement(insertStatementParticipate);
				ps8.setInt(1, electionID);
				ps8.setString(2, nic);
				
				System.out.println("Candidate Details Are Updated");
				System.out.println("Candidate Is Added To The Seleced Election");
				
				response.sendRedirect("AdminUserInterface.jsp");
				
				
				
			}
			else if(nicExist == true && isOnElection == true && checkBox.equals("on"))
			{
				String updateStatement = "UPDATE candidate SET firstName=?, lastName=?, dob=?, cAddress=? WHERE nic=?";
				PreparedStatement ps9 = connection.prepareStatement(updateStatement);
				ps9.setString(1, firstName);
				ps9.setString(2, lastName);
				ps9.setString(3, dateOfBirth);
				ps9.setString(4, address);
				ps9.setString(5,  nic);
				
				ps9.executeUpdate();
				System.out.println("Candidate Details Are Updated.Nic Already Exists In Participate Table");
				response.sendRedirect("AdminUserInterface.jsp");
			}
			else if(nicExist == true && isOnElection == true && checkBox == null)
			{
				String candidateExist = "<p style=\"color:red;\" > This Candidate is Already Added to the Selected Election...</p>";
				request.setAttribute("Error1",  candidateExist);
				
				System.out.println("Candidate is Already Added to the Selected ELectioin");
				view.forward(request,  response);
			}
			
			System.out.println();
			System.out.println();
			
			
			//check if the candidate is already added to the database
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
