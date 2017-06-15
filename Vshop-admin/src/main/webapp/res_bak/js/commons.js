/**
 * id数组
 * @type {Array}
 */
var idArray = new Array();

/**
 * 添加id到数组
 * @param id
 */
addIdFun = function(id) {
    idArray.push(id);
}

/**
 * 清空数组
 */
removeAllIdFun = function() {
    for (var i = 0; i<idArray.length; i++) {
        idArray.pop();
    }
}

/**
 * 批量删除
 */
deleteIdsFun = function() {
    $("#grid input[type=checkbox]").each(function(){
        if ( $(this).prop("checked")) {
            addIdFun($(this).val());
        }
    });
    $("#J-detail").show();
}

/**
 * 打开对话框
 * @param id
 */
openWin = function(id) {
    $("#J-detail").show();
    addIdFun(id);
}


/**
 * 关闭对话框
 * @param id
 */
closeWin = function() {
    $("#J-detail").hide();
    removeAllIdFun();
}
/**
 * 全选grid列表
 */
checkAll = function() {
    var flag = $(".all_select input[type=checkbox]").prop("checked");
    $("#grid input[type=checkbox]").each(function(){
        $(this).prop("checked", flag);
    });
}