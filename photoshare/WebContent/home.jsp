<%@ include file="header.jsp"%>

<link rel="stylesheet" type="text/css" href="./css/modifyPhoto.css">

<!---start-content---->
<div class="content">
			
	<div class="wrap">
		<div id="main" role="main">
			<%@ page import="com.photoshare.dao.AccountDao, java.util.ArrayList"%>
			<%
				AccountDao ldao = new AccountDao();
			
				ArrayList<String> listOfPics = ldao.getAllPics();
			%>
			<ul id="tiles">
				<%
					// Give unique IDs to each image
					int i = 0;
					for (String path : listOfPics) {
						String sub = path.substring(path.indexOf("Photos\\"));
						
						out.println("<li class=\"photo\" name=\"" + sub.substring(7) + "\" id=\"photo" + ++i + "\"><img src=http://localhost:8080/photoshare/"+sub+" width=100% height=auto /></li>");
					}
				%>
				
				<!-- End of grid blocks -->
			</ul>
		</div>
	</div>
</div>
<!---//End-content---->
<%@ include file="footer.jsp"%>