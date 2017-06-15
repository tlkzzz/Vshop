/**
 * 
 * 系统ajax访问帮助js对象
 * 	systemPrefixIn - 系统访问前缀
 * 	textStatus - timeout|error|notmodified|success|parseerror
 * 	type - xml|html|script|json|text 
 * 	data - 访问参数，可以使string类型(eg:"name=张三&age=24"),或者是key-value形式(eg:{name:"张三",age:"24"});
 * 	ajax访问成功后回调函数	一般入参 function(responseText,textStatus,xmlHtpReq){}
 * 
 * * */

function FrameAjax(systemPrefixIn){}

with(FrameAjax){
	
	/**处理ajax访问url - 追加一个时间戳以保证不会使用浏览器缓存*/
	prototype.handleURL = function(url){
		var reg = /(\?)/g;
		var tmstp = "tmstp="+new Date().getTime()+""; 
		var result = "";
		if(reg.test(url)){
			result = url+"&"+tmstp;
		}else{
			result = url+"?"+tmstp;
		}
		return result;
	}
	
	/**重新处理ajax访问的data的参数(string类型的)将里面的汉字一类的重新utf-8编码*/
	prototype.handleData = function(data){
		var result = "";
		var datas = data.split("&");
		for(var i = 0; i < datas.length; i=i+1){
			var datais = datas[i].split("=");
			var key = datais[0];
			var value = datais[1];
			result = result+key+"="+encodeURIComponent(value)+"&";
		}
		result = result.substring(0,result.length-1);
		return result;
	}
	
	
	/**异步ajax.get访问 - 回调函数处理后台返回的json格式字符串*/
	prototype.asyGet = function(url,data,callback){
		//校验和处理入参
		if(!url || !callback){
			frameControl.lhDgWrnInfo("$.get入参不合法,无法访问!");
			return;
		}
		
		//处理url消除缓存
		url = this.handleURL(url);
		if(typeof data == "string"){
			data = this.handleData(data);
		}
		
		//ajax访问
		try{
			$.get(url,data,callback);
		}catch(e){
			frameControl.lhDgWrnInfo("$.get访问出错!");
		}
	}
	
	/**同步的ajax.get请求 - 返回的是一个json对象*/
	prototype.get = function(url,paras){
		
		//处理url和参数
		if(paras && (typeof paras == "string")){
			paras = this.handleData(data);
		}
		if(url.indexOf(".do")!=-1){
			url = url.replace(".do",".jsonx");
		}else{
			url = url + ".jsonx";
		}
		url += "?temp="+new Date().getTime();
		
		//ajax访问
		var reValue = $.ajax({
			            async: false,
			            url: url,
			            data: paras,
			            type: "GET",
			            error:function(){
							frameControl.lhDgWrnInfo("操作失败!");
			            }
			        });
		var text = reValue.responseText;
        if (text != '') {
            return eval('(' + text + ')');
        }
        return eval('');
	}
	
	/**异步的ajax.post请求 - 回调函数处理后台返回的json格式的字符串*/
	prototype.asyPost = function(url,data,callback){
		
		//访问前准备 - 校验和处理入参
		if(!url || !callback){
			frameControl.lhDgWrnInfo("$.post入参不合法,无法访问!");
			return;
		}
		url = this.handleURL(url);
		if(typeof data == "string"){
			data = this.handleData(data);
		}
		
		//开始访问
		try{
			$.post(url,data,callback);
		}catch(e){
			frameControl.lhDgWrnInfo("$.get访问出错!");
		}
	}
	
	/**同步的ajax.post请求 - 返回一个jsond对象*/
	prototype.post = function(url,data){
		if(typeof data == "string"){
			data = this.handleData(data);
		}
		if(url.indexOf(".do")!=-1){
			url = url.replace(".do",".jsonx");
		}else{
			url = url + ".jsonx";
		}
		url += "?temp="+new Date().getTime();
		var reValue = $.ajax({
			            async: false,
			            url: url,
			            data: data,
			            type: "POST",
			            error:function(){
						frameControl.lhDgWrnInfo("操作失败!");
			            }
			        });
		var text = reValue.responseText;
        if (text != '') {
            return eval('(' + text + ')');
        }
        return eval('');
	}
	
	
	/**将一个json对象转换为一个json字符串*/
	prototype.jsonObjToJsonStr = function(JsonObj){
		var jsonStr = $.toJSON(JsonObj);
		return jsonStr;
	}
	
	/**将一个json字符串转换为一个json对象*/
	prototype.jsonStrToJsonObj = function(JsonStr){
		var jsonObj = $.evalJSON(JsonStr);
		return jsonStr;
	}
	
	/**将一个form提交后得到的资源视图加载到一个指定的div界面*/
	prototype.loadFm = function(fmId,divId){
		var fom = $(fmId);
		var div = divId||fom[0].target||("#"+$(fmId).closest("div").attr("id"));
		var url = fom[0].action+"?tmsp="+new Date().getTime();
		var paras = fom.serialize();
		$.ajax({
            async:false,
            url:url,
            data:paras,
            error:function(){frameControl.lhDgFalInfo("通讯失败!");},
            dataType:'html',
            type: "POST",
			contentType:"application/x-www-form-urlencoded; charset=utf-8", 
            success: function(data){
				$(div).empty();
				$(div).html(data);
				$(div).hide();
				$(div).fadeIn(300);
			}
        });
	}
	
	/**将一个url所指向的资源视图加载到一个指定的div界面*/
	prototype.loadUrl = function(url,paras,div){
		if(!url || !div){return;}
		if(!paras){
			paras = "tmsp="+new Date().getTime();
		}else{
			paras = paras+"&tmsp="+new Date().getTime();
		}
		$.ajax({
            async:false,
            url:url,
            data:paras,
            error:function(){frameControl.lhDgFalInfo("通讯失败!");},
            dataType:'html',
            type: "POST",
			contentType:"application/x-www-form-urlencoded; charset=utf-8", 
            success: function(data){
				$(div).empty();
				$(div).html(data);
				$(div).hide();
				$(div).fadeIn(300);
			}
        });
	}
	
}

var frameAjax = new FrameAjax();