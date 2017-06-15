<@p.header title="供应商中心－订单统计" />
<div class="layout">
    <div class="sidebar">
    
  </div>
  <script src="${base}/res/js/layer/layer.js"></script>
  <script type="text/javascript" src="${base }/res/js/TimeUtils.js" charset="utf-8"></script>
  <script type="text/javascript" src="${base}/res/js/My97DatePicker/WdatePicker.js"></script>
  <div class="right-content">
     <div class="path">
      <div>
      <a href="#?act=store">供应商中心</a> <span>></span> <a href="orderIndex"/>订单统计</a><span>></span>订单数量统计
	  </div>
    </div>
        <div class="main">
<div class="wrap">
  <div class="tabmenu">
  <ul class="tab pngFix">
	 <li id="goodsTable" class="active"  name="timebutton" timeArg="%Y-%m-%d" ><a  href="javascript:void(0);">按日统计</a></li>
	  <li id="goodsTable" class="normal" name="timebutton" timeArg="%Y-%u" ><a  href="javascript:void(0);" >按周统计</a></li>
	   <li id="goodsTable"class="normal" name="timebutton" timeArg="%Y-%m" ><a  href="javascript:void(0);" >按月统计</a></li>
	    <li id="goodsTable"class="normal" name="timebutton" timeArg="%Y"  ><a  href="javascript:void(0);">按年统计</a></li>
	  <!-- <li id="storeTable" class="normal"><a  href="javascript:void(0);"  onClick="changeReportTable('storeTotalCount')">供应商总销量</a></li> -->
  </ul>
    </div>
    <form method="post"  id="queryForm">
	  <table class="search-form">
            <tbody>
            
             <tr>
				      <th >订单状态：</th>
				     <td class="w100">
        				 <select class="querySelect" name="orderState" style="width: 130px"> 订单状态：0:已取消;10:待付款;20:待发货;30:待收货;40:交易完成;50:已提交;60:已确认;
		                  	  <option value="">请选择...</option>
		                        <option value="0">已取消</option>
		                        <option value="10">待付款</option>
		                        <option value="20">待发货</option>
		                        <option value="30">待收货</option>
		                        <option value="40">交易完成</option>
		                        <option value="50">已提交</option>
		                        <option value="60">已确认</option>
                   		 </select>
        			</td>
				      <th>查询时间：</th>
				       <td  style="width:230px">
			<input name="startTime" id="start" type="text" style="width: 110px" class="txt Wdate" value="${startTime}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />&#8211;
			<input name="endTime" id="end" type="text" style="width: 110px" class="txt Wdate" value="${endTime}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
        </td>
				      <td class="w90 tc"><input type="button"   onclick="searchRangeTime();" class="submit"value="搜索" /></td>
				    </tr>
       </tbody>
        </table>
         <input type="hidden" name="toUrl" value="${toUrl}">
	 	 <input type="hidden" name="condition" value="%Y-%m-%d">
	 	 <input type="hidden" name="storeId" value="${storeId}">
	</form>
    
    
	
  <!-- JS统计图表 -->
   <div id="container" style="height:850px">
		<iframe id="myframe"  width="100%" height="100%"  frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes" ></iframe>
  </div>
</div>
<script type="text/javascript">
$(function(){
	init();
});
//初始化
function init(){
	var toUrl = $("[name=toUrl]").val();
		var args = "";
		var condition = $("[name=condition]").val();
		args += "&toUrl=" + toUrl + "&condition=" +condition;
		encodeURI(args)
		var url = '${base}/report/${toUrl}';
				$("#myframe").attr("src",url + "?" + encodeURI(args));
}

//改变报表
function changeReport(){
	var startTime = $("#start").val();
	var endTime = $("#end").val();
	var toUrl = $("[name=toUrl]").val();
	var storeId = $("[name=storeId]").val();
	var condition = $("[name=condition]").val();
	var orderState = $("[name=orderState]").val();
	var args = "";
	args += "startTime=" + startTime + "&endTime=" + endTime + "&toUrl=" + toUrl + "&condition=" + condition + "&storeId=" + storeId+"&orderState="+orderState;
	var url = '${base}/report/${toUrl}';
	encodeURI(args)
		$("#myframe").attr("src",url + "?" + encodeURI(args));
	//window.location.href = ;
}


</script>

<script type="text/javascript">
$(function(){
	//时间的三个按钮
    $("[name=timebutton]").click(function(){
  		  $("[name=timebutton]").removeClass("active");
  		    $("[name=timebutton]").addClass("normal");
  		     $(this).removeClass("normal");
   		 $(this).addClass("active");
    	var timeArg = $(this).attr("timeArg");
    	 $("[name=condition]").val(timeArg);
    		changeReport();
    });
});
//查询指定日期
function searchRangeTime(){
	changeReport();
}
//导出
$("[name=export]").click(function(){
	var startTime = $("[name=startTime]").val();
	var endTime = $("[name=endTime]").val();
	var toUrl = $("[name=toUrl]").val();
	var condition = $("[name=condition]").val();
	var storeId = $("[name=storeId]").val();
	var exportType = $(this).attr("exportType");
	var args = "";
	args += "startTime=" + startTime + "&endTime=" + endTime + "&toUrl=" + toUrl + "&condition=" + condition + "&exportType=" + exportType
	window.location.href='${base}/report/'+toUrl + "?"+args;
});
</script>    
</div>
  </div>
    <div class="clear"></div>
</div>
<@p.footer />