<@layout.head>
<script type="text/javascript" src="${base}/res/js/jquery-ui/jquery.ui.js"></script>
<script type="text/javascript" src="${base}/res/js/jquery-ui/i18n/zh-CN.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="${base}/res/js/jquery-ui/themes/ui-lightness/jquery.ui.css"/>
</@layout.head>
<@layout.body>
<div class="page">
    <div class="fixed-bar">
        <div class="item-title">
            <h3>商家等级</h3>
            <ul class="tab-base">
                <li><a href="${base}/store/grade/list"><span>管理</span></a></li>
                <li><a href="JavaScript:void(0);" class="current"><span>新增</span></a></li>
            </ul>
        </div>
    </div>
    <div class="fixed-empty"></div>
    <form id="grade_form" method="post" action="${base}/store/grade/edit">
        <table class="table tb-type2">
            <tbody>
            <tr class="noborder">
                <td colspan="2" class="required"><label class="validation" for="sgName">等级名称:</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type="text" id="sg_name" name="sgName" class="txt"></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required"><label class="validation" for="sgGoodsLimit">可发布商品数:</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type="text" id="sg_goods_limit" name="sgGoodsLimit" class="txt" value="10"></td>
                <td class="vatop tips">0表示没有限制</td>
            </tr>
            <tr>
                <td colspan="2" class="required"><label class="validation"> 可上传图片数:</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type="text" value="1000" id="sg_album_limit" name="sgAlbumLimit"
                                                 class="txt"></td>
                <td class="vatop tips"></td>
            </tr>
            <#--<tr>-->
            <#--<td colspan="2" class="required"><label for="skin_limit"><?php echo $lang['optional_template_num'];?>:</label>-->
            <#--</label></td>-->
            <#--</tr>-->
            <#--<tr class="noborder">-->
            <#--<td class="vatop rowform"><span class="grey">(<?php echo $lang['in_store_grade_list_set'];?>)</span></td>-->
            <#--<td class="vatop tips"></td>-->
            <#--</tr>-->
            <#--<tr>-->
            <#--<td colspan="2" class="required"><label for="skin_limit"><?php echo $lang['additional_features'];?>:</label>-->
            <#--</label></td>-->
            <#--</tr>-->
            <#--<tr class="noborder">-->
            <#--<td class="vatop rowform"><ul class="nofloat">-->
            <#--<li>-->
            <#--<input type="checkbox" id="function_editor_multimedia" value="editor_multimedia" name="sg_function[]">-->
            <#--<label for="function_editor_multimedia"><?php echo $lang['editor_media_features'];?></label>-->
            <#--</li>-->
            <#--</ul></td>-->
            <#--</tr>-->
            <tr>
                <td colspan="2" class="required"><label class="validation" for="sgPrice">收费标准:</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type="text" value="" id="sg_price" name="sgPrice" class="txt"></td>
                <td class="vatop tips">收费标准，在会员开通或升级商家时将显示在前台</td>
            </tr>
            <tr>
                <td colspan="2" class="required"><label for="sgDescription">申请说明:</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><textarea rows="6" class="tarea" id="sg_description"
                                                    name="sgDescription"></textarea></td>
                <td class="vatop tips">申请说明，在会员开通或升级商家时将显示在前台</td>
            </tr>
            <tr>
                <!-- <td colspan="2" class="required"><label><?php echo $lang['nc_sort'];?>:</label></td> -->
                <td colspan="2" class="required"><label class="validation">级别: </label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type="text" id="sg_sort" name="sgSort" class="txt"></td>
                <td class="vatop tips">数值越大表明级别越高</td>
            </tr>
            </tbody>
            <tfoot>
            <tr class="tfoot">
                <td colspan="15"><a href="JavaScript:void(0);" class="btn" id="submitBtn"><span>提交</span></a></td>
            </tr>
            </tfoot>
        </table>
    </form>
</div>
<script>

    //按钮先执行验证再提交表单
    $(function () {
        $("#submitBtn").click(function () {
            if ($("#grade_form").valid()) {
                $("#grade_form").submit();
            }
        });
    });
    //
    $(document).ready(function () {
        $('#grade_form').validate({
            errorPlacement: function (error, element) {
                error.appendTo(element.parent().parent().prev().find('td:first'));
            },

            rules: {
                sgName: {
                    required: true,
                    remote: {
                        url: APP_BASE + '/store/grade/validate',
                        type: 'post',
                        data: {
                            sgName: function () {
                                return $('#sg_name').val();
                            },
                            sgId: ''
                        }
                    }
                },
                sgPrice: {
                    required: true
                },
                sgGoodsLimit: {
                    required: true,
                    digits: true
                },
                sgAlbumLimit: {
                    required: true,
                    digits: true
                },
                sgSort: {
                    required: true,
                    digits: true,
                    remote: {
                        url: APP_BASE + '/store/grade/validate',
                        type: 'post',
                        data: {
                            sgSort: function () {
                                return $('#sg_sort').val();
                            },
                            sgId: ''
                        }
                    },
                    number   : true,
                    max : 255,
                    min : 0
                }
            },
            messages: {
                sgName: {
                    required: '等级名称不能为空',
                    remote: '该等级名称已经存在，请您换一个'
                },
                sgPrice: {
                    required: '收费标准不能为空'
                },
                sgGoodsLimit: {
                    required: '可发布商品数',
                    digits: '仅能为整数'
                },
                sgAlbumLimit: {
                    required: '可上传图片数:',
                    digits: '仅能为整数'
                },
                sgSort: {
                    required: '级别信息不能为空',
                    digits: '仅能为整数',
                    remote: '级别已经存在',
                    number   : '分类排序仅能为数字',
                    max : '最大值为255',
                    min : '最小值为0'
                }
            }
        });
    });
</script>
</@layout.body>