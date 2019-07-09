package com.smoothstack.menus;

import com.smoothstack.main.Constants;
import com.smoothstack.main.UI;
import com.smoothstack.models.LibraryBranch;
import com.smoothstack.service.*;

public class LibrarianMenu implements MenuController {

	LibrarianService libService;
	
	public LibrarianMenu(){
		libService = new LibrarianService();
	}
	
	
	@Override
	public void runMainMenu() {
		boolean flag = true;
		while(flag) {
			UI.say(Constants.LIB1);
			int choice = UI.getInstance().readInt();
			
			switch(choice) {
			case 1:
				BranchService bService = (BranchService)LibraryService.getService(Constants.BRANCH);
				LibraryBranch branch = bService.getBranchChoice();								
				if(branch == null) {
					flag = false;
				}
				else {
					libService.setChoosenBranch(branch);
					
				}
					
				break;
			case 2: 
				flag = false;
				break;
			default:
				UI.badInput();				
				break;							
			}						
		}		
		
	}
	
	
	private void runLib2() {
		boolean flag = true;
		while(flag) {
			UI.say(Constants.LIB2);
			int choice = UI.getInstance().readInt();
			
			switch(choice) {
			case 1:
				BranchService bService = (BranchService)LibraryService.getService(Constants.BRANCH);
				bService.updateWithBranch(libService.getChoosenBranch());
				break;
			case 2:
				break;
			case 3:
				flag = false;
				break;
			default:
				UI.badInput();
				break;									
			
			}
			
		}
		
		
	}
	
	

}
