package com.ps.NewBank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorBank {
	
	public static Connection requestConnection() throws ClassNotFoundException, SQLException
	{
		 Connection con=null;
		 String url="jdbc:mysql://localhost:3306/bankjdbc";
		 String user="root";
		 String password="Tiger";
		 Class.forName("com.mysql.cj.jdbc.Driver");
		 con=DriverManager.getConnection(url, user, password);
		 
		 return con;
	}
}
