<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>确认收货</title>
<meta name="viewport" content="width=device-width,inital-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<link href="${base}/res/html5/css/style.css" rel="stylesheet" type="text/css" />
<script src="${base}/res/html5/js/jquery-1.10.2.min.js"></script>
<script src="${base}/res/html5/js/jquery.validate.min.js"></script>
<script src="${base}/res/js/jquery.js" charset="utf-8"></script>
<script src="${base}/res/js/common.js" charset="utf-8"></script>
<script src="${base}/res/js/layer/layer.js" charset="utf-8"></script>
<script type="text/javascript">
/**确认订单**/
function finishOrder() {	
	var orderSn=$("#orderSn").val();
	var fmUrl = '${base}/m/authc/buyer/finishOrder';
	$.ajax({
	           type: "post",
	           url: fmUrl,
	           data: {orderSn:orderSn},
	           dataType: "json",
		 async:false,
		 success:function(data) {
			if(data.success){
				layer.alert("确认收货成功",{icon:1});	
			}else{
				layer.alert("确认收货失败",{icon:2});		
			}
		 }
	}); 

	    
	
}
</script>
</head>
<body>
	<div class="phone_hd"><a class="back" href="javascript:history.go(-1);"><img src="${base}/res/html5/images/back.png" width="25" height="25" /></a>返回<a class="menu home" href="${base}/m/index/index"><img src="${base}/res/html5/images/home.png" width="25" height="25" /></a></div>
	<div class="phone_main" >
	    <form action="" method="post" id="myForm">
			<input type="hidden" name="orderSn" id="orderSn" value="${orderSn}">
		    <div class="phone_login" style="margin-top:0px;">
		    	<ul>
		    		<li class="ip" style="border:none; background: none;">
		        		<span id="orderamount">您是否确已经收到以下订单的货品?</span>
		        	</li>
		        	<li class="ip" style="border:none; background: none;">
		        		<label style="float:left;">订单编号：</label>
		        		<span id="orderamount">${orderSn}</span>
		        	</li>
		          	<li class="yz"></li>
		        	<li class="yzm">
			          	<span id="orderamount">请注意： 如果你尚未收到货品请不要点击“确认”。大部分被骗案件都是由于提前确认付款被骗的，请谨慎操作！ </span>
			        </li>
			        <li class="ip_btn"><input id="confirm_button" type="button" onclick="finishOrder()" value="确定" /></li>
		        </ul>
		    </div>
		</form>  
	</div>
</body>
</html>

