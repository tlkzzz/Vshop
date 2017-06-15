<@p.header title="供应商中心-商品修改"/>
<div class="layout">
	<link rel="stylesheet"
		href="${base}/res/js/jquery-ui/themes/ui-lightness/jquery.ui.css" />
	<link rel="stylesheet"
		href="${base}/res/js/kindeditor/themes/default/default.css" />
	<script type="text/javascript"
		src="${base}/res/js/jquery.ajaxContent.pack.js"></script>
	<script type="text/javascript"
		src="${base}/res/js/jquery-ui/i18n/zh-CN.js" charset="utf-8"></script>
	<script type="text/javascript" src="${base}/res/js/common_select.js"
		charset="utf-8"></script>
	<script type="text/javascript"
		src="${base}/res/js/ajaxfileupload/ajaxfileupload.js" charset="utf-8"></script>
	<script type="text/javascript" src="${base}/res/js/store_goods.js"></script>
	<script type="text/javascript"
		src="${base}/res/js/jquery.validation.min.js"></script>
	<script type="text/javascript" src="${base}/res/js/area.js"></script>
	<script type="text/javascript" src="${base}/res/js/layer/layer.js"></script>
	<script type="text/javascript"
		src="${base}/res/js/kindeditor/kindeditor.js"></script>
	<script type="text/javascript"
		src="${base}/res/js/kindeditor/plugins/code/prettify.js"></script>
	<script type="text/javascript"
		src="${base}/res/js/kindeditor/lang/zh_CN.js"></script>
	<script type="text/javascript"
		src="${base}/res/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${base}/res/js/jquery.cookie.js"></script>
	<script type="text/javascript"
		src="${base}/res/js/perfect-scrollbar.min.js"></script>
	<script type="text/javascript" src="${base}/res/js/jquery.masonry.js"></script>
	<script type="text/javascript"
		src="${base}/res/js/jquery.scrollLoading-min.js"></script>
	<script type="text/javascript" src="${base}/res/js/goods/goods.js"></script>

	<script type="text/javascript" src="${base}/res/js/jquery.sortable.js"
	charset="utf-8"></script>
	
	<script type="text/javascript">
	 jQuery.validator.addMethod("priceMarket",function(value,element){
	    	var gsp = parseFloat($("[name=goods_market_price]").val());
	    	var gmp = parseFloat($("[name=goods_cost_price]").val());
	    	if(gmp>gsp){
	    		return false;
	    	}else{
	    		return true;
	    	}
	    });
	 
	 
	/**
	 * 表单校验
	 */
	var formValidate = function(){
		$('#goods_form').validate({
	        errorPlacement: function(error, element){
	            $(element).next('.field_notice').hide();
	            $(element).after(error);
	        },
	        rules : {
	            goods_name : {
	                required	: true,
	                minlength 	: 3,
	                maxlength	: 50
	            },
	            goods_cost_price : {	
					required   : true,
	                number     : true,
	                min        : 0.01,
	                max		   : 1000000
	            },
	            goods_market_price : {	
	 				required   : true,
	                 number     : true,
	                 max        : 1000000,
	                 min		   : 0.01,
	                 priceMarket  : true
	             },
	            price : {	
					required   : true,
	                number     : true,
	                min        : 0.01,
	                max		   : 1000000
	            },
	            sku : {	
					required   : true,
	                number     : true,
	                min        : 1,
	                max		   : 1000000
	            },
	            goods_storage  : {
					required	: true,
	                digits		: true,
	                min			: 1,
	                max			: 1000000000
	            },
	            area  : {
					required	: true,
	                digits		: true,
	                min			: 1,
	                max			: 1000000000
	            },
	            city  : {
					required	: true,
	                digits		: true,
	                min			: 1,
	                max			: 1000000000
	            },
	            transport_id:{
	            	transportId:true
	            },
	            py_price	:{
	            	pyPrice:true
	            },
	            kd_price	:{
	            	kdPrice:true
	            },
	            es_price	:{
	            	emsPrice:true
	            }                
	        },
	        messages : {
	            goods_name  : {
	                required	: '商品名称不能为空',
	                minlength 	: '商品标题名称长度至少3个字符，最长50个汉字',
	                maxlength 	: '商品标题名称长度至少3个字符，最长50个汉字'
	            },
	            goods_cost_price : {
					required: '商品价格不能为空',
	                number     : '商品价格只能是数字',
	                min		   : '商品价格必须是0.01~1000000之间的数字',
	                max		   : '商品价格必须是0.01~1000000之间的数字'
	            },
	            goods_market_price : {
	            	required   : '市场价格不能为空',
	                number     : '市场价格只能是数字',
	                min		   : '市场价格必须是0.01~1000000之间的数字',
	                max		   : '市场价格必须是0.01~1000000之间的数字',
	                priceMarket  : '市场价格不能小于商品价格'
	            },
	            price : {	
					required   : '价格不为空',
	                number     : '只能数字',
	                min        : '数字不合法',
	                max		   : '数字不合法'
	            },
	            sku : {	
					required   : '库存不为空',
	                number     : '只能数字',
	                min        : '数字不合法',
	                max		   : '数字不合法'
	            },
	            goods_storage : {
					required: '商品库存不能为空',
	                digits  : '库存只能填写数字',
	                min		: '商铺库存数量必须为1~1000000000之间的整数',
	                max		: '商铺库存数量必须为1~1000000000之间的整数'
	            },
	            area : {
					required: '请填写省份',
	                digits  : '请填写省份',
	                min		: '请填写省份',
	                max		: '请填写省份'
	            },
	            city : {
					required: '请填写市',
	                digits  : '请填写市',
	                min		: '请填写市',
	                max		: '请填写市'
	            },
	            py_price	:{
	            	pyPrice:'缺少平邮价格'
	            },
	            kd_price	:{
	            	kdPrice:'缺少快递价格'
	            },
	            es_price	:{
	            	emsPrice:'EMS价格格式错误'
	            },
	            transport_id:{
	            	transportId:'请选择要使用的运费模板'
	            }                  
	        }
	    });
	};
	
	/**
	 * 初始化表单数据
	 */
	var initFormData = function(){
		$("#sub").click(function(){
			if($('#goods_form').valid()){
				//商品名称
				goodsName = $("[name=goods_name]").val();
				//商品副标题
				goodsSubtitle = $("[name=goods_subtitle]").val();
				//分类id
				gcId = $("#cate_id").val();
				//分类名称
				gcName = $("#cate_name").val();
				//品牌id
				brandId = $("#brand").val();
				//品牌名
				if(brandId != ''){
					brandName = $("#brand").find(":selected").html();
				}
				//类型id
				typeId = $("[name=type_id]").val();
				//规格的数量
				specCount = $("[nctype=spec_group_dl]").length;
				if($("[name=skuDo]").length > 0){
					if(specCount != 0 && specCount != ""){
						specName += "{";
						var specNameOne = "";
						$("[nctype=spec_group_dl]").each(function(){
							specId = $(this).attr("spId");
							specNameOne = $(this).attr("spName");
							specName += "\"" + specId + "\":\"" + specNameOne + "\",";
						});
						if(specName != ""){
							specName = specName.substring(0, specName.length-1);
							specName += "}"
						}
					}
				}
				goodsImage = $("[name=deletePhoto]").attr("imageSrc");
				$("[name=deletePhoto]").each(function(){
					goodsImageMore += $(this).attr("imageSrc") + ",";
				});
				goodsCostPrice = $("[name=goods_cost_price]").val();
				goodsMarketPrice = $("[name=goods_market_price]").val();
				goodsSerial = $("[name=goods_serial]").val();
				goodsShow = $("[name=goods_show]:checked").attr("value");
				if(goodsShow == "2"){
					goodsShow = "0";
					var timeTo = $("#time_to").val();
					if(timeTo != ''){
						prepareUp = timeTo;
					}
				}
				goodsCommend = $("[name=goods_commend]:checked").attr("value");
				goodsKeywords = $("[name=seo_keywords]").val();
				goodsDescription = $("[name=seo_description]").val();
				goodsBody = $("#goods_body").val();
				$("[name=attrSelect]").each(function(){
					if($(this).val()==''){
						attrTag = false;
					}
				});
				if($("[name=attrSelect]").length == 0){
					attrTag = false;
				}
				if(attrTag){
					var goodsAttrCount = $("[nc_type=attr_select]").length;
					if(goodsAttrCount > 0){
						goodsAttr += "{";
					}
					$("[nc_type=attr_select]").each(function(){
						var attrId = $(this).attr("attrId");
						var attrName = $(this).attr("attrName");
						var $choice = $(this).find(":selected");
						var attrValueName = $choice.attr("value");
						var attrValueId = $choice.attr("attrValueId");
						goodsAttr += "\"" + attrId + "\":\"{\\\"name\\\":\\\""+ attrName +"\\\",\\\"" + attrValueId + "\\\":\\\"" + attrValueName + "\\\"}\","
					});
					if(goodsAttrCount > 0){
						goodsAttr = goodsAttr.substring(0, goodsAttr.length-1);
						goodsAttr += "}";
					}
				}
				//首先判断规格的数量
				if($("[name=skuDo]").length > 0){
					//设置规格开关开启
					specOpen = "1";
					goodsSpec += "{";
					var specTag = 0;
					//循环获得所选择的规格值
					$("[nctype=spec_group_dl]").each(function(){
						//得到规格id,和所选择规格值的数量
						var goodsSpecId = $(this).attr("spId");
						var goodsSpecValueCount = $(this).find(":checked").length;
						//拼接字符串
						goodsSpec += "\"" + goodsSpecId + "\":\"{";
						//判断是否有选择规格
						if(goodsSpecValueCount > 0 && goodsSpecValueCount != ""){
							
							//循环获得每一个规格值的属性
							$(this).find(":checked").each(function(){
								//规格值id
								var goodsSpecValueId = $(this).attr("value");
								//规格值名称
								var goodsSpecValueName = $(this).attr("spValueName");
								//拼接字符串
								goodsSpec += "\\\"" + goodsSpecValueId + "\\\":\\\"" + goodsSpecValueName + "\\\",";
								specTag++;
							});
							//结束当前这个规格,进入下一个规格
							goodsSpec = goodsSpec.substring(0, goodsSpec.length-1);
							goodsSpec += "}\",";
						}
					});
					if(specTag > 0){
						//整个规格值结束
						goodsSpec = goodsSpec.substring(0, goodsSpec.length-1);
						goodsSpec += "}";
					}

					
				}else{
					//设置规格开关关闭
					specOpen = "0";
				}
				
				if($("[name=skuDo]").length > 0){
					goodsColImg = "{";
					$("[name=customSpecImage]").each(function(){
						if("" != $(this).attr("imageSrc") && $(this).attr("imageState") == "1"){
							goodsColImg += "\"" + $(this).attr("spValueId") + "\":\"" + $(this).attr("imageSrc") + "\",";
							imageSrcCount += 1;
						}
					});
					if(imageSrcCount > 0){
						goodsColImg = goodsColImg.substring(0, goodsColImg.length-1);
						goodsColImg += "}";
					}else{
						goodsColImg = "";
					}
				}
				goodsForm = $("[name=goods_form]:checked").attr("value");
				cityIdSelect = $("#city").val();
				cityName = $("#city").find("option:selected").html();
				provinceIdSelect = $("#area").val();
				provinceName = $("#area").find("option:selected").html();
				goodsTransfeeCharge = $("[name=goods_transfee_charge]:checked").val();
				//如果是买家承担运费
				if(goodsTransfeeCharge == "0"){
					if($("[name=isApplyPostage]:checked").val() == "1"){
						//运费模板ID，不使用运费模板值为0
						transportId = $("#transportSelected").val();
					}/* else{
						//平邮
						var pyPrice = "";
						pyPrice = $("[name=py_price]").val();
						
						//快递
						var kdPrice = "";
						kdPrice = $("[name=kd_price]").val();
						
						//ems
						var esPrice = "";
						esPrice = $("[name=es_price]").val();
					} */
				}
				StoreClassId = $("[name=storeGoodsClass]").val();
				//判断是否有选择规格,如果没有规格则没有价格区间
				if($("[name=skuDo]").length > 0){
					//获得所有的价格
					var $allPrice = $("[name=price]")
					//如果只有一个的价格,则价格区间为:x-x
					if($("[name=skuDo]").length == 1){
						goodsCostPriceInterval = $allPrice.val() + "-" + $allPrice.val();
					}else{
						var arrPriceLength = $("[name=skuDo]").length;
						//循环放入数组
						var arrPrice = new Array(arrPriceLength);
						$allPrice.each(function(j,val){
							arrPrice[j] = parseFloat($(this).val());
						});
						var maxPrice = Math.max.apply(null,arrPrice);
						var minPrice = Math.min.apply(null,arrPrice);
						goodsCostPriceInterval = minPrice + "-" + maxPrice;
					}
				}
				
				
				//定义goodsSpec的实体类json数据
				//首先获得规格名称
				//规格名称
				if($("[name=skuDo]").length > 0){
					var specGoodsName = "";
					//规格的数量
					var specCount = $("[nctype=spec_group_dl]").length;
					if(specCount != 0 && specCount != ""){
						specGoodsName += "{";
						var specNameOne = "";
						$("[nctype=spec_group_dl]").each(function(){
							specId = $(this).attr("spId");
							specNameOne = $(this).attr("spName");
							specGoodsName += "\\\"" + specId + "\\\":\\\"" + specNameOne + "\\\",";
						});
						if(specCount > 0){
							specGoodsName = specGoodsName.substring(0, specGoodsName.length-1);
							specGoodsName += "}"
						}
					}
					var goodsSpecJson = "[";
					$("[name=skuDo]").each(function(){
					var specGoodsSpec = "";
					var spIds = $(this).attr("spId").split(",");
					var spNames = $(this).attr("spName").split(",");
					var specValueNames = $(this).attr("specValueName").split(",");
					var specValueIds = $(this).attr("specValueId").split(",");
					var goodsSpecJsonCount = spIds.length;
					//当前规格下的商品价格
					var specGoodsPrice = $(this).find("[name=price]").val();
					//当前规格下的规格商品编号
					var specGoodsSerial = $(this).find("[name=huohao]").val();
					//当前规格下的商品sku
					var specGoodsStorage = $(this).find("[name=sku]").val();
					//当前规格下的商品规格序列化
					specGoodsSpec += "{";
					for(var i = 0; i < spIds.length; i++){
						var specValueName = specValueNames[i];
						var specValueId = specValueIds[i];
						specGoodsSpec += "\\\"" + specValueId + "\\\":\\\"" + specValueName + "\\\","
					}
					specGoodsSpec = specGoodsSpec.substring(0, specGoodsSpec.length-1);
					specGoodsSpec += "}";
					goodsSpecJson += "{\"specName\":\""+ specGoodsName +"\",\"specGoodsPrice\":\""+ specGoodsPrice +"\",\"specGoodsSerial\":\""+ specGoodsSerial +"\",\"specGoodsStorage\":\""+ specGoodsStorage +"\",\"specGoodsSpec\":\""+ specGoodsSpec +"\"},";
					});
					//去掉最后的逗号
					goodsSpecJson = goodsSpecJson.substring(0,goodsSpecJson.length-1);
					goodsSpecJson += "]";
				}
				var args = {
						"goodsId":$("[name=goodsId]").val(),
						"goodsName":goodsName,
						"storeId" : $("[name=store_id]").val(),
						"storeName":$("[name=store_name]").val(),
						"goodsSubtitle":goodsSubtitle,//商品副标题
						"gcId":gcId,
						"gcName":gcName,
						"brandId":brandId,
						"brandName":brandName,
						"typeId":typeId,
						"specOpen":specOpen,//规格开关
						"specName": specName,//规格名称
						"goodsImage": goodsImage,//商品默认封面图片
						"goodsImageMore": goodsImageMore,//商品多图
						"goodsCostPrice": goodsCostPrice,//商品供应商价格
						"goodsCostPriceInterval": goodsCostPriceInterval,//区间价格
						"goodsStorePrice" : $("[name=goods_store_price]").val(),
						"goodsMarketPrice":goodsMarketPrice,
						"goodsSerial": goodsSerial,//商品货号
						"goodsShow": goodsShow,//商品上架1上架0下架
						"prepareUp": prepareUp,//上架时间
						"goodsCommend": goodsCommend,//商品推荐
						"goodsKeywords": goodsKeywords,//商品关键字
						"goodsDescription": goodsDescription,//商品描述 
						"goodsBody": goodsBody,//商品详细内容
						"goodsAttr": goodsAttr,// 商品属性 
						"goodsSpec": goodsSpec,//商品规格
						"goodsColImg": goodsColImg,//颜色自定义图片
						"goodsForm": goodsForm,//商品类型,1为全新、2为二手
						"transportId": 0,//运费模板ID，不使用运费模板值为0
						/* "pyPrice": pyPrice,
						"kdPrice": kdPrice,
						"esPrice": esPrice, */
						"cityId": cityId,//商品所在地(市)
						"cityName": cityName,
						"provinceId": provinceId,//商品所在地(省)
						"provinceName": provinceName,
						"goodsTransfeeCharge": goodsTransfeeCharge,//商品运费承担方式 默认 0为买家承担 1为卖家承担
						"StoreClassId": StoreClassId,//供应商自定义分类id
						"goodsSpecJson": goodsSpecJson,//goodsSpec的实体类json串
						"goodsTotalStorage": $("[name=goods_storage]").val()//总库存
				};
				var url = APP_BASE + "/pro/updatePro";
		        //加载进度条
		        layer.load(2, {
		               shade: [0.2,'#999999'] //0.1透明度的白色背景
		        });
				$.post(url, args, function(data){
					//保存成功
					if(data.message == "1"){
						layer.msg("修改成功" , 
								{time:1500,
								icon:1 
								},
								function(){location.reload([true])}
						);
						//setTimeout("ok("+goodsShow+")",1500); 
					}else{
						//失败
						layer.msg("保存失败" , {icon:2});
					}
				});
			}else{
				//返回信息错误的地方
				$("html,body").animate({scrollTop:$(".error:visible").offset().top},1000);
			};
		});
	};

	</script>
		
	<style>
	#header h1 i {  font-size: 24px;font-family: "微软雅黑";float: left;margin-top: 12px;margin-left: 12px;}
	h1{
	font-size:24px;
	font-family: arial;
	margin: 10px;
}

