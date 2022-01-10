package com.Driving_School.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
    private TextField search_lastname;


    @FXML
    void search_Action(ActionEvent event) throws Exception {
    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/drivingschool1","root","root");
    	Statement stmt = con.createStatement();
    	//String id = search_name.getText();
    	String first_name = search_name.getText();
    	String family_name = search_lastname.getText();
    	
    	String sql0 = "SELECT student_id FROM student where first_name = '%"+first_name+"%' and family_name = '%"+family_name+"%' " ;
    	
    	ResultSet rs0 = stmt.executeQuery(sql0);
    	int id = 0;
    	while (rs0.next()) {
			id = rs0.getInt("student_id");

	      }
    	rs0.close();

    	
    	String sql1 = "SELECT COUNT(*) lesson_id FROM vehicle_student where student_id ="+id ;
    	String sql2 = "SELECT lesson_price FROM license_type where type_id = (select type_id from student_licensetype where student_id ="+id+");" ;
    	String sql3 = "select amoumt from payments where student_id ="+id ;

    	ResultSet rs1 = stmt.executeQuery(sql1);
    	int lesson_num = 0;
    	while (rs1.next()) {
			lesson_num = rs1.getInt("lesson_id");
	
	      }
    	
    	rs1.close();
    	
		ResultSet rs2 = stmt.executeQuery(sql2);
		int lesson_price = 0;
		while (rs2.next()) {
			lesson_price = rs2.getInt("lesson_price");
	
	      }
		
		rs2.close();
		
		int payed_amount = 0;
		
		ResultSet rs3 = stmt.executeQuery(sql3);
		while (rs3.next()) {
			payed_amount = rs3.getInt("lesson_id");
	
	      }
		
		rs3.close();
		
		
    	search_lb.setText(String.valueOf(payed_amount-(lesson_num*lesson_price)));


		
    }
	

}

	

