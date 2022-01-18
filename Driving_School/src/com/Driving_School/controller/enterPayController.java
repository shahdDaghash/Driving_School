package com.Driving_School.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.Driving_School.model.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class enterPayController implements Initializable {
	
		public static double caculatedNum;
		
		ObservableList<Student> listS;
	
		@FXML
	    private TableColumn<Student, String> C_id;

	    @FXML
	    private TableColumn<Student, String> first_name;

	    @FXML
	    private TableColumn<Student, String> last_name;

	    @FXML
	    private TextField search;

	    @FXML
	    private Button search_btn;

	    @FXML
	    private TableView<Student> table2;
		
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

	    @FXML
		void save(ActionEvent event) throws Exception {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/drivingschool1", "root", "root");
			String studentID = id.getText();
			String moneyAmount = amount.getText();
			String PayDate = date.getText();

			
			if (Double.parseDouble(moneyAmount) > caculatedNum) {
				JOptionPane.showMessageDialog(null, "The amount paid is more than the Requested amount, please re-check");
				clearFileds();
			}

			else {

				String insert = "insert into payments(student_id, payment_date, amount) values(?,?,?);  ";
				PreparedStatement pst;
				try {
					pst = con.prepareStatement(insert);
					pst.setString(1, studentID);
					pst.setString(2, PayDate);
					pst.setString(3, moneyAmount);
					pst.execute();

					JOptionPane.showMessageDialog(null, "Payment Added success");
					clearFileds();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Failed submit Payment, check data");
				}
			}
		}

	  
	    
	    
	    public void clearFileds() {
			id.clear();
			name.clear();
			amount.clear();
			date.clear();
		}

	    public void searchbox2() throws Exception {

			try {
				listS = getDataStudentsSearch();
				table2.setItems(listS);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}
	    
	    
	    @SuppressWarnings("unused")
	    @FXML
	    void search(MouseEvent event) throws Exception {
	    	caculatedNum = 0; 
	    	
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/drivingschool1", "root", "root");
			Statement stmt = con.createStatement();

	    	Student selected2 = table2.getSelectionModel().getSelectedItem();
	    	
	    	name.setText(String.valueOf(""+selected2.getFirst_name()+" "+selected2.getLast_name())+ "");
	    	id.setText(String.valueOf(selected2.getStudent_id()));
	    	
	    	if(selected2 == null) {
				JOptionPane.showInternalMessageDialog(null, "Please Select a Student!");
			}
			
			else {

			String sql1 = "SELECT COUNT(lesson_id) as lessonNum FROM vehicle_student where student_id =  " + selected2.getStudent_id()+ ";";
			String sql2 = "SELECT lesson_price FROM license_type where type_name = (select license from student where student_id ="+ selected2.getStudent_id() + ");";
			String sql3 = "SELECT SUM(amount) as full_amount from payments where student_id = " + selected2.getStudent_id() + "";
			String sql4 = "SELECT test_taken from student where student_id = " + selected2.getStudent_id() + "";

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
				caculatedNum = (testprice + (lesson_num * lesson_price)) - payed_amount;
			}
			else if (test_num == 0) {
				caculatedNum = (lesson_num * lesson_price) - payed_amount;
			}
			else {
				caculatedNum = (testprice + ((testprice + 80) * (test_num - 1)) + (lesson_num * lesson_price)) - payed_amount;
			}
 
	    }
	    	}

		public ObservableList<Student> getDataStudentsSearch() throws Exception {
				
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/drivingschool1", "root", "root");
			ObservableList<Student> list = FXCollections.observableArrayList();
			try {
				Statement stmt = conn.createStatement();
				String sql1;
				if (search.getText().matches("[0-9]+")) {
					sql1 = "Select * from student where student_id like '%" + search.getText() + "%' ;";
				} else {
					String[] split = search.getText().split(" ");
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
		    void openbill(ActionEvent event) throws Exception {
			 FXMLLoader load = new FXMLLoader(getClass().getResource("/com/Driving_School/view/bill.fxml"));
			 Parent r = (Parent) load.load();			
			 Stage stage = new Stage();
			 stage.setTitle("Payment Bill");
			 stage.setScene(new Scene(r));
			 stage.show();
		    }

   



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		C_id.setCellValueFactory(new PropertyValueFactory<Student, String>("student_id"));
		first_name.setCellValueFactory(new PropertyValueFactory<Student, String>("first_name"));
		last_name.setCellValueFactory(new PropertyValueFactory<Student, String>("last_name"));

		try {
			listS = getDataStudent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		table2.setItems(listS);
		
	}

}


