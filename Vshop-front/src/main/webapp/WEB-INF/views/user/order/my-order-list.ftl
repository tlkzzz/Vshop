<@p.userHeader title="我的订单"/>
<script type="text/javascript" src="${base}/res/js/nc-sideMenu.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/utils.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/layer/layer.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<link href="${base}/res/css/font-icons/style.css"  rel="stylesheet" />
<div id="container" class="wrapper">
	<div class="layout">
    	<@u.left/>
    	<div class="right-content"> 
      		<div class="path">
        		<div>
        			<a href="${base}/user/index">我的商家</a><span>&raquo;</span>
                    <a href="${base}/user/list"/>我的订单</a><span>&raquo;</span>
                   	订单列表
                </div>
      		</div>
      		<div class="main">
       			<link rel="stylesheet" type="text/css" href="${base}/res/js/jquery-ui/themes/ui-lightness/jquery.ui.css"  />
				<style type="text/css">
				.store-name {
					width: 130px;
					display: inline-block;
					overflow: hidden;
					white-space: nowrap;
					text-overflow: ellipsis;
				}
				</style>
				<div class="wrap">
  					<div class="tabmenu">
    					<ul class="tab pngFix">
  							<li class="active"><a  href="#">订单列表</a></li>
  							<li class="normal"><a  href="${base}/user/refundList">退款申请</a></li>
  							<li class="normal"><a  href="${base}/user/returnList">退货申请</a></li>
  						</ul>
  					</div>
  					<form method="post" action="${base}/user/list" target="_self" id="queryForm" >
    					<table class="search-form">
      						<input type="hidden" name="act" value="member" />
      						<input type="hidden" name="op" value="order" />
      						<input type="hidden" name="evaluationStatus" value="${evaluationStatus}"/>
      						<tr>
        							<th>订单号：</th>
        							<td class="w100"><input type="text" class="text" style="width: 130px" name="orderSn" value="${orderSn}"></td>
        							<th>订单状态：</th>
        							<td class="w100">
        								<select name="orderState">
							         	   	<option value="99" <#if orderState==99>selected</#if>>所有订单</option>
					                        <option value="10" <#if orderState==10>selected</#if>>待付款</option>
					                        <option value="20" <#if orderState==20>selected</#if>>待发货</option>
					                        <option value="30" <#if orderState==30>selected</#if>>已发货</option>
					                        <option value="40" <#if orderState==40>selected</#if>>已完成</option>
					                        <option value="0" <#if orderState==0>selected</#if>>已取消</option>
					                        <option value="50" <#if orderState==50>selected</#if>>已提交</option>
					                        <option value="60" <#if orderState==60>selected</#if>>已确认</option>
          								</select>
          							</td>
          							<th>下单时间：</th>
          							<td class="w260">
	        							<input name="startTime" id="add_time_from" type="text" style="width: 110px" class="txt Wdate" value="${startTime}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />&#8211;
	          							<input name="endTime" id="add_time_to" type="text" style="width: 110px" class="txt Wdate" value="${endTime}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
	          						</td>
        							<td class="w90 tc">
        								<input type="submit" class="submit" value="搜索" />
        							</td>
      							</tr>
    						</table>
						</form>
  						<table class="order ncu-table-style">
    						<thead>
      							<tr>
									<th class="w10"></th>
        							<th class="w70"></th>
        							<th>商品信息</th>
        							<th class="w60">单价</th>
        							<th class="w40">数量</th>
        							<th class="w150">商家</th>
        							<th class="w100">订单总价</th>
        							<th class="w110">状态与操作</th>
      							</tr>
    						</thead>
        					<tbody>
        					<#assign orderTag = newTag("orderTag")/>
							<#assign orderLists = orderTag("{'orderSn':'${orderSn}','startTime':'${startTime}','endTime':'${endTime}','orderState':'${orderState}','evaluationStatus':'${evaluationStatus}','returnDataType':'2','pageNo':'${pageNo}','pageSize':'${pageSize}'}") />
       						<#if orderLists??>
        						<#list orderLists as order>
	            					<tr>
										<td colspan="19" class="sep-row"></td>
	      							</tr>
	      							<tr>
	        							<th colspan="19"> 
	        								<span class="fl ml10">订单号：<span class="goods-num"><em>${order.orderSn}</em></span></span> 
	        								<span class="fl ml20">下单时间：<em class="goods-time">${order.createTimeStr?string("yyyy-MM-dd")}</em></span> 
	        								<span class="fl ml20"><a href="${base}/user/detail?orderId=${order.orderId}" target="_blank" class="nc-show-order">
	        									<i></i>查看订单</a>
	                    					</span> 
	                    					<#-- <span class="fr">
	                    						<a href="javascript:void(0)" class="snsshare-btn" nc_type="sharegoods" data-param='{"gid":"88"}'>
		                    						<i></i>
		          									<h5>分享商品</h5>
	          									</a>
	          								</span> -->
	          							</th>
	      							</tr>
	      							<#if order.orderGoodsList?size gt 0>
	      							<#assign rowsize = order.orderGoodsList?size>
	  								<#list order.orderGoodsList as orderGoods>
	   								<tr>
		       							<td class="bdl"></td>
		       							<td>
		       								<div class="goods-pic-small">
		       									<span class="thumb size60">
		       										<i></i>
		       										<a href="${base}/product/detail?id=${orderGoods.goodsId}" target="_blank">
		       											<img src="${imgServer}${orderGoods.goodsImage}" onload="javascript:DrawImage(this,60,60);" />
		       										</a>
		       									</span>
		       								</div>
		       							</td>
		       							<td>
		       								<dl class="goods-name">
		           								<dt>
		           									<a href="${base}/product/detail?id=${orderGoods.goodsId}" target="_blank">${orderGoods.goodsName}</a>
		           								</dt>
		           								<dd>${orderGoods.specInfo}</dd>
		           								<dd>
	                                   				<#if order.orderState==40&& orderGoods.evaluationStatus == 0>
							                			<a href="${base}/myReviews/reviews?orderSn=${order.orderSn}&recId=${orderGoods.recId}">我要评价</a>
							                		<#elseif orderGoods.evaluationStatus == 1>
							                			已评价
							                		</#if>
												</dd>
	         								</dl>
	         							</td>
		       							<td class="goods-price">
		       								<i>
		       									<script type="text/javascript">
					              					var price = number_format(${orderGoods.goodsPrice},2);
					              					document.write(price);
					              				</script>
		       								</i>
		       							</td>
	      								<td>${orderGoods.goodsNum}</td>
	      								<#if orderGoods_index == 0>
	      								<td class="bdl" rowspan="${rowsize}">
	      									<#--退款-->
	      									<#if order.refundLog??>
	      										<#if order.refundLog.refundState == 1>
	      											<p><a href="javascript:void(0)" >退款审核中</a></p>
	      										<#elseif order.refundLog.refundState == 2>
	      											<p>退款
	      												<script type="text/javascript">
							              					var refund = number_format(${order.refundLog.orderRefund},2);
							              					document.write(refund);
							              				</script>
							              			</p>
							              		<#elseif order.refundLog.refundState == 3>
							              			<p>商家拒绝退款</p>
	      										</#if>
	      									<#else>
	      										<#if order.returnOrder??>
			      									<#if (order.orderState==20 || order.orderState==30) && order.paymentCode!='2' && order.returnOrder.returnState==2>
									                	<p><a href="javascript:void(0)" onclick="refund('${order.orderId}')" >退款</a></p>
								                	</#if>
							                	</#if>
						                	</#if>
						                	<#--退货-->
						                	<#if order.returnOrder??>
	      										<#if order.returnOrder.returnState == 1>
	      											<p><a href="javascript:void(0)" >退货审核中</a></p>
	      										<#elseif order.returnOrder.returnState == 2>
	      											<p>
	      												退货成功
							              			</p>
							              		<#elseif order.returnOrder.returnState == 3>
	      											<p>
	      												商家拒绝退货
							              			</p>
	      										</#if>
	      									<#else>
		      									<#if order.orderState==30||order.orderState==40>
								                	<p><a href="javascript:void(0)" onclick="returnOrder('${order.orderId}')" >退货</a></p>
							                	</#if>
						                	</#if>
	      									<p class="store-name">
											    <a title="${order.storeName}" target="_blank" href="${base}/store/shop?storeId=${order.storeId}">${order.storeName}</a>
											</p>
											<!-- <p>
											    <a title="店主" href="${base}/store/shop?storeId=${order.storeId}">${order.storeName}</a>
											    <a class="message" title="站内消息" href="#?act=home&op=sendmsg&member_id=5" target="_blank"></a>
											</p> -->
	      								</td>
	      								<td class="bdl" rowspan="${rowsize}">
	      									<p id="order6_order_amount" class="goods-price">
											    <strong>
											    	<script type="text/javascript">
						              					var amount = number_format(${order.orderAmount},2);
						              					document.write(amount);
						              				</script>
											    </strong>
											</p>
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
	      								</td>
	      								<td class="bdl bdr" rowspan="${rowsize}">
											<#if order.orderState==0>
							                    <p><span style="color:#999">订单已取消</span><br/></p>
							                <#elseif order.orderState==10>
						                    	<p><span style="color:#36C">待会员付款</span><br/></p>
						                    	<p><a href="javascript:void(0)" onclick="cancelOrder('${order.orderSn}')" style="color:#F30; text-decoration:underline;">取消订单</a></p>
						                    	<p>
						                    		<a href="${base}/cart/goToPay?orderId=${order.orderId}" target="_blank" class="ncu-btn6 mt5" id="order1_action_confirm">付款</a>
						                    	</p>
							                <#elseif order.orderState==20>
							                    <p><span style="color:#F30">会员已付款</span><br/></p>
							                <#elseif order.orderState==30>
							                	<p><span style="color:#F30">商家已发货</span><br/></p>
	                              				<p><a href="javascript:void(0)" class="ncu-btn7 mt5" onclick="finishOrder('${order.orderSn}')" id="order5_action_cancel">确认收货</a></p>
							                <#elseif order.orderState==40>
						                		<p>
						                			<span style="color:#060">交易完成</span><br/>
						                		</p>
							                <#elseif order.orderState==50>
							                	<p>已提交，待确认<br/></p>
							                <#elseif order.orderState==60>
							                	<p>已确认，待发货<br/></p>
							                </#if>
	      								</td>
	      								</#if>
	      							</tr>
	      							</#list>
	      							</#if>
              					</#list>
              				</#if>
              				
              				
                			</tbody>
        					<tfoot>
      							<tr>
        							<td colspan="19">
        								<#--获取总条数-->
        								<#assign recordCount = orderTag("{'orderSn':'${orderSn}','startTime':'${startTime}','endTime':'${endTime}','orderState':'${orderState}','evaluationStatus':'${evaluationStatus}','returnDataType':'1'}") />
        								<#import "/commons/usertagpage.ftl" as q> <#--引入分页-->
										<#if recordCount??>
										    <@q.pager pageNo=pageNo pageSize=pageSize recordCount=recordCount toURL="${toUrl}"/>
										</#if>
        							</td>
								</tr>
    						</tfoot>
      					</table>
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">

