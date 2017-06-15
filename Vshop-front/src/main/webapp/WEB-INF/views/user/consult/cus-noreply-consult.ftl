<@p.userHeader title="会员首页"/>
<script type="text/javascript" src="${base}/res/js/dialog/dialog.js" id="dialog_js" charset="utf-8"></script> 
<script type="text/javascript" src="${base}/res/js/nc-sideMenu.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/utils.js" charset="utf-8"></script>
<link href="${base}/res/css/font-icons/style.css"  rel="stylesheet" />
<div id="container" class="wrapper">
	<div class="layout">
    	<div class="sidebar">
			<dl class="ncu-user">
        		<dt class="username"><a href="#?act=home&op=member" title="编辑用户信息">${member.memberName}</a></dt>
        		<dd class="userface">
	          		<div class="pic"><span class="thumb size100"><i></i><img src="upload/common/default_user_portrait.gif" onload="javascript:DrawImage(this,100,100);" alt="${member.memberName}" /></span>
	            		<p><a href="#?act=home&op=avatar" title="上传新头像">上传新头像</a><i></i></p>
	          		</div>
        		</dd>
        		<dd class="info">
          			<ul>
            			<li>会员信用：0</li>
                        <li>积分数：160</li>
                        <li>预存款：<span class="price">0.00</span>元</li>
                     </ul>
        		</dd>
      		</dl>
      		<div class="business-intro">
        		<h3>交易信息</h3>
        		<ul>
          			<li><span><a href="#?act=member&op=order&state_type=order_pay">待付款订单</a></span> <i class='yes'>1</i></li>
          			<li><span><a href="#?act=member&op=order&state_type=order_shipping">待确认收货</a></span> <i class='yes'>1</i></li>
          			<li><span><a href="#?act=member&op=order&state_type=noeval">待评价交易</a></span> <i class='no'>0</i></li>
        		</ul>
      		</div>
		    <script type="text/javascript">
				var myMenu;
				window.onload = function() {
					myMenu = new SDMenu("my_menu");
					myMenu.init();
				};
			</script>
      		<div class="business-handle" id="my_menu">
        		<h3>交易操作</h3>
                <div class='normal'><em class="i2"></em><a href="${base}/user/list">已买到的商品</a></div>
        		<!--<div><em class="i3"></em><a href="javascript:void(0)">购买过的商家</a> </div>-->
        		<dl class="collapsed">
					<dt><em class="i4"></em><a href="javascript:void(0)">我的收藏</a><i></i></dt>
					<dd class='normal'><a href="${base}/myuser/goodsFavList">收藏商品</a></dd>
					<dd class='normal'><a href="${base}/myuser/storeFavList">收藏商家</a></dd>
                </dl>
                <dl class="collapsed">
          			<dt><em class="i5"></em><a href="javascript:void(0)">我的积分</a><i></i></dt>
          			<dd class='normal'><a href="#?act=member_points">积分明细</a></dd>
                    <dd class='normal'><a href="#?act=member_pointorder">已兑换的商品</a></dd>
                </dl>
                <div class='normal'>
                	<em class="i6"></em>
                	<a href="#?act=member_voucher">我的代金券</a>
        		</div>
                <div class='normal'>
                	<em class="i7"></em>
                	<a href="${base}/myReviews/reviewsBuyerList">评价管理</a>
                </div>
        		<dl class="collapsed">
          			<dt><em class="i8"></em><a href="javascript:void(0)">咨询与维权</a><i></i></dt>
          			<dd class='normal'><a href="${base}/myInform/index">我的举报</a></dd>
          			<dd class='active'><a href="#">我的咨询</a></dd>
          			<dd class='normal'><a href="#?act=member_complain">投诉管理</a></dd>
        		</dl>
      		</div>
   		</div>
    	<div class="right-content">
      		<div class="path">
        		<div>
        			<a href="#?act=member_snsindex">我的商家</a><span>&raquo;</span>
                    <a href="#?act=member_consult&op=my_consult"/>我的咨询</a><span>&raquo;</span>
                                       全部咨询                
             	</div>
      		</div>
      		<div class="main">
				<div class="wrap">
  					<div class="tabmenu">
    					<ul class="tab pngFix">
  							<li class="normal"><a  href="${base}/myconsult/index">全部咨询</a></li>
  							<li class="active"><a  href="#">未回复咨询</a></li>
  							<li class="normal"><a  href="${base}/myconsult/replyConsultList">已回复咨询</a></li>
  						</ul>
  					</div>
  					<table class="ncu-table-style order">
    					<thead>
      						<tr>
        						<th class="w30"></th>
        						<th>咨询/回复</th>
        						<th class="w30"></th>
      						</tr>
    					</thead>
    					<tbody>
                  			<tr>
						    	<td colspan="19" class="sep-row"></td>
						    </tr>
							<tr>
  								<th colspan="20"><span class="ml10"><a href="#?act=goods&goods_id=43" target="_blank">清爽型BB霜裸妆/倍润保湿爽肤水控油收缩毛孔【演示数据】</a></span><span class="ml20">咨询时间：<em class="goods-time">2015-06-26 14:55:28</em></span></th>
							</tr>
 							<tr>
   								<td class="tl bdl"></td>
   								<td class="tl"><strong>咨询内容：</strong><span class="gray">的飒飒打的</span></td>
   								<td class="bdr"></td>
  							</tr>
						</tbody>
	    				<tfoot>
		            		<tr>
		        				<td colspan="20"><div class="pagination"> <ul><li><span>首页</span></li><li><span>上一页</span></li><li><span class="currentpage">1</span></li><li><span>下一页</span></li><li><span>末页</span></li></ul> </div></td>
		      				</tr>
	          			</tfoot>
  					</table>
				</div>
      		</div>
    	</div>
	</div>
</div>

<script type="text/javascript">
</script>
<@p.userfooter/>