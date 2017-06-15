<@p.header title="供应商中心-信息设置"/>
<style>
	#home .intro .seller-rate dl .rate-star {
	  padding:0px;
	}
</style>
<#assign supplierName="供应商">
<div class="layout">
	<#if supplier ??&& supplier.supplierState==1>
       <div class="sidebar"></div> 
	<div class="right-content">
    <div class="path">
	    <div>
        	<a href="${base}">供应商中心</a> <span>></span> 账户概览              
	    </div>
    </div>
    
    <div class="main">
<div id="home">
  <div class="intro">
    <div class="left">
      <div class="store-pic"><span class="thumb size80"><i></i><img src="${imgServer}${supplier.supplierLogo}"  onload="javascript:DrawImage(this,80,80);" title="${supplier.name}" alt="${supplier.name}" /></a></span>
<!--       <em><a href="${base}/storeSetting/storeseting">更改店标</a></em> -->
      </div>
      <dl class="basic">
	        <dd class="member-name">${supplier.memberName}</dd>
	      <#--   <dd class="cert">
		          <a href="#?act=store&op=store_certified">尚未认证或认证失败</a>
		          <img src="${base}/res/images/member/cert_autonym_no.gif" title="尚未认证或认证失败" alt="尚未认证或认证失败"/>
		          <img src="${base}/res/images/member/cert_material_no.gif" title="尚未认证或认证失败" alt="尚未认证或认证失败"/>  
	        </dd> -->
<!-- 	        <dd class="credit"> <span> 供应商信用：${evaluateStore.averageCredit!'0'}</span> -->
<!-- 	        </dd> -->
      </dl>
      <dl>
        <dt>供应商名称：</dt>
        <dd><a href="JavaScript:void(0);" onclick="show_store('2');">${supplier.name}</a></dd>
<!--         <dd>${store.areaInfo}</dd> -->
      </dl>
<!--       <dl> -->
<!--         <dt>在线客服：</dt> -->
<!--         <dd><a href="${base}/easemob/msgview" target="_blank">${supplier.name}</a></dd> -->
<!--         <dd>${supplier.name}</dd> -->
<!--       </dl> -->
    </div>
    <div class="right seller-rate">
    <!-- 
      <h2>供应商动态评分：</h2>
      <dl>
        <dt>描述相符：</dt>
        <dd class="rate-star" data-score="${evaluateStore.sevalDesccredit}"></dd>
      </dl>
      <dl>
        <dt>服务态度：</dt>
        <dd class="rate-star" data-score="${evaluateStore.sevalServicecredit}"></dd>
      </dl>
      <dl>
        <dt>发货速度：</dt>
        <dd class="rate-star" data-score="${evaluateStore.sevalDeliverycredit}"></dd>
      </dl>
       -->
    </div>
  </div>
  <div class="seller-cont">
    <div class="l">
      <div class="stroe-info container">
        <div class="hd">
          <div class="shop-level">
