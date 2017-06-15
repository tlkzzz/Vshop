<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,inital-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
<title>用户中心 - 优彼，微商家</title>
<link href="${base}/res/html5/css/style.css" rel="stylesheet" type="text/css" />
<script src="${base}/res/html5/js/jquery-1.10.2.min.js"></script>
<script src="${base}/res/html5/js/jquery.validate.min.js"></script>
<script src="${base}/res/js/area.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/layer/layer.js"></script>
<link href="${base}/res/html5/css2/head.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
var SITEURL = '${base}';
</script>
<script>
$(function(){
	//真实姓名
	$("#trueName").change(function(){
		if($(this).val().trim()!=''){
			$(".trueNameMsg").html('');
		} 
	});
	//手机号码
	$("#mobPhone").change(function(){
		var mobileexp = /^1\d{10}$/;
		
		if(mobileexp.test($(this).val().trim())){
			$(".mobPhoneMsg").html('');
		} 
	});	
	//街道地址
	$("#address").change(function(){
		if($(this).val().trim()!=''){
			$(".area_addressMsg").html('');
		} 
	});	
	//邮政编码
	$("#zipCode").change(function(){
		if($(this).val().trim()!=''){
			$(".zipCodeMsg").html('');
		} 
	});
	
	init_area('${address.provinceId}','${address.cityId}','${address.areaId}');
});
function addAddress(){
	 var addressId = $('input[name="id"]').val(); //收货地址id
	 var provinceId = $('#area').val(); //省的id
	 var cityId = $('#city').val(); //城市id
	 var areaId = $('#qu').val(); //区的id
	 var address = $('#address').val(); //具体地址
	 var zipCode = $('#zipCode').val(); //具体地址
	 var trueName = $('#trueName').val(); //收货人
	 var mobPhone = $('#mobPhone').val(); //收货人手机号
	// var telPhone = $('#telPhone').val(); //收货人电话号

	 var provinceval =  $('#area').find("option:selected").html(); //省的值
	 var cityval =  $('#spancity').find("option:selected").html(); //城市的值
	 var quval =  $('#spanqu').find("option:selected").html(); 	 //区的值 
	 var areaInfo=provinceval+","+cityval+","+quval;//保存到常用地址表
	 if(trueName==''){
	     layer.msg('收货人不能为空', {icon: 2});
	     return false;
	 }else{
	     
	 }
	 if(mobPhone==''){
		 layer.msg('手机号填写不正确', {icon: 2});
	     return false;
	 }else{
	    var mobileexp = /^1\d{10}$/;
	     if(!mobileexp.test(mobPhone)){
			 layer.msg('手机号填写不正确', {icon: 2});
	         return false;
	     } else{
	               
	     }
	 }

	 if(provinceId==''||provinceId=='0'){
		 layer.msg('请选择省份', {icon: 2});
	     return false;
	 }else{
	     
	 }
	 if(cityId==''||cityId=='0'){
	     layer.msg('请选择城市', {icon: 2});
	     return false;
	 }
	 if(areaId==''||areaId=='0'){
		 layer.msg('请选择区', {icon: 2});
	     return false;
	 }else{
	     
	 }
	 if(address.trim()==''){
		 layer.msg('请填写详细地址', {icon: 2});
	     return false;
	 }else{
	    
	 }
	// if(zipCode.trim()==''){
  	//	 layer.msg('请正确填写邮编', {icon: 2});
	//     return false;
	// }else{
	    
	// }
	 var formjson = '{';
	 formjson += '\"addressId\":\"' + addressId + '\",';//收货地址id
	 formjson += '\"trueName\":\"' + trueName + '\",';//分类id
	 formjson += '\"provinceId\":\"' + provinceId + '\",';//分类id
	 formjson += '\"cityId\":\"' + cityId + '\",';//城市id
	 formjson += '\"areaId\":\"' + areaId + '\",';//地区ID
	 formjson += '\"mobPhone\":\"' + mobPhone + '\",';//手机号
	// formjson += '\"telPhone\":\"' + telPhone + '\",';//电话号
	 formjson += '\"areaInfo\":\"' + areaInfo + '\",';//地区全称
	 formjson += '\"address\":\"' + address + '\",';//详细地址
	// formjson += '\"zipCode\":\"' + zipCode + '\"';//邮编
	 formjson += '}';
	 $.ajax({
	     url:'${base}/m/authc/buyer/updateAds',
	     type:'post',
	     data : {'data': formjson,'page':'${page}','cartIds':'${cartIds}'},
	     dataType:'json',
	     success:function(data){
	         if(data.success){
	              layer.msg('更新成功', {icon: 1});
	              if(data.page==1){
	            	  //购物车订单页
	            	  window.location.href="${base}/m/authc/cart/cartOrder?cartIds="+data.cartIds; 
	              }else{
	            	  //地址管理页面
	            	  window.location.href="${base}/m/authc/buyer/address";
	              }
	         }else{
	             layer.msg('新增失败', {icon: 2});
	         }
	     },error:function(data){
	          layer.msg('通信失败', {icon: 2});
	     }
	 }); 
}
</script>
</head>
<body>

