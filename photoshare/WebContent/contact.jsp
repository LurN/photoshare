<%@ include file="header.jsp" %>
<div class="container" >
<div class="content">
		<div class="wrap">
			 <div id="main" role="main">
                    <div class="equalHWrap eqWrap">
                    <div class="equalWH eq">
                    <div id="container_demo" >
                    <div id="wrapper">
			<h1><fmt:message key="contact.label.site.developers" /></h1>
			<ul>
				<li>
					<ul>
						<li><label>Luke Bailey</label></li>
						
						<li>
							<p>A co-founder of Photoshare, a community of more than 200
								Billion users who capture the worlds moments and preserve them
								on the service.<br/></p>
						</li>
						<li><a href="mailto:lukebaileyx@gmail.com">Contact</a></li>
					</ul>
				</li>
				<li>
					<ul>
						<li><br/><label>Andrew Polidori</label></li>
						
						<li>
							<p>Another co-founder of Photoshare.</p></li>
							<li><a href="mailto:andrewpolidori@gmail.com">Contact</a></li>
					</ul>
				</li>
				<li>
					<ul>
						<li><br/><label>Fred Proulx</label></li>
						
						<li>
							<p>Co-founder of Photoshare.</p>
						</li>
						<li><a href="mailto:freddycool_16@hotmail.com">Contact</a></li>
					</ul>
				</li>
			</ul>
			</div>
			</div>
			</div>
			<div id="container_demo" >
                    <div id="wrapper">
			<div class="equalHW eq">
			<div id="emailUs" class="animate form">
                            <form  action="contact" autocomplete="on" method="post"> 
                                <h1><fmt:message key="user.settings.change.password" /></h1> 
                                <p> 
                                    <label for="useremail" class="uemail" data-icon="e" ><fmt:message key="user.settings.email" /></label>
                                    <input id="fromemail" name="fromemail" required type="text" value="<%=session.getAttribute("email")%>"/>
                                </p>
                                <p> 
                                    <label for="emailsubject" class="uemail" ><fmt:message key="contact.email.subject" /></label>
                                    <input id="subject" name="subject" required type="text" placeholder="<fmt:message key="contact.email.subject" />"/>
                                </p>
                                <p> 
                                     <label><fmt:message key="contact.email.content" /></label>
                                     <input id="comment" name="comment" required type="text" />
                                </p>
                                <p class="Email Us button"> 
                                    <input type="submit" value="<fmt:message key="contact.email.execute" />" /> 
								</p>
                            </form>
                        </div>
			</div>
			</div>
			
			</div>
			</div>
		</div>
		</div>
</div>
</div>
<%@ include file="footer.jsp" %>