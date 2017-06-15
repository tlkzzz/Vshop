<link href="${base}/res/css/base.css" rel="stylesheet" type="text/css">
<link href="${base}/res/css/member.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${base}/res/js/jquery.js"></script>
<script type="text/javascript">
	var APP_BASE = '${base}';
</script>
<div id="fwin_my_goods_add" class="dialog_wrapper ui-draggable" style="z-index: 1100; position: absolute; width: 550px; top: 38px;">
    <div class="dialog_body" style="position: relative;">
        <div class="dialog_content" style="margin: 0px; padding: 0px;">
            <div class="eject_con">
                <div class="adds">
                    <form method="post" action="" id="goods_form" target="_parent">
                        <input type="hidden" id="goodsId" value="${goodsId}">
                        <input name="goods_store_price_inventory" value="${goods.goodsStorePrice}" type="hidden"  />
                        <input type="hidden" id="phoneMain" value="${resultMap.result}" />
                       <!--  <dl>
				          <dt nc_type="no_spec"><span class="red">*</span>商城价格：</dt>
				          <dd nc_type="no_spec">
				            <p>
				              <input name="goods_store_price" value="" type="text"  class="text" />
				              <input name="goods_store_price_interval" value="" type="hidden"  />
				      价格区间 
				            </p>
				            <p class="hint">商城价格必须是0.01~1000000之间的数字</br>必须大于平台库存的价格：${goods.goodsStorePrice}</p>
				          </dd>
				        </dl> -->
                        <dl>
				          <dt>商城分类：</dt>
				          <dd>
				            <p>
				            	<input type="radio" name="recommendId" checked="checked" value="3"/>新品上市
				            	&nbsp;&nbsp;&nbsp;&nbsp;
				            	<input type="radio" name="recommendId" value="2" />平台力荐
				            </p>
				            <p class="hint">选择分类后，商品将显示在对应的分类列表</p>
				          </dd>
				        </dl>
                      	<dl>
				          <dt>商品推荐：</dt>
				          <dd>
				            <p>
				              <label style="padding-right:8px;">
				                <input name="goods_commend" value="1" type="radio" />是</label>
				              <label>
				                <input name="goods_commend" value="0" checked="checked" type="radio"/>否</label>
				            </p>
				            <p class="hint">被推荐的商品会显示在商城首页对应分类</p>
				          </dd>
				        </dl>
                        <dl>
                        	<dt></dt>
                        	<dd><span id="errmsg" style="color: red"></span></dd>
                        </dl>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
	$(function(){
		
		
		var phoneMain = $("#phoneMain").val();
		if(phoneMain=="max"){
			$("#errmsg").html("是否要以此商品替换微商城首页推荐商品！");
			$("input[name=goods_commend]").get(1).checked=true;
		}else if(phoneMain=="min"){
			$("#errmsg").html("微商城首页中并无推荐商品！");
			$("input[name=goods_commend]").get(0).checked=true;
		}else{
			$("#errmsg").html("微商城的首页中推荐商品不到四条！");
			$("input[name=goods_commend]").get(0).checked=true;
		}
		
		$("input[name=recommendId]").click(function(index){
			var recommendId = $("input[name=recommendId]:checked").val();
			$.ajax({
				type : "POST",
				url : "${base}/pro/queryStoreGoods",
				dataType : "json",
				data : {"recommendId":recommendId},
				success : function(data){
					if(data.result=="max"){
						$("#errmsg").html("是否要以此商品替换微商城首页推荐商品！");
						$("input[name=goods_commend]").get(1).checked=true;
					}else if(data.result == "min"){
						$("#errmsg").html("微商城首页中并无推荐商品！");
						$("input[name=goods_commend]").get(0).checked=true;
					}else{
						$("#errmsg").html("微商城的首页中推荐商品不到四条！");
						$("input[name=goods_commend]").get(0).checked=true;
					}
				}
			});
		});
		
	});
</script>