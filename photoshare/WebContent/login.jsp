<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
	<!-- CSS -->
	<link href="css/login.css" rel="stylesheet" />
	
	<!-- JavaScript -->
	<script src="js/jquery-1.11.2.min.js"></script>
	<script src="js/login.js"></script>
	
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>PhotoShare - Login</title>
</head>

<body onload="start()">
<h1 id="title">PhotoShare</h1>
    <form action="loginServlet" method="post">
        <fieldset id="loginFieldset">
            <legend id="loginCreateAccount"> Login </legend>
            <table>
                <tr>
                    <td><input type="text" name="username" required="required" placeholder="Username" /></td>
                </tr>
                <tr>
                    <td><input type="password" name="userpass" required="required" placeholder="Password" /></td>
                </tr>
                <tr>
                    <td><input id="emailField" type="email" name="useremail" placeholder="Email"/></td>
                </tr>
                <tr>
                    <td>
                    <input id="loginButton" type="submit" value="Login" />
                    <input id="signupButton" type="submit" value="Sign up" />
                    </td>
                </tr>
                <tr>
                    <td><button id="changeButton" type="button" value="Sign up">Sign up</button></td>
                </tr>
            </table>
        </fieldset>
    </form>
</body>
</html>