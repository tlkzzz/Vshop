<#-- 
<script src="${base}/res/js/layer/layer.js"></script>
<link href="${base}/res/css/mycart.css" rel="stylesheet" type="text/css" />
<link href="${base}/res/css/global.css" rel="stylesheet" type="text/css" />
<input type="hidden" name="goodsId" value="${goodsId}"/>
<div id="mycartcontent">
	<div class="body">
		<ul class="list">
		<#assign goodsEvalutebyGoodsIdTag =newTag("goodsEvalutebyGoodsIdTag")>
		<#assign goodsEvalutes =goodsEvalutebyGoodsIdTag("{'goodsId':'${goodsId}','pageNo':'${pageNo}','toUrl':'${toUrl}'}")>
		<#if goodsEvalutes??>
			<#list goodsEvalutes.result as goodsEV>
			    <li>
				    <dl>
					    <dt>${goodsEV.gevalContent}</dt>
					    <#if goodsEV.gevalImage??>
					    <dd>
					    	<#list goodsEV.gevalImage?split(",") as gevalImage>  
							    <div class="img">
							    	<a href="#"><img src="${imgServer}${gevalImage}" alt="" title="" /></a>
							    </div> 
						    </#list>
						</dd><br>
						</#if>
				    </dl>
				    <span class="s3">
				    	<span class="creditevaluate" id="descraty" data-score="${goodsEV.gevalScore}"></span>
				    </span>
				    <span class="s4">
				    	${goodsEV.specInfo}
					</span>
				    <span class="s5">
				    	${goodsEV.gevalFrommembername}<br>
						${goodsEV.createTimeStr}<br>
					</span>
			    </li>
		    </#list>
		</#if>   
		</ul>
	</div>
</div>
<script>
	$(document).ready(function() {
		$(".creditevaluate").raty({
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
-->

			 <#assign evaluateGoodsTag = newTag("evaluateGoodsTag")>
		     <#assign evaluateGoodsList = evaluateGoodsTag("{'goodsId':'${goodsId}', 'pageSize':'10', 'pageNo':'${pageNo}'}")>
				<#if evaluateGoodsList?? && evaluateGoodsList!''>
			<#--		<p>&nbsp;&nbsp;商品评价（${evaluateGoodsList?size}）</p>  -->
					<#list evaluateGoodsList as evaluateGoods>
						<div class="font_top" style="border-bottom:1px;">
							<i>&nbsp;&nbsp;
								<#if evaluateGoods.gevalIsAnonymous==1>
									<img src="${base}/res/html5/img/tx_03.jpg"/>
									匿名
								<#else>
									<img src="${evaluateGoods.memberAvatar}" />
									${evaluateGoods.gevalFrommembername}
								</#if>
							</i>
							<h1 style="border-bottom:0;">${evaluateGoods.gevalContent}</h1>
							<h2 style="float: right; border-bottom: 1px solid #eee; padding-bottom: 2px;">${evaluateGoods.gevalAddTimeStr} &nbsp;&nbsp;&nbsp;&nbsp;产品分类：${evaluateGoods.gcName}</h2>
						</div>
					</#list>
				</#if>
