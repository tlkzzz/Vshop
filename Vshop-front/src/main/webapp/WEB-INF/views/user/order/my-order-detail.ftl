<@p.userHeader title="订单详情"/>
<script type="text/javascript" src="${base}/res/js/dialog/dialog.js" id="dialog_js" charset="utf-8"></script> 
<script type="text/javascript" src="${base}/res/js/nc-sideMenu.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/utils.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/layer/layer.js" charset="utf-8"></script>
<link href="${base}/res/css/font-icons/style.css"  rel="stylesheet" />
<#assign orderDetailTag = newTag("orderDetailTag")/>
<#assign order = orderDetailTag("{'orderId':'${orderId}'}") />   
<div id="container" class="wrapper">

		<div class="wrap-shadow">
			<div class="wrap-all ncu-order-view">
				<h2>订单详情</h2>
				<#if order!=''>
				 <dl class="box">
					<dt>订单状态：</dt>
					<dd>
						<#if order.orderState==0>
		                    <strong><span style="color:#999">订单已取消</span></strong>
		                <#elseif order.orderState==10>
	                    	<strong><span style="color:#36C">待会员付款</span></strong>
		                <#elseif order.orderState==20>
		                    <strong><span style="color:#F30">会员已付款</span></strong>
		                <#elseif order.orderState==30>
		                	<strong><span style="color:#F30">商家已发货</span></strong>
		                <#elseif order.orderState==40>
		                	<strong><span style="color:#060">交易完成</span></strong>
		                <#elseif order.orderState==50>
		                	<strong>已提交，待确认</strong>
		                <#elseif order.orderState==60>
		                	<strong>已确认，待发货</strong>
		                </#if>
					</dd>
					<dt>订单编号：</dt>
					<dd>${order.orderSn}</dd>
					<dt>下单时间：</dt>
					<dd>${order.createTimeStr}</dd>
				</dl>
				<h3>订单信息</h3>
				<table class="ncu-table-style">
					<thead>
						<tr>
							<th class="w10"></th>
							<th class="w70"></th>
							<th>商品名称</th>
							<th>单价</th>
							<th>数量</th>
							<th>商品总价</th>
						</tr>
					</thead>
					<tbody>
					<#if order.orderGoodsList?size gt 0>
						<#list order.orderGoodsList as orderGoods>
						<tr class="bd-line">
							<td></td>
							<td>
								<div class="goods-pic-small">
									<span class="thumb size60">
										<i></i>
										<a target="_blank" href="${base}/product/detail?id=${orderGoods.goodsId}">
											<img style="display: inline;" src="${imgServer}${orderGoods.goodsImage}" onload="javascript:DrawImage(this,60,60);">
										</a>
									</span>
								</div>
							</td>
							<td>
								<dl class="goods-name">
									<dt>
										<a href="${base}/product/detail?id=${orderGoods.goodsId}" target="_blank">${orderGoods.goodsName}</a>
									</dt>
									<dd>${orderGoods.specInfo}</dd>
								</dl>
							</td>
							<td>
								<script type="text/javascript">
	              					var price = number_format(${orderGoods.goodsPrice},2);
	              					document.write(price);
	              				</script>
							</td>
							<td>${orderGoods.goodsNum}</td>
							<td>
								<script type="text/javascript">
	              					var goodsAmount = number_format(${orderGoods.goodsPrice}*${orderGoods.goodsNum},2);
	              					document.write(goodsAmount);
	              				</script>
							</td>
						</tr>
						</#list>
					</#if>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="20" class="transportation">订单总价：
							<b>¥
								<script type="text/javascript">
	              					var amount = number_format(${order.orderAmount},2);
	              					document.write(amount);
	              				</script>
							</b>
							</td>
						</tr>
						<tr>
							<td colspan="20" >
								<dt>商品总价：</dt>
								<dd>¥
								<script type="text/javascript">
	              					var goodsamount = number_format(${order.goodsAmount},2);
	              					document.write(goodsamount);
	              				</script>
	              				</dd>
	              				<dt>余额支付金额：</dt>
								<dd>¥
								<script type="text/javascript">
	              					var predepositAmount = number_format(${order.predepositAmount},2);
	              					document.write(predepositAmount);
	              				</script>
	              				</dd>
	              				<dt>运费价格：</dt>
								<dd>¥
								<script type="text/javascript">
	              					var shippingFee = number_format(${order.shippingFee},2);
	              					document.write(shippingFee);
	              				</script>
	              				</dd>
							</td>
						</tr>
					</tfoot>
				</table>
				<ul class="order_detail_list">
					<li>
						支付方式：${order.paymentName}
					</li>
					<li>下单时间：${order.createTimeStr?string('yyyy-MM-dd HH:mm:ss')}</li>
					<#if order.paymentTime??>
						<li>付款时间：${order.paymentTimeStr?string('yyyy-MM-dd HH:mm:ss')}</li>
					</#if>
					<#if order.orderMessage != null && order.orderMessage != ''>
	                    <li>会员附言：${order.orderMessage}</li>
	                </#if>
				</ul>
				<h3>物流信息</h3>
				<#if order.address??>
					<dl class="logistics">
						<dt>收 货 人：</dt>
						<dd>${order.address.trueName}&nbsp;</dd>
						<dt>电话号码：</dt>
						<dd>${order.address.telPhone}&nbsp;</dd>
						<dt>手机号码：</dt>
						<dd>${order.address.mobPhone}&nbsp;</dd>
						<dt class="cb">收货地址：</dt>
						<dd style="width: 90%;">${order.address.areaInfo}&nbsp;${order.address.address}</dd>
						<#if order.shippingCode??&&order.shippingCode!=''>
							<dt>物流单号：</dt>
							<dd>
								${order.shippingCode} 
								<a href="javascript:void(0)" onclick="kuaidi('${order.shippingCode}','${order.shippingExpressCode}')">查询物流</a>
							</dd>
						</#if>
					</dl>
				</#if>
				<h3>发票信息</h3>
				<dl class="logistics">
					<dd>${order.invoice}</dd>
				</dl>
				<h3>操作历史</h3>
				<ul class="log-list">
				<#if order.orderLogList?size gt 0>
					<#list order.orderLogList as orderlog>
					<li><span class="operator"> ${orderlog.operator} </span> 于
					<span class="log-time">${orderlog.logTimeStr}</span> 订单当前状态：
					<span class="order-status">${orderlog.stateInfo}</span> 下一状态：
						<span class="order-status">
							<#if orderlog.changeState==0>
				            	订单已取消
			                <#elseif orderlog.changeState==10>
			                   	等待会员付款
			                <#elseif orderlog.changeState==20>
			                	等待商家发货
			                <#elseif orderlog.changeState==30>
			                	商家已发货
			                <#elseif orderlog.changeState==40>
			                	交易已完成
			                <#elseif orderlog.changeState==50>
			                	订单已提交
			                <#elseif orderlog.changeState==60>
			                	订单已确认
			                </#if>
						</span>
					</li>
					</#list>
				</#if>
				</ul>
				<#else>
					<dl>
						<b style="font-size: 2em;">请查看自己商家下的订单</b>
					</dl>
				</#if>
			</div>
		</div>
</div>
<@p.userfooter/>
<script>
	var timestamp=new Date().getTime();
	function kuaidi(shippingCode,expressCode){
		layer.open({
			type:2,
            move: false,
            shade: false,
            title: '物流查询',
            content:["http://wap.kuaidi100.com/wap_result.jsp?rand="+timestamp+"&id="+expressCode+"&fromWeb=null&&postid="+shippingCode, 'yes'],
            area: ['500px', '500px'],
		});
	} 
</script>