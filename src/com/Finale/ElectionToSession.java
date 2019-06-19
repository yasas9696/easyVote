package com.Finale;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/ElectionToSession")
public class ElectionToSession extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String selectedElection = request.getParameter("selectedElection");
		HttpSession session = request.getSession();
		RequestDispatcher forward =  null;
		
		session.setAttribute("SelectedElection",  selectedElection);
		
		System.out.println("----------------------------------------");
		System.out.println("Source: ElectionToSession.java");
		System.out.println("-----------------------------------------");
		
		System.out.println("Election Selected: " + selectedElection);
		System.out.println();
		System.out.println();
		
		String purpose = (String) session.getAttribute("Purpose");
		
		if(purpose.equals("Add Candidate"))
		{
			response.sendRedirect("AddCandidate.jsp");
		}
		if(purpose.equals("Remove Candidate"))
		{
			forward = request.getRequestDispatcher("DisplayCandidate");
			forward.forward(request,  response);
		}
		if(purpose.equals("Set Vote"))
		{
			forward = request.getRequestDispatcher("DisplayCandidate");
			forward.forward(request,  response);
		}
		if(purpose.equals("View Election Result"))
		{
			forward = request.getRequestDispatcher("ViewElectionResult");
			forward.forward(request,  response);
		}
	}

}
