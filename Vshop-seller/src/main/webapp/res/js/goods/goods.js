/**
 * 初始化富文本编辑器
 */
var initKinEdit = function(){
	KindEditor.ready(function(K) {
        var editor1 = K.create("textarea[name='goods_body']", {
            uploadJson : APP_BASE+'/kind/upload',
            afterCreate : function() {
                var self = this;
            },
            afterBlur: function(){this.sync();}
        });
    });
};

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
            goods_store_price : {	
				required   : true,
                number     : true,
                min        : 0.01,
                max		   : 1000000
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
                required	: '课程名称不能为空',
                minlength 	: '课程标题名称长度至少3个字符，最长50个汉字',
                maxlength 	: '课程标题名称长度至少3个字符，最长50个汉字'
            },
            goods_store_price : {
				required: '课程价格不能为空',
                number     : '课程价格只能是数字',
                min		   : '课程价格必须是0.01~1000000之间的数字',
                max		   : '课程价格必须是0.01~1000000之间的数字'
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
 * 插入课程图片 
 */
var insert_img = function(name,url){
	var obj = $('#goods_images').find('input[type="hidden"][value=""]:first');
	obj.next('img').attr('src',SITE_URL+"/res/images/loading.gif");
	var error = '';
	var i = 0;
	$('#goods_images').find('input[type="hidden"]').each(function(){
		if($(this).val() == name){
			error = '不能再重复图片';
		}
		if($(this).val() != ''){
			i++;
		}
	});
	if(i == 5){
		error = '不能再添加图片';
	}
	if(error != ''){
		alert(error);
	}else{
		$.getScript("./resource/js/store_goods.js");
		obj.val(name).next('img').attr('src',url);
	}
};

/**
 * 校验运费模板
 */
var checkTransportId = function(){
	jQuery.validator.addMethod("transportId", function(value, element) {
        if ($('#whops_buyer').attr('checked') && $('#isApplyPostage_true').attr('checked')){
	        	if ($('#transport_id').val() == '' || $('#transport_id').val() == '0'){
	        		return false;
	        	}else{
	        		return true;
	        	}
        }else{
        	return true;
        }
    });
};

/**
 * 校验平邮价格
 */
var checkPyPrice = function(){
	jQuery.validator.addMethod("pyPrice", function(value, element) {
	    if ($('#whops_buyer').attr('checked') && $('#isApplyPostage_false').attr('checked')){
	    	if ($('#py_price').val()==''){
	    		return false;
	    	}else{
	    		return this.optional(element) || /^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(value);
	    	}
	    }else{
	    	return true;
	    }
	});
};

/**
 * 校验快递价格
 */
var checkKdPrice = function(){ 
	jQuery.validator.addMethod("kdPrice", function(value, element) {
	    if ($('#whops_buyer').attr('checked') && $('#isApplyPostage_false').attr('checked')){
	    	if ($('#kd_price').val()==''){
	    		return false;
	    	}else{
	    		return this.optional(element) || /^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(value);
	    	}
	    }else{
	    	return true;
	    }
	})
};

/**
 * 校验EMS价格
 */
var checkEmsPrice = function(){
	jQuery.validator.addMethod("emsPrice", function(value, element) {
	    if ($('#whops_buyer').attr('checked') && $('#isApplyPostage_false').attr('checked')){
	        	if ($('#es_price').val() != ''){
	        		return this.optional(element) || /^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(value);
	        	}else{
	        		return true;
	        	}
	    }else{
	    	return true;
	    }
	})
};

/**
 * 点击规格颜色需要上传图片的时候
 * @param obj
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
	};
};

/**
 * 初始化颜色图片
 */
var initColorImage = function(){
	$("[name=specCheckBox]").each(function(){
		clickSpecCol(this);
	});
};

/**
 * 初始化本店分类
 */
var initStoreGoodsClass = function(){
	$("[name=storeGoodsClass]").find("option").each(function(){
		if($(this).val() == storeGoodsClassId){
			$(this).attr("selected","selected");
		}
	});
};

/**
 * 运费初始化
 */
var initGoodsStoreTransfee = function(){
	$("[name=goods_transfee_charge]").each(function(){
		if($(this).val() == goodsFreightPrice){
			$(this).attr("checked","checked");
			if($(this).val() == "1"){
				$("#whops_buyer_box").css("display","none");
			}else{
				$("#whops_buyer_box").css("display","");
					$("[name=isApplyPostage][value=1]").click();
			}
		}
	});
};

/**
 * 初始化,上架下架
 */
var initgoodsUpOrDown = function(){
	if(goodsShow == '0'){
		if(timeTo.trim() != ''){
			$("[name=goods_show][value=2]").attr("checked","checked");
			$("#time_to").attr("value",timeTo);
			$("#time_to").attr("disabled",null);
		}
	};
};

/**
 * 点击运费有谁负责的适合
 */
var initGoodsTransFeeFrom = function(){
	$("[name=goods_transfee_charge]").live("change", function(){
		if($(this).val() == "1"){
			$("#whops_buyer_box").css("display","none");
		}else{
			$("#whops_buyer_box").css("display","");
		}
	});
};

/**
 * 上架下架,以及定时上架
 */
var initGoodsStatus = function(){
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
 * 上传规格图片
 * @param myBlogImage
 * @param views
 */
var ajaxTypeImageUploads = function(myBlogImage,views){		
    $.ajaxFileUpload({
       //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
       url: APP_BASE + "/upload/imageFileUpload",
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
           
           $('.gbin1-list').sortable();
       },
       error:function(data, status, e){ //服务器响应失败时的处理函数
           $('#result').html('图片上传失败，请重试！！');
       }
      
   })
};

/**
 * 删除上传的图片
 */
var deleteUploadImage = function(){
	$("[name=deletePhoto]").live("click", function(){
		$(this).parent().remove();
	});
};

/**
 * 上传图片
 * @param imageid
 * @param ob
 * @param imgDiv
 * @param type
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
 * 初始化表单数据
 */
var initFormData = function(){
	$("#sub").click(function(){
		if($('#goods_form').valid()){
			//课程名称
			goodsName = $("[name=goods_name]").val();
			//课程副标题
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
			goodsStorePrice = $("[name=goods_store_price]").val();
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
			var args = {
					"goodsId":$("[name=goodsId]").val(),
					"goodsName":goodsName,
					"goodsSubtitle":goodsSubtitle,//课程副标题
					"gcId":gcId,
					"gcName":gcName,
					"brandId":brandId,
					"brandName":brandName,
					"typeId":typeId,
					"specOpen":specOpen,//规格开关
					"specName": specName,//规格名称
					"goodsImage": goodsImage,//课程默认封面图片
					"goodsImageMore": goodsImageMore,//课程多图
					"goodsStorePrice": goodsStorePrice,//课程学院价格
					"goodsStorePriceInterval": goodsStorePriceInterval,//区间价格
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
					"transportId": 0,//运费模板ID，不使用运费模板值为0
					/* "pyPrice": pyPrice,
					"kdPrice": kdPrice,
					"esPrice": esPrice, */
					"cityId": cityId,//课程所在地(市)
					"cityName": cityName,
					"provinceId": provinceId,//课程所在地(省)
					"provinceName": provinceName,
					"goodsTransfeeCharge": goodsTransfeeCharge,//课程运费承担方式 默认 0为买家承担 1为卖家承担
					"StoreClassId": StoreClassId,//学院自定义分类id
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
		};
	});
};

/**
 * 
 */
var ok = function(goodsShow){
	//如果用户选择上架,则跳到出售中的课程
	if(goodsShow == '1');
	window.location.href = APP_BASE + '/pro/sale';
	//如果用户选择下架,则跳到仓库中的课程
	if(goodsShow == '0')
	window.location.href = APP_BASE + '/pro/store';
};

/**
 * 买家承担运费&运费模板
 */
var initFreightStore = function(){
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
 * 当选中后,或者不选时,规格值名可编辑,和不可编辑
 * @param obj
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
}

/**
 * 
 * @param arr
 * @returns {Array}
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
 * @param arr
 * @param arr2
 * @returns {Array}
 */
var dosku = function (arr,arr2){
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
	});
	//将总库存放入课程库存
	$("[name=goods_storage]").attr("value",totalSku);
};