<!--           <span>供应商等级：${storeGrade.sgName}</span>  -->
          <!-- <span>有效期：不限制</span> <span>可传商品：100</span> <span>可传图片：500</span> --> 
          </div>
          <h2>供应商提示</h2>
        </div>
        <div class="content">
          <dl class="focus">
            <h2>您需要关注的供应商情况：</h2>
            <dt>商品提示：</dt>
            <dd><a href="${base}/pro/store">仓库待上架商品 (<strong id="tj_goods_storage">${publishCount}</strong>)</a></dd>
            <dt>咨询提示：</dt>
            <dd><a href="#">会员的留言 (<strong id="tj_consult"></strong>)</a></dd>
           <!--  <dt>违规提示：</dt>
            <dd><a href="#" title="30天内举报记录数">被举报禁售 (<strong id="tj_inform">0</strong>)</a></dd> -->
          </dl>
          <ul>
	            <li><a href="${base}/pro/sale">出售中的商品 (<strong id="tj_goods_selling">${onlineCount}</strong>)</a></li>
	            <li><a href="${base}/pro/offShow">违规下架的商品 (<strong id="tj_goods_show0">${lockupCount}</strong>)</a></li>
	            <!-- <li><a href="#">可用金币数 (<strong>0</strong>)</a></li>
	            <li><a href="#">淘宝数据导入</a></li> -->
          </ul>
        </div>
      </div>
      <div class="business-info container">
        <div class="hd">
          <h2>交易提示</h2>
        </div>
        <div class="content">
          <dl class="focus">
            <h2>您需要立即处理的交易订单：</h2>
            <dt>近期售出：</dt>
            <dd><a href="${base}/trade/orderList">所有订单 (<strong id="tj_progressing">${allorderCount}</strong>)</a></dd>
            <!-- <dt>维权提示：</dt>
            <dd><a href="#">收到维权投诉 (<strong id="tj_complain">0</strong>)</a></dd> -->
          </dl>
          <ul>
            <li><a href="${base}/trade/orderList?orderState=10">待付款 (<strong id="tj_pending">${noPayment}</strong>)</a></li>
            <li><a href="${base}/trade/orderList?orderState=20">待发货 (<strong id="tj_shipped">${noDelivery}</strong>)</a></li>
            <li><a href="${base}/trade/orderList?orderState=30">待收货 (<strong id="tj_shipping">${waitDelivery}</strong>)</a></li>
            <li><a href="#">待评价 (<strong id="tj_evalseller">${waitEvaluate}</strong>)</a></li>
           <!--  <li><a href="#" title="30天内退款记录数"> 退款 (<strong id="tj_refund">0</strong>)</a></li>
            <li><a href="#" title="30天内退货记录数"> 退货 (<strong id="tj_return">0</strong>)</a></li> -->
           <!--  <li><a href="#" title="30天内订单记录数"> 近期售出 (<strong id="tj_order30">0</strong>)</a></li> -->
          </ul>
        </div>
      </div>
      
      <!-- <div class="predeposit-info container">
        <div class="hd">
          <h2>预存款账户</h2>
        </div>
        <div class="content">
          <div class="ico pngFix"></div>
          <div class="hint">
            <h3>您的账户余额：0.00 元</h3>
            <p class="hint">使用平台预存款账户是方便交易流程并可购买兑换金币，参与更多供应商促销增值服务</p>
            <a href="#" class="ncu-btn1 ml10 mt5"><span>账户充值</span></a></div>
          <ul style="margin: 0 0 5px 100px">
            <li><a href="#">查看充值记录</a></li>
            <li><a href="#">提现</a></li>
            <li><a href="#">出入明细</a></li>
          </ul>
        </div>
      </div> -->
      
      <!--  
      <div class="marketing-info container">
        <div class="hd">
          <h2>供应商运营推广</h2>
        </div>
        <div class="content">
          <dl class="tghd">
            <dt class="pngFix"><a href="#">团购活动</a></dt>
            <dd>参与平台发起的团购活动提高商品成交量及供应商浏览量</dd>
          </dl>
          <dl class="xszk">
            <dt class="pngFix"><a href="#">限时折扣</a></dt>
            <dd>在规定时间段内对供应商中所选商品进行打折促销活动</dd>
          </dl>
          <dl class="mjs">
            <dt class="pngFix"><a href="#">满即送</a></dt>
            <dd>商家自定义满即送标准与规则，促进购买转化率</dd>
          </dl>
          <dl class="zhxs">
            <dt class="pngFix"><a href="#">组合销售</a></dt>
            <dd>商品组合销售、多重搭配更多实惠、商家必备营销方式</dd>
          </dl>
          <dl class="ztc">
            <dt class="pngFix"><a href="#">直通车</a></dt>
            <dd>参加直通车服务的商品可出现在商品列表页侧面</dd>
          </dl>
          <dl class="djq">
            <dt class="pngFix"><a href="#">代金券</a></dt>
            <dd>自定义代金券使用规则并由平台统一展示供会员领取</dd>
          </dl>
          <dl class="ggfw">
            <dt class="pngFix"><a href="#">广告服务</a></dt>
            <dd>购买商城文字、图片、Flash广告位，提高供应商流量</dd>
          </dl>
          <dl class="zthd">
            <dt class="pngFix"><a href="#">主题活动</a></dt>
            <dd>选择商品参与平台发布的主题活动，审核后集中展示</dd>
          </dl>
          <dl class="yhq">
            <dt class="pngFix"><a href="#">优惠券</a></dt>
            <dd>商家自行发布的线下供应商优惠券，供会员打印使用</dd>
          </dl>
          <div class="clear"></div>
        </div>
      </div>
      -->
      
    </div>
    <div class="r">
      <div class="news container">
        <div class="hd">
          <h2>商城公告</h2>
        </div>
        <div class="content">
          <ul>
               <li><a target="_blank" href="#" title="促销功能满即送使用说明">促销功能满即送使用说明</a></li>
               <li><a target="_blank" href="#" title="促销功能限时折扣使用说明">促销功能限时折扣使用说明</a></li>
          </ul>
        </div>
      </div>
      <div class="contact container">
        <div class="hd">
          <h2>平台联系方式</h2>
        </div>
        <div class="content">
          <ul>
                        <li style="width:100%;">电话：${supplier.fixedTel}</li>
                        <li style="width:100%;">邮箱：${member.memberEmail}</li>
          </ul>
        </div>
      </div>
      <script type="text/javascript" src="#"></script> 
    </div>
  </div>
  <div class="wrap_line mb5">
    <div class="info"></div>
  </div>
