<#assign goodsRcListTag = newTag("goodsRcListTag") />
<#assign recommendGoodslist = goodsRcListTag("{'gcId':'${gcId}','storeId':'${storeid}', 'gcType':'${gcType}', 'sortField':'${sortField}', 'sortOrder':'${sortOrder}','pageNo':'${pageNo}','keyword':'${keyword}'}") />

<#if recommendGoodslist!=null && recommendGoodslist?size&gt;0>
	<#list recommendGoodslist as recommendGoods>
    	<dl style="margin-top: 1.5%;">
    		<dt>
    			<a href="${base}/m/goods/goodsdetail?id=${recommendGoods.goodsId}&amp;storeId=${recommendGoods.storeId}">
	 			<img width="90" height="90" src="${imgServer}${recommendGoods.goodsImage}" title="${recommendGoods.goodsName}" alt="${recommendGoods.goodsName}">
	 			</a>
	 		</dt>
			<dd>
				<h2><b><a href="${base}/m/goods/goodsdetail?id=${recommendGoods.goodsId}&amp;storeId=${recommendGoods.storeId}" style="color:#000;">${recommendGoods.goodsName}</a></b></h2>
				<h2 style="color: red; margin-top: 15%;">¥${recommendGoods.goodsStorePrice}</h2>
				<h2 style="color: #a6a6a6;">${recommendGoods.commentnum}人评价，${recommendGoods.salenum}人已购买</h2>
			</dd>
		</dl>
	</#list>
</#if>