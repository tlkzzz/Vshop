<@p.userHeader title="我的积分"/>
<script type="text/javascript" src="${base}/res/js/dialog/dialog.js" id="dialog_js" charset="utf-8"></script> 
<script type="text/javascript" src="${base}/res/js/nc-sideMenu.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/utils.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/sns/sns.js" charset="utf-8"></script> 
<script type="text/javascript" src="${base}/res/js/jcarousel/jquery.jcarousel.min.js"></script> 
<script type="text/javascript" src="${base}/res/js/layer/layer.js"></script>
<script type="text/javascript" src="${base}/res/js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<link href="${base}/res/css/font-icons/style.css"  rel="stylesheet" />
<link href="${base}/res/js/jcarousel/skins/tango/skin.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${base}/res/js/jquery-ui/themes/ui-lightness/jquery.ui.css"/>
<style type="text/css">
.jcarousel-skin-tango { background-color: #F4F9FD; border: solid 1px #AED2FF;}
.jcarousel-skin-tango a { background-color: #FFF; width: 120px; height: 120px; display: inline-block; border: solid 1px #C4D5E0; }
.jcarousel-skin-tango .jcarousel-clip-horizontal { width: 660px !important; height: 130px !important;}
.jcarousel-skin-tango .jcarousel-item { height: 130px !important;}
.jcarousel-skin-tango .jcarousel-container-horizontal { width: 660px !important;}
</style>
<div id="container" class="wrapper">
	<div class="layout">
    	<@u.left  title="我的积分"/>
    	
    	<div class="right-content">
    	    <div class="path">
        		<div>
        			<a href="#?act=member_snsindex">我的商家</a><span>&raquo;</span>
  					我的积分     
                </div>
      		</div>
      		<div class="main">
				<div class="wrap">
  				 <div class="tabmenu">
				    <ul class="tab pngFix">
				         <li class="active"><a  href="#">积分明细</a></li>
				    </ul>
				    <div class="text-intro">积分总数：${member.memberConsumePoints}</div>
				  </div>
				  <form  id="acct-form" method="post" action="" name ="queryListForm">
				    <table class="search-form">
				      <input type="hidden" name="act" value="member_points" />
				      <tr>
				       <!--  <th>操作：</th>
				        <td class="w100"><select name="stage">
				            <option value="" selected=selected>-请选择-</option>
				            <option value="regist" >注册</option>
				            <option value="login" >登录</option>
				            <option value="comments" >商品评论</option>
				            <option value="order" >订单消费</option>
				            <option value="system" >积分管理</option>
				            <option value="pointorder" >礼品兑换</option>
				            <option value="app" >积分兑换</option>
				          </select></td> -->
				        <th>添加时间：</th>
				        <td><input class="txt date" type="text"  id="query_start_time" name="startTime" class="txt Wdate" value="${startTime}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
		                    <label for="query_start_time">~</label>
		                    <input class="txt date" type="text" id="query_end_time" name="endTime" class="txt Wdate" value="${endTime}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
		                </td>
				        
				        <th>描述：</th>
				        <!-- <td class="w150"><input type="text" class="text" id="description" name="description" value=""></td> -->
				        <td class="w90 tc"><input type="submit" class="submit" value="搜索" /></td>
				      </tr>
				    </table>
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
	$(function(){
		initDataList();
	});	
    /*界面初始化*/
    function initDataList(){
        var div = "#dataListDiv";//显示的list 数据div id 必须传递
        $.ajax({
            async:false,
            url:"${base}/user/shoppoints/mypointslogList",//默认加载list 页面
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
    
   
</script>
<@p.userfooter/>