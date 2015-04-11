<!-- credit to template designer http://www.inpixelitrust.fr Stephanie Walter-->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.internationalization.resources.text" />
<head>
        <meta charset="UTF-8" />
        <!-- <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  -->
        <title><fmt:message key="login.title.tab" /></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <meta name="description" content="Login and Registration Form with HTML5 and CSS3" />
        <meta name="keywords" content="html5, css3, form, switch, animation, :target, pseudo-class" />
        <meta name="author" content="Codrops" />
        <link rel="shortcut icon" href="../favicon.ico"> 
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <link rel="stylesheet" type="text/css" href="css/login.css" />
		<link rel="stylesheet" type="text/css" href="css/animate-custom.css" />
    </head>
    <body>
        <div class="container">
            <!-- Codrops top bar -->
            <div class="codrops-top">
            <form>
            <select id="language" name="language" onchange="submit()">
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                <option value="fr" ${language == 'fr' ? 'selected' : ''}>French</option>
            </select>
        </form>
            </div><!--/ Codrops top bar -->
            <header>
                <h1>PhotoShare</h1>
            </header>
            <section>				
                <div id="container_demo" >
                
                    <!-- hidden anchor to stop jump http://www.css3create.com/Astuce-Empecher-le-scroll-avec-l-utilisation-de-target#wrap4  -->
                    <a class="hiddenanchor" id="toregister"></a>
                    <a class="hiddenanchor" id="tologin"></a>
                    <div id="wrapper">
                    
                    	<!-- Login form -->
                        <div id="login" class="animate form">
                            <form  action="loginServlet" autocomplete="on" method="post"> 
                                <h1><fmt:message key="login.title.login" /></h1> 
                                <p> 
                                    <label for="username" class="uname" data-icon="u" > <fmt:message key="login.label.email" /> </label>
                                    <input id="username" name="username" required type="text" placeholder="myusername or mymail@mail.com"/>
                                </p>
                                <p> 
                                    <label for="password" class="youpasswd" data-icon="p"> <fmt:message key="login.label.password" /> </label>
                                    <input id="password" name="password" required type="password" placeholder="eg. X8df!90EO" /> 
                                </p>
                                <p class="keeplogin"> 
									<input type="checkbox" name="loginkeeping" id="loginkeeping" value="loginkeeping" /> 
									<label for="loginkeeping"><fmt:message key="login.label.keeplogin" /></label>
								</p>
                                <p class="login button"> 
                                    <input type="submit" value="<fmt:message key="login.button.login" />" /> 
								</p>
                                <p class="change_link">
									<fmt:message key="login.label.toregister" />
									<a href="#toregister" class="to_register"><fmt:message key="login.button.toregister" /></a>
								</p>
                            </form>
                        </div>

						<!-- Register new account form -->
                        <div id="register" class="animate form">
                            <form  action="createAccountServlet" autocomplete="on" method="post"> 
                                <h1> <fmt:message key="login.title.signup" /> </h1> 
                                <p> 
                                    <label for="usernamesignup" class="uname" data-icon="u"><fmt:message key="singup.label.username" /></label>
                                    <input id="usernamesignup" name="usernamesignup" required type="text" placeholder="mysuperusername690" />
                                </p>
                                <p> 
                                    <label for="emailsignup" class="youmail" data-icon="e" > <fmt:message key="singup.label.email" /></label>
                                    <input id="emailsignup" name="emailsignup" required type="email" placeholder="mysupermail@mail.com"/> 
                                </p>
                                <p> 
                                    <label for="passwordsignup" class="youpasswd" data-icon="p"><fmt:message key="singup.label.password" /> </label>
                                    <input id="passwordsignup" name="passwordsignup" required type="password" placeholder="eg. X8df!90EO" onchange="form.confnewpassword.pattern = this.value;"/>
                                </p>
                                <p> 
                                    <label for="passwordsignup_confirm" class="youpasswd" data-icon="p"><fmt:message key="singup.label.password.confirm" /> </label>
                                    <input id="passwordsignup_confirm" name="passwordsignup_confirm" required pattern title="<fmt:message key="login.warning.required.password" />" type="password" placeholder="eg. X8df!90EO"/>
                                </p>
                                <p class="signin button"> 
									<input type="submit" value=<fmt:message key="singup.button.signup"/>/> 
								</p>
                                <p class="change_link">  
									<fmt:message key="singup.label.tologin" />
									<a href="#tologin" class="to_register"> <fmt:message key="singup.button.tologin" /> </a>
								</p>
                            </form>
                        </div>
						
                    </div>
                </div>  
            </section>
        </div>
    </body>
</html>