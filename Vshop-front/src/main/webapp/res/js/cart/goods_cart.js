/*function drop_cart_item(goods_id, spec_id){
    var tr = $('#cart_item_' + goods_id);
    $.ajax({
    	url : "${base}/cart/deleteCart",
        type : 'post',
        data : {'goodsId' : goods_id,'specId' : spec_id},
        dataType : 'json',
        success : function(data){
            if(data.success){
            	location.reload();
            }else{
            	layer.msg("删除购物车失败!",{icon:2});
            }
        }
    });
}*/
function change_quantity(goods_price, store_id, cart_id, input){
    var subtotal_span = $('#item' + cart_id + '_subtotal');
    var amount_span = $('#cart' + store_id + '_amount');
    //暂存为局部变量，否则如果用户输入过快有可能造成前后值不一致的问题
    var _v = input.value;
    $.ajax({
    	url : "${base}/cart/updateCartCount",
        type : 'post',
        data : {'cartId' : cart_id,'count' : _v},
        dataType : 'json',
        success : function(data){
            if(data.success){
            	//更新成功
                $(input).attr('changed', _v);
                subtotal_span.html(number_format(result.subtotal,2));
                amount_span.html(number_format(result.amount,2));
            }else{
            	//更新失败
                alert(result.msg);
                $(input).val($(input).attr('changed'));
            }
        }
    });
    $.getJSON('index.php?act=cart&op=update&spec_id=' + spec_id + '&quantity=' + _v, function(result){
        if(result.done){
            //更新成功
            $(input).attr('changed', _v);
            subtotal_span.html(number_format(result.subtotal,2));
            amount_span.html(number_format(result.amount,2));
        }
        else{
            //更新失败
            alert(result.msg);
            $(input).val($(input).attr('changed'));
        }
    });
}
function decrease_quantity(cart_id){
    var item = $('#input_item_' + cart_id);
    var orig = Number(item.val());
    if(orig > 1){
        item.val(orig - 1);
        item.keyup();
    }
}
function add_quantity(cart_id){
    var item = $('#input_item_' + cart_id);
    var orig = Number(item.val());
    item.val(orig + 1);
    item.keyup();
}

