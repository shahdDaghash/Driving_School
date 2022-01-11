package com.Driving_School.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.Driving_School.model.*;
import com.mysql.cj.xdevapi.AddResult;

public class ViewStudentListController implements Initializable {
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

//    //Load the selected row from table to the form to edit
//    public void LoadRow() {
//    	Student selected = ViewStudents.getSelectionModel().getSelectedItem();
//    	txt_student_id.setText(selected.getStudent_id());
//    	txt_first_name.setText(selected.getFirst_name());
//    	txt_last_name.setText(selected.getLast_name());
//    	txt_mobile_num.setText(selected.getMobile_num());
//    	txt_eye_test_date.setText(selected.getEye_test_date());
//    	txt_address.setText(selected.getAddress());
//    	txt_process_state.setText(selected.getProcess_status());
//    }

	// apply edited data to table

//    public void UpdateRow() {
//    	conn = MySQLConnect.connectDb();
//    	Student selected = ViewStudents.getSelectionModel().getSelectedItem();
//    	
//    	String sql = "UPDATE Student SET first_name = ? , last_name = ? , mobile_num = ? , eye_test_date = ? , address = ? , process_status = ?  where student_id = ? ;";
//
//    	try {
//    		if(selected.getStudent_id().equals(txt_student_id.getText())) {
//    			if(!selected.getAddress().equals(txt_address.getText()) || !selected.getFirst_name().equals(txt_first_name.getText()) || !selected.getLast_name().equals(txt_last_name.getText()) || !selected.getMobile_num().equals(txt_mobile_num.getText()) || !selected.getEye_test_date().equals(eye_test_date.getText()) || !selected.getProcess_status().equals(txt_process_state.getText()) ) {
//    				pst = conn.prepareStatement(sql);
//            		pst.setString(1, txt_first_name.getText());
//            		pst.setString(2, txt_last_name.getText());
//            		pst.setString(3, txt_mobile_num.getText());
//            		pst.setString(4, eye_test_date.getText());
//            		pst.setString(5, txt_address.getText());
//            		pst.setString(6, txt_process_state.getText());
//            		pst.execute();
//            		
//            		JOptionPane.showMessageDialog(null, "Student updated Successfully");
//            		
//            		listS = getDataStudent();
//                	ViewStudents.setItems(listS);
//    			}
//    			else {
//    				JOptionPane.showMessageDialog(null, "There are no changes to make");
//    			}
//    		}
//    		else {
//    			JOptionPane.showMessageDialog(null, "No criteria to changing the id. Contact Adminstrator.");
//    		}
//    	} catch (Exception e) {
//    		JOptionPane.showMessageDialog(null, e);
//    	}
//    }

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
						rs.getString("process_status"), rs.getString("emp_id")));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
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
						rs.getString("process_status"), rs.getString("emp_id")));
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
			errorMessage.setText("Please Select a row!");
		} else {
			errorMessage.setText("");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/Driving_School/view/StudentInformation.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("Al-Aqsa Driving School");
			primaryStage.setScene(scene);
			primaryStage.initModality(Modality.WINDOW_MODAL);
			primaryStage.setResizable(false);
			primaryStage.show();

			StudentInformationController cont = loader.getController();

			final Node source = (Node) event.getSource();
			final Stage stage2 = (Stage) source.getScene().getWindow();
			stage2.close();

			cont.showInformation(selected);

		}

		// close page when clicking add
	}

}
