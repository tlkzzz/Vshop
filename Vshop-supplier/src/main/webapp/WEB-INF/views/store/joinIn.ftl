<@p.header title="注册供应商"/>
<div class="layout">
    <div class="wrap-shadow">
        <div class="wrap-all">
            <div class="chart">
                <div class="pos_x1 bg_a2" title="请选择供应商分类"></div>
                <div class="pos_x2 bg_b1" title="填写供应商信息"></div>
                <div class="pos_x3 bg_c" title="完成"></div>
            </div>
            <div class="grade-shop">
                <table>
                    <tbody>
                    <tr>
                        <th class="w100">系统默认</th>
                        <td class="w150">
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
                            <dd>注册供应商需要联系站长！</dd>
                        </dl></td>
                        <td class="w120"><a href="${base}/joinIn/step2" class="ncu-btn4 w110 ml10">立即注册供应商</a></td>
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
     <#assign siteSettingTag = newTag("siteSettingTag") />
	<#assign siteSet = siteSettingTag("") />
        ${siteSet.siteDbxx}
        <br/>
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