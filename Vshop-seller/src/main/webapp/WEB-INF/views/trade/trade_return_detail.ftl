<link href="${base}/res/css/member.css" rel="stylesheet" type="text/css">
<link href="${base}/res/css/base.css" rel="stylesheet" type="text/css">
<link href="${base}/res/css/member_user.css" rel="stylesheet" type="text/css">
<script src="${base}/res/js/jquery.js" charset="utf-8"></script>
<script src="${base}/res/js/common.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/layer/layer.js" charset="utf-8"></script>
<div class="eject_con" style="width: 500px">
  <div id="warning"></div>
  <dl>
    <dt>商品编号：</dt>
    <dd class="goods-num">${returnOrder.returnSn}</dd>
  </dl>
  <dl>
    <dt>退单时间：</dt>
    <dd class="goods-time">${returnOrder.createTimeStr?string('yyyy-MM-dd HH:mm:ss')}</dd>
  </dl>
  <dl>
    <dt>商城名：</dt>
    <dd>${returnOrder.storeName}</dd>
  </dl>
  <dl>
    <dt>审核状态：</dt>
    <dd>
    	<#if returnOrder.returnState==1>
			待审核
		<#elseif returnOrder.returnState==2>
			审核通过
		<#elseif returnOrder.returnState==3>
			已拒绝
		</#if>
    </dd>
  </dl>
  <dl>
    <dt>退单数量：</dt>
    <dd><strong>${returnOrder.returnGoodsNum}</strong></dd>
  </dl>
  <dl>
    <dt>退单原因：</dt>
    <dd>${returnOrder.buyerMessage}</dd>
  </dl>
  <dl>
    <dt>备注信息：</dt>
    <dd>${returnOrder.returnMessage}</dd>
  </dl>
    <table class="ncu-table-style order bc mt20 mb10" style="width:96%;">
    <thead>
    </thead>
    <tbody>
      <tr>
        <th colspan="3" class="tc">商品名称</th>
        <th class="w70 tc">退单数量</th>
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
			          				<img src="${imgOssServer}${returnGoods.goodsImage}" onload="javascript:DrawImage(this,60,60);" width="60" height="15">
			          			</a>
			          		</span>
			          	</div>
			         </td>
		        	 <td>
		        	 	<dl class="goods-name" style="width:auto;">
		          			<dt style="width:auto; white-space: normal;"><a target="_blank" href="${base}/product/detail?id=${returnGoods.goodsId}">${returnGoods.goodsName}</a></dt>
		          			<dd style="width:auto;">${returnGoods.specInfo}</dd>
		          		</dl>
		          	</td>
		        	<td class="bdl bdr">${returnGoods.goodsNum}</td>
		      	</tr>
		      </#list>
		  </#if>  
          
    </tbody>
  </table>
</div>