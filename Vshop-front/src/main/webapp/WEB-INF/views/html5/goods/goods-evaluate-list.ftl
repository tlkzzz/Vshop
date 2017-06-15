<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,inital-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
<title>商品评价 - 优彼，微商家</title>
<link href="${base}/res/html5/css/style.css" rel="stylesheet" type="text/css" />
<script src="${base}/res/html5/js/jquery-1.10.2.min.js"></script>
<script src="${base}/res/js/layer/layer.js"></script>
<link href="${base}/res/css/mycart.css" rel="stylesheet" type="text/css" />
<link href="${base}/res/css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/res/js/jquery.raty/jquery.raty.min.js" ></script>
<!-- 上拉刷新 -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" type="text/css" href="${base}/res/html5/css/scrollbar.css">
<script type="application/javascript" src="${base}/res/html5/js/iscroll.js"></script>
<link rel="stylesheet" href="${base}/res/html5/css2/gong_xq.css"/>
<link rel="stylesheet" href="${base}/res/html5/css2/reset.css"/>
<link rel="stylesheet" type="text/css" href="${base}/res/html5/css2/head.css"/>

<!-- end -->
<script type="text/javascript">
	var myScroll,
	pullDownEl, pullDownOffset,
	pullUpEl, pullUpOffset,
	generatedCount = 0;
		var pageNo = 1;
		$(function(){
				var myScroll,
				pullDownEl, pullDownOffset,
				pullUpEl, pullUpOffset,
				generatedCount = 0;
				//评价星级
				$(".creditevaluate").raty({
					path : "${base}/res/js/jquery.raty/img",
					hints : [ '一般', '不错', "很好", '满意', '非常满意' ],
					readOnly : true,
					width : 100,
					score : function() {
						return $(this).attr("data-score")
					}
				});
		});

		function pullUpAction () {
			pageNo = pageNo + 1;
			var url = "${base}/m/goods/goodsEvalutePage";
			var args = {
					"goodsId":"${goodsId}",
					"pageNo":pageNo,
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
	
	//评价星级
	/* $(document).ready(function() {
		
	}); */
</script>
</head>

<body class="user_bg">
<#if isShow == 1>
<#-- 
<div class="user_bar">
	<a class="back" href="${base}/m/goods/goodsdetail?id=${goodsId}">
		<img src="${base}/res/html5/images/back_black.png" width="30" height="30" />
	</a>
	<span class="fl"></span>
</div>
-->
		<div class="header">
			<a href="${base}/m/goods/goodsdetail?id=${goodsId}"><img src="${base}/res/html5/img/fubu_03.jpg" /></a>
			<p>商品评价</p>
		</div>
		
</#if>
<div class="order_page">
<div id="wrapper">
	<div id="scroller" style="border:0;">
		<div id="pullDown" style="border:0;"></div>
		<div class="order_page_box" id="thelist" style="border:0;">
			 <#assign evaluateGoodsTag = newTag("evaluateGoodsTag")>
		     <#assign evaluateGoodsList = evaluateGoodsTag("{'goodsId':'${goodsId}', 'pageSize':'10'}")>
		     <input type="hidden" name="goodsId" value="${goodsId}"/>
		     
				<#if evaluateGoodsList?? && evaluateGoodsList!''>
			<#--		<p>&nbsp;&nbsp;商品评价（${evaluateGoodsList?size}）</p>  -->
					<#list evaluateGoodsList as evaluateGoods>
						<div class="font_top" style="border-bottom:1px;">
							<i>&nbsp;&nbsp;
								<#if evaluateGoods.gevalIsAnonymous==1>
									<img src="${base}/res/html5/img/tx_03.jpg"/>
									匿名
								<#else>
									<img src="${evaluateGoods.memberAvatar}" />
									${evaluateGoods.gevalFrommembername}
								</#if>
							</i>
							<h1 style="border-bottom:0;">${evaluateGoods.gevalContent}</h1>
							<h2 style="float: right; border-bottom: 1px solid #eee; padding-bottom: 2px;">${evaluateGoods.gevalAddTimeStr} &nbsp;&nbsp;&nbsp;&nbsp;产品分类：${evaluateGoods.gcName}</h2>
						</div>
					</#list>
				<#else>
					<div class="guige2"><a>&nbsp;&nbsp;暂时没有会员进行评价</a></div>
				</#if>
			 
		<#-- 
	   		<div id="mycartcontent">
				<div class="body">
					<ul class="list">
					    <li class="li" style="font-weight:bold">
						    <span class="s2">评价心得</span>
						    <span class="s3">顾客满意度</span>
						    <span class="s4">购买信息</span>
						    <span class="s5">评论者</span>
						</li>
						<input type="hidden" name="goodsId" value="${goodsId}"/>
						<#assign goodsEvalutebyGoodsIdTag =newTag("goodsEvalutebyGoodsIdTag")>
						<#assign goodsEvalutes =goodsEvalutebyGoodsIdTag("{'goodsId':'${goodsId}','pageNo':'${pageNo}','toUrl':'${toUrl}'}")>
						<#if goodsEvalutes??>
							<#list goodsEvalutes.result as goodsEV>
							    <li>
								    <dl>
									    <dt>${goodsEV.gevalContent}</dt>
									    <#if goodsEV.gevalImage??>
									    <dd>
									    	<#list goodsEV.gevalImage?split(",") as gevalImage>  
											    <div class="img">
											    	<a href="#"><img src="${imgServer}${gevalImage}" alt="" title="" /></a>
											    </div> 
										    </#list>
										</dd><br>
										</#if>
								    </dl>
								    <span class="s3">
								    	<span class="creditevaluate" id="descraty" data-score="${goodsEV.gevalScore}"></span>
								    </span>
								    <span class="s4">
								    	${goodsEV.specInfo}
									</span>
								    <span class="s5">
								    	${goodsEV.gevalFrommembername}<br><br>
										${goodsEV.createTimeStr}<br>
									</span>
							    </li>
						    </#list>
						</#if>        
					</ul>
				</div>
			</div>
	   		-->
	    </div>
	    
		<div id="pullUp">
			<span class="pullUpIcon"></span><span class="pullUpLabel">上拉加载更多...</span>
		</div>
	</div>
</div>
</div>

<!-- 

<#if isShow == 0>

<@f.foot/>	
<!--按钮-->
</#if>
 -->

</body>
</html>
