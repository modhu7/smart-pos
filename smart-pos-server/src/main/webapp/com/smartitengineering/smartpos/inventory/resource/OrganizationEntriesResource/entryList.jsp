<%-- 
    Document   : entryList
    Created on : Sep 5, 2010, 1:00:39 PM
    Author     : russel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${param['lang']!=null}">
  <fmt:setLocale scope="session" value="${param['lang']}"/>
</c:if>

<div id="leftmenu_userlist_1" class="leftmenu">
  <div id="leftmenu_header_userlist_1" class="leftmenu_header"><fmt:message key="inv.prdentrytitle"/></div>
  <div id="leftmenu_body_userlist_1" class="leftmenu_body">
    <ul>
      <li><a href="javascript: Orgpageselect()"><fmt:message key="inv.prdentrylink"/></a></li>
    </ul>
  </div>
</div>


<div class="show" id="showList">
  <div id="title_of_organization_users" class="header_of_list">
    <label><fmt:message key="inv.prdentrytitle"/></label>
  </div>
  <div id="top_row" class="list_column_names">
    <div class="tableheadname_user">
      <label class="tablehead_label"><fmt:message key="inv.prdentryinput1"/></label>
    </div>
    <div class="tableheadname_user">
      <label class="tablehead_label"><fmt:message key="inv.prdentryinput3"/></label>
    </div>
    <div class="tableheadname_user">
      <label class="tablehead_label"><fmt:message key="inv.prdentryinput4"/></label>
    </div>
    <div class="tableheadname_user">
      <label class="tablehead_label"><fmt:message key="inv.prdentryinput5"/></label>
    </div>
    <div class="tableheadname_user">
      <label class="tablehead_label"><fmt:message key="inv.prdentryinput2"/></label>
    </div>
  </div>
  <div class="tablecontentname" id="tablecontentid"></div>
</div>


<div class="hide"  id="create">
  <div id="title_of_organization_users" class="header_of_list">
    <label><fmt:message key="inv.prdentryhead"/></label>
  </div>
  <form action="" accept="application/json" enctype="" id="entryList" method="post">
    <div class="form_label"><label><fmt:message key="inv.prdentryinput1" /></label></div>
    <div class="form_textField" id="date"><input type="text" name="entryDate" id="entryDate" class="textfield"></div>
    <div class="clear"></div>
    <div class="form_label"><label><fmt:message key="inv.prdentryinput2" /></label></div>
    <div class="form_textField" id="date"><input type="text" name="expiryDate" id="expiryDate" class="textfield"></div>
    <div class="clear"></div>
    <div class="form_label"><label><fmt:message key="inv.prdentryinput3" /></label></div>
    <div class="form_textField"><input type="text" name="product" id="product" class="textfield"></div>
    <div class="clear"></div>
    <div class="form_label"><label><fmt:message key="inv.prdentryinput4" /></label></div>
    <div class="form_textField"><input type="text" name="productCode" id="productCode" class="textfield"></div>
    <div class="clear"></div>
    <div class="form_label"><label><fmt:message key="inv.prdentryinput5" /></label></div>
    <div class="form_textField"><input type="text" name="quantity" id="quantity" class="textfield"></div>
    <div class="clear"></div>
    <div class="btnfield"><label><fmt:message key="org.usrinput6" var="submitbtn"/></label><input name="submitbtn" type="submit" class="submitbtn" value="SUBMIT"></div>
    <div class="clear"></div>
  </form>
</div>