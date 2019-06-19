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


@WebServlet("/Register")
public class Register extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try
		{
			String firstName = request.getParameter("firstName");
			String middleName = request.getParameter("middleName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String nic = request.getParameter("nic");
			String username = request.getParameter("username");
			String password1 = request.getParameter("password1");
			String password2 = request.getParameter("password2");
			int age  = Integer.parseInt(request.getParameter("age"));
			
			
			RequestDispatcher view = request.getRequestDispatcher("Register.jsp");
			
			request.setAttribute("firstname", firstName);
			request.setAttribute("middlename", middleName);
			request.setAttribute("lastname", lastName);
			request.setAttribute("email", email);
			request.setAttribute("nic", nic);
			request.setAttribute("username", username);
			request.setAttribute("age",  age);
			
			
			
				
			
			DBConnection dbconnection = new DBConnection();
			Connection connection = dbconnection.getConnection();
			PrintWriter out = response.getWriter();
			
			if(connection == null)
			{
				out.print("Database Connection Error");
			}
			else
			{
				//check whether user name is already taken
				String usernameCheck = "SELECT * FROM voters WHERE username = ?";
				String nicCheck = "SELECT * FROM voters WHERE nic = ?";
				
				
				PreparedStatement ps1 = connection.prepareStatement(usernameCheck);
				ps1.setString(1,  username);
				
				PreparedStatement ps2 = connection.prepareStatement(nicCheck);
				ps2.setString(1,  nic);
				
				ResultSet rs1 = ps1.executeQuery();
				boolean usernameExist = rs1.next();
				
				ResultSet rs2 = ps2.executeQuery();
				boolean nicExist = rs2.next();
				

				if(!(password1.equals(password2)))
				{
					String passwordWrong = "<p style=\"color:red;\" > Passwords Does Not Match..</p>";
					request.setAttribute("error2", passwordWrong);
					view.forward(request,  response);
				}
				
				else if(age < 18)
				{
					String ageInvalid = "<p style=\"color:red;\" > Must Be Above 18 To Register..</p>";
					request.setAttribute("errorAge", ageInvalid);
					view.forward(request,  response);
				}
				
				else if(usernameExist == true)
				{
					String usernameTaken = "<p style=\"color:red;\" > Username is Already Taken..</p>";
					request.setAttribute("error3", usernameTaken);
					view.forward(request,  response);
				}
				else if(nicExist == true)
				{
					String nicTaken = "<p style=\"color:red;\" > A User Has Already Registered With This NIC Number..</p>";
					request.setAttribute("nicExistError", nicTaken);
					view.forward(request,  response);
				}
				else
				{
					
					//prepare and send an email to the user with the email verification code
                                        String mailSubject = "Easy Vote Verification";
                                        String mailBody = "Dear Easy Vote User <br> Use The Following Code To Verify Your Account Details. ";
                                        String verificationCode;
                                        
                                        //get the verification code
                                        VerificationCodeGenerator vcg = new VerificationCodeGenerator(1000,99999999);
                                        vcg.GenerateVerificationCode();
                                        verificationCode = vcg.GetVerificationCode();
                                        
                                        mailBody += " " + verificationCode + " <br>";
                                        
                                        System.out.println("Sending Mail....");
                                        
                                        //SendEmail se = new SendEmail(email, mailSubject, mailBody);
                                        //se.SendEmail();
                                        
                                        System.out.println("Mail Sent Successfully");
                                        
                                        //add the key to a session
                                        HttpSession session = request.getSession();
                                        session.setAttribute("VerificationCode", verificationCode);
                                        session.setAttribute("FirstName", firstName);
                                        session.setAttribute("MiddleName", middleName);
                                        session.setAttribute("LastName", lastName);
                                        session.setAttribute("Email", email);
                                        session.setAttribute("NIC", nic);
                                        session.setAttribute("Username", username);
                                        session.setAttribute("Password", password1);
                                        session.setAttribute("Age", age);
                                        
                                        response.sendRedirect("Test.jsp");
                                        	
				}
			}
			
			
			
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		}
		

	}

}
