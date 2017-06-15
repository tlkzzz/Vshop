<@p.header title="商品汇平台管理中心"/>
<script>
	//加载完毕后,查询用户是否登陆,如果登陆则改变页头状态
	/*异步请求查询是否登陆*/
	var url = '${base}/index/getUsername';
	$.post(url, function(data){
		var result = data.result;
		$(".user-entry").empty();
		/*用户已经登陆*/
		if(result == 'true'){
			var memberName = data.memberName;
			$(".user-entry").append("您好<span>&nbsp;&nbsp;<a href='${base}/user/setting/index'>"+memberName+"</a></span>，欢迎来到"
			+"<a href='${base}'  title='首页' alt='首页'>商品汇平台</a><span>[<a href='${base}/logout'>退出</a>]</span>");
		}else{
			$(".user-entry").append("您好，欢迎来到<a href='${base}' title='首页' alt='首页'>商品汇平台</a>"
			+"<span>[<a href='${base}/login'>登录</a>]</span><span>[<a href='${base}/signUp'>注册</a>]</span>");
		}
		$(".user-entry").append("<span class='seller-login'>"
				+"<a href='${sellerServer}' title='商家中心' target='_blank'><i class='icon-signin'></i>商家管理中心</a></span>");
	});
</script>
<div class="clear"></div>
<link href="${base }/css/points/public.css" type="text/css" rel="stylesheet" />
<link href="${base }/css/points/integral.css" rel="stylesheet" type="text/css" />

<script>
jQuery(document).ready(function() {
	jQuery(".inte_rank").slide({titCell:".li_list",targetCell:".li_hover",defaultIndex:1,effect:"slideDown",triggerTime:0,returnDefault:true}); 
	jQuery(".inte_choose").find("li").removeClass("this");
	jQuery(".inte_choose").find("li").eq(0).addClass("this");
});
</script>

<!--[if lt IE 9]>
    <script src="http://b2b2c.iskyshop.com/resources/js/css3-mediaqueries.js"></script>
<![endif]-->

<style type="text/css">
.left_menu_con_center_left dd a { font-weight:normal; font-size:12px;}
</style>

