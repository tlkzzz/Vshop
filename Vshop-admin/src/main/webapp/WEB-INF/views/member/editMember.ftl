<@layout.head>
<script type="text/javascript" src="${base}/res/js/jquery-ui/jquery.ui.js"></script>
<script type="text/javascript" src="${base}/res/js/jquery-ui/i18n/zh-CN.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="${base}/res/js/jquery-ui/themes/ui-lightness/jquery.ui.css"  />
</@layout.head>
<@layout.body>
<div class="page">
    <div class="fixed-bar">
        <div class="item-title">
            <h3>会员管理</h3>
            <ul class="tab-base">
                <li><a href="${base}/member/list" ><span>管理</span></a></li>
                <li><a href="${base}/member/add" ><span>新增</span></a></li>
                <li><a href="javascript:void(0);" class="current"><span>编辑</span></a></li>
            </ul>
        </div>
    </div>
    <div class="fixed-empty"></div>
    <form id="user_form" enctype="multipart/form-data" method="post" action="${base}/member/saveOrUpdate">
        <input type="hidden" name="memberId" value="${member.memberId}"/>
        <input type="hidden" name="memberName" value="${member.memberName}"/>
        <table class="table tb-type2">
            <tbody>
            <tr class="noborder">
                <td colspan="2" class="required"><label>会员:</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform">${member.memberName}</td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required"><label for="memberPasswd">密码:</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type="text" id="memberPasswd" name="memberPasswd" class="txt"></td>
                <td class="vatop tips">留空表示不修改密码</td>
            </tr>
            <tr>
                <td colspan="2" class="required"><label class="validation" for="memberEmail">电子邮箱:</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform">
                    <input type="text" value="${member.memberEmail}" id="memberEmail" name="memberEmail" class="txt">
                    <input type="hidden" value="${member.memberEmail}" id="memberEmailt" class="txt">
                </td>
                <td class="vatop tips">电子邮箱</td>
            </tr>
            <tr>
                <td colspan="2" class="required"><label for="memberTruename">真实姓名:</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type="text" value="${member.memberTruename}" id="memberTruename" name="memberTruename" class="txt"></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required"><label>性别:</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><ul>
                    <li>
                        <input type="radio" checked="checked" value="0" <#if member.memberSex == 0>checked="checked"</#if> name="memberSex" id="memberSex0">
                        <label for="memberSex0">保密</label>
                    </li>
                    <li>
                        <input type="radio"  value="1" name="memberSex" <#if member.memberSex == 1>checked="checked"</#if> id="memberSex1">
                        <label for="memberSex1">男</label>
                    </li>
                    <li>
                        <input type="radio"  value="2" name="memberSex" <#if member.memberSex == 2>checked="checked"</#if> id="memberSex2">
                        <label for="memberSex2">女</label>
                    </li>
                </ul></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required"><label class="validation">选择会员等级：</label> </td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform">
                    <select class="select" name="memberGradeId" style="height:25px;">
	                    <#if mbmergradelsit??>
	                        <#list mbmergradelsit as mbmergrade>
	                            <option value="${mbmergrade.gradeId}" <#if member.memberGradeId == mbmergrade.gradeId>selected="selected" </#if>>${mbmergrade.gradeName}</option>
	                        </#list>
	                    </#if>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2" class="required"><label class="memberQq">QQ:</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type="text" value="${member.memberQq}" id="memberQq" name="memberQq" class="txt"></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required"><label class="memberWw">阿里旺旺:</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type="text" value="${member.memberWw}" id="memberWw" name="memberWw" class="txt"></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required"><label>头像:</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform">
			<span class="type-file-show">
			<img class="show_image" src="${base}/res/images/preview.png">
			<div class="type-file-preview" style="display: none;"><img src="${imgServer}${member.memberAvatar}" id="view_img"></div>
			</span>
            <span class="type-file-box">
              <input type='text' name='memberAvatar' id='memberAvatar' class='type-file-text' />
              <input type='button' name='button' id='button' value='' class='type-file-button' />
              <input name="imageFile" type="file" class="type-file-file" id="_pic" size="30" hidefocus="true" />
            </span>
                </td>
                <td class="vatop tips">支持格式gif,jpg,jpeg,png</td>
            </tr>
            <tr>
                <td colspan="2" class="required"><label>举报商品:</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform onoff"><label for="inform_allow1" class="cb-enable <#if member.informAllow == 1>selected</#if>" ><span>允许</span></label>
                    <label for="inform_allow2" class="cb-disable <#if member.informAllow == 0>selected</#if>" ><span>禁止</span></label>
                    <input id="inform_allow1" name="informAllow" <#if member.informAllow == 1>checked="checked"</#if>  value="1" type="radio">
                    <input id="inform_allow2" name="informAllow" <#if member.informAllow == 0>checked="checked"</#if>  value="0" type="radio"></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required"><label>允许购买商品:</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform onoff">
                    <label for="isbuy_1" class="cb-enable <#if member.isBuy == 1>selected</#if>" ><span>允许</span></label>
                    <label for="isbuy_2" class="cb-disable <#if member.isBuy == 0>selected</#if>" ><span>禁止</span></label>
                    <input id="isbuy_1" name="isBuy" <#if member.isBuy == 1>checked="checked"</#if> value="1" type="radio">
                    <input id="isbuy_2" name="isBuy" <#if member.isBuy == 0>checked="checked"</#if> value="0" type="radio"></td>
                <td class="vatop tips">如果禁止该项则会员不能在前台进行下单操作</td>
            </tr>
            <tr>
                <td colspan="2" class="required"><label>允许发表言论:</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform onoff">
                    <label for="allowtalk_1" class="cb-enable <#if member.isAllowtalk == 1>selected</#if>" ><span>允许</span></label>
                    <label for="allowtalk_2" class="cb-disable <#if member.isAllowtalk == 0>selected</#if>" ><span>禁止</span></label>
                    <input id="allowtalk_1" name="isAllowtalk" <#if member.isAllowtalk == 1>checked="checked"</#if> value="1" type="radio">
                    <input id="allowtalk_2" name="isAllowtalk" <#if member.isAllowtalk == 0>checked="checked"</#if> value="0" type="radio"></td>
                <td class="vatop tips">如果禁止该项则会员不能发表咨询和发送站内信</td>
            </tr>
            <tr>
                <td colspan="2" class="required"><label>允许登录:</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform onoff">
                    <label for="memberstate_1" class="cb-enable <#if member.memberState == 1>selected</#if>" ><span>允许</span></label>
                    <label for="memberstate_2" class="cb-disable <#if member.memberState == 0>selected</#if>" ><span>禁止</span></label>
                    <input id="memberstate_1" name="memberState" <#if member.memberState == 1>checked="checked"</#if> value="1" type="radio">
                    <input id="memberstate_2" name="memberState" <#if member.memberState == 0>checked="checked"</#if> value="0" type="radio"></td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required"><label>积分:</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform">积分&nbsp;<strong class="red">${member.memberPoints}</strong>&nbsp;</td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required"><label>可用预存款:</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform">可用&nbsp;<strong class="red">${member.availablePredeposit}</strong>&nbsp;元</td>
                <td class="vatop tips"></td>
            </tr>
            <tr>
                <td colspan="2" class="required"><label>冻结预存款:</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform">冻结&nbsp;<strong class="red">${member.freezePredeposit}</strong>&nbsp;元</td>
                <td class="vatop tips"></td>
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
<script type="text/javascript">

    $(function(){
        $('input[class="type-file-file"]').change(uploadChange);
        function uploadChange(){
            var filepatd=$(this).val();
            var extStart=filepatd.lastIndexOf(".");
            var ext=filepatd.substring(extStart,filepatd.length).toUpperCase();
            if(ext!=".PNG"&&ext!=".GIF"&&ext!=".JPG"&&ext!=".JPEG"){
                alert("file type error");
                $(this).attr('value','');
                return false;
            }else{
                $("#memberAvatar").val($(this).val());
            }
            if ($(this).val() == '') return false;
        }
        $("#submitBtn").click(function(){
            if($("#user_form").valid()){
                $("#user_form").submit();
            }
        });
        $('#user_form').validate({
            errorPlacement: function(error, element){
                error.appendTo(element.parent().parent().prev().find('td:first'));
            },
            rules : {
                memberPasswd: {
                    maxlength: 20,
                    minlength: 6
                },
                memberEmail   : {
                    required : true,
                    email : true,
                    remote   : {
                        url : APP_BASE+'/member/checkEmail',
                        type:'post',
                        data:{
                            memberEmail : function(){
                                return $('#memberEmail').val();
                            },
                            memberEmailt : function(){
                                return $('#memberEmailt').val();
                            }
                        }
                    }
                }
            },
            messages : {
                memberPasswd : {
                    maxlength: '密码长度应在6-20个字符之间',
                    minlength: '密码长度应在6-20个字符之间'
                },
                memberEmail  : {
                    required : '电子邮箱不能为空',
                    email   : '请您填写有效的电子邮箱',
                    remote : '邮件地址有重复，请您换一个'
                }
            }
        });
    });
</script>
</@layout.body>