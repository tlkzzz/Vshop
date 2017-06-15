<@p.header title="商品汇平台管理中心"/>
  <#assign siteSettingTag = newTag("siteSettingTag") />
	<#assign siteSet = siteSettingTag("") />
<script src="${base}/res/js/jquery.js"></script>
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
			+"<a href='${base}'  title='首页' alt='首页'>${siteSet.siteName}</a><span>[<a href='${base}/logout'>退出</a>]</span>");
		}else{
			$(".user-entry").append("您好，欢迎来到<a href='${base}' title='首页' alt='首页'>${siteSet.siteName}</a>"
			+"<span>[<a href='${base}/login'>登录</a>]</span><span>[<a href='${base}/signUp'>注册</a>]</span>");
		}
		$(".user-entry").append("<span class='seller-login'>"
				+"<a href='${sellerServer}' title='商家中心' target='_blank'><i class='icon-signin'></i>商家管理中心</a></span>");
	});
</script>
<div class="clear"></div>
<!--主页内容，调用index-->
<div class="nch-breadcrumb-layout"></div>
<link href="${base}/res/css/index.css" rel="stylesheet" type="text/css">
<!--[if IE 6]>
<script type="text/javascript" src="res/js/ie6.js" charset="utf-8"></script>
<![endif]-->
<style type="text/css">
    .category {
        display: block !important;
    }
</style>

