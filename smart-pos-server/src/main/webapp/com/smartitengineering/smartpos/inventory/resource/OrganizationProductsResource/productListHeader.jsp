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
    $(".insertBtn").submit(function(){
      $.ajax({
        type: "POST",
        url: "http://russel:9090/orgs/sn/${orgInitial}/users/un/"+usn,
        dataType: "xml"
      })
    });
  });
</script>
