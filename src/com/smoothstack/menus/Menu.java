package com.smoothstack.menus;

import com.smoothstack.main.Constants;
import com.smoothstack.main.UI;
import com.smoothstack.service.LibraryService;

public class Menu {		
	
	public void runMainProgram() {
		try {
			runMainMenu();
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());
		}
		finally {
			UI.getInstance().closeScanner();
		}
		
	}
	
	
	private void runMainMenu() {
		boolean flag = true;
		
		while(flag) {
			UI.say(Constants.MAINMENU);		
			int choice = UI.getInstance().readInt();
			
			switch(choice) {
			case 1:
				
				break;
			case 2:
				UI.lineBreak();
				MenuController.getMenu(Constants.ADMIN).runMainMenu();;
				break;
			case 3:
				break;
			default:
				UI.say("Quiting program");
				flag = false;
				break;															
			}									
		}				
	}
	
	
	
	
	
	
	

}
