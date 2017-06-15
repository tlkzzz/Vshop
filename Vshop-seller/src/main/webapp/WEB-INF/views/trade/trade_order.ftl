<@p.header title="商城中心-订单管理"/>
<script type="text/javascript" src="${base}/res/js/common_select.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="${base}/res/js/ajaxfileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="${base}/res/js/layer/layer.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>	 
<div class="layout">
	<div class="sidebar"></div>
  	<div class="right-content">
        <div class="path">
	        <div>
	        	<a href="${base}">商城中心</a> <span>></span>
	            <a href="#?act=store&op=store_order"/>
	            	订单管理
	            </a>
	            <span></span>
	            <#if orderState==0>
	            	已经取消
	            <#elseif orderState==99>
                   	所有订单
                <#elseif orderState==10>
                   	等待付款
                <#elseif orderState==20>
                	等待发货
                <#elseif orderState==30>
                	已经发货
                <#elseif orderState==40>
                	已经完成
                <#elseif orderState==50>
                	已经提交
                <#elseif orderState==60>
                	已经确认
                </#if>
	        </div>
        </div>
        
        <div class="main">
			<div class="wrap">
				<div class="tabmenu">
					<ul class="tab pngFix">
			           <li <#if orderState=='99' >class="active"<#else>class="normal"</#if>> 
			           		 <a  href="${base}/trade/orderList?orderState=99">所有订单</a>
			           </li>
			           <li <#if orderState=='10' >class="active"<#else>class="normal"</#if>>
			           	   <a  href="${base}/trade/orderList?orderState=10">等待付款</a>
			           </li>
			           <li <#if orderState=='50' >class="active"<#else>class="normal"</#if>>
			           		<a  href="${base}/trade/orderList?orderState=50">已经提交</a>
			           </li>
			           <li <#if orderState=='20' >class="active"<#else>class="normal"</#if>>
			           		<a  href="${base}/trade/orderList?orderState=20">等待发货</a>
			           </li>
			           <li <#if orderState=='30' >class="active"<#else>class="normal"</#if>>
			           		<a  href="${base}/trade/orderList?orderState=30">已经发货</a>
			           </li>
			           <li <#if orderState=='40' >class="active"<#else>class="normal"</#if>>
			           		<a  href="${base}/trade/orderList?orderState=40">已经完成</a>
			           </li>
			           <li <#if orderState=='0' >class="active"<#else>class="normal"</#if>>
			           		<a  href="${base}/trade/orderList?orderState=0">已经取消</a>
			           </li>
			           <li <#if orderState=='60' >class="active"<#else>class="normal"</#if>>
			           		<a  href="${base}/trade/orderList?orderState=60">货到付款发货</a>
			           </li>
			        </ul>
				</div>
				<form method="post" action="${base}/trade/orderList" target="_self" id="queryForm">
				  <table class="search-form">
				    <input type="hidden" name="act" value="store" />
				    <input type="hidden" name="op" value="store_order" />
				    <input type="hidden" name="orderState" value="${orderState}"/>
				    <tr>
				      <th>订单编号：</th>
				      <td class="w100"><input type="text" class="text" name="orderSn" style="width: 130px" value="${orderSn}" /></td>
				      <th>客户：</span></th>
				      <td class="w100"><input type="text" class="text" name="buyerName" value="${buyerName}" style="width: 100px" /></td>
				      <th>下单时间：</th>
				      <td class="w260">
						<input name="startTime" id="add_time_from" type="text" style="width: 110px" class="txt Wdate" value="${startTime}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" />&#8211;
						<input name="endTime" id="add_time_to" type="text" style="width: 110px" class="txt Wdate" value="${endTime}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" />
					  </td>
				      <td class="w90 tc"><input type="submit"  class="submit"value="搜索" /></td>
				    </tr>
				  </table>
				</form>
			  	<table class="ncu-table-style order">
				  	<thead>
				  		<tr>
				    		<th class="w10"></th>
				    		<th colspan="2">商品详情</th>
				    		<th class="w100">商品价格</th>
				    		<th class="w70">利润</th>
				    		<th class="w50">数量</th>
				    		<th class="w90">商城名称</th>
				    		<th class="w70">订单价格</th>
				    		<th class="w70">总利润</th>
				    		<th class="w110">状态与操作</th>
				  		</tr>
				   	</thead>
			   		<#if orderLists??>
			  		<#list orderLists as order>
			    	<tbody>
			      		<tr>
			       	 		<td colspan="20" class="sep-row"></td>
			      		</tr>
			      		<tr>
			               	<th colspan="20"><span class="fl ml10">订单编号：<span class="goods-num"><em>${order.orderSn}</em>
			                </span></span><span class="fl ml20">下单时间：<em class="goods-time">${order.createTimeStr?string('yyyy-MM-dd HH:mm:ss')}</em></span> 
			                <span class="fl ml20">
				                <a href="${base}/trade/orderDetail?orderId=${order.orderId}" target="_blank" class="nc-show-order fl">
				                	<i></i>查看订单
				                </a>
			                </span>
			                <#-- <span class="fr"><a href="#?act=member_orderprint&order_id=3" target="_blank" title="打印订单"/><i class="print-order"></i></a></span></th> -->
			      		</tr>
			      		<#if order.orderGoodsList?size gt 0>
			      		<#assign rowsize = order.orderGoodsList?size>
			      		<#list order.orderGoodsList as orderGoods>
					    <tr>
					        <td class="bdl"></td>
					        <td class="w70">
					        	<div class="goods-pic-small">
						        	<span class="thumb size60"><i></i>
							        	<!-- <a href="${frontServer}/product/detail?id=${orderGoods.goodsId}" target="_blank"> -->
							        		<img src="${imgOssServer}${orderGoods.goodsImage}" onload="javascript:DrawImage(this,60,60);" />
							        	<!-- </a> -->
						        	</span>
					        	</div>
					        </td>
					        <td class="tl">
					        	<dl class="goods-name">
						            <dt>
										<!-- <a target="_blank" href="${frontServer}/product/detail?id=${orderGoods.goodsId}"> -->
										${orderGoods.goodsName}
										<!-- </a> -->
									</dt>
						            <dd></dd>
					            </dl>
					        </td>
						    <td class="bdl">
						    	<script type="text/javascript">
	              					var price = number_format(${orderGoods.goodsPrice},2);
	              					document.write(price);
	              				</script>
						    </td>
						    <td class="bdl">
						    	<script type="text/javascript">
	              					var price = number_format(${orderGoods.goodsCommission},2);
	              					document.write(price);
	              				</script>
						    </td>
						    
						    <td class="bdl">${orderGoods.goodsNum}</td>
						    <#if orderGoods_index == 0>
					       
					        <td class="bdl" rowspan="${rowsize}">
					          	<div class="buyer">${order.buyerName}<p></p>
						            <div class="buyer-info"> <em></em>
						              <div class="con">
						                <h3><i></i><span>联系信息</span></h3>
						                <#if order.address??>
							                <dl>
							                  <dt>姓名：</dt>
							                  <dd>${order.address.trueName}</dd>
							                </dl>
							                <dl>
							                  <dt>电话：</dt>
							                  <dd>${order.address.telPhone}</dd>
							                </dl>
							                <dl>
							                  <dt>手机：</dt>
							                  <dd>${order.address.mobPhone}</dd>
							                </dl>
							                <dl>
							                  <dt>城市：</dt>
							                  <dd>${order.address.areaInfo}</dd>
							                </dl>
							                <dl>
							                  <dt>收货地址：</dt>
							                  <dd>${order.address.address}</dd>
							                </dl>
						                </#if>
						              </div>
						            </div>
					          	</div>
					        </td>
					        <td class="bdl" rowspan="${rowsize}">
					          <p class="goods-price">
					          	<script type="text/javascript">
	              					var amount = number_format(${order.orderAmount},2);
	              					document.write(amount);
	              				</script>
					          </p>
					          <p class="goods-pay"></p>
					          <p class="goods-freight">
					          	<#if order.shippingFee??>
					          		<#if order.shippingFee!=0>
					          			运费:
					          			<script type="text/javascript">
			              					var shipFee = number_format(${order.shippingFee},2);
			              					document.write(shipFee);
			              				</script>
			              			<#else>
			              				（免运费）
					          		</#if>
					          	</#if>
					          </p>
					          <p></p>
					        </td>
					        
					        <td class="bdl" rowspan="${rowsize}">
					        	<script type="text/javascript">
	              					var price = number_format(${order.totalCommission},2);
	              					document.write(price);
	              				</script>
					        </td>
					                  
					        <td class="bdl bdr" rowspan="${rowsize}">
								<#if order.orderState==0>
				                    <p><span style="color:#999">订单已取消</span><br/></p>
				                <#elseif order.orderState==10>
			                    	<p><span style="color:#36C">待客户付款</span><br/></p>
			                    	<!-- 
			                    	<p><a href="javascript:void(0)" onclick="cancelOrder('${order.orderSn}')" style="color:#F30; text-decoration:underline;">取消订单</a></p>
			                    	<p>
			                    		<a href="javascript:void(0)" onclick="updateAmount('${order.orderId}')" class="ncu-btn6 mt5" id="order1_action_confirm">调整费用</a>
			                    	</p>
			                    	 -->
				                <#elseif order.orderState==20>
				                    <p><span style="color:#F30">客户已付款</span><br/></p>
				                    <!-- 
				                    <p><a class="ncu-btn7 mt5" href="${base}/transport/shipments_deliver?orderId=${order.orderId}">发货</a></p>
				                     -->
				                <#elseif order.orderState==30>
				                	<p><span style="color:#F30">商品已发货</span><br/></p>
				                <#elseif order.orderState==40>
				                	<p><span style="color:#060">交易完成</span><br/></p>
				                <#elseif order.orderState==50>
				                	<p>已提交，待确认<br/></p>
				                	<!-- 
				                	<p><a href="javascript:void(0)" onclick="confirmOrder('${order.orderId}')" class="ncu-btn2 mt5" >订单确认</a></p>
				                	 -->
				                <#elseif order.orderState==60>
				                	<p>已确认，待发货<br/></p>
				                	<!-- 
				                	<p><a class="ncu-btn7 mt5" href="${base}/transport/shipments_deliver?orderId=${order.orderId}">发货</a></p>
				                	 -->
				                </#if> 
					        </td>
					        </#if>
					    </tr>
					    </#list>
					   </#if>
					</tbody>	
					</#list> 
					</#if>   
				    <tfoot>
				    	<tr>
					        <td colspan="20">
					        	<#import "/trade/page.ftl" as q><!--引入分页-->
				                <#if recordCount??>
				                    <@q.pager pageNo=pageNo pageSize=pageSize recordCount=recordCount toURL="${toUrl}"/>
				                </#if>
					        </td>
				    	</tr>
					</tfoot>
			  </table>
			  <iframe name="seller_order" style="display:none;"></iframe>
			</div>
    	</div>
    </div>
    <div class="clear"></div>
