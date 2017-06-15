<@p.userHeader title="会员首页"/>
<link href="${base}/res/css/user.css" rel="stylesheet" type="text/css" />
<style>
	/* 上传图片标签 */
	.addImg {
		display: block;
		width: 80px;
		margin-top: 5px;
		height: 80px;
		line-height: 80px;
		border: 1px solid #ccc;
		color: #999;
		text-align: center;
	}
	 a:visited {color: #999} 
	 .img_li{
	 	display: inline; 
	 	padding: 10px;
	 }
	 .img_ul{
	 	padding-top: 10px;
	 }
</style>
<script type="text/javascript" src="${base}/res/js/layer/layer.js"></script>
<script type="text/javascript" src="${base}/res/js/ajaxfileupload/ajaxfileupload.js" charset="utf-8"></script>
<script type="text/javascript">
    $(function(){
        $(".star").mouseenter(function(){
            $(this).addClass("active");
            $(this).prevAll(".star").addClass("active");
            var degree = $(this).parent().find(".active").length;
            if(degree < 2){
                $(this).parent().next("span").text("很差");
            }else if(degree < 4){
                $(this).parent().next("span").text("一般");
            }else{
                $(this).parent().next("span").text("很好");
            }
        })
        $(".star").click(function(){
            $(this).addClass("active");
            $(this).prevAll(".star").addClass("active");
            $(this).nextAll(".star").removeClass("active");
            var degree = $(this).parent().find(".active").length;
            if(degree < 2){
                $(this).parent().next("span").text("很差");
            }else if(degree < 4){
                $(this).parent().next("span").text("一般");
            }else{
                $(this).parent().next("span").text("很好");
            }
        });
        //删除上传的图片
		$("[name=deletePhoto]").live("click", function(){
			$(this).parent().remove();
		});
    })

    function subForm(){
        $("#gevalScore").val($("#goodsScore").find(".active").length);
        $("#gevalContent").val($("#content").val());
        $("#sevalDesccredit").val($("#desccredit").find(".active").length);
        $("#sevalServicecredit").val($("#servicecredit").find(".active").length);
        $("#sevalDeliverycredit").val($("#deliverycredit").find(".active").length);
        if($("#isAnonymous").is(":checked")){
            $("#gevalIsAnonymous").val(1);
        }else{
            $("#gevalIsAnonymous").val(0);
        }
        //商品多图
		var goodsImageMore = "";
		var len = $("[name=deletePhoto]").length;
		$("[name=deletePhoto]").each(function(i){
			if(i == len - 1){
				goodsImageMore += $(this).attr("imageSrc")
			}else{
				goodsImageMore += $(this).attr("imageSrc") + ",";
			}
			
		});
		var data = "goodsImageMore="+goodsImageMore+"&"+$("#evaluate").serialize();
        $.ajax({
            url : '${base}/myReviews/saveReviews',
            data : data,
            dataType : 'json',
            success : function(data){
                if(data.success){
                	 layer.msg(data.msg , {icon:1});
                    setTimeout("ok()", 2000);
                }else{
                	layer.msg(data.msg , {icon:2});
                }
            }
        })
    }
	function ok(){
		window.location.href='${base}/user/list';
	}
	
	/*上传评论图片*/
	function ajaxImageUploads(myBlogImage){
		var imgNo = $("#review_img").find("li").length;
		$.ajaxFileUpload({
	        //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
	        url:"${base}/myReviews/imageFileUpload",
	        secureuri:false,                       //是否启用安全提交,默认为false
	        fileElementId:myBlogImage,           //文件选择框的id属性
	        dataType:'json',                       //服务器返回的格式,可以是json或xml等
	        data:{imgNo:imgNo},						   //已上传的图片数量
	        success:function(data, status){        //服务器响应成功时的处理函数
	            if(data.success){
	            	var count = parseInt(data.listimgSize);
	            	var strs01 = data.photoNewName.split(",");
	            	var imgBasePath = '${imgServer}'
	            	var imgPath = data.imgPath;
	            	for(var i = 0; i < count; i++){	         
	            		var photoSrc01 = imgPath + strs01[i];
	            		var photoServcerSrc = imgBasePath + imgPath + strs01[i];
	            		$("#review_img").append("<li class='img_li'><img style='width:82px;height:82px' src='"+photoServcerSrc+"'/><a href='javascript:void(0)' imageSrc='"+photoSrc01+"' name='deletePhoto'> 删除</a></li>");
	            	}
	            }else{
	            	layer.alert("最多上传三张图片",{icon:2});
	            }
	        },
	        error:function(data, status, e){ //服务器响应失败时的处理函数
	            layer.alert('图片上传失败，请重试！！',{icon:2});
	        }
	    });
	}	
</script>
<div class="main-wrap user">
    <div class="main">
        <div class="breadcrumb">
            <strong><a href="javascript:void(0);">商品评价</a></strong>
            <!-- <span>
                <i>&gt;</i>
                商品评价
            </span> -->
        </div>

        <div class="reviews-content">
            <div class="reviews-detail">
                <h3 class="reviews-tit">对商品进行评价</h3>
                <table class="reviews-proinfo">
                    <thead><tr>
                        <th class="t1">评价商品</th>
                        <th class="t2">商品评分</th>
                        <th class="t3">评价内容</th>
                    </tr></thead>
                    <tbody>
                    <tr>
                        <td class="p-img">
                            <a href="${base}/product/detail?id=${orderGoods.goodsId}" target="_blank" title="${orderGoods.goodsName}">
                                <img src="${base}/upload/${orderGoods.goodsImage}" alt="${orderGoods.goodsName}"/>
                            </a>
                        </td>
                        <td class="p-score">
                            <div class="commstar-mod">
                                    <span class="commstar" id="goodsScore">
                                        <a href="javascript:;" class="star"></a>
                                        <a href="javascript:;" class="star"></a>
                                        <a href="javascript:;" class="star"></a>
                                        <a href="javascript:;" class="star"></a>
                                        <a href="javascript:;" class="star"></a>
                                    </span>
                                    <span class="degree">很差</span>
                            </div>
                        </td>
                        <td class="p-content">
                            <textarea name="" class="textarea" id="content"></textarea>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <h3 class="reviews-tit">晒单</h3>
                <div>
                	<table>
                		<tr>
                			<td><ul class="img_ul" id="review_img"></ul></td>
                			<td>
                				<a class="addImg" href="#none" style="position: relative; z-index: 1;text-decoration:none;">添加图片
			                		<input type="file" onChange="ajaxImageUploads('myBlogImage1')" style="opacity:0; top:0; left:0; width:100%; height:100%; margin:0; position:absolute;" id="myBlogImage1" name="myfiles" class="file" multiple="multiple"/>
			                	</a>
                			</td>
                		</tr>
                	</table>
                </div>
                	
                <h3 class="reviews-tit">对商家信息及服务评分</h3>
                <div class="reviews-shopinfo">
                    <div class="shop-score">
                        <h5><a href="${base}/store/shop?storeId=${storeVo.storeId}" target="_blank">${storeVo.storeName}</a></h5>
                        <ol>
                            <li>商品评分：<b>${evaluateStore.sevalDesccredit}分</b></li>
                            <li>服务评分：<b>${evaluateStore.sevalServicecredit}分</b></li>
                            <li>时效评分：<b>${evaluateStore.sevalDeliverycredit}分</b></li>
                        </ol>
                    </div>
                    <dl>
                        <dt>商品与描述相符：</dt>
                        <dd><div class="commstar-mod">
                            <span class="commstar" id="desccredit">
                                <a href="javascript:;" class="star"></a>
                                <a href="javascript:;" class="star"></a>
                                <a href="javascript:;" class="star"></a>
                                <a href="javascript:;" class="star"></a>
                                <a href="javascript:;" class="star"></a>
                            </span>
                            <span class="degree">很差</span>
                        </div></dd>
                        <dt>商家的服务态度：</dt>
                        <dd><div class="commstar-mod">
                            <span class="commstar" id="servicecredit">
                                <a href="javascript:;" class="star"></a>
                                <a href="javascript:;" class="star"></a>
                                <a href="javascript:;" class="star"></a>
                                <a href="javascript:;" class="star"></a>
                                <a href="javascript:;" class="star"></a>
                            </span>
                            <span class="degree">很差</span>
                        </div></dd>
                        <dt>商家的发货速度：</dt>
                        <dd><div class="commstar-mod">
                            <span class="commstar" id="deliverycredit">
                                <a href="javascript:;" class="star"></a>
                                <a href="javascript:;" class="star"></a>
                                <a href="javascript:;" class="star"></a>
                                <a href="javascript:;" class="star"></a>
                                <a href="javascript:;" class="star"></a>
                            </span>
                            <span class="degree">很差</span>
                        </div></dd>
                    </dl>
                </div>
                <form method="post" id="evaluate">
                    <input type="hidden" name="orderSn" value="${orderSn}"/>
                    <input type="hidden" name="recId" value="${recId}"/>
                     <input type="hidden" name="specInfo" value="${specInfo}"/>
                    <input type="hidden" name="gevalScore" id="gevalScore"/>
                    <input type="hidden" name="gevalContent" id="gevalContent"/>
                    <input type="hidden" name="gevalIsAnonymous" id="gevalIsAnonymous"/>
                    <input type="hidden" name="sevalDesccredit" id="sevalDesccredit"/>
                    <input type="hidden" name="sevalServicecredit" id="sevalServicecredit"/>
                    <input type="hidden" name="sevalDeliverycredit" id="sevalDeliverycredit"/>
                </form>
                <p class="btns">
                    <label class="anony"><input class="checkbox" type="checkbox" name="" id="isAnonymous"/>匿名评价</label>
                    <button class="btna" type="button" onclick="subForm()">提交评价</button>
                </p>
            </div>
        </div><!-- order-content] -->

    </div>
</div><!-- main-wrap] -->
<@p.userfooter/>
