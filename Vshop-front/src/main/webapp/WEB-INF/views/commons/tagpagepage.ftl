<#-- 无异步分页 -->

<#macro pager pageNo pageSize toURL recordCount>
<#-- 定义局部变量pageCount保存总页数 -->
    <#assign pageCount=((recordCount + pageSize - 1) / pageSize)?int>
    <#if recordCount==0><#return/></#if>
<#-- 输出分页样式 -->

<#-- 页号越界处理 -->
    <#if (pageNo > pageCount)>
        <#assign pageNo=pageCount>
    </#if>
    <#if (pageNo < 1)>
        <#assign pageNo=1>
    </#if>
    
<div class="pagination">
    <form method="post" action="" name="qPagerForm">
    <#-- 把请求中的所有参数当作隐藏表单域(无法解决一个参数对应多个值的情况) -->
        <#list RequestParameters?keys as key>
            <#if (key!="pageNo" && RequestParameters[key]??)>
                <input type="hidden" name="${key}" value="${RequestParameters[key]}"/>
            </#if>
        </#list>
        <input type="hidden" name="pageNo" value="${pageNo}"/>
        <ul>
        <#-- 上一页处理 -->
            <#if (pageNo == 1)>
                <li><span>首页</span></li>
            <#--<li><span>上一页</span></li>-->
            <#else>
                <li><a href="javascript:void(0)" class="demo" onclick="turnOverPage(1)" title="首页"><span>首页</span></a></li>
                <li><a href="javascript:void(0)" class="demo" onclick="turnOverPage(${pageNo - 1})" title="上一页"><span>上一页</span></a></li>
            </#if>
        <#-- 如果前面页数过多,显示... -->
            <#assign start=1>
            <#if (pageNo > 4)>
                <#assign start=(pageNo - 1)>
                <li><a class='demo' href="javascript:void(0)"  onclick="turnOverPage(1)"><span>1</span></a></li>
                <li><a class='demo' href="javascript:void(0)"  onclick="turnOverPage(2)"><span>2</span></a></li>
                <li><span>&hellip;</span> </li>
            </#if>
        <#-- 显示当前页号和它附近的页号 -->
            <#assign end=(pageNo + 1)>
            <#if (end > pageCount)>
                <#assign end=pageCount>
            </#if>
            <#list start..end as i>
                <#if (pageNo==i)>
                    <li><span class="currentpage">${i}</span></li>
                <#else>
                    <li><a class='demo' href="javascript:void(0)" onclick="turnOverPage(${i})"><span>${i}</span></a></li>
                </#if>
            </#list>
        <#-- 如果后面页数过多,显示... -->
            <#if (end < pageCount - 2)>
                <li><span>&hellip;</span> </li>
            </#if>
            <#if (end < pageCount - 1)>
                <li><a class='demo' href="javascript:void(0)" onclick="turnOverPage(${pageCount - 1})"><span>${pageCount-1}</span></a></li>
            </#if>
            <#if (end < pageCount)>
                <li><a class='demo' href="javascript:void(0)" onclick="turnOverPage(${pageCount})"><span>${pageCount}</span></a></li>
            </#if>
        <#-- 下一页处理 -->
            <#if (pageNo == pageCount)>
            <#--<li><span>下一页</span> </li>-->
                <li><span>末页</span> </li>
            <#else>
                <li><a class="demo" href="javascript:void(0)" onclick="turnOverPage(${pageNo + 1})" title="下一页"><span>下一页</span></a></li>
                <li><a class="demo" href="javascript:void(0)" onclick="turnOverPage(${((recordCount+pageSize -1)/pageSize)?int})" title="末页"><span>末页</span></a></li>
            </#if>
        </ul>
    </form>
</div>
<script language="javascript">
    function turnOverPage(no){
        if(no>${pageCount}){no=${pageCount};}
        if(no<1){no=1;}
        top.location.href = "${base}${toURL}?acId=${acId}&pageNo="+no;
    }
</script>
</#macro>