package com.smoothstack.models;

public class Book {

	
	private int id;
	private String title;
	
	private Author author;
	private Publisher publisher;
	
	
	public int getBookId() { return id; }
	public void setBookId(int bookId) { this.id = bookId; }
	
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	
	public Author getAuthor() {	return author; }
	public void setAuthor(Author author) { this.author = author; }
	
	public Publisher getPublisher() { return publisher; }
	public void setPublisher(Publisher publisher) {	this.publisher = publisher; }
		
	
}
