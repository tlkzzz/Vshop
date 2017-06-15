/**
系统控制js插件,下为使用示例：
	frameControl.lhDgOpen('win1',"封装工具打开视窗",'http://www.baidu.com',900,400,true,
		function(){frameControl.lhDgOptInfo("确认按钮点击了,回调函数执行了!");return false;});
	
	frameControl.lhDgNoticeOpen({title: '商业定制',width: 350, 
		height:150,content: '若需要商业定制，记得联系我噢'});
			
	frameControl.lhDgOptInfo("我是要提示的内容");
	frameControl.lhDgSucInfo("操作成功了操作成功了操作成功了操作成功了操作成功了操作成功了");
	frameControl.lhDgFalInfo("操作失败");
	frameControl.lhDgWrnInfo("操作失败");
	
	var callBack = function(val){alert(val);}
	frameControl.lhDgPmtInfo("输入url","http://",function(val){alert(val);});
	frameControl.lhDgPmtInfo("输入url","http://",function aa(val){alert("url:"+val);return true;});

	function callBackYes(){frameControl.lhDgOptInfo("我确认要删除了");}
	function callBackNo(){frameControl.lhDgOptInfo("那我就不删除了");}
	frameControl.lhDgCfmInfo("确认窗口返回布尔值!",callBackYes,callBackNo);
	frameControl.lhDgCfmInfo("询问窗口返回布尔值!",function bb(){alert("确定");},function cc(){alert("拒绝");});
	frameControl.lhDgCfmInfo("询问窗口返回布尔值询问窗口返回布尔值!",function(){alert("确定");},function cc(){alert("拒绝");});
	
	frameControl.lhDgDataBegin();
	alert("html郑加载数据呢");
	frameControl.lhDgDataEnd();
 * */


function FrameControl(){}

