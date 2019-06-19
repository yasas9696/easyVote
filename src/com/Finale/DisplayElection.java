package com.Finale;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.sun.corba.se.pept.transport.Connection;

@WebServlet("/DisplayElection")
public class DisplayElection extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try
		{
			DBConnection dbconnection = new DBConnection();
			java.sql.Connection connection = dbconnection.getConnection();
			String purpose = request.getParameter("AddCandidate");
			HttpSession session = request.getSession();
			session.setAttribute("Purpose",  purpose);
			
			System.out.println("----------------------------------------");
			System.out.println("Source: DisplayElection.java");
			System.out.println("-----------------------------------------");
			
			System.out.println("Purpose is: " + purpose);
			
			RequestDispatcher view = request.getRequestDispatcher("SelectElection.jsp");
			
			PrintWriter out = response.getWriter();
			
			
			String sqlStatement = "SELECT * FROM Election";
			
			PreparedStatement ps1 = connection.prepareStatement(sqlStatement);
			PreparedStatement ps2 = connection.prepareStatement(sqlStatement);
			ResultSet rs1 = ps1.executeQuery();
			ResultSet rs2 = ps2.executeQuery();
			
			if(rs2.next() == false)
			{
				RequestDispatcher view2 = request.getRequestDispatcher("NoElectionFound.jsp");
				view2.forward(request, response);
			}
			
			String str = "<table align=center cellpadding= 10 style=\"color:white; font-weight:bold; font-size: 40px\"> ";
			
			while(rs1.next())
			{
				str += "<tr><td align=left> <input style=\" transform: scale(2)\" type=\"radio\" name=\"selectedElection\" value=\"" + rs1.getString("electionName") + "\"> </td>  <td style=\" font-size: 0.1px\">..................................................."
						+ "................................................................................................................................"
						+ "......................................................."
						+ "...................................................................................."
						+ "..........................................................................</td>   <td align=left>" + rs1.getString("electionName") + "</td>  <td align=right> </td>   </tr> <tr><td><br></td><td><br></td><td><br></td> </tr>";
			}
			
			System.out.println();
			System.out.println();
			
			str += "</table>";
			request.setAttribute("InputBox",  str);
			
			view.forward(request,  response);
			
	
		
			
			
			
			
			
			
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
