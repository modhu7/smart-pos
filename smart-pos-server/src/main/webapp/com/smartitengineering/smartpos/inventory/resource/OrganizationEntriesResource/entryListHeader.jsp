<%-- 
    Document   : entryListHeader
    Created on : Sep 5, 2010, 11:29:38 AM
    Author     : russel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<<<<<<< HEAD
<c:choose>
=======

>>>>>>> saumitra/master
  <c:when test="${empty param.count}">
    <c:set var="qParam" value="" />
  </c:when>
  <c:otherwise>
    <c:set var="qParam" value="?count=${param.count}" />
  </c:otherwise>
</c:choose>

<<<<<<< HEAD
<style media="screen" type="text/css">  
  .title { color: #a1a1a1; font-style: italic; }
  .active {color: black; font-style: normal;}
</style>
=======

 <script type="text/javascript">

    $(document).ready(function(){
    var url = "http://localhost:9090/orgs/frags${qParam}";
    $("#tablecontentid").pagination(url, "paginationLinks");
    $("#wrong").hide();
>>>>>>> saumitra/master

  $(document).ready(function(){
    $("#entryDate")[0].value = "       --choose a date--";
    $("#appointment_start")[0].value = "      --hh:mm am/pm--";
    $("#expiryDate")[0].value = "      --Choose a date--";
    $("#product")[0].value = "     --Insert your Product name--";
    $("#productCode")[0].value = "      --Set product code--";
    $("#quantity")[0].value = "      --How much/How many--";

    if($(".textFieldPos").val()== $(".textFieldPos")[0].value){      
      $(".textFieldPos").addClass("title");
    }


    $(".textFieldPos").focus(function(){      
      if ($(this).val() == $(this)[0].value){
        $(this).removeClass("title");
        $(this).addClass("active");
        $(this).val("");
      }
    });
    $(".textFieldPos").blur(function(){
      if ($(this).val() == ""){        
        $(this).addClass("title");
        $(this).val($(this)[0].value);
      }
    });
    $(".textFieldPos").blur();
   
    $('#entryDate').datepicker({
      changeMonth: true,
      changeYear: true,
      dateFormat: 'DD, MM d, yy'
    });
    $("#expiryDate").datepicker({
      changeMonth: true,
      changeYear: true,
      dateFormat: 'DD, MM d, yy'
    });
    
    $("#jqtp_clock").jqtp_realtime();    
    $("#appointment_start").click(function(){
      $("#appointment_start").jqtp_object();
      $("#jqtp_clock").jqtp_getTime();
    });
    
    $("#entryList").validate({
      rules: {
        entryDate: {required: true, date: true},
        expiryDate: {required: true, date: true},
        productCode: "required",
        quantity: "required",
        appointment_start: "required"
      },
      messages:{
        entryDate: "please enter the date of entry",
        expiryDate: "please mention the product expire date",
        productCode: "please enter the code of this product",
        quantity: "please mention the quantity of the entry product",
        appointment_start: "please enter the time of this entry"
      }
    });

    $(".insertBtn").click(function(){

      $(".textFieldPos").each(function(){
        if ($(this).hasClass("active") == false){
          $(this).val("");     
        }
      });
    });
  });
</script>

