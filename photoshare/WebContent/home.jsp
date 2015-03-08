<%@ include file="header.jsp"%>

<script src="./js/modifyPhoto.js"></script>

<link rel="stylesheet" type="text/css" href="./css/modifyPhoto.css">

<!---start-content---->
<div class="content">
			
	<div id="contextMenu">
		<form action="#openModal">
			<input class="contextMenuItem" type="button" value="Add photo..."/><br />
		</form>
		<form action="#deleteModal">
			<input class="contextMenuItem" type="button" value="Delete"/>
		</form>
	</div>
			
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
					for (String path : listOfPics) {
						out.println("<li><img src="+path+" width=400px height=400px /></li>");
					}
				%>
				
				<!-- End of grid blocks -->
			</ul>
		</div>
	</div>
</div>
<!---//End-content---->
<%@ include file="footer.jsp"%>