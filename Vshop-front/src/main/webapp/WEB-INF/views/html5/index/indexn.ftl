<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="apple-wap-web-app-capable" content="yes">
<meta name="apple-wap-web-app-status-bar-style" content="black-translucent">

<#if storeid!=null>
	<#if storeid=="0">
		<title>微商家</title> 
	</#if>
		<#assign storeNameTag = newTag("storeNameTag") />
		<#assign storeName2 = storeNameTag("{'storeid':'${storeid}', 'storeId':'${storeid}'}") />
		<#if storeName2??>
		<title>${storeName2}</title>
		<#else>
		<title>微商家</title>    
		</#if>          
	<#else>
	<title>微商家</title>
</#if>

<#assign goodsRecommendTag = newTag("goodsRecommendTag") />
<#assign sjgoodslist = goodsRecommendTag("{'reCommendId':'0','storeId':'${storeid}','goodsCommend':'0'}") />
           	
<link rel="stylesheet" href="${base}/res/html5/css2/style.css" type="text/css" />
<link rel="stylesheet" href="${base}/res/html5/css2/reset.css" type="text/css" />
<link rel="stylesheet" href="${base}/res/html5/css2/swiper.min.css" type="text/css" />

<script src="${base}/res/html5/js/TouchSlide.1.1.js"></script>

<script src="${base}/res/html5/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${base}/res/html5/js/zepto.min.js"></script>
<script type="text/javascript" src="${base}/res/html5/js/swiper-3.3.1.jquery.min.js"></script>
<script type="text/javascript" src="${base}/res/html5/js/swiper.min.js"></script>
<script>

<!--商品或商家收藏开始-->
function collect_storeorgoods(goodsId,favType,storeId){
	  
		$.ajax({
		    	url : "${base}/Favorite/saveCollection",
		        type : 'post',
		        data : {'goodsId':goodsId,'favType':favType,'storeId':storeId},
		        dataType : 'json',
		        success : function(data){
                if(data.success){
            	    layer.msg(data.msg,{icon:1});
            	    if(data.favType==1){
            	    	 $("#img_product").attr("src","${base}/res/html5/images/star.png");
            	    }else{
            	    	$("#img_store").attr("src","${base}/res/html5/images/star.png");
            	    }
				}else{
            		layer.msg(data.msg,{icon:2});
            	}
            }
		 }); 
  } 
<!--商品或商家收藏结束-->


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
    keyword = keyword.replace(/'/g, "");
    window.location.href='${base}/m/goods/goodslist?searchType='+'keywordSearch'+'&keyword='+keyword;
 }
