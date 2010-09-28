<%-- 
    Document   : entryListHeader
    Created on : Sep 5, 2010, 11:29:38 AM
    Author     : russel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript">
  $(document).ready(function(){   
    $("#entryList").validate({
      rule:{
        entryDate: "required",
        expiryDate: "required",
        productCode: "required",
        quantity: "required"
      }
    });
  });
</script>