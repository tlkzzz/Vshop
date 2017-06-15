<@p.header title="供应商中心-供应商设置"/>
<script type="text/javascript" src="${base}/res/js/common_select.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/ajaxfileupload/ajaxfileupload.js"></script>
<script type="text/javascript" charset="utf-8" src="${base}/res/js/area.js"></script>
<script type="text/javascript">
	// 收缩展开效果
	$(document).ready(function() {
		$(".sidebar dl dt").click(function() {
			$(this).toggleClass("hou");
			var sidebar_id = $(this).attr("id");
			var sidebar_dd = $(this).next("dd");
			sidebar_dd.slideToggle("slow", function() {
				$.cookie(COOKIE_PRE + sidebar_id, sidebar_dd.css("display"), {
					expires : 7,
					path : '/'
				});
			});
		});
	});
</script>
<div class="layout">
  <div class="sidebar">
    
  </div>
  <div class="right-content">
        <div class="path">
      <div><a href="${base}">供应商中心</a> <span>></span>
                <a href="#?act=store&op=store_setting"/>
                供应商设置                </a><span>></span>供应商设置 </div>
    </div>
        <div class="main">
      
<div class="wrap">
  <div class="tabmenu">
    <ul class="tab pngFix">
     <li class="active"><a  href="${base}/storeSetting/storeseting">供应商设置</a></li><li class="normal"><a  href="${base}/storeSetting/storeside">幻灯片设置</a></li>
    </ul>
  </div>
  <div class="ncu-form-style">
    <form method="post"  action="" enctype="multipart/form-data" id="my_store_form">
      <input type="hidden" name="form_submit" value="ok" />
      <input type="hidden" name="storeId" value="${store.storeId}" />
      <dl>
        <dt><em class="pngFix"></em>供应商名称：</dt>
        <dd>
          <p>
            ${store.storeName}
           <#--  <input class="text w400" id="store_name" type="text"  name="storeName" value="${store.storeName}" maxlength="20"/> -->
            <span></span>
          </p>
        </dd>
      </dl>
      <dl>
        <dt>所属分类：</dt>
        <dd>
          ${storeclass.name}
        </dd>
      </dl>
      <#--
      <dl>
        <dt>供应商等级：</dt>
        <dd>
          <p>系统默认                        <a href="#?act=member_store&op=update_grade" class="ncu-btn2" target="_blank" title="马上升级供应商等级">马上升级供应商等级</a>
                      </p>
          </dt>
      </dl>
      -->
      <dl>
        <dt>主营商品：</dt>
        <dd>
          <p>
            <textarea id="store_zy" name="storeZy" rows="2" class="textarea w400"  maxlength="50">${store.storeZy}</textarea>
          </p>
          <p class="hint">主营商品关键字（Tag）有助于搜索供应商时找到您的供应商<br/>关键字最多可输入50字，请用","进行分隔，例如”男装,女装,童装”</p>
        </dd>
      </dl>
      <dl class="setup store-label">
        <dt>供应商logo：</dt>
        <dd>
          <input type="hidden" id="store_old_label" name="storeLabel" value="" />
          <p class="picture">
            <#if store.storeLabel ??&& store.storeLabel!=''>
               <img src="${imgServer}${store.storeLabel}"  id="storeLabelimage" nc_type="store_Labelimage" style="width: 200px;height: 60px;"/>
            <#else>
               <img src="${base}/res/images/member/storelogo.png"  id="storeLabelimage" nc_type="store_Labelimage" style="width: 200px;height: 60px;"/>
            </#if>
          </p>
          <p>
            <input  type="file" name="files"  hidefocus="true" id="storeLablefile" nc_type="change_storeLable_file" onChange="ajaxFileUploads('storeLablefile','storeLabelimage','store_old_label');"/>
          </p>
          <p class="hint">此处为您的供应商logo，将显示在供应商Logo栏里；<br/><span style="color:orange;">建议使用宽200像素-高60像素内的GIF或PNG透明图片；点击下方"提交"按钮后生效。</span></p>
        </dd>
      </dl>
      <dl class="setup store-top-banner">
        <dt>供应商条幅： </dt>
        <dd>
          <input type="hidden" class="text w200" name="storeBanner" id="storeBanner"/>
          <p class="picture">
              <#if store.storeBanner ??&& store.storeBanner!=''>
                  <img src="${imgServer}${store.storeBanner}"  id="storeBannerImage" nc_type="store_BannerImage" style="width:638px;height:158px;"/>
              <#else>
                  <img src="${base}/res/images/member/banner.png"  id="storeBannerImage" nc_type="store_BannerImage" style="width:638px;height:158px;"/>
              </#if>
          </p>
          <p>
            <input  type="file" name="files"  hidefocus="true" id="storeBannerfile" nc_type="change_banner_label" onChange="ajaxFileUploads('storeBannerfile','storeBannerImage','storeBanner');"/>
          </p>
           <p class="hint">此处为您的供应商条幅，将显示在供应商导航上方的banner位置；<br/><span style="color:orange;">建议使用宽1000*高300像素的图片；点击下方"提交"按钮后生效。（宽度不限制大小，如非必要，尽量使用默认宽度）</span></p>
        </dd>
      </dl>
      <dl class="setup store-logo">
        <dt>供应商标志： </dt>
        <dd>
          <input type="hidden" id="storeLogo" value="" name="storeLogo"/>
          <p class="picture">
              <#if store.storeLogo ??&& store.storeLogo!=''>
                   <img src="${imgServer}${store.storeLogo}"  id="storeLogoimage" nc_type="store_logo_image" style="height: 100px;width:100px;"/>
              <#else>
                  <img src="${base}/res/images/common/default_store_logo.gif"  id="storeLogoimage" nc_type="store_logo_image" style="height: 100px;width:100px;"/>
              </#if>
          </p>
          <p>
             <input  type="file" name="files"  hidefocus="true" id="storeLogofile" nc_type="change_store_Logo" onChange="ajaxFileUploads('storeLogofile','storeLogoimage','storeLogo');"/>
          </p>
          <p class="hint">此处为您的供应商标志，将显示在供应商信息栏里；<br/><span style="color:orange;">建议使用宽100像素*高100像素内的方型图片；点击下方"提交"按钮后生效。</span></p>
        </dd>
      </dl>
      <dl>
        <dt>供应商二维码： </dt>
        <dd>
		             上传二维码：<input id="uploadtwocode" name="wocode"  value="1" type="radio" checked="checked">
		             生成二维码:<input id=createtwocode" name="wocode" value="0" type="radio">
        </dd>
      </dl>
      <dl class="setup store-storeTheme">
        <dt>　　</dt>
        <dd>
          <input type="hidden" id="storeCode" value="" name="storeCode"/>
          <p class="picture">
              <#if store.storeCode ??&& store.storeCode!=''>
                   <img src="${imgServer}${store.storeCode}"  id="storeCodeimage" nc_type="store_code_image" style="height: 100px;width:100px;"/>
              <#else>
                  <img src="${base}/res/images/common/default_store_logo.gif"  id="storeCodeimage" nc_type="store_code_image" style="height: 100px;width:100px;"/>
              </#if>
          </p>
          <p id="uploadcode">
             <input  type="file" name="files"  hidefocus="true" id="storeCodefile" nc_type="change_Code_theme" onChange="ajaxFileUploads('storeCodefile','storeCodeimage','storeCode');"/>
          </p>
          <p id="savecode" style="display: none;">
             <input type="button" value="生成二维码" onclick="savestoretwocode();"/>
          </p>
          <p class="hint">此处为您的供应商标志，将显示在供应商信息栏里；<br/><span style="color:orange;">建议使用宽100像素*高100像素内的方型图片；点击下方"提交"按钮后生效。</span></p>
        </dd>
      </dl>
            <dl>
        <dt>二级域名：</dt>
        <dd>
                    <p>
            <input type="text" class="text"  id="store_domain" value="${store.storeDomain}"  name="storeDomain"/>
            &nbsp;. &nbsp;</p>
          <p class="hint">可留空，域名长度应为: 3-12                        &nbsp; &nbsp;已修改次数为: 0 &nbsp; &nbsp;最多可修改次数为: 3                      </p>
                  </dd>
      </dl>
            <dl>
        <dt>身份证号：</dt>
        <dd>
          <p>${store.storeOwnerCard}</p>
        </dd>
      </dl>
      <dl>
        <dt>所在地区：</dt>
        	<dd>
	    <span id="spanarea">
		<select name="provinceId" class="select" id="area" check="needCheck"> 
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
		<input id="memberProvinceid" type="hidden" name="memberProvinceid" value="">
		<input id="city_id" type="hidden" name="cityId"  value="">
		<input id="area_id" class="area_ids" type="hidden" name="areaId" value="">
		<input id="area_info" class="area_names" type="hidden" name="areaInfo" value="">
	  </dd>
      </dl>
      <dl>
        <dt>详细地址：</dt>
        <dd>
          <p>
            <input name="storeAddress" type="text" class="text w400"  id="store_address" value="${store.storeAddress}"/>
          </p>
          <p class="hint">不必重复填写所在地区</p>
        </dd>
      </dl>
      <dl>
        <dt>百度地图坐标：</dt>
        <dd>
          <p>
                                            经度：<input name="storeLongitude" type="text" class="text w150"  id="store_Longitude" value="${store.storeLongitude}"/>--
                                            纬度：<input name="storeAtitude" type="text" class="text w150"  id="store_Atitude" value="${store.storeAtitude}"/>
               <a href="javascript:void(0);" class="btn-upload btng-s" onclick="laodmap();">获取地图坐标</a>　                   
          </p>
        </dd>
      </dl>
      <dl>
        <dt>联系电话：</dt>
        <dd>
          <input class="text w200" name="storeTel" type="text"  id="store_tel" value="${store.storeTel}" maxlength="20"/>
        </dd>
      </dl>
      <dl>
        <dt>QQ：</dt>
        <dd>
          <input class="w200 text" name="storeQq" type="text"  id="store_qq" value="${store.storeQq}" />
        </dd>
      </dl>
      <dl>
        <dt>阿里旺旺：</dt>
        <dd>
          <input class="text w200" name="storeWw" type="text"  id="store_ww" value="${store.storeWw}" />
        </dd>
      </dl>
      <dl>
        <dt>供应商简介：</dt>
        <dd>
          <textarea id="description" name="description" style="width:600px;height:300px;">${store.description}</textarea>
	    </dd>
      </dl>
      <dl>
        <dt>SEO关键字：</dt>
        <dd>
          <p>
            <input class="text w400" name="storeKeywords" type="text"  value="${store.storeKeywords}" />
          </p>
          <p class="hint">用于供应商搜索引擎的优化，关键字之间请用英文逗号分隔</p>
        </dd>
      </dl>
      <dl>
        <dt>SEO供应商描述：</dt>
        <dd>
          <p>
            <textarea name="storeDescription" rows="3" class="textarea w400" id="remark_input">${store.storeDescription}</textarea>
          </p>
          <p class="hint">用于供应商搜索引擎的优化，建议120字以内</p>
        </dd>
      </dl>
     
      <dl class="bottom">
        <dt>&nbsp;</dt>
        <dd style="text-align: center;"><button class="btna" type="button" id="submitBtn">保存</button></dd>
      </dl>
    </form>
  </div>
