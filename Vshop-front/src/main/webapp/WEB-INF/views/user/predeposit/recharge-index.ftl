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
      </div>
      <div class="intro">
      	<form method="post" id="profile_form" action="${base}/predeposit/recharge">
      	  <dl>
	        <dt class="required"><em class="pngFix"></em>充值账户：</dt>
	        <dd>
	        	${member.memberName}
	        </dd>
	      </dl>		
		  <dl>
	        <dt class="required"><em class="pngFix"></em>充值金额：</dt>
	        <dd>
	          <input type="text" maxlength="16" class="text" name="amount" />
	          <label for="amount" generated="true" class="error"></label>
	        </dd>
	      </dl>
	      <dl>
	        <dt>&nbsp;</dt>
	        <dd>
	          <div class="ml50 mb30"><a href="javascript:void(0);" class="cart-button" onclick="goPay();" >下一步</a></div>
	        </dd>
	      </dl>
		</form>
		<div class="clear"></div>
      </div>
      <div class="cart-order-pay"></div>
    </div>
</div>
<script type="text/javascript">
	$(function(){
		$("input[name='amount']").change(function(){
			var reg = /^[-\+]?\d+(\.\d+)?$/; //检验是否为数字的正则表达式
			var amount = $(this).val();
			if(amount==''){
				$(this).val(toDecimal2(0));
			}else if(!reg.test(amount)){
				layer.msg("请您输入正确的金额!",{icon:2});
				$(this).val(toDecimal2(0));
			}else{
				$(this).val(toDecimal2(amount));
			}
		});
	});
	function goPay(){
		var reg = /^[-\+]?\d+(\.\d+)?$/; //检验是否为数字的正则表达式
		var amount = $("input[name='amount']").val();
		if(amount==''){
			layer.msg("请您输入要充值的金额!",{icon:2});
			return false;
		}else if(!reg.test(amount)){
			layer.msg("请您输入正确的金额!",{icon:2});
			return false;
		}
		$("#profile_form").submit();
	}
	
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
<@p.userfooter />