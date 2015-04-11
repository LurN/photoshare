/**
 * File: UpdateServlet.java
 * 
 * Description: Update user settings information.
 */

package com.photoshare.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.photoshare.dao.AccountDao;

public class UpdateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AccountDao ldao;

	
	public UpdateServlet(){
		ldao = new AccountDao();
	}
	
	@Override
	/**
	 * Update user information based on user-specified parameters.
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		java.util.Date birthDate = null;
		java.sql.Date sqlDate = null;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// Get new user information from parameters
		String userName = request.getParameter("username");
		String email = request.getParameter("useremail");
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String stringBirthDate = request.getParameter("birthdate");
		
		try {
			birthDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(stringBirthDate);
			sqlDate = new java.sql.Date(birthDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		HttpSession session = request.getSession(false);
		
		// Successful update, refresh page
		if(ldao.modifyAccount(userName, email, firstName, lastName, sqlDate)) {
			response.sendRedirect("userSettings.jsp");
		} else {
			// Unsuccessful update, not modified
			out.print("<div id=\"main\" role=\"main\" style=\"color:red\"><fmt:message key=\"error.message.account.not.modified\" /></div>");
			RequestDispatcher rd = request.getRequestDispatcher("userSettings.jsp");
			rd.include(request, response);
		}
		
		// Set session attributes
		session.setAttribute("name", userName);
		session.setAttribute("email", ldao.getEmail(userName));
		session.setAttribute("firstName", ldao.getFirstName(userName));
		session.setAttribute("lastName", ldao.getLastName(userName));
		
		try {
			session.setAttribute("birthDate", ldao.getBirthDate(userName));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		out.close();
	}
}