package com.Driving_School.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.Driving_School.model.Employee;
import com.Driving_School.model.MySQLConnect;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ModifyEmployeeController {
	
	Employee emp;

    @FXML
    private TextField address_txt;

    @FXML
    private Label emp_id_label;

    @FXML
    private TextField first_name_txt;

    @FXML
    private Label job_title_label;

    @FXML
    private TextField last_name_txt;

    @FXML
    private TextField mobile_num_txt;
    
    
    ObservableList<Employee> listE;

	int index = -1;

	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	

    @FXML
    void cancelModify(ActionEvent event) throws SQLException, IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/Driving_School/view/EmployeeInformation.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage primaryStage = new Stage();
		primaryStage.setTitle("Al-Aqsa Driving School");
		primaryStage.setScene(scene);
		primaryStage.initModality(Modality.WINDOW_MODAL);
		primaryStage.setResizable(false);
		primaryStage.show();

		EmployeeInformationController cont = loader.getController();

		final Node source = (Node) event.getSource();
		final Stage stage2 = (Stage) source.getScene().getWindow();
		stage2.close();

		cont.showInformation(emp);
    }

    @FXML
    void performUpdate(ActionEvent event) throws SQLException, IOException {
    	conn = MySQLConnect.connectDb();

		String sql = "UPDATE employee SET first_name = ? , last_name = ? , mobile_num = ? , address = ? where emp_id = ? ;";

		Employee selected = emp;
		
		pst = conn.prepareStatement(sql);
		pst.setString(1, first_name_txt.getText());
		pst.setString(2, last_name_txt.getText());
		pst.setString(3, mobile_num_txt.getText());
		pst.setString(4, address_txt.getText());
		pst.setString(5, emp_id_label.getText());
		pst.execute();
		
		
		selected.setFirst_name(first_name_txt.getText());
		selected.setLast_name(last_name_txt.getText());
		selected.setMobile_num(mobile_num_txt.getText());
		selected.setAddress(address_txt.getText());
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/Driving_School/view/EmployeeInformation.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage primaryStage = new Stage();
		primaryStage.setTitle("Al-Aqsa Driving School");
		primaryStage.setScene(scene);
		primaryStage.initModality(Modality.WINDOW_MODAL);
		primaryStage.setResizable(false);
		primaryStage.show();
		
		EmployeeInformationController cont = loader.getController();

		final Node source = (Node) event.getSource();
		final Stage stage2 = (Stage) source.getScene().getWindow();
		stage2.close();

		cont.showInformation(selected);
		
    }
    
    public void showInformation(Employee em) throws SQLException {

		emp = em;

		emp_id_label.setText(em.getEmp_id());
		first_name_txt.setText(em.getFirst_name());
		last_name_txt.setText(em.getLast_name());
		mobile_num_txt.setText(em.getMobile_num());
		address_txt.setText(em.getAddress());
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
    

}
