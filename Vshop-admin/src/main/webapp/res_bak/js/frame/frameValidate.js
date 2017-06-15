/**
 * 系统表单校验工具
 * systemPrefixIn - 系统访问前缀 - 用于绝对路径定义图片位置
 * 每一个input域可以有多个校验条件，语法格式采用css语法格式,例如：
 * 		validation="isNotNull;isLength:10,100;" - 必填项、10 <= 长度 <= 100
 * 		validation="isNotNull;isLength:10,null;" - 必填项、长度至少为10
 * 		validation="isNotNull;isLength:null,100;" - 必填项、长度不超过100,
 * 		validation="isNotNull;isFile:xml" - 必填项、文件选择器-必须是xml文件 */
function FrameValidate(){this.systemPrefix = webRoot;}

with(FrameValidate){
	
	/**验证某一个表单的入口方法;fm - 待验证的表单*/
	prototype.validateForm = function(fm){
		var validateResult = true;
		var eles = fm.elements;
		for(var i = 0; i < eles.length; i=i+1){
			if(!this.validateInput(eles[i])){
				validateResult = false;
			}
		}
		return validateResult;
	}
	
	/**获取某个输入框的域值*/
	prototype.getInEleValue = function(inEle){
		return $(inEle).val();
	}
	
	/**删除一个域原有的校验信息;如果该域没有校验信息 - 不做操作*/
	prototype.clearValidationInfo = function(inEle){
		$(inEle.parentNode).find("span[name='validationInfoSpan']").remove();
	}
	
	/**
	 * 生成某一个输入文本域验证结果信息
	 * inEle - 被校验的输入域
	 * validateInfo - 校验信息
	 * validTag - 校验结果 - true:正确；false:错误*/
	prototype.generateValidateInfo = function(inEle,validateInfo,validTag){
		/*获取父对象*/
		var outParent = inEle.parentNode;
		
		/*整体SPAN*/
		var outerSpan = document.createElement("SPAN");
		$(outerSpan).attr({"name":"validationInfoSpan"});
		$(outerSpan).css({"height":"24px","position":"absolute","margin-top":"7px"});
		
		/*图片*/
		var imgEle = document.createElement("i");
		$(imgEle).css({"margin-left":"3px","margin-right":"3px","font-size":"18px"});
		if(validTag){
			imgEle.className="fa fa-check";
			$(imgEle).css({"color":"#468847"});
		}else{
			imgEle.className="fa fa-exclamation";
			$(imgEle).css({"color":"#b94a48"});
		}
		outerSpan.appendChild(imgEle);
		
		/*如果文字信息不为空*/
		if(validateInfo){
			var fontEle = document.createElement("FONT");
			fontEle.style.fontSize = "11px";
			if(validTag){
				fontEle.style.color = "#468847";
			}else{
				fontEle.style.color = "#b94a48";
			}
			fontEle.innerHTML = validateInfo;
			outerSpan.appendChild(fontEle);
		}
		
		/*追加校验信息*/
		outParent.appendChild(outerSpan);
	}
	
	/**
	 * 处理校验器入参为eval函数可识别的形式*/
	prototype.handleInPara = function(curValidatorPara){
		var result = "";
		var paras = curValidatorPara.split(",");
		for(var i = 0; i < paras.length; i=i+1){
			result = result+"'"+paras[i]+"',"
		}
		result = result.substring(0,result.length-1);
		return result;
	}
	
	/**
	 * 验证某一个输入框的入口方法;	
	 * inEle - 传入的input对象*/
	prototype.validateInput = function(inEle){
		/*即使用户填写了校验的条件也可以使用"closeValidation"属性来关闭*/
		if(inEle.closeValidation != null 
				|| inEle.closeValidation != undefined){
			return true;
		}
		
		/*如果未定义校验条件 - 返回*/
		var validation = $(inEle).attr("validation");
		if(!validation || validation == ""){
			return true;
		}

		var curValidateTag = true;		//当前文本域校验是否通过
		var curValidateInfo = "";		//当前文本域校验不通过的错误信息累加
		var inEleValue = this.getInEleValue(inEle);		//传入的校验域的域值
		
		/*获取所有的校验项*/
		var validators = validation.split(";");
		for(var i = 0; i < validators.length; i=i+1){
			
			/*循环检验每个校验项*/
			var curValidatorInfo = validators[i].split(":");
			var curValidator = curValidatorInfo[0];		//校验项
			
			/*获取校验项参数*/
			var curValidatorPara = null;
			if(curValidatorInfo[1] && curValidatorInfo[1] != ""){
				curValidatorPara = curValidatorInfo[1];
			}
			
			/*拼接待执行的校验函数*/
			var validateStr = null;
			if(curValidatorPara == null){
				validateStr = "this."+curValidator+"('"+inEleValue+"')";
			}else{
				validateStr = "this."+curValidator+"('"+inEleValue+"',"+this.handleInPara(curValidatorPara)+")";
			}
			curValidateInfo = curValidateInfo+eval(validateStr);
			
			/*地方不够用 - 还是只显示一个校验规则的错误信息吧  -如果想要显示多个那么将这段逻辑注掉即可*/
			if(curValidateInfo != ""){
				this.clearValidationInfo(inEle);	//校验未通过 - 删除之前的校验信息
				this.generateValidateInfo(inEle,curValidateInfo,false);		//追加新生成的错误提示信息
				return false;
			}
		}
		
		if(curValidateInfo == ""){
			this.clearValidationInfo(inEle);	//校验通过 删除之前的校验信息
			this.generateValidateInfo(inEle,curValidateInfo,true);		//返回true 并提示正确
			return true;

		}else{
			this.clearValidationInfo(inEle);	//校验未通过 - 删除之前的校验信息
			this.generateValidateInfo(inEle,curValidateInfo,false);		//追加新生成的错误提示信息
			return false;
		}
	}
	
	/**身份证号码校验 - 通过返回"",否则返回错误信息*/
	prototype.isIdCard = function(inputStr){
		if(!inputStr){return "";}
		var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/g;
		if(reg.test(inputStr)){
			return "";
		}else{
			return "身份证非法;";
		}
	}
	
	/**手机号校验 - 通过返回"",否则返回错误信息*/
	prototype.isMobile = function(inputStr){
		if(!inputStr){return "";}
		var reg =  /^1[3|5][0-9]\d{4,8}$/g;
		if(reg.test(inputStr)){
			return "";
		}else{
			return "手机号非法;";
		}
	}
	
	/**座机号校验 - 通过返回"",否则返回错误信息*/
	prototype.isPhone = function(inputStr){
		if(!inputStr){return "";}
		var reg = /^(\d{3,4}|\d{3,4}-)?\d{7,8}$/g;
		if(reg.test(inputStr)){
			return "";
		}else{
			return "手机号码非法;";
		}
	}
	
	/**邮编校验 - 通过返回"",否则返回错误信息*/
	prototype.isPost = function(inputStr){
		if(!inputStr){return "";}
		var reg = /^[1-9][0-9]{5}$/g;
		if(reg.test(inputStr)){
			return "";
		}else{
			return "邮编格式不正确;";
		}
	}
	
	/**邮箱校验 - 通过返回"",否则返回错误信息*/
	prototype.isEmail = function(inputStr){
		if(!inputStr){return "";}
		var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/g;
		if(reg.test(inputStr)){
			return "";
		}else{
			return "邮箱非法;";
		}
	}
	
	/**ip校验 - 通过返回"",否则返回错误信息*/
	prototype.isIp = function(inputStr){
		if(!inputStr){return "";}
		var reg =  /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/g;
		if(reg.test(inputStr)){
			return "";
		}else{
			return "ip非法;";
		}
	}
	
	/**qq号校验 - 通过返回"",否则返回错误信息*/
	prototype.isQQ = function(inputStr){
		if(!inputStr){return "";}
		var reg = /^[1-9][0-9]{4,}$/g;
		if(reg.test(inputStr)){
			return "";
		}else{
			return "QQ号非法;";
		}
	}
	
	/**浮点数校验 - 通过返回"",否则返回错误信息*/
	prototype.isFloat = function(inputStr){
		if(!inputStr){return "";}
		var reg = /^[-\+]?\d+\.\d+$/g;
		if(reg.test(inputStr)){
			return "";
		}else{
			return "必须是浮点数;";
		}
	}
	
	/**必须大于0*/
	prototype.isBeyondZero = function(inputStr){
		if(!inputStr){return "";}
		if(!isNaN(parseFloat(inputStr))){
			if(parseFloat(inputStr) < 0){
				return "必须大于0";
			}else{
				return "";
			}
		}else{
			return "非法数值!"
		}
	}
	
	/**必须小于0*/
	prototype.isBelowZero = function(inputStr){
		if(!inputStr){return "";}
		if(!isNaN(parseFloat(inputStr))){
			if(parseFloat(inputStr) > 0){
				return "必须小于0";
			}else{
				return "";
			}
		}else{
			return "非法数值!"
		}
	}
	
	/**数字校验 - 通过返回"",否则返回错误信息*/
	prototype.isNumber = function(inputStr){
		if(!inputStr){return "";}
		var reg =  /^\d+$/g;
		if(reg.test(inputStr)){
			return "";
		}else{
			return "必须是数字;";
		}
	}
	
	/**整数校验 - 通过返回"",否则返回错误信息*/
	prototype.isInteger = function(inputStr){
		if(!inputStr){return "";}
		var reg = /^[-\+]?\d+$/g;
		if(reg.test(inputStr)){
			return "";
		}else{
			return "必须是整数;";
		}
	}
	
	/**汉字校验 - 通过返回"",否则返回错误信息*/
	prototype.isChinese = function(inputStr){
		if(!inputStr){return "";}
		var reg = /^[\u4E00-\u9FFF]+$/g;
		if(reg.test(inputStr)){
			return "";
		}else{
			return "必须是汉字;";
		}
	}
	
	/**字母校验 - 通过返回"",否则返回错误信息*/
	prototype.isChar = function(inputStr){
		if(!inputStr){return "";}
		var reg = /^[A-Za-z]+$/g;
		if(reg.test(inputStr)){
			return "";
		}else{
			return "必须是字母;";
		}
	}
	
	/**汉字和数字校验 - 通过返回"",否则返回错误信息*/
	prototype.isChineseAndNumber = function(inputStr){
		if(!inputStr){return "";}
		var reg = /([\u4E00-\u9FFF]|[0-9])+/g;
		if(reg.test(inputStr)){
			return "";
		}else{
			return "必须是汉字或数字;";
		}
	}
	
	/**字母和数字校验 - 通过返回"",否则返回错误信息*/
	prototype.isCharAndNumber = function(inputStr){
		if(!inputStr){return "";}
		var reg = /^[A-Za-z0-9_\-]+$/g;
		if(reg.test(inputStr)){
			return "";
		}else{
			return "必须是字母或数字或-或_;";
		}
	
	}
	
	/**汉字和字母校验 - 通过返回"",否则返回错误信息*/
	prototype.isChineseAndChar = function(inputStr){
		if(!inputStr){return "";}
		var reg =  /([\u4E00-\u9FFF]|[A-Za-z])+/g;
		if(reg.test(inputStr)){
			return "";
		}else{
			return "必须是字母或汉字;";
		}
	}
	
	/**非空约束校验 - 通过返回"",否则返回错误信息*/
	prototype.isNotNull = function(inputStr){
		if(!inputStr || inputStr.length == 0){
			return "必填项;";
		}else{
			return "";
		}
	}
	
	/**长度校验 - 通过返回"",否则返回错误信息*/
	prototype.isLength = function(inputStr,minLength,maxLength){
		if(!inputStr){return "";}
		var result = "";
		var curStrLength = inputStr.replace(/[^\x00-\xff]/g,"***").length;
		
		if(!isNaN(parseInt(minLength)) && !isNaN(parseInt(maxLength)) && parseInt(maxLength)==parseInt(minLength)){
			if(curStrLength != parseInt(minLength)){
				result = "长度只能为"+minLength+";";
			}else{
				result="";
			}
		}else{
			//校验最小长度是否合适
			if(!isNaN(parseInt(minLength)) && parseInt(minLength) > curStrLength){
				result = result+"长度要大于"+minLength+";";
			}
			//检验最大长度是否合适
			if(!isNaN(parseInt(maxLength)) && parseInt(maxLength) < curStrLength){
				result = result+"长度不超过"+maxLength+";";
			}
		}
		return result;
	}
	
	/**校验文件类型是否正确*/
	prototype.isFile = function(inputStr,fileTypeIn){
		if(!inputStr){return "";}
		var fileType = null;
		try{
			var fileType = inputStr.split("\.")[1];	
		}catch (e) {
			return "文件格式有误;";
		}
		if(fileType == fileTypeIn){
			return "";
		}else{
			return "文件格式要求是"+fileTypeIn+"文件;";
		}
	}
	
	/**获取字符的有效长度 - 字母、数字、半角标点符号长度为1*/
	prototype.getValidateLength = function(inputStr){
		var curStrLength = inputStr.replace(/[^\x00-\xff]/g,"**").length;
		return curStrLength;
	}
	
	/**不断提示字数限制 - 主要用于textarea */
	prototype.getLengthInfo = function(inEle,lengthIn){
		var result = "";
		var infoTag = true;
		var inputStr = this.getInEleValue(inEle);
		var curLength = this.getValidateLength(inputStr);
		if(parseInt(lengthIn) >= curLength){
			result = "还可以输入"+(parseInt(lengthIn) - curLength)+"个字符";
		}else{
			result = "不能再输入";
			infoTag = false;
		}
		this.clearValidationInfo(inEle);
		this.generateValidateInfo(inEle,result,infoTag);
	}
	
	/**校验文件类型是否正确*/
	prototype.isNotExist = function(inputStr,table,column,excludeName,excludeVal){
		if(!inputStr){return "";}
		if(!excludeName){excludeName = "";}
		if(!excludeVal){excludeVal = "";}
		var respTxt = frameAjax.get(webRoot+"/util/existValidate.do",
				{"entityName":table,"propertyName":column,"inputVal":inputStr,"excludeName":excludeName,"excludeVal":excludeVal});
		if(respTxt.msg == 0){
			return "";
		}else{
			return "当前数据已经存在!";
		}
	}
	
	/**校验两个域的值是否相等*/
	prototype.isTheSame = function(inputStr,anoInputId,errInfo){
		if(inputStr != $("#"+anoInputId).val()){
			return errInfo;
		}else{
			return "";
		}
	}
}

/**工具类 - 直接生成即可*/
var frameValidate = new FrameValidate();