<%@ include file="header.jsp"%>
<!---start-content---->
        <script type="text/javascript">
            function validate()
            {
                var a = document.getElementById("newpassword");
                var b = document.getElementById("confnewpassword");
                var valid = true;
                if(!a.equals(b))
                    {
                        alert("New Passwords Don't Match!");
                        valid = false;
                    }
                return valid;
            };

        </script>
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
                                <h1>Account Information</h1> 
                                <p> 
                                    <label for="username" class="uname" data-icon="u" > Username </label>
                                    <input id="username" name="username" required="required" type="text" value="<%=session.getAttribute("name")%>" readonly/>
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
						<div id="userPassword" class="animate form">
                            <form  action="userPassword" autocomplete="on" method="post" onsubmit="return validate();"> 
                                <h1>Change Password</h1> 
                                <p> 
                                    <label for="oldpassword" class="oldpassword" data-icon="p" > Old Password </label>
                                    <input id="oldpassword" name="oldpassword" required="required" type="password"/>
                                </p>
                                <p> 
                                    <label for="newpassword" class="newpassword" data-icon="p" > New Password </label>
                                    <input id="newpassword" name="newpassword" required="required" type="password"/>
                                </p>
                                <p> 
                                    <label for="confnewpassword" class="confnewpassword" data-icon="p"> Confirm New Password </label>
                                    <input id="confnewpassword" name="confnewpassword" required="required" type="password"/> 
                                </p>
                                <p class="Change Password button"> 
                                    <input type="submit" value="Change Password" /> 
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