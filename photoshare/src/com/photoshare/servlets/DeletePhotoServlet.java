/**
 * File: DeletePhotoServlet.java
 * 
 * Description: Facilitates deletion of images within the database.
 */

package com.photoshare.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.photoshare.utilities.DatabaseUtils;

public class DeletePhotoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private Connection conn = DatabaseUtils.getConnection();

	/**
	 * Set isDeleted flag to true for requested photo.
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Get photo title
		String photoId = request.getParameter("photo");
		
		PreparedStatement deletePhoto = null;
		
		try {
			// Find photo in database with wildcards and set flag
			deletePhoto = conn.prepareStatement("UPDATE form.photos SET isDeleted = true WHERE location LIKE ?");
			deletePhoto.setString(1, "%" + photoId + "%");
			deletePhoto.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}
}
