
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,inital-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
<title>分类 - 优彼，微商家</title>
<link href="${base}/res/html5/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${base}/res/html5/css/fenlei.css?aa=4"/>
<link rel="stylesheet" type="text/css" href="${base}/res/html5/css/resetn.css"/>
<script src="${base}/res/html5/js/jquery-1.10.2.min.js"></script>
<script>
jQuery(document).ready(function(e) {
	
	$(".conter_left>ul>li").eq(0).css("background","#ccc");
	$(".conter_right>ul>li").eq(0).show().siblings().hide();
    jQuery(".conter_left ul li").click(function(){
    	
    	var index=$(this).index();
    	$(".conter_right>ul>li").eq(index).show().siblings().hide();
    	
    	
    	
    	var temp=$(this);
        jQuery(".conter_left ul li").each(function(){
            $(this).css("background","#fff");

        });
        temp.css("background","#ccc");

    	
	})
	
	
	

    
    
});


function searcht(){
    var keyword=$("#keyword").val();
   
    window.location.href='${base}/m/goods/goodslist?searchType='+'keywordSearch'+'&keyword='+keyword;
 }

</script>
</head>

<body >
<div class="wrap">
			<div class="header">
				<div class="sousuo">
					<input type="text" id="keyword" name="keyword" placeholder="搜索商品" />
					<button  onclick="searcht();" >搜索</button>
				</div>
				
				
			</div>
			<div class="conter_left">
				<p>推荐分类</p>
				<ul>
				<#assign goodsClassTag =newTag("goodsClassTag")>
				<#assign goodsList =goodsClassTag("")>
				<#if goodsList?exists && goodsList?size gt 0>
				<#list goodsList as class>
				<li id="${class.gcId}" >${class.gcName}</li>
				
		       	</#list>
				</#if>
	
				</ul>
			</div>
			<div class="conter_right">
		<ul>
				  
			<#if goodsList?exists && goodsList?size gt 0>
			<#list goodsList as class>
		       	<#assign goodsLi =goodsClassTag("{'parentid':'${class.gcId}'}")>
		       	<#assign href ='m/category/categoryList?'>
				<li>
							<img src="${base}/res/html5/images/fenlei1_03.jpg" />
							
							<#list goodsLi as classtwo>
		       			
		       				<h1>${classtwo.gcName}</h1>
		       				<div class="conter_right_jiao1">
									<ul>
									<#assign goodsLi =goodsClassTag("{'parentid':'${classtwo.gcId}'}")>
									<#list goodsLi as classthree>
									<li><a href="${base}/m/goods/goodslist?searchType=gcIdSearch&keyword=${classthree.gcId}"> <img src="${imgServer}${classthree.gcpic}"/><span>${classthree.gcName}</span></a></li>
									</#list>
										
									</ul>
							</div>
		        	</#list>
		        		
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
