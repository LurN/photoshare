<!-- Call delete photo doPost to delete a photo -->
<%@page import="com.photoshare.servlets.DeletePhotoServlet"%>
<%
	// Delete the desired photo
	DeletePhotoServlet dps = new DeletePhotoServlet();
	dps.doGet(request, response);
%>