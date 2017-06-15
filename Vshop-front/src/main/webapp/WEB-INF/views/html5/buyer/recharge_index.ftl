
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,inital-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
<title> 余额充值 - 优彼，微商家</title>
<link href="${base}/res/html5/css/style.css" rel="stylesheet" type="text/css" />
<script src="${base}/res/html5/js/jquery-1.10.2.min.js"></script>
<script src="${base}/res/html5/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${base}/res/js/layer/layer.js"></script>
<script type="text/javascript">
	$(function(){
		$("input[name='amount']").change(function(){
			var reg = /^[-\+]?\d+(\.\d+)?$/; //检验是否为数字的正则表达式
			var amount = $(this).val();
			if(amount==''){
				$(this).val(toDecimal2(0));
			}else if(!reg.test(amount)){
				layer.msg("请您输入正确的金额!",{icon:2});
				$(this).val(toDecimal2(0));
			}else{
				$(this).val(toDecimal2(amount));
			}
		});
	});
	function goPay(){
		var reg = /^[-\+]?\d+(\.\d+)?$/; //检验是否为数字的正则表达式
		var amount = $("input[name='amount']").val();
		if(amount==''){
			layer.msg("请您输入要充值的金额!",{icon:2});
			return false;
		}else if(!reg.test(amount)){
			layer.msg("请您输入正确的金额!",{icon:2});
			return false;
		}
		$("#profile_form").submit();
	}
	
	//准换价格格式
	function toDecimal2(x) {   
	    var f = parseFloat(x);   
	    if (isNaN(f)) {   
	        return false;   
	    }   
	    var f = Math.round(x*100)/100;   
	    var s = f.toString();   
	    var rs = s.indexOf('.');   
	    if (rs < 0) {   
	        rs = s.length;   
	        s += '.';   
	    }   
	    while (s.length <= rs + 2) {   
	        s += '0';   
	    }   
	    return s;   
	}  
</script>
</head>
<body>
<div class="user_bar" style="text-align:center; color:#fff;">
	<a class="back" href="${base}/m/authc/buyer/serviceCenter">
		<img src="${base}/res/html5/images/back.png" width="30" height="30" />
	</a>
	<span style="margin-right:6%;">余额充值</span>
</div>
<div class="phone_main">
  <form  method="post" name="theForm"  id="theForm" action="">
   <div class="phone_login">
	 <ul>
		    <li class="ip" style="border:none; background: none;">
		        		<label style="float:left;">充值账号：</label>
		        		<span id="orderamount">${member.memberName}</span>
		    </li>
		    <li class="yz"></li>
		    <li class="yzm">
	          	<label style="float:left;">充值金额：</label>
	        	<input type="text" maxlength="16" name="amount"  style="width: 50%;height:23px" />
			</li>  
		    <li class="yz"></li>
	 </ul>
  </div>
        <div class="red_submit">
      	   <a href="javascript:void(0)" id="submitBtn"  onclick="goPay();">下一步</a>
        </div>
  </form>
</div>
<div class="note" id="note" style="display:none"></div>
<div class="brand_class_opcity" style="display:none"></div>
<!--底部-->

<!--按钮-->

</body>
</html>
