<@p.storeheader title="首页"/>
 <!--根据商家id获取商家内容  -->
<#assign storeInfoTag =newTag("storeInfoTag")>  
<#assign storeVo =storeInfoTag("{'storeId':'${storeId}'}")>
 <!--根据商家id取评分内容-->
<#assign evaluateStorebyStoreIdTag =newTag("evaluateStorebyStoreIdTag")>  
<#assign evaluateStore =evaluateStorebyStoreIdTag("{'storeId':'${storeId}'}")>

<#assign goodsListTag = newTag("goodsListTag")/>
<!--获得该商家下的商品数量-->
<#assign storegoodscount =goodsListTag("{'storeid':'${storeVo.storeId}','tagDataType':'5'}")>

<#if storeVo??>
   <#if storeVo.storeState==1>
		<style>
		  .raty img { letter-spacing: normal; word-spacing: normal; display: inline-block; width: 16px; height: 16px; margin: 3.5px 0;}
		</style>
		 <!--根据商家id查找商家信息start  -->
		 <!--根据商家id查找商家信息end  -->
		  <div style="width: 1000px;overflow: hidden;margin: 0 auto;">
		  </div>
		  <article id="content">
		    <section class="layout expanded mt10" >
		     <form id="acct-form" method="post" action="" name ="queryListForm">
		      <input type="hidden" name="div" id="div" value = "#dataListDiv"/>
		      <input type="hidden" name="goodsnames" id="goodsnames" value = "${goodsName}"/>
		      <input type="hidden" name="storeId" id="storeId" value ="${storeId}"/>
				      <article class="nc-goods-main"  id="dataListDiv">
				         <#if storeClassId==-1>
				         <!--获取该商家下关灯片-->
							<#assign storeInfoSlideTag = newTag("storeInfoSlideTag")/>
							<#assign storelistside =storeInfoSlideTag("{'storeId':'${storeId}'}")>
				           <div class="flexslider">
						          <ul class="slides">
						                 <#if storelistside??>
						                    <#list storelistside as upslide>
							                  <li>
							                       <a href="${upslide.imgUrl!'javascript:void(0)'}" target="_blank"><img src="<#if upslide.fileName!=null>${imgServer}${upslide.fileName}<#else>${base}/res/images/noslide.png</#if>" width="790" height="355"></a>
							                  </li>
							                 </#list>
						                 </#if> 
						           </ul>
				            </div>
				        
				      
				        <div class="nc-s-c-s3 ncg-list mt10">
				          <div class="title pngFix"> <span><a href="javascript:void(0)" onclick="initDataList2();"  class="more">更多</a></span>
				            <h4> 推荐商品</h4>
				          </div>
				          <div class="content pt20">
				            <ul>
							  <#assign recommendgoodslist =goodsListTag("{'storeid':'${storeVo.storeId}','commend':'1','tagDataType':'2','pagesize':'4'}")>
							  <#if recommendgoodslist?exists && recommendgoodslist?size gt 0>
							      <#list recommendgoodslist as recommendgoods>
							          <li>
						                <dl>
						                  <dt><a href="${base}/product/detail?id=${recommendgoods.goodsId}" target="_blank">${recommendgoods.goodsName}</a></dt>
						                  <dd class="ncg-pic pngFix"><a href="${base}/product/detail?id=${recommendgoods.goodsId}" target="_blank" class="thumb"><i></i><img src="<#if recommendgoods.goodsImage!=null>${imgServer}${recommendgoods.goodsImage}<#else>${base}/res/images/member/default_image.png</#if>" onload="javascript:DrawImage(this,160,160);" title="${recommendgoods.goodsName}" alt="${recommendgoods.goodsName}" /></a></dd>
						                  <dd class="ncg-price"><em class="price"><i>&yen;</i>
						                                           ${recommendgoods.goodsStorePrice}                                       </em></dd>
						                  <dd class="ncg-sold">已销售：<strong> ${recommendgoods.salenum}</strong> 件</dd>
						                </dl>
				                     </li>
				                   <#if (recommendgoods_index==5)>
				                        <#break/>
				                    </#if>
							      </#list>
							  </#if>
				             </ul>
				           </div>
				        </div>
				        
				        <div class="nc-s-c-s3 ncg-list mt10">
				          <div class="title pngFix"><span><a href="javascript:void(0)" onclick="initDataList2();" class="more">更多</a></span>
				            <h4>新品</h4>
				          </div>
				          <div class="content pt20">
				            <ul>
							  <#assign newProductlist = goodsListTag("{'storeid':'${storeVo.storeId}','order':'0','tagDataType':'2','pagesize':'16'}")>
							  <#if newProductlist?exists && newProductlist?size gt 0>
							      <#list newProductlist as newGoods>
							          <li>
						                <dl>
						                  <dt><a href="${base}/product/detail?id=${newGoods.goodsId}" target="_blank">${newGoods.goodsName}</a></dt>
						                  <dd class="ncg-pic pngFix"><a href="${base}/product/detail?id=${newGoods.goodsId}" target="_blank" class="thumb"><i></i><img src="<#if newGoods.goodsImage!=null>${imgServer}${newGoods.goodsImage}<#else>${base}/res/images/member/default_image.png</#if>" onload="javascript:DrawImage(this,160,160);" title="${newGoods.goodsName}" alt="${newGoods.goodsName}" /></a></dd>
						                  <dd class="ncg-price"><em class="price"><i>&yen;</i>
						                                           ${newGoods.goodsStorePrice}                                       </em></dd>
						                  <dd class="ncg-sold">已销售：<strong> ${newGoods.salenum}</strong> 件</dd>
						                </dl>
				                     </li>
				                     <#if (newGoods_index==5)>
				                        <#break/>
				                     </#if>
							      </#list>
							  </#if>
				             </ul>
				           </div>
				       </div>
				       </#if>
				       
				       <div class="nc-s-c-s2">
				          <div class="title">
				            <h4>最新优惠券 </h4>
				          </div>
				          <div class="content">
				          	<ul class="consume_list">
				          	<#assign couponTag =newTag("couponTag")>  
							<#assign couponlist =couponTag("{'storeId':'${storeId}'}")>
							<#if couponlist?size gt 0>
							<#list couponlist as coupon>
								<#if coupon.couponState == 0>
				            	<li>
					                <div class="pics">
					                  	<div class="v_ineffect">有效</div>
					                  	<div class="thumb"> 
					                  		<a href="#" target="_blank"> 
					                  			<img src="${base}/res/images/default_coupon_image.png" alt="" width="210" height="127" style="display: inline;">
					                  		</a> 
					                	</div>
					                </div>
					                <p class="mtn">
					                	<a href="javascript:void(0);" title="${coupon.couponTitle}">${coupon.couponTitle}</a>
					                </p>
					                <p class="xi1">有效期： ${coupon.startTimeStr?string("yyyy-MM-dd")} 至 ${coupon.endTimeStr?string("yyyy-MM-dd")}</p>
					                <p class="xi1"><input type="button" id="recoupon" value="领取优惠券" onclick="receiveCoupon('${coupon.couponId}')"/></p>
					                
				              	</li>
				              	</#if>
				            </#list>
				            </#if>
							</ul>
							<div class="clear"></div>
				          </div>
				       </div>
				     </article>
		     </form>
		      <aside class="nc-sidebar">
		      <!--商家基本信息 -->
		
		<div class="ncs-info clearfix">
		  <div class="shop-card">
		    <h4>${storeVo.storeName}</h4>
		    <dl>
		      <dt><span class="thumb size60"><i></i><a href="#?act=show_store&id=2"><img src="<#if storeVo.storeLogo!=null>${imgServer}${storeVo.storeLogo}<#else>${base}/res/images/member/default_image.png</#if>"  onload="javascript:DrawImage(this,60,60);" title="${storeVo.storeName}" alt="${storeVo.storeName}" /></a></span></dt>
		      <dd><a href="#?act=show_store&op=credit&id=2" class="shopkeeper">${storeVo.memberName}</a> <a href="#?act=home&op=sendmsg&member_id=5" class="message text-hidden" title="发站内信">发站内信</a></dd>
		      <dd class="ncs-level">
		       		 商家信用：${evaluateStore.averageCredit!'0'}</dd>
		     <#-- <dd><span>好评率：</span>0%</dd>-->
		    </dl>
		  </div>
		  <div class="shop-rate">
		    <h4>动态评价</h4>
		    <!-- 动态评价 -->
		    <dl class="rate">
		      <dt>描述相符：</dt>
			  <dd class="raty" data-score="${evaluateStore.sevalDesccredit}"></dd>
		      <dt>服务态度：</dt>
			  <dd class="raty" data-score="${evaluateStore.sevalServicecredit}"></dd>
		      <dt>发货速度：</dt>
			  <dd class="raty" data-score="${evaluateStore.sevalDeliverycredit}"></dd>
		    </dl>
		  </div>
		  <div class="shop-detail">
		    <h4>商家信息</h4>
		    <!-- 商家信息 -->
		    <dl>
		      <dt>创店时间：</dt>
		      <dd>
		         <#if storeVo.storeCreatetime??>
		             ${storeVo.storeCreatetime?string('yyyy-MM-dd')}
		         <#else>
		             　　
		         </#if>
		      </dd>
		      <dt>所&nbsp;&nbsp;在&nbsp;&nbsp;地：</dt>
		      <dd>${storeVo.areaInfo}</dd>
		      <dt>商品数量：</dt>
		      <dd><strong>${storegoodscount}</strong>件商品</dd>
		      <dt>商家收藏：</dt>
		      <dd><strong nctype="store_collect">${storeVo.storeCollect}</strong>人收藏</dd>
		            <!--详细地址       <dt>详细地址：</dt>
		      <dd>红旗路220号 慧谷大厦 0712</dd>
		      --> 
		      <!--联系电话       <dt>联系电话：</dt>
		      <dd>2345678</dd>
		      -->
		    </dl>
		  </div>
		 <!--  <div class="shop-im">
		    <h4>联系方式</h4>
		    <p>
		        <a href="http://wpa.qq.com/msgrd?v=3&uin=905669187&Site=b2b2c.leimingtech.com&Menu=yes" target="_blank"><img src="http://wpa.qq.com/pa?p=2:1234567:51" alt="点击这里给我发消息"></a>
		    </p>
		  </div> -->
		  <div class="shop-other" id="shop-other">
		    <ul>
		      <li class="ncs-info-btn-map"><a href="javascript:void(0)" class="pngFix"><span>商家地图</span><b></b> <!-- 商家地图 -->
		        <div class="ncs-info-map" id="map_container" style="width:198px;height:320px;">地图加载中... </div>
		        </a></li>
		      <li class="ncs-info-btn-qrcode" style="border-left:none;"><a href="javascript:void(0)" class="pngFix"><span>商家二维码</span><b></b> <!-- 商家二维码 -->
		        <p class="ncs-info-qrcode"><img src="${imgServer}${storeVo.storeCode}" onerror="this.src='${imgServer}${storeVo.storeCode}'" title="" onload="javascript:DrawImage(this,150,150);"><em>手机扫描二维码<br/>快速收藏商家</em> </p>
		        </a> </li>
		    </ul>
		  </div>
		</div>
		<div class="clear"></div>
		<!--商家基本信息 E--> 
		<script src="${base}/res/js/jquery.raty/jquery.raty.min.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
		$(document).ready(function(){$(".raty").raty({path:"${base}/res/js/jquery.raty/img",hints:['一般', '不错', "很好", '满意', '非常满意'],readOnly:true,width:100,score:function(){return $(this).attr("data-score")}})});
		var cityName ='${storeVo.areaInfo}';
		var address = '${storeVo.storeAddress}';
		var store_name ='${storeVo.storeName}'; 
		var map = "";
		var localCity = "";
		function initialize() {
			map = new BMap.Map("map_container");
			localCity = new BMap.LocalCity();
			
			map.enableScrollWheelZoom(); 
			localCity.get(function(cityResult){
			  if (cityResult) {
			  	var level = cityResult.level;
			  	if (level < 13) level = 13;
			    map.centerAndZoom(cityResult.center, level);
			    cityResultName = cityResult.name;
			    if (cityResultName.indexOf(cityName) >= 0) cityName = cityResult.name;
			    	    	getPoint();
			    	  }
			});
		}
		 
		function loadScript() {
			var script = document.createElement("script");
			script.src = "http://api.map.baidu.com/api?v=1.2&callback=initialize";
			document.body.appendChild(script);
		}
		function getPoint(){
			var myGeo = new BMap.Geocoder();
			myGeo.getPoint(address, function(point){
			var point="";
			if('${storeVo.storeLongitude}'!=''&&'${storeVo.storeAtitude}'!=null){//判断经纬度是否为空
			    point = new BMap.Point('${storeVo.storeLongitude}','${storeVo.storeAtitude}');
			  }
			  if (point) {
			    setPoint(point);
			  }
			}, cityName);
		}
		function setPoint(point){
			  if (point) {
			    map.centerAndZoom(point, 16);
			    var marker = new BMap.Marker(point);
			    map.addOverlay(marker);
			  }
		}
		
		// 当鼠标放在商家地图上再加载百度地图。
		$(function(){
			$('.ncs-info-btn-map').one('mouseover',function(){
				loadScript();
			});
			
			//获得分类id
		    var str='${storeClassId}';
		    if(str!=-1){
		      initDataList();
		    }
		    //根据返回过来的orderField和order进行样式的选中
		    if('${orderField}'!=null&&'${orderField}'!=''){
		   		var flags = '${orderField}';
		   		var flags2='${order}';
		   		//清空默认的选项
		   		//$("ul.ncs-array]").find("li:first").removeClass();
		   		//$("ul.ncs-array]").find("li:first").find("a").removeClass();
		   		//$("ul.ncs-array").find("li[flags='"+goodsAddTime+"']").removeClass();
		   		//根据选中的结果选中
		        $("ul.ncs-array").find("li[flags='"+flags+"']").addClass("selected");
		       	$("ul.ncs-array").find("li[flags='"+flags+"']").find("a").addClass(flags2);
		    }
		});
		
		    /*界面初始化*/
		    function initDataList(){
		        var div = "#dataListDiv";//显示的list 数据div id 必须传递
		        var flas='${storeClassId}';
		        var ur="${base}/store/shopall?storeId=${storeId}&storeClassId=${storeClassId}";
		        if(flas==-9){
		           ur="${base}/store/shopall?storeId=${storeId}&goodsName=${goodsName}";
		        }else if(flas==-8){
		           ur="${base}/store/shopall?storeId=${storeId}&orderField=${orderField}&order=${order}";
		        }
		        $.ajax({
		            async:false,
		            url:ur,//默认加载list 页面
		            data:{div:div},
		            error:function(){
		                 layer.msg('通信失败', {icon: 2});
		            },
		            dataType:'html',
		            type: "POST",
		            contentType:"application/x-www-form-urlencoded; charset=utf-8",
		            success: function(data){
		                $(div).empty();
		                $(div).html(data);
		                $(div).hide();
		                $(div).fadeIn(300);
		            }
		        });
		         //店内搜索框内值回显
		         $('#keyword').attr("value",$("#goodsnames").val());
		    }
		    
		     function initDataList2(){
		        var div = "#dataListDiv";//显示的list 数据div id 必须传递
		        $.ajax({
		            async:false,
		            url:"${base}/store/shopall?storeId=${storeId}",//默认加载list 页面
		            data:{div:div},
		            error:function(){
		                 layer.msg('通信失败', {icon: 2});
		            },
		            dataType:'html',
		            type: "POST",
		            contentType:"application/x-www-form-urlencoded; charset=utf-8",
		            success: function(data){
		                $(div).empty();
		                $(div).html(data);
		                $(div).hide();
		                $(div).fadeIn(300);
		            }
		        });
		    }
		    //人气，销量等搜索
		    function set_form(set){
				if($('input[name="key"]').val() == set){
					if($('input[name="order"]').val() == 'asc'){
						$('input[name="order"]').val('desc');
					}else{
						$('input[name="order"]').val('asc');
					}
				}else{
					$('input[name="order"]').val('desc');
				}
				$('input[name="key"]').val(set);
				window.location = '${base}/store/shop?storeId='+'${storeId}'+'&storeClassId=-8'+'&orderField='+set+'&order='+$('input[name="order"]').val();
			}
		    
		    // 领取优惠券
		    function receiveCoupon(couponId){
		    	$.ajax({
		            type: "post",
		            url: "${base}/store/receiveCoupon?storeId=${storeId}&couponId=" + couponId,
		            dataType: "json",
					async:false,
					success:function(data) {
						if(data.success){
						 	parent.layer.msg(data.msg,{icon:1,time:1000},function(){
								$("#recoupon").val("已领取");
							});
						}else{
							parent.layer.msg(data.msg,{icon:2,time:1000},function(){
								
							});
						}
					}
		        });
		    }
		</script> 
		        
		<!-- <div class="nc-s-c-s1 ncs-message-bar">
		  <div class="title">
		    <h4>客服中心</h4>
		  </div>
		  <div class="content">
		  </div>
		</div>  -->
		
		<!-- 店内搜索，商品分类，商品排行 -->  
		<#import "/commons/shop_left.ftl" as shopleft>
		<@shopleft.left storeId='${storeId}' /> 
		  
		<!-- <div class="nc-s-c-s1 ncs-link-bar mt10">
		  <div class="title">
		    <h4>友情链接</h4>
		  </div>
		  <div class="content">
		          <ul>
		          </ul>
		  </div>
		</div> -->
		      </aside>
		    </section>
		  </article>
		</div>
		<div id="footer" >
		<div class="wrapper">
		  <p><a href="http://192.168.1.230">首页</a>
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
   </#if>
   <#if storeVo.storeState==0>
       <div align="center">
         <b style="font-size: 3em;">
        	 该商家已关闭
         </b>
       </div>
   </#if>
   <#if storeVo.storeState==2>
     <div align="center">
     	<b style="font-size: 3em;">该商家正在审核中</b>
     </div>
   </#if>
</#if>


<script type="text/javascript" src="${base}/res/js/jquery.cookie.js" ></script>
<script type="text/javascript" src="${base}/res/js/perfect-scrollbar.min.js" ></script>
<script type="text/javascript" src="${base}/res/js/jquery.mousewheel.js" ></script>
<script type="text/javascript" src="${base}/res/js/jquery.masonry.js" ></script>
<script type="text/javascript" src="${base}/res/js/jquery.scrollLoading-min.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
            //实现图片慢慢浮现出来的效果
            $("img").load(function () {
                //图片默认隐藏  
                $(this).hide();
                //使用fadeIn特效  
                $(this).fadeIn("5000");
            });
            // 异步加载图片，实现逐屏加载图片
            $(".scrollLoading").scrollLoading(); 
    });
</script>


<!-- 引入幻灯片JS --> 
<script type="text/javascript" src="${base}/res/js/jquery.flexslider-min.js"></script> 
<!-- 绑定幻灯片事件 --> 
<script type="text/javascript">
	  $(window).load(function() {
		    $('.flexslider').flexslider();
	   });
</script>
</body>
</html>
