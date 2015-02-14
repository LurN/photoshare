package com.photoshare.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.lang.String;

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

	public int getAccountId(String name){
		int id = 0;
		PreparedStatement getId = null;
		ResultSet rsId = null;
		
		try {
			getId = conn.prepareStatement("select idaccount from account where username=?");
			getId.setString(1, name);
			
			rsId = getId.executeQuery();
			System.out.println(rsId.first());
			id = rsId.getInt(1);
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return id;
		
	}

	public ArrayList<String> getAccountPics(String name){
		
		ArrayList<String> listOfPics = new ArrayList<String>();
		PreparedStatement getPics = null;
		ResultSet rsPics = null;
		
		try {
			getPics = conn.prepareStatement("SELECT location FROM `form`.`photos` WHERE `photos`.`userID` = ?");
			getPics.setString(1, String.valueOf(getAccountId(name)));
			
			rsPics = getPics.executeQuery();
			System.out.println(rsPics);
			
			while(rsPics.next())
			{
				listOfPics.add(rsPics.getString("location"));
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listOfPics;
		
		
	}
}	
		
	