
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
<title>订单支付 - 优彼，微商家</title>
<#-- <link href="${base}/res/html5/css/style.css" rel="stylesheet" type="text/css" /> -->
<link rel="stylesheet" type="text/css" href="${base}/res/html5/css2/reset.css"/>
<link rel="stylesheet" type="text/css" href="${base}/res/html5/css2/zhifu.css"/>
		
<script src="${base}/res/html5/js/jquery-1.10.2.min.js"></script>
<script>
jQuery(document).ready(function(e) {
    jQuery(".pay_style").click(function(){
		jQuery(".pay_style").removeClass("this");
		jQuery(this).addClass("this");
		var index = jQuery(this).index(".pay_style");
		jQuery(" input[name=payType]").get(index).checked=true;
	});
	 if(isWeiXin()){
       jQuery("#wx").show();
     }else{
		 jQuery("#ali").show();
		 }
});<!--end-->
var type="goods";
function isWeiXin(){
    var ua = window.navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
        return true;
    }else{
        return false;
    }
}
</script>
</head>

<body>
<div class="wrap">
			<div class="header">
				<a href="javascript:history.go(-1);"><img src="${base}/res/html5/img/fanhui_03.png" /></a>
				<p>订单支付</p>
				<a href="${base}/m/index/index"><img src="${base}/res/html5/images/home.png" style="width: 22.5px; height: 22.5px; margin-left: -15px;"/></a>
			</div>

			<div class="zhifu">
				<dl>
					<dt><img src="${base}/res/html5/images/dun.png" style="background:#fff; width: 62px; height: 80px;"></dt>
					<dd>
			        <ul>
			        	<#if 1=='${paytype}'>
			          		<li style="font-size:20px; margin-bottom:10px;">订单支付</li>
			          	<#else>
			          		<li style="font-size:20px; margin-bottom:10px;">下单成功！等待卖家确认！</li>
			          	</#if>
			            <li>应付金额：<span class="red">¥ ${ordersAmount}</span></li>
			            <li></li>
			          </ul>
			        </dd>
				</dl>
			</div>
			
   
<#-- 

<div class="pd5">
  <div class="payment">
    <div class="pay_detail">
      <dl>
        <dt><img src="${base}/res/html5/images/dun.png" width="62"></dt>
        <dd>
          <ul>
          	<#if 1=='${paytype}'>
          		<li style="font-size:20px; margin-bottom:10px;">订单支付</li>
          	<#else>
          		<li style="font-size:20px; margin-bottom:10px;">下单成功！等待卖家确认！</li>
          	</#if>
            
            <li>应付金额：<span class="red">¥ ${ordersAmount}</span></li>
            <li></li>
          </ul>
        </dd>
      </dl>
    </div>
    
<form id="theForm" method="post" action="">
    <div class="payfor">
      <h1>请选择支付方式：</h1>
                              <table class="pay_table">
        <tbody>
          <tr>
            <td colspan="2"><span style="border-bottom:1px solid #F00">预存款支付</span></td>
          </tr>
          <tr>
            <td width="12%" align="center"><input type="radio" name="payType" id="payType" value="balance"></td>
            <td><div class="pay_style"><a href="javascript:void(0)">预存款</a></div></td>
          </tr>
        </tbody>
      </table>
       </div> 
       <div class="pay_btn"><a href="${base}/alipayh5ment/topay?paySn=${orderPaySn}" >支付宝支付</a></div>
    </form>
    
    <div class="wei">
    			<p>
    				<#if 1=='${paytype}'>
          				订单支付
          			<#else>
          				下单成功！等待卖家确认！
          			</#if>
    			</p>
    		</div>
       -->
    		<div class="wei">
				<img src="${base}/res/html5/img/weixin_07.png" />
				<p>微信支付</p>
			</div>
			<button>
				<a href="${base}/weChatpayment/submitOrder?paySn=${orderPaySn}" style="color:#fff;">支付</a>
			</button>
			<div class="qita">
				<p>使用其他方式支付</p>
			</div>
    
</div>

</body>
</html>
