<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${title}</title>
    <meta name="keywords" content="商城系统演示站"/>
    <meta name="description" content="商城系统演示站"/>
    <meta name="author" content="商城系统演示站">
    <meta name="copyright" content="商城系统演示站">
    <link rel="shortcut icon" href="${base}/res/css/favicon.ico"/>
    <link href="${base}/res/css/base.css" rel="stylesheet" type="text/css">
    <link href="${base}/res/css/sns.css" rel="stylesheet" type="text/css">
    <link id="skin_link" href="${base}/res/css/sns/style/skin_01.css" rel="stylesheet" type="text/css">
    <!--[if IE]>
    <script src="${base}/res/js/html5.js"></script>
    <![endif]-->
    <!--[if IE 6]>
    <script src="${base}/res/js/IE6_MAXMIX.js"></script>
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
    <!--[if IE 6]>
    <style type="text/css">
        body {
            _behavior: url(${base}/res/js/csshover.htc);
        }
    </style>
    <![endif]-->
    <script type="text/javascript">
        var COOKIE_PRE = '5C83_';
        var _CHARSET = 'utf-8';
        var SITEURL = 'http://192.168.1.220';
        var MAX_RECORDNUM = 20;
    </script>
    <script type="text/javascript" src="${base}/res/js/jquery.js"></script>
    <script type="text/javascript" src="${base}/res/js/jquery-ui/jquery.ui.js"></script>
    <script type="text/javascript" src="${base}/res/js/jquery.validation.min.js"></script>
    <script type="text/javascript" src="${base}/res/js/jquery.charCount.js"></script>
    <script type="text/javascript" src="${base}/res/js/common.js" charset="utf-8"></script>
    <script type="text/javascript" src="${base}/res/js/dialog/dialog.js" id="dialog_js" charset="utf-8"></script>
    <script type="text/javascript" src="${base}/res/js/member/member.js" charset="utf-8"></script>
    <script type="text/javascript" src="${base}/res/js/utils.js" charset="utf-8"></script>
    <script type="text/javascript" src="${base}/res/js/sns/sns.js" charset="utf-8"></script>
    <script type="text/javascript" src="${base}/res/js/sns/sns_friend.js" charset="utf-8"></script>
    <script type="text/javascript" src="${base}/res/js/sns/sns_store.js" charset="utf-8"></script>
    <script type="text/javascript" src="${base}/res/js/smilies/smilies.js" charset="utf-8"></script>
    <script type="text/javascript" src="${base}/res/js/smilies/smilies_data.js" charset="utf-8"></script>
    <script type="text/javascript" src="${base}/res/js/jquery.caretInsert.js" charset="utf-8"></script>


