package com.Driving_School.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.Driving_School.model.*;

public class ViewStudentsForLessonsController implements Initializable {
	
	Student c_stu;
	AddLessonController sic;
	
	@FXML
	private TableView<Student> ViewStudents;

	@FXML
	private TableColumn<Student, String> student_id;

	@FXML
	private TableColumn<Student, String> first_name;

	@FXML
	private TableColumn<Student, String> last_name;

	@FXML
	private TextField search_box;

	@FXML
	private Label errorMessage;

	ObservableList<Student> listS;

	int index = -1;

	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		student_id.setCellValueFactory(new PropertyValueFactory<Student, String>("student_id"));
		first_name.setCellValueFactory(new PropertyValueFactory<Student, String>("first_name"));
		last_name.setCellValueFactory(new PropertyValueFactory<Student, String>("last_name"));

		listS = getDataStudent();

		ViewStudents.setItems(listS);

	}

	// search the table
	public void search_it() {
		conn = MySQLConnect.connectDb();

		try {
			listS = getDataStudentsSearch();
			ViewStudents.setItems(listS);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	// display all students
	public ObservableList<Student> getDataStudent() {
		conn = MySQLConnect.connectDb();
		ObservableList<Student> list = FXCollections.observableArrayList();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from student");
			while (rs.next()) {
				list.add(new Student(rs.getString("student_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("mobile_num"), rs.getString("eye_test_date"), rs.getString("address"),
						rs.getString("process_status"),rs.getString("license"), rs.getInt("test_taken"), rs.getString("emp_id")));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
    @FXML
    void cancelSelection(ActionEvent event) throws SQLException, IOException {
		final Node source = (Node) event.getSource();
		final Stage stage2 = (Stage) source.getScene().getWindow();
		stage2.close();

		sic.showInformation(c_stu);
		
		
    }

	// display searched employees
	public ObservableList<Student> getDataStudentsSearch() {
		conn = MySQLConnect.connectDb();
		ObservableList<Student> list = FXCollections.observableArrayList();
		try {
			Statement stmt = conn.createStatement();
			String sql1;
			if (search_box.getText().matches("[0-9]+")) {
				sql1 = "Select * from student where student_id like '%" + search_box.getText() + "%' ;";
			} else {
				String[] split = search_box.getText().split(" ");
				if (split.length == 1) {
					sql1 = "Select * from student where first_name like '%" + split[0] + "%' ;";
				} else {
					sql1 = "Select * from student where first_name like '%" + split[0] + "%' and last_name like '%"
							+ split[1] + "%' ;";
				}
			}

			ResultSet rs = stmt.executeQuery(sql1);
			while (rs.next()) {
				list.add(new Student(rs.getString("student_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("mobile_num"), rs.getString("eye_test_date"), rs.getString("address"),
						rs.getString("process_status"),rs.getString("license"), rs.getInt("test_taken"), rs.getString("emp_id")));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@FXML
	void openStudentRecord(ActionEvent event) throws IOException, SQLException {
		Student selected = ViewStudents.getSelectionModel().getSelectedItem();
		if (selected == null) {
			JOptionPane.showInternalMessageDialog(null, "Please Select a row!");
		} else {
			final Node source = (Node) event.getSource();
			final Stage stage2 = (Stage) source.getScene().getWindow();
			stage2.close();

			sic.showInformation(selected);
			c_stu = selected;

		}
	}

}

