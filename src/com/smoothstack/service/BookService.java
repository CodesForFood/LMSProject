package com.smoothstack.service;

import java.util.*;

import com.smoothstack.dao.*;
import com.smoothstack.main.Constants;
import com.smoothstack.main.UI;
import com.smoothstack.models.*;


public class BookService implements LibraryService {

	public static List<Book> bookList = new ArrayList<Book>();
	
	private Author newOrOldAuthor() {
		boolean flag = true;
		Author auth = new Author();
		while(flag) {
			UI.say("Would you like to \n1)Choose from an existing author? \n2)Make a new Author?");
			int choice = UI.getInstance().readInt();									
			if(choice == 1) {
				AuthorService aService = (AuthorService) LibraryService.getService(Constants.AUTHOR);
				auth = aService.getAuthorChoice();
				if(auth == null) {
					auth = new Author();
				}
				flag = false;
			}
			else if(choice == 2) {
				UI.say("Please enter the name of the Author");
				String name = UI.getInstance().readLine();
				auth.setAuthName(name);
				flag = false;
			}
			else {
				UI.badInput();				
			}						
		}													
		return auth;
	}
	
	private Publisher newOrOldPublisher() {
		boolean flag = true;
		Publisher pub = new Publisher();
		while(flag) {
			UI.say("Would you like to \n1)Choose from an existing Publisher? \n2)Make a new Publisher?");
			int choice = UI.getInstance().readInt();									
			if(choice == 1) {
				PublisherService pService = (PublisherService) LibraryService.getService(Constants.PUBLISHER);
				pub = pService.getPublisherChoice();
				if(pub == null) {
					pub = new Publisher();
				}
				flag = false;
			}
			else if(choice == 2) {
				UI.say("Please enter the name of the Publisher");
				String name = UI.getInstance().readLine();
				
				UI.say("Please enter the address of the Publisher");
				String address = UI.getInstance().readLine();
				
				UI.say("Please enter the phone number of the Publisher");
				String phone = UI.getInstance().readLine();			
				
				pub.setName(name);
				pub.setAddress(address);
				pub.setPhone(phone);								
				
				flag = false;
			}
			else {
				UI.badInput();				
			}						
		}													
		return pub;
	}
	
	
	
	@Override
	public void add() {
		
		UI.say("Please enter the title of the new Book");
		String name = UI.getInstance().readLine();
		
		Author auth = newOrOldAuthor();
		Publisher pub = newOrOldPublisher();
			
		if(auth.getAuthId() == 0) {
			AuthorDAO.getInstance().addAuthor(auth);
			auth.setAuthId(AuthorDAO.getInstance().getHighestIndex());
		}		
		if(pub.getId() == 0) {
			PublisherDAO.getInstance().addPublisher(pub);
			pub.setId(PublisherDAO.getInstance().getHighestIndex());
		}
		
		Book book = new Book(name, auth, pub);
		
		BookDAO.getInstance().addBook(book);
		
	
	}
	
	@Override
	public void viewAll() {
		BookDAO.getInstance().selectAll();		
		
		bookList.stream().
			forEach(book -> UI.say(book.getBookId() + ") " + book.getTitle() 
				+ " - Publisher:" + book.getPublisher().getName() 
				+ " - Author: " + book.getAuthor().getAuthName()));		
	}	
	
	@Override
	public void update() {
		boolean flag = true;
		Book book = getBookChoice();
		
		Author auth = book.getAuthor();
		Publisher pub = book.getPublisher();
		
		mainLoop:
		while(flag && book != null) {
			UI.say("You have chosen to update the Book with Book Id: " + book.getBookId() 
				+ " and the Title: " + book.getTitle() + ".\nEnter ‘quit’ at any prompt to cancel operation");
			UI.say("Please enter the new title for the Book or enter N/A for no change");
			String title = UI.getInstance().readLine();
			
			if("quit".equalsIgnoreCase(title)){
				flag = false;
				break;				
			}
			else if("n/a".equalsIgnoreCase(title)) {
				title = book.getTitle();
			}								

			while(true) {
				UI.say("Would you like to change the associated Author? Y/N");
				String choice = UI.getInstance().readLine();
				
				if("Y".equalsIgnoreCase(choice)) {
					AuthorService aService = (AuthorService)LibraryService.getService(Constants.AUTHOR);
					auth = aService.getAuthorChoice();
					break;
				}
				else if("N".equalsIgnoreCase(choice)) {
					break;
				}
				else if("quit".equalsIgnoreCase(choice)) {
					flag = false;				
					break mainLoop;
				}
				else {
					UI.badInput();
				}				
			}
			
			while(true) {
				UI.say("Would you like to change the associated Publisher? Y/N");
				String choice = UI.getInstance().readLine();
				
				if("Y".equalsIgnoreCase(choice)) {
					PublisherService pService = (PublisherService)LibraryService.getService(Constants.PUBLISHER);
					pub = pService.getPublisherChoice();
					break;
				}
				else if("N".equalsIgnoreCase(choice)) {
					break;
				}
				else if("quit".equalsIgnoreCase(choice)) {
					flag = false;				
					break mainLoop;
				}
				else {
					UI.badInput();
				}				
			}
			
			book.setTitle(title);
			book.setAuthor(auth);
			book.setPublisher(pub);
			
			BookDAO.getInstance().updateBook(book);			
												
			flag = false;
		}		
		
	}
	
	@Override
	public void delete() {
		boolean flag = true;
		Book book = getBookChoice();
		
		while(flag) {
			UI.say("Are you sure you want to delete the Book with Id: " + book.getBookId() + " and Book Title: " + book.getTitle() + "? Y/N");
			UI.say("This action cannot be undone");
			
			String choice = UI.getInstance().readLine();
			
			if("y".equalsIgnoreCase(choice)) {
				BookDAO.getInstance().deleteBook(book);
				flag = false;
			}
			else if("n".equalsIgnoreCase(choice)){
				UI.say("Aborting deletion.");
				flag = false;
			}
			else {
				UI.badInput();
			}
		}		
	}
	
	public Book getById() {
		boolean flag = true;
		while(flag) {
			UI.say("Please enter the id of the book");
			int choice = UI.getInstance().readInt();
			
			Book book = BookDAO.getInstance().getById(choice);
			
			if(book.getBookId() == 0) {
				UI.badInput();
				continue;
			}		
			flag = false;
			return book;						
		}
		return null;							
	}
	
	
	public Book getBookChoice() {
		boolean flag = true;
		while(flag) {
			UI.say("Which Book would you like to choose? Enter -1 to go back");
			viewAll();			
			int choice = UI.getInstance().readInt();
			
			if(choice == -1) {
				flag = false;
				break;
			}			
			
			Book book = BookDAO.getInstance().getById(choice);
			
			if(book.getBookId() == 0) {
				UI.badInput();
				continue;
			}
			else {
				flag = false;
				return book;
			}					
		}
		return null;
	}	
	
}
