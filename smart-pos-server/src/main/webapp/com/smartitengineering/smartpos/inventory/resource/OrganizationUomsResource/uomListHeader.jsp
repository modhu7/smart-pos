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

  <%--(function ($) {
    // custom css expression for a case-insensitive contains()
    jQuery.expr[':'].Contains = function(a,i,m){
      return (a.textContent || a.innerText || "").toUpperCase().indexOf(m[3].toUpperCase())>=0;
    };


    function listFilter(header, list) { // header is any element, list is an unordered list
      // create and add the filter form to the header
      var form = $("<form>").attr({"class":"filterform","action":"#"}),
      input = $("<input>").attr({"class":"filterinput","type":"text","style":"float:left"});
      $(form).append(input).appendTo(header);
      //var link=$("#list").children('div').attr('id').each(function(){alert(link);});
      $(input)
      .change( function () {
        var filter = $(this).val();
        //$("<div>").attr("id", $(list)).each(function(){
          if(filter) {
            // this finds all links in a list that contain the input,
            // and hide the ones not containing the input while showing the ones that do
            $(list).find("a:not(:Contains(" + filter + "))").parent().slideUp();
            $(list).find("a:Contains(" + filter + ")").parent().slideDown();
          } else {
            $(list).find("div").slideDown();
          }
          return false;
        //});
      })
      .keyup( function () {
        // fire the above change event after every letter
        $(this).change();
      });
    }


  //ondomready
  $(function () {
    listFilter($("#title_of_organization_uom"), $("#uomRow"));
  });
}(jQuery));--%>



  $(document).ready(function(){
    var list;
    var i = 0;
    var total= new Array;
    var id;

    $("#list").children('li').children("div").children('div').each(function(){
      list = $(this).children('label').html();
      //id= $(this).children('a').attr('id');alert(id)
      if(list!=null){
      
        total[i++]=list;
        alert(total);
      
      }
      
    });
    
    $("#search").autocomplete(total).result(function(event, item, formatted){
      var t= $("#search").val();
      t = t.substring(t.lastIndexOf("(")+1, t.length - 1);
      $("#search-form").attr('action', 'uoms/name/'+t);
      $(this).parents().find('form:first').submit();
    });
    $('#filterText').keyup(function(){
      var filterByValue = $("#filterText").val();      
      $("#list").children('li').children('div').filter(function(){
        return(!$(this).text().match(filterByValue));
      }).parent().slideUp();
      $("#list").children('li').children('div').filter(function(){
        return($(this).text().match(filterByValue));
      }).parent().slideDown();
    });
                   
    $('select[name = selectCriterion]').val("");
    $('select[name = system]').val("");
    $("#symbol")[0].value= "   -----Set Your Unit Symbol    i.e;Kg-----";
    $("#id")[0].value= "   -----Set a unique id-----";
    $("#longName")[0].value= "   -----Full Name of the Unit   i.e;Kilogram-----"

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

