package com.smoothstack.models;

import java.time.LocalDate;

public class BookLoan {

	private int bookId;
	private int branchId;
	private int cardNumber;
	
	private LocalDate dateOut;
	private LocalDate dateDue;
	
	public int getBookId() { return bookId; }
	public void setBookId(int bookId) { this.bookId = bookId; }
	
	public int getBranchId() { return branchId; }
	public void setBranchId(int branchId) { this.branchId = branchId; }
	
	public int getCardNumber() { return cardNumber; }
	public void setCardNumber(int cardNumber) { this.cardNumber = cardNumber; }
	
	public LocalDate getDateOut() {	return dateOut; }
	public void setDateOut(LocalDate dateOut) { this.dateOut = dateOut; }
	
	public LocalDate getDateDue() {	return dateDue;	}
	public void setDateDue(LocalDate dateDue) {	this.dateDue = dateDue;	}
	
	
}
