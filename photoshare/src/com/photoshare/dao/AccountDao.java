package com.photoshare.dao;

import java.sql.Connection;

import java.util.Random;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.photoshare.utilities.DatabaseUtils;

public class AccountDao {
	
	private Connection conn;
	private Random rand;
	
	public AccountDao(){
		conn = DatabaseUtils.getConnection();
		rand = new Random();
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
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return status;
	}

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
				pstAdd = conn.prepareStatement("INSERT INTO `form`.`account` (`idaccount`, `username`, `password`, `email`)"
						+ "VALUES (?, ?, ?, ?)");
				pstAdd.setString(1, Integer.toString(rand.nextInt(5000)));
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
			System.out.println(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstCheck != null) {
				try {
					pstCheck.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rsCheck != null) {
				try {
					rsCheck.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstAdd != null) {
				try {
					pstCheck.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}

		return status;
	}
}