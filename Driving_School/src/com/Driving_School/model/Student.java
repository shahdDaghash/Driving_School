package com.Driving_School.model;

import java.sql.Date;

public class Student {
	String student_id;
	String first_name;
	String last_name;
	String mobile_num;
	Date eye_test_date;
	String address;
	String process_state;
	String emp_id;
	
	public Student(String student_id, String first_name, String last_name, String mobile_num, Date eye_test_date, String address, String process_state, String emp_id) {
		super();
		this.student_id = student_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.mobile_num = mobile_num;
		this.eye_test_date = eye_test_date;
		this.address = address;
		this.process_state = process_state;
		this.emp_id = emp_id;
	}

	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getMobile_num() {
		return mobile_num;
	}

	public void setMobile_num(String mobile_num) {
		this.mobile_num = mobile_num;
	}

	public Date getEye_test_date() {
		return eye_test_date;
	}

	public void setEye_test_date(Date eye_test_date) {
		this.eye_test_date = eye_test_date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProcess_state() {
		return process_state;
	}

	public void setProcess_state(String process_state) {
		this.process_state = process_state;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	
	
	
	
	
}
