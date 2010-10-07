<%-- 
    Document   : productListHeader
    Created on : Sep 4, 2010, 6:11:04 PM
    Author     : russel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style media="screen" type="text/css">
  .title { color: #a1a1a1; font-style: italic; }
  .active {color: black; font-style: normal;}
</style>

<script type="text/javascript">
  $(document).ready(function(){
    $("#name")[0].value = "  --Insert Product Name--";
    $("#productCode")[0].value = "  --Set Product Code--";
    $("#description")[0].value = "  --Description of the Product--";
    $("#sku")[0].value = "  --Store Keeping Unit--"
    $(".textFieldPos").each(function(){
    if($(this).val()== $(this)[0].value){
      $(this).addClass("title");
    }

    $(this).focus(function(){
      if ($(this).val() == $(this)[0].value){
        $(this).removeClass("title");
        $(this).addClass("active");
        $(this).val("");
      }
    });

    $(this).blur(function(){
      if($(this).val()== ""){
        $(this).removeClass("active");
        $(this).val($(this)[0].value);
        $(this).addClass("title");
      }
    });
    $(this).blur();
    });
    $("#productEntryForm").validate({
      rules: {
        name: "required",
        productCode: "required",
        sku: "required"
      },
      messages:{
        name: "please enter a product name",
        productCode: "please set a code for this product",
        sku: "please set a valid unit for this product "
      }
    });
    var name = $("#name").val();
    var productCode = $("#productCode").val();
    var description = $("#description").val();
    var sku = $("#sku").val();
    var dataString = 'name='+ name +'&productCode='+ productCode +'&description='+ description +'&sku='+ sku;
    
    $(".insertBtn").click(function(){
      $(".textFieldPos").each(function(){
        if($(this).hasClass("active")==false){
          $(this).val("");
        }
      });
      alert(dataString)
      $.ajax({
        type: "POST",
        url: "http://russel:9090/orgs/sn/${orgInitial}/inv/prds"+name,
        dataType: "xml",
        data: dataString,
        success: function(xhr){
          alert(usn);
        },
        error: function(xhr){
          alert(1);
        }
      });      
    });
  });
</script>
