
<%-- 
    Document   : template
    Created on : Aug 4, 2010, 11:19:15 AM
    Author     : uzzal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
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
    <link rel="Stylesheet" href="/css/smart-pos-forms.css">
    <link rel="Stylesheet" href="/css/smart-menu.css">
    <link rel="Stylesheet" href="/css/smart-pos-dashboard-style.css">
    <link rel="Stylesheet" href="/css/smothness/jquery_ui_datepicker.css">
    <link rel="Stylesheet" href="/css/jqtp.css">
    <link rel="Stylesheet" href="/css/autocomplete.css">


    <script type="text/javascript" src="/script/jquery-1.4.2.js"></script>
    <script type="text/javascript" src="/script/jquery.validate.js"></script>
    <script type="text/javascript" src="/script/siteljquerylib.js"></script>
    <script type="text/javascript" src="/script/commonScripts.js"></script>
    <script type="text/javascript" src="/script/jquery_ui_datepicker.js"></script>
    <script type="text/javascript" src="/script/jquery.jqtp.min.js"></script>
    <script type="text/javascript" src="/script/jquery.autocomplete.js"></script>

    <c:if test="${not empty templateHeadContent}">
      <jsp:include page="${templateHeadContent}"></jsp:include>
    </c:if>
    <jsp:include page="smart-pos-dashboard-header.jsp"></jsp:include>

  </head>
  <body>
    <div id="menu_common" class="leftmenu">
      <div id="menu_common_header_1" class="leftmenu_header"><label>Common Navigator</label></div>
      <div id="menu_common_body_1" class="leftmenu_body">
        <ul>
          <li><a href="/orgs">OrganizationList</a></li>
          <li><a href="/orgs/sn/SITEL/inv/stores">StoreList</a></li>
        </ul>
      </div>
    </div>

    <div id="main" class="main_template">
      <div class="maintemplateHeaderContainer">
        <div id="header" class="main_template_header">
          <div id="sitel_logo" class="sitel_logo_container"><img src="/images/site ultimate build 1.0.0.5.png" alt="Smart IT Engineering Limited" id="img_sitel_logo"></div>
          <div id="sitel_slogan" class="sitel_slogan_container"><label>IT for smarter livinG</label></div>
        </div>
      </div>
      <div class="main_template_content">
        <div id="options" class="main_template_options">
          <%--<jsp:include page="smart-pos-dashboard.jsp"></jsp:include>--%>
        </div>
        <div class="clear"></div>
        <div id="left" class="left_menu"></div>
        <div id="content" class="template_content">
          <jsp:include page="${templateContent}"></jsp:include>
          <jsp:include page="smart-pos-dashboard.jsp"></jsp:include>
        </div>
        <div class="clear"></div>
        <div id="footer" class="main_template_footer"><div class="footer_label_container"><label>copyright@smart it engineering limited 2010</label></div></div>
      </div>
      <%--<div class="clear"></div>--%>
    </div>
  </body>
</html>
<%--Uzzal--%>