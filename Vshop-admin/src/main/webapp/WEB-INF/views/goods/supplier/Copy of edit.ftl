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
    <div class="fixed-bar">
        <div class="item-title">
            <h3>供应商</h3>
            <ul class="tab-base">
                <li><a href="${base}/goods/supplier/list"><span>管理</span></a></li>
                <li><a href="${base}/goods/supplier/forward?id=0"><span>新增</span></a></li>
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
                <td colspan="2" class="required"><label class="validation">供应商名称:</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type="text" value="${supplier.name}" name="name" id="name" class="txt"></td>
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
            <tr>
                <td colspan="2" class="required">简称/缩写:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' value="${supplier.shortName}" name='shortName' id='shortName' class='txt' /></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">供应商编号:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' value="${supplier.supplierNO}" name='supplierNO' id='supplierNO' class='txt' /></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">供应商类型:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform">
                    <select name="entType" class="class-select" style="width:260px;">
                        <option value="0">请选择...</option>
                        <#list entTypes as et>
                            <option value="${et.dictionaryId}" <#if et.dictionaryId == supplier.entType>selected="selected"</#if>>${et.dictionaryName}</option>
                        </#list>
                    </select>
                </td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">供应商地址:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' value="${supplier.address}" name='address' id='address' class='txt' /></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">地址经度:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' value="${supplier.longitude}" name='longitude' id='longitude' class='txt' /></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">地址纬度:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' value="${supplier.latitude}" name='latitude' id='latitude' class='txt' /></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">联系人:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' value="${supplier.contacter}" name='contacter' id='contacter' class='txt' /></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">移动电话:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' value="${supplier.mobile}" name='mobile' id='mobile' class='txt' /></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">固定电话:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' value="${supplier.fixedTel}" name='fixedTel' id='fixedTel' class='txt' /></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">传真:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' value="${supplier.fax}" name='fax' id='fax' class='txt' /></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">经营业态:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform">
                    <select name="busType" class="class-select" style="width:260px;">
                        <option value="0">请选择...</option>
                        <#list busTypes as bt>
                            <option value="${bt.dictionaryId}" <#if bt.dictionaryId == supplier.busType>selected="selected"</#if>>${bt.dictionaryName}</option>
                        </#list>
                    </select>
                </td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">市场类型:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform">
                    <select name="marketType" class="class-select" style="width:260px;">
                        <option value="0">请选择...</option>
                        <#list marketTypes as mt>
                            <option value="${mt.dictionaryId}" <#if mt.dictionaryId == supplier.marketType>selected="selected"</#if>>${mt.dictionaryName}</option>
                        </#list>
                    </select>
                </td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">经营面积:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' value="${supplier.busArea}" name='busArea' id='busArea' class='txt' /></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">客户经理:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' value="${supplier.managerId}" name='managerId' id='managerId' class='txt' /></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">营业执照注册号:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' value="${supplier.busLicenseNO}" name='busLicenseNO' id='busLicenseNO' class='txt' /></td>
                <td class="vatop tips"></td>
            </tr>
            
            <tr>
                <td colspan="2" class="required">营业执照图片:  </td>
            </tr>
            <tr class="noborder">
            	<td class="vatop rowform">
	            	<span class="type-file-box">
		            	<input type='text' name='textfield' id='textfield1' class='type-file-text' />
		            	<input type='button' name='button' id='button1' value='' class='type-file-button' />
		            	<input name="imageFile" type="file" class="type-file-file" id="busLicenPurl" size="30" hidefocus="true" nc_type="change_pic">
	            	</span>
            	</td>
                <td class="vatop tips">支持格式gif,jpg,jpeg,png</td>
            </tr>
            
            <tr>
                <td colspan="2" class="required">地税税务登记编号:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' value="${supplier.dishuiRegistNO}" name='dishuiRegistNO' id='dishuiRegistNO' class='txt' /></td>
                <td class="vatop tips"></td>
            </tr>
            
            <tr>
                <td colspan="2" class="required">地税税务登记图片:  </td>
            </tr>
            <tr class="noborder">
            	<td class="vatop rowform">
	            	<span class="type-file-box">
		            	<input type='text' name='textfield' id='textfield1' class='type-file-text' />
		            	<input type='button' name='button' id='button1' value='' class='type-file-button' />
		            	<input name="imageFile" type="file" class="type-file-file" id="dishuiRegistPurl" size="30" hidefocus="true" nc_type="change_pic">
	            	</span>
            	</td>
                <td class="vatop tips">支持格式gif,jpg,jpeg,png</td>
            </tr>
            
            <tr>
                <td colspan="2" class="required">国税税务登记编号:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' value="${supplier.guoshuiRegistNO}" name='guoshuiRegistNO' id='guoshuiRegistNO' class='txt' /></td>
                <td class="vatop tips"></td>
            </tr>
            
            <tr>
                <td colspan="2" class="required">国税税务登记照片:  </td>
            </tr>
            <tr class="noborder">
            	<td class="vatop rowform">
	            	<span class="type-file-box">
		            	<input type='text' name='textfield' id='textfield1' class='type-file-text' />
		            	<input type='button' name='button' id='button1' value='' class='type-file-button' />
		            	<input name="imageFile" type="file" class="type-file-file" id="guoshuiRegistPurl" size="30" hidefocus="true" nc_type="change_pic">
	            	</span>
            	</td>
                <td class="vatop tips">支持格式gif,jpg,jpeg,png</td>
            </tr>
            
            <tr>
                <td colspan="2" class="required">增值税一般纳税人照片:  </td>
            </tr>
            <tr class="noborder">
            	<td class="vatop rowform">
	            	<span class="type-file-box">
		            	<input type='text' name='textfield' id='textfield1' class='type-file-text' />
		            	<input type='button' name='button' id='button1' value='' class='type-file-button' />
		            	<input name="imageFile" type="file" class="type-file-file" id="zzshuiPurl" size="30" hidefocus="true" nc_type="change_pic">
	            	</span>
            	</td>
                <td class="vatop tips">支持格式gif,jpg,jpeg,png</td>
            </tr>
            
            <tr>
                <td colspan="2" class="required">法定代表人:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' value="${supplier.legaler}" name='legaler' id='legaler' class='txt' /></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">法定代表人身份证:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' value="${supplier.legalerID}" name='legalerID' id='legalerID' class='txt' /></td>
                <td class="vatop tips"></td>
            </tr>
            
            <tr>
                <td colspan="2" class="required">法定代表人身份证照片:  </td>
            </tr>
            <tr class="noborder">
            	<td class="vatop rowform">
	            	<span class="type-file-box">
		            	<input type='text' name='textfield' id='textfield1' class='type-file-text' />
		            	<input type='button' name='button' id='button1' value='' class='type-file-button' />
		            	<input name="imageFile" type="file" class="type-file-file" id="legalerPurl" size="30" hidefocus="true" nc_type="change_pic">
	            	</span>
            	</td>
                <td class="vatop tips">支持格式gif,jpg,jpeg,png</td>
            </tr>
             
            <tr>
                <td colspan="2" class="required">商家类型:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform">
                    <select name="shopType" class="class-select" style="width:260px;">
                        <option value="0">请选择...</option>
                        <#list shopTypes as st>
                            <option value="${st.dictionaryId}" <#if st.dictionaryId == supplier.shopType>selected="selected"</#if>>${st.dictionaryName}</option>
                        </#list>
                    </select>
                </td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">结算银行:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' value="${supplier.accountBank}" name='accountBank' id='accountBank' class='txt' /></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">开户行:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' value="${supplier.openingBank}" name='openingBank' id='openingBank' class='txt' /></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">结算银行账号:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' value="${supplier.accountNO}" name='accountNO' id='accountNO' class='txt' /></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">开户人姓名:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' value="${supplier.openingName}" name='openingName' id='openingName' class='txt' /></td>
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
            <!-- 
            <tr>
                <td colspan="2" class="required">月结日期:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' name='acountDate' id='acountDate' class='txt' /></td>
                <td class="vatop tips"></td>
            </tr>
             -->
            <tr>
                <td colspan="2" class="required">删除状态: </td>
            </tr>
           
            <tr class="noborder">
                <td class="vatop rowform onoff">
                	<label for="deleted1" class="<#if supplier.deleted == 1>cb-enable selected<#else>cb-enable</#if>"><span>是</span></label>
                    <label for="deleted0" class="<#if supplier.deleted == 0>cb-disable selected<#else>cb-disable</#if>"><span>否</span></label>
                    <input id="deleted1" name="deleted"  value="1" type="radio" <#if supplier.deleted == 1>checked="checked"</#if>>
                    <input id="deleted0" name="deleted" value="0" type="radio" <#if supplier.deleted == 0>checked="checked"</#if>>
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
					_.each($('#brandTable').datagrid('getSelections'), function(v){brandIds.push(v.brandId), brandNames.push(v.brandName)});
					!_.isEmpty(brandIds) && $('#brandId').val(brandIds.join(',')) && $('#brandName').val(brandNames.join(','));
					$( this ).dialog( "close" );
				},
				"清除": function(){
					$('#brandTable').datagrid('clearSelections');
				}
			},
			modal:true
		});
		$('#brandTable').datagrid({
			width:650,
			height:300,
			rownumbers:true,
		    url:'${base}/goods/supplier/bl',
		    frozenColumns:[[
		        {field:'ck', checkbox:true},
			    {title:'品牌ID',field:'brandId',width:100,sortable:true}
			]],
		    columns:[[
		        {field:'brandName',title:'品牌名称',width:200},
		        {field:'brandClass',title:'品牌类型',width:200, align:'left'}
		    ]]
		});
	}
	$(function(){
		$('#busLicenPurl,#dishuiRegistPurl,#guoshuiRegistPurl,#zzshuiPurl,#legalerPurl').change(function(){
			$(this).siblings(':eq(0)').val($(this).val());
		});
		
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