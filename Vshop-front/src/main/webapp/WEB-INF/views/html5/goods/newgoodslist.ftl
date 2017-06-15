<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">

<#if reCommendId==2>
	<title>店长力荐 - 优彼，微商家</title>
<#elseif reCommendId==3>
	<title>新品上市 - 优彼，微商家</title>
</#if>

<link rel="stylesheet" type="text/css" href="${base}/res/html5/css2/reset.css"/>
<link rel="stylesheet" type="text/css" href="${base}/res/html5/css2/shangpinleibiao.css"/>
<link rel="stylesheet" type="text/css" href="${base}/res/html5/css2/none.css"/>
		
<!-- <link href="${base}/res/html5/css/style.css" rel="stylesheet" type="text/css" /> -->
<script src="${base}/res/html5/js/jquery-1.10.2.min.js"></script>

<!-- 上拉刷新 -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" type="text/css" href="${base}/res/html5/css/scrollbar.css">
<script type="application/javascript" src="${base}/res/html5/js/iscroll.js"></script>

<style type="text/css" media="all">

 #wrapper {
	position:absolute; z-index:1;
	top:44px; bottom:48px; left:0;
	width:100%;
	background:#F5F6F7;
	overflow:auto;
} 

#scroller {
	position:relative;
/*	-webkit-touch-callout:none;*/
	-webkit-tap-highlight-color:rgba(0,0,0,0);

	float:left;
	width:100%;
	padding:0;
}

/**
 *
 * 下拉样式 Pull down styles
 *
 */
#pullDown, #pullUp {
	background:#F5F6F7;
	height:40px;
	line-height:40px;
	padding:5px 10px;
	border-bottom:1px solid #ccc;
	font-weight:bold;
	font-size:14px;
	color:#888;
}
#pullDown .pullDownIcon, #pullUp .pullUpIcon  {
	display:block; float:left;
	width:40px; height:40px;
	//background:url(../images/pull-icon@2x.png) 0 0 no-repeat;
	-webkit-background-size:40px 80px; background-size:40px 80px;
	-webkit-transition-property:-webkit-transform;
	-webkit-transition-duration:250ms;	
}
#pullDown .pullDownIcon {
	-webkit-transform:rotate(0deg) translateZ(0);
}
#pullUp .pullUpIcon  {
	-webkit-transform:rotate(-180deg) translateZ(0);
}

#pullDown.flip .pullDownIcon {
	-webkit-transform:rotate(-180deg) translateZ(0);
}

#pullUp.flip .pullUpIcon {
	-webkit-transform:rotate(0deg) translateZ(0);
}

#pullDown.loading .pullDownIcon, #pullUp.loading .pullUpIcon {
	background-position:0 100%;
	-webkit-transform:rotate(0deg) translateZ(0);
	-webkit-transition-duration:0ms;

	-webkit-animation-name:loading;
	-webkit-animation-duration:2s;
	-webkit-animation-iteration-count:infinite;
	-webkit-animation-timing-function:linear;
}

@-webkit-keyframes loading {
	from { -webkit-transform:rotate(0deg) translateZ(0); }
	to { -webkit-transform:rotate(360deg) translateZ(0); }
}

</style>
<!-- end -->
<script type="text/javascript">

var myScroll,
	pullDownEl, pullDownOffset,
	pullUpEl, pullUpOffset,
	generatedCount = 0;

