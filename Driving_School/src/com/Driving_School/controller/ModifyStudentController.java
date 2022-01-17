package com.Driving_School.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ModifyStudentController {

	Student stu;
	StudentInformationController sic;

	@FXML
	private TextField address_txt;

	@FXML
	private TextField eye_test_date_txt;

	@FXML
	private TextField first_name_txt;

	@FXML
	private TextField last_name_txt;

	@FXML
	private TextField lisence_type_txt;

	@FXML
	private TextField mobile_num_txt;

	@FXML
	private ChoiceBox<String> process_state_txt;

	@FXML
	private TextField student_id_txt;

	@FXML
	private TextField tests_txt;

	@FXML
	private Label trainer_id_label;

	@FXML
	private Label trainer_name_label;

	ObservableList<Student> listS;

	int index = -1;

	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	
	ObservableList<String> types = FXCollections.observableArrayList("In Progress","Graduated","Dropped off");

	@FXML
	void changeTrainer(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/Driving_School/view/ViewEmployeesListForStudent.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage primaryStage = new Stage();
		primaryStage.setTitle("Al-Aqsa Driving School");
		primaryStage.setScene(scene);
		primaryStage.initModality(Modality.WINDOW_MODAL);
		primaryStage.setResizable(false);
		primaryStage.show();

		ViewEmployeesListForStudentController cont = loader.getController();

		cont.c_stu = stu;
		cont.msc = this;
	}

	@FXML
	void performUpdate(ActionEvent event) throws SQLException, IOException {
		conn = MySQLConnect.connectDb();

		String sql = "UPDATE Student SET first_name = ? , last_name = ? , mobile_num = ? , eye_test_date = ? , address = ? , process_status = ? , license = ? , test_taken = ? where student_id = ? ;";

		Student selected = stu;
		

		if (selected.getStudent_id().equals(student_id_txt.getText())) {
			pst = conn.prepareStatement(sql);
			pst.setString(1, first_name_txt.getText());
			pst.setString(2, last_name_txt.getText());
			pst.setString(3, mobile_num_txt.getText());
			pst.setString(4, eye_test_date_txt.getText());
			pst.setString(5, address_txt.getText());
			pst.setString(6, process_state_txt.getValue());
			pst.setString(7, lisence_type_txt.getText());
			pst.setString(8, tests_txt.getText());
			pst.setString(9, student_id_txt.getText());
			pst.execute();
			
			selected.setAddress(address_txt.getText());
			selected.setEye_test_date(eye_test_date_txt.getText());
			selected.setFirst_name(first_name_txt.getText());
			selected.setLast_name(last_name_txt.getText());
			selected.setLicense(lisence_type_txt.getText());
			selected.setMobile_num(mobile_num_txt.getText());
			selected.setProcess_status(process_state_txt.getValue());
			selected.setTest_taken(Integer.parseInt(tests_txt.getText().strip()));

			final Node source = (Node) event.getSource();
			final Stage stage2 = (Stage) source.getScene().getWindow();
			stage2.close();

			sic.showInformation(selected);
			
		} else {
			JOptionPane.showMessageDialog(null, "No criteria to changing the id. Contact Adminstrator.");
		}
	}


	@FXML
	void cancelModify(ActionEvent event) throws SQLException, IOException {
		final Node source = (Node) event.getSource();
		final Stage stage2 = (Stage) source.getScene().getWindow();
		stage2.close();

		sic.showInformation(stu);
	}
	
	

	public void showInformation(Student st) throws SQLException {

		stu = st;

		student_id_txt.setText(st.getStudent_id());
		first_name_txt.setText(st.getFirst_name());
		last_name_txt.setText(st.getLast_name());
		mobile_num_txt.setText(st.getMobile_num());
		address_txt.setText(st.getAddress());
		process_state_txt.setItems(types);
		process_state_txt.getSelectionModel().select(st.getProcess_status());
		eye_test_date_txt.setText(st.getEye_test_date());
		lisence_type_txt.setText(st.getLicense());
		tests_txt.setText(st.getTest_taken() + " ");

		trainer_id_label.setText(st.getEmp_id());
		Connection conn = MySQLConnect.connectDb();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(
				"select first_name, last_name from employee where employee.emp_id = '" + st.getEmp_id() + "';");
		rs.next();
		trainer_name_label.setText(rs.getString("first_name") + " " + rs.getString("last_name"));

	}

}
