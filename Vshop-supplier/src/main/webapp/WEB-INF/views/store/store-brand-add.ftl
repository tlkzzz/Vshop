<link href="${base}/res/css/base.css" rel="stylesheet" type="text/css">
<link href="${base}/res/css/member.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${base}/res/js/common_select.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/jquery.js"></script>
<script type="text/javascript" src="${base}/res/js/ajaxfileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="${base}/res/js/layer/layer.js"></script>
<style>
  .errorMsg{
    margin-left: 15px;
    color: red;
    font-size: 14px;
}
</style>
<div id="fwin_my_goods_brand_apply" class="dialog_wrapper ui-draggable" style="z-index: 1100; position: absolute; width: 480px; left: 53.5px; top: 28.5px;">
<div class="dialog_body" style="position: relative;">
<div class="dialog_content" style="margin: 0px; padding: 0px;">
<div class="eject_con">
    <form method="post" target="_parent" action="" enctype="multipart/form-data" id="brand_apply_form">
      <input type="hidden" name="form_submit" value="ok">
      <input type="hidden" name="brand_id" value="">
      <dl>
        <dt class="required"><em class="pngFix"></em>品牌名称：</dt>
        <dd>
          <input type="text"  class="vat-step" name="brandName" value="${brand.brandName}" id="brand_name" onblur="check_Invoice('brandName', this.value)" fld="strs">
          <span class="errorMsg" id="vat_brandName"></span>
        </dd>
      </dl>
      <dl>
        <dt>品牌类别：</dt>
        <dd id="gcategory">
             <input type="hidden" value="" name="classId" class="mls_id">
             <input type="hidden" value="" name="brandClass" id="brandClass" class="mls_name" fld="strs">
             <select class="class-select">
                        <option value="0">请选择...</option>
                        <#list list as gc>
                            <option value="${gc.gcId}">${gc.gcName}</option>
                        </#list>
             </select>
             <span class="errorMsg" id="vat_brandClass"></span>
        </dd>
      </dl>
      <dl>
        <dt class="required"><em class="pngFix"></em>品牌图标：</dt>
        <dd>
            <input type="hidden" id="brandPic" name="brandPic" value=""  class="vat-step" fld="strs"/>
            <p><span class="sign"><img src="${base}/images/member/default.gif" id="storeband" nc_type="logo1" width="88" height="44"></span></p>
            <p><input type="file"  hidefocus="true" nc_type="logo" name="files" id="brand_pic" onChange="ajaxFileUploads('brand_pic','storeband','brandPic');"></p>
            <span class="errorMsg" id="vat_brandPic"></span>
        </dd>
      </dl>
      <dl>
        <dt>&nbsp;</dt>
        <dd>
          <p class="hint">建议上传大小为88x44的品牌图片。<br>申请品牌的目的是方便会员通过品牌索引页查找商品，申请时请填写品牌所属的类别，方便站长归类。在站长审核前，您可以编辑或撤销申请。</p>
        </dd>
      </dl>
      
       <div class="item" align="center">
			<span class="label">&nbsp;</span>
			<div class="fl">
				<div class="op-btns" style="margin-left: 140px; margin-top: 19px;">
					<a href="#none" class="btn-9" onclick="saveZzInvoice()">保存</a>
					<a href="#none" class="btn-9 ml10" onclick="quxiao()">取消</a>
				</div>
			</div>
	   </div>
    </form>
    </div>
   </div>
</div>
</div>
<script type="text/javascript">
    //上传图片
	function ajaxFileUploads(myBlogImage,imgId,img){
	    $.ajaxFileUpload({
	        //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
	        url: '${base}/storeSetting/fileUpload',
	        secureuri:false,                      //是否启用安全提交,默认为false
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
	 gcategoryInit('gcategory');
</script>

<script type="text/javascript">
    //关闭窗口
	function quxiao(){
		parent.layer.closeAll();
	}
    
	//保存品牌
	function saveZzInvoice(){
		  var check_ok = false;
		  var j=0;
		  $("input[fld='strs']").each(function(){
		    if(check_Invoice(this.name,this.value)) {
		      check_ok = true;
		      j++;
		    }else{
		      check_ok = false;
		      j--;
		    }
		  });
		if(check_ok&&j==3){
			 $.ajax({
	            url : '${base}/storeBrand/saveStorebrands',
	            type : 'post',
	            data : $("#brand_apply_form").serializeArray(),
	            dataType : 'json',
	            success : function(data){
	            	if(data.success){
	            		//$("#invoiceId").val(data.invoiceId);
	            	    setTimeout(layer.msg('新增成功', {icon: 1}),2000);
	            		//parent.layer.closeAll();
	            		parent.location.reload();
	            	}
	            }
	        });
       	} 
	}
	
/**
* 品牌验证
* @param divId
* @param value
*/
function check_Invoice(type, value) {
  // 验证品牌名称
  var flags=false;
  var errorMessage = null;
  if (type == "brandName") {
    var brandName = $("input[name='brandName']");
    if (value=="") {
         errorMessage="品牌名称不能为空";
         flags=true;
    }
  }
   //验证类别
   else if (type == "brandClass") {
   var brandClass = $("input[name='brandClass']");	
		if(value==""){
			errorMessage="品牌类别不能为空";
			flags=true;
		}
     }
     
   //验证品牌图标
   else if (type == "brandPic") {
   var brandPic = $("input[name='brandPic']");	
		if(value==""){
			errorMessage="品牌图标不能为空";
			flags=true;
		}
     }
     if (flags) {
       $("#" + "vat_"+type).html(errorMessage);
          return false;
     }else{
       $("#" + "vat_"+type).html("");
     } 
     return true;
}
</script>
 
