<div class="layout" style="width: 100%;">
<!-- css -->
<link href="${base}/res/css/base.css" rel="stylesheet" type="text/css">
<link href="${base}/res/css/member.css" rel="stylesheet" type="text/css">
<link href="${base}/res/css/member_store.css" rel="stylesheet" type="text/css">
<link href="${base}/res/css/member_user.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${base}/res/js/jquery.js"></script>
<script type="text/javascript" src="${base}/res/js/jquery-ui/jquery.ui.js"></script>
<script src="${base}/res/js/jquery.ajaxContent.pack.js" type="text/javascript"></script>
<script type="text/javascript" src="${base}/res/js/jquery-ui/i18n/zh-CN.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/ajaxfileupload/ajaxfileupload.js" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8" src="${base}/res/js/store_goods.js"></script>
<script type="text/javascript" charset="utf-8" src="${base}/res/js/jquery.validation.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${base}/res/js/area.js"></script>
<script src="${base}/res/js/layer/layer.js"></script>
<link rel="stylesheet" href="${base}/res/js/kindeditor/themes/default/default.css" />
<script charset="utf-8" src="${base}/res/js/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="${base}/res/js/kindeditor/plugins/code/prettify.js"></script>
<script charset="utf-8" src="${base}/res/js/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript" src="${base}/res/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
    $(function (){
        KindEditor.ready(function(K) {
            var editor1 = K.create("textarea[name='pointsGoodsBody']", {
                uploadJson : APP_BASE+'/kind/upload',
                afterCreate : function() {
                    var self = this;
                },
                afterBlur: function(){this.sync();}
            });
        });
    });
    