.gbin1-list {
	list-style: none;
	padding:0;
	margin:0;
}

.gbin1-list li {

	float: left;

	height: 80px;
	text-align: center;
	
	margin: 17px;
	font-family: arial;

	color: #FFF;

	border-radius: 5px;
	padding: 0px;
}

#msg{
	font-size:12px;
	font-family: arial;
	background: #303030;
	color: #cccccc;
	padding: 10px;
	display: none;
	width: 370px;
	margin: 10px;
}
.ui-timepicker-div .ui-widget-header {
	margin-bottom: 8px;
}

.ui-timepicker-div dl {
	text-align: left;
}

.ui-timepicker-div dl dt {
	float: left;
	clear: left;
	padding: 0 0 0 5px;
}

.ui-timepicker-div dl dd {
	margin: 0 10px 10px 45%;
}

.ui-timepicker-div td {
	font-size: 90%;
}

.ui-tpicker-grid-label {
	background: none;
	border: none;
	margin: 0;
	padding: 0;
}

.ui-timepicker-rtl {
	direction: rtl;
}

.ui-timepicker-rtl dl {
	text-align: right;
	padding: 0 5px 0 0;
}

.ui-timepicker-rtl dl dt {
	float: right;
	clear: right;
}

.ui-timepicker-rtl dl dd {
	margin: 0 45% 10px 10px;
}

