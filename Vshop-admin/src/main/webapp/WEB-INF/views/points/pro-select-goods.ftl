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
    $(function(){
        $('#ncsubmit').click(function(){
            $('#formSearch').submit();
        });
    });

    function deleteGoods(){
        var items = $("input[name='ids']:checked").length;
        if (items==0) {
            alert("请至少选择一个要删除的项目");
        }else{
            if(confirm('您确定要删除吗?')){
                $("#form_list").attr("action",APP_BASE+"/goods/goodsCommon/delGoods");
                $('#form_list').submit();
            }
        }
    }

    function wgxjGoods(){
        var items = $("input[name='ids']:checked").length;
        if (items==0) {
            alert("请至少选择一个要下架的项目");
        }else{
            var item = "";
            $("input[name='ids']:checked").each(function(){
                item += this.value+",";
            });
           item = item.substring(0,item.length-1);
            goods_lockup(item);
        }
    }
</script>
</@layout.head>
<@layout.body>
<div class="page">
    <div class="fixed-bar">
        <div class="item-title">
            <h3>选择积分商品在商家 ${store.storeName} 下</h3>
        </div>
    </div>
    <div class="fixed-empty"></div>
    <form method="post" name="formSearch" id="formSearch" action="${base}/points/pro/selectGoods">
        <input type="hidden" name="pageNo" value="${pager.pageNo}">
        <input type="hidden" name="storeId" value="${store.storeId}">
        <table class="tb-type1 noborder search">
            <tbody>
            <tr>
                <th><label for="search_goods_name">商品名称</label></th>
                <td><input type="text" value="${goods.goodsName}" name="goodsName" id="search_goods_name" class="txt"></td>
                <th><label for="search_commonid">商品货号</label></th>
                <td><input type="text" value="${goods.goodsSerial}" name="goodsSerial" id="search_commonid" class="txt" /></td>
                <th><label>分类</label></th>
                <td id="gcategory" colspan="8"><input type="hidden" id="cate_id" name="gcId" value="" class="mls_id" />
                    <input type="hidden" id="cate_name" name="gcName" value="" class="mls_names" />
                    <select class="class="querySelect"">
                    <option value="0">请选择...</option>
                    <#list classList as gc>
                        <option value="${gc.gcId}">${gc.gcName}</option>
                    </#list>
                    </select></td>
            </tr>
            <tr>
                <th><label>品牌</label></th>
                <td><select name="brandId">
                    <option value="">请选择...</option>
                <#list brandList as brand>
                    <option value="${brand.brandId}" <#if gcommon.brandId == brand.brandId>selected="selected" </#if>>${brand.brandName}</option>
                </#list>
                </select></td>
                 <td ><a href="javascript:void(0);" id="ncsubmit" class="btn-search " title="查询">&nbsp;</a>
                    <#if goods.goodsName?? || goods.goodsSerial?? || goods.gcId?? || goods.brandId??>
                        <a href="${base}/points/pro/selectGoods?storeId=${store.storeId}" class="btns " title="撤销检索"><span>撤销检索</span></a>
                    </#if>
                 </td>
                <td class="w120">&nbsp;</td>
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
                <th class="align-center">平台货号</th>
                <th colspan="2">商品名称</th>
                <th>品牌&分类</th>
                <th class="align-center">价格</th>
                <th class="align-center">商品状态</th>
                <th class="w48 align-center" style="width: 80px;">操作  </th>
            </tr>
            </thead>
            <tbody>
            <#if pager.result??>
	            <#list pager.result as goods>
	            <tr class="hover edit">
	                <td><input type="checkbox" name="ids" value="${goods.goodsId}" class="checkitem"></td>
	                <td class="w60 align-center">${goods.goodsId}</td>
	                <td class="w60 picture"><div class="size-56x56"><span class="thumb size-56x56"><i></i>
	                    <img src="${imgServer}${goods.goodsImage}" onload="javascript:DrawImage(this,56,56);"/>
	                </span></div></td>
	                <td class="goods-name w270"><p><span>${goods.goodsName}</span></p>
	                    <p class="store">所属商家:${goods.storeName}</p></td>
	                <td><p>${goods.brandName}</p>
	                    <p>${goods.gcName}</p></td>
	                <td class="align-center">￥${goods.goodsStorePrice}</td>
	                <td class="align-center">
	                	<#if goods.goodsShow==0>下架</#if>
	                    <#if goods.goodsShow==1>上架</#if>
	                    <#if goods.goodsState==1>(违规)</#if>
                    </td>
	                <td class="align-center">
	                	<p><a href="${frontServer}/product/detail?id=${goods.goodsId}" target="_blank">查看</a></p>
	                	<p><a href="${base}/points/pro/selectSpec?goodsId=${goods.goodsId}">生成积分商品</a></p>
	                </td>
	            </tr>
	            <tr style="display:none;">
	                <td colspan="20"><div class="ncsc-goods-sku ps-container"></div></td>
	            </tr>
	            </#list>
            </#if>
            </tbody>
            <tfoot>
            <tr class="tfoot">
                <td colspan="16">
            	<@layout.pager pager/>
            	</td>
            </tr>
            </tfoot>
        </table>
    </form>
</div>
</@layout.body>