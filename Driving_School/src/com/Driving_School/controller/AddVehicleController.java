package com.Driving_School.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.Driving_School.model.Vehicle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddVehicleController {

    @FXML
    private TextField insuranceEndDate;

    @FXML
    private TextField vehicleNum;

    @FXML
    void addVehicle(ActionEvent event) {
    	String insuranceDate = insuranceEndDate.getText().toString();
		String vehicle_num = vehicleNum.getText().toString();
		
		 
		String sql = "insert into vehicle (vehicle_num,insurance_end_date)values(?,?)";
		Connection conn = com.Driving_School.model.MySQLConnect.getConn();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, vehicle_num);
			pst.setString(2, insuranceDate);
			pst.execute();
			JOptionPane.showMessageDialog(null, "Vehicle Add success");
			clearFileds();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"Failed to add Vehicle, already exist");
		}
    }

    @FXML
    void clearFileds() {
    	insuranceEndDate.clear();
    	vehicleNum.clear();
    }

}
