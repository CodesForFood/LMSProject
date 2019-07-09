package com.smoothstack.service;

import com.smoothstack.dao.BorrowerDAO;
import com.smoothstack.models.Borrower;

public class BorrowerService {

	private Borrower choosenBorr = new Borrower();
	BorrowerDAO borrDAO = new BorrowerDAO();
	
	
	public void setChoosenBorr(Borrower borr) {
		this.choosenBorr = borr;
	}
	
	public Borrower getChoosenBorr() {
		return this.choosenBorr;
	}
	
	
	public boolean isValidBorower(int cardNum) {
		Borrower borr = borrDAO.getByCardNumber(cardNum);
		
		if(borr.getCardNumber() == 0) {
			return false;
		}
		else {
			this.choosenBorr = borr;
			return true;
		}
	}
	
	public void checkOutBook() {
		
	}
	
}
