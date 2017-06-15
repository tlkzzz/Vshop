<@p.userHeader title="会员首页"/>
<script type="text/javascript" src="${base}/res/js/dialog/dialog.js" id="dialog_js" charset="utf-8"></script> 
<script type="text/javascript" src="${base}/res/js/nc-sideMenu.js" charset="utf-8"></script>
<script type="text/javascript" src="${base}/res/js/utils.js" charset="utf-8"></script>
<link href="${base}/res/css/font-icons/style.css"  rel="stylesheet" />
<div id="container" class="wrapper">
	<div class="layout">
   		
   		<@u.left  title="我的咨询"/>
    	<div class="right-content">
      		<div class="path">
        		<div>
        			<a href="#">我的商品</a><span>&raquo;</span>
                    <a href="#">我的咨询</a><span>&raquo;</span>
                                       全部咨询                
             	</div>
      		</div>
      		<div class="main">
				<div class="wrap">
  					<div class="tabmenu">
    					<ul class="tab pngFix">
  							<li <#if (cur='index')>class="active"<#else>class="normal"</#if>><a  href="${base}/myconsult/index?cur=index">全部咨询</a></li>
  							<li <#if (cur='noreply')>class="active"<#else>class="normal"</#if>><a  href="${base}/myconsult/index?consultReply=0&cur=noreply">未回复咨询</a></li>
  							<li <#if (cur='reply')>class="active"<#else>class="normal"</#if>><a  href="${base}/myconsult/index?consultReply=1&cur=reply">已回复咨询</a></li>
  						</ul>
  					</div>
  					<table class="ncu-table-style order">
    					<thead>
      						<tr>
        						<th class="w30"></th>
        						<th>咨询/回复</th>
        						<th class="w30"></th>
      						</tr>
    					</thead>
    					<tbody>
    					<#assign consultTag = newTag("consultTag") />
    					<#assign consultList = consultTag("{'pageNo':'${pageNo}','pageSize':'${pageSize}','tagDataType':'2','consultReply':'${consultReply}'}") />
    					<#if consultList??>
    					<#list consultList as consults>
    						<tr>
        						<td colspan="19" class="sep-row"></td>
      						</tr>
      						<tr>
        						<th colspan="20">
        							<span class="ml10"><a href="${base}/product/detail?id=${consults.goodsId}" target="_blank">${consults.cgoodsName}</a></span>
        							<span class="ml20">咨询时间：<em class="goods-time">${consults.consultAddtimeStr?string('yyyy-MM-dd HH:mm:ss')}</em></span>
        						</th>
      						</tr>
      						<tr>
						        <td class="tl bdl"></td>
						        <td class="tl"><strong>咨询内容：</strong><span class="gray">${consults.consultContent }</span></td>
						        <td class="bdr"></td>
						    </tr>
						    <#if (consults.consultReply??)>
				        	<tr>
						    	<td class="tl bdl"></td>
						        <td class="tl"><strong>回复时间：</strong><span class="gray">${consults.consultReply}</span><span class="ml10 goods-time">(${consults.consultReplyTimeStr?string('yyyy-MM-dd HH:mm:ss')})</span></td>
						        <td class="bdr"></td>
    						</tr>
    						</#if>
              				<tr>
						    	<td colspan="19" class="sep-row"></td>
						    </tr>
    					</#list>
    					</#if>
  							
                        </tbody>
    					<tfoot>
            				<tr>
        						<td colspan="20">
        							<#assign recordCount = consultTag("{'tagDataType':'5','consultReply':'${consultReply}'}") />
        							<#import "/commons/usertagpage.ftl" as q><!--引入分页-->
				                    <#if recordCount??>
				                        <@q.pager pageNo=pageNo pageSize=pageSize recordCount=recordCount toURL="${toUrl}"/>
				                    </#if>
        						</td>
      						</tr>
          				</tfoot>
  					</table>
				</div>
      		</div>
    	</div>
	</div>
</div>

<script type="text/javascript">
</script>
<@p.userfooter/>