<%-- 
    Document   : uomList
    Created on : Sep 5, 2010, 4:03:55 PM
    Author     : russel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.smartitengineering.smartpos.inventory.api.UnitOfMeasurement"%>

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

<div class="hide"  id="create">
  <div class="toRoundBox">
    <div id="header_products" class="header_entry_form_pos">
      <label><fmt:message key="inv.uomcreatetitle"/></label>
    </div>
    <div id="form_ProductionEntry" class="entry_form_pos">
      <form action="" accept="application/json" enctype="" id="uomEntryForm" method="post">
        <div class="individual_field_container">
          <div class="form_label_pos"><label><fmt:message key="inv.uominput1"/></label></div>
          <div class="form_textField_pos">
            <select id="selectCriterion" name="selectCriterion" class="textFieldPos" title="Measurement Criterion">
              <option value="">-----Select an option-----</option>
              <option value="hight">Hight</option>
              <option value="length">Length</option>
              <option value="weight">Weight</option>
              <option value="area">Area</option>
              <option value="volume">volume</option>
              <option value="other">Other</option>
            </select>
            <input type="hidden" name="uomType" id="uomType" class="textFieldPos" title="Measurement Criterion"/>
          </div>
          <div class="clear"></div>
        </div>
        <div class="individual_field_container">
          <div class="form_label_pos"><label><fmt:message key="inv.uominput2"/></label></div>
          <div class="form_textField_pos"><input type="text" name="id" id="id" class="textFieldPos" title="Unit"></div>
          <div class="clear"></div>
        </div>
        <div class="individual_field_container">
          <div class="form_label_pos"><label><fmt:message key="inv.uominput3"/></label></div>
          <div class="form_textField_pos"><input type="text" name="symbol" id="symbol" class="textFieldPos" title="Symbol"/></div>
          <div class="clear"></div>
        </div>
        <div class="individual_field_container">
          <div class="form_label_pos"><label><fmt:message key="inv.uominput4"/></label></div>
          <div class="form_textField_pos">
            <select id="system" name="system" title="Unit System" class="textFieldPos">
              <option value="">-----Select an option-----</option>
              <option value="SI">SI</option>
              <option value="Metric">Metric</option>
              <option value="Local">Local</option>
            </select>
            <input type="hidden" class="textFieldPos" name="uomSystem" id="uomSystem" />
          </div>
          <div class="clear"></div>
        </div>
        <div class="individual_field_container">
          <div class="insertField"><input name="submitbtn" type="submit" class="insertBtn" value="INSERT"></div>
          <div class="clear"></div>
        </div>
      </form>
    </div>
  </div>
</div>