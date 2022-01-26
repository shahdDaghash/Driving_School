package com.Driving_School.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.Driving_School.model.MySQLConnect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	
	@FXML
    private Label lessons_total;

    @FXML
    private Label net_profit;

    @FXML
    private Label students_total;

    @FXML
    private Label total_paid;

    @FXML
    private Label trainers_taken;

    @FXML
    private Label trainers_total;
    
    @FXML
    private Label vehicles_taken;

    @FXML
    private Label vehicles_total;
	
	
    public void calculate() throws SQLException {
    	
    	Connection conn = MySQLConnect.connectDb();
		Statement stmt;
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT COUNT(student_id) FROM student;");
		rs.next();
		if(rs.getString(1)!=null) {
			students_total.setText(rs.getString(1));
		}
		
		
		conn = MySQLConnect.connectDb();
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT COUNT(trainer_id) FROM trainer");
		rs.next();
		if(rs.getString(1)!=null) {
			trainers_total.setText(rs.getString(1));
		}
		
		
		conn = MySQLConnect.connectDb();
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT COUNT(vehicle_num) FROM vehicle");
		rs.next();
		if(rs.getString(1)!=null) {
			vehicles_total.setText(rs.getString(1));
		}
		
		
		conn = MySQLConnect.connectDb();
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT COUNT(lesson_id) FROM vehicle_student");
		rs.next();
		if(rs.getString(1)!=null)
			lessons_total.setText(rs.getString(1));
		
    	
    	conn = MySQLConnect.connectDb();
		stmt = conn.createStatement();
		rs = stmt.executeQuery("select SUM(amount) from payments;");
		rs.next();
		if(rs.getString(1)!=null)
			total_paid.setText(rs.getString(1));
		
		
		conn = MySQLConnect.connectDb();
		stmt = conn.createStatement();
		rs = stmt.executeQuery("select SUM(20*amount/100) from payments;");
		rs.next();
		if(rs.getString(1)!=null)
			trainers_taken.setText(rs.getString(1));
		
		conn = MySQLConnect.connectDb();
		stmt = conn.createStatement();
		rs = stmt.executeQuery("select SUM(10*amount/100) from payments;");
		rs.next();
		if(rs.getString(1)!=null)
			vehicles_taken.setText(rs.getString(1));
		
		conn = MySQLConnect.connectDb();
		stmt = conn.createStatement();
		rs = stmt.executeQuery("select SUM(70*amount/100.0) from payments;");
		rs.next();
		if(rs.getString(1)!=null)
			net_profit.setText(rs.getString(1));
		
    }
    
    /*
     * 	SELECT COUNT(student_id) AS total_student FROM student;
		SELECT COUNT(emp_id) AS total_trainers FROM employee;
		SELECT COUNT(vehicle_num) AS total_vehile FROM vehicle;
		SELECT COUNT(lesson_id) AS total_lessons FROM vehicle_student;
     */
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1){
		try {
			calculate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@FXML
	public void OpenHome(ActionEvent event) throws SQLException {
		bp.setCenter(ap);
		calculate();
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
