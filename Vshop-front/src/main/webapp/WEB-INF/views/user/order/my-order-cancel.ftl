<link href="${base}/res/css/member.css" rel="stylesheet" type="text/css">
<link href="${base}/res/css/base.css" rel="stylesheet" type="text/css">
<link href="${base}/res/css/member_user.css" rel="stylesheet" type="text/css">
<script src="${base}/res/js/jquery.js" charset="utf-8"></script>
<div class="eject_con" style="width: 500px">
    <input type="hidden" name="orderSn" id="orderSn" value="${orderSn}">
    <h2>您确定要取消下面的订单吗?</h2>
    <dl>
      <dt>订单号：</dt>
      <dd><span class="num">${orderSn}</span></dd>
    </dl>
    <dl>
      <dt>取消原因：</dt>
      <dd>
        <ul class="checked">
          <li>
            <input type="radio" checked="" name="state_info" id="d1" value="改买其他商品">
            <label for="d1">改买其他商品</label>
          </li>
          <li>
            <input type="radio" name="state_info" id="d2" value="改用其他方式购买">
            <label for="d2">改用其他方式购买</label>
          </li>
          <li>
            <input type="radio" name="state_info" id="d3" value="从其他商家购买">
            <label for="d3">从其他商家购买</label>
          </li>
          <li>
            <input type="radio" name="state_info" flag="other_reason" id="d4" onclick="selectOther(this)" value="其他原因">
            <label for="d4">其他原因</label>
          </li>
          <textarea style="display: none;" name="state_info1" cols="30" rows="3" id="other_reason_input"></textarea>
        </ul>
      </dd>
    </dl>
    <dl class="bottom">
      <dt>&nbsp;</dt>
      <input type="submit" id="confirm_button" class="submit" value="确定">
    </dl>
</div>
<script type="text/javascript">
	function selectOther(obj){
		if($("#other_reason_input").css('display')=="inline-block"){
			$(".checked").find("li").css("display","");
			$("#other_reason_input").css("display","none");
			$("#d1").attr("checked",true);
		}else{
			$(".checked").find("li").css("display","none");
			$(obj).parent().css("display","");
			$("#other_reason_input").css("display","");
		}
	}
</script>