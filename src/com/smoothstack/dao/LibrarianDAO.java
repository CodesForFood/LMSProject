package com.smoothstack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.smoothstack.main.UI;
import com.smoothstack.models.*;
import com.smoothstack.service.BranchService;
import com.smoothstack.service.LibrarianService;

public class LibrarianDAO {

	public void getAllCopiesByBranch(LibraryBranch branch) {
		String sql = "SELECT * FROM tbl_book_copies WHERE branchId = ?";
		
		try(Connection conn = SqlConnection.getInstance().getConnection(); 
				PreparedStatement cmd = conn.prepareStatement(sql)){
			
			cmd.setInt(1, branch.getId());
			ResultSet result = cmd.executeQuery();	
			
			LibrarianService.bookCopyList.clear();
			
			while(result.next()) {
				Book book = BookDAO.getInstance().getById(result.getInt("bookId"));
				int numOfCopies = result.getInt("noOfCopies");
				
				BookCopies bCopy = new BookCopies(book, branch, numOfCopies);
				
				LibrarianService.bookCopyList.add(bCopy);				
			}
			result.close();
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());
		}	
	}
	
	
	public int getCopiesOfBook(LibraryBranch branch, Book book) {
		String sql = "SELECT noOfCopies FROM tbl_book_copies WHERE branchId = ? AND bookId = ?";
		
		try(Connection conn = SqlConnection.getInstance().getConnection(); 
				PreparedStatement cmd = conn.prepareStatement(sql)){
			
			cmd.setInt(1, branch.getId());
			cmd.setInt(2, book.getBookId());
			
			ResultSet result = cmd.executeQuery();
			int numOfCopies = -1;
			if(result.next()) {
				numOfCopies = result.getInt("noOfCopies");
			}
			result.close();
			return numOfCopies;
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());
			return -1;			
		}	
	}
	
	public void addCopies(LibraryBranch branch, Book book, int numOfCopies) {		
		int copyExist = getCopiesOfBook(branch, book);
		
		String sql = "";
		boolean flag = true;
		
		if(copyExist == -1) {
			sql = "INSERT INTO tbl_book_copies (bookId, branchId, noOfCopies) " + 
					"VALUES (?, ?, ?);";
			flag = true;
		}
		else {
			sql = "UPDATE tbl_book_copies SET noOfCopies = ? WHERE bookId = ? AND branchId = ?;";
			flag = false;
		}
		
		try(Connection conn = SqlConnection.getInstance().getConnection(); 
				PreparedStatement cmd = conn.prepareStatement(sql)){
			if(flag) {
				cmd.setInt(1, book.getBookId());
				cmd.setInt(2, branch.getId());
				cmd.setInt(3, numOfCopies);
			}
			else {
				cmd.setInt(1, copyExist + numOfCopies);
				cmd.setInt(2, book.getBookId());
				cmd.setInt(3, branch.getId());
			}
			
			cmd.execute();
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());
		}
		
	}
	
	
}
