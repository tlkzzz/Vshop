       <div class="flexslider">
          <ul class="slides">
                 <#list storeVo.storeSlide?split(",") as slide>  
                     <li><img src="${imgServer}${slide}" width="790" height="355"></li>
                 </#list>  
           </ul>
        </div>
        
        <#assign goodsListTag = newTag("goodsListTag")/>
        <div class="nc-s-c-s3 ncg-list mt10">
          <div class="title pngFix"> <span><a href="#?act=show_store&op=goods_all&id=2" class="more">更多</a></span>
            <h4> 推荐商品</h4>
          </div>
          <div class="content pt20">
            <ul>
			  <#assign recommendgoodslist =goodsListTag("{'storeid':'${storeVo.storeId}','commend':'1','tagDataType':'2','pagesize':'4','storeClassId':'${storeClassId}'}")>
			  <#if recommendgoodslist?exists && recommendgoodslist?size gt 0>
			      <#list recommendgoodslist as recommendgoods>
			          <li>
		                <dl>
		                  <dt><a href="#?act=goods&goods_id=89" target="_blank">${recommendgoods.goodsName}</a></dt>
		                  <dd class="ncg-pic pngFix"><a href="${base}/product/detail?id=${recommendgoods.goodsId}" target="_blank" class="thumb"><i></i><img src="${imgServer}${recommendgoods.goodsImage}" onload="javascript:DrawImage(this,160,160);" title="${recommendgoods.goodsName}" alt="${recommendgoods.goodsName}" /></a></dd>
		                  <dd class="ncg-price"><em class="price"><i>&yen;</i>
		                                           ${recommendgoods.goodsStorePrice}                                       </em></dd>
		                  <dd class="ncg-sold">已销售：<strong> ${recommendgoods.salenum}</strong> 件</dd>
		                </dl>
                     </li>
			      </#list>
			  </#if>
             </ul>
           </div>
        </div>
        
        <div class="nc-s-c-s3 ncg-list mt10">
          <div class="title pngFix"><span><a href="#?act=show_store&op=goods_all&id=2" class="more">更多</a></span>
            <h4>新品</h4>
          </div>
          <div class="content pt20">
            <ul>
			  <#assign newProductlist = goodsListTag("{'storeid':'${storeVo.storeId}','order':'0','tagDataType':'2','pagesize':'8','storeClassId':'${storeClassId}'}")>
			  <#if newProductlist?exists && newProductlist?size gt 0>
			      <#list newProductlist as newGoods>
			          <li>
		                <dl>
		                  <dt><a href="#?act=goods&goods_id=89" target="_blank">${newGoods.goodsName}</a></dt>
		                  <dd class="ncg-pic pngFix"><a href="${base}/product/detail?id=${newGoods.goodsId}" target="_blank" class="thumb"><i></i><img src="${imgServer}${newGoods.goodsImage}" onload="javascript:DrawImage(this,160,160);" title="${newGoods.goodsName}" alt="${newGoods.goodsName}" /></a></dd>
		                  <dd class="ncg-price"><em class="price"><i>&yen;</i>
		                                           ${newGoods.goodsStorePrice}                                       </em></dd>
		                  <dd class="ncg-sold">已销售：<strong> ${newGoods.salenum}</strong> 件</dd>
		                </dl>
                     </li>
			      </#list>
			  </#if>
             </ul>
           </div>
     </div>