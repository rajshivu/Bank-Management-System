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

@WebServlet("/pin")

public class ChangePin extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		String ac=req.getParameter("p1");
		String pass=req.getParameter("p2");
		String npass=req.getParameter("p3");
		String cpass=req.getParameter("p4");
		
		int acc=Integer.parseInt(ac);
	
		Connection con=null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		PreparedStatement ps4=null;
		
		String query="SELECT * FROM customerd WHERE acc=? and pin=?";
		
		try {
			con=ConnectorBank.requestConnection();
			ps=con.prepareStatement(query);
			ps.setInt(1, acc);
			ps.setString(2, pass);
			rs=ps.executeQuery();
			PrintWriter out=resp.getWriter();
			if(rs.next()) {
				String pas=rs.getString(3);
				if(pas.equals(pass)) {
					if(npass.equals(cpass)) {
						String s2 = "UPDATE customerd SET pin=? WHERE acc=?";
						ps4 = con.prepareStatement(s2);
						ps4.setString(1, npass);
						ps4.setInt(2, acc);
						ps4.executeUpdate();
						out.println("<h1>Password changed, New password is: "+npass+"</h1>");
					}
					else {
						out.println("check entered passwords");
					}
				}
				else {
					out.println("invalid password");
				}
			}
			else {
				out.println("Invalid user");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
