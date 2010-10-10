<%--
    Document   : dashboard
    Created on : Oct 2, 2010, 12:51:15 PM
    Author     : uzzal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@page import="com.smartitengineering.user.domain.Privilege"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">

<div id="moddiv" class="dashboardContainer">
  <ul id="nav" class="navigationMenuBar">
    <li class="menuTabs"><a href="" class="selected">Show List</a>
      <ul class="subMenuLevel_1">
        <li id="entryList"><a href="/orgs/sn/${orgInitial}/inv/entries">Entry</a></li>
        <li id="productList"><a href="/orgs/sn/${orgInitial}/inv/prods" class="selected">Product</a></li>
        <li id="storeList"><a href="/orgs/sn/${orgInitial}/inv/stores">Store</a></li>
        <li id="uomList"><a href="/orgs/sn/${orgInitial}/inv/uoms">Unit of measurement</a></li>
        <li id="supplierList"><a href="#">Supplier</a></li>
      </ul>
      <div class="clear"></div>
    </li>
    <li class="menuTabs"><a href="">Setup data</a>
      <ul class="subMenuLevel_1">
        <li id="entryInsert"><a href="javascript: showonlyone('newboxes4')">Entry</a></li>
        <li id="productInsert"><a href="/orgs/sn/${orgInitial}/inv/prods" class="selected">Product</a></li>
        <li id="storeInsert"><a href="/orgs/sn/${orgInitial}/inv/stores">Store</a></li>
        <li id="uomInsert"><a href="/orgs/sn/${orgInitial}/inv/uoms">Unit of measurement</a></li>
        <li id="supplierInsert"><a href="#">Supplier</a></li>
      </ul>
      <div class="clear"></div>
    </li>
    <li class="menuTabs"><a href="#">Transaction</a>
      <ul class="subMenuLevel_1">
        <li id="inPurchase"><a href="/orgs/sn/${orgInitial}/inv/entries/purchase">Inbound Purchase</a></li>
        <li id="inReceive"><a href="/orgs/sn/${orgInitial}/inv/entries/transferin">Inbound Receive</a></li>
        <li id="inReturn"><a href="/orgs/sn/${orgInitial}/inv/returnin">Inbound Return</a></li>
        <li id="outSale"><a href="/orgs/sn/${orgInitial}/inv/sale">Outbound Sale</a></li>
        <li id="outTransfer"><a href="/orgs/sn/${orgInitial}/inv/transferout">Outbound Transfer</a></li>
        <li id="outReturn"><a href="/orgs/sn/${orgInitial}/inv/returnout">Outbound Return</a></li>
      </ul>
      <div class="clear"></div>
    </li>
    <li class="menuTabs"><a href="#">Report</a>
      <ul class="subMenuLevel_1">
        <li id="subMenuLevel_1_Link1" class="subMenuLevel_1_Link"><a href="#">Transaction Report    &nbsp;&nbsp;&nbsp;<label class="arrow">></label></a>
          <div class="subMenuLevel_2" id="transactionSubmenu">
            <li id="store"><a href="#">Store</a></li>
            <li id="product"><a href="#">Product</a></li>
            <li id="supplyer"><a href="#">Supplier</a></li>
            <li id="dataRange"><a href="#">Data Range</a></li>
          </div>
          <div class="clear"></div>
        </li>

        <li id="subMenuLevel_1_Link2" class="subMenuLevel_1_Link"><a href="#">Stock    &nbsp;&nbsp;&nbsp;<label class="arrow">></label></a>
          <div class="subMenuLevel_2" id="stockSubmenu">
            <li id="productWise"><a href="#">Product Wise</a></li>
            <li id="storeWise"><a href="#">Store wise</a></li>
          </div>
        </li>
      </ul>
      <div class="clear"></div>
    </li>
    <li class="menuTabs"><a href="/dashboard">Dashboard</a></li>
  </ul>
  <div class="clear"></div>
</div>
<%--<div id="smartposDashboardBodyId" class="smartposDashboardBody">
  <div><b>Dashboard-------Activity will be here...</b></div>
</div>--%>