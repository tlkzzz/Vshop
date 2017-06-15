<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>商品汇平台管理中心</title>
		<link rel="stylesheet" type="text/css" href="${base}/res/login/css/style.css"/>
		<link rel="stylesheet" type="text/css" href="${base}/res/login/css/reset.css"/>
		<link rel="shortcut icon" href="${base}/res/css/favicon.ico"/>
	    <link href="${base}/res/login/css/base.css" rel="stylesheet" type="text/css"/>
	    <script type="text/javascript" src="${base}/res/js/jquery.js"></script>
	    <script>
	        var imgArr=['bg1.jpg','bg2.jpg','bg3.jpg','bg4.jpg'];
	        function switchBg(){
	            var randomBgIndex = Math.floor(Math.random() * imgArr.length);
	            var img_url = '${base}/res/login/images/' + imgArr[randomBgIndex];
	            $("body").css("background", "url(" + img_url + ")");
	        }
	
	        function changeCaptcha(){
	            var captchaImg = '${base}/generateImage?t=' + Math.random();
	            $("#captcha_img").attr("src", captchaImg);
	        }
	
	        $(function(){
	            changeCaptcha();
	            switchBg();
	            setInterval("switchBg()",5000);
	            //跳出框架在主窗口登录
	            if(top.location != this.location) top.location = this.location;
	            $('#username').focus();
	        });
	    </script>
	</head>
	<script type="text/javascript">
		<#if error??>
		    alert('${error}');
		</#if>
	</script>
	<body>
		<div class="wrap">
			<div class="cont_left">
				<img src="${base}/res/login/img/1.jpg" />
			</div>
			<div class="cont">
				<form id="login" action="loginCheck" method="post">
					<div>
						<input type="text" name="username" id="username" autocomplete="off" placeholder="用户名"/>
					</div>
					<div>
						<input style="background:url(${base}/res/login/img/tu3_06.jpg) no-repeat center left;" type="password" name="password" id="password" autocomplete="off"  placeholder="请输入密码"/>
					</div>
					<div>
						<input style="background:url(${base}/res/login/img/tu3_08.jpg) no-repeat center left; width: 200px;" type="text"  name="captcha" id="captcha"  autocomplete="off" placeholder="请输入验证码"/>
	                    <img  class="code-img" style="position: absolute; top: 69%;left: 75%;cursor: pointer;" id="captcha_img" src="" onclick="changeCaptcha()" title="点击更换一张"/>
					</div>
					<div>
						<h1><input style="width: 15px; height: 15px; float: left;" class="remember" type="checkbox" name="remember_me" value="1"/><span>记住用户名</span></h1>
					</div>
					<button name="submit">登录</button>
				</form>
			</div>
			<footer>
				Copyright ©2016-2017 磁石世纪（北京）投资管理有限公司 版权所有  京ICP备16011767号
			</footer>
		</div>
	</body>
</html>
