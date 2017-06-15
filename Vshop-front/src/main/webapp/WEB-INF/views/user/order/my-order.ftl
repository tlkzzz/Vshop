<@p.userHeader title="会员首页"/>
<script type="text/javascript" src="${base}/res/js/smilies/smilies_data.js" charset="utf-8"></script>  
<script type="text/javascript" src="${base}/res/js/smilies/smilies.js" charset="utf-8"></script> 
<script type="text/javascript" src="${base}/res/js/jquery.caretInsert.js" charset="utf-8"></script> 
<script type="text/javascript" src="${base}/res/js/jcarousel/jquery.jcarousel.min.js" charset="utf-8"></script> 
<script type="text/javascript" src="${base}/res/js/jquery.charCount.js" charset="utf-8"></script> 
<script type="text/javascript" src="${base}/res/js/nc-sideMenu.js" charset="utf-8"></script>
<#-- <script type="text/javascript" src="${base}/res/js/jquery.ajaxdatalazy.js" charset="utf-8"></script>  -->
<div id="container" class="wrapper">
	<div class="layout">
    	<@u.left  title="我的"/>
    	<div class="right-content">
      		<div class="path">
        		<div><a href="#?act=member_snsindex">我的商家</a><span>&raquo;</span></div>
      		</div>
      		<div class="main">
        		<link type="text/css" href="${base}/res/js/jcarousel/skins/tango/skin.css" rel="stylesheet" >
				<style type="text/css">
				.path { display: none;}
				.fd-media .goodsinfo dt { width: 300px;}
				</style>
				<!--内容-->
				<#assign memberInfoTag =newTag("memberInfoTag")>
				<#assign member =memberInfoTag("")>
				<div class="wrap">
  					<div class="layout-l">
   						<div class="member-intro">
      						<dl>
        						<dt class="nc-member-name"><a href="#?act=home&op=member" title="编辑用户信息">${member.memberName}</a>&nbsp;(${member.memberName})</dt>
        						<dd>会员信用0</dd>
                				<dd>积分数&nbsp;<strong>${member.memberPoints}</strong></dd>
                        		<dd class="predeposit">
                        			<a href="javascript:void(0)">
                        				预存款<span class="price ml5 mr5">
                        					<script type="text/javascript">
				              					var amount = number_format(${member.availablePredeposit},2);
				              					document.write("&yen;" + amount);
				              				</script>
                        				</span>元<i></i>
                        			</a>
	          						<div class="down-menu">
	            						<p><a href="${base}/predeposit/predepositIndex">预存款充值</a></p>
	            						<p><a href="${base}/predeposit/predepositIndex">预存款明细</a></p>
	          						</div>
      							</dd>
              				</dl>
      						<ul>
					        	<li class='no'><a href="#">待付款订单&nbsp;(<strong>${member.noPayOrder}</strong>)</a></li>
						        <li class='no'><a href="#">待确认收货&nbsp;(<strong>${member.noReceiveOrder}</strong>)</a></li>
						        <li class='no'><a href="#">待评价交易&nbsp;(<strong>${member.noEvaluationOrder}</strong>)</a></li>
					      	</ul>
    					</div>
					    <!-- 分享心情和宝贝 -->
					    <ul class="release-tab">
				      		<li class="sharemood"><em></em><a href="javascript:void(0)">分享心情</a><i></i></li>
					      	<li class="sharegoods" id="snssharegoods"><em></em><a href="javascript:void(0)">分享商品</a><i></i></li>
					      	<li class="sharestore" id="snssharestore"><em></em><a href="javascript:void(0)">分享商家</a></li>
					    </ul>
    					<div class="release-content"><span class="arrow"></span>
      						<form id="weiboform" method="post" action="#?act=member_snsindex&op=addtrace">
        						<textarea name="content" id="content_weibo" nc_type="contenttxt" class="textarea"resize="none"></textarea>
        						<span class="error"></span>
        						<div class="smile"><em></em><a href="javascript:void(0)" nc_type="smiliesbtn" data-param='{"txtid":"weibo"}'>表情</a></div>
        						<div id="weibocharcount" class="weibocharcount"></div>
        						<div id="weiboseccode" class="weiboseccode">
	          						<label for="captcha" class="ml5 fl"><strong>验证码：</strong></label>
	          						<input name="captcha" class="w40 fl text" type="text" id="captcha" size="4" maxlength="4"/>
	          						<a href="javascript:void(0)" class="ml5 fl"><img src="" title="点击更换验证码" name="codeimage" border="0" id="codeimage" onclick="this.src='#?act=seccode&op=makecode&nchash=d3e4e8ac&t=' + Math.random()" /></a>
	          						<input type="hidden" name="nchash" value="d3e4e8ac"/>
        						</div>
        						<div class="handle">
          							<div nc_type="formprivacydiv" class="privacy-module"><span class="privacybtn" style="width:55px;" nc_type="formprivacybtn"><i></i>所有人</span>
            							<div class="privacytab" nc_type="formprivacytab" style="display:none;">
							            	<ul class="menu-bd">
							                	<li nc_type="formprivacyoption" data-param='{"v":"0"}'><span class="selected">所有人可见</span></li>
							                	<li nc_type="formprivacyoption" data-param='{"v":"1"}'><span>好友可见</span></li>
							                	<li nc_type="formprivacyoption" data-param='{"v":"2"}'><span>仅自己可见</span></li>
							              	</ul>
            							</div>
          							</div>
          							<input type="hidden" name="privacy" id="privacy" value="0"/>
        						</div>
        						<input type="text" class="text" style="display:none;" />
        						<!-- 防止点击Enter键提交 -->
        						<input name="分享" type="button" class="button" value="分享" id="weibobtn" />
      						</form>
      						<!-- 表情弹出层 -->
      						<div id="smilies_div" class="smilies-module"></div>
    					</div>
    
    					<!-- 动态列表 -->
    					<div class="tabmenu" style="z-index:0;">
      						<ul class="tab pngFix">
        						<li class="active" nctype="friendtrace"><a id="tabGoodsIntro" href="javascript:void(0)" ><span>好友动态</span></a></li>
       							<li class="normal" nctype="storetrace"><a href="javascript:void(0)" ><span>商家动态</span></a></li>
      						</ul>
    					</div>
    					<div id="friendtrace" class="mt20"></div>
    					<div id="storetrace" class="mt20" style="display:none;"></div>
  					</div>
  					<div class="layout-r">
    					<div class="visitors pngFix">
      						<h4><span class="active" nc_type="visitmodule" data-param='{"name":"visit_me"}'>谁来看过我</span><span class="line">|</span><span class="normal" nc_type="visitmodule" data-param='{"name":"visit_other"}'>我访问过的人</span></h4>
      						<ul id="visit_me" nc_type="visitlist">
               				 	最近没有人访问过您的空间，多关注些<a href="#?act=member_snsfriend&op=find">好友</a>互动吧~              
               				</ul>
      						<ul id="visit_other" nc_type="visitlist" style="display: none;">
                				最近您没有浏览过好友的空间，赶快去看看<a href="#?act=member_snsfriend&op=follow">好友</a>最近搜罗了哪些宝贝              
                			</ul>
    					</div>
    					<!-- <script type="text/javascript" src="http://192.168.1.230/#?act=adv&op=advshow&ap_id=372"></script> --> 
  					</div>
  					<div class="clear"></div>
				</div>
			</div>
    	</div>
	</div>
