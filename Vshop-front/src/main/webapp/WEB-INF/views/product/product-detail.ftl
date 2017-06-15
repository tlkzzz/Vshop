<#assign goodsBaseTag =newTag("goodsBaseInfoTag")>
<#assign goods =goodsBaseTag("{'goodsid':'${goodsId}'}")>
<@p.header title="${goods.goodsName}－商品汇 微商家"/>
<script src="${base}/res/js/html5shiv.min.js"></script>
<script type="text/javascript" src="${base}/res/js/jquery.cookie.js" ></script>
<link href="${base}/res/css/shop.css"  rel="stylesheet" type="text/css">
<link href="${base}/res/css/style/default/style.css"  rel="stylesheet" type="text/css">
<script type="text/javascript" src="${base}/res/js/shop.js" charset="utf-8"></script>
<script src="${base}/res/js/jquery.js"></script>
<script type="text/javascript" src="${base}/res/js/jquery.raty/jquery.raty.min.js" ></script>
  <#assign siteSettingTag = newTag("siteSettingTag") />
	<#assign siteSet = siteSettingTag("") />
<style type="text/css">
	body{background:0}#content{width:1200px}
	.expanded .nc-goods-main.nc-goods-main{width:980px}
	.expanded .nc-sidebar{width:210px}
	.nc-goods-main{width:1200px}
	.wrapper{width:1200px;}
	.ncg-com-list .content ul li .goods-name_new { line-height:18px;height: 36px; width: 90%; overflow: hidden; margin: 8px auto;}
</style>
<script>
	//加载完毕后,查询用户是否登陆,如果登陆则改变页头状态
	$(function(){
		/*异步请求查询是否登陆*/
		var url = '${base}/index/getUsername';
		$.post(url, function(data){
			var result = data.result;
			$(".user-entry").empty();
			/*用户已经登陆*/
			if(result == 'true'){
				var memberName = data.memberName;
				$(".user-entry").append("您好<span>&nbsp;&nbsp;<a href='${base}/user/setting/index'>"+memberName+"</a></span>，欢迎来到"
				+"<a href='${base}'  title='首页' alt='首页'>${siteSet.siteName}</a><span>[<a href='${base}/logout'>退出</a>]</span>");
			}else{
				$(".user-entry").append("您好，欢迎来到<a href='${base}' title='首页' alt='首页'>${siteSet.siteName}</a>"
				+"<span>[<a href='${base}/login'>登录</a>]</span><span>[<a href='${base}/signUp'>注册</a>]</span>");
			}
			$(".user-entry").append("<span class='seller-login'>"
					+"<a href='${sellerServer}' title='商家中心' target='_blank'><i class='icon-signin'></i>商家管理中心</a></span>");
		});
	});
