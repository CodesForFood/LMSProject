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
		default:
		return null;
		}
	}
	
}
