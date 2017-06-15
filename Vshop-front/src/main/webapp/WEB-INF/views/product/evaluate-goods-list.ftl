<script src="${base}/res/js/layer/layer.js"></script>
<link href="${base}/res/css/mycart.css" rel="stylesheet" type="text/css" />

<div id="mycartcontent">
	<div class="body">
		<ul class="list">
		    <li class="li" style="font-weight:bold">
			    <span class="s2">评价心得</span>
			    <span class="s3">顾客满意度</span>
			    <span class="s4">购买信息</span>
			    <span class="s5">评论者</span>
			</li>
			<input type="hidden" name="goodsId" value="${goodsId}"/>
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
		<div class="totals">
			<div class="del"></div>
			<label>评价数量共<span>${goodsEvalutes.totalRows}</span>条</label>	
			<label>
				<#import "/commons/goods_detail_page.ftl" as q>
				<#if goodsEvalutes.totalRows??>
					<@q.pager pageNo=goodsEvalutes.pageNo pageSize=goodsEvalutes.pageSize recordCount=goodsEvalutes.totalRows toURL="${toUrl}"/>
				</#if>
			</label>
		</div>
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

