/**主控界面数据操作js插件*/

function FrameOperate(){
	this.belongWins = {};
}

with(FrameOperate){
	
	/***********************************************************模拟对话框部分的操作***************************************************************/
	
	/**无条件刷新数据*/
	prototype.freshData = function(){
		$("#queryDataShowIframe")[0].src = $("#queryDataShowIframe")[0].src;
	}
	
	
	/**有条件 - 继续刷新*/
	prototype.freshDataContinue = function(){
		$("#queryDataShowIframe")[0].contentWindow.document.getElementById("continueQueryForm").submit();
	}

	
	/**创建一个lhdialog模拟对话框*/
	prototype.openNewLhDg = function(idIn,titleIn,url,width,height,fixed,callBack){
		var idPa = idIn||'window'+new Date().getTime();
		var dg = $.dialog({
		    id:idPa,
		    title: titleIn||'',
		    content: 'url:'+url,
		    width: width||650,
		    height: height||400,
		    fixed: fixed||true,
		    drag: true,
		    ok:function(){
				if(callBack){
					callBack();
				};
				;return true;
			},
		    resize:false,
		    close:function(){
	 		    var duration = 70,api = this,
		        opt = api.config,wrap = api.DOM.wrap,
		        //top = $(window).scrollTop() - wrap[0].offsetHeight;
		        top = 0 - wrap[0].offsetHeight;
	 		    wrap.animate({top:top + 'px', opacity:0}, duration, function(){
	 		        opt.close = function(){}; api.close();
	 		    });
		   		return false;
			}
		});
		this.belongWins[idPa] = dg;
		return dg;
	}
	
	
	/**准备新增当前模块业务数据**/
	prototype.prepareInsert=function(idIn,titleIn,pageIn,width,height,lockIn,callBack){
		lockIn = lockIn==false?false:true;
		var idPa = idIn||'window-'+new Date().getTime();
		var dg = $.dialog({
		    id:idPa,
		    title: titleIn||'',
		    content: 'url:'+pageIn,
		    width: width||650,
		    height: height||400,
		    fixed:true,
		    drag:true,
		    lock:lockIn,
		    extendDrag:true,
		    resize:true,
		    button: [{name: '重填',
				    	callback: function(){
							this.content.document.forms[0].reset();	
							return false;
						},
					 	focus: true
					}],
		    close:function(){
	 		    var duration = 70,api = this,
		        opt = api.config,wrap = api.DOM.wrap,
		        top = 0 - wrap[0].offsetHeight;
	 		    wrap.animate({top:top + 'px', opacity:0}, duration, function(){
	 		        opt.close = function(){}; api.close();
	 		    });
		   		return false;
			},
		    ok:function(){
				var opFm = this.content.document.forms[0];				//待提交的表单
				if(!frameValidate.validateForm(opFm)){return false;};	//首先验证表单是否合法
				var fmUrl = opFm.action;
				if(fmUrl.indexOf(".do") != -1){							//要求json格式的返回数据
					fmUrl = fmUrl.replace(".do",".jsonx");
				}
				$(opFm).ajaxSubmit({
					url:fmUrl,
					beforeSubmit:function(){},
					error:function(data,status,e){frameControl.lhDgFalInfo("操作失败!",dg);},
					type:"post",
					dataType:"json",
					async:false,
					data:$(opFm).serialize(),
					success:function(data) {
						if(data.msg == "0"){
							if(callBack){callBack();}
						}else{
							frameControl.lhDgWrnInfo(data.msg.substring(2,data.msg.length-1),dg);
						}
					}
				});
				return false;
			},
		});
		this.belongWins[idPa] = dg;
		return dg;
	}
	
	/**修改数据 - 一次修改一条*/
	prototype.prepareUpdate = function(idIn,titleIn,pageIn,width,height,lockIn,callBack){
		lockIn = lockIn==false?false:true;
		var idPa = idIn||'window-'+new Date().getTime();
		var dgUpdate = $.dialog({
		    id:idPa,
		    title: titleIn||'',
		    content: 'url:'+pageIn,
		    width: width||650,
		    height: height||400,
		    fixed:true,
		    drag:true,
		    lock:lockIn,
		    extendDrag:true,
		    resize:true,
		    cancel:true,
		    close:function(){
	 		    var duration = 70,api = this,
		        opt = api.config,wrap = api.DOM.wrap,
		        top = 0 - wrap[0].offsetHeight;
	 		    wrap.animate({top:top + 'px', opacity:0}, duration, function(){
	 		        opt.close = function(){}; api.close();
	 		    });
		   		return false;
			},
		    ok:function(){
				var opFm = this.content.document.forms[0];				//待提交的表单
				if(!frameValidate.validateForm(opFm)){return false;};	//首先验证表单是否合法
				var fmUrl = opFm.action;
				if(fmUrl.indexOf(".do") != -1){							//要求json格式的返回数据
					fmUrl = fmUrl.replace(".do",".jsonx");
				}
				$(opFm).ajaxSubmit({
					url:fmUrl,
					beforeSubmit:function(){},
					error:function(data,status,e){frameControl.lhDgFalInfo("操作失败!",dgUpdate);},
					type:"post",
					dataType:"json",
					async:false,
					data:$(opFm).serialize(),
					success:function(data) {
						if(data.msg == "0"){
							dgUpdate.close();		//关闭当前窗口
							if(callBack){window.setTimeout(callBack,100);}
						}else{
							frameControl.lhDgWrnInfo(data.msg.substring(2,data.msg.length-1),dgUpdate);
						}
					}
				});
				return false;
			},
		});
		this.belongWins[idPa] = dgUpdate;
		return dgUpdate;
	}

	
	/**准备查看**/
	prototype.view = function(idIn,titleIn,pageIn,width,height,lockIn,callBack){
		lockIn = lockIn==false?false:true;
		var idPa = idIn||'window-'+new Date().getTime();
		var dgView = $.dialog({
		    id:idPa,
		    title: titleIn||'',
		    content: 'url:'+pageIn,
		    width: width||650,
		    height: height||400,
		    fixed:true,
		    drag:true,
		    lock:lockIn,
		    extendDrag:true,
		    resize:true,
		    cancel:true,
		    cancelVal:"关闭",
		    close:function(){
	 		    var duration = 70,api = this,
		        opt = api.config,wrap = api.DOM.wrap,
		        top = 0 - wrap[0].offsetHeight;
	 		    wrap.animate({top:top + 'px', opacity:0}, duration, function(){
	 		        opt.close = function(){}; api.close();
	 		    });
		   		return false;
			},
			okVal:"修改",
		    ok:function(){
				var opFm = this.content.document.forms[0];				//待提交的表单
				var fmUrl = opFm.action;
				if(fmUrl.indexOf(".do") != -1){							//要求json格式的返回数据
					fmUrl = fmUrl.replace(".do",".jsonx");
				}
				//ajaxSubmit - 根据请求数据的格式 - 自动做了转换
				$(opFm).ajaxSubmit({
					url:fmUrl,
					beforeSubmit:function(){},
					error:function(data,status,e){frameControl.lhDgFalInfo("操作失败!",dgView);},
					type:"post",
					dataType:"json",
					async:false,
					data:$(opFm).serialize(),
					success:function(data) {
						if(data.msg == "0"){
							dgView.close();		//关闭当前窗口
							if(callBack){window.setTimeout(callBack,100);}
						}else{
							frameControl.lhDgWrnInfo(data.msg.substring(2,data.msg.length-1),dgView);
						}
					}
				});
				return false;
			},
		});
		this.belongWins[idPa] = dgView;
		return dgView;
	}
	
	
	/**Excel导入模板配置**/
	prototype.prepExlImptMdlSet = function(idIn,titleIn,pageIn,width,height,lockIn){
		lockIn = lockIn==false?false:true;
		var idPa = idIn||'window-'+new Date().getTime();
		var dgExlImptMdlSet = $.dialog({
		    id:idIn||'window-'+new Date().getTime(),
		    title: titleIn||'',
		    content: 'url:'+pageIn,
		    width: width||850,
		    height: height||400,
		    fixed:true,
		    drag:true,
		    lock:lockIn,
		    extendDrag:true,
		    resize:true,
		    cancel:true,
		    close:function(){
	 		    var duration = 70,api = this,
		        opt = api.config,wrap = api.DOM.wrap,
		        top = 0 - wrap[0].offsetHeight;
	 		    wrap.animate({top:top + 'px', opacity:0}, duration, function(){
	 		        opt.close = function(){}; api.close();
	 		    });
		   		return false;
			}
		});
		this.belongWins[idPa] = dgExlImptMdlSet;
		return dgExlImptMdlSet;
	}
	
	
	/**Excel导出模板配置**/
	prototype.prepExlExptMdlSet = function(idIn,titleIn,pageIn,width,height,lockIn){
		lockIn = lockIn==false?false:true;
		var idPa = idIn||'window-'+new Date().getTime();
		var dgExlExptMdlSet = $.dialog({
		    id:idPa,
		    title: titleIn||'',
		    content: 'url:'+pageIn,
		    width: width||850,
		    height: height||400,
		    fixed:true,
		    drag:true,
		    lock:lockIn,
		    extendDrag:true,
		    resize:true,
		    cancel:true,
		    close:function(){
	 		    var duration = 70,api = this,
		        opt = api.config,wrap = api.DOM.wrap,
		        top = 0 - wrap[0].offsetHeight;
	 		    wrap.animate({top:top + 'px', opacity:0}, duration, function(){
	 		        opt.close = function(){}; api.close();
	 		    });
		   		return false;
			}
		});
		this.belongWins[idPa] = dgExlExptMdlSet;
		return dgExlExptMdlSet;
	}
	

	/**导入Excel文件*/
	prototype.prepExlImpt = function(idIn,titleIn,pageIn,width,height,lockIn){
		lockIn = lockIn==false?false:true;
		var idPa = idIn||'window-'+new Date().getTime();
		var dgExlImpt = $.dialog({
		    id:idPa,
		    title: titleIn||'',
		    content: 'url:'+pageIn,
		    width: width||850,
		    height: height||400,
		    fixed:true,
		    drag:true,
		    lock:lockIn,
		    extendDrag:true,
		    resize:true,
		    cancel:true,
		    close:function(){
	 		    var duration = 70,api = this,
		        opt = api.config,wrap = api.DOM.wrap,
		        top = 0 - wrap[0].offsetHeight;
	 		    wrap.animate({top:top + 'px', opacity:0}, duration, function(){
	 		        opt.close = function(){}; api.close();
	 		    });
		   		return false;
			}
		});
		this.belongWins[idPa] = dgExlImpt;
		return dgExlImpt;
	}
	
	/**根据id获取打开的视窗*/
	prototype.getWinById = function(winId){
		return this.belongWins[winId];
	}
	
	prototype.testAll = function(){
		for(var key in this.belongWins){
			alert(key+":"+this.belongWins[key]);
		}
	}
	
	
	/***********************************************************分页以及界面初始化***************************************************************/
	/**单击checkbox或硬性指定checkBox的选中状态*/
	prototype.checkData = function(chbx,optTag){
		var trEle = $(chbx).closest("tr");
		//如果指定要选中
		if(optTag ==  true){
			chbx.checked = true;
			trEle.style.backgroundColor="#0088cc";
			
		}else if(optTag == false){
			//如果指定不选中
			chbx.checked = false;
			if(trEle.rowIndex%2 == 0){
  				$(trEle).css("background-color","#f5f5f5");
  			}else{
  				$(trEle).css("background-color","#ffffff");
  			}
			
		}else{
			//如果是单纯的点击checkBox - 只做背景色切换即可
			if(chbx.checked){
				trEle.style.backgroundColor="#0088cc";
			}else{
				if(trEle.rowIndex%2 == 0){
	  				$(trEle).css("background-color","#f5f5f5");
	  			}else{
	  				$(trEle).css("background-color","#ffffff");
	  			}
			}
		}
	}
	

	/**分页导航方法 - 要求：form一定要有id，同时制定target属性指向所要加载数据的div*/
	prototype.forwardToPage = function(operateTag,operateInfo){
		//获取触发分页的事件和元素 - 以及包裹这个元素的form(默认为要提交的分页表单)
		var event=arguments.callee.caller.arguments[0] || window.event; 
		var srcEle = event.srcElement ? event.srcElement : event.target;
		var form = $(srcEle).closest("form")[0];
		
		//指定页码跳转
		if(operateTag=='currentPageNo'){
			//如果入参页码未指定 - 可能是通过跳转图标实现
			var currentPageNo=operateInfo;
			if(isNaN(currentPageNo) || !currentPageNo){
				currentPageNo = $(form).find('input[name="ctPgNo"]').val();
				if(isNaN(currentPageNo) || !currentPageNo){
					$(form).find('input[name="ctPgNo"]').val("");
					frameControl.lhDgWrnInfo("请输入正确格式的页码!");return;
				}
			}
			
			//页码数据合格后重新提交查询表单
			$(form).find('input[name="pageQuery.currentPageNo"]').val(currentPageNo);

			//提交表单
			frameAjax.loadFm("#"+form.id);
			
		}else if(operateTag=='dataCountPerPage'){
			//重新指定每页显示数据数量后跳转
			var dataCountPerPage = $(srcEle).val();
			$(form).find('input[name="pageQuery.dataCountPerPage"]').val(dataCountPerPage);
			
			//提交表单
			frameAjax.loadFm("#"+form.id);
			
		}else if(operateTag == "orderBy"){
			//设置要排序的字段和顺序
			var orderBy = $(operateInfo).attr("orderBy");
			var orderType = $(operateInfo).attr("orderType");
			
			//提交表单
			$(form).find('input[name="pageQuery.orderBy"]').val(orderBy);
			$(form).find('input[name="pageQuery.orderType"]').val(orderType);
			frameAjax.loadFm("#"+form.id);
		}
	}
	
	
	/**获取页面选中的数据,以逗号分隔;入参为包含checkbox的table的主键*/
	prototype.getCheckedDatasByTabId = function(tabId){
		var chbks = $("#"+tabId+" tr input[type='checkbox']:checked");
		var result = "";
		for(var i = 0; i < chbks.length; i=i+1){
			if(chbks[i].checked && (i == chbks.length -1)){
				result = result+chbks[i].value;
			}else if(chbks[i].checked && (i != chbks.length -1)){
				result = result+chbks[i].value+",";
			}
		}
		return result;
	}
	
	
	/**切换数据选中状态*/
	prototype.swicthCheckDatas = function(chMian){
		$(chMian).closest("table").find('tr input[type="checkbox"]:not(:first)').each(function(){
			$(this).trigger("click");
			//如果是选中状态 - 那么突出背景色; 不选中则恢复背景色
			if(this.checked){
				$(this).closest("tr").css({"background-color":"#d9edf7"});
			}else{
				if($(this).closest("tr")[0].rowIndex%2 == 0){
					$(this).closest("tr").css("background-color","#f5f5f5");
  	  			}else{
  	  				$(this).closest("tr").css("background-color","#ffffff");
  	  			}
			}
		});
	}
	
	
	/**勾选全部数据 - chMian:被点击的头checkbox*/
	prototype.checkAllDatas = function(chMian){
		$(chMian).closest("table").find('tr input[type="checkbox"]:not(:first)').each(function(){
			this.checked = true;
		});
	}
	
	
	/**取消勾选全部数据 - chMian:被点击的头checkbox*/
	prototype.unCheckAllDatas = function(chMian){
		$(chMian).closest("table").find('tr input[type="checkbox"]:not(:first)').each(function(){
			this.checked = false;
		});
	}
	
	
	/**获取页面选中的数据,以逗号分隔*/
	prototype.getCheckedDatas = function(chMian){
		var chbks = $(chMian).closest("table").find("tr input[type='checkbox']:checked");
		var result = ""; var arrIndex = 0;
		for(var i = 0; i < chbks.length; i=i+1){
			if(chbks[i].checked && (i == chbks.length -1)){
				result = result+chbks[i].value;
			}else if(chbks[i].checked && (i != chbks.length -1)){
				result = result+chbks[i].value+",";
			}
		}
		return result;
	}
	
	
	/**判断某一行tr头部的checkBox是否被选中*/
	prototype.dataIsChecked = function(trEle){
		var chbx = trEle.cells[0].getElementsByTagName("input");
		if(!chbx || !chbx[0] || chbx[0].type.toUpperCase() != "CHECKBOX"){
			return false;
		}
		var chb = chbx[0];
		return chb.checked;
	}

	
	/**初始化数据展示界面*/
	prototype.initQueryPage = function(tabId){
		//标题头添加排序
  		$(tabId+" th").click(
	 		function(){
		 		var iconArr = this.getElementsByTagName("i");
		 		var icon = null;
		 		if(iconArr != null && iconArr.length != 0){
		 			icon = iconArr[0];
			 	}
			 	if(icon == null){return;}
			 	frameOperate.forwardToPage('orderBy',icon);
	 		}
	 	);
  		
  		//添加全部选中功能 - 给当前table的thead中的第一个checkbox添加
  		$(tabId+' th input[type="checkbox"]').click(function(){
  			frameOperate.swicthCheckDatas(this);
  		});
  		
  		//每一行不同的背景色
  		$.each($(tabId+" tr"),function(index,trEle){
  			$(trEle).css("background-color",(index%2==0?"#f5f5f5":"#ffffff"));
  		});
  		
  		//鼠标悬停时 - 突出背景色 - 如果当前数据时选中则不作操作
  		$(tabId+" tr").mouseover(function(){
  			if(frameOperate.dataIsChecked(this)==false){
  				$(this).css("background-color","#d9edf7");
  			}
  		});
  		
  		//鼠标离开时 - 恢复背景色 - 如果当前数据时选中则不作操作 dff0d8
  		$(tabId+" tr").mouseleave(function(){
  			if(frameOperate.dataIsChecked(this)==false){
  				if(this.rowIndex%2 == 0){
  	  				$(this).css("background-color","#f5f5f5");
  	  			}else{
  	  				$(this).css("background-color","#ffffff");
  	  			}
  			}
  		});
  		
  		//操作图标尺和分页图标的寸改变
		$("i[chgCmIcSize='Y']").mouseover(function(){
			$(this).css({"font-size":"20px","cursor":"pointer"});
		});
		$("i[chgCmIcSize='Y']").mouseleave(function(){
			$(this).css({"font-size":"14px"});
		});
		$("i[chgPgIcSize='Y']").mouseover(function(){
			$(this).css({"font-size":"20px","cursor":"pointer"});
		});
		$("i[chgPgIcSize='Y']").mouseleave(function(){
			$(this).css({"font-size":"14px"});
		});
	}
	
	
	/**初始化操作tabel - 主要是共新增和修改使用*/
	prototype.initOptTable = function(tabId){
		$(tabId).css({"width":"100%","align":"center"});
		$(tabId).find("td").css({"height":"34px","padding":"0px"});
		$(tabId).find("td:even").css({"text-align":"right","padding-right":"3px","width":"15%","font-weight":"bolder"});
		$(tabId).find("td:odd").css({"width":"35%","padding-left":"2px","padding-top":"3px"});
		$(tabId).find("td input").css({"height":"28px","margin-bottom":"4px","margin-top":"4px","width":"65%"});
		$(tabId).find("td input").blur(function(){frameValidate.validateInput(this);});
	}
	
	
	/**初始化查询条件tabel*/
	prototype.initQuyTable = function(tabId){
		$(tabId).css({"width":"100%","align":"center","border-collapse":"collapse"});
		$(tabId).find("td").css({"height":"30px","padding":"0px"});
		$(tabId).find("td:even").css({"text-align":"right","padding-right":"5px","font-weight":"bolder"});
		$(tabId).find("td:odd").css({"padding-left":"2px","padding-top":"3px"});
		$(tabId).find("td input").css({"height":"20px","width":"75%"});
		$(tabId).find("button").closest("td").css({"padding-right":"30px"});
	}
}
var frameOperate = new FrameOperate();