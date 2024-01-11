package com.ps.NewBank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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

@WebServlet("/bank")
public class CheckBal extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException
	{
		Connection con=null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		
		HttpSession session = req.getSession();
        String acc = (String) session.getAttribute("acc");
        String pin = (String) session.getAttribute("price");
		
		int ac=Integer.parseInt(acc);
		String query="SELECT * FROM customerd WHERE acc=? and pin=?";
		
		try {
			con=ConnectorBank.requestConnection();
			ps=con.prepareStatement(query);
			ps.setInt(1, ac);
			ps.setString(2, pin);
			rs=ps.executeQuery();
			PrintWriter out=resp.getWriter();
			if(rs.next()) {
				double bal=rs.getDouble(4);
				out.println("<html><body><h1>Your balance is: "+bal+" Rs</h1></body></html>");	
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
}
}
