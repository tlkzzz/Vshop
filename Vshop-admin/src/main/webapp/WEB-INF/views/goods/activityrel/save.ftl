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
            <h3>商品关联活动</h3>
            <ul class="tab-base">
                <li><a href="${base}/goods/activityrel/list?goodsId=${goodsId}&storeId=${storeId}"><span>管理</span></a></li>
                <li><a href="JavaScript:void(0);" class="current"><span>关联新的活动</span></a></li>
            </ul>
        </div>
    </div>
    <div class="fixed-empty"></div>
    <form id="spec_form" method="post" action="${base}/goods/activityrel/save">
        <table class="table tb-type2">
            <tbody>
            <tr>
                <td colspan="2">
                	<font color="blue">${storeName}</font>  
                	<font color="green">${goodsName}</font>
                	<input type="hidden" name="goodsId" id="goodsId" value="${goodsId}"/>
                	<input type="hidden" name="storeId" id="storeId" value="${storeId}"/>
                </td>
            </tr>
            
            <tr>
                <td class="required" colspan="2"><label class="validation" for="activityId">活动名称：</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform" colspan="2">
                	<select name="activityId" class="select" id="activityId" style="height:30px">
		        		<option selected="selected" value="">请选择</option>
		           		<#if goodsActivityList??>
		  					<#list goodsActivityList as str>
		  						<option value="${str.activityId}" >${str.activityName}</option>
		  					</#list>
						</#if>
	         		</select>
				</td>
            </tr>
            
            <tr class="noborder">
                <td class="required" colspan="2"><label class="validation" for="activityType">活动类型：</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform" colspan="2">
                	<select name="activityType" class="select" id="activityType" style="height:30px">
		        		<option selected="selected" value="">请选择</option>
		           		<#if activityTypeList??>
		  					<#list activityTypeList as str>
		  						<option value="${str.dictionaryId}" >${str.dictionaryName}</option>
		  					</#list>
						</#if>
	         		</select>
                </td>
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
                activityId: {
                    required : true,
                    remote   : {
                        url :APP_BASE+'/goods/activityrel/validate',
                        type:'get',
                        data:{
                        	goodsId : function() {
                                return $('#goodsId').val();
                            },
                        	storeId : function() {
                                return $('#storeId').val();
                            },
                            activityId : function() {
                                return $('#activityId').val();
                            },
                            activityType : function() {
                                return $('#activityType').val();
                            }
                        }
                    }
                },
                activityType: {
                    required : true,
                    remote   : {
                        url :APP_BASE+'/goods/activityrel/validate',
                        type:'get',
                        data:{
                            goodsId : function() {
                                return $('#goodsId').val();
                            },
                        	storeId : function() {
                                return $('#storeId').val();
                            },
                            activityId : function() {
                                return $('#activityId').val();
                            },
                            activityType : function() {
                                return $('#activityType').val();
                            }
                        }
                    }
                }
            },
            messages : {
                activityId: {
                    required : '请选择活动',
                    remote : "此商品已经绑定本活动、活动类型，请重新选择！"
                },
                activityType: {
                    required : '请选择活动类型',
                    remote : "此商品已经绑定本活动类型、活动，请重新选择！"
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

validate
</script>
</@layout.body>