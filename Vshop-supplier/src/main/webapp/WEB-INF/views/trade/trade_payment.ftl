<@p.header title="支付方式"/>
<div class="layout">
    <div class="sidebar">
    
  </div>
  <div class="right-content">
        <div class="path">
      <div><a href="${base}">供应商中心</a> <span>></span>
                <a href="#?act=store&op=payment"/>
                支付方式                </a><span>></span>支付方式设置              </div>
    </div>
    <div class="main">
      
<div class="wrap">
  <div class="tabmenu">
    <ul class="tab pngFix">
	     <li class="active">
	     <a  href="#?act=store&op=payment">支付方式设置</a>
	     </li>
    </ul>
  </div>
  <table class="ncu-table-style">
    <thead>
      <tr>
        <th class="w150">名称</th>
        <th class="tl">插件说明</th>
        <th class="w90">启用</th>
        <th class="w90">操作</th>
      </tr>
    </thead>
    <tbody>
      <tr class="bd-line">
        <td>支付宝</td>
        <td class="tl">支付宝网站(www.alipay.com) 是国内先进的网上支付平台，ShopNC联合支付宝推出优惠套餐：无预付/年费，单笔费率1.2%，无流量限制。<br><a href="http://act.life.alipay.com/systembiz/shopnc" target="_blank" style="color:red; font-weight:bold;">立即在线申请</a>（<a href="http://club.alipay.com/read-htm-tid-10077293.html" target="_blank">如何启用支付宝收款</a>）</td>
        <td>否</td>
        <td>   
            <a href="#?act=store&op=add_payment&payment_code=alipay" class="ncu-btn2 mt5">安装</a>
        </td>
      </tr>
      <tr class="bd-line">
        <td>网银在线即时到帐</td>
        <td class="tl">网银在线(www.chinabank.com.cn)以网上转账方式将相应交易款划转到商户指定银行账号中。</td>
        <td>否</td>
        <td>    
              <a href="#?act=store&op=add_payment&payment_code=chinabank" class="ncu-btn2 mt5">安装</a>
        </td>
      </tr>
      <tr class="bd-line">
        <td>货到付款</td>
        <td class="tl"></td>
        <td>否</td>
        <td>       
           <a href="#?act=store&op=add_payment&payment_code=cod" class="ncu-btn2 mt5">安装</a>
        </td>
      </tr>
      <tr class="bd-line">
        <td>银行汇款</td>
        <td class="tl"></td>
        <td>是</td>
        <td>    
           <a href="#?act=store&op=add_payment&payment_code=offline">配置插件</a> <a href="javascript:void(0);" onclick="ajax_get_confirm('您确实要卸载该插件吗?', 'index.php?act=store&op=uninstall_payment&payment_id=4');" class="ncu-btn2 mt5">卸载</a>
        </td>
      </tr>
      <tr class="bd-line">
        <td>预存款支付</td>
        <td class="tl"></td>
        <td>否</td>
        <td>   
              <a href="#?act=store&op=add_payment&payment_code=predeposit" class="ncu-btn2 mt5">安装</a>
        </td>
      </tr>
      <tr class="bd-line">
        <td>财付通即时到帐</td>
        <td class="tl">财付通(www.tenpay.com) - 本即时到账接口无需预付费，任何订单金额均即时到达您的账户，只收单笔 1% 手续费。</td>
        <td>否</td>
        <td>       
           <a href="#?act=store&op=add_payment&payment_code=tenpay" class="ncu-btn2 mt5">安装</a>
        </td>
      </tr>
          </tbody>
  </table>
	</div>   
  </div>
 </div>
    <div class="clear"></div>
</div>
<script type="text/javascript">
	// 收缩展开效果
	$(document).ready(function() {
		$(".sidebar dl dt").click(function() {
			$(this).toggleClass("hou");
			var sidebar_id = $(this).attr("id");
			var sidebar_dd = $(this).next("dd");
			sidebar_dd.slideToggle("slow", function() {
				$.cookie(COOKIE_PRE + sidebar_id, sidebar_dd.css("display"), {
					expires : 7,
					path : '/'
				});
			});
		});
	});
</script>
</body>
</html>
<@p.footer/>