package com.Driving_School.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.Driving_School.model.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PaymentsController implements Initializable {

	public static double result;

	@FXML
	private TableColumn<Student, String> first_name;

	@FXML
	private TableColumn<Student, String> C_id;

	@FXML
	private TableColumn<Student, String> last_name;

	@FXML
	private Button search_btn;
	
	@FXML
    private Label note;

	@FXML
	private TableView<Student> table;

	@FXML
	private Label search_lb;

	@FXML
	private TextField search_name;

	@FXML
	private TextField search_lastname;

	@FXML
	private TextField amount;

	@FXML
	private Button bt;

	@FXML
	private TextField date;

	@FXML
	private TextField id;

	@FXML
	private TextField name;

	ObservableList<Student> listS;


	public void searchbox() throws Exception {

		try {
			listS = getDataStudentsSearch();
			table.setItems(listS);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public ObservableList<Student> getDataStudentsSearch() throws Exception {
			
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/drivingschool1", "root", "root");
		ObservableList<Student> list = FXCollections.observableArrayList();
		try {
			Statement stmt = conn.createStatement();
			String sql1;
			if (search_name.getText().matches("[0-9]+")) {
				sql1 = "Select * from student where student_id like '%" + search_name.getText() + "%' ;";
			} else {
				String[] split = search_name.getText().split(" ");
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
						rs.getString("process_status"), rs.getString("license"), rs.getInt("test_taken"),
						rs.getString("emp_id")));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public ObservableList<Student> getDataStudent() throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/drivingschool1", "root", "root");
		ObservableList<Student> list = FXCollections.observableArrayList();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from student");
			while (rs.next()) {
				list.add(new Student(rs.getString("student_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("mobile_num"), rs.getString("eye_test_date"), rs.getString("address"),
						rs.getString("process_status"), rs.getString("license"), rs.getInt("test_taken"),
						rs.getString("emp_id")));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	    @FXML
        void Calculate(ActionEvent event) throws Exception {
		
	    result =0;
		Student selected = table.getSelectionModel().getSelectedItem();

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/drivingschool1", "root", "root");
		Statement stmt = conn.createStatement();
		

		if(selected == null) {
			JOptionPane.showInternalMessageDialog(null, "Please Select a Student!");
		}
		
		else {

		String sql1 = "SELECT COUNT(lesson_id) as lessonNum FROM vehicle_student where student_id =  " + selected.getStudent_id()+ ";";
		String sql2 = "SELECT lesson_price FROM license_type where type_name = (select license from student where student_id ="+ selected.getStudent_id() + ");";
		String sql3 = "SELECT SUM(amount) as full_amount from payments where student_id = " + selected.getStudent_id() + "";
		String sql4 = "SELECT test_taken from student where student_id = " + selected.getStudent_id() + "";

		ResultSet rs1 = stmt.executeQuery(sql1);
		double lesson_num = 0;
		while (rs1.next()) {
			lesson_num = rs1.getInt("lessonNum");
		}
		rs1.close();
		
		ResultSet rs2 = stmt.executeQuery(sql2);
		double lesson_price = 0;
		while (rs2.next()) {
			lesson_price = rs2.getInt("lesson_price");

		}
		rs2.close();

		ResultSet rs3 = stmt.executeQuery(sql3);
		double payed_amount = 0;
		while (rs3.next()) {
			payed_amount = rs3.getInt("full_amount");

		}
		rs3.close();

		ResultSet rs4 = stmt.executeQuery(sql4);
		double test_num = 0;
		while (rs4.next()) {
			test_num = rs4.getInt("test_taken");

		}
		rs4.close();

		
		String sql5 = "SELECT test_price from prices where lesson_price = " + lesson_price + "";
		ResultSet rs5 = stmt.executeQuery(sql5);
		double testprice = 0;
		while (rs5.next()) {
			testprice = rs5.getInt("test_price");

		}
		rs5.close();

		
		if (test_num == 1) {
			result = (testprice + (lesson_num * lesson_price)) - payed_amount;
		}
		else if (test_num == 0) {
			result = (lesson_num * lesson_price) - payed_amount;
		}
		else {
			result = (testprice + ((testprice + 80) * (test_num - 1)) + (lesson_num * lesson_price)) - payed_amount;
		}

		if(result < 0) {
			result = (result*-1);
			search_lb.setText(String.valueOf(result));
			note.setText(String.valueOf("There is a surplus in your account of : "));

		}
		else {
		search_lb.setText(String.valueOf(result));
		note.setText(String.valueOf(""));
		}
		
		result=0;
		
		}

	}
	    
	  

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		C_id.setCellValueFactory(new PropertyValueFactory<Student, String>("student_id"));
		first_name.setCellValueFactory(new PropertyValueFactory<Student, String>("first_name"));
		last_name.setCellValueFactory(new PropertyValueFactory<Student, String>("last_name"));

		try {
			listS = getDataStudent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		table.setItems(listS);

	}

}
