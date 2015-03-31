<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Exception e = (Exception)request.getAttribute("ex");
%>
<!DOCTYPE html>
<html>
<head>
<title>404 - 阿豆微信发布与营销平台</title>
<%@include file="/WEB-INF/view/common/inc/display.jsp"%>
</head>
<body>
	<%@include file="/WEB-INF/view/wechat/display/header.jsp"%>
	<!-- container -->
	<div class="container">
		<ol class="breadcrumb">
			<li><a href="<%=domain %>">首页</a></li>
			<li class="active">服务器异常</li>
		</ol>
		<div class="row">
		<!-- Article main content -->
			<article class="col-xs-12 maincontent text-center" style="height: 300px;">
			<div class="row-fluid">
					<div class="span12 page-404">
						<div class="number">
							error
						</div>
						<div class="details">
							<h3>Opps, Something went wrong.</h3>
							<p>
								<%=e.getMessage() %><br>
								<a href="index.html">Return home</a> or try the search bar below.
							</p>
						</div>
					</div>
				</div>
			</article>
		</div>
	</div>	<!-- /container -->
	<%@include file="/WEB-INF/view/wechat/display/footer.jsp"%>
</body>
</html>