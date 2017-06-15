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
                        <dl>
				          <dt nc_type="no_spec"><span class="red">*</span>供应商价格：</dt>
				          <dd nc_type="no_spec">
				            <p>
				              <input name="goods_store_price" value="" type="text"  class="text" />
				              <input name="goods_store_price_interval" value="" type="hidden"  />
				              <!-- 价格区间 --> 
				            </p>
				            <p class="hint">供应商价格必须是0.01~1000000之间的数字</br>必须大于供应商库存的价格：${goods.goodsStorePrice}</p>
				          </dd>
				        </dl>
                        <dl>
				          <dt>本店分类：</dt>
				          <dd>
				            <p>
					            <select name="storeGoodsClass" class="sgcategory">
						            <option value="">请选择</option>
						            <#if StoreGoodsClassVoMap??>
							            <#list StoreGoodsClassVoMap?keys as sgckey>
							            	<#list StoreGoodsClassVoMap[sgckey] as sgclist>
							            		<#if sgclist_index==0>
							            			<option value="${sgclist.parentId}">${sgclist.parentName}</option>
							            		<#elseif sgclist.childState>
							            			<option value="${sgclist.childId}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${sgclist.childName}</option>
							            		</#if>
							            	</#list>
							            </#list>
						            </#if>
					            </select>
				            </p>
				            <p class="hint">商品可以从属于供应商的多个分类之下,</br>供应商分类可以由 "用户中心 -> 供应商 -> 商品管理 -> 分类管理" 中自定义</p>
				          </dd>
				        </dl>
                        <dl>
				          <dt>商品推荐：</dt>
				          <dd>
				            <p>
				              <label style="padding-right:8px;">
				                <input name="goods_commend" value="1"  type="radio" />
				                是</label>
				              <label>
				                <input name="goods_commend" value="0" checked="checked"  type="radio"/>
				                否</label>
				            </p>
				            <p class="hint">被推荐的商品会显示在供应商首页</p>
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
		$('[name=goods_store_price]').blur(function(){
			
			var isNum = /^\d+(\.\d+)?$/;
	        var money = $(this).val();
	        if(!isNum.test(money)){
				$('#errmsg').html('请输入正确的价格');
	            return;
	        }else if(parseFloat($(this).val())<0.01 || parseFloat($(this).val())> 1000000 ){
		
					$('#errmsg').html('供应商价格必须是0.01~1000000之间的数字');
			}else if(parseFloat($(this).val()) < parseFloat('${goods.goodsStorePrice}') ){
				
				$('#errmsg').html('必须大于供应商库存的价格：${goods.goodsStorePrice}');
			}else{
				$('#errmsg').html('');
		
			}
	
			
				
				
				
				
		});
		
	});
</script>