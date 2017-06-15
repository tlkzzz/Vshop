<@p.header title="合作伙伴"/>
<div class="layout">
    <div class="sidebar">
    
  </div>
  <div class="right-content">
        <div class="path">
      <div><a href="${base}">供应商中心</a> <span>></span>
                <a href="#?act=store&op=store_partner"/>
                合作伙伴管理                </a><span>></span>合作伙伴列表              </div>
    </div>
        <div class="main">
      
<div class="wrap">
  <div class="tabmenu">
    <ul class="tab pngFix">
  <li class="active"><a  href="#?act=store&op=store_partner">合作伙伴列表</a></li></ul>
    <a href="javascript:void(0)" class="ncu-btn3" uri="#?act=store&op=store_partner&type=add" nc_type="dialog" dialog_id="my_partner_add" dialog_width="480" dialog_title="新增合作伙伴">新增合作伙伴</a></div>
  <table class="ncu-table-style">
    <thead>
      <tr>
        <th class="w30"></th>
        <th class="w50">排序</th>
        <th class="w150">标识</th>
        <th class="200 tl">标题</th>
        <th class="tl">链接</th>
        <th class="w90">操作</th>
      </tr>
    </thead>
        <tbody>
		      <tr>
		        <td colspan="20" class="norecord"><i>&nbsp;</i><span>暂无符合条件的数据记录</span></td>
		      </tr>
        </tbody>
    </table>
</div>
<#--<script type="text/javascript" src="http://192.168.1.230/resource/js/dialog/dialog.js" id="dialog_js" charset="utf-8"></script> -->   </div>
  </div>
    <div class="clear"></div>
  </div>
<script type="text/javascript">
	//收缩展开效果
	$(document).ready(function() {
		$(".sidebar dl dt").click(function(){
			$(this).toggleClass("hou");
			var sidebar_id = $(this).attr("id");
			var sidebar_dd = $(this).next("dd");
			sidebar_dd.slideToggle("slow", function() {
				$.cookie(COOKIE_PRE + sidebar_id, sidebar_dd.css("display"), {
					expires :7,
					path : '/'
				});
			});
		});
	});
</script>
</body>
</html>
<@p.footer/>