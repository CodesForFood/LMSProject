package com.smoothstack.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.smoothstack.main.UI;
import com.smoothstack.models.*;
import com.smoothstack.service.BranchService;
import com.smoothstack.service.LibrarianService;

public class LibrarianDAO {

	public void getAllCopiesByBranch(LibraryBranch branch) {
		try(Connection conn = SqlConnection.getInstance().getConnection(); 
				Statement cmd = conn.createStatement()){
			
			ResultSet result = cmd.executeQuery("SELECT * FROM tbl_book_copies WHERE branchId = ?");
			
			LibrarianService.bookCopyList.clear();
			
			while(result.next()) {
				Book book = BookDAO.getInstance().getById(result.getInt("bookId"));
				int numOfCopies = result.getInt("noOfCopies");
				
				BookCopies bCopy = new BookCopies();
				
				LibrarianService.bookCopyList.add(e)
				
			}
			result.close();
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());
		}	
	}
	
	
	
}
