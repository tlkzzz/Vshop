<@p.userHeader title="会员首页"/>
<script type="text/javascript" src="${base}/res/js/dialog/dialog.js" id="dialog_js" charset="utf-8"></script> 
<script type="text/javascript" src="${base}/res/js/nc-sideMenu.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/utils.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/layer/layer.js" charset="utf-8"></script>
<link href="${base}/res/css/font-icons/style.css"  rel="stylesheet" />
<div id="container" class="wrapper">
	<div class="layout">
    	<@u.left  title="我的"/>
    	<div class="right-content">
      		<div class="path">
        		<div>
        			<a href="#?act=member_snsindex">我的商家</a><span>&raquo;</span>
                    <a href="#?act=member&op=order"/>我的订单</a><span>&raquo;</span>
                   	退款申请
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
  							<li class="active"><a  href="#">退款申请</a></li>
  							<li class="normal"><a  href="${base}/user/returnList">退货申请</a></li>
  						</ul>
  					</div>
  					<form method="post" action="${base}/user/refundList" id="queryForm">
				        <input type="hidden" name="act" value="member_refund" />
				        <input type="hidden" name="op" value="index" />
				   		<table class="search-form">
      						<tr>
        						<td>&nbsp;</td>
        						<th style="width:115px">
        							<select name="type">
            							<option value="orderSn" <#if type=="orderSn">selected="selected"</#if>>订单编号</option>
            							<option value="refundSn" <#if type=="refundSn">selected="selected"</#if>>退款编号</option>
            							<option value="storeName" <#if type=="storeName">selected="selected"</#if>>商家名</option>
          							</select>：
          						</th>
        						<td class="w160"><input type="text" class="text" name="key" value="${key}" /></td>
        						<th>申请时间：</th>
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
        						<th class="w150">退款编号</th>
        						<th>商家</th>
        						<th class="w80">退款金额</th>
        						<th class="w150">申请时间</th>
        						<th class="w50">审核状态</th>
        						<th class="w90">操作</th>
      						</tr>
    					</thead>
    					<tbody>
    						<#if list??>
    							<#list list as refundLog>
		                  			<tr class="bd-line" >
		        						<td><span style="color: blue">${refundLog.orderSn}</span></td>
		        						<td class="goods-num">${refundLog.refundSn}</td>
		        						<td>
		        							<span style="color: blue">${refundLog.storeName}</span>
		        						</td>
		        						<td>
			        						<em class="goods-price" title="${refundLog.refundPaymentName}">
			        							<script type="text/javascript">
					              					var refund = number_format(${refundLog.orderRefund},2);
					              					document.write(refund);
					              				</script>
			        						</em>
			        					</td>
		        						<td class="goods-time">${refundLog.createTimeStr?string('yyyy-MM-dd HH:mm:ss')}</td>
		        						<td>
		        							<#if refundLog.refundState==1>
		        								待审核
		        							<#elseif refundLog.refundState==2>
		        								审核通过
		        							<#elseif refundLog.refundState==3>
		        								已拒绝
		        							</#if>
		        						</td>
		        						<td>
		        							<a href="javascript:void(0)" onclick="refundLogDetail('${refundLog.logId}')"> 查看 </a>
		        						</td>
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
	//查看退款信息
	function refundLogDetail(id){
	   	layer.open({
		    type: 2,
		    area: ['400px', '270px'],
		    skin: 'layui-layer-rim',
		    title: '退款详情',
		    content :  ['${base}/user/refundLogDetail?logId=' + id, 'no']
		});
	}
</script>
<@p.userfooter/>