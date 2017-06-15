// 顶部菜单
function dropMenu(obj) {
	$(obj).each(function() {
		var theSpan = $(this);
		var theMenu = theSpan.find(".dropmenu");
		var tarHeight = theMenu.height();
		theMenu.css({
			height: 0,
			opacity: 0
		});
		theSpan.hover(function() {
			$(this).addClass("dropmenu-hover");
			theMenu.stop().show().animate({
				height: tarHeight,
				opacity: 1
			},
			20);
		},
		function() {
			$(this).removeClass("dropmenu-hover");
			theMenu.stop().animate({
				height: 0,
				opacity: 0
			},
			20,
			function() {
				$(this).css({
					display: "none"
				});
			});
		});
	});
}
$(document).ready(function() {
	dropMenu(".dropmenuBox");
});
// 购物车
function dropCart(obj) {
	$(obj).each(function() {
		var theSpan = $(this);
		var theCart = theSpan.find(".cart-pop");
		var tarHeight = theCart.height();
		theCart.css({
			height: 0,
			opacity: 0
		});
		theSpan.hover(function() {
			$(this).addClass("cart-hover");
			theCart.stop().show().animate({
				height: tarHeight,
				opacity: 1
			},
			20);
		},
		function() {
			$(this).removeClass("cart-hover");
			theCart.stop().animate({
				height: 0,
				opacity: 0
			},
			200,
			function() {
				$(this).css({
					display: "none"
				});
			});
		});
	});
}
$(document).ready(function() {
	dropCart(".cart-mod");
});
// 快捷通道
function dropQuick(obj) {
	$(obj).each(function() {
		var theSpan = $(this);
		var theQuick = theSpan.find(".quick-pop");
		var tarWidth = theQuick.width();
		theQuick.css({
			width: 0,
			opacity: 0
		});
		theSpan.hover(function() {
			$(this).addClass("quick-hover");
			theQuick.stop().show().animate({
				width: tarWidth,
				opacity: 1
			},
			20);
		},
		function() {
			$(this).removeClass("quick-hover");
			theQuick.stop().animate({
				width: 0,
				opacity: 0
			},
			200,
			function() {
				$(this).css({
					display: "none"
				});
			});
		});
	});
}
$(document).ready(function() {
	dropQuick(".quick-mod");
});
// 学院概括
function dropShop(obj) {
	$(obj).each(function() {
		var theSpan = $(this);
		var theShop = theSpan.find(".shop-pop");
		var tarHeight = theShop.height();
		theShop.css({
			height: 0,
			opacity: 0
		});
		theSpan.hover(function() {
			$(this).addClass("shop-hover");
			theShop.stop().show().animate({
				height: tarHeight,
				opacity: 1
			},
			20);
		},
		function() {
			$(this).removeClass("shop-hover");
			theShop.stop().animate({
				height: 0,
				opacity: 0
			},
			200,
			function() {
				$(this).css({
					display: "none"
				});
			});
		});
	});
}
$(document).ready(function() {
	dropShop(".shop-mod");
});

<!-- TAB切换 -->
function showDiv(obj, num, len) {
	for (var id = 0; id <= len; id++) {
		var ss = obj + id;
		var snav = obj + "list" + id;
		if (id == num) {
			try {
				document.getElementById(ss).style.display = "block"
			} catch(e) {};
			try {
				document.getElementById(snav).className = "cur"
			} catch(e) {};
		} else {
			try {
				document.getElementById(ss).style.display = "none"
			} catch(e) {};
			try {
				document.getElementById(snav).className = ""
			} catch(e) {};
		}
	}
}
// 弹出窗口
function ExpandCollapse(show) {
	for (var i = 1; i < 1000; i++) {
		if (i == show) {
			if (document.getElementById("J-detail" + i).style.display == "none") {
				document.getElementById("J-detail" + i).style.display = "block";
			} else {
				document.getElementById("J-detail" + i).style.display = "none";
			}
		}
	}
}
// 弹出层
$(function() {
	$("#A-detail").click(function(e) {
		$(this).removeClass("drop-link").addClass("drop-hover");
		if ($("#J-detail").css("display") == "none") {
			e.stopPropagation();
			var offset = $(e.target).offset();
			$("#J-detail").show();
		} else {
			$("#J-detail").hide();
		}
	});
	$(document).click(function(event) {
		$("#J-detail").hide();
		$("#A-detail").removeClass("drop-hover").addClass("drop-link");
	});
});

// 智能浮动定位
$.fn.smartFloat = function() {
	var position = function(element) {
		var top = element.position().top,
		pos = element.css("position");
		$(window).scroll(function() {
			var scrolls = $(this).scrollTop();
			if (scrolls > top) {
				if (window.XMLHttpRequest) {
					element.css({
						position: "fixed",
						top: 0
					});
				} else {
					element.css({
						top: scrolls
					});
				}
			} else {
				element.css({
					position: pos,
					top: top
				});
			}
		});
	};
	return $(this).each(function() {
		position($(this));
	});
};
//绑定
$("#float").smartFloat();

//返回顶部
$(function() {
	$(window).bind("scroll",
	function() {
		var scrollTopNum = $(document).scrollTop(),
		winHeight = $(window).height(),
		returnTop = $("div.returnTop"); (scrollTopNum > 0) ? returnTop.fadeIn("fast") : returnTop.fadeOut("fast");
		if (!- [1, ] && !window.XMLHttpRequest) {
			returnTop.css("top", scrollTopNum + winHeight - 200);
		}
	});
	$("div.returnTop").click(function() {
		$("html, body").animate({
			scrollTop: 0
		},
		100);
	});

});

