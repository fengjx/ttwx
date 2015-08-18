<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<title>素材管理</title>
    <link href="<%=resourceUrl%>/css/material.css?v=2014030901" rel="stylesheet" type="text/css"/>
    <link href="<%=resourceUrl%>/css/common.css?v=2014030901" rel="stylesheet" type="text/css"/>
</head>
<body>
    <ol class="breadcrumb">
        <li><a href="<%=domain %>/admin">后台管理</a></li>
        <li><a href="<%=domain %>/admin/wechat">平台管理</a></li>
        <li class="active">素材管理</li>
    </ol>
    <div class="main_bd" style="width:99.5%">
        <jsp:include page="/WEB-INF/view/wechat/admin/inc_material_list.jsp">
            <jsp:param name="showType" value="edit"/>
        </jsp:include>
    </div>

<script src="<%=resourceUrl%>/js/jquery.json-2.4.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="<%=resourceUrl%>/js/jquery.xml2json.js" type="text/javascript" charset="UTF-8"></script>
<script src="<%=resourceUrl%>/script/wechat/admin/material_util.js?v=2014091101" type="text/javascript" charset="UTF-8"></script>
</body>
</html>
