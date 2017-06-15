<@p.header title="供应商申请－填写供应商信息"/>
<meta charset="utf-8" />
<div class="layout">
    <script type="text/javascript" src="${base}/res/js/common_select.js" charset="utf-8"></script>
    <script type="text/javascript" src="${base}/res/js/ajaxfileupload/ajaxfileupload.js"></script>
    <script type="text/javascript" charset="utf-8" src="${base}/res/js/area.js"></script>
    <#assign supplierName="供应商">
    <div class="wrap-shadow">
        <div class="wrap-all">
            <div class="chart">
                <div class="pos_x1 bg_a1" title="基本信息" old="请选择${supplierName}分类"></div>
                <div class="pos_x2 bg_b2" title="完善${supplierName}信息" old="填写${supplierName}信息"></div>
                <div class="pos_x3 bg_c" title="完成" old="完成"></div>
            </div>
            <div class="chart" style="height: 40px;color: red;font-weight: bold;padding-left: 100px;">
            	<span>
            	<#if supplier ??&& supplier.supplierState == 2>
                                                     平台审核中，请耐心等待... ...
                <#elseif supplier ??&& supplier.supplierState == 0>
                                                     该供应商已关闭！
                <#elseif supplier ??&& supplier.supplierState == 3>
                	该供应商审核未通过请重新填写注册信息；<span style="color: #BBB;">原因：${supplier.supplierCloseInfo}</span>
                <#elseif supplier ??&& supplier.supplierState == 1>
                	恭喜您供应商注册成功！<span style="color: #BBB;"><span id="timer">6</span>&nbsp;&nbsp;秒后跳转到供应商主页</span>
                </#if>
                </span>
            </div>
            <div class="ncu-form-style grade-shop">
                <form method="post" enctype="multipart/form-data" id="apply_form" action="">
                    <input type="hidden" name="form_submit" value="ok" />
                    <input type="hidden" name="grade_id" value="1" />
                    <input type="hidden" name="id" value="${supplier.id}">
                    <dl>
                        <dt class="required"><em class="pngFix"></em>${supplierName}名称：</dt>
                        <dd>
                            <p>
                                <input class="w400 text" type="text" name="name" id="name" value="${supplier.name}" maxlength="20"/>
                                <span></span></p>
                            <p class="hint">请控制在20个字符以内</p>
                        </dd>
                    </dl>
                    <dl>
                        <dt class="required"><em class="pngFix"></em>选择品牌：</dt>
                        <dd>
                            <p>
                                <input class="w400 text" type="text" name="brandName" id="brandName" value="<#list brands as b>${b.brandName}<#if b_has_next>,</#if></#list>" readonly="readonly"/>
                                <a href="JavaScript:void(0);" class="btn" id="optBtn" onclick="optionBrand();"><span>选择</span></a>
                                <input type="hidden" name="brandId" id="brandId" value="<#list brands as b>${b.brandId}<#if b_has_next>,</#if></#list>">
                                <span></span>
                            </p>
                        </dd>
                    </dl>
                    <!-- 
                    <dl>
                        <dt><em class="pngFix"></em>简称/缩写：</dt>
                        <dd>
                            <p>
                                <input class="w400 text" type="text" name="shortName" id="shortName" maxlength="20" value="${supplier.shortName}"/>
                                <span></span>
                            </p>
                            <p class="hint">请控制在20个字符以内</p>
                        </dd>
                    </dl>
                    <dl>
                        <dt class="required"><em class="pngFix"></em>${supplierName}编号：</dt>
                        <dd>
                            <p>
                                <input class="w400 text" type="text" name="supplierNO" id="supplierNO" value="${supplier.supplierNO}" maxlength="20"/>
                                <span></span>
                            </p>
                            <p class="hint">请控制在20个字符以内</p>
                        </dd>
                    </dl>
                    <dl>
                        <dt class="required"><em class="pngFix"></em>${supplierName}类型：</dt>
                        <dd>
                        	<p>
							    <span></span>
	                            <select name="entType" id="entType">
	                                <option value="0">请选择...</option>
			                        <#list entTypes as et>
			                            <option value="${et.dictionaryId}" <#if et.dictionaryId == supplier.entType>selected="selected"</#if>>${et.dictionaryName}</option>
			                        </#list>
	                            </select>
                            </p>
                        </dd>
                    </dl>
                     -->
                    <dl>
                        <dt class="required"><em class="pngFix"></em>${supplierName}地址：</dt>
                        <dd>
                            <p>
                                <input class="w400 text" type="text" name='address' id='address' value="${supplier.address}"/>
                                <span></span>
                            </p>
                        </dd>
                    </dl>
                    <!-- 
                    <dl>
                        <dt><em class="pngFix"></em>地址经度：</dt>
                        <dd>
                            <p>
                                <input class="w400 text" type="text" name='longitude' id='longitude' value="${supplier.longitude}"/>
                                <span></span>
                            </p>
                        </dd>
                    </dl>
                    <dl>
                        <dt><em class="pngFix"></em>地址纬度：</dt>
                        <dd>
                            <p>
                                <input class="w400 text" type="text" name='latitude' id='latitude' value="${supplier.latitude}"/>
                                <span></span>
                            </p>
                        </dd>
                    </dl>
                     -->
                    <dl>
                        <dt class="required"><em class="pngFix"></em>联系人：</dt>
                        <dd>
                            <p>
                                <input class="w400 text" type="text" name='contacter' id='contacter' value="${supplier.contacter}"/>
                                <span></span>
                            </p>
                        </dd>
                    </dl>
                    <dl>
                        <dt class="required"><em class="pngFix"></em>移动电话：</dt>
                        <dd>
                            <p>
                                <input class="w400 text" type="text" name='mobile' id='mobile' value="${supplier.mobile}"/>
                                <span></span>
                            </p>
                        </dd>
                    </dl>
                    <dl>
                        <dt><em class="pngFix"></em>固定电话：</dt>
                        <dd>
                            <p>
                                <input class="w400 text" type="text" name='fixedTel' id='fixedTel' value="${supplier.fixedTel}"/>
                                <span></span>
                            </p>
                        </dd>
                    </dl>
                    <!-- 
                    <dl>
                        <dt><em class="pngFix"></em>传真：</dt>
                        <dd>
                            <p>
                                <input class="w400 text" type="text" name='fax' id='fax' value="${supplier.fax}"/>
                                <span></span>
                            </p>
                        </dd>
                    </dl>
                    <dl>
                        <dt class="required"><em class="pngFix"></em>经营业态：</dt>
                        <dd>
                        	<p>
							    <span></span>
	                            <select name="busType" id="busType">
	                                <option value="0">请选择...</option>
			                        <#list busTypes as bt>
			                            <option value="${bt.dictionaryId}" <#if bt.dictionaryId == supplier.busType>selected="selected"</#if>>${bt.dictionaryName}</option>
			                        </#list>
	                            </select>
                            </p>
                        </dd>
                    </dl>
                    <dl>
                        <dt class="required"><em class="pngFix"></em>市场类型：</dt>
                        <dd>
                        	<p>
							    <span></span>
	                            <select name="marketType" id="marketType">
	                                <option value="0">请选择...</option>
			                        <#list marketTypes as mt>
			                            <option value="${mt.dictionaryId}" <#if mt.dictionaryId == supplier.marketType>selected="selected"</#if>>${mt.dictionaryName}</option>
			                        </#list>
	                            </select>
                            </p>
                        </dd>
                    </dl>
                    <dl>
                        <dt><em class="pngFix"></em>经营面积：</dt>
                        <dd>
                            <p>
                                <input class="w400 text" type="text" name='busArea' id='busArea' value="${supplier.busArea}"/>
                                <span></span>
                            </p>
                        </dd>
                    </dl>
                    <dl>
                        <dt><em class="pngFix"></em>客户经理：</dt>
                        <dd>
                            <p>
                                <input class="w400 text" type="text" name='managerId' id='managerId' value="${supplier.managerId}"/>
                                <span></span>
                            </p>
                        </dd>
                    </dl>
                    -->
                    <dl>
                        <dt class="required"><em class="pngFix"></em>营业执照注册号：</dt>
                        <dd>
                        	<p>
	                            <input type="text" class="text w200" name="busLicenseNO" id="busLicenseNO" value="${supplier.busLicenseNO}"/>
	                            <span></span>
	                        </p>
                        </dd>
                    </dl>
                    <dl>
                        <dt class="required"><em class="pngFix"></em>营业执照图片：</dt>
                        <dd>
                             <p>
                             	<input type="hidden" class="text w200" name="busLicenPurl" id="busLicenPurl" value="${supplier.busLicenPurl}"/>
                             	<span></span>
                              	<div class="banner">
                              	<#if supplier.busLicenPurl??>
                              	 <img alt="" id="busLicenloadImage" src="${imgOssServer}${supplier.busLicenPurl}" style="width:150px;height: 160px;">
                              	<#else>
                              	
                              	 <img alt="" id="busLicenloadImage" src="" style="width:150px;height: 160px;">
                              	</#if>
                                    <a href="javascript:void(0);" class="btn-upload btng-s">
                                        <div id="result_banner"></div>
                                        <input type="file" id="busLicenImage" name="files" class="file" onChange="ajaxFileUploads('busLicenImage','busLicenloadImage','busLicenPurl');"/>
                                    </a>
                                    <p class="hint">支持格式jpg,jpeg,png,gif，请保证图片清晰且文件大小不超过400KB</p>
                              	</div>
                             </p> 
                        </dd>
                    </dl>
                    <!-- 
                    <dl>
                        <dt class="required"><em class="pngFix"></em>地税税务登记编号：</dt>
                        <dd>
                             <p>
	                            <input type="text" class="text w200" name="dishuiRegistNO" id="dishuiRegistNO" value="${supplier.dishuiRegistNO}"/>
	                            <span></span>
	                         </p>
                        </dd>
                    </dl>
                    <dl>
                        <dt class="required"><em class="pngFix"></em>地税税务登记图片：</dt>
                        <dd>
                            <p>
                                 <input type="hidden" class="text w200" name="dishuiRegistPurl" id="dishuiRegistPurl" value="${supplier.dishuiRegistPurl}"/>
	                             <span></span>
	                             <div class="banner">
	                                    <img alt="" id="dishuiRegistImage" src="${supplier.dishuiRegistPurl}" style="width:150px;height: 160px;">
	                                    <a href="javascript:void(0);" class="btn-upload btng-s">
	                                        <div id="result_banner"></div>
	                                        <input type="file" id="dishuiImage" name="files" class="file" onChange="ajaxFileUploads('dishuiImage','dishuiRegistImage','dishuiRegistPurl');"/>
	                                    </a>
	                                    <p class="hint">支持格式jpg,jpeg,png,gif，请保证图片清晰且文件大小不超过400KB </p>
	                              </div>
                             </p> 
                        </dd>
                    </dl>
                    <dl>
                        <dt class="required"><em class="pngFix"></em>国税税务登记编号：</dt>
                        <dd>
                             <p>
	                            <input type="text" class="text w200" name="guoshuiRegistNO" id="guoshuiRegistNO" value="${supplier.guoshuiRegistNO}"/>
	                            <span></span>
	                         </p>
                        </dd>
                    </dl>
                    <dl>
                        <dt class="required"><em class="pngFix"></em>国税税务登记照片：</dt>
                        <dd>
                            <p>
                                 <input type="hidden" class="text w200" name="guoshuiRegistPurl" id="guoshuiRegistPurl" value="${supplier.guoshuiRegistPurl}"/>
	                             <span></span>
	                             <div class="banner">
	                                    <img alt="" id="guoshuiRegistPurlImage" src="${supplier.guoshuiRegistPurl}" style="width:150px;height: 160px;">
	                                    <a href="javascript:void(0);" class="btn-upload btng-s">
	                                        <div id="result_banner"></div>
	                                        <input type="file" id="guoshuiImage" name="files" class="file" onChange="ajaxFileUploads('guoshuiImage','guoshuiRegistPurlImage','guoshuiRegistPurl');"/>
	                                    </a>
	                                    <p class="hint">支持格式jpg,jpeg,png,gif，请保证图片清晰且文件大小不超过400KB </p>
	                              </div>
                             </p> 
                        </dd>
                    </dl>
                    <dl>
                        <dt class="required"><em class="pngFix"></em>增值税一般纳税人照片：</dt>
                        <dd>
                            <p>
                                 <input type="hidden" class="text w200" name="zzshuiPurl" id="zzshuiPurl" value="${supplier.zzshuiPurl}"/>
	                             <span></span>
	                             <div class="banner">
	                                    <img alt="" id="zzshuiPurlImage" src="${supplier.zzshuiPurl}" style="width:150px;height: 160px;">
	                                    <a href="javascript:void(0);" class="btn-upload btng-s">
	                                        <div id="result_banner"></div>
	                                        <input type="file" id="zzshuiImage" name="files" class="file" onChange="ajaxFileUploads('zzshuiImage','zzshuiPurlImage','zzshuiPurl');"/>
	                                    </a>
	                                    <p class="hint">支持格式jpg,jpeg,png,gif，请保证图片清晰且文件大小不超过400KB </p>
	                              </div>
                             </p> 
                        </dd>
                    </dl>
                    -->
                    <!-- 
                    <dl>
                        <dt class="required"><em class="pngFix"></em>法定代表人：</dt>
                        <dd>
                             <p>
	                            <input type="text" class="text w200" name="legaler" id="legaler" value="${supplier.legaler}"/>
	                            <span></span>
	                         </p>
                        </dd>
                    </dl>
                    <dl>
                        <dt class="required"><em class="pngFix"></em>法定代表人身份证：</dt>
                        <dd>
                             <p>
	                            <input type="text" class="text w200" name="legalerID" id="legalerID" value="${supplier.legalerID}"/>
	                            <span></span>
	                         </p>
                        </dd>
                    </dl>
                    <dl>
                        <dt class="required"><em class="pngFix"></em>法定代表人身份证照片：</dt>
                        <dd>
                            <p>
                                 <input type="hidden" class="text w200" name="legalerPurl" id="legalerPurl" value="${supplier.legalerPurl}"/>
	                             <span></span>
	                             <div class="banner">
	                                    <img alt="" id="legalerPurlImage" src="${imgServer}${supplier.legalerPurl}" style="width:150px;height: 160px;">
	                                    <a href="javascript:void(0);" class="btn-upload btng-s">
	                                        <div id="result_banner"></div>
	                                        <input type="file" id="legalerImage" name="files" class="file" onChange="ajaxFileUploads('legalerImage','legalerPurlImage','legalerPurl');"/>
	                                    </a>
	                                    <p class="hint">支持格式jpg,jpeg,png,gif，请保证图片清晰且文件大小不超过400KB </p>
	                              </div>
                             </p> 
                        </dd>
                    </dl>
                     -->
                    <!-- 
                    <dl>
                        <dt class="required"><em class="pngFix"></em>${supplierName}类型：</dt>
                        <dd>
                        	<p>
							    <span></span>
	                            <select name="shopType" id="shopType">
	                                <option value="0">请选择...</option>
			                        <#list shopTypes as st>
			                            <option value="${st.dictionaryId}" <#if st.dictionaryId == supplier.shopType>selected="selected"</#if>>${st.dictionaryName}</option>
			                        </#list>
	                            </select>
                            </p>
                        </dd>
                    </dl>
                    <dl>
                        <dt class="required"><em class="pngFix"></em>结算银行：</dt>
                        <dd>
                             <p>
	                            <input type="text" class="text w200" name="accountBank" id="accountBank" value="${supplier.accountBank}"/>
	                            <span></span>
	                         </p>
                        </dd>
                    </dl>
                    -->
                    <dl>
                        <dt class="required"><em class="pngFix"></em>开户行：</dt>
                        <dd>
                             <p>
	                            <input type="text" class="text w200" name="openingBank" id="openingBank" value="${supplier.openingBank}"/>
	                            <span></span>
	                         </p>
                        </dd>
                    </dl>
                    <dl>
                        <dt class="required"><em class="pngFix"></em>公司账号：</dt>
                        <dd>
                             <p>
	                            <input type="text" class="text w200" name="accountNO" id="accountNO" value="${supplier.accountNO}"/>
	                            <span></span>
	                         </p>
                        </dd>
                    </dl>
                    <dl>
                        <dt class="required"><em class="pngFix"></em>开户人名称：</dt>
                        <dd>
                             <p>
	                            <input type="text" class="text w200" name="openingName" id="openingName" value="${supplier.openingName}"/>
	                            <span></span>
	                         </p>
                        </dd>
                    </dl>
                     <!-- 
                    <dl>
                        <dt class="required"><em class="pngFix"></em>结算方式：</dt>
                        <dd>
                        	<p>
							    <span></span>
	                            <select name="accountMethod" id="accountMethod">
	                                <option value="0">请选择...</option>
			                        <#list accountMethods as am>
			                            <option value="${am.dictionaryId}" <#if am.dictionaryId == supplier.accountMethod>selected="selected"</#if>>${am.dictionaryName}</option>
			                        </#list>
	                            </select>
                            </p>
                        </dd>
                    </dl>
                    <dl>
                        <dt class="required"><em class="pngFix"></em>结算周期：</dt>
                        <dd>
                             <p>
	                            <input type="text" class="text w200" name="accountCycle" id="accountCycle" value="${supplier.accountCycle}"/>
	                            <span></span>
	                         </p>
                        </dd>
                    </dl>
                    -->
                    <dl>
                        <dt class="required"><em class="pngFix"></em>${supplierName}LOGO：</dt>
                        <dd>
                            <p>
                                 <input type="hidden" class="text w200" name="supplierLogo" id="supplierLogo" value="${supplier.supplierLogo}"/>
	                             <span></span>
	                             <div class="banner">
	                             <#if supplier.supplierLogo??>
	                              <img alt="" id="supplierLogoImage" src="${imgOssServer}${supplier.supplierLogo}" style="width:150px;height: 160px;">
	                             <#else>
	                             <img alt="" id="supplierLogoImage" src="" style="width:150px;height: 160px;">
	                             </#if>
	                                    
	                                    <a href="javascript:void(0);" class="btn-upload btng-s">
	                                        <div id="result_banner"></div>
	                                        <input type="file" id="logoImage" name="files" class="file" onChange="ajaxFileUploads('logoImage','supplierLogoImage','supplierLogo');"/>
	                                    </a>
	                                    <p class="hint">支持格式jpg,jpeg,png,gif，请保证图片清晰且文件大小不超过400KB </p>
	                              </div>
                             </p> 
                        </dd>
                    </dl>
                    <dl>
                        <dt><em class="pngFix"></em>备注：</dt>
                        <dd>
                            <p>
                                <input class="w400 text" type="text" name='remark' id='remark' value="${supplier.remark}"/>
                                <span></span>
                            </p>
                        </dd>
                    </dl>
                    
                    <dl class="bottom">
                        <dt>&nbsp;</dt>
                        <dd>
                            <p class="mb10">
                                <input name="notice" type="checkbox" id="notice" value="1" checked="checked" />
                                <label for="notice">我已认真阅读并同意<a href="#?act=document&code=open_store" target="_blank">注册${supplierName}协议</a>中的所有条款</label>
                                <span></span> </p>
                            <p class="mb10">
                                <input  class="btnb-l" type="button" id="submitBtn" value="提交注册"/>
                            </p>
                        </dd>
                    </dl>
                </form>
                <div id="brandTableDialog" title="选择品牌" style="padding:5px;">
					<table id="brandTable"></table>
				</div>
            </div>
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
						if(!_.isEmpty(brandIds)){
							$('#brandId').val(brandIds.join(',')) && $('#brandName').val(brandNames.join(','));
							$( this ).dialog( "close" );
						}else{
							alert("至少选择一个品牌");
						}
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
				nowrap: false, 
		        striped: true, 
		        border: true, 
		        collapsible:false,//是否可折叠的 
		        fit: true,//自动大小
		        remoteSort:false,  
		        idField:'brandId',
				rownumbers:true,
			    url:'${base}/joinIn/brand/bl?supplierId=${supplier.id}',
			    frozenColumns:[[{field:'ck', checkbox:true}]],
			    columns:[[
					{title:'品牌ID',field:'brandId',width:100,sortable:true},
			        {field:'brandName',title:'品牌名称',width:200},
			        {field:'brandClass',title:'品牌类型',width:200, align:'left'}
			    ]],
			    onLoadSuccess:function(data){
			    	if(data){
				    	$.each(data.rows, function(index, item){
					    	if(item.check){
					    		$('#brandTable').datagrid('checkRow', index);
					    	}
				    	});
				    }
			    }
			});
		};
		${supplier??} && '${supplier.supplierState}' == 1 && (function(){var sec = parseInt($('#timer').text());(sec == 1 && ($('#timer').text(6), location.href = '${base}')),$('#timer').text(--sec),setTimeout(arguments.callee, 1000)})();
       // var SITE_URL = "http://192.168.1.220";
        $(function(){
            $("#apply_form").validate({
                errorPlacement: function(error, element){
                    var error_td = element.parent('p').children('span');
                    error_td.find('.field_notice').hide();
                    error_td.append(error);
                },
                submitHandler:function(form){
                    ajaxpost('apply_form', '', '', 'onerror');
                },
                rules: {
                    name: {
                        required: true,
                        maxlength: 20,
                        remote   : {
	                        url : '${base}/store/checkStorename',
	                        type: 'get',
	                        data:{
	                            name : function(){
	                                return $('#name').val();
	                            },
	                            id  : ${supplier.id!0}
	                        }
                    	} 
                    },
                    brandId: {
                        required : true
                    },
                    /*
                    supplierNO: {
                        required : true
                    },
                    entType: {
                        number   : true, min : 1
                    },
                    */
                    address: {
                        required : true
                    },
                    contacter: {
                        required : true
                    },
                    mobile: {
                    	required: true,
                        minlength: 11,
                        maxlength: 11
                    },
                    /*
                    busType: {
                        number   : true, min : 1
                    },
                    marketType: {
                        number   : true, min : 1
                    },
                    */
                    busLicenseNO: {
                        required : true,
                        minlength: 15,
                        minlength: 15
                    },
                    busLicenPurl: {
                        required: true,
                        accept: "jpg|jpeg|png|gif"
                    },
                    /*
                    dishuiRegistNo: {
                        required : true
                    },
                    dishuiRegistPurl: {
                        required: true,
                        accept: "jpg|jpeg|png|gif"
                    },
                    guoshuiRegistNo: {
                        required : true
                    },
                    guoshuiRegistPurl: {
                        required: true,
                        accept: "jpg|jpeg|png|gif"
                    },
                    zzshuiPurl: {
                        required: true,
                        accept: "jpg|jpeg|png|gif"
                    },
                    */
                    /*
                    legaler: {
                        required : true
                    },
                    legalerID: {
                    	required: true,
                        minlength: 18,
                        maxlength: 18
                    },
                    legalerPurl: {
                        required: true,
                        accept: "jpg|jpeg|png|gif"
                    },
                    */
                    /*
                    shopType: {
                        number   : true, min : 1
                    },
                    accountBank: {
                        required : true
                    },
                    */
                    openingBank: {
                        required : true
                    },
                    accountNO: {
                        required : true
                    },
                    openingName: {
                        required : true
                    },
                    /*
                    accountMethod: {
                        number   : true, min : 1
                    },
                    accountCycle: {
                    	required : true,
                    	digits   : true
                    },
                    */
                    supplierLogo: {
                        required: true,
                        accept: "jpg|jpeg|png|gif"
                    },
                    notice: {
                        required : true
                    }
                },
                messages: {
                	name : {
                        required : '${supplierName}名称不能为空',
                        remote   : '该${supplierName}名称已经存在了，请您换一个'
                    },
                    /*
                    accountCycle : {
                    	required : '结帐周期必须填写',
                    	digits   : '请输一个正整数'
                    },
                    entType: {
                        number   : '', min : '供应商类型必须选择'
                    },
                    busType: {
                        number   : '', min : '经营业态必须选择'
                    },
                    marketType: {
                        number   : '', min : '市场类型必须选择'
                    },
                    shopType: {
                        number   : '', min : '${supplierName}类型必须选择'
                    },
                    accountMethod: {
                        number   : '', min : '结算方式必须选择'
                    },
                    */
                    brandId: {
                    	required : '请选择品牌'
                    },
                    /*
                    supplierNO: {
                    	required : '请填写${supplierName}的简称'
                    },
                    */
                    address: {
                    	required : '请填写${supplierName}的地址'
                    },
                    /*
                    accountBank: {
                    	required : '请填写结算银行名称'
                    },
                    */
                    openingBank: {
                    	required : '请填写开发行名称'
                    },
                    accountNO: {
                    	required : '请填写结算银行账号'
                    },
                    openingName: {
                    	required : '请填写开户人姓名'
                    },
                    contacter: {
                    	required : '请输入联系人姓名'
                    },
                    /*
                    legaler: {
                    	required : '请输入法定代表人姓名'
                    },
                    legalerID: {
                    	required: '请输入真实身份证号',
                        minlength: '身份证格式不正确',
                        maxlength: '身份证格式不正确'
                    },
                    */
                    mobile: {
                    	required: '联系人手机号必须填写',
                        minlength: '手机号格式不正确',
                        maxlength: '手机号格式不正确'
                    },
                    busLicenseNO: {
                    	required: '营业执照号必须填写',
                    	minlength: '营业执照号格式不正确',
                    	minlength: '营业执照号格式不正确'
                    },
                    /*
                    dishuiRegistNo: {
                    	required: '地税税务登记编号须填写'
                    },
                    guoshuiRegistNo: {
                    	required: '国税税务登记编号须填写'
                    },
                    */
                    busLicenPurl: {
                    	required: '请上传营业执照照片，以便审核通过',
                        accept: '请上传格式为 jpg,jpeg,png,gif 的文件'
                    },
                    /*
                    dishuiRegistPurl: {
                    	required: '请上传地税税务登记照片，以便审核通过',
                        accept: '请上传格式为 jpg,jpeg,png,gif 的文件'
                    },
                    guoshuiRegistPurl: {
                    	required: '请上传国税税务登记照片，以便审核通过',
                        accept: '请上传格式为 jpg,jpeg,png,gif 的文件'
                    },
                    zzshuiPurl: {
                    	required: '请上传增值税一般纳税人照片照片，以便审核通过',
                        accept: '请上传格式为 jpg,jpeg,png,gif 的文件'
                    },
                    */
                    /*
                    legalerPurl: {
                    	required: '请上传法定代表人身份证照片，以便审核通过',
                        accept: '请上传格式为 jpg,jpeg,png,gif 的文件'
                    },
                    */
                    supplierLogo: {
                        required: '请上传首页LOGO图片',
                        accept: '请上传格式为 jpg,jpeg,png,gif 的文件'
                    },
                    notice: {
                        required: '请阅读并同意开店协议'
                    } 
                }
            });
            
             //校验电话格式
	       jQuery.validator.addMethod("isTel", function(value, element) { 
			  var tel =/(^(\d{2,5}-)?\d{6,9}(-\d{2,4})?$)|(^1\d{10}?$)/; //电话号码格式010-12345678 
			  return this.optional(element) || (tel.test(value)); 
			}, "phone"); 
                  //表单提交
        $("#submitBtn").click(function(){
	        if($("#apply_form").valid()){
	               $("#submitBtn").attr("disabled",true);
			        $.ajax({
					         type: "post",
					         url: '${base}/store/save',
					         data: $("#apply_form").serialize(),
					         dataType: "json",
					         contentType : "application/x-www-form-urlencoded; charset=utf-8",
							 async:false,
							 success:function(data) {
								if(data.fail){
									alert(data.message);
									window.location.href="${base}/login";
								}else{
									if(data.success){
										 alert(data.message);
				                         //window.location.href="${base}/joinIn/JoinInSuccess";
				                         window.location.href = _.format('${base}/joinIn/step2?id={0}', data.supplierId);
				                         return true;
									}else{
				                        $("#submitBtn").removeAttr("disabled");
									}
								}
							 }
					 	   });
				  }	  
             });
        });
        //]]>
    </script></div>
