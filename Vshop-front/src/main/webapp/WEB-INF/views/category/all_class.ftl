<@p.header title="分类 - 商品汇平台管理中心" />

<div class="clear"></div>
<!--主页内容，调用#-->
<div class="nch-breadcrumb-layout">
	<div class="nch-breadcrumb wrapper">
		<i class="icon-home"> </i> <span><a href="${base}">首页</a></span><span class="arrow">&gt;</span> <span>商品分类</span>
	</div>
</div>
<link href="${base}/res/css/layout.css" rel="stylesheet" type="text/css">
<div class="nch-container wrapper">
	<div class="nch-all-menu">
		<ul class="tab-bar">
			<li class="current"><a href="${base}/all/class">全部商品分类</a></li>
			<li><a href="${base}/all/brand">全部品牌</a></li>
			<li><a href="${base}/search/goodsSearch">全部商品</a></li>
		</ul>
	</div>
	<div class="nch-category-all">
		<ul class="nch-category-container masonry" id="categoryList">
		
		<#assign goodsClassTag = newTag("goodsClassTag") />
		<#assign goodsList = goodsClassTag("") />
		<#if goodsList?exists && goodsList?size gt 0>
			<#list goodsList as class>
			<li class="classes masonry-brick">
				<div class="title">
					<i></i> <a href="${base}/search/goodsSearch?searchType=gcIdSearch&keyword=${class.gcId}" title="${class.gcName}">${class.gcName}</a>
				</div>
				<#if class.hasChild gt 0>
				<#assign firstchild = class.classList>
				<#list firstchild as firstclass>
					<dl>
						<dt>
							<a href="${base}/search/goodsSearch?searchType=gcIdSearch&keyword=${firstclass.gcId}" title="${firstclass.gcName}">${firstclass.gcName}</a>
						</dt>
						<dd>
							<#if firstclass.hasChild gt 0>
	                    	<#assign secondchild = firstclass.classList>
							<#list secondchild as secondclass>
							<a href="${base}/search/goodsSearch?searchType=gcIdSearch&keyword=${secondclass.gcId}" title="${secondclass.gcName}">${secondclass.gcName}</a> 
							</#list>
							</#if>
						</dd>
					</dl>
				</#list>
				</#if>
			</li>
			</#list>
		</#if>
			
		</ul>
	</div>
</div>
<script>
$(function(){
	$("#categoryList").imagesLoaded( function(){
		$("#categoryList").masonry({
			itemSelector : '.classes'
		});
	});
});
</script>
<div class="clear"></div>
<!-----footer------>
<@p.footer />