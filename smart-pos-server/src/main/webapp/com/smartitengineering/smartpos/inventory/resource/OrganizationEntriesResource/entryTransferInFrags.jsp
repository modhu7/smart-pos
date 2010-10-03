<%-- 
    Document   : entryTransferInFrags
    Created on : Oct 3, 2010, 4:42:24 PM
    Author     : saumitra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${param['lang']!=null}">
  <fmt:setLocale scope="session" value="${param['lang']}"/>
</c:if>

<div id="leftmenu_userlist_1" class="leftmenu">
  <div id="leftmenu_header_userlist_1" class="leftmenu_header"><label><fmt:message key="inv.prdentrytitle"/></label></div>
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
