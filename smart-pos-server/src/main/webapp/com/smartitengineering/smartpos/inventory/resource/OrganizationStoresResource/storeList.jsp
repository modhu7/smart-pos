<%-- 
    Document   : storeList
    Created on : Sep 2, 2010, 11:50:36 AM
    Author     : uzzal
--%>

<%--Uzzal-start-here--%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">

<div id="leftmenu_storelist_1" class="leftmenu">
  <div id="leftmenu_header_storelist_1" class="leftmenu_header"><label>Store Creation</label></div>
  <div id="leftmenu_body_storelist_1" class="leftmenu_body">
    <ul>
      <li><a href="javascript: Orgpageselect()">Create</a></li>
    </ul>
  </div>
</div>

<c:if test="${param['lang']!=null}">
  <fmt:setLocale scope="session" value="${param['lang']}"/>
</c:if>

<div class="show" id="showList">
  <div id="title_of_organization" class="header_of_list">
    <label><fmt:message key="str.title"/></label>
  </div>
  <div id="top_row" class="list_column_names">
    <input type="text" id="filterText" style="float: left"/>
    <c:set var="str" scope="page" value="${it}"></c:set>
    <div class="tableheadname_user">
      <label class="tablehead_label"><fmt:message key="org.tablehead2"/></label>
    </div>
    <div class="tableheadname_user">
      <label class="tablehead_label"><fmt:message key="org.tablehead3"/></label>
    </div>
    <div class="tableheadname_user">
      <label class="tablehead_label"><fmt:message key="org.tablehead3"/></label>
    </div>

    <div class="">
      <form action="" onsubmit="return false" method="GET" id="search-form">
        <input id="search"/>
      </form>
    </div>

  </div>

  <%--<div class="row_of_list">
    <div class="storeOrgs_column">Smart It Engineering</div>
    <div class="storeName_column">Personal Computer</div>
    <div class="storeCode_column">sitel001</div>
  </div>--%>

  <ul id="list">

    <c:forEach varStatus="status" items="${it}">
      <li>
        <div id="strRow${status.index}" class="row_of_list">
          <div id="strName${uom[status.index]}" class="strName_column">${str[status.index].name}</div>
          <div id="strAddress${uom[status.index]}" class="strAddress_column">${str[status.index].address}</div>
          <div id="strCode${status.index}" class="id_column"><label>(${str[status.index].id})</label></div>
          <div id="${status.index}"><a href="uoms/name/${str[status.index].id}">edit</a></div>
        </div>
      </li>
    </c:forEach>
  </ul>

  <%--<div class="tablecontentname" id="tablecontentid"></div>--%>
</div>

<%--uzzal-ends-here--%>

<%--Uzzal--%>

<div class="hide" id="create">
  <div class="toRoundBox">
    <div id="header_store" class="header_entry_form_pos">
      <label>Store Entry Hardcored</label>
    </div>
    <div id="form_storeEntry" class="entry_form_pos">
      <form action="/orgs/sn/${orgInitial}/inv/stores" method="post" accept="application/json" enctype="" id="storeform">
        <div class="individual_field_container">
          <div class="form_label_pos" ><label><fmt:message key="str.inputlabel1"/></label></div>
          <div class="form_textField_pos"><input type="text" name="name" id="name"  class="textFieldPos"></div>
          <div class="clear"></div>
        </div>
        <div class="individual_field_container">
          <div class="form_label_pos"><label><fmt:message key="str.inputlabel2"/></label></div>
          <div class="form_textField_pos" align="left"><input type="text" name="id" Id="id"  class="textFieldPos"><label id ="alertlabel" class="alertlabel"></label></div>
          <div class="clear"></div>
        </div>
        <div class="individual_field_container">
          <div class="form_label_pos"><label><fmt:message key="str.inputlabel3"/></label></div>
          <div class="form_textField_pos"><input type="text" name="houseNo" class="textFieldPos"></div>
          <div class="clear"></div>
        </div>
        <div class="individual_field_container">
          <div class="form_label_pos"><label><fmt:message key="str.inputlabel4"/></label></div>
          <div class="form_textField_pos"><input type="text" name="streetAddress" class="textFieldPos"></div>
          <div class="clear"></div>
        </div>
        <div class="individual_field_container">
          <div class="form_label_pos"><label><fmt:message key="str.inputlabel5"/></label></div>
          <div class="form_textField_pos"><input type="text" name="city" class="textFieldPos"></div>
          <div class="clear"></div>
        </div>
        <div class="individual_field_container">
          <div class="form_label_pos"><label><fmt:message key="str.inputlabel6"/></label></div>
          <div class="form_textField_pos"><input type="text" name="state" class="textFieldPos"></div>
          <div class="clear"></div>
        </div>
        <div class="individual_field_container">
          <div class="form_label_pos"><label><fmt:message key="str.inputlabel7"/></label></div>
          <div class="form_textField_pos"><input type="text" name="country" class="textFieldPos"></div>
          <div class="clear"></div>
        </div>
        <div class="individual_field_container">
          <div class="form_label_pos"><label><fmt:message key="str.inputlabel8"/></label></div>
          <div class="form_textField_pos"><input type="text" name="zip" class="textFieldPos"></div>
          <div class="clear"></div>
        </div>
        <div class="individual_field_container">
          <div class="form_label_pos"><label><fmt:message key="str.inputlabel9"/></label></div>
          <div class="form_textField_pos"><input type="text" name="phone" class="textFieldPos"></div>
          <div class="clear"></div>
        </div>
        <div class="individual_field_container">
          <div class="insertField"><input type="submit" value="INSERT" name="submitbtn" onclick="isEmpty()" onmouseover="onmouse_over()" id="submit" class="insertBtn"></div>
          <div class="clear"></div>
        </div>
      </form>
    </div>
  </div>
</div>
<%--Uzzal-Ends Here--%>