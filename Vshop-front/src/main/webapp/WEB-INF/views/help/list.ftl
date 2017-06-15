<div class="nch-article-con">
	<div class="title-bar">
		<#assign articleClassTag = newTag("articleClassTag")/>
		<#assign articleClass = articleClassTag("{'acId':'${acId}'}") />
		<#if articleClass??>
			<h3>${articleClass.acName}</h3>
		</#if>
	</div>
	<ul class="nch-article-list">
		<#assign articleTag = newTag("articleTag") />
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
	<#--获取总条数-->
	<#assign recordCount = articleTag("{'tagDataType':'5','acId':'${acId}'}") />
	<#import "/commons/tagpage.ftl" as q> <#--引入分页-->
	<#if recordCount??>
	    <@q.pager pageNo=pageNo pageSize=pageSize recordCount=recordCount toURL="${toUrl}"/>
	</#if>
</div>