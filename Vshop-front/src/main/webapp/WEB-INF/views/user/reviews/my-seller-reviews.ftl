<@p.userHeader title="会员首页"/>
<script type="text/javascript" src="${base}/res/js/dialog/dialog.js" id="dialog_js" charset="utf-8"></script> 
<script type="text/javascript" src="${base}/res/js/nc-sideMenu.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/utils.js" charset="utf-8"></script>
<link href="${base}/res/css/font-icons/style.css"  rel="stylesheet" />
<div id="container" class="wrapper">
	<div class="layout">
    	<@u.left  title="来自商家评论"/>
    	<div class="right-content">
      		<div class="path">
        		<div>
        			<a href="#?act=member_snsindex">我的商家</a><span>&raquo;</span>
                                        评价管理   
          		</div>
      		</div>
      		<div class="main">
				<div class="wrap"> 
  				<!-- 评分统计start -->
  					<div class="personal-rating">
    					<h4>商家动态评分</h4>
    					<table class="seller-rate-info" id="sixmonth">
      						<tbody>
        						<tr>
          							<th>
          								<p>宝贝与描述相符</p>
            							<p class="rate-star mt5"><em><i style=" width:0%;"></i></em></p>
            						</th>
          							<td>
          								<dl class="ncs-rate-column">
              								<dt><em style="left:0%;">0</em></dt>
              								<dd>非常不满</dd>
              								<dd>不满意</dd>
              								<dd>一般</dd>
              								<dd>满意</dd>
              								<dd>非常满意</dd>
            							</dl>
            						</td>
        						</tr>
        						<tr>
          							<th>
          								<p>商家的服务态度</p>
            							<p class="rate-star mt5"><em><i style="width:0%;"></i></em></p>
            						</th>
          							<td>
          								<dl class="ncs-rate-column">
              								<dt><em style="left:0%;">0</em></dt>
              								<dd>非常不满</dd>
              								<dd>不满意</dd>
              								<dd>一般</dd>
              								<dd>满意</dd>
              								<dd>非常满意</dd>
            							</dl>
            						</td>
        						</tr>
        						<tr>
          							<th>
          								<p>商家的发货速度</p>
            							<p class="rate-star mt5"><em><i style="width:0%;"></i></em></p>
            						</th>
          							<td>
          								<dl class="ncs-rate-column">
	              							<dt><em style="left:0%;">0</em></dt>
	              							<dd>非常不满</dd>
	              							<dd>不满意</dd>
	              							<dd>一般</dd>
	              							<dd>满意</dd>
	              							<dd>非常满意</dd>
            							</dl>
            						</td>
        						</tr>
      						</tbody>
    					</table>
        				<!-- 评分统计end --> 
  					</div>
  					<div class="tabmenu">
					    <ul id="listpj" class="tab">
					      <li class="normal"><a href="${base}/myReviews/reviewsBuyerList">来自会员的评价</a></li>
					      <li class="active"><a href="#">来自商家的评价</a></li>
					      <li class="normal"><a href="${base}/myReviews/reviewsOtherList">给他人的评价</a></li>
					    </ul>
  					</div>
  					<form id="goodsevalform" method="get">
					    <input type="hidden" name="act" value="member_evaluate"/>
					    <input type="hidden" name="op" value="list"/>
					    <input type="hidden" name="type" value="fromseller"/>
    					<table class="ncu-table-style">
      					<thead>
        					<tr>
          						<th class="w70">
          							<select name="evalscore" nc_type="sform">
              							<option value="0" selected=selected>评价</option>
						              	<option value="1" >好评</option>
						              	<option value="2" >中评</option>
						              	<option value="3" >差评</option>
            						</select>
          						</th>
          						<th class="w200 tl"> 
          							<select name="havecontent" nc_type="sform">
					              		<option value="0" selected=selected>评论</option>
					              		<option value="1" >有评论内容</option>
					             		<option value="2" >无评论内容</option>
            						</select>
          						</th>
          						<th class="w180">评价人</th>
          						<th class="tl">宝贝信息</th>
          						<th class="w90">操作</th>
        					</tr>
      					</thead>
						<tbody>
    						<tr class="bd-line ncgeval">
	    						<td class="ncgeval-good"><span class="ico"></span></td>
	    						<td class="tl">
	    							<p>1</p>
	            					<p class="date">[1970-01-01 08:00:01]</p>
	            				</td>
	    						<td>
	    							<p><a target="_blank" href="#?act=show_store&id=2">kviii</a></p>
	      							<p>卖家信用：0</p>
	      						</td>
	    						<td class="tl">
	    							<a target="_blank" href="#?act=goods&goods_id=2">dsdsadasd</a> <br/>
	      							22.00元
	      						</td>
	    						<td></td>
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
  				</form>
			</div>
 		</div>
	</div>
</div>
</div>

<script type="text/javascript">
	$(function(){
		$('*[nc_type="sform"]').change(function(){
			$("#goodsevalform").submit();
		});
	});
</script>
<@p.userfooter/>