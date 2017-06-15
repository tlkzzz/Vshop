 <@p.header title="商家中心-运费模版"/>

<div class="layout">
<div class="sidebar"></div>
<div class="right-content">
	<div class="path">
		<div>
			<a href="#?act=store">商家中心</a> <span>></span><a
				href="#?act=transport" />物流工具</a><span>></span>物流工具
		</div>
	</div>
	<div class="main">
		<link href="${base }/res/css/transport.css" rel="stylesheet"
	type="text/css">

<div class="wrap">
	<div class="tabmenu">
		<ul class="tab pngFix">
			<li class="active"><a href="${base}/transport/index">运费模板</a></li>
	</ul>
	<a class="ncu-btn3" href="${base}/transport/toAdd">新增运费模板</a>
</div>
<!-----------------list begin------------------------>

<div id="postage-tpl" style="padding: 20px 0;">
<#if transportList?size gt 0>
	<#list transportList as tl>
		<div id="J_TemplateList" class="manage-list">
			<div class="section J_Section">
				<div class="tbl-prefix">
					<span class="meta"> 最后编辑时间：<span class="J_LastModified">${tl.updateTimeStr}</span> 
					<#if tl.isDefault != '1'>
						<a href="javascript:void(0)" data-id="${tl.id}" onClick="setDefTran('${tl.id}')">设为默认的运费模板</a> | 
					</#if>
					<a href="${base}/transport/toEdit?id=${tl.id}">修改</a> | 
					<a class="J_Delete" href="javascript:void(0)" data-id="${tl.id}">删除</a></span>
					<h3 class="J_Title">
						${tl.title}
						<#if tl.isDefault=='1'>
							<font style="color: red;">(默认的运费模板)</font>
						</#if>
					</h3>
					</div>
					<div class="tbl-head">
						<table cellspacing="0" cellpadding="0" border="0">
							<colgroup>
								<col class="col-express">
								<col class="col-area">
								<col class="col-starting">
								<col class="col-postage">
								<col class="col-plus">
								<col class="col-postageplus">
							</colgroup>
							<tbody>
								<tr>
									<th>运送方式</th>
									<th class="cell-area">运送到</th>
									<th>首件(个)</th>
									<th>运费(元)</th>
									<th>续件(个)</th>
									<th>运费(元)</th>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="entity">
						<table cellspacing="0" cellpadding="0" border="0">
							<colgroup>
								<col class="col-express">
								<col class="col-area">
								<col class="col-starting">
								<col class="col-postage">
								<col class="col-plus">
								<col class="col-postageplus">
							</colgroup>
							<tbody>
								<#if tl.transportExtendList??>
									<#list tl.transportExtendList as tel>
										<tr>
											<td>
												<#if tel.type=='py'>
													平邮
												</#if>
												<#if tel.type=='kd'>
													快递
												</#if>
												<#if tel.type=='es'>
													EMS
												</#if>
											</td>
											<td class="cell-area">${tel.areaName}</td>
											<td>${tel.snum}</td>
											<td>${tel.sprice}</td>
											<td>${tel.xnum}</td>
											<td>${tel.xprice}</td>
										</tr>
									</#list>
								</#if>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</#list>
	</#if>
	<div class="list-attach">
		<p class="position2">
		<div class="pagination">
			<ul>
				<li><span>首页</span></li>
				<li><span>上一页</span></li>
				<li><span class="currentpage">1</span></li>
				<li><span>下一页</span></li>
				<li><span>末页</span></li>
			</ul>
		</div>
		</p>
	</div>
</div>

<!-----------------list end----------------------->
</div>
<div class="clear">&nbsp;</div>
<script>
function ok(){
	window.location.href = '${base}/transport/index';
}
$(function(){	
	$('a[class="J_Delete"]').click(function(){
		var id = $(this).attr('data-id');
		if(typeof(id) == 'undefined') return false;
		if (confirm('确认删除此模板?')){
			var url = '${base}/transport/delete';
			var args = {"transportId":id};
			$.post(url, args, function(data){
				if("success" == data){
					layer.msg("删除成功" , {icon:1});
					setTimeout("ok()",1000); 
				}else{
					layer.msg("删除失败" , {icon:2});
				}
			});
		}
	});
	
	$('a[class="J_Clone"]').click(function(){
		var id = $(this).attr('data-id');
		if(typeof(id) == 'undefined') return false;
		$(this).attr('href','#?act=transport&op=clone&type=&id='+id);
		return true;
	});
	$('a[class="ncu-btn2"]').click(function(){
		var data = $(this).attr('enty-data');
		if(typeof(data) == 'undefined') return false;
		data = data.split('|||');
//		opener.document.getElementById("postageName").innerHTML=data[0];
		$("#postageName", opener.document).css('display','inline').html(data[0]);
		$("#transport_id", opener.document).val(data[1]);
		window.close();
	});	
});

//设置默认运费模板
function setDefTran(tranId){
	var url = "${base}/transport/setDefaultTransport"
	var args = {"transportId":tranId};
	$.post(url, args, function(data){
		if(data == "success"){
			layer.msg("修改成功" , {icon:1});
			setTimeout("ok()",1000); 
		}else{
			layer.msg("修改失败" , {icon:2});
		}
	});
}
</script>
		</div>
	</div>
	<div class="clear"></div>
</div>
<@p.footer />
