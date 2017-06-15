<@p.header title="文章中心－演示商场"/>
<div class="clear"></div>
<!--主页内容，调用index.php-->
<div class="nch-breadcrumb-layout">
<!-- 单篇文章 -->
<#assign articleClassTag = newTag("articleClassTag")/>
<#assign articleClass = articleClassTag("{'acId':'${acId}'}") />
	<div class="nch-breadcrumb wrapper">
		<i class="icon-home"> </i> <span><a href="${base }">首页</a></span>
		<span class="arrow">&gt;</span>
		<span><#if articleClass??><a href="${base}/help/index?acId=${articleClass.acId}">${articleClass.acName}</a></#if></span>
	</div>
</div>
<link href="${base}/res/css/layout.css"
	rel="stylesheet" type="text/css">
<div class="nch-container wrapper">
	<div class="left">
		<div class="nch-module nch-module-style01">
			<div class="title">
				<h3>文章分类</h3>
			</div>
			<div class="content">
				<ul class="nch-sidebar-article-class">
					<#assign listTag = articleClassTag("{'tagDataType':'1'}") />
					<#if listTag??>
						<#list listTag as articleClass >
							<li><a href="${base}/help/index?acId=${articleClass.acId}">${articleClass.acName}</a></li>
						</#list>
					</#if>
				</ul>
			</div>
		</div>
		<#assign articleTag = newTag("articleTag") />
		<div class="nch-module nch-module-style03">
			<div class="title">
				<h3>最新文章</h3>
			</div>
			<div class="content">
				<ul class="nch-sidebar-article-list">
					<#assign articleList = articleTag("{'pageNo':'${pageNo}','pageSize':'10','tagDataType':'2'}") />
					<#if articleList??>
						<#list articleList as articles>
							<li>
								<i class="icon-dot"></i>
								<#if articles.articleUrl == "">
									<a href="${base}/help/content?acId=${articles.acId}&articleId=${articles.articleId}">${articles.articleTitle }</a>
								<#else>
									<a href="${articles.articleUrl}" title="${articles.articleTitle}" target="_blank">${articles.articleTitle}</a>
								</#if>
								
							</li>
						</#list>
					</#if>
				</ul>
			</div>
		</div>
	</div>
	
	<form id="acct-form" method="post" action="${base}/help/index" name ="queryListForm">
        <input type="hidden" name="div" id="div" value ="#dataListDiv"/>
        <input type="hidden" name="acId" id="acId" value ="${acId}"/>
    </form>
    <!--列表替换地方-->
	<div class="right" id="dataListDiv">
		
	</div>
</div>
<script type="text/javascript">
    /*界面初始化*/
    $(function(){
        initDataList();
    });

    /*初始化*/
    function initDataList(){
        var div = "#dataListDiv";//显示的list 数据div id 必须传递
        $.ajax({
            async:false,
            url:"${base}/help/list?acId=${acId}",//默认加载list 页面
            data:{div:div},
            error:function(){
                layer.msg("通讯失败!" , 1 , 9 );
            },
            type: "POST",
            success: function(data){
                $(div).empty();
                $(div).html(data);
                $(div).hide();
                $(div).fadeIn(300);
            }
        });
    }
</script>
<div class="clear"></div>
<!-----footer------>
<@p.footer />
