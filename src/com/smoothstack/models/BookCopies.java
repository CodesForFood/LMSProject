package com.smoothstack.models;

public class BookCopies {

	private Book book;
	private LibraryBranch branch;	
	private int numOfCopies;
	
	public Book getBookId() { return book; }
	public void setBookId(Book book) { this.book = book; }
	
	public LibraryBranch getBranchId() { return branch; }
	public void setBranchId(LibraryBranch branch) { this.branch = branch; }
	
	public int getNumOfCopies() { return numOfCopies; }
	public void setNumOfCopies(int numOfCopies) { this.numOfCopies = numOfCopies; }
	
	
	public BookCopies() {}
	
	public BookCopies(Book book, LibraryBranch branch, int numOfCopies) {
		this.book = book;
		this.branch = branch;
		this.numOfCopies = numOfCopies;
		
	}
	
}