<div id="footer" >
    <div class="wrapper">
<!--         <p><a href="http://192.168.1.220">首页</a> -->
<!--             | <a  href="#?act=article&article_id=24">招聘英才</a> -->
<!--             | <a  href="#?act=article&article_id=25">广告合作</a> -->
<!--             | <a  href="#?act=article&article_id=23">联系我们</a> -->
<!--             | <a  href="#?act=article&amp;article_id=22">关于我们</a> -->
<!--         </p> -->
 <#assign siteSettingTag = newTag("siteSettingTag") />
	<#assign siteSet = siteSettingTag("") />
     ${siteSet.siteDbxx}
<!--         &nbsp;&nbsp; -->
<!--         ICP证： -->
        <br/>
<!--         <div class="footer-logo"> -->
<!--             <ul> -->
<!--                 <li class="caifutong"></li> -->
<!--                 <li class="caifubao"></li> -->
<!--                 <li class="beifen"></li> -->
<!--                 <li class="kexin"></li> -->
<!--                 <li class="shiming"></li> -->
<!--                 <li class="wangzhan360"></li> -->
<!--                 <li class="anquanlianmeng"></li> -->
<!--                 <div class="clear"></div> -->
<!--             </ul> -->
<!--         </div> -->
    </div>
</div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        //实现图片慢慢浮现出来的效果
        $("img").load(function () {
            //图片默认隐藏
            $(this).hide();
            //使用fadeIn特效
            $(this).fadeIn("5000");
        });
        // 异步加载图片，实现逐屏加载图片
        //$(".scrollLoading").scrollLoading();
    });
