<@layout.head>
<link rel="stylesheet" type="text/css" href="${base}/res/css/easyui/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${base}/res/css/easyui/icon.css">
<script type="text/javascript" src="${base}/res/js/common/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${base}/res/js/jquery-ui/jquery.ui.js"></script>
<script type="text/javascript" src="${base}/res/js/jquery-ui/i18n/zh-CN.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="${base}/res/js/jquery-ui/themes/ui-lightness/jquery.ui.css"  />
<script type="text/javascript" src="${base}/res/js/admincp.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/common_select.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/ajaxfileupload/ajaxfileupload.js"></script>
<script type="text/javascript" charset="utf-8" src="${base}/res/js/jquery.validation.min.js"></script>
<script type="text/javascript" src="${base}/res/js/My97DatePicker/WdatePicker.js"></script>
</@layout.head>
<@layout.body>
<div class="page">
    <div class="fixed-bar">
        <div class="item-title">
            <h3>优惠券新增</h3>
            <ul class="tab-base">
                <li><a href="${base}/coupon/list"><span>列表</span></a></li>
                <li><a href="javascript:void(0);" class="current"><span>新增</span></a></li>
            </ul>
        </div>
    </div>
    <div class="fixed-empty"></div>
    <form id="coupon_form" method="post" action="${base}/coupon/saveOrUpdate">
        <input type="hidden" name="couponId" value="${coupon.couponId}"/>
        <table class="table tb-type2">
            <tbody>
	            <tr class="noborder">
	                <td class="required" colspan="2"><label class="validation" for=couponTitle>优惠券名称：</label></td>
	            </tr>
	            <tr class="noborder">
	           		<td class="vatop rowform"><input type="text" class="txt" name="couponTitle" id="couponTitle" value="${coupon.couponTitle}" /></td>
	                <td class="vatop tips">请填写优惠券名称。</td>
	            </tr>
	            
	            <tr>
	                <td class="required" colspan="2"><label class="validation" for="couponLimit">限制金额：</label></td>
	            </tr>
	            <tr class="noborder">
	           	 	<td class="vatop rowform"><input type="text" class="txt w50 mr5" name="couponLimit" id="couponLimit" value="${coupon.couponLimit}" /></td>
	                <td class="vatop tips">请填写限制金额。</td>
	            </tr>
	            
	            <tr>
	                <td class="required" colspan="2"><label class="validation" for="couponPrice">优惠金额：</label></td>
	            </tr>
	            <tr class="noborder">
	            	<td class="vatop rowform"><input type="text" class="txt w50 mr5" name="couponPrice" id="couponPrice" value="${coupon.couponPrice}" /></td>
	                <td class="vatop tips">请填写优惠金额。</td>
	            </tr>
	            
	           
	            <tr>
	                <td class="required" colspan="2"><label class="validation" for="couponClassId">优惠券分类：</label></td>
	            </tr>
	            <tr class="noborder">
	            	<td class="vatop rowform">
		            	<select name="couponClassId">
							<#list classlist as class>
								<option value="${class.classId}">${class.className}</option>
							</#list>
						</select>
	            	</td>
	                <td class="vatop tips">请选择优惠券分类。</td>
	            </tr>
	            
	            <tr>
	                <td class="required" colspan="2"><label class="validation" for="sponsorId">选择赞助商：</label></td>
	            </tr>
	            <tr class="noborder">
	            	<td class="vatop rowform">
		            	<select name="sponsorId">
							<#list sponsorList as sponsor>
								<option value="${sponsor.id}">${sponsor.name}</option>
							</#list>
						</select>
	            	</td>
	                <td class="vatop tips">请选择赞助商。</td>
	            </tr>
	            
	            <tr class="noborder">
	                <td class="required" colspan="2"><label class="validation" for="goodsNames">绑定商品：</label></td>
	            </tr>
	            <tr class="noborder">
	           		<td class="vatop rowform">
	           			<input type="text" value="" id="goodsNames" class="txt" readonly="readonly">
            			<input type="hidden" name="goodsIds" id="goodsIds" value="" />
	           		</td>
	                <td class="vatop tips"><a href="JavaScript:void(0);" class="btn" id="optBtn" title="请选择待绑定的商品。" onclick="optionGoods();"><span>选择</span></a></td>
	            </tr>
	            
	            <tr>
	                <td class="required" colspan="2"><label class="validation" for="couponPic">优惠券图片：</label></td>
	            </tr>
	            <tr class="noborder">
	           		<td class="vatop rowform">
	           		<#if coupon.couponPic ?? && coupon.couponPic !=''>
		            	<input type="hidden" id="couponPic" name="couponPic" value="${imgServer}${coupon.couponPic}" />
		            <#else>
		            	<input type="hidden" id="couponPic" name="couponPic" value="" />
		            </#if>
		            <p>
			            <span class="sign">
			            	 <#if coupon.couponPic ?? && coupon.couponPic !=''>
				             	<img src="${imgServer}${coupon.couponPic}"   id="storeCoupon" nc_type="logo1" width="88" height="44"/>
				             <#else>
				                <img src="${base}/images/member/default.gif" id="storeCoupon" nc_type="logo1" width="88" height="44"/>
				             </#if>
			           		 
			            </span>
		            </p>
		            <p><input type="file" hidefocus="true" nc_type="logo" name="files" id="coupon_pic" onChange="ajaxFileUploads('coupon_pic','storeCoupon','couponPic');"></p>
		            <span class="errorMsg" id="vat_brandPic"></span>
	           		
	           		</td>
	                <td class="vatop tips">请上传优惠券图片</td>
	            </tr>
	            
	            <tr>
	                <td class="required" colspan="2"><label class="validation" for="couponStartDate">开始日期：</label></td>
	            </tr>
	            <tr class="noborder">
	            	<td class="vatop rowform">
	            		<input class="text hasDatepicker" name="couponStartDate" 
						   onFocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d',maxDate:'#F{$dp.$D(\'couponEndDate\')}',alwaysUseStartDate:true})"
						   id="couponStartDate" readonly="readonly" type="text" value="${coupon.startTimeStr}"> 
	            	</td>
	                <td class="vatop tips">请选择开始日期。</td>
	            </tr>
	            
	            <tr>
	                <td class="required" colspan="2"><label class="validation" for="couponEndDate">截止日期：</label></td>
	            </tr>
	            <tr class="noborder">
	            	<td class="vatop rowform">
		            	<input class="text hasDatepicker" name="couponEndDate" onFocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'couponStartDate\')}',alwaysUseStartDate:true})"
						id="couponEndDate" readonly="readonly" type="text" value="${coupon.endTimeStr}">
	            	</td>
	                <td class="vatop tips">请选择开始日期。</td>
	            </tr>
	            

	            
	            
	            <tr>
	                <td class="required" colspan="2"><label class="validation" for="couponstorage">优惠券数量：</label></td>
	            </tr>
	            <tr class="noborder">
	            	<td class="vatop rowform">
	            		<input type="text" class="txt w50 mr5" name="couponstorage" id="couponstorage" value="${coupon.couponstorage}" >
	            	
	            	</td>
	                <td class="vatop tips">请填写优惠券数量。</td>
	            </tr>
	            
	            <tr>
	                <td  colspan="2">使用条件：</td>
	            </tr>
	            <tr class="noborder">
	            	<td class="vatop rowform">
	            		<textarea class="w300" name="couponDesc" id="couponDesc" rows="3">${coupon.couponDesc}</textarea>
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
    <div id="GoodsTableDialog" title="选择商品" style="padding:5px;">
		<table id="goodsTable"></table>
	</div>
