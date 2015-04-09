package com.photoshare.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.lang.String;

import com.photoshare.utilities.DatabaseUtils;

public class AccountDao {

	private Connection conn;

	public AccountDao() {
		conn = DatabaseUtils.getConnection();

	}

	public boolean validate(String name, String pass) {
		boolean status = false;

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = conn
					.prepareStatement("select * from account where username=? and password=?");
			pst.setString(1, name);
			pst.setString(2, pass);

			rs = pst.executeQuery();
			status = rs.next();

		} catch (Exception e) {
			System.out.println(e);

		}
		return status;
	}// end validate

	/**
	 * @param userName, email
	 * @author Fred
	 */
	public boolean modifyAccount(String userName, String email,
			String firstName, String lastName, java.sql.Date birthDate) {
		boolean status = false;

		int rsModify = 0;
		PreparedStatement pstModify = null;

		try {

			if (accountExists(userName)) {
				pstModify = conn
						.prepareStatement("UPDATE form.account SET email =?, firstName=?, lastName=?, birthDate=? WHERE username = ?");
				pstModify.setString(1, email);
				pstModify.setString(2, firstName);
				pstModify.setString(3, lastName);
				pstModify.setDate(4, birthDate);
				pstModify.setString(5, userName);

				rsModify = pstModify.executeUpdate();
				if (rsModify != 1) {
					status = false;
				} else {
					status = true;
				}
			}

		} catch (Exception e) {
			System.out.print(e);
		}
		return status;

	}
	
	public boolean modifyPassword(String userName, String password) {
		boolean status = false;

		int rsModify = 0;
		PreparedStatement pstModify = null;

		try {

			if (accountExists(userName)) {
				pstModify = conn
						.prepareStatement("UPDATE form.account SET password =? WHERE username = ?");
				pstModify.setString(1, password);
				pstModify.setString(2, userName);

				rsModify = pstModify.executeUpdate();
				if (rsModify != 1) {
					status = false;
				} else {
					status = true;
				}
			}

		} catch (Exception e) {
			System.out.print(e);
		}
		return status;

	}

	public boolean createAccount(String userName, String password, String email) {
		boolean status = false;

		PreparedStatement pstCheck = null;
		PreparedStatement pstAdd = null;
		ResultSet rsCheck = null;
		int rsAdd = 0;

		try {

			if (!accountExists(userName))
				pstAdd = conn
						.prepareStatement("INSERT INTO `form`.`account` (`accountCreationDate`, `username`, `password`, `email`)"
								+ "VALUES (?, ?, ?, ?)");
			pstAdd.setString(1,
					new String(new Date(System.currentTimeMillis()).toString()));
			pstAdd.setString(2, userName);
			pstAdd.setString(3, password);
			pstAdd.setString(4, email);

			rsAdd = pstAdd.executeUpdate();
			if (rsAdd != 1) {
				status = false;
			} else {
				status = true;
			}

		} catch (Exception e) {
			System.out.print(e);
		}

		return status;
	}// createAccount end

	/**
	 * @param userName
	 * @author Fred
	 */
	public boolean accountExists(String userName) {
		PreparedStatement pstCheck = null;
		ResultSet rsCheck = null;

		try {
			pstCheck = conn
					.prepareStatement("select * from form.account where username=?");

			pstCheck.setString(1, userName);

			rsCheck = pstCheck.executeQuery();
			return rsCheck.next();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public int getAccountId(String userName) {
		int id = 0;
		PreparedStatement getId = null;
		ResultSet rsId = null;

		try {
			getId = conn
					.prepareStatement("select idaccount from account where username=?");
			getId.setString(1, userName);
			rsId = getId.executeQuery();
			
			// Set pointer of rs.
			if(rsId.first()) {
				id = rsId.getInt(1);
				return id;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;

	}

	/**
	 * @param userName
	 * @author Fred
	 */
	public String getFirstName(String userName) {
		String firstName = null;
		PreparedStatement getFirstName = null;
		ResultSet rs = null;

		try {
			getFirstName = conn
					.prepareStatement("select firstName from account where username=?");
			getFirstName.setString(1, userName);

			rs = getFirstName.executeQuery();
			// Set pointer of rs.
			if(rs.first()) {
				firstName = rs.getString(1);
				return firstName;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * @param userName
	 * @author Fred
	 */
	public String getLastName(String userName) {
		String lastName = null;
		PreparedStatement getLastName = null;
		ResultSet rs = null;

		try {
			getLastName = conn
					.prepareStatement("select lastName from account where username=?");
			getLastName.setString(1, userName);

			rs = getLastName.executeQuery();
			// Set pointer of rs.
			if(rs.first()) {
				lastName = rs.getString(1);
				return lastName;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * @param userName
	 * @author Fred
	 */
	public String getEmail(String userName) {
		String email = null;
		PreparedStatement getEmail = null;
		ResultSet rs = null;

		try {
			getEmail = conn
					.prepareStatement("select email from account where username=?");
			getEmail.setString(1, userName);

			rs = getEmail.executeQuery();
			// Set pointer of rs.
			if(rs.first()) {
				email = rs.getString(1);
				return email;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * Returns String representing user's birth date. Returns null on failure.
	 * 
	 * @param userName
	 * @author Fred
	 * @throws ParseException 
	 */
	public String getBirthDate(String userName) throws ParseException {
		java.sql.Date birthDate = null;
		PreparedStatement getBirthDate = null;
		ResultSet rs = null;

		try {
			getBirthDate = conn
					.prepareStatement("select birthDate from form.account where username=?");
			getBirthDate.setString(1, userName);
			

			rs = getBirthDate.executeQuery();
			// Set pointer of rs.
			if(rs.first()) {
				birthDate = rs.getDate(1);
				if(birthDate != null)
					return new SimpleDateFormat("yyyy-MM-dd").format(birthDate).toString();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String getPassword(String userName) {
		String password = null;
		PreparedStatement getPassword = null;
		ResultSet rs = null;

		try {
			getPassword = conn
					.prepareStatement("select password from form.account where username=?");
			getPassword.setString(1, userName);
			

			rs = getPassword.executeQuery();
			// Set pointer of rs.
			if(rs.first()) {
				password = rs.getString(1);
				return password;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public ArrayList<String> getAccountPics(String name){
		
		ArrayList<String> listOfPics = new ArrayList<String>();
		PreparedStatement getPics = null;
		ResultSet rsPics = null;
		
		try {
			getPics = conn.prepareStatement("SELECT location FROM `form`.`photos` WHERE `photos`.`userID` = ?"); // AND `photos`.`isDeleted` <> true
			getPics.setString(1, String.valueOf(getAccountId(name)));
			
			rsPics = getPics.executeQuery();
			
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
	
	public ArrayList<String> getAllPics()
	{
		ArrayList<String> listOfPics = new ArrayList<String>();
		PreparedStatement getPics = null;
		ResultSet rsPics = null;
		
		try {
			getPics = conn.prepareStatement("SELECT location FROM `form`.`photos`");
			
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
	
	/* Delete an existing photo. Sets isDeleted flag, image and database entry remain. */
	public void deletePhoto(String filename, String username) {
		/* Invalid parameters */
		if(filename == null || username == null)
			return;
		
		PreparedStatement deleteQuery = null;
		
		try {
			deleteQuery = conn.prepareStatement("UPDATE `form`.`photos` SET `isDeleted`=true WHERE `photos`.`userID` = ?");
			deleteQuery.setString(1, String.valueOf(getAccountId(username)));
			
			deleteQuery.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}	