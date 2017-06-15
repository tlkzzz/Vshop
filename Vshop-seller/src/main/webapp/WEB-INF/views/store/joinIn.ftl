<@p.header title="选择商家类型"/>
<div class="layout">
    <div class="wrap-shadow">
        <div class="wrap-all">
            <div class="chart">
                <div class="pos_x1 bg_a2" title="请选择商家分类"></div>
                <div class="pos_x2 bg_b1" title="填写院长和商家信息"></div>
                <div class="pos_x3 bg_c" title="完成"></div>
            </div>
            <div class="grade-shop">
                <table>
                    <tbody>
                    <tr>
                        <th class="w100">系统默认</th>
                        <td class="w150">
	                       <!--  <dl>
	                            <dt>商品数：</dt>
	                            <dd>100</dd>
	                        </dl>
                            <dl>
                                <dt>模板数：</dt>
                                <dd>11</dd>
                            </dl>
                            <dl>
                                <dt>收费标准：</dt>
                                <dd>100元/年</dd>
                            </dl> -->
                            <dl>
                            <dt>需要审核：</dt>
                            <dd>
                              	  是              
                            </dd>
                            </dl>
                            </td>
                        <td class="w120"><dl>
                            <dt>附加功能：</dt>
                            <dd>无</dd></td>
                        <td><dl>
                            <dd>设立商家需要联系院长！</dd>
                        </dl></td>
                        <td class="w120"><a href="${base}/joinIn/step2" class="ncu-btn4 w80 ml10">设立商家</a></td>
                    </tr>
                    </tbody>
                    <tfoot>
                    <tr><td colspan="20" class="tc"><a class="ncu-btn5" href="${base}">返&nbsp;&nbsp;&nbsp;&nbsp;回</a></td></tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>
</div>
<div id="footer" >
    <div class="wrapper">
<!--         <p><a href="http://192.168.1.220">首页</a> -->
<!--             | <a  href="#?act=article&article_id=24">招聘英才</a> -->
<!--             | <a  href="#?act=article&article_id=25">广告合作</a> -->
<!--             | <a  href="#?act=article&article_id=23">联系我们</a> -->
<!--             | <a  href="#?act=article&amp;article_id=22">关于我们</a> -->
<!--         </p> -->
        Copyright 2016-2017 磁石世纪（北京）投资管理有限公司 版权所有  京ICP备16011767号.
<!--         &nbsp;&nbsp; -->
<!--         ICP证： -->
        <br/>
<!--         <div class="footer-logo"> -->
<!--             <ul> -->
<!--                 <li class="caifutong"></li> -->
<!--                 <li class="caifubao"></li> -->
<!--                 <li class="beifen"></li> -->
<!--                 <li class="kexin"></li> -->
<!--                 <li class="shiming"></li> -->
<!--                 <li class="wangzhan360"></li> -->
<!--                 <li class="anquanlianmeng"></li> -->
<!--                 <div class="clear"></div> -->
<!--             </ul> -->
<!--         </div> -->
    </div>
</div>
<script language="javascript">
    var searchTxt = ' 请输入您要搜索的商品关键字';
    function searchFocus(e){
        if(e.value == searchTxt){
            e.value='';
            $('#keyword').css("color","");
        }
    }
    function searchBlur(e){
        if(e.value == ''){
            e.value=searchTxt;
            $('#keyword').css("color","#999999");
        }
    }
    function searchInput() {
        if($('#keyword').val()==searchTxt)
            $('#keyword').attr("value","");
        return true;
    }
    $('#keyword').css("color","#999999");
</script>
</body>
</html>