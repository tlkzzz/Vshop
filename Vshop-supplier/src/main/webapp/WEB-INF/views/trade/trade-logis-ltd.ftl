<@p.header title="默认物流公司  "/>
<script type="text/javascript" src="${base}/res/js/common_select.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="${base}/res/js/ajaxfileupload/ajaxfileupload.js"></script>
<div class="layout">
  <div class="sidebar">
    
  </div>
  <div class="right-content">
        <div class="path">
      <div><a href="${base}">会员中心</a> <span>></span>
                <a href="#?act=deliver&op=express"/>
                发货设置                </a><span>></span>默认物流公司              </div>
    </div>
   <div class="main">
		<div class="wrap">
  			<div class="tabmenu">
    			<ul class="tab pngFix">
	         		<li class="normal">
	            		<a  href="${base}/transport/shipments_setting">地址库</a>
	         		</li>
	         		<li class="active">
	          		  <a  href="#">默认物流公司</a>
	         		</li>
    			</ul>
  			</div>
  			<input value="${supplierExtend.supplierId}" name="supplierId" type="hidden"/>
  			<form method="POST" id='express_form' action="" >
    			<input value="ok" name="form_submit" type="hidden">
    			<table class="ncu-table-style" >
      				<thead>
        				<tr>
          					<th class="w20"></th>
          					<th colspan="4" class="tm">物流公司</th>
        				</tr>
      				</thead>
						<tbody>
						    <#assign x=0/>
							<#list list as oi>
							  <#assign x=x+1/>
							   <#if x=1>
								<tr class="bd-line">
									<td></td>
							   </#if>
									<td class="tl">
										<label> 
											<input type="checkbox" name="cexpress[]" value="${oi.id}"<#if expressIds??><#if expressIds?seq_contains(oi.id+'')>checked</#if></#if> />
											${oi.seName}
										</label>
									</td>
							   <#if x%4=0>
								</tr>
								<tr class="bd-line">
									<td></td>
							   </#if>
							</#list>
						</tbody>
						<tfoot>
					        <tr>
					          <td colspan="5"><a class="submit pngFix ml20 mt10" href="javascript:void(0)"  onclick="saveSupplierExtend()"><div align="center"><span>保存</span></div></a></td>
					        </tr>
      					</tfoot>
    				</table>
  				</form>
			</div>
        </div>
    </div>
    <div class="clear"></div>
</div>
</body>
</html>
<script type="text/javascript">
	function saveSupplierExtend(){
		var supplierId = $("input[name='supplierId']").val();
		var data ="";
		if(supplierId != ''){
			data = "supplierId="+supplierId+"&"+$(express_form).serialize();
		}else{
			data = $(express_form).serialize();
		}
		$.ajax({
           url:'${base}/transport/saveSupplierExtend',
           type:'post',
           data : data,
           dataType:'json',
           success:function(data){
				if(data.success){
                	layer.msg('保存成功', {icon: 1,time:1000},function(){location.reload();});
                }else{
                    layer.msg('保存失败', {icon: 2});
                }
           },error:function(data){
                layer.msg('通信失败', {icon: 2});
           }
        });
	}
</script>
<@p.footer/>