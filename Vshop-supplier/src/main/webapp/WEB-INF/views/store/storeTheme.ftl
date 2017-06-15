<@p.header title="供应商中心-信息设置"/>
<div class="layout">
	<div class="sidebar">
		
	</div>
	<div class="right-content">
		<div class="path">
			<div>
				<a href="#">供应商中心</a> <span>></span> <a
					href="#" /> 主题设置 </a><span>></span>可用主题
			</div>
		</div>
		<div class="main">

			<div class="wrap">
				<div class="tabmenu">
					<ul class="tab pngFix">
						<li class="active"><a href="#?act=store&op=theme">可用主题</a>
						</li>
					</ul>
				</div>
				<div class="templet">
					<div class="nonce">
					    <#if store.storeTheme!=null && store.storeTheme>
					       <img src="${base}/res/css/style/${store.storeTheme}/images/preview.jpg" onload="javascript:DrawImage(this,200,200);" id="current_theme_img"/>
					    <#else>
					      <img src="${base}/res/css/style/default/images/preview.jpg" onload="javascript:DrawImage(this,200,200);" id="current_theme_img"/>
					    </#if>
					</div>
					<div class="txt">
						<p>
							供应商名称：<span>${store.storeName}</span><a href="${frontServer}/store/shop?storeId=${store.storeId}"
								target="_blank" class="btn">供应商首页</a>
						</p>
						<p>
							供应商模版名称：<b id="current_template">${store.storeTheme}</b>
						</p>
						<p>
							供应商风格名称：<b id="current_style">纯色简约线条</b>
						</p>
						<p>
							<b id="store_style"></b>
						</p>
					</div>
				</div>
				<h5 class="motif_title">可用主题</h5>
				<div class="motif">
					<ul>
						<li>
							<p>
								<a href="javascript:void(0)" onclick="preview_theme('default');"><img
									id="themeimg_default"
									src="${base}/res/css/style/default/images/preview.jpg"
									onload="javascript:DrawImage(this,200,200);">
								</a>
							</p>
							<h2>
								<span style="float:left; width:30%;padding-left:20px;">模版名称：</span><span
									style="float:left;text-align:left;"><b>default</b>
								</span>
							</h2>
							<h2>
								<span style="float:left; width:30%;padding-left:20px;">风格名称：</span><span
									style="float:left;text-align:left;"><b>纯色简约线条</b>
								</span>
							</h2> <span class="btn"> <a
								href="javascript:void(0);" onclick="use_theme('default');" class="employ">使用</a>
								<a href="javascript:preview_theme('default');" class="preview">预览</a>
						</span></li>
						<li>
							<p>
								<a href="javascript:void(0)" onclick="preview_theme('style1');"><img
									id="themeimg_style1"
									src="${base}/res/css/style/style1/images/preview.jpg"
									onload="javascript:DrawImage(this,200,200);">
								</a>
							</p>
							<h2>
								<span style="float:left; width:30%;padding-left:20px;">模版名称：</span><span
									style="float:left;text-align:left;"><b>style1</b>
								</span>
							</h2>
							<h2>
								<span style="float:left; width:30%;padding-left:20px;">风格名称：</span><span
									style="float:left;text-align:left;"><b>AppleStore</b>
								</span>
							</h2> <span class="btn"> <a
								href="javascript:void(0)" onclick="use_theme('style1');"
								class="employ">使用</a> <a
								href="javascript:preview_theme('style1');" class="preview">预览</a>
						</span></li>
						<li>
							<p>
								<a href="javascript:void(0)" onclick="preview_theme('style2');"><img
									id="themeimg_style2"
									src="${base}/res/css/style/style2/images/preview.jpg"
									onload="javascript:DrawImage(this,200,200);">
								</a>
							</p>
							<h2>
								<span style="float:left; width:30%;padding-left:20px;">模版名称：</span><span
									style="float:left;text-align:left;"><b>style2</b>
								</span>
							</h2>
							<h2>
								<span style="float:left; width:30%;padding-left:20px;">风格名称：</span><span
									style="float:left;text-align:left;"><b>中式水墨典雅</b>
								</span>
							</h2> <span class="btn"> <a
								href="javascript:void(0)" onclick="use_theme('style2');" class="employ">使用</a>
								<a href="javascript:preview_theme('style2');" class="preview">预览</a>
						</span></li>
						<li>
							<p>
								<a href="javascript:void(0)" onclick="preview_theme('style4');"><img
									id="themeimg_style4"
									src="${base}/res/css/style/style4/images/preview.jpg"
									onload="javascript:DrawImage(this,200,200);">
								</a>
							</p>
							<h2>
								<span style="float:left; width:30%;padding-left:20px;">模版名称：</span><span
									style="float:left;text-align:left;"><b>style4</b>
								</span>
							</h2>
							<h2>
								<span style="float:left; width:30%;padding-left:20px;">风格名称：</span><span
									style="float:left;text-align:left;"><b>木纹爱心宠物</b>
								</span>
							</h2> <span class="btn"> <a
								href="javascript:void(0)" onclick="use_theme('style4');" class="employ">使用</a>
								<a href="javascript:preview_theme('style4');" class="preview">预览</a>
						</span></li>
						<li>
							<p>
								<a href="javascript:void(0)" onclick="preview_theme('style5');"><img
									id="themeimg_style5"
									src="${base}/res/css/style/style5/images/preview.jpg"
									onload="javascript:DrawImage(this,200,200);">
								</a>
							</p>
							<h2>
								<span style="float:left; width:30%;padding-left:20px;">模版名称：</span><span
									style="float:left;text-align:left;"><b>style5</b>
								</span>
							</h2>
							<h2>
								<span style="float:left; width:30%;padding-left:20px;">风格名称：</span><span
									style="float:left;text-align:left;"><b>我的默认小店</b>
								</span>
							</h2> <span class="btn"> <a
								href="javascript:void(0)" onclick="use_theme('style5');" class="employ">使用</a>
								<a href="javascript:preview_theme('style5');" class="preview">预览</a>
						</span></li>
						<li>
							<p>
								<a href="javascript:void(0)" onclick="preview_theme('style6');"><img
									id="themeimg_style6"
									src="${base}/res/css/style/style6/images/preview.jpg"
									onload="javascript:DrawImage(this,200,200);">
								</a>
							</p>
							<h2>
								<span style="float:left; width:30%;padding-left:20px;">模版名称：</span><span
									style="float:left;text-align:left;"><b>style6</b>
								</span>
							</h2>
							<h2>
								<span style="float:left; width:30%;padding-left:20px;">风格名称：</span><span
									style="float:left;text-align:left;"><b>黑色数码影像</b>
								</span>
							</h2> <span class="btn"> <a
								href="javascript:void(0)" onclick="use_theme('style6');" class="employ">使用</a>
								<a href="javascript:preview_theme('style6');" class="preview">预览</a>
						</span></li>
						<li>
							<p>
								<a href="javascript:void(0)" onclick="preview_theme('style7');"><img
									id="themeimg_style7"
									src="${base}/res/css/style/style7/images/preview.jpg"
									onload="javascript:DrawImage(this,200,200);">
								</a>
							</p>
							<h2>
								<span style="float:left; width:30%;padding-left:20px;">模版名称：</span><span
									style="float:left;text-align:left;"><b>style7</b>
								</span>
							</h2>
							<h2>
								<span style="float:left; width:30%;padding-left:20px;">风格名称：</span><span
									style="float:left;text-align:left;"><b>红色家居旗舰店</b>
								</span>
							</h2> <span class="btn"> <a
								href="javascript:void(0)" onclick="use_theme('style7');" class="employ">使用</a>
								<a href="javascript:preview_theme('style7');" class="preview">预览</a>
						</span></li>
						<li>
							<p>
								<a href="javascript:void(0)" onclick="preview_theme('style8');"><img
									id="themeimg_style8"
									src="${base}/res/css/style/style8/images/preview.jpg"
									onload="javascript:DrawImage(this,200,200);">
								</a>
							</p>
							<h2>
								<span style="float:left; width:30%;padding-left:20px;">模版名称：</span><span
									style="float:left;text-align:left;"><b>style8</b>
								</span>
							</h2>
							<h2>
								<span style="float:left; width:30%;padding-left:20px;">风格名称：</span><span
									style="float:left;text-align:left;"><b>优衣库旗舰店</b>
								</span>
							</h2> <span class="btn"> <a
								href="javascript:void(0)" onclick="use_theme('style8');" class="employ">使用</a>
								<a href="javascript:preview_theme('style8');" class="preview">预览</a>
						</span></li>
						<li>
							<p>
								<a href="javascript:void(0)" onclick="preview_theme('style9');"><img
									id="themeimg_style9"
									src="${base}/res/css/style/style9/images/preview.jpg"
									onload="javascript:DrawImage(this,200,200);">
								</a>
							</p>
							<h2>
								<span style="float:left; width:30%;padding-left:20px;">模版名称：</span><span
									style="float:left;text-align:left;"><b>style9</b>
								</span>
							</h2>
							<h2>
								<span style="float:left; width:30%;padding-left:20px;">风格名称：</span><span
									style="float:left;text-align:left;"><b>粉色时尚女装</b>
								</span>
							</h2> <span class="btn"> <a
								href="javascript:void(0)" onclick="use_theme('style9');" class="employ">使用</a>
								<a href="javascript:preview_theme('style9');" class="preview">预览</a>
						</span></li>
						<li>
							<p>
								<a href="javascript:void(0)" onclick="preview_theme('style10');"><img
									id="themeimg_style10"
									src="${base}/res/css/style/style10/images/preview.jpg"
									onload="javascript:DrawImage(this,200,200);">
								</a>
							</p>
							<h2>
								<span style="float:left; width:30%;padding-left:20px;">模版名称：</span><span
									style="float:left;text-align:left;"><b>style10</b>
								</span>
							</h2>
							<h2>
								<span style="float:left; width:30%;padding-left:20px;">风格名称：</span><span
									style="float:left;text-align:left;"><b>褐色食品店</b>
								</span>
							</h2> <span class="btn"> <a
								href="javascript:void(0)" onclick="use_theme('style10');" class="employ">使用</a>
								<a href="javascript:preview_theme('style10');" class="preview">预览</a>
						</span></li>
					</ul>
				</div>
			</div>
			<#--<script type="text/javascript"
				src="http://192.168.1.230/resource/js/dialog/dialog.js"
				id="dialog_js" charset="utf-8"></script>-->
			<script type="text/javascript">
				var curr_template_name = 'default';
				var curr_style_name = 'default';
				var preview_img = new Image();
				var store_theme = ' <a href="#?act=store_theme" target="_blank">当前主题可自定义广告（点击进入）</a>';
				preview_img.onload = function() {
					var d = DialogManager.get('preview_image');
					if (!d) {
						return;
					}

					if (d.getStatus() != 'loading') {

						return;
					}

					d.setWidth(this.width + 50);
					d.setPosition('center');
					d.setContents($('<img src="' + this.src + '" alt="" />'));
					ScreenLocker.lock();
				};
				preview_img.onerror = function() {
					alert('加载预览失败');
					DialogManager.close('preview_image');
				};
				function preview_theme(style_name) {
					var screenshot = '${base}/css/style/'
							+ style_name + '/screenshot.jpg';
				    layer.open({
				    type: 2,
				    title: '预览',
				    shadeClose: true,
				    shade: 0.8,
				    area: ['50%', '100%'],
				    content: screenshot //iframe的url
				});
                   /*  layer.open({
					    type: 1,
					    title: false,
					    closeBtn: false,
					    area: ['516px', '620px'],
					    skin: 'layui-layer-nobg', //没有背景色
					    shadeClose: true,
					    content: screenshot
					}); */
					/* var d = DialogManager.create('preview_image');
					d.setTitle('效果预览');
					d.setContents('loading', {
						'text' : '加载中...'
					});
					d.setWidth(270);
					d.show('center');

					preview_img.src = screenshot; */
				}
				
				function use_theme(style) {
				    var url = "${base}/store/updateStore?storeTheme="+style+"&storeId="+'${store.storeId}';
			            $.ajax({
			                type: "post",
			                url: url,
			                data: null,
			                dataType: "json",
			                async:false,
			                success:function(data) {
			                    if(data.success){
			                         layer.msg('设置成功', {icon: 1}, setTimeout("window.location = '${base}/store/storethem'" , 1000));
			                    }else{
			                        layer.msg('设置失败', {icon: 2});
			                    }
			                }
			            });
				}
				
				
				
				// 收缩展开效果
			    /* $(document).ready(function(){
			        $(".sidebar dl dt").click(function(){
			            $(this).toggleClass("hou");
			            var sidebar_id = $(this).attr("id");
			            var sidebar_dd = $(this).next("dd");
			            sidebar_dd.slideToggle("slow",function(){
			                $.cookie(COOKIE_PRE+sidebar_id, sidebar_dd.css("display"), { expires: 7, path: '/'});
			            });
			        });
			        $('.sidebar').find('dd').css('display','none');
			    }); */
			</script>
		</div>
	</div>
	<div class="clear"></div>
</div>

</body>
</html>
<@p.footer/>
