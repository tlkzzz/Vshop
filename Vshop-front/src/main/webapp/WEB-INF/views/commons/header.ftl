<!-- shop首页头部开始-->
<#macro header title="">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <#assign siteSettingTag = newTag("siteSettingTag") />
	<#assign siteSet = siteSettingTag("") />
    <title>${siteSet.siteTitle}</title>
    <meta name="keywords" content="商品系统演示站"/>
    <meta name="description" content="商品系统演示站"/>
    <meta name="author" content="商品系统演示站">
    <meta name="copyright" content="商品系统演示站">
    <link rel="shortcut icon" href="${imgServer}${siteSet.siteLogo1}"/>
    <style type="text/css">
        body {
            _behavior: url("${base}/res/css/new-csshover.htc");
        }
    </style>
    <script src="${base}/res/js/html5shiv.min.js"></script>
    <link href="${base}/res/css/base.css" rel="stylesheet" type="text/css">
    <link href="${base}/res/css/home_header.css" rel="stylesheet" type="text/css">
    <link href="${base}/res/css/home_login.css" rel="stylesheet" type="text/css">
    <link href="${base}/res/css/font-icons/style.css" rel="stylesheet"/>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="res/js/html5shiv.js"></script>
    <script src="res/js/respond.min.js"></script>
    <![endif]-->
    <script>
        COOKIE_PRE = '5C83_';
        _CHARSET = 'utf-8';
        SITEURL = '${frontServer}';
    </script>
    <script src="${base}/res/js/jquery.js"></script>
	<!-- jqueryAutoComplete加载 -->
	<link href="${base}/res/js/jquery-ui-1.9.2.custom/css/custom-theme/jquery-ui-1.9.2.custom.css" rel="stylesheet" type="text/css">
	<script src="${base}/res/js/jquery-ui-1.9.2.custom/js/jquery-ui-1.9.2.custom.js"></script>
	<!-- autocomplete 加载OVER -->
    <script src="${base}/res/js/jquery.SuperSlide.2.1.1.js"></script>
    <script src="${base}/res/js/common.js" charset="utf-8"></script>
    <!-- <script src="${base}/res/js/jquery-ui/jquery.ui.js" type="text/javascript"></script> -->
    <script src="${base}/res/js/jquery.validation.min.js"></script>
    <script src="${base}/res/js/jquery.masonry.js"></script>
    <!--[if IE 6]>
    <script src="${base}/res/js/IE6_PNG.js"></script>
    <script>
        DD_belatedPNG.fix('.pngFix');
    </script>
    <script>
        // <![CDATA[
        if((window.navigator.appName.toUpperCase().indexOf("MICROSOFT")>=0)&&(document.execCommand))
        try{
        document.execCommand("BackgroundImageCache", false, true);
           }
        catch(e){}
        // ]]>
        </script>
        <script type="text/javascript" src="${base}/res/js/IE6_MAXMIX.js"></script>
    <![endif]-->
   <!--  <script type="text/javascript">
        var PRICE_FORMAT = '&yen;%s';
        $(function () {
            //search
            $("#search").children('ul').children('li').click(function () {
                $(this).parent().children('li').removeClass("current");
                $(this).addClass("current");
                $('#search_act').attr("value", $(this).attr("act"));
                $('#keyword').attr("value", $(this).attr("title"));
            });
            var search_act = $("#search").find("li[class='current']").attr("act");
            $('#search_act').attr("value", search_act);
            $("#keyword").blur();
        });
    </script> -->
</head>
<body>
<script type="text/javascript" src="${base}/res/js/common2.2.js" charset="utf-8"></script>
<div id="append_parent"></div>
<div id="ajaxwaitid"></div>
<div class="public-top-layout w">
    <div class="topbar wrapper">
        <div class="user-entry">
        <@shiro.user>
        <script type="text/javascript">
        	isLogin = true;
        </script>
            您好<span>&nbsp;&nbsp;<a href="${base}/user/setting/index"><@shiro.principal/></a></span>，欢迎来到<a href="${base}"  title="首页" alt="首页">${siteSet.siteName}</a>
            <span>[<a href="${base}/logout">退出</a>]</span>
        </@shiro.user>
        <@shiro.guest>
        
        	<script type="text/javascript">
	        	isLogin = false;
	        </script>
            您好，欢迎来到<a href="${base}" title="首页" alt="首页">${siteSet.siteName}</a>
            <span>[<a href="${base}/login">登录</a>]</span>
            <span>[<a href="${base}/signUp">注册</a>]</span>
        </@shiro.guest>
		  	<span class="seller-login">
				<a href="${sellerServer}" title="商家中心" target="_blank">
					<i class="icon-signin"></i>商家管理中心
				</a>
			</span>
        </div>

        <div class="quick-menu">
            <dl>
                <dt><a href="${base}/user/list">我的订单</a><i></i></dt>
                <dd>
                    <ul>
                        <li><a href="${base}/user/list?orderState=10">待付款订单</a>
                        </li>
                        <li>
                            <a href="${base}/user/list?orderState=30">待确认收货</a>
                        </li>
                        <li><a href="${base}/user/list?orderState=40">待评价交易</a>
                        </li>
                    </ul>
                </dd>
            </dl>
            <dl>
                <dt><a href="${base}/myuser/goodsFavIndex" title="我的收藏" target="_top">我的收藏</a><i></i>
                </dt>
                <dd>
                    <ul>
                        <li><a href="${base}/myuser/goodsFavIndex" target="_top" title="收藏的商品">收藏的商品</a></li>
                        <li><a href="${base}/myuser/storeindex" target="_top" title="/">收藏的店铺</a></li>
                    </ul>
                </dd>
            </dl>
            <dl>
                <dt><a href="#" title="客户服务" target="_top" >客户服务</a><i></i></dt>
                <dd>
                    <ul>
                        <li><a href="${base}/help/index?acId=5">售后服务</a></li>
                        <li><a href="${base}/help/content?acId=7&articleId=38">客服中心</a></li>
                    </ul>
                </dd>
            </dl>

            <dl>
                <dt><a href="${base}" title="站点导航" target="_top">站点导航</a><i></i></dt>
                <dd>
                    <ul>
                        <li><a target="_blank" href="${base}">商城首页</a></li>
                    </ul>
                </dd>
            </dl>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $(".quick-menu dl").hover(function () {
                    $(this).addClass("hover");
                },
                function () {
                    $(this).removeClass("hover");
                });

    });
