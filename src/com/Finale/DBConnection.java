package com.Finale;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public Connection getConnection() throws ClassNotFoundException, SQLException
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/OOP_Finale?verifyServerCertificate=false&useSSL=true", "root", "");
			return connection;
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
			return null;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
