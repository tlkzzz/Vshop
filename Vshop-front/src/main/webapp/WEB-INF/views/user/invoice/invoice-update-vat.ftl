<link href="${base}/res/css/shop.css" rel="stylesheet" type="text/css">
<link href="${base}/res/css/base.css" rel="stylesheet" type="text/css">
<link href="${base}/res/css/invoice.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${base}/res/js/jquery.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/area.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/jquery.validation.min.js"></script>
<script type="text/javascript" >
	var SITEURL = '${base}';
</script>
<div>

	<#assign invoiceInfoTag = newTag("invoiceInfoTag")>
	<#assign invoices =invoiceInfoTag("{'invState':'2'}")>
	<#if invoices?size gt 0>
		<script>
			$(function(){
				<#list invoices as invoice>
					$("[name=invCompany]").attr("value",'${invoice.invCompany}');
					$("[name=invCode]").attr("value",'${invoice.invCode}');
					$("[name=invRegAddr]").attr("value",'${invoice.invRegAddr}');
					$("[name=invRegPhone]").attr("value",'${invoice.invRegPhone}');
					$("[name=invRegBname]").attr("value",'${invoice.invRegBname}');
					$("[name=invRegBaccount]").attr("value",'${invoice.invRegBaccount}');
					$("[name=invRecName]").attr("value",'${invoice.invRecName}');
					$("[name=invRecMobphone]").attr("value",'${invoice.invRecMobphone}');
					$("[name=invRecProvince]").attr("value",'${invoice.invRecProvince}');
					$("[name=invGotoAddr]").attr("value",'${invoice.invGotoAddr}');
					$("[name=invContent]").attr("value",'${invoice.invContent}');
				</#list>
			});
		</script>
	</#if>
	<form action="${base}/invoice/updateInvoiceVAT.do" method="post" id="invoiceForm">
		<div class="form" id="invoice-box-03">
			<div class="steps">
				<div class="step step1">
				<input type="hidden" id="vatCanEdit" value="false">
					<div class="item">	
						<span class="label" id="vat_companyName_div"><em>*</em>单位名称：</span>
						<div class="fl">
							<input type="text" class="itxt itxt04 vat-step-1" name="invCompany" id="vat_companyName" value="" onblur="check_Invoice('vat_companyName', this.value)">
						</div>
					</div>
					<div class="item" id="vat_code_div">
						<span class="label"><em>*</em>纳税人识别码：</span>
						<div class="fl">
							<input type="text" class="itxt itxt04 vat-step-1" name="invCode" id="vat_code" value="" onblur="check_Invoice('vat_code', this.value)">
						</div>
					</div>
					<div class="item" id="vat_address_div">
						<span class="label"><em>*</em>注册地址：</span>
						<div class="fl">
							<input type="text" class="itxt itxt04 vat-step-1" name="invRegAddr" id="vat_address" value="" onblur="check_Invoice('vat_address', this.value)">
						</div>
					</div>
					<div class="item" id="vat_phone_div">					
						<span class="label"><em>*</em>注册电话：</span>
						<div class="fl">
							<input type="text" class="itxt itxt04 vat-step-1" name="invRegPhone" id="vat_phone" value="" onblur="check_Invoice('vat_phone', this.value)">
						</div>
					</div>
					<div class="item" id="vat_bankName_div">					
						<span class="label"><em>*</em>开户银行：</span>
						<div class="fl">
							<input type="text" class="itxt itxt04 vat-step-1" name="invRegBname" id="vat_bankName" value="" onblur="check_Invoice('vat_bankName', this.value)">
						</div>
					</div>
					<div class="item" id="vat_bankAccount_div">					
						<span class="label"><em>*</em>银行账户：</span>
						<div class="fl">
							<input type="text" class="itxt itxt04 vat-step-1" name="invRegBaccount" id="vat_bankAccount" value="" onblur="check_Invoice('vat_bankAccount', this.value)">
						</div>
					</div>
					<div class="item">
						<span class="label">发票内容：</span>
						<div class="fl">
							<div class="invoice-list">
								<input type="hidden" name="invContent" value="1"/>
								<ul id="electro_book_content_radio">
									<li class="invoice-item invoice-item-selected" id="electro-invoice-content-1" name="normal-normalContent" value="1" style="cursor:pointer" onclick="select(this);">
										不开发票<b></b>
									</li>
									<li class="invoice-item" id="electro-invoice-content-2" name="normal-normalContent" value="2" style="cursor:pointer" onclick="select(this);">
										明细<b></b>
									</li>
								</ul>
							</div>
						</div>
					</div>					
   					<div class="item" id="name_div">					
						<span class="label"><em>*</em>收票人姓名：</span>
						<div class="fl">
							<input type="text" class="itxt itxt04" id="consignee_name" name="invRecName" value="" maxlength="20" onblur="check_InvoiceConsignee('name_div')">
						</div>
					</div>
					<div class="item" id="call_div">					
						<span class="label"><em>*</em>收票人手机：</span>
						<div class="fl">
							<input type="text" class="itxt itxt04" id="consignee_mobile" name="invRecMobphone" value="" onblur="check_InvoiceConsignee('call_phone_div')" maxlength="11" >
						</div>
					</div>
				
					<div class="item" id="area_div">
						<input name="invRecProvince" value="" type="hidden"/>					
						<span class="label"><em>*</em>收票人省份：</span>
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
					</div>
					<div class="item" id="address_div">					
						<span class="label"><em>*</em>详细地址：</span>
						<div class="fl">
							<input type="text" class="itxt itxt04" id="consignee_address" name="invGotoAddr" value="" maxlength="50" onblur="check_InvoiceConsignee('address_div')">
						</div>
					</div>
					<div class="item">
						<span class="label">&nbsp;</span>
						<div class="fl">
							<div class="op-btns">
								<a href="#none" class="btn-9" onclick="save_Invoice()">保存</a>
								<a href="#none" class="btn-9 ml10" onclick="quxiao()">取消</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>
