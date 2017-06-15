<@layout.head>
<link rel="stylesheet" type="text/css" href="${base}/res/css/easyui/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${base}/res/css/easyui/icon.css">
<script type="text/javascript" src="${base}/res/js/common/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${base}/res/js/jquery-ui/jquery.ui.js"></script>
<script type="text/javascript" src="${base}/res/js/jquery-ui/i18n/zh-CN.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="${base}/res/js/jquery-ui/themes/ui-lightness/jquery.ui.css"  />
<script type="text/javascript" src="${base}/res/js/admincp.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/common_select.js" charset="utf-8"></script>

</@layout.head>
<@layout.body>
<div class="page">
    <div class="fixed-bar">
        <div class="item-title">
            <h3>赞助商</h3>
            <ul class="tab-base">
                <li><a href="${base}/sponsor/list"><span>管理</span></a></li>
                <li><a href="${base}/sponsor/forward?id=0"><span>新增</span></a></li>
                <li><a href="JavaScript:void(0);" class="current"><span>编辑</span></a></li>
            </ul>
        </div>
    </div>
    <div class="fixed-empty"></div>
    <form id="sponsor_form" enctype="multipart/form-data" method="post" name="form1" action="${base}/sponsor/edit">
        <input type="hidden" name="form_submit" value="ok" />
        <input type="hidden" name="id" value="${sponsor.id}" />
        <table class="table tb-type2 nobdb">
            <tbody>
            <tr class="noborder">
                <td colspan="2" class="required"><label class="validation">赞助商名称:</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type="text" value="${sponsor.name}" name="name" id="name" class="txt"></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">简称/缩写:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' value="${sponsor.shortName}" name='shortName' id='shortName' class='txt' /></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">赞助商地址:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' value="${sponsor.address}" name='address' id='address' class='txt' /></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">联系人:  </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type='text' value="${sponsor.contacter}" name='contacter' id='contacter' class='txt' /></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required">移动电话:  </td>
            </tr>
            <tr>
                <td colspan="2" class="required">删除状态: </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform onoff">
                	<label for="deleted1" class="<#if sponsor.deleted == 1>cb-enable selected<#else>cb-enable</#if>"><span>是</span></label>
                    <label for="deleted0" class="<#if sponsor.deleted == 0>cb-disable selected<#else>cb-disable</#if>"><span>否</span></label>
                    <input id="deleted1" name="deleted"  value="1" type="radio" <#if sponsor.deleted == 1>checked="checked"</#if>>
                    <input id="deleted0" name="deleted" value="0" type="radio" <#if sponsor.deleted == 0>checked="checked"</#if>>
                </td>
                <td class="vatop tips"></td>
            </tr>
            </tbody>
            <tfoot>
	            <tr class="tfoot">
	                <td colspan="2" ><a href="JavaScript:void(0);" class="btn" id="submitBtn"><span>提交</span></a></td>
	            </tr>
            </tfoot>
        </table>
    </form>
</div>
<script type="text/javascript">
	$(function(){
		$("#submitBtn").on('click', function(){$("#sponsor_form").valid() && $("#sponsor_form").submit()});
		$("#sponsor_form").validate({
            errorPlacement: function(error, element){
                error.appendTo(element.parent().parent().prev().find('td:first'));
            },
            success: function(label){
                label.addClass('valid');
            },
            rules : {
                name : {
                    required : true,
                    remote   : {
                        url :APP_BASE+'/sponsor/validate',
                        type:'get',
                        data:{
                        	name : function(){
                                return $('#name').val();
                            },
                            id  : ${sponsor.id}
                        }
                    }
                }
            },
            messages : {
            	name : {
                    required : '赞助商名称不能为空',
                    remote   : '该赞助商名称已经存在了，请您换一个'
                }
            }
        });
	});
</script>
</@layout.body>