</script>
<div class="header-wrap">
    <header class="public-head-layout wrapper">
        <!--LOGO-->
        <h1 class="site-logo"><a href="${base}"><img
                src="${imgServer}${siteSet.siteLogo2}" style="max-height:60px;max-width:300px"class="pngFix"></a></h1>
        <!--搜索-->
        <div class="head-search-bar" id="search">
            <!--商品和商家-->
            <ul class="tab">
                <li searchModel="goodsSearch" name="searchTypee" matchAction="goodsKeywordMatch" class="current" act="keywordSearch" title=" 请输入您要搜索的商品关键字">商品</li>
                <li searchModel="storeSearch" name="searchTypee" matchAction="storeKeywordMatch" act="shop_search" title=" 请输入您要搜索的商家关键字">商家</li>
            </ul>
            <form action="${base}/search/goodsSearch" method="get" class="search-form">
                <input name="searchType" id="search_act" value="keywordSearch" type="hidden">
                <input name="keyword" id="keyword" type="text" class="input-text" value=" 请输入您要搜索的商品关键字"
                       onFocus="searchFocus(this)" onBlur="searchBlur(this)" maxlength="60" x-webkit-speech lang="zh-CN"
                       onwebkitspeechchange="foo()" x-webkit-grammar="builtin:search"/>
                <input type="submit" id="button" value="搜索" class="input-submit">
            </form>
            <script type="text/javascript">
            //jquery.autocomplete
				jQuery(function($){
					$("#keyword").autocomplete({
						source: function (request, response){
									$.ajax({
										url:"${base}/search/"+$("[name=searchTypee][class=current]").attr("matchAction"),
										dataType: "json",
										data:{
											keyword: request.term
										},
										success: function(data){
											response($.map(data, function(item){
												return {
													 value: item.keyword,
													 label: item.keyword  + " 约" + item.wordsNum + item.unit
												}
											}));
										}
									});
								},
						minLength: 2
					});
               });
            
            
			</script>
            <script type="text/javascript">
				//商品搜索以及商家搜索
				$(function(){
					$("[searchModel]").click(function(){
						var url = $(this).attr("searchModel");
						$(".search-form").attr("action",'${base}/search/'+url);
						$("[searchModel]").each(function(){
							$(this).attr("class","");
						});
						$(this).attr("class","current");
						return false;
					});
				});
			</script>
            <!--搜索关键字-->
            <!-- <div class="keyword">热门搜索：
                <ul>
                    <li><a href="#">李宁</a></li>
                    <li><a href="#">耐克</a></li>
                    <li><a href="#">Kappa</a></li>
                    <li><a href="#">双肩包</a></li>
                    <li><a href="#">手提包</a></li>
                </ul>
            </div> -->
        </div>
        <div class="head-user-menu">
            <dl class="my-mall">
                <!---我的商城--->
                <dt><span class="icon-user"></span>我的商城<i class="arrow"></i></dt>
                <dd>
                    <div class="sub-title">
                        <h4></h4>
                        <!----会员首页---->
                        <a href="${base}/user/setting/index" class="arrow">我的用户中心<i></i></a>
                    </div>
                    <div class="user-centent-menu">
                        <ul>
                            <li>
                            	<a href="${base}/user/list" class="arrow">我的订单<i></i></a>
                            </li>
                            <li>
                            	<a href="${base}/myconsult/index?consultReply=1&cur=reply" class="arrow">咨询回复<i></i></a>
                            </li>
                            <li>
                            	<a href="${base}/myuser/goodsFavIndex" class="arrow">我的收藏<i></i></a>
                            </li>
                            <li>
                            	<a href="${base}/user/shoppoints/mypointslogindex" class="arrow">我的积分<i></i></a>
                            </li>
                        </ul>
                    </div>
                    <!-- <div class="browse-history">
                        <div class="part-title">
                            <h4>最近浏览的商品</h4>
                        </div>
                        <ul>
                        </ul>
                    </div> -->
                </dd>
            </dl>
            <dl class="my-cart">
                <div class="addcart-goods-num">0</div>
                <dt><span class="icon-cart"></span><a href="${base}/cart/index">购物车结算</a><i class="arrow"></i></dt>
                <dd>
                    <div class="sub-title">
                        <h4>最新加入的商品</h4>
                    </div>
                    <div class="incart-goods-box">
                        <img class="loading" src="${base}/res/images/loading.gif"/>

                        <div class="checkout"><a href="${base}/cart/index" class="btn-cart">结算购物车中的商品</a>
                        </div>
                    </div>

                </dd>
            </dl>
            <script type="text/javascript">
				$(function(){
					$.ajax({
	                    url: '${base}/cart/cart',
	                    async: false,
	                    type: 'POST',
	                    dataType: 'json',
	                    success: function (data) {
	                    	var result = data.result;
	                     	if (result.goodsNum > 0){
	                     		$(".addcart-goods-num").html(result.goodsNum);
	                     	}
	                    }
	                });
				});
			</script>
        </div>
    </header>
