<%@ include file="header.jsp"%>
<!---start-content---->
<div class="content">
	<div class="wrap">
		<div id="main" role="main">
			<div id="updateFields" class="animate form">
                            <form  action="loginServlet" autocomplete="on" method="post"> 
                                <h1>Account Information</h1> 
                                <p> 
                                    <label for="username" class="uname" data-icon="u" > Your email or username </label>
                                    <input id="username" name="username" required="required" type="text" value="<%=session.getAttribute("name")%>"/>
                                </p>
                                <p> 
                                    <label for="password" class="youpasswd" data-icon="p"> Your password </label>
                                    <input id="password" name="password" required="required" type="password" value="<%=session.getAttribute("password")%>" /> 
                                </p>
   
                                <p class="Update button"> 
                                    <input type="submit" value="Update" /> 
								</p>
                            </form>
                        </div>
		</div>
	</div>
</div>
<!---//End-content---->
<%@ include file="footer.jsp"%>