
$(document).ready(function(){
    buildMenu();
});

function buildMenu(){
	var menuHtml = "";
	for (var i = 0; i < menus.length; i++) {
		var menu  = menus[i];
		menuHtml += '<dl>';
		menuHtml += '<dt id="sidebar_goods_manage" ><i class="pngFix"></i>'+menu.name+'</dt>';
		menuHtml += '<dd style=""><ul>';
		var subMenus = menu.subMenu;
		for(var j = 0; j < subMenus.length; j++){
			var sub = subMenus[j];
			if(sub.name == "我的学院"){
				menuHtml += '<li><a class="normal" target="_blank" href="'+FRONT_BASE+sub.url + '?storeId=' + STORE_ID + '">'+sub.name+'</a></li>';
			} else {
				menuHtml += '<li><a class="normal" href="'+APP_BASE+sub.url+'">'+sub.name+'</a></li>';
			}
		}
		menuHtml += '</ul></dd></dl>';
	}
	$(".sidebar").append(menuHtml);
}