<script type="text/javascript">
	
	$(function(){
		formValidate();
	});
	
	//纳税人识别码格式验证
	jQuery.validator.addMethod("isInvCode",function(value,element){
		var code = /^([0-9a-zA-Z]{15,20})$/; //纳税人识别码
		return this.optional(element) || (code.test(value));       
	},"请正确填写您的纳税人识别码");
	
	//电话号码格式验证
	jQuery.validator.addMethod("isRegPhone",function(value,element){
		var tel = /^(\d{2,5}-)?\d{6,9}(-\d{2,4})?$/; //电话号码格式010-12345678 
		return this.optional(element) || (tel.test(value));       
	},"请填写正确的电话号码格式(010-12345678)");
	
	//银行卡号格式验证
	jQuery.validator.addMethod("isRegBaccount",function(value,element){
		var bankcard =/^(\d{16}|\d{17}|\d{18}|\d{19})$/; //银行卡位数是16到19之间的
		return this.optional(element) || (bankcard.test(value));  
	},"请填写正确的银行卡位数(16到19之间)");
	
	jQuery.validator.addMethod("isRecMobphone",function(value,element){
		var pattern = /^1\d{10}$/; //手机号格式
		return this.optional(element) || (pattern.test(value));  
	},"请填写正确的手机号");
	
	var formValidate = function(){
		$('#invoiceForm').validate({
		    errorPlacement: function(error, element){
		        $(element).next('.field_notice').hide();
		        $(element).after(error);
		    },
		    rules : {
		    	invCompany : {
		            required	: true
		        },
		        invCode : {	
					required   : true,
					isInvCode : true
		        },
		        invRegAddr : {
		            required   : true     
		        },
		        invRegPhone : {
		            required	: true,
		            isRegPhone : true
		        },
		        invRegBname : {
		            required	: true
		        },
		        invRegBaccount : {
		            required	: true,
		            isRegBaccount : true
		        },
		        invRecName : {
		            required	: true   
		        },
		        invRecMobphone : {
		            required	: true,   
		            isRecMobphone : true
		        },
		        invGotoAddr : {
		        	required : true   
		        }
		    },
		    messages : {
		    	invCompany  : {
		            required	: '请填写单位名称'
		        },
		        invCode : {
					required : '请填写纳税人识别码',
					isInvCode : '请正确填写您的纳税人识别码'
		        },
		        invRegAddr  : {
		        	required	: '请填写注册地址'  
		        },
		        invRegPhone  : {
		            required	: '请填写注册电话',
		            isRegPhone : '请填写正确的电话号码格式(010-12345678)'
		        }, 
		        invRegBname  : {
		            required	: '请填写开户银行'
		        },
		        invRegBaccount  : {
		            required	: '请填写银行账户',
		            isRegBaccount : '请填写正确的银行卡位数(16到19之间)'
		        },
		        invRecName  : {
		        	required	: '请填写收票人姓名'
		        },
		        invRecMobphone : {
		            required	: '请填写收票人手机',
		            isRecMobphone : '请填写正确的手机号'   
		        },
		        invGotoAddr : {
		        	required	: '请填写收票人详细地址'   
		        }
		    }
		});
	}
	
	//地址回显
	/* $(function(){
		var address = $("input[name='invRecProvince']").val();
		if(address!=""){
			String[] strs = address.split(",");
			init_area(strs[0],strs[1],strs[2]);
		}
	}); */

	//关闭窗口
	function quxiao(){
		parent.layer.closeAll();
	}
	
	function select(obj){
		$("#electro_book_content_radio").find(".invoice-item-selected").removeClass("invoice-item-selected");
		$(obj).addClass("invoice-item-selected");
		$("input[name='invContent']").val($(obj).attr("value"));
	}
	
	function save_Invoice(){
		if($('#invoiceForm').valid()){
			var quid = $("#area_id").val();
			if(quid==""){
				parent.alert("请选择收票人地址");
				return false;
			}else{
				var provinceId = $("#area").val();
				var cityId = $("#city_id").val();
				var areaId = $("#area_id").val();
				$("input[name='invRecProvince']").val(provinceId+","+cityId+","+areaId);
			}
			var invoice = $("#invoiceForm").serialize();
			$.ajax({
	        	url:'${base}/invoice/updateInvoiceVAT.do',
	            type:'post',
	            data : invoice,
	            dataType:'json',
	            success:function(data){
	            	if(data.success){
	            		if(data.invoice.invContent==2){
	            			str = "增值税发票&nbsp;&nbsp;&nbsp;&nbsp;"+data.invoice.invCompany+"&nbsp;&nbsp;&nbsp;&nbsp;"+"明细";
	            			parent.$("#invoice").html(str);
	            			parent.$("input[name='invoiceId']").val(data.invoice.invId);
	            		}else if(data.invoice.invContent==1){
	            			str = "不开发票";
	            			parent.$("#invoice").html(str);
	            			parent.$("input[name='invoiceId']").val(data.invoice.invId);
	            		}
	            		parent.layer.closeAll();
	                }
	            },error:function(){
	            	parent.layer.msg('通信失败', {icon: 2});
	            }
	        }); 
	     }
	}
</script>
