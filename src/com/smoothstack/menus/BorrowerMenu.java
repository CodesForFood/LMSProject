package com.smoothstack.menus;

import com.smoothstack.main.UI;
import com.smoothstack.service.BorrowerService;

public class BorrowerMenu implements MenuController {

	BorrowerService borrService = new BorrowerService();
	
	@Override
	public void runMainMenu() {
		boolean flag = true;
		
		while(flag) {
			UI.say("Please enter you card number. Enter 0 to quit.");
			int cardNum = UI.getInstance().readInt();
			
			if(cardNum == 0) {
				flag = false;
				break;
			}
			
			if(borrService.isValidBorower(cardNum)) {				
				runBorr1();
			}
			else {
				UI.say("That is not a valid card number");
			}			
		}		
	}
	
	public void runBorr1() {
		
	}

}
