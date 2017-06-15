<!-- 供应商头开始 -->
<#macro header title="">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <#assign siteSettingTag = newTag("siteSettingTag") />
	<#assign siteSet = siteSettingTag("") />
    <title>${siteSet.siteTitle}</title>
    <meta name="keywords" content="商品汇平台"/>
    <meta name="description" content="商品汇平台"/>
    <meta name="author" content="商品汇平台">
    <meta name="copyright" content="商品汇平台">
    <link rel="shortcut icon" href="${imgServer}${siteSet.siteLogo1}"/>
    <link rel="stylesheet" type="text/css" href="${base}/res/js/jquery-ui/themes/ui-lightness/jquery.ui.css"  />
    <link rel="stylesheet" type="text/css" href="${base}/res/css/easyui/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${base}/res/css/easyui/icon.css">
    <link href="${base}/res/css/base.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/res/css/member.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/res/css/member_store.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/res/css/font-icons/style.css"  rel="stylesheet" />
    <!--[if IE 6]>
    <style type="text/css">
    	body {_behavior: url(${base}/res/css/csshover.htc);}
	</style>
    <![endif]-->
    <script type="text/javascript" src="${base}/res/js/jquery.js"></script>
    <script type="text/javascript" src="${base}/res/js/common/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${base}/res/js/jquery-ui/jquery.ui.js"></script>
    <script type="text/javascript" src="${base}/res/js/jquery.validation.min.js"></script>
    <script type="text/javascript" src="${base}/res/js/common/lodash-4.5.1-min.js"></script>
    <script type="text/javascript" src="${base}/res/js/common/lodash-extend.js"></script>
    <script type="text/javascript" src="${base}/res/js/common/json2-min.js"></script>
    <script type="text/javascript" src="${base}/res/js/common.js"></script>
    <script type="text/javascript" src="${base}/res/js/member/member.js" charset="utf-8"></script>
    <script type="text/javascript" src="${base}/res/js/utils.js" charset="utf-8"></script>
    <script type="text/javascript" src="${base}/res/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="${base}/res/js/layer/layer.js"></script>
    <script type="text/javascript" src="${base}/res/js/menu.js"></script>
    <script type="text/javascript" src="${base}/res/js/index.js"></script>
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
    <script type="text/javascript">
        COOKIE_PRE = '5C83_';_CHARSET = 'utf-8';SITEURL = '${sellerServer}';
        var PRICE_FORMAT = '&yen;%s';
        $(function(){
            //search
            $("#details").children('ul').children('li').click(function(){
                $(this).parent().children('li').removeClass("current");
                $(this).addClass("current");
                $('#search_act').attr("value",$(this).attr("act"));
            });
            var search_act = $("#details").find("li[class='current']").attr("act");
            $('#search_act').attr("value",search_act);
            $("#keyword").blur();
        });
        /* function show_store(store_id){
            var store_url="#?act=show_store&id=";
            var s_id=store_id;
            $.get("#?act=get_session&key=store_id", function(data){
                if(data != '') s_id=data;
            });
            if(s_id > 0) window.open(store_url+s_id,'','');
        } */
    </script>
    <script type="text/javascript">
        var APP_BASE = '${base}';
        var FRONT_BASE = '${frontServer}';
        var STORE_ID = '${storeid}';
    </script>
</head>
<body>
<script type="text/javascript" src="${base}/res/js/common2.2.js" charset="utf-8"></script>
<div id="append_parent"></div>
<div id="ajaxwaitid"></div>
<div class="public-top-layout w">
    <div class="topbar wrapper">
        <div class="user-entry" >
            <@shiro.user>
                您好<span>&nbsp;&nbsp;<a href="${base}"><@shiro.principal/></a></span>，欢迎来到
<!--                 <a href="${frontServer}"  title="首页" alt="首页"> -->
                商品汇平台
<!--                 </a> -->
                <span>[<a href="${base}/logout">退出</a>]</span>
                <span class="seller-login">
				<a href="${base}" title="供应商中心" >
					<i class="icon-signin"></i>供应商管理中心
				</a>
			</span>
            </@shiro.user>
            <@shiro.guest>
                您好，欢迎来到<a href="${base}" title="首页" alt="首页">供应商管理平台</a>
                <span>[<a href="${base}/login">登录</a>]</span>
                <span>[<a href="${base}/signUp">注册</a>]</span>
            </@shiro.guest>
        </div>
