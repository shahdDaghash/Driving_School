

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
import com.Driving_School.model.Vehicle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class NewViewVehicleListController implements Initializable{

	Vehicle veh;
	
	AddLessonController msc;
	
	
    @FXML
    private TableView<Vehicle> ViewVehicles;

    @FXML
    private TableColumn<Vehicle, String> vehicle_num;

    @FXML
    private TableColumn<Vehicle, String> insurance_end_date;

    
    @FXML
    private TextField search_box;
    
    ObservableList<Vehicle> listE;

	 
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;


    @FXML
    void cancelVehicleSelection(ActionEvent event) throws SQLException, IOException {
		final Node source = (Node) event.getSource();
		final Stage stage2 = (Stage) source.getScene().getWindow();
		stage2.close();

		msc.showVehicleInformation(veh);
		
    }

    @FXML
    void chooseVehicle(ActionEvent event) throws SQLException, IOException {
    	
    	veh.setVehicle_num(ViewVehicles.getSelectionModel().getSelectedItem().getVehicle_num());
		final Node source = (Node) event.getSource();
		final Stage stage2 = (Stage) source.getScene().getWindow();
		stage2.close();

		msc.showVehicleInformation(veh);
    	
    }

    @FXML
    public void search_it() {
    	conn = MySQLConnect.connectDb();
    	try {
    		listE = getDataVehicleSearch();
        	ViewVehicles.setItems(listE);
    		
    	} catch(Exception e) {
    		JOptionPane.showMessageDialog(null, e);
    	}
    }
    
//    @Override
//	public void initialize(URL url, ResourceBundle rb) {
//    	vehicle_num.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("vehicle_num"));
//    	
//		insurance_end_date.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("insurance_end_date"));
//		listE = getDataVehicles();
//		ViewVehicles.setItems(listE);
//	}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	vehicle_num.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("vehicle_num"));
    	insurance_end_date.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("insurance_end_date"));
    	
    	listE = getDataVehicles();
    	
    	ViewVehicles.setItems(listE);
    }
    
    
    public ObservableList<Vehicle> getDataVehicles() {
		conn = MySQLConnect.connectDb();
		ObservableList<Vehicle> list = FXCollections.observableArrayList();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select vehicle_num, insurance_end_date from vehicle");
			while (rs.next()) {
				list.add(new Vehicle(rs.getString("vehicle_num"), rs.getString("insurance_end_date")));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
    
    
    
    public ObservableList<Vehicle> getDataVehicleSearch() { 
    	conn = MySQLConnect.connectDb();
		ObservableList<Vehicle> list = FXCollections.observableArrayList();
		try {
			Statement stmt = conn.createStatement();
			String sql1 = null;
			if(search_box.getText().matches("[0-9]+")) {
				sql1 = "Select * from vehicle where vehicle_num like '%" + search_box.getText() + "%' ;";
			}
			
			ResultSet rs = stmt.executeQuery(sql1);
			while (rs.next()) {
				list.add(new Vehicle(rs.getString("vehicle_num"), rs.getString("insurance_end_date")));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

    
}
