
//默认 加载   全选，全部金额，全部数量
$(document).ready(function(){
	$('input[type="checkbox"]').attr("checked",true);
	getDisable();
});


//添加数量
$(function(){
	
	 $("#chk_all").click(function() {
		   	$('input[type="checkbox"]').attr("checked",this.checked);
		   	getDisable();
	 });	
	
	
	 //增加数量
	 $(".btnAdd").click(function(){
		var trcart = $(this).parents("td");
		var goodsStorage = trcart.find("input[name='goodsStorage']").val();
         var goodsId = trcart.find("input[name='goodsId']").val();
		var txtBox=$(this).prev();
		var value=txtBox.val();
		value = parseInt(value) + 1;
		if(value<1){
			layer.alert("购买数量不能低于1件课程",8);
			return false;
		}
		if(parseInt(value) >parseInt(goodsStorage)){
			value = goodsStorage;
			layer.alert("该课程最大库存量为"+goodsStorage+"!",9);
		}
         $(txtBox).val(value);//赋值输入框的参数
         getDisable();//如果是checked状态 就刷新下面的结算金额
         $.ajax({
         	url:APP_BASE+'/cart/updateCart',
         	data:'goodsId='+goodsId+'&count='+value,
         	dataType:'json',
            type:'POST',
             success:function(data){
             	$(txtBox).val(value);//赋值输入框的参数
         		trcart.parent().find("input[name='goodscount']").val(value);//隐藏域课程个数
         		var goodsPrice = trcart.find("input[name='goodsPrice']").val();//隐藏域课程单价金额
         		var goodsTotalPrice = toDecimal2(parseFloat(value) * parseFloat(goodsPrice));//获取单价*课程个数
         		trcart.parent().find("span[nc_type='totalprice']").html("¥"+goodsTotalPrice);//赋值小计金额
         		trcart.parent().find("input[name='goodstotal']").val(goodsTotalPrice);//赋值隐藏域金额
         		getDisable();//如果是checked状态 就刷新下面的结算金额
         	}
         });
	});

	//减去数量
	$(".btnCut").click(function(){
		
		var trcart = $(this).parent().parent();
		var goodsId = trcart.find("input[name='goodsId']").val();
		var txtBox=trcart.find("input[name='txtNum']");
		var value=$(txtBox).attr("value");
		value = parseInt(value) - 1;
		
		if(parseInt(value)<=1){
			value = 1;
			$(txtBox).val(value);
			trcart.parent().find("input[name='goodscount']").val(value);//隐藏域课程个数
		}
		$.ajax({
			url:APP_BASE+'/cart/updateCart',
			data:'goodsId='+goodsId+'&count='+value,
	   		dataType:'json',
		    type:'POST',
		    success:function(data){
		    	$(txtBox).val(value);//赋值输入框的参数
				trcart.parent().find("input[name='goodscount']").val(value);//隐藏域课程个数
				var goodsPrice = trcart.find("input[name='goodsPrice']").val();//隐藏域课程单价金额
				var goodsTotalPrice = toDecimal2(parseFloat(value) * parseFloat(goodsPrice));//获取单价*课程个数
				trcart.parent().find("span[nc_type='totalprice']").html("¥"+goodsTotalPrice);//赋值小计金额
				trcart.parent().find("input[name='goodstotal']").val(goodsTotalPrice);//赋值隐藏域金额
				getDisable();//如果是checked状态 就刷新下面的结算金额
			}
		});
	});

	//鼠标操作数量 离开文本框 
	$("input[name='txtNum']").blur(function(){
		var trcart = $(this).parent().parent();
		var goodsStorage = trcart.find("input[name='goodsStorage']").val();
        var goodsId = trcart.find("input[name='goodsId']").val();
		var txtBox=trcart.find("input[name='txtNum']");
		var value=$(txtBox).attr("value");
		
		if(value ==''){
			value = 1;
			layer.alert("购买数量不能低于1件课程",8);
			$(txtBox).val(1);//赋值输入框的参数
		}
		
		if(value < 1){
			layer.alert("购买数量不能低于1件课程",8);
			$(txtBox).val(1);//赋值输入框的参数
		}
		if(parseInt(value) >parseInt(goodsStorage)){
			value = goodsStorage;
			layer.alert("该课程最大库存量为"+goodsStorage+"!",9);
			$(txtBox).val(goodsStorage);//赋值输入框的参数
		}
		
		$.ajax({
			url:APP_BASE+'/cart/updateCart',
			data:'goodsId='+goodsId+'&count='+value,
	   		dataType:'json',
		    type:'POST',
		    success:function(data){
		    	$(txtBox).val(value);//赋值输入框的参数
				trcart.parent().find("input[name='goodscount']").val(value);//隐藏域课程个数
				var goodsPrice = trcart.find("input[name='goodsPrice']").val();//隐藏域课程单价金额
				var goodsTotalPrice = toDecimal2(parseFloat(value) * parseFloat(goodsPrice));//获取单价*课程个数
				trcart.parent().find("span[nc_type='totalprice']").html("¥"+goodsTotalPrice);//赋值小计金额
				trcart.parent().find("input[name='goodstotal']").val(goodsTotalPrice);//赋值隐藏域金额
				getDisable();//如果是checked状态 就刷新下面的结算金额
			 }
		});
	});

});



