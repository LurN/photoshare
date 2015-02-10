package com.photoshare.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String username = request.getParameter("username");
		String email = request.getParameter("useremail");
		String firstName = request.getParameter("firstname");
		String lastPass = request.getParameter("lastname");
		String stringBirthDate = request.getParameter("birthdate");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//surround below line with try catch block as below code throws checked exception
		try {
			Date birthDate = sdf.parse(stringBirthDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HttpSession session = request.getSession(false);

		if (session != null)
			session.setAttribute("name", username);



		out.close();
	}
}