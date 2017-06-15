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
    function delSpec(){
        var items = $("input[name='ids']:checked").length;
        if (items==0) {
            alert("请至少选择一个要删除的项目");
        } else {
            if(confirm('您确定要删除吗?')) {
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
            <h3>收发码管理</h3>
            <ul class="tab-base">
                <li><a class="current" href="JavaScript:void(0);"><span>管理</span></a></li>
                <li><a href="${base}/goods/consumercode/forward?id=0"><span>新增本平台码</span></a></li>
            </ul>
        </div>
    </div>
    <div class="fixed-empty"></div>
    <form method="post" name="formSearch" id="formSearch" action="${base}/goods/consumercode/list">
        <input type="hidden" name="pageNo" value="${pager.pageNo}">
    </form>
    <table id="prompt" class="table tb-type2">
        <tbody>
        <tr class="space odd">
            <th colspan="12" class="nobg"> <div class="title">
                <h5>操作提示</h5>
                <span class="arrow"></span> </div>
            </th>
        </tr>
        <tr class="odd">
            <td><ul>
                <li>操作提示1</li>
                <li>操作提示2</li>
            </ul></td>
        </tr>
        </tbody>
    </table>
    <form id="form_list" method="get" action="${base}/goods/consumercode/delete">
        <table class="table tb-type2">
            <thead>
            <tr class="thead">
                <th></th>
                <th class="align-center">商品消费码</th>
                <th class="align-center">码来源</th>
                <th class="align-center">管理员Id/会员Id</th>
                <th class="align-center">创建时间</th>
                <th class="align-center">状态</th>
                <th class="align-center">操作</th>
            </tr>
            </thead>
            <tbody>
            <#list pager.result as consumercode>
            <tr class="hover edit">
                <td class="w24"><input type="checkbox" name="ids" value="${consumercode.consumerCodeId}" class="checkitem"/></td>
                <td class="align-center"><span>${consumercode.consumerCodeBunch}</span></td>
                <td class="align-center"><span>
                	<#if consumercode.codeSource = 1>本平台</#if>
                	<#if consumercode.codeSource = 3>第三方</#if>
                </span></td>
                <td class="align-center"><span>${consumercode.memberId}</span></td>
                <td class="align-center"><span>${consumercode.codeCreateTimeStr}</span></td>
                <td class="align-center"><span>
                	<#if consumercode.codeStatus = 0><font color="blue">未下发</font></#if>
                	<#if consumercode.codeStatus = 1><font color="green">下发未用</font></#if>
                	<#if consumercode.codeStatus = 2><font color="orange">下发已用</font></#if>
                	<#if consumercode.codeStatus = 3><font color="gray">失效作废</font></#if>
                </span></td>
                <td class="w96 align-center"><a href="${base}/goods/consumercode/forward?id=${consumercode.consumerCodeId}">编辑</a> | <a href="javascript:void(0)" onclick="delRow(this)">删除</a> </td>
            </tr>
            </#list>
            </tbody>
            <tfoot>
            <tr>
                <td><input type="checkbox" class="checkall" id="checkallBottom" /></td>
                <td id="dataFuncs" colspan="16"><label for="checkallBottom">全选</label>
                    <a class="btn" href="JavaScript:void(0);" onclick="delSpec()"> <span>删除</span> </a>
                <@layout.pager pager/></td>
            <tr>
            </tfoot>
        </table>
    </form>
</div>
</@layout.body>