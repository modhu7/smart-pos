<%--
    Document   : dashboard
    Created on : Oct 2, 2010, 12:51:15 PM
    Author     : uzzal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<div id="moddiv" class="dashboardContainer">
  <ul id="nav" class="navigationParent">
    <li class="navigationChild"><a href="#" class="selected">Setup data</a>
      <ul>
        <li><a href="#" class="selected">Product</a></li>
        <li><a href="#">Store</a></li>
        <li><a href="#">Unit of measurement</a></li>
        <li><a href="#">Supplier</a></li>
      </ul>
      <div class="clear"></div>
    </li>
    <li class="navigationChild"><a href="#">Transaction</a>
      <ul>
        <li><a href="#">Inbound Purchase</a></li>
        <li><a href="#">Inbound Receive</a></li>
        <li><a href="#">Inbound Return</a></li>
        <li><a href="#">Outbound Sale</a></li>
        <li><a href="#">Outbound Transfer</a></li>
        <li><a href="#">Outbound Return</a></li>
      </ul>
      <div class="clear"></div>
    </li>

    <li class="navigationChild"><a href="#">Report</a>
      <ul class="submenu_2_Parent">
        <li id="submenu_2_1"><a href="#">Transaction Report</a>
          <ul>
            <li><a href="#">Store</a></li>
            <li><a href="#">Product</a></li>
            <li><a href="#">Supplier</a></li>
            <li><a href="#">Data Range</a></li>
          </ul>
        </li>
        <li id="submenu_2_2"><a href="#">Stock</a>
          <ul>
            <li><a href="#">Product Wise</a></li>
            <li><a href="#">Store wise</a></li>
          </ul>
        </li>
      </ul>
      <div class="clear"></div>
    </li>
    <li class="navigationChild"><a href="">Home</a></li>
  </ul>
  <div class="clear"></div>
</div>