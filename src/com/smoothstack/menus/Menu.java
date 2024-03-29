package com.smoothstack.menus;

import com.smoothstack.main.Constants;
import com.smoothstack.main.UI;

public class Menu {		
	
	public void runMainProgram() {
		try {
			runMainMenu();
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());
			ex.printStackTrace();
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
				UI.lineBreak();
				MenuController.getMenu(Constants.LIBRARIAN).runMainMenu();
				break;
			case 2:
				UI.lineBreak();
				MenuController.getMenu(Constants.ADMIN).runMainMenu();
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
