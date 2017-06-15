<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,inital-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
<title>商品列表 - 优彼，微商家</title>
<link href="${base}/res/html5/css/style.css" rel="stylesheet" type="text/css" />
<script src="${base}/res/html5/js/jquery-1.10.2.min.js"></script>
<!-- 上拉刷新 -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" type="text/css" href="${base}/res/html5/css/scrollbar.css">
<script type="application/javascript" src="${base}/res/html5/js/iscroll.js"></script>

<link rel="stylesheet" type="text/css" href="${base}/res/html5/css2/shangpinleibiao.css"/>

 <style type="text/css" media="all">

 #wrapper {
	position:absolute; z-index:1;
	top:86px; bottom:48px; left:0;
	width:100%;
	background:#F5F6F7;
	overflow:auto;
	text-align:center; 
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
	var url = "${base}/m/goods/goodslistpage";  // goodsListPage
	var args = {
			"searchType":"${searchType}",
			"keyword":"${keyword}",
			"pageNo":pageNo,
			"keyword":"${keyword}",
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

<body class="index_bg">
	
	<div class="header">
				<a href="javascript:history.go(-1);"><img src="${base}/res/html5/img/fanhui_03.png" /></a>
				<p>搜索.${keyword}</p>
				<a href="${base}/m/index/index"><img src="${base}/res/html5/img/fanhui_05.jpg" style="width: 26px; height: 22.5px; margin-left: -15px;" /></a>
			</div>
			<#-- 
	<div class="phone_hd">
		<a class="back" href="javascript:history.go(-1);"><img src="${base}/res/html5/images/back.png" width="25" height="25" /></a>
		商品列表<a class="menu home" href="${base}/m/index/index"><img src="${base}/res/html5/images/home.png" width="25" height="25" /></a>
	</div>
	-->
	
	<div class="main">
  	<div class="mt50"></div>
  	<div class="group_top">
	    <ul>
	      <li id="top_goods_salenum" sortField="salenum">
	      	<a href="javascript:void(0)">
	      		<span><b><img src="${base}/res/html5/images/group_down.png"></b>销量</span>
	      	</a>
	      </li>
	      <li id="top_goods_collect" sortField="goodsClick"><a href="javascript:void(0)"><span><b><img src="${base}/res/html5/images/group_down.png"></b>人气</span></a></li>
	      <li id="top_store_price" sortField="goodsStorePrice"><a href="javascript:void(0)"><span><b><img src="${base}/res/html5/images/group_sort.png"></b>价格</span></a></li>
	    </ul>
 	 </div>
	<div id="wrapper">
		<div id="scroller">
	  		<div id="pullDown" style="height: 0px; border-bottom: 0;">
	  		</div>
			<div class="group_list" id="thelist"> 
			  <#--
			  	<#assign goodsSearchTag = newTag("goodsSearchTag")>
				<#assign lucenePage = goodsSearchTag("{'searchType':'${searchType}','keyword':'${keyword}','pageNo':'${pageNo}','filterConditions':'${filterConditions}','specFilter':'${specFilter}','sortField':'${sortField}','sortOrder':'${sortOrder}'}")>
				-->
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
								<h2><b style="text-align: left;"><a href="${base}/m/goods/goodsdetail?id=${recommendGoods.goodsId}&amp;storeId=${recommendGoods.storeId}" style="color:#000;">${recommendGoods.goodsName}</a></b></h2>
								<h2 style="color: red; margin-top: 15%; text-align: left;">¥${recommendGoods.goodsStorePrice}</h2>
								<h2 style="color: #a6a6a6;">${recommendGoods.commentnum}人评价，${recommendGoods.salenum}人已购买</h2>
							</dd>
						</dl>
					</#list>
					<#else>
							<a href="${base}/m/index/index">对不起，没有找到商品，请修改重新查询！</a>
		    		</#if>
    
    <#-- 
					<#if lucenePage!="" && lucenePage??>
						<#if lucenePage.result??>
							<#list lucenePage.result as goods>
							  	<a href="${base}/m/goods/goodsdetail?id=${goods.goodsId}">
							    <dl>
							      <dt><img width="90" height="90" src=<#if goods.goodsImage!''>"${imgServer}${goods.goodsImage}"<#else>"${base}/res/images/common/default_goods_image.gif_small.gif"</#if> title="${goods.goodsName}" alt="${goods.goodsName}"></dt>
							      <dd>
							        <h3><b style="text-align: left;">${goods.goodsName}</b></h3>
							        <span style="text-align: left;"><strong>¥${goods.goodsStorePrice}</strong></span> 
							        <em>${goods.commentnum}人评价，${goods.salenum}人已购买，${goods.goodsClick}人已查看</em> 
							      </dd>
							    </dl>
							    </a> 
			 				</#list>
			 			<#else>
							对不起，您来晚了，此类商品已经售罄！
						</#if>   
					<#else>
						对不起，您来晚了，此类商品已经售罄！
				    </#if>
				     -->
				    
		  		</div>
		  		
		  		<#-- <#if lucenePage!="" && lucenePage && lucenePage.result??> -->
		  		<#if recommendGoodslist!=null && recommendGoodslist?size&gt;10>
		  		<div id="pullUp">
	  				<span class="pullUpIcon"></span><span class="pullUpLabel">
	  					上拉加载更多...
	  				</span>
	  			</div>
	  			</#if>
		</div>
	</div>

</div>
</body>
</html>
