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


@WebServlet("/g1")

public class Bank1 extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		String acc=req.getParameter("acc");
		String pas=req.getParameter("pas");
		
		HttpSession session = req.getSession();
        session.setAttribute("acc", acc);
        session.setAttribute("price", pas);
		
		Connection con=null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		String query="SELECT * FROM customerd WHERE acc=? and pin=?";
		
		try {
			con=ConnectorBank.requestConnection();
			ps=con.prepareStatement(query);
			int ac=Integer.parseInt(acc);
			ps.setInt(1, ac);
			ps.setString(2, pas);
			rs=ps.executeQuery();
			PrintWriter out=resp.getWriter();
			if(rs.next()==true) {
				String name=rs.getString(2);
				//out.println("<html><body><a href='accountCheck.html'></a></body></html>");
				resp.sendRedirect("accountCheck.html");	
			}
			else {
				req.setAttribute("error", "Invalid Password or Acc!");
				RequestDispatcher rd=req.getRequestDispatcher("login.jsp");
				rd.forward(req, resp);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
	}

}
