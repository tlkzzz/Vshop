<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,inital-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
<title>商品咨询 - 优彼，微商家</title>
<link href="${base}/res/html5/css/style.css" rel="stylesheet" type="text/css" />
<script src="${base}/res/html5/js/jquery-1.10.2.min.js"></script>
<script src="${base}/res/js/layer/layer.js"></script>

<!-- 上拉刷新 -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" type="text/css" href="${base}/res/html5/css/scrollbar.css">
<script type="application/javascript" src="${base}/res/html5/js/iscroll.js"></script>

<link rel="stylesheet" type="text/css" href="${base}/res/html5/css2/reset.css"/>
<link rel="stylesheet" type="text/css" href="${base}/res/html5/css2/zaixian.css"/>
		
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
		});

		function pullUpAction () {
			pageNo = pageNo + 1;
			var url = "${base}/m/goods/goodsConsultPage";
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
	 
	 //咨询
	function showConsult() {
	    $(".form-consult").show();
	}
	//提交
	function subConsult() {
		var content = $("#consultContent").val();
	    if (content != '') {
	        $.ajax({
	            url: '${base}/product/saveConsult',
	            dataType: 'json',
	            data: $("#consultForm").serializeArray(),
	            success: function (data) {
	                if (data.success) {
	                    // layer.msg(data.msg, 1, 9);
	                    layer.msg("咨询发布成功" , {icon:1});
	                    $("#consultContent").val("");
	                    $(".form-consult").hide();
	                    window.location.reload();
	                } else {
	                	layer.msg("咨询发布失败" , {icon:2});
	                }
	            }
	        });
	    }
	}
</script>
</head>

<body class="user_bg">

			<div class="header">
				<a href="${base}/m/goods/goodsdetail?id=${goodsId}"><img src="${base}/res/html5/img/fanhui_03.png" /></a>
				<p>咨询</p>
				<a href="${base}/m/index/index" style="width:22.5px;">
				<img src="${base}/res/html5/images/home.png" style="width: 22.5px; height: 22.5px; margin-left: -15px;"/></a>
			</div>
	
	<#--
	<div class="user_bar">
		<a class="back" href="${base}/m/goods/goodsdetail?id=${goodsId}">
		<img src="${base}/res/html5/images/back_black.png" width="30" height="30" /></a><span class="fl"></span></div>
	-->
	
			<div class="but"><p>今天10:17</p></div>
			<div class="yishu_conter">
				<dl>
					<dt><img src="${base}/res/html5/img/yishu_03.png"/></dt>
					<dd>
						<p>匠心之笔01款</p>
						<p style="color: red;">¥287.00</p>
					</dd>
				</dl>
				<div class="but1">
					<p>发送宝贝链接</p>
				</div>
			</div>
	
<div class="order_page" style="padding:0px;">
<div id="wrapper">
	<div id="scroller">
		<div id="pullDown" style="display:none;"></div>
		<div class="order_page_box" id="thelist">
		
		<div class="liao" style="height:270px;">
		
		<#assign consultInfoTag =newTag("consultInfoTag")>
		<#assign consultInfo =consultInfoTag("{'goodsId':'${goodsId}','pageNo':'${pageNo}'}")>
		<#if consultInfo?size gt 0>
			<#list consultInfo.result as consult>
			<#--
			    	<h1><span class="fl">
			    		<#if consult.memberId==0>
								游客
						</#if>
						<#if consult.memberId!=0>
							<#if consult.isanonymous>
								匿名
							<#else>
								${consult.cmemberName}
							</#if>                                	
						</#if>
			    		</span>
		    			<span class="fr">时间:${consult.consultAddtimeStr}</span>
		    		</h1>
		    		 -->
		    	<div class="prview_left">
					<div class="preview-avator">
						<#if consult.memberId==0>
							<img src="${base}/res/html5/img/zixun_03.png" width="50" height="50" title="游客"/>
						</#if>
						<#if consult.memberId!=0>
							<#if consult.isanonymous>
								<img src="${base}/res/html5/img/zixun_03.png" width="50" height="50" title="匿名"/>
							<#else>
								<img src="${consult.memberAvatar}" width="50" height="50" title="${consult.cmemberName}"/>
							</#if>                                	
						</#if>
					</div>
					<div class="preview">
						${consult.consultContent}
					</div>
					<div class="left_arrow"></div>
					<div class="time">
						<span>${consult.consultAddtimeStr}</span>
					</div>
				</div>
				<#if consult.consultReply!=null>
				<div class="prview_right">
					<div class="preview-avator1">
						<img src="${base}/res/html5/img/zixun_07.png" width="50" height="50" />
					</div>
					<div class="preview1">
						${consult.consultReply}
					</div>
					<div class="left_arrow1"></div>
					<#--
					<div class="time">
						<span>${consult.consultReplyTimeStr}</span>
					</div>
					-->
				</div>
		    	</#if>
		    	<#--
				        <div class="order_goods_much">
				            <ul>
				            	${consult.consultContent}
				            </ul>
				            <#if consult.consultReply!=null>
				            <ul>
				            	回复: ${consult.consultReply}
				            </ul>
				            </#if>
				        </div>
				        
				         <div class="order_total">商家名称：<b>${consult.storeName}</b></div>
				        
			    	<hr style="height:1px; border:none; border-top:1px dotted #185598;" />
			    	 -->
			</#list>
	   	</#if>
	   	
	   	</div>
	   	</div>
		<div id="pullUp" style="display:none;">
			<!-- <span class="pullUpIcon"></span><span class="pullUpLabel"></span> -->
		</div>
	</div>
</div>
</div>
		<#assign goodsBaseTag =newTag("goodsBaseInfoTag")>
		<#assign goodsInfo =goodsBaseTag("{'goodsid':'${goodsId}'}")>
		
		<div class="zi">购买之前，如有问题，请向商优彼城咨询。</div>
		<form id="consultForm" method="post" action="${base}/product/consult" name="queryListForm">
			<input type="hidden" name="goodsId" value="${goodsInfo.goodsId}"/>
            <input type="hidden" name="cgoodsName" value="${goodsInfo.goodsName}"/>
            <input type="hidden" name="storeId" value="${goodsInfo.storeId}"/>
            <input type="hidden" name="div" id="div" value="#kfzxData"/>
			<textarea name="consultContent" id="consultContent" rows="6" cols="10" 
				onfocus="if(this.value=='请输入不要超过150字') {this.value='';}" onblur="if(this.value=='') {this.value='请输入不要超过150字';}" value="请输入不要超过150字" maxlength="20">请输入不要超过150字</textarea>
			<button type="button" class="btng" onclick="subConsult()">提交</button>
		</form>
			<#--
	<div class="product-consult" id="kfzx">
            <!-- <h3><b>客服咨询</b></h3><a name="explain"></a> -- //>

            <p class="explain">购买之前，如有问题，请向商品优彼咨询。
                <a href="javascript:;" style="color: #3366CC;" onclick="showConsult()">我要咨询</a></p> -->
                
                <#--
            <dl class="form-consult" style="display:none">
                <form id="consultForm" method="post" action="${base}/product/consult" name="queryListForm">

                    <input type="hidden" name="goodsId" value="${goodsInfo.goodsId}"/>
                    <input type="hidden" name="cgoodsName" value="${goodsInfo.goodsName}"/>
                    <input type="hidden" name="storeId" value="${goodsInfo.storeId}"/>
                    <input type="hidden" name="div" id="div" value="#kfzxData"/>
                    <dt>内容：</dt>
                    <dd><textarea class="textarea" name="consultContent" id="consultContent" style="width: 600px; height: 100px;"></textarea>

                        <p class="text-note">※ 请输入不要超过 <b>150</b> 个字</p>
                        <button type="button" class="btng" onclick="subConsult()">提交</button>
                    </dd>
                </form>
            </dl>
           
        </div>
         -->
<!--底部-->
<!-- 
<@f.foot/>	
 -->

<!--按钮-->

</body>
</html>
