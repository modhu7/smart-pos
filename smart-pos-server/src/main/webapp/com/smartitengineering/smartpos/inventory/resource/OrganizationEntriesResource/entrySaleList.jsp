<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${param['lang']!=null}">
  <fmt:setLocale scope="session" value="${param['lang']}"/>
</c:if>

<div id="leftmenu_userlist_1" class="leftmenu">
  <div id="leftmenu_header_userlist_1" class="leftmenu_header"><label><fmt:message key="inv.prdentrytitle"/></label></div>
  <div id="leftmenu_body_userlist_1" class="leftmenu_body">
    <ul>
      <li><a href="javascript: Orgpageselect()"><fmt:message key="inv.prdentrylink"/></a></li>
    </ul>
  </div>
</div>


<div class="show" id="showList">
  <div id="title_of_organization_users" class="header_of_list">
    <label><fmt:message key="inv.prdentrytitle"/></label>
  </div>
  <div id="top_row" class="list_column_names">
    <div class="tableheadname_user">
      <label class="tablehead_label"><fmt:message key="inv.prdentryinput1"/></label>
    </div>
    <div class="tableheadname_user">
      <label class="tablehead_label"><fmt:message key="inv.prdentryinput3"/></label>
    </div>
    <div class="tableheadname_user">
      <label class="tablehead_label"><fmt:message key="inv.prdentryinput4"/></label>
    </div>
    <div class="tableheadname_user">
      <label class="tablehead_label"><fmt:message key="inv.prdentryinput5"/></label>
    </div>
    <div class="tableheadname_user">
      <label class="tablehead_label"><fmt:message key="inv.prdentryinput2"/></label>
    </div>
  </div>
  <div class="tablecontentname" id="tablecontentid"></div>

</div>
<div class="hide" id="create">
  <div id="title_of_entries" class="header_entry_form_pos">
  <label><fmt:message key="inv.prdentryhead" /></label>
  </div>
  <div id="form_EntriesEntry" class="entry_form_pos">
    <form action="" accept="application/json" enctype="" id="entryList" method="post">
      <div class="individual_field_container">
        <div class="form_label_pos"><label><fmt:message key="inv.prdentryinput1" /></label></div>
        <div class="form_textField_pos"><input type="text" name="entryDate" id="entryDate" class="textFieldPos"/></div>
        <div class="clear"></div>
      </div>
      <div class="individual_field_container">
        <div class="form_label_pos"><label>Entry Time:</label></div>
        <div class="form_textField_pos" style="float: left"><input type="text" name="appointment_start" id="appointment_start" size="8" class="textFieldPos" />
          <input type="hidden" id="jqtp_clock_hr" name="jqtp_clock_hr" size="2"/>
          <input type="hidden" id="jqtp_clock_min" name="jqtp_clock_min" size="2"/>
        </div>
        <div class="clear"></div>
      </div>
      <div class="individual_field_container">
        <div class="form_label_pos"><label><fmt:message key="inv.prdentryinput2" /></label></div>
        <div class="form_textField_pos"><input type="text" name="expiryDate" id="expiryDate" class="textFieldPos" /></div>
        <div class="clear"></div>
      </div>
      <div class="individual_field_container">
        <div class="form_label_pos"><label><fmt:message key="inv.prdentryinput3" /></label></div>
        <div class="form_textField_pos"><input type="text" name="product" id="product" class="textFieldPos" /></div>
        <div class="clear"></div>
      </div>
      <div class="individual_field_container">
        <div class="form_label_pos"><label><fmt:message key="inv.prdentryinput4" /></label></div>
        <div class="form_textField_pos"><input type="text" name="productCode" id="productCode" class="textFieldPos"/></div>
        <div class="clear"></div>
      </div>
      <div class="individual_field_container">
        <div class="form_label_pos"><label><fmt:message key="inv.prdentryinput5" /></label></div>
        <div class="form_textField_pos"><input type="text" name="quantity" id="quantity" class="textFieldPos" /></div>
        <div class="clear"></div>
      </div>
      <div class="individual_field_container">
        <div class="insertField"><input name="submitbtn" type="submit" class="insertBtn" value="INSERT"></div>
        <div class="clear"></div>
      </div>
    </form>
    <div id="jqtp_clock"></div>
  </div>
</div>