with(FrameControl){
	
	/**获取当前系统的主窗口*/
	prototype.getSystemTop = function(){
		var mainWin = window; 
		
		/*最多向上查找15层*/
		var i = 0;
		while(true){
			if(i>15)break;
			if(mainWin.cyanlazurite != null){
				break;
			}else{
				mainWin = mainWin.parent;
			}
			i = i+1;
		}
		
		return mainWin;
	}

	/**调用系统框架打开新打开一个窗口*/
	prototype.commonOpenNewWin = function(url,title,newWin,openerWin){
		
		/*首先获取主窗口*/
	  	var result = this.getSystemTop().cyanlazurite;
	  	
	  	/*具体创建交给systemContent向外提供的接口来完成*/
		result.children[1].children[1].contentWindow.openWin(url,title,newWin,openerWin);
	}
	
	/**调用系统框架关闭一个窗口*/
	prototype.closeWin = function(winId){
		
		/*首先获取主窗口*/
	  	var result = this.getSystemTop().cyanlazurite;

	  	/*具体创建交给systemContent向外提供的接口来完成*/
		result.children[1].children[1].contentWindow.closeWin(winId);
	}
	
	/**调用系统框架关闭当前窗口*/
	prototype.closeCurWin = function(){
		
		/*首先获取主窗口*/
	  	var result = this.getSystemTop().cyanlazurite;
	  	
		/*具体创建交给systemContent向外提供的接口来完成*/
		result.children[1].children[1].contentWindow.closeCurWin();
	}
	
	/**最大化浏览器的工作区*/
	prototype.maxWorkZone = function(imgEle){
		var mainWin = this.getSystemTop();
		var mdeFmSt = mainWin.subFrame;
		$(imgEle).find("i").attr({"class":"fa fa-angle-double-right"});
		mdeFmSt.cols="0px,*";
		imgEle.onclick = function(){
			frameControl.resizeWorkZone(imgEle);
		}
	}
	
	/**回复浏览器工作区的尺寸*/
	prototype.resizeWorkZone = function(imgEle){
		var mainWin = this.getSystemTop();
		var mdeFmSt = mainWin.subFrame;
		$(imgEle).find("i").attr({"class":"fa fa-angle-double-left"});
		mdeFmSt.cols="220px,*";
		imgEle.onclick = function(){
			frameControl.maxWorkZone(imgEle);
		}
	}
	
	/**打开一个新的lhdialog窗口*/
	prototype.lhDgOpen = function(idIn,titleIn,url,width,height,fixed,callBack){
		var dg = $.dialog({
		    id:idIn||'win'+new Date().getTime(),
		    title: titleIn||'视窗',
		    content: url?"url:"+url:'视窗',
		    width: width||600,
		    height: height||350,
		    fixed: fixed||true,
		    drag:true,
		    lock:false,
		    bgcolor:'#000',
		    resize:true,
		    ok:function(){
				callBack();
				return false;
			},
		    close:function(){
	 		    var duration = 70, 
			        api = this,
			        opt = api.config,
			        wrap = api.DOM.wrap,
			        //top = $(window).scrollTop() - wrap[0].offsetHeight;
			        top = 0 - wrap[0].offsetHeight;
		 		    wrap.animate({top:top + 'px', opacity:0}, duration, function(){
		 		        opt.close = function(){};
		 		        api.close();
		 		    });
			   		return false;
			}
		});
		return dg;
	}
	
	/**打开一个公告窗口*/
	prototype.lhDgNoticeOpen = function(options){
		var opts = options || {},
        api, aConfig, hide, wrap, top,
        duration = opts.duration || 100;
	    var config = {
	        id: 'Notice',
	        left: '100%',
	        top: '100%',
	        fixed: true,
	        drag: false,
	        max:false,
	        min:false,
	        resize: false,
	        init: function(here){
	            api = this;
	            aConfig = api.config;
	            wrap = api.DOM.wrap;
	            top = parseInt(wrap[0].style.top);
	            hide = top + wrap[0].offsetHeight;
	            //hide = $(window).scrollTop() - wrap[0].offsetHeight;
	            wrap.css('top', hide + 'px')
	            .animate({top: top + 'px'}, duration, function(){
	                opts.init && opts.init.call(api, here);
	            });
	        },
	        close: function(here){
	            wrap.animate({top: hide + 'px'}, duration, function(){
	                opts.close && opts.close.call(this, here);
	                aConfig.close = $.noop;
	                api.close();
	            });
	            return false;
	        }
	    };
	    for(var i in opts){
	        if( config[i] === undefined ) config[i] = opts[i];
	    }
	    return $.dialog( config );
	}
	
	/**系统提示*/
	prototype.lhDgOptInfo = function(content,parentWin){
		//是否锁定
		var lockIn = false;
		if(parentWin){
			lockIn = true;
		}else{
			parentWin = null;lockIn = false;
		}
		$.dialog({
			id:"lhDgOptInfo",
			title:'系统提示',
		    content: content||"",
		    max:false,
		    min:false,
		    fixed:true,
		    icon:'confirm.gif',
		    width:155,
		    resize:false,
		    lock:lockIn,
		    parent:parentWin,
		    height:120,
			close:function(){
	 		    var duration = 70, 
 		        api = this,
 		        opt = api.config,
 		        wrap = api.DOM.wrap,
 		        //top = $(window).scrollTop() - wrap[0].offsetHeight;
 		       top = 0 - wrap[0].offsetHeight;
 		        wrap.animate({top:top + 'px', opacity:0}, duration, function(){
	 		        opt.close = function(){};
	 		        api.close();
	 		    });
 		   		return false;
			}
		});
	}
	
	/**警告 - 只能同时打开一个*/
	prototype.lhDgWrnInfo = function(content,parentWin){
		//是否锁定
		var lockIn = false;
		if(parentWin){
			lockIn = true;
		}else{
			parentWin = null;lockIn = false;
		}
		
		$.dialog({
			id:"lhDgWrnInfo",
			title:'操作警告',
		    content: content||"",
		    icon:'alert.gif',
		    max:false,
		    min:false,
		    fixed:true,
		    resize:false,
		    width:155,
		    lock:lockIn,
		    parent:parentWin,
		    height:120,
			close:function(){
	 		    var duration = 70, 
 		        api = this,
 		        opt = api.config,
 		        wrap = api.DOM.wrap,
 		        //top = $(window).scrollTop() - wrap[0].offsetHeight;
 		       top = 0 - wrap[0].offsetHeight;
 		        wrap.animate({top:top + 'px', opacity:0}, duration, function(){
	 		        opt.close = function(){};
	 		        api.close();
	 		    });
 		   		return false;
			}
		});
	}
	
	/**成功提示 - 只能同时打开一个*/
	prototype.lhDgSucInfo = function(content,parentWin){
		//是否锁定
		var lockIn = false;
		if(parentWin){
			lockIn = true;
		}else{
			parentWin = null;lockIn = false;
		}
		$.dialog({
			id:"lhDgSucInfo",
			title:'操作成功',
		    content: content||"",
		    icon:'success.gif',
		    max:false,
		    min:false,
		    resize:false,
		    fixed:true,
		    lock:lockIn,
		    parent:parentWin,
		    width:155,
		    height:120,
			close:function(){
	 		    var duration = 70, 
 		        api = this,
 		        opt = api.config,
 		        wrap = api.DOM.wrap,
 		        //top = $(window).scrollTop() - wrap[0].offsetHeight;
 		       top = 0 - wrap[0].offsetHeight;
 		        wrap.animate({top:top + 'px', opacity:0}, duration, function(){
	 		        opt.close = function(){};
	 		        api.close();
	 		    });
 		   		return false;
			}
		});
	}
	
	/**失败 - 只能同时打开一个*/
	prototype.lhDgFalInfo = function(content,parentWin){
		//是否锁定
		var lockIn = false;
		if(parentWin){
			lockIn = true;
		}else{
			parentWin = null;lockIn = false;
		}
		$.dialog({
			id:"lhDgFalInfo",
			title:'操作失败',
		    icon: 'error.gif',
		    content: content||"",
		    max:false,
		    min:false,
		    resize:false,
		    fixed:true,
		    width:155,
		    parent:parentWin,
		    lock:lockIn,
		    height:120,
			close:function(){
	 		    var duration = 70, 
 		        api = this,
 		        opt = api.config,
 		        wrap = api.DOM.wrap,
 		       top = 0 - wrap[0].offsetHeight;
 		        wrap.animate({top:top + 'px', opacity:0}, duration, function(){
	 		        opt.close = function(){};
	 		        api.close();
	 		    });
 		   		return false;
			}
		});
	}
	
	/**提问对话框 - info:提示信息; prefix:预显示信息; callBack:用户输入完毕后回调函数*/
	prototype.lhDgPmtInfo = function(infoIn,prefixIn,callBack){
		var info = infoIn||"";
		var prefix = prefixIn||"";
		$.dialog.prompt(info,
		    function(val){
				callBack(val);
		    },
		    prefix
		);
	}

	/**确认对话框 - */
	prototype.lhDgCfmInfo = function(confirm,callBackYes,callBackNo){
		$.dialog.confirm(confirm, callBackYes,callBackNo);
	}
	
	/**声明对话框*/
	prototype.lhDgAlt = function(content){
		$.dialog.alert(content,function(){});
	}
	
	/**打开数据加载中图标*/
	prototype.lhDgDataBegin = function(){
		$.dialog.tips('数据加载完毕',600,'loading.gif',function(){});	
	}
	
	/**关闭数据加载中图标*/
	prototype.lhDgDataEnd = function(){
		$.dialog.tips('数据加载完毕',1,'tips.gif',function(){});
	}
}
var frameControl = new FrameControl();