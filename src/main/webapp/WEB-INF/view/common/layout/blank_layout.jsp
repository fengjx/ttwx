<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><sitemesh:title/></title>
    <jsp:include page="/WEB-INF/view/common/inc/admin.jsp"></jsp:include>
    <script>
        // 注册app
        (function (a, $) {
            window['app'] = a;
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
        })(top.app, jQuery);

        /**
         * 页面初始化
         */
        $(function () {
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
        });
    </script>
    <sitemesh:head/>
</head>
<body style="background-color:#FFF">
<sitemesh:body/>
</body>
</html>