<!--  
        <div class="quick-menu">
            <dl>
                <dt><a href="#?act=member&op=order">我的订单</a><i></i></dt>
                <dd>
                    <ul>
                        <li><a href="#?act=member&op=order&state_type=order_pay">待付款订单</a></li>
                        <li><a href="#?act=member&op=order&state_type=order_shipping">待确认收货</a></li>
                        <li><a href="#?act=member&op=order&state_type=noeval">待评价交易</a></li>
                    </ul>
                </dd>
            </dl>
            <dl>
                <dt><a href="#?act=member_favorites&op=fglist" title="我的收藏" target="_top" >我的收藏</a><i></i></dt>
                <dd>
                    <ul>
                        <li><a href="#?act=member_favorites&op=fglist" target="_top" title="收藏的商品" >收藏的商品</a></li>
                        <li><a href="#?act=member_favorites&op=fslist" target="_top" title="收藏的供应商" >收藏的供应商</a></li>
                    </ul>
                </dd>
            </dl>
            <dl>
                <dt>客户服务<i></i></dt>
                <dd>
                    <ul>
                        <li><a href="#?act=article&ac_id=2">帮助中心</a></li>
                        <li><a href="#?act=article&ac_id=5">售后服务</a></li>
                        <li><a href="#?act=article&ac_id=6">客服中心</a></li>
                    </ul>
                </dd>
            </dl>

            <dl>
                <dt>站点导航<i></i></dt>
                <dd>
                    <ul>
                        <li><a target="_blank" href="${frontServer }">商城首页</a></li>
                    </ul>
                </dd>
            </dl>
        </div>
        
        -->
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
<!-- PublicHeadLayout Begin --><div id="header">
    <h1 title="商品汇平台">
    	<a href="${sellerServer }">
    		<img src="${imgServer}${siteSet.siteLogo2}" alt="商品汇平台" class="pngFix" style="max-height: 60px; max-width: 300px;">
    	</a>
   		<i>供应商中心</i>
    </h1>
    <!-- 暂时没有pc商城
    <div id="search" class="search">
        <div class="details" id="details">
            <ul class="tab">
                <li searchModel="goodsSearch" class="current" act="keywordSearch"><span>商品</span></li>
                <li  searchModel="storeSearch" act="storeSearch"><span>供应商</span></li>
            </ul>
            <div id="a1" class="form">
                <form method="get" action="${frontServer}/search/goodsSearch" id="searchForm" target="_blank">
                    <input name="searchType" id="search_act" value="keywordSearch" type="hidden">
                    <div class="formstyle">
                        <input name="keyword" id="keyword" type="text" class="textinput" value=" 搜索其实很容易！" onFocus="searchFocus(this)" onBlur="searchBlur(this)" maxlength="200"/>
                        <input name="" type="submit" class="search-button" value="">
                    </div>
                </form>
            </div>
        </div>
    </div>
     -->
</div>
<script type="text/javascript">
	//商品搜索以及供应商搜索
	$(function(){
		$("[searchModel]").click(function(){
			var url = $(this).attr("searchModel");
			$("#searchForm").attr("action",'${frontServer}/search/'+url);
			$("[searchModel]").each(function(){
				$(this).attr("class","");
			});
			$(this).attr("class","current");
			return false;
		});
	});
</script>
</#macro>
<!-- 供应商头结束 -->

<#macro footer>
<div id="footer" >
    <div class="wrapper">
<!--         <p><a href="#">首页</a> -->
<!--             | <a  href="#?act=article&article_id=24">招聘英才</a> -->
<!--             | <a  href="#?act=article&article_id=25">广告合作</a> -->
<!--             | <a  href="#?act=article&article_id=23">联系我们</a> -->
<!--             | <a  href="#?act=article&amp;article_id=22">关于我们</a> -->
<!--         </p> -->
 <#assign siteSettingTag = newTag("siteSettingTag") />
	<#assign siteSet = siteSettingTag("") />
       ${siteSet.siteDbxx}
<!--         &nbsp;&nbsp;ICP证： -->
        <br/>
<!--         <div class="footer-logo"> -->
<!--             <ul> -->
<!--                 <li class="caifutong"></li> -->
<!--                 <li class="caifubao"></li> -->
<!--                 <li class="beifen"></li> -->
<!--                 <li class="kexin"></li> -->
<!--                 <li class="shiming"></li> -->
<!--                 <li class="wangzhan360"></li> -->
<!--                 <li class="anquanlianmeng"></li> -->
<!--                 <div class="clear"></div> -->
<!--             </ul> -->
<!--         </div> -->
    </div>
