package com.smoothstack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import com.smoothstack.main.UI;
import com.smoothstack.models.*;


public class BorrowerDAO {
	
	
	private static BorrowerDAO borrDAO;
	
	private BorrowerDAO() {	
	}
	
	public static BorrowerDAO getInstance() {
		return borrDAO == null ? borrDAO = new BorrowerDAO() : borrDAO; 
	}

	
	public Borrower getByCardNumber(int cardNum) {
		
		String sql = "SELECT * FROM tbl_borrower WHERE cardNo = ?";
		Borrower borr = new Borrower();
		try(Connection conn = SqlConnection.getInstance().getConnection(); 
				PreparedStatement cmd = conn.prepareStatement(sql)){
			
			cmd.setInt(1, cardNum);			
			ResultSet result = cmd.executeQuery();
			
			if(result.next()) {
				borr.setCardNumber(cardNum);
				borr.setName(result.getString("name"));
				borr.setAddress(result.getString("address"));
				borr.setPhone(result.getString("phone"));
			}
			result.close();
			return borr;
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());
			return borr;		
		}	
	}
	
	
	public List<Book> getBooksFromBranch(LibraryBranch branch) {
		String sql = "SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_copies WHERE branchId = ? AND noOfCopies > 0); ";
		List<Book> bookList = new ArrayList<Book>();
		
		try(Connection conn = SqlConnection.getInstance().getConnection(); 
				PreparedStatement cmd = conn.prepareStatement(sql)){
			
			cmd.setInt(1, branch.getId());
			
			ResultSet result = cmd.executeQuery();
			
			
			while(result.next()) {
				Author auth = AuthorDAO.getInstance().getById(result.getInt("authId"));
				Publisher pub = PublisherDAO.getInstance().getById(result.getInt("pubId"));
				Book book = new Book(result.getInt("bookId"), result.getString("title"), auth, pub);
				bookList.add(book);
			}
			return bookList;		
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());
			return bookList;
		}
	}
	
	
}
