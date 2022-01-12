package com.Driving_School.model;


public class Student {
	String student_id;
	String first_name;
	String last_name;
	String mobile_num;
	String eye_test_date;
	String address;
	String process_status;
	String license;
	int test_taken;
	String emp_id;

	
	
	public Student(String student_id, String first_name, String last_name, String mobile_num, String eye_test_date, String address, String process_status, String license, int test_taken, String emp_id) {
		super(); 
		this.student_id = student_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.mobile_num = mobile_num;
		this.eye_test_date = eye_test_date;
		this.address = address;
		this.process_status = process_status;
		this.license = license;
		this.test_taken = test_taken;
		this.emp_id = emp_id;
	}
	
//	public Student(String student_id, String first_name, String last_name, String mobile_num, String eye_test_date, String address, String process_state) {
//		super();
//		this.student_id = student_id;
//		this.first_name = first_name;
//		this.last_name = last_name;
//		this.mobile_num = mobile_num;
//		this.eye_test_date = eye_test_date;
//		this.address = address;
//		this.process_state = process_state;
//		//this.emp_id = emp_id;
//	}
	
	

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

	public String getEye_test_date() {
		return eye_test_date;
	}

	public void setEye_test_date(String eye_test_date) {
		this.eye_test_date = eye_test_date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProcess_status() {
		return process_status;
	}

	public void setProcess_status(String process_state) {
		this.process_status = process_state;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public int getTest_taken() {
		return test_taken;
	}

	public void setTest_taken(int test_taken) {
		this.test_taken = test_taken;
	}
	
	
	
	
	
}
