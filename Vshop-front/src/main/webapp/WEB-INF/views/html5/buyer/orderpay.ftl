<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,inital-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
<title>订单支付成功- 优彼，微商家</title>



<link href="${base}/res/html5/css/style.css" rel="stylesheet" type="text/css" />
<script src="${base}/res/html5/js/jquery-1.10.2.min.js"></script>

<script type="text/javascript">
var jsParam=${jsParam};

function wxpay(){
	 if(jsParam!=null){
		WeixinJSBridge.invoke(
				'getBrandWCPayRequest'
				,jsParam
				,callback
			);
	} 
	
}

function callback(res){ 
	//  返回 res.err_msg,取值 
	// get_brand_wcpay_request:cancel   用户取消 
    // get_brand_wcpay_request:fail  发送失败 
    // get_brand_wcpay_request:ok 发送成功 
  
     WeixinJSBridge.log(res.err_msg); 	     	     				 
     if(res.err_msg=='get_brand_wcpay_request:ok')
     {
     	
		 
		 location.replace("${base}/m/authc/buyer/orderpaysuccess");
	
		 
     }
	else
	{
		var msg='支付失败,请重新支付.';
	   // alert(msg);
		// alert(res.err_code+" err_desc="+res.err_desc+" err_msg="+res.err_msg); 	
	}
};





</script>
</head>

<body class="index_bg">
<div class="phone_hd"><a class="back" href="javascript:history.go(-1);"><img src="${base}/res/html5/images/back.png" width="25" height="25" /></a>订单支付<a class="menu home" href="${base}/m/index/index"><img src="${base}/res/html5/images/home.png" width="25" height="25" /></a></div>
<div class="main">
  <div class="mt50" style="height:49px;"></div>
  <div class="error_page1">
    <div  class="backindex">
    <!-- 
     <input name="" type="button"  onclick="wxpay();" class="submit_btn bg_pink" value="立刻支付">
     
      <div class="red_submit"><a class="back" href="javascript:void(0)" onclick="wxpay();" >立刻支付</a>
     -->
         
        
         
         
         	<div class="pay_btn"><a class="back" href="javascript:void(0)" onclick="wxpay();"  >立刻支付</a></div>
     
        </div>
	</div>
  </div>

</div>

<!--底部-->


<!--按钮-->

</body>
</html>
