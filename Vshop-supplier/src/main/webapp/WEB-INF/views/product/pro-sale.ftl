<@p.header title="供应商中心-出售中的商品"/>
<script type="text/javascript" src="${base}/res/js/common_select.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/ajaxfileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="${base}/res/js/layer/layer.js"></script>
<div class="layout">
   <div class="sidebar">
  </div>
  <div class="right-content">
        <div class="path">
		      <div><a href="${base}">供应商中心</a> <span>></span>
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
</style>

<div class="wrap">
  <div class="tabmenu">
    <ul class="tab pngFix">
	  <li class="active">
	  	<a  href="javascript:void(0);">出售中的商品</a>
	  </li>
    </ul>
<!--     <a href="${base}/pro/sell" class="ncu-btn3" title="发布新商品">发布新商品</a>  -->
  </div>
  <form method="post" action="${base}/pro/sale"  name="queryListForm">
    <table class="search-form">
      <input type="hidden" name="storeClassId" value="${storeClassId}"/>
      <input type="hidden" name="goodsName" value="${goodsName}"/>
      <tr>
        <td>&nbsp;</td>
<!--         <th>本店分类：</th> -->
<!--         <td class="w160"> -->
<!--            <select name="newStoreClassId" class="w150"> -->
<!--             <option value="">请选择</option> -->
<!--             <#if StoreGoodsClassVoMap??> -->
<!-- 	            <#list StoreGoodsClassVoMap?keys as sgckey> -->
<!-- 	            	<#list StoreGoodsClassVoMap[sgckey] as sgclist> -->
<!-- 	            		<#if sgclist_index==0> -->
<!-- 	            			<option value="${sgclist.parentId}">${sgclist.parentName}</option> -->
<!-- 	            		<#else> -->
<!-- 	            			<option value="${sgclist.childId}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${sgclist.childName}</option> -->
<!-- 	            		</#if> -->
<!-- 	            	</#list> -->
<!-- 	            </#list> -->
<!--             </#if> -->
<!--            </select> -->
<!--         </td> -->
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
			 <th class="w230" coltype="editable" column="goods_name" checker="check_required" inputwidth="230px" colspan="3" style="text-align:left;padding-left:13%;">商品名称&nbsp;/&nbsp;类型</th>
			 <th class="w100" coltype="editable" column="goods_store_price" checker="check_number" inputwidth="50px">商品价格</th>
			 <th class="w100" coltype="editable" column="spec_goods_storage" checker="check_pint" inputwidth="50px">库存</th>
			 <th class="w100">发布时间</th>
			<!--  <th class="w100" coltype="switchable" column="goods_commend" onclass="recommend-ico-btn" offclass="norecommend-ico-btn">推荐</th>-->
			 <th class="w90">操作</th>
		 </tr>
		 <tr>
			 <td class="tc"><input type="checkbox" id="all" class="checkall"/></td>
			 <td colspan="20">
				 <label for="all" >全选</label>
<!-- 				 <a href="javascript:void(0);" class="ncu-btn1" onClick="deleteBatchGoods();"> -->
<!-- 				 	<span>删除</span> -->
<!-- 				 </a> -->
				 <a href="javascript:void(0);" class="ncu-btn1" onClick="downBatchGoods();">
				 	<span>下架</span>
				 </a>
			 </td>
		 </tr>
      </thead>
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
<!-- 			        	<img src="${imgServer}${gl.goodsImage}" onload="javascript:DrawImage(this,60,60);" /> -->
<!-- 			        </a> -->
			        <a href="${base}/pro/lookgoods?goodsId=${gl.goodsId}" target="_blank">
			        	<img src="${imgServer}${gl.goodsImage}" onload="javascript:DrawImage(this,60,60);" />
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
	        <span class="tip" nctype="prict_selector" nc_gid="89" title="">
	        	${gl.goodsCostPrice}
	        </span>
        </td>
        <td>
	        <span class="tip" nctype="stock_selector" nc_id="89" title="">
	        	${gl.goodsTotalStorage}
	        </span>
        </td>
        <td class="goods-time">${gl.createTimeStr}</td>
        <!--<#if gl.goodsCommend == 0>
        	<td><span nc_type="editobj" class="tip norecommend-ico-btn" status="off" title="选择是否作为供应商推荐商品" onclick="changeToCommend();"></span>
        	<input type="hidden" id="goodsCommend" value="${gl.goodsCommend}" /></td>
        <#else>
        	<td><span nc_type="editobj" class="tip recommend-ico-btn" status="on" title="选择是否作为供应商推荐商品" onclick="changeToCommend();"></span>
        	<input type="hidden" id="goodsCommend" value="${gl.goodsCommend}" /></td>
        </#if>-->
        <td class="tc">
<!--         	<a href="javascript:void(0);" onClick="combinationDialog('${gl.goodsId}','${gl.storeId}')"> -->
<!-- 	        	修改组合商品 -->
<!-- 	        </a> -->
<!-- 	        <br> -->
<!-- 	        <a href="${base}/pro/editgoods?goodsId=${gl.goodsId}" target="_blank"> -->
<!-- 	        	编辑商品 -->
<!-- 	        </a> -->

					<a href="${base}/pro/lookgoods?goodsId=${gl.goodsId}" target="_blank">
					        	查看商品
			        </a>
					        
<!--         	<a  href="javascript:void(0);" onclick="deleteGoods('${gl.goodsId}');" class="ncu-btn2 mt5"> -->
<!-- 	        	删&nbsp;除 -->
<!-- 	        </a> -->
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
			var zhezhao = layer.load(1, {shade: [0.8, '#393D49']});
			zhezhao;
			$.post(url, args, function(data){
				layer.close(zhezhao);
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





</script> 
 </div>
  </div>
    <div class="clear"></div>
</div>


</body>
</html>
<@p.footer/>