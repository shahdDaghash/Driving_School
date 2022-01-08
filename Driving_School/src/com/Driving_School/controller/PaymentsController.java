package com.Driving_School.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PaymentsController {

	

    @FXML
    private Button search_btn;

    @FXML
    private Label search_lb;

    @FXML
    private TextField search_name;

    @FXML
    void search_Action(ActionEvent event) throws Exception {
    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/drivingschool1","root","root");
    	Statement stmt = con.createStatement();
    	String id = search_name.getText();
    	String sql = "SELECT COUNT(*) lesson_id FROM vehicle_student where student_id ="+id ;
    	String sql1 = "SELECT lesson_price FROM license_type where type_id = (select type_id from student_licensetype where student_id ="+id+");" ;
    	String sql2 = "select amoumt from payments where student_id ="+id ;

    	ResultSet rs = stmt.executeQuery(sql);
    	int lesson_num = 0;
    	while (rs.next()) {
			lesson_num = rs.getInt("lesson_id");
	
	      }
    	
    	rs.close();
    	
		ResultSet rs1 = stmt.executeQuery(sql1);
		int lesson_price = 0;
		while (rs1.next()) {
			lesson_price = rs1.getInt("lesson_price");
	
	      }
		
		rs1.close();
		ResultSet rs2 = stmt.executeQuery(sql2);

		
		
		
		int payed_amount = 0;

		
		
		while (rs2.next()) {
			payed_amount = rs2.getInt("lesson_id");
	
	      }
		
		rs2.close();
		

	
		
		search_lb.setText(String.valueOf(payed_amount-(lesson_num*lesson_price)));
    }
	
	
//	@SuppressWarnings("unused")
//	private static void calculations() throws SQLException {
//	
//		String name;
//		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/drivingschool1","root","root");
//		Statement stmt = con.createStatement();
//		String sql = "SELECT COUNT(*) As s_num FROM student" ;
//		ResultSet rs = stmt.executeQuery(sql);
//		
//		String sql1 = "SELECT student_id FROM student where first_name and family_name;" ;
//		ResultSet rs1 = stmt.executeQuery(sql);
//		
//		while (rs.next()) {
//	        int supplierID = rs.getInt("s_num");
//	       
//	        System.out.println(supplierID);
//	      }
//	
//	}
//	
//	

}

	
/*	private void checkLogin() throws IOException, SQLException, ClassNotFoundException {
		Main m = new Main();
		String userName = username.getText().toString();
		String pass = password.getText().toString();
		String sql = "Select * from employee Where emp_id='" + userName + "' and emp_id='" + pass + "'";
		Connection conn = com.Driving_School.model.MySQLConnect.getConn();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			wrongLogIn.setText("Success!");
			//change scene to the main page
			m.changeScene("/com/Driving_School/view/AddNewEmployee.fxml");
		}

}*/
