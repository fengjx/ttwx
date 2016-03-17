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
        })(top.app, jQuery);
    </script>
    <sitemesh:head/>
</head>
<body class="no-skin">
<div class="main-container">
    <sitemesh:body/>
</div>
<script src="${resourceUrl}/script/common/leftMenu.js?v=2015082001" type="text/javascript"></script>
</body>
</html>

