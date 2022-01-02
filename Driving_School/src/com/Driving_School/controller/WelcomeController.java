package com.Driving_School.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class WelcomeController implements Initializable {

	
	@FXML
	private AnchorPane ap;

	@FXML
	private BorderPane bp;
	
	@FXML
	private Button home;
	
	@FXML
	private Button employee;
	
	@FXML
	private Button student;
	
	@FXML
	private Button payments;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void OpenHome(ActionEvent event) {
		bp.setCenter(ap);	
	}
	
	
	@FXML
	private void OpenEmployee(ActionEvent event) {
		loadPage("AddNewEmployee");
		
	}
	
	@FXML
	private void OpenStudent(ActionEvent event) {
		loadPage("ViewStudents");
		
	}
	
	@FXML
	private void OpenPayments(ActionEvent event) {
		loadPage("Payments");
		
	}

	private void loadPage(String page) {
			Parent r = null;
			try {
				r = FXMLLoader.load(getClass().getResource("/com/Driving_School/view/"+page+".fxml"));
			} catch (IOException e) {
				Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, e);
			}
			bp.setCenter(r);
		

	}
	
	
	
	

}
