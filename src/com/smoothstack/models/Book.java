package com.smoothstack.models;

import com.smoothstack.main.UI;

public class Book {

	
	private int id = 0;
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
		
	public Book() {}
	
	public Book(int id, String title, Author auth, Publisher pub) {
		this.id = id;
		this.title = title;
		this.author = auth;
		this.publisher = pub;		
	}
	
	public Book(String title, Author auth, Publisher pub) {
		this.title = title;
		this.author = auth;
		this.publisher = pub;
	}
	
	
	public void displayBook() {
		UI.say(id + ") " + title + " - Author: " + author.getAuthName() + " - Publisher: " + publisher.getName());
	}
	
}
