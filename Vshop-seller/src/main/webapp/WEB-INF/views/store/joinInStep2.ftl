<@p.header title="分销商申请－填写分销商信息"/>
<meta charset="utf-8" />
<div class="layout">
    <script type="text/javascript" src="${base}/res/js/common_select.js" charset="utf-8"></script>
    <script type="text/javascript" src="${base}/res/js/ajaxfileupload/ajaxfileupload.js"></script>
    <script type="text/javascript" charset="utf-8" src="${base}/res/js/area.js"></script>
    <div class="wrap-shadow">
        <div class="wrap-all">
            <div class="chart">
                <div class="pos_x1 bg_a1" title="请选择分销商分类"></div>
                <div class="pos_x2 bg_b2" title="填写分销商信息"></div>
                <div class="pos_x3 bg_c" title="完成"></div>
            </div>
            <div class="chart" style="height: 40px;color: red;font-weight: bold;padding-left: 100px;">
            	<span>
            	<#if store ??&& store.storeState == 2>
                                                     平台审核中，请耐心等待... ...
                <#elseif store ??&& store.storeState == 0>
                                                     该商家已关闭！
                <#elseif store ??&& store.storeState == 3>
                	该分销商审核未通过请重新填写注册信息；<span style="color: #BBB;">原因：${store.storeCloseInfo}</span>
                <#elseif store ??&& store.storeState == 1>
                	恭喜您分销商注册成功！<span style="color: #BBB;"><span id="timer">6</span>&nbsp;&nbsp;秒后跳转到分销商主页</span>
                </#if>
                </span>
            </div>
            <div class="ncu-form-style grade-shop" ms-controller="mains">
                <form method="post" enctype="multipart/form-data" id="apply_form" action="">
                    <input type="hidden" name="form_submit" value="ok" />
                    <input type="hidden" name="grade_id" value="1" />
                    <input type="hidden" name="storeId" value="${store.storeId}" />
                    <dl>
                        <dt class="required"><em class="pngFix"></em>注册类型：</dt>
                        <dd>
                            <p>
                                <input type="radio" value="1" name="storeType" id="storeType1" ms-duplex-number="storeType"><label for="storeType1">企业 </label>
                                <input type="radio" value="0" name="storeType" id="storeType0" ms-duplex-number="storeType"><label form="storeType0">个人 </label>
                            </p>
                        </dd>
                    </dl>
                    <dl>
                        <dt class="required"><em class="pngFix"></em>分销商名称：</dt>
                        <dd>
                            <p>
                                <input class="w400 text" type="text" name="storeName" id="store_name" value="${store.storeName}" maxlength="50"/>
                                <span></span></p>
                            <p class="hint">请控制在50个字符以内</p>
                        </dd>
                    </dl>
                    <!-- 
                    <dl>
                        <dt class="required"><em class="pngFix"></em>商家分类：</dt>
                        <dd>
                        	<p>
                        		<input id="stId" type="hidden" name="stId" value="${store.stId}" validate="required:true" min="1">
							    <span></span>
                        	</p>
                        	<span id="stIdList">
	                            <select id="stIds" onchange="option(this);">
	                                <option value="" selected="selected">-请选择-</option>
							        <#list typeList1 as sc><option value="${sc.id}">${sc.name}</option></#list>
	                            </select>
                            </span>
                        </dd>
                    </dl>
                    <dl>
                        <dt class="required">主营商品：</dt>
                        <dd>
                            <p>
                                <textarea name="storeZy" rows="2" class="textarea w400" maxlength="50" >${store.storeZy}</textarea>
                                <span></span> </p>
                            <p class="hint">主营商品关键字（Tag）有助于搜索商家时找到您的商家<br/>关键字最多可输入50字，请用","进行分隔，例如”男装,女装,童装”</p>
                        </dd>
                    </dl>
			        <dl>
			          <dt class="required"><em class="pngFix"></em>所在地区：</dt>
			          	<dd>
			          	    <p>
							   <input id="city_id" type="hidden" name="cityId" value="${store.cityId}">
							   <span></span>
							</p>
						    <span id="spanarea">
							<select name="provinceId" class="select" id="area" check="needCheck"> 
								<option value="">请选择</option>
								<#if areas??> 
									<#list areas as str>
										<option value="${str.areaId}">${str.areaName}</option>
									</#list> 
								</#if>
							</select> 
							<span id="spancity"></span>
							<span class="form-tips" style="color: red" name="check" id="checkedarea"></span> 
							<input id="area_id" class="area_ids" type="hidden" name="areaId" value="">
							<input id="area_info" class="area_names" type="hidden" name="areaInfo" value="">
						</dd>
			        </dl>
			         -->
                    <dl>
                        <dt class="required"><em class="pngFix"></em>详细地址：</dt>
                        <dd>
                            <p>
                                <input class="w400 text" type="text" name="storeAddress" value="${store.storeAddress}"/>
                                <span></span>
                            </p>
                        </dd>
                    </dl>
                    <!-- 
                    <dl>
                        <dt><em class="pngFix"></em>邮政编码：</dt>
                        <dd>
                             <p>
	                            <input type="text" class="text w200" name="storeZip" value="${store.storeZip}"/>
	                            <span></span>
	                         </p>
                        </dd>
                    </dl>
                     -->
                    
                    <dl>
                        <dt class="required"><em class="pngFix"></em>联系人：</dt>
                        <dd>
                             <p>
	                            <input type="text" class="text w200" name="contacter" value="${storeExpand.contacter}"/>
	                            <span></span>
	                         </p>
                        </dd>
                    </dl>
                    <dl>
                        <dt class="required"><em class="pngFix"></em>移动电话：</dt>
                        <dd>
                            <p>
                                <input type="text" class="text w200" name="mobile" maxlength="20" value="${storeExpand.mobile}"/>
                                <span></span></p>
                        </dd>
                    </dl>
                    <dl>
                        <dt><em class="pngFix"></em>固定电话：</dt>
                        <dd>
                            <p>
                                <input type="text" class="text w200" name="storeTel" maxlength="20" value="${store.storeTel}" />
                                <span></span>
                            </p>
                        </dd>
                    </dl>
                    <!-- 
                    <dl>
                        <dt><em class="pngFix"></em>传真：</dt>
                        <dd>
                            <p>
                                <input type="text" class="text w200" name="fax" maxlength="20" value="${storeExpand.fax}" />
                                <span></span></p>
                        </dd>
                    </dl>
                     -->
                    <dl ms-if="storeType == 0">
                        <dt class="required"><em class="pngFix"></em>身份证号：</dt>
                        <dd>
                            <p>
                                <input type="text" class="text w200" name="storeOwnerCard" value="${store.storeOwnerCard}" />
                                <span></span></p>
                        </dd>
                    </dl>
                    <dl ms-if="storeType == 0">
                        <dt class="required"><em class="pngFix"></em>上传联系人身份证：</dt>
                        <dd>
                             <p>
                                  <input type="hidden" class="text w200" name="storeImage" id="storeImage" value="${store.storeImage}"/>
                             	  <span></span>
	                              <div class="banner">
	                              <#if store.storeImage??>
                              	 <img alt="" id="IdcarduploadImage" src="${imgOssServer}${store.storeImage}" style="width:150px;height: 160px;">
                              	<#else>
                              	 <img alt="" id="IdcarduploadImage" src="" style="width:150px;height: 160px;">
                              	</#if>
	                                    <a href="javascript:void(0);" class="btn-upload btng-s">
	                                        <div id="result_banner"></div>
	                                        <input type="file" id="IdcardImage" name="files" class="file" onChange="ajaxFileUploads('IdcardImage','IdcarduploadImage','storeImage');"/></a>
	                                    <p class="hint">支持格式jpg,jpeg,png,gif，请保证图片清晰且文件大小不超过400KB</p>
	                              </div>
                             </p> 
                        </dd>
                        </dd>
                    </dl>
                    <!-- 
                    <dl>
                        <dt class="required"><em class="pngFix"></em>法定代表人：</dt>
                        <dd>
                             <p>
	                            <input type="text" class="text w200" name="legaler" value="${storeExpand.legaler}"/>
	                            <span></span>
	                         </p>
                        </dd>
                    </dl>
                    <dl>
                        <dt class="required"><em class="pngFix"></em>法定代表人身份证：</dt>
                        <dd>
                             <p>
	                            <input type="text" class="text w200" name="legalerId" value="${storeExpand.legalerId}"/>
	                            <span></span>
	                         </p>
                        </dd>
                    </dl>
                    <dl>
                        <dt><em class="pngFix"></em>上传法定代表人身份证：</dt>
                        <dd>
                             <p>
                              <div class="banner">
                                  <img alt="" id="legalerPurlImage" src="${storeExpand.legalerPurl}" style="width:150px;height: 160px;">
                                  <a href="javascript:void(0);" class="btn-upload btng-s">
                                      <div id="result_banner"></div>
                                      <input type="file" id="legalerImage" name="files" class="file" onChange="ajaxFileUploads('legalerImage','legalerPurlImage','legalerPurl');"/>
                                  	  <input type="hidden" class="text w200" name="legalerPurl" id="legalerPurl" value="${storeExpand.legalerPurl}"/>
                                  </a>
                                  <p class="hint">支持格式jpg,jpeg,png,gif，请保证图片清晰且文件大小不超过400KB</p>
                              </div>
                             </p> 
                        </dd>
                        </dd>
                    </dl>
                    -->
                    
                    <dl ms-if="storeType == 1">
                        <dt class="required"><em class="pngFix"></em>营业执照注册号：</dt>
                        <dd>
                             <p>
	                            <input type="text" class="text w200" name="busLicenseNo" value="${storeExpand.busLicenseNo}"/>
	                            <span></span>
	                         </p>
                        </dd>
                    </dl>
                    <dl ms-if="storeType == 1">
                        <dt class="required"><em class="pngFix"></em>上传营业执照图片：</dt>
                        <dd>
                            <p>
                                 <input type="hidden" class="text w200" name="storeImage1" id="storeImage1" value="${store.storeImage1}"/>
	                             <span></span>
	                             <div class="banner">
	                             	<#if store.storeImage1??>
                              	 <img alt="" id="uploadBannerImage" src="${imgOssServer}${store.storeImage1}" style="width:150px;height: 160px;">
                              	<#else>
                              	
                              	 <img alt="" id="uploadBannerImage" src="" style="width:150px;height: 160px;">
                              	</#if>
	                                    <a href="javascript:void(0);" class="btn-upload btng-s">
	                                        <div id="result_banner"></div>
	                                        <input type="file" id="bannerImage" name="files" class="file" onChange="ajaxFileUploads('bannerImage','uploadBannerImage','storeImage1');"/></a>
	                                     <p class="hint">支持格式jpg,jpeg,png,gif，请保证图片清晰且文件大小不超过400KB </p>
	                              </div>
                             </p> 
                        </dd>
                    </dl>
                    <!--
                    <dl>
                        <dt class="required"><em class="pngFix"></em>地税税务登记编号：</dt>
                        <dd>
                             <p>
	                            <input type="text" class="text w200" name="dishuiRegistNo" value="${storeExpand.dishuiRegistNo}"/>
	                            <span></span>
	                         </p>
                        </dd>
                    </dl>
                    <dl>
                        <dt class="required"><em class="pngFix"></em>上传地税税务登记图片：</dt>
                        <dd>
                            <p>
                                 <input type="hidden" class="text w200" name="dishuiRegistPurl" id="dishuiRegistPurl" value="${storeExpand.dishuiRegistPurl}"/>
	                             <span></span>
	                             <div class="banner">
	                                    <img alt="" id="dishuiRegistImage" src="${storeExpand.dishuiRegistPurl}" style="width:150px;height: 160px;">
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
	                            <input type="text" class="text w200" name="guoshuiRegistNo" value="${storeExpand.guoshuiRegistNo}"/>
	                            <span></span>
	                         </p>
                        </dd>
                    </dl>
                    <dl>
                        <dt class="required"><em class="pngFix"></em>上传国税税务登记照片：</dt>
                        <dd>
                            <p>
                                 <input type="hidden" class="text w200" name="guoshuiRegistPurl" id="guoshuiRegistPurl" value="${storeExpand.guoshuiRegistPurl}"/>
	                             <span></span>
	                             <div class="banner">
	                                    <img alt="" id="guoshuiRegistPurlImage" src="${storeExpand.guoshuiRegistPurl}" style="width:150px;height: 160px;">
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
                        <dt><em class="pngFix"></em>结算银行：</dt>
                        <dd>
                             <p>
	                            <input type="text" class="text w200" name="accountBank" value="${storeExpand.accountBank}"/>
	                            <span></span>
	                         </p>
                        </dd>
                    </dl>
                     -->
                     <dl>
                        <dt class="required"><em class="pngFix"></em>开户行：</dt>
                        <dd>
                             <p>
	                            <input type="text" class="text w200" name="bankName" id="bankName" value="${store.bankName}"/>
	                            <span></span>
	                         </p>
                        </dd>
                    </dl>
                    <dl>
                        <dt class="required"><em class="pngFix"></em>银行账号：</dt>
                        <dd>
                             <p>
	                            <input type="text" class="text w200" name="bankAccountNumber" id="bankAccountNumber" value="${store.bankAccountNumber}"/>
	                            <span></span>
	                         </p>
                        </dd>
                    </dl>
                    <dl>
                        <dt class="required"><em class="pngFix"></em>开户人名称：</dt>
                        <dd>
                             <p>
	                            <input type="text" class="text w200" name="bankAccountName" id="bankAccountName" value="${store.bankAccountName}"/>
	                            <span></span>
	                         </p>
                        </dd>
                    </dl>
                    <dl>
                        <dt class="required"><em class="pngFix"></em>微信账号：</dt>
                        <dd>
                             <p>
	                            <input type="text" class="text w200" name="weichatAccountNumber" id="weichatAccountNumber" value="${store.weichatAccountNumber}"/>
	                            <span></span>
	                         </p>
                        </dd>
                    </dl>
                    <dl>
                        <dt class="required"><em class="pngFix"></em>分销商LOGO：</dt>
                        <dd>
                            <p>
                                 <input type="hidden" class="text w200" name="storeLogo" id="storeLogo" value="${store.storeLogo}"/>
	                             <span></span>
	                             <div class="banner">
	                             	<#if storeExpand.storeLogo??>
                              	 <img alt="" id="storeLogoImage" src="${imgOssServer}${storeExpand.storeLogo}" style="width:150px;height: 160px;">
                              	<#else>
                              	 <img alt="" id="storeLogoImage" src="" style="width:150px;height: 160px;">
                              	</#if>
	                                    <a href="javascript:void(0);" class="btn-upload btng-s">
	                                        <div id="result_banner"></div>
	                                        <input type="file" id="logoImage" name="files" class="file" onChange="ajaxFileUploads('logoImage','storeLogoImage','storeLogo');"/>
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
                                <input class="w400 text" type="text" name='remark' id='remark' value="${storeExpand.remark}"/>
                                <span></span>
                            </p>
                        </dd>
                    </dl>
                    <dl class="bottom">
                        <dt>&nbsp;</dt>
                        <dd>
                            <p class="mb10">
                                <input name="notice" type="checkbox" id="notice" value="1" checked="checked" />
                                <label for="notice">我已认真阅读并同意<a href="#?act=document&code=open_store" target="_blank">设立商家协议</a>中的所有条款</label>
                                <span></span> </p>
                            <p class="mb10">
                               <#-- <input type="submit" class="submit" value="设立商家" />-->
                                <input  class="btnb-l" type="button" id="submitBtn" value="提交注册"/>
                            </p>
                        </dd>
                    </dl>
                </form>
            </div>
        </div>
    </div>
    <script type="text/template" id="type_option">
    	<select onchange="option(this);">
            <option value="">-请选择-</option>
            <%_.each(data.typeList, function(type){%><option value="<%=type.id%>"><%=type.name%></option><%})%>
        </select>
    </script>
    <script type="text/javascript">
    	function option(obj){
    		var $obj = $(obj), $vo = $('#stId'), val = $obj.val();
    		$obj.nextAll().remove(), $('#stId').val(''), $vo.next().html() && $vo.next().children().show(0);
    		if(!!val){
    			var _list = _.filter(${typeAll}, function(_t){return val == _t.parentId});
    			_.isEmpty(_list) ? ($vo.val(val), $vo.next().html() && $vo.next().children().hide(0)) : $obj.after(_.templateToHtml($('#type_option').html(), {typeList: _list}));
    		}
    	}
    	${store??} && '${store.storeState}' == 1 && (function(){var sec = parseInt($('#timer').text());(sec == 1 && ($('#timer').text(6), location.href = '${base}')),$('#timer').text(--sec),setTimeout(arguments.callee, 1000)})();
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
                    storeOwnerCard: {
                        required: true,
                        minlength: 18,
                        maxlength: 18
                    },
                    storeName: {
                        required: true,
                        maxlength: 20,
                        remote   : {
	                        url : '${base}/store/checkStorename',
	                        type: 'get',
	                        data:{
	                            storename : function(){
	                                return $('#store_name').val();
	                            },
	                            id  : ${store.storeId!0}
	                        }
                    	} 
                    },
                    storeAddress: {
                        required: true,
                    },
                    /*
                    storeTel: {
                        required: true,
                        isTel:true
                    },
                     scId: {
                        required: true
                    },  */
                    /*
                    stId: {
                        required: true
                    },
                    cityId: {
                        required: true
                    },
                    storeZip: {
                        number: true,
                        minlength: 6,
                        maxlength: 6
                    },
                    */
                    storeImage: {
                    	required: true,
                        accept: "jpg|jpeg|png|gif"
                    },
                    storeImage1: {
                        required: true,
                        accept: "jpg|jpeg|png|gif"
                    },
                    notice: {
                        required : true
                    },
                    /*
                    legalerId: {
                    	required: true,
                        minlength: 18,
                        maxlength: 18
                    },
                    */
                    contacter: {
                    	required : true
                    },
                    /*
                    legaler: {
                    	required : true
                    },
                    */
                    mobile: {
                    	required: true,
                        minlength: 11,
                        maxlength: 11
                    },
                    busLicenseNo: {
                    	required: true
                    },
                    /*
                    dishuiRegistNo: {
                    	required: true
                    },
                    guoshuiRegistNo: {
                    	required: true
                    },
                    dishuiRegistPurl: {
                    	required: true,
                        accept: "jpg|jpeg|png|gif"
                    },
                    guoshuiRegistPurl: {
                    	required: true,
                        accept: "jpg|jpeg|png|gif"
                    }
                    */
                    bankName:{
                    	required : true
                    },
                    bankAccountNumber:{
                    	required : true
                    },
                    bankAccountName:{
                    	required : true
                    },
                    weichatAccountNumber:{
                    	required : true
                    },
                    storeLogo:{
                    	required: true,
                        accept: "jpg|jpeg|png|gif"
                    }
                },
                messages: {
                    storeOwnerCard: {
                        required: '请输入真实身份证号',
                        minlength: '身份证格式不正确',
                        maxlength: '身份证格式不正确'
                    },
                    storeAddress: {
                        required: '请输入详细的商家地址，以便顾客能够上门提货及服务',
                    },
                    storeName: {
                        required: '请输入商家名称',
                        maxlength:'请控制在20个字符以内',
                        remote: '该商家名称已经存在，请您更换一个店面名称'
                    },
                    /*
                    storeTel: {
                        required: '请输入联系电话',
                        isTel:'请输入正确的联系方式'
                    },
                    scId: {
                        required: '请选择商家分类'
                    }, 
                    stId: {
                        required: '请选择商家分类'
                    },
                    cityId: {
                        required: '请选择地区'
                    },
                    storeZip: {
                        number: '邮编必须为数字',
                        minlength: '邮编格式不正确',
                        maxlength: '邮编格式不正确'
                    },
                    */
                    storeImage: {
                    	required: '请上身份证照片，以便审核通过',
                        accept: '请上传格式为 jpg,jpeg,png,gif 的文件'
                    },
                    storeImage1: {
                        required: '请上传营业执照，以便审核通过',
                        accept: '请上传格式为 jpg,jpeg,png,gif 的文件'
                    },
                    notice: {
                        required: '请阅读并同意开店协议'
                    },
                    /*
                    legalerId: {
                    	required: '请输入真实身份证号',
                        minlength: '身份证格式不正确',
                        maxlength: '身份证格式不正确'
                    },
                    */
                    contacter: {
                    	required : '请输入联系人姓名'
                    },
                    /*
                    legaler: {
                    	required : '请输入法定代表人姓名'
                    },
                    */
                    mobile: {
                    	required: '联系人手机号必须填写',
                        minlength: '手机号格式不正确',
                        maxlength: '手机号格式不正确'
                    },
                    busLicenseNo: {
                    	required: '营业执照号必须填写'
                    },
                    /*
                    dishuiRegistNo: {
                    	required: '地税税务登记编号须填写'
                    },
                    guoshuiRegistNo: {
                    	required: '国税税务登记编号须填写'
                    },
                    dishuiRegistPurl: {
                    	required: '请上传地税税务登记照片，以便审核通过',
                        accept: '请上传格式为 jpg,jpeg,png,gif 的文件'
                    },
                    guoshuiRegistPurl: {
                    	required: '请上传国税税务登记照片，以便审核通过',
                        accept: '请上传格式为 jpg,jpeg,png,gif 的文件'
                    }
                    */
                    bankName:{
                    	required : '请填写开发行名称'
                    },
                    bankAccountNumber:{
                    	required : '请填写结算银行账号'
                    },
                    bankAccountName:{
                    	required : '请填写开户人姓名'
                    },
                    weichatAccountNumber:{
                    	required : '请填写微信帐号'
                    },
                    storeLogo:{
                    	required: '请上传首页LOGO图片',
                        accept: '请上传格式为 jpg,jpeg,png,gif 的文件'
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
							 	if(data.success){
									 alert(data.message);
			                         //window.location.href="${base}/joinIn/JoinInSuccess";
			                         window.location.href = _.format("${base}/joinIn/step2?storeId={0}", data.storeId);
			                         return true;
								}else{
			                        $("#submitBtn").removeAttr("disabled");
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
        Copyright 2016-2017 磁石世纪（北京）投资管理有限公司 版权所有  京ICP备16011767号.
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
	avalon.define({$id: "mains", storeType: ${store.storeType!0}});
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
	        url: '${base}/storeSetting/fileUpload',
	        secureuri:false,                      //是否启用安全提交,默认为false
	        fileElementId:myBlogImage,           //文件选择框的id属性
	        dataType:'json',                       //服务器返回的格式,可以是json或xml等
	        fileSize:5120000,
	        allowType:'jpg,jpeg,png,JPG,JPEG,PNG',
	        success:function(data, status){        //服务器响应成功时的处理函数
	            if( true == data.success){     //0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述)
	            	//alert(data.result);
	               $("img[id='"+imgId+"']").attr("src", "${imgServer}"+data.result);
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