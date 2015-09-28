<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/inc/path.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="decorator" content="display"/>
	<jsp:include page="/WEB-INF/view/common/inc/meta.jsp">
		<jsp:param value="激活成功" name="title"/>
	</jsp:include>
</head>
<body>
	<!-- container -->
	<div class="container page-wrapper">
		<ol class="breadcrumb">
			<li><a href="${domain}">首页</a></li>
			<li class="active">激活成功</li>
		</ol>
		<div class="row">
			<!-- Article main content -->
			<article class="col-xs-12 maincontent text-center" style="height: 300px;">
				<h4>激活成功，你现在可以<a href="${domain}/login">登录</a>了！</h4>
			</article>
			<!-- /Article -->
		</div>
	</div>	<!-- /container -->
	
</body>
</html>