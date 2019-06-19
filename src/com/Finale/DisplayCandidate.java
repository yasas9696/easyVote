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


@WebServlet("/DisplayCandidate")
public class DisplayCandidate extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try
		{
			DBConnection dbconnection = new DBConnection();
			Connection connection = dbconnection.getConnection();
			HttpSession session = request.getSession();
			String purpose = (String) session.getAttribute("Purpose");
			System.out.println("Purpose DisplayCandidate: " + purpose);
			String selectedElection = (String) session.getAttribute("SelectedElection");
			int electionID = 0;
			String candidateNIC = null;
			
			RequestDispatcher view = request.getRequestDispatcher("SelectCandidate.jsp");
			
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
				System.out.println("Source: DisplayCandidate.java");
				System.out.println("------------------------------------------------");
				System.out.println("Election Details:....");
				System.out.println("Selected Election Name: " + selectedElection);
				System.out.println("Selected Election ID: " + electionID);
				System.out.println();
				System.out.println();
				System.out.println();
				
				//get the nic number of candidates who participate for the selected election
				String getCandidateDetails = "select c.nic, c.firstName, c.lastName, c.dob, c.cAddress from oop_finale.candidate c, oop_finale.participate p where p.candidateID = c.nic and p.electionID = ? ";
				
				PreparedStatement ps2 = connection.prepareStatement(getCandidateDetails);
				PreparedStatement ps3 = connection.prepareStatement(getCandidateDetails);
				ps2.setInt(1, electionID);
				ps3.setInt(1, electionID);
				
				ResultSet rs2 = ps2.executeQuery();
				
				ResultSet rs3 = ps3.executeQuery();
				
				String str = "<table  cellpadding=7 cellspacing = 10 style=\"color:white; font-weight:bold; font-size: 20px\"> ";
				
				if(rs3.next() == false)
				{
					RequestDispatcher view2 = request.getRequestDispatcher("NoCandidateFound.jsp");
					view2.forward(request,  response);
					
				}
				
				while(rs2.next())
				{
					str += "<tr><td align=left> <input style=\" transform: scale(2)\" type=\"radio\" name=\"selectedCandidate\" value=\"" + rs2.getString("nic") + "\"  </td>  <td style=\" font-size: 0.1px\">...................................................\"\r\n" + 
							"						+ \"................................................................................................................................\"\r\n" + 
							"						+ \".......................................................\"\r\n" + 
							"						+ \"....................................................................................\"\r\n" + 
							"						+ \"..........................................................................</td>   <td align=left>Name: </td> <td style=\" font-size: 0.1px\">...................................................\"\r\n" + 
							"						+ \"................................................................................................................................\"\r\n" + 
							"						+ \".......................................................\"\r\n" + 
							"						+ \"....................................................................................\"\r\n" + 
							"						+ \"..........................................................................</td>  <td align=left>" + rs2.getString("firstName") + " " + rs2.getString("lastName") + "</td></tr>"
							+ "<tr> <td align=left></td> <td style=\" font-size: 0.1px\">...................................................\"\r\n" + 
							"						+ \"................................................................................................................................\"\r\n" + 
							"						+ \".......................................................\"\r\n" + 
							"						+ \"....................................................................................\"\r\n" + 
							"						+ \"..........................................................................</td>   <td align=left> NIC Number: </td> <td style=\" font-size: 0.1px\">...................................................\"\r\n" + 
							"						+ \"................................................................................................................................\"\r\n" + 
							"						+ \".......................................................\"\r\n" + 
							"						+ \"....................................................................................\"\r\n" + 
							"						+ \"..........................................................................</td>  <td align=left> " + rs2.getString("nic") + "</td></tr>" +
							"<tr> <td align=left></td> <td style=\" font-size: 0.1px\">...................................................\"\r\n" + 
							"						+ \"................................................................................................................................\"\r\n" + 
							"						+ \".......................................................\"\r\n" + 
							"						+ \"....................................................................................\"\r\n" + 
							"						+ \"..........................................................................</td>  <td align=left>Date Of Birth: </td> <td style=\" font-size: 0.1px\">...................................................\"\r\n" + 
							"						+ \"................................................................................................................................\"\r\n" + 
							"						+ \".......................................................\"\r\n" + 
							"						+ \"....................................................................................\"\r\n" + 
							"						+ \"..........................................................................</td>  <td align=left> " + rs2.getString("dob") + "</td></tr>" +
							"<tr> <td align=left> </td> <td style=\" font-size: 0.1px\">...................................................\"\r\n" + 
							"						+ \"................................................................................................................................\"\r\n" + 
							"						+ \".......................................................\"\r\n" + 
							"						+ \"....................................................................................\"\r\n" + 
							"						+ \"..........................................................................</td>  <td align=left> Address: </td><td style=\" font-size: 0.1px\">...................................................\"\r\n" + 
							"						+ \"................................................................................................................................\"\r\n" + 
							"						+ \".......................................................\"\r\n" + 
							"						+ \"....................................................................................\"\r\n" + 
							"						+ \"..........................................................................</td>   <td align=left> " + rs2.getString("cAddress") + "</td></tr>"
									+ ""
									+ ""
									+ "<tr><td><br></td><td><br></td><td><br></td> </tr>";
					
				}
				
				str += "</table>";
				
				request.setAttribute("InputtBox",  str);
				
				view.forward(request,  response);
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
