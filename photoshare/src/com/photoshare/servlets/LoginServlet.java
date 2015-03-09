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
//import com.photoshare.models.Account;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AccountDao ldao;
	
	
	public LoginServlet(){
		ldao = new AccountDao();
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String userName = request.getParameter("username");
		String pass = request.getParameter("password");
		

		HttpSession session = request.getSession(false);

		if (session != null)
			session.setAttribute("name", userName);


		// Valid username/password
		if(ldao.validate(userName, pass)) {
			response.sendRedirect("home.jsp");
			
		} else {
			// Invalid username/password
			out.print("<p style=\"color:red\">Incorrect username or password.</p>");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.include(request, response);
		}
		session.setAttribute("email", ldao.getEmail(userName));
		session.setAttribute("firstName", ldao.getFirstName(userName));
		session.setAttribute("lastName", ldao.getLastName(userName));
		try {
			System.out.println(ldao.getBirthDate(userName));
			session.setAttribute("birthDate", ldao.getBirthDate(userName));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			

		out.close();
	}
}