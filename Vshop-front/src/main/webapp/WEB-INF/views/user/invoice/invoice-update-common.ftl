<link href="${base}/res/css/shop.css" rel="stylesheet" type="text/css">
<link href="${base}/res/css/style/default/style.css" rel="stylesheet"
	type="text/css">
<link href="${base}/res/css/base.css" rel="stylesheet" type="text/css">
<link href="${base}/res/css/invoice.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${base}/res/js/jquery.js"
	charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/layer/layer.js" charset="utf-8"></script>
<#assign invoiceInfoTag = newTag("invoiceInfoTag")> 
<#assign invoices=invoiceInfoTag("{'invState':'1'}")>
<#if invoices?size!=0>
	<#assign invoiceCon = invoices[0].invContent> 
</#if>
<form action="#" method="post" id="invoiceForm">
	<div id="fapiao" class="form">
		<div class="item">
			<span class="label">发票抬头：</span>
			<div class="fl">
				<div class="invoice-list invoice-tit-list" id="invoice-tit-list">
					<div class="invoice-item" style="cursor:pointer" onclick="select(this);">
						<div id="invoice-1" style="cursor:pointer">
							<span class="fore2" id="invoice-r1-0" name="usualInvoiceList" value="0">
								<input style="cursor:pointer" class="itxt" data-r="个人" value="个人"	readonly="true" type="text"><b></b>
							</span>
						</div>
					</div>
					<#list invoices as invs>
						<div date-fid="fid${invs.invId}" class="invoice-item <#if invs.isDefault==1>invoice-item-selected</#if>" style="cursor:pointer" onclick="select(this);">
							<div style="cursor:pointer">
								<span class="fore2" id="invoice-r1-${invs.invId}" name="usualInvoiceList" value="${invs.invId}">
									<input class="itxt" style="cursor:pointer" data-r="${invs.invTitle}" value="${invs.invTitle}" readonly="true" type="text"> <b></b>
								</span>
							</div>
							<div class="btns">
								<a href="javascript:void(0);" class="ftx-05 edit-tit" onclick="update(this);">编辑</a> 
								<a href="javascript:void(0);" class="ftx-05 update-tit hide" onclick="updateInvoice('${invs.invId}',this);">保存</a>
								<a href="javascript:void(0);" class="ftx-05 ml10 del-tit" onclick="deleteInvoice('${invs.invId}')">删除</a>
							</div>
						</div>
					</#list>

					<div id="save-invoice" class="invoice-item hide">
						<div class="add-invoice-tit">
							<input name="" class="itxt itxt04" placeholder="新增单位发票抬头" type="text">
							<div class="btns">
								<a href="javascript:void(0)" class="ftx-05 save-tit" onclick="saveInvoiceTitle(this)">保存</a>
							</div>
						</div>
					</div>

				</div>
				<div style="display: block;" id="add-invoice" class="add-invoice">
					<a href="#none" class="ftx-05 add-invoice-btn" onclick="add_save()">新增单位发票</a>
				</div>
			</div>
		</div>
	</div>
	<div class="tab-box">
		<div class="tab-con ui-switchable-panel-selected">
			<div class="form">
				<div class="item">
					<span class="label">发票内容：</span>
					<div class="fl">
						<div class="invoice-list">
							<ul id="electro_book_content_radio">
								<#if invoiceCon??>
									<li class="invoice-item <#if invoiceCon==1>invoice-item-selected</#if>" id="electro-invoice-content-1" name="normal-normalContent" value="1" style="cursor:pointer">
										不开发票<b></b>
									</li>
									<li class="invoice-item <#if invoiceCon==2>invoice-item-selected</#if>" id="electro-invoice-content-2" name="normal-normalContent" value="2" style="cursor:pointer">
										明细<b></b>
									</li>
								<#else>
									<li class="invoice-item invoice-item-selected" id="electro-invoice-content-1" name="normal-normalContent" value="1" style="cursor:pointer">
										不开发票<b></b>
									</li>
									<li class="invoice-item" id="electro-invoice-content-2" name="normal-normalContent" value="2" style="cursor:pointer">
										明细<b></b>
									</li>
								</#if>
							</ul>
						</div>
					</div>
				</div>
				<div class="item">
					<span class="label">&nbsp;</span>
					<div class="fl">
						<div class="op-btns">
							<a href="#none" class="btn-9" onclick="saveUpInvoice();">保存发票信息</a>
							<a href="#none" class="btn-9 ml10" onclick="quxiao();">取消</a>
						</div>
					</div>
				</div>	
			</div>
		</div>
	</div>
