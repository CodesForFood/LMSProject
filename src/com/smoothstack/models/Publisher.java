package com.smoothstack.models;

public class Publisher {

	private int id = 0;
	private String name = "No Publisher";
	private String address;
	private String phone;
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	
	public String getName() {return name; }
	public void setName(String name) { this.name = name; }
	
	public String getAddress() { return address; }
	public void setAddress(String address) { this.address = address; }
	
	public String getPhone() { return phone; }
	public void setPhone(String phone) { this.phone = phone; }
	
	public Publisher() { }
	public Publisher(int id, String name, String address, String phone) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}
	
	public Publisher(String name, String address, String phone) {
		this.name = name;
		this.address = address;
		this.phone = phone;
	}
	
}