</div>
<!-- PublicHeadLayout End -->
<!-- publicNavLayout Begin -->
<nav class="public-nav-layout">
    <div class="wrapper">
        <div class="all-category">
            <div class="title">
                <h3><a href="${base}/all/class">所有商品分类</a></h3>
                <i class="arrow"></i>
            </div>
            <!--左侧菜单-->
            <div class="category">
                <ul class="menu">
                	<#assign goodsClassTag =newTag("goodsClassTag")>
					<#assign goodsList =goodsClassTag("")>
					<#if goodsList?exists && goodsList?size gt 0>
						<#list goodsList as class>
						<#if class.gcshow == 1>
							<li cat_id="${class.gcId}" class="<#if class_index%2 == 0>odd<#else>even</#if>">
								<div class="class">
									<span class="ico">
										<img src="${imgServer}${class.gcpic}">
									</span>
									<h4>
										<a href="${base}/search/goodsSearch?searchType=gcIdSearch&keyword=${class.gcId}" title="${class.gcName}">${class.gcName}</a>
									</h4>
									<span class="recommend-class" id="recommend-class-${class.gcId}">
										
									</span>
									<span class="arrow"></span>
								</div>
								<#if class.hasChild gt 0>
								<#assign firstchild = class.classList>
								<div class="sub-class" cat_menu_id="${class.gcId}">
									<#list firstchild as firstclass>
									<#if firstclass.gcshow==1>
									<dl>
			                                <dt>
			                                	<h3>
			                                		<a href="${base}/search/goodsSearch?searchType=gcIdSearch&keyword=${firstclass.gcId}" title="${firstclass.gcName}">${firstclass.gcName}</a>
			                                	</h3>
			                                </dt>
		                                <dd class="goods-class">	
		                                <#if firstclass.hasChild gt 0>
		                                	<#assign secondchild = firstclass.classList>
		                                	<#assign secondsize = secondchild?size>
		                                	<#list secondchild as secondclass>
		                                		<#if secondclass_index == 0 && secondclass.gcshow==1>
		                                			<script>
		                                				var h = "<a href='${base}/search/goodsSearch?searchType=gcIdSearch&keyword=${secondclass.gcId}' title='${secondclass.gcName}'>${secondclass.gcName}</a>";
		                                				$("#recommend-class-${class.gcId}").append(h);
		                                			</script>
		                                		</#if>
		                                		<#if secondclass.gcshow==1>
		                                		<a href="${base}/search/goodsSearch?searchType=gcIdSearch&keyword=${secondclass.gcId}" 
		                                			title="${secondclass.gcName}" <#if secondclass_index+1 == secondsize>style="background:none;"</#if>>${secondclass.gcName}</a>
		                                		</#if>
		                                	</#list>
		                                </#if>
                                    	</dd>
                                      </#if>
		                            </dl>
		                            </#list>
								</div>
								</#if>
							</li>
						</#if>
						</#list>
					</#if>
                </ul>
            </div>
        </div>
        <!--分类树结束-->
        <ul class="site-menu">
            <li class="current"><a href="${base}">首页</a></li>
            <li class="link"><a href="${base}/all/brand">品牌</a></li>
          <!--  <li class="link"><a href="#">优惠券</a></li>-->
            <!--<li class="link"><a href="${base}/points/index">积分中心</a></li>
            <li class="link"><a href="#" class="pngFix">团购</a></li>
            <li class="link"><a href="#">品牌</a></li>
            <li class="link"><a href="#">优惠券</a></li>
            <li class="link"><a href="#">积分中心</a></li>
            <li class="link"><a href="#">运动专场</a></li>
            <li class="link"><a target="_blank" href="#">微商城</a></li> -->
        </ul>
    </div>
</nav>
<!-- PublicNavLayout End-->
</#macro>
<!-- shop页头 结束-->

<!-- shop页脚开始 -->
<#macro footer>
<div class="footer">
    <div class="wrapper">
        <div id="footer-top">
            <ul>
                <li class="one"><strong>正品行货<em>100%正品保证</em></strong></li>
                <li class="two"><strong>天天低价<em>天天有超低价</em></strong></li>
                <li class="three"><strong>7天包退还<em>7天无理由退货</em></strong></li>
                <li class="four"><strong>极速配送<em>极速配送服务</em></strong></li>
                <li class="five"><strong>精致服务<em>贴心精致服务</em></strong></li>
            </ul>
        </div>
    </div>
    <!--帮助中心-->
    <div id="faq">
        <div class="wrapper">
            <ul>
            <#assign articleClassTitleTag = newTag("articleClassTitleTag") >
            <#assign tagList = articleClassTitleTag("")>
			<#if tagList??>
	            <#list tagList as str>
	                <li>
	                    <dl class="s${str_index+1}">
	                        <dt>
	                        ${str.acName}
	                        </dt>
	                        <#list str.article as str1>
	                            <dd>
		                            <i></i>
		                            <#if str1.articleUrl == "">
									    <a href="${base}/help/index?acId=${str.acId}" title="${str1.articleTitle}" target="_blank">${str1.articleTitle}</a>
									<#else>
										<a href="${str1.articleUrl}" title="${str1.articleTitle}" target="_blank">${str1.articleTitle}</a>
									</#if>
		                           
	                            </dd>
	                        </#list>
	                    </dl>
	                </li>
	            </#list>
	         </#if>
                <div class="clear"></div>
            </ul>
        </div>
    </div>
    <!--底部版权，调用footer-->
    <div id="footer">
        <div class="wrapper">
            <p>
       ${siteSet.siteDbxx}
            </p>
         
            
           <!--  <script type="text/javascript">
            	var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");
            		document.write(unescape("%3Cspan id='cnzz_stat_icon_1256196951'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D1256196951%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E"));
           	</script> -->
            
            <br/>

            <div class="footer-logo">
                <ul>
                    <li class="caifutong"></li>
                    <li class="caifubao"></li>
                    <li class="beifen"></li>
                    <li class="kexin"></li>
                    <li class="shiming"></li>
                    <li class="wangzhan360"></li>
                    <li class="anquanlianmeng"></li>
                    <div class="clear"></div>
                </ul>
            </div>
        </div>
    </div>
