package com.smoothstack.models;

public class LibraryBranch {

	private int id = 0 ;
	private String name = "No Name";
	private String address = "No Address";
	
	public int getId() { return id;	}
	public void setId(int id) { this.id = id; }
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public String getAddress() { return address; }
	public void setAddress(String address) { this.address = address; }
	
	
	public LibraryBranch() {};
	
	public LibraryBranch(String name, String address) {
		this.name = name;
		this.address = address;
	}
	
	public LibraryBranch(int id, String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
	}
	
	
}
