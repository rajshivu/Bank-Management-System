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

@WebServlet("/view")

public class ViewUser extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException 
	{
		Connection con=null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		
		String query="SELECT * FROM customerd";
		
		try {
			con=ConnectorBank.requestConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			PrintWriter out=resp.getWriter();
			if(rs.next()) {
				int acc=rs.getInt(1);
				String name=rs.getString(2);
				double bal=rs.getDouble(4);
				String email=rs.getString(5);
				
				out.println("<h1>Acc Number is: "+acc+"</h1>"
						+"<h1>Acc Name is: "+name+"</h1>"
								+ "<h1>Acc Email is: "+email+"</h1>"
										+ "<h1>Acc Balance is: "+bal+" Rs</h1>");
				
			}
			else {
				out.println("<h1>Invalid account</h1>");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
