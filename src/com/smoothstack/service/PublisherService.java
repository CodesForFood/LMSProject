package com.smoothstack.service;

import java.util.*;


import com.smoothstack.dao.PublisherDAO;
import com.smoothstack.main.UI;
import com.smoothstack.models.Publisher;

public class PublisherService implements LibraryService {

	public static List<Publisher> pubList = new ArrayList<Publisher>();
	
	@Override
	public void add() {
		
		UI.say("Please enter the name of the new Publisher");
		String name = UI.getInstance().readLine();
		
		UI.say("Please enter the location of the Publisher");
		String address = UI.getInstance().readLine();
		
		UI.say("Please enter the phone number of the Publisher");
		String phone = UI.getInstance().readLine();		
		
		Publisher pub = new Publisher(name, address, phone);
		
		PublisherDAO.getInstance().addPublisher(pub);
	}
	
	@Override
	public void viewAll() {
		PublisherDAO.getInstance().selectAll();		
		
		pubList.stream().
			forEach(pub -> UI.say(pub.getId() + ") " + pub.getName() + " : " + pub.getAddress() + " : " + pub.getPhone()));
	}	
	
	@Override
	public void update() {
		boolean flag = true;
		Publisher pub = getPublisherChoice();
		
		while(flag && pub != null) {
			UI.say("You have chosen to update the Publisher with Publisher Id: " + pub.getId() 
				+ " and Publisher Name: " + pub.getName() + ".\nEnter ‘quit’ at any prompt to cancel operation");
			UI.say("Please enter the new name for the Publisher or enter N/A for no change");
			String name = UI.getInstance().readLine();
			
			if("quit".equalsIgnoreCase(name)){
				flag = false;
				break;				
			}
			else if("n/a".equalsIgnoreCase(name)) {
				name = pub.getName();
			}
			
			UI.say("Please enter the new address for the Publisher or enter N/A for no change");
			String address = UI.getInstance().readLine();
			
			if("quit".equalsIgnoreCase(address)){
				flag = false;
				break;				
			}
			else if("n/a".equalsIgnoreCase(address)) {
				address = pub.getAddress();
			}	
			
			UI.say("Please enter the new phone for the Publisher or enter N/A for no change");
			String phone = UI.getInstance().readLine();
			
			if("quit".equalsIgnoreCase(phone)) {
				flag = false;
				break;
			}
			else if("n/a".equalsIgnoreCase(phone)) {
				phone = pub.getPhone();
			}				
			
			pub.setName(name);
			pub.setAddress(address);
			pub.setPhone(phone);
			
			PublisherDAO.getInstance().updatePublisher(pub);										
			flag = false;
		}		
		
	}
	
	@Override
	public void delete() {
		boolean flag = true;
		Publisher pub = getPublisherChoice();
		
		while(flag) {
			UI.say("Are you sure you want to delete the Publisher with Id: " + pub.getId() + " and Publisher Name: " + pub.getName() + "? Y/N");
			UI.say("This action cannot be undone");
			
			String choice = UI.getInstance().readLine();
			
			if("y".equalsIgnoreCase(choice)) {
				PublisherDAO.getInstance().deletePublisher(pub);
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
	
	public Publisher getPublisherChoice() {
		boolean flag = true;
		while(flag) {
			UI.say("Which Publisher would you like to choose? Enter -1 to go back");
			viewAll();			
			int choice = UI.getInstance().readInt();
			
			if(choice == -1) {
				flag = false;
				break;
			}			
			
			Publisher pub = PublisherDAO.getInstance().getById(choice);
			
			if(pub.getId() == 0) {
				UI.badInput();
				continue;
			}
			else {
				flag = false;
				return pub;
			}					
		}
		return null;
	}	
	
	

}
