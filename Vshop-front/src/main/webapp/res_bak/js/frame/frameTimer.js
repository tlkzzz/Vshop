/**
 * 
 * 	js任务管理器 - 即每隔一段时间执行一个指定的函数
 * 该对象提供：开始任务、暂停任务功能
 * runCodePara - 等待执行方法
 * spaceTimePara - 任务执行间隔
 * 
 * 	使用实例 - 页面数据定时刷新 - 页面数据自动舒新函数
 *	var pageTimer = new PageTimerTask("freshData()",5000);
 *	function startFresh(){pageTimer.startTimer();}
 *	function endFresh(){pageTimer.endTimer();}
 *
 */
function PageTimerTask(runCodePara,spaceTimePara){
	this.runCode = runCodePara;
	this.spaceTime = spaceTimePara;
	//任务执行管理器
	this.timerManager;
}
with(PageTimerTask){
	//开始执行指定时间间隔的任务
	prototype.startTimer = function(){
		//任务管理对象不存在 - 开始
		if(!this.timerManager){
			this.timerManager = window.setInterval(this.runCode, this.spaceTime);
		}//任务对象已经存在 - window中删除现有的任务管理对象并重新开始(因为存在暂停后重新开始的情况)
		else{
			this.endTimer();//防止多个任务同时运行
			this.timerManager = window.setInterval(this.runCode, this.spaceTime);
		}
	};
	//结束该任务
	prototype.endTimer = function(){
		if(this.timerManager){//如果开始则结束
			window.clearInterval(this.timerManager);
		}else{	//未开始则返回
			return ;
		}
	};
}