<@p.header title="供应商中心-供应商设置"/>
<script type="text/javascript" src="${base}/res/js/common_select.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="${base}/res/js/ajaxfileupload/ajaxfileupload.js"></script>
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
			<div>
				<a href="${base}">供应商中心</a> <span>></span> <a
					href="#?act=store&op=store_setting" /> 供应商设置 </a><span>></span>幻灯片设置
			</div>
		</div>
		<div class="main">
			<style>
.upload-button {
	line-height: 28px;
	text-decoration: none;
	color: #555;
	text-align: center;
	display: inline-block;
	height: 28px;
	border: solid 1px #D8D8D8;
	border-radius: 5px;
	width: 78px;
}

a:hover.upload-button {
	color: #06C;
}
/*焦点图轮换
*********************************/
.flex-container a:active,.flexslider a:active {
	outline: none;
}

.slides,.flex-control-nav,.flex-direction-nav {
	margin: 0;
	padding: 0;
	list-style: none;
}

.flexslider {
	width: 790px;
	clear: both;
	margin: 5px 5px;
	*margin: 0 auto 5px auto;
	padding: 0;
}

.flexslider .slides>li {
	display: none;
}

.flexslider .slides img {
	max-width: 100%;
	display: block;
}

.flex-pauseplay span {
	text-transform: capitalize;
}

.slides:after {
	content: ".";
	display: block;
	clear: both;
	visibility: hidden;
	line-height: 0;
	height: 0;
}

html[xmlns] .slides {
	display: block;
}

* html .slides {
	height: 1%;
}

.no-js .slides>li:first-child {
	display: block;
}

.flexslider {
	background: #fff;
	position: relative;
	zoom: 1;
}

.flexslider .slides {
	zoom: 1;
}

.flexslider .slides>li {
	position: relative;
}

.flex-container {
	zoom: 1;
	position: relative;
}

.flex-direction-nav li a {
	text-indent: -9999px;
	background-color: transparent;
	display: block;
	width: 36px;
	height: 36px;
	padding: 0;
	margin: -8px 0 0 0;
	position: absolute;
	top: 45%;
	cursor: pointer;
	opacity: 0.3;
	filter: alpha(opacity = 30);
}

.flex-direction-nav li a:hover {
	opacity: 0.9;
	filter: alpha(opacity = 90)
}

.flex-direction-nav li .next {
	font-size: 0px;
	line-height: 0;
	width: 0px;
	height: 0px;
	border: 16px solid;
	border-color: transparent transparent transparent #333;
	right: 0px;
}

.flex-direction-nav li .prev {
	font-size: 0px;
	line-height: 0;
	width: 0px;
	height: 0px;
	border: 16px solid;
	border-color: transparent #333 transparent transparent;
	left: 0px;
}

.flex-direction-nav li .disabled {
	opacity: .3;
	filter: alpha(opacity = 30);
	cursor: default;
}

.flex-control-nav {
	width: 100%;
	position: absolute;
	bottom: -20px;
	*bottom: 5px;
	text-align: center;
}

.flex-control-nav li {
	margin: 0 0 0 9px;
	_margin-left: 4px;
	display: inline-block;
	zoom: 1;
	*display: inline;
}

.flex-control-nav li:first-child {
	margin: 0;
}

.flex-control-nav li a {
	width: 10px;
	height: 10px;
	line-height: 10px;
	display: block;
	background-color: #EEE;
	cursor: pointer;
	text-indent: -9999px;
	border-radius: 5px;
}

.flex-control-nav li a:hover {
	background-color: #FC0;
}

