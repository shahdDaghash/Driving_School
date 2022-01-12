package com.Driving_School.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

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


    @FXML
    private ToggleGroup role;

    @FXML
    private RadioButton secretary;

    @FXML
    private RadioButton trainer;

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
			JOptionPane.showMessageDialog(null, "Employee Add success");
			clearFileds();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"Failed to add employee, already exist");
		}
		String emp_role = "";
		String col_name = "";
		
		if(trainer.isSelected()) {
			emp_role="trainer";
			col_name = "trainer_id";
		}else {
			emp_role="secretary";
			col_name = "secretary_id";
		}
		sql = "insert into "+ emp_role + " ("+ col_name +")values(?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, empID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