</div>
<!-- youce -->
<!-- youce -->
<script type="text/javascript" src="${base}/res/js/jquery.cookie.js"></script>
<script type="text/javascript" src="${base}/res/js/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="${base}/res/js/jquery.mousewheel.js"></script>
<script type="text/javascript" src="${base}/res/js/jquery.masonry.js"></script>
<script type="text/javascript" src="${base}/res/js/jquery.scrollLoading-min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        //实现图片慢慢浮现出来的效果
        $("img").load(function () {
            //图片默认隐藏
            $(this).hide();
            //使用fadeIn特效
            $(this).fadeIn("5000");
        });
        // 异步加载图片，实现逐屏加载图片
        $(".scrollLoading").scrollLoading();
    });
</script>
<script type="text/javascript">
    var PRICE_FORMAT = '&yen;%s';
    $(function () {
        //首页左侧分类菜单
        $(".category ul.menu").find("li").each(
                function () {
                    $(this).hover(
                            function () {
                                var cat_id = $(this).attr("cat_id");
                                var menu = $(this).find("div[cat_menu_id='" + cat_id + "']");
                                menu.show();
                                $(this).addClass("hover");
                                if (menu.attr("hover") > 0) return;
                                menu.masonry({itemSelector: 'dl'});
                                var menu_height = menu.height();
                                if (menu_height < 60) menu.height(80);
                                menu_height = menu.height();
                                var li_top = $(this).position().top;
                                if ((li_top > 60) && (menu_height >= li_top)) $(menu).css("top", -li_top + 50);
                                if ((li_top > 150) && (menu_height >= li_top)) $(menu).css("top", -li_top + 90);
                                if ((li_top > 240) && (li_top > menu_height)) $(menu).css("top", menu_height - li_top + 90);
                                if (li_top > 300 && (li_top > menu_height)) $(menu).css("top", 60 - menu_height);
                                if ((li_top > 40) && (menu_height <= 120)) $(menu).css("top", -5);
                                menu.attr("hover", 1);
                            },
                            function () {
                                $(this).removeClass("hover");
                                var cat_id = $(this).attr("cat_id");
                                $(this).find("div[cat_menu_id='" + cat_id + "']").hide();
                            }
                    );
                }
        );
        $(".head-user-menu dl").hover(function () {
                    $(this).addClass("hover");
                },
                function () {
                    $(this).removeClass("hover");
                });
        $('.head-user-menu .my-mall').mouseover(function () {// 最近浏览的商品
            load_history_information();
            $(this).unbind('mouseover');
        });
        $('.head-user-menu .my-cart').hover(function () {// 运行加载购物车
            	load_cart_information();
        	},function(){
        		$(this).unbind('cart-hover');
        	}
        );
    });

</script>
<script language="javascript">
    var searchTxt = ' 请输入您要搜索的商品关键字';
    function searchFocus(e) {
        if (e.value == searchTxt) {
            e.value = '';
            $('#keyword').css("color", "");
        }
    }
    function searchBlur(e) {
        if (e.value == '') {
            e.value = searchTxt;
            $('#keyword').css("color", "#999999");
        }
    }
    function searchInput() {
        if ($('#keyword').val() == searchTxt)
            $('#keyword').attr("value", "");
        return true;
    }
    $('#keyword').css("color", "#999999");