</div>
<!-- 异步更新会员首页的数据 -->
<!-- <script>
$(function(){
	var timestamp=Math.round(new Date().getTime()/1000/60);//异步URL一分钟变化一次
    $.getJSON('index.php?act=store&op=statistics&rand='+timestamp, null, function(data){
    	if (data == null) return false;
		for(var a in data) {
			if(data[a] != 'undefined') {$('#tj_'+a).html(data[a]);}
		}
    });
});
</script>  -->  
     </div>
  </div>
  <div class="clear"></div>
</div>
<#elseif supplier ?? && supplier.supplierState == 0>
	<div class="main" >
            <div class="wrap" style="width: 970px;margin-left: 6px;">
                <div class="open-store">
                    <div style="padding-left: 240px;">
                        <em style="margin-top:-9px;"></em>
                        <dl>
                          <dt style="font-size: 3em;">该${supplierName}已关闭！&nbsp;</a></dt>
                        </dl>
                    <div class="clear"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<#elseif supplier ?? && supplier.supplierState == 3>
	<div class="main" >
            <div class="wrap" style="width: 970px;margin-left: 6px;">
                <div class="open-store">
                    <div style="padding-left: 240px;">
                        <em style="margin-top:-9px;"></em>
                        <dl>
                          <dt style="font-size: 3em;">审核未通过，请<a href="${base}/joinIn/step2?id=${supplier.id}">重新注册</a>！&nbsp;</a></dt>
                          <dt>原因：<span style="color: red;">${supplier.supplierCloseInfo}</span></dt>
                        </dl>
                    <div class="clear"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<#else>
    <div class="main" >
            <div class="wrap" style="width: 970px;margin-left: 6px;">
                <div class="open-store">
                    <div style="padding-left: 240px;">
                        <em style="margin-top:-9px;"></em>
                        <dl>
                          <dt style="font-size: 3em;">该供应商正在审核中&nbsp;</a></dt>
                        </dl>
                    <div class="clear"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#if>
<script type="text/javascript" src="${base}/res/js/jquery.raty/jquery.raty.min.js"></script>
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
		
		$(".rate-star").raty({
			path : "${base}/res/js/jquery.raty/img",
			hints : [ '一般', '不错', "很好", '满意', '非常满意' ],
			readOnly : true,
			width : 100,
			score : function() {
				return $(this).attr("data-score");
			}
		})
		
	});
</script>
</body>
</html>
<@p.footer/>