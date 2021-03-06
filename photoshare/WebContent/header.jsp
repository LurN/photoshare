<%@page import="com.photoshare.servlets.DeletePhotoServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  
    pageEncoding="ISO-8859-1"%> 
<%
	// User isn't logged in, redirect to login
	if(session.getAttribute("name") == null) {
	    String redirectURL = "http://localhost:8080/photoshare/login.jsp";
	    response.sendRedirect(redirectURL);
	}
%>

<%
	// Create deletePhoto object
	DeletePhotoServlet dps = new DeletePhotoServlet();
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.internationalization.resources.text" />
<html>
	<head>
		<title>PhotoShare Home :: <%=session.getAttribute("name")%></title>
		<link href="css/style2.css" rel='stylesheet' type='text/css' />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="shortcut icon" type="image/x-icon" href="images/fav-icon.png" />
		<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
		
		<script src="./js/modifyPhoto.js"></script>
		
		<!----webfonts---->
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
		<!----//webfonts---->
		<!-- Global CSS for the page and tiles -->
  		<link rel="stylesheet" href="css/main.css">
  		<link rel="stylesheet" href="css/style.css">
  		<link rel="stylesheet" href="css/login.css">
  		<link rel="stylesheet" type="text/css" href="css/animate-custom.css"/>
  		<!-- //Global CSS for the page and tiles -->
  		
		<!---start-click-drop-down-menu----->
		<script src="js/jquery.min.js"></script>
		
        <!----start-dropdown--->
         <script type="text/javascript">
			var $ = jQuery.noConflict();
				$(function() {
					$('#activator').click(function(){
						$('#box').animate({'top':'0px'},500);
					});
					$('#boxclose').click(function(){
					$('#box').animate({'top':'-700px'},500);
					});
				});
				$(document).ready(function(){
				//Hide (Collapse) the toggle containers on load
				$(".toggle_container").hide(); 
				//Switch the "Open" and "Close" state per click then slide up/down (depending on open/close state)
				$(".trigger").click(function(){
					$(this).toggleClass("active").next().slideToggle("slow");
						return false; //Prevent the browser jump to the link anchor
				});
									
			});
		</script>
        <!----//End-dropdown--->
        
		<!---//End-click-drop-down-menu----->
	</head>
	<body>
			
	<!-- Context menu -->
	<div id="contextMenu">
		<a href="#openModal" id="openModalLink">
			<input class="contextMenuItem" type="button" value="Add photo..."/><br />
		</a>
		<a href="#deleteModal" id="deleteModalLink">
			<input class="contextMenuItem" type="button" value="Delete"/>
		</a>
	</div>
	
		<!---start-wrap---->
			<!---start-header---->
			<div class="header">
				<div class="wrap">
				<div class="logo">
					<a href="home.jsp"><img src="images/logo.png" title="pinbal" /></a>
				</div>
				<div class="nav-icon">
					 <a href="#" class="right_bt" id="activator"><span> </span> </a>
				</div>
				 <div class="box" id="box">
					 <div class="box_content">        					                         
						<div class="box_content_center">
						 	<div class="form_content">
								<div class="menu_box_list">
									<ul>
										<li><a href="home.jsp"><span><fmt:message key="header.menu.home" /></span></a></li>
										<li><a href="userHome.jsp"><span><fmt:message key="header.menu.userHome" /></span></a></li>
										<li><a href="#openModal"><span><fmt:message key="header.menu.create.album" /></span></a></li>
										<li><a href="userSettings.jsp"><span><fmt:message key="header.menu.user.settings" /></span></a></li>
										<li><a href="contact.jsp"><span><fmt:message key="header.menu.contact" /></span></a></li>
										<li><a href="logout.jsp"><span><fmt:message key="header.menu.logout" /></span></a></li>
									</ul>
									<div class="clear"> </div>
								</div>
								<a class="boxclose" id="boxclose"> <span> </span></a>
							</div>                                  
						</div> 	
					</div> 
				</div>       	  
				
				<div class="userinfo">
					<div class="user">
						<ul>
							<li><a href="userHome.jsp"><img src="images/user-pic.png" title="user-name" /><span><%=session.getAttribute("name")%></span></a></li>
						</ul>
					</div>
				</div>
				<form>
            <select id="language" name="language" onchange="submit()">
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                <option value="fr" ${language == 'fr' ? 'selected' : ''}>French</option>
            </select>
        </form>
				<div class="clear"> </div>
			</div>
		</div>
		
<!-- Modal upload photos box -->
<div id="openModal" class="modalDialog">
	<div>
		<a href="#close" title="Close" class="close">X</a>
		<form enctype="multipart/form-data" action="fileUpload" method="post">
		<table id="file_upload_table">
			<tr>
				<td id="url_upload_list">
					<label for="image_upload">Select your photos</label>
					<input type="file" name="image_upload" id="image_upload_button" onchange="fileSelected();" multiple />
					<input type="submit" id="submit_album" value="upload" />
				</td>
			</tr>
		</table>
		</form>
	</div>
</div>

<div id="photoModal" class="modalDialog">
	<div>
		<a href="#close" title="Close" class="close">X</a>
		<img id="photoModalPhoto" />
	</div>
</div>
		<!---//End-header---->
