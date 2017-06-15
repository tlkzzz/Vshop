<div class="wrap">
    <div class="tabmenu">
        <ul class="tab pngFix">
            <li class="active"><a href="javascript:void(0)">地址列表</a></li></ul>
        <a href="javascript:void(0)" class="ncu-btn3" onclick="addAddress()" title="新增地址">新增地址</a></div>
    <table class="ncu-table-style" >
        <thead>
        <tr>
            <th class="w70">收货地址</th>
            <th class="tl">收货人</th>
            <th class="tl">所在地区</th>
            <th class="tl">街道地址</th>
            <th class="tl">邮编</th>
            <th class="tl">手机号码</th>
            <th class="w90">操作</th>
        </tr>
        </thead>
        <tbody>
        <#if lists??>
            <#list lists as item>
                <tr class="bd-line">
                	<td><input type="radio" name="is_default" <#if item.isDefault == '1'>checked</#if> value="${item.addressId}"> 默认</td>
                    <td class="tl">${item.trueName}</td>
                    <td class="tl">${item.areaInfo}</td>
                    <td class="tl">${item.address}</td>
                    <td class="tl">${item.zipCode}</td>
                    <td class="tl"><span class="mob">${item.mobPhone}</span></td>
                    <td><p><a href="javascript:void(0);" onclick="updateAddress(${item.addressId})">编辑地址</a></p>
                        <p><a href="javascript:void(0)" onclick="deleteAddress(${item.addressId})" class="ncu-btn2 mt5">删&nbsp;除</a></p></td>
                </tr>
            </#list>
        <#else>
            <tr>
                <td colspan="20" class="norecord"><i>&nbsp;</i><span>暂无记录</span></td>
            </tr>
        </#if>
        <tfoot>
            <tr>
                <td colspan="20">
                    <#import "/commons/userpage.ftl" as q><!--引入分页-->
                    <#if recordCount??>
                        <@q.pager pageNo=pageNo pageSize=pageSize recordCount=recordCount toURL="${toUrl}"/>
                    </#if>
                </td>
            </tr>
        </tfoot>
    </table>
</div>
<script type="text/javascript" src="${base}/res/js/common_select.js" charset="utf-8"></script>
<script type="text/javascript">
$(function (){
	$('input[name="is_default"]').bind('click',function(){
		var addressId = $(this).val();
		$.ajax({
			type: "post",
	 		url: '${base}/myaddress/updateDef',
			data: {addressId:addressId},
			dataType: "json",
			success:function(data) {
				if(data.success){
					$(this).attr(checked);
				}else{
					layer.msg("默认地址设置失败!",{icon:2});
				}
			}
		});  
	});
});
</script>