</div>
<script type="text/javascript">
	function optionGoods(){
		$('#GoodsTableDialog').dialog({
		    width:700,
		    height:400,
			buttons:{
				"确定": function(){
					var goodsIds = [], goodsNames = [];
					_.each($('#goodsTable').datagrid('getSelections'), function(v){goodsIds.push(v.goodsId), goodsNames.push(v.goodsName)});
					!_.isEmpty(goodsIds) && $('#goodsIds').val(goodsIds.join(',')) && $('#goodsNames').val(goodsNames.join(','));
					$( this ).dialog( "close" );
				},
				"清除": function(){
					$('#goodsTable').datagrid('clearSelections');
				}
			},
			modal:true
		});
		$('#goodsTable').datagrid({
			width:650,
			height:'auto',
			nowrap: false, 
	        striped: true, 
	        border: true, 
	        collapsible:false,//是否可折叠的 
	        fit: true,//自动大小
	        remoteSort:false,  
	        idField:'goodsId',
			pagination:true,//分页控件 
			rownumbers:true,
		    url:'${base}/coupon/goods/list',
		    frozenColumns:[[{field:'ck', checkbox:true}]],
		    columns:[[
		        {field:'goodsName',title:'商品名称',width:200},
		        {field:'gcName',title:'商品类型',width:200, align:'left'},
		        {field:'brandName',title:'品牌名称',width:200, align:'left'}
		    ]]
		});
		var p = $('#goodsTable').datagrid('getPager'); 
	    $(p).pagination({ 
	        pageSize: 10,
	        pageList: [10,20,40],
	        beforePageText: '第',
	        afterPageText: '页    共 {pages} 页', 
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录' 
	        /* onBeforeRefresh:function(){
	            $(this).pagination('loading');
	            alert('before refresh');
	            $(this).pagination('loaded');
	        }  */
	    });  
	}




    $(function(){
    	
    	formValidate();
    	
        //按钮先执行验证再提交表单
        $("#submitBtn").click(function(){
        	
        	if($('#coupon_form').valid()){
            	 $("#coupon_form").submit();
        	}
        	
        	
        });
    });
    
    var formValidate = function(){
    	$('#coupon_form').validate({
    	    errorPlacement: function(error, element){
    	        $(element).next('.field_notice').hide();
    	        $(element).after(error);
    	    },
    	    rules : {
    	    	couponTitle : {
    	            required	: true,
    	            minlength 	: 3,
    	            maxlength	: 50
    	        },
    	        couponLimit : {	
    				required   : true,
    	            number     : true,
    	            min        : 0,
    	            digits     :true                
    	        },
    	        couponPrice : {
    	            required   : true,
    	            number     : true,
    	            min        : 0,
    	            digits     :true         
    	        },
    	        couponPic : {
    	            //required	: true
    	        },
    	        couponStartDate : {
    	            required	: true
    	        },
    	        couponEndDate : {
    	            required	: true
    	        },
    	        couponstorage : {
    	            required	: true,
    	            number      : true,
    	            min         : 0,
    	            digits     :true         
    	        }
    	        
    	    },
    	    messages : {
    	    	couponTitle  : {
    	            required	: '名称不能为空',
    	            minlength 	: '名称长度至少3个字符，最长50个汉字',
    	            maxlength 	: '名称长度至少3个字符，最长50个汉字'
    	        },
    	        couponLimit : {
    				required	: '限制金额不能为空',
    	            number  	: '限制金额只能是数字',
    	            min			: '限制金额最小值为0',
    	            digits      : '请输入整数'  
    	        },
    	        couponPrice  : {
    	        	required	: '优惠金额不能为空',
    	            number  	: '优惠金额只能是数字',
    	            min			: '优惠金额最小值为0',
    	            digits      : '请输入整数'  
    	        },
    	        couponPic  : {
    	           // required	: '优惠券图片不能为空'
    	        }, 
    	        couponStartDate  : {
    	            required	: '优惠券开始时间不能为空'
    	        },
    	        couponEndDate  : {
    	            required	: '优惠券截止时间不能为空'
    	         
    	        },
    	        couponstorage  : {
    	        	required	: '优惠券数量不能为空',
    	            number  	: '优惠券数量只能是数字',
    	            min			: '优惠券数量最小值为0',
    	            digits      : '请输入整数'  
    	        }
    	    }
    	});
    }
    
    
  //上传图片
    function ajaxFileUploads(myBlogImage,imgId,img){
        $.ajaxFileUpload({
            //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
            url: '${base}/coupon/fileUpload',
            secureuri:false,                      //是否启用安全提交,默认为false
            fileElementId:myBlogImage,           //文件选择框的id属性
            dataType:'json',                       //服务器返回的格式,可以是json或xml等
            fileSize:5120000,
    		allowType:'jpg,jpeg,png,JPG,JPEG,PNG',
            success:function(data, status){        //服务器响应成功时的处理函数
                if( true == data.success){     //0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述)
                   $("img[id='"+imgId+"']").attr("src", "${imgServer}"+data.result);
                   $("#"+img).val(data.result);
                }
            },
            error:function(data, status, e){ //服务器响应失败时的处理函数
            	 layer.msg('图片上传失败，请重试！！', {icon:2}); 
            }
        });
    }

</script>
</@layout.body>