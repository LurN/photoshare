/**
 * File: PasswordChangeServlet.java
 * 
 * Description: Changes a users password.
 */

package com.photoshare.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.photoshare.dao.AccountDao;

public class PasswordChangeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AccountDao ldao;

	
	public PasswordChangeServlet(){
		ldao = new AccountDao();
	}
	
	@Override
	/**
	 * 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		
		// Get parameters from user request
		String userName = (String) session.getAttribute("username");
		String oldPassword = request.getParameter("oldpassword");
		String oldPassworddb = ldao.getPassword(userName);
		String newPassword = request.getParameter("newpassword");
		String confirmOldPassword = request.getParameter("confnewpassword");
		
		// Invalid old password
		if(!newPassword.equals(confirmOldPassword)) {
			response.sendRedirect("userSettings.jsp");
			out.print("<div id=\"main\" role=\"main\" style=\"color:red\"><fmt:message key=\"error.message.confirm.password\" /></div>");
		}
		
		// Invalid, new password is same as old
		if(!oldPassword.equals(oldPassworddb)) {
			response.sendRedirect("userSettings.jsp");
			out.print("<div id=\"main\" role=\"main\" style=\"color:red\"><fmt:message key=\"error.message.incorrect.password\" /></div>");
		} else if(ldao.modifyPassword(userName, newPassword)) { // Modify password and refresh page
			response.sendRedirect("userSettings.jsp");
		} else { // Password unchanged
			out.print("<div id=\"main\" role=\"main\" style=\"color:red\"><fmt:message key=\"error.message.account.not.modified\" /></div>");
			RequestDispatcher rd = request.getRequestDispatcher("userSettings.jsp");
			rd.include(request, response);
		}
		
		out.close();
	}
}