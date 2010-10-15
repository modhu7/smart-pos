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
    $("#id")[0].value = "  --Set Product Code--";
    $("#description")[0].value = "  --Description of the Product--";
    $("#skuId")[0].value = "  --Store Keeping Unit--"
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
        id: "required",
        skuId: "required"
      },
      messages:{
        name: "please enter a product name",
        id: "please set a code for this product",
        skuId: "please set a valid unit for this product "
      }
    });
    $("#skuId").keyup(function(){
      var uom = $(this).val();
      $.ajax({
        type: 'GET',
        url: 'http://localhost:10090/inv/uoms',
        data:uom,
        success:function(){          
          $(this).autocomplete(uom);
        },
        error:function(){
          alert(000);
        }
      });
    });

    var name = $("#name").val();
    var productCode = $("#id").val();
    var description = $("#description").val();
    var sku = $("#skuId").val();
    var dataString = 'name='+ name +'&id='+ productCode +'&description='+ description +'&skuId='+ sku;
    
    $(".insertBtn").click(function(){
      $(".textFieldPos").each(function(){
        if($(this).hasClass("active")==false){
          $(this).val("");
        }
      });
      alert(dataString)
      $.ajax({
        type: "POST",
        url: "http://localhost:10090/orgs/sn/${orgInitial}/inv/prods"+name,
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
