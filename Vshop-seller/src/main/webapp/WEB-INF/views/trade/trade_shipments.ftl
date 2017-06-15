<@p.header title="商家中心-发货"/>
<script type="text/javascript" src="${base}/res/js/common_select.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/ajaxfileupload/ajaxfileupload.js"></script>
<div class="layout">
    <div class="sidebar"></div>
 	<div class="right-content">
        <div class="path">
      		<div>
      			<a href="#">商家中心</a> <span></span>
                <a href="#"/>发货</a><span></span>
                <#if orderState==20 >
                	等待发货的订单
                <#elseif orderState==30 >
                	发货中的订单
                <#elseif orderState==40 >
                	已收到货的订单
                </#if>
            </div>
    	</div>
        <div class="main">
			<div class="wrap">
  				<div class="tabmenu">
    				<ul class="tab pngFix">
  						<li <#if orderState==20 >class="active"<#else>class="normal"</#if>>
  							<a  href="${base}/transport/shipments?orderState=20">等待发货的订单</a>
  						</li>
  						<li <#if orderState==30 >class="active"<#else>class="normal"</#if>>
  							<a  href="${base}/transport/shipments?orderState=30">发货中的订单</a>
  						</li>
  						<li <#if orderState==40 >class="active"<#else>class="normal"</#if>>
  							<a  href="${base}/transport/shipments?orderState=40">已收到货的订单</a>
  						</li>
  					</ul>
  				</div>
  				<form method="post" action="${base}/transport/shipments" target="_self" id="queryForm">
    				<table class="search-form">
      					<input type="hidden" name="act" value="deliver" />
      					<input type="hidden" name="op" value="search_deliver" />
      					<input type="hidden" name="orderState" value="${orderState}"/>
      					<tbody>
	      					<tr>
	        					<td>&nbsp;</td>
	        					<th>订单编号：</th>
	        					<td class="w150"><input type="text" class="text" name="orderSn" value="${orderSn}"  placeholder="输入您要查询的订单编号" /></td>
	        					<td class="w90 tc"><input type="submit"  class="submit"value="搜索" /></td>
	      					</tr>
	  					</tbody>
    				</table>
  				</form>
    			<table class="ncu-table-style order deliver">
	    			<#if orderLists??>
	    				<#if orderLists?size != 0>
	    					<#list orderLists as order>
		    					<tbody>
									<tr>
        								<td colspan="20" class="sep-row"></td>
      								</tr>
      								<tr>
        								<th colspan="20">
        									<span class="fr mr30"></span>
        									<span class="ml5">订单编号：
        										<span class="goods-num">${order.orderSn}</span> 
        										<span class="ml20">下单时间：<em class="goods-time">${order.createTimeStr?string('yyyy-MM-dd HH:mm:ss')}</em></span> 
        										<span class="ml20"></span>
        									</span>
        								</th>
      								</tr>
      								<#if order.orderGoodsList?size gt 0>
			      						<#assign rowsize = order.orderGoodsList?size>
			      						<#list order.orderGoodsList as orderGoods>
		            						<tr>
		        								<td class="bdl w10"></td>
		        								<td class="w70">
		        									<div class="goods-pic-small">
		        										<span class="thumb size60"><i></i>
		        											<a href="${frontServer}/product/detail?id=${orderGoods.goodsId}" target="_blank">
		        												<img src="${imgOssServer}${orderGoods.goodsImage}" onload="javascript:DrawImage(this,60,60);" width="60" height="15">
		        											</a>
		        										</span>
		        									</div>
		        								</td>
		        								<td class="tl goods-info">
		        									<dl>
		            									<dt><a target="_blank" href="${frontServer}/product/detail?id=${orderGoods.goodsId}">${orderGoods.goodsName}</a></dt>
		            									<dd>${orderGoods.specInfo}</dd>
		            									<dd class="tr">
		            										<script type="text/javascript">
								              					var price = number_format(${orderGoods.goodsPrice},2);
								              					document.write(price);
								              				</script>
								              				&nbsp;x&nbsp;${orderGoods.goodsNum}
		            									</dd>
		          									</dl>
		          								</td>
		          								<#if orderGoods_index == 0>
			                						<td class="bdl bdr order-info w400" rowspan="${orderGoods?size}">
			                							<dl>
			            									<dt>客户：</dt>
			            									<dd class="vm">
			            										${order.buyerName} 
			                                        		</dd>
			          									</dl>
			          									<#if order.addressList??>
				                							<#list order.addressList as address>
					          									<dl>
					            									<dt>收货信息：</dt>
					            									<dd class="ts">
					            										<span>${address.areaInfo}&nbsp;&nbsp;${address.address}，</span>
					            										<span class="ml5">${address.zipCode}，</span>
					            										<span class="ml5">${address.trueName}，</span>
					            										<span class="ml10">${address.telPhone}</span>
					            										<span class="ml10">${address.mobPhone}</span>
					            									</dd>
					          									</dl>
					          								</#list>
				          								</#if>
			          									<dl>
			            									<dt>运费:</dt>
			            									<dd>
			            										<#if order.shippingFee??>
													          		<#if order.shippingFee!=0>
													          			
													          			<script type="text/javascript">
											              					var shipFee = number_format(${order.shippingFee},2);
											              					document.write(shipFee);
											              				</script>
											              			<#else>
											              				（免运费）
													          		</#if>
													          	</#if>
			            									</dd>
			          									</dl>
			          									<#if orderState==20>
				          									<dl>
				                                    			<dt>&nbsp;</dt>
				            									<dd><a href="${base}/transport/shipments_deliver?orderId=${order.orderId}" class="ncu-btn6 mr10 fr">发货</a></dd>
				                      						</dl>
				                      					<#elseif orderState==30>
				                      						<dl>
				                                    			<dt>&nbsp;</dt>
				            									<dd><a href="${base}/transport/shipments_deliver_update?orderId=${order.orderId}" class="ncu-btn2 mr10 fr">编辑发货信息</a></dd>
				                      						</dl>
			                      						</#if>
			                      					</td>
		                      					</#if>
		              						</tr>
              							</#list>
              						</#if>
			 					</tbody>
			 				</#list>
		    				<#else>	
		    				<tbody>
								<tr>
		        					<td colspan="20" class="norecord"><i>&nbsp;</i><span>暂无符合条件的数据记录</span></td>
		      					</tr>
		 					</tbody>
		 				</#if> 	
	 				</#if>
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
    	</div>
  	</div>
    <div class="clear"></div>
</div>
<@p.footer/>