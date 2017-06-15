
//新增本店分类
function addSelectDIV(){
	var divdis = $("#displayclass").html();
	$("#classselect").append(divdis);
}
//最上面分类 点击修改调用的方法
function updateClass() {
    window.location.href = APP_BASE + "/pro/sell";
}
	

	
$(function(){
	//加载地区
	$("#area").change(function(){
		$("#spancity").html("");
		var id = $(this).val();
	  	$.ajax({
             type: "post",
             url: "../pro/getChildArea?id="+id,
             data: '',
             dataType: "json",
			 async:false,
			 success:function(data) {
                 var $li = '<select name="city" class="select" id="city">';
			 	if(data.result!='null'){
				 	var jsonObj = eval("(" + data.result + ")");
					for ( var i = 0; i < jsonObj.length; i++) {
						$li += '<option value='+jsonObj[i].areaId+'>'+jsonObj[i].areaName+'</option>'
					}
			 	}
				$("#spancity").append($li+"</select>");
			}
     	});
	});
	
});

    
    
    
	
