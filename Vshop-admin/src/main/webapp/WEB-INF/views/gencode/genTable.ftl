<@layout.head>
<script type="text/javascript" src="${base}/res/js/jquery-ui/jquery.ui.js"></script>
<script type="text/javascript" src="${base}/res/js/jquery-ui/i18n/zh-CN.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="${base}/res/js/jquery-ui/themes/ui-lightness/jquery.ui.css"  />
</@layout.head>
<@layout.body>
<div class="page">
    <div class="fixed-bar">
        <div class="item-title">
            <h3>业务表</h3>
            <ul class="tab-base">
                <li><a href="${base}/genTable/list"><span>列表</span></a></li>
            <#if genTable.id?exists && genTable.id!=0>
            	<li><a href="${base}/genTable/forward"><span>新增</span></a></li>
                <li><a href="javascript:void(0);" class="current"><span>修改</span></a></li>
            <#else>
                <li><a href="javascript:void(0);" class="current"><span>新增</span></a></li>
            </#if>
            </ul>
        </div>
    </div>
    <div class="fixed-empty"></div>
    <#if genTable.name?exists && genTable.name!=''>
    
    <#else>
    <form id="genTable_form" method="post" action="${base}/genTable/form">
        <table class="table tb-type2">
            <tbody>
            	
            	<tr class="noborder">
		            <td colspan="2" class="required"><label class="validation" for="classId">表名:</label></td>
		        </tr>
		        <tr class="noborder">
		            <td class="vatop rowform">
						<select id="name" name="name">
							<option value="">请选择...</option>
							<#list tableList as table>
								<option value="${table.name}">${table.name!''} : ${table.comments!''}</option>
							</#list>
						</select>		
					</td>
		            <td class="vatop tips"></td>
		        </tr>
	            
            </tbody>
            <tfoot>
	            <tr class="tfoot">
	                <td colspan="2">
	                	<a id="submitBtn" class="btn" href="JavaScript:void(0);"> <span>下一步</span> </a>
	                	<a class="btns" href="${base}/genTable/list"> <span>返回</span> </a>
	                </td>
	            </tr>
            </tfoot>
        </table>
    </form>
    </#if>
</div>
<script type="text/javascript">
    $(function(){

        //按钮先执行验证再提交表单
        $("#submitBtn").click(function(){
            $("#genTable_form").submit();
        });
    });

</script>
</@layout.body>