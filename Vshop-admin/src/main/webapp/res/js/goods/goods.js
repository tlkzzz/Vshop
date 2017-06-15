/**
 * 初始化富文本编辑器
 */
var initKindEdit = function(){
	KindEditor.ready(function(K) {
        var editor1 = K.create("textarea[name='goods_body']", {
            uploadJson : APP_BASE+'/kind/upload',
            afterCreate : function() {
                var self = this;
            },
            afterBlur: function(){this.sync();}
        });
    });
}

/**
 * 课程表单校验
 */
var goodsFormValid = function(){
	 jQuery.validator.addMethod("priceMarket",function(value,element){
	    	var gsp = parseFloat($("[name=goods_store_price]").val());
	    	var gmp = parseFloat($("[name=goods_market_price]").val());
	    	if(gmp<gsp){
	    		return false;
	    	}else{
	    		return true;
	    	}
	    });
	    jQuery.validator.addMethod("priceCost",function(value,element){
	    	var gsp = parseFloat($("[name=goods_store_price]").val());
	    	var gcp = parseFloat($("[name=goods_cost_price]").val());
	    	if(gcp>gsp){
	    		return false;
	    	}else{
	    		return true;
	    	}
	    });
	$('#goods_form').validate({
        errorPlacement: function(error, element){
            $(element).next('.field_notice').hide();
            $(element).after(error);
        },
        rules : {
        	brand_id : {
                required	: true
            },
            supplier_id : {
                required	: true
            },
//        	store_id : {
//                required	: true
//            },
            goods_name : {
                required	: true,
                minlength 	: 3,
                maxlength	: 50
            },
            goods_store_price : {	
				required   : true,
                number     : true,
                min        : 0.01,
                max		   : 1000000,
            },
            goods_market_price : {	
				required   : true,
                number     : true,
                max        : 1000000,
                min		   : 0.01,
                priceMarket  : true
            },
            goods_cost_price : {	
                required   : true,            
            	number     : true,
                min        : 0.01,
                max		   : 1000000,
                priceCost  : true
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
            goods_store_price : {	
				required   : true,
                number     : true,
                min        : 0.01,
                max		   : 1000000
            }
        },
        messages : {
        	brand_id  : {
                required	: '请选择品牌'
            },
            supplier_id  : {
                required	: '请选择供应商'
            },
//        	store_id  : {
//                required	: '请选择学院'
//            },
            goods_name  : {
                required	: '课程名称不能为空',
                minlength 	: '课程标题名称长度至少3个字符，最长50个汉字',
                maxlength 	: '课程标题名称长度至少3个字符，最长50个汉字'
            },
            goods_store_price : {
				required   : '课程价格不能为空',
                number     : '课程价格只能是数字',
                min		   : '课程价格必须是0.01~1000000之间的数字',
                max		   : '课程价格必须是0.01~1000000之间的数字'
            },
            goods_market_price : {
            	required   : '市场价格不能为空',
                number     : '市场价格只能是数字',
                min		   : '市场价格必须是0.01~1000000之间的数字',
                max		   : '市场价格必须是0.01~1000000之间的数字',
                priceMarket  : '市场价格不能小于课程价格'
            },
            goods_cost_price : {
            	required   : '成本价格不能为空',
                number     : '成本价格只能是数字',
                min		   : '成本价格必须是0.01~1000000之间的数字',
                max		   : '成本价格必须是0.01~1000000之间的数字',
                priceCost  : '成本价格不能大于课程价格'
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
				required: '课程库存不能为空',
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
            transport_id:{
            	transportId:'请选择要使用的运费模板'
            },
            goods_store_price : {
				required: '佣金比例不能为空',
                number     : '佣金比例只能是数字',
                min		   : '佣金比例必须是0.01~1000000之间的数字',
                max		   : '佣金比例必须是0.01~1000000之间的数字'
            }
        }
    });
};

/**
 * 获取规格的tr行数
 */
var getSkuTr = function(arr){
	var a = 1;
	for(var r = 0; r < arr.length; r++){
		a *= arr[r].length;
	}
	var newArray = new Array(a);
	newArray = arr[0];
	for(var m=1;m<arr.length;m++){ 
		var arr2 =arr[m]; 
		newArray = dosku(newArray,arr2)
	}
	return newArray;
};

/**
 * 
 */
var dosku = function(arr,arr2){
	var a = arr.length;
	var b = arr2.length;
	var newArr = new Array(a*b);
	var q = 0;
	for(var i=0;i<arr.length;i++){
		for(var j=0;j<arr2.length;j++){ 
			var spec = {spId:arr[i].spId + ',' + arr2[j].spId, 
						spName:arr[i].spName + ',' + arr2[j].spName, 
						specValueId:arr[i].specValueId + ',' + arr2[j].specValueId, 
						specValueName:arr[i].specValueName + ',' + arr2[j].specValueName
		               };
			newArr[q] = spec;
			q++;
		}
	} 
	return newArr;
};

/**
 * 库存的计算方法
 */
var getTotalSku = function(){
	var totalSku = 0;
	//获得总库存
	$("[name=sku]").each(function(){
		var sku = $(this).val();
		totalSku += parseInt(sku);
	})
	//将总库存放入课程库存
	$("[name=goods_storage]").attr("value",totalSku);
};

/**
 * 价格的计算方法
 */
var caculatePrice = function(){
	var minPrice = parseInt("0");
	$("[name=price]").each(function(x,y){
		var price = parseInt($(this).val());
		if(x == 0){
			minPrice = price;
		}else{
			if(minPrice > price){
				minPrice = price;
			}
		}
	});
	$("[name=goods_store_price]").attr("value",minPrice);
};

/**
 * 验证是否选择运费模板
 */
jQuery.validator.addMethod("transportId", function(value, element) {
    if ($('#whops_buyer').attr('checked') && $('#isApplyPostage_true').attr('checked')){
        	if ($('#transport_id').val() == '' || $('#transport_id').val() == '0'){
        		return false;
        	}else{
        		return true;
        	}
    }else{
    	return true;
    }}
); 

var checkselected = function(currentvalue){
	var result = true;
	jQuery.each($(".sgcategory"),function(){
		if(currentvalue!=0 && currentvalue == $(this).val()){
			result = false;
		}
	});
	return result;
};

/**
 * 当选中后,或者不选时,规格值名可编辑,和不可编辑
 */
var specValueChangeEdit = function(obj){
	//得到标签对象
	var $txt = $(obj).parent().parent().children().last();
	//当选中的时候
	if($(obj).attr("checked") == "checked"){
		//取得原来的名字
		var oldSpecValueName = $txt.html();
		//设置里面是一个input text标签
		$txt.html("<input type='text' name='specValueEdit' maxlength='20' value="+ oldSpecValueName + ">");
	}else{
		//当不选中的时候
		var oldSpecValueName = $txt.children().val();
		$txt.html(oldSpecValueName);
	}
};

/**
 * 当选中规格后需要上传图片的时候
 */
var afterSelectSpec = function(){
	$("[name=specCheckBox]").live("click",function(){
		var tag = true;
		if($(this).attr("checked") != "checked"){
			tag = false;
		}
		var groupId = $(this).attr("groupId");
		var i = 0;
		$("[name=specCheckBox]").each(function(){
			if(groupId == $(this).attr("groupId")){
				if($(this).attr("checked") == "checked"){
					i++;
				}
			}
		});
		if($(this).attr("spFormat")=="1"){
			var specValueId = $(this).attr("value");
			$("[nctype=img_table]").each(function(){
				var groupValue = $(this).attr("groupValue");
				if(groupValue == groupId){
					if(i == 0){
						$(this).find("[nctype=file_tr]").each(function(){
							$(this).css("display","none");
							$(this).find("[name=customSpecImage]").attr("imageState","0");
						});
						$(this).css("display","none");
					}else{
						$(this).css("display","");
						$(this).find("[nctype=file_tr]").each(function(){
							if($(this).attr("value") == specValueId){
								if(tag){
									$(this).css("display","");
									$(this).find("[name=customSpecImage]").attr("imageState","1");
								}else{
									$(this).css("display","none");
									$(this).find("[name=customSpecImage]").attr("imageState","0");
								}
							}
						});
					}
				}
			});
		}
	});
};

/**
 * 买家承担运费&运费模板
 */
var freightModel = function(){
	$("[name=isApplyPostage]").live("click",function(){
		if($(this).attr("value") == "1"){
			$("[nc_type=transport]").attr("value","");
			$("[nc_type=transport]").attr("disabled","disabled");
			$("#Postage").css("display","");
		}else{
			$("#Postage").css("display","none");
			$("[nc_type=transport]").attr("disabled",null);
		}
	});
};

/**
 * 上传规格图片
 */
var ajaxFileUploads = function(imageid,ob,imgDiv,type) {
	var $obj = $(ob);
    $.ajaxFileUpload({
        url: APP_BASE + '/upload/goodsImage',
        data: '',
        secureuri: false,
        fileElementId: imageid,
        dataType: 'json',
        success: function (data, status) {
        	if (data.success) {
	        	if(type == "spec"){
	        		$("[name=customSpecImage]").each(function(){
	            		if($(this).attr("nctype") == imgDiv){
	            			$(this).attr("src",data.imageServer+"/"+data.result);
	            			$(this).attr("imageSrc",data.result);
	            			$(this).attr("imageName",data.filename);
	            		}
	            	});
	        	}
	        	if(type == "goods"){
	        		$("[name=goodsImage]").each(function(){
	            		if($(this).attr("nctype") == imgDiv){
	            			$(this).attr("src",data.imageServer+"/"+data.result);
	            			$(this).attr("imageSrc",data.result);
	            			$(this).attr("imageName",data.filename);
	            		}
	            	});
	        	}
        	}
        },
        error: function (data, status, e) { //服务器响应失败时的处理函数
            if (type == 0) {
                $('#result_logo').html('图片上传失败，请重试！！');
            } else {
                $('#result_banner').html('图片上传失败，请重试！！');
            }
        }
    });
};

/**
 * 删除上传的图片
 */
var deleteImage = function(){
	$("[name=deletePhoto]").live("click", function(){
		$(this).parent().remove();
	});
};

/**
 * 上传规格图片
 */
var ajaxTypeImageUploads = function(myBlogImage,views){		
	$.ajaxFileUpload({
	    //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
	    url:APP_BASE + "/upload/imageFileUpload",
	    secureuri:false,                       //是否启用安全提交,默认为false
	    fileElementId:myBlogImage,           //文件选择框的id属性
	    dataType:'json',                       //服务器返回的格式,可以是json或xml等
	    success:function(data, status){        //服务器响应成功时的处理函数
	        if(data.success){
	        	var count = parseInt(data.listimgSize);
	        	var strs01 = data.photoNewName.split(",");
	        	var strs02 = data.photoSrc.split(",");
	        	var imgPath = data.imgPath;
	        	var imageUrl = "";
	        	if(views != "photo"){
	        		var strs03 = data.oldName.split(",");
	        	}
	        	for(var i = 0; i < count; i++){	         
	        		var photoSrc01 = imgPath + "/" + strs01[i];
	        		var photoServcerSrc = imgBasePath + imgPath + "/" + strs01[i];
	       			$("#photoView01").append("<li style='height:120px;display:inline'><img class='img' style='width:100px;height:100px' src='"+photoServcerSrc+"'/><a href='javascript:void(0)' imageSrc='"+photoSrc01+"' name='deletePhoto'> 删除</a></li>");
	        	}
	        }
	    },
	    error:function(data, status, e){ //服务器响应失败时的处理函数
	        $('#result').html('图片上传失败，请重试！！');
	    }
	});
};

/**
 * 上架下架,以及定时上架
 */
var onOrOff = function(){
	$("[name=goods_show]").live("click", function(){
		if($(this).val() == "2"){
			$("#time_to").attr("disabled",null);
		}else{
			$("#time_to").attr("disabled","disabled");
		}
		$("#time_to").attr("value","");
	});
};

/**
 * 运费付责方
 */
var payFreight = function(){
	$("[name=goods_transfee_charge]").live("change", function(){
		if($(this).val() == "1"){
			$("#whops_buyer_box").css("display","none");
		}else{
			$("#whops_buyer_box").css("display","");
		}
	});
};

/**
 * 点击规格颜色需要上传图片的时候
 */
var clickSpecCol = function(obj){
	var tag = true;
	if($(obj).attr("checked") != "checked"){
		tag = false;
	}
	var groupId = $(obj).attr("groupId");
	var i = 0;
	$("[name=specCheckBox]").each(function(){
		if(groupId == $(this).attr("groupId")){
			if($(this).attr("checked") == "checked"){
				i++;
			}
		}
	});
	if($(obj).attr("spFormat")=="1"){
		var specValueId = $(obj).attr("value");
		$("[nctype=img_table]").each(function(){
			var groupValue = $(this).attr("groupValue");
			if(groupValue == groupId){
				if(i == 0){
					$(this).find("[nctype=file_tr]").each(function(){
						$(this).css("display","none");
						$(this).find("[name=customSpecImage]").attr("imageState","0");
					});
					$(this).css("display","none");
				}else{
					$(this).css("display","");
					$(this).find("[nctype=file_tr]").each(function(){
						if($(this).attr("value") == specValueId){
							if(tag){
								$(this).css("display","");
								$(this).find("[name=customSpecImage]").attr("imageState","1");
							}else{
								$(this).css("display","none");
								$(this).find("[name=customSpecImage]").attr("imageState","0");
							}
						}
					});
				}
			}
		});
	}
};



/**
 * 获取规格名称
 */
var specNameFun = function(){
	var count = $("[nctype=spec_group_dl]").length;
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
};

/**
 * 课程属性
 */
var goodsAttrFun = function(){
	var attribute = "";
	var attrTag = true;
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
			attribute += "{";
		}
		$("[nc_type=attr_select]").each(function(){
			var attrId = $(this).attr("attrId");
			var attrName = $(this).attr("attrName");
			var $choice = $(this).find(":selected");
			var attrValueName = $choice.attr("value");
			var attrValueId = $choice.attr("attrValueId");
			attribute += "\"" + attrId + "\":\"{\\\"name\\\":\\\""+ attrName +"\\\",\\\"" + attrValueId + "\\\":\\\"" + attrValueName + "\\\"}\","
		});
		if(goodsAttrCount > 0){
			attribute = attribute.substring(0, attribute.length-1);
			attribute += "}";
		}
	}
	return attribute;
}

