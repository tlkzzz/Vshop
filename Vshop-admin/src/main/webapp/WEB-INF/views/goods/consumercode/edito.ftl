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
                <li><a href="${base}/goods/consumercode/forward?id=0"><span>新增</span></a></li>
                <li><a href="JavaScript:void(0);" class="current"><span>编辑</span></a></li>
            </ul>
        </div>
    </div>
    <div class="fixed-empty"></div>
    <form id="spec_form" method="post" action="${base}/goods/consumercode/update">
    	<input type="hidden" name="consumerCodeId" id="c_code_id" value="${consumerCode.consumerCodeId}"/>
        <table class="table tb-type2">
            <tbody>
            <tr class="noborder">
                <td class="required" colspan="2"><label class="validation" for="c_code">商品消费码：</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type="text" class="txt" name="consumerCodeBunch" readOnly="readOnly" id="c_code" value="${consumerCode.consumerCodeBunch}"/></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td class="required" colspan="2"><label class="validation" for="goods_id">商品id</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type="text" class="txt" name="goodsId" id="goods_id" value="${consumerCode.goodsId}"/></td>
                <td class="vatop tips">请填写自然数。</td>
            </tr>
            <tr>
                <td class="required" colspan="2"><label class="validation" for="code_source">码来源：</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform">
	                <input type="radio" name="codeSource" <#if consumerCode.codeSource = 1>checked="checked"</#if> specType="self" value="1"/>本平台
	                <input type="radio" name="codeSource" <#if consumerCode.codeSource = 3>checked="checked"</#if> specType="other" value="3"/>第三方
				</td>
                <td class="vatop tips">商品消费码来源，一类是本平台根据需要生成，另外一类是第三方电商直接提供。</td>
            </tr>
            <tr>
                <td class="required" colspan="2"><label class="validation" for="member_id">会员id：</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type="text" class="txt" name="memberId" id="member_id" value="${consumerCode.memberId}"/></td>
                <td class="vatop tips">请填写自然数。</td>
            </tr>
            <tr>
                <td class="required" colspan="2"><label class="validation" for="code_status">消费码状态</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform">
                	<input type="radio" name="codeStatus" <#if consumerCode.codeStatus = 0>checked="checked"</#if> value="0" id="codeStatus0"/><label for="codeStatus0">未激活</label>
	                <input type="radio" name="codeStatus" <#if consumerCode.codeStatus = 1>checked="checked"</#if> value="1" id="codeStatus1"/><label for="codeStatus1">激活未用</label>
	                <input type="radio" name="codeStatus" <#if consumerCode.codeStatus = 2>checked="checked"</#if> value="2" id="codeStatus2"/><label for="codeStatus2">激活已用</label>
	                <input type="radio" name="codeStatus" <#if consumerCode.codeStatus = 3>checked="checked"</#if> value="3" id="codeStatus3"/><label for="codeStatus3">失效作废</label>	                
	            </td>
                <td class="vatop tips">请选择。</td>
            </tr>
           
            </tbody>
            <tfoot>
            <tr class="tfoot">
                <td colspan="15"><a id="submitBtn" class="btn" href="JavaScript:void(0);"> <span>提交</span> </a></td>
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
                consumerCodeBunch: {
                    required : true,
                    maxlength: 10,
                    minlength: 1
                },
                goodsId: {
                    required : true,
                    digits	 : true
                },
                memberId: {
                    required : true,
                    digits	 : true,
                    maxlength: 3,
                    minlength: 1
                }
            },
            messages : {
                consumerCodeBunch : {
                    required : '请填写规格名称',
                    maxlength: '规格名称长度应在1-10个字符之间',
                    minlength: '规格名称长度应在1-10个字符之间'
                },
                goodsId: {
                    required : '请填写商品Id',
                    digits   : '请填写整数'
                },
                memberId: {
                    required : '请填写会员Id',
                    digits   : '请填写整数',
               	 	maxlength: '排序应在0-999之间数字',
                    minlength: '排序应在0-999之间数字'
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