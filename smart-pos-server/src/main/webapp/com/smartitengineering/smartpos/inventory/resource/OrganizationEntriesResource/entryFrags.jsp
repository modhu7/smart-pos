<%-- 
    Document   : entryFrags
    Created on : Sep 5, 2010, 1:00:58 PM
    Author     : russel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.smartitengineering.smartpos.inventory.api.Entry"%>


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

<div id="userListRootDiv">
  <div class="tableList" id="userListContainer">
    <c:forEach var="userPerson" items="${it}" varStatus="status">
        <c:if test="${status.first}">
          <c:set var="first" value="${entry.entryDate}"></c:set>
        </c:if>
        <c:if test="${status.last}">
          <c:set var="last" value="${entry.entryDate}"></c:set>
        </c:if>
      <div id="userRow${status.index}" class="row_of_list">
        
        <div id="entryDate${status.index}" class="entryDate_column"><a href="inv/entries/entrydate/${entry.entryDate}">${entry.entryDate}</a></div>
        <div id="productName${status.index}" class="productName_column"><a href="inv/entries/entrydate/${entry.entryDate}">${entry.productName}</a></div>
        <div id="productCode${status.index}" class="productCode_column"><a href="inv/entries/entrydate/${entry.entryDate}">${entry.productId}</a></div>
        <div id="quantity${status.index}" class="quantity_column"><a href="inv/entries/entrydate/${entry.entryDate}">${entry.quantity}</a></div>
        <div id="expireDate${status.index}" class="expireDate_column"><a href="inv/entries/entrydate/${entry.entryDate}">${entry.expiryDate}</a></div>
      </div>
    </c:forEach>
  </div>

  <div class="navigation_container" id="linkcontainer">
    <div id="nextUsersLinkCont" class="list_navigation_links"><a id="next" href="entries/after/afterEntryDate/${last}/frags${qParam}">next >></a> </div>
    <div id="previousUsersLinkCont" class="list_navigation_links"><a id="previous" href="entries/before/beforeEntryDate/${first}/frags${qParam}"><< previous</a></div>
  </div>
</div>