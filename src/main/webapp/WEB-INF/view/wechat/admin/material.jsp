<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta name="decorator" content="admin"/>
	<title>素材管理</title>
    <link href="${resourceUrl}/css/material.css?v=2014030901" rel="stylesheet" type="text/css"/>
</head>
<body>
    <div class="breadcrumbs">
        <ol class="breadcrumb">
            <li>微信管理</li>
            <li class="active">素材管理</li>
        </ol>
    </div>
    <div class="page-content">
        <div class="main_bd" style="width:99.5%">
            <jsp:include page="/WEB-INF/view/wechat/admin/inc_material_list.jsp">
                <jsp:param name="showType" value="edit"/>
            </jsp:include>
        </div>
    </div>

<script src="${resourceUrl}/script/wechat/admin/material_util.js?v=2014091101" type="text/javascript" charset="UTF-8"></script>
</body>
</html>
