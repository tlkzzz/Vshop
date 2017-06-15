<@p.userHeader title="设置－个人中心"/>
<script src="${base}/res/js/jquery.validation.min.js" ></script>
<script type="text/javascript" src="${base}/res/js/layer/layer.js"></script>
<script type="text/javascript" src="${base}/res/js/ajaxfileupload/ajaxfileupload.js"></script>
<div id="container" class="wrapper">
	<div class="layout">
		<@m.memberleft title="修改头像"/>
		<div class="right-content">
			<div class="path">
				<div>
					<a href="${base}/user/index">会员中心</a><span>&raquo;</span>
					<a href="${base}/user/setting/index" />个人资料</a> <span>&raquo;</span>基本信息
				</div>
			</div>
			<div class="main">
				<link rel="stylesheet" type="text/css"
					href="${base}/js/jquery-ui/themes/ui-lightness/jquery.ui.css" />

				<div class="wrap">
					<div class="tabmenu">
						<ul class="tab pngFix">
							<li class="normal"><a href="${base}/user/setting/index">基本信息</a>
							</li>
							<li class="normal"><a href="${base}/user/setting/queryPass">修改密码</a>
							</li>
							<li class="active"><a href="${base}/user/setting/queryFace">更换头像</a>
							</li>
						</ul>
					</div>
					<div class="ncu-form-style">
						<form method="post" id="profile_form" action="">
						  <input type="hidden" name="memberId" id="memberId" value="${member.memberId}" />
						  <div class="ncu-form-style">
							  <dl>
							    <dd>
							       <input type="hidden" id="memberAvatar" name="memberAvatar" value="" />
							          <p class="picture">
							            <#if member.memberAvatar ??&& member.memberAvatar!=''>
							               <img src="${imgServer}${member.memberAvatar}" id="memberAvatarimage" width="120" height="120" alt="" nc_type="avatar" />
							            <#else>
							               <img src="${base}/res/images/member/default_image.png"  id="memberAvatarimage" nc_type="store_Labelimage" style="width: 200px;height: 60px;"/>
							            </#if>
							          </p>
							          <p>
							            <input  type="file" name="files"  hidefocus="true" id="memberAvatarfile" nc_type="change_storeLable_file" onChange="ajaxFileUploads('memberAvatarfile','memberAvatarimage','memberAvatar');"/>
							          </p>
							    </dd>
							  </dl>
							  <dl class="bottom">
								<dt>&nbsp;</dt>
								<dd>
								    <input type="button" id="submitBtn" name="Submit" value="保存修改" class="submitsubmit fl" title="立即注册" />
									<!-- <input type="submit" class="submit" value="保存修改" /> -->
								</dd>
							</dl>
                          </div>
						</form>
					</div>
				</div>
				<script type="text/javascript" src="${base}/res/js/common_select.js"
					charset="utf-8"></script>
				<script type="text/javascript">
					$(function(){
				       $("#submitBtn").click(function(){
					         $.ajax({
							         type: "post",
							         url: '${base}/user/setting/updateFace',
							         data: $("#profile_form").serialize(),
							         dataType: "json",
									 async:false,
									 success:function(data) {
									 	if(data.success){
											layer.msg(data.message, {icon: 1}, setTimeout("window.location = '${base}/user/setting/index'" , 1000));
										  }else{
					                         layer.msg(data.message, {icon: 2});
										  }
									  } 
							 	 });
				            });
				    });
				     //上传图片
					function ajaxFileUploads(myBlogImage,imgId,img){
					    $.ajaxFileUpload({
					        //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
					        url: '${base}/fileupload/fileUpload',
					        secureuri:false,                      //是否启用安全提交,默认为false
					        fileElementId:myBlogImage,           //文件选择框的id属性
					        dataType:'json',                       //服务器返回的格式,可以是json或xml等
					        fileSize:5120000,
					        allowType:'jpg,jpeg,png,JPG,JPEG,PNG',
					        success:function(data, status){        //服务器响应成功时的处理函数
					            if(true == data.success){     //0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述)
					            	//alert(data.result);
					               $("img[id='"+imgId+"']").attr("src", "${imgServer}"+data.result);
					               $("#"+img).val(data.result);
					            }
					        },
					        error:function(data, status, e){ //服务器响应失败时的处理函数
					             layer.msg('图片上传失败，请重试！！', {icon: 2});
					        	 //layer.msg('图片上传失败，请重试！！', 1, 8);
					            //$('#result').html('图片上传失败，请重试！！');
					        }
					    });
					}
				</script>
				<script charset="utf-8" type="text/javascript"
					src="${base}/res/js/jquery-ui/i18n/zh-CN.js"></script>
			</div>
		</div>
	</div>
</div>
<@p.userfooter />