</script>
<style>
.ui-timepicker-div .ui-widget-header { margin-bottom: 8px; }
.ui-timepicker-div dl { text-align: left; }
.ui-timepicker-div dl dt { float: left; clear:left; padding: 0 0 0 5px; }
.ui-timepicker-div dl dd { margin: 0 10px 10px 45%; }
.ui-timepicker-div td { font-size: 90%; }
.ui-tpicker-grid-label { background: none; border: none; margin: 0; padding: 0; }
.ui-timepicker-rtl{ direction: rtl; }
.ui-timepicker-rtl dl { text-align: right; padding: 0 5px 0 0; }
.ui-timepicker-rtl dl dt{ float: right; clear: right; }
.ui-timepicker-rtl dl dd { margin: 0 45% 10px 10px; }
</style>
<script type="text/javascript">
APP_BASE = '${base}';
</script>
<link rel="stylesheet" type="text/css" href="${base}/res/js/jquery-ui/themes/ui-lightness/jquery.ui.css"  />
<style>
.box_arr .table_btn { width: 222px;}
.box_arr .table_btn a { float: left;}
.box_arr .table_btn a.disable_spec { background: url(http://192.168.1.220/templates/default/images/member/btn.gif) repeat 0 -1018px; float: right;}
.dialog_body { border: 0px;}
.add_spec .add_link { color: #919191;}
.add_spec .add_link:hover { color: red;}
.add_spec h2 { padding-left: 10px;}
.f_l { float: left; line-height: 24px;}
.mls_id { width: 0; filter: alpha(opacity=0); opacity: 0;}
.noSelect { color: #B9B9B9 !important;}
</style>

<script type="text/javascript">
var SITE_URL = "${base}";
var DEFAULT_points_goods_IMAGE = "upload/common/default_points_goods_image.gif_tiny.gif";

$(function(){
     $('#points_goods_form').validate({
        errorPlacement: function(error, element){
            $(element).next('.field_notice').hide();
            $(element).after(error);
        },
        rules : {
        	pointsGoodsName : {
                required	: true,
                minlength 	: 3,
                maxlength	: 50
            },
            pointsNums : {	
				required   : true,
                number     : true,
                min        : 1,
                max		   : 1000000
            },
            pointsGoodsStorage  : {
				required	: true,
                digits		: true,
                min			: 1,
                max			: 1000000
            },
        },
        messages : {
        	pointsGoodsName  : {
                required	: '商品名称不能为空',
                minlength 	: '商品标题名称长度至少3个字符，最长50个汉字',
                maxlength 	: '商品标题名称长度至少3个字符，最长50个汉字'
            },
            pointsNums : {
				required: '消耗积分不能为空',
                number     : '消耗积分只能是数字',
                min		   : '消耗积分必须是0.01~1000000之间的数字',
                max		   : '消耗积分必须是0.01~1000000之间的数字'
            },
            pointsGoodsStorage : {
				required: '商品库存不能为空',
                digits  : '库存只能填写数字',
                min		: '库存数量必须为1~${goodsSpec.specGoodsStorage}之间的整数',
                max		: '库存数量必须为1~${goodsSpec.specGoodsStorage}之间的整数'
            }
        }
    });
});
</script>
<div class="wp" style="width: 98%;">
  <div class="item-publish">
    <form method="post" id="points_goods_form" action="javascript:void(0);">
      <div class="goods-attribute" style="width: 98%;">
        <dl class="tit">
          <h3>积分商品信息修改</h3>
        </dl>
        <dl>
          <dt></dt>
        </dl>
        <dl>
          <dt class="required"><span class="red">*</span>积分商品名称：</dt>
          <dd>
            <p>
              <input name="pointsGoodsName" type="text" class="text w400" title="" value="${pointsGoods.pointsGoodsName}"/>
            <p>
            <p class="hint">商品标题名称长度至少3个字符，最长50个汉字</p>
          </dd>
        </dl>
		<dl>
          <dt nc_type="no_spec">积分商品副标题：</dt>
          <dd nc_type="no_spec">
            <p>
              <input name="pointsGoodsSubtitle" type="text"  class="text w500" value="${pointsGoods.pointsGoodsSubtitle}" />
            </p>
            <p class="hint">商品副标题做商品特殊说明，位于详情页商品名称下面</p>
          </dd>
        </dl>
        
        <dl>
          <dt nc_type="no_spec"><span class="red">*</span>消耗积分：</dt>
          <dd nc_type="no_spec">
            <p>
              <input name="pointsNums" type="text"  class="text" value="${pointsGoods.pointsNums}" />
            </p>
          </dd>
        </dl>
        
       <dl>
          <dt nc_type="no_spec"><span class="red">*</span>商品库存：</dt>
          <dd  nc_type="no_spec">
            <p>
              <input name="pointsGoodsStorage" type="text"  class="text" value="${pointsGoods.pointsGoodsStorage}"/>
            </p>
            <p class="hint">库存必须小于</p>
          </dd>
       </dl>
       
               
        <dl>
          <dt nc_type="no_spec">每个会员最多可兑换的个数：</dt>
          <dd nc_type="no_spec">
            <p>
              <input name="exchangeCount" type="text"  class="text" value="${pointsGoods.exchangeCount}"/>
            </p>
          </dd>
        </dl>
               
        <dl>
          <dt nc_type="no_spec">会员等级要求：</dt>
          <dd nc_type="no_spec">
            <p>
              <input name="memberGrade" type="text"  class="text" value=""/>
            </p>
          </dd>
        </dl>
       
        <dl>
          <dt>商品描述：</dt>
          <dd>
            <p>
              <textarea id="points_goods_body" name="pointsGoodsBody" style="width:100%;height:400px;visibility:hidden;" value="${pointsGoods.pointsGoodsBody}"></textarea>
	            </p>
          </dd>
        </dl>
        <!--transport info begin-->
        <dl>
          <dt>运费：</dt>
          <dd>
            <p style="float:left;  clear:both; margin-top:8px;">
              <input type="radio" value="1" checked="checked"  name="pointsGoodsTransfeeCharge" id="whops_seller">
              <label for="whops_seller">商家承担运费</label>
            </p>
          </dd>
        </dl>
        <!--transport info end-->
        <dl class="tit">
          <h3>其他信息</h3>
        </dl>
        <dl>
          <dt>兑换时间：</dt>
          <dd>
            <p class="mt5">
              <label>
                <input name="pointsGoodsShow" value="1" type="radio" checked="checked" />
                立即
              </label>
            </p>
            <p class="mt5">
              <label>
                <input name="pointsGoodsShow" value="2" type="radio" />
                指定开始时间
              </label>
              <input onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" disabled="disabled" class="txt" type="text" id="pointsGoodsStarttime" name="pointsGoodsStarttime" style="width: 250px;height: 18px;"/>
            </p>
            <p class="mt5">
                结束时间
                <input onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" class="txt" type="text" id="" name="pointsGoodsEndtime" style="width: 250px;height: 18px;"/>
            </p>    
      </dd>
        </dl>
        <div class="clear">
          <p>&nbsp;</p>
          <p>&nbsp;</p>
        </div>
        <div class="ncu-form-style tc mb30">
          <input type="button" class="submit" value="提交" id="sub"/>
        </div>
      </div>
    </form>
  </div>
</div>
</div>
<script type="text/javascript">
//点击提交的时候
$(function(){
	//点击指定开始时间触发事件
	$("[name=pointsGoodsShow]").click(function(){
		if($(this).val() == "2"){
			$("#pointsGoodsStarttime").attr("disabled", null);
		}else{
			$("#pointsGoodsStarttime").attr("disabled", "disabled");
		}
	});
	
	$("#sub").click(function(){
		if($('#points_goods_form').valid()){
			var show = $("[name=pointsGoodsShow]:checked").val();//兑换时间
			var args = {
					"pointsGoodsId":"${pointsGoods.pointsGoodsId}",
					"goodsSpecId":"${pointsGoods.goodsSpecId}",
					"pointsGoodsName":$("[name=pointsGoodsName]").val(),
					"pointsGoodsSubtitle":$("[name=pointsGoodsSubtitle]").val(),//商品副标题
					"pointsNums":$("[name=pointsNums]").val(),//消耗积分
					"pointsGoodsStorage":$("[name=pointsGoodsStorage]").val(),//库存
					"exchangeCount":$("[name=exchangeCount]").val(),//每个会员最多可兑换的个数
					"memberGrade":$("[name=memberGrade]").val(),//会员等级要求
					"pointsGoodsBody":$("[name=pointsGoodsBody]").val(),//商品详细描述
					"pointsGoodsTransfeeCharge": 1,//商家承担运费
					"pointsGoodsShow": show,
					"pointsGoodsStarttimeStr": $("[name=pointsGoodsStarttime]").val(),//开始时间
					"pointsGoodsEndtimeStr": $("[name=pointsGoodsEndtime]").val()//结束时间
			};
			
			var url = "${base}/points/pro/update";
	        //加载进度条
	        layer.load(2, {
	               shade: [0.2,'#999999'] //0.1透明度的白色背景
	        });
			 $.post(url, args, function(data){
				//保存成功
				if(data.message == "1"){
					layer.msg("修改成功" , {icon:1});
					setTimeout("ok("+show+")",1500); 
				}else{
					//失败
					layer.msg("修改失败" , {icon:2});
				}
			});
		}else{
			//返回信息错误的地方
			$("html,body").animate({scrollTop:$(".error:visible").offset().top},1000);
		}
	});
});

function ok(show){
	window.location.href='${base}/points/all/list';
}
</script>