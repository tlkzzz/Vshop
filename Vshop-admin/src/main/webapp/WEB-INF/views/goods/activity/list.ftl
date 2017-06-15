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
            <h3>商品活动管理</h3>
            <ul class="tab-base">
                <li><a class="current" href="JavaScript:void(0);"><span>管理</span></a></li>
                <li><a href="${base}/goods/activity/forward?id=0"><span>新增</span></a></li>
            </ul>
        </div>
    </div>
    <div class="fixed-empty"></div>
    <form method="post" name="formSearch" id="formSearch" action="${base}/goods/activity/list">
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
                <li>微信平台活动设置管理功能</li>
            </ul></td>
        </tr>
        </tbody>
    </table>
    <form id="form_list" method="get" action="${base}/goods/activity/delete">
        <table class="table tb-type2">
            <thead>
            <tr class="thead">
                <th></th>
                <th class="align-center">活动名称</th>
                <th class="align-center">活动banner</th>
                <th class="align-center">活动状态</th>
                <th class="align-center">创建时间</th>
                <th class="align-center">活动banner2</th>
                <th class="align-center">更新时间</th>
                <th class="align-center">操作</th>
            </tr>
            </thead>
            <tbody>
            <#list pager.result as activity>
            <tr class="hover edit">
                <td class="w24"><input type="checkbox" name="ids" value="${activity.activityId}" class="checkitem"/></td>
                <td class="align-center"><span>${activity.activityName}</span></td>
                <td class="align-center"><span>
                	<#if activity.activityBanner ??&& activity.activityBanner!=''>
                         <img src="${imgServer}${activity.activityBanner}" height="100px" class="img">
                    <#else>
                         <img src="${base}/images/nopic.png" width="100px" height="100px" class="img">
                    </#if>
                </span></td>
                <td class="align-center"><span>
                	<#if activity.activityUse = 0><font color="red">停用</font></#if>
                	<#if activity.activityUse = 1><font color="blue">启用</font></#if>
                </span></td>
                <td class="align-center"><span>${activity.createTimeStr}</span></td>
                <td class="align-center"><span>
                	<#if activity.activityBanner2 ??&& activity.activityBanner2!=''>
                         <img src="${imgServer}${activity.activityBanner2}" height="100px" class="img">
                    <#else>
                         <img src="${base}/images/nopic.png" width="100px" height="100px" class="img">
                    </#if>
                </span></td>
                <td class="align-center"><span>${activity.lastUpdateTimeStr}</span></td>
                <td class="w96 align-center"><a href="${base}/goods/activity/forward?id=${activity.activityId}">编辑</a> | <a href="javascript:void(0)" onclick="delRow(this)">删除</a> </td>
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