</head>
<body>
<script type="text/javascript" src="${base}/res/js/common2.2.js" charset="utf-8"></script>
<link href="${base}/res/css/dialog2.2.css" rel="stylesheet" type="text/css">
<div id="append_parent"></div>
<div id="ajaxwaitid"></div>
<div class="public-top-layout w">
    <div class="topbar wrapper">
        <div class="user-entry">
        <@shiro.user>
            您好<span>&nbsp;&nbsp;<a href="${base}/user/setting/index/"><@shiro.principal/></a></span>，欢迎来到<a href="${base}"  title="首页" alt="首页">商品汇平台</a>
            <span>[<a href="${base}/logout">退出</a>]</span>
        </@shiro.user>
        <@shiro.guest>
            您好，欢迎来到<a href="${base}" title="首页" alt="首页">商品汇平台</a>
            <span>[<a href="login">登录</a>]</span>
            <span>[<a href="signUp">注册</a>]</span>
        </@shiro.guest>
	  <span class="seller-login">
	<a href="http://192.168.1.220/index.php?act=store" target="_top" title="商家中心" target="_blank" title="登录商家管理中心"><i
            class="icon-signin"></i>商家管理中心</a></span>
        </div>

        <div class="quick-menu">
            <dl>
                <dt><a href="http://192.168.1.220/index.php?act=member&op=order">我的订单</a><i></i></dt>
                <dd>
                    <ul>
                        <li><a href="http://192.168.1.220/index.php?act=member&op=order&state_type=order_pay">待付款订单</a>
                        </li>
                        <li>
                            <a href="http://192.168.1.220/index.php?act=member&op=order&state_type=order_shipping">待确认收货</a>
                        </li>
                        <li><a href="http://192.168.1.220/index.php?act=member&op=order&state_type=noeval">待评价交易</a>
                        </li>
                    </ul>
                </dd>
            </dl>
            <dl>
                <dt><a href="http://192.168.1.220/index.php?act=member_favorites&op=fglist" title="我的收藏" target="_top">我的收藏</a><i></i>
                </dt>
                <dd>
                    <ul>
                        <li><a href="http://192.168.1.220/index.php?act=member_favorites&op=fglist" target="_top"
                               title="收藏的商品">收藏的商品</a></li>
                        <li><a href="http://192.168.1.220/index.php?act=member_favorites&op=fslist" target="_top"
                               title="收藏的商家">收藏的商家</a></li>
                    </ul>
                </dd>
            </dl>
            <dl>
                <dt>客户服务<i></i></dt>
                <dd>
                    <ul>
                        <li><a href="http://192.168.1.220/index.php?act=article&ac_id=2">帮助中心</a></li>
                        <li><a href="http://192.168.1.220/index.php?act=article&ac_id=5">售后服务</a></li>
                        <li><a href="http://192.168.1.220/index.php?act=article&ac_id=6">客服中心</a></li>
                    </ul>
                </dd>
            </dl>

            <dl>
                <dt>站点导航<i></i></dt>
                <dd>
                    <ul>
                        <li><a target="_blank" href="${base}">商家首页</a></li>
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
<!-- PublicHeadLayout Begin -->
<header id="header" class="pngFix">
    <div class="wrapper">
        <h1 id="logo" title="商品汇平台"><a href="${base}"><img
                src="${base}/res/images/common/690ea902fe2708381da2cabff4ee46c8.png" alt="商品汇平台" class="pngFix"></a></h1>

        <h2>个人主页</h2>

        <div class="search">
            <form id="formSearch" name="formSearch" onSubmit="return searchInput();" action="#? method="get">
                <input id="search_act" type="hidden" value="search" name="act">
                <input id="keyword" class="ncs-search-input-text" type="text" lang="zh-CN"
                       x-webkit-grammar="builtin:search" onwebkitspeechchange="foo()" x-webkit-speech=""
                       onBlur="searchBlur(this)" onFocus="searchFocus(this)" name="keyword"
                       style="color: rgb(153, 153, 153);">
                <a class="ncs-search-btn-mall" nctype="search_in_shop" href="javascript:void(0)"
                   onClick="$('#formSearch').submit();"> <span>搜索</span> </a>
            </form>
        </div>
        <ul class="menu">
            <li class="noborder"><a href="#?act=member_snsindex">返回我的商城</a></li>
            <li><a href="javascript:void(0)" class="my-friend">我关注的<i></i></a>

                <div class="friend-menu">
                    <dl>
                        <dd><a href="javascript:void(0);">还没有关注过。</a></dd>
                    </dl>
                    <p>
                        <a href="#?act=member_snsfriend&op=find">查找好友...</a>
                    </p>
                </div>
            </li>
            <li><a href="#?act=member_snshome">我的个人主页</a></li>
        </ul>
    </div>
