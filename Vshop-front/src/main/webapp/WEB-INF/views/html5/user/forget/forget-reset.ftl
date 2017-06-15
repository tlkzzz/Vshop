<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,inital-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
<title>登陆页 － 商品汇，微商家</title>
<link href="${base}/res/html5/css/style.css" rel="stylesheet" type="text/css" />
<script src="${base}/res/html5/js/jquery-1.10.2.min.js"></script>
<script src="${base}/res/html5/js/jquery.validate.min.js"></script>
<link href="${base}/res/html5/css2/head.css" rel="stylesheet" type="text/css" />
</head>
<body>
<#--
<div class="phone_hd"><a class="back" href="javascript:history.go(-1);">
<img src="${base}/res/html5/images/back.png" width="25" height="25" /></a>修改密码
<a class="menu home" href="${base}/m/index/index"><img src="${base}/res/html5/images/home.png" width="25" height="25" /></a></div>
-->
<div class="header">
		<a href="javascript:history.go(-1);"><img src="${base}/res/html5/img/fanhui_03.png" /></a>
		<p>
		修改密码
		</p>
		<a href="${base}/m/index/index"><img src="${base}/res/html5/img/fanhui_05.jpg" style="width: 26px; height: 22.5px; margin-left: -15px;" /></a>	
	</div>
	
<div class="phone_main">
	<!--登陆页-->
    <form action="javascript:void(0);" method="post" id="subForm">
	    <input type="hidden" name="memberid" id="memberid" value="${member.memberId}">
	    <input type="hidden" name="validCode" id="validCode" value="${validCode}">
	    <input class="text" type="hidden" name="flags" value="${flags}"/>
    <div class="phone_login">
    	<ul>
        	<li class="ip">
        	<input type="password" name="newPasswd" id="newPasswd" placeholder="新密码" />
        	<label></label>
        	</li>
            <li class="ip">
            <input type="password" name="newPasswd1" id="newPasswd1" placeholder="再输入一遍" />
            <label></label>
            </li>
            <li class="ip_btn"><input type="button" id="updatePasswordSubmit" onclick="toVaild();" value="提 交"></li>
        </ul>
    </div>
	</form>
    <!--底部-->
       <@f.foot/>	

<!--按钮-->
<script type="text/javascript">
	function toVaild(){
		var newPasswd = $("#newPasswd").val();
		var newPasswd1 = $("#newPasswd1").val();
		if(newPasswd==""||newPasswd1==""){
			alert("请输入密码！");
			return false;
		}
		if(newPasswd != newPasswd1){
			alert("2次密码输入不对，请重新输入！");
			return false;
		}
		var url = "${base}/m/forget/updatePWD?"+$("#subForm").serialize();
		$.post(url, function(data){
			if(data == "success"){
				alert("修改成功");
				window.location.href='${base}/m/index/login';
			}
		});
		return false;
	}
</script>
</div>
</body>
</html>