</div>
</div>
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
<script type="text/javascript">
    var PRICE_FORMAT = '&yen;%s';
    $(function(){
        //首页左侧分类菜单
        $(".category ul.menu").find("li").each(
                function() {
                    $(this).hover(
                            function() {
                                var cat_id = $(this).attr("cat_id");
                                var menu = $(this).find("div[cat_menu_id='"+cat_id+"']");
                                menu.show();
                                $(this).addClass("hover");
                                if(menu.attr("hover")>0) return;
                                menu.masonry({itemSelector: 'dl'});
                                var menu_height = menu.height();
                                if (menu_height < 60) menu.height(80);
                                menu_height = menu.height();
                                var li_top = $(this).position().top;
                                if ((li_top > 60) && (menu_height >= li_top)) $(menu).css("top",-li_top+50);
                                if ((li_top > 150) && (menu_height >= li_top)) $(menu).css("top",-li_top+90);
                                if ((li_top > 240) && (li_top > menu_height)) $(menu).css("top",menu_height-li_top+90);
                                if (li_top > 300 && (li_top > menu_height)) $(menu).css("top",60-menu_height);
                                if ((li_top > 40) && (menu_height <= 120)) $(menu).css("top",-5);
                                menu.attr("hover",1);
                            },
                            function() {
                                $(this).removeClass("hover");
                                var cat_id = $(this).attr("cat_id");
                                $(this).find("div[cat_menu_id='"+cat_id+"']").hide();
                            }
                    );
                }
        );
        $(".head-user-menu dl").hover(function() {
                    $(this).addClass("hover");
                },
                function() {
                    $(this).removeClass("hover");
                });
        $('.head-user-menu .my-mall').mouseover(function(){// 最近浏览的商品
            load_history_information();
            $(this).unbind('mouseover');
        });
        $('.head-user-menu .my-cart').mouseover(function(){// 运行加载购物车
            load_cart_information();
            $(this).unbind('mouseover');
        });
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
        if($('#keyword').val()==searchTxt);
            $('#keyword').attr("value","");
        return true;
    }
    $('#keyword').css("color","#999999");
</script>
<!-- <script language="javascript">
    // 加载购物车信息
    function load_cart_information(){
        $.getJSON('index.php?act=cart&op=ajaxcart', function(result){
            if(result){
                var result  = result;
                $('.addcart-goods-num').html(result.goods_all_num);
                var html = '';
                if(result.goods_all_num >0){
                    html+="<div class='incart-goods'>";
                    var i= 0;
                    var data = result['goodslist'];
                    for (i = 0; i < data.length; i++)
                    {
                        html+="<dl id='cart_item_"+data[i]['specid']+"' count='"+data[i]['num']+"'>";
                        html+="<dd class='goods-thumb'><span class='thumb size40'><i></i><img src='"+data[i]['images']+"' title='"+data[i]['gname']+"' onload='javascript:DrawImage(this,40,40);' ></span></dd>";
                        html+="<dt class='goods-name'><a href='http://192.168.1.220/index.php?act=goods&goods_id="+data[i]['goodsid']+"' title='"+data[i]['gname']+"' target='_top'>"+data[i]['gname']+"</a></dt>";

                        html+="<dd class='goods-price'><p>&yen;"+data[i]['price']+"×"+data[i]['num']+"</p><dd class='handle'><a  href='javascript:void(0)' onClick='drop_topcart_item("+data[i]['storeid']+","+data[i]['specid']+");'>删除</a></dd></dd>";
                        html+="</dl>";
                    }
                    html+="<div colspan='3' class='checkout'><span class='total-price'>共<i>"+result.goods_all_num+"</i>种商品   金额总计：<em>&yen;"+result.goods_all_price+"</em></span><span class='btn-cart' ><a href='http://192.168.1.220/index.php?act=cart' target='_top' title='结算商品' style='color: #FFF;' >结算购物车中的商品</a></span></div>";
                }else{
                    html="<div class='incart-goods'><div class='no-order'><span>您的购物车中暂无商品，赶快选择心爱的商品吧！</span></div><div class='checkout' ><a href='http://192.168.1.220/index.php?act=cart'  title='结算商品' class='btn-cart' >结算购物车中的商品</a></div></div>";
                }
                $(".incart-goods-box").html(html);
            }
        });
    }

    //头部删除购物车信息
    function drop_topcart_item(store_id, spec_id){
        var tr = $('#cart_item_' + spec_id);
        var amount_span = $('#cart_amount');
        var cart_goods_kinds = $('.addcart-goods-num');
        $.getJSON('index.php?act=cart&op=drop&specid=' + spec_id + '&storeid=' + store_id, function(result){
            if(result.done){
                //删除成功
                if(result.quantity == 0){
                    $('.addcart-goods-num').html('0');
                    var html = '';
                    html="<div class='incart-goods'><div class='no-order'><span>您的购物车中暂无商品，赶快选择心爱的商品吧！</span></div><div class='checkout' ><a href='http://192.168.1.220/index.php?act=cart'  title='结算商品' class='btn-cart' >结算购物车中的商品</a></div></div>";
                    $(".incart-goods-box").html(html);
                    html="<div class='addcart-goods-num'>0</div>";
                }
                else{
                    dl.remove();        //移除
                    amount_span.html(price_format(result.amount));  //刷新总费用
                    cart_goods_kinds.html(result.quantity);       //刷新商品种类
                }
            }else{
                alert(result.msg);
            }
        });
    }
</script> -->
</body>
</html>
</#macro>