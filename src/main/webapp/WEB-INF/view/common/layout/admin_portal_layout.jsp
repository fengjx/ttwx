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
    <sitemesh:head/>
</head>
<body class="no-skin">
<jsp:include page="/WEB-INF/view/common/inc/admin-header.jsp"></jsp:include>
<div class="main-container">
    <jsp:include page="/WEB-INF/view/portal/admin/inc_menu.jsp"></jsp:include>
    <div class="main-content">
        <div id="context" class="main-content-inner">
            <sitemesh:body/>
        </div>
    </div>
</div>
</body>
</html>

