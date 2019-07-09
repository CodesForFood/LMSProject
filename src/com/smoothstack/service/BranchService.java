package com.smoothstack.service;

import java.util.*;

import com.smoothstack.dao.LibraryBranchDAO;
import com.smoothstack.main.UI;
import com.smoothstack.models.*;


public class BranchService implements LibraryService {

	public static List<LibraryBranch> branchList = new ArrayList<LibraryBranch>();
	
	@Override
	public void add() {
		
		UI.say("Please enter the name of the new Branch");
		String name = UI.getInstance().readLine();
		
		UI.say("Please enter the location of the Branch");
		String address = UI.getInstance().readLine();
		
		LibraryBranch branch = new LibraryBranch(name, address);
		
		LibraryBranchDAO.getInstance().addBranch(branch);	
	}
	
	@Override
	public void viewAll() {
		LibraryBranchDAO.getInstance().selectAll();		
		
		branchList.stream().
			forEach(branch -> UI.say(branch.getId() + ") " + branch.getName() + " : " + branch.getAddress()));		
	}	
	
	@Override
	public void update() {
		boolean flag = true;
		LibraryBranch branch = getBranchChoice();
		
		while(flag && branch != null) {
			UI.say("You have chosen to update the Branch with Branch Id: " + branch.getId() 
				+ " and Branch Name: " + branch.getName() + ".\nEnter ‘quit’ at any prompt to cancel operation");
			UI.say("Please enter the new name for the branch or enter N/A for no change");
			String name = UI.getInstance().readLine();
			
			if("quit".equalsIgnoreCase(name)){
				flag = false;
				break;				
			}
			else if("n/a".equalsIgnoreCase(name)) {
				name = branch.getName();
			}
			
			UI.say("Please enter the new address for the branch or enter N/A for no change");
			String address = UI.getInstance().readLine();
			
			if("quit".equalsIgnoreCase(address)){
				flag = false;
				break;				
			}
			else if("n/a".equalsIgnoreCase(address)) {
				address = branch.getAddress();
			}															
			
			
			branch.setName(name);
			branch.setAddress(address);
			
			LibraryBranchDAO.getInstance().updateBranch(branch);										
			flag = false;
		}		
		
	}
	
	
	public void updateWithBranch(LibraryBranch branch) {
		boolean flag = true;
		
		while(flag && branch != null) {
			UI.say("You have chosen to update the Branch with Branch Id: " + branch.getId() 
				+ " and Branch Name: " + branch.getName() + ".\nEnter ‘quit’ at any prompt to cancel operation");
			UI.say("Please enter the new name for the branch or enter N/A for no change");
			String name = UI.getInstance().readLine();
			
			if("quit".equalsIgnoreCase(name)){
				flag = false;
				break;				
			}
			else if("n/a".equalsIgnoreCase(name)) {
				name = branch.getName();
			}
			
			UI.say("Please enter the new address for the branch or enter N/A for no change");
			String address = UI.getInstance().readLine();
			
			if("quit".equalsIgnoreCase(address)){
				flag = false;
				break;				
			}
			else if("n/a".equalsIgnoreCase(address)) {
				address = branch.getAddress();
			}															
			
			
			branch.setName(name);
			branch.setAddress(address);
			
			LibraryBranchDAO.getInstance().updateBranch(branch);										
			flag = false;
		}		
	}
	
	@Override
	public void delete() {
		boolean flag = true;
		LibraryBranch branch = getBranchChoice();
		
		while(flag && branch != null) {
			UI.say("Are you sure you want to delete the Branch with Id: " + branch.getId() + " and Branch Name: " + branch.getName() + "? Y/N");
			UI.say("This action cannot be undone");
			
			String choice = UI.getInstance().readLine();
			
			if("y".equalsIgnoreCase(choice)) {
				LibraryBranchDAO.getInstance().deleteBranch(branch);
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
	
	public LibraryBranch getBranchChoice() {
		boolean flag = true;
		while(flag) {
			UI.say("Which branch would you like to choose? Enter -1 to go back");
			viewAll();			
			int choice = UI.getInstance().readInt();
			
			if(choice == -1) {
				flag = false;
				break;
			}			
			
			LibraryBranch branch = LibraryBranchDAO.getInstance().getById(choice);
			
			if(branch.getId() == 0) {
				UI.badInput();
				continue;
			}
			else {
				flag = false;
				return branch;
			}					
		}
		return null;
	}	
	
	
	
	
}