</div>
<link rel="stylesheet" type="text/css" href="${base}/res/js/jquery-ui/themes/ui-lightness/jquery.ui.css"  />
<script type="text/javascript">
$(function(){
    $('.checkall_s').click(function(){
        var if_check = $(this).attr('checked');
        $('.checkitem').each(function(){
            if(!this.disabled)
            {
                $(this).attr('checked', if_check);
            }
        });
        $('.checkall_s').attr('checked', if_check);
    });
});

/**取消订单**/
function cancelOrder(id) {
   	layer.open({
	    type: 2,
	    area: ['500px', '300px'],
	    skin: 'layui-layer-rim',
	    title: '取消订单',
	    //content: APP_BASE + '/cart/addresslist'
	    content :  ['${base}/trade/cancelOrderIndex?orderSn=' + id, 'no'],
	    success: function(layero, index){
	    	layer.getChildFrame('#confirm_button',index).on('click', function(){
				var orderSn=layer.getChildFrame("#orderSn",index).val();
				var cancelCause=layer.getChildFrame("#other_reason_input",index).val();
				if(cancelCause==''){
					layer.getChildFrame("#error").html("请填写取消原因");
					return false;
				}
				var fmUrl = '${base}/trade/cancelOrder';
				$.ajax({
		             type: "post",
		             url: fmUrl,
		             data: {orderSn:orderSn,cancelCause:cancelCause},
		             dataType: "json",
					 async:false,
					 success:function(data) {
						if(data.success){
							parent.layer.alert("订单取消成功",{icon:1},function(){
								location.reload();
							});	
						}else{
							parent.layer.alert("订单取消失败",{icon:2},function(){
								location.reload();
							});		
						}
					}
		         }); 
			});	
	    }
	});
}

