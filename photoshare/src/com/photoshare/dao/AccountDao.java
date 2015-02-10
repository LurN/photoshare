package com.photoshare.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.photoshare.utilities.DatabaseUtils;

public class AccountDao {
	
	private Connection conn;
	
	
	public AccountDao(){
		conn = DatabaseUtils.getConnection();
		
	}
	
	public boolean validate(String name, String pass) {
		boolean status = false;
		
		PreparedStatement pst = null;
		ResultSet rs = null;

	
		try {
			pst = conn.prepareStatement("select * from account where username=? and password=?");
			pst.setString(1, name);
			pst.setString(2, pass);

			rs = pst.executeQuery();
			status = rs.next();

		} catch (Exception e) {
			System.out.println(e);
		
		}
		return status;
	}//end validate

	public boolean createAccount(String name, String pass, String email) {
		boolean status = false;
		
		PreparedStatement pstCheck = null;
		PreparedStatement pstAdd = null;
		ResultSet rsCheck = null;
		int rsAdd = 0;

		

		try {
			
			pstCheck = conn.prepareStatement("select * from account where username=?");
			pstCheck.setString(1, name);
			

			rsCheck = pstCheck.executeQuery();
			status = rsCheck.next();
			
			if(!status)
				pstAdd = conn.prepareStatement("INSERT INTO `form`.`account` (`accountCreationDate`, `username`, `password`, `email`)"
						+ "VALUES (?, ?, ?, ?)");
				pstAdd.setString(1, new String(new Date(System.currentTimeMillis()).toString()));
				pstAdd.setString(2, name);
				pstAdd.setString(3, pass);
				pstAdd.setString(4, email);
				
				rsAdd = pstAdd.executeUpdate();
				if(rsAdd != 1){
					status = false;
				}else{
					status = true;
				}
				
		} catch (Exception e) {
			System.out.print(e);
			}

		return status;
	}//createAccount end
}