<link href="${base}/res/css/member.css" rel="stylesheet" type="text/css">
<link href="${base}/res/css/base.css" rel="stylesheet" type="text/css">
<link href="${base}/res/css/member_user.css" rel="stylesheet" type="text/css">
<script src="${base}/res/js/jquery.js" charset="utf-8"></script>
<div class="eject_con" style="width: 500px">
  <form method="post" action="" >
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
            <textarea name="state_info1" cols="30" rows="4" id="other_reason_input"></textarea>
       		<span id="error" style="color:red;"></span>
        </ul>
      </dd>
    </dl>
    <dl class="bottom">
      <dt>&nbsp;</dt>
      <input type="submit" id="confirm_button" class="submit" value="确定">
    </dl>
  </form>
</div>
<script type="text/javascript">
	$(function(){
		$("#other_reason_input").change(function(){
			if($(this).val()!=''){
				$("#error").html('');
			} 
		});
	});
</script>