<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>系统设置</title>
	<%@include file="/web/common/inc/admin.jsp"%>
	<link href="<%=curUrl%>/web/wechat/admin/material/css/material.css?v=2014030901" rel="stylesheet" type="text/css"/>
	<script src="<%=resourceUrl%>/js/jquery.json-2.4.min.js" type="text/javascript" charset="UTF-8"></script>
	<script src="<%=curUrl %>/web/wechat/admin/sys/js/setting.js?v=20140323" type="text/javascript" charset="UTF-8"></script>
</head>
<body>
	<iframe src="https://mp.weixin.qq.com/" id="IFeditValue" name="IFeditValue" scrolling="no" width="100%" height="90%"></iframe>
	
	<form action="https://mp.weixin.qq.com//cgi-bin/login?lang=zh_CN">
		<input type="text" name="username" value="">
		<input type="text" name="pwd" value="">
		<input type="submit" value="提交">
	</form>
	
	<jsp:include page="/WEB-INF/view/common/inc/admin.jsp"></jsp:include>
</body>
</html> 