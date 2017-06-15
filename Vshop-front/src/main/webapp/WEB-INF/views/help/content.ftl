<@p.header title="商品汇平台管理中心-内容"/>
<div class="clear"></div>
<!--主页内容-->
<#assign articleTag = newTag("articleTag") />
<#assign article = articleTag("{'articleId':'${articleId}'}") />

<#assign articleClassTag = newTag("articleClassTag")/>
<#assign articleClass = articleClassTag("{'acId':'${article.acId}'}") />
<div class="nch-breadcrumb-layout">
	<div class="nch-breadcrumb wrapper">
		<i class="icon-home"> </i> <span><a href="${base }">首页</a></span>
		<span class="arrow">&gt;</span>
		<span><#if articleClass??><a href="${base}/help/index?acId=${articleClass.acId}">${articleClass.acName}</a></#if></span>
		<span class="arrow">&gt;</span><span>文章内容</span>
	</div>
</div>
<link href="${base }/res/css/layout.css"
	rel="stylesheet" type="text/css">
<!---文章内容--->
<div class="nch-container wrapper">
	<!---左侧--->
	<div class="left">
		<div class="nch-module nch-module-style01">
			<div class="title">
				<h3>文章分类</h3>
			</div>
			<div class="content">
				<ul class="nch-sidebar-article-class">
				<#assign articleClassTag = newTag("articleClassTag")/>
				<#assign listTag = articleClassTag("{'tagDataType':'1'}") />
				<#if listTag??>
					<#list listTag as articleClass >
						<li><a href="${base}/help/index?acId=${articleClass.acId}">${articleClass.acName}</a></li>
					</#list>
				</#if>
				</ul>
			</div>
		</div>
		
		<div class="nch-module nch-module-style03">
			<div class="title">
				<h3>最新文章</h3>
			</div>
			<div class="content">
				<ul class="nch-sidebar-article-list">
				<#assign articleList = articleTag("{'start':'0','pageSize':10,'tagDataType':'2'}") />
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
	<!---右侧--->
	<div class="right">
		<div class="nch-article-con">
		
			<#if article?? && article!'' >
				<h1>${article.articleTitle!''}</h1>
				<h2>${article.articleTime!''}</h2>
				<div class="default">
					<div>
					${article.articleContent!''}
					</div>
				</div>
			</#if>
			<!-- <div class="more_article">
				<span class="fl">上一篇: <a
					href="#?act=article&article_id=8">忘记密码</a> <span>2011-01-11
						09:28</span>
				</span> <span class="fr">下一篇: <a
					href="#?act=article&article_id=6">如何注册成为会员</a> <span>2011-01-11
						09:25</span>
				</span>
			</div> -->
		</div>
	</div>
</div>
<div class="clear"></div>
<!-----footer------>
<@p.footer />