</div>
<script type="text/javascript">
var SITE_URL = "http://192.168.1.230";
$(function(){
    //控制是上传二维码还是生成二维码
    $("[name='wocode']").live("click",function(){
			var checkeval=$('input[name="wocode"]:checked ').val();
			if(checkeval==1){
			  $("#uploadcode").show();
			  $("#savecode").hide();
			}else{
			  $("#uploadcode").hide();
			  $("#savecode").show();
			}
	  });
    
	//regionInit("region");
	//地区初始化
	init_area('${store.provinceId}', '${store.cityId}');
	/* $('input[nc_type="change_storeLable_file"]').change(function(){
		var src = getFullPath($(this)[0]);
		$('img[nc_type="store_Labelimage"]').attr('src', src);
	});
	$('input[nc_type="change_banner_label"]').change(function(){
		var src = getFullPath($(this)[0]);
		$('img[nc_type="store_banner"]').attr('src', src);
	});
	$('input[nc_type="change_store_label"]').change(function(){
		var src = getFullPath($(this)[0]);
		$('img[nc_type="store_label"]').attr('src', src);
	});
	$('input[class="edit_region"]').click(function(){
		$(this).css('display','none');
		$('#area_id').val('');
		$(this).parent().children('select').css('display','');
		$(this).parent().children('span').css('display','none');
	}); */
	jQuery.validator.addMethod("domain", function(value, element) {
			return this.optional(element) || /^[\w\-]+$/i.test(value);
    }, ""); 
	
	
	
    // ajax 修改供应商二维码
   /*  $('#a_store_code').click(function(){
    	$('#img_store_code').attr('src','');
		$.getJSON($(this).attr('href'),function(data){
			$('#img_store_code').attr('src','upload/store/'+data);
		});
		return false;
    });  */
    $("#my_store_form").validate({
                errorPlacement: function(error, element){
                    var error_td = element.parent('p').children('span');
                    error_td.find('.field_notice').hide();
                    error_td.append(error);
                },
                submitHandler:function(form){
                    ajaxpost('my_store_form', '', '', 'onerror');
                },
                rules: {
                  /*   storeOwnerCard: {
                        required: true,
                        minlength: 18,
                        maxlength: 18
                    }, */
                    /*storeName: {
                        required: true,
                        maxlength: 20
                         remote : {
                            url  : 'index.php?act=member_store&op=checkname&column=ok',
                            type : 'get',
                            data : {
                                storeName : function(){
                                    return $('#store_name').val();
                                }
                            }
                        } */
                    }
                },
                messages: {
                   /*  storeOwnerCard: {
                        required: '请输入真实身份证号',
                        minlength: '身份证格式不正确',
                        maxlength: '身份证格式不正确'
                    },
                    storeAddress: {
                        required: '请输入详细的商家地址，以便顾客能够上门提货及服务',
                    }, */
                   /*  storeName: {
                        required: '请输入供应商名称',
                        maxlength: '请控制在20个字符以内'
                        remote: '该供应商名称已经存在，请您更换一个店面名称' 
                    }*/
                   /*  storeTel: {
                        required: '请输入联系电话',
                        minlength: '电话号码不能少于6位'
                    },
                    scId: {
                        required: '请选择供应商分类'
                    },
                    area_id: {
                        required: '请选择地区',
                        checkarea: '请选择地区'
                    },
                    storeZip: {
                        number: '邮编必须为数字',
                        minlength: '邮编格式不正确',
                        maxlength: '邮编格式不正确'
                    },
                    storeImage: {
                        required: '请上传本人的身份证复印件，以便审核通过',
                        accept: '请上传格式为 jpg,jpeg,png,gif 的文件'
                    },
                    storeImage1: {
                        accept: '请上传格式为 jpg,jpeg,png,gif 的文件'
                    },
                    notice: {
                        required: '请阅读并同意开店协议'
                    } */
                }
            });
    
    
    //修改供应商信息
     $('#submitBtn').click(function(){
		     if($("#my_store_form").valid()){
               //$("#submitBtn").attr('disabled',true);
				$.ajax({
		            type: "post",
		            url: '${base}/store/updateStore',
		            data: $("#my_store_form").serialize(),
		            dataType: "json",
		            async:false,
		            success:function(data) {
		                if(data.success){
		                    layer.msg(data.message , {icon:1});
		                    setTimeout("window.location.reload()", 3200);
		                }else{
		                    layer.msg(data.message , {icon:2});
		                    // $("#submitBtn").removeAttr("disabled");
		                }
		            }
		        }); 
		      }  
        });   
    
});

    /*加载地图*/
   function laodmap(){
            layer.open({
			    type: 2,
			    title:'双击获取供应商坐标',
			    shadeClose: true,
			    area: ['700px', '500px'],
			    fix: false, //不固定
			    /*maxmin: true, */
			    content:['${base}/storeSetting/loadmap','no'],
			    btns: 2,
                btn: ['确定', '取消'],
                yes:function (index) {
                    layer.close(index); //一般设定yes回调，必须进行手工关闭
                },cancel:function (index) {
                      layer.close(index);
                 }
                   
		});
   }
   
  
</script> 
    </div>
  </div>
    <div class="clear"></div>
</div>

<script>
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
	
	function savestoretwocode(){
	  $.ajax({
            url: '${base}/store/createtwocode',
            dataType: 'json',
            data:'',
            success: function (data) {
                if (data.success) {
                     alert(data.storetwocodeurl);
                     layer.msg(data.message,{icon:1});
                     setTimeout("" ,500);
                     $("#storeCode").val(data.storetwocodeurl);
                     $("#storeCodeimage").attr("src","${imgServer}"+data.storetwocodeurl);
                    
                } else {
                	layer.msg("生成失败" , {icon:2});
                }
            }
        });
	}
</script>
</body>
</html>
<@p.footer/>