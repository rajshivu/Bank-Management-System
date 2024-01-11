package com.ps.NewBank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addAmount")

public class AddAmount extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException
	{
		String add=req.getParameter("add");
		String amount=req.getParameter("amount");
		int ad=Integer.parseInt(add);
		double am=Double.parseDouble(amount);
		
		String q = "UPDATE customerd SET bal=bal+? WHERE acc=?";
		Connection con=null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		
		try {
			con=ConnectorBank.requestConnection();
			ps = con.prepareStatement(q);
			ps.setInt(2, ad);
			ps.setDouble(1, am);
			int i=ps.executeUpdate();
			PrintWriter out=resp.getWriter();
			if(i>0) {
				
				out.println("<html><body><h1>Amount added: "+am+" Rs</h1></body></html>");	
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	

}
