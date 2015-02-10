<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  
    pageEncoding="ISO-8859-1"%> 


<html>
	<head>
		<title>PhotoShare Home :: <%=session.getAttribute("name")%></title>
		<link href="css/style2.css" rel='stylesheet' type='text/css' />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="shortcut icon" type="image/x-icon" href="images/fav-icon.png" />
		<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
		
		<!----webfonts---->
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
		<!----//webfonts---->
		<!-- Global CSS for the page and tiles -->
  		<link rel="stylesheet" href="css/main.css">
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
										<li><a href="home.jsp"><span>home</span></a></li>
										<li><a href="#openModal"><span>create album</span></a></li>
										<li><a href="#"><span>Works</span></a></li>
										<li><a href="#"><span>Clients</span></a></li>
										<li><a href="#"><span>Blog</span></a></li>
										<li><a href="contact.html"><span>Contact</span></a></li>
									</ul>
									<div class="clear"> </div>
								</div>
								<a class="boxclose" id="boxclose"> <span> </span></a>
							</div>                                  
						</div> 	
					</div> 
				</div>       	  
				<div class="top-searchbar">
					<form>
						<input type="text" /><input type="submit" value="album_search" />
					</form>
				</div>
				<div class="userinfo">
					<div class="user">
						<ul>
							<li><a href="userSettings.jsp"><img src="images/user-pic.png" title="user-name" /><span><%=session.getAttribute("name")%></span></a></li>
						</ul>
					</div>
				</div>
				<div class="clear"> </div>
			</div>
		</div>
<!-- Modal upload photos box -->
<div id="openModal" class="modalDialog">
	<div>
		<a href="#close" title="Close" class="close">X</a>
		<table id="file_upload_table">
			<tr>Create new album</tr>
			<tr>
				<td style="vertical-align: top;">
					<input type="text" placeholder="Album name" /><br /><br /><br />
					<input type="submit" id="submit_album" value="Make album" />
				</td>
				<td id="url_upload_list">
					<label for="image_upload">Select your photos</label>
					<input type="file" name="image_upload" id="image_upload_button" onchange="fileSelected();" multiple />
					<br /><br />Or<br /><br />
					<input type="text" class="url_upload" placeholder="URL to photo" />
					&nbsp&nbsp<button id="add_url_upload" onclick="addURLUpload();">+</button>
					&nbsp&nbsp<button id="remove_url_upload" style="display:none;" onclick="removeURLUpload();">-</button>
				</td>
			</tr>
		</table>
	</div>
</div>

<!-- Upload files script -->
<script>
var urlUploadCount = 1;

function addURLUpload() {
	++urlUploadCount;
	
	var url_upload = document.createElement("input");
	url_upload.class = "url_upload";
	url_upload.placeholder = "URL to photo";
	
	var list = document.getElementById("url_upload_list");
	
	list.appendChild(url_upload);
	
	document.getElementById("remove_url_upload").style.display = "inline-block";
};

function removeURLUpload() {
	--urlUploadCount;
	
	var list = document.getElementById("url_upload_list");
	
	list.removeChild(list.lastChild);
	
	if(urlUploadCount === 1)
		document.getElementById("remove_url_upload").style.display = "none";
};
</script>
		<!---//End-header---->