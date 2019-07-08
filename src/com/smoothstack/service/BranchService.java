package com.smoothstack.service;

import java.util.*;

import com.smoothstack.dao.LibraryBranchDAO;
import com.smoothstack.main.UI;
import com.smoothstack.models.*;


public class BranchService {

	public static List<LibraryBranch> branchList = new ArrayList<LibraryBranch>();
	
	
	public void addBranch() {
		
		UI.say("Please enter the name of the new Branch");
		String name = UI.getInstance().readLine();
		
		UI.say("Please enter the location of the Branch");
		String address = UI.getInstance().readLine();
		
		LibraryBranch branch = new LibraryBranch(name, address);
		
		LibraryBranchDAO.getInstance().addBranch(branch);	
	}
	
	public void viewAllBranches() {
		LibraryBranchDAO.getInstance().selectAll();
		
		
		branchList.stream().
			forEach(branch -> UI.say(branch.getId() + ") Name:" + branch.getName() + " Location: " + branch.getAddress()));		
	}
	
	
	
	
}
