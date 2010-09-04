<%-- 
    Document   : storeList
    Created on : Sep 2, 2010, 11:50:36 AM
    Author     : russel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${param['lang']!=null}">
  <fmt:setLocale scope="session" value="${param['lang']}"/>
</c:if>


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