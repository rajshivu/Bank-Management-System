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

@WebServlet("/trans")

public class TransferMoney extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException 
	{
		String ac1=req.getParameter("yan");
		String ac2=req.getParameter("ran");
		String pas=req.getParameter("ypin");
		String am=req.getParameter("amount");
		
		int acc1=Integer.parseInt(ac1);
		int acc2=Integer.parseInt(ac2);
		double amount=Double.parseDouble(am);
		
		Connection conc=null;
		Connection con1=null;
		Connection con2=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		
		
		String query="SELECT * FROM customerd WHERE acc=? and pin=?";
		
		try {
			conc=ConnectorBank.requestConnection();
			ps=conc.prepareStatement(query);
			
			ps.setInt(1, acc1);
			ps.setString(2,pas);
			rs=ps.executeQuery();
			PrintWriter out=resp.getWriter();
			if(rs.next()) {
				double bal=rs.getDouble(4);
				if(bal>=amount) {
					
					String s2 = "UPDATE customerd SET bal=bal+? WHERE acc=?";
					String s3 = "UPDATE customerd SET bal=bal-? WHERE acc=?";
					ps1 = conc.prepareStatement(s2);
					ps1.setDouble(1, amount);
					ps1.setInt(2, acc2);
					int i=ps1.executeUpdate();
					if(i>0) {
						ps2 = conc.prepareStatement(s3);
						ps2.setDouble(1, amount);
						ps2.setInt(2, acc1);
						ps2.executeUpdate();
						out.println("<h1>Amount added to account: "+amount+"</h1>");
						
					}
					else {
						out.println("<h1>inavid account number</h1>");
						
					}	
				}
				else {
					out.println("Insufficient balance");
				}
			}
			else {
				out.println("User not found");
			}
				
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}
