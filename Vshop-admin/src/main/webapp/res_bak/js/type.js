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
    var url = APP_BASE+"/goods/type/batchDelete";
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

function addLine(){
    var insertTr=$(".spec-list:eq(0)").find("tbody>tr:eq(0)").clone();
    var index=$(".spec-list:eq(0)").find("tbody>tr").length;
    insertTr.find("td:eq(0)").find("input").attr("name","attrList["+index+"].attrSort");
    insertTr.find("td:eq(1)").find("input").attr("name","attrList["+index+"].attrName");
    insertTr.find("td:eq(2)").find("input").attr("name","attrList["+index+"].attrValue");
    insertTr.find("td:eq(3)").find("input").attr("name","attrList["+index+"].attrShow");
    insertTr.find("td:eq(0)").find("input").val("");
    insertTr.find("td:eq(1)").find("input").val("");
    insertTr.find("td:eq(2)").find("input").val("");
    $(".spec-list:eq(0)").find("tbody>tr:last").after(insertTr);
}

function deleteLine(obj){

    if($(".spec-list:eq(0)").find("tbody>tr").length > 1){
        $(obj).parents("tr").remove();
        $(".spec-list:eq(0)").find("tbody>tr").each(function(i){
            $(this).find("td:eq(0)").find("input").attr("name","attrList["+i+"].attrName");
            $(this).find("td:eq(1)").find("input").attr("name","attrList["+i+"].attrValue");
            $(this).find("td:eq(2)").find("input").attr("name","attrList["+i+"].attrValue");
            $(this).find("td:eq(3)").find("input").attr("name","attrList["+i+"].attrShow");
        });
    }
}

/**准备删除*/
function deleteType(bizIdIn){
    /*首先获取主键 - 然获取删除*/
    var bizId = null;
    if(bizIdIn){
        bizId = bizIdIn;
    }

    /*开始删除操作*/
    var url = APP_BASE+"/goods/type/delete";
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
    var url = APP_BASE+"/goods/type/forward?id="+id;
    if(id == 0){
        frameOperate.prepareUpdate('save','新增类型',url,1280,600,false);
    }else{
        frameOperate.prepareUpdate('update','修改类型',url,1280,600,false);
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