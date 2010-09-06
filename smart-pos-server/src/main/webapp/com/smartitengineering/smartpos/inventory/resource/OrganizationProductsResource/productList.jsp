<%-- 
    Document   : productList
    Created on : Sep 4, 2010, 6:11:23 PM
    Author     : russel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${param['lang']!=null}">
  <fmt:setLocale scope="session" value="${param['lang']}"/>
</c:if>

<div id="leftmenu_userlist_1" class="leftmenu">
  <div id="leftmenu_header_userlist_1" class="leftmenu_header"><fmt:message key="inv.prdcreatetitle"/></div>
  <div id="leftmenu_body_userlist_1" class="leftmenu_body">
    <ul>
      <li><a href="javascript: Orgpageselect()"><fmt:message key="inv.prdcreatelink"/></a></li>
    </ul>
  </div>
</div>

    <div class="show" id="showList">
  <div id="title_of_organization_users" class="header_of_list">
    <label><fmt:message key="inv.prdtitle"/></label>
  </div>
  <div id="top_row" class="list_column_names">
    <div class="tableheadname_user">
      <label class="tablehead_label"><fmt:message key="inv.prdtablehead1"/></label>
    </div>
    <div class="tableheadname_user">
      <label class="tablehead_label"><fmt:message key="inv.prdtablehead2"/></label>
    </div>
    <div class="tableheadname_user">
      <label class="tablehead_label"><fmt:message key="inv.prdtablehead3"/></label>
    </div>
  </div>
  <div class="tablecontentname" id="tablecontentid"></div>
</div>


<div class="hide"  id="create">
  <div id="header_organization_users" class="header_entry_form">
    <label><fmt:message key="inv.prdentrytitle"/></label>
  </div>

  <div id="form_organizationentry" class="entry_form">
    <form action="" accept="application/json" enctype="" id="productEntryForm" method="post">
      <div class="form_label"><label><fmt:message key="inv.prdinput1"/></label></div>
      <div class="form_textField"><input type="text" name="name" id="name" class="textfield"></div>
      <div class="clear"></div>
      <div class="form_label"><label><fmt:message key="inv.prdinput2"/></label></div>
      <div class="form_textField"><input type="text" name="productCode" id="productCode" class="textfield"></div>
      <div class="clear"></div>
      <div class="form_label"><label><fmt:message key="inv.prdinput3"/></label></div>
      <div class="form_textField"><input type="text" name="description" id="description" class="textfield"></div>
      <div class="clear"></div>
      <div class="btnfield"><label><fmt:message key="org.usrinput6" var="submitbtn"/></label><input name="submitbtn" type="submit" class="submitbtn" value="SUBMIT"></div>
      <div class="clear"></div>
    </form>
  </div>
</div>