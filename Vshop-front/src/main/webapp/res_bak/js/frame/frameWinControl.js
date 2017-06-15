
/**
 * 工作区模拟窗口控制插件
 * 		push - 新元素添加到数组最后一个位置并返回数组长度!
 * 		unshift - 新元素添加到数组第一个位置并返回数组长度!
 * 		pop - 移除数组最后一个元素并返回!
 * 		shift - 移除数组第一个元素并返回!
 * 	unshift、shift - 添加、移除一个元素，操作位是第一个元素
 * 	push、pop - 添加移除一个元素，操作位是最后一个元素
 * 
 * */
function FrameWinControl(){
	
	/*url数组*/
	this.idsArr = new Array();
	
	/*系统窗口标题栏*/
	this.titleBar = document.getElementById("systemTitleBar");
	
	/*窗口所在div区域*/
	this.winBar = document.getElementById("winIfmsDv");
	
	/*当前窗口标题栏可用宽度 - 150：
	 *3个按钮:90; bar左右内置padding:10;左右隐藏标题提示位:30;边界线计：10; 余量：10;计算结果最小为1不为负数 */
	this.titleBarWidth = (document.body.offsetWidth-145)>0?(document.body.offsetWidth-145):1;
	
	/*默认系统可打开的最多窗口数目*/
	this.totalWinNum = 15;
	
	/*调整窗口时最后一个隐藏的标题*/
	this.lastHideLi = null;
}

