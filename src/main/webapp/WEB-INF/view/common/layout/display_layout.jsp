<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/inc/path.jsp" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html>
<head>
    <title><sitemesh:title/></title>
    <jsp:include page="/WEB-INF/view/common/inc/display.jsp"></jsp:include>
    <sitemesh:head/>
</head>
<body>
<jsp:include page="/WEB-INF/view/wechat/display/header.jsp"></jsp:include>

    <sitemesh:body/>
    <jsp:include page="/WEB-INF/view/wechat/display/footer.jsp"></jsp:include>

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