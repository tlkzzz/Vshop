<script type="text/javascript" src="${base}/res/js/member/member.js"></script>
<table class="ncu-table-style">
	<thead>
		<tr>
			<th class="w150">添加时间</th>
			<th class="w100">积分变更</th>
			<th class="w150">操作</th>
			<th class="tl">描述</th>
		</tr>
	</thead>
	<tbody>
	      <#assign shopPointsLogListTag = newTag("shopPointsLogListTag")/>
	      <#assign shopPointsLogList =shopPointsLogListTag("{'tagDataType':'2','pageSize':'${pageSize}','pageNo':'${pageNo}'}")>
   			<#if shopPointsLogList?exists && shopPointsLogList?size gt 0>
			   <#list shopPointsLogList as shopPointsLog>
					<tr class="bd-line">
						<td class="goods-time">${shopPointsLog.createTimeStr}</td>
						<td class="goods-price">${shopPointsLog.points}</td>
						<td>${shopPointsLog.stage}</td>
						<td class="tl">${shopPointsLog.desc}</td>
					</tr>
			</#list>
	      </#if>
	</tbody>

	<tfoot>
		<tr>
			<td colspan="7">
				<div class="pagination">
				    <#assign recordCount =shopPointsLogListTag("{'pageSize':'${pageSize}','tagDataType':'5'}")>
					<#import "/commons/tagpage.ftl" as q> <#--引入分页--> 
					<#if recordCount??>
					 <@q.pager pageNo=pageNo pageSize=pageSize recordCount=recordCount toURL="${toUrl}"/>
					</#if>
				</div>
			</td>
		</tr>
	</tfoot>
</table>