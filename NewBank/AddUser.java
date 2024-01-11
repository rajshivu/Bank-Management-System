package com.ps.NewBank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addUser")

public class AddUser extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{ 
		String name=req.getParameter("uname");
		String email=req.getParameter("uemail");
		String uid=req.getParameter("uid");
		int id=Integer.parseInt(uid);
		
		Connection con=null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		
		
		String query="INSERT INTO customerd (cname,email,id)VALUES(?,?,?)";
		PrintWriter out=resp.getWriter();
		try {
			con=ConnectorBank.requestConnection();
			ps=con.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setInt(3, id);
			int i=0;
			i=ps.executeUpdate();
			
			if(i>0) {
				out.println("<h1>Account created</h1>");
			}
			else {
				out.println("<h1>Something wents wrong</h1>");
			}
		} catch (ClassNotFoundException | SQLException e) {
			if (e instanceof java.sql.SQLIntegrityConstraintViolationException) {
                // Handle the specific integrity constraint violation exception
				out.println("Foreign key violation: " + e.getMessage());
                // Implement your error handling logic here
            } else {
                // Handle other SQL exceptions
                e.printStackTrace();
            }
		}	
	}

}