.flex-control-nav li a.active {
	background-color: #F60;
	cursor: default;
	box-shadow: 1px 1px 1px #CC3300 inset;
}
</style>

			<div class="wrap" style="_overflow:hidden;">
				<div class="tabmenu">
					<ul class="tab pngFix">
						<li class="normal"><a href="${base}/storeSetting/storeseting">供应商设置</a>
						</li>
						<li class="active"><a href="${base}/storeSetting/storeside">幻灯片设置</a>
						</li>
					</ul>
				</div>
				<div class="ncu-form-style">
					<div class="flexslider">
						<ul class="slides">
							<li><a><img
									src="upload/store/slide/1f45eba1a0e76a4be5a9e9afec33f1c7.jpg">
							</a>
							</li>
							<li><a><img
									src="upload/store/slide/1489cc3b0ca457a23c1b0b3fc05268c3.jpg">
							</a>
							</li>
							<li><a><img
									src="upload/store/slide/1ce2600930e183c2693064b7b24b190a.jpg">
							</a>
							</li>
							<li><a><img
									src="upload/store/slide/8a85014ec8d718655fc2a14699b24995.jpg">
							</a>
							</li>
							<li><a><img
									src="upload/store/slide/e48903245b25344f49578fe3347d07ac.jpg">
							</a>
							</li>
						</ul>
					</div>
					<form action="" id="my_store_form" method="post">
						<input type="hidden" name="form_submit" value="ok" /> <input
							type="hidden" name="storeId" value="${store.storeId}" />
						<!-- 图片上传部分 -->
						<ul class="ncs-slider" id="goods_images">
							<#if list??> <#assign x = 5 - list?size/> <#list list as slide>
							<li nc_type="handle_pic" id="thumbnail_${slide_index+1}">
								<div class="picture">
									<span class="thumb size142-80"><i></i> <img
										nctype="file_0" src="${imgServer}${slide.fileName}"
										alt="幻灯片${slide_index+1}"
										onerror="this.src='${base}/res/images/member/default_sildeshow.gif'"
										style="width:142px;height:80px;"
										id="storeSlideimage${slide_index+1}"
										onload="javascript:DrawImage(this,142,80);" /> <input
										type="hidden" name="storeSlide"
										id="storeSlide${slide_index+1}" nctype="file_${slide_index+1}"
										value="${slide.fileName}" /> </span>
								</div>
								<div nc_type="handler" class="bg" id="${slide_index+1}">
									<p class="operation">
										<span class="delete" nctype="drop_image" title="删除"></span>
									</p>
								</div>
								<div class="url">
									<label>跳转URL...</label> <input type="text" class="text"
										name="storeSlideUrl" id="storeSlideUrl${slide_index+1}"
										value="${slide.imgUrl}"
										placeholder="填写示例：http://www.baidu.com" />
								</div>
								<div class="upload-btn">
									<a href="javascript:void(0);"> <span
										style="width: 80px; height: 30px; position: absolute; left: 0; top: 0; z-index: 999; cursor:pointer; ">
											<input type="file" name="files" id="file_${slide_index+1}"
											file_id="0"
											style="width:80px; height: 30px; cursor: pointer; opacity:0; filter: alpha(opacity=0)"
											size="1" hidefocus="true" maxlength="0"
											onChange="ajaxFileUploads('file_${slide_index+1}','storeSlideimage${slide_index+1}','storeSlide${slide_index+1}');" />
									</span>
										<div class="upload-button">图片上传</div> </a>
								</div></li> </#list> <#if (x>0)> <#list 1..x as i>
							<li nc_type="handle_pic" id="thumbnail_${i+list?size}">
								<div class="picture">
									<span class="thumb size142-80"><i></i> <img
										nctype="file_${i+list?size}"
										id="storeSlideimage${i+list?size}"
										src="${base}/res/images/member/default_sildeshow.gif"
										onerror="this.src='${base}/res/images/member/default_sildeshow.gif'"
										style="width:142px;height:80px;"
										onload="javascript:DrawImage(this,142,80);" /> <input
										type="hidden" name="storeSlide" nctype="file_${i+list?size}"
										id="storeSlide${i+list?size}" alt="幻灯片${i+list?size}" /> </span>
								</div>
								<div nc_type="handler" class="bg" id="${i+list?size}">
									<p class="operation">
										<span class="delete" nctype="drop_image" title="删除"></span>
									</p>
								</div>
								<div class="url">
									<label>跳转URL...</label> <input type="text" class="text"
										name="storeSlideUrl" id="storeSlideUrl${i+list?size}"
										value="http://" placeholder="填写示例：http://www.baidu.com" />
								</div>
								<div class="upload-btn">
									<a href="javascript:void(0);"> <span
										style="width: 80px; height: 30px; position: absolute; left: 0; top: 0; z-index: 999; cursor:pointer; ">
											<input type="file" name="files" id="file_${i+list?size}"
											file_id="${i+list?size}"
											style="width:80px; height: 30px; cursor: pointer; opacity:0; filter: alpha(opacity=0)"
											size="1" hidefocus="true" maxlength="0"
											onChange="ajaxFileUploads('file_${i+list?size}','storeSlideimage${i+list?size}','storeSlide${i+list?size}');" />
									</span>
										<div class="upload-button">图片上传</div> </a>
								</div></li> </#list> </#if> </#if>
						</ul>
						<div class="ncm-notes">
							<ul>
								<li>最多可上传5张幻灯片图片。</li>
								<li>支持jpg、jpeg、gif、png格式上传，建议图片宽度790px、高度在300px到400px之间、大小1.00M以内的图片。提交2~5张图片可以进行幻灯片播放，一张图片没有幻灯片播放效果。</li>
								<li>操作完成以后，按“提交”按钮，可以在当前页面进行幻灯片展示。</li>
								<li>跳转链接必须带有<b style="color:red;">“http://”</b>
								</li>
							</ul>
						</div>
						<!-- 图片上传部分 -->
						<!--  <input type="submit" class="btn" value="提交" style=" margin: 20px;"/> -->
						<button class="btn" type="button" id="submitBtn"
							style=" margin: 20px;">保存</button>
					</form>
				</div>
			</div>
			<!-- 引入幻灯片JS -->
			<!-- 引入幻灯片JS -->
			<script src="${base}/res/js/store_slide.js" charset="utf-8"></script>
		</div>
	</div>
	<div class="clear"></div>
