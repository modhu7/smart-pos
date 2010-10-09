<%-- 
    Document   : uomListHeader
    Created on : Sep 5, 2010, 4:04:09 PM
    Author     : russel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>


<style media="screen" type="text/css">
  .title { color: #a1a1a1; font-style: italic; }
  .active {color: black; font-style: normal;}
</style>

<script type="text/javascript">

  $(document).ready(function(){ 
    
    $('select[name = selectCriterion]').val("");
    $('select[name = system]').val("");
    $("#symbol")[0].value= "   -----Set Your Unit Symbol    i.e;Kg-----";    
    $("#id")[0].value= "   -----Full Name of the Unit    i.e;Kilogram-----"

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


    $("#uomEntryForm").validate({
      rules: {
        id: "required",
        symbol: "required",
        uomSystem: "required",
        uomType: "required"
      },
      messages:{
        uomType: "you should have to select a criterion",
        symbol: "please enter symbol for this unit",
        uomSystem: "please select a system",
        id: "please enter the full name of the unit"
      }
    });    

    $(".insertBtn").click(function(){
      $("#uomType")[0].value = $('select[name = selectCriterion]').val();
      $("#uomType").addClass("active");
      $("#uomSystem")[0].value = $('select[name = system]').val();
      $("#uomSystem").addClass("active");
      $(".textFieldPos").each(function(){
        if ($(this).hasClass("active") == false){
          $(this).val("");          
        }
        
      });
    });
  });
</script>