.box_arr .table_btn {
	width: 222px;
}

.box_arr .table_btn a {
	float: left;
}

.box_arr .table_btn a.disable_spec {
	background:
		url(${base}/res/images/btn.gif)
		repeat 0 -1018px;
	float: right;
}

.dialog_body {
	border: 0px;
}

.add_spec .add_link {
	color: #919191;
}

.add_spec .add_link:hover {
	color: red;
}

.add_spec h2 {
	padding-left: 10px;
}

.f_l {
	float: left;
	line-height: 24px;
}

.mls_id {
	width: 0;
	filter: alpha(opacity = 0);
	opacity: 0;
}

.noSelect {
	color: #B9B9B9 !important;
}
</style>


	<div class="wp">
		<ul class="flow-chart">
			<li class="step a2" title="选择商品所在分类"></li>
			<li class="step b1" title="填写商品详细信息"></li>
			<li class="step c2" title="商品发布成功"></li>
		</ul>
		<div class="item-publish">
			<form method="post" id="goods_form" action="#">
				<input type="hidden" name="form_submit" value="ok" /> <input
					type="hidden" name="goodsId" value="${goods.goodsId}" /> 
					<input type="hidden" name="type_id" value="${goods.typeId}" />
					<input type="hidden" name="goods_store_price" value="${goods.goodsStorePrice}" />
				<div class="goods-attribute">
					<dl class="tit">
						<h3>商品基本信息</h3>
					</dl>
					<dl>
						<dt>商品分类：</dt>
						<dd id="gcategory">
							<span class="f_l">${catename}</span> <a
								class="edit_gcategory ncu-btn1 ml10" href="${base}/pro/sell">
								<!-- <span>编辑</span> -->
							</a> <input type="text" id="cate_id" name="cate_id" value="${gcId}"
								class="mls_id text" /> <input type="hidden" name="cate_name"
								id="cate_name" value="${catename}" class="mls_names text" />
						</dd>
					</dl>
					<dl>
						<dt>商品类型：</dt>
						<dd>
							<p>
								<label style="padding-right: 8px;"> <#if
									goods.goodsForm==1> <input name="goods_form" value="1"
									checked="checked" type="radio" /> 全新 <#else> <input
									name="goods_form" value="1" type="radio" /> 全新 </#if>
								</label> <label> <#if goods.goodsForm==2> <input
									name="goods_form" value="2" checked="checked" type="radio" />
									二手 <#else> <input name="goods_form" value="2" type="radio" />
									二手 </#if>
								</label>
							</p>
						</dd>
					</dl>
					<dl>
						<dt class="required">
							<span class="red">*</span>商品名称：
						</dt>
						<dd>
							<p>
								<input name="goods_name" type="text" class="text w400" title=""
									value="${goods.goodsName}" />
							<p>
							<p class="hint">商品标题名称长度至少3个字符，最长50个汉字</p>
						</dd>
					</dl>
					<dl>
						<dt nc_type="no_spec">商品副标题：</dt>
						<dd nc_type="no_spec">
							<p>
								<input name="goods_subtitle" value="${goods.goodsSubtitle}"
									type="text" class="text w500" />
							</p>
							<p class="hint">商品副标题做商品特殊说明，位于详情页商品名称下面</p>
						</dd>
					</dl>
					<dl>
						<dt nc_type="no_spec">
							<span class="red">*</span>商品价格：
						</dt>
						<dd nc_type="no_spec">
							<p>
								<input name="goods_cost_price" value="${goods.goodsCostPrice}"
									type="text" class="text" /> <input
									name="goods_cost_price_interval" value="" type="hidden" />
								<!-- 价格区间 -->
							</p>
							<p class="hint">
								商品价格必须是0.01~1000000之间的数字</br>若启用了库存配置，请在下方商品库存区域进行管理</br>商品规格库存表中如有价格差异，供应商价格显示为价格区间形式
							</p>
						</dd>
					</dl>
					<dl>
						<dt nc_type="no_spec">
							<span class="red">*</span>市场价格：
						</dt>
						<dd nc_type="no_spec">
							<p>
								<input name="goods_market_price" value="${goods.goodsMarketPrice}"
									type="text" class="text" /> <input
									name="goods_market_price_interval" value="" type="hidden" />
								<!-- 价格区间 -->
							</p>
							<p class="hint">
								市场价格必须是0.01~1000000之间的数字</br>若启用了库存配置，请在下方商品库存区域进行管理</br>商品规格库存表中如有价格差异，供应商价格显示为价格区间形式
							</p>
						</dd>
					</dl>
					<#if specs??> <#list specs as sps>
					<dl nc_type="spec_group_dl_${sps_index}" spId="${sps.spId}"
						spName="${sps.spName}" nctype="spec_group_dl" class="spec-bg"
						spec_img="t" spFormat="${sps.spFormat}">
						<dt>
							<input type="hidden" name="sp_name[${sps_index}]"
								value="${sps.spName}" /> ${sps.spName}：
						</dt>
						<dd nctype="sp_group_val">
							<ul class="spec">
								<#if sps.specValueList??> <#list sps.specValueList as svl>
								<li><span nctype="input_checkbox" class="checkbox">
										<input type="checkbox"<#if specMap??>
										<#list specMap?keys as key> <#list specMap[key] as splist>
										<#if splist.spValueId==svl.spValueId> checked="checked" </#if>
										</#list> </#list> </#if> groupId="group_${sps_index}"
										name="specCheckBox" value="${svl.spValueId}"
										spValueName="${svl.spValueName}" class="sp_val"
										spFormat="${sps.spFormat}" >
								</span> <#if sps.spFormat==1> <span class="img"><img
										src="${imgServer}/${svl.spValueImage}"
										style="height: 17px; width: 17px;"></span> </#if> <span
									nctype="pv_name" class="pvname"> ${svl.spValueName} </span></li>
								</#list> </#if>
							</ul>
							<div class="clear"></div>
							<#if sps.spFormat==1&&sps.spName=="颜色">
							<table border="0" cellpadding="0" cellspacing="0"
								groupValue="group_${sps_index}" class="spec_table"
								style="display: none;" nctype="img_table">
								<thead>
									<tr>
										<th>${sps.spName}</th>
										<th class="w250">图片（无图片可不填）</th>
										<th>已有图片</th>
									</tr>
								</thead>
								<tbody>
									<#if sps.specValueList??> <#list sps.specValueList as svl>
									<tr style="display: none;" nctype="file_tr"
										value="${svl.spValueId}">
										<td><span class="img"> <img
												src="${imgServer}/${svl.spValueImage}"
												style="height: 17px; width: 17px;">
										</span> <span class="pvname" nctype="pv_name">${svl.spValueName}</span>
											<input type="hidden" value="" /></td>
										<td class="w300"><span class="pvname"> <input
												type="file" name="files"
												id="mySpecImage_${sps_index}_${svl_index}"
												multiple="multiple"
												onChange="ajaxFileUploads('mySpecImage_${sps_index}_${svl_index}',this,'customSpecImage_${sps_index}_${svl_index}','spec');" />
										</span> <span> <img class="spec-img"
												style="border: 0; height: 17px; width: 17px;" src=""
												imageSrc="" imageSrc="" imageName=""
												spValueName="${svl.spValueName}"
												spValueId="${svl.spValueId}" name="customSpecImage"
												imageState="0"
												nctype="customSpecImage_${sps_index}_${svl_index}" />
										</span></td>
										<td>当前无图片</td>
									</tr>
									</#list> </#if>
									<tr>
										<td colspan="15">
											<p class="hint" style="padding-left: 10px;">
												支持jpg、jpeg、gif、png格式图片。<br />建议上传尺寸360x360、大小1.00M内的图片。<br />商品详细页选中颜色图片后，颜色图片将会在商品展示图区域展示。
											</p>
										</td>
									</tr>
								</tbody>
							</table>
							</#if>
						</dd>
					</dl>
					</#list> </#if>
					<dl nc_type="spec_dl" class="spec-bg" style="display: none">
						<dt>库存配置：</dt>
						<dd class="spec-dd">
							<table border="0" cellpadding="0" cellspacing="0"
								class="spec_table">
								<thead>
									<#if specs??> <#list specs as sps>
									<th>${sps.spName}</th> </#list> </#if>
									<th><span class="red">*</span>价格</th>
									<th><span class="red">*</span>库存</th>
									<th>商品货号</th>
								</thead>
								<tbody nc_type="spec_table">
								</tbody>
							</table>
						</dd>
					</dl>
					<dl>
						<dt nc_type="no_spec">
							<span class="red">*</span>商品库存：
						</dt>
						<dd nc_type="no_spec">
							<p>
								<input name="goods_storage" value="${goodsstorenum}" type="text" class="text" />
							</p>
							<p class="hint">
								商铺库存数量必须为1~1000000000之间的整数</br>若启用了库存配置，则系统自动计算商品的总数，此处无需供应商填写
							</p>
						</dd>
					</dl>
					<dl>
						<dt nc_type="no_spec">商品货号：</dt>
						<dd nc_type="no_spec">
							<p>
								<input name="goods_serial" value="${goods.goodsSerial}"
									type="text" class="text" />
							</p>
							<p class="hint">
								商品货号是指供应商个人管理商品的编号，会员不可见</br>最多可输入20个字符，支持输入中文、字母、数字、_、/、-和小数点
							</p>
						</dd>
					</dl>
					<!-- 图片上传 -->
					<dl>
						<dt>商品图片：</dt>
						<dd>
							<div class="pic_list">
								<ul id="menu" class="menu">
									<li class="active" id="li_1"><a href="javascript:void(0);"
										style="background: #3366CC; color: #fff; line-height: 22px; height: 22px; padding: 0 11px; position: relative; margin-right: 20px;">
											本地上传 <input type="file"
											onChange="ajaxTypeImageUploads('myBlogImage1','photo')"
											style="opacity: 0; top: 0; left: 0; width: 100%; height: 100%; margin: 0; position: absolute;"
											id="myBlogImage1" name="myfiles" class="file"
											multiple="multiple" />
									</a></li>
								</ul>
								<div class="content">
									<div id="demo"></div>
									<div class="standard">
										
											<ul
												style="min-height: 130px; overflow: auto; overflow-x: hidden;"
												id="photoView01" class="gbin1-list" >
												<#if imageList??> <#list imageList as imgSrc>
												<li style='height: 120px; display: inline'>
												<img class='img' style='width: 100px; height: 100px'src='${imgServer}${imgSrc}' />
												<a href='javascript:void(0)'
													imageSrc='${imgSrc}' name='deletePhoto'> 删除</a></li>
													 </#list>
												</#if>
											</ul>
								
									</div>
								</div>
							</div>
						</dd>
					</dl>


					<dl class="tit">
						<h3>商品详情描述</h3>
					</dl>
					<dl>
						<dt>商品属性：</dt>
						<dd>
							<div class="attr">
								<#if goodsAttributes??> <#list goodsAttributes as gas>
								${gas.attrName} <select attrId="${gas.attrId}"
									attrName="${gas.attrName}" nc_type="attr_select"
									name="attrSelect">
									<option value='' nc_type='' selected="selected">请选择</option>
									<#if gas.avList??> <#list gas.avList as gav>
									<option attrValueId="${gav.attrValueId}"
										value="${gav.attrValueName}">${gav.attrValueName}</option>
									</#list> </#if>
								</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </#list> </#if>
							</div>
							<div class="clear"></div>
						</dd>
					</dl>
					<dl>
						<dt>商品品牌：</dt>
						<dd>
							<select name="brand_id" id="brand" disabled="disabled">
								<option value="" selected="selected" >-请选择-</option> <#if
								brands??> <#list brands as bds>
								<option  value="${bds.brandId}">${bds.brandName}</option>
								</#list> </#if>
							</select>
						</dd>
					</dl>
					<dl>
						<dt>商品描述：</dt>
						<dd>
							<p>
								<textarea id="goods_body" name="goods_body"
									style="width: 100%; height: 400px; visibility: hidden;">
              	${goods.goodsBody}
              </textarea>

							</p>
							<!-- <p class="info-album">
								<a class="des_demo"
									href="#?act=store_album&op=pic_list&item=des">插入相册图片</a>
							</p> -->
							<p id="des_demo" style="display: none;"></p>
						</dd>
					</dl>
					<!--transport info begin-->
					<dl class="tit">
						<h3>商品物流信息</h3>
					</dl>
					<dl>
						<dt>所在地：</dt>
						<dd>
							<span id="spanarea"> <select name="area" class="select"
								id="area" check="needCheck">
									<option value="">请选择</option> <#if areas??> <#list areas as
									str>
									<option value="${str.areaId}">${str.areaName}</option> </#list>
									</#if>
							</select>
							</span>&nbsp;&nbsp; <span id="spancity"></span> <span class="form-tips"
								style="color: red" name="check" id="checkedarea"></span> <input
								id="memberProvinceid" type="hidden" name="memberProvinceid"
								value=""> <input id="city_id" type="hidden"
								name="city_id" value=""> <input id="area_id"
								class="area_ids" type="hidden" name="area_id" value="">
							<input id="area_info" class="area_names" type="hidden"
								name="area_info" value="">
						</dd>
					</dl>
					<dl>
						<dt>运费：</dt>
						<dd>
							<p style="float: left; clear: both; margin-top: 8px;">
								<input type="radio" value="1" checked="checked"
									name="goods_transfee_charge" id="whops_seller"> <label
									for="whops_seller">供应商承担运费</label>
							</p>
							<p style="float: left; clear: both; margin-top: 8px;">
								<input type="radio" value="0" name="goods_transfee_charge"
									id="whops_buyer"> <label for="whops_buyer">会员承担运费</label>
							</p>
							<div class="transport_tpl" id="whops_buyer_box"
								style="display: none">
								<ul style="line-height: 30px">
									<li><input id="isApplyPostage_true" name="isApplyPostage"
										class="radio" type="radio" value="1"> <label
										for="isApplyPostage_true">使用运费模板</label>
										<div id="Postage" style="display: none">
											<div class="freight-template">
												<#if transport??> ${transport.title} </#if> <input
													onclick="window.open('${base}/transport/index')"
													type="button" value="查看我的运费模板" class="select-templ"
													id="postageButton">
											</div>
										</div></li>
								</ul>
							</div>
						</dd>
					</dl>
					<!--transport info end
					<dl class="tit">
						<h3>其他信息</h3>
					</dl>
					<dl>
						<dt>本店分类：</dt>
						<dd>
							<p>
								<select name="storeGoodsClass" class="sgcategory">
									<option value="">请选择</option> <#if StoreGoodsClassVoMap??>
									<#list StoreGoodsClassVoMap?keys as sgckey> <#list
									StoreGoodsClassVoMap[sgckey] as sgclist> <#if sgclist_index==0>
									<option value="${sgclist.parentId}">${sgclist.parentName}</option>
									<#elseif sgclist.childState>
									<option value="${sgclist.childId}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${sgclist.childName}</option>
									</#if> </#list> </#list> </#if>
								</select>
							</p>
							</p>
							<p class="hint">
								商品可以从属于供应商的多个分类之下,</br>供应商分类可以由 "用户中心 -> 供应商 -> 商品管理 -> 分类管理" 中自定义
							</p>
						</dd>
					</dl>
					<dl>
						<dt>商品发布：</dt>
						<dd>
							<p class="mt5">
								<label> <input name="goods_show" value="1" type="radio"
									<#if goods.goodsShow==1>checked="checked"</#if> /> 立即发布
								</label>
							</p>
							<p class="mt5">
								<label> <input name="goods_show" value="2" type="radio" 
										<#if goods.goodsShow==2>checked="checked"</#if>/>
									发布时间
								</label> <input onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"
									class="txt" type="text" value="" id="time_to" name="endTime"
									style="width: 250px; height: 18px;" />
							</p>
							<p class="mt5">
								<label> <input name="goods_show" value="0" type="radio" 
										<#if goods.goodsShow==0>checked="checked"</#if>/>
									放入仓库
								</label>
							</p>
						</dd>
					</dl>
					<dl>
						<dt>商品推荐：</dt>
						<dd>
							<p>
								<label style="padding-right: 8px;"> <input
									name="goods_commend" value="1"  type="radio" 
									<#if goods.goodsCommend==1>checked="checked"</#if>/>
									是
								</label> <label> <input name="goods_commend" value="0"
									type="radio" <#if goods.goodsCommend==0>checked="checked"</#if>/> 否
								</label>
							</p>
							<p class="hint">被推荐的商品会显示在供应商首页</p>
						</dd>
					</dl>
					<dl>
						<dt>
							SEO关键字</br>(keywords)：
						</dt>
						<dd>
							<p>
								<input name="seo_keywords" type="text" class="w400 text"
									value="${goods.goodsKeywords}" />
							</p>
							<p class="hint">
								SEO关键字 (keywords) 出现在商品详细页面头部的 Meta 标签中，</br>用于记录本页面商品的关键字，多个关键字间请用半角逗号
								"," 隔开
							</p>
						</dd>
					</dl>
					<dl>
						<dt>
							SEO描述</br>(description)：
						</dt>
						<dd>
							<p>
								<textarea class="w400" name="seo_description" rows="3"
									id="remark_input">${goods.goodsDescription}</textarea>
							</p>
							<p class="hint">
								SEO描述 (description) 出现在商品详细页面头部的 Meta 标签中，</br>用于记录本页面商品内容的概要与描述，建议120字以内
							</p>
						</dd>
					</dl>-->
					<div class="clear">
						<p>&nbsp;</p>
						<p>&nbsp;</p>
					</div>
					<div class="ncu-form-style tc mb30">
						<input type="button" class="submit" value="提交" id="sub" />
					</div>
				</div>
			</form>
		</div>
	</div>

	<script type="text/javascript">
