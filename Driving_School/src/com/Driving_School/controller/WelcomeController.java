package com.Driving_School.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

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
	 void Add_e(ActionEvent event) {
		 loadPage("AddNewEmployee");
	 }

	 @FXML
	 void Add_L(ActionEvent event) {
		 loadPage("AddLesson");
	 }

	 @FXML
	 void Add_s(ActionEvent event) {
		 loadPage("AddNewStudent");
	 }

	 @FXML
	 void Add_v(ActionEvent event) {
		 loadPage("AddVehicle");
	 }

	 @FXML
	 void view_e(ActionEvent event) {
		 loadPage("EmployeeInformation");
	 }

	 @FXML
	 void view_s(ActionEvent event) {
		 loadPage("StudentInformation");
	}

	
	 @FXML
	 private void OpenPayments(ActionEvent event) {
		 loadPage("Payments");
		
	 }
	
	 @FXML
	 private void EnterPay(ActionEvent event) {
		 loadPage("enterPay");
		
	 }
	
	 @FXML
	 void info(ActionEvent event) {
	 	 loadPage("info");
	 }

   	 void loadPage(String page) {
			Parent r = null;
			try {
				r = FXMLLoader.load(getClass().getResource("/com/Driving_School/view/"+page+".fxml"));
			} catch (IOException e) {
				Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, e);
			}
			bp.setCenter(r);
		

	 }
	
	
	
	

}
