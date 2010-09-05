<%-- 
    Document   : productListHeader
    Created on : Sep 4, 2010, 6:11:04 PM
    Author     : russel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript">
  $(document).ready(function(){
    $("#productEntryForm").validate({
      rules: {
        name: "required",
        productCode: "required"
      }
    });
  });
</script>
