<@p.header title="商品搜索－商品汇 微商家"/>
<div class="clear"></div>
<!--主页内容，调用index.html-->
<script type="text/javascript" src="${base}/res/js/search_goods.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/class_area_array.js" charset="utf-8"></script>
<script src="${base}/res/js/jquery.datalazyload.js" type="text/javascript"></script>
<link href="${base}/res/css/layout.css" rel="stylesheet" type="text/css">
<link href="${base}/res/css/select/selector.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body {
	_behavior: url(${base}/res/css/csshover.htc);
}
.selector .s-brand .sl-v-list .v-fixed {
	height: auto;
}
</style>
<div class="nch-breadcrumb-layout">
	<div class="nch-breadcrumb wrapper">
		<i class="icon-home"> </i> <span><a href="${base}">首页</a></span>
		<#assign classNavTag =newTag("classNavTag")>
		<#assign nav =classNavTag("{'classId':'${keyword}', 'searchType':'${searchType}'}")>
		<#if keyword != ''>
			<#if searchType == "gcIdSearch">
				<span class="arrow">&gt;</span> 
				<span><a href="${base}/all/class">商品分类</a></span>
				${nav}
			<#elseif searchType == "BrandIdSearch">	
				<span class="arrow">&gt;</span> 
				<span><a href="${base}/all/brand">商品品牌</a></span>
				${nav}
			<#else>
				<span class="arrow">&gt;</span>
				<span>搜索结果</span>
			</#if>
		</#if>
	</div>
