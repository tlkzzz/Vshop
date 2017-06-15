<@layout.head>
<script type="text/javascript" src="${base}/res/js/ajaxfileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="${base}/res/js/jquery-ui/jquery.ui.js"></script>
<script type="text/javascript" src="${base}/res/js/jquery-ui/i18n/zh-CN.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/layer/layer.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="${base}/res/js/jquery-ui/themes/ui-lightness/jquery.ui.css"/>
<script type="text/javascript" src="${base}/res/js/admincp.js" charset="utf-8"></script>
</@layout.head>
<@layout.body>
<div class="page">
    <div class="fixed-bar">
        <div class="item-title">
            <h3>收发码管理</h3>
            <ul class="tab-base">
                <li><a href="${base}/goods/consumercode/list"><span>管理</span></a></li>
                <li><a href="JavaScript:void(0);" class="current"><span>新增本平台码</span></a></li>
            </ul>
        </div>
    </div>
    <div class="fixed-empty"></div>
    <form id="spec_form" method="post" action="${base}/goods/consumercode/save">
        <table class="table tb-type2">
            <tbody>
            <tr>
                <td class="required" colspan="2"><label class="validation" for="code_source">码来源：</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform">
	                <input type="radio" name="codeSource" checked="checked" specType="self" value="1"/>本平台
	            </td>
                <td class="vatop tips">商品消费码来源，一类是本平台根据需要生成，另外一类是第三方电商直接提供。</td>
            </tr>
            
            <tr>
                <td class="required" colspan="2"><label class="validation" for="code_status">消费码状态</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform">
                	<input type="radio" name="codeStatus" checked="checked" value="0" id="codeStatus0"/><label for="codeStatus0"><font color="blue">未下发</font></label>
	            </td>
                <td class="vatop tips">新增时，消费码状态为初始化状态：未下发</td>
            </tr>
           
           	<tr>
                <td class="required" colspan="2"><label class="validation" for="code_numbers">新增个数</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type="text" class="txt" name="code_numbers" id="code_numbers" value="10" /></td>
                <td class="vatop tips">可以批量新增码个数，请填写自然数。</td>
            </tr>
            
            </tbody>
            <tfoot>
            <tr class="tfoot">
                <td colspan="15"><a id="submitBtn" class="btn" href="JavaScript:void(0);"><span>提交</span></a></td>
            </tr>
            </tfoot>
        </table>
    </form>
</div>
<script type="text/javascript">
	
    $(function(){
   		// 表单验证
        $('#spec_form').validate({
            errorPlacement: function(error, element){
                error.appendTo(element.parent().parent().prev().find('td:first'));
            },

            rules : {
                code_numbers: {
                    required : true,
                    digits	 : true,
                    maxlength: 3,
                    minlength: 1,
                    min: 1
                }
            },
            messages : {
                code_numbers: {
                    required : '请填写新增消费码个数',
                    digits   : '请填写自然数',
               	 	maxlength: '个数应在1-999之间数字',
                    minlength: '个数应在1-999之间数字',
                    min 	 : '个数应该大于0'
                }
            }
        });

        // 按钮先执行验证再提交表单
        $("#submitBtn").click(function(){
            if($("#spec_form").valid()){
            	$("#spec_form").submit();
		    }
        });
    });

</script>
</@layout.body>