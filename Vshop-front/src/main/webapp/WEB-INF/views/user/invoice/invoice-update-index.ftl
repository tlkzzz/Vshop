<link href="${base}/res/css/shop.css" rel="stylesheet" type="text/css">
<link href="${base}/res/css/style/default/style.css"  rel="stylesheet" type="text/css">
<link href="${base}/res/css/base.css" rel="stylesheet" type="text/css">
<link href="${base}/res/css/invoice.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${base}/res/js/area.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/jquery.js" charset="utf-8"></script>

<#assign invoiceInfoTag = newTag("invoiceInfoTag")>
<#assign invoices =invoiceInfoTag("{'isDefault':'1'}")>

<div class="invoice-thickbox" id="invoice-tab">
	<div class="tab-nav">
		<br/>
		<ul>
			<#if invoices?size gt 0>
				<#if invoices[0].invState == '1'>
					<li id="click_1" class="tab-nav-item tab-item-selected" val="updateInvoiceCommon">普通发票<b></b></li>
					<li id="click_3" class="tab-nav-item" val="updateInvoiceVAT">增值税发票<b></b></li>
				<#else>
					<li id="click_1" class="tab-nav-item" val="updateInvoiceCommon">普通发票<b></b></li>
					<li id="click_3" class="tab-nav-item tab-item-selected" val="updateInvoiceVAT">增值税发票<b></b></li>
				</#if>
			<#else>
				<li id="click_1" class="tab-nav-item tab-item-selected" val="updateInvoiceCommon">普通发票<b></b></li>
				<li id="click_3" class="tab-nav-item" val="updateInvoiceVAT">增值税发票<b></b></li>
			</#if>
		</ul>
	</div>
	<div id="invoiceBody">
	
	</div>
	

<script>
	//初始化
	function init(){
		changeTable($(".tab-nav-item").attr("val"));
		//changeTable($(".tab-nav-item tab-item-selected").attr("value"));
	}
	
	//切换table
	function changeTable(action){
		var url = "${base}/invoice/" + action;
		$.post(url, function(data){
			$("#invoiceBody").empty();
			$("#invoiceBody").append(data);
		});
	}
	
	$(function(){
		//初始化
		init();
		
		//radio触发事件
		/* $("[name=invoiceType]").live("change",function(){
			changeTable($(this).val());
		}); */
		
		$(".tab-nav-item").live("click",function(){
			changeTable($(this).attr("val"));
			$(".tab-nav-item").each(function(){
				$(this).removeClass();
				$(this).addClass("tab-nav-item");
			});
			$(this).addClass("tab-nav-item tab-item-selected");
		});
	});
</script>