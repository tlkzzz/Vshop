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
    <link rel="shortcut icon" href="${imgServer}${siteSet.siteLogo1}"/>

    <link href="res/css/base.css" rel="stylesheet" type="text/css">
    <link href="res/css/home_header.css" rel="stylesheet" type="text/css">
    <script src="res/js/jquery.js" ></script>
</head>
<body>
<!--头部-->
<!-- PublicHeadLayout Begin -->
<div class="header-wrap">
    <header class="public-head-layout wrapper">
        <!--LOGO-->
        <h1 class="site-logo"><a href="${base}"><img src="${imgServer}${siteSet.siteLogo2}" class="pngFix"></a></h1>
        <!--搜索-->
    </header>
</div>
<div class="clear"></div>
<style type="text/css">
    .wrapper {
        width: 1000px;
    }#footer-top ul li{margin-right:60px;}body,.header-wrap{background-color:#f2f2f2;}
</style>
<div class="nc-login-layout">
    <div class="nc-login-main">
            <div class="nc-login-content" id="demo-form-site">
                <div class="intro">
					 <p style="text-align: center; font-size: 20px; line-height: 100px;">抱歉您没有访问权限<a href="${sellerServer}" style=" font-size: 14px;">&lt;&lt;返回</a><span id="timeto" style=" font-size: 14px;"></span></p>
			    </div>
            </div>
        <div class="clear"></div>
    </div>
</div>
<script>
var wait=5;
$(function() {
	time();
});
function time() {
	//console.log(wait);
	if(wait==0){
		location.href="${sellerServer}";
	}else{
		var timeto = $("#timeto");
		timeto.html("(" + wait + "秒后返回供应商中心)");
		wait--;
		setTimeout(function() {
			time();
		}, 1000);
	}
}
</script>
<div class="clear"></div>
<!-----footer------>
<div class="footer">
    <!--底部版权，调用footer -->
    <div id="footer" >
        <div class="wrapper">
            <!--  <p><a href="${frontServer}">首页</a>
                | <a  href="#?act=article&article_id=24">招聘英才</a>
                | <a  href="#?act=article&article_id=25">广告合作</a>
                | <a  href="#?act=article&article_id=23">联系我们</a>
                | <a  href="#?act=article&amp;article_id=22">关于我们</a>
            </p> -->
         ${siteSet.siteDbxx}
<!--             &nbsp;&nbsp; -->
<!--             ICP证： -->
            <br/>
<!--             <div class="footer-logo"> -->
<!--                 <ul> -->
<!--                     <li class="caifutong"></li> -->
<!--                     <li class="caifubao"></li> -->
<!--                     <li class="beifen"></li> -->
<!--                     <li class="kexin"></li> -->
<!--                     <li class="shiming"></li> -->
<!--                     <li class="wangzhan360"></li> -->
<!--                     <li class="anquanlianmeng"></li> -->
<!--                     <div class="clear"></div> -->
<!--                 </ul> -->
<!--             </div> -->
        </div>
    </div>
</div>
</body>
</html>
