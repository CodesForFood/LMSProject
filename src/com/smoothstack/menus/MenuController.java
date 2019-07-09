package com.smoothstack.menus;

import com.smoothstack.main.Constants;

public interface MenuController {	
	
	public void runMainMenu();	
	
	public static MenuController getMenu(String type) {
		switch(type){
			case Constants.ADMIN:
				return new AdminMenu();
			
			
			
			default:
				return null;
		}
		
		
	}
	
}
