<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,inital-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
<title>发红包</title>
<link href="${base}/res/html5/css/style.css" rel="stylesheet" type="text/css" />
<script src="${base}/res/html5/js/jquery-1.10.2.min.js"></script>
<script src="${base}/res/html5/js/jquery.validate.min.js"></script>
<link href="${base}/res/html5/css2/head.css" rel="stylesheet" type="text/css" />
<script>

</script>
</head>
<body>

<div class="header">
		<a href="javascript:history.go(-1);"><img src="${base}/res/html5/img/fanhui_03.png" /></a>
		<p>
		发红包
		</p>
		<a href="${base}/m/index/index"><img src="${base}/res/html5/img/fanhui_05.jpg" style="width: 26px; height: 22.5px; margin-left: -15px;" /></a>	
	</div>
	
<div class="phone_main">
  <!--注册页-->
  <form action="" method="post" name="theForm" id="theForm">
    <div class="phone_login">
      <ul>
        <li class="ip ">
          <input type="text" id="user_name" name="memberName"  placeholder="请输入用户名" title="3-20位字符，可由中文、英文、数字及“_”、“-”组成" />
        </li>
        <li class="yz"></li>
        <li class="ip">
          <input type="password" id="password" name="memberPasswd" placeholder="请输入密码" title="6-20位字符，可由英文、数字及标点符号组成"/>
        </li>
        <li class="yz"></li>
        <li class="ip">
          <input type="password" id="password_confirm" name="password_confirm" placeholder="请确认密码" title="请再次输入您的密码"/>
        </li>
        <li class="yz"></li>
        <li class="ip">
          <input type="text" id="email" name="memberEmail" placeholder="请输入您的邮箱" title="请输入常用的邮箱，将用来找回密码、接受订单通知等"/>
        </li>
        <li class="yz"></li>
        <li class="ip">
          <input type="text" id="mobile" name="memberMobile" placeholder="请输入您的手机号" title="请输入您的手机号"/>
        </li>
        <li class="yz"></li>
                <script>
                 function refreshCode(){
                	 var captchaImg = '${base}/generateImage?t=' + Math.random();
                     $("#code_img").attr("src", captchaImg);
                 }
             </script>
        <li class="yzm">
          <input name="code" type="text"  id="code" placeholder="请输入验证码" />
          <img src="generateImage"  onclick="refreshCode()" id="code_img" width="73" height="27"/>
	      <a href="javascript:void(0);" onClick="refreshCode();" >
          	<img src="${base}/res/html5/images/refresh.png" width="32" height="32" />
          </a>
        </li>
        <li class="yz"></li>        
        <!-- <li>
          <input name="agree" type="checkbox"  id="agree" value="true" checked="checked" />
          <label for="agree">我已阅读并同意 </label>
          <a href="http://b2b2c.iskyshop.com/wap/doc.htm?mark=register"> 《商城在线服务协议》</a></li><li class="yz"> -->
        <li class="ip_btn">
          <input id="submitBtn" type="submit"   value="立即注册"/>
        </li>
        <li><span class="fl"><a href="${base}/m/index/login">登陆</a></span><span class="fr">
        <#--	<a href="${base}/m/forget/index">找回密码</a> -->
        	</span></li>
      </ul>
    </div>
  </form>
  <!--底部-->
  <!--底部-->
  
  <!--  
       <@f.foot/>
       
       -->
<!--按钮-->
 </div>
</body>
</html>
