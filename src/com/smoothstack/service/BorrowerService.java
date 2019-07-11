package com.smoothstack.service;

import java.util.*;

import com.smoothstack.dao.BorrowerDAO;
import com.smoothstack.main.UI;
import com.smoothstack.models.*;

public class BorrowerService {

	private Borrower choosenBorr = new Borrower();		
	
	public void setChoosenBorr(Borrower borr) {
		this.choosenBorr = borr;
	}
	
	public Borrower getChoosenBorr() {
		return this.choosenBorr;
	}
	
	
	public boolean isValidBorower(int cardNum) {
		Borrower borr = BorrowerDAO.getInstance().getByCardNumber(cardNum);
		
		if(borr.getCardNumber() == 0) {
			return false;
		}
		else {
			this.choosenBorr = borr;
			return true;
		}
	}
	
	public void checkOutBook() {
		boolean flag = true;
		BranchService bService = new BranchService();
		while(flag) {
			LibraryBranch branch = bService.getBranchChoice();
			if(branch == null) {
				flag = false;
				break;
			}
			List<Book> bookList = BorrowerDAO.getInstance().getBooksFromBranch(branch);
			
			if(bookList.size() > 0) {
				
			}
			
			
		}
	}
	
	private Book getBookChoiceFromList(List<Book> bList) {
		boolean flag = true;
		while(flag) {
			UI.say("Which Book would you like to choose? Enter -1 to go back");
			bList.stream()
				.forEach(book -> book.displayBook());	
			
			int choice = UI.getInstance().readInt();
			
			if(choice == -1) {
				flag = false;
				break;
			}			
			
			Book choosenBook = (Book) bList.stream()
					.filter(book -> book.getBookId() == choice);
		
			
			
		}
		return null;
		
	}
	
	


	
}
