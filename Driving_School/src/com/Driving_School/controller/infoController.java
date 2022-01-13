package com.Driving_School.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;

public class infoController implements Initializable {
	 
	@FXML
    private Label taxi_lessonP;

    @FXML
    private Label taxi_retestP;

    @FXML
    private Label taxi_testP;

	@FXML
    private Label bus_retest;
	
    @FXML
    private ImageView bus_image;
    
    @FXML
    private Label bus_lessonP;

    @FXML
    private Label bus_name;

    @FXML
    private Label bus_testP;
    
    @FXML
    private Label heavy_lesson;

    @FXML
    private Label heavy_retest;

    @FXML
    private Label heavy_test;

    @FXML
    private Label light_lesson;

    @FXML
    private Label light_retest;

    @FXML
    private Label light_test;

    @FXML
    private Label private_lesson;

    @FXML
    private Label private_retest;

    @FXML
    private Label private_test;

    @FXML
    private Label trella_lesson;

    @FXML
    private Label trella_retest;

    @FXML
    private Label trella_test;
    
   
    @FXML
    public void connect() throws Exception {

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/drivingschool1","root","root");
    	Statement stmt = con.createStatement();
    	////////////bus 
    	int	lesson_P = 0;
    	String bus_L_P = "SELECT lesson_price FROM license_type where type_name = 'private' ";
    	ResultSet rs0 = stmt.executeQuery(bus_L_P);
    	while (rs0.next()) {
    		lesson_P = rs0.getInt("lesson_price");
          }
    	rs0.close();
    	bus_lessonP.setText(String.valueOf(lesson_P));
    	
    
    	System.out.println(lesson_P);
    	
    	int	test_P = 0;
    	String bus_T_P = "SELECT test_price FROM prices where lesson_price = "+lesson_P+" ";
    	ResultSet rs1 = stmt.executeQuery(bus_T_P);
    	while (rs1.next()) {
    		test_P = rs1.getInt("test_price");
          }
    	bus_testP.setText(String.valueOf(test_P));
    	rs1.close();
    	bus_retest.setText(String.valueOf(test_P+80));
    	
    	
    	////////////taxi
    	
    	int	taxi_lesson = 0;
    	String taxi_L_P = "SELECT lesson_price FROM license_type where type_name = 'taxi' ";
    	ResultSet rst = stmt.executeQuery(taxi_L_P);
    	while (rst.next()) {
    		taxi_lesson = rst.getInt("lesson_price");
    		
          }
    	rst.close();
    	taxi_lessonP.setText(String.valueOf(taxi_lesson));
    	
        	
    	int	taxi_test_P = 0;
    	String taxi_T_P = "SELECT test_price FROM prices where lesson_price = "+taxi_lesson+" ";
    	ResultSet rst1 = stmt.executeQuery(taxi_T_P);
    	while (rst1.next()) {
    		taxi_test_P = rst1.getInt("test_price");
          }
    	taxi_testP.setText(String.valueOf(taxi_test_P));
    	rst1.close();
    	taxi_retestP.setText(String.valueOf(taxi_test_P+80));

    	//////////////private
    	int	private_lessonP = 0;
    	String private_L_P = "SELECT lesson_price FROM license_type where type_name = 'private' ";
    	ResultSet rsp = stmt.executeQuery(private_L_P);
    	while (rsp.next()) {
    		private_lessonP = rsp.getInt("lesson_price");
    		
          }
    	rsp.close();
    	private_lesson.setText(String.valueOf(private_lessonP));
    	
        	
    	int	private_test_P = 0;
    	String private_T_P = "SELECT test_price FROM prices where lesson_price = "+private_lessonP+" ";
    	ResultSet rsp1 = stmt.executeQuery(private_T_P);
    	while (rsp1.next()) {
    		private_test_P = rsp1.getInt("test_price");
          }
    	private_test.setText(String.valueOf(private_test_P));
    	rsp1.close();
    	private_retest.setText(String.valueOf(private_test_P+80));
    	
    	/////////trella
    	
    	int	trella_lessonP = 0;
    	String trella_L_P = "SELECT lesson_price FROM license_type where type_name = 'trella' ";
    	ResultSet rs_tr = stmt.executeQuery(trella_L_P);
    	while (rs_tr.next()) {
    		trella_lessonP = rs_tr.getInt("lesson_price");
    		
          }
    	rs_tr.close();
    	trella_lesson.setText(String.valueOf(trella_lessonP));
    	
        	
    	int	trella_test_P = 0;
    	String trella_T_P = "SELECT test_price FROM prices where lesson_price = "+trella_lessonP+" ";
    	ResultSet rs_tr1 = stmt.executeQuery(trella_T_P);
    	while (rs_tr1.next()) {
    		trella_test_P = rs_tr1.getInt("test_price");
          }
    	trella_test.setText(String.valueOf(trella_test_P));
    	rs_tr1.close();
    	trella_retest.setText(String.valueOf(trella_test_P+80));
    	
    	///////////heavy truck
    	
    	int	heavy_lessonP = 0;
    	String heavy_L_P = "SELECT lesson_price FROM license_type where type_name = 'heavy truck' ";
    	ResultSet rsh = stmt.executeQuery(heavy_L_P);
    	while (rsh.next()) {
    		heavy_lessonP = rsh.getInt("lesson_price");
    		
          }
    	rsh.close();
    	heavy_lesson.setText(String.valueOf(heavy_lessonP));
    	
        	
    	int	heavy_test_P = 0;
    	String heavy_T_P = "SELECT test_price FROM prices where lesson_price = "+heavy_lessonP+" ";
    	ResultSet rsh1 = stmt.executeQuery(heavy_T_P);
    	while (rsh1.next()) {
    		heavy_test_P = rsh1.getInt("test_price");
          }
    	heavy_test.setText(String.valueOf(heavy_test_P));
    	rsh1.close();
    	heavy_retest.setText(String.valueOf(heavy_test_P+80));
    	
    	//////////ligh truck 
    	
    	int	light_lessonP = 0;
    	String light_L_P = "SELECT lesson_price FROM license_type where type_name = 'light truck' ";
    	ResultSet rsl = stmt.executeQuery(light_L_P);
    	while (rsl.next()) {
    		light_lessonP = rsl.getInt("lesson_price");
    		
          }
    	rsl.close();
    	light_lesson.setText(String.valueOf(light_lessonP));
    	
        	
    	int	light_test_P = 0;
    	String light_T_P = "SELECT test_price FROM prices where lesson_price = "+light_lessonP+" ";
    	ResultSet rsl1 = stmt.executeQuery(light_T_P);
    	while (rsl1.next()) {
    		light_test_P = rsl1.getInt("test_price");
          }
    	light_test.setText(String.valueOf(light_test_P));
    	rsl1.close();
    	light_retest.setText(String.valueOf(light_test_P+80));
		
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		
	try {
		connect();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	}
    
    }

    
   
    