</script>
<div class="background clearfix">
<script type="text/javascript" src="${base}/res/js/shopstatgoods/shopstatgoods.js" ></script>
<article id="content">
	<!-- 导航条 begin-->
	<div class="nch-breadcrumb-layout">
	  <div class="nch-breadcrumb wrapper">
	  	<i class="icon-home"> </i>
	 	 	<span><a href="javascript:void(0);">首页</a></span>
	     	<#assign classNavTag =newTag("classNavTag")>
			<#assign nav =classNavTag("{'classId':'${goods.gcId}'}")>
			${nav}
			<span class="arrow">&gt;</span>
			<span class="arrow"><a href="${base}/product/detail?id=${goods.goodsId}">${goods.goodsName}</a></span>
		</div>
	</div>  
	<!-- 导航条 end-->  
	<section class="nc-detail">
	    <!-- <div class="nc-inform">
	    	<span>举报中心<i></i></span>
		    <ul>
		      <li><a href="javascript:void(0);" title="举报该商品">举报该商品</a></li>
		    </ul>
	 	</div> -->
        <div class="nc-detail-bd"> 
        	
        	<style type="text/css">
			.preview{width:390px; height:400px;}
			/* smallImg */
			.smallImg{position:relative; height:52px; margin-top:1px; background-color:#F1F0F0; padding:6px 5px; width:380px; overflow:hidden;float:left;}
			.scrollbutton{width:14px; height:50px; overflow:hidden; position:relative; float:left; cursor:pointer; }
			.scrollbutton.smallImgUp , .scrollbutton.smallImgUp.disabled{background:url(${base}/res/images/jaoben/images/d_08.png) no-repeat;}
			.scrollbutton.smallImgDown , .scrollbutton.smallImgDown.disabled{background:url(${base}/res/images/jaoben/images/d_09.png) no-repeat; margin-left:365px; margin-top:-50px;}
			
			#imageMenu {height:50px; width:360px; overflow:hidden; margin-left:0; float:left;}
			#imageMenu li {height:50px; width:60px; overflow:hidden; float:left; text-align:center;}
			#imageMenu li img{width:50px; height:50px;cursor:pointer;}
			#imageMenu li#onlickImg img, #imageMenu li:hover img{ width:44px; height:44px; border:3px solid #959595;}
			/* bigImg */
			.bigImg{position:relative; float:left; width:390px; height:400px; overflow:hidden;}
			.bigImg #midimg{width:390px; height:400px;}
			.bigImg #winSelector{width:235px; height:210px;}
			#winSelector{position:absolute; cursor:crosshair; filter:alpha(opacity=15); -moz-opacity:0.15; opacity:0.15; background-color:#000; border:1px solid #fff;}
			/* bigView */
			#bigView{position:absolute;border: 1px solid #959595; overflow: hidden; z-index:999;}
			#bigView img{position:absolute;}
			</style>
			<aside class="nc-gallery">
        	<!-- S 商品图片及收藏分享 -->
			<div class="preview">
				<div id="vertical" class="bigImg">
					<img src="${imgServer}${goods.goodsImage}" width="380" height="400" alt="" id="midimg" />
					<div style="display:none;" id="winSelector"></div>
				</div><!--bigImg end-->	
				<div class="smallImg">
					<div class="scrollbutton smallImgUp disabled"></div>
					<div id="imageMenu">
						<ul>
							<#assign goodsImgTag =newTag("goodsGallyTag")>
							<#assign goodsImgList =goodsImgTag("{'goodsid':'${goodsId}'}")>
			                <#if goodsImgList??>
				                <#list goodsImgList as il>
					                <#if il_index==0>
					                	<li id="onlickImg"><img src="${imgServer}${il}" width="68" height="68" alt=""/></li>
					                <#else>
										<li><img src="${imgServer}${il}" width="68" height="68" alt=""/></li>
									</#if>
								</#list>
							</#if>
						</ul>
					</div>
					<div class="scrollbutton smallImgDown"></div>
				</div><!--smallImg end-->	
				<div id="bigView" style="display:none;"><img width="800" height="800" alt="" src="" /></div>
			</div>
			</aside>
			<script src="${base}/res/js/layer/layer.js"></script>
			<script type="text/javascript">
			$(document).ready(function(){
				// 图片上下滚动
				var count = $("#imageMenu li").length - 5; /* 显示 6 个 li标签内容 */
				var interval = $("#imageMenu li:first").width();
				var curIndex = 0;
				
				$('.scrollbutton').click(function(){
					if( $(this).hasClass('disabled') ) return false;
					
					if ($(this).hasClass('smallImgUp')) --curIndex;
					else ++curIndex;
					
					$('.scrollbutton').removeClass('disabled');
					if (curIndex == 0) $('.smallImgUp').addClass('disabled');
					if (curIndex == count-1) $('.smallImgDown').addClass('disabled');
					
					$("#imageMenu ul").stop(false, true).animate({"marginLeft" : -curIndex*interval + "px"}, 600);
				});	
				// 解决 ie6 select框 问题
				$.fn.decorateIframe = function(options) {
			        if ($.browser.msie && $.browser.version < 7) {
			            var opts = $.extend({}, $.fn.decorateIframe.defaults, options);
			            $(this).each(function() {
			                var $myThis = $(this);
			                //创建一个IFRAME
			                var divIframe = $("<iframe />");
			                divIframe.attr("id", opts.iframeId);
			                divIframe.css("position", "absolute");
			                divIframe.css("display", "none");
			                divIframe.css("display", "block");
			                divIframe.css("z-index", opts.iframeZIndex);
			                divIframe.css("border");
			                divIframe.css("top", "0");
			                divIframe.css("left", "0");
			                if (opts.width == 0) {
			                    divIframe.css("width", $myThis.width() + parseInt($myThis.css("padding")) * 2 + "px");
			                }
			                if (opts.height == 0) {
			                    divIframe.css("height", $myThis.height() + parseInt($myThis.css("padding")) * 2 + "px");
			                }
			                divIframe.css("filter", "mask(color=#fff)");
			                $myThis.append(divIframe);
			            });
			        }
			    }
			    $.fn.decorateIframe.defaults = {
			        iframeId: "decorateIframe1",
			        iframeZIndex: -1,
			        width: 0,
			        height: 0
			    }
			    //放大镜视窗
			    $("#bigView").decorateIframe();
			    //点击到中图
			    var midChangeHandler = null;
				
			    $("#imageMenu li img").bind("click", function(){
					if ($(this).attr("id") != "onlickImg") {
						midChange($(this).attr("src").replace("small", "mid"));
						$("#imageMenu li").removeAttr("id");
						$(this).parent().attr("id", "onlickImg");
					}
				}).bind("mouseover", function(){
					if ($(this).attr("id") != "onlickImg") {
						window.clearTimeout(midChangeHandler);
						midChange($(this).attr("src").replace("small", "mid"));
						$(this).css({ "border": "3px solid #959595" });
					}
				}).bind("mouseout", function(){
					if($(this).attr("id") != "onlickImg"){
						$(this).removeAttr("style");
						midChangeHandler = window.setTimeout(function(){
							midChange($("#onlickImg img").attr("src").replace("small", "mid"));
						}, 1000);
					}
				});
			    function midChange(src) {
			        $("#midimg").attr("src", src).load(function() {
			            changeViewImg();
			        });
			    }
			    //大视窗看图
			    function mouseover(e) {
			        if ($("#winSelector").css("display") == "none") {
			            $("#winSelector,#bigView").show();
			        }
			        $("#winSelector").css(fixedPosition(e));
			        e.stopPropagation();
			    }
			    function mouseOut(e) {
			        if ($("#winSelector").css("display") != "none") {
			            $("#winSelector,#bigView").hide();
			        }
			        e.stopPropagation();
			    }
			    $("#midimg").mouseover(mouseover); //中图事件
			    $("#midimg,#winSelector").mousemove(mouseover).mouseout(mouseOut); //选择器事件
			
			    var $divWidth = $("#winSelector").width(); //选择器宽度
			    var $divHeight = $("#winSelector").height(); //选择器高度
			    var $imgWidth = $("#midimg").width(); //中图宽度
			    var $imgHeight = $("#midimg").height(); //中图高度
			    var $viewImgWidth = $viewImgHeight = $height = null; //IE加载后才能得到 大图宽度 大图高度 大图视窗高度
			
			    function changeViewImg() {
			        $("#bigView img").attr("src", $("#midimg").attr("src").replace("mid", "big"));
			    }
			    changeViewImg();
			    $("#bigView").scrollLeft(0).scrollTop(0);
			    function fixedPosition(e) {
			        if (e == null) {
			            return;
			        }
			        var $imgLeft = $("#midimg").offset().left; //中图左边距
			        var $imgTop = $("#midimg").offset().top; //中图上边距
			        X = e.pageX - $imgLeft - $divWidth / 2; //selector顶点坐标 X
			        Y = e.pageY - $imgTop - $divHeight / 2; //selector顶点坐标 Y
			        X = X < 0 ? 0 : X;
			        Y = Y < 0 ? 0 : Y;
			        X = X + $divWidth > $imgWidth ? $imgWidth - $divWidth : X;
			        Y = Y + $divHeight > $imgHeight ? $imgHeight - $divHeight : Y;
			
			        if ($viewImgWidth == null) {
			            $viewImgWidth = $("#bigView img").outerWidth();
			            $viewImgHeight = $("#bigView img").height();
			            if ($viewImgWidth < 200 || $viewImgHeight < 200) {
			                $viewImgWidth = $viewImgHeight = 800;
			            }
			            $height = $divHeight * $viewImgHeight / $imgHeight;
			            $("#bigView").width($divWidth * $viewImgWidth / $imgWidth);
			            $("#bigView").height($height);
			        }
			        var scrollX = X * $viewImgWidth / $imgWidth;
			        var scrollY = Y * $viewImgHeight / $imgHeight;
			        $("#bigView img").css({ "left": scrollX * -1, "top": scrollY * -1 });
			        $("#bigView").css({ "top": 10, "left": $(".preview").width() + 15 });
			
			        return { left: X, top: Y };
			    }
			   saveshopstatgoods('${goods.goodsId}','${goods.goodsName}','${goods.storeId}');
			});
			
			  
			  //修改商品收藏数量
			  function collect_goods(goodsId,favType,storeId){
			       var collectgoodscount='${goods.goodsCollect}';
			       $.ajax({
					    	url : "${base}/Favorite/SaveFavorite",
					        type : 'post',
					        data : {'goodsId':goodsId,'favType':favType,'storeId':storeId},
					        dataType : 'json',
					        success : function(data){
			                if(data.success){
			            	    layer.msg(data.msg,{icon:1});
			            	    //$("em").find("nctype:goods_collect").html(99);
			            	    $("em[nctype='goods_collect']").html(data.collectgoodcount);
			            	}else{
			            		layer.msg(data.msg,{icon:2});
			            	}
			            }
					 }); 
			  } 
			  
			  //修改商家收藏数量
			  function collect_store(goodsId,favType,storeId){
			       $.ajax({
					    	url : "${base}/Favorite/SaveFavorite",
					        type : 'post',
					        data : {'goodsId':goodsId,'favType':favType,'storeId':storeId},
					        dataType : 'json',
					        success : function(data){
			                if(data.success){
			            	    layer.msg(data.msg,{icon:1});
			            	    $("em[nctype='store_collect']").html(data.collectstorecount);
			            	}else{
			            		layer.msg(data.msg,{icon:2});
			            	}
			            }
					 }); 
			  } 
			  
			</script>
			
        	<!-- E 商品图片及收藏分享 --> 
        	<!-- S 商品基本信息 -->
        
	        <article class="ncs-goods-summary">
				<div class="name">
					<input type="hidden" id="goodsId" value="${goodsId}"/>
					<input type="hidden" id="goodsSpecId" value=""/>
			        <h1>${goods.goodsName}</h1>
			        <strong>${goods.goodsSubtitle}</strong>
		        </div>
	          	<div class="nc-meta"> 
		            <!-- S 商品发布 -->
		            <dl class="nc-detail-price ">
		              <dt>价&#12288;&#12288;格：</dt>
		              <dd>
		              	<strong nctype="goods_price">&yen;${goods.goodsStorePrice}<#if rate!=''>（&#36;${(goods.goodsStorePrice/(rate?number))?string("0.##")}）</#if></strong>
		              </dd>
		            </dl>
		            <dl>
		            	<dt>分&#12288;&#12288;享：</dt>
		            	<dd style="margin-top: 5px;">
		            		<div class="jiathis_style" style="right: 0;">
								<a class="jiathis_button_icons_1"></a>
								<a class="jiathis_button_icons_2"></a>
								<a class="jiathis_button_icons_3"></a>
								<a class="jiathis_button_icons_4"></a>
								<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>
							</div>
							<script type="text/javascript" src="http://v3.jiathis.com/code_mini/jia.js" charset="utf-8"></script>
		            	</dd>
		            </dl>
		            <!-- E 商品发布价格 --> 
		            <!-- S 物流运费 -->
		            <dl class="ncs-freight">
		              <dt>运&#12288;&#12288;费：</dt>
		              <dd id="transport_price">
		                <#if goods.goodsTransfeeCharge==1>
		              		商家承担运费
		              	<#else>
		              		会员承担运费
		              	</#if>
		              
		              </dd>
		              <dd style="color:red;display:none" id="loading_price">loading.....</dd>
		            </dl>
		           
		            <!-- S 商品规格值-->
		            <#assign goodsSpecTag =newTag("goodsSpecTag")>
					<#assign goodsSpecObj =goodsSpecTag("{'goodsid':'${goodsId}'}")>
					 <!--S 商家信息-->
					<#assign storeInfoTag =newTag("storeInfoTag")>
					<#assign storeinfo =storeInfoTag("{'storeId':'${goods.storeId}'}")>
					 <!--根据商家id取评分内容-->
					<#assign evaluateStorebyStoreIdTag =newTag("evaluateStorebyStoreIdTag")>  
					<#assign evaluateStore =evaluateStorebyStoreIdTag("{'storeId':'${goods.storeId}'}")>
					 <!-- 判断是否下架 -->
		            <#if goods.goodsShow != 0>
		            <!-- E 物流运费 ---> 
					<ul class="tm-ind-panel">
						<!-- S 累计售出数量 -->
						<li class="tm-ind-item tm-ind-sellCount">
							<p class="tm-count">
								<em><a href="#ncGoodsTraded">${goods.salenum}</a></em>
							</p>
							<p class="tm-label">累计售出</p>
						</li>
						<!-- E 累计售出数量 -->
						<!-- S 描述相符评分及评价数量 -->
						<li class="tm-ind-item tm-ind-reviewCount tm-line3" id="J_ItemRates">
							<p class="tm-count" id="comment_num"><em>${goods.commentnum}</em></p>
							<p class="tm-label">条评论</p>
							<p class="raty ra" data-score="0"></p>
						</li>
						<!-- E 描述相符评分及评价数量 --> 
						<!-- S 商品类型及浏览数-->
						<li class="tm-ind-item tm-ind-emPointCount">
							<a target="_blank">
								<p class="tm-count">
									<em>
									<#if goods.goodsForm==1>
						              全新
						            <#else>
						              二手
						            </#if>
									</em>
								</p>
								<p class="tm-label">商品类型</p>
							</a>
						</li>
					</ul>
		            <!-- E 商品类型及浏览数-->
				</div>
				<div class="nc-key"> 
		          	<div class="nc-spec">
		            
					
					<#if goodsSpecObj != null>
		            <#assign nameMap = goodsSpecObj.get("specname")>
		            <#assign valueMap = goodsSpecObj.get("specvalue")>
		            <#assign goodsSpecs = goodsSpecObj.get("goodsSpecs")>
		            <#assign goodsColImg = goodsSpecObj.get("goodsColImg")>
		            
			            <#if nameMap??>
				            <#list nameMap.keySet() as key>
					            <dl>
						            <dt>${nameMap.get(key)}：</dt>
						            <dd>
							            <ul nctyle="ul_sign">
								            <!-- 文字类型规格-->
								            <#if valueMap??>
								            	<#list valueMap[key] as goodsSpecMapValues>
									            	<li class="sp-txt">
											            	<a href="javascript:void(0)" spId="${key}" spValueId="${goodsSpecMapValues.spValueId}" onClick="selectSpec('${goodsSpecMapValues.spId}', this, '${goodsSpecMapValues.spValueId}')" class="">
											            	${goodsSpecMapValues.spValueName}
											            		<#if key==1>
											            			<#if goodsColImg?? && goodsColImg!''>
											            				<#list goodsColImg?keys as goodsColImgKey>
											            					<#if goodsSpecMapValues.spValueId==goodsColImgKey>
											            						<img alt="" src="${imgServer}${goodsColImg[goodsColImgKey]}" style="height: 25px;width: 25px;">
											            					</#if>
											            				</#list>
											            			</#if>
											            		</#if>
											            	<i></i>
										            	   </a>
									            	</li>
									        	 </#list>
			            					</#if>
							            </ul>
						            </dd>
					            </dl>
					        </#list>
			            </#if>
		            </#if>
		            <!-- E 商品规格值--> 
		            </div>
		            <!-- S 购买数量及库存 -->
		            <dl>
		           	<dt>购买数量：</dt>
					<dd class="nc-figure-input">
						<a href="javascript:void(0)" onclick="caculate('cut');">
							<i class="icon-minus decrease fl text-hidden"></i>
						</a>
						<input type="text" name="" id="quantity" value="1" size="3" maxlength="6" class="fl" style="border-radius:0;">
						<a href="javascript:void(0)" onclick="caculate('add');">
							<i class="icon-plus increase fl text-hidden"></i>
						</a> 
		                <em class="fl ml20">
			                (当前库存<strong nctype="goods_stock" id="stockGoods">22</strong>件)
		                </em> 
					</dd>
					<div class="clear"></div>
		            </dl>
		            <!-- E 购买数量及库存 --> 
		            <!-- S 提示已选规格及库存不足无法购买 -->
		            <dl class="nc-point" nctype="goods_prompt" style="display:none;"></dl>
		            <!-- E 提示已选规格及库存不足无法购买 -->
					<div class="clear"></div>
		            <!-- S 购买按钮 -->
		            <div class="nc-btn clearfix">
						<a href="javascript:buynow('');" class="buynow fl text-hidden" title="立即购买">立即购买</a><!-- 立即购买--> 
						<a href="javascript:buy('');" class="addcart fl ml10 text-hidden" title="添加到购物车">添加到购物车<!-- 加入购物车--></a> 
		              	<!-- S 加入购物车弹出提示框 -->
		              	<div class="ncs_cart_popup">
		                	<dl>
		                  		<dt>
				                    <h3>成功添加到购物车</h3>
				                    <a title="关闭" onClick="$('.ncs_cart_popup').css({'display':'none'});">关闭</a>
								</dt>
		                  		<dd>
		                    		<p class="mb5">购物车共有 <strong id="bold_num"></strong> 种商品 总金额为：<em id="bold_mly" class="price"></em></p>
				                    <p>
				                      <input type="submit" class="btn1" name="" value="查看购物车" onClick="javascript:void(0);"/>
				                      <input type="submit" class="btn2" name="" value="继续购物" onClick="$('.ncs_cart_popup').css({'display':'none'});"/>
				                    </p>
		                  		</dd>
		                	</dl>
						</div>
		              	<!-- E 加入购物车弹出提示框 -->
					</div>
					<!-- E 购买按钮 -->
					<div class="clear"></div>
				</div>
				<!-- S 收藏 -->
				<div class="ncs_share" style="float:none;">
				  <div class="ncs-goods-handle fl">
				    <div class="handle-left"><i class="share-goods"></i><a href="javascript:void(0)" onclick="collect_goods('${goods.goodsId}','1','${goods.storeId}');">收藏商品</a><em nctype="goods_collect">${goods.goodsCollect}</em></div>
				    <div class="handle-right" id="handle-r"><span></span>
				      <ul >
				        <li class="tab"><span></span></li>
				        <li><i class="share-store"></i><a href="javascript:void(0)" onclick="collect_store('-9','2','${goods.storeId}');" >收藏商家<em nctype="store_collect">${storeinfo.storeCollect}</em></a></li>
				      </ul>
				    </div>
				  </div>
				   <div class="clear"></div>
				</div>
				<!-- E 收藏 --> 
	        </article>
	        <!--E 商品信息 --> 
        			<!-- 判断是否下架 -->
		<#else>
			<dl class="nsg-handle">
            <dt>商品已下架</dt>
            <dd>1.&nbsp;请联系卖家咨询</dd>
            <dd>2.&nbsp;逛逛<!--<a href="${base}/store/shop?storeId=${goods.storeId}">商家商家</a>-->选择其他商品 </dd>
          </dl>
		</#if>
			<div class="ncg-store"style=" position: absolute; z-index: 1; top: 60px; right: 0;">
				<div class="title">商家信息</div>
			    <div class="content">
			        <div class="ncg-store-info">
			          <dl class="name">
			            <dt>店　　铺：</dt>
			            <dd>${storeinfo.storeName}</dd>
			          </dl>
			          <dl class="all-rate">
			            <dt>综合评分：</dt>
			            <dd>
			              <div class="rating">
			              	<span style="width:%;" id="allrate"></span>
			              </div>
			              <em>${evaluateStore.averageCredit!'0'}</em>分 
			              <script>
			              	var num = ${evaluateStore.averageCredit!'0'};
			              	var rate = (num*100)/5;
			              	$("#allrate").css("width", rate + "%");
			              </script>
			             </dd>
			          </dl>
					  
			          <div class="detail-rate">
			            <h5><strong>商家动态评分</strong></h5>
			            <ul class="rate">
			              <li>
			              		描述相符：
			              		<span class="credit ra" id="descraty" data-score="${evaluateStore.sevalDesccredit}"></span>
			              </li>
			              <li>
			              		服务态度：
			              		<span class="credit ra"   data-score="${evaluateStore.sevalServicecredit}"></span>
			              </li>
			              <li>
			              		发货速度：
			              		<span class="credit ra" data-score="${evaluateStore.sevalDeliverycredit}"></span>
			              </li>
			            </ul>
			          </div>
			          
			          <dl class="messenger">
			            <dt>在线客服：</dt>
						<dd>     
							<!--<a target="_blank" href="${base}/easemob/msgview?touser=${goods.storeId}" title="1234567">
								<img border="0" src="http://wpa.qq.com/pa?p=2:1234567:52" style=" vertical-align: middle;">
							</a> -->
							<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=6031335&site=qq&menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:6031335:52" alt="点击这里给我发消息" title="点击这里给我发消息"/></a>
			      		</dd>
			          </dl>
			          <div class="goto" id="shop-other"> 
		          	  		<!--<a href="${base}/store/shop?storeId=${goods.storeId}" target="_blank">进入商家</a>-->
			          </div>
			        </div>
				</div>
		    </div>
		    <!--E 商家信息 --> 
		</div>
    	<div class="clear"></div>
	</section>

    <section class="layout expanded nc-main" >
		<article class="nc-goods-main">
			<section class="nc-promotion"style="display:none;">
	  			<nav class="tabbar pngFix">
          			<div class="pr">
			            <ul>
			              <li class="current"><a href=""><span>组合销售</span></a></li>
			            </ul>
					</div>
        		</nav>
			    <!--S 组合销售 -->
			    <div class="nc-bundling" id="nc-bundling" style="display:none;"></div>
			    <!--E 组合销售 -->
			</section>
    		
   		<section class="nc-s-c-s2 ncg-com-list">
          <div class="title">
            <h4>组合商品</h4>
          </div>
       	<#assign goodsCombinationTag =newTag("goodsCombinationTag")>
		<#assign goodsCombinations =goodsCombinationTag("{'goodsid':'${goodsId}'}")>
          <div class="content" style="overflow:auto;">
            <ul>
            	<#if goodsCombinations != null && goodsCombinations?size gt 0>
	            	<#list goodsCombinations as goodsCombination>
		                <li>
						<dl>
							<dt class="goods-name"><a href="${base}/product/detail?id=${goodsCombination.combinationGoodsId}" target="_blank" title="">${goodsCombination.combinationGoodsName}<em></em></a></dt>
							<dd class="goods-pic">
								<a href="${base}/product/detail?id=${goodsCombination.combinationGoodsId}" target="_blank" title="">
									<img src="${imgServer}/${goodsCombination.combinationGoodsImg}" alt="${goodsCombination.combinationGoodsName}" style="display: inline;">
								</a>
							</dd>
							<dd class="goods-price">价&nbsp;格：<em class="price">&yen;${goodsCombination.combinationGoodsPrice}</em></dd>
						</dl>
					   </li>
				   </#list>
			   </#if>
             </ul>
            <div class="clear"></div>
          </div>
        </section>
    		
			<div  id="main-nav-holder">
				<nav class="tabbar pngFix" id="main-nav">
					<div class="pr" style="z-index: 70;">
					  <ul id="categorymenu">
					    <li class="current"><a name="table" toAction="goodsBody" toTable="#table1" href="javascript:void(0);" ><span>商品详情</span></a></li>
					    <li><a name="table" toAction="evaluateGoods" toTable="#table2" href="javascript:void(0);"><span>商品评论</span></a></li>
					    <li><a name="table" toAction="saleRecord" toTable="#table3" href="javascript:void(0);"><span>销售记录</span></a></li>
					    <li><a name="table" toAction="consult" toTable="#table4" href="javascript:void(0);"><span>商品咨询</span></a></li>
					  </ul>
					  <div class="switch-bar"><a href="javascript:void(0)" id="abc">&nbsp;</a></div>
					  <div class="gotop"><a href="#top">&nbsp;</a></div>
					</div>
		        </nav>
		        <section class="nc-s-c-s4 ncg-intro" name="goodsTable" id="table1">
		        </section>
		        
		       	<section class="nc-s-c-s4 ncg-comment" name="goodsTable" id="table2">
		        </section>
		        
		       	<section class="nc-s-c-s4 ncg-salelog" name="goodsTable" id="table3">
		        </section> 
		        
		       	<section class="nc-s-c-s4 ncg-guestbook" name="goodsTable" id="table4">
		        </section> 
		        
		        <section class="nc-s-c-s2 ncg-com-list">
		          <div class="title">
		            <h4>推荐商品</h4>
		          </div>
		          <div class="content">
		            <ul>
		            <#assign goodsSearchTag = newTag("goodsSearchTag")>
					<#assign lucenePage = goodsSearchTag("{'searchType':'gcIdSearch','keyword':'${goods.gcId}','pageNo':'1','pagesize':'5'}")>
					<#if lucenePage!="" && lucenePage??>
						<#if lucenePage.result??>
							<#list lucenePage.result as recommengoods>
			                <li>
							<dl>
								<dt class="goods-name_new">
									<a href="${base}/product/detail?id=${recommengoods.goodsId}" target="_blank" title="${recommengoods.goodsName}" target="_blank">
										${recommengoods.goodsName}<em></em>
									</a>
								</dt>
								<dd class="goods-pic">
									<a href="${base}/product/detail?id=${recommengoods.goodsId}" target="_blank" title="">
										<img src="<#if recommengoods.goodsImage??>${imgServer}${recommengoods.goodsImage}<#else>${base}/res/images/common/default_goods_image.gif_small.gif</#if>" alt="${recommengoods.goodsName}">
									</a>
								</dd>
								<dd class="goods-price">
									商&nbsp;城&nbsp;价：<em class="price">&yen;${recommengoods.goodsStorePrice}</em>
								</dd>
									
							</dl>
						   	</li>
						   	</#list>
						</#if>
					</#if>
		             </ul>
		            <div class="clear"></div>
		          </div>
		        </section>
			  </div>
		</article>
		<aside class="nc-sidebar">
			<!-- <div class="nc-s-c-s1">
		        <div class="title">
		          <h4>商品二维码</h4>
		        </div>
		        <div class="content">
		          <div class="ncs-goods-code">
			          <p><img src="" width='150' height='150' alt='李宁运动cgl'></p> 
					  <span class="ncs-goods-code-note"><i></i>扫描二维码，手机查看分享</span> 
					</div>         
			    </div>
			</div> -->
	        
			<!-- <div class="nc-s-c-s1 ncs-message-bar">
				<div class="title">
					<h4>客服中心</h4>
				</div>
			  	<div class="content">
				</div>
			</div> -->        
			
			
			<!-- 店内搜索，商品分类，商品排行 -->  
			<#import "/commons/shop_left.ftl" as shopleft>
			<@shopleft.left storeId='${goods.storeId}' />
			
		</aside>
  		<div class="clear"></div>
   	</section>
</article>
<div class="clear"></div>
<!-----footer------>
<@p.footer />

<script type="text/javascript">
/* spec对象 */
function spec(id, spec, price, stock){
    this.id    = id;
    this.spec  = spec;
    this.price = price;
    this.stock = stock;
}
/* goodsspec对象 */
function goodsspec(specs, specQty, defSpec){
    this.specs = specs;
    this.specQty = specQty;
    this.defSpec = defSpec;
    this.spec1 = null;
    this.spec2 = null;
    this.spec3 = null;
    this.spec4 = null;
    if (this.specQty >= 1){
        for(var i = 0; i < this.specs.length; i++){
            if (this.specs[i].id == this.defSpec){
                  this.spec1 = this.specs[i].id;
                  this.spec2 = this.specs[i].spec;
                  this.spec3 = this.specs[i].price;
                  this.spec4 = this.specs[i].stock;
                  break;
            }
        }
    }


    // 取得选中的spec
    this.getSpec = function(){
        for (var i = 0; i < this.specs.length; i++){
              if (this.specs[i].spec != this.spec2) continue;
              return this.specs[i];
        }
        return null;
    }

}

var specs = new Array();
var source_goods_price = '${goods.goodsStorePrice}';
var specQty;
var defSpec = '${goods.specId}';
var goodsspecc;

//异步请求获得sku信息
function getSku(callbackfunction){
	var url = "${base}/product/goodsSpecs";
	var args = {"goodsId":$("#goodsId").val()};
	$.post(url, args, function(data){
		//goodsSpecs是个list
		var goodsSpecs = data.goodsSpecs;
		specs = new Array();
		for(var i = 0; i < goodsSpecs.length; i++){
			var goodsSpec = goodsSpecs[i];
			//alert(goodsSpec.specGoodsStorage);
			//alert(goodsSpec.specValueIdStr);
			if(goodsSpec.specValueIdStr==null){
				specs.push(new spec(goodsSpec.goodsSpecId, 
						"",
						goodsSpec.specGoodsPrice, 
						goodsSpec.specGoodsStorage));
			}else{
				specs.push(new spec(goodsSpec.goodsSpecId, 
						goodsSpec.specValueIdStr.split(","), 
						goodsSpec.specGoodsPrice, 
						goodsSpec.specGoodsStorage));
			}
			
		}
		specQty = goodsSpecs.length;
		goodsspecc = new goodsspec(specs, specQty, defSpec);
		if(callbackfunction != undefined){
			callbackfunction();
		}
	});
}

function init(){
	getSku(function(){
		var tatolStorage = 0;
		//alert(specQty);
		//计算总库存
		if(specQty==1){
			//alert(specs[0].stock);
			tatolStorage = specs[0].stock;
		}else{
			 for(var i = 0; i < specQty; i++){
				//alert("spec:"+specs[i]);
				//alert("stock:"+specs[i].stock);
				tatolStorage += parseInt(specs[i].stock);
			}
		}
		
		$("[nctype=goods_stock]").html(tatolStorage);
		});
	
	//异步地址
	var url = '${base}/product/goodsBody';
	//参数
	var agrs = {
			"goodsId":$("#goodsId").val()
	};
	//异步加载table
	$.post(url, agrs, function(data){
		$("#table1").append(data);
	});
}
//切换table
function changeTable(obj,pageNo){
	var tableId = $(obj).attr("toTable");
	var toAction = $(obj).attr("toAction");
	
	//清空所有table
	$("[name=goodsTable]").each(function(){
		//取消所有table标题按钮颜色
		$("#categorymenu").find("li").each(function(){
			$(this).removeClass("current");
		});
		$(this).empty();
	});
	
	//设置当前table标题按钮class
	$(obj).parent().attr("class", "current");
	
	//异步地址
	var url = '${base}/product/' + toAction;
	
	
	//参数
	if(pageNo !=''){
		var agrs = {
				"goodsId":$("#goodsId").val(),
				"pageNo":pageNo
		};
	}else{
		var agrs = {
				"goodsId":$("#goodsId").val()
		};
	}
	//异步加载table
	$.post(url, agrs, function(data){
		$(tableId).append(data);
	});
}
$(function(){
	//初始化
	init();
	//当点击table需要切换的时候
	$("[name=table]").click(function(){
		changeTable(this);
	});
	saveGoodsCookie();//调用保存cookie的方法
});


/* 选中某规格 num=1,2 */
function selectSpec(num, liObj, SID){
	getSku();
	//选择颜色规格的时候,颜色图片会再左边的图片框改变
	if($(liObj).attr("spId") == "1"){
		$("#bigImage").attr("src",$(liObj).children().attr("src"));
	}
    $(liObj).addClass("hovered");
    $(liObj).parents('li').siblings().find('a').removeClass("hovered");
    var sign = 't';
    var spValueId = "";
    $('ul[nctyle="ul_sign"]').each(function(){
		if($(this).find('.hovered').html() == null ){
			sign = 'f';
		}
		spValueId += $(this).find('.hovered').attr("spValueId") +",";
    });
	spValueId = spValueId.substring(0, spValueId.length-1);
	goodsspecc['spec2'] = spValueId;
	var spec = goodsspecc.getSpec();
    if (spec != null && sign == 't'){
        $('[nctype="goods_price"]').html("&yen;"+spec.price);
        //限时折扣价格
         $('[nctype=goods_stock]').html(spec.stock);
        if(parseInt(spec.stock) == 0){
        	$('[nctype="goods_prompt"]').show().html('<dt>提示：</dt><dd><em class="no fl">所选规格库存量不足，请选择其它规格购买。</em></dd>');
        }else{
            SP_V = '';
            $('ul[nctyle="ul_sign"]').find('li > .hovered').each(function(i){
				SP_V += $(this).text()+'，';
            });
            SP_V = SP_V.substr(0,SP_V.length-1);
        	$('[nctype="goods_prompt"]').show().html('<dt>提示：</dt><dd><em class="yes fl">您选择了：'+SP_V+'</em></dd>');
        	$("#goodsSpecId").attr("value", spec.id);
        }
     }
}


function saveGoodsCookie(){
      //保存商品的cookie信息
	 var goodsId='${goods.goodsId}';
	 var goodsName='${goods.goodsName}';
	 var goodsImage='${goods.goodsImage}';
	 var goodsStorePrice='${goods.goodsStorePrice}';
	 
	 
	 //获取用户历史浏览记录，如果已经存在浏览历史，则分析历史记录的cookie信息（JSON数据格式），获取记录长度。
	 var canAdd = true; //初始可以插入cookie信息 
	 var hisGoods = $.cookie("hisGoods"); 
	 var len = 0; 
	 if(hisGoods){ 
	    hisGoods = eval("("+hisGoods+")"); 
	    len = hisGoods.length; 
	 } 
	 
	 //如果浏览历史cookie信息已经存在，则遍历cookie信息，对比当前文章标题，如果cookie信息中已经存在当前文章标题，则中止程序，不做任何操作
	 $(hisGoods).each(function(){
	    if(this.goodsId == goodsId){
	        canAdd = false; //已经存在，不能插入
	        return false;
	    } 
      });
	// 如果浏览历史cookie中不存在当前文章，则可以像浏览历史cookie中插入当前文章的cookie信息。
	//此时需要构建json数据，将已有的浏览记录cookie和当前页面的cookie信息已经组合成JSON数据，然后通过$.cookie()方法写入到浏览历史记录中
	if(canAdd==true){ 
		var json = "["; 
		var start = 0; 
	    if(len>4){start = 1;} 
	    for(var i=start;i<len;i++){ 
	        json = json + "{\"goodsId\":\""+hisGoods[i].goodsId+"\",\"goodsName\":\""+hisGoods[i].goodsName+"\",\"goodsImage\":\""+hisGoods[i].goodsImage+"\",\"goodsStorePrice\":\""+hisGoods[i].goodsStorePrice+"\"},"; 
	    } 
	    json = json + "{\"goodsId\":\""+goodsId+"\",\"goodsName\":\""+goodsName+"\",\"goodsImage\":\""+goodsImage+"\",\"goodsStorePrice\":\""+goodsStorePrice+"\"}]";
	    $.cookie("hisGoods",json,{expires:15,path:'/'});
	}
	
	//5、接下来，我们就要在需要展示用户浏览历史记录的cookie信息。在本例对应的demo页面，首先要获取浏览历史cookie：
	//hisArt的值，然后分析，遍历，组合成字符串输出到页面，代码如下：
}

$("#quantity").change(function(){
	var num = $(this).val();
	if(isNaN(num)){
		$(this).attr("value",1);
	}else{
		var count = parseInt(num);
		var stock = parseInt($("[nctype=goods_stock]").html());
		if(count > 100){ //购买商品数量上限不能超过100
			$(this).attr("value",100);
		}else if(count > stock){
			$(this).attr("value",stock);
		}else if(count <= 0){
			$(this).attr("value",1);
		}
	}
});

//加减
function caculate(fun){
	var num = $("#quantity").val();
	if(isNaN(num)){
		$("#quantity").attr("value","1");
	}else{
		var count = parseInt(num);
		if(fun == "add"){
			if(count>100){ //购买商品数量上限不能超过100
				$("#quantity").attr("value",100);
			}else if(parseInt($("[nctype=goods_stock]").html()) == count){
				
			}else{
				$("#quantity").attr("value",count);
			}
		}else if(fun == "cut"){
			if(count == 1){
			}else{
				$("#quantity").attr("value",count);
			}
		}
	}

}
function isPositiveNum(s){//是否为正整数  
   var re = /^[0-9]*[1-9][0-9]*$/ ;  
   return re.test(s)  
} 
// 商品规格选择js部分
var SITE_URL = "${base}";
function buy(type){
	var ncspec = $(".nc-spec").find("dl").html(); //判断商品是否存在规格
    var goodsId = $("#goodsId").val(); //商品id
    var goodsNum = parseInt($("#quantity").val()); //商品数量
    var specId = $("#goodsSpecId").val(); //商品规格id
    if (typeof(ncspec) == "undefined"){ //若商品没有规格,则将默认规格值存入
    	specId = ${goods.specId};
    }
    if(!isPositiveNum(goodsNum)){
    	layer.msg("请填写正确的购买数量" , {icon:2,time:1000});
        $("#quantity").val('1');
        return;
    }
    max = parseInt($('[nctype="goods_stock"]').text());
    if(goodsNum > max){
    	layer.msg("库存不足,请您重新选择商品数量" , {icon:2,time:1000});
    	return;
    }
    if(specId != ''){
    	$.ajax({
	    	url : "${base}/cart/saveCart",
	        type : 'post',
	        data : {'goodsId' : goodsId,'count' : goodsNum, 'specId' : specId},
	        dataType : 'json',
	        success : function(data){
	        	if(data.success=='true'){
	        		layer.msg("加入购物车成功",{icon:1,time:1000});
	        		var num = $(".addcart-goods-num").html(); //右侧购物车原有数量
	        		//更新购物车数量
	        		$(".addcart-goods-num").html(goodsNum*10/10+num*10/10);
	        	}else{
	        		layer.msg(data.msg,{icon:2,time:1000});
	        	}
	        }
	    });
    }else{
    	layer.msg("请选择商品规格",{icon:2,time:1000});
    }
   	
}

function class_list(obj){
	var stc_id=$(obj).attr('span_id');
	var span_class=$(obj).attr('class');
	if(span_class=='ico-block') {
		$("#stc_"+stc_id).show();
		$(obj).html('<em>-</em>');
		$(obj).attr('class','ico-none');
	}else{
		$("#stc_"+stc_id).hide();
		$(obj).html('<em>+</em>');
		$(obj).attr('class','ico-block');
	}
}
    //浮动导航  waypoints.js
   /*  $('#main-nav-holder').waypoint(function(event, direction) {
        $(this).parent().toggleClass('sticky', direction === "down");
        event.stopPropagation();
    });  */
    
// 立即购买js
function buynow(type){
	var ncspec = $(".nc-spec").find("dl").html(); //判断商品是否存在规格
    var goodsId = $("#goodsId").val(); //商品id
    var goodsNum = parseInt($("#quantity").val()); //商品数量
    var specId = $("#goodsSpecId").val(); //商品规格id
    if (typeof(ncspec) == "undefined"){ //若商品没有规格,则将默认规格值存入
    	specId = ${goods.specId};
    }
    if(!isPositiveNum(goodsNum)){
    	layer.msg("请填写正确的购买数量" , {icon:2,time:1000});
        $("#quantity").val('1');
        return;
    }
    max = parseInt($('[nctype="goods_stock"]').text());
    if(goodsNum > max){
    	layer.msg("库存不足,请您重新选择商品数量" , {icon:2,time:1000});
    	return;
    }
    if(specId != ''){
    	if(isLogin){ //判断是否登录
	   		$.ajax({
		    	url : "${base}/cart/buyNow",
		        type : 'post',
		        data : {'goodsId' : goodsId,'count' : goodsNum, 'specId' : specId},
		        dataType : 'json',
		        success : function(data){
		        	if(data.success=='true'){
		        		window.location = "${base}/cart/gotoOrder?cartIds="+data.cartIds;
		        	}else{
		        		layer.msg(data.msg,{icon:2,time:1000});
		        	}
		        }
		    });
		}else{
    		location.href="${base}/login";
    	}
    }else{
    	layer.msg("请选择商品规格",{icon:2,time:1000});
    }
}
/* $(function(){
    //选择地区查看运费
    $('#transport_pannel>a').click(function(){
    	var id = $(this).attr('nctype');
    	if (id=='undefined') return false;
    	var _self = this,tpl_id = '0';
	    var url = 'index.php?act=goods&op=calc&rand='+Math.random();
	    $('#transport_price').css('display','none');
	    $('#loading_price').css('display','');
	    $.getJSON(url, {'id':id,'tid':tpl_id}, function(data){
	    	if (data == null) return false;
	        if(data.kd != 'undefined') {$('#nc_kd').html(data.kd);}else{$('#nc_kd').html('');}
	        if(data.py != 'undefined') {$('#nc_py').html(data.py);}else{$('#nc_py').html('');}
	        if(data.es != 'undefined') {$('#nc_es').html(data.es);}else{$('#nc_es').html('');}
	        $('#transport_price').css('display','');
	    	$('#loading_price').css('display','none');
	        $('#ncrecive').html($(_self).html());
	    });
    });
   	$("#nc-bundling").load('index.php?act=goods&op=get_bundling&goods_id=95&id=3');
  	$("#goodseval").load('index.php?act=goods&op=comments&goods_id=95&id=3');
	$("#salelog_demo").load('index.php?act=goods&op=salelog&goods_id=95&id=3');
	$("#cosulting_demo").load('index.php?act=goods&op=cosulting&goods_id=95&id=3');
}); */
</script> 
<script type="text/javascript" src="${base}/res/js/jquery.raty/jquery.raty.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $('#raty').raty({
            path: "${base}/res/js/jquery.raty/img",
			readOnly: true,
            width: 80,
            score: function() {
          return $(this).attr('data-score');
        }
        });
    });
