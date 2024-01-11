package com.ps.NewBank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class CDetails implements CustomerDetails{

	@Override
	public List getCustomer() {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String q="SELECT * FROM customerd";
		Details s=null;
		ArrayList<Details> customerList= new ArrayList<Details>();
		try {
			con=ConnectorBank.requestConnection();
			ps=con.prepareStatement(q);
			rs=ps.executeQuery();
			while(rs.next()) {
				int acc=rs.getInt(1);
				String sname=rs.getString(2);
				double bal=rs.getDouble(4);
				String mail=rs.getString(5);
				int id=rs.getInt(6);
				String password=rs.getString(3);
				s=new Details(acc,sname,bal,mail,id,password);
				customerList.add(s);	
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customerList;
	
	}

	@Override
	public Details getCustomer(int acc1) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String q="SELECT * FROM customerd WHERE acc=?";
		Details s=null;
		ArrayList<Details> customerList= new ArrayList<Details>();
		try {
			con=ConnectorBank.requestConnection();
			ps=con.prepareStatement(q);
			ps.setInt(1,acc1);
			rs=ps.executeQuery();
			if(rs.next()) {
				int acc=rs.getInt(1);
				String sname=rs.getString(2);
				double bal=rs.getDouble(4);
				String mail=rs.getString(5);
				int id=rs.getInt(6);
				String password=rs.getString(3);
				s=new Details(acc,sname,bal,mail,id,password);
				customerList.add(s);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	

}
