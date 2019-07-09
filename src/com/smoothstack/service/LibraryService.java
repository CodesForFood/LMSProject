package com.smoothstack.service;

import com.smoothstack.main.Constants;

public interface LibraryService {

	public void add();
	public void update();
	public void viewAll();
	public void delete();	
	
	
	public static LibraryService getService(String type) {
		switch(type) {
		
		case Constants.BRANCH :
			return new BranchService();
		case Constants.AUTHOR:
			return new AuthorService();
		case Constants.ADMINBORROWER:
			return new AdminBorrowerService();
		case Constants.BOOK:
			return new BookService();
		case Constants.PUBLISHER:
			return new PublisherService();
		default:
		return null;
		}
	}
	
}
