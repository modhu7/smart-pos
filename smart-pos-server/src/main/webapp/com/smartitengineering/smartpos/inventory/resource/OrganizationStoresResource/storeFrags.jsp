<%-- 
    Document   : storeFrags
    Created on : Sep 4, 2010, 11:05:22 AM
    Author     : uzzal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.smartitengineering.smartpos.inventory.api.Store"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="first" value="0"></c:set>
<c:set var="last" value="0"></c:set>

<c:choose>
  <c:when test="${empty param.count}">
    <c:set var="qParam" value="" />
  </c:when>
  <c:otherwise>
    <c:set var="qParam" value="?count=${param.count}" />
  </c:otherwise>
</c:choose>

<div id="storeFragsRootDiv">
  <div id="storeListContainer" class="tableList">
    <c:forEach var="store" items="${it}" varStatus="status">
      <c:if test="${status.first}">
        <c:set var="first" value="${store.code}"></c:set>
      </c:if>
      <c:if test="${status.last}">
        <c:set var="last" value="${store.code}"></c:set>
      </c:if>
      <div id="storeRow${status.index}" class="row_of_list">
        <div id="orgName${status.index}" class="orgName_column"><a href="/orgs/sn/}">${store.organization}</a></div>
        <div id="storeName${status.index}" class="orgShortName_column"><a href="/orgs/sn/}">${store.name}</a></div>
        <div id="storeCode${status.index}" class="orgShortName_column"><a href="/orgs/sn/}">${store.code}</a></div>
      </div>
    </c:forEach>
  </div>

  <div class="navigation_container" id="linkcontainer">
    <div id="nextUsersLinkCont" class="list_navigation_links"><a id="next" href="/after/${last}/frags${qParam}">next >></a> </div>
    <div id="previousUsersLinkCont" class="list_navigation_links"><a id="previous" href="/before/${first}/frags${qParam}"><< previous</a></div>
  </div>
</div>