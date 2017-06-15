<@p.userHeader title="会员首页"/>
<script type="text/javascript" src="${base}/res/js/dialog/dialog.js" id="dialog_js" charset="utf-8"></script> 
<script type="text/javascript" src="${base}/res/js/nc-sideMenu.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/layer/layer.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/utils.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/common_select.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/ajaxfileupload/ajaxfileupload.js"></script>
<link href="${base}/res/css/font-icons/style.css"  rel="stylesheet" />
<div id="container" class="wrapper">
	<div class="layout">
	   <@u.left  title="会员评论"/>
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
  					<!-- <div class="personal-rating">
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
          								<p>卖家的服务态度</p>
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
          								<p>卖家的发货速度</p>
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
        				评分统计end 
  					</div> -->
  					<div class="tabmenu">
					    <ul id="listpj" class="tab">
					     <!--  <li class="active"><a href="#">来自会员的评价</a></li>
					      <li class="normal"><a href="${base}/myReviews/reviewsSellerList">来自卖家的评价</a></li> -->
					      <li class="normal"><a href="#">我的评价</a></li>
					    </ul>
  					</div>
  					<form id="queryListForm" method="post">
					    <input type="hidden" name="act" value="member_evaluate"/>
					    <input type="hidden" name="op" value="list"/>
					    <input type="hidden" name="type" value="fromseller"/>
    					<table class="ncu-table-style">
      					 <thead>
        					<tr>
          						<th class="w70">
          							<!-- <select name="evalscore" id="gevalscore" nc_type="sform" onchange="reInitDataList();">
              							<option value="0" selected=selected>评价</option>
						              	<option value="5" >好评</option>
						              	<option value="3" >中评</option>
						              	<option value="1" >差评</option>
            						</select> -->
          						</th>
          						<th class="w200 tl"> 
          							<!-- <select name="havecontent"  id="havecontent" nc_type="sform" onchange="reInitDataList();">
					              		<option value="0" selected=selected>评论</option>
					              		<option value="1" >有评论内容</option>
					             		<option value="2" >无评论内容</option>
            						</select> -->
          						</th>
          						<th class="w180">评价人</th>
          						<th class="tl">商品信息</th>
          						<!-- <th class="w90">操作</th> -->
        					</tr>
      					</thead> 
						<tbody>
						<#if datas?size gt 0>
							<#list datas as evaluateGoods>
								 <tr class="bd-line ncgeval">
									 <td class="ncgeval-good"><span class="ico"></span></td>
									 <td class="tl">
									 	<p>${evaluateGoods.gevalContent}</p>
									 	<p>
									 		<#if evaluateGoods.gevalImage??>
										     	<#list evaluateGoods.gevalImage?split(',') as img>
										     		<#if img!=''>
										     			<img width="50px" height="50px" src="${imgServer}${img}" onclick="bigImg(this)"/>
										     		</#if>
										     	</#list>
									     	</#if>
								 	    </p>
								 	    <p class="date">${evaluateGoods.createTimeStr}</p>
									 </td>
									 <td>
										 <p>${evaluateGoods.gevalFrommembername}</p>
										 <!--   <p>卖家信用：0</p> -->
									 </td>
									 <td class="tl">
										<a target="_blank" href="${frontServer}/product/detail?id=${evaluateGoods.gevalGoodsId}">${evaluateGoods.gevalGoodsName}</a> <br/>
									 	${evaluateGoods.gevalGoodsPrice}元
								     </td>
								     <td></td>
								 </tr>
							</#list>
						<#else>
							<tr>
								<td colspan="20" class="norecord"><i>&nbsp;</i><span>暂无符合条件的数据记录</span></td>
							</tr>
						</#if>
  						</tbody>
      					<tfoot>
        					<tr>
          						<td colspan="20">
          							
          							<#import "/commons/usertagpage.ftl" as q> <#--引入分页-->
									<#if recordCount??>
									    <@q.pager pageNo=pageNo pageSize=pageSize recordCount=recordCount toURL="${toUrl}"/>
									</#if>
          						</td>
       	 					</tr>
      					</tfoot>
          			</table>
  				</form>
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
	
	function reInitDataList(){
		var gevalscore = $("#gevalscore").val();
		var havecontent = $("#havecontent").val();
	    $.ajax({
	        async:false,
	        url:"${base}/myReviews/reviewsBuyerList",//默认加载list 页面
	        data:{
	        	gevalscore:gevalscore,
	        	havecontent:havecontent
	        },
	        error:function(){
	            layer.msg("通讯失败!" , 1 , 9 );
	        },
	        dataType:'html',
	        type: "POST",
	        contentType:"application/x-www-form-urlencoded; charset=utf-8",
	        success: function(data){
	            window.location = "${base}${toUrl}";
	        }
	    });  
	}
	
</script>
<@p.userfooter/>