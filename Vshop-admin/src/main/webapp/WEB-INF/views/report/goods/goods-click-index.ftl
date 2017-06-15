<@layout.head>
<script type="text/javascript" src="${base}/res/js/jquery-ui/jquery.ui.js"></script>
<script type="text/javascript" src="${base}/res/js/jquery-ui/i18n/zh-CN.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="${base}/res/js/jquery-ui/themes/ui-lightness/jquery.ui.css"  />
<script type="text/javascript" src="${base}/res/js/jquery.edit.js"></script>
<script src="${base}/res/js/layer/layer.js"></script>
<script type="text/javascript" src="${base }/res/js/TimeUtils.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/My97DatePicker/WdatePicker.js"></script>
</@layout.head>
<@layout.body>
<div  class="page">
 <div class="fixed-bar">
        <div class="item-title">
            <h3>商品流量情况</h3>
           <!-- <ul class="tab-base">
                <li><a href="JavaScript:void(0);" name="timebutton" timeArg="today" class="current"><span>今日情况</span></a></li>
                <li><a href="JavaScript:void(0);" name="timebutton" timeArg="week" ><span>本周情况</span></a></li>
                  <li><a href="JavaScript:void(0);" name="timebutton"timeArg="month" ><span>本月情况</span></a></li>
                    <li><a href="JavaScript:void(0);"name="timebutton"  timeArg="year"><span>今年情况</span></a></li>
            </ul>
            -->
        </div>
    </div>
      <div class="fixed-empty"></div>
      <!-- 
    <form method="post" action="#" name="formSearch" id="formSearch">
        <input type="hidden" name="pageNo" value="${pager.pageNo}">
        <table class="tb-type1 noborder search">
            <tbody>
      <tr>
        <td>
	        <input style="cursor:pointer" type="button" value="本周情况" name="timebutton" timeArg="week">&nbsp;
	        <input style="cursor:pointer" type="button" value="本月情况" name="timebutton" timeArg="month">&nbsp;
	        <input style="cursor:pointer" type="button" value="今年情况" name="timebutton" timeArg="year">
             <input style="cursor:pointer" type="button" value="导出pdf" exportType="pdf" name="export">&nbsp;
 			<input style="cursor:pointer" type="button" value="导出excel" exportType="excel" name="export">&nbsp;
        </td>
       <th>查询时间：</th>
        <td>
	        <input type="text" class="txt date"  value="${startTime}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" id="start" /> &#8211;
	        <input type="text" class="txt date"   value="${endTime}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" id="end" />
        </td>
        <td>
        	<a href="javascript:void(0);"  onclick="searchRangeTime();" class="btn-search " title="查询"></a>
        </td>
     
      </tr>
        </table>
         <input type="hidden" name="toUrl" value="${toUrl}">
	 	 <input type="hidden" name="condition" value="${condition}">
	 	 <input type="hidden" name="storeId" value="${storeId}">
    </form>
       -->
         <input type="hidden" name="toUrl" value="${toUrl}">
	 	 <input type="hidden" name="condition" value="${condition}">
	 	 <input type="hidden" name="storeId" value="${storeId}">
  <!-- JS统计图表 -->
  <div id="container" style="height:850px">
		<iframe id="myframe" width="100%" height="100%"  frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes" ></iframe>
  </div>
<script type="text/javascript">
$(function(){
	init();
});
//初始化
function init(){
	
	var storeId = $("[name=storeId]").val();
	var toUrl = $("[name=toUrl]").val();
				var args = "";
		args += "&toUrl=" + toUrl + "&condition=" + "assign" + "&storeId=" + storeId
		var url = '${base}/report/${toUrl}';
				$("#myframe").attr("src",url + "?" + args);
}


</script>
  </div>
    <div class="clear"></div>
</div>
</@layout.body>