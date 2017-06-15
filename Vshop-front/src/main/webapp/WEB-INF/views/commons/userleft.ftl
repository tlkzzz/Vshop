<#macro left title="">

<#assign memberInfoTag =newTag("memberInfoTag")>
<#assign member =memberInfoTag("")>


<div class="sidebar">
	<dl class="ncu-user">
 		<dt class="username"><a href="${base}/user/setting/index" title="编辑用户信息">${member.memberName}</a></dt>
 		<dd class="userface">
    		<div class="pic">
    			<span class="thumb size100">
    				<i></i>
    				<#if member.memberAvatar??>
	    				<img src="${imgServer}${member.memberAvatar}" onload="javascript:DrawImage(this,100,100);" alt="${member.memberName}" />
    				<#else>
	    				<img src="upload/common/default_user_portrait.gif" onload="javascript:DrawImage(this,100,100);" alt="${member.memberName}" />
    				</#if>
    			</span>
      			<p><a href="${base}/user/setting/index" title="上传新头像">上传新头像</a><i></i></p>
    		</div>
 		</dd>
 		<dd class="info">
   			<ul>
     			<li>会员信用：${member.memberCredit}</li>
            	<li>积分数：${member.memberPoints}</li>
            	<!-- <li>预存款：<span class="price">0.00</span>元</li> -->
        	</ul>
 		</dd>
	</dl>
	<div class="business-intro">
 		<h3>交易信息</h3>
 		<ul>
   			<li>
   				<span><a href="${base}/user/list?orderState=10">待付款订单</a></span> 
   				<#if member.noPayOrder gt 0>
   					<i class='yes'>${member.noPayOrder}</i>
   				<#else>
   					<i class='no'>${member.noPayOrder}</i>
   				</#if>
   			</li>
   			<li>
   				<span><a href="${base}/user/list?orderState=30">待确认收货</a></span> 
   				<#if member.noReceiveOrder gt 0>
   					<i class='yes'>${member.noReceiveOrder}</i>
   				<#else>
   					<i class='no'>${member.noReceiveOrder}</i>
   				</#if>
   			</li>
   			<li>
   				<span><a href="${base}/user/list?evaluationStatus=0">待评价交易</a></span> 
   				<#if member.noEvaluationOrder gt 0>
   					<i class='yes'>${member.noEvaluationOrder}</i>
   				<#else>
   					<i class='no'>${member.noEvaluationOrder}</i>
   				</#if>
   			</li>
 		</ul>
	</div>
	<script type="text/javascript">
		var myMenu;
		window.onload = function() {
			myMenu = new SDMenu("my_menu");
			myMenu.init();
		};
	</script>
	<div class="business-handle" id="my_menu">
		<h3>交易操作</h3>
        <div class='active'><em class="i2"></em><a href="${base}/user/list">已买到的商品</a></div>
		<!--<div><em class="i3"></em><a href="javascript:void(0)">购买过的商家</a> </div>-->
   		<dl class="">
			<dt><em class="i4"></em><a href="javascript:void(0)">我的收藏</a><i></i></dt>
			<dd class='normal'><a href="${base}/myuser/goodsFavIndex">收藏商品</a></dd>
			<dd class='normal'><a href="${base}/myuser/storeindex">收藏商家</a></dd>
		</dl>
		<dl class="">
			<dt><em class="i5"></em><a href="javascript:void(0)">我的积分</a><i></i></dt>
			<dd class='normal'><a href="${base}/user/shoppoints/mypointslogindex">积分明细</a></dd>
	    	<!-- <dd class='normal'><a href="#?act=member_pointorder">已兑换的商品</a></dd> -->
	    </dl>
	    <dl class="">
			<dt><em class="i5"></em><a href="javascript:void(0)">我的余额</a><i></i></dt>
			<dd class='normal'><a href="${base}/predeposit/predepositIndex">余额</a></dd>
	    	<!-- <dd class='normal'><a href="#?act=member_pointorder">已兑换的商品</a></dd> -->
	    </dl>
	    <!-- <div class='normal'>
	      	<em class="i6"></em>
	      	<a href="#?act=member_voucher">我的代金券</a>
		</div> -->
		<div class='normal'>
	      	<em class="i7"></em>
	      	<a href="${base}/myReviews/reviewsBuyerList">评价管理</a>
		</div>
		<dl class="">
			<dt><em class="i8"></em><a href="javascript:void(0)">咨询与维权</a><i></i></dt>
			<!-- <dd class='normal'><a href="${base}/myInform/index">我的举报</a></dd> -->
			<dd class='normal'><a href="${base}/myconsult/index">我的咨询</a></dd>
	  		<!-- <dd class='normal'><a href="#?act=member_complain">投诉管理</a></dd> -->
		</dl>
		<div class='normal'>
	      	<em class="i7"></em>
	      	<a href="${base}/user/mycoupon/myCouponIndex">优惠券管理</a>
		</div>
	</div>
</div>	
</#macro>