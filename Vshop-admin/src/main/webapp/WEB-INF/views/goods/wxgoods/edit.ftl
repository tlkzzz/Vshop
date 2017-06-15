<@layout.head>
<script type="text/javascript" src="${base}/res/js/jquery-ui/jquery.ui.js"></script>
<script type="text/javascript" src="${base}/res/js/jquery-ui/i18n/zh-CN.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="${base}/res/js/jquery-ui/themes/ui-lightness/jquery.ui.css"  />
<script type="text/javascript" src="${base}/res/js/admincp.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/common_select.js" charset="utf-8"></script>
</@layout.head>
<@layout.body>
<div class="page">
    <div class="fixed-bar">
        <div class="item-title">
            <h3>红包</h3>
            <ul class="tab-base">
                <li><a href="${base}/goods/wxgoods/list"><span>管理</span></a></li>
                <li><a href="${base}/goods/wxgoods/forward"><span>新增</span></a></li>
                <li><a href="JavaScript:void(0);" class="current"><span>编辑</span></a></li>
            </ul>
        </div>
    </div>
    <div class="fixed-empty"></div>
    <form id="brand_form" enctype="multipart/form-data" method="post" name="form1" action="${base}/goods/wxgoods/edit">
        <input type="hidden" name="form_submit" value="ok" />
        <input type="hidden" name="id" value="${w.id}" />
        <table class="table tb-type2 nobdb">
            <tbody>
            <tr class="noborder">
                <td colspan="2" class="required"><label class="validation">商品名称:</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type="text" value="${w.name}" id="name" name="name"  class="txt"></td>
                <td class="vatop tips">请输入2~8个字符</td>
            </tr>
              <tr class="noborder">
                <td colspan="2" class="required"><label class="validation">保质期:</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type="text" value="${w.xsqy}" name="xsqy" id="xsqy"  class="txt"></td>
                <td class="vatop tips">请输入2~8个字符</td>
            </tr>
            <tr class="noborder">
                <td colspan="2" class="required"><label class="validation">生产产地:</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type="text" value="${w.scs}" id="scs" name="scs"  class="txt"></td>
                <td class="vatop tips">请输入2~8个字符</td>
            </tr>
             <tr class="noborder">
                <td colspan="2" class="required"><label class="validation">生产日期:</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type="text" id="query_start_time" value="${w.scsj}" name="scsj"  class="txt date"></td>
                <td class="vatop tips"></td>
            </tr>
              <tr class="noborder">
                <td colspan="2" class="required"><label class="validation">红包金额:</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type="number" value="${w.je}" id="je" name="je"  class="txt"></td>
                <td class="vatop tips">请输入正整数0或者100-20000注：100=1元人民币 最低100</td>
            </tr>
             <tr class="noborder">
                <td colspan="2" class="required"><label class="validation">红包提供方名称:</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type="text" value="${w.tgfmc}" name="tgfmc"  class="txt"></td>
                <td class="vatop tips">请输入2~8个字符</td>
            </tr>
             <tr class="noborder">
                <td colspan="2" class="required"><label class="validation">红包发送者名称:</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type="text" value="${w.fszmc}" name="fszmc"  class="txt"></td>
                <td class="vatop tips">请输入2~8个字符</td>
            </tr>
             <tr class="noborder">
                <td colspan="2" class="required"><label class="validation">活动名称:</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type="text" value="${w.hdmc}" name="hdmc"  class="txt"></td>
                <td class="vatop tips">请输入2~8个字符</td>
            </tr>
            <tr class="noborder">
                <td colspan="2" class="required"><label class="validation">备注:</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type="text" value="${w.bz}" name="bz"  class="txt"></td>
                <td class="vatop tips">请输入2~200个字符</td>
            </tr>
             <tr class="noborder">
                <td colspan="2" class="required"><label class="validation">红包祝福语:</label></td>
            </tr>
            <tr class="noborder">
                <td class="vatop rowform"><input type="text" value="${w.hbzfy}" name="hbzfy"  class="txt"></td>
                <td class="vatop tips">请输入2~8个字符</td>
            </tr>
          
            </tbody>
            <tfoot>
            <tr class="tfoot">
                <td colspan="2" ><a href="JavaScript:void(0);" class="btn" id="submitBtn"><span>提交</span></a></td>
            </tr>
            </tfoot>
        </table>
       
       
       
    </form>
</div>
<script>
   
    //按钮先执行验证再提交表单
    $(function(){$("#submitBtn").click(function(){
        if($("#brand_form").valid()){
            $("#brand_form").submit();
        }
    });
    });
    //
    $(document).ready(function(){
        $('#query_start_time').datepicker({dateFormat: 'yy-mm-dd'});
        $("#brand_form").validate({
            errorPlacement: function(error, element){
                error.appendTo(element.parent().parent().prev().find('td:first'));
            },
            success: function(label){
                label.addClass('valid');
            },
            rules : {
                name : {
                    required : true,
                    minlength:2,
                 //   maxlength:8
                },
                 xsqy : {
                    required : true,
                    minlength:2,
                  //  maxlength:8  
                },
                  scs : {
                    required : true,
                    minlength:2,
                  //  maxlength:8  
                },
                 tgfmc : {
                    required : true,
                    minlength:2,
                  //  maxlength:8  
                },
                 fszmc : {
                    required : true,
                    minlength:2,
                  //  maxlength:8  
                },
                  scsj : {
                    required : true,
                    //minlength:2,
                  //  maxlength:8  
                },
                je  :{
                required : true,
                digits:true,
                range:[0,20000]
                },
                  hdmc : {
                    required : true,
                    minlength:2,
                  //  maxlength:8  
                },
                 bz : {
                    required : true,
                    minlength:2,
                  //  maxlength:200  
                }, hbzfy : {
                    required : true,
                    minlength:2,
                  //  maxlength:8  
                },
                
                
                
               
            },
            messages : {
                 name : {
                    required : '品牌名称不能为空',
                    minlength:'最少2个字符',
                 //   maxlength：'最多8个字符'
                },
                  xsqy : {
                    required : '保质期不能为空',
                    minlength:'最少2个字符',
                  //  maxlength：'最多8个字符'
                },
                  scs : {
                    required : '生产产地不能为空',
                    minlength:'最少2个字符',
                  //  maxlength：'最多8个字符'
                },
                  scsj : {
                    required : '请选择生产日期',
                   // minlength:2,
                  //  maxlength:8  
                },
                 je  :{
                  required : '金额不能为空',
              	 digits:'必须输入整数',
              	 range:'金额只能介于0-2000'
                }, 
                hdmc : {
                    required :'活动名称不能为空',
                     minlength:'最少2个字符',
                  //  maxlength：'最多8个字符'
                },
                  tgfmc : {
                    required :'红包提供方名称不能为空',
                     minlength:'最少2个字符',
                  //  maxlength：'最多8个字符'
                },
                  fszmc : {
                    required :'红包发送者名称不能为空',
                     minlength:'最少2个字符',
                  //  maxlength：'最多8个字符'
                },
                bz : {
                    required :'备注不能为空',
                     minlength:'最少2个字符',
                  //  maxlength：'最多8个字符'
                },
                 hbzfy : {
                    required :'红包祝福语不能为空',
                     minlength:'最少2个字符',
                  //  maxlength：'最多8个字符'
                },
               
               
               
            }
        });
    });
    gcategoryInit('gcategory');
</script>
</@layout.body>