var APP_BASE = '${base}';
var SITE_URL = "${imgServer}";
var imgBasePath = '${imgServer}';
var DEFAULT_GOODS_IMAGE = "upload/common/default_goods_image.gif_tiny.gif";
var provinceId = '${goods.provinceId}';
var cityId = '${goods.cityId}';
var storeGoodsClassId = '${goods.storeClassId}';
var goodsFreightPrice = '${goods.goodsTransfeeCharge}';
var goodsShow = '${goods.goodsShow}';
var timeTo = '${goods.prepareUpTime}';
var goodsName = "";             // 商品名称
var storeId = "";				//商家ID
var storeName = "";				//商家 名字
var goodsSubtitle = "";         // 商品副标题
var gcId = "";                  // 分类id
var gcName = "";                // 分类名称
var brandId = "";               // 品牌id
var brandName = "";             // 品牌名
var typeId = "";                // 类型id
var specName = "";              // 规格名称
var specCount = "";             // 规格的数量
var goodsImage = "";            // 商品默认封面图片
var goodsImageMore = "";        // 商品多图
var goodsCostPrice = "";       // 商品供应商价格
var goodsCostPriceInterval = "";// 设置商品的价格区间
var goodsMarketPrice = "";		//商品市场价格
var goodsSerial = "";           // 商品货号
var goodsShow = "";             // 商品上架1上架0下架
var prepareUp = "";             // 商品上架1上架0下架
var goodsCommend = "";          // 商品推荐
var goodsKeywords = "";         // 商品关键字
var goodsDescription = "";      // 商品描述
var goodsBody = "";             // 商品详细内容
var goodsAttr = "";             // 商品属性 
var attrTag = true;             // 商品属性 
var goodsSpec = "";             // 商品规格
var specOpen = "";              // 规格开关
var goodsColImg = "";           // 颜色自定义图片
var imageSrcCount = 0;          // 图片数量
var goodsForm = "";             // 商品类型,1为全新、2为二手
var cityIdSelect = "";          // 商品所在地(市)
var cityName = "";              // 商品所在地(市)名称
var provinceIdSelect = "";      // 商品所在地(省)
//var goodsTransfeeCharge = "";   // 商品运费承担方式 默认 0为会员承担 1为供应商承担
//var transportId = "0";
//var StoreClassId = "";          // 供应商自定义分类id
//var goodsStorePriceInterval = "";// 设置商品的价格区间
var PRICE_FORMAT = '&yen;%s';
var searchTxt = ' 搜索其实很容易！';
var specName = '${goods.specName}';
$(function (){
	// 初始化富文本编辑器
	initKinEdit();
    // 校验表单
    formValidate();
    // 校验运费模板
    checkTransportId();
  	// 颜色图片
    initColorImage();
  	// 初始化规格颜色的图片
    initSpecColor();
	// 初始化商品属性
	initGoodsAttr();
	// 初始化商品品牌
	initGoodsBrand();
	// 地区初始化
	init_area(provinceId, cityId);
	// 本店分类初始化
	initStoreGoodsClass();
	// 初始化运费
	initGoodsStoreTransfee();
	// 初始化,上架下架
	initgoodsUpOrDown();
	// 点击运费有谁负责的适合
	initGoodsTransFeeFrom();
	// 上架下架,以及定时上架
	initGoodsStatus();
	// 删除上传的图片
	deleteUploadImage();
	// 初始化表单数据
	initFormData();
	// 会员承担运费&运费模板
	initFreightStore();
	// 实现图片慢慢浮现出来的效果
	initImageFade();
	// 当选中规格后需要上传图片的时候
	specCheckBoxFun();
	$('#keyword').css("color","#999999");
	if(specName != ""){
		var totalSku = 0;
		$("[name=sku]").each(function(){
			totalSku += parseInt($(this).attr("value"));		
		});
		$("[name=goods_storage]").attr("value",totalSku);
		// 商品库存
		specGroupFun();
		// 初始化库存配置
		initSkuDoStore();
		getTotalSku();
	}
	
	/**
	 *这一段js是用来控制规格,以及规格的图片上传,以及选中规格后库存的控制
	 */
	//当规格都至少选中一个的时候,增加一个节点,用来让用户控制商品的库存,以及货号
	$("[nctype=spec_group_dl]").live("change",function(){
		specGroupFun();
	});
	
	//库存配置变化
	$("[nc_type=spec_table]").live("change",function(){
		getTotalSku();
		caculatePrice();
	});
	$("[name=specCheckBox]").live("click",function(){
		getTotalSku();
		caculatePrice();
	});
	
	$("[name=goodsInfo]").live("change",function(){
		$(this).next().attr("value",$(this).val);
	});
		
});

