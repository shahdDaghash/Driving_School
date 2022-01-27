package com.Driving_School.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.Driving_School.model.Student;
import com.Driving_School.model.Vehicle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * Adding new lesson
 *  
 * @author Tala Alsweiti - 1191068
 *
 */
public class AddLessonController {
	Student stu = new Student();
	Vehicle ve = new Vehicle();
	
	@FXML
	private TextField lessonDate;

	@FXML
	private TextField student;

	@FXML
	private TextField vehicle;

	@FXML
	void addLesson(ActionEvent event) {
		String lesson_date = lessonDate.getText();
		/*
		 * Using this query, a new lesson will be added for the chosen student
		 */
		String sql = "insert into vehicle_student (student_id,lesson_date,vehicle_num)values(?,?,?)";
		Connection conn = com.Driving_School.model.MySQLConnect.getConn();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, stu.getStudent_id());
			pst.setString(2, lesson_date);
			pst.setString(3, ve.getVehicle_num());
			pst.execute();

			JOptionPane.showMessageDialog(null, "Lesson Add success");
			clearFileds();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Failed to add Lesson");
		}
	}

	/**
	 * This function used to choose a student by showing the students table
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void chooseStudent(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("/com/Driving_School/view/ViewStudentsForLessons.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage primaryStage = new Stage();
		primaryStage.setTitle("Al-Aqsa Driving School");
		primaryStage.setScene(scene);
		primaryStage.initModality(Modality.WINDOW_MODAL);
		primaryStage.setResizable(false);
		primaryStage.show();
		ViewStudentsForLessonsController cont = loader.getController();
		cont.c_stu = stu;
		cont.sic = this;
	}
	/**
	 * Used to choose a vehicle by showing the vehicles table
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void chooseVehicle(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/Driving_School/view/ViewVehiclesList.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage primaryStage = new Stage();
		primaryStage.setTitle("Al-Aqsa Driving School");
		primaryStage.setScene(scene);
		primaryStage.initModality(Modality.WINDOW_MODAL);
		primaryStage.setResizable(false);
		primaryStage.show();
		NewViewVehicleListController cont = loader.getController();
		cont.veh = ve;
		cont.msc = this;
	}

	@FXML
	void clearFileds() {
		lessonDate.clear();
		student.clear();
		vehicle.clear();
	}
	
	public void showInformation(Student st) {
		stu.setStudent_id(st.getStudent_id());
		student.setText(st.getFirst_name() + " " + st.getLast_name());
	}

	public void showVehicleInformation(Vehicle veh) {
		ve.setVehicle_num(veh.getVehicle_num());
		vehicle.setText(veh.getVehicle_num());
	}
}
