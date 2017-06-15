//显示下一级
function showNext(obj){

    if($(obj).attr('class') == 'fold-on'){
        $(obj).parents("tr").nextAll(".rank2").each(function(){
                $(this).show();
        })
        $(obj).removeClass("fold-on");
        $(obj).addClass("fold-off");
    }else{
       $(obj).parents("tr").nextAll(".rank2").each(function(){
                $(this).hide();
       });
        $(obj).removeClass("fold-off");
        $(obj).addClass("fold-on");
    }

}

/**准备删除*/
function deleteClass(bizIdIn){
    /*首先获取主键 - 然获取删除*/
    var bizId = null;
    if(bizIdIn){
        bizId = bizIdIn;
    }

    /*开始删除操作*/
    var url = APP_BASE+"/website/class/delete";
    var param = {"id":bizId};

    frameControl.lhDgCfmInfo(
            "确定要删除本条数据",
        function(){
            $.ajax({
                type: "post",
                url: url,
                data: param,
                dataType: "json",
                async:false,
                success:function(data) {
                    if(data.success == "true"){
                        frameControl.lhDgSucInfo("操作成功!",frameElement.api);
                        initDataList();
                    }else{
                        frameControl.lhDgFalInfo(data.result+"!",frameElement.api);
                    }
                }
            });
        },
        function(){});

}

function openSave(id,func){
    if(func == 'save'){
        var url = APP_BASE+"/website/class/findOne?id="+id;
        frameOperate.prepareUpdate('save','新增分类',url,840,400,false);
    }else{
        var url = APP_BASE+"/website/class/editFind?id="+id;
        frameOperate.prepareUpdate('update','修改分类',url,840,400,false);
    }

}

/**修改数据*/
function saveUpdate(){
    /*首先验证表单是否合法*/
    var opFm = $("#saveForm")[0];
    var fmUrl = opFm.action;
    $.ajax({
        type: "post",
        url: fmUrl,
        data: $(opFm).serialize(),
        dataType: "json",
        async:false,
        success:function(data) {
            if(data.success == "true"){
                $.dialog({
                    time : 2,
                    icon: 'success.gif',
                    content: '操作成功,2秒后关闭',
                    close : function(){
                        frameElement.api.opener.initDataList();
                        frameElement.api.close();
                    }
                });
            }else{
                $.dialog({
                    icon: 'success.gif',
                    content: data.message
                });
            }
        }
    });
}
