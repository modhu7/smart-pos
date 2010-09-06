<%-- 
    Document   : storeListHeader
    Created on : Sep 4, 2010, 12:08:20 PM
    Author     : uzzal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
  <c:when test="${empty param.count}">
    <c:set var="qParam" value="" />
  </c:when>
  <c:otherwise>
    <c:set var="qParam" value="?count=${param.count}" />
  </c:otherwise>
</c:choose>


<script type="text/javascript">
  
  $(document).ready(function(){    
    $('#storeform').validate({
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

    var url = "/orgs/sn/${orgInitial}/stores/frags${qParam}";
    $("#tablecontentid").pagination(url,"linkcontainer");
    $("#wrong").hide();
    $("#code").blur(function(){
      var cde =$("#code").val();
      $.ajax({
        type: "GET",
        url: "http://localhost:9090/orgs/sn/${orgInitial}/users/un/"+usn,
        dataType: "xml",
        success: function(xhr){
          $("#wrong").show();
          $("#alertlabel").html('Store Code is not unique: try another');
        },
        error: function(xhr){
          $("#wrong").hide();
          $("#alertlabel").html('');
        }
      });
    });
  });
</script>
