<@p.header title="商品搜索－商品汇 微商家"/>
<div class="clear"></div>
<!--主页内容，调用index-->
<script type="text/javascript" src="${base}/res/js/search_goods.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/class_area_array.js" charset="utf-8"></script>
<script src="${base}/res/js/jquery.datalazyload.js" type="text/javascript"></script>
<link href="${base}/res/css/layout.css" rel="stylesheet" type="text/css">
<link href="${base}/res/css/select/selector.css" rel="stylesheet" type="text/css" />
<link href="${base}/res/css/shop.css" rel="stylesheet" type="text/css">
<style type="text/css">
body {
	_behavior: url(${base}/res/css/csshover.htc);
}
</style>
<div class="nch-breadcrumb-layout">
<div class="nch-container wrapper">
	<div class="left">
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
    		$("#keyword").attr("value",keyword);
			$("[searchModel]").each(function(){
				$(this).attr("class","");
			});
			$("[searchModel=storeSearch]").attr("class","current");
			$(".search-form").attr("action",'${base}/search/storeSearch');
    	
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
    
    $(function(){
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
    	var url = '${base}/search/storeSearch?keyword=' + keyword
    	//排序
    	if(sortArgs != ""){
    		url += '&' + sortArgs;
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
	</div>
	<div class="sort-bar" id="result">
		<!-- 商品搜索 -->
		<#assign storeSearchTag = newTag("storeSearchTag")>
		<#assign lucenePage = storeSearchTag("{'keyword':'${keyword}','pageNo':'${pageNo}','filterConditions':'${filterConditions}','sortField':'${sortField}','sortOrder':'${sortOrder}'}")>
		
		<div class="shop_con_list" id="main-nav-holder">
			<nav class="nc-gl-sort-bar" id="main-nav" style="left: 100px;">
				<div class="nch-sortbar-array">
					排序方式：
					<ul>
						<li>
							<a href="javascript:void(0)" class="nobg" sortState="0" name="sort" sortField="default" title="默认排序">默认<i></i></a>
						</li>
						<li>
							<a href="javascript:void(0)" sortState="0" name="sort" sortField="storeSales" sortOrder="asc">销量<i></i></a>
						</li>
						<li>
							<a href="javascript:void(0)" sortState="0" name="sort" sortField="storeCredit" sortOrder="asc">信用<i></i></a>
						</li>
						<li>
							<a href="javascript:void(0)" sortState="0" name="sort" sortField="storeCollect" sortOrder="asc">收藏<i></i></a>
						</li>
						<li>
							<a href="javascript:void(0)" sortState="0" name="sort" sortField="storeClick" sortOrder="asc">人气<i></i></a>
						</li>
						<li>
							<a href="javascript:void(0)" sortState="0" name="sort" sortField="praiseRate" sortOrder="desc">好评<i></i></a>
						</li>
					</ul>
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
	 	<#if lucenePage??>
		    <#if lucenePage.result??>
			<!-- <div class="squares" nc_type="current_display_mode"> -->
		    <ul class="nc-store-list">
		    	<#list lucenePage.result as store>
				    <li class="item" >
				      <dl class="shop-info" >
				        <dt class="shop-name"><a href="${base}/store/shop?storeId=${store.storeId}">${store.storeName}</a></dt>
				        <dd class="shop-pic">
						<a href="${base}/store/shop?storeId=${store.storeId}" title="">
							<span class="size72">
								<img src=<#if store.storeLogo??>"${imgServer}${store.storeLogo}"<#else>"${base}/res/images/common/default_goods_image.gif_small.gif"</#if> class="size72" />
							</span>
						</a>
					  </dd>
				        <dd class="shopkeeper">店主：${store.memberName}
					  </dd>
				      </dl>
				      <dl class="w200">
				        <dd>总销量${store.storeSales}笔</dd>
				      </dl>
				      <dl class="w150">
				      	<!-- 商家信用度 -->
				        	<dd><#if store.storeCredit!=0>信用:${store.storeCredit}<#else>暂无信用</#if></dd>
				        	<!-- 商家好评率 -->
				        	<dd><#if store.storeCredit!=0>好评率:${store.praiseRate}%<#else>暂无评价</#if></dd>
				        <!-- 商家动态评分 -->
				        <dd class="shop-rate" nc_type="shop-rate" store_id='2'>商家动态评分：<span><i></i></span>
				          <div class="shop-rate-con">
				              <div class="arrow"></div>
				              <dl class="rate">
				              <#assign evaluateStorebyStoreIdTag =newTag("evaluateStorebyStoreIdTag")>  
								<#assign evaluateStore =evaluateStorebyStoreIdTag("{'storeId':'${store.storeId}'}")>
				                  <dt>描述相符：</dt>
				                  <dd data-score="${evaluateStore.sevalDesccredit}"><span>${evaluateStore.sevalDesccredit}分</span></dd>
				                  <dt>服务态度：</dt>
				                  <dd data-score="${evaluateStore.sevalServicecredit}"><span>${evaluateStore.sevalServicecredit}分</span></dd>
				                  <dt>发货速度：</dt>
				                  <dd data-score="${evaluateStore.sevalDeliverycredit}"><span>${evaluateStore.sevalDeliverycredit}分</span></dd>
				              </dl>
				          </div>
				          </dd>
				      </dl>
				      <dl class="w120">
				        <dd class="tr">${store.storeAddress}</dd>
				      </dl>
				  </li>
		    	</#list>
			 <div class="clear"></div>
		  	</ul>
		  	<!-- </div> -->
		  <#else>
		  	<div class="squares" nc_type="current_display_mode" >
			    <div class="no-results">没有找到符合条件的商家</div>
			</div>
		 </#if>
	</#if>	   
	</textarea>
</div>
			<div class="tc mt20 mb20">
                <#import "/search/search-page.ftl" as q><!--引入分页-->
                <#if lucenePage.totalRows??>
                    <@q.pager pageNo=lucenePage.pageNo pageSize=lucenePage.pageSize recordCount=lucenePage.totalRows toURL="${toUrl}"/>
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