</div>

<script>
	$(function() {
		//修改供应商信息
		$('#submitBtn').click(function() {
			if ($("#my_store_form").valid()) {
				$("#submitBtn").attr('disabled', true);
				$.ajax({
					type : "post",
					url : '${base}/store/updateStore',
					data : $("#my_store_form").serialize(),
					dataType : "json",
					async : false,
					success : function(data) {
						if (data.success) {
							alert(data.message);
							setTimeout("window.location.reload()", 3200);
						} else {
							alert(data.message);
							$("#submitBtn").removeAttr("disabled");
						}
					}
				});
			}
		});

	});

	//上传图片
	function ajaxFileUploads(myBlogImage, imgId, img) {
		$.ajaxFileUpload({
			//处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
			url : '${base}/storeSetting/fileUpload',
			secureuri : false, //是否启用安全提交,默认为false
			fileElementId : myBlogImage, //文件选择框的id属性
			dataType : 'json', //服务器返回的格式,可以是json或xml等
			fileSize : 5120000,
			allowType : 'jpg,jpeg,png,JPG,JPEG,PNG',
			success : function(data, status) { //服务器响应成功时的处理函数
				if (true == data.success) { //0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述)
					//alert(data.result);
					$("img[id='" + imgId + "']").attr("src",
							"${imgServer}" + data.result);
					$("#" + img).val(data.result);
				}
			},
			error : function(data, status, e) { //服务器响应失败时的处理函数
				layer.msg('图片上传失败，请重试！！', 1, 8);
				//$('#result').html('图片上传失败，请重试！！');
			}
		});
	}
</script>
</body>
</html>
<@p.footer/>