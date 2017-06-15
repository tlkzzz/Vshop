<#assign goodsListTag = newTag("goodsListTag")/>
<#assign storeGoodsClassbyIdTag = newTag("storeGoodsClassbyIdTag")/>
<#assign storeGoodsClass =storeGoodsClassbyIdTag("{'Stid':'${storeClassId}'}")>
<div class="nc-s-c-s3 ncg-list">
	<div class="title pngFix">
		<h4>
		   <#if storeGoodsClass?exists && storeGoodsClass!=''>
		      ${storeGoodsClass.stcName}
		   <#else>
		                全部商品
		   </#if>
		</h4>
	</div>
	<form id="search_form" method='get' name="search_form">
		    <input name="act" type="hidden" value="show_store" />
		    <input name="key" type="hidden" value="${orderField}" />
            <input name="order" type="hidden" value="${order}" />
		    <div class="ncs-goodslist-bar">
			<ul class="ncs-array">
				<li class='' flags='goodsAddTime'><a class='' href="javascript:void(0)"
					onClick="set_form('goodsAddTime');">新品</a>
				</li>
				<li class='' flags='goodsStorePrice'><a class='' href="javascript:void(0)"
					onClick="set_form('goodsStorePrice');">价格</a>
				</li>
				<li class='' flags='salenum'><a class='' href="javascript:void(0)"
					onClick="set_form('salenum');">销量</a>
				</li>
				<li class='' flags='goodsCollect'><a class='' href="javascript:void(0)"
					onClick="set_form('goodsCollect');">收藏</a>
				</li>
				<li class='' flags='goodsClick'><a class='' href="javascript:void(0)"
					onClick="set_form('goodsClick');">人气</a>
				</li>
			</ul>
			<!-- <div class="price-search">
				    <em>&yen;</em>&nbsp;
				    <input type="text" class="w30"name="start_price" value="">
				    <i>-</i>
					<input type="text" class="w30" name="end_price" value="">
					<a href="javascript:void(0)">搜索</a>
			</div> -->
		</div>
	</form>
	<div class="content">
		<ul>
		      <#assign recommendallgoodslist =goodsListTag("{'storeid':'${storeId}','tagDataType':'2','pagesize':'${pageSize}','pageno':'${pageNo}','storeClassId':'${storeClassId}','goodsName':'${goodsName}','orderField':'${orderField}','order':'${order}'}")>
			  <#if recommendallgoodslist?exists && recommendallgoodslist?size gt 0>
			      <#list recommendallgoodslist as recommendgoods>
			          <li>
		                <dl>
		                  <dt><a href="#?act=goods&goods_id=89" target="_blank">${recommendgoods.goodsName}</a></dt>
		                  <dd class="ncg-pic pngFix"><a href="${base}/product/detail?id=${recommendgoods.goodsId}" target="_blank" class="thumb"><i></i><img src="<#if recommendgoods.goodsImage!=null>${imgServer}${recommendgoods.goodsImage}<#else>${base}/res/images/member/default_image.png</#if>" onload="javascript:DrawImage(this,160,160);" title="${recommendgoods.goodsName}" alt="${recommendgoods.goodsName}" /></a></dd>
		                  <dd class="ncg-price">
		                                        <em class="price">
		                                        <i>&yen;</i>
		                                           ${recommendgoods.goodsStorePrice}    
		                                        </em></dd>
		                  <dd class="ncg-sold">已销售：<strong> ${recommendgoods.salenum}</strong> 件</dd>
		                </dl>
                     </li>
			      </#list>
			  </#if>
		</ul>
		<div class="pagination">
		   	<#--获取总条数-->
				<#assign recordCount = goodsListTag("{'storeid':'${storeId}','pagesize':'${pageSize}','tagDataType':'5','storeClassId':'${storeClassId}','goodsName':'${goodsName}','orderField':'${orderField}','order':'${order}'}")>
				<#import "/commons/tagpage.ftl" as q> <#--引入分页-->
				<#if recordCount??>
				    <@q.pager pageNo=pageNo pageSize=pageSize recordCount=recordCount toURL="${toUrl}"/>
				</#if>
		</div>
		<div class="clear"></div>
	</div>
</div>
