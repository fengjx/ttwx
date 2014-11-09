<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>500 - 阿豆微信发布与营销平台</title>
<%@include file="/WEB-INF/view/common/inc/bootstrap.jsp"%>
</head>
<body>
	<%@include file="/WEB-INF/view/wechat/display/header.jsp"%>
	
	<!-- container -->
	<div class="container">
		<ol class="breadcrumb">
			<li><a href="<%=domain %>">首页</a></li>
			<li class="active">500服务器出错</li>
		</ol>
		<div class="row">
		<!-- Article main content -->
			<article class="col-xs-12 maincontent text-center" style="height: 300px;">
			<div class="row-fluid">
					<div class="span12 page-500">
						<div class=" number">
							500
						</div>
						<div class=" details">
							<h3>Opps, Something went wrong.</h3>
							<p>
								We are fixing it!<br>
								Please come back in a while.<br><br>
							</p>
						</div>
					</div>
				</div>
		</div>
	</div>	<!-- /container -->
	
	<%@include file="/WEB-INF/view/wechat/display/footer.jsp"%>
</body>
</html>