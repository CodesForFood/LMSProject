package com.smoothstack.models;

public class Author {

	private int id = 0;
	private String name = "No Author";
	
	
	public int getAuthId() { return id; }
	public void setAuthId(int authId) { this.id = authId; }
	
	public String getAuthName() { return name; }
	public void setAuthName(String authName) { this.name = authName; }
	
	public Author() {}
	
	public Author(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Author(String name) {
		this.name = name;
	}
	
}
