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

@WebServlet("/emp")

public class Bank2 extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException
	{
		String acc=req.getParameter("eid");
		String pas=req.getParameter("epass");
		
		Connection con=null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		
		String query="SELECT * FROM bankjdbc WHERE id=? and password=?";
		
		try {
			con=ConnectorBank.requestConnection();
			ps=con.prepareStatement(query);
			int ac=Integer.parseInt(acc);
			ps.setInt(1, ac);
			ps.setString(2, pas);
			rs=ps.executeQuery();
			PrintWriter out=resp.getWriter();
			if(rs.next()) {
				String name=rs.getString(3);
				resp.sendRedirect("empInterface.html");
			}
			else {
				req.setAttribute("error", "Invalid Password or Acc!");
				RequestDispatcher rd=req.getRequestDispatcher("Elogin.jsp");
				rd.forward(req, resp);
			}
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
	}

}
