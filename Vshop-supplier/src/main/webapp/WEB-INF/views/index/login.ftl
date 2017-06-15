<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <#assign siteSettingTag = newTag("siteSettingTag") />
	<#assign siteSet = siteSettingTag("") />
    <title>${siteSet.siteTitle}</title>
    <meta name="keywords" content="商品汇平台"/>
    <meta name="description" content="商品汇平台"/>
    <meta name="author" content="商品汇平台">
    <meta name="copyright" content="商品汇平台">
    <link rel="shortcut icon" href="${imgOssServer}${siteSet.siteLogo1}"/>

<!--     <link href="res/css/base.css" rel="stylesheet" type="text/css"> -->
<!--     <link href="res/css/home_header.css" rel="stylesheet" type="text/css"> -->
<!--     <link href="res/css/home_login.css" rel="stylesheet" type="text/css"> -->
<link href="res/css/login/style.css" rel="stylesheet" type="text/css"> 
<link href="res/css/login/reset.css" rel="stylesheet" type="text/css"> 
    <script src="res/js/jquery.js" ></script>
    <script src="res/js/jquery-ui/jquery.ui.js" ></script>
    <script src="res/js/jquery.validation.min.js" ></script>
    <script type="text/javascript" src="${base}/res/js/layer/layer.js"></script>
    <script type="text/javascript" src="res/js/jquery.tips.js"></script>
    <style type="text/css">
	<!--
	
	.button{
		clear: both;
		display: block;
		width: 435px;
		height: 45px;
		background:#db481c ;
		color: #ffffff;
		cursor: pointer;
		margin-left: 64px;
		position: absolute;
		top: 190px;
	}
	
	
	-->
	</style>

</head>
<body>
<script type="text/javascript">
    $(function () {
        $("#username").focus();
    });
    function changeCaptcha() {
        var captchaImg = '${base}/generateImage?t=' + Math.random();
        $("#captcha_img").attr("src", captchaImg);
    }
</script>


<div class="wrap">
	<div class="logo">
		<img src="${imgServer}${siteSet.siteLogo2}" style="max-height:60px;max-width:300px;"class="pngFix"/>
		<div class="biao">
			
		</div>
	</div>
	<div class="font">
		<img src="res/images/logn/pic.jpg" />
		<div class="input">
			<form id="login_form" action="loginCheck"  method="post" onsubmit="return check();" >
			<p>
			<span>用户名：</span>
			<input type="text" placeholder="用于登录、找回密码" name="username" id="username" />
			<label ></label>
			</p>
			<p><span style="margin-left: 16px;padding-top: 20px;">
			密码：</span><input  style="margin-top: 20px;" type="password" placeholder="6-20位数字、字母、符号" name="password" id="password" />
			<label></label>
			</p>
			<p>
				<span style="margin-top: 30px;">验证码：</span>
				<input style="width: 105px;margin-top: 20px;" type="text" placeholder="请输入验证码" name="captcha" id ="captcha"/>
<!-- 				<img style="margin-top: 20px;margin-left: 30px;" src="res/images/logn/yz.jpg"/> -->
				 <img style="margin-top: 20px;margin-left: 30px;" src="generateImage" title="看不清？点击换一张" onclick="changeCaptcha()" border="0" id="captcha_img" class="fl ml5"/>
				<div style="font-size: 12px; color: #db481c; margin-left: 30px; margin-top: 33px;  float: left;"> 
				<a href="javascript:void(0)"  onclick="changeCaptcha()">看不清，换一张</a>
				
				<label style="color: red;" id="errors">
                </label>
				</div>
			</p>
<!-- 			<p> -->
<!-- 				<div style="font-size: 12px; color: #db481c; margin-left: 30px; margin-top: 33px;  float: left;">  -->
<!-- 					<label style="color: red;" id="errors"> -->
<!-- 		            </label> -->
<!-- 	             </div> -->
<!--              </p> -->
			<a href="JavaScript:void(0);" class="button"  id="submitBtn"><span style="line-height:45px;margin-left: 178px;margin-bottom: 30px;font-size: 15px;">立即登录</span></a>
<!-- 			<button>立即登录</button> -->
			<div class="wangji" >
<!-- 				<a  href="${base}/forget/index"><b>忘记密码?</b></a><span>还不是本站会员?<a title="注册" href="${base}/signUp">立即注册</a></span> -->
				<b id="fogetPwd" style="cursor: pointer;" onclick="fogetPwd()" >忘记密码?</b><span>还不是本站会员?<i id="register" style="cursor: pointer;" onclick="register()" >立即注册</i></span>
			</div>
			</form>
		</div>
	</div>
</div>

<script>
    $(document).ready(function(){
        
         $('#submitBtn').click(function(){
        	 if(check()){
		         //加载进度条
	            layer.load(2, {
		               shade: [0.2,'#999999'] //0.1透明度的白色背景
	            });
		       // $("#submitBtn").attr("disabled",true);
				$.ajax({
		            type: "post",
		            url: 'loginCheck',
		            data: $("#login_form").serialize(),
		            dataType: "json",
		            async:false,
		            success:function(data) {
		                if(data.success){
		                    //alert(data.message);
		                    //layer.msg(data.message, {icon: 1});
		                    setTimeout("window.location='${base}'" ,200);
		                }else{
		                    $("#errors").html(data.message);
		                    //$("#submitBtn").removeAttr("disabled");
		                    layer.closeAll('loading');
		                    //alert(data.message);
		                }
		            }
		        }); 
		      }  
        	 return false;
        });   
        /* $("#submitBtn").click(function(){
	        if($("#login_form").valid()){
	            $("#login_form").submit();
	        }
        }); */
           //回车登陆事件
           document.onkeydown = function(e){
		    var ev = document.all ? window.event : e;
		    if(ev.keyCode==13) {
		    	if(check()){
                //加载进度条
	            layer.load(2, {
		               shade: [0.2,'#999999'] //0.1透明度的白色背景
	            });
		       // $("#submitBtn").attr("disabled",true);
				$.ajax({
		            type: "post",
		            url: 'loginCheck',
		            data: $("#login_form").serialize(),
		            dataType: "json",
		            async:false,
		            success:function(data) {
		                if(data.success){
		                    //alert(data.message);
		                    //layer.msg(data.message, {icon: 1});
		                    setTimeout("window.location='${base}'" , 200);
		                }else{
		                    $("#errors").html(data.message);
		                   // $("#submitBtn").removeAttr("disabled");
		                    layer.closeAll('loading');
		                    //alert(data.message);
		                }
		            }
		        }); 
		      }
             }
        }
    });
    
    function fogetPwd(){
    	window.location.href = '${base}/forget/index';
    }
    function register(){
    	window.location.href = '${base}/signUp';
    }
    
    function check() {

		if ($("#username").val() == "") {

			$("#username").tips({
				side : 2,
				msg : '用户名不得为空',
				bg : '#AE81FF',
				time : 3
			});

			$("#username").focus();
			return false;
		} else {
			$("#username").val(jQuery.trim($('#username').val()));
		}

		if ($("#password").val() == "") {

			$("#password").tips({
				side : 2,
				msg : '密码不得为空',
				bg : '#AE81FF',
				time : 3
			});

			$("#password").focus();
			return false;
		}
		if ($("#captcha").val() == "") {

			$("#captcha").tips({
				side : 1,
				msg : '验证码不得为空',
				bg : '#AE81FF',
				time : 3
			});

			$("#captcha").focus();
			return false;
		}


		return true;
	}
    
</script>
</body>
</html>
