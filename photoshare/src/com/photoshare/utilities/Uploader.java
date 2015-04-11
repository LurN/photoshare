/**
 * File: Uploader.java
 * 
 * Description: Allows uploading an image to the server and adding it to the database.
 */

package com.photoshare.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import com.sun.jndi.toolkit.url.Uri;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/FileUpload")
@MultipartConfig
public class Uploader extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn = DatabaseUtils.getConnection();
	
	/**
	 * Upload an image to the server.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();     

        // For each part, upload the photo and add to database
        for (Part part : request.getParts()) {
            String name = part.getName();
            String contentType = part.getContentType();
            
            out.println(contentType);
            
            // Wrong image types
            if(!contentType.equals("image/png")) {
                out.println("Only png format supported!");
                break;
            }

            InputStream is = request.getPart(name).getInputStream();
            
            File uploadDir = new File("C:\\Users\\Andrew\\git\\photoshare\\photoshare\\WebContent\\Photos\\");
            System.out.println("UploadDir:"+uploadDir);
            File file = File.createTempFile("img", ".PNG", uploadDir);

            FileOutputStream fos = new FileOutputStream(file);

            int data = 0;
            while ((data = is.read()) != -1) {
                fos.write(data);
            }
            
            String path = file.getAbsolutePath();
            fos.close();
            
            PreparedStatement pst = null;
    		
            // Add entry to database
    		try {
    			pst = conn.prepareStatement("INSERT into form.photos (userID, location) VALUES (?,?)");
    			pst.setString(1, request.getSession().getAttribute("id").toString());
    			pst.setString(2, path);
    			pst.executeUpdate();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }
        
        // Send the user to the home page
        response.sendRedirect("home.jsp");
    }
}