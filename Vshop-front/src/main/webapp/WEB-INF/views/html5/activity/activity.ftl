<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,inital-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta name="apple-wap-web-app-capable" content="yes">
<meta name="apple-wap-web-app-status-bar-style" content="black-translucent">
<title>优彼，微商家</title>
<link href="${base}/res/html5/css/style.css" rel="stylesheet" type="text/css" />
<link href="${base}/res/html5/css/stylen.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${base}/res/html5/css/swiper.min.css?a=1" />
<link href="${base}/res/html5/css/resetn.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${base}/res/html5/css/zhuanqu.css"/>
		


<script src="${base}/res/html5/js/TouchSlide.1.1.js"></script>

<script src="${base}/res/html5/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${base}/res/html5/js/zepto.min.js"></script>
<script type="text/javascript" src="${base}/res/html5/js/swiper-3.3.1.jquery.min.js"></script>
<script type="text/javascript" src="${base}/res/html5/js/swiper.min.js"></script>

	
<script>




function timeCount(remain_id){
   function _fresh(){
      var nowDate = new Date(),                                    //当前时间
      endDate = new Date($('#'+remain_id).attr('endtime')),    //截止时间
      totalS  = parseInt((endDate.getTime() - nowDate.getTime()) / 1000);     //总秒数
      _day    = parseInt(totalS / 3600 / 24)+"";
      _hour   = parseInt(totalS / 3600% 24)+"";
      _minute = parseInt((totalS / 60) % 60)+"";
      _second = parseInt(totalS % 60)+"";
	  d_html = "";
	  if(_day.length<2){
			d_html = "<b>0</b>";
			}
	  for(var n = 0;n<_day.length;n++){
     		var q = _day.substring(n,n+1);
			d_html = d_html+'<b>'+q+'</b>';
		}
		d_html = d_html+"<b>天</b>"
	  h_html = "";
	  if(_hour.length<2){
			h_html = "<b>0</b>";
			}
	  for(var n = 0;n<_hour.length;n++){
     		var q = _hour.substring(n,n+1);
			h_html = h_html+'<b>'+q+'</b>';
		}
		m_html = "";
		if(_minute.length<2){
			m_html = "<b>0</b>";
			}
	  for(var n = 0;n<_minute.length;n++){
     		var q = _minute.substring(n,n+1);
			m_html =m_html+ '<b>'+q+'</b>'
		}
		s_html = "";
		if(_second.length<2){
			s_html = "<b>0</b>";
			}
	  for(var n = 0;n<_second.length;n++){
     		var q = _second.substring(n,n+1);
			s_html =s_html+ '<b>'+q+'</b>'
		}
     jQuery('#'+remain_id).html(d_html+'<strong>:</strong>'+h_html+'<strong>:</strong>'+m_html+'<strong>:</strong>'+s_html);
     if( totalS <= 0){
       jQuery('#'+remain_id).html('该0元试用已结束');
       clearInterval(sh);
     }
  }
  _fresh();
  var sh = setInterval(_fresh,1000);
}
jQuery(document).ready(function() {
	jQuery("li[id=gc_]").each(function(index, element) {
		if(jQuery(this).find(".fl").html().length>5){
			var a=jQuery(this).find(".fl").html().substring(0,5);
			jQuery(this).find(".fl").html(a);
			}	
    });
	
	
	
timeCount("time_down_info");
 if(isWeiXin()){
       jQuery(".phone_top").hide();
     }else{
		 jQuery(".phone_top").show();
		 }
});
function search_form(){
	jQuery("#theForm").submit();
}
function isWeiXin(){
    var ua = window.navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
        return true;
    }else{
        return false;
    }
}

function searcht(){
    var keyword=$("#keyword").val();
   
    window.location.href='${base}/m/goods/goodslist?searchType='+'keywordSearch'+'&keyword='+keyword;
 }