/**
 * 价格的计算方法
 */
var caculatePrice = function (){
	var minPrice = 0;
	$("[name=price]").each(function(x,y){
		var price = $(this).val();
		if(!isNaN(price) && price.trim() != ""){
			if(x == 0){
				minPrice = price;
			}else{
				if(minPrice > price){
					minPrice = price;
				}
			}
		}
	});
	$("[name=goods_store_price]").attr("value",minPrice);
};

/**
 * 
 * @param currentvalue
 * @returns {Boolean}
 */
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
 * 实现图片慢慢浮现出来的效果
 */
var initImageFade = function(){
	$("img").load(function () {
	    //图片默认隐藏  
	    $(this).hide();
	    //使用fadeIn特效  
	    $(this).fadeIn("5000");
	});
	// 异步加载图片，实现逐屏加载图片
	$(".scrollLoading").scrollLoading(); 
};

/**
 * 当选中规格后需要上传图片的时候
 */
var specCheckBoxFun = function(){
	$("[name=specCheckBox]").live("click",function(){
		//specValueChangeEdit(this);
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

var specGroupFun = function(){
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
	$("[nc_type=spec_table]").empty();
	var tag = true;
	//准备数组,放入
	var SpecArray = new Array($("[nctype=spec_group_dl]").length);
	//循环每个规格,是否每个规格至少选中一个元素
	$("[nctype=spec_group_dl]").each(function(i,val){
		//规格id
		var spId = $(this).attr("spId");
		//规格名称
		var spName = $(this).attr("spName");
		if($(this).find(":checked").length == 0){
			tag = false;
			$("[nc_type=spec_dl]").css("display","none");
			$("[name=goods_storage]").attr("value","0");
			$("[name=goods_storage]").attr("disabled",false);
			$("[name=goods_store_price]").attr("disabled",false);
			return;
		}else{
			//准备数组,放入
			var SpecValueArray = new Array($(this).find(":checked").length);
			//循环,判断获取每个规格中选中的值
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
	if(tag){
		$("[nc_type=spec_dl]").css("display","");
		//获得控制库存的数组
		var arr = getSkuTr(SpecArray);
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
			}
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
	}
};

/**
 * 
 * @param e
 */
var searchFocus = function (e){
	if(e.value == searchTxt){
		e.value='';
		$('#keyword').css("color","");
	}
};

/**
 * 
 * @param e
 */
var searchBlur = function(e){
	if(e.value == ''){
		e.value=searchTxt;
		$('#keyword').css("color","#999999");
	}
};
/**
 * 
 * @returns {Boolean}
 */
var searchInput = function() {
	if($('#keyword').val()==searchTxt)
		$('#keyword').attr("value","");
	return true;
};


/**
 * 首页左侧分类菜单
 */
//var initMenuLeft = function(){
//	$(".category ul.menu").find("li").each(function() {
//		$(this).hover(
//			function() {
//			    var cat_id = $(this).attr("cat_id");
//				var menu = $(this).find("div[cat_menu_id='"+cat_id+"']");
//				menu.show();
//				$(this).addClass("hover");
//				if(menu.attr("hover")>0) return;
//				menu.masonry({itemSelector: 'dl'});
//				var menu_height = menu.height();
//				if (menu_height < 60) menu.height(80);
//				menu_height = menu.height();
//				var li_top = $(this).position().top;
//				if ((li_top > 60) && (menu_height >= li_top)) $(menu).css("top",-li_top+50);
//				if ((li_top > 150) && (menu_height >= li_top)) $(menu).css("top",-li_top+90);
//				if ((li_top > 240) && (li_top > menu_height)) $(menu).css("top",menu_height-li_top+90);
//				if (li_top > 300 && (li_top > menu_height)) $(menu).css("top",60-menu_height);
//				if ((li_top > 40) && (menu_height <= 120)) $(menu).css("top",-5);
//				menu.attr("hover",1);
//			},
//			function() {
//				$(this).removeClass("hover");
//			    var cat_id = $(this).attr("cat_id");
//				$(this).find("div[cat_menu_id='"+cat_id+"']").hide();
//			}
//		);
//	});
//};
//$(".head-user-menu dl").hover(function() {
//	$(this).addClass("hover");
//},
//function() {
//	$(this).removeClass("hover");
//});
//$('.head-user-menu .my-mall').mouseover(function(){// 最近浏览的课程
//	load_history_information();
//	$(this).unbind('mouseover');
//});
//$('.head-user-menu .my-cart').mouseover(function(){// 运行加载购物车
//	load_cart_information();
//	$(this).unbind('mouseover');
//});





