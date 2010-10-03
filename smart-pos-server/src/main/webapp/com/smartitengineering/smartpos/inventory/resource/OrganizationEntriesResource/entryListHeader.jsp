<%-- 
    Document   : entryListHeader
    Created on : Sep 5, 2010, 11:29:38 AM
    Author     : russel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>

  <c:when test="${empty param.count}">
    <c:set var="qParam" value="" />
  </c:when>
  <c:otherwise>
    <c:set var="qParam" value="?count=${param.count}" />
  </c:otherwise>
</c:choose>
<script type="text/javascript">

  $(document).ready(function(){
    var url = "http://localhost:9090/orgs/frags${qParam}";
    $("#tablecontentid").pagination(url, "paginationLinks");
    $("#wrong").hide();
  });
</script>