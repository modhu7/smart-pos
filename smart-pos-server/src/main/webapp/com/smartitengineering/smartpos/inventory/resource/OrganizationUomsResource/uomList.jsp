<%-- 
    Document   : uomList
    Created on : Sep 5, 2010, 4:03:55 PM
    Author     : russel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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
  </div>
  <div class="tablecontentname" id="tablecontentid"></div>
</div>

<div class="hide"  id="create">
  <div class="toRoundBox">
    <div id="header_products" class="header_entry_form_pos">
      <label><fmt:message key="inv.uomcreatetitle"/></label>
    </div>
    <div id="form_ProductionEntry" class="entry_form_pos">
      <form action="" accept="application/json" enctype="" id="productEntryForm" method="post">
        <div class="individual_field_container">
          <div class="form_label_pos"><label><fmt:message key="inv.uominput1"/></label></div>
          <div class="form_textField_pos"><input type="text" name="name" id="name" class="textFieldPos"></div>
          <div class="clear"></div>
        </div>
        <div class="individual_field_container">
          <div class="form_label_pos"><label><fmt:message key="inv.uominput2"/></label></div>
          <div class="form_textField_pos"><input type="text" name="productCode" id="productCode" class="textFieldPos"></div>
          <div class="clear"></div>
        </div>
        <%--<div class="individual_field_container">
          <div class="form_label_pos"><label><fmt:message key="inv.prdinput3"/></label></div>
          <div class="form_textField_pos"><input type="text" name="description" id="description" class="textFieldPos"></div>
          <div class="clear"></div>
        </div>
        <div class="individual_field_container">
          <div class="form_label_pos"><label><fmt:message key="inv.prdinput4"/></label></div>
          <div class="form_textField_pos"><input type="text" name="sku" id="sku" class="textFieldPos"></div>
          <div class="clear"></div>
        </div>--%>
        <div class="individual_field_container">
          <div class="insertField"><input name="submitbtn" type="submit" class="insertBtn" value="INSERT"></div>
          <div class="clear"></div>
        </div>
      </form>
    </div>
  </div>
</div>