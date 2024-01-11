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

@WebServlet("/delete")

public class DeleteUser extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		String ac=req.getParameter("dacc");
		String pin=req.getParameter("dpin");
		int acc= Integer.parseInt(ac);
		int id= Integer.parseInt(pin);
		
		Connection con=null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		
		String query="DELETE FROM customerd WHERE acc=? and id=?";
		
		try {
			con=ConnectorBank.requestConnection();
			ps=con.prepareStatement(query);
			ps.setInt(1, acc);
			ps.setInt(2, id);
			int i=0;
			i=ps.executeUpdate();
			PrintWriter out=resp.getWriter();
			if(i>0) {
				out.println("<h1>Account deleted</h1>");
			}
			else {
				out.println("<h1>Account not found</h1>");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
