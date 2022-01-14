package com.Driving_School.model;

public class Vehicle {
	
	String vehicle_num;
	String insurance_end_date;

	public Vehicle() {
		
	}
	
	public Vehicle(String vehicle_num, String insurance_end_date) {
		this.insurance_end_date = insurance_end_date;
		this.vehicle_num = vehicle_num;
	}

	public String getVehicle_num() {
		return vehicle_num;
	}

	public void setVehicle_num(String vehicle_num) {
		this.vehicle_num = vehicle_num;
	}

	public String getInsurance_end_date() {
		return insurance_end_date;
	}

	public void setInsurance_end_date(String insurance_end_date) {
		this.insurance_end_date = insurance_end_date;
	}
	
	


}
