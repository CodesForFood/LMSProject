package com.smoothstack.service;

import java.util.*;
import com.smoothstack.dao.AuthorDAO;
import com.smoothstack.main.UI;
import com.smoothstack.models.Author;



public class AuthorService implements LibraryService {

	public static List<Author> authList = new ArrayList<Author>();

	@Override
	public void add() {
		UI.say("Please enter the name of the new Author");
		String name = UI.getInstance().readLine();
		
		AuthorDAO.getInstance().addAuthor(new Author(name));		
	}

	@Override
	public void update() {
		Author auth = getAuthorChoice();
		if(auth != null) {
			UI.say("You have choosen to update the author " + auth.getAuthName() 
				+ " with Author Id: " + auth.getAuthId() + "\nEnter 'quit' to cancle the Update");
			UI.say("What is the new name of the Author?");
			String name = UI.getInstance().readLine();
			if("quit".equalsIgnoreCase(name)) {
				return;
			}
			
			auth.setAuthName(name);			
			AuthorDAO.getInstance().updateAuthor(auth);
		}		
	}
	
	public Author update(int id) {
		Author auth = AuthorDAO.getInstance().getById(id);
		
		UI.say("You have choosen to update the author " + auth.getAuthName() 
			+ " with Author Id: " + auth.getAuthId() + "\nEnter 'quit' to cancle the Update");
		UI.say("What is the new name of the Author?");
		String name = UI.getInstance().readLine();
		if("quit".equalsIgnoreCase(name)) {
			return auth;
		}
		
		auth.setAuthName(name);					
		return auth;				
	}

	@Override
	public void viewAll() {
		AuthorDAO.getInstance().selectAll();		
		
		authList.stream().
			forEach(auth -> UI.say(auth.getAuthId() + ") " + auth.getAuthName()));		
	}

	@Override
	public void delete() {
		boolean flag = true;
		Author auth = getAuthorChoice();
		
		while(flag) {
			UI.say("Are you sure you want to delete the Author with Id: " + auth.getAuthId() + " and Author Name: " + auth.getAuthName() + "? Y/N");
			UI.say("This action cannot be undone");
			
			String choice = UI.getInstance().readLine();
			
			if("y".equalsIgnoreCase(choice)) {
				AuthorDAO.getInstance().deleteAuthor(auth);
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
	
	public Author getAuthorChoice() {
		boolean flag = true;
		while(flag) {
			UI.say("Which Author would you like to choose? Enter -1 to go back");
			viewAll();			
			int choice = UI.getInstance().readInt();
			
			if(choice == -1) {
				flag = false;
				break;
			}			
			
			Author auth = AuthorDAO.getInstance().getById(choice);
			
			if(auth.getAuthId() == 0) {
				UI.badInput();
				continue;
			}
			else {
				flag = false;
				return auth;
			}					
		}
		return null;
	}	
	
	
}
