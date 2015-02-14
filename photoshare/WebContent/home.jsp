<%@ include file="header.jsp"%>
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