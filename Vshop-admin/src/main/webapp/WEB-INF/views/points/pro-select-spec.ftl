<@layout.head>
<script type="text/javascript" src="${base}/res/js/jquery-ui/jquery.ui.js"></script>
<script type="text/javascript" src="${base}/res/js/jquery-ui/i18n/zh-CN.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="${base}/res/js/jquery-ui/themes/ui-lightness/jquery.ui.css"  />
<script type="text/javascript" src="${base}/res/js/admincp.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/common_select.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/perfect-scrollbar.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/common.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/jquery.mousewheel.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/layer/layer.js"></script>
</@layout.head>
<@layout.body>
<div class="page">
<div class="fixed-bar">
    <div class="item-title">
        <h3>请选择 ${goods.goodsName} 的规格</h3>
    </div>
</div>
<div class="fixed-empty"></div>
    <table id="prompt" class="table tb-type2">
        <tbody>
        <tr class="space odd">
            <th colspan="12" class="nobg"> <div class="title">
                <h5>操作提示</h5>
                <span class="arrow"></span> </div>
            </th>
        </tr>
        <tr class="odd">
            <td><ul>
                <li>关联规格不是必选项，它会影响商品发布时的规格及价格的录入。不选为没有规格。</li>
            </ul></td>
        </tr>
        </tbody>
    </table>
    <form action="${base}/points/pro/detail" method="post" id="nextForm">
    	<input type="hidden" value="" id="specId" name="goodsSpecId">
    	<input type="hidden" value=" ${goods.goodsId}" name="goodsId">
    </form>
   <table class="table tb-type2">
        <tbody>
        <tr>
            <td colspan="3"><a href="${frontServer}/product/detail?id=${goods.goodsId}">查看商品详情</a></td>
        </tr>
        <!-- <tr class="noborder">
            <td class="vatop rowform"><input type="text" class="txt" name="goodsType.typeName" id="t_mane" /></td>
            <td class="vatop tips"></td>
        </tr> 
        <tr>
            <td class="required" colspan="2"><label class="validation" for="goodsType.typeSort">排序：</label></td>
        </tr>
		<tr class="noborder">
            <td class="vatop rowform"><input type="text" class="txt" name="goodsType.typeSort" id="t_sort" value="0" /></td>
            <td class="vatop tips">请填写自然数。类型列表将会根据排序进行由小到大排列显示。</td>
        </tr>  -->
        </tbody>
    </table>
    <#if goodsSpecObj??>
    <#assign nameMap = goodsSpecObj.specname>
    <#assign valueMap = goodsSpecObj.specvalue>
    <#assign goodsSpecs = goodsSpecObj.goodsSpecs>
    <#assign goodsColImg = goodsSpecObj.goodsColImg>
    
   <#if nameMap??>
   	<#list nameMap?keys  as key>
	    <div style="width: 100%; float: left; margin: 10px 0; border: solid #DEEFFB; border-width: 0 0 1px 0;" name="specDiv">
	        <table class="table tb-type2" style="margin: 0;">
	            <thead class="thead">
	            <tr class="space">
	                <th colspan="15"> <label style=" float: left; margin-right: 10px;">规格名称：${nameMap[key]}</label>
	                </th>
	            </tr>
	            <tr>
	            	<th></th>
	            	<th>规格值</th>
	            	<th>规格图片</th>
	            </tr>
	            </thead>
	        </table>
	        <div style="position:relative; max-height:240px; overflow: hidden;" id="spec_div" class="scrollbar-box">
	            <table class="table tb-type2" id="spec_table">
			  	 <#if valueMap??>
	                <tbody>
	                	<#list valueMap[key] as goodsSpecMapValues>
	                        <tr class="hover edit">
	                            <td width="50px">
	                                <ul class="nofloat">
	                                     <li class="left w33pre h36">
	                                         <input class="checkitem" onClick="selectSpec('${goodsSpecMapValues.spId}', this, '${goodsSpecMapValues.spValueId}')" type="radio" value="${goodsSpecMapValues.spValueId}" name="${key}">
	                                     </li>
	                                </ul>
	                            </td>
	                            <td>${goodsSpecMapValues.spValueName}</td>
	                            <td>
									<#if key==1>
				            			<#if goodsColImg?? && goodsColImg!''>
				            				<#list goodsColImg?keys as goodsColImgKey>
				            					<#if goodsSpecMapValues.spValueId==goodsColImgKey>
				            						<img alt="" src="${imgServer}${goodsColImg[goodsColImgKey]}" style="height: 25px;width: 25px;">
				            					</#if>
				            				</#list>
				            			</#if>
				            		</#if>
								</td>
	                        </tr>
	                    </#list>
	                </tbody>
	                </#if>
	            </table>
	        </div>
	    </div>
    </#list>
    </#if>
    </#if>
    <a class="btn" href="javascript:void(0);" id="next">下一步</a>
</div>
<script>
	/*下一步*/
	$(function(){
		$("#next").click(function(){
			var goodsSpecId = $("#specId").val();
			if(goodsSpecId == ""){
				layer.alert("请选择规格",{icon:2});
			}else{
				$("#nextForm").submit();
			}
		});
	});
</script>
<script type="text/javascript">
/* spec对象 */
function spec(id, spec, price, stock){
    this.id    = id;
    this.spec  = spec;
    this.price = price;
    this.stock = stock;
}
/* goodsspec对象 */
function goodsspec(specs, specQty, defSpec){
    this.specs = specs;
    this.specQty = specQty;
    this.defSpec = defSpec;
    this.spec1 = null;
    this.spec2 = null;
    this.spec3 = null;
    this.spec4 = null;
    if (this.specQty >= 1){
        for(var i = 0; i < this.specs.length; i++){
            if (this.specs[i].id == this.defSpec){
                  this.spec1 = this.specs[i].id;
                  this.spec2 = this.specs[i].spec;
                  this.spec3 = this.specs[i].price;
                  this.spec4 = this.specs[i].stock;
                  break;
            }
        }
    }

    // 取得选中的spec
    this.getSpec = function(){
        for (var i = 0; i < this.specs.length; i++){
              if (this.specs[i].spec != this.spec2) continue;
              return this.specs[i];
        }
        return null;
    }

}

var specs = new Array();
var source_goods_price = '${goods.goodsStorePrice}';
<#if goodsSpecObj??>
<#if goodsSpecs??>
	<#list goodsSpecs as gss>
		specs.push(new spec('${gss.goodsSpecId}', 
				'${gss.specValueIdStr}'.split(","), 
				'${gss.specGoodsPrice}', 
				'${gss.specGoodsStorage}'));
	</#list>
</#if>
var specQty = '${goodsSpecs?size}';
var defSpec = '${goods.specId}';
var goodsspec = new goodsspec(specs, specQty, defSpec);
</#if>

/* 选中某规格 num=1,2 */
function selectSpec(num, liObj, SID){
	
    var spValueId = "";
    
    var sign = "t";
    
    if($('[type="radio"]:checked').length < $('[name="specDiv"]').length){
    	sign = 'f';
    }else{
    	$('[type="radio"]:checked').each(function(){
			spValueId += $(this).val() +",";
   		 });
    }
	spValueId = spValueId.substring(0, spValueId.length-1);
	
	goodsspec['spec2'] = spValueId;
	var spec = goodsspec.getSpec();
	
    if (spec != null && sign == 't'){
    	$("#specId").attr("value", spec.id);
     }
}
</script>
</@layout.body>