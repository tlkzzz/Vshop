<@p.header title="商家中心-品牌列表"/>
<script type="text/javascript" src="${base}/res/js/common_select.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="${base}/res/js/ajaxfileupload/ajaxfileupload.js"></script>
 <script type="text/javascript" src="${base}/res/js/layer/layer.js"></script>
<div class="layout">
	<div class="sidebar">
		
	</div>
	<div class="right-content">
		<div class="path">
			<div>
				<a href="${base}">商家中心</a> <span>></span> 品牌列表
			</div>
		</div>
		<div class="main">
			<div class="wrap">
				<div class="tabmenu">
					<ul class="tab pngFix">
						<li class="active"><a
							href="#?act=store_goods&op=brand_list">品牌列表</a>
						</li>
					</ul>
					<a href="javascript:void(0)" class="ncu-btn3" nc_type="dialog"
						dialog_title="品牌申请" dialog_id="my_goods_brand_apply"
						dialog_width="480" onclick="addStorebrand();">品牌申请</a>
				</div>
				<table class="search-form">
					<form  name ="queryListForm" id="acct-form" method="post" action="">
						<tr>
							<td>&nbsp;
							    <input type="hidden" id="pageNo" />
							    <input type="hidden" name="flags" id="flags" value = "9"/>
						    </td>
							<th>品牌名称：</th>
							<td class="w160"><input type="text" class="text"
								name="brandName" value="${brand.brandName}" />
							</td>
							 <td class="w90 tc">
							 <!--<input type="submit" class="submit"
								value="搜索" /> -->
								<button  type="button" class="submit" onclick="sbQuery();">搜索</button>
							</td>
						</tr>
						<input type="hidden" name="div" id="div" value = "#dataListDiv"/>
					</form>
				</table>
				<!--列表替换地方-->
	            <div class="main" id="dataListDiv">
	            </div>
			</div>
		</div>
	</div>
	<div class="clear"></div>
</div>
<script type="text/javascript">
    /*界面初始化*/
    $(function(){
        initDataList();
    });
    /*初始化*/
    function initDataList(){
        var div = "#dataListDiv";//显示的list 数据div id 必须传递
        $.ajax({
            async:false,
            url:"${base}/storeBrand/list",//默认加载list 页面
            data:{div:div},
            error:function(){
                layer.msg("通讯失败!" , 1 , 9 );
               /* frameControl.lhDgFalInfo("通讯失败!");*/
            },
            dataType:'html',
            type: "POST",
            contentType:"application/x-www-form-urlencoded; charset=utf-8",
            success: function(data){
                $(div).empty();
                $(div).html(data);
                $(div).hide();
                $(div).fadeIn(300);
            }
        });
    }
    
     /*添加品牌*/
    function addStorebrand(){
        layer.open({
            type:2,
            move: false,
            shade: false,
            title: '新增品牌',
            content:['${base}/storeBrand/toAdd', 'no'],
            area: ['550px', '400px'],
           /*  btns: 2,
            btn: ['确定','取消'],
            yes: function (index) {
                var brandName = layer.getChildFrame('#brand_name').val(); //品牌名称
                var brandClass = layer.getChildFrame('#brand_class').val(); //品牌类型
                var brandPic = layer.getChildFrame('#brandPic').val(); //品牌图标
                var classId = layer.getChildFrame('#classId').val(); //品牌类别id
                var brandClass = layer.getChildFrame('#brandClass').val(); //品牌名称
                var formjson = '{';
                formjson += '\"brandName\":\"' + brandName + '\",';//品牌名称
                formjson += '\"brandClass\":\"' + brandClass + '\",';//品牌类型
                formjson += '\"brandPic\":\"' + brandPic + '\",';//品牌图标
                formjson += '\"classId\":\"' + classId + '\",';//品牌类别id
                formjson += '\"brandClass\":\"' + brandClass + '\"';//品牌名称
                formjson += '}';
                 $.ajax({
                    url:'${base}/storeBrand/saveStorebrand',
                    type:'post',
                    data : {'data': formjson},
                    dataType:'json',
                    success:function(data){
                        if(data.success){
                             layer.msg('新增成功', {icon: 1},initDataList());
                        }else{
                            layer.msg('新增失败', {icon: 2});
                        }
                    },error:function(data){
                         layer.msg('通信失败', {icon: 2});
                    }
                });
                layer.close(index); //一般设定yes回调，必须进行手工关闭
            },
            cancel: function(index){
                layer.close(index);
            } */
        });
    }
    
      /*修改品牌*/
    function updatestorebrand(id){
        layer.open({
            type:2,
            move: false,
            shade: false,
            title: '新增品牌',
            content:['${base}/storeBrand/updatestorebrand?brandId='+id, 'no'],
            area: ['550px', '400px'],
           /*  btns: 2,
            btn: ['确定','取消'],
            yes: function (index) {
                var brandId = layer.getChildFrame('#brandId').val();
                var brandName = layer.getChildFrame('#brand_name').val(); //品牌名称
                var brandPic = layer.getChildFrame('#brandPic').val(); //品牌图标
                var classId = layer.getChildFrame('#classId').val(); //品牌类别id
                var brandClass = layer.getChildFrame('#brandClass').val(); //品牌名称
                var formjson = '{';
                formjson += '\"brandId\":\"' + brandId + '\",';//品牌id
                formjson += '\"brandName\":\"' + brandName + '\",';//品牌名称
                formjson += '\"brandPic\":\"' + brandPic + '\",';//品牌图标
                formjson += '\"classId\":\"' + classId + '\",';//品牌类别id
                formjson += '\"brandClass\":\"' + brandClass + '\"';//品牌名称
                formjson += '}';
                 $.ajax({
                    url:'${base}/storeBrand/saveStorebrand',
                    type:'post',
                    data : {'data': formjson},
                    dataType:'json',
                    success:function(data){
                        if(data.success){
                             layer.msg('修改成功', {icon: 1},initDataList());
                        }else{
                            layer.msg('修改失败', {icon: 2});
                        }
                    },error:function(data){
                         layer.msg('通信失败', {icon: 2});
                    }
                });
                layer.close(index); //一般设定yes回调，必须进行手工关闭
            },
            cancel: function(index){
                layer.close(index);
            } */
        });
    }
    
     /**准备删除*/
    function deleteStorebrand(id){
        var url = "${base}/storeBrand/del?id="+id;
        layer.confirm('确定删除?', function(){
            $.ajax({
                type: "post",
                url: url,
                data: null,
                dataType: "json",
                async:false,
                success:function(data) {
                    if(data.success == "true"){
                        layer.msg('删除成功', {icon: 1},initDataList());
                    }else{
                        layer.msg('删除失败', {icon: 2});
                    }
                }
            });
        });
    }
    
    
    
    
    function sbQuery(){
			var div = $("#div").val();
			var parma = $("#acct-form").serialize();
			$.ajax({
	            async:false,
	            url:"${base}/storeBrand/list",//默认加载list 页面
	            data:parma,
	            error:function(){layer.msg("通讯失败!", 3,8)},
	            dataType:'html',
	            type: "POST",
				contentType:"application/x-www-form-urlencoded; charset=utf-8", 
	            success: function(data){
					$(div).empty();
					$(div).html(data);
					$(div).hide();
					$(div).fadeIn(300);
				}
	        });
		}
</script>
</body>
</html>
<@p.footer/>