</script>
<script language="javascript">
    // 加载购物车信息
    function load_cart_information() {
        $.post('${base}/cart/cart', function (data) {
            if (data.success) {
                var result = data.result;
                $('.addcart-goods-num').html(result.goodsNum);
                var html = '';
                if (result.goodsNum > 0) {
                    html += "<div class='incart-goods'>";
                    var i = 0;
                    var data = result['list'];
                    for (i = 0; i < data.length; i++) {
                        html += "<dl id='cart_item_" + data[i]['specId'] + "' count='" + data[i]['goodsNum'] + "'>";
                        html += "<dd class='goods-thumb'><span class='thumb size40'><i></i><img src='${imgServer}" + data[i]['goodsImages'] + "' title='" + data[i]['goodsName'] + "'  ></span></dd>";
                        //onload='javascript:DrawImage('this',40,40);'     图片缩放
                        html += "<dt class='goods-name'><a href='${base}/product/detail?id=" + data[i]['goodsId'] + "' title='" + data[i]['goodsName'] + "' target='_top'>" + data[i]['goodsName'] + "</a></dt>";

                        html += "<dd class='goods-price'><p>&yen;" + number_format(data[i]['goodsPrice'],2) + "×" + data[i]['goodsNum'] + "</p><dd class='handle'><a  href='javascript:void(0)' onClick='deleteTopCart(" + data[i]['goodsId'] + "," + data[i]['specId'] + ");'>删除</a></dd>";
                        html += "</dl>";
                    }
                    html += "<div colspan='3' class='checkout'><span class='total-price'>共<i>" + result.goodsNum + "</i>种商品   金额总计：<em>&yen;" + number_format(result.goodsTotalPrice,2) + "</em></span><span class='btn-cart' ><a href='${base}/cart/index' target='_top' title='结算商品' style='color: #FFF;' >结算购物车中的商品</a></span></div>";
                } else {
                    html = "<div class='incart-goods'><div class='no-order'><span>您的购物车中暂无商品，赶快选择心爱的商品吧！</span></div><div class='checkout' ></div></div>";
                }
                $(".incart-goods-box").html(html);
            } 
        });
    }
    
    //导航删除购物车信息
    function deleteTopCart(goodsId,specId){
    	var tr = $('#cart_item_' + specId);
    	var amount_span = $('#cart_amount');
        var cart_goods_kinds = $('.addcart-goods-num');
    	$.ajax({
            url: '${base}/cart/deleteCart',
            type: 'POST',
            data: {'goodsId': goodsId, 'specId': specId},
            dataType: "json",
            success: function (data) {
            	if(data.success){
            		var result = data.result;
            		if (result.goodsNum == 0) {
	                    $('.addcart-goods-num').html('0');
	                    var html = '';
	                    html = "<div class='incart-goods'><div class='no-order'><span>您的购物车中暂无商品，赶快选择心爱的商品吧！</span></div><div class='checkout' ><a href='#?act=cart'  title='结算商品' class='btn-cart' >结算购物车中的商品</a></div></div>";
	                    $(".incart-goods-box").html(html);
	                    html = "<div class='addcart-goods-num'>0</div>";
	                }
	                else {
	            		tr.remove();
	            		amount_span.html(price_format(result.goodsTotalPrice));  //刷新总费用
	                    cart_goods_kinds.html(result.goodsNum);  //刷新商品种类
                    }
            	}
            }
        });
    } 

    //头部删除购物车信息
    /* function drop_topcart_item(store_id, spec_id) {
        var tr = $('#cart_item_' + spec_id);
        var amount_span = $('#cart_amount');
        var cart_goods_kinds = $('.addcart-goods-num');
        $.getJSON('#?act=cart&op=drop&specid=' + spec_id + '&storeid=' + store_id, function (result) {
            if (result.done) {
                //删除成功
                if (result.quantity == 0) {
                    $('.addcart-goods-num').html('0');
                    var html = '';
                    html = "<div class='incart-goods'><div class='no-order'><span>您的购物车中暂无商品，赶快选择心爱的商品吧！</span></div><div class='checkout' ><a href='#?act=cart'  title='结算商品' class='btn-cart' >结算购物车中的商品</a></div></div>";
                    $(".incart-goods-box").html(html);
                    html = "<div class='addcart-goods-num'>0</div>";
                }
                else {
                    dl.remove();        //移除
                    amount_span.html(price_format(result.amount));  //刷新总费用
                    cart_goods_kinds.html(result.quantity);       //刷新商品种类
                }
            } else {
                alert(result.msg);
            }
        });
    } */
</script>
</body>
</html>
</#macro>
<!-- shop页脚结束 -->

<!----------------------------------------------- 页头页脚分割线 --------------------------------------------------------------------->

<!-- user页头开始 -->
<#macro userHeader title="">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    < <#assign siteSettingTag = newTag("siteSettingTag") />
	<#assign siteSet = siteSettingTag("") />
    <title>${siteSet.siteTitle}</title>
    <meta name="keywords" content="商城系统演示站"/>
    <meta name="description" content="商城系统演示站"/>
    <meta name="author" content="商城系统演示站">
    <meta name="copyright" content="商城系统演示站">
    <link rel="shortcut icon" href="${imgServer}${siteSet.siteLogo1}"/>
    <link href="${base}/res/css/base.css" rel="stylesheet" type="text/css">
    <link href="${base}/res/css/member.css" rel="stylesheet" type="text/css">
    <link href="${base}/res/css/member_user.css" rel="stylesheet" type="text/css">
    <!--[if IE 6]><style type="text/css">
    body {_behavior: url(${base}/res/css/csshover.htc);}
</style>
    <![endif]-->
    <script>
        COOKIE_PRE = '5C83_';_CHARSET = 'utf-8';SITEURL = '${base}';
    </script>
    <script src="${base}/res/js/jquery.js" charset="utf-8"></script>
    <script src="${base}/res/js/jquery-ui/jquery.ui.js" type="text/javascript"></script>
    <script src="${base}/res/js/jquery.validation.min.js" charset="utf-8"></script>
    <script src="${base}/res/js/common.js" charset="utf-8"></script>
    <script src="${base}/res/js/member/member.js" charset="utf-8"></script>
<#nested>
    <!--[if IE]>
    <script src="${base}/res/js/html5.js"></script>
    <![endif]-->
    <!--[if IE 6]>
    <script src="${base}/res/js/IE6_PNG.js"></script>
    <script>
        DD_belatedPNG.fix('.pngFix');
    </script>
    <script>
        // <![CDATA[
            if((window.navigator.appName.toUpperCase().indexOf("MICROSOFT")>=0)&&(document.execCommand))
            try{
            document.execCommand("BackgroundImageCache", false, true);
               }
            catch(e){}
        // ]]>
    </script>
    <![endif]-->