<div class="main">
	<div class="integral_left">
    	<h1>商家积分信息</h1>
                
        <div class="inte_detail">
        	<ul>
                <li><a href="#/doc.htm?mark=integral" target="_blank">积分兑换说明</a></li>
            </ul>
        </div>
        
        <div class="inte_arrow"></div>
        <h1>积分选择区</h1>
        <div class="inte_choose">
        	<ul>
            	<li class="this"><a href="#">全部</a></li>
            	<li><a href="#?begin=0&end=1999&rank=1">1999分以下</a></li>
                <li><a href="#?begin=2000&end=3999&rank=2">2000分~3999分</a></li>
                <li><a href="#?begin=4000&end=5999&rank=3">4000分~5999分</a></li>
                <li><a href="#?begin=6000&end=9999&rank=4">6000分~9999分</a></li>
                <li><a href="#?begin=10000&end=0&rank=5">10000以上</a></li>
            </ul>
        </div>
        
        <div class="inte_arrow"></div>
        <h1>兑换排行榜</h1>
        <div class="inte_rank">
                            <h3 class="li_list  on "><b>1</b><a href="#/integral/view_5.htm" target="_blank">爵奇 加厚不锈钢煎蛋器煎蛋模具 创意煎蛋圈煎鸡蛋模型套装心形 </a></h3>
                <div class="li_hover"  style="display:block;" >
                    <div class="li_hover_img"><a href="#/integral/view_5.htm" target="_blank"><img src="/f0629fc5-a2f1-4e0d-b3d6-f558b890e0cf.jpg_small.jpg" /></a></div>
                    <div class="inte_price">
                        <span>市场价格:¥39.00</span>
                        <span>兑换积分:<i class="red">1780</i>分</span>
                        <span>兑换数量:<i class="red">8</i>个</span>
                    </div>
                </div>
                            <h3 class="li_list "><b>2</b><a href="#/integral/view_1.htm" target="_blank">100%埃及长绒纯棉 无印洁面吸水柔软毛巾 加厚良品方巾两条装</a></h3>
                <div class="li_hover" >
                    <div class="li_hover_img"><a href="#/integral/view_1.htm" target="_blank"><img src="/3ca794e3-919b-4605-b6ef-d5ad4de47ccd.jpg_small.jpg" /></a></div>
                    <div class="inte_price">
                        <span>市场价格:¥100.00</span>
                        <span>兑换积分:<i class="red">2000</i>分</span>
                        <span>兑换数量:<i class="red">1</i>个</span>
                    </div>
                </div>
                            <h3 class="li_list "><b>3</b><a href="#/integral/view_2.htm" target="_blank">【全积分兑换】菲尔美正品床上用品决明子定型枕芯简约线条枕头芯 </a></h3>
                <div class="li_hover" >
                    <div class="li_hover_img"><a href="#/integral/view_2.htm" target="_blank"><img src="/38ba9f82-bee7-4d39-9994-f64a6052afed.jpg_small.jpg" /></a></div>
                    <div class="inte_price">
                        <span>市场价格:¥129.00</span>
                        <span>兑换积分:<i class="red">3900</i>分</span>
                        <span>兑换数量:<i class="red">0</i>个</span>
                    </div>
                </div>
                            <h3 class="li_list "><b>4</b><a href="#/integral/view_3.htm" target="_blank">BYZA正品抗疲劳眼睛防辐射眼镜防蓝光电脑游戏平光护目镜男女潮款 </a></h3>
                <div class="li_hover" >
                    <div class="li_hover_img"><a href="#/integral/view_3.htm" target="_blank"><img src="/2e8fbab0-3da3-4ff3-8f20-894d464d66d3.jpg_small.jpg" /></a></div>
                    <div class="inte_price">
                        <span>市场价格:¥118.00</span>
                        <span>兑换积分:<i class="red">2500</i>分</span>
                        <span>兑换数量:<i class="red">0</i>个</span>
                    </div>
                </div>
                            <h3 class="li_list "><b>5</b><a href="#/integral/view_4.htm" target="_blank">厨房用品创意青花陶瓷调味罐套装调味品调料架套装送架子勺子包邮 </a></h3>
                <div class="li_hover" >
                    <div class="li_hover_img"><a href="#/integral/view_4.htm" target="_blank"><img src="/ac2e4d1d-e7cb-49a9-abc2-c93e5229ae70.jpg_small.jpg" /></a></div>
                    <div class="inte_price">
                        <span>市场价格:¥139.00</span>
                        <span>兑换积分:<i class="red">3500</i>分</span>
                        <span>兑换数量:<i class="red">0</i>个</span>
                    </div>
                </div>
                            <h3 class="li_list "><b>6</b><a href="#/integral/view_6.htm" target="_blank">时尚公主蕾丝台灯 现代田园卧室床头温馨结婚庆装饰台灯603-1T </a></h3>
                <div class="li_hover" >
                    <div class="li_hover_img"><a href="#/integral/view_6.htm" target="_blank"><img src="/a844d715-d45c-4365-bf90-108f308f90f1.jpg_small.jpg" /></a></div>
                    <div class="inte_price">
                        <span>市场价格:¥258.00</span>
                        <span>兑换积分:<i class="red">11800</i>分</span>
                        <span>兑换数量:<i class="red">0</i>个</span>
                    </div>
                </div>
                            <h3 class="li_list "><b>7</b><a href="#/integral/view_7.htm" target="_blank">冰美人水润嫩白护手霜60g 美白滋润保湿防裂缓解干燥 栀子花香型 </a></h3>
                <div class="li_hover" >
                    <div class="li_hover_img"><a href="#/integral/view_7.htm" target="_blank"><img src="/958e90a8-8872-413f-b551-8febd7b69737.jpg_small.jpg" /></a></div>
                    <div class="inte_price">
                        <span>市场价格:¥12.00</span>
                        <span>兑换积分:<i class="red">600</i>分</span>
                        <span>兑换数量:<i class="red">0</i>个</span>
                    </div>
                </div>
                            <h3 class="li_list "><b>8</b><a href="#/integral/view_8.htm" target="_blank">芬尚女士香水 持久淡香清新 浪漫樱花30ml 送小样 正品专柜淡香水 </a></h3>
                <div class="li_hover" >
                    <div class="li_hover_img"><a href="#/integral/view_8.htm" target="_blank"><img src="/4fb92367-a503-4c5f-897f-9f24be65193e.jpg_small.jpg" /></a></div>
                    <div class="inte_price">
                        <span>市场价格:¥118.00</span>
                        <span>兑换积分:<i class="red">2900</i>分</span>
                        <span>兑换数量:<i class="red">0</i>个</span>
                    </div>
                </div>
                            <h3 class="li_list "><b>9</b><a href="#/integral/view_9.htm" target="_blank">创意可爱节能led小夜灯 usb电池充电宝宝卧室床头灯 婴儿喂奶灯具 </a></h3>
                <div class="li_hover" >
                    <div class="li_hover_img"><a href="#/integral/view_9.htm" target="_blank"><img src="/1475d31b-1725-4804-ac62-709c73b8b8d3.jpg_small.jpg" /></a></div>
                    <div class="inte_price">
                        <span>市场价格:¥128.00</span>
                        <span>兑换积分:<i class="red">2800</i>分</span>
                        <span>兑换数量:<i class="red">0</i>个</span>
                    </div>
                </div>
                            <h3 class="li_list "><b>10</b><a href="#/integral/view_10.htm" target="_blank">美卡芙优 电动洁面仪洗脸刷 毛孔清洁器 去黑头美容仪 卸妆洗脸机 </a></h3>
                <div class="li_hover" >
                    <div class="li_hover_img"><a href="#/integral/view_10.htm" target="_blank"><img src="/a202b835-ff40-44c8-9400-6865cf22cac5.jpg_small.jpg" /></a></div>
                    <div class="inte_price">
                        <span>市场价格:¥298.00</span>
                        <span>兑换积分:<i class="red">3900</i>分</span>
                        <span>兑换数量:<i class="red">0</i>个</span>
                    </div>
                </div>
                            <h3 class="li_list "><b>11</b><a href="#/integral/view_11.htm" target="_blank">李医生 面膜芦荟保湿水嫩面膜10片装 晒后修护补水保湿面膜贴正品 </a></h3>
                <div class="li_hover" >
                    <div class="li_hover_img"><a href="#/integral/view_11.htm" target="_blank"><img src="/86fb9e88-9dc6-4bb4-9971-f8f5752c8f88.jpg_small.jpg" /></a></div>
                    <div class="inte_price">
                        <span>市场价格:¥99.00</span>
                        <span>兑换积分:<i class="red">2300</i>分</span>
                        <span>兑换数量:<i class="red">0</i>个</span>
                    </div>
                </div>
                            <h3 class="li_list "><b>12</b><a href="#/integral/view_12.htm" target="_blank">美亚MPE正品 魔力护腰枕 优质天然乳胶 透气 健康环保 P02 </a></h3>
                <div class="li_hover" >
                    <div class="li_hover_img"><a href="#/integral/view_12.htm" target="_blank"><img src="/1ecf7ae7-ce65-4960-afec-68708eb02642.jpg_small.jpg" /></a></div>
                    <div class="inte_price">
                        <span>市场价格:¥498.00</span>
                        <span>兑换积分:<i class="red">9900</i>分</span>
                        <span>兑换数量:<i class="red">0</i>个</span>
                    </div>
                </div>
                            <h3 class="li_list "><b>13</b><a href="#/integral/view_13.htm" target="_blank">雅美奇毛毯法兰绒毯子夏天珊瑚绒毛毯盖毯午睡毯法莱绒毯 </a></h3>
                <div class="li_hover" >
                    <div class="li_hover_img"><a href="#/integral/view_13.htm" target="_blank"><img src="/ce05eda5-f737-4b63-bb25-c02c04e922cc.jpg_small.jpg" /></a></div>
                    <div class="inte_price">
                        <span>市场价格:¥488.00</span>
                        <span>兑换积分:<i class="red">5980</i>分</span>
                        <span>兑换数量:<i class="red">0</i>个</span>
                    </div>
                </div>
                    </div>
    </div>
    
    <div class="integral_right">
        <div class="inte_banner">
        	<div class="inte_slide">
            	<script type="text/javascript">
            	jQuery(function(){jQuery('.slideBox').slide({mainCell:'.bd ul',effect:'fold',autoPlay:true});});
            	</script>
            	<div class="slideBox" id="iskyshop_advert">
				 	<#assign advPositionTag = newTag("advTag") />
            		<#assign pointAdvs = advPositionTag("{'apKey':'points'}") />
				    <#if pointAdvs?? && pointAdvs!''>
					    <#assign points = pointAdvs.advList />
			            	<div class="hd">
				            	<ul style="z-index:0;">
							       <#list points as point >
					            	<li <#if (point_index==0)>class="on"<#else>class=""</#if> >${point_index+1 }</li>
					            	</#list>
				            	</ul>
			            	</div>
			            	<div class="bd">
				            	<ul style="z-index: 0; position: relative; width: 785px; height: 310px;">
				            	 <#list points as point >
					            	<li style="position: absolute; width: 785px; left: 0px; top: 0px; display: list-item;"><a href="${point.advUrl}" target="_blank"><img src="${imgServer }${point.resUrl}" width="785" height="310" style="display: block;"></a></li>
				            	 </#list>
				            	</ul>
			            	</div>
	            	</#if>
            	</div>
            </div>
            <div class="inte_advert">
            <#assign pLeftAdvs = advPositionTag("{'apKey':'pointleft'}") />
	            <div style="float:left;overflow:hidden;">
	            <#if pLeftAdvs?? &&pLeftAdvs!''>
	            <#assign pointLeft = pLeftAdvs.advList />
	            	<#list pointLeft as pLeft >
	            	<a href="${pLeft.advUrl}" target="_blank"><img width="190" height="310" src="${imgServer }${pLeft.resUrl}" style="border: none; display: inline;"></a>
	            	<#if (pLeft_index>1)><#break></#if>
	            	</#list>
	            </#if>
	            </div>
            </div>
        </div>
        
        <h2><b>推荐兑换商品</b><a href="#/integral/list.htm">更多>></a></h2>
        <div class="inte_list_box">
            <div class="inte_list">
            	<ul>
                	<li class="recom_img"><a href="#/integral/view_1.htm" target="_blank"><img src="/3ca794e3-919b-4605-b6ef-d5ad4de47ccd.jpg" width="200" height="200" /></a><span>
                    <a href="#/integral/view_1.htm" target="_blank">100%埃及长绒纯棉 无印洁面吸水柔软毛巾 加厚良品方巾两条装</a></span>
                    <em></em></li>
                    <li class="inte_number">
                    	  <div class="level_img"><img src="${base}/css/points/userlevel_0.png" width="25" height="45" /></div>
                        <div class="level_num">
                        <strong>铜牌及以上会员</strong>
                    	<b>需要2000积分</b>
                        </div>
                    <a href="javascript:void(0);" dialog_uri="#/user_dialog_login.htm" dialog_title="会员登录" dialog_width="430" dialog_height="100" dialog_id="user_login" >兑换</a>
                    </li>
                </ul>
                 <ul>
                	<li class="recom_img"><a href="#/integral/view_2.htm" target="_blank"><img src="/38ba9f82-bee7-4d39-9994-f64a6052afed.jpg" width="200" height="200" /></a><span>
                    <a href="#/integral/view_2.htm" target="_blank">【全积分兑换】菲尔美正品床上用品决明子定型枕芯简约线条枕头芯 </a></span>
                    <em></em></li>
                    <li class="inte_number">
                    	 <div class="level_img"><img src="${base}/css/points/userlevel_1.png" width="25" height="45" /></div>
                        <div class="level_num">
                        <strong>银牌及以上会员</strong>
                    	<b>需要3900积分</b>
                        </div>
                    <a href="javascript:void(0);" dialog_uri="#/user_dialog_login.htm" dialog_title="会员登录" dialog_width="430" dialog_height="100" dialog_id="user_login" >兑换</a>
                    </li>
                </ul>
                <ul>
                	<li class="recom_img"><a href="#/integral/view_3.htm" target="_blank"><img src="/2e8fbab0-3da3-4ff3-8f20-894d464d66d3.jpg" width="200" height="200" /></a><span>
                    <a href="#/integral/view_3.htm" target="_blank">BYZA正品抗疲劳眼睛防辐射眼镜防蓝光电脑游戏平光护目镜男女潮款 </a></span>
                    <em></em></li>
                    <li class="inte_number">
                    	<div class="level_img"><img src="${base}/css/points/userlevel_0.png" width="25" height="45" /></div>
                        <div class="level_num">
                        <strong>铜牌及以上会员</strong>
                    	<b>需要2500积分</b>
                        </div>
                    <a href="javascript:void(0);" dialog_uri="#/user_dialog_login.htm" dialog_title="会员登录" dialog_width="430" dialog_height="100" dialog_id="user_login" >兑换</a>
                    </li>
                </ul>
                <ul>
                	<li class="recom_img"><a href="#/integral/view_4.htm" target="_blank"><img src="/ac2e4d1d-e7cb-49a9-abc2-c93e5229ae70.jpg" width="200" height="200" /></a><span>
                    <a href="#/integral/view_4.htm" target="_blank">厨房用品创意青花陶瓷调味罐套装调味品调料架套装送架子勺子包邮 </a></span>
                    <em></em></li>
                    <li class="inte_number">
                    	<div class="level_img"><img src="${base}/css/points/userlevel_2.png" width="25" height="45" /></div>
                        <div class="level_num">
                        <strong>金牌及以上会员</strong>
                    	<b>需要3500积分</b>
                        </div>
                    <a href="javascript:void(0);" dialog_uri="#/user_dialog_login.htm" dialog_title="会员登录" dialog_width="430" dialog_height="100" dialog_id="user_login" >兑换</a>
                    </li>
                </ul>
                <ul>
                	<li class="recom_img"><a href="#/integral/view_5.htm" target="_blank"><img src="/f0629fc5-a2f1-4e0d-b3d6-f558b890e0cf.jpg" width="200" height="200" /></a><span>
                    <a href="#/integral/view_5.htm" target="_blank">爵奇 加厚不锈钢煎蛋器煎蛋模具 创意煎蛋圈煎鸡蛋模型套装心形 </a></span>
                    <em></em></li>
                    <li class="inte_number">
                    	<div class="level_img"><img src="${base}/css/points/userlevel_0.png" width="25" height="45" /></div>
                        <div class="level_num">
                        <strong>铜牌及以上会员</strong>
                    	<b>需要1780积分</b>
                        </div>
                    <a href="javascript:void(0);" dialog_uri="#/user_dialog_login.htm" dialog_title="会员登录" dialog_width="430" dialog_height="100" dialog_id="user_login" >兑换</a>
                    </li>
                </ul>
                <ul>
                	<li class="recom_img"><a href="#/integral/view_6.htm" target="_blank"><img src="/a844d715-d45c-4365-bf90-108f308f90f1.jpg" width="200" height="200" /></a><span>
                    <a href="#/integral/view_6.htm" target="_blank">时尚公主蕾丝台灯 现代田园卧室床头温馨结婚庆装饰台灯603-1T </a></span>
                    <em></em></li>
                    <li class="inte_number">
                    	<div class="level_img"><img src="${base}/css/points/userlevel_3.png" width="25" height="45" /></div>
                        <div class="level_num">
                        <strong>超级会员专享</strong>
                    	<b>需要11800积分</b>
                        </div>
                    <a href="javascript:void(0);" dialog_uri="#/user_dialog_login.htm" dialog_title="会员登录" dialog_width="430" dialog_height="100" dialog_id="user_login" >兑换</a>
                    </li>
                </ul>
                <ul>
                	<li class="recom_img"><a href="#/integral/view_7.htm" target="_blank"><img src="/958e90a8-8872-413f-b551-8febd7b69737.jpg" width="200" height="200" /></a><span>
                    <a href="#/integral/view_7.htm" target="_blank">冰美人水润嫩白护手霜60g 美白滋润保湿防裂缓解干燥 栀子花香型 </a></span>
                    <em></em></li>
                    <li class="inte_number">
                    	<div class="level_img"><img src="${base}/css/points/userlevel_0.png" width="25" height="45" /></div>
                        <div class="level_num">
                        <strong>铜牌及以上会员</strong>
                    	<b>需要600积分</b>
                        </div>
                    <a href="javascript:void(0);" dialog_uri="#/user_dialog_login.htm" dialog_title="会员登录" dialog_width="430" dialog_height="100" dialog_id="user_login" >兑换</a>
                    </li>
                </ul>
                <ul>
                	<li class="recom_img"><a href="#/integral/view_8.htm" target="_blank"><img src="/4fb92367-a503-4c5f-897f-9f24be65193e.jpg" width="200" height="200" /></a><span>
                    <a href="#/integral/view_8.htm" target="_blank">芬尚女士香水 持久淡香清新 浪漫樱花30ml 送小样 正品专柜淡香水 </a></span>
                    <em></em></li>
                    <li class="inte_number">
                    	 <div class="level_img"><img src="${base}/css/points/userlevel_1.png" width="25" height="45" /></div>
                        <div class="level_num">
                        <strong>银牌及以上会员</strong>
                    	<b>需要2900积分</b>
                        </div>
                    <a href="javascript:void(0);" dialog_uri="#/user_dialog_login.htm" dialog_title="会员登录" dialog_width="430" dialog_height="100" dialog_id="user_login" >兑换</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
 ﻿<script>
jQuery(document).ready(function(){
			//滚动条滚动事件
	jQuery(window).scroll(function(){
	var top = jQuery(document).scrollTop();
	if(top==0){
		  jQuery("#back_box").hide();
		  jQuery(".back_box_x").hide();
		}else{
		  jQuery("#back_box").show();	
	      jQuery(".back_box_x").show();
		}
	});	
	//
	jQuery("#toTop").click(function(){
       jQuery('body,html').animate({scrollTop:0},1000);
       return false;
    });
});
</script>
<div class="clear"></div>
<@p.footer/>
