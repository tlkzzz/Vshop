
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>会员个人中心</title>
<meta name="viewport" content="width=device-width,inital-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta name="apple-wap-web-app-capable" content="yes" />
<meta name="apple-wap-web-app-status-bar-style" content="black-translucent" />
<link href="${base}/res/html5/css/style.css" rel="stylesheet" type="text/css" />
<script src="${base}/res/html5/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${base}/res/js/common.js" charset="utf-8"></script>
</head>

<body>
<#assign memberInfoTag =newTag("memberInfoTag")>
<#assign member =memberInfoTag("")>
<div class="phone_main">
    	<!--头部-->
            	<div class="user_hd">
        	<dl>
            	<dt>
            	<#if member.memberAvatar??>
            		<img src="${imgServer}/${member.memberAvatar}" />
            	<#else>
            		<img src="upload/common/default_user_portrait.gif" />
            	</#if>
            	</dt>
                <dd><p class="name">${member.memberName}</p>
                <p>可用余额：
     	            <script type="text/javascript">
		    			var available = number_format(${member.availablePredeposit},2);
		    			document.write("&yen;" + available);
				    </script>
                </p>
                <p>锁定余额：
     	            <script type="text/javascript">
	                    var available = number_format(${member.freezePredeposit},2);
						document.write("&yen;" + available);
				    </script>
                </p>
                <!-- <p>
	                <img src="${base}/res/html5/images/userlevel_0.png" width="25" />
	                <span>铜牌会员</span>
                </p> --!>
                </dd>
            </dl>
            <!-- <div class="message"><a href="http://b2b2c.iskyshop.com/wap/buyer/message_list.htm"><img src="${base}/res/html5/images/mess.png" width="30" height="30" /><b>
                        0
                        </b></a></div> --!>
       		 </div>
        <!--导航-->
        <div class="user_nav">
        	<ul>
            	<li class="color_01">
	            	<a href="${base}/m/authc/buyer/orderList?orderState=10">
		            	<img src="${base}/res/html5/images/user_icon_01.png" />
		            	<p>未付款</p>
	            	</a>
	            	<b>${member.noPayOrder}</b>
            	</li>
                <li class="color_02">
	                <a href="${base}/m/authc/buyer/orderList?orderState=20">
		                <img src="${base}/res/html5/images/user_icon_02.png"/>
		                <p>未发货</p>
	                </a>
	                <b>${member.noFilledOrder}</b>
                </li>
                <li class="color_03">
	                <a href="${base}/m/authc/buyer/orderList?orderState=30">
		                <img src="${base}/res/html5/images/user_icon_03.png" />
		                <p>未收货</p>
	                </a>
	                <b>${member.noReceiveOrder}</b>
                </li>
                <li class="color_04"><a href=""><img src="${base}/res/html5/images/user_icon_04.png" /><p>生活购</p></a></li>
                <li class="color_05"><a href=""><img src="${base}/res/html5/images/user_icon_05.png" /><p>团购码</p></a></li>
                <li class="color_06"><a href="${base}/m/authc/buyer/couponIndex"><img src="${base}/res/html5/images/user_icon_06.png" /><p>优惠劵</p></a></li>
                <li class="color_07"><a href=""><img src="${base}/res/html5/images/user_icon_07.png" /><p>积分兑换</p></a></li>
                <li class="color_08"><a href="${base}/m/authc/buyer/serviceCenter"><img src="${base}/res/html5/images/user_icon_08.png" /><p>服务中心</p></a></li>
            </ul>
        </div>
        <!--收藏商品-->
        <div class="collect">
        	<div class="tab">
            	<ul>
                	<li class="this"><a href="javascript:void(0);" onclick="shouchang('favorite_goods',this);">收藏商品</a></li>
                    <li><a href="javascript:void(0);" onclick="shouchang('favorite_store',this);">收藏商家</a></li>
                   <!--  <li><a href="javascript:void(0);" onclick="shouchang('your_like_goods',this);" id="cai">猜你喜欢</a></li> -->
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
                <!-- 我的商家 -->
               	<#assign favoriteStoreList =favoriteGoodsListTag("{'tagDataType':'2','flags':'store'}")>
                <ul class="collect_store" id="favorite_store" style="display:none;">
                  <#if favoriteStoreList?exists && favoriteStoreList?size gt 0>
			         <#list favoriteStoreList as favoriteStore>
                     	<li><a href=""><img src="${imgServer}${favoriteStore.store.storeImage}" /><p class="name">${favoriteStore.store.storeName} </p></a></li>
                   	 </#list>
                  </#if>
               	</ul>
                <#-- <ul id="your_like_goods" style="display:none;" class="collect_ul">
                 	<li><a href=""><img src="${base}/res/html5/images/84806807-4976-4b1e-9f0c-d14c02a7449c.jpg" /><p class="name">男士棉衣2014秋冬新款加厚保暖男外套修身立领棉服青年短款棉袄 </p><p class="price">¥<b>145.00</b></p></a></li>
                   	<li><a href=""><img src="${base}/res/html5/images/69cb86e9-a6f6-4951-9fe5-0c1c7311b5d4.jpg" /><p class="name">ZZN 冬新款 毛领 毛呢大衣 羊毛大衣外套 呢子大衣 男</p><p class="price">¥<b>699.00</b></p></a></li>
                   	<li><a href=""><img src="${base}/res/html5/images/1a6e65ab-c1d6-4623-abda-692f69abfeef.jpg" /><p class="name">毛呢外套2014冬装新款欧美森女系撞色加厚毛领中长款毛呢大衣女 </p><p class="price">¥<b>268.00</b></p></a></li>
                   	<li><a href=""><img src="${base}/res/html5/images/974e597a-2a1f-4b33-9a0c-267093643ed6.jpg" /><p class="name">范思娜 新款正品时尚潮轻薄修身女反季清仓短款 大毛领羽绒服女款 </p><p class="price">¥<b>388.00</b></p></a></li>
                   	<li><a href=""><img src="${base}/res/html5/images/f173695b-5879-4e91-a90d-33122a7f6b7b.jpg" /><p class="name">南极人男士保暖衬衫加绒加厚 男士长袖休闲冬衬衣桑蚕丝填充男装</p><p class="price">¥<b>238.00</b></p></a></li>
                   	<li><a href=""><img src="${base}/res/html5/images/244dc70a-4eb6-4944-a035-cca84b1119af.jpg" /><p class="name">2014早秋新款套装 短外套装裙子 秋冬装名媛小香风两件套时尚套裙</p><p class="price">¥<b>216.00</b></p></a></li>
                   	<li><a href=""><img src="${base}/res/html5/images/add36a59-e813-45c2-8ed8-ef8167caf3e6.jpg" /><p class="name">欧洲站2014秋季新款秋装连衣裙气质上衣短裙两件套早秋休闲套装女</p><p class="price">¥<b>339.00</b></p></a></li>
                   	<li><a href=""><img src="${base}/res/html5/images/1812b11b-8371-45d4-90e8-846d0ddc0d42.jpg" /><p class="name">Acer 宏碁E1-571G-53234G1TMnks 15.6英寸笔记本电脑</p><p class="price">¥<b>3199.00</b></p></a></li>
                   	<li><a href=""><img src="${base}/res/html5/images/cea2baf9-d2cb-4c48-b90b-2858723a030c.jpg" /><p class="name">Haier/海尔 BCD-216SDN/216升/节能电冰箱/家用三门/农村可送</p><p class="price">¥<b>2299.00</b></p></a></li>
                </ul> --%>
            </div>
        </div>
        
<!--底部-->
<!-- 
    <@f.foot/>	
 -->

<!--按钮-->

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
<#--            <li><a href="${base}/m/authc/cart/cartGoodsList"><img src="${base}/res/html5/images/layer_03.png" width="20" height="20" /><p>购物车</p></a></li> -->
            <li><a href="${base}/m/authc/buyer/center"><img src="${base}/res/html5/images/layer_04.png" width="20" height="20" /><p>用户中心</p></a></li>
        </ul>
        <div class="show_bg"></div>
    </div>
</div>

        
</div>
</body>
</html>