var APP_BASE = '${base}';
//订单支付方法
function goPay(ordersn, paymentId) {
    location.href = APP_BASE + "/cart/orderpay?paysn="+ordersn+"&paymentId="+paymentId;
}

/**取消订单**/
function cancelOrder(id) {
   	layer.open({
	    type: 2,
	    area: ['500px', '290px'],
	    skin: 'layui-layer-rim',
	    title: '取消订单',
	    //content: APP_BASE + '/cart/addresslist'
	    content :  ['${base}/user/cancelOrderIndex?orderSn=' + id, 'no'],
	    success: function(layero, index){
	    	layer.getChildFrame('#confirm_button',index).on('click', function(){
				var orderSn=layer.getChildFrame("#orderSn",index).val();
				var state_info=layer.getChildFrame("input[name='state_info']:checked",index);
				var cancelCause="";
				if(state_info.attr("flag")=="other_reason"){
					cancelCause = layer.getChildFrame("#other_reason_input",index).val();
				}else{
					cancelCause = $(state_info).val();
				}
				var fmUrl = '${base}/user/cancelOrder';
				$.ajax({
		             type: "post",
		             url: fmUrl,
		             data: {orderSn:orderSn,cancelCause:cancelCause},
		             dataType: "json",
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

/**确认收货**/
function finishOrder(id) {
   	layer.open({
	    type: 2,
	    area: ['500px', '300px'],
	    skin: 'layui-layer-rim',
	    title: '确认收货',
	    //content: APP_BASE + '/cart/addresslist'
	    content :  ['${base}/user/finishOrderIndex?orderSn=' + id, 'no'],
	    success: function(layero, index){
	    	layer.getChildFrame('#confirm_button',index).on('click', function(){
				var orderSn=layer.getChildFrame("#orderSn",index).val();
				var fmUrl = '${base}/user/finishOrder';
				$.ajax({
		             type: "post",
		             url: fmUrl,
		             data: {orderSn:orderSn},
		             dataType: "json",
					 success:function(data) {
						if(data.success){
							parent.layer.alert("确认收货成功",{icon:1},function(){
								location.reload();
							});	
						}else{
							parent.layer.alert("确认收货失败",{icon:2},function(){
								location.reload();
							});		
						}
					}
		         }); 
			});	 
	    }
	});
}

/**退款**/
function refund(id){
	layer.open({
	    type: 2,
	    area: ['500px', '300px'],
	    skin: 'layui-layer-rim',
	    title: '订单退款',
	    //content: APP_BASE + '/cart/addresslist'
	    content :  ['${base}/user/refundOrderIndex?orderId=' + id, 'no'],
	    success: function(layero, index){
	    	layer.getChildFrame('#confirm_button',index).on('click', function(){
				var orderId=layer.getChildFrame("input[name='orderId']",index).val(); //订单id
				var orderRefund=layer.getChildFrame("input[name='order_refund']",index).val(); //订单退款金额
				var buyerMessage=layer.getChildFrame("textarea[name='buyer_message']",index).val(); //退款原因
				if(orderRefund==''){
					layer.msg("请填写退款金额",{icon:2});
					return false;
				}else if(buyerMessage==''){
					layer.msg("请填写退款原因",{icon:2});
					return false;
				} 
				var fmUrl = '${base}/user/refundOrder';
				$.ajax({
		             type: "post",
		             url: fmUrl,
		             data: {orderId:orderId,orderRefund:orderRefund,buyerMessage:buyerMessage},
		             dataType: "json",
					 success:function(data) {
						if(data.success){
							parent.layer.alert("退款提交成功",{icon:1},function(){
								location.reload();
							});	
						}else{
							parent.layer.alert("退款提交失败",{icon:2},function(){
								location.reload();
							});		
						}
					}
		         }); 
			});	   
	    }
	});
}

/**退货**/
function returnOrder(id){
	layer.open({
	    type: 2,
	    area: ['500px', '450px'],
	    skin: 'layui-layer-rim',
	    title: '订单退货',
	    //content: APP_BASE + '/cart/addresslist'
	    content :  ['${base}/user/returnOrderIndex?orderId=' + id, 'no'],
	    success: function(layero, index){
	    	layer.getChildFrame('#confirm_button',index).on('click', function(){
	    		var orderId = layer.getChildFrame("input[name='orderId']",index).val(); //订单id
	    		var goodsIds = layer.getChildFrame("input[name='goodsId']",index); //商品id数组
	    		var buyerMessage=layer.getChildFrame("textarea[name='buyer_message']",index).val(); //退货原因
	    		var formjson = '{'; //新建一个json串
	    		var goodsidss = ''; //新建一个字符串来存储退款商品的id
				$(goodsIds).each(function(index){ //遍历商品id数组
					var goodsId = $(this).val();
					if(goodsId==''){ 
						layer.msg("请您输入正确的数量!",{icon:2});
						return false;
					}else{ //若不为空.拼接json
						var goodsNum = layer.getChildFrame("#returnGoodsNum_"+goodsId,index).val();
						if(index==goodsIds.length-1){ //判断最后一行
							formjson += '\"'+goodsId+'\":\"' + goodsNum + '\"'; //拼接商品id:商品数量json串
						}else{
							formjson += '\"'+goodsId+'\":\"' + goodsNum + '\",'; //拼接商品id:商品数量json串
						}
						goodsidss += goodsId+',';
					}
				});
				formjson += '}';
				if(buyerMessage==''){
					layer.msg("请填写退货原因",{icon:2});
					return false;
				} 
				var fmUrl = '${base}/user/returnOrder';
				$.ajax({
		             type: "post",
		             url: fmUrl,
		             data: {orderId:orderId,buyerMessage:buyerMessage,data:formjson,goodsIds:goodsidss},
		             dataType: "json",
					 success:function(data) {
						if(data.success){
							parent.layer.alert("退货提交成功",{icon:1},function(){
								location.reload();
							});	
						}else{
							parent.layer.alert("退货提交失败",{icon:2},function(){
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
<@p.userfooter/>