/**
 * 初始化规格颜色的图片
 */
var initSpecColor = function(){
	$("[nctype=file_tr]").find("[name=customSpecImage]").each(function(){
		<#if colImgMap??>
			<#list colImgMap?keys as colImgKey>
				if($(this).attr("spValueId") == '${colImgKey}'){
					$(this).attr("src",'${imgServer}${colImgMap[colImgKey]}');
					$(this).attr("imageSrc",'${colImgMap[colImgKey]}');
					$(this).attr("imageState","1");
					$(this).attr("imageName","${colImgMap[colImgKey]}");
				}
			</#list>
		</#if>
	});
};

/**
 * 初始化库存配置
 */
var initSkuDoStore = function(){
	$("[name=skuDo]").each(function(){
		var skuDoSpecValueIdArr = $(this).attr("specValueId").split(",");
		<#if goodsSpecs??>
			<#list goodsSpecs as gss>
				var specStr = '${gss.specValueIdStr}';
				var specStrArr = specStr.split(",");
				if(skuDoSpecValueIdArr.sort().toString() == specStrArr.sort().toString()){
					$(this).find("[name=price]").attr("value",'${gss.specGoodsPrice}');
					$(this).find("[name=sku]").attr("value",'${gss.specGoodsStorage}');
					$(this).find("[name=huohao]").attr("value",'${gss.specGoodsSerial}');
				}
			</#list>
		</#if>
	});
};

/**
 * 初始化属性
 */
var initGoodsAttr = function(){
	$("[nc_type=attr_select]").each(function(){
		$(this).find("option").each(function(){
			var attrValueId = $(this).attr("attrValueId");
			<#if attrVoList??>
				<#list attrVoList as avl>
					if(attrValueId == '${avl.attrValueId}'){
						$(this).attr("selected","selected");
					}
				</#list>
			</#if>
		});
	});
};

/**
 * 品牌初始化
 */
var initGoodsBrand = function(){
	<#if goods.brandId??>
		$("#brand").find("option").each(function(){
			$(this).val();
			if($(this).val() == '${goods.brandId}'){
				$(this).attr("selected","selected");
			}
		});
	</#if>
};

</script>

<script type="text/javascript">

	$('.gbin1-list').sortable();

</script>
</div>
<@p.footer/>
</body>
</html>