function toDecimal2(x) {   
    var f = parseFloat(x);   
    if (isNaN(f)) {   
        return false;   
    }   
    var f = Math.round(x*100)/100;   
    var s = f.toString();   
    var rs = s.indexOf('.');   
    if (rs < 0) {   
        rs = s.length;   
        s += '.';   
    }   
    while (s.length <= rs + 2) {   
        s += '0';   
    }   
    return s;   
}  



	/**2J选中框**/
	function selectBox(){
		var len = $("input[name='secondpro']").length;//判断总长度
		var checkedlen = $("input[name='secondpro']:checked").length;//判断选中的长度
		if(checkedlen == len ){
			$("#chk_all").attr("checked",true);
			getDisable()
		}else{
			$("#chk_all").attr("checked",false);
			getDisable()
		}
	 } 
	 
	 
	 
//获取总共价格
function GetTotalPrice(){
	var totalNum=0;
	$("input[name='secondpro']").each(function(i){
	    if($(this).is(':checked')){
	    	totalNum += parseFloat($(this).parents("tr").find(".p-subtotal>input[name='goodstotal']").val());
	    }
	});
	return toDecimal2(totalNum);
} 

//获取总数量
function GetTotalCount(){
	var totalNum=0;
	$("input[name='secondpro']").each(function(i){
	    if($(this).attr("checked") == 'checked'){
	    	totalNum += parseInt($(this).parent().parent().find("input[name='goodscount']").val());
	    }
	});
	return totalNum;
} 

//统一执行的方法
function getDisable(){
	$("div[class='cart-total']").find("b[id='totalprice']").html("¥"+GetTotalPrice());
	$("div[class='cart-total']").find("b[id='totalcount']").html(GetTotalCount());
	$("div[class='cart-total']").find("strong[id='totalprice']").html("¥"+GetTotalPrice());
}



//删除购物车数据
function deletecart(goodsId){

	layer.confirm('确定要移除购物车?',function(index){
	    $.post(APP_BASE+'/cart/deleteCart',{'goodsId':goodsId},function(data){
			if (data) {
				if(data.success){
					layer.msg('删除成功!', 2, function(){
					    location.reload(); //自动关闭后可做一些刷新页面等操作
					});
				}else{
					layer.alert("删除失败",8);
				}
			}
		});
	});
}
//收藏
function saveFav(goodsId){

    $.post(APP_BASE+'/cart/saveFav',{'goodsId':goodsId,'favType':1},function(data){
		if (data) {
			layer.alert(data.success,9);
		}
	});
}



//去购物车
function goToOrder(){
	
	var checkId = "";
	$("input[name='secondpro']").each(function(i){

	    if($(this).is(":checked")){

	    	checkId += $(this).parent().parent().find("input[name='goodsId']").val()+",";
	    }
	});
	if(checkId == ""){
		layer.alert("请选择购买课程",8);
		return false;
	}else{
		checkId = checkId.substring(0, checkId.length-1);
        $.ajax({
            url : APP_BASE+'/checkLogin',
            dataType : 'json',
            success : function(data){
                if(data.success){
                    window.location.href = APP_BASE+"/cart/cartOrder?cartId="+checkId;
                }else{
                    $("#loginDiv").show();
                    $("#returnUrl").val(APP_BASE+"/cart/cartOrder?cartId="+checkId);
                }
            }
        });
	}
}

function changeCaptcha() {
    var captchaImg = APP_BASE + '/generateImage?t=' + Math.random();
    $("#captcha_img").attr("src", captchaImg);
}

function closeLogin(id){
    $("#"+id).hide();
}

