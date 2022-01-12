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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewEmployeeListForEmployeeController implements Initializable {

	Employee c_emp;
	
	@FXML
	private TableView<Employee> ViewEmployees;

	@FXML
	private TableColumn<Employee, String> emp_id;

	@FXML
	private TableColumn<Employee, String> first_name;

	@FXML
	private TableColumn<Employee, String> last_name;

	@FXML
	private TextField search_box;

	ObservableList<Employee> listE;

	int index = -1;

	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		emp_id.setCellValueFactory(new PropertyValueFactory<Employee, String>("emp_id"));
		first_name.setCellValueFactory(new PropertyValueFactory<Employee, String>("first_name"));
		last_name.setCellValueFactory(new PropertyValueFactory<Employee, String>("last_name"));

		listE = getDataEmployees();

		ViewEmployees.setItems(listE);

	}
	
	@FXML
	void search_it(KeyEvent event) {
		conn = MySQLConnect.connectDb();

		try {
			listE = getDataEmployessSearch();
			ViewEmployees.setItems(listE);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public ObservableList<Employee> getDataEmployees() {
		conn = MySQLConnect.connectDb();
		ObservableList<Employee> list = FXCollections.observableArrayList();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from employee;");
			while (rs.next()) {
				list.add(new Employee(rs.getString("emp_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("mobile_num"), rs.getString("address")));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@FXML
	void cancelSelection(ActionEvent event) throws IOException, SQLException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/Driving_School/view/EmployeeInformation.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage primaryStage = new Stage();
		primaryStage.setTitle("Al-Aqsa Driving School");
		primaryStage.setScene(scene);
		primaryStage.initModality(Modality.WINDOW_MODAL);
		primaryStage.setResizable(false);
		primaryStage.show();
		
		final Node source = (Node) event.getSource();
		final Stage stage2 = (Stage) source.getScene().getWindow();
		stage2.close();
		
		EmployeeInformationController cont = loader.getController();
		cont.showInformation(c_emp);

	}

	
	public ObservableList<Employee> getDataEmployessSearch() {
		conn = MySQLConnect.connectDb();
		ObservableList<Employee> list = FXCollections.observableArrayList();
		try {
			Statement stmt = conn.createStatement();
			String sql1;
			if (search_box.getText().matches("[0-9]+")) {
				sql1 = "Select * from employee where emp_id like '%" + search_box.getText() + "%' ;";
			} else {
				String[] split = search_box.getText().split(" ");
				if (split.length == 1) {
					sql1 = "Select * from employee where first_name like '%" + split[0] + "%' ;";
				} else {
					sql1 = "Select * from employee where first_name like '%" + split[0] + "%' and last_name like '%"
							+ split[1] + "%' ;";
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
	
	
	@FXML
	void openEmployeeRecord(ActionEvent event) throws SQLException, IOException {
		Employee selected = ViewEmployees.getSelectionModel().getSelectedItem();
		if (selected == null) {
			JOptionPane.showInternalMessageDialog(null, "Please Select a row!");
		} else {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/Driving_School/view/EmployeeInformation.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("Al-Aqsa Driving School");
			primaryStage.setScene(scene);
			primaryStage.initModality(Modality.WINDOW_MODAL);
			primaryStage.setResizable(false);
			primaryStage.show();

			EmployeeInformationController cont = loader.getController();

			final Node source = (Node) event.getSource();
			final Stage stage2 = (Stage) source.getScene().getWindow();
			stage2.close();

			cont.showInformation(selected);
			c_emp = selected;

		}
		
	}


}
