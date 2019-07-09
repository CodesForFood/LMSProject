package com.smoothstack.service;

import java.util.*;

import com.smoothstack.dao.BorrowerDAO;
import com.smoothstack.main.UI;
import com.smoothstack.models.Borrower;


public class BorrowerService implements LibraryService {

	public static List<Borrower> borrList = new ArrayList<Borrower>();
	
	private int getUniqueCardNumber() {
		boolean flag = true;
		while(flag) {
			UI.say("Please enter a Unique card number for the Borrower, Enter '-1' to cancel");
			int cardNum = UI.getInstance().readInt();
			
			if(cardNum == -1) {
				return cardNum;
			}
			else if(cardNum == 0) {
				UI.badInput();
				continue;
			}
			
			BorrowerDAO.getInstance().selectAll();
			
			if(borrList.stream()
				.filter((borr) -> borr.getCardNumber() == cardNum)
				.count() == 0) {
				flag = false;
				return cardNum;
			}					
		}
		return 0;
	}
	
	

	@Override
	public void add() {		
		int cardNum = getUniqueCardNumber();
		if(cardNum == 0) {
			return;
		}			
		
		UI.say("Please enter the name of the new Borrower");
		String name = UI.getInstance().readLine();
		
		UI.say("Please enter the address of the Borrower");
		String address = UI.getInstance().readLine();
		
		UI.say("Please enter the Borrower's phone number");
		String phone = UI.getInstance().readLine();
		
		Borrower borr = new Borrower(cardNum, name, address, phone);			
		BorrowerDAO.getInstance().addBorrower(borr);					
	}
	
	@Override
	public void viewAll() {
		BorrowerDAO.getInstance().selectAll();		
		
		borrList.stream().
			forEach(borr -> UI.say(borr.getCardNumber() + ") " + borr.getName() + " : " + borr.getAddress() + " : " + borr.getPhone()));		
	}	
	
	@Override
	public void update() {
		boolean flag = true;
		Borrower borr = getBorrChoice();
		final int oldCardNumber = borr.getCardNumber();
		
		while(flag && borr != null) {
			UI.say("You have chosen to update the Borrower with Card Number: " + borr.getCardNumber() 
				+ " whose Named: " + borr.getName() + ".\nEnter ‘quit’ at any prompt to cancel operation");
			int newCardNumber = 0;
			boolean flag2 = true;
			while(flag2) {
				UI.say("Will you change the Card Number? Y/N");
				String choice = UI.getInstance().readLine();
				
				if("y".equalsIgnoreCase(choice)) {
					newCardNumber = getUniqueCardNumber();															
					flag2 = false;
				}
				else if("n".equalsIgnoreCase(choice)) {
					newCardNumber = oldCardNumber;
					flag2 = false;
				}
				else if("quit".equalsIgnoreCase(choice)) {
					flag2 = false;
					flag = false;
					return;
				}
				else {
					UI.badInput();
				}
			}						
			
			UI.say("Please enter the new name for the Borrower or enter N/A for no change");
			String name = UI.getInstance().readLine();
			
			if("quit".equalsIgnoreCase(name)){
				flag = false;
				break;				
			}
			else if("n/a".equalsIgnoreCase(name)) {
				name = borr.getName();
			}
			
			UI.say("Please enter the new address for the Borrower or enter N/A for no change");
			String address = UI.getInstance().readLine();
			
			if("quit".equalsIgnoreCase(address)){
				flag = false;
				break;				
			}
			else if("n/a".equalsIgnoreCase(address)) {
				address = borr.getAddress();
			}		
			
			UI.say("Please enter the new phone number of the Borrower or enter N/A for no change");
			String phone = UI.getInstance().readLine();
			
			if("quit".equalsIgnoreCase(phone)) {
				flag = false;
				break;				
			}
			else if("n/a".equalsIgnoreCase(phone)) {
				phone = borr.getPhone();
			}
			
			
			borr.setName(name);
			borr.setAddress(address);
			borr.setCardNumber(newCardNumber);
			
			BorrowerDAO.getInstance().updateBorrower(borr, oldCardNumber);										
			flag = false;
		}		
		
	}
	
	@Override
	public void delete() {
		boolean flag = true;
		Borrower borr = getBorrChoice();
		
		while(flag && borr != null) {
			UI.say("Are you sure you want to delete the Borrower with Card Number: " + borr.getCardNumber()  + " and Name: " + borr.getName() + "? Y/N");
			UI.say("This action cannot be undone");
			
			String choice = UI.getInstance().readLine();
			
			if("y".equalsIgnoreCase(choice)) {
				BorrowerDAO.getInstance().deleteBorrower(borr);
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
	
	private Borrower getBorrChoice() {
		boolean flag = true;
		while(flag) {
			UI.say("Which Borrower would you like to choose? Enter -1 to go back");
			viewAll();			
			int choice = UI.getInstance().readInt();
			
			if(choice == -1) {
				flag = false;
				break;
			}			
			
			Borrower borr = BorrowerDAO.getInstance().getById(choice);
			
			if(borr.getCardNumber() == 0) {
				UI.badInput();
				continue;
			}
			else {
				flag = false;
				return borr;
			}					
		}
		return null;
	}	
	

}
