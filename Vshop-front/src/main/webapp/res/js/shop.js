
/** header.php **/
function slideUp_fn()
{
    $('.ncs_cart_popup').slideUp('slow');
}
//
$(function(){
	$(".ncs-search-input-text").focus(function(){
  		$(".search a").css("display","block");
	});
	$("#formSearch").blur(function(){
  		$(".search a").css("display","none");
	});
});
// 头部导航下拉菜单
	var timeout         = 500;
	var closetimer		= 0;
	var ddmenuitem      = 0;

	function jsddm_open()
	{	jsddm_canceltimer();
		jsddm_close();
		ddmenuitem = $(this).find('.sub').eq(0).css('visibility', 'visible');}

	function jsddm_close()
	{	if(ddmenuitem) ddmenuitem.css('visibility', 'hidden');}

	function jsddm_timer()
	{	closetimer = window.setTimeout(jsddm_close, timeout);}

	function jsddm_canceltimer()
	{	if(closetimer)
		{	window.clearTimeout(closetimer);
			closetimer = null;}}

	$(document).ready(function()
	{	$('#jsddm > div').bind('mouseover', jsddm_open);
		$('#jsddm > div').bind('mouseout',  jsddm_timer);});


/** goods.php **/
$(function(){	
	// 课程小图关联大图选中后样式变化
	$('.nc-zoom-gallery').click(function(){
		$('.zoom-desc').find('.nc-zoom-gallery').removeClass('hovered');
		$(this).addClass('hovered');
	});
	// 课程内容部分折叠收起侧边栏控制
	$('#abc').click(function(){
  		$('.layout').toggleClass('expanded');
	});
	// 课程内容介绍Tab样式切换控制
	$('#categorymenu').find("li").click(function(){
		$('#categorymenu').find("li").removeClass('current');
		$(this).addClass('current');
	});
	// 课程详情默认情况下显示全部
	$('#tabGoodsIntro').click(function(){
		$('.bd').css('display','');
		$('.hd').css('display','');	
	});
	// 点击评价隐藏其他以及其标题栏
	$('#tabGoodsRate').click(function(){
		$('.bd').css('display','none');
		$('#ncGoodsRate').css('display','');
		$('.hd').css('display','none');
	});
	// 点击成交隐藏其他以及其标题
	$('#tabGoodsTraded').click(function(){
		$('.bd').css('display','none');
		$('#ncGoodsTraded').css('display','');
		$('.hd').css('display','none');
	});
	// 点击咨询隐藏其他以及其标题
	$('#tabGuestbook').click(function(){
		$('.bd').css('display','none');
		$('#ncGuestbook').css('display','');
		$('.hd').css('display','none');
	});
	//课程排行Tab切换
	$(".ncs-top-tab > li > a").mouseover(function(e) {
		if (e.target == this) {
			var tabs = $(this).parent().parent().children("li");
			var panels = $(this).parent().parent().parent().children(".ncs-top-panel");
			var index = $.inArray(this, $(this).parent().parent().find("a"));
			if (panels.eq(index)[0]) {
				tabs.removeClass("current ").eq(index).addClass("current ");
				panels.addClass("hide").eq(index).removeClass("hide");
			}
		}
	});
	//信用评价动态评分打分人次Tab切换
	$(".ncs-rate-tab > li > a").mouseover(function(e) {
		if (e.target == this) {
			var tabs = $(this).parent().parent().children("li");
			var panels = $(this).parent().parent().parent().children(".ncs-rate-panel");
			var index = $.inArray(this, $(this).parent().parent().find("a"));
			if (panels.eq(index)[0]) {
				tabs.removeClass("current ").eq(index).addClass("current ");
				panels.addClass("hide").eq(index).removeClass("hide");
			}
		}
	});
		
//触及显示缩略图	
	$('.goods-pic > .thumb').hover(
		function(){
			$(this).next().css('display','block');
		},
		function(){
			$(this).next().css('display','none');
		}
	);
	
	/* 课程购买数量增减js */
	// 增加
	$('.increase').click(function(){
		num = parseInt($('#quantity').val());
		max = parseInt($('[nctype="goods_stock"]').text());
		if(num < max){
			$('#quantity').val(num+1);
		}
	});
	//减少
	$('.decrease').click(function(){
		num = parseInt($('#quantity').val());
		if(num > 1){
			$('#quantity').val(num-1);
		}
	});
	
	// 搜索价格不能填写非数字。
	var re = /^[1-9]+[0-9]*(\.\d*)?$|^0(\.\d*)?$/;
	$('input[name="start_price"]').change(function(){
		if(!re.test($(this).val())){
			$(this).val('');
		}
	});
	$('input[name="end_price"]').change(function(){
		if(!re.test($(this).val())){
			$(this).val('');
		}
	});
});

/* add cart */
function add_to_cart(spec_id, quantity)
{
    var url = 'index.php?act=cart&op=add';
    $.getJSON(url, {'spec_id':spec_id, 'quantity':quantity}, function(data){
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

/** left.php **/
// 课程分类
function class_list(obj){
	var stc_id=$(obj).attr('span_id');
	var span_class=$(obj).attr('class');
	if(span_class=='ico-block') {
		$("#stc_"+stc_id).show();
		$(obj).html('<em>-</em>');
		$(obj).attr('class','ico-none');
	}else{
		$("#stc_"+stc_id).hide();
		$(obj).html('<em>+</em>');
		$(obj).attr('class','ico-block');
	}
}