</script>
</head>
<body >
	<div id="wrap">
			<#if sjgoodslist!=null && sjgoodslist?size gt 0>
			<header style="width: 100%; height: 44px; overflow: hidden;">
				<div class="swiper-container1">
			        <div style="width: 100%;" class="swiper-wrapper">
			        <!--	<div class="swiper-slide"><a href="${base}/m/category/category">商品类目</a></div> -->
			        <#assign goodsClassTag = newTag("goodsClassTag")>
					<#assign goodsList = goodsClassTag("{'parentid':'0', 'storeid':'${storeid}', 'clsLevel':'1'}")>
			 		<#list goodsList as class>
			 			<div class="swiper-slide"><a href="${base}/m/goods/gcgoodslist?gcId=${class.gcId}&amp;gcType=0&amp;pcId=${class.gcId}">${class.gcName}</a></div>
			        </#list>
			   		<#--         
			            <div class="swiper-slide"><a href="${base}/m/category/category">商品类目</a></div>
			            <div class="swiper-slide"><a href="${base}/m/goods/goodslist?searchType=gcIdSearch&amp;keyword=703">教育书籍</a></div>
						<div class="swiper-slide"><a href="${base}/m/goods/goodslist?searchType=gcIdSearch&amp;keyword=744">户外探险</a></div>
						<div class="swiper-slide"><a href="${base}/m/goods/goodslist?searchType=gcIdSearch&amp;keyword=747">文具教具</a></div>
					-->
			        </div>
			        <script>
			        	var swiper = new Swiper('.swiper-container1', {
					        paginationClickable: true,
					        slidesPerView: 5,
					        breakpoints: {
					            1024: {
					                slidesPerView: 4,
					                spaceBetween: 40
					            },
					            768: {
					                slidesPerView: 3,
					                spaceBetween: 30
					            },
					            640: {
					                slidesPerView: 2,
					                spaceBetween: 20
					            },
					            320: {
					                slidesPerView: 1,
					                spaceBetween: 10
					            }
					        }
					    });
			        </script>
			        <!-- Add Pagination -->
			    </div>
			</header>
			</#if>
			<header>
				<#if sjgoodslist!=null && sjgoodslist?size gt 0>
				<div class="in">
					<div class="inn"></div>
					<input type="text" id="keyword" name="keyword" placeholder="搜索商品" />
					<button onclick="searcht();">搜索</button>
				</div>
				</#if>
			
				<div id="box" class="swiper-container">
					<ul class="swiper-wrapper">
						<li class="swiper-slide"><img src="${base}/res/html5/img/ban1.jpg"></li>
						<li class="swiper-slide"><img src="${base}/res/html5/img/ban2.jpg"></li>
						<li class="swiper-slide"><img src="${base}/res/html5/img/ban3.jpg"></li>
				
					</ul>
				</div>
				
				<script>
					var mySwiper = new Swiper(".swiper-container", {
						slidesPerView: 'auto',
						centeredSlides: true,
						paginationClickable: true,
						spaceBetween: 0,
						autoplay: 3000,
						loop: true,
						loopedSlides: 10,
						autoplayDisableOnInteraction: false,
						pagination: '.swiper-pagination',
					});
				</script>
				
				<!--<#if sjgoodslist!=null && sjgoodslist?size gt 0>
				<div class="in">
					<div class="inn"></div>
					<input type="text" id="keyword" name="keyword" placeholder="搜索商品" />
					<button onclick="searcht();">搜索</button>
				</div>
				</#if>-->
			</header>
			
			<#if sjgoodslist!=null && sjgoodslist?size gt 0>
			
			<#assign goodsRecommendTag = newTag("goodsRecommendTag") />
           	<#assign recommendGoodslist = goodsRecommendTag("{'reCommendId':'3','storeId':'${storeid}','goodsCommend':'1'}") />
           	
			<div class="main">
				<div class="m-top">
					<h3>劲爆新品</h3>
					<a href="${base}/m/goods/newgoodslist?reCommendId=3">更多>></a>
				</div>
				
				<#if recommendGoodslist!=null && recommendGoodslist?size gt 0>
                	<#list recommendGoodslist as recommendGoods>
                		<#if (recommendGoods_index%2==0)>
	            		<div class="m-bot">
	            		<dl>
                			<a href="${base}/m/goods/goodsdetail?id=${recommendGoods.goodsId}&amp;storeId=${recommendGoods.storeId}">
								<dt><img src="${imgServer}${recommendGoods.goodsImage}" alt="" style="width:158px; height:158px;" /></dt>
							</a>
							<dd>
								<p>
									<#if recommendGoods.goodsName?length lte 11>   
										${recommendGoods.goodsName}
								  	<#else> 
										${recommendGoods.goodsName[0..11]}... 
								   	</#if>
								</p>
								<div>  
									<h5>¥${recommendGoods.goodsStorePrice}</h5>
									<a><span>${recommendGoods.salenum}</span>人已经购买</a>
								</div>
							</dd>
						</dl>
				        </#if>
				             
				        <#if (recommendGoods_index%2==1)>
				        <dl  class="last">
                			<a href="${base}/m/goods/goodsdetail?id=${recommendGoods.goodsId}&amp;storeId=${recommendGoods.storeId}">
								<dt><img src="${imgServer}${recommendGoods.goodsImage}" alt="" style="width:158px; height:158px;" /></dt>
							</a>
							<dd>
								<p>
									<#if recommendGoods.goodsName?length lte 11>   
										${recommendGoods.goodsName}
									<#else> 
										${recommendGoods.goodsName[0..11]}... 
									</#if>
								</p>
								<div>  
									<h5>¥${recommendGoods.goodsStorePrice}</h5>
									<a><span>${recommendGoods.salenum}</span>人已经购买</a>
								</div>
							</dd>
						</dl>
						</div>
				        </#if>
				        
				        <#if (recommendGoods_index==3)>
	               			<#break/>
				    	</#if>
					</#list>
				<#else>
					<div class="m-bot2">				
						该店铺主人很懒，还没有推荐新品到首页
					</div>
				</#if>
			</div>
			
           	<#assign recommendGoodslist = goodsRecommendTag("{'reCommendId':'2','storeId':'${storeid}','goodsCommend':'1'}") />
			<div class="main">
				<div class="m-top">
					<h3>店长力荐</h3>
					<a href="${base}/m/goods/recommendgoodslist?reCommendId=2">更多>></a>
				</div>
				
				<#if recommendGoodslist!=null && recommendGoodslist?size gt 0>
                	<#list recommendGoodslist as recommendGoods>
                	
                	<#if (recommendGoods_index%2==0)>
	            	<div class="m-bot">
	               		<dl>
                			<a href="${base}/m/goods/goodsdetail?id=${recommendGoods.goodsId}&amp;storeId=${recommendGoods.storeId}">
								<dt><img src="${imgServer}${recommendGoods.goodsImage}" alt="" style="width:158px; height:158px;" /></dt>
							</a>
							<dd>
								<p>
									<#if recommendGoods.goodsName?length lte 11>   
										${recommendGoods.goodsName}
									<#else> 
										${recommendGoods.goodsName[0..11]}... 
									</#if>
								</p>
							<div>  
								<h5>¥${recommendGoods.goodsStorePrice}</h5>
								<a><span>${recommendGoods.salenum}</span>人已经购买</a>
							</div>
						</dd>
					</dl>
				    </#if>
				             
				    <#if (recommendGoods_index%2==1)>
				        <dl  class="last">
                			<a href="${base}/m/goods/goodsdetail?id=${recommendGoods.goodsId}&amp;storeId=${recommendGoods.storeId}">
								<dt><img src="${imgServer}${recommendGoods.goodsImage}" alt="" style="width:158px; height:158px;" /></dt>
							</a>
							<dd>
								<p>
									<#if recommendGoods.goodsName?length lte 11>   
										${recommendGoods.goodsName}
									<#else> 
										${recommendGoods.goodsName[0..11]}... 
									</#if>
								</p>
							<div>  
								<h5>¥${recommendGoods.goodsStorePrice}</h5>
								<a><span>${recommendGoods.salenum}</span>人已经购买</a>
							</div>
						</dd>
					</dl>
					</div>
					</#if>
				        
					<#if (recommendGoods_index==3)>
	               		<#break/>
				    </#if>
				</#list>
			<#else>
					<div class="m-bot2">				
						该店铺主人很懒，还没有在首页力荐商品
					</div>
			</#if>
			
			<#else>
					<div class="m-bot2" style="color:red;">				
						该店铺主人很懒，还没有上架商品！
					</div>
			</#if>
		</div>
	</div>
	<div class="mt50" style="height:49px;"></div>
