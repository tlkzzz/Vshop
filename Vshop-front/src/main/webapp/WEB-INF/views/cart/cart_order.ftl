<@p.header title="确认收货人资料及送货方式"/>
<script type="text/javascript" src="${base}/res/js/cart/goods_cart.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/common_select.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/cart/area_array.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/layer/layer.js" charset="utf-8"></script>
<link href="${base}/res/css/home_cart.css" rel="stylesheet" type="text/css">
<style type="text/css">
  #navBar { display: none !important;} 
</style>
<ul class="flow-chart">
  <li class="step a2" title="确认购物清单"></li>
  <li class="step b1" title="确认收货人资料及送货方式"></li>
  <li class="step c2" title="购买完成"></li>
</ul>
<form method="post" id="order_form" name="order_form" action="${base}/cart/subOrder">
	<input type="hidden" name="cartIds" value="${cartIds}" id="cartIds" /> 
  	<div class="content wrapper">
		<div id="warning" class="warning" style="display:none;"></div>
		<div class="cart-title">
  			<h3>收货人地址</h3>
 			<div class="btns">
  				<a href="JavaScript:void(0);" id="span_newaddress" onclick="shownewaddress();" class="newadd"><i></i>使用新地址</a>
  			</div>
		</div>
		<div id="addressone_model" style="display:none;">
		  <ul class="receive_add address_item">
		    <li class="goto">寄送至</li>
		    <li address="" buyer=""></li>
		  </ul>
		</div>
		<div id="addresslist">
			<#assign cartTag = newTag("cartTag") />
			<#assign cartMap = cartTag("{'cartIds':'${cartIds}'}") />
			<#if cartMap.get("addressList")??>
				<#list cartMap.get("addressList") as address>
					<#if address.isDefault == 1>
						<ul class="receive_add address_item selected_address">
			    			<li class="goto">寄送至</li>
			    			<li address="${address.areaInfo}&nbsp;&nbsp;${address.address}" buyer="${address.trueName}&nbsp;&nbsp;${address.mobPhone}">
			      				<input id="address_${address.addressId}" type="radio" city_id="${address.cityId}" name="address_options" value="${address.addressId}" checked/>
			      				&nbsp;&nbsp;${address.areaInfo}&nbsp;&nbsp;${address.address}&nbsp;&nbsp; ${address.trueName}&nbsp;&nbsp; ${address.mobPhone}  
			      			</li>
			  			</ul>
			  			<input type="hidden" id="chooseaddressid" name="chooseaddressid" value='${address.addressId}'/>
			  			<#else>
			  				<ul class="receive_add address_item">
				    			<li class="goto">  &nbsp; </li>
				    			<li address="${address.areaInfo}&nbsp;&nbsp;${address.address}" buyer="${address.trueName}">
				      				<input id="address_${address.addressId}" type="radio" city_id="${address.cityId}" name="address_options" value="${address.addressId}" />
				      				&nbsp;&nbsp;${address.areaInfo}&nbsp;&nbsp;${address.address}&nbsp;&nbsp; ${address.trueName}&nbsp;&nbsp; ${address.mobPhone}  
				      			</li>
				  			</ul>
				  			<input type="hidden" id="chooseaddressid" name="chooseaddressid" value='${address.addressId}'/>
					</#if>
				</#list>
			</#if>
    		
		</div>
		<div class="content wrapper">
    		<div class="cart-title">
    			<h3>选择付款方式</h3>
  			</div>
			
  			<table style="margin-top:10px; margin-left:10px; line-height:30px; height:50px;">
  				<tr>
	  				<td>
	  					<input type="radio" name="paytype" value="1" checked/>
	  					在线支付
	  				</td>
	  				<td  >
	  					<input type="radio" name="paytype" value="2"/ style="margin-left:20px;">
	  					货到付款
	  				</td>
  				</tr>
  			</table>
  		</div>
		<div class="content wrapper">
    		<div class="cart-title">
    			<h3>发票信息</h3>
  			</div>
  			<input name="invoiceId" type="hidden" val="" />
  			<table style="margin-top:10px; margin-left:10px;line-height:30px; height:50px;">
  				<tr>
	  				<td id="invoice">
	  					不开发票
	  				</td>
	  				<td>
	  					<a href="javascript:void(0);" style="color: blue;" onClick="updateInvoice()">修改</a>
	  				</td>
  				</tr>
  			</table>
  		</div>
  		<div class="buytable">
    		<div class="cart-title">
    			<h3>确认购物清单</h3>
  			</div>
		<#if cartMap.get("cartVoList")??>
    	<#list cartMap.get("cartVoList") as cartVo>
    	<input type="hidden" name="storeId" value="${cartVo.storeId}" /> 
    	
		
  			<table class="buy-table"nc_type="table_cart" >
    			<thead>
      				<tr>
        				<th colspan="2">商品          <hr></th>
        				<th class="w120">单价(元)          <hr></th>
        				<th class="w120">数量          <hr></th>
        				<th class="w120">小计(元)          <hr></th>
      				</tr>
    			</thead>
    			<tbody>
      				<tr>
        				<th colspan="20">商家：<a target="_blank" href="${base}/store/shop?storeId=${cartVo.storeId}">${cartVo.storeName}</a><#if cartMap.get("promotionMessage")!=''><span style="color:red">优惠：${cartMap.get("promotionMessage")}</span></#if></th>
      				</tr>
      				<#if cartVo.list??>
      					<#list cartVo.list as cart>
		            		<tr id="cart_item_${cart.cartId}">
		        				<td class="w70">
		        					<div class="cart-goods-pic">
		        						<a href="${base}/product/detail?id=${cart.goodsId}" target="_blank" ><span class="thumb size60"><i></i><img src="${imgServer}${cart.goodsImages}" alt="${cart.goodsName}" onload="javascript:DrawImage(this,60,60);" /></span></a>
		        					</div>
		        				</td>
		        				<td class="tl vt">
		        					<dl class="cart-goods-info">
		            					<dt class="cart-goods-info-name"><a href="${base}/product/detail?id=${cart.goodsId}" target="_blank">${cart.goodsName}</a></dt>
		            					<dd class="cart-goods-info-spec">${cart.specInfo}</dd>
		          					</dl>
		          				</td>
		        				<td class="tc">
		        					<span class="cart-goods-price-s">
		        						<script type="text/javascript">
				    						var price = number_format(${cart.goodsPrice},2);
				    						document.write("&yen;"+price);
				    					</script>
		        					</span>
		        				</td>
		        				<td class="tc">${cart.goodsNum}</td>
		       					<td>
		       						<span class="cart-goods-price">
		       							<em nc_type="eachGoodsTotal">
		       								<script type="text/javascript">
					    						var amount = number_format(${cart.goodsPrice*cart.goodsNum},2);
					    						document.write("&yen;"+amount);
					    					</script>
		       							</em>
		       						</span>
		       					</td>
		      				</tr>
      					</#list>
      				</#if>
				</tbody>
    			<tfoot>
      				<tr>
        				<td colspan="2" class="tl"><!-- 给卖家留言 -->
        					<script type="text/javascript">
        						function postscript_activation(tt,storeId){{tt.value = '';tt.name='orderMessage_'+storeId;}}
        					</script>
          					<label>给商家留言：            
          						<input type="text" id="postscript" onclick="postscript_activation(this,'${cartVo.storeId}');" name="order_message_${cartVo.storeId}" value="选填，可以告诉卖家您对商品的特殊需求，如颜色、尺码等" maxlength="200" class="text w400" />
          					</label>
          				</td>
        				<td colspan="3" class="tr">
        					<i class="transport-ico"></i>
                   			<span id="ship_${cartVo.storeId}"><!-- 显示运费 -->免运费</span>
                    		<em id="trans_total_${cartVo.storeId}" class="cart-goods-price ml5 mr20"></em>
                    	</td>
      				</tr>
    			</tfoot>
  			</table>
		</#list>
      	</#if>
      	</div>
      	<#assign availablePredeposit = cartMap.get("member").availablePredeposit/>
      	<div class="buytable">
      		<table style="border:1px solid #A3D1EC; width:100%; margin-top:10px;  background:#eefaff">
      			<tbody style="margin-top:10px; margin-left:10px; line-height:30px; height:50px;">
      				<tr>
      					<td>
      						&nbsp;&nbsp;<input type="checkbox" name="isPd" value="0"  <#if availablePredeposit==0>disabled="disabled"</#if> />
      						<label>使用余额（账户当前余额：
      							<script type="text/javascript">
	    							var pd = number_format(${availablePredeposit},2);
	    							document.write("&yen;"+pd);
	    						</script>
      						元）</label>
      					</td>
      				</tr>
      			</tbody>
      		</table>
      	</div>
      	<#assign couponMemberTag = newTag("couponMemberTag") />
		<#assign map = couponMemberTag("{'cartIds':'${cartIds}'}") />
		<div id="outer">
			<ul id="tab">
				<li class="current">可用优惠券<span><b>(${map.cannum})</b></span></li>
				<#if map.nonum!=0>
					<li>不可用优惠券<span><b>(${map.nonum})</b></span></li>
				</#if>
			</ul>
			<div id="content">
				<ul style="display:block;">
					<#if map.cannum==0>
						<p>此订单暂无可用的优惠券。</p>
					<#else>
						<input name="couponId" id="couponMember" value="" type="hidden"/>
						<p>
							<#list map.couponMemberList as couponMember>
								<!-- 显示可以使用的优惠券 -->
								<#if couponMember.couponSuc==0>
									<div class="virtual-table">
	                        			<div class="virtual-table-body">
	          								<div class="virtual-action">
	            								<input type="checkbox" class="jdcheckbox" value="${couponMember.couponId}" onclick="selectCoupon(this,'${couponMember.couponPrice}');" name="couponCheckBox" style="margin-top: 7px;">
	          								</div>
	          								<div class="virtual-sum">
	            								<span class="coupon-scope">${couponMember.couponLimit}-${couponMember.couponPrice}<font>元</font>
	            								</span>
	          								</div>
	
	          								<div class="virtual-type" title="乔丹官方旗舰店">
	                          					<div sytle="width:300px;height:28px;overflow:hidden;line-height:28px;">
	                                 				 ${couponMember.storeName}
	                              				</div>
	                      					</div>
	          								<div class="virtual-vtime">
	            								有效期至${couponMember.endTimeStr?string('yyyy-MM-dd')}
	          								</div>
	          								<div class="clr"></div>
	        							</div>
	                            	</div>
                            	</#if>
							</#list>
						</p>
					</#if>
					共使用了 <span id="couponNum">0</span> 张优惠券　可以优惠 <span id="couponPrice">0.00</span> 元
				</ul>
				<ul>
					<#list map.couponMemberList as couponMember>
						<!-- 显示不可以使用的优惠券 -->
						<#if couponMember.couponSuc==1>
							<div class="virtual-table">
                       			<div class="virtual-table-body">
         							<div class="virtual-action">
           								<input type="checkbox" class="jdcheckbox" value="${couponMember.couponId}" disabled="disabled"  style="margin-top: 7px;">
         							</div>
         							<div class="virtual-sum">
           								<span class="coupon-scope">${couponMember.couponLimit}-${couponMember.couponPrice}<font>元</font>
           								</span>
         							</div>

         							<div class="virtual-type" title="乔丹官方旗舰店">
                       					<div sytle="width:300px;height:28px;overflow:hidden;line-height:28px;">
                              				 ${couponMember.storeName}
                           				</div>
                     				</div>
       								<div class="virtual-vtime">
         								有效期至${couponMember.endTimeStr?string('yyyy-MM-dd')}
       								</div>
       								<div class="clr"></div>
       							</div>
                           	</div>
                         </#if>
					</#list>
				</ul>
			</div>
		</div>
		
        <div class="cart-bottom">
  			<div class="confirm-popup" >
    			<div class="confirm-box">
      				<dl>
        				<dt>商品总金额：</dt>
        				<dd id="goods_amount"></dd>
      				</dl>
      				<dl>
        				<dt>运费：</dt>
        				<dd id="order_shipp"></dd>
      				</dl>
      				<dl>
        				<dt>优惠券金额：</dt>
        				<dd id="coupon_price"></dd>
      				</dl>
      				<dl>
        				<dt>优惠金额：</dt>
        				<dd id="condition_price"></dd>
      				</dl>
      				<dl>
        				<dt>余额：</dt>
        				<dd id="predeposit_amount"></dd>
      				</dl>
      				<dl>
        				<dt>应付金额：</dt>
        				<dd class="cart-goods-price-b">
        					<em id="order_amount">
        						<script type="text/javascript">
	    							var totalprice = number_format(${cartMap.get('map').get('goodsTotalPrice')},2);
	    							document.write("&yen;"+totalprice);
	    						</script>
	    					</em>
	    				</dd>
      				</dl>
    			</div>
  			</div>
  			<div class="cart-buttons">
        		<a href="${base}/cart/index" class="cart-back-button mr10">返回购物车</a>
        		<a href="javascript:void(0)" id='submitToPay' class="cart-button mr10">提交订单</a> 
        	</div>
		</div>
    	<div class="clear"></div>
  	</div>
