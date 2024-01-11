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

@WebServlet("/update")

public class UpdateUser extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException 
	{
		String name=req.getParameter("upName");
		String ac=req.getParameter("acc");
		String email=req.getParameter("upEmail");
		String pin=req.getParameter("upPin");
		String bal=req.getParameter("upBal");
		int balance= Integer.parseInt(bal);
		int acc= Integer.parseInt(ac);
		
		Connection con=null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		
		String query="UPDATE customerd SET pin=?,cname=?,email=?,bal=+? WHERE acc=?";
		
		try {
			con=ConnectorBank.requestConnection();
			ps=con.prepareStatement(query);
			ps.setString(1, pin);
			ps.setString(2, name);
			ps.setString(3, email);
			ps.setInt(4, balance);
			ps.setInt(5, acc);
			int i=0;
			i=ps.executeUpdate();
			PrintWriter out=resp.getWriter();
			if(i>0) {
				out.println("<h1>Account updated</h1>");
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