<!--悬浮层-->
<script>
function click_layer() {
	var ret = jQuery("#show").attr("mark");
	if(ret=="show") {
		jQuery("#show").hide();
		jQuery("#show").attr("mark","hide");
	} else {
		jQuery("#show").show();
		jQuery("#show").attr("mark","show");	
	}
}

</script>
<div class="layer">
	<!--<a class="layer_menu" href="javascript:void(0);" onclick="click_layer();"><img src="${base}/res/html5/images/layer_05.png" width="35" height="35" /><span class="black_bg"></span></a>-->
    <div class="layer_show" id="show" mark="hide">
    	<ul>
        	<li><a href="${base}/m/index/index"><img src="${base}/res/html5/images/layer_01.png" width="20" height="20" /><p>首页</p></a></li>
            <li><a href="${base}/m/authc/buyer/orderList?orderState=99"><img src="${base}/res/html5/images/layer_02.png" width="20" height="20" /><p>订单</p></a></li>
            <#-- 
            <li><a href="${base}/m/authc/cart/cartGoodsList"><img src="${base}/res/html5/images/layer_03.png" width="20" height="20" /><p>商品表</p></a></li>
             -->
            <li><a href="${base}/m/authc/buyer/center"><img src="${base}/res/html5/images/layer_04.png" width="20" height="20" /><p>用户中心</p></a></li>
        </ul>
        <div class="show_bg"></div>
    </div>
</div>

</body>
</html>