with(FrameWinControl){
	
	/*****************************************************************标题、视窗的创建、切换、删除*******************************************************/		
	
	/**窗口尺寸发生变化时 - 重新计算标题栏可用宽度*/
	prototype.reSetTitleBar = function(){
		
		/*重新计算标题栏可用宽度 - 并设置显示的标题栏中标题状体*/
		this.titleBarWidth = (document.body.offsetWidth-145)>0?(document.body.offsetWidth-145):1;
		this.switchTitleBar("adjust");
		//alert("this.titleBarWidth:"+this.titleBarWidth);
		//alert("this.cliendWidth:"+document.body.offsetWidth);
	}
	
	/**创建一个标题li*/
	prototype.createTitle = function(url,tag,idIn){
		
		/*长度超过8标题只显示部分文字*/
  		var id = idIn;
  		var showTag = (tag.length>8)?(tag.substr(0,7)+".."):(tag);
  		var liWidth = (20+showTag.length*13);	//li的宽度
  		
  		var li = document.createElement('li');
  		$(li).attr({"class":"active","onmouseover":"javascript:frameWinControl.showClolseImg(this)","id":id,"url":url,
  			"onmouseout":"javascript:frameWinControl.hideCloseImg(this)","onclick":"javascript:frameWinControl.openTheWin(this.id,'switch');"});
  		$(li).css({"width":(liWidth+"px")});
  		li.innerHTML = '<a href="#" style="padding:2px 5px;margin-top:5px;border-radius:3px;cursor:default;"><table class="titleFtThemeSet"><tr><td>'
  							+showTag+'</td><td><i class="fa fa-times-circle" style="font-size:15px;position:absolute;top:2px;display:none;color:red;cursor:pointer"></i>'
  							+'</td></tr></table></a></li>';
		return li;
	}
	
	/**创建窗口IFRAME*/
	prototype.createWin = function(url,tag,idIn){
		
		/*如果未指定id则使用url作为id*/
		var id=idIn;
		
		/*创建IFRAME*/
		var newWin = document.createElement('iframe');
		$(newWin).css({"width":"100%","height":"100%","display":"block"});
		$(newWin).attr({"id":(id+'Ifm'),"frameborder":"0","scrolling":"auto","src":url});
	
		return newWin;
	}
		
	/**置暗全部的标题 隐藏全部的iframe - 通过css实现*/
	prototype.hideAllWins = function(){
		
		/*置暗(不选中)全部标题*/
		$("#systemTitleBar li").each(function(){
			$(this).attr("class","");
		});
		
		/*隐藏全部窗口*/
		$("iframe").each(function(){
			if($(this).css("display") == "block"){
				$(this).css("display","none");
			}
		});
	}
	
	/**判断某个url是否存在*/
	prototype.isUrlExist = function(url){
		var result = false;
		var exists = $("#systemTitleBar li[url='"+url+"']");
		if(exists.length > 0){result = true;}
		return result;
	}
	
	/**系统工作区最多展开界面的限制 - 最多6个*/
	prototype.canAddMore = function(url){
		var result = true;
		if((this.idsArr.length) >= this.totalWinNum){
			frameControl.lhDgOptInfo("当前系统最多只支持"+this.totalWinNum+"个视窗,请关闭不使用的视窗然后再打开要操作的视窗!");
			//alert("当前系统最多只支持"+this.totalWinNum+"个视窗,请关闭掉不使用的视窗后再打开要操作的视窗!");
			result = false;
		}
		
		return result;
	}
	
	/**
	 * 通过不断的新增、删除、前移等操作
	 * 实现url数组顺序的有效记录，方便
	 * 用户的良好体验 */
	prototype.operateTitleIds = function(url,operateTag){
		if("add" == operateTag){
			
			/*新增一个url值*/
			this.idsArr.unshift(url);
			// alert(this.idsArr.join("-"))
			
		}else if("delete" == operateTag){
			
			/*剔除一个url*/
			var index = null;
			for(var i = 0; i < this.idsArr.length; i=i+1){
				if(this.idsArr[i] == url){
					index = i;
					break;
				}
			}
			if(index != null){
				for(var i = index; i < this.idsArr.length-1; i=i+1){
					this.idsArr[i] = this.idsArr[i+1];
				}
				this.idsArr.pop();
			}
			
		}else if("switch" == operateTag){
			
			/*将一个url移到数组第一个*/
			var index = null;
			for(var i = 0; i < this.idsArr.length; i=i+1){
				if(this.idsArr[i] == url){
					index = i;
					break;
				}
			}
			var medVul = this.idsArr[index];
			for(var i = index; i >= 1; i = i-1){
				this.idsArr[i]=this.idsArr[i-1];
			}
			this.idsArr[0] = medVul;
			
		}
	}
	
	
	/**显示指定url的窗口,新增或者激活*/
	prototype.openTheWin = function(id,optType){
		/*隐藏全部的标题栏和窗口*/
		this.hideAllWins();
		
		/*显示标题*/
		$("#"+id).attr({"class":"active"});
		//document.getElementById(id).className="active";
		
		/*显示模拟窗口*/
		document.getElementById(id+"Ifm").style.display="block";
		
		/*重新调整id数组的排序*/
		this.operateTitleIds(id,optType);
		
		/*标题栏调整*/
		this.switchTitleBar(optType);
	}
	
	/**显示指定url的窗口,新增或者激活 - 为点击已经打开的菜单栏服务*/
	prototype.openTheWinByUrl = function(url,optType){
		var id = $("#systemTitleBar li[url='"+url+"']")[0].id;
		this.openTheWin(id,optType);
		return id;
	}
	
	/**
	 *	新建一个窗口 
	 *	url：新窗口指向的url
	 *	tag：新窗口的标题
	 *	newWin：重复打开标识*/
	prototype.openWin = function(url,tagIn,newWin,openerWinIn){
		/*窗口标题*/
		var tag = tagIn||"新窗口";
		
		/*如果传入的url已经打开那么直接激活就行
		 * 也可以通过传入"newWin"来强行重复打开*/
		if(!newWin || "newWin" != newWin){
			if(!this.isUrlExist(url)){
				
				/*校验是否可以再增加窗口*/
				if(!this.canAddMore()){return;}
				
				/*如果入参url不存在则新建窗口新建并添加标题td*/
				var id = new Date().getTime()+"";
				var newTitle = this.createTitle(url,tag,id);
				this.bindCloseToImg(newTitle);
				var lastLi = this.titleBar.children[this.titleBar.children.length-1];
				this.titleBar.insertBefore(newTitle,lastLi);
				
		  		/*创建并添加模拟窗口*/
		  		var newWin = this.createWin(url,tag,id);
		  		this.winBar.appendChild(newWin);
		  		
		  		/*如果是从界面新建窗口 - 那么设置来源*/
		  		if(openerWinIn){
		  			newWin.contentWindow.openerWin = openerWinIn;
		  		}
		  		
		  		/*激活新建窗口*/
		  		this.openTheWin(id,"add");
		  		return id;
		  		
			}else{
				/*如果指定url已经存在 - 重新激活*/
				var id = this.openTheWinByUrl(url,"switch");
				return id;
				
			}
		}else{
			
			/*可以通过入参newWin 强制重复打开存在的url*此时在使用
			 * url做为操作主键可能导致重复故使用时间戳做为主键*/
			var id = new Date().getTime()+"";
			//alert("id:"+id);
			
			/*插入标题*/
			var newTitle = this.createTitle(url,tag,id);
			this.bindCloseToImg(newTitle);
			var lastLi = this.titleBar.children[this.titleBar.children-1];
			this.titleBar.insertBefore(newTitle,lastLi);
	  		
	  		/*创建模拟窗口并添加*/
	  		var newWin = this.createWin(url,tag,id);
	  		this.winBar.appendChild(newWin);
	  
	  		/*激活存在的窗口*/
	  		this.openTheWin(id,"add");
	  		return id;
		}
  	}
		
	/**通过单击图片关闭某个窗口*/
	prototype.bindCloseToImg = function(newTitle){
		$(newTitle).find("i").click(function(event){
			event.stopPropagation();
			var liEle = $(this).closest("li")[0];
			var liId = liEle.id;
			var curId = frameWinControl.idsArr[0];
			if(liId == curId){
				
				/*如果关闭的是当前的窗口*/
				$(document.getElementById(liId+"Ifm")).remove();
				$(liEle).remove();
				frameWinControl.operateTitleIds(liId,"delete");
				
				/*关闭之后还需要显示前一个操作窗口*/
				frameWinControl.openTheWin(frameWinControl.idsArr[0],"switch");
				
			}else{
				
				/*如果关闭的是非当前的窗口*/
				$(document.getElementById(liId+"Ifm")).remove();
				$(liEle).remove();
				frameWinControl.operateTitleIds(liId,"delete");
				
				/*根据删除后留下的空当重新调整标题栏*/
				frameWinControl.switchTitleBar("switch");
			}
			
		});
	}
	
	/**通过单击图片关闭某个窗口*/
	prototype.closeWinById = function(id){
		var curId = frameWinControl.idsArr[0];
		if(id == curId){
			/*如果关闭的是当前的窗口*/
			$("#"+id+"Ifm").remove();
			$("#"+id).remove();
			frameWinControl.operateTitleIds(id,"delete");
			
			/*关闭之后还需要显示前一个操作窗口*/
			frameWinControl.openTheWin(frameWinControl.idsArr[0],"switch");
			
		}else{
			/*如果关闭的是非当前的窗口*/
			$("#"+id+"Ifm").remove();
			$("#"+id).remove();
			frameWinControl.operateTitleIds(id,"delete");
			
			/*根据删除后留下的空当重新调整标题栏*/
			frameWinControl.switchTitleBar("switch");
		}
	}
	
	/**g关闭全部窗口*/
	prototype.closeAllWin = function(id){
		var len = frameWinControl.idsArr.length;
		for(var i = 0; i < len; i++){
			this.closeWinById(frameWinControl.idsArr[0]);
		}
	}
	
	/**关闭当前窗口*/
	prototype.closeCurWin = function(){
		if(frameWinControl.idsArr.length != 0){
			var id = frameWinControl.idsArr[0];
			this.closeWinById(id);
		}
	}
		
	/**鼠标悬停标题时 - 关闭按钮显示*/
	prototype.showClolseImg = function(icon){
		$(icon).find("i[class='fa fa-times-circle']").show();
	}
	
	/**鼠标离开标题时 - 关闭按钮隐藏*/
	prototype.hideCloseImg = function(icon){
		$(icon).find("i[class='fa fa-times-circle']").hide();
	}
	
	
	/*****************************************************************标题栏的隐藏、移动****************************************************************/		
	
	/*判断标题栏是否溢出*/
	prototype.isBarsOverFlow = function(){
		var result = false;
		var curTitlesLength = 0;
		var validBars = $("#systemTitleBar li");
		for(var i = 1; i < validBars.length-1; i = i+1){
			
			/*只计算渲染的,隐藏的不计*/
			if(($(validBars[i]).css("display") == "list-item")
					||($(validBars[i]).css("display") == "block")){
				curTitlesLength = curTitlesLength+$(validBars[i]).width();
			}
		}
		//alert(curTitlesLength+":"+this.titleBarWidth);
		if(curTitlesLength >= this.titleBarWidth){
			result = true;
		}
		return result;
	}

	/*获取前线可视的全部标题宽度之和*/
	prototype.curTitleSetWidthSum = function(){
		var result = false;
		var curTitlesLength = 0;
		var validBars = $("#systemTitleBar li");
		for(var i = 1; i < validBars.length-1; i = i+1){
			
			/*只计算渲染的,隐藏的不计*/
			if(($(validBars[i]).css("display") == "list-item")
					||($(validBars[i]).css("display") == "block")){
				curTitlesLength = curTitlesLength+$(validBars[i]).width();
			}
		}
		
		return curTitlesLength;
	}
	
	/*计算左右隐藏提示标签的显示状态*/
	prototype.caliCulaHideTagStatus = function(){
		
		/*左右隐藏提示标签的默认状态*/
		var leftTag = "h";
		var rightTag = "h";
		var leftTagHtml = "";
		var rightTagHtml = "";
		var midTitShow = false;

		/*只统计标题li标签,隐藏标题提示标签不计*/
		var validBars = $("#systemTitleBar li");
		for(var i = 1; i < validBars.length-1; i = i+1){
			
			/*判断当前的遍历是否经过了中间渲染的标签*/
			//midTitShow(false:未经过；true:已经过)
			if(($(validBars[i]).css("display") == "block")
					||($(validBars[i]).css("display") == "list-item")){
				midTitShow = true;
			}
			
			/*如果有隐藏的并且没有遍历到中间的渲染标签 - 显示左侧隐藏提示标签*/
			if(($(validBars[i]).css("display") == "none") 
					&& !midTitShow ){
				leftTag = 's';
				leftTagHtml = $(validBars[i]).find("a table td:first").html();
				leftTagHtml = leftTagHtml.substring(leftTagHtml.length-3,leftTagHtml.length);
			}
			
			/*如果已经遍历过中间的渲染标签并且有隐藏的标签 - 显示右侧隐藏提示标签*/
			if(($(validBars[i]).css("display") == "none") 
					&& midTitShow ){
				rightTag = 's';
				rightTagHtml = $(validBars[i]).find("a table td:first").html();
				rightTagHtml =  rightTagHtml.substring(0,2)+".";
			}
		}
		return leftTag+rightTag+"-"+leftTagHtml+"-"+rightTagHtml;
	}
	
	/*控制隐藏提示标签 ss:都显示;hh:都隐藏;sh:左显示右隐藏;hs:左隐藏有显示*/
	prototype.switchHideTagStatus = function(paraIn){
		var optTypes = paraIn.split("-");
		var optType = optTypes[0];
		var leftHtml = optTypes[1];
		var rightHtml = optTypes[2];
		if(optType == "ss"){
			$("#beginHideTag").show(100);
			//if(leftHtml){$("#beginHideTag").find("a").html(leftHtml);}
			$("#endHideTag").show(100);
			//if(rightHtml){$("#endHideTag").find("a").html(rightHtml);}
		}else if(optType == "hh"){
			$("#beginHideTag").hide(100);
			$("#endHideTag").hide(100);
		}else if(optType == "sh"){
			$("#beginHideTag").show(100);
			//if(leftHtml){$("#beginHideTag").find("a").html(leftHtml);}
			$("#endHideTag").hide(100);
		}else if(optType == "hs"){
			$("#beginHideTag").hide(100);
			$("#endHideTag").show(100);
			//if(rightHtml){$("#endHideTag").find("a").html(rightHtml);}
		}
	}
	
	/**根据入参类型的不同分别获取左侧第一个和右侧最后一个可视li标题*/
	prototype.getVisibleLiByDirction = function(dirction){
		var result = null;
		var validBars = $("#systemTitleBar li");
		if("left" == dirction){
			for(var i = 1; i < validBars.length-1; i = i+1){
				if(($(validBars[i]).css("display") == "block")
						||($(validBars[i]).css("display") == "list-item")){
					result = validBars[i];
					break;
				}
			}
		}else{
			for(var i = validBars.length-2; i > 0; i = i-1){
				if(($(validBars[i]).css("display") == "block")
						||($(validBars[i]).css("display") == "list-item")){
					result = validBars[i]; 
					break;
				}
			}
		}
		return result;
	}
	
	/**根据入参类型的不同分别获取左侧最后一个和右侧第一个隐藏的li标题*/
	prototype.getHideLiByDirction = function(dirction){
		var result = null;
		var validBars = $("#systemTitleBar li");
		if("left" == dirction){
			for(var i = 1; i < validBars.length-1; i = i+1){
				if(($(validBars[i]).css("display") == "block")
						||($(validBars[i]).css("display") == "list-item")){
					break;
				}
				if($(validBars[i]).css("display") == "none"){
					result = validBars[i];
				}
			}
		}else{
			for(var i = validBars.length-2; i > 0; i = i-1){
				if(($(validBars[i]).css("display") == "block")
						||($(validBars[i]).css("display") == "list-item")){
					break;
				}
				if($(validBars[i]).css("display") == "none"){
					result = validBars[i];
				}
			}
		}
		return result;
	}
	
	/**获取窗口放大后下一个可以被显示出来的元素*/
	prototype.getNextShowEleAfterAdjust = function(){
		var result = null;

		/*先计算当前可视标题宽度只和*/
		var curTitlesLength = 0;
		var validBars = $("#systemTitleBar li");
		for(var i = 1; i < validBars.length-1; i = i+1){
			
			/*只计算渲染的,隐藏的不计*/
			if(($(validBars[i]).css("display") == "block")
					||($(validBars[i]).css("display") == "list-item")){
				curTitlesLength = curTitlesLength+$(validBars[i]).width();
			}
		}
		
		/*如果当前可视标题集合宽度只和小于可用标题栏宽度*/
		if(curTitlesLength < this.titleBarWidth){
			var midResult = this.getHideLiByDirction("right");
			if(midResult == null){
				midResult =  this.getHideLiByDirction("left");
			}
			if(midResult != null 
					&& ((curTitlesLength+$(midResult).width()) <= this.titleBarWidth)){
				result = midResult;
			}
		}
		return result;
	}
	
	/**如果所有的元素都隐藏了 - 那么获取最后一个被隐藏的元素*/
	prototype.getLastHideEleAfterAdjust = function(){
		var result = null;
		
		/*默认"0" - 可操作*/
		var msg = "0";
		var ltHideEle = this.lastHideLi;
		
		/*如果最后隐藏的元素不存在 - 不需要当前操作*/
		if(!ltHideEle){
			msg = "2"
			return  {"msg":msg,"ltHideEle":ltHideEle};
		}
		
		/*如果存在可见元素  - 则不需要当前操作*/
		var validBars = $("#systemTitleBar li");
		for(var i = 1; i < validBars.length-1; i = i+1){
			if(($(validBars[i]).css("display") == "block")
					||($(validBars[i]).css("display") == "list-item")){
				msg = "2";
				return  {"msg":msg,"ltHideEle":ltHideEle};
			}
		}
		
		/*如果需要第一个指定显示的元素宽度大于可用宽度 - 暂时等待*/
		if($(ltHideEle).width() >= this.titleBarWidth){
			msg = "1";
			return  {"msg":msg,"ltHideEle":ltHideEle};
		}
		
		/*如果这个指定要被第一个显示的元素确定了,当前对象不再维护了
		 * 等到窗口缩小时重新围护*/
		this.lastHideLi = null;
		return {"msg":msg,"ltHideEle":ltHideEle};
		
	}
	
	/**如果当前的可视标题集合宽度只和小于可视宽度 
		如果右边有隐藏的则不断循环显示出来,右边没有的话显示左边的*/
	prototype.leftAndRightAdjustShow = function(){
		
		var shEle = this.getNextShowEleAfterAdjust();
		if(shEle != null){
			while(true){
				$(shEle).css("display","list-item");
				shEle = this.getNextShowEleAfterAdjust();
				if(shEle == null){break;}
			}
		}	
	}
	
	/**调整标题栏optType:add|switch|adjust|right|left*/
	prototype.switchTitleBar = function(optType){

		if("add" == optType){
			/*如果新增时菜单发生溢出 - 单向向左移动直到不再发生溢出*/
			var loopTag = this.isBarsOverFlow();
			if(!loopTag) return;
			while(loopTag){
				var hdLiEle = this.getVisibleLiByDirction("left");
				//$(hdLiEle).hide(300);
				$(hdLiEle).css("display","none");
				loopTag = this.isBarsOverFlow();
			}
			
			/*调整隐藏提示标签*/
			var status = this.caliCulaHideTagStatus();
			this.switchHideTagStatus(status);
			
		}else if("adjust" == optType){
			
			/*如果发生了溢出*/
			var loopTag = this.isBarsOverFlow();
			if(loopTag){
				while(loopTag){
					
					/*不断隐藏右边的可视标题 - 直到不再溢出*/
					var hdLiEle = this.getVisibleLiByDirction("right");
					$(hdLiEle).css("display","none");
					loopTag = this.isBarsOverFlow();
					
					/*记录最后隐藏的标题*/
					this.lastHideLi = hdLiEle;
					
				}
			}else{
				
				/*如果有需要指定第一个显示的元素*/
				var result = this.getLastHideEleAfterAdjust();
				//alert(result.msg);
				
				if(result.msg == "0"){
					/*有需要第一个指定显示的元素 */
					var ltHideEle = result.ltHideEle;
					$(ltHideEle).css("display","list-item");
					
					/*还有剩余宽度 - 接着显示*/
					this.leftAndRightAdjustShow();
					
				}else if(result.msg == "1"){
					/*有需要指定第一个显示的隐藏元素 - 但是宽度不够 - 返回等待*/
					
				}else if(result.msg == "2"){
					
					/*没有需要指定第一个显示的隐藏元素*/
					this.leftAndRightAdjustShow();
				}
			}
			//调整隐藏提示标签
			var status = this.caliCulaHideTagStatus();
			this.switchHideTagStatus(status);
			
		}else if("switch" == optType){
			
			/*可见标题关闭后 - 会有从新需要显示的 - 先显示又在然后是左边*/
			this.leftAndRightAdjustShow();
			
			/*调整隐藏提示标签*/
			var status = this.caliCulaHideTagStatus();
			this.switchHideTagStatus(status);
			
		}else if("right" == optType){
			
			/*ndShowEle-需要显示的元素;  ndHideEle-需要隐藏的元素*/
			var ndShowEle = this.getHideLiByDirction("left");
			if(!ndShowEle){return;}
			var ndHideEle = this.getVisibleLiByDirction("right");
			
			/*如果存在需要显示的元素 - 那么需要被隐藏的元素才会被隐藏*/
			if(ndHideEle){
				$(ndHideEle).css("display","none");
			}
			if(ndShowEle){
				$(ndShowEle).css("display","list-item");
			}
			
			/*如果发生了溢出 - 那么不断的隐藏可视标题栏中右侧的标题 - 直到不再发生溢出*/
			var loopTag = this.isBarsOverFlow();
			while(loopTag){
				var ndHideAdjWdth = this.getVisibleLiByDirction("right");
				if(!ndHideAdjWdth){
					break;
				}else{
					$(ndHideAdjWdth).css("display","none");
				}
				var loopTag = this.isBarsOverFlow();
				if(!loopTag){
					break;
				}
			}
			
			/*调整隐藏提示标签*/
			var status = this.caliCulaHideTagStatus();
			this.switchHideTagStatus(status);
			
		}else if("left" == optType){
			/*ndShowEle - 需要被显示的元素; ndHideEle - 需要被隐藏的元素*/
			var ndShowEle = this.getHideLiByDirction("right");
			if(!ndShowEle){return;}
			var ndHideEle = this.getVisibleLiByDirction("left");
			if(ndHideEle){
				$(ndHideEle).css("display","none");
			}
			if(ndShowEle){
				$(ndShowEle).css("display","list-item");
			}
			
			/*如果发生了溢出 - 那么不但的隐藏可视标题栏中左侧的标题 - 直到不再发生溢出*/
			var loopTag = this.isBarsOverFlow();
			while(loopTag){
				var ndHideAdjWdth = this.getVisibleLiByDirction("left");
				if(!ndHideAdjWdth){
					break;
				}else{
					$(ndHideAdjWdth).css("display","none");
				}
				var loopTag = this.isBarsOverFlow();
				if(!loopTag){
					break;
				}
			}		
			
			/*调整隐藏提示标签*/
			var status = this.caliCulaHideTagStatus();
			this.switchHideTagStatus(status);
		}
	}
}

var frameWinControl = null;
$(function(){
	frameWinControl = new FrameWinControl();
	
	/*窗口大小发生变化时 - 调整工作区操作div高度以便控制下拉条
	 *同时调整标题栏 - 以免发生标题溢出*/
	$(window).resize(function(){
		frameWinControl.reSetTitleBar();
		$("#winIfmsDv").height(window.innerHeight-34);
	});
	
	/*页面加载完成 - 调整工作区操作div高度以便控制下拉条*/
	$("#winIfmsDv").height(window.innerHeight-34);
	
}); 