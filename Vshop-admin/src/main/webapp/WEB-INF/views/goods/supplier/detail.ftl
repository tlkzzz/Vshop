<@layout.head>
<script type="text/javascript" src="${base}/res/js/jquery-ui/jquery.ui.js"></script>
<script type="text/javascript" src="${base}/res/js/jquery-ui/i18n/zh-CN.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="${base}/res/js/jquery-ui/themes/ui-lightness/jquery.ui.css"  />
<script type="text/javascript" src="${base}/res/js/jquery.edit.js"></script>
<script type="text/javascript" src="${base}/res/js/jquery.nyroModal/custom.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/jquery.poshytip.min.js" charset="utf-8"></script>
<link href="${base}/res/js/jquery.nyroModal/styles/nyroModal.css" rel="stylesheet" type="text/css" id="cssfile2" />
<script type="text/javascript">
    $(function(){
	    $("#submitBtn").click(function(){
	            $("#form_store_verify").submit();
	    });
	    var supplierState = $("#supplierState").val();
	    if(supplierState != null && supplierState != 2){
	       $("#checkstate").hide();
	    }
    });
</script>
</@layout.head>
<@layout.body>
<div class="page">
<#assign supplierName="供应商">
<div class="fixed-bar">
    <div class="item-title">
        <h3>${supplierName}</h3>
        <ul class="tab-base">
            <li><a href="${base}/goods/supplier/list"><span>管理</span></a></li>
            <li><a href="${base}/goods/supplier/auditList" ><span>注册审请</span></a></li>
            <li><a href="JavaScript:void(0);" class="current"><span>查看${supplierName}</span></a></li>
        </ul>
    </div>
</div>
<div class="fixed-empty"></div>
<table border="0" cellpadding="0" cellspacing="0" class="store-joinin">
    <thead>
	    <tr>
	        <th colspan="20">${supplierName}及联系人信息</th>
	    </tr>
    </thead>
    <tbody>
	    <tr>
	        <th>${supplierName}名称：</th>
	        <td>
	           ${supplier.name}
	           <input type="hidden"  id="supplierState" value="${supplier.supplierState}"/>
	        </td>
	        <th>账号：</th>
	        <td colspan="20">${supplier.memberName}</td>
	    </tr>
	    <tr>
	        <th>${supplierName}所在地：</th>
	        <td>${supplier.areaInfo}</td>
	        <th>${supplierName}详细地址：</th>
	        <td colspan="20">${supplier.address}</td>
	    </tr>
	    <tr>
	        <th>联系电话：</th>
	        <td>${supplier.mobile}</td>
	        <th>邮政编码：</th>
	        <td>${supplier.storeZip}</td>
	    </tr>
	    <tr>
	    	<th>所属品牌：</th>
	    	<td  colspan="20"><#list brands as b>${b.brandName}<#if b_has_next>，</#if></#list></td>
	    </tr>
    </tbody>
</table>
<table border="0" cellpadding="0" cellspacing="0" class="store-joinin">
    <thead>
    <tr>
        <th colspan="20">营业执照信息（副本）</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th>营业执照号：</th>
        <td>${supplier.busLicenseNO}</td>
        <th>电子版执照：</th>
        <td colspan="20">
            <a nctype="nyroModal"  href="${imgServer}${supplier.busLicenPurl}">
            <img src="${imgServer}${supplier.busLicenPurl}" alt="" /> </a>
        </td>
    </tr>
    </tbody>
</table>
<table border="0" cellpadding="0" cellspacing="0" class="store-joinin">
    <thead>
	    <tr>
	        <th colspan="20">${supplierName}法人信息</th>
	    </tr>
    </thead>
    <tbody>
    <tr>
        <th>法人代表姓名：</th>
        <td>${supplier.legaler}</td>
        <th>法人代表身份证：</th>
        <td>${supplier.legalerID}</td>
    </tr>
    <tr>
        <th>开户行信息：</th>
        <td>${supplier.openingBank}</td>
        <th>公司账号：</th>
        <td>${supplier.accountNO}</td>
    </tr>
    <tr>
        <th>法人代表身份证照：</th>
        <td>
            <a nctype="nyroModal"  href="${imgServer}${supplier.legalerPurl}">
            <img src="${imgServer}${supplier.legalerPurl}" alt="" /> </a>
        </td>
        <th>机构LOGO：</th>
        <td>
            <a nctype="nyroModal"  href="${imgServer}${supplier.supplierLogo}">
            <img src="${imgServer}${supplier.supplierLogo}" alt="" /> </a>
        </td>
    </tr>
    </tbody>
</table>
<form id="form_store_verify" action="${base}/goods/supplier/verify" method="post">
    <input name="memberId" type="hidden" value="${supplier.memberId}" />
    <input name="id" id="id" type="hidden" value="${supplier.id}"/>
   <table  border="0" cellpadding="0" cellspacing="0" class="store-joinin" id="checkstate">
        <thead>
	        <tr>
	            <th colspan="20">审核:</th>
	        </tr>
        </thead>
        <tbody>
	        <tr>
	        	<th>审核:</th>
	            <td class="vatop rowform onoff" rowspan="4">
		                <label for="supplier_state1" class="cb-enable selected" ><span>通过</span></label>
		                <label for="supplier_state0" class="cb-disable" ><span>失败</span></label>
		                <input id="supplier_state1" name="supplierState" checked="checked" value="1" type="radio" onclick="javascript:$('#nopass2').hide(0);"/>
		                <input id="supplier_state0" name="supplierState" value="3" type="radio" onclick="javascript:$('#nopass2').show(0);"/>
	            </td>
	        </tr>
	        <tr class="noborder" id="nopass2" style="display: none;">
	            <th>未通过原因:</th>
	            <td class="vatop rowform"  colspan="4"><textarea name="supplierCloseInfo" rows="6" class="tarea" id="supplier_close_info"></textarea></td>
	        </tr>
       </tbody>
   </table>
        <a href="JavaScript:void(0);" class="btn" id="submitBtn" style="margin-left: 390px;"><span>提交</span></a>
</form>
</div>
</@layout.body>