<@p.header title="金币日志"/>
<div class="layout">
  <div class="sidebar">
  </div>
  <div class="right-content">
        <div class="path">
      <div><a href="#">商家中心</a> <span>></span>
                <a href="#"/>
                金币管理                </a><span>></span>金币日志              </div>
    </div>
        <div class="main">
      <link rel="stylesheet" type="text/css" href="${base}/res/js/jquery-ui/themes/ui-lightness/jquery.ui.css"  />

<div class="wrap">
  <div class="tabmenu">
    <ul class="tab pngFix">
      <li class="normal"><a  href="${base}/other/storegbuy">购买记录</a></li><li class="active"><a  href="${base}/other/goldlog">金币日志</a></li>
    </ul>
  </div>
  <table class="search-form">
    <form method="get" action="#?>
      <tr>
        <input type="hidden" name="act" value="store_gbuy" />
        <input type="hidden" name="op" value="gold_log" />
        <td>&nbsp;</td>
        <th>增减类型：</th>
        <td class="w90"><select name="method">
            <option value="" selected>-请选择-</option>
            <option value="1" >增加</option>
            <option value="2" >减少</option>
          </select></td>
        <th>添加时间：</th>
        <td class="w180"><input type="text" class="text" name="add_time_from" id="add_time_from" value="" />
          &#8211;
          <input type="text" class="text" id="add_time_to" name="add_time_to" value="" /></td>
        <td class="w90 tc"><input type="submit" class="submit" value="搜索" /></td>
      </tr>
    </form>
  </table>
  <table class="ncu-table-style">
    <thead>
      <tr>
        <th class="w100">金币数额</th>
        <th class="w150">增减类型</th>
        <th class="w200">添加时间</th>
        <th class="tl">操作描述</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td colspan="20" class="norecord"><i>&nbsp;</i><span>暂无符合条件的数据记录</span></td>
      </tr>
    </tbody>
  </table>
</div>
<!-- <script type="text/javascript" src="http://192.168.1.230/resource/js/jquery-ui/i18n/zh-CN.js" charset="utf-8"></script> --> 
<script type="text/javascript">
	$(function(){
	    $('#add_time_from').datepicker({dateFormat: 'yy-mm-dd'});
	    $('#add_time_to').datepicker({dateFormat: 'yy-mm-dd'});
	});
</script>    
     </div>
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