<%@ include file="header.jsp"%>
<!---start-content---->

<div class="container">
            <!-- Codrops top bar -->
            <div class="codrops-top">
            </div><!--/ Codrops top bar -->
            <header>
                <h1>UserSettings</h1>
            </header>
            <section>				
                <div id="container_demo" >
                    <div id="wrapper">
                        <div id="userSetting" class="animate form">
                            <form  action="UserSettingServlet" autocomplete="on" method="post"> 
                                <h1>Account Information</h1> 
                                <p> 
                                    <label for="username" class="uname" data-icon="u" > Username </label>
                                    <input id="username" name="username" required="required" type="text" value="<%=session.getAttribute("name")%>"/>
                                </p>
                                <p> 
                                    <label for="useremail" class="uemail" data-icon="e" > Email Address </label>
                                    <input id="useremail" name="useremail" required="required" type="text" value="<%=session.getAttribute("email")%>"/>
                                </p>
                                <p> 
                                    <label for="firstname" class="firstname" data-icon="u"> First Name </label>
                                    <input id="firstname" name="firstname" required="required" type="text" value="<%=session.getAttribute("firstName")%>" /> 
                                </p>
                                <p> 
                                    <label for="lastname" class="lastname" data-icon="u" > Last Name </label>
                                    <input id="lastname" name="lastname" required="required" type="text" value="<%=session.getAttribute("lastName")%>"/>
                                </p>
                                <p> 
                                    <label for="birthdate" class="birthdate" data-icon="u" > Date of Birth </label>
                                    <input id="birthdate" name="birthdate" required="required" type="date" value="<%=session.getAttribute("birthDate")%>"/>
                                </p>
                                <p class="Update button"> 
                                    <input type="submit" value="Update" /> 
								</p>
                            </form>
                        </div>

						
                    </div>
                </div>  
            </section>
        </div>
<!---//End-content---->
<%@ include file="footer.jsp"%>