</header>
<div id="container" class="wrapper mt20">
    <div class="user-info">
        <div class="user-face">
            <div class="hover-layout"><span class="thumb size120"><i></i><img
                    src="${base}/res/images/default_user_portrait.gif"/>
                <a href="#?act=home&op=avatar" title="">更改头像</a>
                </span></div>
        </div>
        <dl class="user-data">
            <dt>
            <h2>15910576548</h2>
            <h4>会员信用： <span>- 暂无信用</span></h4>
        <span class="add-friend ml10"><span style="display:none;" nc_type="mutualsign">互相关注</span><span
                style="display:none;" nc_type="followsign">已关注</span>
                </span> </dt>
            <dd>
                <span class="location"></span>
                <span><a href="#?act=home&op=member" title="编辑资料">编辑资料</a></span>
            </dd>
        </dl>
        <div class="user-stat">
            <dl class="noborder">
                <dd>0</dd>
                <dt>粉丝</dt>
            </dl>
            <dl>
                <dd>0</dd>
                <dt>关注</dt>
            </dl>
            <dl>
                <dd>3</dd>
                <dt>访问</dt>
            </dl>
        </div>
    </div>
    <div class="sns-nav">
        <ul>
            <li><a class="current" href="#?act=member_snshome&mid=7"><i class="home pngFix"></i>首页</a></li>
            <li><a href="#?act=member_snshome&op=shareglist&mid=7"><i class="goods pngFix"></i>商品</a></li>
            <li><a href="#?act=member_snshome&op=storelist&mid=7"><i class="store pngFix"></i>商家</a></li>
            <li><a href="#?act=sns_album&mid=7"><i class="album pngFix"></i>相册</a></li>
            <li><a href="#?act=member_snshome&op=trace&mid=7"><i class="news pngFix"></i>新鲜事</a></li>
        </ul>
        <div class="skin"><a href="javascript:void(0)" title="选择皮肤" class="pngFix" nctype="change_skin">选择皮肤</a></div>
    </div>
    <div class="sns-main">
        <link href="${base}/res/js/jcarousel/skins/tango/skin.css" rel="stylesheet" type="text/css">
        <style type="text/css">
            .jcarousel-skin-tango .jcarousel-clip-horizontal {
                width: 600px !important;
                height: 142px !important;
            }

            .jcarousel-skin-tango .jcarousel-item {
                width: 140px !important;
                height: 140px !important;
            }

            .jcarousel-skin-tango .jcarousel-container-horizontal {
                width: 600px !important;
            }
        </style>
        <div class="sidebar">
            <div class="visitors pngFix">
                <h4><span class="active" nc_type="visitmodule" data-param='{"name":"visit_me"}'>谁来看过我</span><span
                        class="line">|</span><span class="normal" nc_type="visitmodule"
                                                   data-param='{"name":"visit_other"}'>我访问过的人</span></h4>
                <ul id="visit_me" nc_type="visitlist">
                    最近没有人访问过您的空间，多关注些<a href="#?act=member_snsfriend&op=find">好友</a>互动吧~
                </ul>
                <ul id="visit_other" nc_type="visitlist" style="display: none;">
                    最近您没有浏览过好友的空间，赶快去看看<a href="#?act=member_snsfriend&op=follow">好友</a>最近搜罗了哪些宝贝
                </ul>
            </div>
            <script>
                $(function () {
                    $("[nc_type='visitmodule']").bind('click', function () {
                        var data_str = $(this).attr('data-param');
                        eval("data_str = " + data_str);
                        $("[nc_type='visitmodule']").removeClass('active');
                        $("[nc_type='visitmodule']").addClass('normal');
                        $(this).removeClass('normal');
                        $(this).addClass('active');
                        $("[nc_type='visitlist']").hide();
                        $("#" + data_str.name).show();
                    });
                });
            </script>
            <div class="side-message">
                <div class="title">
                    <h4>访客留言</h4>
                </div>
                <div nctype="message_list" class="message-list">
                    <div>暂无任何留言。</div>
                </div>
            </div>
            <script>
                $(function () {
                    $("[nc_type='visitmodule']").bind('click', function () {
                        var data_str = $(this).attr('data-param');
                        eval("data_str = " + data_str);
                        $("[nc_type='visitmodule']").removeClass('active');
                        $("[nc_type='visitmodule']").addClass('normal');
                        $(this).removeClass('normal');
                        $(this).addClass('active');
                        $("[nc_type='visitlist']").hide();
                        $("#" + data_str.name).show();
                    });

                    // 回复提交
                    $("[nctype='commentbtn']").live('click', function () {
                        if ($("#send_form").valid()) {
                            ajaxpost('send_form', '', '', 'onerror');
                        }
                        return false;
                    });
                    $('#send_form').validate({
                        errorPlacement: function (error, element) {
                            element.after(error);
                        },
                        rules: {
                            msg_content: {
                                required: true,
                                maxlength: 140
                            }
                        },
                        messages: {
                            msg_content: {
                                required: '留言不能为空',
                                maxlength: '留言不能超过140个字符'
                            }
                        }
                    });

                    //评论字符个数动态计算
                    $("#content_msg").charCount({
                        allowed: 140,
                        warning: 10,
                        counterContainerID: 'commentcharcount',
                        errortype: 'negative'
                    });

                    // 回复
                    $('a[nctype="reply_msg"]').live('click', function () {
                        var p_dd = $(this).parents('dd:first');
                        var data;
                        eval('data = ' + p_dd.attr('data-param'));
                        if (!p_dd.next().is('dd[nctyoe="replyform"]')) {
                            $('<dd nctyoe="replyform" class="re-msg"></dd>')
                                    .append('<i></i>')
                                    .append('<form id="replyform' + data.msgid + '" action="#?act=home&op=savereply" method="post"></form>').children('form')
                                    .append('<input type="hidden" value="ok" name="form_submit"><input type="hidden" value="' + data.msgid + '" name="message_id">')
                                    .append('<textarea class="re-msg-content" name="msg_content" id="content_msg' + data.msgid + '" placeholder="回复@' + data.fname + '~"></textarea><div class="action"></div>').children('div')
                                    .append('<a class="face" nc_type="smiliesbtn" data-param=\'{"txtid":"msg' + data.msgid + '"}\' href="javascript:void(0);">表情</a>')
                                    .append('<a nc_type="commentbtn' + data.msgid + '" class="btn" href="javascript:void(0);">提交</a>')
                                    .append('<span class="charcount"><em id="commentcharcount' + data.msgid + '"></em>/140</span>')
                                    .end().end().insertAfter(p_dd);
                            //评论字符个数动态计算
                            $("#content_msg" + data.msgid).charCount({
                                allowed: 140,
                                warning: 10,
                                counterContainerID: 'commentcharcount' + data.msgid,
                                errortype: 'negative'
                            });
                            // 回复提交
                            $("[nc_type='commentbtn" + data.msgid + "']").live('click', function () {
                                if ($("#content_msg" + data.msgid).val().length <= 140) {
                                    ajaxpost("replyform" + data.msgid, '', '', 'onerror');
                                }
                                return false;
                            });
                        }
                    });
                });

                function leaveMsgSuccess(data) {
                    $('<dl></dl>')
                            .append('<dt><a href="#?act=member_snshome&mid=' + data.from_member_id + '">' + data.from_member_name + '：</a><span>' + data.msg_content + '</span></dt>')
                            .append('<dd data-param="{\'msgid\':\'' + data.msg_id + '\',\'fname\':\'' + data.from_member_name + '\'}"><span class="time">刚刚</span><span class="handle"><a href="javascript:void(0);"onclick="ajax_get_confirm(\'您确定要删除该信息吗？\', \'index.php?act=home&op=dropcommonmsg&drop_type=sns_msg&message_id=' + data.msg_id + '\');">删除</a></span></dd>')
                            .prependTo('div[nctype="message_list"]');
                    $('div[nctype="message_list"]').children('div').hide();
                }

                function replyMsgSuccess(data) {
                    $('dd[nctyoe="replyform"]').remove();
                    var to = $('dl[nctype="dl' + data.message_parent_id + '"]').children('dd:last');
                    $('<dl class="re-content"></dl>')
                            .append('<dt><a href="#?act=member_snshome&mid=' + data.from_member_id + '">' + data.from_member_name + '回复：</a><span>' + data.msg_content + '</span></dt>')
                            .append('<dd data-param="{\'msgid\':\'' + data.msg_id + '\'}"><span class="time">刚刚</span></dd>')
                            .appendTo(to);
                }
            </script>
        </div>
        <div class="left-content">
            <!-- 分享商品 START -->
            <!-- 分享商品 END -->
            <!-- 分享图片 START -->
            <!-- 分享图片 END -->
            <!-- 分享商家 START -->
            <!-- 分享商家 END -->
            <!-- 新鲜事 START -->
            <!-- 新鲜事 END -->
            <!-- 为空提示 START -->
            <div class="sns-norecord"><i class="store-ico pngFix"></i><span>很遗憾！<br/>
    我的空间暂无任何分享~</span></div>
            <!-- 为空提示 END -->
        </div>
        <div class="clear"></div>
        <script type="text/javascript"
                src="${base}/res/js/jcarousel/jquery.jcarousel.min.js"></script>
        <script type="text/javascript">
            $(function () {
                //图片轮换
                $('[nc_type="mycarousel"]').jcarousel({visible: 4});
                //删除分享的商家
                $("[nc_type='storedelbtn']").live('click', function () {
                    var data_str = $(this).attr('data-param');
                    eval("data_str = " + data_str);
                    showDialog('您确定要删除该信息吗？', 'confirm', '', function () {
                        ajax_get_confirm('', 'index.php?act=member_snsindex&op=delstore&id=' + data_str.sid);
                        return false;
                    });
                });
            });
        </script>
    </div>
    <!-- 表情弹出层 -->
    <div id="smilies_div" class="smilies-module"></div>
