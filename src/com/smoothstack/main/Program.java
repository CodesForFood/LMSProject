package com.smoothstack.main;

import com.smoothstack.dao.SqlConnection;
import com.smoothstack.service.BranchService;

public class Program {
	
	public static void main(String[] args) {		
		try {
			//Class.forName("com.mysql.jdbc.Driver"); 
		}
		catch(Exception ex) {
			
		}
		
		BranchService service = new BranchService();
		service.addBranch();
		service.viewAllBranches();
	
	}

}
