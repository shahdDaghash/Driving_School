package com.Driving_School.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddEmployeeController {
	public AddEmployeeController() {

	}

	@FXML
	private Button clearButton;
	
	@FXML
	private Button addButton;
	
	@FXML
	private TextField id;
	
	@FXML
	private TextField firstName;
	
	@FXML
	private TextField lastName;
	
	@FXML
	private TextField mobileNum;
	
	@FXML
	private TextField address;

	public void addEmployee() {
	
		String empID = id.getText().toString();
		String empFirstName = firstName.getText().toString();
		String empLastName = lastName.getText().toString();
		String mobile = mobileNum.getText().toString();
		String empAddress = address.getText().toString();
		String sql = "insert into employee (emp_id,first_name,last_name,mobile_num,address)values(?,?,?,?,? )";
		Connection conn = com.Driving_School.model.MySQLConnect.getConn();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, empID);
			pst.setString(2, empFirstName);
			pst.setString(3, empLastName);
			pst.setString(4, mobile);
			pst.setString(5, empAddress);
			pst.execute();
			JOptionPane.showMessageDialog(null, "Employee Add succes");
			clearFileds();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"Falied to add the employee");
		}

	}
	
	public void clearFileds() {
		id.clear();
		firstName.clear();
		lastName.clear();
		mobileNum.clear();
		address.clear();
	}
	
}
