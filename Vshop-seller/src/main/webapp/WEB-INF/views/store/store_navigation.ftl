<@p.header title="导航管理"/>
<div class="layout">
   <div class="sidebar">
    
  </div>
  <div class="right-content">
        <div class="path">
      <div><a href="${base}">商家中心</a> <span>></span>
                <a href="#?act=store&op=store_navigation"/>
                导航管理                </a><span>></span>导航列表              </div>
    </div>
        <div class="main">
      
<div class="wrap">
  <div class="tabmenu">
    <ul class="tab pngFix">
  <li class="active"><a  href="#">导航列表</a></li><li class="normal"><a  href="${base}/store/storenavigationadd">新增导航</a></li></ul>
  </div>
  <table class="ncu-table-style">
    <thead>
      <tr>
        <th class="w30"></th>
        <th class="w60">排序</th>
        <th class="w10"></th>
        <th class="tl">导航名称</th>
        <th class="w120">是否显示</th>
        <th class="w90">操作</th>
      </tr>
          </thead>
    <tbody>
	      <tr>
	        <td colspan="20" class="norecord"><i>&nbsp;</i><span>暂无符合条件的数据记录</span></td>
	      </tr>
          </tbody>
	    <tfoot>
	    </tfoot>
  </table>
</div>
    </div>
  </div>
    <div class="clear"></div>
</div>
<script type="text/javascript">
	// 收缩展开效果
	$(document).ready(function() {
		$(".sidebar dl dt").click(function() {
			$(this).toggleClass("hou");
			var sidebar_id = $(this).attr("id");
			var sidebar_dd = $(this).next("dd");
			sidebar_dd.slideToggle("slow", function() {
				$.cookie(COOKIE_PRE + sidebar_id, sidebar_dd.css("display"), {
					expires : 7,
					path : '/'
				});
			});
		});
	});
</script>
</body>
</html>
 <@p.footer/>