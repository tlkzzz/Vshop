<@layout.head>
<script type="text/javascript" src="${base}/res/js/jquery-ui/jquery.ui.js"></script>
<script type="text/javascript" src="${base}/res/js/jquery-ui/i18n/zh-CN.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="${base}/res/js/jquery-ui/themes/ui-lightness/jquery.ui.css"  />
<script type="text/javascript" src="${base}/res/js/jquery.edit.js"></script>
<script type="text/javascript">
    $(function(){
        $('#ncsubmit').click(function(){
           $('#formSearch').submit();
        });
         $('#dcsubmit').click(function(){
           $("#formSearch").attr("action","${base}/goods/wxgoods/exportExcel");
           $('#formSearch').submit();
        });
    });
    function delBrand(){
    	var brandIds = (function(){
    	var _ids = []; 
    	$("input[name='id']:checked").each(function(){
    	_ids.push($(this).val())});
    	return _ids})();
    	
    	_.isEmpty(brandIds) ? alert("请至少选择一个要删除的项目") : (confirm('您确定要删除吗?') && delValide(brandIds));
    }
    function delRow(obj){
        confirm('您确定要删除吗?') && delValide([$(obj).parents("tr").find("td:eq(0)>input").attr("checked", true).val()]);
    }
    
    function delValide(brandIds){
    	$.post("${base}/goods/wxgoods/delValide", {id: brandIds.toString()}, function(result){
    		if(result.success){
    		location.reload();
    			//$('#form_list').submit();
    		}else{
    		alert('删除失败');
    		}
    	}, "JSON");
    }
</script>
</@layout.head>
<@layout.body>
<div class="page">
    <div class="fixed-bar">
        <div class="item-title">
            <h3>发红包</h3>
            <ul class="tab-base">
                <li><a href="JavaScript:void(0);" class="current"><span>管理</span></a></li>
                <li><a href="${base}/goods/wxgoods/forward"><span>新增</span></a></li>
            </ul>
        </div>
    </div>
    <div class="fixed-empty"></div>
        <form method="post" name="formSearch" id="formSearch" action="${base}/goods/wxgoods/list">
        <input type="hidden" name="pageNo" value="${pager.pageNo}">
        <table class="tb-type1 noborder search">
            <tbody>
            <tr>
                <th><label for="search_supplier_name">名称</label></th>
                <td><input class="txt" name="name"  value="${wxgoods.name}" type="text" style="width:160px;"></td>
                  <th><label for="search_supplier_spbh">编号</label></th>
                <td><input class="txt" name="spbh"  value="${wxgoods.spbh}" type="text" style="width:160px;"></td>
                  <th><label for="search_supplier_je">金额</label></th>
                <td><input class="txt" name="je"  value="${wxgoods.je}" type="text" style="width:160px;"></td>
                <th><label for="search_supplier_state">是否已扫码状态</label></th>
                <td>
                	<select name="state"  class="class-select" style="width:90px;">
                	   <option value="">请选择</option>
                      	 <option <#if wxgoods.state == 0>selected="selected"</#if> value="0">未扫码</option>
                        <option <#if wxgoods.state == 1>selected="selected"</#if> value="1">已扫码</option>
                    </select>
                </td>
                <td><a href="javascript:void(0);" id="ncsubmit" class="btn-search " title="检索">&nbsp;</a>
                
                    <#if wxgoods.name != '' || wxgoods.state != '' || wxgoods.spbh != '' || wxgoods.je != ''>
                        <a class="btns " href="${base}/goods/wxgoods/list" title="撤销检索"><span>撤销检索</span></a>
                    </#if>
                    <a href="javascript:void(0);" id="dcsubmit" class="btn " title="导出">导出</a>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
    <table class="table tb-type2" id="prompt">
        <tbody>
        <tr class="space odd">
            <th colspan="12"><div class="title"><h5>操作提示</h5><span class="arrow"></span></div></th>
        </tr>
        <tr>
            <td>
                <ul>
                    <!--<li>当院长添加商品时可选择商品品牌，用户可根据品牌查询商品列表</li>-->
                </ul></td>
        </tr>
        </tbody>
    </table>
    <form method="post" id='form_list' action="${base}/goods/wxgoods/delete">
        <input type="hidden" name="form_submit" value="ok" />
        <table class="table tb-type2">
            <thead>
            <tr class="thead">
                <th class="w24"></th>
            
                <th>名称</th>
                <th>编号</th>
                <th>扫码人</th>
                 <th>扫描时间</th>
                <th>扫描次数</th>
                <th>状态</th>
                <th>金额</th>
                <th>二维码地址</th>
                <th>创建时间</th>
                <th class="w72 align-center">操作</th>
            </tr>
            </thead>
            <tbody>
            <#list pager.result as brand>
	            <tr class="hover edit">
	                <td>
	                	<input value="${brand.id}" class="checkitem" type="checkbox" name="id">
	                </td>
	            	 <td>
	                       ${brand.name}
				    </td>
				     <td>
	                       ${brand.spbh}
				    </td>
				    	 <td>
	                       ${brand.smr}
				    </td>
				     <td>
	                       
	                     	${brand.smsj}
	                    
				    </td>
				     <td>
	                       ${brand.nb}
				    </td>
	                <td>
	                       <#if brand.state ==0>
	                        		未扫码
	                       <#elseif brand.state ==1>
						   			已扫码
	                       </#if>
				    </td>
				     <td>
	                       ${brand.je}
				    </td>
				    <td>${brand.url}</td>
	                <td>
	                  ${brand.cjsj}
				    </td>
	                 </td>
	                <td class="align-center">
	                    <a href="${base}/goods/wxgoods/forwards?id=${brand.id}">编辑</a>
	                    &nbsp;|&nbsp;
	                    <a href="javascript:;" onclick="delRow(this)" >删除</a>
	                </td>
	            </tr>
            </#list>
            </tbody>
            <tfoot>
            <tr colspan="15" class="tfoot">
                <td><input type="checkbox" class="checkall" id="checkallBottom"></td>
                <td colspan="16"><label for="checkallBottom">全选</label>
                    &nbsp;&nbsp;<a href="JavaScript:void(0);" class="btn"
                                   onclick="delBrand()"><span>删除</span></a>
                    <@layout.pager pager/>
                 </td>
            </tr>
            </tfoot>
        </table>
    </form>
    <div class="clear"></div>
</div>
</div>
</@layout.body>