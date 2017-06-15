<link href="${base}/res/css/member.css" rel="stylesheet" type="text/css">
<link href="${base}/res/css/base.css" rel="stylesheet" type="text/css">
<link href="${base}/res/css/member_user.css" rel="stylesheet" type="text/css">
<script src="${base}/res/js/jquery.js" charset="utf-8"></script>
<script src="${base}/res/js/common.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/layer/layer.js" charset="utf-8"></script>
<div class="eject_con" style="width: 500px">
  <div id="warning"></div>
    <table class="order ncu-table-style bc mt20" style="width:96%;">
      <input name="orderId" value="${order.orderId}" type="hidden"/>
      <tbody>
        <tr>
          <th colspan="3" class="tc">商品名称</th>
          <th class="tc">可退数量</th>
          <th class="tc">本次退货</th>
        </tr>
        <#if order.orderGoodsList??>
        	<#list order.orderGoodsList as orderGoods>
        		<input name="goodsId" value="${orderGoods.recId}" type="hidden"/>
		        <tr>
		          <td class="bdl w10"></td>
		          <td class="w70">
		          	<div class="goods-pic-small">
		          		<span class="thumb size60">
		          			<i></i>
		          			<a target="_blank" href="${base}/product/detail?id=${orderGoods.goodsId}">
		          				<img src="${imgServer}${orderGoods.goodsImage}" onload="javascript:DrawImage(this,60,60);" width="60" height="15">
		          			</a>
		          		</span>
		          	</div>
		          </td>
		          <td><dl class="goods-name" style=" width:auto;">
		              <dt style=" width:auto; white-space: normal;"><a target="_blank" href="${base}/product/detail?id=${orderGoods.goodsId}">${orderGoods.goodsName}</a></dt>
		              <dd style=" width:auto;">${orderGoods.specInfo}</dd>
		            </dl></td>
		          <td class="w90 bdl"><span id="goodsNum">${orderGoods.goodsNum}</span></td>
		          <td class="w90 bdl bdr"><input type="text" class="w50 text" id="returnGoodsNum_${orderGoods.recId}" name="returnGoodsNum_${orderGoods.recId}" value="${orderGoods.goodsNum}" onChange="change(this)"/></td>
		        </tr>
		    </#list>
        </#if>
      </tbody>
    </table>
    <dl>
      <dt class="required"><em class="pngFix"></em>退货原因：</dt>
      <dd>
        <textarea name="buyer_message" class="textarea w250"></textarea>
      </dd>
    </dl>
    <dl>
      <dt>&nbsp;</dt>
      <dd>
        <p class="hint">退货信息只能填写提交一次，建议与商家沟通后认真填写。</p>
      </dd>
    </dl>
    <dl class="bottom">
      <dt>&nbsp;</dt>
      <dd>
        <input type="submit" class="submit" id="confirm_button" name="confirm_button" value="确定">
      </dd>
    </dl>
</div>
<script type="text/javascript">
	
	function change(obj){
		var reg = /^\d+$/; //检验是否为数字的正则表达式
		var returnGoodsNum = $(obj).val();
		var goodsNum = $(obj).parent().parent().find("#goodsNum").html();
		if(returnGoodsNum==''){
			$(obj).val(goodsNum);
		}else if(!reg.test(returnGoodsNum)){
			layer.msg("请您输入正确的数量!",{icon:2});
			$(obj).val(goodsNum);
		}else if(returnGoodsNum>goodsNum){
			layer.msg("请您输入正确的数量!",{icon:2});
			$(obj).val(goodsNum);
		}
	}
	
</script>
