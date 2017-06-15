<#assign goodsAttrTag =newTag("goodsAttrTag")>
<#assign goodsAttr =goodsAttrTag("{'goodsid':'${goodsId}'}")>
<#assign attrList = goodsAttr.get("goodsattr")>
<div class="content bd">
	<ul class="nc-goods-sort">
		<li>品牌：${goodsAttr.get("goodsbrandname")}</li> 
		<#if attrList?? && attrList!''> 
			<#list attrList as ga>
			<li>
				${ga.attrName}：${ga.attrValueName}
			</li> 
			</#list> 
		</#if>
	</ul>
	<div class="default">${goodsAttr.get("goodsbody")}</div>
</div>