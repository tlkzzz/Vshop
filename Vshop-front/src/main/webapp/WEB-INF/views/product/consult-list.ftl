<script src="${base}/res/js/layer/layer.js"></script>
    <div class="title hd">
      <h4 class="titbar">商品咨询</h4>
    </div>
    <div class="content bd" id="ncGuestbook"> 
      <div class="ncg-guestbook">
        <ol class="consult-list">
		<#if consultList??>
			<#list consultList as consult>
				<li>
					<dl class="ask-user">
						<dt>
							<#if consult.memberId==0>
								游客
							</#if>
							<#if consult.memberId!=0>
								<#if consult.isanonymous>
									匿名
								<#else>
									${consult.cmemberName}
								</#if>                                	
							</#if>
						</dt>
						<dd>
							<span class="date">${consult.consultAddtimeStr}</span>
							<p class="txt">${consult.consultContent}</p>
						</dd>
					</dl>
					<dl class="answ-user">
						<dt>${consult.storeName}</dt>
						<dd><p class="txt">${consult.consultReply}</p></dd>
					</dl>
				</li>
			</#list>
		</#if>
	</ol>
	<div class="page-mod">
		<tr>
			<th colspan="7">
				<#import "/commons/page.ftl" as q><!--引入分页 -->
				<#if pager??>
					<@q.pager pageNo=pageNo pageSize=pageSize recordCount=recordCount toURL="${toUrl}"/>
				</#if>
			</th>
		</tr>
	</div>
	<#assign goodsBaseTag =newTag("goodsBaseInfoTag")>
	<#assign goodsInfo =goodsBaseTag("{'goodsid':'${goodsId}'}")>
	<div class="product-consult" id="kfzx">
            <!-- <h3><b>客服咨询</b></h3><a name="explain"></a> -->

            <p class="explain">购买之前，如有问题，请向商品汇咨询。
                <a href="javascript:;" style="color: #3366CC;" onclick="showConsult()">我要咨询</a></p>
            <dl class="form-consult" style="display:none">
                <form id="consultForm" method="post" action="${base}/product/consult" name="queryListForm">

                    <input type="hidden" name="goodsId" value="${goodsInfo.goodsId}"/>
                    <input type="hidden" name="cgoodsName" value="${goodsInfo.goodsName}"/>
                    <input type="hidden" name="storeId" value="${goodsInfo.storeId}"/>
                    <input type="hidden" name="div" id="div" value="#kfzxData"/>
                    <dt>内容：</dt>
                    <dd><textarea class="textarea" name="consultContent" id="consultContent" style="width: 600px; height: 100px;"></textarea>

                        <p class="text-note">※ 请输入不要超过 <b>150</b> 个字</p>
                        <button type="button" class="btng" onclick="subConsult()">提交</button>
                    </dd>
                </form>
            </dl>
        </div>
      </div>
    </div>
    
 <script type="text/javascript">
 //咨询
function showConsult() {
    $(".form-consult").show();
}
//提交
function subConsult() {
    var content = $("#consultContent").val();
    if (content != '') {
        $.ajax({
            url: '${base}/product/saveConsult',
            dataType: 'json',
            data: $("#consultForm").serializeArray(),
            success: function (data) {
                if (data.success) {
                    //layer.msg(data.msg, 1, 9);
                    layer.msg("咨询发布成功" , {icon:1});
                    $("#consultContent").val("");
                    $(".form-consult").hide();
                    initKfzx();
                } else {
                	layer.msg("咨询发布失败" , {icon:2});
                }
            }
        })
    }
}

//初始化客服咨询
function initKfzx() {
	var obj = $("[toAction=consult]");
	changeTable(obj);
}
</script>
