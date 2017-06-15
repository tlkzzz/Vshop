<link href="${base}/res/css/base.css" rel="stylesheet" type="text/css">
<link href="${base}/res/css/member.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${base}/res/js/jquery.js"></script>
<script src="${base}/res/js/area.js" charset="utf-8"></script>
<script type="text/javascript">
var SITEURL = '${base}';
</script>
<div id="fwin_my_address_add" class="dialog_wrapper ui-draggable" style="z-index: 1100; position: absolute; width: 550px; top: 38px;">
    <div class="dialog_body" style="position: relative;">
        <div class="dialog_content" style="margin: 0px; padding: 0px;">
            <div class="eject_con">
                <div class="adds">
                    <form method="post" action="" id="address_form" target="_parent">
                        <input type="hidden" name="form_submit" value="ok">
                        <input type="hidden" name="id" value="${address.addressId}">
                        <dl>
                            <dt class="required"><em class="pngFix"></em>收货人：</dt>
                            <dd>
                                <p>
                                    <input type="text" class="text" id="trueName" name="trueName" value="${address.trueName}">
                                    <span class="trueNameMsg" style="color: red"></span>
                                </p>

                                <p class="hint">请填写您的真实姓名</p>
                            </dd>
                        </dl>
                        <dl>
                            <dt class="required"><em class="pngFix"></em>所在地区：</dt>
                            <dd>
                                <div id="region">
                                	<input id="city_id" type="hidden" name="city_id" value="">
									<input id="area_id" class="area_ids" type="hidden" name="area_id" value="">
									<input id="area_info" class="area_names" type="hidden" name="area_info" value="">
                                    <span id="spanarea">
							        	<select name="area" class="select" id="area">
							        		<option selected="selected" value="0">请选择</option>
							           		<#if areas??>
							  					<#list areas as str>
							  						<option value="${str.areaId}" >${str.areaName}</option>
							  					</#list>
											</#if>
						         		</select> 	
								    </span>
								    <span id="spancity"></span>
								    <span id="spanqu"></span>
								    <span class="areaMsg" style="color: red"></span>
                                </div>
                            </dd>
                        </dl>
                        <dl>
                            <dt class="required"><em class="pngFix"></em>街道地址：</dt>
                            <dd>
                                <p>
                                    <input class="text w300" type="text" name="address" id="address" value="${address.address}">
                                	<span class="area_addressMsg" style="color: red"></span>
                                </p>

                                <p class="hint">不必重复填写地区</p>
                            </dd>
                        </dl>
                      <!--  <dl>
                            <dt class="required"><em class="pngFix"></em>邮编：</dt>
                            <dd>
                                <input type="text" class="text valid" name="zipCode" id="zipCode" maxlength="6" value="${address.code}">
                            	<span class="zipCodeMsg" style="color: red"></span>
                            </dd>
                        </dl>-->
                        <dl>
                            <dt class="required"><em class="pngFix"></em>手机号码：</dt>
                            <dd>
                                <input type="text" class="text" name="mobPhone" id="mobPhone" value="${address.mobPhone}">
                                <span class="mobPhoneMsg" style="color: red"></span>
                            </dd>
                        </dl>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
$(function(){
	//真实姓名
	$("#trueName").change(function(){
		if($(this).val().trim()!=''){
			$(".trueNameMsg").html('');
		} 
	});
	//手机号码
	$("#mobPhone").change(function(){
		var mobileexp = /^1\d{10}$/;
		
		if(mobileexp.test($(this).val().trim())){
			$(".mobPhoneMsg").html('');
		} 
	});	
	//街道地址
	$("#address").change(function(){
		if($(this).val().trim()!=''){
			$(".area_addressMsg").html('');
		} 
	});	
	//邮政编码
	//$("#zipCode").change(function(){
	//	if($(this).val().trim()!=''){
	//		$(".zipCodeMsg").html('');
		//} 
	//});
});
</script>