<@p.header title="商家中心-评价管理"/>
<script type="text/javascript" src="${base}/res/js/common_select.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/ajaxfileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="${base}/res/js/jquery.raty/jquery.raty.min.js"></script>
<script type="text/javascript">
	// 收缩展开效果
	$(document).ready(function() {
		$(".rate-star").raty({
			path : "${base}/res/js/jquery.raty/img",
			hints : [ '一般', '不错', "很好", '满意', '非常满意' ],
			readOnly : true,
			width : 100,
			score : function() {
				return $(this).attr("data-score")
			}
		})
		
	});
</script>
<div class="layout">
    <div class="sidebar">
    
  </div>
  <div class="right-content">
        <div class="path">
      <div><a href="${base}">商家中心</a> <span>></span>
                评价管理              </div>
    </div>
        <div class="main">
      
<div class="wrap"> 
  
  <!-- 评分统计start -->
  <div class="personal-rating">
    <h4>商家动态评分</h4>
    <table class="seller-rate-info" id="sixmonth">
     <#if store??>
      <tbody>
        <tr>
          <th>
          	<p>宝贝与描述相符</p>
            <p class="rate-star mt5" data-score="${evaluateStore.sevalDesccredit}"></p>
          </th>
          <td><dl class="ncs-rate-column">
              <dt>
              	  <#if evaluateStore.sevalDesccredit!=''>
              		<em style="left:${evaluateStore.sevalDesccredit*20}%;">${evaluateStore.sevalDesccredit?string('0.0')}</em></dt>
             	  <#else>
             	  	<em style="left:0%;">0.0</em></dt>
             	  </#if>
              <dd>非常不满</dd>
              <dd>不满意</dd>
              <dd>一般</dd>
              <dd>满意</dd>
              <dd>非常满意</dd>
            </dl></td>
        </tr>
        <tr>
          <th>
          	<p>商家的服务态度</p>
            <p class="rate-star mt5" data-score="${evaluateStore.sevalServicecredit}"></p>
          </th>
          <td><dl class="ncs-rate-column">
              <dt>
              <#if evaluateStore.sevalServicecredit!=''>
            	<em style="left:${evaluateStore.sevalServicecredit*20}%;">${evaluateStore.sevalServicecredit?string('0.0')}</em></dt>
           	  <#else>
           	  	<em style="left:0%;">0.0</em></dt>
           	  </#if>
              </dt>
              <dd>非常不满</dd>
              <dd>不满意</dd>
              <dd>一般</dd>
              <dd>满意</dd>
              <dd>非常满意</dd>
            </dl></td>
        </tr>
        <tr>
          <th>
          	<p>商家的发货速度</p>
            <p class="rate-star mt5" data-score="${evaluateStore.sevalDeliverycredit}"></p>
          </th>
          <td><dl class="ncs-rate-column">
              <dt>
              	 <#if evaluateStore.sevalDeliverycredit!=''>
	            	<em style="left:${evaluateStore.sevalDeliverycredit*20}%;">${evaluateStore.sevalDeliverycredit?string('0.0')}</em></dt>
	           	  <#else>
	           	  	<em style="left:0%;">0.0</em></dt>
	           	  </#if>	
              </dt>
              <dd>非常不满</dd>
              <dd>不满意</dd>
              <dd>一般</dd>
              <dd>满意</dd>
              <dd>非常满意</dd>
            </dl></td>
        </tr>
      </tbody>
     </#if>
    </table>
  </div>
  <div class="tabmenu">
    <ul id="listpj" class="tab">
      <li class="active"><a href="#?act=store_evaluate&op=list#listpj">来自会员的评价</a></li>
      <!-- <li class="normal"><a href="#?act=store_evaluate&op=list&type=fromseller#listpj">来自商家的评价</a></li>
      <li class="normal"><a href="#?act=store_evaluate&op=list&type=toothers#listpj">给他人的评价</a></li> -->
    </ul>
  </div>
  <form id="goodsevalform" method="get">
    <input type="hidden" name="act" value="store_evaluate"/>
    <input type="hidden" name="op" value="list"/>
    <input type="hidden" name="type" value="fromseller"/>
 </form>
 <!-- 分页的表单 -->
 <form action="javascript:void(0);" method="get" name="queryListForm">
 	<input type="hidden" name="div" id="div" value = "#dataListDiv"/>
 </form>
	<div class="main" id="dataListDiv">
	
	</div>
</div>
<script type="text/javascript">
/*界面初始化*/
$(function(){
    initDataList();
});
/*初始化*/
function initDataList(){
    var div = "#dataListDiv";//显示的list 数据div id 必须传递
    $.ajax({
        async:false,
        url:"${base}/trade/reviewList",//默认加载list 页面
        data:{div:div},
        error:function(){
            layer.msg("通讯失败!" , 1 , 9 );
           /* frameControl.lhDgFalInfo("通讯失败!");*/
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
<!-- <script type="text/javascript">
$(function()
{
	$('*[nc_type="sform"]').change(function(){
		$("#goodsevalform").submit();
	});
});

    // 收缩展开效果
    $(document).ready(function(){
        $(".sidebar dl dt").click(function(){
            $(this).toggleClass("hou");
            var sidebar_id = $(this).attr("id");
            var sidebar_dd = $(this).next("dd");
            sidebar_dd.slideToggle("slow",function(){
                $.cookie(COOKIE_PRE+sidebar_id, sidebar_dd.css("display"), { expires: 7, path: '/'});
            });
        });
        $('.sidebar').find('dd').css('display','none');
    });
</script>   -->  
</div>
  </div>
    <div class="clear"></div>
</div>
</body>
</html>
<@p.footer/>