<@p.storeheader title="首页"/>
<!--根据商家id获取商家内容  -->
<#assign storeInfoTag =newTag("storeInfoTag")>  
<#assign storeVo =storeInfoTag("{'storeId':'${storeId}'}")>
<!--根据商家id取评分内容-->
<#assign evaluateStorebyStoreIdTag =newTag("evaluateStorebyStoreIdTag")>  
<#assign evaluateStore =evaluateStorebyStoreIdTag("{'storeId':'${storeId}'}")>

<#assign goodsListTag = newTag("goodsListTag")/>
<!--获得该商家下的商品数量-->
<#assign storegoodscount =goodsListTag("{'storeid':'${storeVo.storeId}','tagDataType':'5'}")>
<#if storeVo??>
   <#if storeVo.storeState==1>
	  <div style="width: 1000px;overflow: hidden;margin: 0 auto;">
	    </div>
	  <article id="content">
	    <section class="layout expanded mt10">
	      <article class="nc-goods-main"> 
	        <!-- 商品评分统计信息S -->
	        <div class="nc-s-c-s2">
	          <div class="title">
	            <h4>好评率 ( 0 % )</h4>
	          </div>
	          <div class="content">
	            <table class="ncs-evaluation-tb">
	              <thead>
	                <tr>
	                  <th></th>
	                  <td class="ncse-good"><span class="ico pngFix"></span>好评</td>
	                  <td class="ncse-normal"><span class="ico pngFix"></span>中评</td>
	                  <td class="ncse-bad"><span class="ico pngFix"></span>差评</td>
	                  <td class="ncse-sum">总计</td>
	                </tr>
	              </thead>
	              <tbody>
	                <tr>
	                  <th>最近1周</th>
	                  <td class="ncse-good">0</td>
	                  <td class="ncse-normal">0</td>
	                  <td class="ncse-bad">0</td>
	                  <td class="ncse-sum">0</td>
	                </tr>
	                <tr>
	                  <th>最近1个月</th>
	                  <td class="ncse-good">0</td>
	                  <td class="ncse-normal">0</td>
	                  <td class="ncse-bad">0</td>
	                  <td class="ncse-sum">0</td>
	                </tr>
	                <tr>
	                  <th>最近6个月</th>
	                  <td class="ncse-good">0</td>
	                  <td class="ncse-normal">0</td>
	                  <td class="ncse-bad">0</td>
	                  <td class="ncse-sum">0</td>
	                </tr>
	                <tr>
	                  <th>6个月前</th>
	                  <td class="ncse-good">0</td>
	                  <td class="ncse-normal">0</td>
	                  <td class="ncse-bad">0</td>
	                  <td class="ncse-sum">0</td>
	                </tr>
	              </tbody>
	              <tfoot>
	                <tr>
	                  <th>总计</th>
	                  <td>0</td>
	                  <td>0</td>
	                  <td>0</td>
	                  <td>0</td>
	                </tr>
	              </tfoot>
	            </table>
	          </div>
	        </div>
	        <!-- 商品评分统计信息E -->
	        <div class="nc-s-c-s2 mt10">
	          <div class="title">
	            <h4>商家半年内动态评分</h4>
	          </div>
	          <div class="content">
	            <div class="ncs-rate clearfix">
	              <ul class="ncs-rate-tab">
	                <li class="current"><a href="javascript:void(0);">宝贝与描述相符：&nbsp;&nbsp;<em>${evaluateStore.sevalDesccredit!'0'}</em>分</a></li>
	                <li><a href="javascript:void(0);">商家的服务态度：&nbsp;&nbsp;<em>${evaluateStore.sevalServicecredit!'0'}</em>分</a></li>
	                <li><a href="javascript:void(0);">商家的发货速度：&nbsp;&nbsp;<em>${evaluateStore.sevalDeliverycredit!'0'}</em>分</a></li>
	              </ul>
	              <dl class="ncs-rate-icos">
	                <dd class="rate-star"><em><i style="width: 100%"></i></em><span>5分</span></dd>
	                <dd class="rate-star"><em><i style="width: 80%"></i></em><span>4分</span></dd>
	                <dd class="rate-star"><em><i style="width: 60%"></i></em><span>3分</span></dd>
	                <dd class="rate-star"><em><i style="width: 40%"></i></em><span>2分</span></dd>
	                <dd class="rate-star"><em><i style="width: 20%"></i></em><span>1分</span></dd>
	              </dl>
	                            <!-- 	<ul class="ncs-rate-panel ">
	              	              		              			<li>
	              			暂无人打分              			</li>
	              		              	              		              			<li>
	              			暂无人打分              			</li>
	              		              	              		              			<li>
	              			暂无人打分              			</li>
	              		              	              		              			<li>
	              			暂无人打分              			</li>
	              		              	              		              			<li>
	              			暂无人打分              			</li>
	              		              	              		              	              	</ul>
	                            	<ul class="ncs-rate-panel hide">
	              	              		              			<li>
	              			暂无人打分              			</li>
	              		              	              		              			<li>
	              			暂无人打分              			</li>
	              		              	              		              			<li>
	              			暂无人打分              			</li>
	              		              	              		              			<li>
	              			暂无人打分              			</li>
	              		              	              		              			<li>
	              			暂无人打分              			</li>
	              		              	              		              	              	</ul> -->
	                            	<ul class="ncs-rate-panel hide">
	              	              		              			<li>
	              			暂无人打分              			</li>
	              		              	              		              			<li>
	              			暂无人打分              			</li>
	              		              	              		              			<li>
	              			暂无人打分              			</li>
	              		              	              		              			<li>
	              			暂无人打分              			</li>
	              		              	              		              			<li>
	              			暂无人打分              			</li>
	              		              	              		              	              	</ul>
	                          </div>
	          </div>
	        </div>
	      </article>
	      <aside class="nc-sidebar">
	        <!--商家基本信息 S-->
	
	<div class="ncs-info clearfix">
	  <div class="shop-card">
	    <h4>${storeVo.storeName}</h4>
	    <dl>
	      <dt><span class="thumb size60"><i></i><a href="#"><img src="<#if storeVo.storeLogo!=null>${imgServer}${storeVo.storeLogo}<#else>${base}/res/images/member/default_image.png</#if>"  onload="javascript:DrawImage(this,60,60);" title="${storeVo.storeName}" alt="${storeVo.storeName}" /></a></span></dt>
	      <dd><a href="#" class="shopkeeper">${storeVo.memberName}</a> <a href="#?act=home&op=sendmsg&member_id=5" class="message text-hidden" title="发站内信">发站内信</a></dd>
	      <dd class="ncs-level">
	        商家信用：${evaluateStore.averageCredit!'0'}</dd>
	      <!-- <dd ><span>好评率：</span>0%</dd> -->
	    </dl>
	  </div>
	  <div class="shop-rate">
	    <h4>动态评价</h4>
	    <!-- 动态评价 -->
	    <dl class="rate">
	      <dt>描述相符：</dt>
		  <dd class="raty" data-score="${evaluateStore.sevalDesccredit}"></dd>
	      <dt>服务态度：</dt>
		  <dd class="raty" data-score="${evaluateStore.sevalServicecredit}"></dd>
	      <dt>发货速度：</dt>
		  <dd class="raty" data-score="${evaluateStore.sevalDeliverycredit}"></dd>
	    </dl>
	  </div>
	  <div class="shop-detail">
	    <h4>商家信息</h4>
	    <!-- 商家信息 -->
	    <dl>
	      <dt style="width: 75px;">创店时间：</dt>
	      <dd>
	      　　　　　 <#if storeVo.storeCreatetime??>
	             ${storeVo.storeCreatetime?string('yyyy-MM-dd')}
	         <#else>
	             　　
	         </#if>
	      </dd>
	      <dt style="width: 75px;">所&nbsp;&nbsp;在&nbsp;&nbsp;地：</dt>
	      <dd>${storeVo.storeAddress}</dd>
	      <dt style="width: 75px;">商品数量：</dt>
	      <dd><strong>${storegoodscount}</strong>件商品</dd>
	      <dt style="width: 75px;">商家收藏：</dt>
	      <dd><strong nctype="store_collect">${storeVo.storeCollect}</strong>人收藏</dd>
	            <!--详细地址       <dt>详细地址：</dt>
	      <dd>红旗路220号 慧谷大厦 0712</dd>
	      --> 
	      <!--联系电话       <dt>联系电话：</dt>
	      <dd>2345678</dd>
	      -->
	    </dl>
	  </div>
	 <!--  <div class="shop-im">
	    <h4>联系方式</h4>
	    <p>
	            <a href="http://wpa.qq.com/msgrd?v=3&uin=905669187&Site=b2b2c.leimingtech.com&Menu=yes" target="_blank"><img src="http://wpa.qq.com/pa?p=2:1234567:51" alt="点击这里给我发消息"></a>
	                </p>
	  </div> -->
	  <div class="shop-other" id="shop-other">
	    <ul>
	      <li class="ncs-info-btn-map"><a href="javascript:void(0)" class="pngFix"><span>商家地图</span><b></b> <!-- 商家地图 -->
	        <div class="ncs-info-map" id="map_container" style="width:198px;height:320px;">地图加载中... </div>
	        </a></li>
	      <li class="ncs-info-btn-qrcode"><a href="javascript:void(0)" class="pngFix"><span>商家二维码</span><b></b> <!-- 商家二维码 -->
	        <p class="ncs-info-qrcode"><img src="${imgServer}${storeVo.storeCode}" onerror="this.src='${base}/res/images/20131211091752-393669037.jpg'" title="#" onload="javascript:DrawImage(this,150,150);"><em>手机扫描二维码<br/>快速收藏商家</em> </p>
	        </a> </li>
	    </ul>
	  </div>
	</div>
	<div class="clear"></div>
	<!--商家基本信息 E--> 
	<script src="${base}/res/js/jquery.raty/jquery.raty.min.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
	$(document).ready(function(){$(".raty").raty({path:"${base}/res/js/jquery.raty/img",hints:['一般', '不错', "很好", '满意', '非常满意'],readOnly:true,width:100,score:function(){return $(this).attr("data-score")}})});
	var cityName ="北京市";
	var address = '${storeVo.storeAddress}';
	var store_name ='${storeVo.storeName}';
	var map = "";
	var localCity = "";
	function initialize() {
		map = new BMap.Map("map_container");
		localCity = new BMap.LocalCity();
		
		map.enableScrollWheelZoom(); 
		localCity.get(function(cityResult){
		  if (cityResult) {
		  	var level = cityResult.level;
		  	if (level < 13) level = 13;
		    map.centerAndZoom(cityResult.center, level);
		    cityResultName = cityResult.name;
		    if (cityResultName.indexOf(cityName) >= 0) cityName = cityResult.name;
		    	    	getPoint();
		    	  }
		});
	}
	 
	function loadScript() {
		var script = document.createElement("script");
		script.src = "http://api.map.baidu.com/api?v=1.2&callback=initialize";
		document.body.appendChild(script);
	}
	
	function getPoint(){
		var myGeo = new BMap.Geocoder();
		myGeo.getPoint(address, function(point){
		var point="";
		if('${storeVo.storeLongitude}'!=''&&'${storeVo.storeAtitude}'!=null){//判断经纬度是否为空
		    point = new BMap.Point('${storeVo.storeLongitude}','${storeVo.storeAtitude}');
		  }
		  if (point) {
		    setPoint(point);
		  }
		}, cityName);
	}
	function setPoint(point){
		  if (point) {
		    map.centerAndZoom(point, 16);
		    var marker = new BMap.Marker(point);
		    map.addOverlay(marker);
		  }
	}
	
	// 当鼠标放在商家地图上再加载百度地图。
	$(function(){
		$('.ncs-info-btn-map').one('mouseover',function(){
			loadScript();
		});
	});
	</script> 
	      </aside>
	    </section>
	    <div class="clear"></div>
	    <!-- 商品评价列表S -->
	    <div class="nc-s-c-s3 ncs-comment mt10">
	      <!-- <div class="title">
	        <h4>评价</h4>
	      </div> -->
	      <#--
	      <div class="content">
	        <div id="goodseval" class="ncs-loading"></div>
	        <script type="text/javascript">
				$("#goodseval").load('index.php?act=show_store&op=comments&id=2');
		    </script> 
	      </div>
	    </div>
	    -->
	  </article>
	</div>
	<div id="footer" >
	<div class="wrapper">
	  <p><a href="http://192.168.1.230">首页</a>
	                | <a  href="#?act=article&article_id=24">招聘英才</a>
	                | <a  href="#?act=article&article_id=25">广告合作</a>
	                | <a  href="#?act=article&article_id=23">联系我们</a>
	                | <a  href="#?act=article&amp;article_id=22">关于我们</a>
	                            </p>Copyright 2016-2017 磁石世纪（北京）投资管理有限公司 版权所有  京ICP备16011767号.&nbsp;&nbsp;ICP证：<br/>
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
   </#if>
   <#if storeVo.storeState==0>
      <div align="center">
        <b style="font-size: 3em;">该商家已关闭</b>
      </div>
   </#if>
   <#if storeVo.storeState==2>
       <div align="center">
     	 <b style="font-size: 3em;">该商家正在审核中</b>
       </div>
   </#if>
</#if>
<script type="text/javascript" src="${base}/res/js/jquery.cookie.js" ></script>
<script type="text/javascript" src="${base}/res/js/perfect-scrollbar.min.js" ></script>
<script type="text/javascript" src="${base}/res/js/jquery.mousewheel.js" ></script>
<script type="text/javascript" src="${base}/res/js/jquery.masonry.js" ></script>
<script type="text/javascript" src="${base}/res/js/jquery.scrollLoading-min.js"></script>

<script src="${base}/res/js/jquery.ajaxContent.pack.js" type="text/javascript"></script> 
