<@layout.head>
<script type="text/javascript" src="${base}/res/js/jquery-ui/jquery.ui.js"></script>
<script type="text/javascript" src="${base}/res/js/jquery-ui/i18n/zh-CN.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="${base}/res/js/jquery-ui/themes/ui-lightness/jquery.ui.css"  />
</@layout.head>
<@layout.body>
<div class="page">
    <table class="table tb-type2 order">
        <tbody>
        <tr class="space">
            <th colspan="15">订单状态</th>
        </tr>
        <tr>
            <td colspan="2">
                <ul>
                    <li>
                        <strong>订单号:</strong>${order.orderSn}
                        ( 支付单号 : ${order.paySn} )
                    </li>
                    <li><strong>订单状态:</strong>
                        <#if order.orderState==10>待付款</#if>
                        <#if order.orderState==20>待发货</#if>
                        <#if order.orderState==30>待收货</#if>
                        <#if order.orderState==40>交易完成</#if>
                        <#if order.orderState==0>已取消</#if>
                    </li>
                    <li>
                    	<strong>支付金额:</strong>
                    	<span class="red_common">¥
                    		<script type="text/javascript">
	    						var amount = number_format(${order.orderAmount},2);
	    						document.write(amount);
	    					</script>
                    	</span>
                        <#if (order.refundAmount > 0)>
                            (退款:¥
                            	<script type="text/javascript">
		    						var amount = number_format(${order.refundAmount},2);
		    						document.write(amount);
		    					</script>)
                        </#if>
                    </li>
                    <li><strong>余额支付金额:</strong>¥
                    	<script type="text/javascript">
    						var predepositAmount = number_format(${order.predepositAmount},2);
    						document.write(predepositAmount);
    					</script>
                    </li>
                    <li><strong>商品总额:</strong>¥
                    	<script type="text/javascript">
    						var goodsamount = number_format(${order.goodsAmount},2);
    						document.write(goodsamount);
    					</script>
                    </li>
                    <li><strong>运费:</strong>
                    	<#if order.shippingFee??>
					    	<#if order.shippingFee!=0>
					    		¥
		                    	<script type="text/javascript">
		    						var ship = number_format(${order.shippingFee},2);
		    						document.write(ship);
		    					</script>
		    				<#else>
	              				（免运费）
			          		</#if>
			          	<#else>
			          		（免运费）
			          	</#if>
                    </li>
                </ul>
            </td>
        </tr>
        <tr class="space">
            <th colspan="2">订单详情</th>
        </tr>
        <tr>
            <th>订单信息</th>
        </tr>
        <tr>
            <td><ul>
                <li><strong>会员：</strong>${order.buyerName}</li>
                <li><strong>商家：</strong>${order.storeName}</li>
                <li><strong>支付方式：</strong>${order.paymentName}</li>
                <li><strong>下单时间：</strong>${order.createTimeStr?string("yyyy-MM-dd HH:mm:ss")}</li>
                <#if order.paymentTimeStr??>
                    <li><strong>支付时间：</strong>${order.paymentTimeStr?string("yyyy-MM-dd HH:mm:ss")}</li>
                </#if>
                <#if order.finnshedTimeStr??>
                    <li><strong>完成时间：</strong>${order.finnshedTimeStr?string("yyyy-MM-dd HH:mm:ss")}</li>
                </#if>
                <#if order.orderMessage != null && order.orderMessage != ''>
                    <li><strong>会员附言：</strong>${order.orderMessage}</li>
                </#if>
            </ul></td>
        </tr>
        <tr>
            <th>收货人及发货信息</th>
        </tr>
        <tr>
            <td>
                <ul>
                	<#if order.address??>
               			<li><strong>收货人姓名：</strong>${order.address.trueName}</li>
               			<li><strong>收货人手机：</strong>${order.address.mobPhone}</li>
               			<li><strong>收货人电话：</strong>${order.address.telPhone}</li>
               			<li><strong>收货地址：</strong>${order.address.areaInfo}&nbsp;${order.address.address}</li>
               			<li><strong>邮编：</strong>${order.address.zipCode}</li>
                	</#if>
                </ul>
            </td>
        </tr>
        <tr>
            <th>发票信息</th>
        </tr>
        <tr>
            <td>
                <ul>
                	<li>${order.invoice}</li>
                </ul>
            </td>
        </tr>
        <tr>
            <th>商品信息</th>
        </tr>
        <tr>
            <td><table class="table tb-type2 goods ">
                <tbody>
                <tr>
                    <th></th>
                    <th>商品信息</th>
                    <th class="align-center">单价</th>
                    <th class="align-center">数量</th>
                    <th class="align-center">小计</th>
                </tr>
                <#list order.orderGoodsList as goods>
                    <tr>
                        <td class="w60 picture"><div class="size-56x56"><span class="thumb size-56x56"><i></i><a href="javascript:;" target="_blank"><img src="${imgServer}${goods.goodsImage}" onload="javascript:DrawImage(this,60,60);" /> </a></span></div></td>
                        <td class="w50pre"><p><a href="${frontServer}/product/detail?id=${goods.goodsId}" target="_blank">${goods.goodsName}</a></p></td>
                        <td class="w96 align-center">
                        	<span class="red_common">￥
                        		<script type="text/javascript">
		    						var price = number_format(${goods.goodsPrice},2);
		    						document.write(price);
		    					</script>
                        	</span>
                        </td>
                        <td class="w96 align-center">${goods.goodsNum}</td>
                        <td class="w96 align-center">
                        	<span class="red_common">￥
                        		<script type="text/javascript">
		    						var payprice = number_format(${goods.goodsPrice*goods.goodsNum},2);
		    						document.write(payprice);
		    					</script>
                        	</span>
                        </td>
                        
                    </tr>
                </#list>
                </tbody>
            </table></td>
        </tr>
        <#if vo.refundList?? && vo.refundList?size != 0>
            <tr>
                <th>退款记录</th>
            </tr>
            <#list vo.refundList as refund>
                <tr>
                    <td>发生时间：${refund.addTimeStr?string("yyyy-MM-dd HH:mm:ss")}&emsp;&emsp;退款单号：${refund.refundSn}&emsp;&emsp;退款金额：￥${refund.refundAmount}&emsp;备注：${refund.goodsName}</td>
                </tr>
            </#list>
        </#if>
        <#if vo.returnList?? && vo.returnList?size != 0>
        <tr>
            <th>退货记录</th>
        </tr>
            <#list vo.returnList as return>
            <tr>
                <td>发生时间：${return.addTimeStr?string("yyyy-MM-dd HH:mm:ss")}&emsp;&emsp;退款单号：${return.refundSn}&emsp;&emsp;退款金额：￥${return.refundAmount}&emsp;备注：${return.goodsName}</td>
            </tr>
            </#list>
        </#if>
        <#if order.orderLogList?? && order.orderLogList?size != 0>
        <tr>
            <th>操作历史</th>
        </tr>
            <#list order.orderLogList as log>
            <tr>
                <td>
                    ${log.operator}&emsp;于&emsp;${log.createTimeStr?string("yyyy-MM-dd HH:mm:ss")}&emsp;${log.stateInfo}
                </td>
            </tr>
            </#list>
        </#if>
        </tbody>
        <tfoot>
        <tr class="tfoot">
            <td><a href="JavaScript:void(0);" class="btn" onclick="history.go(-1)"><span>返回</span></a></td>
        </tr>
        </tfoot>
    </table>
</div>
</@layout.body>