</div>
<script type="text/javascript">
	var max_recordnum = '20';
	document.onclick = function(){ $("#smilies_div").html(''); $("#smilies_div").hide();};
	$(function(){
		// 标签切换
		$(".tab").children("li").click(function(){
			$(".tab").children("li").removeClass().addClass("normal");
			$(this).removeClass().addClass("active");

			var trace_sign = $(this).attr("nctype");
			var friendtrace_url = '#?act=member_snsindex&op=tracelist&curpage=1';
			var storetrace_url	= '';
			var url_friendtrace	= '#?act=member_snsindex&op=tracelist&curpage=1'
			var url_storetrace	= '#?act=store_snshome&op=stracelist';
			$('#friendtrace,#storetrace').html('').hide();
			$('#'+trace_sign).show('fast',function(){
				$('#'+trace_sign).lazyinit();
				$('#'+trace_sign).lazyshow({url:eval('url_'+trace_sign),'iIntervalId':true});
			});  
		}); 
		
		//谁看过我,我看过谁切换
		$("[nc_type='visitmodule']").bind('click',function(){
			var data_str = $(this).attr('data-param');
		    eval( "data_str = "+data_str);
		    $("[nc_type='visitmodule']").removeClass('active');
		    $("[nc_type='visitmodule']").addClass('normal');
		    $(this).removeClass('normal');
		    $(this).addClass('active');
		    $("[nc_type='visitlist']").hide();
		    $("#"+data_str.name).show();
		});
		//提交分享心情表单
		$("#weibobtn").bind('click',function(){			
			if($("#weiboform").valid()){
				var cookienum = $.cookie(COOKIE_PRE+'weibonum');
				cookienum = parseInt(cookienum);
				if(cookienum >= max_recordnum && $("#weiboseccode").css('display') == 'none'){
					//显示验证码
					$("#weiboseccode").show();
					$("#weiboseccode").find("#codeimage").attr('src','#?act=seccode&op=makecode&nchash=d3e4e8ac&t=' + Math.random());
				}else if(cookienum >= max_recordnum && $("#captcha").val() == ''){
					showDialog('请填写验证码');
				}else{
					ajaxpost('weiboform', '', '', 'onerror');
					//隐藏验证码
					$("#weiboseccode").hide();
					$("#weiboseccode").find("#codeimage").attr('src','');
					$("#captcha").val('');
				}
			}
			return false;
		});
		$('#weiboform').validate({
			errorPlacement: function(error, element){
				element.next('.error').append(error);
		    },      
		    rules : {
		    	content : {
		            required : true,
		            maxlength : 140
		        }
		    },
		    messages : {
		    	content : {
		            required : '请填写心情',
		            maxlength: '不能超过140字'
		        }
		    }
		});
		//显示分享商品页面
		$('#snssharegoods').click(function(){
		    ajax_form("sharegoods", '分享已买到和收藏的宝贝', 'http://192.168.1.230/#?act=member_snsindex&op=sharegoods&irefresh=1', 500);
		    return false;
		});
		//显示分享商家页面
		$('#snssharestore').click(function(){
		    ajax_form("sharestore", '分享商家', 'http://192.168.1.230/#?act=member_snsindex&op=sharestore&irefresh=1', 500);
		    return false;
		});
        //加载好友动态分页
		$('#friendtrace').lazyinit();
		$('#friendtrace').lazyshow({url:"#?act=member_snsindex&op=tracelist&curpage=1",'iIntervalId':true});
		//心情字符个数动态计算
		/* $("#content_weibo").charCount({
			allowed: 140,
			warning: 10,
			counterContainerID:'weibocharcount',
			firstCounterText:'还可以输入',
			endCounterText:'字',
			errorCounterText:'已经超出'
		}); */
		

	});
</script>
<@p.userfooter/>