<%-- 
    Document   : productFrags
    Created on : Sep 4, 2010, 6:11:40 PM
    Author     : russel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.smartitengineering.smartpos.inventory.api.Product"%>


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
          <c:set var="first" value="${product.name}"></c:set>
        </c:if>
        <c:if test="${status.last}">
          <c:set var="last" value="${product.name}"></c:set>
        </c:if>
      <div id="userRow${status.index}" class="row_of_list">

        <div id="productId${status.index}" class="productId_column"><a href="prods/code/${product.}">${status.count}</a></div>
        <div id="productName${status.index}" class="productName_column"><a href="prods/code/${product.}">${userPerson.user.username}</a></div>
        <div id="description${status.index}" class="description_column"><a href="prods/code/${product.}">${firstName} ${middleInitial} ${lastName}</a></div>
      </div>
    </c:forEach>
  </div>

  <div class="navigation_container" id="linkcontainer">
    <div id="nextUsersLinkCont" class="list_navigation_links"><a id="next" href="prods/after/${last}/frags${qParam}">next >></a> </div>
    <div id="previousUsersLinkCont" class="list_navigation_links"><a id="previous" href="prods/before/${first}/frags${qParam}"><< previous</a></div>
  </div>
</div>