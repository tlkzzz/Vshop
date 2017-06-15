<@p.userHeader title="会员首页"/>
<script type="text/javascript" src="${base}/res/js/dialog/dialog.js" id="dialog_js" charset="utf-8"></script> 
<script type="text/javascript" src="${base}/res/js/nc-sideMenu.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/utils.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/sns/sns.js" charset="utf-8"></script> 
<script type="text/javascript" src="${base}/res/js/jcarousel/jquery.jcarousel.min.js"></script> 
<script type="text/javascript" src="${base}/res/js/layer/layer.js"></script>
<link href="${base}/res/css/font-icons/style.css"  rel="stylesheet" />
<link href="${base}/res/js/jcarousel/skins/tango/skin.css" rel="stylesheet" type="text/css">
<style type="text/css">
.jcarousel-skin-tango { background-color: #F4F9FD; border: solid 1px #AED2FF;}
.jcarousel-skin-tango a { background-color: #FFF; width: 120px; height: 120px; display: inline-block; border: solid 1px #C4D5E0; }
.jcarousel-skin-tango .jcarousel-clip-horizontal { width: 660px !important; height: 130px !important;}
.jcarousel-skin-tango .jcarousel-item { height: 130px !important;}
.jcarousel-skin-tango .jcarousel-container-horizontal { width: 660px !important;}
</style>
<div id="container" class="wrapper">
	<div class="layout">
    	<@u.left  title="我的商家收藏"/>
    	
    	<div class="right-content">
      		<div class="path">
        		<div>
        			<a href="#?act=member_snsindex">我的商家</a><span>&raquo;</span>
  					收藏商家                  
                </div>
      		</div>
      		<div class="main">
				<div class="wrap">
  					<div class="tabmenu">
    					<ul class="tab pngFix">
  							<li class="normal"><a  href="${base}/myuser/goodsFavIndex">收藏商品</a></li>
  							<li class="active"><a  href="#">收藏商家</a></li>
  						</ul>
  					</div>
  					<form id="acct-form" method="post" action="" name ="queryListForm">
	  					<input type="hidden" name="div" id="div" value = "#dataListDiv"/>
	  					<div  id="dataListDiv">
	  					     
	  					</div>
  					</form>
				</div>
      		</div>
    	</div>
	</div>
</div>

<script type="text/javascript">
function get_store_goods(store_id,goods_count){
	if(goods_count < 1) return ;
	var store=$("#store-goods-"+store_id);
	var store_arrow=$("#store-arrow-"+store_id);
	var store_display=store.css("display");
	var store_goods=store.find("ul").html();
	if(store_goods == '') {
		store.find("ul").html('<li><img src="${base}/templates/default/images/loading.gif" alt="loading..." ></li>');
		store.show();
		store_arrow.attr("class","arrow-up");
		var ajaxurl = '#?act=member_favorites&op=store_goods&store_id='+store_id;
		var store_goods = $.ajax({url: ajaxurl,async: false}).responseText;
		store.find("ul").html(store_goods);
		store.find("ul").jcarousel({visible: 5});//图片轮换
	}else{
		if(store_display == 'none') {
			store_arrow.attr("class","arrow-up");
			store.show();
		}else{
			store_arrow.attr("class","arrow-down");
			store.hide();
		}
	}
}

	$(function(){
	    initDataList();
	     //批量删除
       $('a[nc_type="batchbuttons"]').live("click",function(){
             
			 /* 是否有选择 */
	        if($('.checkitem:checked').length == 0){    //没有选择
	            layer.msg("请至少选择一项",{icon:2});
	            return false;
	        }else{
	            /* 获取选中的项 */
		        var items = '';
		        $('.checkitem:checked').each(function(){
		            items += this.value + ',';
		        });
		        items = items.substr(0, (items.length - 1));
		        deletefavotiteStore(items,'store');
	        }
	  });
	});

    /*界面初始化*/
    function initDataList(){
        var div = "#dataListDiv";//显示的list 数据div id 必须传递
        $.ajax({
            url:"${base}/myuser/storeFavList",//默认加载list 页面
            data:{div:div},
            error:function(){
                 layer.msg('通信失败', {icon: 2});
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
    
    //准备删除
    function deletefavotiteStore(favid,favtype){
        var url = '${base}/myuser/deleteAllFav';
	        layer.confirm('确定删除?', function(){
	            $.ajax({
	                type: "post",
	                url: url,
	                data: {'favIds':favid,'favType':favtype},
	                dataType: "json",
	                async:false,
	                success:function(data) {
	                    if(data.success =="true"){
	                         layer.msg(data.result,{icon:1},initDataList());
	                    }else{
	                         layer.msg('取消失败',{icon:2});
	                    }
	                }
	            });
	        });
    }
</script>
<@p.userfooter/>