/**
 * 页面初始化
 */
$(function () {
    app.parser.parse();
    try {
        // 链接去掉虚框
        $("a").bind("focus", function () {
            if (this.blur) {
                this.blur()
            }
        });
    } catch (e) {
        // blank
    }
    /* 定义jquery AJAX 默认设置 */
    $.ajaxSetup({
        type: 'post',
        dataType: "json",
        headers: {
            "Request-Flag": "ajax"
        },
        dataFilter: function (data, type) {
            // 对Ajax返回的原始数据进行预处理
            if (type === "json") {
                var _data = $.parseJSON(data);
                if (_data && "-1" == _data.code) { // -1表示登陆超时
                    alert("登陆超时，请重新登陆！");
                    window.location.href = domain + "/login";
                    return false;
                }
            }
            return data; // 返回处理后的数据
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            app.alertModal(XMLHttpRequest.responseText.split('<script')[0]);
            NProgress.done();
        },
        beforeSend: function (XMLHttpRequest) {
            //app.loadingModal();
            NProgress.set(0.4);
            NProgress.start();
        },
        complete: function () {
            // app.closeDialog();
            NProgress.done();
        }
    });
});