</script>
</head>
<body >

	<div class="wrap">
			 <#assign activityTag = newTag("activityTag") />
			   <#assign activity = activityTag("{'activity_id':'${activityId}'}") />
			   
			   
			<div class="banner">
				<img src="${imgServer}${activity.activityBanner}" />
			</div>
			
			  <#assign activityGoodsTag = newTag("activityGoodsTag") />
           <#assign relGoodsActivitylist = activityGoodsTag("{'activity_id':'${activityId}','activity_type':'30'}") />
           
			<div class="main">
				<div class="m-top">
					<h3>平台推荐</h3>
					<!-- 
					<a href="#">更多>></a>
					 -->
					
					
				</div>
				<div class="m-bot">
				
           
           <#if relGoodsActivitylist??>
                	<#list relGoodsActivitylist as goodsActivityRel>
                	
                	
					
					
	                       
	                         <#if (goodsActivityRel_index==0)>
	               			     	<dl>
                		  <a href="${base}/m/goods/goodsdetail?id=${goodsActivityRel.goods.goodsId}">
						<dt><img src="${imgServer}${goodsActivityRel.goods.goodsImage}" alt=""  style="width: 186px;height: 186px;" /></dt>
						</a>
						<dd>
							<p><#if goodsActivityRel.goods.goodsName?length lt 11>   
											${goodsActivityRel.goods.goodsName}
									    <#else> 
											${goodsActivityRel.goods.goodsName[0..10]}... 
									   </#if>
									   </p>
							<div>  
								<h5>¥${goodsActivityRel.goods.goodsStorePrice}
								</h5>
								<a>¥${goodsActivityRel.goods.goodsMarketPrice}</a>
							</div>
						</dd>
					</dl>
				             </#if>
				             
				        <#if (goodsActivityRel_index==1)>
				               	     	<dl  class="last">
                		  <a href="${base}/m/goods/goodsdetail?id=${goodsActivityRel.goods.goodsId}">
						<dt><img src="${imgServer}${goodsActivityRel.goods.goodsImage}" alt=""  style="width: 186px;height: 186px;" /></dt>
						</a>
						<dd>
							<p><#if goodsActivityRel.goods.goodsName?length lt 11>   
											${goodsActivityRel.goods.goodsName}
									    <#else> 
											${goodsActivityRel.goods.goodsName[0..10]}... 
									   </#if>
									   </p>
							<div>  
								<h5>¥${goodsActivityRel.goods.goodsStorePrice}
								</h5>
								<a>¥${goodsActivityRel.goods.goodsMarketPrice}</a>
							</div>
						</dd>
					</dl>
				        </#if>
				        
				            <#if (goodsActivityRel_index==1)>
	               			      <#break/>
				             </#if>
                    </#list>
                 </#if>
                 
                 
				</div>
				
				
			</div>
			<div class="banner">
				<img src="${imgServer}${activity.activityBanner2}" />
			</div>
			
			 <#assign activityGoodsTag = newTag("activityGoodsTag") />
           <#assign relGoodsActivitylist = activityGoodsTag("{'activity_id':'${activityId}','activity_type':'31'}") />
           
           
			<div class="main">
				<div class="m-top">
					<h3>主打商品</h3>
					<!-- 
					<a href="#">更多>></a>
					 -->
					
				</div>
				<div class="m-bot">
					
           <#if relGoodsActivitylist??>
                	<#list relGoodsActivitylist as goodsActivityRel>
                	
                	
					
					
	                       
	                         <#if (goodsActivityRel_index==0)>
	               			     	<dl>
                		  <a href="${base}/m/goods/goodsdetail?id=${goodsActivityRel.goods.goodsId}">
						<dt><img src="${imgServer}${goodsActivityRel.goods.goodsImage}" alt=""  style="width: 186px;height: 186px;" /></dt>
						</a>
						<dd>
							<p><#if goodsActivityRel.goods.goodsName?length lt 11>   
											${goodsActivityRel.goods.goodsName}
									    <#else> 
											${goodsActivityRel.goods.goodsName[0..10]}... 
									   </#if>
									   </p>
							<div>  
								<h5>¥${goodsActivityRel.goods.goodsStorePrice}
								</h5>
								<a>¥${goodsActivityRel.goods.goodsMarketPrice}</a>
							</div>
						</dd>
					</dl>
				             </#if>
				             
				        <#if (goodsActivityRel_index==1)>
				               	     	<dl  class="last">
                		  <a href="${base}/m/goods/goodsdetail?id=${goodsActivityRel.goods.goodsId}">
						<dt><img src="${imgServer}${goodsActivityRel.goods.goodsImage}" alt=""  style="width: 186px;height: 186px;" /></dt>
						</a>
						<dd>
							<p><#if goodsActivityRel.goods.goodsName?length lt 11>   
											${goodsActivityRel.goods.goodsName}
									    <#else> 
											${goodsActivityRel.goods.goodsName[0..10]}... 
									   </#if>
									   </p>
							<div>  
								<h5>¥${goodsActivityRel.goods.goodsStorePrice}
								</h5>
								<a>¥${goodsActivityRel.goods.goodsMarketPrice}</a>
							</div>
						</dd>
					</dl>
				        </#if>
				        
				            <#if (goodsActivityRel_index==1)>
	               			      <#break/>
				             </#if>
                    </#list>
                 </#if>
                 
				</div>
				
			</div>
			
				 <#assign activityGoodsTag = newTag("activityGoodsTag") />
           <#assign relGoodsActivitylist = activityGoodsTag("{'activity_id':'${activityId}','activity_type':'32'}") />
			<div class="main">
				<div class="m-top">
					<h3>名师力荐</h3>
					<!-- 
					
					<a href="#">更多>></a>
					 -->
					
				</div>
				<div class="m-bot">
					
           <#if relGoodsActivitylist??>
                	<#list relGoodsActivitylist as goodsActivityRel>
                	
                	
					
					
	                       
	                         <#if (goodsActivityRel_index==0)>
	               			     	<dl>
                		  <a href="${base}/m/goods/goodsdetail?id=${goodsActivityRel.goods.goodsId}">
						<dt><img src="${imgServer}${goodsActivityRel.goods.goodsImage}" alt=""  style="width: 186px;height: 186px;" /></dt>
						</a>
						<dd>
							<p><#if goodsActivityRel.goods.goodsName?length lt 11>   
											${goodsActivityRel.goods.goodsName}
									    <#else> 
											${goodsActivityRel.goods.goodsName[0..10]}... 
									   </#if>
									   </p>
							<div>  
								<h5>¥${goodsActivityRel.goods.goodsStorePrice}
								</h5>
								<a>¥${goodsActivityRel.goods.goodsMarketPrice}</a>
							</div>
						</dd>
					</dl>
				             </#if>
				             
				        <#if (goodsActivityRel_index==1)>
				               	     	<dl  class="last">
                		  <a href="${base}/m/goods/goodsdetail?id=${goodsActivityRel.goods.goodsId}">
						<dt><img src="${imgServer}${goodsActivityRel.goods.goodsImage}" alt=""  style="width: 186px;height: 186px;" /></dt>
						</a>
						<dd>
							<p><#if goodsActivityRel.goods.goodsName?length lt 11>   
											${goodsActivityRel.goods.goodsName}
									    <#else> 
											${goodsActivityRel.goods.goodsName[0..10]}... 
									   </#if>
									   </p>
							<div>  
								<h5>¥${goodsActivityRel.goods.goodsStorePrice}
								</h5>
								<a>¥${goodsActivityRel.goods.goodsMarketPrice}</a>
							</div>
						</dd>
					</dl>
				        </#if>
				        
				            <#if (goodsActivityRel_index==1)>
	               			      <#break/>
				             </#if>
                    </#list>
                 </#if>
                 
				</div>
			</div>
		</div>		
