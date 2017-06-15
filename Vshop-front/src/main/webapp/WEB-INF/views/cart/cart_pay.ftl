<@p.header title="订单支付"/>
<link href="${base}/res/css/home_cart.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${base}/res/js/layer/layer.js" charset="utf-8"></script>
<style type="text/css">
  #navBar { display: none !important;} 
</style>
<ul class="flow-chart"> 
  <li class="step a2" title="确认购物清单"></li>
  <li class="step b2" title="确认收货人资料及送货方式"></li>
  <li class="step c1" title="购买完成"></li>
</ul>
<div class="content wrapper">
    <div class="cart-order-info">
      <div class="title">
      	<span class="all-goods-name">${goodsNames}</span><span class="goto-order"><a href="${base}/user/list" target="_blank">订单详情</a></span>
      	<span class="order-price" title="应付金额：" id="order_amount">
      		<script type="text/javascript">
      			var amount = number_format(${ordersAmount},2);
				document.write("&yen;"+amount);      		
      		</script>
      	</span>
      	</div>
      <div class="intro">
      	<#if orderList??>
      		<#list orderList as order>
		        <dl>
		          <dt>商家：</dt>
		          <dd>${order.storeName}</dd>
		        </dl>
		        <dl>
		          <dt>下单时间：</dt>
		          <dd>${order.createTimeStr?string("yyyy-MM-dd")}</dd>
		        </dl>
		        <dl>
		          <dt>商品：</dt>
		          <#if	order.orderGoodsList??>
		          	<#list order.orderGoodsList as orderGoods>
		          		<dd>${orderGoods.goodsName}&nbsp;&nbsp;&nbsp;&nbsp;${orderGoods.specInfo}</dd>
		          		<dd>&nbsp;&nbsp;&nbsp;&nbsp;</dd>
		          	</#list>
		          </#if>
		        </dl>
        	</#list>
        </#if>
      </div>
    </div>
        <div class="cart-order-pay">
      <div class="title">
        <h3>选择支付方式</h3>
        <ul class="tabs-nav">
        	<#if paytype==1><li class="tabs-selected"><a href="javascript:void(0)">在线支付</a></li></#if>
        	<#if paytype==2><li class="tabs-selected"><a href="javascript:void(0)">货到付款</a></li></#if>
        </ul>
      </div>
      <!-- 判断是否余额支付完毕 -->
      <#if ordersAmount==0>
      		<div class="tabs-panel">
		        <ul class="cart-defray">
		        	<span style="color:green; font-size: 30px;font-family: \5FAE\8F6F\96C5\9ED1;font-weight: normal;margin-bottom: 15px;">
		      			支付成功,等待商家发货
		      		</span>
		        </ul>
	      	</div>
	      	<!-- online end -->
	      	<div class="ml50 mb30"><a href="javascript:check();" class="cart-button">查看订单</a></div>
	      	<div class="clear"></div>
      	  <#else>	
	      <#if paytype==1>
		      <div class="tabs-panel">
		        <ul class="cart-defray">
		          <li>
		          	<#assign paymentTag = newTag("paymentTag") />
					<#assign paymentlist = paymentTag("{'isshow':'1'}") />
					<#if paymentlist??>
						<#list paymentlist as payment>
				            <label class="radio">
				              <input id="payment_predeposit" type="radio" name="paymentCode" value="${payment.paymentCode}" extendattr="predeposit" checked/>
				              <input type="hidden" name="paymentId" value="${payment.paymentId}"/>
				            </label>
				            <span class="logo"><img src="${imgServer}${payment.paymentLogo}" alt="${payment.paymentName}" title="${payment.paymentName}" onload="javascript:DrawImage(this,125,50);" /></span>
				            <dl class="explain">
				              <dt></dt>
				              <dd></dd>
				            </dl>
				        </#list>
		            </#if>
		          </li>
		        </ul>
		      </div>
		      <!-- online end -->
		      <div class="ml50 mb30"><a href="javascript:void(0);" class="cart-button" onclick="goPay()" >确认支付</a></div>
		      <div class="clear"></div>
	      </#if>
	      <#if paytype==2>
	      	<div class="tabs-panel">
		        <ul class="cart-defray">
		        	<span style="color:green; font-size: 30px;font-family: \5FAE\8F6F\96C5\9ED1;font-weight: normal;margin-bottom: 15px;">
		      			提交成功,等待商家确认
		      		</span>
		        </ul>
	      	</div>
	      	<!-- online end -->
	      	<div class="ml50 mb30"><a href="${base}/user/list" class="cart-button">查看订单</a></div>
	      	<div class="clear"></div>
	      </#if>
      </#if>
    </div>
</div>
<script type="text/javascript">
	var APP_BASE = '${base}';
    function goPay() {
        var paySn = '${orderPaySn}';
        var paymentCode = $("input[name='paymentCode']:checked").val();
       	var paymentId = $("input[name='paymentCode']:checked").parent().find("input[name='paymentId']").val();
        location.href = APP_BASE + "/cart/orderpay?paysn="+paySn+"&paymentCode="+paymentCode+"&paymentId="+paymentId;
        /* $.ajax({
            url: '${base}/cart/goToPay',
            type: 'POST',
            data: {"paySn": paySn, "paymentId": paymentId},
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    layer.msg(data.msg,{icon:1});
                } else {
                    layer.msg(data.msg,{icon:2});
                }
            }
        }); */
    }
</script>
<@p.footer />