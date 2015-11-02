<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta name="decorator" content="sys"/>
	<title>角色管理</title>
</head>
<body>
	<div class="breadcrumbs">
		<ol class="breadcrumb">
			<li><a href="${adminPath}">后台管理</a></li>
			<li><a href="${adminPath}/sys">系统管理</a></li>
			<li class="active">角色管理</li>
		</ol>
	</div>
	<div class="page-content">
		<ul class="nav nav-tabs">
			<li class="active"><a href="${adminPath}/sys/role/">角色列表</a></li>
			<li><a href="${adminPath}/sys/role/form">角色添加</a></li>
		</ul>
		<table id="data-table"></table>
		<div id="tablePager"></div>
	</div>

<script src="${resourceUrl}/script/sys/role.js?v=2015103101" type="text/javascript" charset="UTF-8"></script>
</body>
</html>