</form>
<!--  -->
<script>
var APP_BASE = '${frontServer}';
$(function(){	
	//加载运费
	addShipping();
	//加载默认收货地址,收货人
	addressmember();
 	//选择已经存在的收货人地址
    $('.address_item').live('click',function(){
    	$(this).parent().find('.goto').html('&nbsp;');
    	$(this).children().first().html('寄送至');
        var checked_address_radio = $(this).find("input[name='address_options']");
        $(checked_address_radio).attr('checked', true);
        $('.address_item').removeClass('selected_address');
        $(this).addClass('selected_address');
        $("#chooseaddressid").val($(checked_address_radio).val());
        //getTransport(); //获取运费方法
        var _next = $(this).find('li').eq(1);
        
        //更换地址后,重新加载运费
    	addShipping();
    });
    //选择是否使用余额
    $("input[name='isPd']").live('click',function(){
    	if($(this).attr("checked")){
    		$(this).val(1);
    	}else{
    		$(this).val(0);
    	}
    	getTotalPrice();
    });
    //提交订单
	$('#submitToPay').click(function(){
		ifsubmit = true;
		if ($('input[name="address_options"]:checked').val() == null){
			layer.alert('请设置收货地址',{icon:2});
			return false;
		}
		/* $('select[nc_type="sel_transport"]').each(function(){
			if($(this).val() == '' || $(this).val() == null){
				layer.alert('配送方式未选择',{icon:2});
				ifsubmit = false;
			}
		}); */
		if (ifsubmit == true){
			var cartIds = $("#cartIds").val();
			$.ajax({
		    	url : "${base}/cart/orderVal",
		        type : 'post',
		        data : {'cartIds' : cartIds},
		        dataType : 'json',
		        success : function(data){
		            if(data.success){
		            	//获取返回的map集合
		            	var result = data.result;
		            	if(result.specnotfund.length!=0){ //判断商品是否存在
		            		//新建一个字符串,存储不存在的商品名称
		            		var str = "";
		            		result.specnotfund.forEach(function(s){
		            			str += s.goodsName + "&nbsp;&nbsp;&nbsp;";
		            		});
		            		layer.alert("您购买的商品:"+str+"不存在,请您重新提交订单!",{icon:2});
		            	}else if(result.goodsshow.length!=0){ //先判断商品状态
		           			//新建一个字符串,存储状态异常的商品名称
		            		var str = "";
		            		result.goodsshow.forEach(function(s){
		            			str += s.goodsName + "&nbsp;&nbsp;&nbsp;";
		            		});
		            		layer.alert("您购买的商品:"+str+"状态异常,请您重新提交订单!",{icon:2});
		            	}else if(result.understock.length!=0){ //判断库存量
		           			//新建一个字符串,存储库存不足的商品名称
		            		var str = "";
		            		result.understock.forEach(function(s){
		            			str += s.goodsName + "&nbsp;&nbsp;&nbsp;";
		            		});
		            		layer.alert("您购买的商品:"+str+"库存量不足,请您重新提交订单!",{icon:2});
		            	}else if(result.pricechange.length!=0){ //然后判断价格是否变动
		            		//新建一个字符串,存储价格变动的商品名称
		            		var str = "";
		            		result.pricechange.forEach(function(s){
		            			str += s.goodsName + "&nbsp;&nbsp;&nbsp;";
		            		});
		            		layer.alert("您购买的商品:"+str+"价格出现变动,请您重新提交订单!",{icon:2});
		            	}
		            }else{
		            	$('#order_form').submit();
		            	/* var cartIds = $("input[name='cartIds']").val();
		            	var storeId = $("input[name='storeId']").val();
		            	var addressId = $("input[name='address_options']").val();
		            	var paytype = $("input[name='paytype']").val();
		            	var couponId = $("input[name='couponId']").val();
		            	alert(storeId); */
		            } 
		        }
		    }); 
		}
	});
	
	//优惠券切换
	window.onload = function()
	{
		var $li = $('#tab li');
		var $ul = $('#content ul');
		$li.mouseover(function(){
			var $this = $(this);
			var $t = $this.index();
			$li.removeClass();
			$this.addClass('current');
			$ul.css('display','none');
			$ul.eq($t).css('display','block');
		});
	}
});
	/**添加收货地址**/
	function shownewaddress(){
		layer.open({
            type:2,
            move: false,
            shade: false,
            title: '新增地址',
            //content: '${base}/myaddress/addAddress',
            content:['${base}/cart/addresslist', 'no'],
            //content:$('#fwin_my_address_add'),
            area: ['550px', '400px'],
            btns: 2,
            btn: ['确定', '取消'],
            yes: function (index) {
                var provinceId = layer.getChildFrame('#area').val(); //省的id
                var cityId = layer.getChildFrame('#city').val(); //城市id
                var areaId = layer.getChildFrame('#qu').val(); //区的id
                var address = layer.getChildFrame('#address').val(); //具体地址
               // var zipCode = layer.getChildFrame('#zipCode').val(); //邮政编码
                var trueName = layer.getChildFrame('#trueName').val(); //收货人
                var mobPhone = layer.getChildFrame('#mobPhone').val(); //收货人手机号

                var provinceval = layer.getChildFrame('#area').find("option:selected").html(); //省的值
                var cityval = layer.getChildFrame('#city').find("option:selected").html(); //城市的值
                var quval = layer.getChildFrame('#qu').find("option:selected").html(); 	 //区的值
                var areaInfo=provinceval+","+cityval+","+quval;//保存到常用地址表
                if(trueName.trim()==''){
                    layer.getChildFrame(".trueNameMsg").html('收货人不能为空');
                    return false;
                }else{
                    layer.getChildFrame(".trueNameMsg").html('');
                }
                

                if(provinceId==''||provinceId=='0'){
                    layer.getChildFrame(".areaMsg").html('请选择省份');
                    return false;
                }else{
                    layer.getChildFrame(".areaMsg").html('');
                }
                if(cityId==''||cityId=='0'){
                    layer.getChildFrame(".areaMsg").html('请选择城市');
                    return false;
                }
                if(areaId==''||areaId=='0'){
                    layer.getChildFrame(".areaMsg").html('请选择区');
                    return false;
                }else{
                    layer.getChildFrame(".areaMsg").html('');
                }
                if(mobPhone.trim()==''){
                   	layer.getChildFrame(".mobPhoneMsg").html('手机号填写不正确');
                    return false;
                }else{
                   var mobileexp = /^1\d{10}$/;
                    if(!mobileexp.test(mobPhone.trim())){
                        layer.getChildFrame(".mobPhoneMsg").html('手机号填写不正确');
                        return false;
                    } else{
                        layer.getChildFrame(".mobPhoneMsg").html('');                    
                    }
	            }
                if(address.trim()==''){
                    layer.getChildFrame(".area_addressMsg").html('请填写详细地址');
                    return false;
                }else{
                    layer.getChildFrame(".area_addressMsg").html('');
                }
               // if(zipCode.trim()==''){
                 //   layer.getChildFrame(".zipCodeMsg").html('请填写邮编');
                  //  return false;
               // }else{
                //    layer.getChildFrame(".zipCodeMsg").html('');
              //  }
                var formjson = '{';
                formjson += '\"trueName\":\"' + trueName + '\",';//分类id
                formjson += '\"provinceId\":\"' + provinceId + '\",';//分类id
                formjson += '\"cityId\":\"' + cityId + '\",';//城市id
                formjson += '\"areaId\":\"' + areaId + '\",';//地区ID
                formjson += '\"mobPhone\":\"' + mobPhone + '\",';//手机号
                formjson += '\"areaInfo\":\"' + areaInfo + '\",';//地区全称
                formjson += '\"address\":\"' + address + '\",';//详细地址
               // formjson += '\"zipCode\":\"' + zipCode + '\"';//邮编
                formjson += '}';
                $.ajax({
                    url:'${base}/myaddress/saveAddress',
                    type:'post',
//                    data:{"trueName":trueName,"proviceId":proviceId,"cityId":cityId,"areaId":areaId,"mobPhone":mobPhone,"address":address,"zipCode":zipCode},
                    data : {'data': formjson},
                    dataType:'json',
                    success:function(data){
                        if(data.success){
                            //删除原有的样式
		                   	$('.address_item').find('.goto').html('&nbsp;');
		                   	$('.address_item').removeClass('selected_address');
		                   	//插入新样式
		            	    var obj = data.data;
		            	    var html = "<ul class='receive_add address_item selected_address'>";
		            	    html += "<li class='goto'>寄送至</li>";
		            	    html += "<li address='"+obj.areaInfo+"&nbsp;&nbsp;"+obj.address+"' buyer='"+obj.trueName+"'>";
		                   	html += "<input id='address_"+obj.addressId+"' type='radio' city_id='"+obj.cityId+"' name='address_options' value='"+obj.addressId+"' checked/>";
		                   	html += "&nbsp;&nbsp;"+obj.areaInfo+"&nbsp;&nbsp;"+obj.address+"&nbsp;&nbsp;"+ obj.trueName+"&nbsp;&nbsp;"+obj.mobPhone;
		                   	html += "</li></ul>";
		                   	html += "<input type='hidden' id='chooseaddressid' name='chooseaddressid' value='"+obj.addressId+"'/>";
		                   	$("#addresslist").append(html);
		                   	layer.closeAll();
                        }else{
                            layer.msg('新增失败', {icon: 2});
                        }
                    },error:function(data){
                         layer.msg('通信失败', {icon: 2});
                    }
                });
                layer.close(index); //一般设定yes回调，必须进行手工关闭
            },cancel: function(index){
                layer.close(index);
            }
        });
 	}
 	
 	//加载运费
 	function addShipping(){
 		//市级id
 		var cityId = "";
 		//收货地址信息
 		var city = $("input[name='address_options']:checked");
 		//若选择收货地址,获取城市id
 		if (typeof(city) != "undefined"){
 			cityId = city.attr("city_id");
 		}
 		var cartIds = $("#cartIds").val();
 		$.ajax({
        	url:'${base}/cart/addShipping',
            type:'post',
            data : {'cartIds':cartIds,'cityId':cityId},
            dataType:'json',
            success:function(data){
            	if(data.success){
            		$.each(data.result,function(key,values){   //遍历map
            			var str='';
            			var num = 0; //循环次数
					    str+='<span>配送方式：<select name="transport_type" onChange="shipUpdate(this,'+key+');" >';
					    $.each(values,function(key1,values1){  //遍历运费模板map
					    	num += 1;
					    	str+='<option value="';
					    	str+=key1+'|'+key+'">';  //选择的值为运输方式|商家id
					    	if(key1=='py'){
					    		str+='平邮  '+toDecimal2(values1);
					    	}else if(key1=='kd'){
					    		str+='快递  '+toDecimal2(values1);
					    	}else if(key1=='es'){
					    		str+='EMS  '+toDecimal2(values1);
					    	}
					    	str+='</option>';
					    });   
					    str+='</select></span>';
					    //将运费加入页面
					    if(num!=0){
					    	$("#ship_"+key).html(str);
					    	var freight = $("#ship_"+key).find("select[name='transport_type'] option:selected").text().split("  ");
				    		$("#ship_"+key).parent().find("#trans_total_"+key).html("&yen;"+toDecimal2(freight[1]));
					    }
					});  
					getTotalPrice();
                }
            },error:function(data){
            	layer.msg('通信失败', {icon: 2});
            }
        }); 
 	}
 	
 	//计算应付金额
 	function getTotalPrice(){
 		//优惠券id
 		var couponId = "";	
 		//获取优惠券信息
 		var couponMember = $("#couponMember");
 		if (typeof(couponMember) != "undefined"){
 			if(couponMember.val()!=''){
 				couponId = couponMember.val();
 			}
 		}
 		//运费信息
 		var freight = "";
 		//运费模块
 		var ship = $("select[name='transport_type']");
 		if (typeof(ship) != "undefined"){
	 		ship.each(function(){
	 			freight += $(this).val() + ",";
	 		});
 		}
 		//市级id
 		var cityId = "";
 		//收货地址信息
 		var city = $("input[name='address_options']:checked");
 		//若选择收货地址,获取城市id
 		if (typeof(city) != "undefined"){
 			cityId = city.attr("city_id");
 		}
 		
 		//是否使用余额
		var isPd = 0;
		//判断是否勾选
		if($("input[name='isPd']").attr("checked")){//选中
			isPd = 1;
		}
		 		
 		//多个购物车的id
 		var cartIds = $("#cartIds").val();
 		$.ajax({
        	url:'${base}/cart/getTotalPrice',
            type:'post',
            data : {'cartIds':cartIds,'cityId':cityId,'couponId':couponId,'freight':freight,'isPd':isPd},
            dataType:'json',
            success:function(data){
            	if(data.success){
            		$("#goods_amount").html("&yen;"+toDecimal2(data.result.totalGoodsPrice));
            		$("#order_amount").html("&yen;"+toDecimal2(data.result.totalPrice));
            		$("#order_shipp").html("&yen;"+toDecimal2(data.result.totalFreight));
            		$("#coupon_price").html("&yen;"+toDecimal2(data.result.couponPrice));
            		$("#condition_price").html("&yen;"+toDecimal2(data.result.conditionPrice));
                	$("#predeposit_amount").html("&yen;"+toDecimal2(data.result.predepositAmount));
                }
            },error:function(data){
            	layer.msg('通信失败', {icon: 2});
            }
        });
 	}

	function addressmember(){
		var checked_address_radio = $("input[name='address_options']:checked");
		var address = $(checked_address_radio).parent().attr("address");
		var buyer = $(checked_address_radio).parent().attr("buyer");
	}
	//修改订单运费显示
	function shipUpdate(obj,storeId){
		var freight = $(obj).find("option:selected").text().split("  ");
		var store = "#trans_total_" + storeId;
		$(obj).parent().parent().parent().find(store).html("&yen;"+toDecimal2(freight[1]));
		//重新加载订单金额信息
		getTotalPrice();
	}
	
	//修改发票信息
	function updateInvoice(){
		layer.open({
            type:2,
            move: false,
            shade: false,
            title: '发票信息',
            content:['${base}/invoice/updateInvoiceIndex', 'no'],
            area: ['600px', '600px'],
        });
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
	
	/** 优惠券选择 **/
	function selectCoupon(obj,price){
		if($(obj).attr("checked")){
			$("input[name='couponCheckBox']").attr("checked",false);
			$(obj).attr("checked",true);
		}
		var checkcoupon = $("input[name='couponCheckBox']:checked");
		if(typeof(checkcoupon.val()) == "undefined"){
			$("#couponMember").val('');
			$("#couponNum").html("0");
			$("#couponPrice").html(toDecimal2(0));
		}else{
			$("#couponMember").val(checkcoupon.val());
			$("#couponNum").html("1");
			$("#couponPrice").html(toDecimal2(price));
		}
		getTotalPrice();
	}
</script>
<@p.footer />