package com.Driving_School.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class enterPayController  {
	
	    @FXML
	    private TextField amount;

	    @FXML
	    private Button bt;

	    @FXML
	    private TextField date;

	    @FXML
	    private TextField id;

	    @FXML
	    private TextField name;


	    @FXML
		void save(ActionEvent event) throws Exception {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/drivingschool1", "root", "root");
			String studentID = id.getText();
			String moneyAmount = amount.getText();
			String PayDate = date.getText();

			if (Integer.parseInt(moneyAmount) > PaymentsController.result) {
				JOptionPane.showMessageDialog(null, "The amount paid is more than the Requested amount, please re-check");
				clearFileds();
			}

			else {

				String insert = "insert into payments(student_id, payment_date, amount) values(?,?,?);  ";
				PreparedStatement pst;
				try {
					pst = con.prepareStatement(insert);
					pst.setString(1, studentID);
					pst.setString(2, PayDate);
					pst.setString(3, moneyAmount);
					pst.execute();

					JOptionPane.showMessageDialog(null, "Employee Add success");
					clearFileds();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Failed submit Payment, check data");
				}
			}
		}

	    	
	    	

	    
	    
	    public void clearFileds() {
			id.clear();
			name.clear();
			amount.clear();
			date.clear();
		}


}
