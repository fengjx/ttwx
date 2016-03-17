<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/inc/path.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="decorator" content="display"/>
	<title>500</title>
</head>
<body>
	<!-- container -->
	<div class="container">
		<ol class="breadcrumb">
			<li><a href="${domain}">首页</a></li>
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

</body>
</html>