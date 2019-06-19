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

@WebServlet("/ViewElectionResult")
public class ViewElectionResult extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			DBConnection dbconnection = new DBConnection();
			Connection connection = dbconnection.getConnection();
			HttpSession session = request.getSession();
			String purpose = (String) session.getAttribute("Purpose");
			String selectedElection = (String) session.getAttribute("SelectedElection");
			int electionID = 0;
			String candidateNIC = null;
			
			RequestDispatcher views = request.getRequestDispatcher("ViewElectionResults.jsp");
			PrintWriter out = response.getWriter();
			
			if(connection == null)
			{
				out.print("Database Connection Error");
			}
			else
			{
				//get the election id for the selected election
				String getElectionID = "SELECT * FROM election WHERE electionName = ?";
				PreparedStatement ps1 = connection.prepareStatement(getElectionID);
				ps1.setString(1, selectedElection);
				
				ResultSet rs1 = ps1.executeQuery();
				
				
				while(rs1.next()) 
				{
					electionID = rs1.getInt("electionID");
					session.setAttribute("ElectionID",  electionID);
				}
				
				System.out.println("------------------------------------------------");
				System.out.println("Source: ViewElectionResult.java");
				System.out.println("------------------------------------------------");
				System.out.println("Purpose: " + purpose);
				System.out.println("Election Details:....");
				System.out.println("Selected Election Name: " + selectedElection);
				System.out.println("Selected Election ID: " + electionID);
				System.out.println();
				System.out.println();
				System.out.println();
				
				String getResult = "SELECT c.nic, c.firstName, c.lastName, c.dob, c.cAddress, COUNT(vv.voterID) AS 'Votes' FROM candidate c, votes vv WHERE vv.candidateID=c.nic AND vv.electionID = ? GROUP BY c.nic, c.firstName, c.lastName, c.dob, c.cAddress ORDER BY COUNT(vv.voterID) DESC ";
				PreparedStatement ps2 = connection.prepareStatement(getResult);
				ps2.setInt(1,electionID);
				
				PreparedStatement ps3 = connection.prepareStatement(getResult);
				ps3.setInt(1, electionID);
				ResultSet rs3 = ps3.executeQuery();
				
				if(rs3.next() == false) {
					RequestDispatcher view2 = request.getRequestDispatcher("NoCandidateFound.jsp");
					view2.forward(request,  response);
				}
				
				System.out.println("SQL query executing");
				
				String str = "<table cellpadding=7 style=\"color:white; font-weight:bold; font-size: 20px\"> ";
				
				ResultSet rs2 = ps2.executeQuery();
				
				System.out.println("Query Execution Successful");

				
				while(rs2.next())
				{
					str += "<tr><td> </td>   <td>Name: </td> <td style=\" font-size: 0.1px\">...................................................\"\r\n" + 
							"						+ \"................................................................................................................................\"\r\n" + 
							"						+ \".......................................................\"\r\n" + 
							"						+ \"....................................................................................\"\r\n" + 
							"						+ \"..........................................................................</td> <td>" + rs2.getString("firstName") + " " + rs2.getString("lastName") + "</td></tr>"
							+ "<tr> <td></td> <td> NIC Number: </td> <td style=\" font-size: 0.1px\">...................................................\"\r\n" + 
							"						+ \"................................................................................................................................\"\r\n" + 
							"						+ \".......................................................\"\r\n" + 
							"						+ \"....................................................................................\"\r\n" + 
							"						+ \"..........................................................................</td><td> " + rs2.getString("nic") + "</td></tr>" +
							"<tr> <td></td> <td>Date Of Birth: </td><td style=\" font-size: 0.1px\">...................................................\"\r\n" + 
							"						+ \"................................................................................................................................\"\r\n" + 
							"						+ \".......................................................\"\r\n" + 
							"						+ \"....................................................................................\"\r\n" + 
							"						+ \"..........................................................................</td> <td> " + rs2.getString("dob") + "</td></tr>" +
							"<tr> <td> </td> <td> Address: </td><td style=\" font-size: 0.1px\">...................................................\"\r\n" + 
							"						+ \"................................................................................................................................\"\r\n" + 
							"						+ \".......................................................\"\r\n" + 
							"						+ \"....................................................................................\"\r\n" + 
							"						+ \"..........................................................................</td> <td> " + rs2.getString("cAddress") + "</td></tr><tr> <td> </td> <td>Number Of Votes: </td> <td style=\" font-size: 0.1px\">...................................................\"\r\n" + 
									"						+ \"................................................................................................................................\"\r\n" + 
									"						+ \".......................................................\"\r\n" + 
									"						+ \"....................................................................................\"\r\n" + 
									"						+ \"..........................................................................</td><td>" + rs2.getString("Votes") + " </td> </tr> </tr> <tr><td><br></td><td><br></td><td><br></td> </tr>";
				}
			
				
				
				
				
				str += "</table>";
				
				request.setAttribute("ElectionResult",  str);
				request.setAttribute("ElectionName", selectedElection);
				
				views.forward(request, response);
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
