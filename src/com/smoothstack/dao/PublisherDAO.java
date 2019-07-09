package com.smoothstack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.smoothstack.main.UI;
import com.smoothstack.models.Publisher;
import com.smoothstack.service.PublisherService;

public class PublisherDAO {

	
	private static PublisherDAO pubDAO; 
	
	private PublisherDAO() {
		
	}
		
	public static PublisherDAO getInstance() {
		return pubDAO == null ? pubDAO = new PublisherDAO() : pubDAO;
	}
	

	public void selectAll() {
		try(Connection conn = SqlConnection.getInstance().getConnection(); 
				Statement cmd = conn.createStatement()){
			ResultSet result = cmd.executeQuery("SELECT * FROM tbl_publisher;");
			
			PublisherService.pubList.clear();
			
			while(result.next()) {
				Publisher pub = new Publisher(result.getInt("publisherId"),
						result.getString("publisherName"), 
						result.getString("publisherAddress"),
						result.getString("publisherPhone"));
				
				PublisherService.pubList.add(pub);			
			}
			
			result.close();
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());
		}	
	}
	
	
	public void addPublisher(Publisher pub) {
		if(pub == null) {
			UI.say("Publisher object is null, in addPublisher");
			return;
		}		
		String sql = "INSERT INTO tbl_publisher (publisherName, publisherAddress, publisherPhone) VALUES (?, ?, ?);";
		
		try(Connection conn = SqlConnection.getInstance().getConnection(); 
				PreparedStatement cmd = conn.prepareStatement(sql);){			
			cmd.setString(1, pub.getName());
			cmd.setString(2, pub.getAddress());
			cmd.setString(3, pub.getPhone());
			
			cmd.execute();		
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());
		}	
	}
	
	public void updatePublisher(Publisher pub) {
		if(pub == null) {
			UI.say("Publisher object is null, in updatePublisher");
			return;
		}		
		String sql = "UPDATE tbl_publisher SET publisherName = ?, publisherAddress = ?, publisherPhone WHERE publisherId = ?";
		
		try(Connection conn = SqlConnection.getInstance().getConnection(); 
				PreparedStatement cmd = conn.prepareStatement(sql);){			
			cmd.setString(1, pub.getName());
			cmd.setString(2, pub.getAddress());
			cmd.setString(3, pub.getPhone());
			cmd.setInt(4, pub.getId());
			
			cmd.execute();		
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());
		}	
	}
	
	public void deletePublisher(Publisher pub) {
		if(pub == null) {
			UI.say("Publisher object is null, in updatePublisher");
			return;
		}		
		String sql = "DELETE FROM tbl_publisher WHERE publisherId = ?";
		
		try(Connection conn = SqlConnection.getInstance().getConnection(); 
				PreparedStatement cmd = conn.prepareStatement(sql);){
			
			cmd.setInt(1, pub.getId());			
			cmd.execute();		
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());
		}	
	}
	
	public Publisher getById(int id) {
		String sql = "SELECT * FROM tbl_publisher WHERE publisherId = ?";
		
		try(Connection conn = SqlConnection.getInstance().getConnection(); 
				PreparedStatement cmd = conn.prepareStatement(sql);){	
			
			cmd.setInt(1, id);
			ResultSet result = cmd.executeQuery();
			
			if(result.next()) {
				Publisher pub = new Publisher(result.getInt("publisherId"),
						result.getString("publisherName"), 
						result.getString("publisherAddress"),
						result.getString("publisherPhone"));
				result.close();
				return pub;
			}
			else {				
				result.close();
				return new Publisher();
			}
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());
			return new Publisher();
		}	
		
	}
	
	public int getHighestIndex() {		
		String sql = "SELECT publisher FROM tbl_publisher ORDER BY authorId DESC LIMIT 1";
		
		try(Connection conn = SqlConnection.getInstance().getConnection(); 
				PreparedStatement cmd = conn.prepareStatement(sql);){
			
			ResultSet result = cmd.executeQuery();
			if(result.next()) {
				int highest = result.getInt("publisherId");
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
