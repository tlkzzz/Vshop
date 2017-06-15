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
$(document).ready(function() {
	//dropCart(".cart-mod");
    $(".cart-mod").mouseenter(function(){
        $("#cartDiv").empty();
        var theCart = $("#cartDiv");
        $.ajax({
            url : APP_BASE+'/cart/cart',
            type : 'POST',
            dataType : 'json',
            success : function(data){
                $(".cart-mod").removeClass("cart-hover");
                if(data.result.length == 0){
                    $("#cartDiv").empty();
                    $("#cartDiv").append('<p class="nogoods">购物车中还没有牛仔课程，赶紧选购吧！</p>');
                }else{
                    var temp = '<div class="cart-con" style="display:;"><ol>';
                    var tcartId = '';
                    var array = data.result;
                    $(array).each(function(index,obj){
                        temp += '<li><a class="p-del" href="javascript:;" onclick="delCart('+obj.goodsId+',this)">删除</a><span class="p-price">¥'+obj.goodsPrice+'</span>'+
                            '<a href="'+APP_BASE+'/product/detail?id='+obj.goodsId+'" target="_blank"><span class="p-img">'+
                            '<img src="'+IMG_BASE+obj.goodsImage+'" alt=""/></span>'+
                            '<span class="p-name">'+obj.goodsName+'</span></a>'+
                            '<span class="p-quantity">数量：'+obj.goodsNum+'</span></li>';
                        tcartId += obj.goodsId + ",";
                    });
                    temp += '</ol><div class="cart-btn"><a class="btna" href="'+APP_BASE+'/cart/cartOrder?cartId='+tcartId.substring(0,tcartId.length-1)+'">去结算</a>' +
                        '<a class="link" href="'+APP_BASE+'/cart/index">查看购物车</a></div>';
                    $("#cartDiv").empty();
                    $("#cartDiv").append(temp);
                }
                $(".cart-mod").addClass("cart-hover");

                var tarHeight = theCart.height();
                theCart.stop().show().animate({
                        height: tarHeight,
                        opacity: 1
                    },
                    20);
            }
        })
    });
    $(".cart-mod").mouseleave(function(){
        $(".cart-mod").removeClass("cart-hover");
        var theCart = $("#cartDiv");
        theCart.stop().animate({
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

function delCart(goodsId,obj){
    $.post(APP_BASE+'/cart/deleteCart',{'goodsId':goodsId},function(data){
        if (data) {
            if(data.success) {
                window.location.reload();
            }
        }
    });
}