<@p.userHeader title="设置－个人中心"/>
<script src="${base}/res/js/jquery.validation.min.js" ></script>
<script type="text/javascript" charset="utf-8" src="${base}/res/js/area.js"></script>
<script type="text/javascript" src="${base}/res/js/layer/layer.js"></script>
<script type="text/javascript" src="${base}/res/js/My97DatePicker/WdatePicker.js"></script>
<div id="container" class="wrapper">
	<div class="layout">
		<@m.memberleft title="用户基本信息"/>
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
							<li class="active"><a href="${base}/user/setting/index">基本信息</a>
							</li>
							<li class="normal"><a href="${base}/user/setting/queryPass">修改密码</a>
							</li>
							<li class="normal"><a href="${base}/user/setting/queryFace">更换头像</a>
							</li>
						</ul>
					</div>
					<div class="ncu-form-style">
						<form method="post" id="profile_form" action="">
							<#--<input type="hidden" name="form_submit" value="ok" />-->
							<input type="hidden" name="memberId" id="memberId" value="${member.memberId}" />
							<dl>
								<dt>用户名称：</dt>
								<dd>${member.memberName}</dd>
							</dl>
							<dl>
								<dt>电子邮件：</dt>
								<dd>${member.memberEmail}</dd>
							</dl>
							<dl>
								<dt><em class="pngFix"></em>真实姓名：</dt>
								<dd>
									<p>
										<input type="text" class="text" maxlength="30"
											name="memberTruename" id="memberTruename" value="${member.memberTruename}" />
									</p>
									<label for="memberTruename" generated="true" class="error"></label>
								</dd>
							</dl>
							<dl>
								<dt>性别：</dt>
								<dd>
								      <#if member.memberId??>
								        <label><input type="radio" name="memberSex" value="3" <#if member.memberSex??&&member.memberSex==3>checked</#if>/>保密</label> 
										<label><input type="radio" name="memberSex" value="2" <#if member.memberSex??&&member.memberSex==2>checked</#if>/> 女</label>
										<label><input type="radio" name="memberSex" value="1" <#if member.memberSex??&&member.memberSex==1>checked</#if>/> 男</label>
								      <#else>
								        <label><input type="radio" name="memberSex" value="3" checked="checked"/>保密</label> 
										<label><input type="radio" name="memberSex" value="2"/> 女</label>
										<label><input type="radio" name="memberSex" value="1"/> 男</label>
								      </#if>
								</dd>
							</dl>
						    <dl>
								<dt>生日：</dt>
								<dd>
									<input onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" class="txt Wdate" type="text" value="<#if member.memberBirthdaystr??>${member.memberBirthdaystr?string("yyyy-MM-dd")}</#if>" id="memberBirthday" name="Birthday" style="width: 151px;height: 18px;"/>
								</dd>
							</dl>
							<dl>
                             <dt class="required">所在地区：</dt>
       						 <dd>
							    <span id="spanarea">
								<select name="memberProvinceid" class="select" id="area" check="needCheck"> 
									<option value="">请选择</option>
									<#if areas??> 
										<#list areas as str>
											<option value="${str.areaId}">${str.areaName}</option>
										</#list> 
									</#if>
								</select> 
								</span>&nbsp;&nbsp;	
								<span id="spancity"></span>
								<span class="form-tips" style="color: red" name="check" id="checkedarea"></span> 
								<input id="memberProvinceid" type="hidden" name="Provinceid" value="">
								<input id="city_id" type="hidden" name="memberCityid"  value="">
								<input id="area_id" class="area_ids" type="hidden" name="memberAreaid" value="">
								<input id="area_info" class="area_names" type="hidden" name="memberAreainfo" value="">
							  </dd>
     						</dl>
							<dl>
								<dt><em class="pngFix"></em>QQ：</dt>
								<dd>
									<p>
										<input type="text" class="text" maxlength="30" name="memberQq" id="memberQq" value="${member.memberQq}" />
									</p>
									<label for="memberQq" generated="true" class="error"></label>
								</dd>
							</dl>
							<dl>
								<dt>阿里旺旺：</dt>
								<dd>
									<p>
										<input name="memberWw" type="text" class="text" maxlength="50" id="memberWw" value="${member.memberWw}" />
									</p>
									<label for="memberWw" generated="true" class="error"></label>
								</dd>
							</dl>
							<dl class="bottom">
								<dt>&nbsp;</dt>
								<dd>
								    <input type="button" id="submitBtn" name="Submit" value="保存修改" class="submitsubmit fl" title="立即注册" />
									<!-- <input type="submit" class="submit" value="保存修改" /> -->
								</dd>
							</dl>
						</form>
					</div>
				</div>
				<script type="text/javascript" src="${base}/res/js/common_select.js"
					charset="utf-8"></script>
				<script type="text/javascript">
					//注册表单验证
					$(function() {
					    //regionInit("region");
						//表单提交
				        $("#submitBtn").click(function(){
					       if($("#profile_form").valid()){
					          // $("#profile_form").submit();
			                //   $("#submitBtn").attr("disabled",true);
					             $.ajax({
							         type: "post",
							         url: '${base}/user/setting/updateMember',
							         data: $("#profile_form").serialize(),
							         dataType: "json",
									 async:false,
									 success:function(data) {
									 	if(data.success){
											layer.msg('设置成功', {icon: 1}, setTimeout("window.location = '${base}/user/setting/index'" , 1000));
										  }else{
					                         layer.msg('设置失败', {icon: 2});
										  }
									  } 
							 	 });
							   }	  
				            });
				            
				            //地区初始化
						if('${member.memberCityid}'!=null&&'${member.memberCityid}'!=''){
						  init_area('${member.memberProvinceid}','${member.memberCityid}');
						}
						
						$('#profile_form').validate(
								{
									errorPlacement: function(error, element){
						            error.appendTo(element.parents("dd").find('label:last'));
						           },
									rules : {
										memberTruename : {
											minlength : 2,
											maxlength : 20
										},
										memberQq : {
										    number:true,
											minlength : 5,
											maxlength : 11
										}
									},
									messages : {
										memberTruename : {
											minlength : '姓名长度大于等于2位小于等于20位',
											maxlength : '姓名长度大于等于2位小于等于20位'
										},
										memberQq : {
										    number:'请填入正确的QQ号码',
											minlength : '请填入正确的QQ号码',
											maxlength : '请填入正确的QQ号码'
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
