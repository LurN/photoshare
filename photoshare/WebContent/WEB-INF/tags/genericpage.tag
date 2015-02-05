<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<html>
  <body>
    <div id="pageheader">
    This is the header.
      <jsp:invoke fragment="header"/>
    </div>
    <div id="pagefooter">
    This is the footer.
      <jsp:invoke fragment="footer"/>
    </div>
  </body>
</html>