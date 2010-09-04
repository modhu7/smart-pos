<%-- 
    Document   : storeListHeader
    Created on : Sep 3, 2010, 11:48:24 AM
    Author     : russel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<script type="text/javascript" src="/script/user-validation.js"></script>
<script text="text/javascript">
  $(document).ready(function(){
    $("#storeform").validate({
      rules:{
        storeName: "required",
        code: "required",
        houseNo: "required",
        street: "required",
        city: "required",
        state: "required",
        country: "required",
        zip: "required",
        phone: "required"
      }
    });
  });
</script>
