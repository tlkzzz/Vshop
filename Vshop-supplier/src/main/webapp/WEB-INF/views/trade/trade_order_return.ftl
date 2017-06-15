<link href="${base}/res/css/member.css" rel="stylesheet" type="text/css">
<link href="${base}/res/css/base.css" rel="stylesheet" type="text/css">
<link href="${base}/res/css/member_user.css" rel="stylesheet" type="text/css">
<div class="eject_con" style="width: 500px">
  <div id="warning"></div>
  <table class="ncu-table-style order bc mt20 mb10" style="width:96%;">
  	<input name="returnId" value="${returnOrder.returnId}" type="hidden"/>
    <thead>
    </thead>
    <tbody>
      	<tr>
        	<th colspan="3" class="tc">商品名称</th>
       	 	<th class="w70 tc">退货数量</th>
      	</tr>
      	<#if returnOrder.returnGoodsList??>
      		<#list returnOrder.returnGoodsList as returnGoods>
		        <tr>
		          	<td class="bdl w10"></td>
		          	<td>
		          		<div class="goods-pic-small">
			          		<span class="thumb size60">
			          			<i></i>
			          			<a target="_blank" href="${frontServer}/product/detail?id=${returnGoods.goodsId}">
			          				<img src="${imgServer}${returnGoods.goodsImage}" onload="javascript:DrawImage(this,60,60);" width="60" height="15">
			          			</a>
			          		</span>
			          	</div>
			         </td>
		        	 <td>
		        	 	<dl class="goods-name" style="width:auto;">
		          			<dt style="width:auto; white-space: normal;"><a target="_blank" href="${frontServer}/product/detail?id=${returnGoods.goodsId}">${returnGoods.goodsName}</a></dt>
		          			<dd style="width:auto;">${returnGoods.specInfo}</dd>
		          		</dl>
		          	</td>
		        	<td class="bdl bdr">${returnGoods.goodsNum}</td>
		      	</tr>
		      </#list>
		  </#if>
     </tbody>
  </table>
  <dl>
    <dt>退货原因：</dt>
    <dd>${returnOrder.buyerMessage}</dd>
  </dl>
    <dl>
      <dt class="required"><em class="pngFix"></em>是否同意：</dt>
      <dd>
        <label>
          <input type="radio" name="return_state" value="2" checked/>
          是 </label>
        <label>
          <input type="radio" name="return_state" value="3">
          否 </label>
      </dd>
    </dl>
    <dl>
      <dt class="required"><em class="pngFix"></em>备注信息：</dt>
      <dd>
        <textarea name="return_message" class="textarea w300"></textarea>
      </dd>
    </dl>
    <dl class="bottom">
      <dt>&nbsp;</dt>
      <dd>
        <input type="submit" class="submit" id="confirm_button" name="confirm_button" value="确定">
      </dd>
    </dl>
</div>