</head>
<body>
<script type="text/javascript" src="${base}/res/js/common2.2.js" charset="utf-8"></script>
<!-- <link href="${base}/res/css/dialog2.2.css" rel="stylesheet" type="text/css"> -->
<div id="append_parent"></div>
<div id="ajaxwaitid"></div>
<div class="public-top-layout w">
    <div class="topbar wrapper">
        <div class="user-entry" >
            <@shiro.user>
                您好<span>&nbsp;&nbsp;<a href="${base}/user/setting/index"><@shiro.principal/></a></span>，欢迎来到<a href="${base}"  title="首页" alt="首页">${siteSet.siteName}</a>
                <span>[<a href="${base}/logout">退出</a>]</span>
                <span class="seller-login">
				<a href="" target="_top" title="商家中心" target="_blank" title="登录商家管理中心"><i class="icon-signin"></i>商家管理中心</a></span>
            </@shiro.user>
            <@shiro.guest>
                您好，欢迎来到<a href="${base}" title="首页" alt="首页">${siteSet.siteName}</a>
                <span>[<a href="${base}/login">登录</a>]</span>
                <span>[<a href="${base}/signUp">注册</a>]</span>
            </@shiro.guest>
        </div>

        <div class="quick-menu">
            <dl>
                <dt><a href="${base}/user/list">我的订单</a><i></i></dt>
                <dd>
                    <ul>
                        <li><a href="${base}/user/list?orderState=10">待付款订单</a></li>
                        <li><a href="${base}/user/list?orderState=30">待确认收货</a></li>
                        <li><a href="${base}/user/list?orderState=40">待评价交易</a></li>
                    </ul>
                </dd>
            </dl>
            <dl>
                <dt><a href="${base}/myuser/goodsFavIndex" title="我的收藏" target="_top" >我的收藏</a><i></i></dt>
                <dd>
                    <ul>
                        <li><a href="${base}/myuser/goodsFavIndex" target="_top" title="收藏的商品" >收藏的商品</a></li>
                        <li><a href="${base}/myuser/storeindex" target="_top" title="收藏的商家" >收藏的店铺</a></li>
                    </ul>
                </dd>
            </dl>
            <dl>
                <dt><a href="${base}/myReviews/index" title="我的收藏" target="_top" >客户服务</a><i></i></dt>
                <dd>
                    <ul>
                        <li><a href="${base}/help/index?acId=5">售后服务</a></li>
                        <li><a href="${base}/help/content?acId=7&articleId=38">客服中心</a></li>
                    </ul>
                </dd>
            </dl>

            <dl>
                <dt><a href="${base}" title="我的收藏" target="_top">站点导航</a><i></i></dt>
                <dd>
                    <ul>
                        <li><a target="_blank" href="${base}">商城首页</a></li>
                    </ul>
                </dd>
            </dl>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function(){
        $(".quick-menu dl").hover(function() {
            $(this).addClass("hover");
        },
        function() {
            $(this).removeClass("hover");
        });
    });
</script>
<!-- PublicHeadLayout Begin -->
<header id="header" class="pngFix">
    <div class="wrapper">
        <h1 id="logo" title=""><a href="${base}">
        	<img src="${imgServer}${siteSet.siteLogo2}" alt="商城系统演示站" class="pngFix" style="display: inline; width:260px; margin-top:8px;">
        </a></h1>
        <nav>
            <ul>
                <li class="frist"><a <#if (apm='index')>class="active"<#else>class='normal'</#if> href="${base}/user/index" title="">用户首页</a></li>
                <li><a <#if (apm='setting')>class="active"<#else>class='normal'</#if> href="${base}/user/setting/index" title="设置">设置</a></li>
            </ul>
            <div class="search-box">
                <form method="get" action="${base}/search/goodsSearch" target="_blank">
                    <input name="searchType" id="search_act" value="keywordSearch" type="hidden">
                    <input name="keyword" id="keyword" type="text" class="text"  placeholder=" 搜索其实很容易！" maxlength="200"/>
                    <input name="" type="submit" value="" class="submit pngFix">
                </form>
            </div>
        </nav>
    </div>
</header>
</#macro>
<!-- user页头结束 -->
<!-- user页脚开始 -->
<#macro userfooter>
<div id="footer">
    <div class="wrapper">
        <p>${siteSet.siteDbxx}
        </p>
     
        
        <script type="text/javascript">
           	var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");
           		document.write(unescape("%3Cspan id='cnzz_stat_icon_1256196951'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D1256196951%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E"));
       	</script>
        <br/>
        <div class="footer-logo">
            <ul>
                <li class="caifutong"></li>
                <li class="caifubao"></li>
                <li class="beifen"></li>
                <li class="kexin"></li>
                <li class="shiming"></li>
                <li class="wangzhan360"></li>
                <li class="anquanlianmeng"></li>
                <div class="clear"></div>
            </ul>
        </div>
    </div>
</div>
<!-- youce -->
<!-- youce -->
<script type="text/javascript" src="${base}/res/js/jquery.cookie.js" ></script>
<script type="text/javascript" src="${base}/res/js/perfect-scrollbar.min.js" ></script>
<script type="text/javascript" src="${base}/res/js/jquery.mousewheel.js" ></script>
<script type="text/javascript" src="${base}/res/js/jquery.masonry.js" ></script>
<script type="text/javascript" src="${base}/res/js/jquery.scrollLoading-min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        //实现图片慢慢浮现出来的效果
        $("img").load(function () {
            //图片默认隐藏
            $(this).hide();
            //使用fadeIn特效
            $(this).fadeIn("5000");
        });
        // 异步加载图片，实现逐屏加载图片
        $(".scrollLoading").scrollLoading();
    });
</script>
<script language="javascript">
    var searchTxt = ' 搜索其实很容易！';
    function searchFocus(e){
        if(e.value == searchTxt){
            e.value='';
            $('#keyword').css("color","");
        }
    }
    function searchBlur(e){
        if(e.value == ''){
            e.value=searchTxt;
            $('#keyword').css("color","#999999");
        }
    }
    function searchInput() {
        if($('#keyword').val()==searchTxt)
            $('#keyword').attr("value","");
        return true;
    }
    $('#keyword').css("color","#999999");
</script>
</body>
</html>
</#macro>
<!-- user页脚结束 -->

