$(document).ready(function(){
    buildMenu();
    pagestyle();
    $(window).resize(pagestyle);

});

var pagestyle = function() {
    var iframe = $("#workspace");
    var h = $(window).height() - iframe.offset().top;
    var w = $(window).width() - iframe.offset().left;
    if(h < 300) h = 300;
    if(w < 973) w = 973;
    iframe.height(h);
    iframe.width(w);
}

function buildMenu(){
    var menuHtml = "";
    var subMenuHtml = "";
    for (var i = 0; i < menus.length; i++) {
        var menu = menus[i];
        menuHtml+='<li><a href="javascript:;" id="'+menu.id+'" onclick="openItem(\''+menu.id+'\')">'+menu.name+'</a></li>';

        var subMenus = menu.subMenu;
        subMenuHtml+='<h2 class="side-tit" id="'+menu.id+'_desc">'+menu.desc+'</h2>';
        subMenuHtml+='<ol id="'+menu.id+'_sub">';
        for (var j = 0; j < subMenus.length; j++) {
            var sub = subMenus[j];
            subMenuHtml+='<li><a href="javascript:;" url="'+APP_BASE+sub.url+'" id="'+sub.id+'" onclick="openItem(\''+menu.id+'\',\''+sub.id+'\')">'+sub.name+'</a></li>';
        }
        subMenuHtml+="</ol>";
    }
    $("#menu_top").append(menuHtml);
    $("#menu_left").append(subMenuHtml);
    setFirst(menus[0].id);
}

function openItem(args) {
    if (arguments.length==1) {
        var menu_id = arguments[0];
        setFirst(menu_id);
    }else if(arguments.length==2){
        var menu_id = arguments[0];
        var sub_menu_id = menu_id+"_sub";
        $("#"+sub_menu_id+">li").removeClass("cur");
        var sub_menu_target = arguments[1];
        $("#"+sub_menu_target).parent("li").addClass("cur");
        $("#workspace").attr("src",$("#"+sub_menu_target).attr("url"));
    }

}

function setFirst(menu_id){
    var sub_menu_id = menu_id +"_sub";
    var sub_menu_desc = menu_id +"_desc";
    $("#menu_top>li").removeClass("cur");
    $("#"+menu_id).parent("li").addClass("cur");
    $("#menu_left >ol").hide();
    $("#menu_left >h2").hide();
    $("#"+sub_menu_desc).show();
    $("#"+sub_menu_id).show();
    $("#"+sub_menu_id+">li").removeClass("cur");
    var target =$("#"+sub_menu_id+">li").first();
    target.addClass("cur");
    $("#workspace").attr("src",target.find("a").first().attr("url"));
}
