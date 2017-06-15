<@p.userHeader title="余额充值"/>
<link href="${base}/res/css/home_cart.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${base}/res/js/layer/layer.js" charset="utf-8"></script>
<style type="text/css">
  #navBar { display: none !important;} 
</style>
<div class="content wrapper">
    <div class="cart-order-info">
      <div class="title">
      	<span class="goto-order">余额充值</span>
      	<span class="order-price" title="应付金额：" id="order_amount">
      		<script type="text/javascript">
      			var amount = number_format(${predepositRecharge.pdrAmount},2);
				document.write("&yen;"+amount);      		
      		</script>
      	</span>
      </div>
      <div class="intro">
      	<div align="center" style="height: 100px">
      		<span style="color:green; font-size: 30px;font-family: \5FAE\8F6F\96C5\9ED1;font-weight: normal;margin-bottom: 15px;">
      			您已申请账户余额充值，请立即在线支付！
      		</span>
      	</div>
      </div>
    </div>
    <div class="cart-order-pay">
	      <div class="title">
	        <h3>选择支付方式</h3>
	        <ul class="tabs-nav">
        	<li class="tabs-selected"><a href="javascript:void(0)">在线支付</a></li>
        </ul>
	      </div>
	      <!-- online begin -->
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
	   </div>
</div>
<script type="text/javascript">
	var APP_BASE = '${base}';
	function goPay() {
        var pdrSn = '${predepositRecharge.pdrSn}';
        var paymentCode = $("input[name='paymentCode']:checked").val();
       	var paymentId = $("input[name='paymentCode']:checked").parent().find("input[name='paymentId']").val();
        location.href = APP_BASE + "/predeposit/gotopay?pdrSn="+pdrSn+"&paymentCode="+paymentCode+"&paymentId="+paymentId;
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
<@p.userfooter />