package com.Driving_School.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MySQLConnect {
	static Connection conn = null;
	
	
	public static Connection getConn() {
		return conn;
	}

	public static void setConn(Connection conn) {
		MySQLConnect.conn = conn;
	}

	public static void connectDb() {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/drivingschool1", "root", "root");
			 JOptionPane.showInternalMessageDialog(null, "ConnectionEstablished");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showInternalMessageDialog(null, e);

		}
		
	}

	public static ObservableList<Employee> getDataEmployess() { 
		ObservableList<Employee> list = FXCollections.observableArrayList();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from employee");
			while (rs.next()) {
//				System.out.println(rs.getString("emp_id"));
				list.add(new Employee(rs.getString("emp_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("mobile_num"), rs.getString("address")));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

}
