/**
 * File: LoginServlet.java
 * 
 * Description: Servlet used to login.
 */

package com.photoshare.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.photoshare.dao.AccountDao;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AccountDao ldao;
	
	
	public LoginServlet(){
		ldao = new AccountDao();
	}
	
	@Override
	/**
	 * Post called to login user.
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// Get parameters from Post
		String userName = request.getParameter("username");
		String pass = request.getParameter("password");

		HttpSession session = request.getSession(false);

		if (session != null)
			session.setAttribute("name", userName);

		// Valid username/password
		if(ldao.validate(userName, pass)) {
			response.sendRedirect("home.jsp");
			if(session != null) {
				session.setAttribute("username", userName);
				session.setAttribute("email", ldao.getEmail(userName));
				session.setAttribute("firstName", ldao.getFirstName(userName));
				session.setAttribute("lastName", ldao.getLastName(userName));
				session.setAttribute("id", ldao.getAccountId(userName));
				
				try {
					session.setAttribute("birthDate", ldao.getBirthDate(userName));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
			}
		} else {
			// Invalid username/password
			out.print("<p><fmt:message key=\"error.message.incorrect\" /></p>");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.include(request, response);
			response.sendRedirect("home.jsp");
		}
		
		out.close();
	}
}
