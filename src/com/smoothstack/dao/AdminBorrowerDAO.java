package com.smoothstack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.smoothstack.main.UI;
import com.smoothstack.models.Borrower;
import com.smoothstack.service.AdminBorrowerService;


public class AdminBorrowerDAO {	
	
	private static AdminBorrowerDAO borrDAO; 
	
	private AdminBorrowerDAO() {
		
	}
		
	public static AdminBorrowerDAO getInstance() {
		return borrDAO == null ? borrDAO = new AdminBorrowerDAO() : borrDAO;
	}
	

	public void selectAll() {
		try(Connection conn = SqlConnection.getInstance().getConnection(); 
				Statement cmd = conn.createStatement()){
			ResultSet result = cmd.executeQuery("SELECT * FROM tbl_borrower;");
			
			AdminBorrowerService.borrList.clear();
			
			while(result.next()) {
				Borrower borr = new Borrower(result.getInt("cardNo"),
						result.getString("name"), 
						result.getString("address"),
						result.getString("phone"));
				
				AdminBorrowerService.borrList.add(borr);			
			}	
			result.close();
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());
		}	
	}
	
	
	public void addBorrower(Borrower borr) {
		if(borr == null) {
			UI.say("Borrower object is null, in addBorrower");
			return;
		}		
		String sql = "INSERT INTO tbl_borrower (cardNo, name, address, phone) VALUES (?, ?, ?, ?);";
		
		try(Connection conn = SqlConnection.getInstance().getConnection(); 
				PreparedStatement cmd = conn.prepareStatement(sql);){			
			cmd.setInt(1, borr.getCardNumber());
			cmd.setString(2, borr.getName());
			cmd.setString(3, borr.getAddress());
			cmd.setString(4, borr.getPhone());
			
			cmd.execute();		
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());
		}	
	}
	
	public void updateBorrower(Borrower borr, int oldCardNo) {
		if(borr == null) {
			UI.say("Borrower object is null, in updateBorrower");
			return;
		}		
		String sql = "UPDATE tbl_borrower SET cardNo = ?, name = ?, address = ?, phone = ? WHERE cardNo = ?";
		
		try(Connection conn = SqlConnection.getInstance().getConnection(); 
				PreparedStatement cmd = conn.prepareStatement(sql);){			
			cmd.setInt(1, borr.getCardNumber());
			cmd.setString(2, borr.getName());
			cmd.setString(3, borr.getAddress());
			cmd.setString(4, borr.getPhone());
			cmd.setInt(5, oldCardNo);
			
			cmd.execute();		
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());
		}	
	}
	
	public void deleteBorrower(Borrower borr) {
		if(borr == null) {
			UI.say("Borrower object is null, in Delete Borrower");
			return;
		}		
		String sql = "DELETE FROM tbl_borrower WHERE cardNo = ?";
		
		try(Connection conn = SqlConnection.getInstance().getConnection(); 
				PreparedStatement cmd = conn.prepareStatement(sql);){
			
			cmd.setInt(1, borr.getCardNumber());			
			cmd.execute();		
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());
		}	
	}
	
	public Borrower getById(int id) {
		String sql = "SELECT * FROM tbl_borrower WHERE cardNo = ?";
		
		try(Connection conn = SqlConnection.getInstance().getConnection(); 
				PreparedStatement cmd = conn.prepareStatement(sql);){	
			
			cmd.setInt(1, id);
			ResultSet result = cmd.executeQuery();
			
			if(result.next()) {
				Borrower borr = new Borrower(result.getInt("cardNo"),
						result.getString("name"), 
						result.getString("address"),
						result.getString("phone"));
				result.close();
				return borr;
			}
			else {			
				result.close();
				return new Borrower();
			}
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());
			return new Borrower();
		}	
		
	}

}