</div>
<div class="nch-container wrapper">
	<div class="left">
	<!-- 通过关键词搜索不显示分类 -->
		<#if searchType == "gcIdSearch">
			<#assign goodsClassTag =newTag("goodsClassTag")>
			<#assign goodsList =goodsClassTag("{'parentid':'${keyword}'}")>
			<#if goodsList?exists && goodsList?size gt 0>
			<div class="nch-module nch-module-style02">
				<div class="title">
					<h3>商品分类</h3>
				</div>
				<div class="content">
					<ul id="files" class="tree" role="tree">
						<#list goodsList as class>
							<li role="treeitem" aria-expanded="true"><i class="tree-parent" tabindex="0"></i>
							<a href="${base}/search/goodsSearch?searchType=gcIdSearch&keyword=${class.gcId}">${class.gcName} </a></li>
						</#list>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			</#if>
		</#if>
		<!--    <div class="group-hot">
      <div class="nch-module_sidebar">
	          </div>
    </div>
    -->
    <script type="text/javascript">
    //关键词
    var keyword = "${keyword}";
    //搜索方式
    var searchType = "${searchType}";
    //分页
    var toPageNo = "";
    //初始化
    $(function(){
    	init();
    })
    
    //初始化方法
	function init(){
    	//搜索框的赋值
    	if(searchType == "keywordSearch"){
    		$("#keyword").attr("value",keyword);
    	}
    	//规格初始化
    	var specFilter = GetQueryString("specFilter");
		if(specFilter != null && specFilter.toString().length>0){
			var specAttr = specFilter.split(",");
			for(var i = 0; i < specAttr.length; i++){
				$spec = $("[filterName=goodsSpec][conditionData="+specAttr[i]+"]");
				$spec.attr("specState","1");
				var specId = $spec.attr("specId");
				var specName = $spec.attr("specName");
				var specValueName = $spec.html();
				$("#sepcFilterView").append(
						"<a href='javascript:void(0);' class='ss-item'  onClick='deleteFilter(\"goodsSpec\",\""+specAttr[i]+"\",\"specState\",this)'>"
						+"<b>"+specName+"：</b>"
						+"<em>"+specValueName+"</em>"
						+"<i></i>"
						+"</a>");
				$("[specDivName="+specId+"]").css("display","none");
			}
		}
		
    	//过滤初始化
    	var filterConditions = GetQueryString("filterConditions");
    	if(filterConditions != null && filterConditions.toString().length>0){
    		//将filterConditions转为实体类
    		var filterJson = eval(filterConditions);
    		//循环初始化
    		for(var i = 0; i < filterJson.length; i++){
    			var filterName =  filterJson[i].filterName;
    			var conditionData = filterJson[i].conditionData;
    			//地区初始化
    			if(filterName == "provinceId"){
    				$area = $("[filterName=provinceId][conditionData="+conditionData+"]");
    				$area.attr("areaState","1");
    				var areaName = $area.html();
    				$("[name=area]").html(areaName);
    			}
    			//品牌初始化
    			if(filterName == "brandId"){
    				$brand = $("[filterName=brandId][conditionData="+conditionData+"]");
    				$brand.attr("brandState","1");
    				$("[specDivName=brandDiv]").css("display","none");
    				var brandName = $brand.attr("brandName");
    				$("#sepcFilterView").append(
    						"<a href='javascript:void(0);' class='ss-item'  onClick='deleteFilter(\"brandId\",\""+conditionData+"\",\"brandState\",this)'>"
    						+"<b>品牌：</b>"
    						+"<em>"+brandName+"</em>"
    						+"<i></i>"
    						+"</a>");
    			}
    		}
    	}
    	
    	//排序初始化
    	var sortField = GetQueryString("sortField");
    	if(sortField !=null && sortField.toString().length>0){
    		var sortOrder = GetQueryString("sortOrder");
    		var $sort = $("[sortField="+sortField+"]");
    		$sort.attr("sortOrder",sortOrder);
    		$sort.attr("class",sortOrder);
    		$sort.attr("sortState","1");
    		$sort.parent().attr("class","selected");
    	}else{
    		$("[sortField=default]").parent().attr("class","selected");
    	}
    }
    
    //获得浏览器的参数
	function GetQueryString(name)
	{
	     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var r = window.location.search.substr(1).match(reg);
	     if(r!=null)return  unescape(r[2]); 
	     return null;
	}
    
    //删除指定规格搜索
    function deleteFilter(filterName,conditionData,stateName,obj){
    	$filter = $("[filterName="+filterName+"][conditionData="+conditionData+"]");
		$filter.attr(stateName,"0");
		searchGoods();
    }
    
    $(function(){
    	//过滤规格
    	$("[filterName=goodsSpec]").click(function(){
    		$(this).attr("specState","1");
    		searchGoods();
    	});
    	//过滤品牌
    	$("[filterName=brandId]").click(function(){
    		$("[brandState=1]").attr("brandState","0");
    		$(this).attr("brandState","1");
    		searchGoods();
    	});
    	//过滤地址
    	$("[filterName=provinceId]").click(function(){
    		$("[areaState=1]").attr("areaState","0");
    		if($(this).attr("conditionData") != ""){
    			$(this).attr("areaState","1");
    		}else{
    			$("[name=area]").html('所在地');
    		}
    		searchGoods();
    	});
    	
    	//排序搜索
    	$("[name=sort]").click(function(){
    		$("[sortState=1]").attr("sortState","0");
    		$(this).attr("sortState","1");
    		if($(this).attr("sortField") != "default"){
        		if($(this).attr("sortOrder") == "asc"){
        			$(this).attr("sortOrder","desc");
        		}else{
        			$(this).attr("sortOrder","asc");
        		}
    		}
    		searchGoods();
    	});
    	
    	//调用已经保存的cookie
		var json = eval("("+$.cookie("hisGoods")+")"); 
	    var list = ""; 
	    if(json!=null){
		    for(var i=0; i<json.length;i++){ 
		        list = list +'<dl class="nch-sidebar-bowers">'+
								'<dt class="goods-name"><a href="'+'${base}/product/detail?id='+json[i].goodsId+'">'+json[i].goodsName+'</a></dt>'+
								'<dd class="goods-pic"><a href="'+'${base}/product/detail?id='+json[i].goodsId+'"><img src="'+'${imgServer}'+json[i].goodsImage+'" alt="'+json[i].goodsName+'"></a></dd>'+
								'<dd class="goods-price">'+json[i].goodsStorePrice+'</dd>'+
			                  '</dl>';
		    } 
		    $("#hisgoods").html(list);
	    }
    });
    
    //排序,过滤条件搜索
    function searchGoods(){
    	
    	//规格筛选
    	var $spec = $("[specState=1]");
    	var specFilter = "";
    	if($spec[0] != undefined){
    		$spec.each(function(){
        		var conditionData = $(this).attr("conditionData");
        		specFilter += conditionData + ",";
    		});
    		specFilter = specFilter.substring(0, specFilter.length - 1);
    	}
    	
    	/*
    	* 获得过滤
    	*/
    	var filterConditions = "[";
    	//地区
    	var $area = $("[areaState=1]");
    	if($area[0] != undefined){
    		var filterName = $area.attr("filterName");
    		var conditionData = $area.attr("conditionData");
    		filterConditions += "{\"filterName\":\"provinceId\",\"conditionData\":" + "\"" + conditionData + "\"},";
    	}
    	//品牌
    	var $brand = $("[brandState=1]");
    	if($brand[0] != undefined){
    		var filterName = $brand.attr("filterName");
    		var conditionData = $brand.attr("conditionData");
    		filterConditions += "{\"filterName\":\"brandId\",\"conditionData\":" + "\"" + conditionData + "\"},";
    	}
    	
    	//获得排序
    	var $sort = $("[sortState=1]");
    	var sortArgs = "";
    	if($sort[0] != undefined){
	    	var sortField = $sort.attr("sortField");
	    	var sortOrder = $sort.attr("sortOrder");
	    	if(sortField != "default"){
	    		sortArgs = 'sortField=' + sortField + '&sortOrder=' + sortOrder;
	    	}
    	}
    	//url
    	var url = '${base}/search/goodsSearch?keyword=' + keyword + '&searchType=' + searchType
    	//排序
    	if(sortArgs != ""){
    		url += '&' + sortArgs;
    	}
    	//规格
    	if(specFilter != ""){
    		url += '&specFilter=' + specFilter;
    	}
    	//过滤
    	if(filterConditions != "["){
    		filterConditions = filterConditions.substring(0, filterConditions.length-1);
    		filterConditions += "]";
    		url += '&filterConditions=' + filterConditions;
    	}
    	//分页
    	if(toPageNo != ""){
    		url += '&pageNo=' + toPageNo;
    	}
    	//搜索
    	window.location.href = url;
    }
    </script>
		
		<div class="nch-module nch-module-style03">
			<div class="title">
				<h3>浏览过的商品</h3>
			</div>
			<div class="content" id="hisgoods">
			</div>
		</div>
		<#--<div class="nch-module_sidebar"> 
	      <a href=""><img style="width: 200px; height: 350px; display: inline;" border="0" class="scrollLoading" src="${imgServer}/upload/img/adv/8f2b628c139627a3d3426200c59a495b.jpg" 
	      data-url="${imgServer}/upload/img/adv/8f2b628c139627a3d3426200c59a495b.jpg" alt=""></a>
	      <div class="clear"></div>
	    </div> -->
	</div>
	<div class="right" id="result">
		<!--属性 开始 -->
		<div>
			<div class="selector-set" id="sepcFilterView">
			</div>
		</div> 
		<!-- 商品搜索 -->
		<#assign goodsSearchTag = newTag("goodsSearchTag")>
		<#assign lucenePage = goodsSearchTag("{'searchType':'${searchType}','keyword':'${keyword}','pageNo':'${pageNo}','filterConditions':'${filterConditions}','specFilter':'${specFilter}','sortField':'${sortField}','sortOrder':'${sortOrder}'}")>
		
		<#if lucenePage!="" && lucenePage??>
		<!-- 规格 -->
		<div class="nch-sortbar-spec">
			<div id="J_selector" class="selector">
			<!-- 品牌 -->
				<#if lucenePage.brandList??>
				<#if lucenePage.brandList?size gt 0>
					<div specDivName="brandDiv" class="J_selectorLine s-brand">
						<div class="sl-wrap">
							<div class="sl-key">
								<span>品牌：</span>
							</div>
							<div class="sl-value">
								<div class="clr"></div>
								<div class="sl-v-list">
									<ul class="J_valueList v-fixed">
										<#list lucenePage.brandList as bl>
											<li data-initial="x">
											<a href="javascript:void(0);" brandName="${bl.brandName}" brandState="0" filterName="brandId" conditionData="${bl.brandId}">
												<i></i>
												${bl.brandName}
											</a>
											</li>
										</#list>
									</ul>
								</div>
							</div>
						</div>
					</div>
					</#if>
				</#if>
				<#if lucenePage.specList??>
					<#list lucenePage.specList as sl>
						<div specDivName="${sl.spId}" class="J_selectorLine s-line J_selectorFold">
							<div class="sl-wrap">
								<div class="sl-key">
									<span name="spec" spId="${sl.spId}">${sl.spName}：</span>
								</div>
								<div class="sl-value">
									<div class="clr"></div>
									<div class="sl-v-list">
										<ul class="J_valueList v-fixed">
											<#list sl.specValueList as svl>
												<li data-initial="x">
												<a href="javascript:void(0);" specName="${sl.spName}" specId="${sl.spId}" specState="0" name="spec" filterName="goodsSpec" conditionData="${svl.spValueId}">
													<i></i>
													${svl.spValueName}
												</a>
												</li>
											</#list>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</#list>
				</#if>
				<!-- <div style="display: block;" overfour="false" data-id="413" class="J_selectorLine s-line J_selectorFold">
					<div class="sl-wrap">
						<div class="sl-key">
							<span>尺寸：</span>
						</div>
						<div class="sl-value">
							<div class="sl-v-list">
								<ul class="J_valueList">
									<li>
									<a href="javascript:void(0);" rel="nofollow"><i></i>65英寸及以上</a>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div> -->
			</div>
		</div>
		</#if>
		<!--属性 结束 -->
		
		<div class="shop_con_list" id="main-nav-holder">
			<nav class="sort-bar" id="main-nav">
				<div class="nch-sortbar-array">
					排序方式：
					<ul>
						<li>
							<a href="javascript:void(0)" class="nobg" sortState="0" name="sort" sortField="default" title="默认排序">默认<i></i></a>
						</li>
						<li>
							<a href="javascript:void(0)" sortState="0" name="sort" sortField="createTime" sortOrder="asc">上架时间<i></i></a>
						</li>
						<li>
							<a href="javascript:void(0)" sortState="0" name="sort" sortField="salenum" sortOrder="asc">销量<i></i></a>
						</li>
						<li>
							<a href="javascript:void(0)" sortState="0" name="sort" sortField="goodsClick" sortOrder="asc">人气<i></i></a>
						</li>
						<li>
							<a href="javascript:void(0)" sortState="0" name="sort" sortField="" sortOrder="asc">信用<i></i></a>
						</li>
						<li>
							<a href="javascript:void(0)" sortState="0" name="sort" sortField="goodsStorePrice" sortOrder="desc">价格<i></i></a>
						</li>
					</ul>
				</div>
				<div class="nch-sortbar-owner">
					商品类型： 
					<span>
						<a href="javascript:void(0)" onClick="javascript:dropParam('promotion');" title="不限" class="selected"><i></i>
							全部
						</a>
					</span> 
					<span>
						<a href="javascript:void(0)"  title="限时折扣"><i></i><!-- onClick="javascript:replaceParam('promotion','xianshi');" -->
							限时折扣
						</a>
					</span> 
					<!-- <span>
						<a href="javascript:void(0)"  title="团购"><i></i>onClick="javascript:replaceParam('promotion','groupbuy');"
							团购
						</a>
					</span> -->
				</div>
				<div class="nch-sortbar-location">
					商品所在地：
					<div class="select-layer">
						<div class="holder">
							<em nc_type="area_name" name="area">所在地<!-- 所在地 --></em>
						</div>
						<!-- <div class="selected">
							<a nc_type="area_name">所在地所在地</a>
						</div> -->
						<i class="direction"></i>
						<ul class="options">
							<div class="filter-detailc" id="addressDraw">
								<dl class="location-hots">
									<#assign provinceTag = newTag("provinceTag")>
									<#assign province = provinceTag()>
									<#if province??>
										<#list province as p>
											<#if p_index%4==0>
												<br/>
												<dd>
													<a href="javascript:void(0);" areaState="0" filterName="provinceId" conditionData="${p.areaId}">${p.areaName}</a>
												</dd>
											<#else>
												<dd>
													<a href="javascript:void(0);" areaState="0" filterName="provinceId" conditionData="${p.areaId}">${p.areaName}</a>
												</dd>
											</#if>
										</#list>
									</#if>
								</dl>
								<p class="oreder-default">
									<a href="javascript:void(0);" filterName="provinceId" conditionData="">不限地区</a>
								</p>
							</div>
						</ul>
					</div>
				</div>
			</nav>
			<!-- 商品列表循环  -->
