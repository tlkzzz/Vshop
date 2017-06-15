<link href="${base}/res/css/member.css" rel="stylesheet" type="text/css">
<link href="${base}/res/css/base.css" rel="stylesheet" type="text/css">
<link href="${base}/res/css/member_user.css" rel="stylesheet" type="text/css">
<script src="${base}/res/js/jquery.js" charset="utf-8"></script>
<div class="eject_con" style="width: 500px">
    <input type="hidden" name="orderSn" id="orderSn" value="${orderSn}">
    <h2 style="line-height: 20px;font-weight: 600;background-color: #FEFEDA;color: #630;text-align: left;width: 90%;padding: 8px 16px;margin: 5px auto 5px auto;border: solid 1px #FFE8C2;">
    	您是否确已经收到以下订单的货品??</h2>
    <dl >
      <dt style="color: #555;text-align: right;text-overflow: ellipsis;white-space: nowrap;width: 29%;float: left;">订单号：</dt>
      <dd><span style="font-weight: 600;color: #390;">${orderSn}</span></dd>
    </dl>
    <dl>
    	<p class="hint pl10 pr10">请注意： 如果你尚未收到商品请不要点击“确认”。大部分被骗案件都是由于提前确认付款被骗的，请谨慎操作！ </p>
    </dl>
    <dl class="bottom">
      <dt>&nbsp;</dt>
       <input type="submit" id="confirm_button" class="submit" value="确定">
    </dl>
</div>