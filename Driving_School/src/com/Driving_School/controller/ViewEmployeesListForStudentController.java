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

import com.Driving_School.model.Employee;
import com.Driving_School.model.MySQLConnect;
import com.Driving_School.model.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ViewEmployeesListForStudentController implements Initializable{

	Student c_stu;
	ModifyStudentController msc;
	
    @FXML
    private TableView<Employee> ViewEmployees;

    @FXML
    private TableColumn<Employee, String> emp_id;

    @FXML
    private TableColumn<Employee, String> first_name;

    @FXML
    private TableColumn<Employee, String> last_name;
    
    @FXML
	private Label errorMessage;
    

    @FXML
    private TextField search_box;
    
    ObservableList<Employee> listE;

	int index = -1;

	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;


    @FXML
    void cancelTrainerSelection(ActionEvent event) throws SQLException, IOException {
		final Node source = (Node) event.getSource();
		final Stage stage2 = (Stage) source.getScene().getWindow();
		stage2.close();

		msc.showInformation(c_stu);
		
		
    }

    @FXML
    void changeTrainer(ActionEvent event) throws SQLException, IOException {
    	
    	Employee selected = ViewEmployees.getSelectionModel().getSelectedItem();
		if (selected == null) {
			errorMessage.setText("Please Select a Row!");
			return;
		}
    	
    	conn = MySQLConnect.connectDb();
    	
    	String sql = "UPDATE student SET emp_id = ? where student_id = ? ;";
    	
    	if(!c_stu.getEmp_id().equals(ViewEmployees.getSelectionModel().getSelectedItem().getEmp_id())) {
    		pst = conn.prepareStatement(sql);
    		pst.setString(1, ViewEmployees.getSelectionModel().getSelectedItem().getEmp_id());
    		pst.setString(2, c_stu.getStudent_id());
    		pst.execute();
    		c_stu.setEmp_id(ViewEmployees.getSelectionModel().getSelectedItem().getEmp_id());
    	}
    	
		final Node source = (Node) event.getSource();
		final Stage stage2 = (Stage) source.getScene().getWindow();
		stage2.close();

		msc.showInformation(c_stu);
    	
    }

    @FXML
    public void search_it() {
    	conn = MySQLConnect.connectDb();
    	try {
    		listE = getDataEmployessSearch();
        	ViewEmployees.setItems(listE);
    		
    	} catch(Exception e) {
    		JOptionPane.showMessageDialog(null, e);
    	}
    }
    
    @Override
	public void initialize(URL url, ResourceBundle rb) {
		emp_id.setCellValueFactory(new PropertyValueFactory<Employee, String>("emp_id"));
		first_name.setCellValueFactory(new PropertyValueFactory<Employee, String>("first_name"));
		last_name.setCellValueFactory(new PropertyValueFactory<Employee, String>("last_name"));

		listE = getDataEmployees();

		ViewEmployees.setItems(listE);

	}
    

    
    public ObservableList<Employee> getDataEmployees() {
		conn = MySQLConnect.connectDb();
		ObservableList<Employee> list = FXCollections.observableArrayList();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from trainer t, employee e where t.trainer_id = e.emp_id;");
			while (rs.next()) {
				list.add(new Employee(rs.getString("emp_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("mobile_num"), rs.getString("address")));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
    
    
    //display searched employees
    public ObservableList<Employee> getDataEmployessSearch() { 
    	conn = MySQLConnect.connectDb();
		ObservableList<Employee> list = FXCollections.observableArrayList();
		try {
			Statement stmt = conn.createStatement();
			String sql1;
			if(search_box.getText().matches("[0-9]+")) {
				sql1 = "Select * from employee where emp_id like '%" + search_box.getText() + "%' ;";
			}
			else {
				String[] split = search_box.getText().split(" ");
				if(split.length == 1) {
					sql1 = "Select * from employee where first_name like '%" + split[0] + "%' ;";
				}
				else {
					sql1 = "Select * from employee where first_name like '%" + split[0] + "%' and last_name like '%" + split[1] + "%' ;";
				}
			}
			
			ResultSet rs = stmt.executeQuery(sql1);
			while (rs.next()) {
				list.add(new Employee(rs.getString("emp_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("mobile_num"), rs.getString("address")));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

    
}
