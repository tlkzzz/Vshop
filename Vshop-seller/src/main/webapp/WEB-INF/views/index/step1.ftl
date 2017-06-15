<@p.header title="商家中心"/>
<nav class="navmenu pngFix"><span class="left-side pngFix"></span><span class="right-side pngFix"></span>
    <ul>
        <li><a href="#?act=store" class="selected"><span>商家中心</span></a></li>
        <li><a class="" href="#?act=member_snsindex"><span>会员中心</span></a></li>
        <li><a class="" href="#?act=home&op=member"><span>账户设置</span></a></li>
    </ul>
</nav>

<script type="text/javascript">
    // 收缩展开效果
    $(document).ready(function(){
        $(".sidebar dl dt").click(function(){
            $(this).toggleClass("hou");
            var sidebar_id = $(this).attr("id");
            var sidebar_dd = $(this).next("dd");
            sidebar_dd.slideToggle("slow",function(){
                $.cookie(COOKIE_PRE+sidebar_id, sidebar_dd.css("display"), { expires: 7, path: '/'});
            });
        });
        $('.sidebar').find('dd').css('display','none');
    });
</script>
<div class="layout">
   <#if store ??&& store.storeState==1>
       <div class="sidebar"></div> 
   </#if> 
    <div class="right-content">
        <div class="main">

            <div class="wrap">
                <div class="open-store">
                    <h1>欢迎来到商品汇商家平台</h1>
                    <h3>您现在还没有商家，无法对商家中心功能进行操作，您可以：</h3>
                    <div><em></em>
                        <dl>
                            <#if store ??&& store.storeState==2>
                                  <dt>该商家正在审核中&nbsp;&#8250;</a></dt>
                            <#elseif store ??&& store.storeState==0>
                                  <dt>该商家已关闭&nbsp;&#8250;</a></dt>
                            <#elseif store ??&& store.storeState==3>
                                  <dt>该商家审核未通过请<a href="${base}/joinIn/step2?storeId=${store.storeId}" style="color: red;">重新填写注册信息</a></dt>
                                  <dt>原因：<span style="color: red;">${store.storeCloseInfo}</span></dt>
                            <#else>
                                  <dt><a href="${base}/joinIn/step1">申请成为商家&nbsp;&#8250;</a></dt>
                                  <dd>选择商家类型并填写相关信息，即可开设您的商家。</dd>
                            </#if>
                        </dl><div class="clear"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="clear"></div>
</div>
<@p.footer/>