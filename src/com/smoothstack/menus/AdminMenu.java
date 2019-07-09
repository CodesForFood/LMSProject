package com.smoothstack.menus;

import com.smoothstack.main.Constants;
import com.smoothstack.main.UI;
import com.smoothstack.service.LibraryService;

public class AdminMenu implements MenuController {

	@Override
	public void runMainMenu() {
		boolean flag = true;
		while(flag) {
			
			UI.say(Constants.ADMINMAINMENU);
			int choice = UI.getInstance().readInt();
			
			switch(choice) {
			
			case 1:
				//book
				break;
			case 2:
				//publisher
				break;
			case 3:
				//authors				
				break;
			case 4:
				UI.lineBreak();
				runAdminBranchMenu();
				break;
			case 5:
				//borrowers
				break;
			case 99:
				flag = false;
				break;
			default:
				break;										
			}
			
			
		}		
	}
	
	private void runAdminBranchMenu() {
		boolean flag = true;
		LibraryService service = LibraryService.getService(Constants.BRANCH);
		while(flag) {
			UI.say(Constants.ADMINBRANCHMENU);
			int choice = UI.getInstance().readInt();
			
			switch(choice) {
			case 1://add
				service.add();
				UI.lineBreak();
				break;
			case 2://update
				service.update();
				UI.lineBreak();
				break;								
			case 3://view all
				service.viewAll();
				UI.lineBreak();
				break;
			case 4://delete
				service.delete();	
				UI.lineBreak();
				break;
			case 99:
				flag = false;
				break;
			default:
				UI.badInput();
				break;
			}
		}
	}
	
	

	
	
	
	
	
}
