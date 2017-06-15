<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>退货</title>
<meta name="viewport" content="width=device-width,inital-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<link href="${base}/res/html5/css/style.css" rel="stylesheet" type="text/css" />
<script src="${base}/res/html5/js/jquery-1.10.2.min.js"></script>
<script src="${base}/res/html5/js/jquery.validate.min.js"></script>
<script src="${base}/res/js/jquery.js" charset="utf-8"></script>
<script src="${base}/res/js/common.js" charset="utf-8"></script>
<script src="${base}/res/js/layer/layer.js" charset="utf-8"></script>
<script type="text/javascript">
function change(obj){
	
	var reg = /^\d+$/; //检验是否为数字的正则表达式
	var returnGoodsNum = $(obj).val();
	var goodsNum =$("input[name='goodsNum']").val();;
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
/**退货**/
function returnOrder(){	
   		var orderId = $("input[name='orderId']").val(); //订单id
   		var goodsIds = $("input[name='goodsId']"); //商品id数组
   		var buyerMessage=$("textarea[name='buyer_message']").val(); //退货原因
   		var formjson = '{'; //新建一个json串
   		var goodsidss = ''; //新建一个字符串来存储退款商品的id
		$(goodsIds).each(function(index){ //遍历商品id数组
			var goodsId = $(this).val();
			if(goodsId==''){ 
				layer.msg("请您输入正确的数量!",{icon:2});
				return false;
			}else{ //若不为空.拼接json
				var goodsNum = $("#returnGoodsNum_"+goodsId,index).val();
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
		
		$("#confirm_button").click(function(){
			layer.alert("请勿重复提交退货申请",{icon:2});	
		});
	
		var fmUrl = '${base}/m/authc/buyer/returnOrder';
		$.ajax({
             type: "post",
             url: fmUrl,
             data: {orderId:orderId,buyerMessage:buyerMessage,data:formjson,goodsIds:goodsidss},
             dataType: "json",
			 async:false,
			 success:function(data) {
				if(data.success){
					layer.alert("退货提交成功",{icon:1});	
				}else{
					layer.alert("退货提交失败",{icon:2});		
					$("#confirm_button").click(function(){
						returnOrder();
					});
				}
			}
         }); 
	
}
</script>
</head>
<body class="user_bg">
<div class="phone_hd"><a class="back" href="javascript:history.go(-1);"><img src="${base}/res/html5/images/back.png" width="25" height="25" /></a>返回<a class="menu home" href="${base}/m/index/index"><img src="${base}/res/html5/images/home.png" width="25" height="25" /></a></div>
<div class="order_page">
		<input name="orderId" value="${order.orderId}" type="hidden"/>
		<table class="trade_table" cellpadding="0" cellspacing="0">
	    	<tbody>
	        	<tr>
	            	<td class="td_left"><img src="${base}/res/html5/images/order_info_01.png" width="30" height="30" /></td>
	                <td>
	                	<ul>
			           		 <li class="big"><span class="fl">订单退货</span></li>
	                       
	                    </ul>
	                </td>
	            </tr>
	        </tbody>
	    </table>
	    <form action="" method="post" id="myForm">
			<div class="order_page_box">
				 <#if order.orderGoodsList??>
		        	<#list order.orderGoodsList as orderGoods>
		        		<input name="goodsId" value="${orderGoods.recId}" type="hidden"/>
						<div class="order_goods">
					       	<div class="dt">
					       		<a  href="${base}/m/goods/goodsdetail?id=${orderGoods.goodsId}">
			          				<img src="${imgServer}${orderGoods.goodsImage}" width="50" height="50">
			          			</a>
					       	</div>
					        <div class="dd dd_01">
						        <span class="name">
						        	<a href="${base}/m/goods/goodsdetail?id=${orderGoods.goodsId}">${orderGoods.goodsName}</a>
						        </span></br>
						        <span class="name">
						        	${orderGoods.specInfo}
						        </span>
						        <span class="size"> </span>
					        </div>
					         <div class="dd dd_01">
						        <span class="name">
						        	<input name="goodsNum" value="${orderGoods.goodsNum}" type="hidden"/>
						        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						        	可退数量：&nbsp;&nbsp;${orderGoods.goodsNum}
						        </span>
						        <span class="name"></br>
								<label>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									本次退货：&nbsp;
									<input type="text" id="returnGoodsNum_${orderGoods.recId}" name="returnGoodsNum_${orderGoods.recId}" value="${orderGoods.goodsNum}"  onChange="change(this)"/>            
						  		</label>
						        </span>
						        <span class="size"> </span>
					         </div>
				        </div>
				   </#list>
		      </#if>
		        <div class="order_goods">
			       	<span class="fl">
			       		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;退货原因：
			       	</span>
			        <span class="fl">
			       		<textarea name="buyer_message"></textarea>
			       	</span>
			       
		        </div>
		        <div class="order_goods">
			       	<span class="fl">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			       		退货信息只能填写提交一次，建议与卖家沟通后认真填写。
			       	</span>
		        </div>
			</div>
	</form>
</div>
<div class="order_foot">
	<a class="bg_red" href="javascript:void(0)" onclick="returnOrder()" id="confirm_button" name="confirm_button"> 确定 </a>               
</div>

</body>
</html>
