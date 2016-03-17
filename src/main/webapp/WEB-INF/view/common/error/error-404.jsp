<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/inc/path.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="decorator" content="display"/>
	<title>404</title>
</head>
<body>

	<!-- container -->
	<div class="container">
		<ol class="breadcrumb">
			<li><a href="${domain}">首页</a></li>
			<li class="active">404找不到页面</li>
		</ol>
		<div class="row">
		<!-- Article main content -->
			<article class="col-xs-12 maincontent text-center" style="height: 300px;">
			<div class="row-fluid">
					<div class="span12 page-404">
						<div class="number">
							404
						</div>
						<div class="details">
							<h3>Opps, You're lost.</h3>
							<p>
								We can not find the page you're looking for.<br>
								<a href="index.html">Return home</a> or try the search bar below.
							</p>
						</div>
					</div>
				</div>
			</article>
		</div>
	</div>	<!-- /container -->
	
</body>
</html>