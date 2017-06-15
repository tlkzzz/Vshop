<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,inital-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
<title>地址管理 - 优彼，微商家</title>
<link href="${base}/res/html5/css/style.css" rel="stylesheet" type="text/css" />
<script src="${base}/res/html5/js/jquery-1.10.2.min.js"></script>
<link href="${base}/res/html5/css2/head.css" rel="stylesheet" type="text/css" />
</head>

<body class="order_bg">
<#-- 
<div class="user_bar" style="color:#fff; text-align:center;">
	<a class="back" href="${base}/m/authc/buyer/serviceCenter">
		<img src="${base}/res/html5/images/back.png" width="30" height="30" />
	</a>
	<span style="margin-left:6%;">地址管理</span>
	<span class="fr">
		<a href="${base}/m/authc/buyer/addressAdd?page=2&cartIds=0" >+新增</a>
	</span>
</div>
-->

<div class="header" style="position:relative;">
		<a href="${base}/m/authc/buyer/serviceCenter">
			<img src="${base}/res/html5/img/fanhui_03.png" /></a>
		<p>
		地址管理
		</p>
		<a href="${base}/m/authc/buyer/addressAdd?page=2&cartIds=0" style="width: 56px; margin-left: -15px;">+新增</a>
		<#--
		<a href="${base}/m/index/index"><img src="${base}/res/html5/img/fanhui_05.jpg" 
		style="width: 26px; height: 22.5px; margin-left: -15px;" /></a> -->	
	</div>

<div class="main">
  <#-- <div class="mt50" style="height:49px;"></div> -->
  <div class="address_list">
          <#assign addresslist = newTag("addressTag") />
          <#assign addresslist = addresslist("") />
					<#list addresslist as address>
				  	    <dl>
						      <dt id="${address.addressId}" >
						        <h3>
							        <b>
							           ${address.mobPhone}
							        </b>
							        <strong>
							           ${address.trueName}
							        </strong>
							    </h3>
						        <span>
						               ${address.address}<br>${address.areaInfo}
						        </span>
						      </dt>
						      <dd>
						          <a href="javascript:void(0);" onClick="deleteAddress('${address.addressId}');" class="address_list_edit">
							          <span>
							             <img src="${base}/res/html5/images/user_del.png">
							          </span>
							          <b>
							            	删除
							          </b>
						          </a>
						      </dd>
				    </dl>
	      </#list>
    </div>
</div>
<!--底部-->
	

<!--按钮-->

</body>
<script>
jQuery(document).ready(function(e) {
	//地址更新
    jQuery(".address_list dt").click(function(){
		var id = jQuery(this).attr("id");
		window.location.href="${base}/m/authc/buyer/updateAddress?page=2&cartIds=0&id="+id;
	});
  
});<!--end-->

//地址删除
function deleteAddress(id){
    var url = "${base}/m/authc/buyer/deleteAddress?id="+id+"&cartIds=0";
    //layer.confirm('确定删除?', function(){
        $.ajax({
            type: "post",
            url: url,
            data: null,
            dataType: "json",
            async:false,
            success:function(data) {
                if(data.success == "true"){
                	window.location.href = "${base}/m/authc/buyer/address";
                }else{
                    layer.msg('删除失败', {icon: 2});
                }
            }
        });
   // });
}
</script>
</html>
