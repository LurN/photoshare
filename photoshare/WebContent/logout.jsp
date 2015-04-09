<!-- User is logged out by visiting this page, then redirected to login.jsp -->
<%
	if(session != null)
	    session.invalidate();

	response.sendRedirect("login.jsp");
%>