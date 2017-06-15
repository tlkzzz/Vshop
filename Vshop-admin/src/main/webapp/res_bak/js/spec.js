//全选按钮
function selectAll(obj){

    if(obj.checked){
        $(".type-list").find("tr>td").find(".checkbox").each(function(i){
            this.checked = true;
        });
    }else{
        $(".type-list").find("tr>td").find(".checkbox").each(function(i){
            this.checked = false;
        });
    }

}

//批量删除
function batchDelete(rootUrl){
    var updIds = frameOperate.getCheckedDatasByTabId("tbId");
    /*开始删除操作*/
    var url = APP_BASE+"/goods/spec/batchDelete";
    var param = {"id":updIds};
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

/**准备删除*/
function deleteSpec(bizIdIn){
    /*首先获取主键 - 然获取删除*/
    var bizId = null;
    if(bizIdIn){
        bizId = bizIdIn;
    }

    /*开始删除操作*/
    var url = APP_BASE+"/goods/spec/delete";
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

function openSave(id){
    var url = APP_BASE+"/goods/spec/forward?id="+id;
    if(id == 0){
        frameOperate.prepareUpdate('save','新增规格',url,840,400,false);
    }else{
        frameOperate.prepareUpdate('update','修改规格',url,840,400,false);
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
                    icon: 'error.gif',
                    content: data.message
                });
            }
        }
    });
}