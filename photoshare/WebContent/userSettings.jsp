<%@ include file="header.jsp"%>
<!---start-content---->
        
<div class="container" >
<div class="content">
			<div class="wrap">
			 <div id="main" role="main">
            <!-- Codrops top bar -->
            <div class="codrops-top">
            </div><!--/ Codrops top bar -->
            <section>
            	<!--  <div class="menu_box_list">
									<ul>
										<li><a href="#userSetting"><span>Account Information</span></a></li>
										<li><a href="#changePassword"><span>Change Password</span></a></li>
									</ul>
									<div class="clear"> </div>
								</div>-->
                <div id="container_demo" >
                    <div id="wrapper">
                        <div id="userSetting" class="animate form">
                            <form  action="userSettings" autocomplete="on" method="post">
                                <h1><fmt:message key="user.settings.account.information" /></h1> 
                                <p> 
                                    <label for="username" class="uname" data-icon="u" ><fmt:message key="user.settings.username" /></label>
                                    <input id="username" name="username" required type="text" value="<%=session.getAttribute("name")%>" readonly/>
                                </p>
                                <p> 
                                    <label for="useremail" class="uemail" data-icon="e" ><fmt:message key="user.settings.email" /></label>
                                    <input id="useremail" name="useremail" required type="text" value="<%=session.getAttribute("email")%>"/>
                                </p>
                                <p> 
                                    <label for="firstname" class="firstname" data-icon="u"><fmt:message key="user.settings.first.name" /></label>
                                    <input id="firstname" name="firstname" required type="text" value="<%=session.getAttribute("firstName")%>" /> 
                                </p>
                                <p> 
                                    <label for="lastname" class="lastname" data-icon="u" ><fmt:message key="user.settings.last.name" /></label>
                                    <input id="lastname" name="lastname" required type="text" value="<%=session.getAttribute("lastName")%>"/>
                                </p>
                                <p> 
                                    <label for="birthdate" class="birthdate" data-icon="u" ><fmt:message key="user.settings.birthdate" /></label>
                                    <input id="birthdate" name="birthdate" required type="date" value="<%=session.getAttribute("birthDate")%>"/>
                                </p>
                                <p class="Update button"> 
                                    <input type="submit" value="<fmt:message key="user.settings.button.update" />" /> 
								</p>
                            </form>
                        </div>
						<div id="userPassword" class="animate form">
                            <form  action="userPassword" autocomplete="on" method="post"> 
                                <h1><fmt:message key="user.settings.change.password" /></h1> 
                                <p> 
                                    <label for="oldpassword" class="oldpassword" data-icon="p" ><fmt:message key="user.settings.oldpass" /></label>
                                    <input id="oldpassword" name="oldpassword" required type="password"/>
                                </p>
                                <p> 
                                    <label for="newpassword" class="newpassword" data-icon="p" ><fmt:message key="user.settings.newpass" /></label>
                                    <input id="newpassword" name="newpassword" required type="password" onchange="form.confnewpassword.pattern = this.value;"/>
                                </p>
                                <p> 
                                    <label for="confnewpassword" class="confnewpassword" data-icon="p"><fmt:message key="user.settings.confirm.newpass" /></label>
                                    <input id="confnewpassword" name="confnewpassword" required pattern title="<fmt:message key="login.warning.required.password" />" type="password" > 
                                </p>
                                <p class="Change Password button"> 
                                    <input type="submit" value="<fmt:message key="user.settings.execute" />" /> 
								</p>
                            </form>
                        </div>
						
                    </div>
                </div>  
            </section>
        </div>
        </div>
        </div>
        </div>
<!---//End-content---->
<%@ include file="footer.jsp"%>