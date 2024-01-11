package com.ps.NewBank;

public class Details {
	
	private int acc;
	private String sname;
	private double bal;
	private String mail;
	private int id;
	private String password;
	
    public Details() {
		
	}
    
    public Details(int acc, String sname, double bal, String mail, 
		int id, String password) {
		this.acc = acc;
		this.sname = sname;
		this.bal = bal;
		this.mail = mail;
		this.id = id;
		this.password = password;
	}

	public int getAcc() {
		return acc;
	}

	public void setAcc(int acc) {
		this.acc = acc;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public double getBal() {
		return bal;
	}

	public void setBal(double bal) {
		this.bal = bal;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
