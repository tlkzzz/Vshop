function saveshopstatgoods(goodsid,goodsname,storeid){
	$.ajax({
        type: "post",
        url: SITEURL+'/shopStatGoods/saveShopStatGoods',
        data:{'goodsId':goodsid,'storeId':storeid,'goodsName':goodsname},
        dataType: "json",
        async:false,
        success:function(data) {
            /*if(data.success){
                alert(data.message);
                setTimeout("window.location.reload()", 3200);
            }else{
                alert(data.message);
                // $("#submitBtn").removeAttr("disabled");
            }*/
        }
    }); 	
}