<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title><sitemesh:write property='title' /></title>
    <jsp:include page="/WEB-INF/view/common/inc/display.jsp"></jsp:include>
    <sitemesh:write property='head' />
</head>
<body>
<%@include file="/WEB-INF/view/wechat/display/header.jsp"%>

    <sitemesh:write property='body' /> 
    <%@include file="/WEB-INF/view/wechat/display/footer.jsp"%>
    <script>
        var _hmt = _hmt || [];
        (function() {
            var hm = document.createElement("script");
            hm.src = "//hm.baidu.com/hm.js?a2efe10d99e73a13db453bd7cc51b0d1";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script>
</body>
</html>