</div>
<div id="footer">
    <div class="wrapper">
        <p><a href="http://192.168.1.220">首页</a>
            | <a href="#?act=article&article_id=24">招聘英才</a>
            | <a href="#?act=article&article_id=25">广告合作</a>
            | <a href="#?act=article&article_id=23">联系我们</a>
            | <a href="#?act=article&amp;article_id=22">关于我们</a>
        </p>
        Copyright 2016-2017 磁石世纪（北京）投资管理有限公司 版权所有  京ICP备16011767号.&nbsp;&nbsp;
        ICP证：<br/>
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
        $('.head-user-menu .my-cart').mouseover(function () {// 运行加载购物车
            load_cart_information();
            $(this).unbind('mouseover');
        });
    });

</script>
<script language="javascript">
    var searchTxt = ' 搜索其实很容易！';
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
        $.getJSON('index.php?act=cart&op=ajaxcart', function (result) {
            if (result) {
                var result = result;
                $('.addcart-goods-num').html(result.goods_all_num);
                var html = '';
                if (result.goods_all_num > 0) {
                    html += "<div class='incart-goods'>";
                    var i = 0;
                    var data = result['goodslist'];
                    for (i = 0; i < data.length; i++) {
                        html += "<dl id='cart_item_" + data[i]['specid'] + "' count='" + data[i]['num'] + "'>";
                        html += "<dd class='goods-thumb'><span class='thumb size40'><i></i><img src='" + data[i]['images'] + "' title='" + data[i]['gname'] + "' onload='javascript:DrawImage(this,40,40);' ></span></dd>";
                        html += "<dt class='goods-name'><a href='http://192.168.1.220/index.php?act=goods&goods_id=" + data[i]['goodsid'] + "' title='" + data[i]['gname'] + "' target='_top'>" + data[i]['gname'] + "</a></dt>";

                        html += "<dd class='goods-price'><p>&yen;" + data[i]['price'] + "×" + data[i]['num'] + "</p><dd class='handle'><a  href='javascript:void(0)' onClick='drop_topcart_item(" + data[i]['storeid'] + "," + data[i]['specid'] + ");'>删除</a></dd></dd>";
                        html += "</dl>";
                    }
                    html += "<div colspan='3' class='checkout'><span class='total-price'>共<i>" + result.goods_all_num + "</i>种商品   金额总计：<em>&yen;" + result.goods_all_price + "</em></span><span class='btn-cart' ><a href='http://192.168.1.220/index.php?act=cart' target='_top' title='结算商品' style='color: #FFF;' >结算购物车中的商品</a></span></div>";
                } else {
                    html = "<div class='incart-goods'><div class='no-order'><span>您的购物车中暂无商品呢，赶快选择心爱的商品吧！</span></div><div class='checkout' ><a href='http://192.168.1.220/index.php?act=cart'  title='结算商品' class='btn-cart' >结算购物车中的商品</a></div></div>";
                }
                $(".incart-goods-box").html(html);
            }
        });
    }

    //头部删除购物车信息
    function drop_topcart_item(store_id, spec_id) {
        var tr = $('#cart_item_' + spec_id);
        var amount_span = $('#cart_amount');
        var cart_goods_kinds = $('.addcart-goods-num');
        $.getJSON('index.php?act=cart&op=drop&specid=' + spec_id + '&storeid=' + store_id, function (result) {
            if (result.done) {
                //删除成功
                if (result.quantity == 0) {
                    $('.addcart-goods-num').html('0');
                    var html = '';
                    html = "<div class='incart-goods'><div class='no-order'><span>您的购物车中暂无商品呢，赶快选择心爱的商品呢吧！</span></div><div class='checkout' ><a href='http://192.168.1.220/index.php?act=cart'  title='结算商品' class='btn-cart' >结算购物车中的商品</a></div></div>";
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
    }
</script>
<script>
    /* $(function () {
        $("#rightsead a").hover(function () {
            if ($(this).prop("className") == "youhui") {
                $(this).children("img.hides").show();
            } else {
                $(this).children("img.hides").show();
                $(this).children("img.shows").hide();
                $(this).children("img.hides").animate({marginRight: '0px'}, 'slow');
            }
        }, function () {
            if ($(this).prop("className") == "youhui") {
                $(this).children("img.hides").hide('slow');
            } else {
                $(this).children("img.hides").animate({marginRight: '-143px'}, 'slow', function () {
                    $(this).hide();
                    $(this).next("img.shows").show();
                });
            }
        });

        $("#top_btn").click(function () {
            if (scroll == "off") return;
            $("html,body").animate({scrollTop: 0}, 600);
        });

    }); */
</script>
<script type="text/javascript" src="${base}/res/js/jquery.ajaxContent.pack.js"></script>
<script type="text/javascript" language="javascript">
    var max_recordnum = '20';

    $(function () {
        // 显示关注好友名单
        $(".my-friend").click(function () {
            $(".friend-menu").slideToggle("1000");
        });

        //存在多规格时的价格修改
        $('a[nctype="change_skin"]').click(function () {
            ajax_form('change_skin', '选择皮肤', SITEURL + '/index.php?act=sns_setting&op=change_skin', '480');
        });
    });
</script>
</body>
</html>
