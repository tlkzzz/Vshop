//显示下一级
function showNext(obj,rank){

    if($(obj).attr('class') == 'fold-on'){
        if(rank != ''){
            $(obj).parents("tr").nextAll("."+rank).each(function(){
                $(this).show();
            })
        }
        $(obj).removeClass("fold-on");
        $(obj).addClass("fold-off");
    }else{
        if(rank != ''){
            $(obj).parents("tr").nextAll("."+rank).each(function(){
                $(this).hide();
            })
        }
        $(obj).removeClass("fold-off");
        $(obj).addClass("fold-on");
    }

}

function setSelect(level){

    $.ajax({
        type : 'POST',
        url  : APP_BASE+'/goods/class/sort',
        data : {'level' : level},
        dataType : 'json',
        success : function(data){
            $("#sortSelect").empty();
            var options = '<option value="0" selected="selected">选择分类</option>';
            $.each(data.result, function (i, item) {
                options += '<option value="'+item.gcId+'">- '+item.gcName+'</option>'
            });
            $("#sortSelect").append(options);
        }
    });
}

function setSelectNext(flag){

    $.ajax({
        type : 'POST',
        url  : APP_BASE+'/goods/class/parentSort',
        data : {'gcParentId' : flag},
        dataType : 'json',
        success : function(data){
            if(data.level == 1){
                $(".item").eq(0).empty();
                $(".item").eq(0).append(' <label class="tit">所属类别：</label> '+
                    ' <label class="mr"><input class="radio" type="radio" name="gcLevel" '+
                    ' checked="checked" value="2"/>二级分类</label> ');
            }else if(data.level == 2){
                $(".item").eq(0).empty();
                $(".item").eq(0).append(' <label class="tit">所属类别：</label> '+
                    ' <label class="mr"><input class="radio" type="radio" name="gcLevel" '+
                    ' checked="checked" value="3"/>三级分类</label> ');
            }
            $("#sortSelect").empty();
            var options = '<option value="0" name="gcLevel" >选择分类</option>';
            options += '<option value="'+data.result.gcId+'" selected="selected">'+data.result.gcName+'</option>';
            $("#sortSelect").append(options);
        }
    });
}

function deleteClass(bizIdIn){
    /*首先获取主键 - 然获取删除*/
    var bizId = null;
    if(bizIdIn){
        bizId = bizIdIn;
    }

    /*开始删除操作*/
    var url = APP_BASE+"/goods/class/delete";
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

    var url = APP_BASE+"/goods/class/forward?gcId=0&gcParentId="+id;
    frameOperate.prepareUpdate('save','新增分类',url,840,400,false);

}

function openEdit(id){
    var url = APP_BASE+"/goods/class/forward?gcId="+id;
    frameOperate.prepareUpdate('edit','修改分类',url,840,400,false);
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