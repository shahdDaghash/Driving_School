package com.Driving_School.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.Driving_School.model.MySQLConnect;
import com.Driving_School.model.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * Adding new student 
 * 
 * @author Tala Alsweiti - 1191068
 *
 */
public class AddStudentController implements Initializable {

	Student stu = new Student();

	@FXML
	private TextField address;

	@FXML
	private RadioButton dropped_off;

	@FXML
	private TextField emp_name;

	@FXML
	private String license_type;

	@FXML
	private TextField eye_test_date;

	@FXML
	private TextField first_name;

	@FXML
	private RadioButton graduted;

	@FXML
	private RadioButton in_progress;

	@FXML
	private TextField last_name;

	@FXML
	private TextField mobile_num;

	@FXML
	private ToggleGroup process;

	@FXML
	private TextField student_id;
	@FXML
	private ChoiceBox<String> LicenseType;

	ObservableList<String> types = FXCollections.observableArrayList("Private", "Taxi", "Trella", "Light Truck",
			"Heavy Truck", "Bus");

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		LicenseType.setItems(types);
	}

	/**
	 * Used to choose a trainer by showing the trainers table
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void ShowTrainers(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/Driving_School/view/ViewTrainersList.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage primaryStage = new Stage();
		primaryStage.setTitle("Al-Aqsa Driving School");
		primaryStage.setScene(scene);
		primaryStage.initModality(Modality.WINDOW_MODAL);
		primaryStage.setResizable(false);
		primaryStage.show();
		ViewTrainersListController cont = loader.getController();
		cont.c_stu = stu;
		cont.msc = this;
	}

	@FXML
	void addStudent(ActionEvent event) {
		String studentId = student_id.getText().toString();
		String firstName = first_name.getText().toString();
		String lastName = last_name.getText().toString();
		String mobile = mobile_num.getText().toString();
		String studentAddress = address.getText().toString();
		String eyeTestDate = eye_test_date.getText().toString();
		String studentProgress = "";
		license_type = LicenseType.getValue();

		if (dropped_off.isSelected()) {
			studentProgress = "Dropped Off";
		} else if (graduted.isSelected()) {
			studentProgress = "Graduated";
		} else {
			studentProgress = "In Progress";
		}

		stu.setStudent_id(studentId);
		stu.setFirst_name(firstName);
		stu.setLast_name(lastName);
		stu.setMobile_num(mobile);
		stu.setAddress(studentAddress);
		stu.setEye_test_date(eyeTestDate);
		stu.setProcess_status(studentProgress);
		stu.setLicense(license_type);
		/*
		 * Using this query, a new student will be added
		 */
		String sql = "insert into student (student_id,first_name,last_name,mobile_num,eye_test_date,address,process_status,license,emp_id)values(?,?,?,?,?,?,?,?,? )";
		Connection conn = com.Driving_School.model.MySQLConnect.getConn();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);

			pst.setString(1, studentId);
			pst.setString(2, firstName);
			pst.setString(3, lastName);
			pst.setString(4, mobile);
			pst.setString(5, eyeTestDate);
			pst.setString(6, studentAddress);
			pst.setString(7, studentProgress);
			pst.setString(8, license_type);
			pst.setString(9, stu.getEmp_id());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Student Add success");
			clear();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Failed to add Student");
		}
	}

	@FXML
	void clear() {
		address.clear();
		last_name.clear();
		mobile_num.clear();
		student_id.clear();
		eye_test_date.clear();
		first_name.clear();
		emp_name.clear();
	}
	
	/**
	 * Show trainers to choose from them 
	 * @param st
	 * @throws SQLException
	 */
	public void showInformation(Student st) throws SQLException {
		stu.setEmp_id(st.getEmp_id());
		Connection conn = MySQLConnect.connectDb();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(
				"select first_name, last_name from employee where employee.emp_id = '" + st.getEmp_id() + "';");
		rs.next();
		emp_name.setText(rs.getString("first_name") + " " + rs.getString("last_name"));

	}

}
