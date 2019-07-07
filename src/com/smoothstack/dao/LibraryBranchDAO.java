package com.smoothstack.dao;

import java.sql.*;

public class LibraryBranchDAO {
	
	
	
	
	public void getBranchInfo() {
		try(Connection conn = SqlConnection.getInstance().getConnection(); 
				Statement cmd = conn.createStatement()){
			ResultSet result = cmd.executeQuery("");
		}
		catch(Exception ex) {
			
		}
	}
	
	

}
