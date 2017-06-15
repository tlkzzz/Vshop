<@layout.head>
<link href="${base}/res/css/font/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
<!--[if IE 7]>
  <link rel="stylesheet" href="${base}/res/css/font/font-awesome/css/font-awesome-ie7.min.css">
<![endif]-->
<script type="text/javascript" src="${base}/res/js/jquery-ui/jquery.ui.js"></script>
<script type="text/javascript" src="${base}/res/js/jquery-ui/i18n/zh-CN.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="${base}/res/js/jquery-ui/themes/ui-lightness/jquery.ui.css"  />
<script type="text/javascript" src="${base}/res/js/common_select.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/dialog/dialog.js" id="dialog_js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="${base}/res/js/dialog/dialog.css"  />
<script type="text/javascript" src="${base}/res/js/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="${base}/res/js/jquery.mousewheel.js"></script>
<script type="text/javascript">
    
</script>
</@layout.head>
<@layout.body>
<div class="page">
    <div class="fixed-empty"></div>
    <form method="post" name="formSearch" id="formSearch" action="${base}/goods/goodsCommon/recommendlist">
        <input type="hidden" name="pageNo" value="${pager.pageNo}">
        <table class="tb-type1 noborder search">
            <tbody>
	            <tr>
	                <th><label for="search_goods_name">商品名称</label></th>
	                <td><input type="text" value="${regoods.goods.goodsName}" name="goodsName" id="search_goods_name" class="txt"></td>
	                <td ><a href="javascript:void(0);" id="ncsubmit" class="btn-search " title="查询">&nbsp;</a></td>
	            </tr>
            </tbody>
        </table>
    </form>
    <table class="table tb-type2" id="prompt">
        <tbody>
        <tr class="space odd">
            <th colspan="12"><div class="title">
                <h5>操作提示</h5>
                <span class="arrow"></span></div></th>
        </tr>
        <tr>
            <td><ul>
                <li>上架，当商品处于非上架状态时，前台将不能浏览该商品，院长和管理员都可控制商品上架状态</li>
                <li>违规下架，当商品处于违规下架状态时，前台将不能浏览该商品，只有管理员可控制商品违规下架状态，并且商品不能上架</li>
            </ul></td>
        </tr>
        </tbody>
    </table>
    <form method='post' id="form_list">
        <input type="hidden" name="form_submit" value="ok" />
        <table class="table tb-type2">
            <thead>
            <tr class="thead">
                <th class="w24"></th>
                <th class="w24"></th>
                <th class="align-center">平台货号</th>
                <th colspan="2">商品名称</th>
                <th>品牌&分类</th>
                <th class="align-center">价格</th>
                <th class="align-center">商品状态</th>
                <th class="w48 align-center">操作  </th>
            </tr>
            </thead>
            <tbody>
            <#if checklist??>
	            <#list checklist as regoods>
	            <tr class="hover edit">
	                <td><input type="checkbox"name="ids" id="ids" value="${regoods.goods.goodsId}" class="checkitem"></td>
	                <td><i class="icon-plus-sign" style="cursor: pointer;" nctype="ajaxGoodsList" data-comminid="${regoods.goods.goodsId}"
	                       title="点击展开查看此商品全部规格；规格值过多时请横向拖动区域内的滚动条进行浏览。"></i></td>
	                <td class="w60 align-center">${regoods.goods.goodsId}</td>
	                <td class="w60 picture"><div class="size-56x56"><span class="thumb size-56x56"><i></i>
	                    <img src="${imgServer}${regoods.goods.goodsImage}" onload="javascript:DrawImage(this,56,56);"/>
	                </span></div></td>
	                <td class="goods-name w270"><p><span>${regoods.goods.goodsName}</span></p>
	                    <p class="store">所属商家:${regoods.goods.storeName}</p></td>
	                <td><p>${regoods.goods.brandName}</p>
	                    <p>${regoods.goods.gcName}</p></td>
	                <td class="align-center">￥${regoods.goods.goodsStorePrice}</td>
	                <td class="align-center">
	                	<#if regoods.goods.goodsShow==0>下架</#if>
	                    <#if regoods.goods.goodsShow==1>上架</#if>
	                    <#if regoods.goods.goodsState==1>(违规)</#if>
                    </td>
                     <td class="align-center">
	                	<p><a href="javascript:void(0);" onclick="delrecommend('${regoods.relId}');">删除</a></p>
	                </td>
	            </tr>
	            <tr style="display:none;">
	                <td colspan="20"><div class="ncsc-goods-sku ps-container"></div></td>
	            </tr>
	            </#list>
            </#if>
            </tbody>
        </table>
    </form>
</div>

<script type="text/javascript">
    
    // 获得选中ID
    function getId() {
        var str = '';
        $('#form_goods').find('input[name="id[]"]:checked').each(function(){
            id = parseInt($(this).val());
            if (!isNaN(id)) {
                str += id + ',';
            }
        });
        if (str == '') {
            return false;
        }
        str = str.substr(0, (str.length - 1));
        return str;
    }

    // 商品下架
    function goods_lockup(ids) {
        _uri = APP_BASE+"/goods/goodsCommon/remark?id="+ids ;
        CUR_DIALOG = ajax_form('goods_lockup', '违规下架理由', _uri, 350);
    }
    
      function delrecommend(id){
              //校验菜单底线是否含有子菜单
	           if(confirm('您确定要删除吗?')){
	           var url = "${base}/RelGoodsRecommend/delete";
			   var para = {"id":id};
			   $.ajax({
		             type: "post",
		             url: url,
		             data: para,
		             dataType: "json",
					 async:false,
					 success:function(data) {
						if(data.success == "true"){
						     alert(data.result);
						     window.location = '${base}/RelGoodsRecommend/checkrecommendlist?reCommendId='+'${reCommendId}';
						}else{
							  alert(data.result);
						}
					}	
		         });
		       }
        }   
</script>
</@layout.body>