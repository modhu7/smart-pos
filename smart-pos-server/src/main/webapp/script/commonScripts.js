/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//uzzal(start)

$( init );
function init() {
  $('#left').append( $('.leftmenu') );
  $('#options').append( $('.dashboardContainer') );
}

$(document).ready(function () {
  $(".leftmenu_body ul li").hover(function () {
    $(this).css({
      //      'background-color' : '#F7F5FE',
      'background-color' : '#FFFFFF',
      'color' : 'Black'
    });
  }, function () {
    var cssObj = {
      'background-color' : '',
      'color' : '#23819C'
    }
    $(this).css(cssObj);
  });

});

$(document).ready(function () {
  $(".leftmenu_body ul li a").hover(function () {
    $(this).css({
      'color' : '#000000',
      'font-weight' : 'bolder',
      'font-size':'10pt'
    });
  }, function () {
    var cssObj = {
      'background-color' : '',
      'font-weight' : '',
      'font-size':'9pt',
      'color' : '#0066CC'
    }
    $(this).css(cssObj);
  });

});

function showonlyone(thechosenone) {
  var newboxes = document.getElementsByTagName("div");
  for(var x=0; x<newboxes.length; x++) {
    name = newboxes[x].getAttribute("name");
    if (name == 'newboxes') {
      if (newboxes[x].id == thechosenone) {
        newboxes[x].style.display = 'block';
      }
      else {
        newboxes[x].style.display = 'none';
      }
    }
  }
}

$(document).ready(function () {
  $("li").hover(
    function(){
      $(this).css({
        'color':'white'
      });
    });
  $('#nav li').hover(
    function () {
      //show its submenu
      $('ul', this).slideDown(100);
    },
    function () {
      //hide its submenu
      $('ul', this).slideUp(100);
    }
    );
});

$(document).ready(function () {
  $('.subMenuLevel_2').hide();
  $('#subMenuLevel_1_Link1').hover(
    function () {
      //show its submenu
      $('#transactionSubmenu', this).show(100);
    },
    function () {
      //hide its submenu
      $('#transactionSubmenu', this).hide(100);
    }
    );
  $('#subMenuLevel_1_Link2').hover(
    function () {
      //show its submenu
      $('#stockSubmenu', this).show(100);
    },
    function () {
      //hide its submenu
      $('#stockSubmenu', this).hide(100);
    }
    );
});

$(document).ready(function () {
  $(".navigationMenuBar li").hover(function () {
    $(this).css({
      'background' : 'url(/images/img13.gif)',
      'background-position':'bottom left',
      'font-weight' : '700'
    });
  }, function () {
    var cssObj = {
      'background': 'url(/images/img12.gif)',
      'background-position':'bottom left',
      'position':'relative',
      'font-weight' : '500',
      'background-color':	'#54C571',
      'z-index':'500'
    }
    $(this).css(cssObj);
  });
  $(".navigationMenuBar li a").hover(function () {
    $(this).css({
      'color' : '#000000',
      'font-weight' :'700'
    });
  }, function () {
    var cssObj = {
      'color' : '#FFFFFF',
      'font-weight' : 'bold'
    }
    $(this).css(cssObj);
  });
  $(".subMenuLevel_1 a").hover(function () {
    $(this).css({
      'background-color' : '#CCCCCC',
      'color' : 'aqua',
      'font-weight' : '700'
    });
  }, function () {
    var cssObj = {
      'background-color' : '#',
      'color' : '#FFFFFF',
      'font-weight' : '500'
    }
    $(this).css(cssObj);
  });
});
//uzzal(End)

function Orgpageselect()
{

  var className=document.getElementById("showList").className;
  if(className=="show")
  {
    document.getElementById("showList").className="hide";
    document.getElementById("create").className="show";
  }
  else
  {
    document.getElementById("showList").className="show";
    document.getElementById("create").className="hide";
  }
}