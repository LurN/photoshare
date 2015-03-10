package com.photoshare.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.photoshare.dao.AccountDao;
//import com.photoshare.models.Account;

public class UpdateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AccountDao ldao;

	
	public UpdateServlet(){
		ldao = new AccountDao();
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		java.util.Date birthDate = null;
		java.sql.Date sqlDate = null;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String userName = request.getParameter("username");
		String email = request.getParameter("useremail");
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String stringBirthDate = request.getParameter("birthdate");
		
//surround below line with try catch block as below code throws checked exception
		try {
			birthDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(stringBirthDate);
			sqlDate = new java.sql.Date(birthDate.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HttpSession session = request.getSession(false);
		
		if(ldao.modifyAccount(userName, email, firstName, lastName, sqlDate)) {
			response.sendRedirect("userSettings.jsp");
		} else {
			out.print("<p style=\"color:red\">Account not modify.</p>");
			RequestDispatcher rd = request.getRequestDispatcher("userSettings.jsp");
			rd.include(request, response);
		}
		
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