<!-- HomeFocusLayout Begin-->
<div class="home-focus-layout">
	<!-- 首页大广告 -->
 	<#assign advPositionTag = newTag("advTag") />
 	<#assign topPosition = advPositionTag("{'apId':'6'}") />
 	<#if topPosition?? && topPosition!''>
 	<#assign apList = topPosition.advList />
 	<ul id="fullScreenSlides" class="full-screen-slides">
  	 	<#list apList as advs >
   		<li style="z-index: 800; display: list-item; opacity: 1;">
   		<a href="${advs.advUrl}" target="_blank"><img class="scrollLoading" src="${imgServer}${advs.resUrl}" data-url="${imgServer}${advs.resUrl}" alt="${advs.advTitle}" width="770" height="480" style="display: inline;"></a>
   		</li>
   	 	</#list>
  	</ul>
  	
  	<ul id="SlidesPagination" class="full-screen-slides-pagination" style="margin-left: -388px;">
  		<#list apList as advs >
  			<li ><a href="javascript:void(0);">${advs_index+1 }</a></li>
  		</#list>
  	</ul>
  	</#if>
  	<!--栏目商品
  	<#assign recommendGoodsTag = newTag("recommendGoodsTag") />
   <#assign recommendGoodslist = recommendGoodsTag("{'goodsflagsname':'recommend'}") />
    <div class="jfocus-trigeminy">
	    <ul style="left: -776px; width: 1552px;">
	      <li>
		         <#if recommendGoodslist??>
	                	<#list recommendGoodslist as recommendGoods >
	                	   <a href="${base}/product/detail?id=${recommendGoods.goods.goodsId}" style="opacity: 1;"><img style="width: 259px; height: 180px; display: inline;" border="0" class="scrollLoading" src="${imgServer}${recommendGoods.goods.goodsImage}" 
			                data-url="${imgServer}${recommendGoods.goods.goodsImage}" alt=""></a>
			                <#if (recommendGoods_index>2)>
			                    <#break/>
			                </#if>
	                    </#list>
	             </#if>
		  </li>
	      <li> 
	            <#if recommendGoodslist??>
	                	<#list recommendGoodslist as recommendGoods >
	                	    <#if (recommendGoods_index>2)>
		                	    <a href="${base}/product/detail?id=${recommendGoods.goods.goodsId}" style="opacity: 1;"><img style="width: 259px; height: 180px; display: inline;" border="0" class="scrollLoading" src="${imgServer}${recommendGoods.goods.goodsImage}" 
				                   data-url="${imgServer}${recommendGoods.goods.goodsImage}" alt="">
				                </a>
				            <#elseif (recommendGoods_index>5)>
			                    <#break/>
			                </#if>
	                    </#list>
	             </#if>
		  </li>
	    </ul>
  		<div class="pagination"><span style="opacity: 0.4;"></span><span style="opacity: 1;"></span></div><div class="arrow pre" style="opacity: 0;"></div><div class="arrow next" style="opacity: 0;"></div>
    </div>-->
    <div class="right-sidebar">
        <div class="policy">
            <ul>
                <li class="b1">七天包退</li>
                <li class="b2">正品保障</li>
                <li class="b3">闪电发货</li>
            </ul>
        </div>
        <!---团购模块-->
        <div class="groupbuy">
            <div class="title"><i>抢</i>限时抢购</div>
            <ul>
            <#assign rigthPosition = advPositionTag("{'apId':'7'}") />
            <#if rigthPosition?? && rigthPosition!''>
	            <#assign apList = rigthPosition.advList />
	            <#if apList??>
	              <#list apList as advs >
		              <li> 
						  <a href="${advs.advUrl}" target="_blank" style="opacity: 1;"><img style="width: 210px; height: 180px; display: inline;" border="0" class="scrollLoading" src="${imgServer}${advs.resUrl}" 
						  data-url="${imgServer}${advs.resUrl}" alt="${advs.advTitle}"></a>
					  </li>
				  </#list>
				</#if>
			</#if>
            </ul>
        </div>

        <div class="proclamation">
            <ul class="tabs-nav">
                <li class="tabs-selected"><h3>商城公告</h3></li>
                <li class=""><h3>招商入驻</h3></li>
            </ul>
            <div class="tabs-panel">
                <ul class="mall-news">
                <#assign articleTag = newTag("articleTag") />
                <#assign noticeList = articleTag("{'tagDataType':'2','acId':'8','pageSize':'5'}") />
                <#if noticeList??>
                	<#list noticeList as notices >
                    <li><i class="icon-dot"></i>
                        <a href="${base}/help/content?acId=${notices.acId}&articleId=${notices.articleId}" title="${notices.articleTitle}">${notices.articleTitle}</a>
                        <time>(${notices.createTimeStr?string("yyyy-MM-dd")})</time>
                    </li>
                    </#list>
                 </#if>
                </ul>
            </div>
            <div class="tabs-panel tabs-hide">
                <a href="${base}/signUp" title="商家开店" class="store-join-btn" target="_blank">&nbsp;</a>
                <a href="${base}/help/index?acId=7" target="_blank" class="store-join-help"><i class="icon-question-sign"></i>查看开店协议</a>
            </div>
        </div>
    </div>
