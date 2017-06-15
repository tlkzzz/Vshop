<script type="text/javascript" src="${base}/res/js/common_select.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/ajaxfileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="${base}/res/js/layer/layer.js"></script>
<table class="ncu-table-style">
<tbody>
      <thead>
        <tr>
          <th class="w70"><select name="evalscore" id="gevalscore" nc_type="sform" onchange="reInitDataList();">
              <option value="0" selected=selected>评价</option>
              <option value="5" >好评</option>
              <option value="3" >中评</option>
              <option value="1" >差评</option>
            </select>
          </th>
          <th class="w200 tl"> <select name="havecontent"  id="havecontent" nc_type="sform" onchange="reInitDataList();">
              <option value="0" selected=selected>评论</option>
              <option value="1" >有评论内容</option>
              <option value="2" >无评论内容</option>
            </select>
          </th>
          <th class="w180">评价人</th>
          <th class="tl">宝贝信息</th>
         <!--  <th class="w90">操作</th> -->
        </tr>
      </thead>
	<#if datas?size gt 0>
		<#list datas as evaluateGoods>
		 <tr class="bd-line ncgeval">
			 <td class="ncgeval-good"><span class="ico"></span></td>
			 <td class="tl">
			 	<p>${evaluateGoods.gevalContent}</p>
			 	<p class="date">${evaluateGoods.updateTimeStr}</p>
			 	<p>
			 		<#if evaluateGoods.gevalImage??>
				     	<#list evaluateGoods.gevalImage?split(',') as img>
				     		<#if img!=''>
				     			<img width="50px" height="50px" src="${imgOssServer}${img}" onclick="bigImg(this)"/>
				     		</#if>
				     	</#list>
			     	</#if>
			 	</p>
			 </td>
			 <td>
				 <p><a target="_blank" href="javascript:void(0);">${evaluateGoods.gevalFrommembername}</a></p>
				 <!--   <p>商家信用：0</p> -->
			 </td>
			 <td class="tl">
				<a target="_blank" href="${frontServer}/product/detail?id=${evaluateGoods.gevalGoodsId}">${evaluateGoods.gevalGoodsName}</a> <br/>
			 	${evaluateGoods.gevalGoodsPrice}元
		     </td>
		     <td></td>
		 </tr>
		</#list>
	<#else>
		<tr>
		<td colspan="20" class="norecord"><i>&nbsp;</i><span>暂无符合条件的数据记录</span></td>
		</tr>
	</#if>
</tbody>
 <tfoot>
    <tr>
        <td colspan="20">
            <#import "/commons/page.ftl" as q><!--引入分页-->
            <#if recordCount??>
                <@q.pager pageNo=pageNo pageSize=pageSize recordCount=recordCount toURL="${toUrl}"/>
            </#if>
        </td>
    </tr>
</tfoot>
</table>
<script>
function reInitDataList(){
	var gevalscore = $("#gevalscore").val();
	var havecontent = $("#havecontent").val();
    var div = "#dataListDiv";//显示的list 数据div id 必须传递
    $.ajax({
        async:false,
        url:"${base}/trade/reviewList",//默认加载list 页面
        data:{
        	div:div,
        	gevalscore:gevalscore,
        	havecontent:havecontent
        },
        error:function(){
            layer.msg("通讯失败!" , 1 , 9 );
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

function bigImg(obj){
	layer.open({
	    type: 1,
	    title: false,
	    closeBtn: true,
	    area: '516px',
	    skin: 'layui-layer-nobg', //没有背景色
	    shadeClose: true,
	    content: $(obj),
	    end: function(layero, index){
	    	$(obj).css("display","")
	    }
	});
}
</script>