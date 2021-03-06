package com.Driving_School.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.Driving_School.model.MySQLConnect;
import com.Driving_School.model.Student;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * 
 * @author Shahd
 *
 */
public class StudentInformationController {

	Student stu;

	@FXML
	private Label address_label;

	@FXML
	private Label eye_test_date_label;

	@FXML
	private Label first_name_label;

	@FXML
	private Label last_lesson_label;

	@FXML
	private Label last_name_label;

	@FXML
	private Label last_payment_label;

	@FXML
	private Label liscence_type_label;

	@FXML
	private Label mobile_num_label;

	@FXML
	private Label process_status_label;

	@FXML
	private Label student_id_label;

	@FXML
	private Label trainer_id_label;

	@FXML
	private Label trainer_name_label;
	
	@FXML
	private Label tests_label;
	
    @FXML
    private Label lessons_taken_label;
	
	
	public StudentInformationController() {
		this.address_label = new Label();
		this.eye_test_date_label = new Label();
		this.first_name_label = new Label();
		this.last_lesson_label = new Label();
		this.last_name_label = new Label();
		this.last_payment_label = new Label();
		this.liscence_type_label = new Label();
		this.mobile_num_label = new Label();
		this.process_status_label = new Label();
		this.student_id_label = new Label();
		this.trainer_id_label = new Label();
		this.trainer_name_label = new Label();
		this.tests_label = new Label();
		this.lessons_taken_label = new Label();
	}

	@FXML
	void openStudentTable(ActionEvent event) throws IOException, InterruptedException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/Driving_School/view/ViewStudentListForStudent.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage primaryStage = new Stage();
		primaryStage.setTitle("Al-Aqsa Driving School");
		primaryStage.setScene(scene);
		primaryStage.initModality(Modality.WINDOW_MODAL);
		primaryStage.setResizable(false);
//		primaryStage.setAlwaysOnTop(true);
		primaryStage.show();

		ViewStudentListForStudentController cont = loader.getController();
		cont.c_stu = stu;
		cont.sic = this;
		
	}

	
	public void showInformation(Student st) throws SQLException {
		
		if(st != null){
			student_id_label.setText(st.getStudent_id());
			first_name_label.setText(st.getFirst_name());
			last_name_label.setText(st.getLast_name());
			mobile_num_label.setText(st.getMobile_num());
			address_label.setText(st.getAddress());
			liscence_type_label.setText(st.getLicense());
			process_status_label.setText(st.getProcess_status());
			eye_test_date_label.setText(st.getEye_test_date());
			tests_label.setText(st.getTest_taken()+ " ");
			
			
			trainer_id_label.setText(st.getEmp_id());
			Connection conn = MySQLConnect.connectDb();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select first_name, last_name from employee where employee.emp_id = '"+ st.getEmp_id() + "';");
			rs.next();
			trainer_name_label.setText(rs.getString("first_name")+ " " + rs.getString("last_name"));
			
			
			conn = MySQLConnect.connectDb();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select COUNT(student_id) from vehicle_student where student_id = "+ st.getStudent_id() +" ;");
			rs.next();
			lessons_taken_label.setText(rs.getString(1));
			
			
			conn = MySQLConnect.connectDb();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select vs.lesson_date from vehicle_student vs where vs.student_id = " + st.getStudent_id() + " order by vs.lesson_id asc");
			String lastDate = "None";
			while(rs.next()) {
				lastDate = rs.getString("lesson_date");
			}
			
			last_lesson_label.setText(lastDate);
			
			
			conn = MySQLConnect.connectDb();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select p.payment_date from payments p where p.student_id = " + st.getStudent_id() + " order by p.payment_id asc");
			lastDate = "None";
			while(rs.next()) {
				lastDate = rs.getString("payment_date");
			}
			
			last_payment_label.setText(lastDate);
			
		}
		
		stu = st;
		
	}

	@FXML
	void updateStudentRecord(ActionEvent event) throws IOException, SQLException {
		
		if(stu == null) {
			JOptionPane.showInternalMessageDialog(null, "You must select a record first!");
			return;
		}
		
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/Driving_School/view/ModifyStudent.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage primaryStage = new Stage();
		primaryStage.setTitle("Al-Aqsa Driving School - Update Student Record");
		primaryStage.setScene(scene);
		primaryStage.initModality(Modality.WINDOW_MODAL);
		primaryStage.setResizable(false);
		primaryStage.show();

		ModifyStudentController msc = loader.getController();
		msc.sic = this;

		msc.showInformation(stu);
		
	}
}
