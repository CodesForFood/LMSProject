package com.smoothstack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.smoothstack.main.UI;
import com.smoothstack.models.Author;
import com.smoothstack.service.*;

public class AuthorDAO {

	private static AuthorDAO authorDAO;
	
	private AuthorDAO() {
		
	}
	
	
	public static AuthorDAO getInstance() {
		return authorDAO == null ? authorDAO = new AuthorDAO() : authorDAO;
	}
	
	
	public void selectAll() {
		try(Connection conn = SqlConnection.getInstance().getConnection(); 
				Statement cmd = conn.createStatement()){
			ResultSet result = cmd.executeQuery("SELECT * FROM tbl_author;");
			
			AuthorService.authList.clear();
			
			while(result.next()) {
				Author auth = new Author(result.getInt("authorId"),
							result.getString("authorName"));
				
				AuthorService.authList.add(auth);
			}	
			result.close();
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());
		}	
	}	
	
	public void addAuthor(Author auth) {
		if(auth == null) {
			UI.say("Author object is null, in addAuthor");
			return;
		}		
		String sql = "INSERT INTO tbl_author (authorName) VALUES (?);";
		
		try(Connection conn = SqlConnection.getInstance().getConnection(); 
				PreparedStatement cmd = conn.prepareStatement(sql);){			
			cmd.setString(1, auth.getAuthName());					
			cmd.execute();		
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());
		}	
	}
	
	public void updateAuthor(Author auth) {
		if(auth == null) {
			UI.say("Author object is null, in updateAuthor");
			return;
		}		
		String sql = "UPDATE tbl_author SET authorName = ? WHERE authorId = ?";
		
		try(Connection conn = SqlConnection.getInstance().getConnection(); 
				PreparedStatement cmd = conn.prepareStatement(sql);){			
			cmd.setString(1, auth.getAuthName());			
			cmd.setInt(2, auth.getAuthId());
			
			cmd.execute();		
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());
		}	
	}
	
	public void deleteAuthor(Author auth) {
		if(auth == null) {
			UI.say("Author object is null, in deleteAuthor");
			return;
		}		
		String sql = "DELETE FROM tbl_author WHERE authorId = ?";
		
		try(Connection conn = SqlConnection.getInstance().getConnection(); 
				PreparedStatement cmd = conn.prepareStatement(sql);){
			
			cmd.setInt(1, auth.getAuthId());			
			cmd.execute();		
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());
		}	
	}
	
	public Author getById(int id) {
		String sql = "SELECT * FROM tbl_author WHERE authorId = ?";
		
		try(Connection conn = SqlConnection.getInstance().getConnection(); 
				PreparedStatement cmd = conn.prepareStatement(sql);){
			
			cmd.setInt(1, id);
			ResultSet result = cmd.executeQuery();
			
			if(result.next()) {
				Author auth = new Author(result.getInt("authorId"),
						result.getString("authorName"));
				result.close();
				return auth;
			}
			else {				
				result.close();
				return new Author();
			}
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());
			return new Author();
		}	
		
	}
	
	public int getHighestIndex() {		
		String sql = "SELECT authorId FROM tbl_author ORDER BY authorId DESC LIMIT 1";
		
		try(Connection conn = SqlConnection.getInstance().getConnection(); 
				PreparedStatement cmd = conn.prepareStatement(sql);){
			
			ResultSet result = cmd.executeQuery();
			if(result.next()) {
				int highest = result.getInt("authorId");				
				result.close();								
				return highest;
			}	
			
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());			
		}
		
		return 0;
	}
	
	
	
}
