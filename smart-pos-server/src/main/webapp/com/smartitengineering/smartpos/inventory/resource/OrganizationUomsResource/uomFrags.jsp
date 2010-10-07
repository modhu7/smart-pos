<%-- 
    Document   : uomFrags
    Created on : Sep 5, 2010, 4:03:37 PM
    Author     : russel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.smartitengineering.smartpos.inventory.api.UnitOfMeasurement"%>


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

<div id="uomListRootDiv">
  <div class="tableList" id="uomListContainer">
    <c:forEach var="uom" items="${it}" varStatus="status">
        <c:if test="${status.first}">
          <c:set var="first" value="${uom.name}"></c:set>
        </c:if>
        <c:if test="${status.last}">
          <c:set var="last" value="${uom.name}"></c:set>
        </c:if>
      <div id="userRow${status.index}" class="row_of_list">

        <div id="uomType${status.index}" class="uomType_column"><a href="uoms/name/${uom.uomType}">${uom.uomType}</a></div>
        <div id="id${status.index}" class="id_column"><a href="uoms/name/${uom.name}">${uom.name}</a></div>
      </div>
    </c:forEach>
  </div>

  <div class="navigation_container" id="linkcontainer">
    <div id="nextUsersLinkCont" class="list_navigation_links"><a id="next" href="prods/after/${last}/frags${qParam}">next >></a> </div>
    <div id="previousUsersLinkCont" class="list_navigation_links"><a id="previous" href="prods/before/${first}/frags${qParam}"><< previous</a></div>
  </div>
</div>