/**
 * 获取课程规格
 */
var getGoodsSpec = function(){
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
	return goodsSpec;
};

/**
 * 获取颜色自定义图片
 */
var getGoodsImg = function(){
	if($("[name=skuDo]").length > 0){
		var goodsColImg = "{";
		var imageSrcCount = 0;
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
	return goodsColImg;
};

/**
 * 判断是否有价格区间
 */
var getGoodsStorePriceInterval = function(){
	if($("[name=skuDo]").length > 0){
		//获得所有的价格
		var $allPrice = $("[name=price]")
		//如果只有一个的价格,则价格区间为:x-x
		if($("[name=skuDo]").length == 1){
			goodsStorePriceInterval = $allPrice.val() + "-" + $allPrice.val();
		}else{
			var arrPriceLength = $("[name=skuDo]").length;
			//循环放入数组
			var arrPrice = new Array(arrPriceLength);
			$allPrice.each(function(j,val){
				arrPrice[j] = parseFloat($(this).val());
			});
			var maxPrice = Math.max.apply(null,arrPrice);
			var minPrice = Math.min.apply(null,arrPrice);
			goodsStorePriceInterval = minPrice + "-" + maxPrice;
		}
	}
};

/**
 * 定义goodsSpec的实体类json数据
 */
var getGoodsSpecJson = function(){
	var goodsSpecJson = "";
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
		//当前规格下的课程价格
		var specGoodsPrice = $(this).find("[name=price]").val();
		//当前规格下的规格课程编号
		var specGoodsSerial = $(this).find("[name=huohao]").val();
		//当前规格下的课程sku
		var specGoodsStorage = $(this).find("[name=sku]").val();
		//当前规格下的课程规格序列化
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
	return goodsSpecJson;
};

/**
 * 初始化课程提交的字段名
 */
var initGoodsNameAllAttr = function(){
	//课程名称
	goodsName = $("[name=goods_name]").val();
	//课程副标题
	goodsSubtitle = $("[name=goods_subtitle]").val();
	//分类id
	gcId = $("#cate_id").val();
	//分类名称
	gcName = $("#cate_name").val();
	//学院id
//	storeId = $("#store").val();
	//品牌id
	brandId = $("#brand").val();
	//供应商id
	supplierId = $("#supplier").val();
	//类型id
	typeId = $("[name=type_id]").val();
	//规格名称
	specName = specNameFun();
	//规格的数量
	specCount = $("[nctype=spec_group_dl]").length;
	//课程默认封面图片
	goodsImage = $("[name=deletePhoto]").attr("imageSrc");
	//课程多图
	$("[name=deletePhoto]").each(function(){
		goodsImageMore += $(this).attr("imageSrc") + ",";
	});
	//课程店铺价格
	goodsStorePrice = $("[name=goods_store_price]").val();
	goodsMarketPrice = $("[name=goods_market_price]").val();
	goodsCostPrice = $("[name=goods_cost_price]").val();
	//课程货号
	goodsSerial = $("[name=goods_serial]").val();
	//课程上架1上架0下架
	goodsShow = $("[name=goods_show]:checked").attr("value");
	if(goodsShow == "2"){
		goodsShow = "0";
		var timeTo = $("#time_to").val();
		if(timeTo != ''){
			prepareUp = timeTo;
		}
	}
	//课程推荐
	goodsCommend = $("[name=goods_commend]:checked").attr("value");
	//课程关键字
	goodsKeywords = $("[name=seo_keywords]").val();
	//课程描述
	goodsDescription = $("[name=seo_description]").val();
	//课程详细内容
	goodsBody = $("#goods_body").val();
	//课程属性 
	goodsAttr = goodsAttrFun();
	//首先判断规格的数量
	goodsSpec = getGoodsSpec();
	//颜色自定义图片
	goodsColImg = getGoodsImg();
	//课程类型,1为全新、2为二手
	goodsForm = $("[name=goods_form]:checked").attr("value");
	//课程所在地(市)
	cityId = $("#city").val();
	//课程所在地(市)名称
	cityName = $("#city").find("option:selected").html();
	//课程所在地(省)
	provinceId = $("#area").val();
	//课程所在地(省)名称
	provinceName = $("#area").find("option:selected").html();
	//是否发码
	isCode = $("[name=isCode]:checked").attr("value");
	//课程运费承担方式 默认 0为买家承担 1为卖家承担
//	var goodsTransfeeCharge = "";
	goodsTransfeeCharge = $("[name=goods_transfee_charge]:checked").val();
//	var transportId = "0";
	//如果是买家承担运费
	if(goodsTransfeeCharge == "0"){
		if($("[name=isApplyPostage]:checked").val() == "1"){
			//运费模板ID，不使用运费模板值为0
			transportId = $("#transportSelected").val();
		}
	}
};

/**
 * 保存课程
 */
var subGoodsForm = function(){
	$("#sub").click(function(){
		if($('#goods_form').valid()){
			initGoodsNameAllAttr();
			//店铺自定义分类id
			/* var StoreClassId = "";
			StoreClassId = $("[name=storeGoodsClass]").val(); */
			//设置课程的价格区间
			//判断是否有选择规格,如果没有规格则没有价格区间
			goodsStorePriceInterval = getGoodsStorePriceInterval();
			//定义goodsSpec的实体类json数据
			var goodsSpecJson = getGoodsSpecJson();
			var args = {
				"goodsId":$("[name=goodsId]").val(),
				"goodsName":goodsName,
				"goodsSubtitle":goodsSubtitle,//课程副标题
				"gcId":gcId,
				"gcName":gcName,
//				"storeId":storeId,
				"brandId":brandId,
				"supplierId":supplierId,
				"typeId":typeId,
				"specOpen":specOpen,//规格开关
				"specName": specName,//规格名称
				"goodsImage": goodsImage,//课程默认封面图片
				"goodsImageMore": goodsImageMore,//课程多图
				"goodsStorePrice": goodsStorePrice,//课程店铺价格
				"goodsStorePriceInterval": goodsStorePriceInterval,//区间价格
				"goodsMarketPrice": goodsMarketPrice,//课程店铺价格
				"goodsCostPrice": goodsCostPrice,//课程店铺价格
				"goodsSerial": goodsSerial,//课程货号
				"goodsShow": goodsShow,//课程上架1上架0下架
				"prepareUp": prepareUp,//上架时间
				"goodsCommend": goodsCommend,//课程推荐
				"goodsKeywords": goodsKeywords,//课程关键字
				"goodsDescription": goodsDescription,//课程描述 
				"goodsBody": goodsBody,//课程详细内容
				"goodsAttr": goodsAttr,// 课程属性 
				"goodsSpec": goodsSpec,//课程规格
				"goodsColImg": goodsColImg,//颜色自定义图片
				"goodsForm": goodsForm,//课程类型,1为全新、2为二手
				"transportId": transportId,//运费模板ID，不使用运费模板值为0
				"cityId": cityId,//课程所在地(市)
				"cityName": cityName,
				"provinceId": provinceId,//课程所在地(省)
				"provinceName": provinceName,
				"isCode":isCode,//是否发码
				"goodsCommissionRate":goodsCommissionRate,//付款比例
				"goodsTransfeeCharge": goodsTransfeeCharge,//课程运费承担方式 默认 0为买家承担 1为卖家承担
				//"StoreClassId": StoreClassId,//店铺自定义分类id
				"goodsSpecJson": goodsSpecJson,//goodsSpec的实体类json串
				"goodsTotalStorage": $("[name=goods_storage]").val()//总库存
			};
			var url = APP_BASE + "/platform/updatePro";
	        //加载进度条
	        layer.load(2, {
               shade: [0.2,'#999999'] //0.1透明度的白色背景
	        });
			$.post(url, args, function(data){
				//保存成功
				if(data.message == "1"){
					layer.msg("发布成功" , {icon:1});
					setTimeout("ok("+goodsShow+")",1500); 
				}else{
					//失败
					layer.msg("保存失败" , {icon:2});
				}
			});
		}else{
			//返回信息错误的地方
			$("html,body").animate({scrollTop:$(".error:visible").offset().top},1000);
		}
	});
};

var ok = function(goodsShow){
	window.location.href= APP_BASE + '/platform/sellList';
}

/**
 * 初始化库存配置行
 */
var initStockTrLen = function(){
	var skuTrArr = new Array($("[name=skuDo]").length);
	$("[name=skuDo]").each(function(y,x){
		var oldSpec = {
			id:$(this).attr("specValueId"),
			price:$(this).find("[name=price]").val(),
			sku:$(this).find("[name=sku]").val(),
			huohao:$(this).find("[name=huohao]").val()
		};
		skuTrArr[y] = oldSpec;
	});
	return skuTrArr;
};

/**
 * 初始化规格
 */
var initEachSpec = function(){
	var SpecArray = new Array($("[nctype=spec_group_dl]").length);
	// 循环每个规格,判断是否有选择规格
	$("[nctype=spec_group_dl]").each(function(i,val){
		// 规格id
		var spId = $(this).attr("spId");
		// 规格名称
		var spName = $(this).attr("spName");
		// 如果没有选择任何规格隐藏库存配置项，课程库存置为0且允许输入值,课程价格允许输入值
		if($(this).find(":checked").length == 0){
			tag = false;
			$("[nc_type=spec_dl]").css("display","none");
			$("[name=goods_storage]").attr("value","0");
			$("[name=goods_storage]").attr("disabled",false);
			$("[name=goods_store_price]").attr("disabled",false);
			return;
		}else{ // 有选择规格
			// 定义规格值数组
			var SpecValueArray = new Array($(this).find(":checked").length);
			// 循环,判断获取每个规格中选中的值
			$(this).find(":checked").each(function(j,specVal){
				//创建规格对象
				var spec = {
					spId:spId, 
					spName:spName, 
					specValueId:$(this).val(), 
					specValueName:$(this).attr("spValueName")
				};
				//将规格值放入数组中
				SpecValueArray[j] = spec;
			});
			//将规格值数组放入规格数组中
			SpecArray[i] = SpecValueArray;
		}
	});
	return SpecArray;
}

/**
 * 有规格时执行的函数
 */
var specGroupFun = function(){
	// 选择规格时初始化库存配置(价格、库存、课程货号)已存在的值
	var skuTrArr = initStockTrLen();
	// 移除已存在的库存配置里的内容，重新计算好后再添加上
	$("[nc_type=spec_table]").empty();
	var tag = true;
	// 定义规格数组变量
	var SpecArray = initEachSpec();
	// 有选择的规格进行的操作
	if(tag){
		initGoodsStockValue(skuTrArr, SpecArray);
	}
};

/**
 * 库存配置
 */
var initGoodsStockValue = function(skuTrArr, SpecArray){
	// 显示库存配置
	$("[nc_type=spec_dl]").css("display","");
	// 获得控制库存的数组
	var arr = getSkuTr(SpecArray);
	// 
	for(var s = 0; s < arr.length; s++){
		var args = arr[s].specValueName;
		var valueNames = args.split(",");
		var str = "";
		for(var l = 0; l < valueNames.length; l++){
			str += '<td>'+valueNames[l]+'</td>';
		}
		//判断是否已经存在tr
		var vaId = arr[s].specValueId;
		var flg = true;
		for(var e = 0; e < skuTrArr.length; e++){
			if(skuTrArr[e].id == vaId){
				flg = false;
				var tr = '<tr name="skuDo" spId='+arr[s].spId+' spName='+arr[s].spName+' specValueName='+arr[s].specValueName+' specValueId='+arr[s].specValueId+' value='+arr[s].specValueId+'>'+
				str+
    			'<td><input class="text" name="price" type="text" value="'+skuTrArr[e].price+'"/></td>'+
    			'<td><input class="text" name="sku" type="text" value="'+skuTrArr[e].sku+'"/></td>'+
    			'<td><input class="text" name="huohao" type="text" value="'+skuTrArr[e].huohao+'"/></td>'+
				'</tr>';
			}
		}
		if(flg){
			var tr = '<tr name="skuDo" spId='+arr[s].spId+' spName='+arr[s].spName+' specValueName='+arr[s].specValueName+' specValueId="'+arr[s].specValueId+'" value="'+arr[s].specValueId+'">'+
			str+
			'<td><input class="text" name="price" type="text"/></td>'+
			'<td><input class="text" name="sku" type="text" value="0"/></td>'+
			'<td><input class="text" name="huohao" type="text"/></td>'+
			'</tr>';
		}
		
    	$("[nc_type=spec_table]").append(tr);
	};
	
	//判断库存
	if($("[nc_type=spec_table]").length > 0){
		//课程库存不能手动修改
		$("[name=goods_storage]").attr("disabled",true);
		//课程价格不能手动修改
		$("[name=goods_store_price]").attr("disabled",true);
	}else{
		$("[name=goods_storage]").attr("disabled",false);
		$("[name=goods_store_price]").attr("disabled",false);
	}
};


/**
 * 初始化上下架状态
 */
var initGoodsStatus = function(){
	if(goodsShow == '0'){
		if(timeTo.trim() != ''){
			$("[name=goods_show][value=2]").attr("checked","checked");
			$("#time_to").attr("value",timeTo);
			$("#time_to").attr("disabled",null);
		}
	};
};


