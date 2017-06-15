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
    <link href="res/css/home_login.css" rel="stylesheet" type="text/css">
    <link href="res/css/login.css" rel="stylesheet" type="text/css">
    <script src="res/js/jquery.js" ></script>
    <script src="res/js/jquery.ui.js" ></script>
    <script src="res/js/jquery.validation.min.js" ></script>
    <script src="${base}/res/js/html5shiv.min.js"></script>
    <script type="text/javascript" src="${base}/res/js/layer/layer.js"></script>
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
        <div class="left-pic"><img src="res/images/login/3.jpg"  border="0"></div>
        <div class="nc-login">
            <div class="nc-login-title">
                <h3>用户登录</h3>
            </div>
            <div class="nc-login-content" id="demo-form-site">
                <form id="login_form" action="" class="bg" method="post">
                    <dl>
                        <dt>用户名</dt>
                        <dd style="min-height:54px;">
                            <input type="text" class="text" autocomplete="off"  name="username" id="username">
                            <label></label>
                        </dd>
                    </dl>
                    <dl>
                        <dt>密&nbsp;&nbsp;&nbsp;码 </dt>
                        <dd style="min-height:54px;">
                            <input type="password" class="text" name="password" autocomplete="off"  id="password">
                            <label></label>
                        </dd>
                    </dl>
                    <dl>
                        <dt>验证码</dt>
                        <dd style="min-height:54px;">
                            <input class="text w50 fl" type="text" name="captcha" id="code" maxlength="4" size="10"/>
                            <img src="generateImage" title="看不清？点击换一张" onclick="changeCaptcha()" border="0" id="captcha_img" class="fl ml5"/>
                            <a href="javascript:void(0)" class="ml5" onclick="changeCaptcha()">看不清，换一张</a>
                            <label style="color: red;" id="errors">
                        </dd>
                    </dl>
                    <dl>
                    	<dt>&nbsp;</dt>
                        <dd style="float:left;">
                            <a href="JavaScript:void(0);" class="submit" id="submitBtn"><span>登&nbsp;&nbsp;&nbsp;录</span></a>
                            <!-- <input type="submit" class="submit" value="登&nbsp;&nbsp;&nbsp;录" name="Submit"> -->
                            <a class="forget" href="${base}/forget/index">忘记密码？</a><input type="hidden" value="index" name="ref_url">
                        </dd>
                    </dl>

                </form>
                <dl class="mt10 mb10">
                    <dt>&nbsp;</dt>
                    <dd>还不是本站会员？立即<a title="注册" href="signUp" class="register btn">注册</a>
                    </dd>
                </dl>
               <!-- <dl>
                	<dt>合作登录：</dt>
                	<dd>
                		<div class="login_entry">
							<ul class="account_list_big clearfix">
								<li><a href="${qqnurl}" target="_blank" class="qq" title="QQ"></a></li>
								<li><a href="${sinaurl}" target="_blank" class="sina" title="新浪微博"></a></li>
								<li><a href="${weixinurl}" target="_blank" class="weixin" title="微信"></a></li>
							</ul>
						</div>
                	</dd>
                </dl>-->
                
            </div>
        </div>
        <div class="clear"></div>
    </div>
</div>
<script>
	// 登录方法函数
	var loginFun = function(){
		if($("#login_form").valid()){
	         //加载进度条
        	layer.load(2, {
	               shade: [0.2,'#999999'] //0.1透明度的白色背景
           	});
	      	//$("#submitBtn").attr("disabled",true);
			$.ajax({
	            type: "post",
	            url: 'loginCheck',
	            data: $("#login_form").serialize(),
	            dataType: "json",
	            async:true,
	            success:function(data) {
	                if(data.success){
	                    $("#errors").html("");
	                    //加载进度条
	                    var referer ='${referer}';
	                    if(referer.indexOf("signUp")>-1 || referer.indexOf("forget")>-1){
	                    	window.location = '${base}';
	                    }else{
	                    	window.location='${referer}';
	                    }
	                }else{
	                    $("#errors").html(data.message);
	                    alert(data.message);
	                    //$("#submitBtn").removeAttr("disabled");
	                    //关闭进度条
	                    layer.closeAll('loading');
	                }
	            }
	        }); 
		}
	}
	
    $(document).ready(function(){
        $("#login_form ").validate({
            errorPlacement: function(error, element){
               var error_td = element.parent('dd');
                error_td.find('label').hide();
                error_td.append(error);
            },
            rules: {
                username: "required",
                password: "required",
				captcha:{
					required:true,
					remote:{
						url: "${base}/webcodeCheck",  //后台处理程序
			            type: "post",               //数据发送方式
			            dataType: "json",           //接受数据格式 
			            async:false,
			            data: {                     //要传递的数据
			                  "captcha": function(){return jQuery("#code").val();}
					    }
					}
			   }
            },
            messages: {
				username:{required:"用户名不能为空"},
				password:{required:"密码不能为空"},
				captcha:{
			  		required:"验证码不能为空",
			  		remote:"验证码不正确"
				}
		  }
        });
        
        $('#submitBtn').click(function(){
        	loginFun();
        });   
        
        //回车登陆事件
        document.onkeydown = function(e){ 
		    var ev = document.all ? window.event : e;
		    if(ev.keyCode==13) {
		    	loginFun();
            }
        }
    });
</script>
<div class="clear"></div>
<!-----footer------>
<@p.footer/>
