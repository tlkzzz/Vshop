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
            <h3>商品活动管理</h3>
            <ul class="tab-base">
                <li><a href="${base}/goods/activity/list"><span>管理</span></a></li>
                <li><a href="JavaScript:void(0);" class="current"><span>新增</span></a></li>
            </ul>
        </div>
    </div>
    <div class="fixed-empty"></div>
    <form id="spec_form" method="post" action="${base}/goods/activity/save">
        <table class="table tb-type2">
            <tbody>
            <tr>
                <td class="required" colspan="2"><label class="validation" for="activityName">活动名称：</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type="text" class="txt" name="activityName" id="activityName" value=""/></td>
                <td class="vatop tips"></td>
            </tr>
            <tr class="noborder">
                <td class="required" colspan="2"><label class="validation" for="activityBanner">活动banner：</label></td>
            </tr>
            <tr class="noborder">
            	<td>
					<div class="logo clf" > 
                          <a href="javascript:void(0);" class="btn-upload btng-s" style="background-color:#eee; color:#000; cursor:pointer;">
                             <input type="file" class="file" name="files" id="myBlogImage0" onChange="ajaxFileUploads('myBlogImage0','busLicense','activityBanner');" />
                          </a>
		                  <img  src="${base}/images/nopic.png" height="100px" id="busLicense" class="img">
		            </div>
		            <input type="hidden" id="activityBanner" name="activityBanner" value="" />
                </td>
                <td class="vatop tips">支持格式gif,jpg,jpeg,png</td>
            </tr>
            <tr>
                <td class="required" colspan="2"><label class="validation" for="activityUse">启用状态</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform">
                	<input type="radio" name="activityUse" checked="checked" value="1" id="activityUse1"/><label for="activityUse1">启用</label>
	                <input type="radio" name="activityUse" value="0"  id="activityUse0"/><label for="activityUse0">停用</label>
	            </td>
                <td class="vatop tips"></td>
            </tr>
            <tr class="noborder">
                <td class="required" colspan="2"><label for="activityBanner2">活动banner2：</label></td>
            </tr>
            <tr class="noborder">
                <td>
					<div class="logo clf" > 
                          <a href="javascript:void(0);" class="btn-upload btng-s" style="background-color:#eee; color:#000; cursor:pointer;">
                             <input type="file" class="file" name="files" id="myBlogImage1" onChange="ajaxFileUploads('myBlogImage1','busLicense1','activityBanner2');" />
                          </a>
		                  <img  src="${base}/images/nopic.png" height="100px" id="busLicense1" class="img">
		            </div>
		            <input type="hidden" id="activityBanner2" name="activityBanner2" value="" />
                </td>
                <td class="vatop tips">支持格式gif,jpg,jpeg,png</td>
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
            	activityName : {
                    required : true,
                    remote   : {
                        url :APP_BASE+'/goods/activity/validate',
                        type:'get',
                        data:{
                            activityName : function() {
                                return $('#activityName').val();
                            }
                        }
                    }
                },
                activityBanner : {
                	required : true
                }
            },
            messages : {
                activityName: {
                    required : '活动名称不能为空',
                    remote   : '该活动名称已经存在了，请您换一个'
                },
                activityBanner: {
                    required : '请上传活动Banner'
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

	// 上传图片
	function ajaxFileUploads(myBlogImage,imgId,img){
	    $.ajaxFileUpload({
	        //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
	        url:'${base}/upload/fileUpload',
	        secureuri:false,                       //是否启用安全提交,默认为false
	        fileElementId:myBlogImage,           //文件选择框的id属性
	        dataType:'json',                       //服务器返回的格式,可以是json或xml等
	        fileSize:5120000,
	        allowType:'jpg,jpeg,png,JPG,JPEG,PNG',
	        success:function(data, status){        //服务器响应成功时的处理函数
	            if( true == data.success){     //0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述)
	            	//alert(data.result);
	               $("img[id='"+imgId+"']").attr("src", "${imgServer}"+data.result);
	               $("#"+img).val(data.result);
	            }
	        },
	        error:function(data, status, e){ //服务器响应失败时的处理函数
	        	layer.msg('图片上传失败，请重试！！', 1, 8);
	            //$('#result').html('图片上传失败，请重试！！');
	        }
	    });
	}
</script>
</@layout.body>