//特别推荐
function recommend(brandId,obj,rootUrl){

    //判断是否特别推荐
    if(obj.className == 'icon-offrecom'){

         $.ajax({
             type : 'POST',
             url  : rootUrl+'/goods/brand/recommond',
             data : {'brandId' : brandId, 'brandRecommend' : 0},
             success : function(data){
                 if(data.success == 'true'){
                     $.dialog({
                         time : 2,
                         icon: 'success.gif',
                         content: '操作成功,2秒后关闭',
                         close : function(){
                             obj.className = 'icon-onrecom';
                             obj.title = '特别推荐';
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
    }else{
         $.ajax({
             type : 'POST',
             url  : rootUrl+'/goods/brand/recommond',
             data : {'brandId' : brandId,'brandRecommend' : 1},
             success : function(data){
                 if(data.success == 'true'){
                     $.dialog({
                         time : 2,
                         icon: 'success.gif',
                         content: '操作成功,2秒后关闭',
                         close : function(){
                             obj.className = 'icon-offrecom';
                             obj.title = '已特别推荐';
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
}

//批量特别推荐
function batchRecommend(){

    var updIds = frameOperate.getCheckedDatasByTabId("tbId");
    /*开始删除操作*/
    var url = APP_BASE+"/goods/brand/batchRecommand";
    var param = {"id":updIds};
    frameControl.lhDgCfmInfo(
        "确定要批量特别推荐",
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

//全选按钮
function selectAll(obj){

    if(obj.checked){
        $(".brand-list").find("tr>td").find(".checkbox").each(function(i){
            this.checked = true;
        });
    }else{
        $(".brand-list").find("tr>td").find(".checkbox").each(function(i){
            this.checked = false;
        });
    }

}

//批量删除
function batchDelete(){
    var updIds = frameOperate.getCheckedDatasByTabId("tbId");
    /*开始删除操作*/
    var url = APP_BASE+"/goods/brand/batchDelete";
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

function openSave(id){
    var url = APP_BASE+"/goods/brand/forward?id="+id;
    if(id == 0){
        frameOperate.prepareUpdate('save','新增品牌',url,840,400,false);
    }else{
        frameOperate.prepareUpdate('update','修改品牌',url,840,400,false);
    }

}

/**修改数据*/
function saveSuccess(){
    /*首先验证表单是否合法*/
                $.dialog({
                    time : 2,
                    icon: 'success.gif',
                    content: '操作成功,2秒后关闭',
                    close : function(){
                        frameElement.api.opener.initDataList();
                        frameElement.api.close();
                    }
                });
}

/**修改数据*/
function saveFail(){

    $.dialog({
        icon: 'error.gif',
        content: '操作失败'
    })
}

/**准备删除*/
function deleteBrand(bizIdIn){
    /*首先获取主键 - 然获取删除*/
    var bizId = null;
    if(bizIdIn){
        bizId = bizIdIn;
    }

    /*开始删除操作*/
    var url = APP_BASE+"/goods/brand/delete";
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