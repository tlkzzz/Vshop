<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>商品汇平台管理中心-欢迎登录</title>
    <meta name="keywords" content="商城系统演示站"/>
    <meta name="description" content="商城系统演示站"/>
    <meta name="author" content="商城系统演示站">
    <meta name="copyright" content="商城系统演示站">
    <link rel="shortcut icon" href="${base}/res/css/favicon.ico"/>

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
        <h1 class="site-logo"><a href="${base}"><img src="${base}/res/images/common/690ea902fe2708381da2cabff4ee46c8.png" class="pngFix"></a></h1>
        <!--搜索-->
    </header>
</div>
<!-- PublicHeadLayout End -->
<div class="clear"></div>
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
                <h3>修改密码</h3>
            </div>
            <div class="nc-login-content" id="demo-form-site">
                <form action="javascript:void(0);" method="post" id="subForm">
                   <input type="hidden" name="memberid" id="memberid" value="${member.memberId}">
                   <input type="hidden" name="validCode" id="validCode" value="${validCode}">
                   <input class="text" type="hidden" name="flags" value="${flags}"/>
                    <dl>
                        <dt>
                       		<#if flags ??&& flags==9>
	                          		 密码：
	                 		<#else>
	                   				 新密码：
	                 		</#if>
		                 </dt>
                        <dd style="min-height:54px;">
                            <input class="text" type="password" name="newPasswd" id="newPasswd">
                            <label></label>
                        </dd>
                    </dl>
                    <dl>
                        <dt>
							<#if flags ??&& flags==9>
		                          		 再次输入密码：
		                 		<#else>
		                   				 重复密码：
	                 		</#if>
						</dt>
                        <dd style="min-height:54px;">
                            <input class="text" type="password" name="newPasswd1" id="newPasswd1">
                            <label></label>
                        </dd>
                    </dl>
                    <dl>
                        <dt>&nbsp;</dt>
                        <dd>
                            <button id="updatePasswordSubmit" class="submit" onclick="toVaild();">提 交</button>
                    </dl>
                </form>
            </div>
        </div>
        <div class="clear"></div>
    </div>
</div>
<script type="text/javascript">
function toVaild(){
	var newPasswd = $("#newPasswd").val();
	var newPasswd1 = $("#newPasswd1").val();
	if(newPasswd==""||newPasswd1==""){
		layer.alert("请输入密码！",3);
		return false;
	}
	if(newPasswd != newPasswd1){
		layer.alert("2次密码输入不对，请重新输入！",3);
		return false;
	}
	var url = "${base}/forget/updatePWD?"+$("#subForm").serialize();
	$.post(url, function(data){
		if(data == "success"){
			layer.msg("修改成功" , {icon:1});
			setTimeout("ok()",1500); 
			
		}
	});
	return false;
}
function ok(){
	window.location.href='${base}/login';
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
