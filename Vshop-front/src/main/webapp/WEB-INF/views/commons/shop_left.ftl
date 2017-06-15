<#macro left storeId>
<#--
<div class="nc-s-c-s1 ncs-search-bar mt10">
  <div class="title">
    <h4>店内搜</h4>
  </div>
  <div class="content">
    <form id="" name="searchShop" method="get" action="#">
      <input type="hidden" name="act" value="show_store" />
      <input type="hidden" name="op" value="goods_all" />
      <input type="hidden" name="id" value="2" />
      <table class="ncs-search">
        <tr>
          <th>关键字：</th>
          <td><input type="text" class="w90" name="keyword"></td>
        </tr>
        <tr>
          <th>价格：</th>
          <td><input type="text" class="w30" name="start_price">
            -
            <input type="text" class="w30" name="end_price"></td>
        </tr>
        <tr>
          <th>&nbsp;</th>
          <td><a href="javascript:document.searchShop.submit();" >搜索</a></td>
        </tr>
      </table>
    </form>
  </div>
</div> -->

<div class="nc-s-c-s1 ncs-class-bar mt10">
  <div class="title">
    <h4>商品分类</h4>
  </div>
     <div class="content">
         <!--  <p> <span><a href="#">按新品</a></span><span><a href="#">按价格</a></span><span><a href="#">按销量</a></span><span><a href="#">按人气</a></span></p> -->
                <!-- 根据商家id获取商家详情 start -->
                <#assign storeInfoTag =newTag("storeInfoTag")>  
                <#assign storeVo =storeInfoTag("{'storeId':'${storeId}'}")>
                <!-- 根据商家id获取商家详情 end -->
               <#assign storeGoodsClassTag =newTag("storeGoodsClassTag")>
			   <#assign storeGoodsClassListT =storeGoodsClassTag("{'storeid':'${storeId}','parentId':'0'}")>
			   <#if storeGoodsClassListT?exists && storeGoodsClassListT?size gt 0>
			     <ul class="ncs-submenu">
					<#list storeGoodsClassListT as storeclass>
						 <#if storeclass.hasChild!=0>
						     <li>
						     	  <span class="ico-none" onclick="class_list(this);" span_id="${storeclass.stcId}" style="cursor: pointer;"><em>-</em></span><a href="${base}/store/shop?storeId=${storeclass.storeId}&storeClassId=${storeclass.stcId}">${storeclass.stcName}</a>
							       <ul id="stc_${storeclass.stcId}" style="display: block;">
							          <#list storeclass.classList as storeclass2>
								          <#if storeclass2.stcState>
								          		<li><span class="ico-sub">&nbsp;</span><a href="${base}/store/shop?storeId=${storeclass2.storeId}&storeClassId=${storeclass2.stcId}">${storeclass2.stcName}</a></li>
								          </#if>
						              </#list>
						           </ul>
					         </li>
						 <#else>
						     <li><span class="ico-none"><em>-</em></span><a href="${base}/store/shop?storeId=${storeclass.storeId}&storeClassId=${storeclass.stcId}">${storeclass.stcName}</a></li>
						 </#if>
					</#list>
			   </ul>
			</#if>
    <div class="clear mb10"></div>
  </div>
</div>

<div class="nc-s-c-s1 ncs-top-bar mt10">
	<div class="title">
		<h4>商品排行</h4>
	</div>
	<div class="content">
		<ul class="ncs-top-tab pngFix">
			<li class="current"><a
				href="javascript:void(0)">热销商品排行</a></li>
			<li><a
				href="javascript:void(0)">热门收藏排行</a></li>
		</ul>
		<div class="ncs-top-panel">
		<#assign hotGoodsTag =newTag("hotGoodsTag")>  
        <#assign hotlist =hotGoodsTag("{'pagesize':'5','storeId':'${storeId}'}")>
			<ol>
			<#list hotlist as hot>
				<#if hot.goodsShow==0>
					<li>
						<dl>
							<dt>
								<a href="${base}/product/detail?id=${hot.goodsId}">${hot.goodsName}</a>
							</dt>
							<dd class="goods-pic">
								<a href="#">
									<span class="thumb size40">
										<i></i>
										<img src="<#if hot.goodsImage??>${imgServer}${hot.goodsImage}<#else>${base}/res/images/common/default_goods_image.gif_small.gif</#if>" 
											title="${hot.goodsName}" onload="javascript:DrawImage(this,40,40);">
									</span>
								</a>
								<p>
									<span class="thumb size100">
										<i></i>
										<img src="<#if hot.goodsImage??>${imgServer}${hot.goodsImage}<#else>${base}/res/images/common/default_goods_image.gif_small.gif</#if>" onload="javascript:DrawImage(this,100,100);">
										<big></big>
										<small></small>
									</span>
								</p>
							</dd>
							<dd class="price pngFix">${hot.goodsStorePrice}</dd>
							<dd class="selled pngFix">
								售出：<strong>${hot.salenum}</strong>笔
							</dd>
						</dl>
					</li>
				</#if>
			</#list>
			</ol>
		</div>
		<div class="ncs-top-panel hide">
		<#assign hotCollectGoodsTag =newTag("hotCollectGoodsTag")>  
        <#assign hotlist =hotCollectGoodsTag("{'pagesize':'5','storeId':'${storeId}'}")>
			<ol>
			<#list hotlist as hot>
				<#if hot.goodsShow==0>
					<li>
						<dl>
							<dt>
								<a href="${base}/product/detail?id=${hot.goodsId}">${hot.goodsName}</a>
							</dt>
							<dd class="goods-pic">
								<a href="#">
									<span class="thumb size40">
										<i></i>
										<img src="<#if hot.goodsImage??>${imgServer}${hot.goodsImage}<#else>${base}/res/images/common/default_goods_image.gif_small.gif</#if>" 
											title="${hot.goodsName}" onload="javascript:DrawImage(this,40,40);">
									</span>
								</a>
								<p>
									<span class="thumb size100">
										<i></i>
										<img src="<#if hot.goodsImage??>${imgServer}${hot.goodsImage}<#else>${base}/res/images/common/default_goods_image.gif_small.gif</#if>" onload="javascript:DrawImage(this,100,100);">
										<big></big>
										<small></small>
									</span>
								</p>
							</dd>
							<dd class="price pngFix">${hot.goodsStorePrice}</dd>
							<dd class="selled pngFix">
								收藏：<strong>${hot.goodsCollect}</strong>个
							</dd>
						</dl>
					</li>
				</#if>
			</#list>
			</ol>
		</div>
		<!--<p>
			<a href="${base}/store/shop?storeId=${storeId}" onclick="initDataList2();" target="_blank">查看本店其他商品</a>
		</p>-->
	</div>
</div>

</#macro>