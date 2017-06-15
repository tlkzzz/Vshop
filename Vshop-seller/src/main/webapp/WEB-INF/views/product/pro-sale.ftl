<@p.header title="商家中心-出售中的商品"/>
<script type="text/javascript" src="${base}/res/js/common_select.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/ajaxfileupload/ajaxfileupload.js"></script>

<div class="layout">
   <div class="sidebar">
  </div>
  <div class="right-content">
        <div class="path">
		      <div><a href="${base}">商家中心</a> <span>></span>
		                       出售中的商品             
              </div>
        </div>
        <div class="main">
      
<!-- ajax编辑商品名称样式 -->
<style type="text/css">
.ncu-table-style tbody tr:hover td { background-color: #FFF;}
.ajax-edit:hover { background-color: #FFF9D4; color:#F30 !important;}
.goods-name textarea { font-family: Tahoma; height: 16px; line-height: 16px; height: 32px !important; background-color:#F9F9F9; padding: 1px 2px 3px 4px !important; padding: 3px 2px 1px 4px; border: solid 1px; border-color: #CCC #DDD #DDD #CCC; box-shadow: 2px 2px 1px 0 #E7E7E7 inset; -moz-box-shadow: 2px 2px 1px 0 #E7E7E7 inset/* if FF*/; -webkit--box-shadow: 2px 2px 1px 0 #E7E7E7 inset/* if Webkie*/;}
.goods-name textarea:hover { background-color:#FFF;}
.goods-name textarea:focus { background-color:#FFF; border-color: #CCC; box-shadow: 1px 1px 1px 0 #E7E7E7; -moz-box-shadow: 1px 1px 1px 0 #E7E7E7/* if FF*/; -webkit--box-shadow: 1px 1px 1px 0 #E7E7E7/* if Webkie*/;}
.hiddenDIV { height: 0px; display: none; }
.showDIV {
		width: 240px;
		height: 160px;
		position: relative;
		display: inline;
		border-style:solid;
		border-width:thin;
		border-color:#FFD39B;
		z-index:999;
		background-color:#fff;
	}
	
	.showDIV img {
		margin-top: 10px;
		margin-left: 10px;
	}
	
	.showDIV button{
		width:60px;
		height: 30px;
		color: #fff;
		background: #f90;
		border: none;
		position: absolute;
		right: 16px;
		top: 40px;
		border-radius: 8px;
	}
</style>
<script type="text/javascript">
;;
</script>
<script type="text/javascript" src="${base}/res/js/zeroclipboard/ZeroClipboard.js" charset="utf-8"></script>

<script type="text/javascript">
	function myZeroClipboard(goodsId, storeId) {
		ZeroClipboard.setMoviePath("${base}/res/js/zeroclipboard/ZeroClipboard.swf");
	  	var clip = new ZeroClipboard.Client();
	  	clip.setHandCursor(true);  	
	  	clip.addEventListener('mouseOver', function (client){
	  		var uurl = "${frontServer}/weixin/detail?goodsId="+goodsId+"&storeId="+storeId;
	  		clip.setText(uurl);
	  	});
	  	clip.addEventListener('complete', function (client, text) {   
	    	layer.msg('复制成功', {icon: 1});
	  	});
	  	clip.glue('clip_button'+goodsId+'', 'clip_container'+goodsId+'');
	}
</script>

<div class="wrap">
  <div class="tabmenu">
    <ul class="tab pngFix">
	  <li class="active">
	  	<a  href="javascript:void(0);">出售中的商品</a>
	  </li>
    </ul>
<!--     <a href="${base}/pro/sell" class="ncu-btn3" title="发布新商品">发布新商品</a>  -->
  </div>
  <form method="post" action="${base}/pro/sale" name="queryListForm">
    <table class="search-form">
      <input type="hidden" name="storeClassId" value="${storeClassId}"/>
      <input type="hidden" name="goodsName" value="${goodsName}"/>
      <tr>
        <td>&nbsp;</td>
        <th>本店分类：</th>
        <td class="w160">
           <select name="newStoreClassId" class="w150">
            <option value="">请选择</option>
            <#if StoreGoodsClassVoMap??>
	            <#list StoreGoodsClassVoMap?keys as sgckey>
	            	<#list StoreGoodsClassVoMap[sgckey] as sgclist>
	            		<#if sgclist_index==0>
	            			<option value="${sgclist.parentId}">${sgclist.parentName}</option>
	            		<#else>
	            			<option value="${sgclist.childId}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${sgclist.childName}</option>
	            		</#if>
	            	</#list>
	            </#list>
            </#if>
           </select>
        </td>
        <th>商品名称：</th>
        <td class="w160"><input type="text" class="text" name="newGoodsName" value="${goodsName}"/></td>
        <td class="tc w90"><input type="submit" class="submit" value="搜索" name="search"/></td>
      </tr>
    </table>
  </form>
  <table id="my_goods" class="ncu-table-style">
      <thead>
	     <tr nc_type="table_header">
		   <!--<th class="w30"></th>
       		 <th class="w70"></th>-->
			 <th coltype="editable" column="goods_name" checker="check_required" inputwidth="200px" colspan="3" style="text-align:left;padding-left:11%;">商品名称&nbsp;/&nbsp;类型</th>
			 <th class="w90" coltype="editable" column="store_name" checker="check_number" inputwidth="30px">供应商名称</th>
			 <th class="w70" coltype="editable" column="goods_store_price" checker="check_number" inputwidth="30px">商品价格</th>
			 <th class="w50" coltype="editable" column="goods_commission_rate" checker="check_number" inputwidth="30px">利润比例</th>
			 <th class="w50" coltype="editable" column="spec_goods_storage" checker="check_pint" inputwidth="30px">库存</th>
			 <th class="w100">上架时间</th>
			 <th class="w100" coltype="switchable" column="goods_commend" onclass="recommend-ico-btn" offclass="norecommend-ico-btn">推荐</th>
			 <th class="w90">操作</th>
		 </tr>
		 <tr>
			 <td class="tc"><input type="checkbox" id="all" class="checkall"/></td>
			 <td colspan="20">
				 <label for="all" >全选</label>
				 <!-- 
				 <a href="javascript:void(0);" class="ncu-btn1" onClick="deleteBatchGoods();">
				 	<span>删除</span>
				 </a>
				  -->
				 <a href="javascript:void(0);" class="ncu-btn1" onClick="downBatchStoreGoods();">
				 	<span>下架</span>
				 </a>
			 </td>
		 </tr>
      </thead>
      <div id="twodimencode_div" class="hiddenDIV"></div>
        <tbody>
   <#if goodsList??>
    <#list goodsList as gl>
      <tr>
        <th class="tc"><input type="checkbox" name="goods" class="checkitem tc" value="${gl.goodsId}"/></th>
        <th colspan="20">商品货号：${gl.goodsSerial}</th>
      </tr>
      <tr nc_type="table_item" idvalue="89">
        <td>&nbsp;</td>
        <td class="w70">
	        <div class="goods-pic-small">
		        <span class="thumb size60"><i></i>
<!-- 			        <a href="${frontServer}/product/detail?id=${gl.goodsId}" target="_blank"> -->
						<a href="${base}/pro/lookgoods?goodsId=${gl.goodsId}" target="_blank">
			        	<img src="${imgOssServer}${gl.goodsImage}" onload="javascript:DrawImage(this,60,60);" />
			        </a>
		        </span>
	        </div>
        </td>
        <td>
	        <dl class="goods-name">
		        <dt class="tip" nc_type="editobj" inputtype="textarea" title="">
		        <a href="${base}/pro/lookgoods?goodsId=${gl.goodsId}" target="_blank">
					${gl.goodsName}
				</a>
				</dt>
	        	<dd>${gl.gcName}</dd>
	        </dl>
        </td>
         <td>
        	<span class="tip" >
        		${gl.supplierName}
        	</span>
        </td> 
        <td>
	        <span class="tip" nctype="stock_selector" nc_gid="89" title="">
	        	${gl.goodsStorePrice}
	        </span>
        </td>
        <td>
	        <span class="tip" nctype="stock_selector" nc_id="89" title="">
	        	${gl.goodsCommissionRate}
	        </span>
        </td>
        <td>
	        <span class="tip" nctype="stock_selector" nc_id="89" title="">
	        	${gl.goodsTotalStorage}
	        </span>
        </td>
        <td class="goods-time">
	        <span class="tip" nctype="stock_selector" nc_id="89" title="">
	        	${gl.upForTimeStr}
	        </span>
        </td>
        
        <#if gl.goodsCommend == 0>
        	<td><span nc_type="editobj" class="tip norecommend-ico-btn" status="off" title="选择是否推荐到商家首页" goodsCommend="${gl.goodsCommend}" onclick=" changeToCommend(${gl.goodsId},${gl.goodsCommend},this);">
        	</span></td>
        <#else>
        	<td><span nc_type="editobj" class="tip recommend-ico-btn" status="on" title="选择是否推荐到商家首页" goodsCommend="${gl.goodsCommend}"  onclick=" changeToCommend(${gl.goodsId},${gl.goodsCommend},this);">
        	</span></td>
        </#if>
        <td class="tc">
        	<p>
        	<a href="javascript:void(0);" onclick='showewmLayer3("${gl.goodsId}","${gl.storeId}");' 
        		style="cursor:hand;" tip="${gl.goodsId}" >本商品二维码</a>
        	</p>
        	
        	
        	
        <!-- 
        	<a href="javascript:void(0);" onClick="combinationDialog('${gl.goodsId}','${gl.storeId}')">
	        	修改组合商品
	        </a>
	        <br>
	        <a href="${base}/pro/editgoods?goodsId=${gl.goodsId}" target="_blank">
	        	编辑商品
	        </a>
        	<a  href="javascript:void(0);" onclick="deleteGoods('${gl.goodsId}');" class="ncu-btn2 mt5">
	        	删&nbsp;除
	        </a>
	        
	         -->
	         <p>
	         <a href="${base}/pro/lookgoods?goodsId=${gl.goodsId}" target="_blank">
			        	查看商品
	        </a>
	        </p>
	        
	        <p>
				<nobr><span id="clip_container${gl.goodsId}"><a id="clip_button${gl.goodsId}">复制地址</a></span>
				<script type="text/javascript">try{ myZeroClipboard('${gl.goodsId}', '${gl.storeId}'); } catch(e) {} </script>
				</nobr>
			</p>
        </td>
      </tr>
     </#list>
    </#if>
  </tbody>
    <tfoot>
        <tr>
            <td colspan="20">
                <#import "/product/page.ftl" as q><!--引入分页-->
                <#if recordCount??>
                    <@q.pager pageNo=pageNo pageSize=pageSize recordCount=recordCount toURL="${toUrl}"/>
                </#if>
            </td>
        </tr>
   </tfoot>
<!--     <tfoot>
            <tr>
		        <td class="tc"><input type="checkbox" id="all2" class="checkall"/></td>
		        <td colspan="10"><label for="all">全选</label>
		        <a href="javascript:void(0);" nc_type="batchbutton" uri="#?act=store_goods&op=drop_goods" name="goods_id" confirm="您确定要删除吗?" class="ncu-btn1">
		        	<span>删除</span>
		        </a> 
		        <a href="javascript:void(0);" nc_type="batchbutton" uri="#?act=store_goods&op=goods_unshow" name="goods_id" class="ncu-btn1">
		        	<span>下架</span>
		        </a>
		        <div class="pagination"> <ul><li><span>首页</span></li><li><span>上一页</span></li><li><span class="currentpage">1</span></li><li><a class='demo' href='/index.php?act=store_goods&op=goods_list&curpage=2'><span>2</span></a></li><li><a class='demo' href='/index.php?act=store_goods&op=goods_list&curpage=3'><span>3</span></a></li><li><a class='demo' href='/index.php?act=store_goods&op=goods_list&curpage=4'><span>4</span></a></li><li><a class='demo' href='/index.php?act=store_goods&op=goods_list&curpage=5'><span>5</span></a></li><li><a class='demo' href='/index.php?act=store_goods&op=goods_list&curpage=6'><span>6</span></a></li><li><span>...</span></li><li><a class="demo" href="/index.php?act=store_goods&op=goods_list&curpage=2"><span>下一页</span></a></li><li><a class="demo" href="/index.php?act=store_goods&op=goods_list&curpage=8"><span>末页</span></a></li></ul> </div></td>
           </tr>
    </tfoot> -->
  </table>
</div>
<!-- <script type="text/javascript" src="${base}/res/js/dialog.js" id="dialog_js" charset="utf-8"></script>  -->
<!-- <script type="text/javascript" src="http://192.168.1.230/resource/js/jquery.poshytip.min.js" charset="utf-8"></script> -->

<script type="text/javascript">

$(function(){
	//初始化
	init();
	//搜索
	$("[name=search]").click(function(){
		var storeClassId = $("[name=newStoreClassId] option:selected").val();
		$("[name=storeClassId]").attr("value",storeClassId);
		$("[name=goodsName]").attr("value",$("[name=newGoodsName]").val());
	});
});

/*
 * 初始化
 */
function init(){
	//本店分类搜索初始化
	if($("[name=storeClassId]").val() != ""){
		var classid = $("[name=storeClassId]").val();
		$("[name=newStoreClassId] option[value="+classid+"]").attr("selected","selected");
	}

}

/**
 * 删除
 */
 function deleteGoods(goodsIds){
	if(confirm("您确认要删除商品吗?")){
		var url = '${base}/pro/deleteGoods'
			var args = {"goodsIds" : goodsIds};
			$.post(url, args, function(data){
				//成功
				if(data.success){
					alert("删除成功!");
					location.reload([true]);
				}else{//失败
					alert("删除失败!");
				}
			});
	}
 }
 
 /*
  * 批量删除
  */
  function deleteBatchGoods(){
	 var goodsIds = ""
	 $("[name=goods]:checked").each(function(){
		 goodsIds += $(this).val() + ",";
	 });
	 if(goodsIds != ''){
		 goodsIds = goodsIds.substring(0,goodsIds.length-1);
		 deleteGoods(goodsIds);
	 }else{
		 alert("请至少选择一个商品进行删除");
	 }
 }
  
 /*
  * 下架
  */
  function downGoods(goodsIds){
	if(confirm("您确认要下架商品吗?")){
		var url = '${base}/pro/downGoods'
			var args = {"goodsIds" : goodsIds};
			$.post(url, args, function(data){
				//成功
				if(data.success){
					alert("下架成功!");
					location.reload([true]);
				}else{//失败
					alert("下架失败!");
				}
			});
	}
 }
 
  function downStoreGoods1(goodsIds){
		if(confirm("您确认要下架商品吗?")){
			var url = '${base}/pro/downStoreGoods'
				var args = {"goodsIds" : goodsIds};
				$.post(url, args, function(data){
					//成功
					if(data.success){
						alert("下架成功!");
						location.reload([true]);
					}else{//失败
						alert("下架失败!");
					}
				});
		}
	 }

  /*
   * 批量下架
   */
   function downBatchGoods(){
	 var goodsIds = ""
	 $("[name=goods]:checked").each(function(){
		 goodsIds += $(this).val() + ",";
	 });
	 if(goodsIds != ''){
		 goodsIds = goodsIds.substring(0,goodsIds.length-1);
		 downGoods(goodsIds);
	 }else{
		 alert("请至少选择一个商品进行下架");
	 }
  }
  
   function downBatchStoreGoods(){
		 var goodsIds = ""
		 $("[name=goods]:checked").each(function(){
			 goodsIds += $(this).val() + ",";
		 });
		 if(goodsIds != ''){
			 goodsIds = goodsIds.substring(0,goodsIds.length-1);
			 downStoreGoods(goodsIds);
		 }else{
			 layer.msg("请至少选择一个商品进行下架",{
				 time : 1500
			 });
		 }
	  }
   
   function downStoreGoods(goodsIds){
	   layer.confirm("您确认要下架商品吗?",{
		   btn : ['确认','取消']
	   },function(){
		   var url = '${base}/pro/downStoreGoods'
				var args = {"goodsIds" : goodsIds};
				var zhezhao = layer.load(1, {shade: [0.8, '#393D49']});
			    zhezhao;
				$.post(url, args, function(data){
					layer.close(zhezhao);
					//成功
					if(data.success){
						layer.msg("下架成功!",{
							time : 1500
						},function(){
							location.reload([true]);
						});
						
					}else{//失败
						layer.msg("下架失败!",{
							time : 1500
						});
					}
				});
	   });
   }
  
   /*
    * 组合商品修改
    */
  function combinationDialog(goodsId,storeId) {
	  layer.open({
          type: 2,
          move: false,
          shade: false,
          title: '修改组合商品',
          content:['${base}/pro/updateCombinationIndex?goodsId=' + goodsId + "&storeId=" + storeId, 'no'],
          area: ['600px', '300px'],
          btns: 2,
          btn: ['确定', '取消'],
          yes: function (index) {
        	  
        	  var allCombinationGoodsIdStr = "";
        	  layer.getChildFrame('[name=goodsCombination]:checked').each(function(){
        		  allCombinationGoodsIdStr += $(this).val() + ",";
        	  });
        	  
        	  var url = "${base}/pro/updateCombination";
        	  var args = {"goodsId":layer.getChildFrame('#goodsId').val(), "allCombinationGoodsIdStr":allCombinationGoodsIdStr};
        	  
        	  $.post(url, args, function(data){
        		 if(data == "true"){
        			 layer.msg('修改成功', {icon: 1});
        			 layer.close(index);
        		 }else{
        			 layer.msg('修改失败', {icon: 2});
        		 }
        	  });
        	  
          }, no: function () {
              layer.close(index);
          }
      });
  };
  
  function showewmLayer3(goodsId, storeId) {
			  layer.open({
		          type: 2,
		          move: false,
		          shade: false,
		          title: '',
		          content:['${base}/showewm?storeId='+storeId+'&ewmType=2&goodsId='+goodsId, 'no'],
		          area: ['370px', '250px'],
		          btns: 2,
		          btn: ['关闭'],
		          btn1: function (index) {
		        	  layer.close(index);
		          }
		      });
		  }
		  
  		var aa;
		function showTwodimencode(event, goodsId,storeId) {
			if(goodsId == "") {
				return ;
			}
			
			try {
				// var urlurl = "${frontServer}/product/detail?goodsId="+goodsId+"&storeId="+storeId;
				
				var urlurl = "${frontServer}/weixin/detail?goodsId="+goodsId+"&storeId="+storeId;
				// var str = '<img src="${base}/twodimencodeImage?content='+encodeURIComponent(urlurl)+'" />';
				
				var str = '<table style="width: 99%; vertical-align: middle; text-align: center;"><tr><td style="width: 150px;">'
					+ '<img id="twodimencodeImage" src="${base}/twodimencodeImage?content='+encodeURIComponent(urlurl)+'&imgtype=2" />'
					+ '</td><td>'
					+ '<a href="${base}/downloadTwodimencodeImage?content='+encodeURIComponent(urlurl)+'&imgtype=2" target="_blank"><button>保存</button></a>'
					+ '<button style="position: absolute;right: 16px;top: 90px;" onclick="hidediv();">关闭</button>'
					+ '</td></tr>'
					+ '</table>';
					
				var obj = document.getElementById("twodimencode_div");
				if(obj != null) {
					obj.innerHTML = str;
					obj.className = "showDIV";
					var wi = document.body.scrollWidth;
					var sheight = document.documentElement.clientHeight; // document.body.scrollHeight;
					if((event.clientX + 260) < wi) {
						obj.style.left = (event.clientX + 20) + "px"; //鼠标目前在X轴上的位置，加10是为了向右边移动10个px方便看到内容    
					} else {
						obj.style.left = (event.clientX - 260) + "px"; //鼠标目前在X轴上的位置，加10是为了向右边移动10个px方便看到内容    
					}
					
					if((event.clientY + 160) < sheight) {
						obj.style.top = (event.clientY + 20 + $(document).scrollTop()) + "px";
					} else {
						obj.style.top = (event.clientY - 150 + $(document).scrollTop()) + "px";
					}
					 
					obj.style.position = "absolute"; //必须指定这个属性，否则div3层无法跟着鼠标动    
					// clearTimeout(aa);
				}
				// alert(document.body.clientHeight);
				/**
				alert(document.body.scrollHeight);
				alert("window.event.x:"+window.event.x
					+"\nwindow.event.y:"+window.event.y
					+"\nevent.clientX:"+event.clientX
					+"\nevent.clientY:"+event.clientY
					+"\nwindow.event.screenX:"+window.event.screenX
					+"\nwindow.event.screenY:"+window.event.screenY
					+"\n$(document).scrollTop():"+$(document).scrollTop());
				**/
			} catch(e) {}
		}
		
		function clearTwodimencode() {
			aa = setTimeout(hidediv, 500);
		}
		
		function hidediv() {
			var obj = document.getElementById("twodimencode_div");
			if(obj != null) {
				obj.innerHTML = "";
				obj.className = "hiddenDIV";
			}
		}
		
 
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

/* $(function(){
	//Ajax提示
	$('.tip').poshytip({
		className: 'tip-yellowsimple',
		showTimeout: 1,
		alignTo: 'target',
		alignX: 'center',
		alignY: 'top',
		offsetY: 5,
		allowTipHover: false
	});
    var t = new EditableTable($('#my_goods'));
	// 存在多规格时的库存修改
	$('span[nctype="stock_selector"]').click(function(){
		id	= $(this).attr('nc_id');
		sum	= $(this).html();
		ajax_form('stock_selector_'+id, '修改库存', SITE_URL + '/index.php?act=store_goods&op=goods_stock_list&goods_id='+id+ '&stock_sum=' +sum, '480');
	});

	// 存在多规格时的价格修改
	$('span[nctype="prict_selector"]').click(function(){
		id	= $(this).attr('nc_gid');
		ajax_form('price_selector_'+id, '修改价格', SITE_URL + '/index.php?act=store_goods&op=goods_price_list&goods_id='+id, '480');
	});
});
 */
function change_stock_count(id,count) {
	$('span[nc_id="'+id+'"]').html(count);
}
function change_price(id,price) {
	$('span[nc_gid="'+id+'"]').html(number_format(price, 2));
}
/**
 * 切换推荐状态
 */
function changeToCommend(goodsId,goodsCommend,thisSpan){
	var status = $(thisSpan).attr('status');
	goodsCommend = $(thisSpan).attr('goodsCommend');
	 $.ajax({
		type : 'post',
		url :  '${base}/pro/changeToCommend',
	    data : {'goodsId':goodsId,'goodsCommend':goodsCommend},
		dataType : 'json',
		success:function(data){
			if(data.resultMap == 'success'){
				if(status=='off'){
					$(thisSpan).removeClass('norecommend-ico-btn');
					$(thisSpan).addClass('recommend-ico-btn');
					$(thisSpan).attr('goodsCommend','1');
					$(thisSpan).attr('status','on');
				}else if(status=='on'){
					$(thisSpan).removeClass('recommend-ico-btn');
					$(thisSpan).addClass('norecommend-ico-btn');
					$(thisSpan).attr('goodsCommend','0');
					$(thisSpan).attr('status','off');
				}
			}else{
				alert("服务器繁忙，请稍后重试！");
			}
		}
	}); 
}



		
</script> 
 </div>
  </div>
    <div class="clear"></div>
</div>


</body>
</html>
<@p.footer/>