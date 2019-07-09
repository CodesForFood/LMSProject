package com.smoothstack.dao;

import java.sql.*;

import com.smoothstack.main.UI;
import com.smoothstack.models.LibraryBranch;
import com.smoothstack.service.BranchService;

public class LibraryBranchDAO {
	
	private static LibraryBranchDAO branchDAO; 
	
	private LibraryBranchDAO() {
		
	}
		
	public static LibraryBranchDAO getInstance() {
		return branchDAO == null ? branchDAO = new LibraryBranchDAO() : branchDAO;
	}
	

	public void selectAll() {
		try(Connection conn = SqlConnection.getInstance().getConnection(); 
				Statement cmd = conn.createStatement()){
			ResultSet result = cmd.executeQuery("SELECT * FROM tbl_library_branch;");
			
			BranchService.branchList.clear();
			
			while(result.next()) {
				LibraryBranch branch = new LibraryBranch(result.getInt("branchId"),
						result.getString("branchName"), 
						result.getString("branchAddress"));
				
				BranchService.branchList.add(branch);			
			}			
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());
		}	
	}
	
	
	public void addBranch(LibraryBranch branch) {
		if(branch == null) {
			UI.say("Branch object is null, in addBranch");
			return;
		}		
		String sql = "INSERT INTO tbl_library_branch (branchName, branchAddress) VALUES (? , ?);";
		
		try(Connection conn = SqlConnection.getInstance().getConnection(); 
				PreparedStatement cmd = conn.prepareStatement(sql);){			
			cmd.setString(1, branch.getName());
			cmd.setString(2, branch.getAddress());
			
			cmd.execute();		
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());
		}	
	}
	
	public void updateBranch(LibraryBranch branch) {
		if(branch == null) {
			UI.say("Branch object is null, in updateBranch");
			return;
		}		
		String sql = "UPDATE tbl_library_branch SET branchName = ?, branchAddress = ? WHERE branchId = ?";
		
		try(Connection conn = SqlConnection.getInstance().getConnection(); 
				PreparedStatement cmd = conn.prepareStatement(sql);){			
			cmd.setString(1, branch.getName());
			cmd.setString(2, branch.getAddress());
			cmd.setInt(3, branch.getId());
			
			cmd.execute();		
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());
		}	
	}
	
	public void deleteBranch(LibraryBranch branch) {
		if(branch == null) {
			UI.say("Branch object is null, in updateBranch");
			return;
		}		
		String sql = "DELETE FROM tbl_library_branch WHERE branchId = ?";
		
		try(Connection conn = SqlConnection.getInstance().getConnection(); 
				PreparedStatement cmd = conn.prepareStatement(sql);){
			
			cmd.setInt(1, branch.getId());			
			cmd.execute();		
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());
		}	
	}
	
	public LibraryBranch getById(int id) {
		String sql = "SELECT * FROM tbl_library_branch WHERE branchId = ?";
		
		try(Connection conn = SqlConnection.getInstance().getConnection(); 
				PreparedStatement cmd = conn.prepareStatement(sql);){	
			
			cmd.setInt(1, id);
			ResultSet result = cmd.executeQuery();
			
			if(result.next()) {
				LibraryBranch branch = new LibraryBranch(result.getInt("branchId"),
						result.getString("branchName"), 
						result.getString("branchAddress"));
				return branch;
			}
			else {				
				return new LibraryBranch();
			}
		}
		catch(Exception ex) {
			UI.say(ex.getMessage());
			return new LibraryBranch();
		}	
		
	}

}
