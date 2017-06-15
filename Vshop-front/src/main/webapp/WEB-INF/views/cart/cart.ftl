<@p.header title="确认购物清单"/>
<script type="text/javascript" src="${base}/res/js/common_select.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/cart/area_array.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/layer/layer.js" charset="utf-8"></script>
<link href="${base}/res/css/home_cart.css" rel="stylesheet" type="text/css">
<style type="text/css">
  #navBar { display: none !important;}
</style>
<ul class="flow-chart">
  <li class="step a1" title="确认购物清单"></li>
  <li class="step b2" title="确认收货人资料及送货方式"></li>
  <li class="step c2" title="购买完成"></li> 
</ul>
<div class="content wrapper">
	<div class="cart-title">
    	<h3>购物车清单</h3>
    </div>
    <form action="${base}/cart/cartOrder" method="post" id="subform">
    	<table class="buy-table">
	    	<thead>
	        	<tr>
	        		<th class="w40">
	        			<label>
                       		<input type="checkbox" class="checkbox" name="chk_all" checked="checked"/>全选
                       	</label>
	        		</th>
	            	<th colspan="2">商品<hr/></th>
	            	<th class="w120">单价(元)<hr/></th>
	            	<th class="w120">数量 <hr/></th>
	            	<th class="w120">小计 <hr/></th>
	            	<th class="w120">操作 <hr/></th>
	          	</tr>
		    </thead>
			<tbody>
				<#assign cartTag = newTag("cartTag") />
				<#assign cartMap = cartTag() />
	       		<#if cartMap.get("cartVoList")??>
	    			<#list cartMap.get("cartVoList") as cartVo>
		            <tr>
		              <th colspan="20">商家：<a href="${base}/store/shop?storeId=${cartVo.storeId}"  target="_Blank">${cartVo.storeName}</a></th>
		            </tr>
		            <#if cartVo.list??>
		            	<#list cartVo.list as cart>
				            <tr id="cart_item_${cart.goodsId}">
				            	<td class="w20"><input type="checkbox" value="${cart.cartId}" onclick = "selectBox()" name="secondpro" checked="checked"/></td>
								<td class="w70"><div class="cart-goods-pic"><a href="${base}/product/detail?id=${cart.goodsId}" target="_blank"><span class="thumb size60"><i></i><img src="${imgServer}${cart.goodsImages}" alt="${cart.goodsName}" onload="javascript:DrawImage(this,60,60);" /></span></a></div></td>
				              	<td class="tl vt">
				              		<dl class="cart-goods-info">
				                  		<dt class="cart-goods-info-name"><a href="${base}/product/detail?id=${cart.goodsId}" target="_blank">${cart.goodsName}</a> </dt>
				                 		<dd class="cart-goods-info-spec">${cart.specInfo}</dd>
				                	</dl>
				                </td>
				              	<td>
				              		<span class="cart-goods-price-s">
				              			<input type="hidden" name="goodstotal" value="${cart.goodsPrice}" />
				              			<em>
				              				<script type="text/javascript">
				              					var price = number_format(${cart.goodsPrice},2);
				              					document.write("&yen;" + price);
				              				</script>
				              			</em>
				              		</span>
				              	</td>
				              	<td>
				              		<a href="JavaScript:void(0);" onclick="decrease_quantity('${cart.cartId}');" title="减少" class="subtract">&nbsp;</a>
				                	<input id="input_item_${cart.cartId}" name="goodsNum" value="${cart.goodsNum}" orig="1" changed="1" onkeyup="change_quantity('${cart.goodsPrice}','${cartVo.storeId}','${cart.cartId}','${cart.specId}', this);" class="text1  vm" type="text"  style="width:30px; *float: left;text-align: center;"/>
				                	<a href="JavaScript:void(0);" onclick="add_quantity('${cart.cartId}');" title="增加" class="adding" >&nbsp;</a>
				                </td>
				              	<td>
				              		<span class="cart-goods-price" >
				              			<em id="item${cart.cartId}_subtotal">
				              				<script type="text/javascript">
				              					var goodsprice = number_format(${cart.goodsNum*cart.goodsPrice},2);
				              					document.write("&yen;" + goodsprice);
				              				</script>
				              			</em>
				              		</span>
				              	</td>
				              	<td>
				              		<a href="javascript:void(0)" onclick="drop_cart_item('${cart.cartId}');">删除</a>
				              	</td>
				            </tr>
				     	</#list>
			         </#if>
					</#list>
   				</#if>
			</tbody>
		</table>
    </form>
    <div class="cart-bottom storemodule_3">
    	<div align="left">
    		<input type="checkbox" class="checkbox" name="chk_all" checked="checked"/>全选
    		<a href="#" class="cart-button" onclick="deleteCarts();">删除选中的商品</a>
    	</div>
    	<p>商品总价（不含运费）
    		<span class="cart-goods-price-b mr10" >
    			<em id="cart_amount">
    				<script type="text/javascript">
    					var totalprice = number_format(${cartMap.get('map').get('goodsTotalPrice')},2);
    					document.write("&yen;" + totalprice);
    				</script>
    			</em>
    		</span>
    	</p>
        <p><a href="${base}" class="cart-back-button mr10">继续购物</a><a href="javascript:void(0)" onclick="subMit();" class="cart-button">去结算</a></p>
    </div>
    <div class="full_module" >
    	<div id="content" class="infocontent">
        	<div id="top" class="infolist"></div>
        	<span class="ad_middle"> 
	        	<!-- <script type="text/javascript" src="#?act=adv&op=advshow&ap_id=16"></script>  -->
	        	<a href="">
	        		<img style="width: 235px; height: 135px; display: inline;" border="0" class="scrollLoading" src="${imgServer}/upload/img/adv/7c90a171168802f0c662db129f26cdb4.jpg" 
	        			data-url="${imgServer}/upload/img/adv/7c90a171168802f0c662db129f26cdb4.jpg" alt="" />
	        	</a>
        	</span>
        	<span class="ad_middle"> 
        		<!-- <script type="text/javascript" src="#?act=adv&op=advshow&ap_id=17"></script>  -->
        		<a href="">
        			<img style="width: 235px; height: 135px; display: inline;" border="0" class="scrollLoading" src="${imgServer}/upload/img/adv/5bbc6415e7cd6c72e2f1f043d0ba71f0.jpg" 
        				data-url="${imgServer}/upload/img/adv/5bbc6415e7cd6c72e2f1f043d0ba71f0.jpg" alt="" />
        		</a>
        	</span>
	        <span class="ad_middle"> 
	        	<!-- <script type="text/javascript" src="#?act=adv&op=advshow&ap_id=18"></script>  -->
	        	<a href="">
	        		<img style="width: 235px; height: 135px; display: inline;" border="0" class="scrollLoading" src="${imgServer}/upload/img/adv/ed3c8ae92c78458b070374f0287de7fa.jpg" 
	        			data-url="${imgServer}/upload/img/adv/ed3c8ae92c78458b070374f0287de7fa.jpg" alt="" />
	        	</a>
	        </span>
	        <span class="ad_middle"> 
	       		<!-- <script type="text/javascript" src="#?act=adv&op=advshow&ap_id=19"></script>  -->
	        	<a href="">
	        		<img style="width: 235px; height: 135px; display: inline;" border="0" class="scrollLoading" src="${imgServer}/upload/img/adv/765ca4596e41dfcc6c6e5748f301229c.jpg" 
	        			data-url="${imgServer}/upload/img/adv/765ca4596e41dfcc6c6e5748f301229c.jpg" alt="">
	        	</a>
	        </span>
	        <span class="ad_middle"> 
	      		<!-- <script type="text/javascript" src="#?act=adv&op=advshow&ap_id=20"></script> -->
	      		<a href="">
	      			<img style="width: 235px; height: 135px; display: inline;" border="0" class="scrollLoading" src="${imgServer}/upload/img/adv/80311ee52b3e715318c3437a9b8c8d80.jpg" 
	      				data-url="${imgServer}/upload/img/adv/80311ee52b3e715318c3437a9b8c8d80.jpg" alt="">
	      		</a>
	      	</span>
        	<div id="bottom" class="infolist"></div>
      	</div>
      	<div class="clear"></div>
      	<p><a href="#">我也要出现在这里</a></p>
	</div>
