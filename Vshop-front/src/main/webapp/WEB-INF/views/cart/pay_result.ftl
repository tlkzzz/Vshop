<@p.header title="支付结果－演示商家"/>
<meta charset="utf-8" />
<link href="${base}/res/css/home_cart.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${base}/res/js/layer/layer.js" charset="utf-8"></script>
<style type="text/css">
  #navBar { display: none !important;} 
</style>
<ul class="flow-chart">
  <li class="step a2" title="确认购物清单"></li>
  <li class="step b2" title="确认收货人资料及送货方式"></li>
  <li class="step c1" title="购买完成"></li>
</ul>
<div class="content wrapper">
    <div class="cart-order-info">
      <div class="title">
      	<span class="all-goods-name"></span><span class="goto-order"></span>
      	<span class="order-price" title="应付金额：" id="order_amount">
      	</span>
      	</div>
      <div class="intro">
      	 <#if (status=1)>
	      <p style=" text-align: center; font-size: 20px; line-height: 100px;">${msg}<a href="${base }" style=" font-size: 14px;">&lt;&lt; 返回首页</a><span id="timeto" style=" font-size: 14px;"></span></p>
		 <#else> 
		 	<p style=" text-align: center; font-size: 20px; line-height: 100px;">${msg}<a href="${base }" style=" font-size: 14px;">&lt;&lt; 继续购物</a><span id="timeto" style=" font-size: 14px;"></span></p>
		 </#if>
      </div>
    </div>
</div>
<script type="text/javascript">
var wait=5;
$(function() {
	time();
});
function time() {
	//console.log(wait);
	if(wait==0){
		location.href="${base}";
	}else{
		var timeto = $("#timeto");
		timeto.html("(" + wait + "秒后返回首页)");
		wait--;
		setTimeout(function() {
			time();
		}, 1000);
	}
}
</script>
<@p.footer />