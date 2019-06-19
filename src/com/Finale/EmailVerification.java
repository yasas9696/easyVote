/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Finale;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class EmailVerification extends HttpServlet {

   

  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        try
        {
            //get database connection
            DBConnection dbconnection = new DBConnection();
            Connection connection = dbconnection.getConnection();
            PrintWriter out = response.getWriter();
            HttpSession session = request.getSession();
            RequestDispatcher view = request.getRequestDispatcher("EmailVerification.jsp");
            
            if(connection == null)
            {
                out.print("Database Connection Error");
            }
            else
            {
                String userCode = request.getParameter("verificationCode");
                String verificationCode = (String) session.getAttribute("VerificationCode");
                
                //check whether verification code match
                if(!(verificationCode.equals(userCode)))
                {
                    String error =  "<p style=\"color:white; font-weight:bold; font-size: 20px\"> Verification Code Does Not Match </p>";
                    request.setAttribute("VerificationError", error);
                    view.forward(request, response);
                }
                else
                {
                    String firstName = (String)session.getAttribute("FirstName");
                    String middleName = (String) session.getAttribute("MiddleName");
                    String lastName = (String) session.getAttribute("lastName");
                    String email = (String) session.getAttribute("email");
                    String nic = (String) session.getAttribute("nic");
                    String username = (String) session.getAttribute("Username");
                    String password = (String) session.getAttribute("Password");
                    int age = (int) session.getAttribute("Age");
                    
                    String insertStatement = "INSERT INTO voters(firstName, middleName, lastName, email, nic, username, password, age) values(?,?,?,?,?,?,?,?)";
                    PreparedStatement ps3 = connection.prepareStatement(insertStatement);
					
                    ps3.setString(1,  firstName);
                    ps3.setString(2,  middleName);
                    ps3.setString(3,  lastName);
                    ps3.setString(4, email);
                    ps3.setString(5,  nic);
                    ps3.setString(6, username);
                    ps3.setString(7,  password);
                    ps3.setInt(8,  age);
					
                    ps3.executeUpdate();
					
                    System.out.println("----------------------------------------");
                    System.out.println("Source: Register.java");
                    System.out.println("-----------------------------------------");
                    System.out.println("Registration Successful");
                    System.out.println("Name: " + firstName + " " + middleName + " " + lastName);
                    System.out.println("Email: " + email);
                    System.out.println("NIC Number: " + nic);
                    System.out.println("Age: " + age);
                    System.out.println("Username: " + username);
					
                    System.out.println();
                    System.out.println();
					
                    response.sendRedirect("Login.jsp");
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
