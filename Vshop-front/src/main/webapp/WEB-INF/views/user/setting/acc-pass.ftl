<@p.userHeader title="设置－个人中心"/>
<script src="${base}/res/js/jquery.validation.min.js" ></script>
<script type="text/javascript" src="${base}/res/js/layer/layer.js"></script>
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
							<li class="active"><a href="${base}/user/setting/queryPass">修改密码</a>
							</li>
							<li class="normal"><a href="${base}/user/setting/queryFace">更换头像</a>
							</li>
						</ul>
					</div>
					<div class="ncu-form-style">
						<form method="post" id="profile_form" action="">
						  <dl>
					        <dt class="required"><em class="pngFix"></em>您的密码：</dt>
					        <dd>
					          <input type="password"  maxlength="16" class="text" name="memberPasswd" id="memberPasswd"/>
					          <label for="memberPasswd" generated="true" class="error"></label>
					        </dd>
					      </dl>
					      <dl>
					        <dt class="required"><em class="pngFix"></em>新密码：</dt>
					        <dd>
					          <input type="password"  maxlength="16" class="password" name="newPasswd" id="newPasswd"/>
					          <label for="newPasswd" generated="true" class="error"></label>
					        </dd>
					      </dl>
					      <dl>
					        <dt class="required"><em class="pngFix"></em>确认密码：</dt>
					        <dd>
					          <input type="password" maxlength="16" class="password" name="confirm_password" id="confirm_password" />
					          <label for="confirm_password" generated="true" class="error"></label>
					        </dd>
					      </dl>
					      <dl class="bottom">
					        <dt>&nbsp;</dt>
					        <dd>
					          <input type="button" id="submitBtn" name="Submit" value="保存修改" class="submitsubmit fl" title="立即注册" />
					        </dd>
					      </dl>
						</form>
					</div>
				</div>
				<script type="text/javascript" src="${base}/res/js/common_select.js"
					charset="utf-8"></script>
				<script type="text/javascript">
					$(function(){
					$('#submitBtn').click(function(){
				        if($("#profile_form").valid()){
				           $("#submitBtn").attr('disabled',true);
							$.ajax({
					            type: "post",
					            url: SITEURL+'/user/setting/updatePass',
					            data: $("#profile_form").serialize(),
					            dataType: "json",
					            async:false,
					            success:function(data) {
					                if(data.success=='true'){
					                        setTimeout(layer.msg(data.message, {icon: 1}),1000);
					                }else{
						                    setTimeout(layer.msg(data.message, {icon: 2}),1000);
						                    $("#submitBtn").removeAttr("disabled");
					                }
					            }
					        }); 
				        }
				    });
				    
					 $("#profile_form").validate({
				         errorPlacement: function(error, element){
				             error.appendTo(element.parents("dd").find('label:last'));
				         },
				         rules : {
				        	 memberPasswd: {
				                 required: true,
				                 minlength: 6,
				                 maxlength: 20
				              },
				              newPasswd: {
				                  required: true,
				                  minlength: 6,
				                  maxlength: 20
				               },
				               confirm_password:
							      {
							        required: true,
							        minlength: 6,
							        equalTo: "#newPasswd"
							      }
				         },
				         messages : {
				        	 memberPasswd : {
				                 required : '原始密码不能为空',
				                 maxlength: '密码长度应在6-20个字符之间',
				                 minlength: '密码长度应在6-20个字符之间'
				             },
				             newPasswd : {
				                 required : '新密码不能为空',
				                 maxlength: '密码长度应在6-20个字符之间',
				                 minlength: '密码长度应在6-20个字符之间'
				             },
				             confirm_password:
						      {
						        required: '确认密码不能为空',
						        minlength:'确认密码不能小于6个字符',
						        equalTo: '两次输入密码不一致'
						      }
				         }
				     });
				});
				</script>
				<script charset="utf-8" type="text/javascript"
					src="${base}/res/js/jquery-ui/i18n/zh-CN.js"></script>
			</div>
		</div>
	</div>
</div>
<@p.userfooter />
