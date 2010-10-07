<%--
    Document   : frag
    Created on : Aug 21, 2010, 2:05:22 PM
    Author     : russel
--%>
<%@page import="com.smartitengineering.smartpos.inventory.api.Entry"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<<<<<<< Temporary merge branch 1

=======
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.smartitengineering.smartpos.inventory.api.Entry"%>


>>>>>>> Temporary merge branch 2
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

<<<<<<< Temporary merge branch 1
<div id="orgFragRootDiv">
  <div id="mainOrgPaginatedList" class="tableList">
    <c:forEach var="entry" items="${it}" varStatus="status">
      <c:if test="${status.first}">
        <c:set var="first" value="${entry.entryDate}"></c:set>
      </c:if>
      <c:if test="${status.last}">
        <c:set var="last" value="${entry.entryDate}"></c:set>
      </c:if>
      <div id="row${status.index}" class="row_of_list">
        <div id="productId${status.index}" class="productId_column">${entry.productId}</div>
        <div id="storeId${status.index}" class="storeId_column">${entry.storeId}</div>
        <div id="entryDate${status.index}" class="date_column">${entry.entryDate}</div>
        <div id="quantity${status.index}" class="quantity_column">${entry.quantity}</div>
      </div>
    </c:forEach>
  </div>
  <div class="navigation_container" id="paginationLinks">
    <div id="nextLinkContainer"  class="list_navigation_links">
      <a id="next" href="/orgs/after/${last}/frags${qParam}" class="nxt">next >><%--<img src="/images/31_64x64.png" alt="next" class="list_nav">--%></a>
    </div>
    <div id="previousLinkContainer"  class="list_navigation_links">
      <a id="previous" href="/orgs/before/${first}/frags${qParam}" class="prev"><< previous<%--<img src="/images/30_64x64.png" alt="previous" class="list_nav">--%></a>
    </div>
=======
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
>>>>>>> Temporary merge branch 2
  </div>
</div>