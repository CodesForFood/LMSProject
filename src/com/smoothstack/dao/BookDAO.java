package com.smoothstack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.smoothstack.main.UI;
import com.smoothstack.models.*;
import com.smoothstack.service.BookService;

public class BookDAO {

private static BookDAO bookDAO; 
	
	private BookDAO() {
		
	}
		
	public static BookDAO getInstance() {
		return bookDAO == null ? bookDAO = new BookDAO() : bookDAO;
	}
	

	public void selectAll() {
		try(Connection conn = SqlConnection.getInstance().getConnection(); 
				Statement cmd = conn.createStatement()){
			ResultSet result = cmd.executeQuery("SELECT * FROM tbl_book;");
			
			BookService.bookList.clear();
			
			while(result.next()) {
				
				Author auth = AuthorDAO.getInstance().getById(result.getInt("authId"));
				Publisher pub = PublisherDAO.getInstance().getById(result.getInt("pubId"));
				
				Book book = new Book(result.getInt("bookId"),
						result.getString("title"),
						auth,
						pub);
				
				BookService.bookList.add(book);			
			}	
			result.close();
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());
			ex.printStackTrace();
		}	
	}
	
	
	public void addBook(Book book) {
		if(book == null) {
			UI.say("Book object is null, in addBook");
			return;
		}		
		String sql = "INSERT INTO tbl_book (title, authId, pubId) VALUES (?, ?, ?);";
		
		try(Connection conn = SqlConnection.getInstance().getConnection(); 
				PreparedStatement cmd = conn.prepareStatement(sql);){			
			cmd.setString(1, book.getTitle());
			cmd.setInt(2, book.getAuthor().getAuthId());
			cmd.setInt(3, book.getPublisher().getId());
			
			cmd.execute();		
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());
		}	
	}
	
	public void updateBook(Book book) {
		if(book == null) {
			UI.say("Book object is null, in updateBook");
			return;
		}		
		String sql = "UPDATE tbl_book SET title = ?, authId = ?, pubId = ? WHERE bookId = ?";
		
		try(Connection conn = SqlConnection.getInstance().getConnection(); 
				PreparedStatement cmd = conn.prepareStatement(sql);){			
			cmd.setString(1, book.getTitle());
			cmd.setInt(2, book.getAuthor().getAuthId());
			cmd.setInt(3, book.getPublisher().getId());
			cmd.setInt(4, book.getBookId());
			
			cmd.execute();		
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());
		}	
	}
	
	public void deleteBook(Book book) {
		if(book == null) {
			UI.say("Book object is null, in deleteBook");
			return;
		}		
		String sql = "DELETE FROM tbl_book WHERE bookId = ?";
		
		try(Connection conn = SqlConnection.getInstance().getConnection(); 
				PreparedStatement cmd = conn.prepareStatement(sql);){
			
			cmd.setInt(1, book.getBookId());			
			cmd.execute();		
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());
		}	
	}
	
	public Book getById(int id) {
		String sql = "SELECT * FROM tbl_book WHERE bookId = ?";
		
		try(Connection conn = SqlConnection.getInstance().getConnection(); 
				PreparedStatement cmd = conn.prepareStatement(sql);){	
			
			cmd.setInt(1, id);
			ResultSet result = cmd.executeQuery();
			
			if(result.next()) {
				Author auth = AuthorDAO.getInstance().getById(result.getInt("authId"));
				Publisher pub = PublisherDAO.getInstance().getById(result.getInt("pubId"));
				
				Book book = new Book(result.getInt("bookId"),
						result.getString("title"),
						auth,
						pub);
				result.close();
				return book;
			}
			else {			
				result.close();
				return new Book();
			}
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());
			return new Book();
		}	
		
	}
	
	
}
