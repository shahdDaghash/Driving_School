package com.Driving_School.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.Driving_School.model.*;

public class ViewEmployeeController implements Initializable{
	@FXML
    private TableView<Employee> ViewEmployees;

    @FXML
    private TableColumn<Employee, String> emp_id;

    @FXML
    private TableColumn<Employee, String> first_name;

    @FXML
    private TableColumn<Employee, String> last_name;

    @FXML
    private TableColumn<Employee, String> mobile_num;
    
    @FXML
    private TableColumn<Employee, String> address;

    @FXML
    private TextField txt_address;

    @FXML
    private TextField txt_emp_id;

    @FXML
    private TextField txt_first_name;

    @FXML
    private TextField txt_last_name;

    @FXML
    private TextField txt_mobile_num;
    
    @FXML
    private TextField search_box;
    
    
    
    ObservableList <Employee> listE;
    
    int index = -1;
    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	emp_id.setCellValueFactory(new PropertyValueFactory<Employee, String>("emp_id"));
    	first_name.setCellValueFactory(new PropertyValueFactory<Employee, String>("first_name"));
    	last_name.setCellValueFactory(new PropertyValueFactory<Employee, String>("last_name"));
    	mobile_num.setCellValueFactory(new PropertyValueFactory<Employee, String>("mobile_num"));
    	address.setCellValueFactory(new PropertyValueFactory<Employee, String>("address"));
    	
    	listE = getDataEmployess();
    	ViewEmployees.setItems(listE);
    	
    	
    }
    
    
    
    //Load the selected row from table to the form to edit
    public void LoadRow() {
    	Employee selected = ViewEmployees.getSelectionModel().getSelectedItem();
    	txt_emp_id.setText(selected.getEmp_id());
    	txt_first_name.setText(selected.getFirst_name());
    	txt_last_name.setText(selected.getLast_name());
    	txt_mobile_num.setText(selected.getMobile_num());
    	txt_address.setText(selected.getAddress());
    }
    
    
    //apply edited data to table
    public void UpdateRow() {
    	conn = MySQLConnect.connectDb();
    	Employee selected = ViewEmployees.getSelectionModel().getSelectedItem();
    	
    	String sql = "UPDATE employee SET first_name = ? , last_name = ? , mobile_num = ? , address = ? where emp_id = ? ;";

    	try {
    		if(selected.getEmp_id().equals(txt_emp_id.getText())) {
    			if(!selected.getAddress().equals(txt_address.getText()) || !selected.getFirst_name().equals(txt_first_name.getText()) || !selected.getLast_name().equals(txt_last_name.getText()) || !selected.getMobile_num().equals(txt_mobile_num.getText())) {
    				pst = conn.prepareStatement(sql);
            		pst.setString(1, txt_first_name.getText());
            		pst.setString(2, txt_last_name.getText());
            		pst.setString(3, txt_mobile_num.getText());
            		pst.setString(4, txt_address.getText());
            		pst.setString(5, txt_emp_id.getText());
            		pst.execute();
            		
            		JOptionPane.showMessageDialog(null, "Employee updated Successfully");
            		
            		listE = getDataEmployess();
                	ViewEmployees.setItems(listE);
    			}
    			else {
    				JOptionPane.showMessageDialog(null, "There are no changes to make");
    			}
    		}
    		else {
    			JOptionPane.showMessageDialog(null, "No criteria to changing the id. Contact Adminstrator.");
    		}
    	} catch (Exception e) {
    		JOptionPane.showMessageDialog(null, e);
    	}
    }
    
    
    
    //search the table
    public void search_it() {
    	conn = MySQLConnect.connectDb();
    	
    	try {
    		listE = getDataEmployessSearch();
        	ViewEmployees.setItems(listE);
    		
    	} catch(Exception e) {
    		JOptionPane.showMessageDialog(null, e);
    	}
    }
    
    
    //display all employees
    public ObservableList<Employee> getDataEmployess() { 
    	conn = MySQLConnect.connectDb();
		ObservableList<Employee> list = FXCollections.observableArrayList();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from employee");
			while (rs.next()) {
//				System.out.println(rs.getString("emp_id"));
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
			String sql1 = "Select * from employee where emp_id like '%" + search_box.getText() + "%' ;";
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














