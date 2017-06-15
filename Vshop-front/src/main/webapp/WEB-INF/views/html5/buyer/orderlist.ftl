<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,inital-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
<title>订单列表 - 优彼，微商家</title>
<link href="${base}/res/html5/css/style.css" rel="stylesheet" type="text/css" />
<script src="${base}/res/html5/js/jquery-1.10.2.min.js"></script>
<!-- 上拉刷新 -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" type="text/css" href="${base}/res/html5/css/scrollbar.css">
<script type="application/javascript" src="${base}/res/html5/js/iscroll.js"></script>
<script type="text/javascript" src="${base}/res/js/nc-sideMenu.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/utils.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/layer/layer.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<link href="${base}/res/css/font-icons/style.css"  rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${base}/res/html5/css2/head.css"/>

    <style type="text/css" media="all">

 #wrapper {
	position:absolute; z-index:1;
	top:87px; bottom:48px; left:0;
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
	text-align:center; 
	
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
	
	<!--按钮样式变换的开始-->
		jQuery(document).ready(function(){
			var orderState = '${orderState}';
			if(orderState==99){
				jQuery("#order_status_0").addClass("this");
			}else if(orderState==10){
				jQuery("#order_status_order_nopay").addClass("this");
			}else if(orderState==20){
				jQuery("#order_status_order_noship").addClass("this");
			}else if(orderState==30){
				jQuery("#order_status_order_notake").addClass("this");
			}else if(orderState==40){
				jQuery("#order_status_order_over").addClass("this");
			}
				 
			
		});
	
	<!--按钮样式变换的结束-->
	<!--上拉刷新开始-->
	var pageNo = ${pageNo};
	function pullUpAction () {
		pageNo = pageNo + 1;
		var url = "${base}/m/authc/buyer/orderListPage";
		var args = {
				"orderState":"${orderState}",
				"pageNo":pageNo
				
		};
		 //ajax
		 $.post(url, args, function(data){
			$("#thelist").append(data);
			myScroll.refresh();
		 });
	 }
	<!--上拉刷新结束-->
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

<body class="user_bg">

			<div class="header">
				<a href="${base}/m/authc/buyer/center"><img src="${base}/res/html5/img/fanhui_03.png" /></a>
				<p>我的订单</p>
				<a href="${base}/m/index/index"><img src="${base}/res/html5/img/fanhui_05.jpg" style="width: 26px; height: 22.5px; margin-left: -15px;" /></a>
			</div>
			<#-- 
<div class="user_bar"  style="position:absolute; text-align:center; color:#fff;">
	<a class="back" href="${base}/m/authc/buyer/center">
<!-- <img src="${base}/res/html5/images/back_black.png" width="30" height="30" /> --//>
<img src="${base}/res/html5/images/back.png" width="30" height="30" /></a>我的订单<a href="${base}/m/index/index">
<img src="${base}/res/html5/images/home.png" width="25" height="25" style="float:right; margin-top:3%; margin-right:5%;"/></a>
</div>
-->

<div class="order_page">
<div class="order_hd">
    <ul>
      <li id="order_status_0">
      	<a href="${base}/m/authc/buyer/orderList?orderState=99">全 部</a>
      </li>
      <li id="order_status_order_nopay">
      	<a href="${base}/m/authc/buyer/orderList?orderState=10">待付款</a>
      </li>
      <li id="order_status_order_noship">
      	<a href="${base}/m/authc/buyer/orderList?orderState=20">待发货</a>
      </li>
      <li id="order_status_order_notake">
      	<a href="${base}/m/authc/buyer/orderList?orderState=30">待收货</a>
      </li>
      <li id="order_status_order_over">
      	<a href="${base}/m/authc/buyer/orderList?orderState=40">已收货</a>
      </li>
    </ul>
    <input type="hidden" name="url" id="url">
</div>


