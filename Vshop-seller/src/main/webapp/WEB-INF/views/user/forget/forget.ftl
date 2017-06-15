<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <#assign siteSettingTag = newTag("siteSettingTag") />
	<#assign siteSet = siteSettingTag("") />
  <title>${siteSet.siteTitle}</title>
    <meta name="keywords" content="商城系统演示站"/>
    <meta name="description" content="商城系统演示站"/>
    <meta name="author" content="商城系统演示站">
    <meta name="copyright" content="商城系统演示站">
    <link rel="shortcut icon" href="${imgOssServer}${siteSet.siteLogo2}"/>

    <link href="${base}/res/css/base.css" rel="stylesheet" type="text/css">
    <link href="${base}/res/css/home_header.css" rel="stylesheet" type="text/css">
    <link href="${base}/res/css/home_login.css" rel="stylesheet" type="text/css">
    <script src="${base}/res/js/jquery.js" ></script>
    <script src="${base}/res/js/jquery.ui.js" ></script>
    <script src="${base}/res/js/jquery.validation.min.js" ></script>
    <script type="text/javascript" src="${base}/res/js/layer/layer.js"></script>
</head>
<body>
<!--头部-->
<!-- PublicHeadLayout Begin -->
<div class="header-wrap">
    <header class="public-head-layout wrapper">
        <!--LOGO-->
       <div class="logo"><!--style="height:60px;width:300px;-->
		<img src="${imgServer}${siteSet.siteLogo2}" style="max-height:60px;max-width:300px;"class="pngFix" />
		
	</div>
    </header>
</div>
<!-- PublicHeadLayout End -->
<div class="clear"></div>
<script type="text/javascript">
    $(function () {
        $("#username").focus();
    });
    function changeCaptcha() {
        var captchaImg = '${base}/generateImage?t=' + Math.random();
        $("#captcha_img").attr("src", captchaImg);
    }
</script>
<style type="text/css">
    .wrapper {
        width: 1000px;
    }#footer-top ul li{margin-right:60px;}body,.header-wrap{background-color:#f2f2f2;}
</style>
<div class="nc-login-layout">
    <div class="nc-login-main">
        <div class="left-pic"><img src="${base}/res/images/login/3.jpg"  border="0"></div>
        <div class="nc-login">
            <div class="nc-login-title">
                <h3>重设密码</h3>
            </div>
            <div class="nc-login-content" id="demo-form-site">
                <form action="${base}/forget/checkValidCode" method="post" id="myForm" onsubmit="return toVaild()">
                    <dl>
                        <dt>手机号码</dt>
                        <input class="text" type="hidden" name="flags" value="${flags}"/>
                        <dd style="min-height:54px;">
                            <input type="text" class="text"  placeholder="输入您的手机号码" name="mobile" id="mobile" autocomplete="off"/>
                            <label></label>
                        </dd>
                    </dl>
                    <dl>
                        <dt>验证码</dt>
                        <dd style="min-height:54px;">
                            <input class="text w50 fl" type="text" name="validCode" id="validCode"/>
                            <input class="btng" type="button" id="validBtn" class="code_disabled" value="免费获取">
                            <br>&nbsp;&nbsp;&nbsp; <font id="captcha_img"></font><#if error??><span id="erro-span" style="color:red">${error}</span></#if><span id="test"></span>
                            <label style="color: red;" id="errors">
                            </label>
                        </dd>
                    </dl>
                    <dl>
                        <dt>&nbsp;</dt>
                        <dd>
                            <input type="submit" class="submit" id="forgetSubmit" value="下一步">
                    </dl>

                </form>
            </div>
        </div>
        <div class="clear"></div>
    </div>
</div>
<script type="text/javascript">
	var wait=60;
	function time(o) {
		console.log(wait);
		if (wait == 0) {
			o.removeAttr("disabled");
			o.val("免费获取验证码");
			o.removeClass("code_disabled");
			wait = 60;
		} else {
			o.attr("disabled", true);
			o.addClass("code_disabled");
			o.val("重新发送(" + wait + ")");
			wait--;
			setTimeout(function() {
				time(o)
			}, 1000)
		}
	}
	/* var reg =/^0{0,1}(13[0-9]|15[0-9]|15[0-9]|18[0-9]|17[0-9])[0-9]{8}$/; */
	var reg = /^1\d{10}$/;
	//添加数量
	$(function() {
		//鼠标操作数量 离开文本框 
		//保存调用的js
		$("#validBtn").click(function() {
			var self = $(this);
			var mobile = $('#mobile').val();//
			if(!reg.test(mobile)){
				layer.alert("请输入正确的手机号!",3);
				return false;
			} 
			var getTimestamp = new Date().getTime();
			time(self);
			$.post("${base}/forget/getValid?mobile=" + mobile
					+ "&currStr=" + getTimestamp, null, function(data) {
				if (data) {
					if (data.success == "true") {
						$("#erro-span").html("");
						$("#test").html(data.message);
						//$("#captcha_img").html(data.validCode);
					} else {
						layer.alert(data.message, 3);
					}
				}
			});
		});
	});
	
	function toVaild(){
		var mobile = $('#mobile').val();//
		var validCode = $('#validCode').val();//
		if(!reg.test(mobile)){
			layer.alert("请正确输入手机号!",3);
			return false;
		} 
		if(validCode==""){
			layer.alert("请输入验证码!",3);
			return false;
		}
 	}
</script>
<div class="clear"></div>
<!-----footer------>
<div class="footer">
    <!--底部版权，调用footer -->
    <div id="footer" >
        <div class="wrapper">
            <p><a href="/">首页</a>
                | <a  href="#?act=article&article_id=24">招聘英才</a>
                | <a  href="#?act=article&article_id=25">广告合作</a>
                | <a  href="#?act=article&article_id=23">联系我们</a>
                | <a  href="#?act=article&amp;article_id=22">关于我们</a>
            </p>
            Copyright 2016-2017 磁石世纪（北京）投资管理有限公司 版权所有  京ICP备16011767号.&nbsp;&nbsp;
            ICP证：<br/>
            <div class="footer-logo">
                <ul>
                    <li class="caifutong"></li>
                    <li class="caifubao"></li>
                    <li class="beifen"></li>
                    <li class="kexin"></li>
                    <li class="shiming"></li>
                    <li class="wangzhan360"></li>
                    <li class="anquanlianmeng"></li>
                    <div class="clear"></div>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
