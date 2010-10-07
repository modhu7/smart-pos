<%-- 
    Document   : InboundParchase
    Created on : Sep 23, 2010, 11:47:18 AM
    Author     : atiqul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Inbound Purchase</title>
  </head>
  <body>
    <div class="toRoundBox">
      
      <div id="form_EntriesEntry" class="entry_form_pos">


        <form action="" accept="application/json" enctype="" id="entryList" method="post">
          <div class="individual_field_container">
            <div class="form_label_pos"><label><fmt:message key="inv.prdentryinput1" /></label></div>
            <div class="form_textField_pos"><input type="text" name="entryDate" id="entryDate" class="textFieldPos"></div>
            <div class="clear"></div>
          </div>
          <div class="individual_field_container">
            <div class="form_label_pos"><label>Entry Time:</label></div>
            <div class="form_textField_pos" style="float: left"><input type="text" id="appointment_start" size="8" /></div>
            <div class="clear"></div>
          </div>
          <div class="individual_field_container">
            <div class="form_label_pos"><label><fmt:message key="inv.prdentryinput2" /></label></div>
            <div class="form_textField_pos"><input type="text" name="expiryDate" id="expiryDate" class="textFieldPos"></div>
            <div class="clear"></div>
          </div>
          <div class="individual_field_container">
            <div class="form_label_pos"><label><fmt:message key="inv.prdentryinput3" /></label></div>
            <div class="form_textField_pos"><input type="text" name="product" id="product" class="textFieldPos"></div>
            <div class="clear"></div>
          </div>
          <div class="individual_field_container">
            <div class="form_label_pos"><label><fmt:message key="inv.prdentryinput4" /></label></div>
            <div class="form_textField_pos"><input type="text" name="productCode" id="productCode" class="textFieldPos"></div>
            <div class="clear"></div>
          </div>
          <div class="individual_field_container">
            <div class="form_label_pos"><label><fmt:message key="inv.prdentryinput5" /></label></div>
            <div class="form_textField_pos"><input type="text" name="quantity" id="quantity" class="textFieldPos"></div>
            <div class="clear"></div>
          </div>
          <div class="individual_field_container">
            <div class="insertField"><input name="submitbtn" type="submit" class="insertBtn" value="INSERT"></div>
            <div class="clear"></div>
          </div>
        </form>
      </div>
    </div>
  </body>
</html>