</div>
<div class="home-sale-layout wrapper">
    <div class="left-layout">
        <ul class="tabs-nav">
            <li class="tabs-selected">
            	<i class="arrow"></i>
                <h3>新品上市</h3>
            </li>
            <li>
            	<i class="arrow"></i>
                <h3>猜你喜欢</h3>
            </li>
            <li>
            	<i class="arrow"></i>
                <h3>推荐商品</h3>
            </li>
             <li>
            	<i class="arrow"></i>
                <h3>关联产品</h3>
            </li>
        </ul>
        <!--新品上市 -->
        <#assign recommendGoodslist = recommendGoodsTag("{'goodsflagsname':'newgoods'}") />
        <div class="tabs-panel sale-goods-list ">
            <ul class="specially">
               <#if recommendGoodslist??>
               	<#list recommendGoodslist as recommendGoods >
                      <li>
	                    <dl>
	                        <!--商品名称-->
	                        <dt class="goods-name">
	                        	<a target="_blank" href="${base}/product/detail?id=${recommendGoods.goods.goodsId}" title="${recommendGoods.goods.goodsName}">
	                        		${recommendGoods.goods.goodsName}
	                        	</a>
	                        </dt>
	                        </dt>
	                        <!--商品图片-->
	                        <dd class="goods-thumb">
	                        	<a target="_blank" href="${base}/product/detail?id=${recommendGoods.goods.goodsId}">
	                        		<img class="scrollLoading" src="" data-url="${imgServer}${recommendGoods.goods.goodsImage}"
	                                alt="${recommendGoods.goods.goodsName}"/></a></dd>
	                        <!--商品价格-->
	                        <dd class="goods-price"> 商城价：<em>&yen;${recommendGoods.goods.goodsStorePrice}</em></dd>
		                   </dl>
	               		</li>
              		   <#if (recommendGoods_index>5)>
              			  <#break/>
		             </#if>
                   </#list>
                </#if>
            </ul>
        </div>
        <!--猜你喜欢-->
        <#assign youlikeGoodslist = recommendGoodsTag("{'goodsflagsname':'youlike'}") />
        <div class="tabs-panel sale-goods-list tabs-hide">
            <ul class="specially">
               <#if youlikeGoodslist??>
               	<#list youlikeGoodslist as youlikeGoods >
                      <li>
                    	<dl>
	                        <!--商品名称-->
	                        <dt class="goods-name">
	                        	<a target="_blank" href="${base}/product/detail?id=${youlikeGoods.goods.goodsId}" title="${youlikeGoods.goods.goodsName}">
	                        		${youlikeGoods.goods.goodsName}
	                        	</a>
	                        </dt>
	                        </dt>
	                        <!--商品图片-->
	                        <dd class="goods-thumb">
	                        	<a target="_blank" href="${base}/product/detail?id=${youlikeGoods.goods.goodsId}">
	                        		<img class="scrollLoading" src="" data-url="${imgServer}${youlikeGoods.goods.goodsImage}"
	                                alt="${youlikeGoods.goods.goodsName}"/></a></dd>
	                        <!--商品价格-->
	                        <dd class="goods-price"> 商家价：<em>&yen;${youlikeGoods.goods.goodsStorePrice}</em></dd>
	                    </dl>
               		</li>
              		     <#if (youlikeGoods_index>5)>
              			      <#break/>
		             </#if>
                   </#list>
                </#if>
            </ul>
        </div>
        <!--推荐商品-->
        <#assign newGoodslist = recommendGoodsTag("{'goodsflagsname':'recommend'}") />
        <div class="tabs-panel sale-goods-list tabs-hide">
            <ul class="specially">
               <#if newGoodslist??>
               	<#list newGoodslist as newGoods >
                     <li>
	                    <dl>
	                        <!--商品名称-->
	                        <dt class="goods-name">
	                        	<a target="_blank" href="${base}/product/detail?id=${newGoods.goods.goodsId}" title="${newGoods.goods.goodsName}">
	                        		${newGoods.goods.goodsName}
	                        	</a>
	                        </dt>
	                        </dt>
	                        <!--商品图片-->
	                        <dd class="goods-thumb">
	                        	<a target="_blank" href="${base}/product/detail?id=${newGoods.goods.goodsId}">
	                        		<img class="scrollLoading" src="" data-url="${imgServer}${newGoods.goods.goodsImage}"
	                                alt="${newGoods.goods.goodsName}"/></a></dd>
	                        <!--商品价格-->
	                        <dd class="goods-price"> 商城价：<em>&yen;${newGoods.goods.goodsStorePrice}</em></dd>
	                    </dl>
               		  </li>
           		     <#if (newGoods_index>5)>
           			      <#break/>
		             </#if>
                   </#list>
                </#if>
            </ul>
        </div>
          <!--推荐商品-->
        <#assign glcpGoodslist = recommendGoodsTag("{'goodsflagsname':'glcp'}") />
        <div class="tabs-panel sale-goods-list tabs-hide">
            <ul class="specially">
               <#if glcpGoodslist??>
               	<#list glcpGoodslist as glcp >
                     <li>
	                    <dl>
	                        <!--商品名称-->
	                        <dt class="goods-name">
	                        	<a target="_blank" href="${base}/product/detail?id=${glcp.goods.goodsId}" title="${glcp.goods.goodsName}">
	                        		${glcp.goods.goodsName}
	                        	</a>
	                        </dt>
	                        </dt>
	                        <!--商品图片-->
	                        <dd class="goods-thumb">
	                        	<a target="_blank" href="${base}/product/detail?id=${glcp.goods.goodsId}">
	                        		<img class="scrollLoading" src="" data-url="${imgServer}${glcp.goods.goodsImage}"
	                                alt="${glcp.goods.goodsName}"/></a></dd>
	                        <!--商品价格-->
	                        <dd class="goods-price"> 商城价：<em>&yen;${glcp.goods.goodsStorePrice}</em></dd>
	                    </dl>
               		  </li>
           		     <#if (glcp_index>5)>
           			      <#break/>
		             </#if>
                   </#list>
                </#if>
            </ul>
        </div>
    </div>
