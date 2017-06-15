<@layout.head>
<script type="text/javascript" src="${base}/res/js/jquery-ui/jquery.ui.js"></script>
<script type="text/javascript" src="${base}/res/js/jquery-ui/i18n/zh-CN.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="${base}/res/js/jquery-ui/themes/ui-lightness/jquery.ui.css"  />
</@layout.head>
<@layout.body>
<div class="page">
    <div class="fixed-bar">
        <div class="item-title">
            <h3>设置</h3>
            <ul class="tab-base">
                <li><a href="JavaScript:void(0);" class="current"><span>缓存设置</span></a></li>
            </ul>
        </div>
    </div>
    <div class="fixed-empty"></div>
    <table class="table tb-type2" id="prompt">
		<tbody>
			<tr style="background: none repeat scroll 0% 0% rgb(255, 255, 255);"
				class="space odd">
				<th colspan="12"><div class="title">
						<h5>操作提示</h5>
						<span class="arrow"></span>
					</div>
				</th>
			</tr>
			<tr style="background: none repeat scroll 0% 0% rgb(255, 255, 255);"
				class="odd">
				<td><ul>
						<li>使用缓存必须安装redis服务</li>
						<li>请安装 redis 3.0 或以上版本</li>
					</ul>
				</td>
			</tr>
		</tbody>
	</table>
    <table class="table tb-type2">
        <thead>
	        <tr class="thead">
	            
	            <th class="align-center">类型</th>
	            <th class="align-center">时间(秒)</th>
	            <th>状态</th>
	            <th class="align-center">备注</th>
	        </tr>
        </thead>
        <tbody id="treet1">
            <tr class="hover edit">
                <td class="align-center">广告缓存</td>
                <td class="input align-center"><input name="adv_seconds" class="editable" value="${map['adv_seconds']}"/></td>
                <td class="onoff">
	                <label for="isShow1" class="cb-enable selected"><span>是</span></label>
	                <label for="isShow0" class="cb-disable"><span>否</span></label>
	                <input id="isShow1" name="isShow"  value="1" type="radio" checked="true">
	                <input id="isShow0" name="isShow" value="0" type="radio">
                </td>
                <td class="txt"></td>
            </tr>
            <tr class="hover edit">
                <td class="align-center">分类缓存</td>
                <td class="input align-center"><input name="category_seconds" class="editable" value="${map['category_seconds']}"/></td>
                <td class="onoff">
	                <label for="isShow1" class="cb-enable selected"><span>是</span></label>
	                <label for="isShow0" class="cb-disable"><span>否</span></label>
	                <input id="isShow1" name="isShow"  value="1" type="radio" checked="true">
	                <input id="isShow0" name="isShow" value="0" type="radio">
                </td>
                <td class="txt"></td>
            </tr>
            <tr class="hover edit">
                <td class="align-center">数据字典缓存</td>
                <td class="input align-center"><input name="dic_seconds" class="editable" value="${map['dic_seconds']}"/></td>
                <td class="onoff">
	                <label for="isShow1" class="cb-enable selected"><span>是</span></label>
	                <label for="isShow0" class="cb-disable"><span>否</span></label>
	                <input id="isShow1" name="isShow"  value="1" type="radio" checked="true">
	                <input id="isShow0" name="isShow" value="0" type="radio">
                </td>
                <td class="txt"></td>
            </tr>
            <tr class="hover edit">
                <td class="align-center">其他</td>
                <td class="input align-center"><input name="other_seconds" class="editable" value=""/></td>
                <td class="onoff">
	                <label for="isShow1" class="cb-enable selected"><span>是</span></label>
	                <label for="isShow0" class="cb-disable"><span>否</span></label>
	                <input id="isShow1" name="isShow"  value="1" type="radio" checked="true">
	                <input id="isShow0" name="isShow" value="0" type="radio">
                </td>
                <td class="txt"></td>
            </tr>
        </tbody>
        <tfoot>
	        <tr>
	            <td colspan="16">
	                &nbsp;&nbsp;<a href="JavaScript:void(0);" class="btn" onclick="updSet()"><span>保存</span></a></td>
	        </tr>
        </tfoot>
    </table>
</div>
<script type="text/javascript">
var APP_BASE = '${base}';
function updSet(){
	alert("还未实现");
}
</script>
</@layout.body>