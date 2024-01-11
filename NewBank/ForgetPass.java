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

@WebServlet("/forget")

public class ForgetPass extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		String id=req.getParameter("fid");
		String acc=req.getParameter("fac");
		String name=req.getParameter("fem");
		
		Connection con=null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		
		String query="SELECT * FROM customerd WHERE acc=? and id=?";
		
		try {
			con=ConnectorBank.requestConnection();
			ps=con.prepareStatement(query);
			int fid=Integer.parseInt(id);
			ps.setInt(2, fid);
			long ac=Long.parseLong(acc);
			ps.setLong(1,ac);
			
			rs=ps.executeQuery();
			PrintWriter out=resp.getWriter();
			String n="";
			String p1="";
			if(rs.next()) {
				int fid2=rs.getInt(6);
				long l1=rs.getLong(1);
				 n=rs.getString(2);
				 p1=rs.getString(3);
				 if(fid==fid2 && l1==ac) {
				 out.println("<html><body bgcolor='skyblue'style='text-align:center;'><h1>Your Password is:"
				 		+ " "+p1+""
				 		+ "</h1></body></html>");
				 
				 out.println("<html><body><h1>back to Login</h1><a href='login.jsp'>"
				 		+ "<input type='button' value='Back to Login' "
				 		+ "style='background-color:green;color:white;cursor:pointer;'>"
				 		+ "</a></body></html>");
				 
				 }
				 else {
					 out.println("<html><body><h1>Invalid user</h1></body></html>"); 
				 }
			}
			else {
				out.println("<html><body><h1>Invalid user</h1></body></html>"); 
			}
				
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}

}