</div>
<div class="clear"></div>
<script type="text/javascript">
	
	$(function(){
		$("input[name='chk_all']").click(function() {
		   	$('input[type="checkbox"]').attr("checked",this.checked);
		   	getDisable();
		});	
		getDisable();
	});
	
	/**2J选中框**/
	function selectBox(){
		var len = $("input[name='secondpro']").length;//判断总长度
		var checkedlen = $("input[name='secondpro']:checked").length;//判断选中的长度
		if(checkedlen == len ){
			$("input[name='chk_all']").attr("checked",true);
			getDisable();
		}else{
			$("input[name='chk_all']").attr("checked",false);
			getDisable();
		}
	 } 
	//单个删除购物车
	function drop_cart_item(cartId){
		var cartIds = cartId + ",";
	    $.ajax({
	    	url : "${base}/cart/delete",
	        type : 'post',
	        data : {'cartId' : cartIds},
	        dataType : 'json',
	        success : function(data){
	            if(data.success){
	            	location.reload();
	            }else{
	            	layer.msg("删除购物车失败!",{icon:2});
	            }
	        }
	    });
	}
	
	//批量删除购物车
	function deleteCarts(){
		var checkId = "";
		$("input[name='secondpro']").each(function(i){
	
		    if($(this).is(":checked")){
	
		    	checkId += $(this).val()+",";
		    }
		});
		if(checkId == ""){
			layer.alert("请选择要删除的商品",{icon:2});
			return false;
		}else{
			$.ajax({
		    	url : "${base}/cart/delete",
		        type : 'post',
		        data : {'cartId' : checkId},
		        dataType : 'json',
		        success : function(data){
		            if(data.success){
		            	location.reload();
		            }else{
		            	layer.msg("删除购物车失败!",{icon:2});
		            }
		        }
		    });
		}
	}
	
	function change_quantity(goods_price, store_id, cart_id, spec_id, input){
		$(input).parents("tr").find("input[name='secondpro']").attr("checked",true);
		selectBox();
		
	    var subtotal_span = $('#item' + cart_id + '_subtotal');
	    var amount_span = $('#cart_amount');
	    //暂存为局部变量，否则如果用户输入过快有可能造成前后值不一致的问题
	    var count = input.value;
	    if(!isPositiveNum(count)){
	    	layer.msg("请填写正确的数字",{icon:2});
	    	$(input).val(1);
	    	return false;
	    }
	    if(count<0 || count>100){
	    	layer.msg("商品最大购买量在0-100之间",{icon:2});
	    	$(input).val(100);
	    	return false;
	    }
	    $.ajax({
	    	url : "${base}/cart/updateCartCount",
	        type : 'post',
	        data : {'cartId' : cart_id,'count' : count,'storeId' : store_id,'specId' : spec_id},
	        dataType : 'json',
	        success : function(data){
	            if(data.success){
	            	var result = data.result;
	            	//更新成功
	                $(input).attr('changed', count);
	              	subtotal_span.html("&yen;"+number_format(goods_price*count,2)); //更新商品小计数量
	            	getDisable();
	            }else{
	            	//更新失败
	                layer.msg(data.msg,{icon:2});
	                $(input).val($(input).attr('changed'));
	            }
	        }
	    });
	}
	function decrease_quantity(cart_id){
	    var item = $('#input_item_' + cart_id);
	    
	    var orig = Number(item.val());
	    if(orig > 1){
	        item.val(orig - 1);
	        item.keyup();
	    }
	}
	function add_quantity(cart_id){
	    var item = $('#input_item_' + cart_id);
	    
	    var orig = Number(item.val());
	    if(orig < 100){
	    	item.val(orig + 1);
	    	item.keyup();
	    }
	}
	
	function isPositiveNum(s){//是否为正整数  
	   var re = /^[0-9]*[1-9][0-9]*$/ ;  
	   return re.test(s)  
	} 
	
	//去购物车
	function subMit(){
		
		var checkId = "";
		$("input[name='secondpro']").each(function(i){
	
		    if($(this).is(":checked")){
	
		    	checkId += $(this).val()+",";
		    }
		});
		if(checkId == ""){
			layer.alert("请选择购买商品",{icon:2});
			return false;
		}else{
			checkId = checkId.substring(0, checkId.length-1);
			window.location.href = "${base}/cart/cartOrder?cartIds="+checkId;
		}
	}
	
	//统一执行的方法
	function getDisable(){
		$('#cart_amount').html("¥"+GetTotalPrice());
	}
	
	//获取总共价格
	function GetTotalPrice(){
		var totalNum=0;
		$("input[name='secondpro']").each(function(i){
		    if($(this).is(':checked')){
		    	var goodsNum = $(this).parents("tr").find("input[name='goodsNum']").val();
		    	totalNum += parseFloat($(this).parents("tr").find(".cart-goods-price-s input[name='goodstotal']").val()*goodsNum);
		    }
		}); 
		return toDecimal2(totalNum);
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
<script type="text/javascript" src="${base}/res/js/ldomain.js" charset="utf-8"></script>
<@p.footer />