</form>
<script>
	function init() {
		if ($("[name=invTitle]:checked").length == 0) {
			$($("[name=invTitle]").get(0)).attr("checked", "checked");
		}
	}

	//新增发票单位
	function add_save(){
		//将新增发票单位项显示
	
		
		 $('#invoice-tit-list .invoice-item-selected').removeClass('invoice-item-selected');
		$('#save-invoice').addClass('invoice-item-selected').removeClass('hide').show().find('input').removeAttr('readonly').val('').focus();
		$('#invoice-tit-list').scrollTop($('#invoice-tit-list')[0].scrollHeight);
		$('#add-invoice').hide();
	}

	$(function() {
		//init();

		//当发票抬头change
		/* $("[name=invTitleValue]").live("change", function() {
			$("[name=invTitleValue]").each(function() {
				if ($(this).val() != '') {
					$("#addInvoiceA").css("display", "");
				}
			});
		}); */

		//选择发票抬头触发的事件
		/* $("[name=invTitle]").live("change", function() {
			$("[name=invTitle]").each(function() {
				$(this).attr("value", "");
			});
			$(this).attr("value", $(this).next().val());
		}); */
		
		//发票内容选择切换
		$(".tab-box").find(".invoice-item").click(function(){
			//将其他选中样式取消
			$(".tab-box").find(".invoice-item-selected").removeClass("invoice-item-selected");
			//当前选项加入选中样式
			$(this).addClass("invoice-item invoice-item-selected");
		});
		
		//点击发票抬头,显示,编辑删除按钮
		$("#fapiao .invoice-item").mouseover(function(){
			var fid = $(this).attr("date-fid");
			if(typeof(fid)!='undefined'){
				$(this).find(".btns").css("display", "inline");
			}
		});
		
		//点击发票抬头,显示,编辑删除按钮
		$("#fapiao .invoice-item").mouseout(function(){
			var fid = $(this).attr("date-fid");
			if(typeof(fid)!='undefined'){
				$(this).find(".btns").css("display", "none");
			}
		});
		
		//若发票下没有选中默认发票
		if(typeof($("#fapiao .invoice-item-selected").html())=="undefined"){
			$("#invoice-1").parent().addClass("invoice-item-selected");
		}
	});
	
	//选择发票信息
	function select(obj){
		//先将新增发票设为隐藏
		$("#save-invoice").addClass("hide");
		//将其他选中样式取消
		$("#fapiao .invoice-item-selected").removeClass("invoice-item-selected");
		//当前选项加入选中样式
		$(obj).addClass("invoice-item invoice-item-selected");
		
		$('#add-invoice').show();
	}
	
	//保存发票抬头
	function saveInvoiceTitle(obj){
		var val = $(obj).parents(".add-invoice-tit").find("input").val();
		if(val==''){
			parent.layer.alert("输入不能为空");
		}else{
			$.ajax({
	        	url:'${base}/invoice/saveInvoiceTitle',
	            type:'post',
	            data : {title:val},
	            dataType:'json',
	            success:function(data){
	            	var invs = data.invoice;
	            	if(data.success){
	            		//现将新增发票信息设为隐藏
	            		/* $("#save-invoice").addClass("hide");
	            		var str = "<div date-fid='fid"+invs.invId+"' class='invoice-item invoice-item-selected' style='cursor:pointer' onclick='select(this);'>";
	            		str += "<div style='cursor:pointer'>";
	            		str += "<span class='fore2' id='invoice-r1-"+invs.invId+"' name='usualInvoiceList' value='"+invs.invId+"'>";
	            		str += "<input class='itxt' style='cursor:pointer' data-r='"+invs.invTitle+"' value='"+invs.invTitle+"' readonly='true' type='text'> <b></b></span></div>";
	            		str += "<div class='btns'>";
	            		str += "<a href='javascript:void(0);' class='ftx-05 edit-tit'>编辑</a> ";
	            		str += "<a href='avascript:void(0);' class='ftx-05 update-tit hide'>保存</a>";
	            		str += "<a href='javascript:void(0);' class='ftx-05 ml10 del-tit' onclick='parent.delete_Invoice('${invs.invId}')'>删除</a>";
	            		str += "</div></div>";
	            		$("#invoice-1").parent().after(str); */
	            		//刷新当前页面
	            		location.reload();
	                }
	            },error:function(){
	            	parent.layer.msg('通信失败', {icon: 2});
	            }
	        });
		}
	}
	
	//删除发票信息
	function deleteInvoice(id){
		$.ajax({
        	url:'${base}/invoice/deleteInvoice',
            type:'post',
            data : {id:id},
            dataType:'json',
            success:function(data){
            	if(data.success){
            		location.reload();
                }
            },error:function(){
            	parent.layer.msg('通信失败', {icon: 2});
            }
        });
	}
	
	//编辑发票信息
	function update(obj){
		$(obj).addClass("hide");
		$(obj).parent().find(".update-tit").removeClass("hide");	
		$(obj).parent().parent().find(".itxt").attr("readonly",false);
	}
	
	//修改发票抬头信息
	function updateInvoice(id,obj){
		var title = $(obj).parent().parent().find(".itxt").val();
		$.ajax({
        	url:'${base}/invoice/updateInvoice',
            type:'post',
            data : {id:id,title:title},
            dataType:'json',
            success:function(data){
            	if(data.success){
            		location.reload();
                }
            },error:function(){
            	parent.layer.msg('通信失败', {icon: 2});
            }
        });
	}
	
	//关闭窗口
	function quxiao(){
		parent.layer.closeAll();
	}
	
	//保存发票信息,修改发票信息表
	function saveUpInvoice(){
		var invId = $("#fapiao .invoice-item-selected").find(".fore2").attr("value");
		var content = $("#electro_book_content_radio .invoice-item-selected").attr("value");
		var title = $("#fapiao .invoice-item-selected .fore2 input").val();
		$.ajax({
        	url:'${base}/invoice/updateInvoice',
            type:'post',
            data : {id:invId,content:content},
            dataType:'json',
            success:function(data){
            	if(data.success){
            		if(content==2){
            			str = "普通发票&nbsp;&nbsp;&nbsp;&nbsp;"+title+"&nbsp;&nbsp;&nbsp;&nbsp;"+"明细";
            			parent.$("#invoice").html(str);
            			parent.$("input[name='invoiceId']").val(data.id);
            		}else if(content==1){
            			str = "不开发票";
            			parent.$("#invoice").html(str);
            			parent.$("input[name='invoiceId']").val(data.id);
            		}
            		parent.layer.closeAll();
                }
            },error:function(){
            	parent.layer.msg('通信失败', {icon: 2});
            }
        }); 
	}
</script>