</div>
<!--HomeFocusLayout End-->
<div class="clear"></div>

<!--首页分类，后台设置-->
<#assign webCodeTag = newTag("webCodeTag") />
<#assign taglist = webCodeTag("{'webCodeType':'floor_list'}") />
<#if taglist??>
	
	<#list taglist as webCode>
	<#assign json = webCode.codeInfo?eval />
	    <#list json as item>
			<div class="home-standard-layout wrapper style-<#if (item.floorColor?? && item.floorColor!='null')>${item.floorColor}<#else>gray</#if>">
		    <div class="left-sidebar">
		        <div class="title">
		            <div class="pic-type">
		            <a href="${base}/search/goodsSearch?searchType=gcIdSearch&keyword=${item.gcId}" target="_blank" title="${item.floorName}"><img border=0 src="${imgServer}${item.bannerImg}"></a></div>
		        </div>
		        <div class="left-ads">
		        <a href="${base}/search/goodsSearch?searchType=gcIdSearch&keyword=${item.gcId}" target="_blank"><img class="scrollLoading" border=0 src="${imgServer}${item.floorImg}" data-url="${imgServer}${item.floorImg}"></a>
		        </div>
		        <div class="recommend-classes">
		            <ul>
			            <#list json.goodsClassList as goodsClassList>
			                <li><a href="${base}/search/goodsSearch?searchType=gcIdSearch&keyword=${goodsClassList.gcId}" title="${goodsClassList.gcName}" target="_blank">${goodsClassList.gcName}</a></li>
		                </#list>
		            </ul>
		        </div>
		    </div>
		    <div class="middle-layout">
		        <ul class="tabs-nav">
		            <li class="tabs-selected"><i class="arrow"></i>
		                <h3>${item.floorName}</h3></li>
		        </ul>
		        <div class="tabs-panel middle-banner-style01 fade-img ">
						<#assign ag = 1 />
						<#assign cg = 1 />
						<#assign dg = 1 />
		        		<#list json.goodsList as goods >
		        			<#if (goods_index<2)>
		                	<li class="a${goods_index+1}" style="opacity: 1;">
					            <a href="${base}/product/detail?id=${goods.goodsId}" target="_blank">
					            <img style="width: 194px; height: 194px; display: inline;" border="0" class="scrollLoading" src="${imgServer}${goods.goodsImage}" 
					            data-url="${imgServer}${goods.goodsImage}" alt="${goods.goodsName}">
					            </a>
				            </li>
				            <#elseif (goods_index=2)>
				            	 <li class="b1" style="opacity: 1;">
						             <a href="${base}/product/detail?id=${goods.goodsId}" target="_blank">
							            <img style="width: 388px; height: 388px; display: inline;" border="0" class="scrollLoading" src="${imgServer}${goods.goodsImage}" 
							            data-url="${imgServer}${goods.goodsImage}" alt="${goods.goodsName}">
							          </a>
					            </li>
					         <#elseif (goods_index>2 && goods_index<5)>
					         	<li class="c${cg}" style="opacity: 1;">
						           <a href="${base}/product/detail?id=${goods.goodsId}" target="_blank">
							            <img style="width: 194px; height: 194px; display: inline;" border="0" class="scrollLoading" src="${imgServer}${goods.goodsImage}" 
							            data-url="${imgServer}${goods.goodsImage}" alt="${goods.goodsName}">
							          </a>
					            </li>
					            <#assign cg=cg+1/>
					         <#elseif (goods_index>4 &&goods_index<9)>
					         	<li class="d${dg}" style="opacity: 1;">
						            <a href="${base}/product/detail?id=${goods.goodsId}" target="_blank">
						            <img style="width: 194px; height: 194px; display: inline;" border="0" class="scrollLoading" src="${imgServer}${goods.goodsImage}" 
						            data-url="${imgServer}${goods.goodsImage}" alt="${goods.goodsName}"></a>
					            </li>
					            <#assign dg = dg+1/>
					         <#else>
					          	<#break/>
				             </#if>
		                </#list>
		        </div>
		    </div>
		    <div class="right-sidebar">
		        <div class="title"></div>
		        <div class="recommend-brand">
		            <ul>
		            <li><a href="${base}/search/goodsSearch?searchType=BrandIdSearch&keyword=${brands.brandId}" target="_blank">
	                 	<img src="${imgServer}${brands.brandPic}" alt="${brands.brandName}" onload="javascript:DrawImage(this,96,32);"></a>
	                 </li>
		            <#assign brandTag = newTag("brandTag") />
					<#assign brandlist = brandTag("{'classid':'${item.gcId}'}") />
					<#assign count=0/>
					<#if brandlist??>
						<#list brandlist as brands>
						<#if brands.brandRecommend==1>
							 
			                 <#assign count=count+1/>
						</#if>
						<#if count &gt; 11><#break/></#if>
						</#list>
					</#if>
		            </ul>
		        </div>
		        
		        <div class="right-c">
		       		<a href="#" target="_blank"><img style="height:100%;" src="http://ubisp.oss-cn-shanghai.aliyuncs.com/upload/img/store/0/1488512520210.jpg" /></a>
		   		</div>
		   		
		        <div class="right-side-focus">
		        	<!-- 楼层广告 -->
		            <ul style="left: 0px; width: 212px;">
		            <!-- <script type="text/javascript" src="#?act=adv&amp;op=advshow&amp;ap_id=332"></script> -->
		          
		            <li><a href="${item.ggUrl}" target="_blank"><img class="scrollLoading" src="${imgServer }${item.ggImg}" 
		            data-url="${imgServer }${item.ggImg}" alt="" style="display: inline;"></a>
		            </li>
		            		
		            </ul>
		        </div>
		    </div>
			</div>
		</#list>
	</#list>
</#if>


<!--底部广告位-->
<div class="wrapper">
    <div class="mt10">
    	<#assign bottomPosition = advPositionTag("{'apId':'8'}") />
    	<#if bottomPosition?? && bottomPosition!''>
        <#assign apList = bottomPosition.advList />
	       <#if apList??>
	       <#list apList as advs >
		       <a href="${advs.advUrl}" target="_blank"><img style="width: 1200px; height: 80px; display: inline;" border="0" class="scrollLoading" src="${imgServer }${advs.resUrl}" 
		       data-url="${imgServer }${advs.resUrl}" alt=""></a>
	       <#if (advs_index>1)><#break></#if>
	       </#list>
       	   </#if>
       </#if>
    </div>
</div>

<script type="text/javascript" src="res/js/home_index.js" charset="utf-8"></script>

<div style="clear: both;"></div>
<div id="web_chat_dialog" style="display: none;float:right;"></div>
<a id="chat_login" href="javascript:void(0)" style="display: none;"></a>

<script type="text/javascript" src="res/js/jquery.charCount.js" charset="utf-8"></script>
<script type="text/javascript" src="res/js/jquery.smilies.js" charset="utf-8"></script>
<div class="clear"></div>
<!-----footer------>
<@p.footer/>
