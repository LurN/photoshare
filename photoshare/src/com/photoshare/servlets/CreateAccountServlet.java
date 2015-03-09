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

public class CreateAccountServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AccountDao ldao;

	
	public CreateAccountServlet(){
		ldao = new AccountDao();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String userName = request.getParameter("usernamesignup");
		String pass = request.getParameter("passwordsignup");
		String email = request.getParameter("emailsignup");

		HttpSession session = request.getSession(false);

		if (session != null) 
			session.setAttribute("name", userName);
		

		// Valid username/password
		if(ldao.createAccount(userName, pass, email)) {
			response.sendRedirect("home.jsp");
		} else {
			// Invalid username/password
			out.print("<p style=\"color:red\">Account already exists.</p>");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.include(request, response);
		}
		session.setAttribute("id", ldao.getAccountId(userName));
		session.setAttribute("email", ldao.getEmail(userName));

		out.close();
	}
}