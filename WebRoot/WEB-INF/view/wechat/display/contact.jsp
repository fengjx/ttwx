<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/inc/path.jsp" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/view/common/inc/meta.jsp">
	<jsp:param value="联系我们" name="title"/>
</jsp:include>
</head>
<body>
	<%@include file="/WEB-INF/view/wechat/display/header.jsp"%>
	<!-- container -->
	<div class="container">
		<ol class="breadcrumb">
			<li><a href="<%=curUrl %>">首页</a></li>
			<li class="active">联系我们</li>
		</ol>
		<div class="row">
			<!-- Article main content -->
			<article class="col-sm-9 maincontent" style="margin-bottom: 20px;">
				<p>
					我们很乐意听取你的意见。如果在使用过程中有任何疑问，或者有兴趣加入我们。请填写下面表单的相关信息，我们会尽快给你回应。
				</p>
				<br>
					<form id="form-guestbook" method="POST">
						<div class="row">
							<div class="col-sm-4">
								<input id="name" name="name" class="form-control" type="text" placeholder="姓名（必填）">
							</div>
							<div class="col-sm-4">
								<input id="email" name="email" class="form-control" type="text" placeholder="邮箱（必填）">
							</div>
							<div class="col-sm-4">
								<input id="qq" name="qq" class="form-control" type="text" placeholder="QQ">
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-sm-12">
								<textarea id="msg" name="msg" placeholder="编辑你要发送的信息（必填）..." class="form-control" rows="9"></textarea>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-sm-12 text-right">
								<input id="btn-guestbook" type="submit" class="btn btn-primary" data-loading-text="正在发送..." value="发送信息" />
							</div>
						</div>
					</form>

			</article>
			<!-- /Article -->
			
			<!-- Sidebar -->
			<aside class="col-sm-3 sidebar sidebar-right">

				<div class="widget">
					<h4>电话:</h4>
					<address>
						(+86)185-2022-6106
					</address>
					<h4>QQ:</h4>
					<address>
						466516623
					</address>
					<h4>E-mail:</h4>
					<address>
						xd-fjx@qq.com
					</address>
				</div>
			</aside>
			<!-- /Sidebar -->
		</div>
	</div>	<!-- /container -->
	
	<%@include file="/WEB-INF/view/wechat/display/footer.jsp"%>
	<jsp:include page="/WEB-INF/view/common/inc/display.jsp"></jsp:include>
	<script src="<%=resourceUrl%>/js/jquery.form.js" type="text/javascript"></script>
	<script src="<%=resourceUrl%>/script/display/contact.js" type="text/javascript"></script>
</body>
</html>