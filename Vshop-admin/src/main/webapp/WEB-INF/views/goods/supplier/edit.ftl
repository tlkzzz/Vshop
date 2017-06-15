<@layout.head>
<link rel="stylesheet" type="text/css" href="${base}/res/css/easyui/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${base}/res/css/easyui/icon.css">
<script type="text/javascript" src="${base}/res/js/common/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${base}/res/js/jquery-ui/jquery.ui.js"></script>
<script type="text/javascript" src="${base}/res/js/jquery-ui/i18n/zh-CN.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="${base}/res/js/jquery-ui/themes/ui-lightness/jquery.ui.css"  />
<script type="text/javascript" src="${base}/res/js/admincp.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/common_select.js" charset="utf-8"></script>
<!-- 
<script type="text/javascript" src="${base}/res/js/goods/supplier.js" charset="utf-8"></script>
 -->
</@layout.head>
<@layout.body>
<div class="page">
	<#assign supplierName="供应商">
    <div class="fixed-bar">
        <div class="item-title">
            <h3>${supplierName}</h3>
            <ul class="tab-base">
                <li><a href="${base}/goods/supplier/list"><span>管理</span></a></li>
                <li><a href="${base}/goods/supplier/auditList"><span>注册审请</span></a></li>
                <li><a href="JavaScript:void(0);" class="current"><span>编辑</span></a></li>
            </ul>
        </div>
    </div>
    <div class="fixed-empty"></div>
    <form id="supperlier_form" enctype="multipart/form-data" method="post" name="form1" action="${base}/goods/supplier/edit">
        <input type="hidden" name="form_submit" value="ok" />
        <input type="hidden" name="id" value="${supplier.id}" />
        <table class="table tb-type2 nobdb">
            <tbody>
            <tr class="noborder">
                <td colspan="2" class="required"><label class="validation">${supplierName}名称:</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type="text" value="${supplier.name}" name="name" id="name" class="txt" disabled="disabled"></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">${supplierName}编号:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' value="${supplier.supplierNO}" name='supplierNO' id='supplierNO' class='txt'/></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">选择品牌: </td>
            </tr>
            <tr class="noborder">
            	<td class="vatop rowform">
            		<input type="text" value="<#list brands as b>${b.brandName}<#if b_has_next>,</#if></#list>" name="brandName" id="brandName" class="txt" readonly="readonly">
            		<input type="hidden" name="brandId" id="brandId" value="<#list list as b>${b.brandId}<#if b_has_next>,</#if></#list>" />
            	</td>
                <td class="vatop tips"><a href="JavaScript:void(0);" class="btn" id="optBtn" onclick="optionBrand();"><span>选择</span></a></td>
            </tr>
            <!-- 
            <tr class="noborder">
                <td colspan="2" class="required"><label class="validation">${supplierName}名称:</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type="text" value="${supplier.name}" name="name" id="name" class="txt" disabled="disabled"></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">简称/缩写:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' value="${supplier.shortName}" name='shortName' id='shortName' class='txt'  disabled="disabled"/></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">${supplierName}编号:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' value="${supplier.supplierNO}" name='supplierNO' id='supplierNO' class='txt'/></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">${supplierName}地址:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' value="${supplier.address}" name='address' id='address' class='txt' disabled="disabled"/></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">法定代表人:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' value="${supplier.legaler}" name='legaler' id='legaler' class='txt' disabled="disabled" /></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">法定代表人身份证:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' value="${supplier.legalerID}" name='legalerID' id='legalerID' class='txt' disabled="disabled" /></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">开户行信息:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' value="${supplier.accountBank}" name='accountBank' id='accountBank' class='txt' disabled="disabled" /></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">开户行:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' value="${supplier.openingBank}" name='openingBank' id='openingBank' class='txt' disabled="disabled" /></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">结算银行账号:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' value="${supplier.accountNO}" name='accountNO' id='accountNO' class='txt' disabled="disabled" /></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">开户人姓名:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' value="${supplier.openingName}" name='openingName' id='openingName' class='txt' disabled="disabled" /></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">结算方式:  </td>
            </tr>
            <tr class="noborder">
            	<td class="vatop rowform">
                    <select name="accountMethod" class="class-select" style="width:260px;">
                        <option value="0">请选择...</option>
                        <#list accountMethods as am>
                            <option value="${am.dictionaryId}" <#if am.dictionaryId == supplier.accountMethod>selected="selected"</#if>>${am.dictionaryName}</option>
                        </#list>
                    </select>
                </td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">结算周期:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' value="${supplier.accountCycle}" name='accountCycle' id='accountCycle' class='txt' /></td>
                <td class="vatop tips"></td>
            </tr>
             -->
            <tr>
                <td colspan="2" class="required">状态: </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform">
                	<label for="supplier_state0"><span>关闭</span></label>
	                <input id="supplier_state0" type="radio" value="0" name="supplierState" <#if supplier.supplierState == 0>checked="checked"</#if>>&nbsp;&nbsp;
	                <label for="supplier_state2"><span>审核</span></label>
	                <input id="supplier_state2" type="radio" value="2" name="supplierState" <#if supplier.supplierState == 2>checked="checked"</#if>>&nbsp;&nbsp;
	                <label for="supplier_state1"><span>通过</span></label>
	                <input id="supplier_state1" type="radio" value="1" name="supplierState" <#if supplier.supplierState == 1>checked="checked"</#if> disabled="disabled">&nbsp;&nbsp;
	                <label for="supplier_state3"><span>审核未过</span></label>
	                <input id="supplier_state3" type="radio" value="3" name="supplierState" <#if supplier.supplierState == 3>checked="checked"</#if> disabled="disabled">
	                <!-- 
                	<#if supplier.supplierState == 2>
                		未审核
                    <#else>
                    	<label for="supplierState1" class="cb-enable <#if supplier.supplierState == 1>selected</#if>"><span>开启</span></label>
	                    <label for="supplierState0" class="cb-disable <#if supplier.supplierState == 0>selected</#if>"><span>关闭</span></label>
	                    <input id="supplierState1" name="supplierState"  value="1" type="radio" <#if supplier.supplierState == 1>checked="checked"</#if>>
	                    <input id="supplierState0" name="supplierState" value="0" type="radio" <#if supplier.supplierState == 0>checked="checked"</#if>>
                    </#if>
                     -->
                </td>
                <td class="vatop tips"></td>
            </tr>
            </tbody>
            <tfoot>
            <tr class="tfoot">
                <td colspan="2" ><a href="JavaScript:void(0);" class="btn" id="submitBtn"><span>提交</span></a></td>
            </tr>
            </tfoot>
        </table>
    </form>
    <div id="brandTableDialog" title="选择品牌" style="padding:5px;">
		<table id="brandTable"></table>
	</div>
