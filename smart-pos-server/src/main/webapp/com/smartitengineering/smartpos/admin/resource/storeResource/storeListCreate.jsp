<%-- 
    Document   : storeListCreate
    Created on : Sep 1, 2010, 12:58:38 PM
    Author     : uzzal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>store list and create</title>
  </head>
  <body>
    <div class="show" id="showList">
      <div id="title_of_stores" class="header_of_list">
        <label><fmt:message key="org.title"/></label>
      </div>
      <div id="top_row" class="list_column_names">
        <div class="tableheadname">
          <label class="tablehead_label">Store Name</label>
        </div>
        <div class="tableheadname">
          <label class="tablehead_label">Location</label>
        </div>
      </div>
      <div class="tablecontentname" id="tablecontentid"></div>
    </div>
  </body>
</html>