<!-- 商家头开始 -->
<#macro storeheader title="">
<#assign storeInfoTag =newTag("storeInfoTag")>  
<#assign store =storeInfoTag("{'storeId':'${storeId}'}")>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta content="IE=9" http-equiv="X-UA-Compatible">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <#assign siteSettingTag = newTag("siteSettingTag") />
	<#assign siteSet = siteSettingTag("") />
    <title>${siteSet.siteTitle}</title>
<meta name="keywords" content="首页-商家中心" />
<meta name="description" content="首页-商家中心" />
<meta name="author" content="首页-商家中心">
<meta name="copyright" content="首页-商家中心 Inc. All Rights Reserved">
<link href="${base}/res/css/base.css" rel="stylesheet" type="text/css">
<link href="${base}/res/css/shop.css" rel="stylesheet" type="text/css">
<link href="${base}/res/css/home_login.css" rel="stylesheet" type="text/css">
<link href="${base}/res/css/font-icons/style.css"  rel="stylesheet" />
<link href="${base}/res/css/style/<#if store.storeTheme??&&store.storeTheme!=''>${store.storeTheme}<#else>default</#if>/style.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="${imgOssServer}${siteSet.siteLogo2}"/>
<!--[if IE 6]><style type="text/css">
body {_behavior: url(${base}/res/css/csshover.htc);}
</style>
<![endif]-->
<script type="text/javascript" src="${base}/res/js/jquery.js"></script>
<script type="text/javascript" src="${base}/res/js/jquery-ui/jquery.ui.js"></script>
<script type="text/javascript" src="${base}/res/js/jquery.validation.min.js"></script>
<script type="text/javascript" src="${base}/res/js/common.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/member/member.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/utils.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/shop.js" charset="utf-8"></script>
<#nested>
<!--[if IE 6]>
<script src="${base}/res/js/IE6_PNG.js"></script>
<script>
DD_belatedPNG.fix('.pngFix,.pngFix:hover');
</script>
<script> 
// <![CDATA[ 
if((window.navigator.appName.toUpperCase().indexOf("MICROSOFT")>=0)&&(document.execCommand)) 
try{ 
document.execCommand("BackgroundImageCache", false, true); 
   } 
catch(e){} 
// ]]> 
</script> 
<script type="text/javascript" src="${base}/res/js/IE6_MAXMIX.js"></script> 
<![endif]--> 
<script>
COOKIE_PRE = '5C83_';_CHARSET = 'utf-8';SITEURL = '${base}';
</script>
</head>
<body>
<script type="text/javascript" src="${base}/res/js/common2.2.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/layer/layer.js"></script>
<div id="append_parent"></div>
<div id="ajaxwaitid"></div>
<div class="public-top-layout w">
  <div class="topbar wrapper">  
       <div class="user-entry">
      <@shiro.user>
            您好<span>&nbsp;&nbsp;<a href="${base}/user/setting/index"><@shiro.principal/></a></span>，欢迎来到<a href="${base}"  title="首页" alt="首页">${siteSet.siteName}</a>
            <span>[<a href="${base}/logout">退出</a>]</span>
        </@shiro.user>
        <@shiro.guest>
            您好，欢迎来到<a href="${base}" title="首页" alt="首页">${siteSet.siteName}</a>
            <span>[<a href="${base}/login">登录</a>]</span>
            <span>[<a href="${base}/signUp">注册</a>]</span>
        </@shiro.guest>
	  <span class="seller-login">
	<a href="${sellerServer}" target="_top" title="商家中心" target="_blank" title="登录商家管理中心"><i class="icon-signin"></i>商家管理中心</a></span>
    </div>
    	
    <div class="quick-menu">
     	<dl>
            <dt><a href="${base}/user/list">我的订单</a><i></i></dt>
            <dd>
                <ul>
                    <li><a href="${base}/user/list?orderState=10">待付款订单</a></li>
                    <li><a href="${base}/user/list?orderState=30">待确认收货</a></li>
                    <li><a href="${base}/user/list?orderState=40">待评价交易</a></li>
                </ul>
            </dd>
        </dl>
        <dl>
            <dt><a href="${base}/myuser/goodsFavIndex" title="我的收藏" target="_top" >我的收藏</a><i></i></dt>
            <dd>
                <ul>
                    <li><a href="${base}/myuser/goodsFavIndex" target="_top" title="收藏的商品" >收藏的商品</a></li>
                    <li><a href="${base}/myuser/storeindex" target="_top" title="收藏的商家" >收藏的店铺</a></li>
                </ul>
            </dd>
        </dl>
        <dl>
            <dt><a href="${base}/myReviews/index" title="我的收藏" target="_top" >客户服务</a><i></i></dt>
                <dd>
                    <ul>
                        <li><a href="${base}/help/index?acId=5">售后服务</a></li>
                        <li><a href="${base}/help/content?acId=7&articleId=38">客服中心</a></li>
                    </ul>
                </dd>
        </dl>

        <dl>
            <dt><a href="${base}" title="我的收藏" target="_top">站点导航</a><i></i></dt>
            <dd>
                <ul>
                    <li><a target="_blank" href="${base}">商城首页</a></li>
                </ul>
            </dd>
        </dl>
    </div>
  </div>
