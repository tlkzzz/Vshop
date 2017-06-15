<@p.header title="供应商中心"/>
<nav class="navmenu pngFix"><span class="left-side pngFix"></span><span class="right-side pngFix"></span>
    <ul>
        <li><a href="#?act=store" class="selected"><span>供应商中心</span></a></li>
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
   <#if supplier ??&& supplier.supplierState==1>
       <div class="sidebar"></div> 
   </#if> 
    <div class="right-content">
        <div class="main">

            <div class="wrap">
                <div class="open-store">
                    <h1>欢迎来到商品分销平台</h1>
                    <h3>您现在还没有供应商，无法对中心功能进行操作，您可以：</h3>
                    <div><em></em>
                        <dl>
                        	<#if supplier ??&& supplier.supplierState==2>
                                  <dt>该供应商正在审核中&nbsp;&#8250;</a></dt>
                            <#elseif supplier ??&& supplier.supplierState==0>
                                  <dt>该供应商已关闭&nbsp;&#8250;</a></dt>
                            <#elseif supplier ??&& supplier.supplierState==3>
                                  <dt>该供应商审核未通过请<a href="${base}/joinIn/step2?id=${supplier.id}" style="color: red;">重新填写注册信息</a></dt>
                                  <dt>原因：<span style="color: red;">${supplier.supplierCloseInfo}</span></dt>
                            <#else>
                                  <dt><a href="${base}/joinIn/step1">申请成为供应商&nbsp;&#8250;</a></dt>
                                  <dd>选择供应商类型并填写相关信息，即可开设您的供应商。</dd>
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