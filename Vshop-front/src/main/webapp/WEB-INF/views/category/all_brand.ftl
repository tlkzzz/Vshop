<@p.header title="品牌 - 商品汇平台管理中心" />
<div class="clear"></div>
<!--主页内容，调用index-->
<div class="nch-breadcrumb-layout">
	<div class="nch-breadcrumb wrapper">
		<i class="icon-home"> </i> <span><a href="${base}">首页</a></span>
		<span class="arrow"> &gt;</span> <span>所有商品</span>
	</div>
</div>
<link href="${base}/res/css/layout.css"
	rel="stylesheet" type="text/css">
<div class="nch-container wrapper">
	<div class="nch-all-menu">
		<ul class="tab-bar">
			<li><a href="${base}/all/class">全部商品分类</a></li>
			<li class="current"><a href="${base}/all/brand">全部品牌</a></li>
			<li><a href="${base}/search/goodsSearch">全部商品</a></li>
		</ul>
	</div>
	<div class="nch-recommend-borand">
		<div class="title" title="推荐品牌"></div>
		<div class="nch-barnd-list">
		
		<#assign brandTag = newTag("brandTag") /> 
		<#assign brandList = brandTag("{'classid':''}")/>
			<ul>
			<#if brandList??>
				<#list brandList as brands >
					<#if brands.brandApply ??&& brands.brandApply==1>
		            <li>
						<dl>
							<dt>
								<a href="${base}/search/goodsSearch?searchType=BrandIdSearch&keyword=${brands.brandId}"><img src="${imgServer}/${brands.brandPic}" alt="${brands.brandName}"></a>
							</dt>
							<dd>
								<a href="${base}/search/goodsSearch?searchType=BrandIdSearch&keyword=${brands.brandId}">${brands.brandName}</a>
							</dd>
						</dl>
					</li>  
		            </#if>
				</#list>
			</#if>
			</ul>
		</div>
	</div>
	
</div>
<script>
jQuery(".nch-brand-class").slide({titCell:".hd ul li",mainCell:".bd",titOnClassName:"tabs-selected"});
</script>

<div class="clear"></div>
<!-----footer------>
<@p.footer />
