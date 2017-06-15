<link href="${base}/res/css/base.css" rel="stylesheet" type="text/css">
<link href="${base}/res/css/member.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${base}/res/js/jquery.js"></script>
<link href="${base}/res/css/skin_0.css" rel="stylesheet" type="text/css" id="cssfile"/>
<script src="${base}/res/js/admincp.js" type="text/javascript"></script>
<script src="${base}/res/js/common.js" type="text/javascript"></script>
<script type="text/javascript">
	var APP_BASE = '${base}';
</script>
<div id="fwin_my_goods_add" class="dialog_wrapper ui-draggable" style="z-index: 1100; position: absolute; width: 550px; top: 38px;">
    <div class="dialog_body" style="position: relative;">
        <div class="dialog_content" style="margin: 0px; padding: 0px;">
            <div class="eject_con">
                <div class="adds">
                    <form method="post" action="" id="goods_form" target="_parent">
                        <input type="hidden" id="goodsId" value="${goodsId}"/>
                         <!--<input name="goods_store_price_inventory" value="${goods.goodsStorePrice}" type="hidden"  />-->
                         <dl>
				          <dt nc_type="no_spec"><span class="red">*</span>成本价格：</dt>
				          <dd nc_type="no_spec">
				            <p>
				              <input name="goods_cost_price_inventory" value="${goods.goodsCostPrice}" type="text" style="height:25px;" class="text" disabled="disabled"/>
				            </p>
				          </dd>
				        </dl> 
				         <dl>
				          <dt nc_type="no_spec"><span class="red">*</span>市场价格：</dt>
				          <dd nc_type="no_spec">
				            <p>
				              <input name="goods_market_price" value="${goods.goodsMarketPrice}" type="text" style="height:25px;" class="text" />
				              <input type="hidden" name="goods_market_price_old" value="${goods.goodsMarketPrice}" />
				             <#-- 价格区间 -->
				            </p>
				            <p class="hint">市场价格必须是0.01~1000000之间的数字</p>
				          </dd>
				        </dl> 
				         <dl>
				          <dt nc_type="no_spec"><span class="red">*</span>课时价格：</dt>
				          <dd nc_type="no_spec">
				            <p>
				              <input name="goods_store_price" value="" type="text" style="height:25px;" class="text" />				              
				             <#-- 价格区间 -->
				            </p>
				            <p class="hint">成本价格必须是0.01~1000000之间的数字</p>
				          </dd>
				        </dl> 
				        <dl>
				          <dt nc_type="no_spec"><span class="red">*</span>佣金比例：</dt>
				          <dd nc_type="no_spec">
				            <p>
				              <input name="goods_commission_rate" value="" type="text" style="height:25px;" class="text" />
				              <input type="hidden" name="goods_commission_rate_old" value="${goods.goodsCommissionRate}" />
				             <#-- 价格区间 -->
				            </p>
				            <p class="hint">佣金价格必须是0.01~1之间的数字</p>
				          </dd>
				        </dl> 
				        <dl class="vatop rowform onoff">
				        <dt nc_type="no_spec"><span class="red">*</span>审批：</dt>
			                <label for="goods_state1" class="cb-enable selected" ><span>通过</span></label>
			                <label for="goods_state0" class="cb-disable" ><span>失败</span></label>
			                <input id="goods_state1" name="goodsState" checked="checked" value="0" type="radio" onclick="javascript:$('#nopass2').hide(0);"/>
		              	    <input id="goods_state0" name="goodsState" value="1" type="radio" onclick="javascript:$('#nopass2').show(0);"/>
	            		</dl>
	            		 <dl class="noborder" id="nopass2" style="display: none;">
	            		 	 <dt>未通过原因:</dt>
	           				 <dd class="vatop rowform"><textarea name="goodsCloseInfo" rows="4" style="width:150px;" class="tarea" id="goods_close_info"></textarea></dd>
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
		checkMoney();
	});
	
	function checkMoney(){
		var goodsState = $(':checked').val();//是否通过
		var goodsMarketPriceOld = $('[name=goods_market_price_old]').val();//商家录入市场价格
		var goodsCommissionRateOld = $('[name=goods_commission_rate_old]').val();//佣金比例
		var goodsCostPrice = $('[name=goods_cost_price_inventory]').val();//商家录入课时价格
		var goosMarketPrice = $('[name=goods_market_price]').val();
			$('[name=goods_market_price]').blur(function(){
				var isNum = /^\d+(\.\d+)?$/;
		        var money = $(this).val();
		        if(!isNum.test(money)){
					$('#errmsg').html('请输入正确的市场价格');
		            return;
		        }else if(parseFloat($(this).val())<0.01 || parseFloat($(this).val())> 1000000 ){
						$('#errmsg').html('市场价格必须是0.01~1000000之间的数字');
				}else if(parseFloat($(this).val())< parseFloat(goodsStorePrice) ){
						$('#errmsg').html('市场价格不能小于商品价格');
				}else{
					$('#errmsg').html('');
				}
			});
			$('[name=goods_store_price]').blur(function(){
				var isNum = /^\d+(\.\d+)?$/;
		        var money = $(this).val();
		        if(!isNum.test(money)){
					$('#errmsg').html('请输入正确的成本价格');
		            return;
		        }else if(parseFloat($(this).val())<0.01 || parseFloat($(this).val())> 1000000 ){
						$('#errmsg').html('成本价格必须是0.01~1000000之间的数字');
				}else if( parseFloat($(this).val())< parseFloat(goosMarketPrice) ){
						$('#errmsg').html('课时价格必须大于市场价格');
				}else{
					$('#errmsg').html('');
				}
			});
			$('[name=goods_commission_rate]').blur(function(){
				var isNum = /^\d+(\.\d+)?$/;
		        var money = $(this).val();
		        if(!isNum.test(money)){
					$('#errmsg').html('请输入正确的佣金比例');
		            return;
		        }else if(parseFloat($(this).val())<0.01 || parseFloat($(this).val())> 1){
						$('#errmsg').html('价格必须是0.01~1之间的数字');
				}else{
					$('#errmsg').html('');
				}
			});
			$('[name=goodsCloseInfo]').blur(function(){
		        var goodsCloseInfo = $(this).val();
					if(goodsCloseInfo=="" ){
						$('#errmsg').html('请输入正确的未通过原因');
			            return;
				}else{
					$('#errmsg').html('');
				}	
			});		
		
	}
</script>