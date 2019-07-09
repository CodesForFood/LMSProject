package com.smoothstack.menus;

import com.smoothstack.main.Constants;
import com.smoothstack.main.UI;
import com.smoothstack.models.Book;
import com.smoothstack.service.BookService;
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
				UI.lineBreak();
				runAdminBookMenu();
				break;
			case 2:
				UI.lineBreak();
				runMenu(Constants.ADMINPUBLISHERMENU, Constants.PUBLISHER);
				break;
			case 3:
				UI.lineBreak();
				runMenu(Constants.ADMINAUTHORMENU, Constants.AUTHOR);
				break;
			case 4:
				UI.lineBreak();
				runMenu(Constants.ADMINBRANCHMENU, Constants.BRANCH);
				break;
			case 5:
				UI.lineBreak();
				runMenu(Constants.ADMINBORROWERMENU, Constants.BORROWER);
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
	
	private void runMenu(String menu, String type) {
		boolean flag = true;
		LibraryService service = LibraryService.getService(type);
		while(flag) {
			UI.say(menu);
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
	
	
	private void runAdminBookMenu() {
		boolean flag = true;
		BookService service = (BookService)LibraryService.getService(Constants.BOOK);
		while(flag) {
			UI.say(Constants.ADMINBOOKMENU);
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
			case 4:
				Book book = service.getById();
				book.displayBook();
				break;
			case 5://delete
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
