<@p.header title="商家中心-咨询管理"/>
<div class="layout">
    <div class="sidebar">
    
    </div>
  <div class="right-content">
        <div class="path">
      <div><a href="#">商家中心</a> <span>></span>
                <a href="#"/>咨询管理</a><span>></span>全部咨询</div>
    </div>
    
   <form method="post" action="" name="queryForm" id="queryForm">
   <input id="consultReply" type="hidden" value="${consultReply}" name="consultReply" >
   <input id="cur" type="hidden" value="${cur}" name="cur" >
   <input id="apm" type="hidden" value="${apm}" name="apm" >
   </form>
   <div class="main">
		<div class="wrap">
		  <div class="tabmenu">
		  <ul class="tab pngFix">
			<li <#if (cur='index')>class="active"<#else>class="normal"</#if>><a href="${base}/consult/index?cur=index">全部咨询</a></li>
			<li <#if (cur='noreply')>class="active"<#else>class="normal"</#if>><a href="${base}/consult/index?consultReply=0&cur=noreply">未回复咨询</a></li>
			<li <#if (cur='reply')>class="active"<#else>class="normal"</#if>><a href="${base}/consult/index?consultReply=1&cur=reply">已回复咨询</a></li>
		  </ul>
		  </div>
		  <table class="ncu-table-style order">
		    <thead>
		      <tr>
		        <th class="w30"></th>
		        <th>咨询/回复</th>
		        <th class="w30"></th>
		        <th class="w90">操作</th>
		      </tr>
		      <tr>
		        <td class="tc"><input id="all" type="checkbox" class="checkall" /></td>
		        <td colspan="20"><label for="all">全选</label>
		          <a class="ncu-btn1" nc_type="batchbutton" uri="" name="id"><span>删除</span></a></td>
		      </tr>
		    </thead>
		    <tbody>
		    <#if datas?? >
		    	<#list datas as data>
			      <tr>
			        <td colspan="19" class="sep-row"></td>
			      </tr>
			      <tr>
			        <th colspan="20" class="tl"><input type="checkbox"  value="${data.consultId}" class="checkitem ml10 mr10" />
			          <span><a href="${frontServer}/product/detail?id=${data.goodsId}" target="_blank">${data.cgoodsName }</a></span><span class="ml20">咨询用户：</span>
			          ${data.cmemberName}<span class="ml20">咨询时间：<em class="goods-time">${data.consultAddtimeStr?string('yyyy-MM-dd')}</em></span></th>
			      </tr>
			      <tr>
			        <td class="bdl w30"></td>
			        <td class="tl"><strong>咨询内容：</strong><span class="gray">${data.consultContent}</span></td>
			        <td></td>
			        <td rowspan="2" class="bdl bdr"><p><a href="javascript:void(0);" class="edit" nc_type="dialog" dialog_id="my_qa_edit_reply" dialog_title="回复咨询" dialog_width="480" onclick="updateConsult('${data.consultId}')">回复</a></p>
			                    <p> <a href="javascript:void(0)" onclick="delConsult('${data.consultId}');" class="ncu-btn2 mt5">删除</a> </p></td>
			      </tr>
			      <tr>
			        <td class="bdl"></td>
			        <td class="tl"><strong>我的回复：</strong><span class="gray">${data.consultReply}</span><#if data.consultReply?? && data.consultReplyTimeStr><span class="ml10 goods-time">回复时间：${data.consultReplyTimeStr?string('yyyy-MM-dd')}</span></#if></td>
			        <td></td>
			      </tr>
		       </#list >
		     <#else >
			     <tbody>
			           <tr>
			              <td colspan="20" class="norecord"><i>&nbsp;</i><span>暂无符合条件的数据记录</span></td>
			           </tr>
			      </tbody>
		      </#if>
		    </tbody>
		    <tfoot>
		      <tr>
		        <td colspan="20">
		           <#import "/trade/page.ftl" as q><!--引入分页-->
	               <#if recordCount??>
	                   <@q.pager pageNo=pageNo pageSize=pageSize recordCount=recordCount toURL="${toUrl}"/>
	               </#if>
		        </td>
		   	  </tr>
		   	   <!-- <tr>
		        <td class="tc"><input id="all" type="checkbox" class="checkall" /></td>
		        <td colspan="20"><label for="all2">全选</label>
		          <a href="javascript:void(0);" class="ncu-btn1" nc_type="batchbutton" uri="#?act=store_consult&op=drop_consult" name="id" confirm="您确定要删除吗?"><span>删除</span></a>
		          <div class="pagination"><ul><li><span>首页</span></li><li><span>上一页</span></li><li><span class="currentpage">1</span></li><li><span>下一页</span></li><li><span>末页</span></li></ul></div></td>
		      </tr> -->
		    </tfoot>
		  </table>
		</div>
	</div>