<!--悬浮层-->
<script>
function click_layer(){
	var ret = jQuery("#show").attr("mark");
	if(ret=="show"){
		jQuery("#show").hide();
		jQuery("#show").attr("mark","hide");
		}else{
		jQuery("#show").show();
		jQuery("#show").attr("mark","show");	
			}
	}

</script>
<div class="layer">
	<a class="layer_menu" href="javascript:void(0);" onclick="click_layer();"><img src="${base}/res/html5/images/layer_05.png" width="35" height="35" /><span class="black_bg"></span></a>
    <div class="layer_show" id="show" mark="hide" style="display:none">
    	<ul>
        	<li><a href="${base}/m/index/index"><img src="${base}/res/html5/images/layer_01.png" width="20" height="20" /><p>首页</p></a></li>
            <li><a href="${base}/m/authc/buyer/orderList?orderState=99"><img src="${base}/res/html5/images/layer_02.png" width="20" height="20" /><p>订单</p></a></li>
            
            <!-- 
            <li><a href="${base}/m/authc/cart/cartGoodsList"><img src="${base}/res/html5/images/layer_03.png" width="20" height="20" /><p>购物车</p></a></li>
             -->
            <li><a href="${base}/m/authc/buyer/center"><img src="${base}/res/html5/images/layer_04.png" width="20" height="20" /><p>用户中心</p></a></li>
        </ul>
        <div class="show_bg"></div>
    </div>
</div>

</body>
</html>
