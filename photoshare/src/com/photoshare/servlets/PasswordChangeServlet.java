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

public class PasswordChangeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AccountDao ldao;

	
	public PasswordChangeServlet(){
		ldao = new AccountDao();
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		String userName = (String) session.getAttribute("username");
		String oldPassword = request.getParameter("oldpassword");
		String oldPassworddb = ldao.getPassword(userName);
		String newPassword = request.getParameter("newpassword");
		
		if(!oldPassword.equals(oldPassworddb)) {
			response.sendRedirect("userSettings.jsp");
			out.print("<p style=\"color:red\">Incorrect Password.</p>");
		} else
		
		if(ldao.modifyPassword(userName, newPassword)) {
			response.sendRedirect("userSettings.jsp");
		} else {
			out.print("<p style=\"color:red\">Account not modified.</p>");
			RequestDispatcher rd = request.getRequestDispatcher("userSettings.jsp");
			rd.include(request, response);
		}
		
		out.close();
	}
}