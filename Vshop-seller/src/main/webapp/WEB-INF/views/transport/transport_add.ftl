<@p.header title="添加模版"/>
<script type="text/javascript">
var BASE_PATH = '${base}';
var transportId = "";
</script>
<script src="${base}/res/js/layer/layer.js"></script>
<div class="layout">
	<div class="sidebar">
	</div>
	<div class="right-content">
		<div class="path">
			<div>
				<a href="#">商家中心</a> <span>></span> 
				<a href="#" /> 物流工具 </a><span>></span>物流工具
			</div>
		</div>
		<div class="main">
			<link href="${base }/res/css/transport.css" rel="stylesheet" type="text/css">
			<div class="wrap">
				<div class="tabmenu">
					<ul class="tab pngFix">
						<li class="active"><a href="${base}/transport/index">运费模板</a></li>
					</ul>
				</div>
				<div class="ncu-form-style">
					<div id="dialog_batch" class="ks-ext-position ks-overlay ks-dialog dialog-batch" style="left: 0px; top: 0px; z-index: 9999; display: none">
						<div class="ks-contentbox">
							<div class="ks-stdmod-header"></div>
							<div class="ks-stdmod-body">
								<form method="post">
									默认运费： 
										<input class="w50 text" type="text" maxlength="4" autocomplete="off" data-field="start" value="1" name="express_start"> 件内， 
										<input class="w50 text" type="text" maxlength="6" autocomplete="off" value="0.00" name="express_postage" data-field="postage"> 元， 每增加 
										<input class="w50 text" type="text" maxlength="4" autocomplete="off" value="1" data-field="plus" name="express_plus"> 件，增加运费 
										<input class="w50 text" type="text" maxlength="6" autocomplete="off" value="0.00" data-field="postageplus" name="express_postageplus"> 元
									<div class="J_DefaultMessage"></div>
									<div class="btns">
										<button class="J_SubmitPL" type="button">确定</button>
										<button class="J_Cancel" type="button">取消</button>
									</div>
								</form>
							</div>
							<div class="ks-stdmod-footer"></div>
							<a class="ks-ext-close" href="javascript:void(0)"> <span
								class="ks-ext-close-x">X</span>
							</a>
						</div>
					</div>
					<form method="post" id="tpl_form" name="tpl_form" action="javascript:void(0);">
						<input type="hidden" name="transport_id" value="" /> 
						<input type="hidden" name="form_submit" value="ok" /> 
							<input type="hidden" name="type" value="">
						<div class="postage-tpl-head">
							<ul>
								<li class="form-elem"><label for="J_TemplateTitle" class="label-like">模板名称：</label> 
								<input type="text" class="text" id="title" autocomplete="off" value="" name="title">
									<p class="msg" style="display: none" error_type="title">
										<span class="error">必须填写模板名称</span>
									</p>
								</li>
								<li class="form-elem express"><span class="label-like">运送方式：</span>
									<span class="field-note">除指定地区外，其余地区的运费采用"默认运费"</span>
									<p class="msg" style="display: none" error_type="trans_type">
										<span class="error">请至少选择一种运送方式</span>
									</p>
								</li>
							</ul>
						</div>
						<!-----------------------POST begin--------------------------------------->
						<div class="postage-tpl" id="py">
							<p class="trans-line">
								<input id="Deliverypy" type="checkbox" value="py" name="tplType[]"> <label for="Deliverypy">平邮</label>
							</p>
						</div>
						<!-----------------------EXPRESS begin--------------------------------------->
						<div class="postage-tpl" id="kd">
							<p class="trans-line">
								<input id="Deliverykd" type="checkbox" value="kd" name="tplType[]"> <label for="Deliverykd">快递</label>
							</p>
						</div>
						<!-----------------------EMS begin--------------------------------------->
						<div class="postage-tpl" id="es">
							<p class="trans-line">
								<input id="Deliveryes" type="checkbox" value="es" name="tplType[]"> <label for="Deliveryes">EMS</label>
							</p>
						</div>
						<!-----------------------EMS end--------------------------------------->
						<div class="trans-submit">
							<input type="submit" id="submit_tpl" class="submit" value="保存" />
						</div>
					</form>

				</div>
				<div class="ks-ext-mask" style="position: absolute; left: 0px; top: 0px; width: 100%; height: 5000px; z-index: 9998; display: none"></div>
			</div>
			<div id="dialog_areas" class="ks-ext-position ks-overlay ks-dialog dialog-areas" style="left: 112px; top: 307.583px; z-index: 9999; display: none">
				<div class="ks-contentbox">
					<div class="ks-stdmod-header">
						<div class="title">选择区域</div>
					</div>
					<div class="ks-stdmod-body">
						<form method="post">
							<ul id="J_CityList">
								<style type="text/css">
									em.zt {
										font-size: 0;
										line-height: 0;
										width: 0;
										height: 0;
										display: inline-block;
										padding: 0;
										border: 4px solid;
										border-color: #333 transparent transparent transparent; /
										border-style: solid dashed dashed dashed;
									}
								</style>
								<li>
									<div class=" dcity clearfix">
										<div class="province-list">
											<#list areas as aas>
												<div class="ecity">
													<span class="gareas"> <input type="checkbox" class="J_Province" id="J_Province_${aas.areaId}" value="${aas.areaId}" /> 
													<label for="J_Province_${aas.areaId}">${aas.areaName}</label> 
													<span class="check_num" />
													</span><em class="zt trigger"></em>
													<div class="citys">
														<#list aas.childern as ac>
															<span class="areas">
															<input type="checkbox" class="J_City" id="J_City_${ac.areaId}" value="${ac.areaId}" />
															<label for="J_City_${ac.areaId}">${ac.areaName}</label>
															</span> 
														</#list>
														<p style="text-align: right;">
															<input type="button" class="close_button" value="关闭" />
														</p>
													</div>
													</span>
												</div>
											</#list>
										</div>
									</div>
								</li>
							</ul>
							<div class="btns">
								<button class="J_Submit" type="button">确定</button>
								<button class="J_Cancel" type="button">取消</button>
							</div>
						</form>
					</div>
					<div class="ks-stdmod-footer">
						<a class="ks-ext-close" href="javascript:void(0)"><span class="ks-ext-close-x">X</span></a>
					</div>
				</div>
			</div>
<script type="text/javascript" src="${base }/res/js/transport.js" charset="utf-8">
var BASE_PATH = '${base}';
</script>
<script>
$(function(){
	$('div[class="postage-tpl"]').each(function(){
		var tplType = $(this).find('input[name="tplType[]"]').attr('value');
	});

});
</script>
		</div>
	</div>
	<div class="clear"></div>
</div>
<@p.footer />
