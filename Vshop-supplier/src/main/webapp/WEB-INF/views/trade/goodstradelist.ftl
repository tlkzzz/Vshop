<@p.header title="供应商中心-商品交易"/>
<script type="text/javascript" src="${base}/res/js/layer/layer.js" charset="utf-8"></script>
<div class="layout">
	<div class="sidebar"></div>
	<div class="right-content">
		<div class="path">
			<div>
				<a href="${base}">商品交易</a> 
				<span>&gt;</span> 
			</div>
		</div>
		<div class="main">
			<link rel="stylesheet" type="text/css" href="${base}/res/js/jquery-ui/themes/ui-lightness/jquery.ui.css">
			<div class="wrap">
				<div class="tabmenu">
					<ul class="tab pngFix">
						<li class="active"><a
							href="#?act=store&amp;op=store_coupon">商品交易列表</a></li>
					</ul>
				</div>
				<form method="get" action="${base}/tradegoods/list" target="_self" id="queryForm">
				</form>
				<table class="ncu-table-style">
					<thead>
						<tr>
							<th class="w10"></th>
							<th class="w120">序号</th>
							<th class="w130">货物名称</th> 
							<th class="w60">分类名称</th>
							<th class="w180">交易量</th>
							<th class="w60">交易额(元)</th>
							<th class="w80">销售地</th>
						</tr>
					</thead>
					<tbody>
						<#if datas?size gt 0>
							<#list datas as goodstrade>
								<tr class="bd-line">
									<td></td>
									<td>${goodstrade_index+1}</td>
									<td>
<!-- 									  <a href="${frontServer}/product/detail?id=${goodstrade.goodsId}" title="${goodstrade.goodsName}"> -->
											<#if goodstrade.goodsName?length lt 15>   
											     ${goodstrade.goodsName}
											<#else> 
											     ${goodstrade.goodsName[0..21]}... 
											</#if>
<!-- 									   </a> -->
									</td> 
									<td class="tl">
										${goodstrade.gcName}
									</td>
									<td class="goods-count">
									    ${goodstrade.tradeGoodsCount}
									</td>
									<td class="goods-price">
									      ${goodstrade.tradeGoodsPrice}
									 </td>
									<td class="goods-place">
									     ${goodstrade.provinceName}${goodstrade.cityName}
								</tr>
							</#list>
						<#else>
							<tr>
								<td colspan="8">暂无数据</td>
							</tr>
						</#if>
					</tbody>
					<tfoot>
						<tr>
					        <td colspan="20">
					        	<#import "/trade/page.ftl" as q><!--引入分页-->
				                <#if recordCount??>
				                    <@q.pager pageNo=pageNo pageSize=pageSize recordCount=recordCount toURL="${toUrl}"/>
				                </#if>
					        </td>
				    	</tr>
					</tfoot>
					
				</table>
			</div>
		  <script type="text/javascript" src="${base}/res/js/jquery-ui/i18n/zh-CN.js" charset="utf-8"></script>
	    </div>
	  </div>
	<div class="clear"></div>
<@p.footer/>