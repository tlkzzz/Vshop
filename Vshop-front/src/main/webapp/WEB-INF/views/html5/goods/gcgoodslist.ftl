<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<meta charset="UTF-8">
		<title>商品列表</title>
		<link rel="stylesheet" type="text/css" href="${base}/res/html5/css2/reset.css"/>
		<link rel="stylesheet" type="text/css" href="${base}/res/html5/css2/shangpinleibiao.css"/>
	</head>
	<script src="${base}/res/html5/js/jquery-2.1.1.min.js"></script>
	<script src="${base}/res/html5/js/swiper-3.3.1.jquery.min.js"></script>
	<script src="${base}/res/html5/js/swiper.min.js"></script>
	<script src="${base}/res/html5/js/zepto.min.js"></script>
	
	<link rel="stylesheet" type="text/css" href="${base}/res/html5/css/scrollbar.css">
	<script type="application/javascript" src="${base}/res/html5/js/iscroll.js"></script>
	
	<style>
		.zhezhao{width: 100%;height: 100%;background: #000000;opacity: 0.5;position: absolute;top: 0;left: 0;z-index: 9;display: none;}
		.toggle{z-index: 9;}
	</style>
	<body>
		<div class="wrap">
			<div class="header">
				<a href="javascript:history.go(-1);"><img src="${base}/res/html5/img/fanhui_03.png" /></a>
				<p>
					${titleStr}
				</p>
				<a href="${base}/m/index/index"><img src="${base}/res/html5/img/fanhui_05.jpg" style="width: 26px; height: 22.5px; margin-left: -15px;" /></a>
			</div>
			<div class="tuijian">
				<ul>
					<li class="on">商品分类</li>
					<li class="on">智能排序</li>
					<li class="on" style="background-position-x:70%;">筛选</li>
				</ul>
			</div>
				
				
			<div id="wrapper">
			<div id="scroller">
	  			<div id="pullDown" style="height: 0px; border-bottom: 0;"></div>
	  			<div class="conter" id="thelist">
	<#assign goodsRcListTag = newTag("goodsRcListTag") />
    <#assign recommendGoodslist = goodsRcListTag("{'gcId':'${gcId}','storeId':'${storeid}', 'gcType':'${gcType}', 'sortField':'${sortField}', 'sortOrder':'${sortOrder}','pageNo':'${pageNo}','keyword':'${keyword}'}") />
    
    <#if recommendGoodslist!=null && recommendGoodslist?size&gt;0>
    	
    		<#list recommendGoodslist as recommendGoods>
        	 	<dl style="margin-top: 1.5%;">
        	 		<dt>
        	 			<a href="${base}/m/goods/goodsdetail?id=${recommendGoods.goodsId}&amp;storeId=${recommendGoods.storeId}">
        	 			<img width="90" height="90" src="${imgServer}${recommendGoods.goodsImage}" title="${recommendGoods.goodsName}" alt="${recommendGoods.goodsName}">
        	 			</a>
        	 		</dt>
					<dd>
						<h2><b><a href="${base}/m/goods/goodsdetail?id=${recommendGoods.goodsId}&amp;storeId=${recommendGoods.storeId}" style="color:#000;">${recommendGoods.goodsName}</a></b></h2>
						<h2 style="color: red; margin-top: 15%;">¥${recommendGoods.goodsStorePrice}</h2>
						<h2 style="color: #a6a6a6;">${recommendGoods.commentnum}人评价，${recommendGoods.salenum}人已购买</h2>
					</dd>
				</dl>
			</#list>
    </#if>
    		</div>
    		
    <#if recommendGoodslist!=null && recommendGoodslist?size&gt;10>
    		<div id="pullUp">
  				<span class="pullUpIcon"></span><span class="pullUpLabel">
  					上拉加载更多...
  				</span>
  			</div>
	</#if>
    		</div>
    		</div>
    		
			<div class="toggle animated" style="display: none;">
				<div class="toggle_menu_one pholder">
					<div class="toggle_menu_one_left rand">
						<ul>
							<#assign goodsClassTag =newTag("goodsClassTag")>
							<#assign goodsList =goodsClassTag("{'parentid':'${pcId}', 'storeid':'${storeid}', 'clsLevel':'2'}")>
							
							<#if goodsList?exists && goodsList?size gt 0>
							<#list goodsList as class>
								<#if mcId==class.gcId>
									<li style="display: block; background: #fff;" id="${class.gcId}" >${class.gcName}</li>
								<#elseif class_index==0 && mcId lte 0>
									<li style="display: block; background: #fff;" id="${class.gcId}" >${class.gcName}</li>
								<#else>
									<li id="${class.gcId}" >${class.gcName}</li>
								</#if>
							</#list>
							</#if>
						</ul>
					</div>
					<div class="toggle_menu_one_right" style=" overflow: hidden;">
						
						<#if goodsList?exists && goodsList?size gt 0>
						<#list goodsList as class>
							<#assign goodsLi = goodsClassTag("{'parentid':'${class.gcId}', 'storeid':'${storeid}', 'clsLevel':'3'}")>
					    	<ul class="hold">
					       	<#list goodsLi as classtwo>
					       		<li id="${classtwo.gcId}">
			       					<#if classtwo.gcId==gcId>
			       						<a href="${base}/m/goods/gcgoodslist?gcId=${classtwo.gcId}&amp;gcType=2&amp;pcId=${pcId}&amp;mcId=${class.gcId}&amp;keyword=${keyword}" style="color:red">${classtwo.gcName}</a>
			       					<#else>
			       						<a href="${base}/m/goods/gcgoodslist?gcId=${classtwo.gcId}&amp;gcType=2&amp;pcId=${pcId}&amp;mcId=${class.gcId}&amp;keyword=${keyword}" style="color:black">${classtwo.gcName}</a>
			       					</#if>
			       				</li>
							</#list>
							</ul>	
			        	</#list>
			        	</#if>
					</div>
				</div>
				<div class="toggle_menu_two pholder" style="display: none;">
					<div class="toggle_menu_two_left rand" style="width: 100%;">
						<ul>
							<li <#if sortField=='salenum'>style="background: #fff;"</#if> >
								<a href="${base}/m/goods/gcgoodslist?gcId=${gcId}&amp;sortField=salenum&amp;sortOrder=${sortOrder}&amp;pcId=${pcId}&amp;mcId=${mcId}&amp;keyword=${keyword}" style="color:black">销量排序</a></li>
	       					<li <#if sortField=='goodsClick'>style="background: #fff;"</#if> >
	       						<a href="${base}/m/goods/gcgoodslist?gcId=${gcId}&amp;sortField=goodsClick&amp;sortOrder=${sortOrder}&amp;pcId=${pcId}&amp;mcId=${mcId}&amp;keyword=${keyword}" style="color:black">人气排序</a></li>
	       					<li <#if sortField=='goodsStorePrice'>style="background: #fff;"</#if> >
	       						<a href="${base}/m/goods/gcgoodslist?gcId=${gcId}&amp;sortField=goodsStorePrice&amp;sortOrder=${sortOrder}&amp;pcId=${pcId}&amp;mcId=${mcId}&amp;keyword=${keyword}" style="color:black">价格排序</a></li>
	      				</ul>
					</div>
				</div>
				<div class="toggle_menu_three pholder" style="display: none;">
					<div class="toggle_menu_three_left rand" style="width: 100%;">
						<ul>
							<#if gcId==pcId>
							<li style="background: #fff;">
								<a href="${base}/m/goods/gcgoodslist?gcId=${pcId}&amp;gcType=0&amp;pcId=${pcId}&amp;keyword=${keyword}" style="color:black">全部</a>
							</li>
							<#else>
							<li>
								<a href="${base}/m/goods/gcgoodslist?gcId=${pcId}&amp;gcType=0&amp;pcId=${pcId}&amp;keyword=${keyword}" style="color:black">全部</a>
							</li>
							</#if>
							
							<#assign goodsList3 = goodsClassTag("{'topId':'${pcId}', 'storeid':'${storeid}'}")>
							<#if goodsList3?exists && goodsList3?size gt 0>
							<#list goodsList3 as classleaf>
					       		<#if classleaf.gcId==gcId>
			       				<li id="${classleaf.gcId}" style="background: #fff;">
			       						<a href="${base}/m/goods/gcgoodslist?gcId=${classleaf.gcId}&amp;gcType=2&amp;pcId=${pcId}&amp;keyword=${keyword}" style="color:black">${classleaf.gcName}</a>
			       				</li>
			       				<#else>
			       				<li id="${classleaf.gcId}">
			       						<a href="${base}/m/goods/gcgoodslist?gcId=${classleaf.gcId}&amp;gcType=2&amp;pcId=${pcId}&amp;keyword=${keyword}" style="color:black">${classleaf.gcName}</a>
			       				</li>
			       				</#if>
							</#list>
							</#if>
						</ul>
					</div>
				</div>
			</div>
			<div class="zhezhao"></div>
		</div>
		
		<script>
			var myScroll,
			pullDownEl, pullDownOffset,
			pullUpEl, pullUpOffset,
			generatedCount = 0;
			
			$('.tuijian ul li').click(function(){
				var index = $(this).index();
				if( $(this).hasClass('on') ){
					$('.tuijian ul li').addClass('on')
					$(this).removeClass('on');
					$(this).css("background","url(img/shaixuan_03.png) center right no-repeat")
					$('.tuijian ul li').css('background-size','9.5px')
					$(".zhezhao").css("display","block")
					$('.tuijian ul').addClass('on')
					
					if($(this).index() == 0){
//						$(".toggle").slideDown(500)
//						$('.toggle .pholder').eq(index).slideDown(500).siblings('.pholder').slideUp(500);
						$(this).css("background-position-x","83%");
						toggle();
					}
					if($(this).index() == 1){
//						$(".toggle").slideDown(500)
//						$('.toggle .pholder').eq(index).slideDown(500).siblings('.pholder').slideUp(500);
						$(this).css("background-position-x","83%");
						toggle();
					}
					if($(this).index() == 2){
						$(".toggle").slideDown(500)
						$('.toggle .pholder').eq(index).slideDown(500).siblings('.pholder').slideUp(500);
						$(this).css("background-position-x","70%");
					}
					function toggle(){
						$(".toggle").slideDown(500)
						$('.toggle .pholder').eq(index).slideDown(500).siblings('.pholder').slideUp(500);
						$(this).css("background-position-x","83%");
					}
				}else{
					$('.tuijian ul li').removeClass('on')		
					$('.tuijian ul li').css("background","url(img/shaixuan_05.png) center right no-repeat")
					$('.tuijian ul li').css("background-size","9.5px")
					$('.tuijian ul li').eq(0).css('background-position-x',"83%")
					$('.tuijian ul li').eq(1).css('background-position-x',"83%")
					$('.tuijian ul li').eq(2).css('background-position-x',"70%")
					if($(this).index() == 0){
//						$(".toggle").slideUp(100)
//						$('.tuijian ul li').addClass('on');
//						$('.toggle .pholder').eq(index).slideDown(100).siblings('.pholder').slideUp(100);
						$(this).css("background-position-x","83%");
//						$(".zhezhao").css("display","none")
						toto();
					}
					if($(this).index() == 1){
//						$(".toggle").slideUp(100)
//						$('.tuijian ul li').addClass('on');
//						$('.toggle .pholder').eq(index).slideDown(100).siblings('.pholder').slideUp(100);
						$(this).css("background-position-x","83%");
//						$(".zhezhao").css("display","none")
						toto();
					}
					if($(this).index() == 2){
//						$(".toggle").slideUp(100)
//						$('.tuijian ul li').addClass('on');
//						$('.toggle .pholder').eq(index).slideDown(100).siblings('.pholder').slideUp(100);
						$(this).css("background-position-x","70%");
//						$(".zhezhao").css("display","none")
//						toto();
						toto();
					}
					function toto(){
						$(".toggle").slideUp(100)
						$('.tuijian ul li').addClass('on');
						$('.toggle .pholder').eq(index).slideDown(100).siblings('.pholder').slideUp(100);
//						$(this).css("background-position-x","83%");
						$(".zhezhao").css("display","none")
					}
				}
			});
			$(".zhezhao").click(function(){
				$(".shuliang").slideUp(500);
				$(".zhezhao").css("display","none")
				$('.toggle').css('display','none')
				$('.tuijian ul li').css("background","url(img/shaixuan_05.png) center right no-repeat")
				$('.tuijian ul li').css("background-size","9.5px")
				$('.tuijian ul li').eq(0).css('background-position-x',"83%")
				$('.tuijian ul li').eq(1).css('background-position-x',"83%")
				$('.tuijian ul li').eq(2).css('background-position-x',"70%")
			})
			$("ul.hold").css("display","none");
			$("ul.hold").eq(0).css("display","block");
			$('.rand ul li').click(function(){
				var index = $(this).index();
				$('.toggle_menu_one_right ul').eq(index).show().siblings('.hold').hide(0);
				var temp=$(this);
			    $(".rand ul li").each(function(){
			        $(this).css("background","#ebebeb");
			    });
			    temp.css("background","#fff");
			});
			
var pageNo = ${pageNo};
function pullUpAction () {
	pageNo = pageNo + 1;
	var url = "${base}/m/goods/gcgoodslistdata";
	var args = {
			"gcId":"${gcId}",
			"mcId":"${mcId}",
			"pcId":"${pcId}",
			"storeId":"${storeid}", 
			"gcType":"${gcType}", 
			"keyword":"${keyword}",
			"pageNo":pageNo,
			"sortField":"${sortField}",
			"sortOrder":"${sortOrder}"
	};
	 // ajax
	 $.post(url, args, function(data){
	 	if(data!=null) {
			$("#thelist").append(data);
			myScroll.refresh();
		}
	 });
 }

/**
 * 初始化iScroll控件
 */
function loaded() {
	pullDownEl = document.getElementById("pullDown");
	pullDownOffset = pullDownEl.offsetHeight;
	pullUpEl = document.getElementById('pullUp');	
	pullUpOffset = pullUpEl.offsetHeight;
	
	myScroll = new iScroll('wrapper', {
		scrollbarClass: 'myScrollbar', /* 重要样式 */
		useTransition: false, /* 此属性不知用意，本人从true改为false */
		topOffset: pullDownOffset,
		onRefresh: function () {
			if (pullDownEl.className.match('loading')) {
				pullDownEl.className = '';
				pullDownEl.querySelector('.pullDownLabel').innerHTML = '下拉刷新...';
			} else if (pullUpEl.className.match('loading')) {
				pullUpEl.className = '';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉加载更多...';
			}
		},
		onScrollMove: function () {
			if (this.y > 5 && !pullDownEl.className.match('flip')) {
				pullDownEl.className = 'flip';
				pullDownEl.querySelector('.pullDownLabel').innerHTML = '松手开始更新...';
				this.minScrollY = 0;
			} else if (this.y < 5 && pullDownEl.className.match('flip')) {
				pullDownEl.className = '';
				pullDownEl.querySelector('.pullDownLabel').innerHTML = '下拉刷新...';
				this.minScrollY = -pullDownOffset;
			} else if (this.y < (this.maxScrollY - 5) && !pullUpEl.className.match('flip')) {
				pullUpEl.className = 'flip';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '松手开始更新...';
				this.maxScrollY = this.maxScrollY;
			} else if (this.y > (this.maxScrollY + 5) && pullUpEl.className.match('flip')) {
				pullUpEl.className = '';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉加载更多...';
				this.maxScrollY = pullUpOffset;
			}
		},
		onScrollEnd: function () {
			if (pullDownEl.className.match('flip')) {
				pullDownEl.className = 'loading';
				pullDownEl.querySelector('.pullDownLabel').innerHTML = '加载中...';				
				pullDownAction();	// Execute custom function (ajax call?)
			} else if (pullUpEl.className.match('flip')) {
				pullUpEl.className = 'loading';
				pullUpEl.querySelector('.pullUpLabel').innerHTML = '加载中...';				
				pullUpAction();	// Execute custom function (ajax call?)
			}
		}
	});
	
	setTimeout(function () { document.getElementById('wrapper').style.left = '0'; }, 800);
}

//初始化绑定iScroll控件 
document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
document.addEventListener('DOMContentLoaded', loaded, false);

		</script>
	</body>
</html>
