<link href="${base}/res/css/member.css" rel="stylesheet" type="text/css">
<link href="${base}/res/css/base.css" rel="stylesheet" type="text/css">
<link href="${base}/res/css/member_user.css" rel="stylesheet" type="text/css">
<script src="${base}/res/js/jquery.js" charset="utf-8"></script>
<script src="${base}/res/js/common.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/layer/layer.js" charset="utf-8"></script>
<div class="eject_con" style="width: 500px">
  <div id="warning"></div>
  	<input type="hidden" name="orderId" value="${order.orderId}"/> 
    <dl>
      <dt>订单金额：</dt>
      	<dd>
			<script type="text/javascript">
				var amount = number_format(${order.orderAmount},2);
				document.write(amount);
			</script>
		</dd>
    </dl>
    <dl>
      <dt class="required"><em class="pngFix"></em>退款金额：</dt>
      <dd>
        <input type="text" class="text w50" name="order_refund" value="${order.orderAmount}">
      </dd>
    </dl>
    <dl>
      <dt class="required"><em class="pngFix"></em>退款原因：</dt>
      <dd>
        <textarea name="buyer_message" rows="3" class="textarea w250"></textarea>
      </dd>
    </dl>
    <dl>
      <dt>&nbsp;</dt>
      <dd>
        <p class="hint">退款只能申请一次，建议与商家沟通后认真填写。<br>如果商家同意，将用线下支付的方式付款给你，请及时查收。</p>
      </dd>
    </dl>
    <dl class="bottom">
      <dt>&nbsp;</dt>
      <dd>
        <input type="submit" class="submit" id="confirm_button" value="确定">
      </dd>
    </dl>
</div>
<script type="text/javascript">
	$(function(){
		$("input[name='order_refund']").val(toDecimal2(${order.orderAmount}));
		$("input[name='order_refund']").change(function(){
			var reg = /^[-\+]?\d+(\.\d+)?$/; //检验是否为数字的正则表达式
			var refundPrice = $(this).val();
			if(refundPrice==''){
				$(this).val(toDecimal2(${order.orderAmount}));
			}else if(!reg.test(refundPrice)){
				layer.msg("请您输入正确的金额!",{icon:2});
				$(this).val(toDecimal2(${order.orderAmount}));
			}else if(refundPrice>${order.orderAmount}){
				layer.msg("请您输入正确的金额!",{icon:2});
				$(this).val(toDecimal2(${order.orderAmount}));
			}else{
				$(this).val(toDecimal2(refundPrice));
			}
		});
	});
	
	//准换价格格式
	function toDecimal2(x) {   
	    var f = parseFloat(x);   
	    if (isNaN(f)) {   
	        return false;   
	    }   
	    var f = Math.round(x*100)/100;   
	    var s = f.toString();   
	    var rs = s.indexOf('.');   
	    if (rs < 0) {   
	        rs = s.length;   
	        s += '.';   
	    }   
	    while (s.length <= rs + 2) {   
	        s += '0';   
	    }   
	    return s;   
	}  
</script>