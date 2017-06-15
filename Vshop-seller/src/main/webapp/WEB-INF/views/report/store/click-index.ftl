<@p.header title="商家中心－流量统计" />
<div class="layout">
    <div class="sidebar">
    
  </div>
  <script src="${base}/res/js/layer/layer.js"></script>
  <script type="text/javascript" src="${base }/res/js/TimeUtils.js" charset="utf-8"></script>
  <script type="text/javascript" src="${base}/res/js/My97DatePicker/WdatePicker.js"></script>
  <div class="right-content">
     <div class="path">
      <div>
      <a href="#?act=store">商家中心</a> <span>></span> <a href="clickIndex"/>流量统计</a><span>></span>商品流量
	  </div>
    </div>
        <div class="main">
<div class="wrap">
  <div class="tabmenu">
  <ul class="tab pngFix">
	 <li id="goodsTable" class="active"><a  href="javascript:void(0);" onClick="changeReportTable('storeSellCount')">商品流量</a></li>
	  <!-- <li id="storeTable" class="normal"><a  href="javascript:void(0);"  onClick="changeReportTable('storeTotalCount')">商家总销量</a></li> -->
  </ul>
    </div>
 	<input type="hidden" name="startTime" value="${startTime}">
	  <input type="hidden" name="endTime" value="${endTime}">
	  <input type="hidden" name="toUrl" value="${toUrl}">
	  <input type="hidden" name="condition" value="${condition}">
   <div id="container" style="height:850px">
		<iframe id="myframe" width="100%" height="100%"  frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes" ></iframe>
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
		args += "&toUrl=" + toUrl ;
		var url = '${base}/report/${toUrl}';
				$("#myframe").attr("src",url + "?" + args);
}


</script>

<script type="text/javascript" src="${base }/res/js/jquery-ui/i18n/zh-CN.js" charset="utf-8"></script> 
  
</div>
  </div>
    <div class="clear"></div>
</div>
<@p.footer />