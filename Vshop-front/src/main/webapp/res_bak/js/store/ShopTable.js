/**
 *
 * 自定义ajax分页插件
 *
 */

(function ($) {
    "use strict";
    var ShopTable = function(element,options){
        var defaults = {
            url: '',
            dataType: 'html',
            type: 'POST',
            contentType: 'application/x-www-form-urlencoded; charset=utf-8',
            errorFun:errorFun,
            successFun:successFun,
            formId:'',
            queryFun:queryFun,
            resetFun:resetFun
            //……
        };
        var settings = $.extend({}, defaults, options || {});
        var div = $(element);

        /**
         * 默认失败函数
         */
        function errorFun(){
            frameControl.lhDgFalInfo("通讯失败!");
        }

        /**
         * 默认成功函数
         * @param data
         */
        function successFun(data) {
            div.empty();
            div.html(data);
            div.hide();
            div.fadeIn(300);
        }

        /**
         * 默认表单重置函数
         */
        function resetFun(){
            $("#"+settings.formId)[0].reset();
        }

        /**
         * 默认查询函数
         */
        function queryFun(){
            var param = $("#"+settings.formId).serialize();
            load(param);
        }
        function load(data){
            $.ajax({
                async:false,
                url:APP_BASE+settings.url,//默认加载list 页面
                data:data,
                error:settings.errorFun,
                dataType:settings.dataType,
                type: settings.type,
                contentType:settings.contentType,
                success: successFun
            });
        };


        function init(){
            load({div:div.id});
            if (settings.formId!=null&&settings.formId.length>0) {
                $("#resetBtn").bind("click",resetFun);
                $("#queryBtn").bind("click",queryFun);
            }
        }

        init();
        return this;
    }

    $.fn.ShopTable = function (options) {
        new ShopTable(this, options);
    }

})(jQuery);