jQuery(document).ready(function(){
	var order = '${sortOrder}';
	if("${sortField}"=="salenum"){
		jQuery("#top_goods_salenum").addClass("this");
	  if("desc"==order){
		  jQuery("#top_goods_salenum img").attr("src","${base}/res/html5/images/goodsclass_down.png");
	  }else{
	  	  jQuery("#top_goods_salenum img").attr("src","${base}/res/html5/images/goodsclass_up.png");
	  }
   }else if("${sortField}"=="goodsClick"){
	   jQuery("#top_goods_collect").addClass("this");
	  if("desc"==order){
		  jQuery("#top_goods_collect img").attr("src","${base}/res/html5/images/goodsclass_down.png");
	  }else{
	  	  jQuery("#top_goods_collect img").attr("src","${base}/res/html5/images/goodsclass_up.png");
	  }
   }else if("${sortField}"=="goodsStorePrice"){
	   jQuery("#top_store_price").addClass("this");
	  if("desc"==order){
		  jQuery("#top_store_price img").attr("src","${base}/res/html5/images/goodsclass_down.png");
	  }else{
	  	  jQuery("#top_store_price img").attr("src","${base}/res/html5/images/goodsclass_up.png");
	  }
   }

   	
	jQuery(".group_top li").click(function(){
		var searchtype = jQuery(this).attr("sortField");
		var order = '${sortOrder}';
		if(order == "desc"){
			order = "asc";
		}else{
			order = "desc";
		}
		window.location.href="${base}/m/goods/goodslist?searchType=${searchType}&keyword=${keyword}&sortField="+ searchtype + "&sortOrder=" + order;
	}) 	  
});<!--end-->

var pageNo = ${pageNo};
function pullUpAction () {
	pageNo = pageNo + 1;
	var url = "${base}/m/goods/goodsListPage";
	var args = {
			"searchType":"${searchType}",
			"keyword":"${keyword}",
			"pageNo":pageNo,
			"keyword":"${keyword}",
			"sortField":"${sortField}",
			"sortOrder":"${sortOrder}"
	};
	 //ajax
	 $.post(url, args, function(data){
		$("#thelist").append(data);
		myScroll.refresh();
	 });
 }

/**
 * 初始化iScroll控件
 */
function loaded() {
	pullDownEl = document.getElementById('pullDown');
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
</head>

<body>
  <div class="wrap">
	
	<div class="header">
		<a href="javascript:history.go(-1);"><img src="${base}/res/html5/img/fanhui_03.png" /></a>
		<p>
		<#if reCommendId==2>
			老师力荐商品列表
		<#elseif reCommendId==3>
			新品上市商品列表
		</#if>
		</p>
		<a href="${base}/m/index/index"><img src="${base}/res/html5/img/fanhui_05.jpg" style="width: 26px; height: 22.5px; margin-left: -15px;" /></a>	
	</div>
	 
    <#assign goodsRecommendTag = newTag("goodsRecommendTag") />
    <#assign recommendGoodslist = goodsRecommendTag("{'reCommendId':'${reCommendId}','storeId':'${storeid}','goodsCommend':'0'}") />
    
    <#if recommendGoodslist!=null && recommendGoodslist?size&gt;0>
    	<div class="conter" style="top: 42px">
			<#list recommendGoodslist as recommendGoods>
        	 	<dl>
        	 		<dt>
        	 			<a href="${base}/m/goods/goodsdetail?id=${recommendGoods.goodsId}&amp;storeId=${recommendGoods.storeId}">
        	 			<img width="90" height="90" src="${imgServer}${recommendGoods.goodsImage}" title="${recommendGoods.goodsName}" alt="${recommendGoods.goodsName}">
        	 			</a>
        	 		</dt>
					<dd>
						<h2><b><a href="${base}/m/goods/goodsdetail?id=${recommendGoods.goodsId}&amp;storeId=${recommendGoods.storeId}" style="color:black;">${recommendGoods.goodsName}</a></b></h2>
						<h2 style="color: red; margin-top: 15%;">¥${recommendGoods.goodsStorePrice}</h2>
						<h2 style="color: #a6a6a6;">${recommendGoods.commentnum}人评价，${recommendGoods.salenum}人已购买</h2>
					</dd>
				</dl>
			</#list>
		</div>
    <#else>
    	<div class="m-bot2">				
			该店铺主人很懒，还没有上架商品
		</div>
    	<#--
    	<div class="font">
			<img src="${base}/res/html5/img/wujiehuo_03.jpg" />
			<div class="font_cont">
				<h1>
					<#if reCommendId==2>
						暂无老师力荐商品
					<#elseif reCommendId==3>
						暂无新品上市商品
					</#if>
				</h1>
				<h1></h1>
			</div>
		</div>
		-->
    </#if>
    
  </div>
</body>
</html>
