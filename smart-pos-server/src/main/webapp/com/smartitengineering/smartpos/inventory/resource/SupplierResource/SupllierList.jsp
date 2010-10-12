<%-- 
    Document   : SupllierList
    Created on : Oct 11, 2010, 12:27:11 PM
    Author     : saumitra
--%>


<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.smartitengineering.smartpos.inventory.api.Supplier"%>

<c:if test="${param['lang']!=null}">
  <fmt:setLocale scope="session" value="${param['lang']}"/>
</c:if>

<div id="leftmenu_uomlist_1" class="leftmenu">
  <div id="leftmenu_header_uomlist_1" class="leftmenu_header"><label><fmt:message key="inv.uomtitle"/></label></div>
  <div id="leftmenu_body_uomlist_1" class="leftmenu_body">
    <ul>
      <li><a href="javascript: Orgpageselect()"><fmt:message key="inv.uomcreatelink"/></a></li>
    </ul>
  </div>
</div>

<div class="show" id="showList">
  <div id="title_of_organization_uom" class="header_of_list">
    <label><fmt:message key="inv.uomtabletitle"/></label>
  </div>
  <div id="top_row" class="list_column_names">
    <div class="tableheadname_user">
      <label class="tablehead_label"><fmt:message key="inv.uomtablehead1"/></label>
    </div>
    <div class="tableheadname_user">
      <label class="tablehead_label"><fmt:message key="inv.uomtablehead2"/></label>
    </div>
    <div class="tableheadname_user">
      <label class="tablehead_label"><fmt:message key="inv.uomtablehead3"/></label>
    </div>
  </div>
  <div class="tablecontentname" id="tablecontentid"></div>
  <div class="tableList" id="uomListContainer">
    <c:set var="uom" scope="page" value="${it}"></c:set>
    <c:forEach varStatus="status" items="${it}">
      <div id="userRow${status.index}" class="row_of_list">
        <div id="uomType${status.index}" class="uomType_column"><a href="uoms/name/${uom[status.index].id.id}">${uom[status.index].uomType}</a></div>
        <div id="uomId${status.index}" class="id_column"><a href="uoms/name/${uom[status.index].id.id}">${uom[status.index].id.id}</a></div>
        <div id="symbol${status.index}" class="symbol_column"><a href="uoms/name/${uom[status.index].id.id}">${uom[status.index].symbol}</a></div>
      </div>
    </c:forEach>
  </div>
</div>
