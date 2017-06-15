
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

    <link href="${base}/res/css/base.css" rel="stylesheet" type="text/css">
    <link href="${base}/res/css/home_header.css" rel="stylesheet" type="text/css">
    <link href="${base}/res/css/home_login.css" rel="stylesheet" type="text/css">
    <script src="${base}/res/js/jquery.js" ></script>
    <script src="${base}/res/js/jquery.ui.js" ></script>
    <script src="${base}/res/js/jquery.validation.min.js" ></script>
    <script type="text/javascript" src="${base}/res/js/layer/layer.js"></script>
</head>
<body>
<script type="text/javascript">
    $(function(){
        $(".quick-menu dl").hover(function() {
            $(this).addClass("hover");
        },
        function() {
            $(this).removeClass("hover");
        });
    });
</script>
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
        $("#user_name").focus();
    });
    function changeCaptcha() {
        var captchaImg = '${base}/generateImage?t=' + Math.random();
        $("#captcha_img").attr("src", captchaImg);
    }
</script>
<!--主页内容，调用index-->
<style type="text/css">
    .wrapper {
         width: 1000px;}#footer-top ul li{margin-right:60px;}
   			 body,.header-wrap{background-color:#f2f2f2;}
     .nc-login-content dd .text{ font-family: Tahoma; width: 220px; height: 26px; 
    							 line-height: 20px; background-color:#FFF; padding: 3px!important; 
    							 border: solid 1px; border-color: #BBB;}
</style>
<div class="nc-login-layout">
    <div class="nc-login-main">
        <div class="nc-login">
            <div class="nc-login-title">
                <h3>用户注册</h3>
            </div>
            <div class="nc-login-content">
                <form id="register_form" method="post" action="">
                    <dl>
                        <dt>用户名<font color=#FF0000>*</font></dt>
                        <dd style="min-height:54px;">
                            <input type="text" id="user_name" name="memberName" class="text tip" title="3-20位字符，可由中文、英文、数字及“_”、“-”组成"/>
                            <label></label>
                        </dd>
                    </dl>
                    <dl>
                        <dt>设置密码<font color=#FF0000>*</font></dt>
                        <dd style="min-height:54px;">
                            <input type="password" id="password" name="memberPasswd" class="text tip" title="6-16位字符，可由英文、数字及标点符号组成" />
                            <label></label>
                        </dd>
                    </dl>
                    <dl>
                        <dt>确认密码<font color=#FF0000>*</font></dt>
                        <dd style="min-height:54px;">
                            <input type="password" id="password_confirm" name="password_confirm" class="text tip" title="请再次输入您的密码"/>
                            <label></label>
                        </dd>
                    </dl>
                    <dl>
                        <dt>邮箱<font color=#FF0000>*</font></dt>
                        <dd style="min-height:54px;">
                            <input type="text" id="email" name="memberEmail" class="text tip" title="请输入常用的邮箱，将用来找回密码、接受订单通知等" />
                            <label></label>
                        </dd>
                    </dl>
                    <!--新加手机号开始-->
                    <dl>
                        <dt>手机号<font color=#FF0000>*</font></dt>
                        <dd style="min-height:54px;">
                            <input type="text" id="mobile" name="memberMobile" class="text tip" title="请输入您的手机号">
                            <label></label>
                        </dd>
                    </dl>
                    <!--新加手机号结束-->
                    <dl>
                        <dt>验证码</dt>
                        <dd style="min-height:54px;">
                            <input class="text w50 fl" type="text" name="captcha" id="captcha" maxlength="4" size="10" title="请输入验证码，不区分大小写"/>
                            <img src="generateImage" title="看不清？点击换一张" onclick="changeCaptcha()" border="0" id="captcha_img" class="fl ml5"/>
                            <a href="javascript:void(0)" class="ml5" onclick="changeCaptcha()">看不清，换一张</a>
                            <label></label>
                        </dd>
                    </dl>
                    <dl>
                        <dt>&nbsp;</dt>
                        <dd>
                            <input type="button" id="submitBtn" name="Submit" value="立即注册" class="submit fl" title="立即注册" />
                            <input name="agree" type="checkbox" class="fl mt10 ml10" id="clause" value="1" checked="checked" />
                            <span for="clause" class="fl ml5">阅读并同意<a href="${base}/help/content?acId=9&articleId=45" target="_blank" class="agreement" title="阅读并同意">服务协议</a></span>
                            <label></label>
                        </dd>
                    </dl>
                   <#-- <input type="hidden" value="" name="ref_url">
                        <input name="nchash" type="hidden" value="f8205b21" />-->
                </form>
                <div class="clear"></div>
            </div>
        </div>
        <div class="nc-login-left">
            <h3>注册之后您可以</h3>
            <ol>
                <li class="ico05"><i></i>购买商品支付订单</li>
                <li class="ico01"><i></i>申请开店销售商品</li>
                <li class="ico03"><i></i>空间好友推送分享</li>
                <li class="ico02"><i></i>收藏商品关注商家</li>
                <li class="ico06"><i></i>商品咨询服务评价</li>
                <li class="ico04"><i></i>安全交易诚信无忧</li>
                <div class="clear"></div>
            </ol>
            <h3 class="mt20">如果您是本站用户</h3>
            <div class="nc-login-now mt10">
            	<span class="ml20">我已经注册过帐号，立即<a href="${base}/login" title="" class="register btn">登录</a></span>
            	<span>或是<a class="forget" href="${base}/forget/index">找回密码？</a></span>
            </div>
        </div>
        <div class="clear"></div>
    </div>
</div>
<#--<script type="text/javascript" src="${base}/res/js/jquery.poshytip.min.js" charset="utf-8"></script>-->

   
<script>

    //注册表单提示
   /*  $('.tip').poshytip({
        className: 'tip-yellowsimple',
        showOn: 'focus',
        alignTo: 'target',
        alignX: 'center',
        alignY: 'top',
        offsetX: 0,
        offsetY: 5,
        allowTipHover: false
    }); */
    //注册表单验证
    $(function(){
        jQuery.validator.addMethod("lettersonly", function(value, element) {
            return this.optional(element) || /^[^:%,'\*\"\s\<\>\&]+$/i.test(value);
        }, "Letters only please");
        jQuery.validator.addMethod("lettersmin", function(value, element) {
            return this.optional(element) || ($.trim(value.replace(/[^\u0000-\u00ff]/g,"aa")).length>=3);
        }, "Letters min please");
        jQuery.validator.addMethod("lettersmax", function(value, element) {
            return this.optional(element) || ($.trim(value.replace(/[^\u0000-\u00ff]/g,"aa")).length<=15);
        }, "Letters max please");
         jQuery.validator.addMethod("chmobile", function(value, element) {
	    	    var pattern = /^1\d{10}$/;
	        	return this.optional(element) || (pattern.test(value));
	        }, "mobile");
        
        $("#register_form").validate({
            errorPlacement: function(error, element){
                var error_td = element.parent('dd');
                error_td.find('label').hide();
                error_td.append(error);
            },
           /*  submitHandler:function(form){
                ajaxpost('register_forms', '', '', 'onerror')
            }, */
            rules : {
                memberName : {
                    required : true,
                    lettersmin : true,
                    lettersmax : true,
                    lettersonly : true,
                    remote   : {
                        url :'${base}/checkMemeber',
                        type:'get',
                        data:{
                            name : function(){
                                return $('#user_name').val();
                            }
                        }
                    }
                },
                memberPasswd : {
                    required : true,
                    minlength: 6,
                    maxlength: 20
                },
                password_confirm : {
                    required : true,
                    equalTo  : '#password'
                },
                memberEmail : {
                    required : true,
                    email    : true,
                    remote   : {
                        url : '${base}/checkEmail',
                        type: 'get',
                        data:{
                            email : function(){
                                return $('#email').val();
                            }
                        }
                    } 
                },
                memberMobile : {
                    required : true,
                    chmobile:true,
                    remote   : {
                        url : '${base}/checkMobile',
                        type: 'get',
                        data:{
                            mobile : function(){
                                return $('#mobile').val();
                            }
                        }
                    } 
                },
                captcha : {
                    required : true,
                    remote   : {
                        url : '${base}/checkCaptcha',
                        type: 'get',
                        data:{
                            captcha : function(){
                                return $('#captcha').val();
                            }
                        }
                    }
                },
                agree : {
                    required : true
                }
            },
            messages : {
                memberName : {
                    required : '用户名不能为空',
                    lettersmin : '用户名必须在3-15个字符之间',
                    lettersmax : '用户名必须在3-15个字符之间',
                    lettersonly: '用户名不能包含敏感字符',
                    remote	 : '该用户名已经存在'
                },
                memberPasswd  : {
                    required : '密码不能为空',
                    minlength: '密码长度应在6-20个字符之间',
                    maxlength: '密码长度应在6-20个字符之间'
                },
                password_confirm : {
                    required : '请再次输入您的密码',
                    equalTo  : '两次输入的密码不一致'
                },
                memberEmail : {
                    required : '电子邮箱不能为空',
                    email    : '这不是一个有效的电子邮箱',
                    remote	 : '该电子邮箱已经存在'
                },
                memberMobile : {
                    required : '手机号不能为空',
                    chmobile:'请填写正确格式的手机号',
                    remote : '该手机号已存在'
                },
                captcha : {
                    required : '请输入验证码',
                    remote	 : '验证码不正确'
                },
                agree : {
                    required : '请阅读并同意该协议'
                }
            }
        });
        
        //表单提交
        $("#submitBtn").click(function(){
	        if($("#register_form").valid()){
	                $("#submitBtn").attr("disabled",true);
			        //加载进度条
		            layer.load(2, {
			               shade: [0.2,'#999999'] //0.1透明度的白色背景
		            });
			         $.ajax({
					         type: "post",
					         url: '${base}/sign',
					         data: $("#register_form").serialize(),
					         dataType: "json",
							 async:true,
							 success:function(data) {
							 	if(data.success){
									 //layer.msg(data.message, 1, 8);
			                         setTimeout("window.location='${base}'" ,500);
								  }else{
			                         $("#submitBtn").removeAttr("disabled");
			                         layer.msg(data.msg,{icon: 2});
								  }
							  }
					 	 });
					}
             });
    });
</script><div class="clear"></div>
<!-----footer------>
<@p.footer/>