/**调整费用**/
function updateAmount(id) {
   	layer.open({
	    type: 2,
	    area: ['400px', '200px'],
	    skin: 'layui-layer-rim',
	    title: '调整订单费用',
	    //content: APP_BASE + '/cart/addresslist'
	    content :  ['${base}/trade/updateAmountIndex?orderId=' + id, 'no'],
	    success: function(layero, index){
	    	layer.getChildFrame('#confirm_button',index).on('click', function(){
				var orderId=layer.getChildFrame("#orderId",index).val();
				var orderAmount=layer.getChildFrame("#orderAmount",index).val();
				var fmUrl = '${base}/trade/updateAmount';
				$.ajax({
		             type: "post",
		             url: fmUrl,
		             data: {orderId:orderId,orderAmount:orderAmount},
		             dataType: "json",
					 async:false,
					 success:function(data) {
						if(data.success){
							parent.layer.msg("订单修改成功",{icon:1},function(){
								location.reload();
							});	
						}else{
							parent.layer.msg("订单修改失败",{icon:2},function(){
								location.reload();
							});		
						}
					}
		         });  
			});	
	    }
	});
}

/**确认订单**/
function confirmOrder(id) {
   	layer.open({
	    type: 2,
	    area: ['400px', '200px'],
	    skin: 'layui-layer-rim',
	    title: '订单确认',
	    //content: APP_BASE + '/cart/addresslist'
	    content :  ['${base}/trade/confirmOrderIndex?orderId=' + id, 'no'],
	    success: function(layero, index){
	    	layer.getChildFrame('#confirm_button',index).on('click', function(){
				var orderSn=layer.getChildFrame("#orderSn",index).val();
				var fmUrl = '${base}/trade/confirmOrder';
				$.ajax({
		             type: "post",
		             url: fmUrl,
		             data: {orderSn:orderSn},
		             dataType: "json",
					 async:false,
					 success:function(data) {
						if(data.success){
							parent.layer.msg("订单确认成功",{icon:1},function(){
								location.reload();
							});	
						}else{
							parent.layer.msg("订单确认失败",{icon:2},function(){
								location.reload();
							});		
						}
					}
		         });  
			});	
	    }
	});
}

