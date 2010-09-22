<%-- 
    Document   : entryListHeader
    Created on : Sep 5, 2010, 11:29:38 AM
    Author     : russel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript">
  $(document).ready(function(){
   
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
    $("#jqtp_clock").jqtp();
    $("#jqtp_clock").jqtp_realtime();
    $("#restart_btn").click(function () {
      $("#jqtp_clock").jqtp_realtime();
    });
    $("#appointment_start").click(function(){
      $("#appointment_start").jqtp_object();
      $("#jqtp_clock").jqtp_getTime();
    });
    $("#entryList").validate({
      rule: {
        entryDate: "required",
        expiryDate: "required",
        productCode: "required",
        quantity: "required",
        jqtp_clock: "required"
      }
    });    
  });
</script>