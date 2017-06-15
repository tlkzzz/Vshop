<@p.header title="文章中心不是异步的分页－演示商家"/>
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
<link href="${base}/res/css/layout.css" rel="stylesheet" type="text/css">
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
	
    <!--列表替换地方-->
	<div class="right" id="dataListDiv">
	
		<div class="nch-article-con">
			<div class="title-bar">
				<#assign articleClass = articleClassTag("{'acId':'${acId}'}") />
				<#if articleClass??>
					<h3>${articleClass.acName}</h3>
				</#if>
			</div>
			<ul class="nch-article-list">
				<#assign articleList = articleTag("{'acId':'${acId}','pageNo':'${pageNo}','pageSize':'${pageSize}','tagDataType':'2'}") />
				<#if articleList??>
					<#list articleList as articles>
						<li>
						<i class="icon-dot"></i>
						<#if articles.articleUrl == "">
							<a href="${base}/help/content?acId=${articles.acId}&articleId=${articles.articleId}">${articles.articleTitle }</a>
						<#else>
							<a href="${articles.articleUrl}" title="${articles.articleTitle}" target="_blank">${articles.articleTitle}</a>
						</#if>
						<time>${articles.articleTime}</time>
					</li>
					</#list>
				</#if>
			</ul>
		</div>
		<div class="tc mb20">
			<#import "/commons/tagpage.ftl" as q><!--引入分页-->
			<#if recordCount??>
			    <@q.pager pageNo=pageNo pageSize=pageSize recordCount=recordCount toURL="${toUrl}"/>
			</#if>
		</div>
		
	</div>
</div>
<div class="clear"></div>
<!-----footer------>
<@p.footer />
