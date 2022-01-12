package com.Driving_School.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.Driving_School.model.Employee;
import com.Driving_School.model.MySQLConnect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EmployeeInformationController {

	Employee emp;
	
    @FXML
    private Label address_label;

    @FXML
    private Label emp_id_label;

    @FXML
    private Label first_name_label;

    @FXML
    private Label job_title_label;

    @FXML
    private Label last_name_label;

    @FXML
    private Label mobile_num_label;
    
    
    public EmployeeInformationController() {
    	this.address_label = new Label();
    	this.emp_id_label = new Label();
    	this.first_name_label = new Label();
    	this.last_name_label = new Label();
    	this.mobile_num_label = new Label();
    	this.job_title_label = new Label();
    }
    

    @FXML
    void openemployeeTable(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/Driving_School/view/ViewEmployeeListForEmployee.fxml"));
    	Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage primaryStage = new Stage();
		primaryStage.setTitle("Al-Aqsa Driving School");
		primaryStage.setScene(scene);
		primaryStage.initModality(Modality.WINDOW_MODAL);
		primaryStage.setResizable(false);
		primaryStage.show();
		
		ViewEmployeeListForEmployeeController cont = loader.getController();
		cont.c_emp = emp;
    	
		final Node source = (Node) event.getSource();
		final Stage stage2 = (Stage) source.getScene().getWindow();
		stage2.close();
    }
    
    
    
    public void showInformation(Employee em) throws SQLException {
    	if(em != null) {
    		emp_id_label.setText(em.getEmp_id());
    		first_name_label.setText(em.getFirst_name());
    		last_name_label.setText(em.getLast_name());
    		mobile_num_label.setText(em.getMobile_num());
    		address_label.setText(em.getAddress());
    		
    		
    		Connection conn = MySQLConnect.connectDb();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select e.emp_id from employee e where e.emp_id IN (select t.trainer_id from trainer t);");
			int cnt = 0;
			while(rs.next()) {
				if(rs.getString("emp_id").equals(em.getEmp_id()))
					cnt++;
			}
			if(cnt == 1) {
				job_title_label.setText("Trainer");
			}
			else {
				conn = MySQLConnect.connectDb();
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select e.emp_id from employee e where e.emp_id IN (select s.secretary_id from secretary s);");
				cnt = 0;
				while(rs.next()) {
					if(rs.getString("emp_id").equals(em.getEmp_id()))
						cnt++;
				}
				if(cnt == 1) {
					job_title_label.setText("Secretary");
				}
				else {
					job_title_label.setText("None");
				}
			}
    	}
    	emp = em;
    }
    

    @FXML
    void updateStudentRecord(ActionEvent event) throws IOException, SQLException {
    	if(emp == null) {
			JOptionPane.showInternalMessageDialog(null, "You must select a record first!");
			return;
		}
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/Driving_School/view/ModifyEmployee.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage primaryStage = new Stage();
		primaryStage.setTitle("Al-Aqsa Driving School - Update Employee Record");
		primaryStage.setScene(scene);
		primaryStage.initModality(Modality.WINDOW_MODAL);
		primaryStage.setResizable(false);
		primaryStage.show();
		
		ModifyEmployeeController mec = loader.getController();
		
		final Node source = (Node) event.getSource();
		final Stage stage2 = (Stage) source.getScene().getWindow();
		stage2.close();
		
		mec.showInformation(emp);
    }

}












