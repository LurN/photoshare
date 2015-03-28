<%@ include file="header.jsp"%>

<link rel="stylesheet" type="text/css" href="./css/modifyPhoto.css">

<!---start-content---->
<div class="content">
			
	<div class="wrap">
		<div id="main" role="main">
			<%@ page import="com.photoshare.dao.AccountDao, java.util.ArrayList"%>
			<%
				AccountDao ldao = new AccountDao();
			
				ArrayList<String> listOfPics = ldao.getAccountPics(session
						.getAttribute("name").toString());
			%>
			<ul id="tiles">
				<%
					// Give unique IDs to each image
					int i = 0;
					for (String path : listOfPics) {
						System.out.println(path);
						out.println("<li class=\"photo\" id=\"photo" + ++i + "\"><img src=../.."+path.substring(54)+" width=100% height=auto /></li>");
					}
				%>
				
				<!-- End of grid blocks -->
			</ul>
		</div>
	</div>
</div>
<!---//End-content---->
<%@ include file="footer.jsp"%>