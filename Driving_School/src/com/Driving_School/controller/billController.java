package com.Driving_School.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class billController implements Initializable{
	
	    @FXML
	    private Label amount;

	    @FXML
	    private Label date;

	    @FXML
	    private Label id;

	    @FXML
	    private Label name;

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			Connection conn;
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/drivingschool1", "root", "root");
				Statement stmt = conn.createStatement();
				String sql = "SELECT student_id, payment_date, amount FROM payments WHERE Payment_id = (SELECT MAX(payment_id) FROM payments);";
				ResultSet rs = stmt.executeQuery(sql);
				String printed_id = null;
				String printed_date = null;
				String printed_amount = null;

				while (rs.next()) {
					printed_id = rs.getString("student_id");
					printed_date = rs.getString("payment_date");
					printed_amount = rs.getString("amount");

				}
				
				rs.close();
				String sql1 = "SELECT first_name, last_name FROM student WHERE student_id = " + printed_id + "; ";
				ResultSet rs1 = stmt.executeQuery(sql1);
				String printed_firstname = null;
				String printed_lastname = null;
				while (rs1.next()) {
					printed_firstname = rs1.getString("first_name");	
					printed_lastname = rs1.getString("last_name");	

				}
				rs1.close();
				
				name.setText(String.valueOf(""+printed_firstname+ " "+printed_lastname+""));
				id.setText(String.valueOf(printed_id));
				date.setText(String.valueOf(printed_date));
				amount.setText(String.valueOf(printed_amount));


			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
}
