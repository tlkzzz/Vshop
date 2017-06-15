
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>会员个人中心</title>
<meta name="viewport" content="width=device-width,inital-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta name="apple-wap-web-app-capable" content="yes" />
<meta name="apple-wap-web-app-status-bar-style" content="black-translucent" />
<link href="${base}/res/html5/css/style.css" rel="stylesheet" type="text/css" />
<link href="${base}/res/html5/css/resetn.css" rel="stylesheet" type="text/css" />
<script src="${base}/res/html5/js/jquery-1.10.2.min.js"></script>
<script src="${base}/res/html5/js/zepto.min.js"></script>
<script type="text/javascript" src="${base}/res/js/common.js" charset="utf-8"></script>
<link href="${base}/res/html5/css2/my.css" rel="stylesheet" type="text/css" />
</head>

<body>
<#assign memberInfoTag =newTag("memberInfoTag")>
<#assign member =memberInfoTag("")>
<div id="wrap">
			<header>
				<div >
					<img src="${base}/res/html5/images/img3.png" alt="banner" class="banner"/>	
				</div>
				<div class="in" >
					
					<dl>
						<dt class="swing">
						<#-- 
							<img src="${base}/res/html5/images/img4.png" alt="" />
							-->
							<p>
							<#if member.memberAvatar??>
			            		<img src="${member.memberAvatar}" />
			            	<#else>
			            		<img src="upload/common/default_user_portrait.gif" />
			            	</#if>
							</p>
						</dt>
						<dd>
								<h3>${member.memberName}</h3>
					
					<#--
							<p>
								<h3>电话号：${member.memberMobile}</h3>
							</p>
							<p>
								<h4>地址：${member.memberAreainfo}</h4>
							</p>
							-->
	     	            </dd>
					</dl> 
					
				</div>
			</header>
		
			<div class="main">
				<ul>
					<li>
					<div style="width:100%;">
						<a href="${base}/m/authc/buyer/orderList?orderState=10">
							<h4 style="float:left;">
								<img src="${base}/res/html5/images/img5.png" alt="" />
								<p>未付款</p>
							</h4>
							<i style="float:right;">
								<img src="${base}/res/html5/images/right.png" alt="" />
							</i>
							</a>
						</div>
					</li>
					<li>
						<div style="width:100%;">
							 <a href="${base}/m/authc/buyer/orderList?orderState=20">
							<h4 style="float:left;">
								<img src="${base}/res/html5/images/img6.png" alt="" />
								<p>未发货</p>
							</h4>
							<i style="float:right;">
								<img src="${base}/res/html5/images/right.png" alt="" />
							</i>
							</a>
						</div>
					</li>
					<li>
						<div style="width:100%;">
						 <a href="${base}/m/authc/buyer/orderList?orderState=30">
							<h4 style="float:left;">
								<img src="${base}/res/html5/images/img7.png" alt="" />
								<p>未收货</p>
							</h4>
							<i style="float:right;">
								<img src="${base}/res/html5/images/right.png" alt="" />
							</i>
							</a>
							</div>
						</li>
					<li>
						<div style="width:100%;">
						   <a href="${base}/m/authc/buyer/serviceCenter" style="width:100%;">
							<h4 style="float:left;">
								<img src="${base}/res/html5/images/img8.png" alt="" />
								<p>服务中心</p>
							</h4>
							<i style="float:right;">
								<img src="${base}/res/html5/images/right.png" alt="" />
							</i>
							</a>
						</div>
					</li>
				</ul>
			</div>
			 
			<!-- <div class="s-box">
				<h2>我的收藏</h2>
				<ul>
					<li>小店</li>
					<li>协会</li>
					<li>种养</li>
					<li>课堂</li>
				</ul>
				<ul style="width: 50%;">
					<li style="width: 25%;">健康中心</li>
					<li style="width: 25%;">+</li>
				</ul>
			</div> -->
			
			
			   <!--收藏商品-->
        <div class="collect">
        	<div class="tab">
            	<ul>
                	<li class="this"><a href="javascript:void(0);" onclick="shouchang('favorite_goods',this);">关注商品</a></li>
                	<!--  
                    <li><a href="javascript:void(0);" onclick="shouchang('favorite_store',this);">收藏商家</a></li>
                   <li><a href="javascript:void(0);" onclick="shouchang('your_like_goods',this);" id="cai">猜你喜欢</a></li> -->
                </ul>
            </div>
            <script>
            	function shouchang(id,obj){
					jQuery(".tab ul li").each(function(index, element) {
                        jQuery(element).removeClass("this");
                    });
					jQuery(obj).parent().addClass("this");
					jQuery(".collect_list ul").each(function(index, element) {
                        jQuery(element).hide();
                    });
					jQuery("#"+id).show();
				}

				
				jQuery(function(){
					jQuery("#cai").click();
				});
					
            </script>
             <#assign favoriteGoodsListTag = newTag("favoriteGoodsListTag")/>
             <!-- 我的商品-->
    	     <#assign favoriteGoodsList =favoriteGoodsListTag("{'tagDataType':'2','flags':'goods'}")>
            <div class="collect_list">
            	<ul id="favorite_goods" class="collect_ul">
            	   <#if (favoriteGoodsList?size>0)>
			       <#list favoriteGoodsList as favoritegoods>
	            		<li>
		            		<a href="${base}/m/goods/goodsdetail?id=${favoritegoods.favId}">
			            		<img src="${imgServer}${favoritegoods.goods.goodsImage}" />
			            		<p class="name">${favoritegoods.goods.goodsName}</p>
			            		<p class="price">¥<b>${favoritegoods.goods.goodsStorePrice}</b></p>
		            		</a>
	            		</li>
            		</#list>
            		</#if>
                </ul>
               
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
	<!--<a class="layer_menu" href="javascript:void(0);" onclick="click_layer();"><img src="${base}/res/html5/images/layer_05.png" width="35" height="35" /><span class="black_bg"></span></a>-->
    <div class="layer_show" id="show" mark="hide">
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

        
</div>
</body>
</html>
