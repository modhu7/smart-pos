<%-- 
    Document   : storeList
    Created on : Sep 2, 2010, 11:50:36 AM
    Author     : russel
--%>

<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@page import="com.smartitengineering.user.domain.Organization"%>--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">

<div id="leftmenu_" class="leftmenu">
  <div id="leftmenu_header_" class="leftmenu_header"><label>Organization-Creation</label></div>
  <div id="leftmenu_body_" class="leftmenu_body">
    <ul>
      <li><a href="javascript: Orgpageselect()">Create</a></li>
    </ul>
  </div>
</div>

<c:if test="${param['lang']!=null}">
  <fmt:setLocale scope="session" value="${param['lang']}"/>
</c:if>

<div class="show" id="showList">
  <div id="title_of_organization" class="header_of_list">
    <label><fmt:message key="org.title"/></label>
  </div>
  <div id="top_row" class="list_column_names">
    <div class="tableheadname_user">
      <label class="tablehead_label"><fmt:message key="org.tablehead2"/></label>
    </div>
    <div class="tableheadname_user">
      <label class="tablehead_label"><fmt:message key="org.tablehead3"/></label>
    </div>
    <div class="tableheadname_user">
      <label class="tablehead_label"><fmt:message key="org.tablehead3"/></label>
    </div>
  </div>

    <div class="row_of_list">
      <div class="storeOrgs_column">Smart It Engineering</div>
      <div class="storeName_column">Personal Computer</div>
      <div class="storeCode_column">sitel001</div>
    </div>

  <%--<div class="tablecontentname" id="tablecontentid"></div>--%>
</div>