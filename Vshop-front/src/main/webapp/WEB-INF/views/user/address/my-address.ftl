<@p.userHeader title="我的地址－个人中心"/>
<script type="text/javascript" src="${base}/res/js/layer/layer.js"></script>
<div id="container" class="wrapper">
    <div class="layout">
        <@m.memberleft  title="收货地址"/>
        <div class="right-content">
            <div class="path">
                <div><a href="#?act=member_snsindex">我的商家</a><span>&raquo;</span>
                    <a href="#?act=member&op=address"/>收货地址</a><span>&raquo;</span>地址列表
                </div>
            </div>
            <form id="acct-form" method="post" action="${base}/myaddress/index" name ="queryListForm">
                <input type="hidden" name="div" id="div" value = "#dataListDiv"/>
            </form>
            <!--列表替换地方-->
            <div class="main" id="dataListDiv">
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    /*界面初始化*/
    $(function(){
        initDataList();
    });

    /*初始化*/
    function initDataList(){
        $.ajax({
            async:false,
            url:"${base}/myaddress/list",//默认加载list 页面
            error:function(){
                layer.msg("通讯失败!" , 1 , 9 );
               /* frameControl.lhDgFalInfo("通讯失败!");*/
            },
            dataType:'html',
            type: "POST",
            contentType:"application/x-www-form-urlencoded; charset=utf-8",
            success: function(data){
                $("#dataListDiv").empty();
                $("#dataListDiv").html(data);
                $("#dataListDiv").hide();
                $("#dataListDiv").fadeIn(300);
            }
        });
    }
    
    
     /**准备删除*/
    function deleteAddress(id){
        var url = "${base}/myaddress/deleteAddress?id="+id;
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
    /*修改地址*/
     function updateAddress(id) {
         layer.open({
             type: 2,
             move: false,
             shade: false,
             title: '修改地址',
             content: ['${base}/myaddress/updateAddress?id='+id, 'no'],
             area: ['550px', '400px'],
             btns: 2,
             btn: ['确定', '取消'],
             yes: function (index) {
             	var addressId = layer.getChildFrame('#addressId').val(); //收货地址id
                var provinceId = layer.getChildFrame('#area').val(); //省的id
                var cityId = layer.getChildFrame('#city').val(); //城市id
                var areaId = layer.getChildFrame('#qu').val(); //区的id
                var address = layer.getChildFrame('#address').val(); //具体地址
                var zipCode = layer.getChildFrame('#zipCode').val(); //具体地址
                var trueName = layer.getChildFrame('#trueName').val(); //收货人
                var mobPhone = layer.getChildFrame('#mobPhone').val(); //收货人手机号

                var provinceval = layer.getChildFrame('#area').find("option:selected").html(); //省的值
                var cityval = layer.getChildFrame('#city').find("option:selected").html(); //城市的值
                var areaInfo=provinceval+","+cityval;
                var quval = layer.getChildFrame('#qu').find("option:selected").html(); 	 //区的值
                //三级地区菜单没有的校验
				if(typeof(quval) != "undefined"){
					areaInfo=provinceval+","+cityval+","+quval;//保存到常用地址表
				}
                if(trueName==''){
                    //layer.getChildFrame(".trueNameMsg").html('收货人不能为空');
                    layer.msg('收货人不能为空', {icon: 2});
                    return false;
                }else{
                    //layer.getChildFrame(".trueNameMsg").html('');
                }
                if(mobPhone==''){
                   //layer.getChildFrame(".mobPhoneMsg").html('手机号填写不正确');
                    layer.msg('手机号填写不正确', {icon: 2});
                    return false;
                }else{
                   var mobileexp = /^1\d{10}$/;
                    if(!mobileexp.test(mobPhone)){
                        //layer.getChildFrame(".mobPhoneMsg").html('手机号填写不正确');
                        layer.msg('手机号填写不正确', {icon: 2});
                        return false;
                    } else{
                        //layer.getChildFrame(".mobPhoneMsg").html('');                    
                    }
	            }

                if(provinceId==''||provinceId=='0'){
                    //layer.getChildFrame(".areaMsg").html('请选择省份');
                    layer.msg('请选择省份', {icon: 2});
                    return false;
                }else{
                    //layer.getChildFrame(".areaMsg").html('');
                }
                if(cityId==''||cityId=='0'){
                    //layer.getChildFrame(".areaMsg").html('请选择城市');
                    layer.msg('请选择城市', {icon: 2});
                    return false;
                }
                if(areaId==''||areaId=='0'){
                    //layer.getChildFrame(".areaMsg").html('请选择区');
                    layer.msg('请选择区', {icon: 2});
                    return false;
                }else{
                    //layer.getChildFrame(".areaMsg").html('');
                }
                if(address.trim()==''){
                    //layer.getChildFrame(".area_addressMsg").html('请填写详细地址');
                    layer.msg('请填写详细地址', {icon: 2});
                    return false;
                }else{
                    //layer.getChildFrame(".area_addressMsg").html('');
                }
                if(zipCode.trim()==''){
                    //layer.getChildFrame(".area_addressMsg").html('请填写详细地址');
                    layer.msg('请正确填写', {icon: 2});
                    return false;
                }else{
                    //layer.getChildFrame(".area_addressMsg").html('');
                }

                var formjson = '{';
                formjson += '\"addressId\":\"' + addressId + '\",';//收货地址id
                formjson += '\"trueName\":\"' + trueName + '\",';//分类id
                formjson += '\"provinceId\":\"' + provinceId + '\",';//省id
                formjson += '\"cityId\":\"' + cityId + '\",';//城市id
                //三级地区菜单没有的校验
				if(typeof(quval) != "undefined"){
					formjson += '\"areaId\":\"' + areaId + '\",';//地区ID
				}
                formjson += '\"mobPhone\":\"' + mobPhone + '\",';//手机号
                formjson += '\"areaInfo\":\"' + areaInfo + '\",';//地区全称
                formjson += '\"address\":\"' + address + '\",';//详细地址
                formjson += '\"zipCode\":\"' + zipCode + '\"';//邮编
                formjson += '}';
                 $.ajax({
                     url: '${base}/myaddress/updateAds',
                     type: 'post',
                     data: {'data': formjson},
                     dataType: 'json',
                     success: function (data) {
                         if (data.success == 'true') {
                             layer.msg('修改成功', {icon: 1}, initDataList());
                         } else {
                             layer.msg('修改失败', {icon: 2});
                         }
                     }, error: function (data) {
                         layer.msg('通信失败', {icon: 2});
                     }
                 });
                 layer.close(index); //一般设定yes回调，必须进行手工关闭
             }, cancel: function (index) {
                 layer.close(index);
             }
         });
     }

    /*添加地址*/
    function addAddress(){
        layer.open({
            type:2,
            move: false,
            shade: false,
            title: '新增地址',
            //content: '${base}/myaddress/addAddress',
            content:['${base}/myaddress/addAddress', 'no'],
            //content:$('#fwin_my_address_add'),
            area: ['550px', '400px'],
            btns: 2,
            btn: ['确定', '取消'],
            yes: function (index) {
                var provinceId = layer.getChildFrame('#area').val(); //省的id
                var cityId = layer.getChildFrame('#city').val(); //城市id
                var areaId = layer.getChildFrame('#qu').val(); //区的id
                var address = layer.getChildFrame('#address').val(); //具体地址
                var zipCode = layer.getChildFrame('#zipCode').val(); //具体地址
                var trueName = layer.getChildFrame('#trueName').val(); //收货人
                var mobPhone = layer.getChildFrame('#mobPhone').val(); //收货人手机号
                
                var provinceval = layer.getChildFrame('#area').find("option:selected").html(); //省的值
                var cityval = layer.getChildFrame('#city').find("option:selected").html(); //城市的值
                var areaInfo=provinceval+","+cityval;
                var quval = layer.getChildFrame('#qu').find("option:selected").html(); 	 //区的值
                //三级地区菜单没有的校验
				if(typeof(quval) != "undefined"){
					areaInfo=provinceval+","+cityval+","+quval;//保存到常用地址表
				}
                if(trueName==''){
                    //layer.getChildFrame(".trueNameMsg").html('收货人不能为空');
                    layer.msg('收货人不能为空', {icon: 2});
                    return false;
                }else{
                    //layer.getChildFrame(".trueNameMsg").html('');
                }
                if(mobPhone==''){
                   //layer.getChildFrame(".mobPhoneMsg").html('手机号填写不正确');
                    layer.msg('手机号填写不正确', {icon: 2});
                    return false;
                }else{
                   var mobileexp = /^1\d{10}$/;
                    if(!mobileexp.test(mobPhone)){
                        //layer.getChildFrame(".mobPhoneMsg").html('手机号填写不正确');
                        layer.msg('手机号填写不正确', {icon: 2});
                        return false;
                    } else{
                        //layer.getChildFrame(".mobPhoneMsg").html('');                    
                    }
	            }

                if(provinceId==''||provinceId=='0'){
                    //layer.getChildFrame(".areaMsg").html('请选择省份');
                    layer.msg('请选择省份', {icon: 2});
                    return false;
                }else{
                    //layer.getChildFrame(".areaMsg").html('');
                }
                if(cityId==''||cityId=='0'){
                    //layer.getChildFrame(".areaMsg").html('请选择城市');
                    layer.msg('请选择城市', {icon: 2});
                    return false;
                }
                if(areaId==''||areaId=='0'){
                    //layer.getChildFrame(".areaMsg").html('请选择区');
                    layer.msg('请选择区', {icon: 2});
                    return false;
                }else{
                    //layer.getChildFrame(".areaMsg").html('');
                }
                if(address.trim()==''){
                    //layer.getChildFrame(".area_addressMsg").html('请填写详细地址');
                    layer.msg('请填写详细地址', {icon: 2});
                    return false;
                }else{
                    //layer.getChildFrame(".area_addressMsg").html('');
                }
                if(zipCode.trim()==''){
                    //layer.getChildFrame(".area_addressMsg").html('请填写详细地址');
                    layer.msg('请正确填写', {icon: 2});
                    return false;
                }else{
                    //layer.getChildFrame(".area_addressMsg").html('');
                }
                var formjson = '{';
                formjson += '\"trueName\":\"' + trueName + '\",';//分类id
                formjson += '\"provinceId\":\"' + provinceId + '\",';//分类id
                formjson += '\"cityId\":\"' + cityId + '\",';//城市id
                //三级地区菜单没有的校验
				if(typeof(quval) != "undefined"){
					formjson += '\"areaId\":\"' + areaId + '\",';//地区ID
				}
                formjson += '\"mobPhone\":\"' + mobPhone + '\",';//手机号
                formjson += '\"areaInfo\":\"' + areaInfo + '\",';//地区全称
                formjson += '\"address\":\"' + address + '\",';//详细地址
                formjson += '\"zipCode\":\"' + zipCode + '\"';//邮编
                formjson += '}';
                $.ajax({
                    url:'${base}/myaddress/saveAddress',
                    type:'post',
//                    data:{"trueName":trueName,"proviceId":proviceId,"cityId":cityId,"areaId":areaId,"mobPhone":mobPhone,"address":address,"zipCode":zipCode},
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
            },cancel: function(index){
                layer.close(index);
            }
        });
    }
</script>
<@p.userfooter />
