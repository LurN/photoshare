/**
 * File: AccountDao.java
 * 
 * Description: Used to keep track of and set account information.
 */

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

	/**
	 * Verify whether or not a user is valid for login.
	 * @param name username
	 * @param pass user password
	 * @return
	 */
	public boolean validate(String name, String pass) {
		// Status of login
		boolean status = false;

		PreparedStatement pst = null;
		ResultSet rs = null;

		// Look for user in database
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
	 * Modify user account information.
	 * @param userName logged in user
	 * @param email logged in user email
	 * @param firstName new first name of user
	 * @param lastName new last name of user
	 * @return whether or not change was successful
	 * @author Fred
	 */
	public boolean modifyAccount(String userName, String email,
			String firstName, String lastName, java.sql.Date birthDate) {
		// Status of update
		boolean status = false;

		// Status of update query
		int rsModify = 0;
		PreparedStatement pstModify = null;

		// Try to update user information in database
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
	
	/**
	 * Modify a users password.
	 * @param userName username
	 * @param password user password
	 * @return whether or not update was successful
	 */
	public boolean modifyPassword(String userName, String password) {
		// Status of password change
		boolean status = false;

		// Return value of update query
		int rsModify = 0;
		PreparedStatement pstModify = null;

		// Try to update user information
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

	/**
	 * Create a new user.
	 * @param userName username
	 * @param password user password
	 * @param email user email
	 * @return whether or not the account creation was successful
	 */
	public boolean createAccount(String userName, String password, String email) {
		// Status up account creation
		boolean status = false;

		PreparedStatement pstAdd = null;
		int rsAdd = 0;

		try {
			// Determine if user already exists
			if (!accountExists(userName))
				pstAdd = conn
						.prepareStatement("INSERT INTO form.account (accountCreationDate, username, password, email)"
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
	 * Determine whether or not an account exists
	 * @param userName username
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

	/**
	 * Get id of account based on username.
	 * @param userName username
	 * @return id of specified account, 0 on failure
	 */
	public int getAccountId(String userName) {
		PreparedStatement getId = null;
		ResultSet rsId = null;

		try {
			getId = conn
					.prepareStatement("select idaccount from account where username=?");
			getId.setString(1, userName);
			rsId = getId.executeQuery();
			
			// Set pointer of rs.
			if(rsId.first()) {
				int id = rsId.getInt(1);
				return id;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * Get first name of user based on username.
	 * @param userName username
	 * @return first name of user, null on failure
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
	 * Get last name of user based on username.
	 * @param userName
	 * @return last name of user, null on failure
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
	 * Get email of user based on username.
	 * @param userName username
	 * @return user email, null on failure
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
	 * Get date of birth of user based on username.
	 * @param userName username
	 * @return date of birth string of user, null on failure.
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
	
	/**
	 * Get password of user based on username.
	 * @param userName username
	 * @return password of user, null on failure.
	 */
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

	/**
	 * Get an array list of images based on a username.
	 * @param name username
	 * @return array of images belonging to a user
	 */
	public ArrayList<String> getAccountPics(String name) {
		ArrayList<String> listOfPics = new ArrayList<String>();
		PreparedStatement getPics = null;
		ResultSet rsPics = null;
		
		// Get all photos that belong to this user that have not been deleted
		try {
			getPics = conn.prepareStatement("SELECT location FROM form.photos WHERE photos.userID = ? AND photos.isDeleted <> 1");
			getPics.setString(1, String.valueOf(getAccountId(name)));
			
			rsPics = getPics.executeQuery();
			
			// Add location of each image to array list
			while(rsPics.next())
			{
				listOfPics.add(rsPics.getString("location"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listOfPics;
	}
	
	/**
	 * Get all photos in the database that have not been deleted.
	 * @return array list of all photos that have not been deleted
	 */
	public ArrayList<String> getAllPics()
	{
		ArrayList<String> listOfPics = new ArrayList<String>();
		PreparedStatement getPics = null;
		ResultSet rsPics = null;
		
		// Get all images in the database that have not been deleted
		try {
			getPics = conn.prepareStatement("SELECT location FROM form.photos WHERE form.photos.isDeleted <> true");
			
			rsPics = getPics.executeQuery();
			
			// Add location of each image to array list
			while(rsPics.next())
			{
				listOfPics.add(rsPics.getString("location"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listOfPics;
	}
}	