</script>
<script language="javascript">
    var searchTxt = ' 请输入您要搜索的商品关键字';
    function searchFocus(e){
        if(e.value == searchTxt){
            e.value='';
            $('#keyword').css("color","");
        }
    }
    function searchBlur(e){
        if(e.value == ''){
            e.value=searchTxt;
            $('#keyword').css("color","#999999");
        }
    }
    function searchInput() {
        if($('#keyword').val()==searchTxt)
            $('#keyword').attr("value","");
        return true;
    }
    $('#keyword').css("color","#999999");
    
     //上传图片
	function ajaxFileUploads(myBlogImage,imgId,img){
	    $.ajaxFileUpload({
	        //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
	        url: '${base}/joinIn/fileUpload',
	        secureuri:false,                      //是否启用安全提交,默认为false
	        fileElementId:myBlogImage,           //文件选择框的id属性
	        dataType:'json',                       //服务器返回的格式,可以是json或xml等
	        fileSize:5120000,
	        allowType:'jpg,jpeg,png,JPG,JPEG,PNG',
	        success:function(data, status){        //服务器响应成功时的处理函数
	            if( true == data.success){     //0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述)
	            
	               $("img[id='"+imgId+"']").attr("src", '${imgServer}'+data.result);
	               $("#"+img).val(data.result);
	            }
	        },
	        error:function(data, status, e){ //服务器响应失败时的处理函数
	        	layer.msg('图片上传失败，请重试！！', 1, 8);
	            //$('#result').html('图片上传失败，请重试！！');
	        }
	    });
	}
</script>
</body>
</html>