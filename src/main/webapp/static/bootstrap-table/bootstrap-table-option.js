$(function () {
    $.fn.bootstrapTable.defaults = $.extend($.fn.bootstrapTable.defaults,{
        iconSize : "xs",
        ajaxOptions:{
            beforeSend : function(XMLHttpRequest) {
                app.loadingModal();
            }
        },
        onLoadSuccess : function (data) {
            app.closeDialog();
        },
        onLoadError : function (status) {
            app.alert(status);
            app.closeDialog();
        }
    });
});
