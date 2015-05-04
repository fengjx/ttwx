<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/inc/path.jsp" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/view/common/inc/meta.jsp">
	<jsp:param value="登录" name="title"/>
</jsp:include>
<style type="text/css">
	
	.label-icon{
		margin-top: 40px;
	}
	
</style>
</head>
<body>
	<%@include file="/WEB-INF/view/wechat/display/header.jsp"%>
	<!-- container -->
	<div class="container">
		<ol class="breadcrumb">
			<li><a href="<%=domain %>">首页</a></li>
			<li class="active">用户登录</li>
		</ol>
		<div class="row">
			<!-- Article main content -->
			<article class="col-xs-12 maincontent">
				<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
					<div class="panel panel-default">
						<div class="panel-body">
							<h3 class="thin text-center">登录你的账号</h3>
							<hr>
							<form id="form-login" method="post" class="login-form">
								<div class="top-margin control-group">
									<label>用户名<span class="text-danger">*</span></label>
									<input id="username" name="username" type="text" class="form-control login-field" placeholder="输入用户名" value="" />
									<label class="login-field-icon fui-user label-icon" for="username"></label>
								</div>
								<div class="top-margin control-group">
									<label>密码<span class="text-danger">*</span></label>
									<input id="pwd" name="pwd" type="password" class="form-control login-field" placeholder="输入密码" value="" />
									<label class="login-field-icon fui-lock label-icon" for="pwd"></label>
								</div>
								<div class="row top-margin control-group">
									<div class="col-lg-8">
										<label>验证码<span class="text-danger">*</span></label>
										<input id="valid_code" name="valid_code" maxlength="4" type="text" class="form-control login-field" placeholder="输入验证码" />
										<label class="login-field-icon fui-clip label-icon" style="margin-right: 15px;" for="valid_code"></label>
									</div>
									<div class="col-lg-4" style="margin-top: 40px;">
										<img id="valid-img" title="点击刷新" width="90" height="42" src="<%=domain %>/common/verification_code.jpg">
										<a id="ref-valid-img" href="javascript:void(-1);">刷新</a>
									</div>
								</div>
								<hr>
								<div class="row">
									<div class="col-lg-8">
										<b><a href="">忘记密码？</a></b>
									</div>
									<div class="col-lg-4 text-right">
										<input id="btn-login" type="submit" class="btn btn-primary" data-loading-text="正在登录..." value="&nbsp;登&nbsp;&nbsp;录&nbsp;" />
									</div>
								</div>
							</form>
						</div>
					</div>

				</div>
				
			</article>
			<!-- /Article -->
		</div>
	</div>	<!-- /container -->
	
	<%@include file="/WEB-INF/view/wechat/display/footer.jsp"%>
	<jsp:include page="/WEB-INF/view/common/inc/display.jsp"></jsp:include>
	<script src="<%=resourceUrl%>/js/jquery.form.js" type="text/javascript"></script>
	<script src="<%=resourceUrl%>/script/display/login.js" type="text/javascript"></script>
</body>
</html>