</script>
<script>
    //收藏分享处下拉操作
    jQuery.divselect = function(divselectid,inputselectid) {
      var inputselect = $(inputselectid);
      $(divselectid).mouseover(function(){
          var ul = $(divselectid+" ul");
          ul.slideDown("fast");
          if(ul.css("display")=="none"){
              ul.slideDown("fast");
          }
      });
      $(divselectid).live('mouseleave',function(){
          $(divselectid+" ul").hide();
      });
    };
	$(function(){
	    // 加入购物车
	    $('a[nctype="addcart_submit"]').click(function(){
	        addcart(146, checkQuantity());
	    });
	    // 立即购买
	    $('a[nctype="buynow_submit"]').click(function(){
	        buynow(146,checkQuantity());
	    });
	
	    //浮动导航  waypoints.js
	    $('#main-nav').waypoint(function(event, direction) {
	        $(this).parent().parent().parent().toggleClass('sticky', direction === "down");
	        event.stopPropagation();
	    });
	
	    // 分享收藏下拉操作
	    $.divselect("#handle-l");
	    $.divselect("#handle-r");
	
	    // 规格选择
	    $('dl[nctype="nc-spec"]').find('a').each(function(){
	        $(this).click(function(){
	            if ($(this).hasClass('hovered')) {
	                return false;
	            }
	            $(this).parents('ul:first').find('a').removeClass('hovered');
	            $(this).addClass('hovered');
	            checkSpec();
	        });
	    });
	});
</script>

<script>
	$(document).ready(function() {
		$(".credit").raty({
			path : "${base}/res/js/jquery.raty/img",
			hints : [ '一般', '不错', "很好", '满意', '非常满意' ],
			readOnly : true,
			width : 100,
			score : function() {
				return $(this).attr("data-score")
			}
		})
	});
</script>
<script type="text/javascript" src="${base}/res/js/waypoints.js"></script> 