/**退款审核**/
function refund(id) {
   	layer.open({
	    type: 2,
	    area: ['500px', '400px'],
	    skin: 'layui-layer-rim',
	    title: '退款审核',
	    //content: APP_BASE + '/cart/addresslist'
	    content :  ['${base}/trade/refundOrderIndex?logId=' + id, 'no'],
	    success: function(layero, index){
	    	layer.getChildFrame('#confirm_button',index).on('click', function(){
				var logId=layer.getChildFrame("input[name='logId']",index).val();
				var refundState = layer.getChildFrame("input[name='refund_state']:checked",index).val();
				var refundMessage = layer.getChildFrame("textarea[name='refund_message']",index).val();
				alert(refundMessage);
				var fmUrl = '${base}/trade/refundOrder';
				$.ajax({
		             type: "post",
		             url: fmUrl,
		             data: {logId:logId,refundState:refundState,refundMessage:refundMessage},
		             dataType: "json",
					 async:false,
					 success:function(data) {
						if(data.success){
							parent.layer.msg("审核成功!",{icon:1},function(){
								location.reload();
							});	
						}else{
							parent.layer.msg("审核失败!",{icon:2},function(){
								location.reload();
							});		
						}
					}
		         });  
			});	
	    }
	});
}

/**退货审核**/
function returnOrder(id){
	layer.open({
	    type: 2,
	    area: ['500px', '400px'],
	    skin: 'layui-layer-rim',
	    title: '退货审核',
	    //content: APP_BASE + '/cart/addresslist'
	    content :  ['${base}/trade/returnOrderIndex?returnId=' + id, 'no'],
	    success: function(layero, index){
	    	layer.getChildFrame('#confirm_button',index).on('click', function(){
				var returnId=layer.getChildFrame("input[name='returnId']",index).val();
				var returnState = layer.getChildFrame("input[name='return_state']:checked",index).val();
				var returnMessage = layer.getChildFrame("textarea[name='return_message']",index).val();
				var fmUrl = '${base}/trade/returnOrder';
				$.ajax({
		             type: "post",
		             url: fmUrl,
		             data: {returnId:returnId,returnState:returnState,returnMessage:returnMessage},
		             dataType: "json",
					 async:false,
					 success:function(data) {
						if(data.success){
							parent.layer.msg("审核成功!",{icon:1},function(){
								location.reload();
							});	
						}else{
							parent.layer.msg("审核失败!",{icon:2},function(){
								location.reload();
							});		
						}
					}
		         });   
			});	
	    }
	});
}
</script>
</body>
</html>
<@p.footer/>