<div id="div_lazyload">
	<textarea class="text-lazyload" id="div_lazyload" style="display: none;">
	<style type="text/css">
	#box {
		background: #FFF;
		width: 238px;
		height: 410px;
		margin: -390px 0 0 0;
		display: block;
		border: solid 4px #D93600;
		position: absolute;
		z-index: 999;
		opacity: .5
	}
	</style>
	 	<#if lucenePage!="" && lucenePage??>
		    <#if lucenePage.result??>
			<div class="squares" nc_type="current_display_mode">
		    <ul class="list_pic">
		    	<#list lucenePage.result as goods>
			        <li class="item">
				      <div class="goods-content" nctype_goods="${goods.goodsId}" nctype_store="${goods.storeId}">
				        <div class="goods-pic">
				        <a href="${base}/product/detail?id=${goods.goodsId}" target="_blank" title="${goods.goodsName}">
				        <img src=<#if goods.goodsImage??>"${imgServer}${goods.goodsImage}"<#else>"${base}/res/images/common/default_goods_image.gif_small.gif"</#if> title="${goods.goodsName}" alt="${goods.goodsName}"></a>
						</div>
						 <div class="goods-info" style="top: 230px;">
							<div class="goods-pic-scroll-show">
								<ul>
								   <!-- S 第一个后其它的有图片时显示 -->
								<#list goods.goodsImageMore?split(",") as image>
								<#if image!=''>
				                	<li><a href="javascript:void(0);"><img src="${imgServer}/${image}" alt="" onload="javascript:DrawImage(this,60,60);"></a></li>
								</#if>
								</#list>
								</ul>
							</div>
				         <div class="goods-name">
				         	<a href="${base}/product/detail?id=${goods.goodsId}" target="_blank" title="${goods.goodsName}"><em>${goods.goodsName}</em></a>
						 </div>
				        <div class="goods-price">
					        <!-- S 促销价格显示 -->
							<em class="sale-price" title="">&yen;${goods.goodsStorePrice}</em>
							<!-- E 促销价格显示 --> 
							<span class="raty" data-score="0"></span>
				      	</div>
				        <div class="sell-stat">
				            <ul>
				              <li>
				              <a href="#" target="_blank" class="status">${goods.salenum}</a><p>商品销量</p>
							  </li>
				              <li>
				              <a href="#" target="_blank">${goods.commentnum}</a><p>用户评论</p>
							  </li>
							  <li>
							  <!-- <a class="kefu" href="http://wpa.qq.com/msgrd?v=3&uin=905669187&Site=b2b2c.leimingtech.com&Menu=yes" target="_blank">
							  <img src="http://wpa.qq.com/pa?p=2:1234567:52" alt=""></a> <p>在线客服</p> -->
				              </li>
				            </ul>
				          </div>
				          <div class="store">
				            <#if goods.storeId != '0'>
								<a href="javascript:void(0);" class="name" title="${goods.storeName}">${goods.storeName}</a>
							<#else>
								<a href="javascript:void(0);" class="name" style="font: 12px/18px '\5B8B\4F53';color: #fff;background: #e4393c;" title="${goods.storeName}">平台自营</a>
							</#if>
						  </div>
						  <div class="add-cart">
								<a href="${base}/product/detail?id=${goods.goodsId}"><i class="icon-cart"></i>立即购买</a>
						  </div>
							<!-- <form id="groupbuy_form" method="get" action="http://192.168.1.230/#?act=show_groupbuy&op=groupbuy_buy">
								<input id="act" name="act" type="hidden" value="show_groupbuy" />
								<input id="op" name="op" type="hidden" value="groupbuy_buy" />
								<input id="group_id" name="group_id" type="hidden" />
								<input id="groupbuy_spec_id" name="groupbuy_spec_id" type="hidden" />
								<input id="groupbuy_quantity" name="groupbuy_quantity" type="hidden" />
							</form>
							<form id="buynow_form" method="get" action="http://192.168.1.230/#?act=buynow">
								<input id="act" name="act" type="hidden" value="buynow" />
								<input id="buynow_spec_id" name="buynow_spec_id" type="hidden" />
								<input id="buynow_quantity" name="buynow_quantity" type="hidden" value='1' />
							</form> -->
				        </div>
				      </div>
			    	</li>
		    	</#list>
			 <div class="clear"></div>
		  	</ul>
		  	</div>
		  <#else>
		  	<div class="squares" nc_type="current_display_mode" >
			    <div class="no-results">没有找到符合条件的商品</div>
			</div>
		 </#if>
	</#if>	   
	</textarea>