</div>
<script type="text/javascript">
	function optionBrand(){
		$('#brandTableDialog').dialog({
		    width:700,
		    height:400,
			buttons:{
				"确定": function(){
					var brandIds = [], brandNames = [];
					_.each(requiredRows, function(row){brandIds.push(row.brandId), brandNames.push(row.brandName)});
					_.each($('#brandTable').datagrid('getSelections'), function(v){brandIds.push(v.brandId), brandNames.push(v.brandName)});
					brandIds = _.uniq(brandIds), brandNames = _.uniq(brandNames);
					if(!_.isEmpty(brandIds)){
						$('#brandId').val(brandIds.join(',')) && $('#brandName').val(brandNames.join(','));
						$( this ).dialog( "close" );
					}else{
						alert("至少选择一个品牌");
					}
				},
				"清除": function(){
					_.isEmpty(requiredRows) && $('#brandTable').datagrid('clearSelections');
				}
			},
			modal:true
		});
		var $checks, requiredRows = [];
		$('#brandTable').datagrid({
			width:650,
			height:300,
			idField:'brandId',
			rownumbers:true,
		    url:'${base}/goods/supplier/bl?supplierId=${supplier.id}',
		    frozenColumns:[[{field:'ck', checkbox:true}]],
		    columns:[[
				{title:'品牌ID',field:'brandId',width:100,sortable:true},
		        {field:'brandName',title:'品牌名称',width:200},
		        {field:'brandClass',title:'品牌类型',width:200, align:'left'}
		    ]],
		    onLoadSuccess:function(data){
		    	if(data){
		    		$checks = $('[name=ck'), allRows = data.rows;
			    	$.each(data.rows, function(index, item){
				    	if(item.check){
				    		$('#brandTable').datagrid('checkRow', index);
				    		item.count > 0 && requiredRows.push(item) && $checks.eq(index).attr('disabled', true) && $('.datagrid-htable').find('input').attr('disabled', true);
				    	}
			    	});
			    }
		    },
		    onBeforeUncheck: function(index, row){
		    	if(row.check && row.count > 0) return false;
		    }
		});
	}
	$(function(){
		//按钮先执行验证再提交表单
		$("#submitBtn").on('click', function(){$("#supperlier_form").valid() && $("#supperlier_form").submit()});
		$("#supperlier_form").validate({
            errorPlacement: function(error, element){
                error.appendTo(element.parent().parent().prev().find('td:first'));
            },
            success: function(label){
                label.addClass('valid');
            },
            rules : {
                name : {
                    required : true,
                    remote   : {
                        url :APP_BASE+'/goods/supplier/validate',
                        type:'get',
                        data:{
                        	name : function(){
                                return $('#name').val();
                            },
                            id  : ${supplier.id}
                        }
                    }
                },
                accountCycle : {
                	digits   : true
                },
                busArea : {
                    number   : true, min : 0
                },
                longitude : {
                    number   : true, min : 0
                },
                latitude : {
                    number   : true, min : 0
                }
            },
            messages : {
            	name : {
                    required : '供应商名称不能为空',
                    remote   : '该供应商名称已经存在了，请您换一个'
                },
                accountCycle : {
                	digits   : '请输一个正整数'
                },
                busArea : {
                    number   : '请输入有效的面积数字', min : '请输入有效的面积数字'
                },
                longitude : {
                    number   : '请输入有效的经度', min : '请输入有效的经度'
                },
                latitude : {
                    number   : '请输入有效的纬度', min : '请输入有效的纬度'
                }
            }
        });
	});
</script>
</@layout.body>