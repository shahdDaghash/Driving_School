package com.Driving_School.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.Driving_School.model.*;

public class ViewStudentController implements Initializable{
	
	@FXML
    private TableView<Student> ViewStudents;

	@FXML
    private TableColumn<Student, String> student_id;
	
	@FXML
    private TableColumn<Student, String> first_name;

    @FXML
    private TableColumn<Student, String> last_name;

    @FXML
    private TableColumn<Student, String> mobile_num;

    @FXML
    private TableColumn<Student, Date> eye_test_date;
	
    @FXML
    private TableColumn<Student, String> address;
    
    @FXML
    private TableColumn<Student, String> process_state;

    
    @FXML
    private TextField txt_address;

    @FXML
    private TextField txt_eye_test_date;

    @FXML
    private TextField txt_first_name;

    @FXML
    private TextField txt_last_name;

    @FXML
    private TextField txt_mobile_num;

    @FXML
    private TextField txt_process_date;

    @FXML
    private TextField txt_student_id;

    @FXML
    private TextField txt_trainor;
    
    @FXML
    private TextField search_box;
    
    
    
    ObservableList <Student> listS;
    
    int index = -1;
    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	student_id.setCellValueFactory(new PropertyValueFactory<Student, String>("student_id"));
    	first_name.setCellValueFactory(new PropertyValueFactory<Student, String>("first_name"));
    	last_name.setCellValueFactory(new PropertyValueFactory<Student, String>("last_name"));
    	mobile_num.setCellValueFactory(new PropertyValueFactory<Student, String>("mobile_num"));
    	eye_test_date.setCellValueFactory(new PropertyValueFactory<Student, Date>("eye_test_date"));
    	address.setCellValueFactory(new PropertyValueFactory<Student, String>("address"));
    	process_state.setCellValueFactory(new PropertyValueFactory<Student, String>("process_state"));
    	
    	listS = getDataStudents();
    	ViewStudents.setItems(listS);
    }
    
    
    //display all employees
    public ObservableList<Student> getDataStudents() { 
    	conn = MySQLConnect.connectDb();
		ObservableList<Student> list = FXCollections.observableArrayList();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from student");
			while (rs.next()) {
				list.add(new Student(rs.getString("student_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("mobile_num"), rs.getDate("eye_test_date"), rs.getString("address"), rs.getString("process_state"), rs.getString("emp_id")));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
    
    
  //Load the selected row from table to the form to edit
    public void LoadRow() {
    	Student selected = ViewStudents.getSelectionModel().getSelectedItem();
    	txt_student_id.setText(selected.getEmp_id());
    	txt_first_name.setText(selected.getFirst_name());
    	txt_last_name.setText(selected.getLast_name());
    	txt_mobile_num.setText(selected.getMobile_num());
    	txt_eye_test_date.setText(selected.getEye_test_date().toString());
    	txt_address.setText(selected.getAddress());
    }
    
    
    
    

}