<div id="wrapper">
	<div id="scroller">
	<#assign orderTag = newTag("orderTag")/>
	<#assign orderLists = orderTag("{'orderSn':'${orderSn}','startTime':'${startTime}','endTime':'${endTime}','orderState':'${orderState}','returnDataType':'2','pageNo':'${pageNo}','pageSize':'${pageSize}'}") />
	<#if orderLists??>
	<#if (orderLists?size gt 0)>
	<div id="pullDown">
		</div>
	</#if>
	
	</#if>
			
		<div class="order_page_box" id="thelist">
		
	    	<#if orderLists??>
		   	    <#list orderLists as order>
			    	<h1 style="border-top: 1px solid #eee;"><span class="fl">订单号：${order.orderSn}</span>
			    	<#assign orderState = order.orderState/>
			    	<#if orderState??>
				    	<#if orderState==10>
				    		<span class="fr">待会员付款</span></h1>
				    	<#elseif orderState==20>
				    		<span class="fr">会员已付款</span></h1>
				    	<#elseif orderState==30>
				    		<span class="fr">卖家已发货</span></h1>
				    	<#elseif orderState==40>
				    		<span class="fr">会员已收货</span></h1>
				    	<#elseif orderState==50>
				    		<span class="fr">等待卖家确认</span></h1>
				    	<#elseif orderState==60>
				    		<span class="fr">确认成功</span></h1>
				    	<#elseif orderState==0>
				    		<span class="fr">订单已取消</span></h1>
				    	</#if>
			    	</#if>
	            	<#if order.orderGoodsList?size gt 0>
		      			<#assign rowsize = 0>
			  				<#list order.orderGoodsList as orderGoods>
			  					<#assign rowsize = rowsize + orderGoods.goodsNum>
				            	<div class="order_goods_much" >
				        			<div style="width: 80px; float: left;">
					          			<a href="${base}/m/goods/goodsdetail?id=${orderGoods.goodsId}">
							            	<img src="${imgServer}${orderGoods.goodsImage}" width="50" height="50" />
							            </a>
						       		</div>
				        			<div style=" float: left;">
					            		<span style="line-height: 24px">${orderGoods.goodsName}</span><br>
					           		    <#if orderState??>
								            <#if order.orderState==40&& orderGoods.evaluationStatus == 0>
						                		<a href="${base}/m/authc/buyer/reviews?orderSn=${order.orderSn}&recId=${orderGoods.recId}"  style="color:red;">我要评价</a>
						                	<#elseif orderGoods.evaluationStatus == 1>
						                		已评价
						                	</#if>
						            	</#if>
				       			    </div>
			      				</div> 
				            </#list>
				   </#if>
			        <div class="order_total">共 ${rowsize} 件商品 实付：<b>¥ ${order.orderAmount}</b></div>
			        <div class="order_box_bt">
			        <#if orderState??>
				    	<#if orderState==10>
				    		<a href="${base}/m/authc/buyer/orderView?orderId=${order.orderId}" >查看订单</a>
					        <!--<a href="${base}/m/authc/buyer/shipDetail" >查看物流</a>-->
					        <a href="${base}/m/authc/buyer/cancelOrderIndex?orderSn=${order.orderSn}"  >取消订单</a>
					        <a class="bg_orange" href="${base}/weChatpayment/submitOrder?paySn=${order.paySn}&ordersAmount=${order.orderAmount}&paytype=1">付款</a>
			    		<#elseif orderState==20>
				    		<a href="${base}/m/authc/buyer/orderView?orderId=${order.orderId}" >查看订单</a>
				    		<#if order.refundLog??>
	      						<#if order.refundLog.refundState == 1>
									<a class="bg_orange" href="javascript:void(0)" >退款审核中</a></p>
								<#elseif order.refundLog.refundState == 2>
									<a class="bg_orange" href="javascript:void(0)" >同意退款</a>
              					<#elseif order.refundLog.refundState == 3>
              						<a class="bg_orange" href="javascript:void(0)" onclick="alertRefundMsg('退款','${order.refundLog.buyerMessage}', '${order.refundLog.adminMessage}');">已拒绝退款</a>
								</#if>
							<#else>
								<a class="bg_orange" href="${base}/m/authc/buyer/refundOrderIndex?orderId=${order.orderId}">退款</a>
 							</#if>
						<#elseif orderState==30>
				    		<a href="${base}/m/authc/buyer/orderView?orderId=${order.orderId}" >查看订单</a>
				    		<a class="bg_orange" href="${base}/m/authc/buyer/finishOrderIndex?orderSn=${order.orderSn}" >确认收课</a>
				    		<#if order.returnOrder??>
 								<#if order.returnOrder.returnState == 1>
									<a class="bg_orange" href="javascript:void(0)" >退货审核中</a>
								<#elseif order.returnOrder.returnState == 2>
									<a class="bg_orange" href="javascript:void(0)" >同意退货</a>
								<#elseif order.returnOrder.returnState == 3>
          							<a class="bg_orange" href="javascript:void(0)" onclick="alertRefundMsg('退货','${order.returnOrder.buyerMessage}', '${order.returnOrder.returnMessage}');">已拒绝退货</a>
								</#if>
							<#else>
		                		<a class="bg_orange" href="${base}/m/authc/buyer/returnOrderIndex?orderId=${order.orderId}" >退货</a>        	
					       	</#if>
			    		<#elseif orderState==40>
				    		<a href="${base}/m/authc/buyer/orderView?orderId=${order.orderId}" >查看订单</a>
				    	<#elseif orderState==50>
				    		<a href="${base}/m/authc/buyer/orderView?orderId=${order.orderId}" >查看订单</a>
				    	<#elseif orderState==60>
				    		<a href="${base}/m/authc/buyer/orderView?orderId=${order.orderId}" >查看订单</a>
				    	<#elseif orderState==0>
				    		<a href="${base}/m/authc/buyer/orderView?orderId=${order.orderId}" >查看订单</a>   
			    	</#if>
			    	</#if>
			    	</div>
				</#list>
	   		</#if>
	    </div>
	    
	    
	    <#if orderLists??>
	    
	    <#if (orderLists?size gt 0)>
	  
		<div id="pullUp">
			<span class="pullUpIcon"></span><span class="pullUpLabel">上拉加载更多...</span>
		</div>
		 <#else>
		   <div class="dingdan">
				<img src="${base}/res/html5/images/dingdan2_03.png" />
				<p>您还没有相关订单呦！</p>
				<a href="${base}/m/index/index"><p style="top: 210px;left: 38%; font-size: 12px;color: #f94d4d;">去首页逛逛>></p></a>
		</div>
		</#if>
		</#if>
		<!--底部-->
		<!-- 
		
		<@f.foot/>	
		 -->
      

<!--按钮-->
	</div>
</div>
<script>
function url(url){
	var type = jQuery("#url").val();
	url = url+"&&type="+type;
	window.location.href=url;
}

function alertRefundMsg(refmsg, buyer_message, msg) {
	if(buyer_message==null||buyer_message==''||buyer_message=='null') {
		buyer_message = "无";
	}
	if(msg==null||msg==''||msg=='null') {
		msg = "无";
	}
	layer.alert(refmsg+"原因:"+buyer_message + "<br>拒绝原因:"+msg);
}
</script>
<!--<div class="loading" id="loading" style="display:none">
<ul>
  <li><img src="${base}/res/html5/images/loading.gif" width="16" height="16"></li>
  <li>正在加载....</li>
</ul>
</div>
 <div class="loading" id="loading_none" style="display:none">
<ul>
  <li>没有更多了....</li>
</ul>
</div>  --> 
</div>




</body>
</html>