<div class="header" style="position:relative;">
		<a href="javascript:history.go(-1);">
			<img src="${base}/res/html5/img/fanhui_03.png" /></a>
		<p>
		地址更新
		</p>
		<#--
		<a href="${base}/m/authc/buyer/addressAdd?page=2&cartIds=0" style="width: 56px; margin-left: -15px;">+新增</a>
		<a href="${base}/m/index/index"><img src="${base}/res/html5/img/fanhui_05.jpg" 
		style="width: 26px; height: 22.5px; margin-left: -15px;" /></a> -->
	</div>
	<#-- 
<div class="user_bar"><a class="back" href="javascript:history.go(-1);">
<img src="${base}/res/html5/images/back_black.png" width="30" height="30" /></a><span class="fl">地址更新</span></div>
-->
<div class="info_form">
  <form  method="post"  name="theForm" id="theForm" action="">
  <input type="hidden" name="form_submit" value="ok">
  <input type="hidden" name="id" value="${address.addressId}">
 <#-- <div class="mt50" style="height:49px;"></div> -->
  <ul>
    <li>
	    <span class="form_title">收货人<font color="red">*</font></span>
	    <span class="form_size300">
	      <!-- <input name="trueName" id="trueName" value="" type="text"> -->
	    	<input type="text" class="text" id="trueName" name="trueName" value="${address.trueName}">
	    	<span class="trueNameMsg" style="color: red"></span>
	    </span>
    </li>
    <li>
	    <span class="form_title">手机<font color="red">*</font></span>
	    <span class="form_size300">
	      <!--  <input name="mobile" id="mobile" value="" type="text">-->
	    	<input type="text" class="text" name="mobPhone" id="mobPhone" value="${address.mobPhone}">
	    	<span class="mobPhoneMsg" style="color: red"></span>
	    </span>
    </li>
  <!--  <li>
	    <span class="form_title">电话号码</span>
	    <span class="form_size300">
	       <input name="mobile" id="mobile" value="" type="text">
	    	<input type="text" class="text" name="telPhone" id="telPhone" value="${address.telPhone}">
	    </span>
    </li> -->
    <li>
    	<span class="select_left">所在区域<font color="red">*</font></span>
	    <div id="region">
		    <input id="city_id" type="hidden" name="city_id" value="">
			<input id="area_id" class="area_ids" type="hidden" name="area_id" value="">
			<input id="area_info" class="area_names" type="hidden" name="area_info" value="">
		    <span id="spanarea">
			    <select name="area" class="select" id="area">
			    <option selected="selected" value="0">请选择</option>
		        <#if areas??>
					<#list areas as str>
						<option value="${str.areaId}" >${str.areaName}</option>
					</#list>
				</#if>
			    </select> 	
		    </span>
		    <span id="spancity"></span>
		    <span id="spanqu"></span>
		    <span class="areaMsg" style="color: red"></span>
        </div>
        
    </li>
  	<li>
	  	<span class="form_title">详细地址<font color="red">*</font></span>
		  	<span class="form_size300">
		     <!--  <input name="area_info" id="area_info" value="" type="text"> -->
		    	<input class="text w300" type="text" name="address" id="address" value="${address.address}">
	            <span class="area_addressMsg" style="color: red"></span>
		    </span>
	    </li>
  <!--  <li>
    <span class="form_title">邮编<font color="red">*</font></span>
	    <span class="form_size300">
	      <input name="zip" id="zip" value="" type="text"> 
		    <input type="text" class="text valid" name="zipCode" id="zipCode" maxlength="6" value="${address.zipCode}">
		    <span class="zipCodeMsg" style="color: red"></span>
   	    </span>
    </li>-->
  </ul>
  <input id="area_id" name="area_id"  value="4521986"  type="hidden">
  <input id="id" name="id" value="" type="hidden">
  <input id="type" name="type" value="" type="hidden">
      </form>
  <div class="mt50" style="height:10px;"></div>
  <div class="red_submit"><a href="javascript:void(0);" onClick="addAddress();" style="background-color:#c81313;">提交</a></div>
</div>
<div class="note" id="note" style="display:none"></div>
<div class="mt50" style="height:49px;"></div>
<!--底部-->

<!--按钮-->

</body>
</html>
