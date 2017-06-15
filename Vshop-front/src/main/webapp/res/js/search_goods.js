$(function(){
    /* 筛选事件 */
    $("dd[nc_type='dd_filter']").click(function(){
    	Img = $(this).find('img');
    	dropParam(Img.attr('alt'),Img.attr('kid'),'del');
        return false;
    });
    $("#search_by_price").click(function(){
        replaceParam('price', $(this).siblings("input:first").val() + '-' + $(this).siblings("input:last").val());
        return false;
    });

    /* 显示方式 */
    $("[nc_type='display_mode']").click(function(){
        $(this).parent().attr('class', $(this).attr('ecvalue')+'-mode');
        setcookie('goodsDisplayMode', $(this).attr('ecvalue'));
        history.go(0);
    });
    
    // 图片延时加载
	/*$(".list_pic img").lazyload({
		placeholder : "./templates/default/images/loading.gif", 
		event : "scroll" 
	}); */
    //列表延时加载
    if($("#div_lazyload").length>0){
    	$('#div_lazyload').datalazyload({dataItem: '.item', loadType: 'item', effect: 'fadeIn', effectTime: 1000 });
    }

    // 筛选的下拉展开
    $(".select").hover(function(){
        $(this).addClass("over").next().css("display","block");
    },function(){
        $(this).removeClass("over").next().css("display","none");
    });
    $(".option").hover(function(){
        $(this).css("display","block");
    },function(){
        $(this).css("display","none");
    });
    $('.list_pic').find('dl').live('mouseout',function(){
        $(this).find('.slide-show').hide();
    });
    /*  */
    $('.slide_tiny').live('mouseover',function(){
        small_image = $(this).attr('nctype');
        $(this).parents('.slide-show').find('img:first').attr('src',small_image);
    });

    //鼠标经过弹出图片信息
    $(".item").hover(
        function() {
            $(this).find(".goods-info").animate({"top": "180px"}, 400, "swing");
        },function() {
            $(this).find(".goods-info").stop(true,false).animate({"top": "230px"}, 400, "swing");
        }
    );
    // 加入购物车
    $('a[nctype="add_cart"]').click(function() {
        var _parent = $(this).parent(), thisTop = _parent.offset().top, thisLeft = _parent.offset().left;
        animatenTop(thisTop, thisLeft), !1;
        eval('var data_str = ' + $(this).attr('data-param'));
        add_to_cart(data_str.spec_id);
    });
    // 立即购买
    $('a[nctype="buynow"]').click(function(){
        eval('var data_str = ' + $(this).attr('data-param'));
        $("#buynow_spec_id").val(data_str.spec_id);
        $("#buynow_quantity").val(1);
    	$("#buynow_form").submit();
    })
	$('a[nctype="groupbuy"]').click(function(){
        eval('var data_str = ' + $(this).attr('data-param'));
		$("#group_id").val(data_str.group_id);
        $("#groupbuy_spec_id").val(data_str.spec_id);
        $("#groupbuy_quantity").val(1);
    	$("#groupbuy_form").submit();
    })
    // 图片切换效果
    $('.goods-pic-scroll-show').find('a').mouseover(function(){
        $(this).parents('li:first').addClass('selected').siblings().removeClass('selected');
        var _src = $(this).find('img').attr('src');
        _src = _src.replace('_tiny.', '_small.');
        $(this).parents('.goods-content').find('.goods-pic').find('img').attr('src', _src);
    });
});
function animatenTop(thisTop, thisLeft) {
    var CopyDiv = '<div id="box" style="top:' + thisTop + "px;left:" + thisLeft + 'px" ></div>', topLength = $(".my-cart").offset().top, leftLength = $(".my-cart").offset().left;
    $("body").append(CopyDiv), $("body").children("#box").animate({
        "width": "0",
        "height": "0",
        "margin-top":"0",
        "top": topLength,
        "left": leftLength,
        "opacity": 0
    }, 1000, function() {
        $(this).remove();
    });
}

function add_to_cart(spec_id)
{
    var url = 'index.php?act=cart&op=add';
    $.getJSON(url, {'spec_id':spec_id, 'quantity':1}, function(data){
    	if(data != null){
    		if (data.done)
            {
                $('#bold_num').html(data.num);
                $('#bold_mly').html(price_format(data.amount));
                $('.ncs_cart_popup').slideDown('slow');
                setTimeout(slideUp_fn, 5000);
                // 头部加载购物车信息
                load_cart_information();
            }
            else
            {
                alert(data.msg);
            }
    	}
    });
}
function setcookie(name,value){
    var Days = 30;   
    var exp  = new Date();   
    exp.setTime(exp.getTime() + Days*24*60*60*1000);   
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();   
}

/* 替换参数 */
function replaceParam(key, value, arg)
{
	if(!arguments[2]) arg = 'string';
    var params = location.search.substr(1).split('&');
    var found  = false;
    for (var i = 0; i < params.length; i++)
    {
        param = params[i];
        arr   = param.split('=');
        pKey  = arr[0];
        // 如果存在分页，跳转到第一页
        if (pKey == 'curpage')
        {
            params[i] = 'curpage=1';
        }
        if(arg == 'string'){
            if (pKey == key)
            {
                params[i] = key + '=' + value;
                found = true;
            }
        }else{
            for(var j = 0; j < key.length; j++){
                if(pKey ==  key[j]){
                    params[i] = key[j] + '=' + value[j];
                    found = true;
                }
            }
        }
    }
    if (!found)
    {
        if (arg == 'string'){
            value = transform_char(value);
            params.push(key + '=' + value);
        }else{
            for(var j = 0; j < key.length; j++){
                params.push(key[j] + '=' + transform_char(value[j]));
            }
        }
    }
    location.assign(SITE_URL + params.join('&'));
}

/* 删除参数 */
function dropParam(key, id, arg)
{
    if(!arguments[2]) arg = 'string';
    var params = location.search.substr(1).split('&');
    for (var i = 0; i < params.length; i++)
    {
        param = params[i];
        arr   = param.split('=');
        pKey  = arr[0];
        if(arg == 'string'){

            if (pKey == key)
            {
                params.splice(i, 1);
            }
        }else if(arg == 'del'){
            pVal = arr[1].split(',')
            for (var j=0; j<pVal.length; j++){
                if(pKey == key && pVal[j] == id){
                    pVal.splice(j, 1);
                    params.splice(i, 1, pKey+'='+pVal);
                }
            }
        }else{
            for(var j = 0; j < key.length; j++){
                if(pKey == key[j]){
                    params.splice(i, 1);i--;
                }
            }
        }
    }
    location.assign(SITE_URL );
}
