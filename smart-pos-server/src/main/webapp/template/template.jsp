
<%-- 
    Document   : template
    Created on : Aug 4, 2010, 11:19:15 AM
    Author     : uzzal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%--Uzzal--%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>aponn for you</title>

    <link rel="Stylesheet" href="/css/style.css">
    <link rel="Stylesheet" href="/css/smart-list.css">
    <link rel="Stylesheet" href="/css/smart-forms-style.css">
    <link rel="Stylesheet" href="/css/smart-menu.css">

    <script type="text/javascript" src="/script/jquery-1.4.2.js"></script>
    <script type="text/javascript" src="/script/jquery.validate.js"></script>
    <script type="text/javascript" src="/script/siteljquerylib.js"></script>
    <script type="text/javascript" src="/script/commonScripts.js"></script>

    <c:if test="${not empty templateHeadContent}">
      <jsp:include page="${templateHeadContent}"></jsp:include>
    </c:if>

  </head>
  <body>

    <div id="menu_common" class="leftmenu">
      <div id="menu_common_header_1" class="leftmenu_header"><label>Common Navigator</label></div>
      <div id="menu_common_body_1" class="leftmenu_body">
        <ul>
          <li><a href="/orgs">OrganizationList</a></li>
        </ul>
      </div>
    </div>

    <div id="main" class="main_template">
      <div id="header" class="main_template_header">
        <div id="sitel_logo" class="sitel_logo_container"><img src="/images/site ultimate build 1.0.0.5.png" alt="Smart IT Engineering Limited" id="img-sitel-logo"></div>
        <div id="sitel_slogan" class="sitel_slogan_container"><label>IT for smarter living</label></div>
      </div>
      <div id="options" class="main_template_options"></div>
      <div class="clear"></div>
      <div id="content" class="template_content">
        <jsp:include page="${templateContent}"></jsp:include>
      </div>
      <div id="left" class="left_menu"></div>
      <div class="clear"></div>
      <div id="footer" class="main_template_footer"><div class="footer_label_container"><label>copyright@smart it engineering limited 2010</label></div></div>
    </div>

  </body>
</html>
<%--Uzzal--%>