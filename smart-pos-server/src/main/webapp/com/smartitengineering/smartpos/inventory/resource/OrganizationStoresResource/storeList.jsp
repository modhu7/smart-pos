<%-- 
    Document   : storeList
    Created on : Sep 2, 2010, 11:50:36 AM
    Author     : uzzal
--%>

<%--Uzzal-start-here--%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <div class="tableheadname_user">
      <label class="tablehead_label"><fmt:message key="org.tablehead2"/></label>
    </div>
    <div class="tableheadname_user">
      <label class="tablehead_label"><fmt:message key="org.tablehead3"/></label>
    </div>
    <div class="tableheadname_user">
      <label class="tablehead_label"><fmt:message key="org.tablehead3"/></label>
    </div>
  </div>

  <div class="row_of_list">
    <div class="storeOrgs_column">Smart It Engineering</div>
    <div class="storeName_column">Personal Computer</div>
    <div class="storeCode_column">sitel001</div>
  </div>

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
      <form action="http://localhost:9090/orgs" method="post" accept="application/json" enctype="" id="storeform">
        <div class="individual_field_container">
          <div class="form_label_pos" ><label><fmt:message key="str.inputlabel1"/></label></div>
          <div class="form_textField_pos"><input type="text" name="storeName"  class="textFieldPos"></div>
          <div class="clear"></div>
        </div>
        <div class="individual_field_container">
          <div class="form_label_pos"><label><fmt:message key="str.inputlabel2"/></label></div>
          <div class="form_textField_pos" align="left"><input type="text" name="code" Id="code"  class="textFieldPos"><label id ="alertlabel" class="alertlabel"></label></div>
          <div class="clear"></div>
        </div>
        <div class="individual_field_container">
          <div class="form_label_pos"><label><fmt:message key="str.inputlabel3"/></label></div>
          <div class="form_textField_pos"><input type="text" name="houseNo" class="textFieldPos"></div>
          <div class="clear"></div>
        </div>
        <div class="individual_field_container">
          <div class="form_label_pos"><label><fmt:message key="str.inputlabel4"/></label></div>
          <div class="form_textField_pos"><input type="text" name="street" class="textFieldPos"></div>
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