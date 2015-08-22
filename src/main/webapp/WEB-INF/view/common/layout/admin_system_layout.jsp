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
    <sitemesh:head/>
    <jsp:include page="/WEB-INF/view/common/inc/admin.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/view/common/inc/admin-header.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="/WEB-INF/view/system/inc_menu.jsp"></jsp:include>
        <div id="context" class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <sitemesh:body/>
        </div>
    </div>
</div>
</body>
</html>

