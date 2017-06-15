<@p.header title="选择商家类型"/>

<div class="layout">
    <div class="wrap-shadow">
        <div class="wrap-all">
            <div class="chart">
                <div class="pos_x1 bg_a1" title="请选择平台分类"></div>
                <div class="pos_x2 bg_b1" title="填写院长和商家信息"></div>
                <div class="pos_x3 bg_c2" title="完成"></div>
            </div>
            <div class="grade-shop">
                <table>
                    <tbody>
                    <tr style="border-bottom:none">
                        <th class="w490">恭喜您，商家注册完成！
                            </br><span id="timeto"></span>
                        </th>
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
<script type="text/javascript">
    var wait=5;
    $(function() {
        time();
    });
    function time() {
        //console.log(wait);
        if(wait==0){
            top.location="${base}";
        }else{
            var timeto = $("#timeto");
            timeto.html("(" + wait + "秒后返回首页)");
            wait--;
            setTimeout(function() {
                time();
            }, 1000);
        }
    }
</script>
<script language="javascript">
    var searchTxt = ' 搜索其实很容易！';
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