</div>
    <div class="clear"></div>
</div>
 <script type="text/javascript">
	//收缩展开效果
	$(document).ready(function() {
		$(".sidebar dl dt").click(function(){
			$(this).toggleClass("hou");
			var sidebar_id = $(this).attr("id");
			var sidebar_dd = $(this).next("dd");
			sidebar_dd.slideToggle("slow", function() {
				$.cookie(COOKIE_PRE + sidebar_id, sidebar_dd.css("display"), {
					expires :7,
					path : '/'
				});
			});
		});
	});
	
	
	function delConsult(id){
        var url = "${base}/consult/delete?replyId="+id;
        layer.confirm('确定删除?', function(){
            $.ajax({
                type: "post",
                url: url,
                data: null,
                dataType: "json",
                async:false,
                success:function(data) {
                    if(data.success){
                        layer.msg('删除成功', {icon: 1},function(){location.reload();});
                    }else{
                        layer.msg('删除失败', {icon: 2});
                    }
                }
            });
        });
    }
	
	
	
	 function updateConsult(id) {
         layer.open({
             type: 2,
             move: false,
             shade: false,
             title: '回复',
             content: ['${base}/consult/findById?replyId='+id, 'no'],
             area: ['450px', '200px'],
             btns: 2,
             btn: ['确定', '取消'],
             yes: function (index) {
            	var consultId = layer.getChildFrame('#consultId').val(); //具体地址
            	if(consultId==''){
            		layer.msg('提交失败', {icon: 2});
                    return false;
            	}
            	var content = layer.getChildFrame('#content').val(); //具体地址
                if(content==''){
                    layer.msg('回复内容不能为空', {icon: 2});
                    return false;
                }
                 $.ajax({
                     url: '${base}/consult/edit',
                     type: 'post',
                     data: {consultReply:content, consultId:consultId},
                     dataType: 'json',
                     success: function (data) {
                         if (data.success == true ) {
                             layer.msg('修改成功', {icon: 1}, initDataList());
                         } else {
                             layer.msg('修改失败', {icon: 2});
                         }
                     }, error: function (data) {
                         layer.msg('通信失败', {icon: 2});
                     }
                 });
                 layer.close(index); //一般设定yes回调，必须进行手工关闭
             }, cancel: function (index) {
                 layer.close(index);
             }
         });
     }
	   /* 批量操作按钮 */
	 $('a[nc_type="batchbutton"]').click(function(){
		var items = '';
		var len = $('.checkitem:checked').length;
        $('.checkitem:checked').each(function(i){
        	if(i == len - 1){
        		items += this.value;
        	}else{
        		items += this.value + ',';
        	}
            
        });
        var url = "${base}/consult/batchDelete?replyIds="+items;
        layer.confirm('确定删除?', function(){
            $.ajax({
                type: "post",
                url: url,
                data: null,
                dataType: "json",
                async:false,
                success:function(data) {
                    if(data.success){
                        layer.msg('删除成功', {icon: 1},initDataList());
                    }else{
                        layer.msg('删除失败', {icon: 2});
                    }
                }
            });
        });
        
	});
 function initDataList(){
	 location.reload();
 }
</script>
</body>
</html>
<@p.footer/>