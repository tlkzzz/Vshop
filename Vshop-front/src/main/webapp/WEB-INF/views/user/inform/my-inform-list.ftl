<@p.userHeader title="会员首页"/>
<script type="text/javascript" src="${base}/res/js/dialog/dialog.js" id="dialog_js" charset="utf-8"></script> 
<script type="text/javascript" src="${base}/res/js/nc-sideMenu.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/utils.js" charset="utf-8"></script>
<link href="${base}/res/css/font-icons/style.css"  rel="stylesheet" />
<div id="container" class="wrapper">
	<div class="layout">
    	<@u.left  title="我的"/>
    	<div class="right-content">
      		<div class="path">
        		<div>
        			<a href="#?act=member_snsindex">我的商家</a><span>&raquo;</span>我的举报                  </div>
      			</div>
      			<div class="main">
					<div class="wrap">
  						<div class="tabmenu">
    						<ul class="tab pngFix">
  								<li class="active"><a href="#?act=member_inform&op=inform_list">我的举报</a></li>
  							</ul>
  						</div>
  						<form id="list_form" method="get">
    						<table class="search-form">
      							<input type="hidden" id='act' name='act' value='store_voucher' />
      							<input type="hidden" id='op' name='op' value='voucher_template_inform_detail' />
      							<tr>
        							<td></td>
							        <td class="w100 tr">
							        	<select name="select_inform_state">
								            <option value="0" > 所有举报 </option>
								            <option value="1" > 未处理 </option>
								            <option value="2" > 已处理 </option>
							          	</select>
							        </td>
							        <td class="w90 tc"><input type="submit" class="submit" onclick="submit_search_form()" value="搜索" /></td>
      							</tr>
    						</table>
  						</form>
  						<table class="ncu-table-style">
    						<thead>
						    	<tr>
						        	<th class="w10"></th>
						        	<th class="w150 tl">商品名称</th>
						        	<th class="w100">举报类型</th>
						        	<th>举报主题</th>
						        	<th class="w80">图片</th>
						        	<th class="w100">举报时间</th>
						        	<th class="w60">状态</th>
						        	<th class="w60">处理结果</th>
						        	<th class="w90">操作</th>
						      	</tr>
    						</thead>
        				<tbody>
	            			<tr class="bd-line">
	        					<td></td>
	        					<td class="tl"><a href="#?act=goods&goods_id=88" target="_blank"> 李宁运动鞋 </a></td>
	        					<td>出售禁售品</td>
	        					<td>管制刀具、弓弩类、其他武器等</td>
	        					<td>暂无图片</td>
	        					<td>2015-06-26</td>
	        					<td>未处理</td>
	        					<td></td>
	        					<td>
	        						<a href="javascript:void(0)" class="show_detail">举报内容</a>
	                    			<a class="ncu-btn2 mt5" href="javascript:void(0)" onclick="ajax_get_confirm('确认取消该举报?', 'index.php?act=member_inform&op=inform_cancel&inform_id=1');">取消</a>
	          					</td>
	      					</tr>
	      					<tr class="inform_detail">
	        					<td colspan="20">
	        						<div class="shadow">
							            <dl>
							              	<dt>举报内容：</dt>
							              	<dd>是的撒的</dd>
							            </dl>
		            					<dl style="border-bottom:0;">
		              						<dt>处理信息：</dt>
		              						<dd>无</dd>
		            					</dl>
		            					<div class="close_detail">
		            						<a href="JavaScript:void(0);" title="关闭">关闭</a>
		            					</div>
	          						</div>
	          					</td>
	      					</tr>
                		</tbody>
    					<tfoot>
		            		<tr>
		        				<td colspan="20">
		        					<div class="pagination">
		        						<ul>
		        							<li><span>首页</span></li>
		        							<li><span>上一页</span></li>
		        							<li><span class="currentpage">1</span></li>
		        							<li><span>下一页</span></li>
		        							<li><span>末页</span></li>
		        						</ul>
		        					</div>
		        				</td>
		      				</tr>
          				</tfoot>
  					</table>
				</div>
    		</div>
    	</div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
    $(".inform_detail").hide();
    $(".show_detail").click(function(){
        $(".inform_detail").hide();
        $(this).parents("tr").next(".inform_detail").show();
    });
    $(".close_detail").click(function(){
        $(this).parents(".inform_detail").hide();
    });
});
function submit_search_form(){
        $('#act').val('member_inform');
        $('#op').val('inform_list');
        $('#list_form').submit();
}
</script>
<@p.userfooter/>