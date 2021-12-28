package com.Driving_School.model;

public class Employee {
	String emp_id;
	String first_name;
	String last_name;
	String mobile_num;
	String address;
	/**
	 * @param id
	 * @param first_name
	 * @param last_name
	 * @param mobile_num
	 * @param address
	 */
	public Employee(String emp_id, String first_name, String last_name, String mobile_num, String address) {
		super();
		this.emp_id = emp_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.mobile_num = mobile_num;
		this.address = address;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	 
}