</div>
			<div class="tc mt20 mb20">
                <#import "/search/search-page.ftl" as q><!--引入分页-->
                <#if lucenePage!="" && lucenePage??>
                <#if lucenePage.totalRows??>
                    <@q.pager pageNo=lucenePage.pageNo pageSize=lucenePage.pageSize recordCount=lucenePage.totalRows toURL="${toUrl}"/>
                </#if>
                </#if>
			</div>
			
		</div>
	</div>
</div>
<script type="text/javascript" src="${base}/res/js/jquery.raty/jquery.raty.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.raty').raty({
			path : "${base}/res/js/jquery.raty/img",
			hints : [ '一般', '不错', "很好", '满意', '非常满意' ],
			readOnly : true,
			width : 80,
			score : function() {
				return $(this).attr('data-score');
			}
		});
	});
</script>
<script src="${base}/res/js/waypoints.js"></script>
<script src="${base}/res/js/search_category_menu.js"></script>
<script type="text/javascript">
	var defaultSmallGoodsImage = 'upload/common/default_goods_image.gif_small.gif';
	var defaultTinyGoodsImage = 'upload/common/default_goods_image.gif_tiny.gif';

	//浮动导航  waypoints.js
	$('#main-nav-holder').waypoint(function(event, direction) {
		$(this).parent().toggleClass('sticky', direction === "down");
		event.stopPropagation();
	});
	// 单行显示更多
	$('span[nc_type="show"]').click(function() {
		s = $(this).parents('dd').prev().find('li[nc_type="none"]');
		if (s.css('display') == 'none') {
			s.show();
			$(this).html('<i class="icon-arrow-up2"></i>收起');
		} else {
			s.hide();
			$(this).html('<i class="icon-arrow-down2"></i>更多');
		}
	});
</script>
<div class="clear"></div>
<!--底部版权，调用footer-->
<@p.footer />
