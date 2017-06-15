<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<meta charset="UTF-8">
		<title>订单发票</title>
		<link rel="stylesheet" type="text/css" href="${base}/res/html5/css2/fapiao.css"/>
		<link rel="stylesheet" type="text/css" href="${base}/res/html5/css2/reset.css"/>
		<script src="${base}/res/html5/js/jquery-2.1.1.min.js"></script>
		<script src="${base}/res/js/layer/layer.js"></script>
		
	</head>
	<body>
		<div class="wrap">
			<div class="header">
				<a href="javascript:history.go(-1);"><img src="${base}/res/html5/img/fanhui_03.png" /></a>
				<p>设置发票信息</p>
			</div>
			<div class="zhifu_cont">
				<p>发票类型</p>
				<div class="imgs">
					<button>纸质发票</button>
				</div>
			</div>
			<span id="fptt_span" style="display:none;">
			<div style="margin-top: 3%; height:70px ;" class="zhifu_cont" id="fptt_div">
				<p>发票抬头</p>
				<div class="imgs_fu">
					<div class="kong2" id="taitou_gr" onClick="fpTitle('taitou_gr', 'taitou_gs');"></div>
					<p id="p_gr" onClick="fpTitle('taitou_gr', 'taitou_gs');">个人</p>
					<div class="kong1" id="taitou_gs" onClick="fpTitle('taitou_gs', 'taitou_gr');"></div>
					<p id="p_dw" onClick="fpTitle('taitou_gs', 'taitou_gr');">单位</p>
				</div>
				<input type="text" placeholder="请填写公司名称" id="invoiceTitle" name="invoiceTitle" value="个人" style="display:none;"/>
			</div>
			</span>
			<div class="fapiao_cont">
				<h1>发票内容</h1>
				<p>图书商品</p>
				<ul>
					<li style="margin-top: 0;"><div class="kong2" id="fc_bk" onClick="fpContent('fc_bk', 'fc_jc', 'fc_ts', 'fc_zl', 'fc_yx', '');"></div>
						<lable onClick="fpContent('fc_bk', 'fc_jc', 'fc_ts', 'fc_zl', 'fc_yx', '');">&nbsp;不开发票</lable></li>
					<li><div class="kong1" id="fc_jc" onClick="fpContent('fc_jc', 'fc_bk', 'fc_ts', 'fc_zl', 'fc_yx', '食品');"></div>
						<lable onClick="fpContent('fc_jc', 'fc_bk', 'fc_ts', 'fc_zl', 'fc_yx', '食品');">&nbsp;食品</lable></li>
					<li><div class="kong1" id="fc_ts" onClick="fpContent('fc_ts', 'fc_jc', 'fc_bk', 'fc_zl', 'fc_yx', '生活用品');"></div>
						<lable onClick="fpContent('fc_ts', 'fc_jc', 'fc_bk', 'fc_zl', 'fc_yx', '生活用品');">&nbsp;生活用品</lable></li>
					<li><div class="kong1" id="fc_zl" onClick="fpContent('fc_zl', 'fc_jc', 'fc_ts', 'fc_bk', 'fc_yx', '车用品');"></div>
						<lable onClick="fpContent('fc_zl', 'fc_jc', 'fc_ts', 'fc_bk', 'fc_yx', '车用品');">&nbsp;车用品</lable></li>
					<li><div class="kong1" id="fc_yx" onClick="fpContent('fc_yx', 'fc_jc', 'fc_ts', 'fc_zl', 'fc_bk', '婴儿用品');"></div>
						<lable onClick="fpContent('fc_yx', 'fc_jc', 'fc_ts', 'fc_zl', 'fc_bk', '婴儿用品');">&nbsp;婴儿用品</lable></li>
					<input type="hidden" id="invoiceContent" name="invoiceContent" value="">
				</ul>
			</div>
			<a href="javascript:void(0);" onClick="returnorder();"><button class="but">确定</button></a>
		</div>
	</body>
	
	<script type="text/javascript">
		function returnorder() {
			var cartIds = "${cartIds}";
			var ititle = $("#invoiceTitle").val();
			var invoiceContent = $("#invoiceContent").val(); 
			
			if($.trim(invoiceContent)=="") {
				window.location.href="${base}/m/authc/cart/cartOrder?cartIds=${cartIds}&addressId=${addressId}&invoiceId=0";
				return;
			}
			
			$.ajax({
	        	url:'${base}/m/invoice/saveInvoice',
	            type:'post',
	            data : {cartIds:cartIds, title:ititle, content: invoiceContent},
	            dataType:'json',
	            success:function(data){
	            	if(data.success && data.invoice!=null) {
	            		// 购物车订单页
	        			window.location.href="${base}/m/authc/cart/cartOrder?cartIds=${cartIds}&addressId=${addressId}&invoiceId="+data.invoice.invId;
	                }
	            } ,error:function() {
	            	layer.msg('提交失败', {icon: 2});
	            }
	        });	
		}
		
		/* 选中chk1 */
		function fpTitle(chk1, chk2) {
			$("#"+chk1+"").attr("class","kong2");
			$("#"+chk2+"").attr("class","kong1");
			
			if("taitou_gr"==chk1) {
				$("#invoiceTitle").attr("style", "display:none;");
				$("#invoiceTitle").val("个人");
				$("#fptt_div").height("70px");
			} else {
				$("#invoiceTitle").attr("style", "display:inline;");
				$("#invoiceTitle").val("");
				$("#fptt_div").height("120px");
			}
		}
		
		function fpContent(chk1, chk2, chk3, chk4, chk5, content) {
			$("#"+chk1+"").attr("class","kong2");
			$("#"+chk2+"").attr("class","kong1");
			$("#"+chk3+"").attr("class","kong1");
			$("#"+chk4+"").attr("class","kong1");
			$("#"+chk5+"").attr("class","kong1");
			
			if("fc_bk"==chk1) {
				$("#fptt_span").css("display", "none");
			} else {
				$("#fptt_span").css("display", "inline");
			}
			
			$("#invoiceContent").val(content);
		}
	</script>
</html>
