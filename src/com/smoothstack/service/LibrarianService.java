package com.smoothstack.service;


import java.util.*;

import com.smoothstack.dao.LibrarianDAO;
import com.smoothstack.main.UI;
import com.smoothstack.models.*;


public class LibrarianService {

	private LibraryBranch choosenBranch;
	private LibrarianDAO libDAO = new LibrarianDAO();
	
	public static List<BookCopies> bookCopyList = new ArrayList<BookCopies>();

	public LibraryBranch getChoosenBranch() {
		return choosenBranch;
	}

	public void setChoosenBranch(LibraryBranch choosenBranch) {
		this.choosenBranch = choosenBranch;
	}		
	
	public void addBookCopy() {
		BookService bService = new BookService();
		Book book = bService.getBookChoice();
		if(book == null) {
			return;
		}
		
		UI.say("Enter the number of copies of " + book.getTitle() + " to add");
		int numOfCopies = UI.getInstance().readInt();
		
		libDAO.addCopies(choosenBranch, book, numOfCopies);	
	}

	
	public void getNumberOfCopies() {
		BookService bService = new BookService();
		Book book = bService.getBookChoice();
		
		int numOfCopies = libDAO.getCopiesOfBook(choosenBranch, book);
		if(numOfCopies == -1) {			
			numOfCopies = 0;
		}
		
		
		UI.say("You have " + numOfCopies + " of the Book Titled: " + book.getTitle() 
			+ " in the Library Branch of " + choosenBranch.getName());
	}
		
	public void getNumberOfAllCopies() {
		libDAO.getAllCopiesByBranch(choosenBranch);
		
		bookCopyList.stream().
			forEach(bCopy -> UI.say("Book: " + bCopy.getBook().getTitle() 
					+ " Copies: " + bCopy.getNumOfCopies()));	
	}


}
