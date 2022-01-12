package com.Driving_School.model;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;


public class MySQLConnect {
	static Connection conn = null;
	
	
	public static Connection getConn() {
		return conn;
	}

	public static void setConn(Connection conn) {
		MySQLConnect.conn = conn;
	}

	public static Connection connectDb() {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/drivingschool1", "root", "root");
//			 JOptionPane.showInternalMessageDialog(null, "ConnectionEstablished");
			 return conn;
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showInternalMessageDialog(null, e);

		}
		return conn;
	}

}
