package com.smoothstack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.smoothstack.main.UI;
import com.smoothstack.models.Borrower;

public class BorrowerDAO {

	
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
	
	
}
