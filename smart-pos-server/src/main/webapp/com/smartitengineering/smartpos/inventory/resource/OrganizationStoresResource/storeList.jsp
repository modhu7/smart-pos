<%-- 
    Document   : storeList
    Created on : Sep 2, 2010, 11:50:36 AM
    Author     : russel
--%>

<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@page import="com.smartitengineering.user.domain.Organization"%>--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">

<div id="leftmenu_" class="leftmenu">
  <div id="leftmenu_header_" class="leftmenu_header"><label>Organization-Creation</label></div>
  <div id="leftmenu_body_" class="leftmenu_body">
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
    <label><fmt:message key="org.title"/></label>
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

<div id="form_storeEntry" class="entry_form">
  <form action="http://localhost:9090/orgs" method="post" accept="application/json" enctype="" id="storeform">
    <div class="form_label" ><label><fmt:message key="str.inputlabel1"/></label></div>
    <div class="form_textField"><input type="text" name="storeName"  class="textField"></div>
    <div class="clear"></div>
    <div class="form_label"><label><fmt:message key="str.inputlabel2"/></label></div>
    <div class="form_textField" align="left"><input type="text" name="code" Id="code"  class="textField"><label id ="alertlabel" class="alertlabel"></label></div>
    <div class="clear"></div>
    <div class="form_label"><label><fmt:message key="str.inputlabel3"/></label></div>
    <div class="form_textField"><input type="text" name="houseNo" class="textField"></div>
    <div class="clear"></div>
    <div class="form_label"><label><fmt:message key="str.inputlabel4"/></label></div>
    <div class="form_textField"><input type="text" name="street" class="textField"></div>
    <div class="clear"></div>
    <div class="form_label"><label><fmt:message key="str.inputlabel5"/></label></div>
    <div class="form_textField"><input type="text" name="city" class="textField"></div>
    <div class="clear"></div>
    <div class="form_label"><label><fmt:message key="str.inputlabel6"/></label></div>
    <div class="form_textField"><input type="text" name="state" class="textField"></div>
    <div class="clear"></div>
    <div class="form_label"><label><fmt:message key="str.inputlabel7"/></label></div>
    <div class="form_textField"><input type="text" name="country" class="textField"></div>
    <div class="clear"></div>
    <div class="form_label"><label><fmt:message key="str.inputlabel8"/></label></div>
    <div class="form_textField"><input type="text" name="zip" class="textField"></div>
    <div class="clear"></div>
    <div class="form_label"><label><fmt:message key="str.inputlabel9"/></label></div>
    <div class="form_textField"><input type="text" name="phone" class="textField"></div>
    <div class="clear"></div>
    <div class="btnfield"><input type="submit" value="SUBMIT" name="submitbtn" onclick="isEmpty()" onmouseover="onmouse_over()" id="submit" class="submitbtn"></div>
    <div class="clear"></div>
  </form>
</div>