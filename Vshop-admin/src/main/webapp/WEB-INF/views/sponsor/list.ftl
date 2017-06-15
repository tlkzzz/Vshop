<@layout.head>
<script type="text/javascript" src="${base}/res/js/jquery-ui/jquery.ui.js"></script>
<script type="text/javascript" src="${base}/res/js/jquery-ui/i18n/zh-CN.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="${base}/res/js/jquery-ui/themes/ui-lightness/jquery.ui.css"  />
<script type="text/javascript" src="${base}/res/js/jquery.edit.js"></script>
<script type="text/javascript">
    $(function(){
        $('#ncsubmit').click(function(){
           $('#formSearch').submit();
        });
    });
    function delSponsor(){
        var items = $("input[name='ids']:checked").length;
        if (items==0) {
            alert("请至少选择一个要删除的项目");
        }else{
            if(confirm('您确定要删除吗?')){
                $('#form_list').submit();
            }
        }
    }
    function delRow(obj){
        if(confirm('您确定要删除吗?')) {
            $(obj).parents("tr").find("td:eq(0)>input").attr("checked", true);
            $('#form_list').submit();
        }
    }
</script>
</@layout.head>
<@layout.body>
<div class="page">
    <div class="fixed-bar">
        <div class="item-title">
            <h3>赞助商</h3>
            <ul class="tab-base">
                <li><a href="JavaScript:void(0);" class="current"><span>管理</span></a></li>
                <li><a href="${base}/sponsor/forward?id=0"><span>新增</span></a></li>
            </ul>
        </div>
    </div>
    <div class="fixed-empty"></div>
    <form method="post" name="formSearch" id="formSearch" action="${base}/sponsor/list">
        <input type="hidden" name="pageNo" value="${pager.pageNo}">
        <table class="tb-type1 noborder search">
            <tbody>
            <tr>
                <th><label for="search_sponsor_name">赞助名称</label></th>
                <td><input class="txt" name="name" id="search_sponsor_name" value="${sponsor.name}" type="text" style="width:260px;"></td>
                <th><label for="search_sponsor_deleted">状态</label></th>
                <td>
                	<select name="deleted" class="class-select" id="search_sponsor_deleted" style="width:90px;">
                        <option value="">请选择...</option>
                        <option <#if sponsor.deleted == 0>selected="selected"</#if> value="0">正常</option>
                        <option <#if sponsor.deleted == 1>selected="selected"</#if> value="1">删除</option>
                    </select>
                </td>
                <td><a href="javascript:void(0);" id="ncsubmit" class="btn-search " title="检索">&nbsp;</a>
                    <#if sponsor.name != '' || sponsor.deleted != ''>
                        <a class="btns " href="${base}/sponsor/list" title="撤销检索"><span>撤销检索</span></a>
                    </#if>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
    <table class="table tb-type2" id="prompt">
        <tbody>
        <tr class="space odd">
            <th colspan="12"><div class="title"><h5>操作提示</h5><span class="arrow"></span></div></th>
        </tr>
        <tr>
            <td>
                <ul>
                    <li><!-- 当院长添加商品时可选择商品品牌，用户可根据品牌查询商品列表 --></li>
                    <li><!-- 被推荐品牌，将在前台品牌推荐模块处显示 --></li>
                    <li><!-- 在品牌列表页面，品牌将按类别分组，即具有相同类别的品牌为一组，品牌类别与品牌分类无联系 --></li>
                </ul></td>
        </tr>
        </tbody>
    </table>
    <form method="post" id='form_list' action="${base}/sponsor/delete">
        <input type="hidden" name="form_submit" value="ok" />
        <table class="table tb-type2">
            <thead>
            <tr class="thead">
                <th class="w24"></th>
                <th class="w200">赞助商名称</th>
                <th class="w60">联系人</th>
                <th class="w70">联系电话</th>
                <th class="w90 align-center">创建时间</th>
                <th class="w50 align-center">状态</th>
                <th class="w90 align-center">操作</th>
            </tr>
            </thead>
            <tbody>
            <#list pager.result as sponsor>
	            <tr class="hover edit">
	                <td>
	                	<#if sponsor.deleted == 1>
	                    	<input type="checkbox" disabled="disabled">
	                    <#else>
	                    	<input value="${sponsor.id}" class="checkitem" type="checkbox" name="ids">
	                    </#if>
	                </td>
	                <td>
	                    <span  nc_type="inline_edit" fieldname="name" modUrl="${base}/sponsor/modifyName" fieldid="${sponsor.id}" required="1" title="可编辑">${sponsor.name}</span>
	                </td>
	                <td class="contacter">
	                	<span  nc_type="inline_edit" fieldname="contacter" modUrl="${base}/sponsor/modifyName" fieldid="${sponsor.id}" required="1" title="可编辑">${sponsor.contacter}</span>
	                </td>
	                <td>
	                    ${sponsor.mobile}
				    </td>
				    <td class="align-center">
				    	<#if sponsor.createTime??>
	                     	${sponsor.createTime?string("yyyy-MM-dd HH:mm:ss")}
	                    </#if>
				    </td>
				    <td class="align-center">
	                     <#if sponsor.deleted == 1>删除<#else>正常</#if>
				    </td>
	                <td class="align-center">
	                    <a href="${base}/sponsor/forward?id=${sponsor.id}">编辑</a>
	                    &nbsp;|&nbsp;
	                    <#if sponsor.deleted == 1>
	                    	<a href="javascript:void(0);" style="color:#AFAFAF;">删除</a>
	                    <#else>
	                    	<a href="javascript:void(0);" onclick="delRow(this)">删除</a>
	                    </#if>
	                </td>
	            </tr>
            </#list>
            </tbody>
            <tfoot>
	            <tr colspan="15" class="tfoot">
	                <td><input type="checkbox" class="checkall" id="checkallBottom"></td>
	                <td colspan="16">
	                	<label for="checkallBottom">全选</label>&nbsp;&nbsp;
	                	<a href="JavaScript:void(0);" class="btn" onclick="delSponsor()"><span>删除</span></a>
	                    <@layout.pager pager/>
	                 </td>
	            </tr>
            </tfoot>
        </table>
    </form>
    <div class="clear"></div>
</div>
</div>
</@layout.body>