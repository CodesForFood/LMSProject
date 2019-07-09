package com.smoothstack.models;

public class BookCopies {

	private Book book;
	private LibraryBranch branch;	
	private int numOfCopies;
	
	public Book getBook() { return book; }
	public void setBook(Book book) { this.book = book; }
	
	public LibraryBranch getBranch() { return branch; }
	public void setBranch(LibraryBranch branch) { this.branch = branch; }
	
	public int getNumOfCopies() { return numOfCopies; }
	public void setNumOfCopies(int numOfCopies) { this.numOfCopies = numOfCopies; }
	
	
	public BookCopies() {}
	
	public BookCopies(Book book, LibraryBranch branch, int numOfCopies) {
		this.book = book;
		this.branch = branch;
		this.numOfCopies = numOfCopies;
		
	}
	
}
