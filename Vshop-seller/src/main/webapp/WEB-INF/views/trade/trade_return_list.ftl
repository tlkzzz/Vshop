<@p.header title="商家中心-退货记录"/>
<script type="text/javascript" src="${base}/res/js/common_select.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="${base}/res/js/ajaxfileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="${base}/res/js/layer/layer.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>	 
<div class="layout">
	<div class="sidebar"></div> 
	<div class="right-content">
        <div class="path">
      		<div>
      			<a href="#">分销商中心</a> <span></span>
                <a href="#"/>退单记录 </a><span></span>
                	退单记录             
            </div>
    	</div>
        <div class="main">
			<div class="wrap">
  				<div class="tabmenu">
    				<ul class="tab pngFix">
  						<li class="active"><a  href="#">退单记录</a></li>
  					</ul>
  				</div>
  				<form method="get" action="${base}/trade/returnOrderList" id="queryForm">
    				<table class="search-form">
      					<input type="hidden" name="act" value="return" />
      					<tr>
        					<td>&nbsp;</td>
					        <th style="width:115px">
					        	<select name="type">
					            	<option value="orderSn" >订单编号</option>
					            	<option value="returnSn" >退单编号</option>
					            	<option value="buyerName" >会员名</option>
					          	</select>：
					        </th>
        					<td class="w160"><input type="text" class="text" name="key" value="" /></td>
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
				        <th class="w180">订单编号</th>
				        <th class="w180">退单编号</th>
				        <th class="w80">会员名</th>
				        <th class="w80">退单数量</th>
				        <th class="w150"><p class="goods-time">退单时间</p></th>
				        <th class="w90">状态</th>
				        <th class="w90">操作</th>
				      </tr>
    				</thead>
			        <tbody>
			        	<#if list??>
       						<#list list as returnOrder>
	            				<tr class="bd-line" >
	        						<td><span style="color: blue">${returnOrder.orderSn}</span></td>
	        						<td class="goods-num">${returnOrder.returnSn}</td>
	        						<td><span style="color: blue">${returnOrder.buyerName}</span></td>
	        						<td><strong>${returnOrder.returnGoodsNum}</strong></td>
	        						<td class="goods-time"><#if returnOrder.createTimeStr??>${returnOrder.createTimeStr?string('yyyy-MM-dd HH:mm:ss')}</#if></td>
	        						<td>
	        							<#if returnOrder.returnState==1>
	        								待审核
	        							<#elseif returnOrder.returnState==2>
	        								同意
	        							<#elseif returnOrder.returnState==3>
	        								拒绝
	        							</#if>
	        						</td>
	        						<td><a href="javascript:void(0)" onclick="returnOrderDetail('${returnOrder.returnId}')"> 查看 </a>
	        							<#if returnOrder.returnState == 1>
					        				<a href="javascript:void(0)" onclick="returnOrder('${returnOrder.returnId}')" >审核</a>
					        			</#if>
					        		</td>
	      						</tr>
      						</#list>
      					</#if>
                	</tbody>
    				<tfoot>
            			<tr>
        					<td colspan="20">
        						<#import "/trade/page.ftl" as q><!--引入分页-->
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
	<div class="clear"></div>
</div>
</body>
</html>
<script type="text/javascript">
	//查看退货信息
	function returnOrderDetail(id){
	   	layer.open({
		    type: 2,
		    area: ['500px', '500px'],
		    skin: 'layui-layer-rim',
		    title: '退货详情',
		    content :  ['${base}/trade/returnOrderDetail?returnId=' + id, 'no']
		});
	}
	
	/**退货审核**/
	function returnOrder(id){
		layer.open({
		    type: 2,
		    area: ['500px', '400px'],
		    skin: 'layui-layer-rim',
		    title: '退货审核',
		    //content: APP_BASE + '/cart/addresslist'
		    content :  ['${base}/trade/returnOrderIndex?returnId=' + id, 'no'],
		    success: function(layero, index){
		    	layer.getChildFrame('#confirm_button',index).on('click', function(){
					var returnId=layer.getChildFrame("input[name='returnId']",index).val();
					var returnState = layer.getChildFrame("input[name='return_state']:checked",index).val();
					var returnMessage = layer.getChildFrame("textarea[name='return_message']",index).val();
					var fmUrl = '${base}/trade/returnOrder';
					$.ajax({
			             type: "post",
			             url: fmUrl,
			             data: {returnId:returnId,returnState:returnState,returnMessage:returnMessage},
			             dataType: "json",
						 async:false,
						 success:function(data) {
							if(data.success){
								parent.layer.msg("审核成功!",{icon:1},function(){
									location.reload();
								});	
							}else{
								parent.layer.msg("审核失败!",{icon:2},function(){
									location.reload();
								});		
							}
						}
			         });   
				});	
		    }
		});
	}
</script>
<@p.footer/>	