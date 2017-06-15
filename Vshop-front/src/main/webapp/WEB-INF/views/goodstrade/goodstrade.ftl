<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
</head>
<body>
<script type="text/javascript" src="${base}/res/js/common2.2.js" charset="utf-8"></script>
<!-- <link href="${base}/res/css/dialog2.2.css" rel="stylesheet" type="text/css"> -->
<div id="append_parent"></div>
<div id="ajaxwaitid"></div>

<!-- PublicHeadLayout Begin -->
<header id="header" class="pngFix">
</header>
<script type="text/javascript" src="${base}/res/js/dialog/dialog.js" id="dialog_js" charset="utf-8"></script> 
<script type="text/javascript" src="${base}/res/js/nc-sideMenu.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/utils.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/sns/sns.js" charset="utf-8"></script> 
<script type="text/javascript" src="${base}/res/js/jcarousel/jquery.jcarousel.min.js"></script> 
<script type="text/javascript" src="${base}/res/js/layer/layer.js"></script>
<link href="${base}/res/css/font-icons/style.css"  rel="stylesheet" />
<link href="${base}/res/js/jcarousel/skins/tango/skin.css" rel="stylesheet" type="text/css">
<style type="text/css">
.jcarousel-skin-tango { background-color: #F4F9FD; border: solid 1px #AED2FF;}
.jcarousel-skin-tango a { background-color: #FFF; width: 120px; height: 120px; display: inline-block; border: solid 1px #C4D5E0; }
.jcarousel-skin-tango .jcarousel-clip-horizontal { width: 660px !important; height: 130px !important;}
.jcarousel-skin-tango .jcarousel-item { height: 130px !important;}
.jcarousel-skin-tango .jcarousel-container-horizontal { width: 660px !important;}
</style>
<div id="container" class="wrapper">
	<div class="layout">
    	<div class="right-content">
      		<div class="main">
				<div class="wrap">
  					<form id="acct-form" method="post" action="" name ="queryListForm">
	  					<input type="hidden" name="div" id="div" value = "#dataListDiv"/>
	  					<div  id="dataListDiv">
	  					    <script type="text/javascript" src="${base}/res/js/member/member.js"></script>
							<table class="ncu-table-style">
								<#if goodsTradeList?exists && goodsTradeList?size gt 0>
								<#list goodsTradeList as  goodstrade>
								<thead>
									<tr>
										<th>序号</th>
						                <th>商品名称</th>
						                <th>分类名称</th>
						                <th>交易量</th>
						                <th>交易额(元)</th>
						                <th>销售地</th>
						                <th>商家名称</th>
									</tr>
								</thead>
								<tbody>
								
								<tr class="bd-line">
									<td class="tc">
										 ${goodsTrade_index+1}
									</td>
									<td>
										 <a href="javascript:void(0)" title="${goodstrade.goodsName}">
											<#if goodstrade.goodsName?length lt 15>   
											     ${goodstrade.goodsName}
											<#else> 
											     ${goodstrade.goodsName[0..21]}... 
											</#if>
									    </a>
									</td>
									<td class="tl">
										${goodstrade.gcName}
									</td>
									<td>
									   ${goodstrade.tradeGoodsCount}
									</td>
									<td>
									   ${goodstrade.tradeGoodsPrice}
									</td>
									<td>
									   ${goodstrade.provinceName}${goodstrade.cityName}
									</td>
									<td>
										 ${goodstrade.storeName}
									</td>
								</tr>
								<tr id="store-goods-2" class="shop-new-goods" style="display:none;">
									<td colspan="20" style=" padding-top: 0;" >
										<div class="fr pr">
											<div class="arrow"></div>
											<ul class="jcarousel-skin-tango"></ul>
										</div>
									</td>
								</tr>
								</tbody>
								</#list>
								<#else>
								<tbody>
							 	<tr>
							 		<td colspan="9">
							 			暂无数据
							 		</td>
							 	</tr>
							 	</tbody>
								</#if>
							</table>
	  					</div>
  					</form>
				</div>
      		</div>
    	</div>
	</div>
</div>
<@p.userfooter/>