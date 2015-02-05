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

		String name = request.getParameter("username");
		String pass = request.getParameter("userpass");
		String email = request.getParameter("useremail");

		HttpSession session = request.getSession(false);

		if (session != null)
			session.setAttribute("name", name);

		// Valid username/password
		if(ldao.createAccount(name, pass, email)) {
			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
		} else {
			// Invalid username/password
			out.print("<p style=\"color:red\">Account already exists.</p>");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.include(request, response);
		}

		out.close();
	}
}