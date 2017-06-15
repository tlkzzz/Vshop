<@p.userHeader title="会员首页"/>
<script type="text/javascript" src="${base}/res/js/dialog/dialog.js" id="dialog_js" charset="utf-8"></script> 
<script type="text/javascript" src="${base}/res/js/nc-sideMenu.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/utils.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/layer/layer.js"></script>
<link href="${base}/res/css/font-icons/style.css"  rel="stylesheet" />
<div id="container" class="wrapper">
	<div class="layout">
    	
    	<@u.left  title="我的商品收藏"/>
    	<div class="right-content">
      		<div class="path">
		        <div>
		        	<a href="#?act=member_snsindex">我的商家</a><span>&raquo;</span>
		                        优惠券管理                
		        </div>
		    </div>
      		<div class="main">
       			<div class="wrap">
  					<div class="tabmenu">
    					<ul class="tab pngFix">
 							<li <#if couponIsUser == "0">class="active"<#else> class="normal"</#if> >
 								<a  href="${base}/user/mycoupon/myCouponIndex?couponIsUser=0">未使用</a>
 							</li>
 							<li <#if couponIsUser == "1">class="active"<#else> class="normal"</#if> >
 								<a  href="${base}/user/mycoupon/myCouponIndex?couponIsUser=1">已使用</a>
 							</li>
 							<li <#if couponIsUser == "2">class="active"<#else> class="normal"</#if> >
 								<a  href="${base}/user/mycoupon/myCouponIndex?couponIsUser=2">已过期</a>
 							</li>
 						</ul>
  					</div>
  					
  					<input type="hidden" name="div" id="div" value = "#dataListDiv"/>
  					<div  id="dataListDiv">
  					
  					</div>
  					
				</div>
    		</div>
      	</div>
	</div>
</div>
<script type="text/javascript">
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
		        deletefavotitegoods(items,'goods');
	        }
	  });
	});

    /*界面初始化*/
    function initDataList(){
        $.ajax({
            url:"${base}/user/mycoupon/myCouponList",//默认加载list 页面
            data:{
            	 couponIsUser:"${couponIsUser}",
            	 time:"${time}"
           	},
            dataType:'html',
            type: "POST",
            contentType:"application/x-www-form-urlencoded; charset=utf-8",
            success: function(data){
                $("#dataListDiv").empty();
                $("#dataListDiv").html(data);
                $("#dataListDiv").hide();
                $("#dataListDiv").fadeIn(300);
            },
            error:function(){
                layer.msg('通信失败', {icon: 2});
           	}
        });
    }
    
      //准备删除
    function deletefavotitegoods(favid,favtype){
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