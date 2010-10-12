<%-- 
    Document   : uomDetails
    Created on : Sep 6, 2010, 11:55:11 AM
    Author     : russel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.smartitengineering.smartpos.inventory.api.UnitOfMeasurement" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="java.util.Collection"%>

<c:if test="${param['lang']!=null}">
  <fmt:setLocale scope="session" value="${param['lang']}"/>
</c:if>


<div id="leftmenu_userdeatils_1" class="leftmenu">
  <div id="leftmenu_header_userdeatils_1" class="leftmenu_header"><label>Individual-User</label></div>
  <div id="leftmenu_body_userdeatils_1" class="leftmenu_body">
    <ul>
      <li><a href="/orgs/sn/${orgInitial}/inv/uoms"><fmt:message key="inv.uomtabletitle"/></a></li>
      <li><a href="javascript: Orgpageselect()"><fmt:message key="inv.uomeditlink"/></a></li>

    </ul>
  </div>
</div>

<div id="showList" class="show">
  <div id="individual_user_details_header"  class="header_details_info"><label><c:out value="${it.id}"></c:out></label></div>
  <div id="individual_user_details_content" class="content_details_info">
    <div class="individual_details_label"><label><fmt:message key="inv.uominput5"/></label></div>
    <div class="individual_details_data"><label>${it.uomType}</label></div>
    <div class="clear"></div>
    <div class="individual_details_label"><label><fmt:message key="inv.uominput2"/></label></div>
    <div class="individual_details_data"><label>${it.id.id}</label></div>
    <div class="clear"></div>

    <form method="POST" action ="http://localhost:10090/inv/uoms/name/${it.id.id}/delete" accept="application/json" id="organizationform">
      <input type="hidden" name="id" value="${it.id.id}" class="textField" id="id">
      <input type="hidden" name="longName" value="${it.longName}" class="textField" id="id">
      <input type="hidden" name="uomType" value="${it.uomType}" class="textField" id="id">
      <input type="hidden" name="symbol" value="${it.symbol}" class="textField" id="id">
      <input type="hidden" name="uomSystem" value="${it.uomSystem}" class="textField" id="id">
      <div class="clear"></div>
      <div class="btnfield"><input type="submit" value="DELETE" name="submitbtn" class="submitbtn"></div>
      <div class="clear"></div>

    </form>
  </div>
</div>


<div id="create" class="hide">
  <div id="header_organization_uom" class="header_entry_form"><label id="header_uom_label"><c:out value="${it.id.id}"></c:out><fmt:message key="inv.uomedittablehead1" /></label></div>

  <fmt:message key="inv.uomeditbtn" var="submitbtn"/>
  <div class="toRoundBox">
    <div id="form_organizationentry" class="entry_form">
      <form method="POST" action ="http://localhost:10090/inv/uoms/name/${it.id.id}/update" accept="application/json" id="userEditForm">
        <input type="hidden" name="id" value="${it.id.id}" class="textField" id="id">
        <div style="clear: both"></div>
        <div class="form_label"><label><fmt:message key="inv.uominput6"/></label></div>
        <div class="form_textField">
          <select id="uomType" name="uomType">
            <option value="hight">hight</option>
            <option value="weight">weight</option>
            <option value="length">length</option>
            <option value="area">area</option>
            <option value="volume">volume</option>
          </select>
        </div>
        <div style="clear: both"></div>
        <div class="form_label"><label><fmt:message key="inv.uominput7"/></label></div>
        <div class="form_textField"><input type="text" name="longName" class="textField" id="longName" value="${it.longName}"></div>
        <div style="clear: both"></div>
        <div class="form_label"><label><fmt:message key="inv.uominput3"/></label></div>
        <div class="form_textField"><input type="text" name="symbol" class="textField" id="symbol" value="${it.symbol}"></div>
        <div style="clear: both"></div>
        <div class="form_label"><label><fmt:message key="inv.uominput4"/></label></div>
        <div class="form_textField">
          <select id="uomSystem" name="uomSystem">
            <option value="SI">SI</option>
            <option value="Metric">Metric</option>
            <option value="Local">Local</option>
          </select>
        </div>
        <div style="clear: both"></div>
        <div class="btnfield"><input type="submit" value="UPDATE" name="submitbtn" class="submitbtn" id="edit"></div>
        <div class="clear"></div>
      </form>
    </div>
  </div>
</div>