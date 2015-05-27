$(function () {
    $.fn.bootstrapTable.defaults = $.extend($.fn.bootstrapTable.defaults,{
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
