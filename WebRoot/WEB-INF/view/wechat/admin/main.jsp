<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title><%=appName %> - 后台管理</title>
</head>

<body id="indexLayout" class="easyui-layout">
	<div href="<%=domain %>/admin/north" data-options="region:'north',border:false" style="height:55px;background:#FFF;"></div>
	<div href="<%=domain %>/admin/west" data-options="region:'west',split:true,title:'导航'" style="width:200px;overflow: hidden;"></div>
	<div href="<%=domain %>/admin/center" data-options="region:'center',title:'当前登录：${sessionScope.sys_user_login_key.username}'"></div>
	<div href="<%=domain %>/admin/south" data-options="region:'south',border:false" style="height:30px;padding:5px; text-align: center;"></div>
	
	<jsp:include page="/WEB-INF/view/common/inc/admin.jsp"></jsp:include>
</body>
</html>