</div>
<script type="text/javascript">
$(function(){
	$(".quick-menu dl").hover(function() {
		$(this).addClass("hover");
	},
	function() {
		$(this).removeClass("hover");
	});
});
</script>
<!-- PublicHeadLayout Begin -->
<header id="header">
  <div class="layout">
    <h1 id="shop-logo">
          <a class="mall-home" href="${base }" title="首页" style="left: -5px;">
          <#assign siteSettingTag = newTag("siteSettingTag") />
			<#assign siteSet = siteSettingTag("") />
          	<img src="${imgServer}${siteSet.siteLogo2}" alt="" 
          		title="商城系统演示站" class="pngFix" style="max-width: 220px; max-height: 60px;">
          </a>
          <em>商城</em>
    </h1>
    <div class="shop-head-info" id="jsddm">
      <div class="search" id="shop-info">
        <form method="get" action="/#" name="formSearch" id="formSearch">
          <input name="act" id="search_act" value="search" type="hidden">
          <input name="keyword" id="keyword" type="text" class="ncs-search-input-text" value="" x-webkit-speech="" lang="zh-CN" onwebkitspeechchange="foo()" x-webkit-grammar="builtin:search" placeholder="想找什么商品？" style="color: rgb(153, 153, 153);">
          <a href="javascript:void(0)" class="ncs-search-btn-mall" nctype="search_in_shop"><span>全站搜</span></a><a href="javascript:void(0)" class="ncs-search-btn-shop" nctype="search_in_store"><span>店内搜</span></a>
        </form>
      </div>
      <!--<div class="service"><i></i>客服<em></em>
        <div class="arrow"></div>
        <div class="sub" style="visibility: hidden;">
		<div class="title-bar">
            <h3>客服中心</h3>
        </div>
		<div class="ncs-message-bar">
  		<div class="service-list">
  		<ul>
		     <li><script>var online= new Array();</script>
				<script src="http://webpresence.qq.com/getonline?Type=1&1234567:"></script>
				<script>if(online[0]==0){document.write('<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=905669187&Site=b2b2c.leimingtech.com&Menu=yes"><img border="0" src="http://192.168.1.230/templates/default/images/qq2.gif" alt="在线客服" title="在线客服"></a>')}
				else{document.write('<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=905669187&Site=b2b2c.leimingtech.com&Menu=yes"><img border="0" src="http://192.168.1.230/templates/default/images/qq1.gif" alt="在线客服" title="在线客服"></a>')} </script></li>
         </ul>
         </div>
  		 </div>
         </div>
         </div>-->
      <!-- <div class="service"><i></i>客服<em></em>
         
        <div class="arrow"></div>
        <div class="sub" style="visibility: hidden;">
          <div class="ncs-message-bar">
			  <div class="default">
			    <h5>极限运动基地</h5>
			          </div>
			    <div class="service-list">
			        <dl>
			      	<dt>客服中心</dt>
			        <dd><span><a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uuin=1234567&site=qq&menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:1234567:52" alt="点击这里给我发消息" title="点击这里给我发消息"/></a></span>
			        </dd>
			        </dl>
			   </div>
		   </div>
        </div>
      </div> -->
      <div class="favorites"><i></i>收藏<em></em>
        <div class="arrow"></div>
        <div class="sub" style="visibility: hidden;">
          <div class="title-bar">
            <h3>商城空间</h3>
          </div>
          <ul>
            <li><a href="javascript:void(0)" onclick="collect_storeorgoods('-9','2','${storeId}');" class="btn"><i></i>收藏</a></li>
            <li><a href="javascript:void(0);" nctype="store_collect" class="no-url">${store.storeCollect}</a><span id="storecollectcount">收藏人气</span></li>
           <!-- <li><a href="http://www.shopnctest.com/b2b2c/2014/demo/shop/#?act=store_snshome&sid=1" target="_blank">0</a><span>商家动态</span></li>
            <li><a href="javascript:void(0);" class="share" nctype="share_store"></a><span>分享</span></li>-->
          </ul>
        </div>
      </div>
    </div>
  </div>
</header>
<script type="text/javascript">
$(function(){
	$('a[nctype="search_in_store"]').click(function(){
		/* $('#search_act').val('show_store');
		$('<input type="hidden" value="2" name="id" /> <input type="hidden" name="op" value="goods_all" />').appendTo("#formSearch");
		$('#formSearch').submit(); */
		var goodsName = $("input[name='keyword']").val();
		window.location.href = "${base}/store/shop?storeId="+${store.storeId}+"&storeClassId=-9"+"&goodsName="+goodsName;
	});
	
	var store_id = "2";
	var goods_id = "";
	var act = "show_store";
	var op  = "index";
	$.getJSON("#?act=show_store&op=ajax_flowstat_record",{id:store_id,goods_id:goods_id,act_param:act,op_param:op},function(result){
	});
});
  //修改商家收藏数量
  function collect_storeorgoods(storeorgoodsId,favType,storeId){
			       $.ajax({
					    	url : "${base}/Favorite/SaveFavorite",
					        type : 'post',
					        data : {'sgId':storeorgoodsId,'favType':favType,'storeId':storeId},
					        dataType : 'json',
					        success : function(data){
			                if(data.success){
			            	    layer.msg(data.msg,{icon:1});
			            	    $("a[nctype='store_collect']").html(data.collectstorecount);
			            	}else{
			            		layer.msg(data.msg,{icon:2});
			            	}
			            }
					 }); 
			  } 
</script>
<div class="background clearfix">
  <div class="ncsl-nav">
  	<div class="ncs-default-banner pngFix" style="background: url(${imgServer}${store.storeBanner})"></div>
      <nav id="nav" class="pngFix">
	    <ul class="pngFix">
	      <li class="<#if apm='shop'>active<#else>normal</#if>"><a href="../store/shop?storeId=${store.storeId}"><span>商城首页<i></i></span></a></li>
	      <li class="<#if apm='credit'>active<#else>normal</#if>" ><a href="../store/credit?storeId=${store.storeId}"><span>信用评价<i></i></span></a></li>
	      <li class="<#if apm='info'>active<#else>normal</#if> pngFix"><a href="../store/info?storeId=${store.storeId}"><span>商城详情<i></i></span></a></li>
	     </ul>
	  </nav>
   </div>
</#macro>
<!-- 商家头结束 -->