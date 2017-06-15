<table class="ncu-table-style">
					<thead>
						<tr>
							<th>品牌图标</th>
							<th>品牌名称</th>
							<th>所属类别</th>
							<th>品牌状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
			        <#if brandList??>
			            <#list brandList as item>
			                <tr class="bd-line">
			                    <td>
			                         <img src="${imgServer}${item.brandPic}"   style="width:60px;height: 60px;"/>
			                    </td>
			                    <td>${item.brandName}</td>
			                    <td>${item.brandClass}</td>
			                    <td>
			                        <#if item.brandApply ??&& item.brandApply==1>
			                        		 审核通过
			                        <#elseif item.brandApply ??&& item.brandApply==2>
								   			 审核未通过
								    <#elseif item.brandApply ??&& item.brandApply==0>
			                         		 审核中
	                       			</#if>
			                    </td>
			                    <td>
			                       <p><a href="javascript:void(0);" onclick="updatestorebrand(${item.brandId})">修改</a></p>
			                       <p><a href="javascript:void(0)" onclick="deleteStorebrand(${item.brandId})" class="ncu-btn2 mt5">删&nbsp;除</a></p>
			                    </td>
			                </tr>
			            </#list>
				         <#else>
				           <tr>
								<td colspan="20" class="norecord"><i>&nbsp;</i><span>暂无符合条件的数据记录</span>
								</td>
						   </tr>
				        </#if>
		        </tbody>
			   <tfoot>
		            <tr>
		                <td colspan="20">
		                    <#import "/commons/page.ftl" as q><!--引入分页-->
		                    <#if recordCount??>
		                        <@q.pager pageNo=pageNo pageSize=pageSize recordCount=recordCount toURL="${toUrl}"/>
		                    </#if>
		                </td>
		            </tr>
		        </tfoot>
</table>
