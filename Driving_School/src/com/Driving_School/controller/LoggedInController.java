package com.Driving_School.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import application.Main;

public class LoggedInController {
	
	
	public LoggedInController() {

	}

	@FXML
	private Button button;
	@FXML
	private Label wrongLogIn;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;

	public void userLogIn(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
		checkLogin();
	}

	private void checkLogin() throws IOException, SQLException, ClassNotFoundException {
		Main m = new Main();
		String userName = username.getText().toString();
		String pass = password.getText().toString();
		String sql = "Select * from employee Where emp_id='" + userName + "' and emp_id='" + pass + "'";
		//System.out.println("Here");
		Connection conn = com.Driving_School.model.MySQLConnect.connectDb();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			wrongLogIn.setText("Success!");
			//change scene to the main page
			m.changeScene("/com/Driving_School/view/Welcome.fxml");
		}

		else if (username.getText().isEmpty() && password.getText().isEmpty()) {
			wrongLogIn.setText("Please enter your data.");
		}

		else {
			wrongLogIn.setText("Wrong username or password!");
		}

	}
	

}