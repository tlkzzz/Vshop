$(".conter_left ul li").eq(0).css("background","#ccc");
$(".conter_left ul li").eq(0).css("color","#fff");
$(".conter_left ul li").click(function(){
	var index=$(this).index();
	$(".conter_right>ul").eq(index).show().siblings().hide()
	var temp=$(this);
    $(".conter_left ul li").each(function(){
        $(this).css("background","#fff");
        $(this).css("color","#666");
    });
    temp.css("background","#ccc");
    temp.css("color","#fff");
});