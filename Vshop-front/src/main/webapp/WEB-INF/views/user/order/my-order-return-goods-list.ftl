<@p.userHeader title="会员首页"/>
<script type="text/javascript" src="${base}/res/js/dialog/dialog.js" id="dialog_js" charset="utf-8"></script> 
<script type="text/javascript" src="${base}/res/js/nc-sideMenu.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/utils.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/layer/layer.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<link href="${base}/res/css/font-icons/style.css"  rel="stylesheet" />
<div id="container" class="wrapper">
	<div class="layout">
    	<@u.left  title="我的"/>
    	<div class="right-content">
      		<div class="path">
        		<div>
        			<a href="#?act=member_snsindex">我的商家</a><span>&raquo;</span>
                    <a href="#?act=member&op=order"/>我的订单</a><span>&raquo;</span>
                   	退货申请
                </div>
      		</div>
      		<div class="main">
       			<link rel="stylesheet" type="text/css" href="${base}/res/js/jquery-ui/themes/ui-lightness/jquery.ui.css"  />
				<style type="text/css">
				.store-name {
					width: 130px;
					display: inline-block;
					overflow: hidden;
					white-space: nowrap;
					text-overflow: ellipsis;
				}
				</style>
				<div class="wrap">
  					<div class="tabmenu">
    					<ul class="tab pngFix">
  							<li class="normal"><a  href="${base}/user/list">订单列表</a></li>
  							<li class="normal"><a  href="${base}/user/refundList">退款申请</a></li>
  							<li class="active"><a  href="#">退货申请</a></li>
  						</ul>
  					</div>
  					<form method="post" action="${base}/user/returnList">
    					<table class="search-form">
      						<input type="hidden" name="act" value="member_return" />
       						<input type="hidden" name="op" value="index" />
      						<tr>
        						<td>&nbsp;</td>
        						<th style="width:115px">
        							<select name="type">
            							<option value="orderSn" <#if type=="orderSn">selected="selected"</#if>>订单编号</option>
            							<option value="returnSn" <#if type=="returnSn">selected="selected"</#if>>退货编号</option>
            							<option value="storeName" <#if type=="storeName">selected="selected"</#if>>商家名</option>
          							</select>：
          						</th>
        						<td class="w160"><input type="text" class="text" name="key" value="${key}" /></td>
        						<th>退货时间：</th>
        						<td class="w260">
        							<input name="startTime"  type="text" class="txt Wdate" value="${startTime}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />&#8211;
          							<input name="endTime" type="text" class="txt Wdate" value="${endTime}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
          						</td>
          						<td class="w90 tc"><input type="submit" class="submit" value="搜索" /></td>
      						</tr>
    					</table>
  					</form>
  					<table class="ncu-table-style">
    					<thead>
      						<tr>
        						<th class="w150">订单编号</th>
       							<th class="w150">退货编号</th>
        						<th>商家</th>
        						<th class="w80">退货数量</th>
        						<th class="w150"><p class="goods-time">退货时间</p></th>
       							<th class="w50">审核状态</th>
        						<th class="w90">操作</th>
      						</tr>
    					</thead>
        				<tbody>
        					<#if list??>
        						<#list list as returnOrder>
		            				<tr class="bd-line" >
		        						<td><span style="color: blue">${returnOrder.orderSn}</span></td>
		        						<td class="goods-num">${returnOrder.returnSn}</td>
		        						<td><span style="color: blue">${returnOrder.storeName}</span></td>
		        						<td><strong>${returnOrder.returnGoodsNum}</strong></td>
		        						<td class="goods-time">${returnOrder.createTimeStr?string('yyyy-MM-dd HH:mm:ss')}</td>
		        						<td>
		        							<#if returnOrder.returnState==1>
		        								待审核
		        							<#elseif returnOrder.returnState==2>
		        								审核通过
		        							<#elseif returnOrder.returnState==3>
		        								已拒绝
		        							</#if>
		        						</td>
		        						<td><a href="javascript:void(0)" onclick="returnOrderDetail('${returnOrder.returnId}')"> 查看 </a></td>
		      						</tr>
	      						</#list>
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
				</div>
      		</div>
    	</div>
	</div>
</div>
<script type="text/javascript">
	//查看退货信息
	function returnOrderDetail(id){
	   	layer.open({
		    type: 2,
		    area: ['400px', '270px'],
		    skin: 'layui-layer-rim',
		    title: '退货详情',
		    content :  ['${base}/user/returnOrderDetail?returnId=' + id, 'no']
		});
	}
</script>
<@p.userfooter/>