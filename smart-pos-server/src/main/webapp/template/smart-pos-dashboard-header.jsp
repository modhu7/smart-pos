<%-- 
    Document   : smart-pos-dashboard-header
    Created on : Oct 7, 2010, 3:48:23 PM
    Author     : uzzal
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="permission" value="31"></c:set>
<script type="text/javascript">
  $(document).ready(function () {
    var p = ${permission};
    if (p==31) {
      $('#').hide();
    }
    else if(p==30){
      $('#').hide();
    }
    else if(p==29){
      $('#').hide();
    }
    else if(p==20){
      $('#').hide();
    }
  });
</script>