package com.photoshare.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.Part;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class FileUpload
 */
@WebServlet("/FileUpload")
@MultipartConfig
public class Uploader extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn = DatabaseUtils.getConnection();

	/**
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public Uploader() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();     

        for (Part part : request.getParts()) {
            String name = part.getName();
            String contentType = part.getContentType();
            out.println(contentType);
            
            if(!contentType.equals("image/png")) {
                out.println("Only png format supported!");
                break;
            }

            InputStream is = request.getPart(name).getInputStream();
            File uploadDir = new File("C:\\Users\\Andrew\\git\\photoshare\\photoshare\\WebContent\\Photos");
            File file = File.createTempFile("img", ".PNG", uploadDir);
           

            FileOutputStream fos = new FileOutputStream(file);

            int data = 0;
            while ((data = is.read()) != -1) {
                fos.write(data);
            }

            
            String path = file.getAbsolutePath();
            fos.close();
            
            out.println("File uploaded.");
            System.out.println("Hello ANDREW");
            
            PreparedStatement pst = null;
    		//ResultSet rs = null;
    		
    		try {
    			pst = conn.prepareStatement("INSERT into `form`.`photos` (`userID`, `location`) VALUES (?,?)");
    			pst.setString(1, request.getSession().getAttribute("id").toString());
    			pst.setString(2, path);
    			
    			
    			
    			pst.executeUpdate();
    			

    		} catch (Exception e) {
    			System.out.println(e);
    		
    		}
    		
            
        }
        
        response.sendRedirect("userHome.jsp");
    }
	
	/*
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		PrintWriter out = response.getWriter();
		HttpSession httpSession = request.getSession();
		String filePathUpload = (String) httpSession.getAttribute("path") != null ? httpSession
				.getAttribute("path").toString() : "";

		String path1 = filePathUpload;
		String filename = null;
		File path = null;
		

		 

		if (ServletFileUpload.isMultipartContent(request)) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			
				
			
			String FieldName = "";
			try {
				
				List items = upload.parseRequest(request);
				Iterator iterator = items.iterator();
				while (iterator.hasNext()) {
					FileItem item =  (FileItem) iterator.next();

					if (!item.isFormField()) {
						
						filename = item.getName();
						
						path = new File(path1 + File.separator);
						if (!path.exists()) {
							boolean status = path.mkdirs();
						}
						//START OF CODE FRO PRIVILEDGE 

						File uploadedFile = new File(path + filename); // for
																		// copy
																		// file
						item.write(uploadedFile);
					} else {
						String f1 = item.getName();
					}

				} // END OF WHILE
				response.sendRedirect("welcome.jsp");

			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception ef) {
				ef.printStackTrace();
			}
		}
	}*/

	/*private String getFileName(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String fileName = cd.substring(cd.indexOf('=') + 1).trim()
						.replace("\"", "");
				return fileName.substring(fileName.lastIndexOf('/') + 1)
						.substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
			}
		}
		return null;
	}*/

}
