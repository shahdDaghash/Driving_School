package com.Driving_School.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import com.Driving_School.model.MySQLConnect;
import com.Driving_School.model.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StudentInformationController {

	Stage stg;

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
	}

	@FXML
	void openStudentTable(ActionEvent event) throws IOException, InterruptedException {
		Parent root = FXMLLoader.load(getClass().getResource("/com/Driving_School/view/ViewStudentList.fxml"));
		Scene scene = new Scene(root);
		Stage primaryStage = new Stage();
		primaryStage.setTitle("Select Student Record");
		primaryStage.setScene(scene);
		primaryStage.initModality(Modality.WINDOW_MODAL);
		primaryStage.setResizable(false);
		primaryStage.show();

		final Node source = (Node) event.getSource();
		final Stage stage2 = (Stage) source.getScene().getWindow();
		stage2.close();
	}

	public void showInformation(Student st) throws SQLException {

		student_id_label.setText(st.getStudent_id());
		first_name_label.setText(st.getFirst_name());
		last_name_label.setText(st.getLast_name());
		mobile_num_label.setText(st.getMobile_num());
		address_label.setText(st.getAddress());
		//!return to license type
		process_status_label.setText(st.getProcess_status());
		eye_test_date_label.setText(st.getEye_test_date());
		
		
		trainer_id_label.setText(st.getEmp_id());
		Connection conn = MySQLConnect.connectDb();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select first_name, last_name from employee where employee.emp_id = '"+ st.getEmp_id() + "';");
		rs.next();
		trainer_name_label.setText(rs.getString("first_name")+ " " + rs.getString("last_name"));
		
		
		ResultSet rs2 = stmt.executeQuery("select p.payment_date from payments p where p.student_id = '"+ st.getStudent_id() + "';");
		while(rs2.next()) {
			System.out.println(rs2.getString("payment_date"));
		}
		//!return to payments
		
		
		//!return to last lesson date
		
	}

	@FXML
	void updateStudentRecord(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/com/Driving_School/view/ModifyStudent.fxml"));
		Scene scene = new Scene(root);
		Stage primaryStage = new Stage();
		primaryStage.setTitle("Update Student Record");
		primaryStage.setScene(scene);
		primaryStage.initModality(Modality.WINDOW_MODAL);
		primaryStage.setResizable(false);
		primaryStage.show();

//		final Node source = (Node) event.getSource();
//		final Stage stage2 = (Stage) source.getScene().getWindow();
//		stage2.close();
	}

	@FXML
	void viewTrainerInfo(MouseEvent event) {

	}

}
