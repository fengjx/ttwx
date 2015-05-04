<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/inc/path.jsp" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/view/common/inc/meta.jsp">
	<jsp:param value="注册" name="title"/>
</jsp:include>
</head>
<body>
	<%@include file="/WEB-INF/view/wechat/display/header.jsp"%>
	<!-- container -->
	<div class="container">

		<ol class="breadcrumb">
			<li><a href="<%=domain%>">首页</a></li>
			<li class="active">注册</li>
		</ol>
		<div class="row">
			<!-- Article main content -->
			<article class="col-xs-12 maincontent">
				<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
					<div class="panel panel-default">
						<div class="panel-body">
							<h3 class="thin text-center">注册一个新的账号</h3>
							<hr>
							<form id="form-register" method="post" class="login-form" >
								<!-- <div style="height: 60px;">
									<div class="alert alert-warning" role="alert">
									  <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
									  Better check yourself, you're not looking too good.
									</div>
								</div> -->
								<div class="form-group">
									<label>用户名<span class="text-danger">*</span></label>
									<input id="username" name="username" type="text" class="form-control" valid="0" />
								</div>
								<div class="form-group">
									<label>邮箱<span class="text-danger">*</span></label>
									<input id="email" name="email" type="text" class="form-control" valid="0" />
								</div>

								<div class="row form-group">
									<div class="col-sm-6">
										<label>密码 <span class="text-danger">*</span></label>
										<input id="pwd" name="pwd" type="password" class="form-control"/>
									</div>
									<div class="col-sm-6">
										<label>确认密码<span class="text-danger">*</span></label>
										<input id="pwd2" name="pwd2" type="password" class="form-control" />
									</div>
								</div>
								<hr>
								<div class="row">
									<div class="col-lg-8">
										<label class="checkbox">
											<input type="checkbox"> 
											I've read the <a href="page_terms.html">Terms and Conditions</a>
										</label>
									</div>
									<div class="col-lg-4 text-right">
										<input id="btn-register" type="submit" class="btn btn-primary" data-loading-text="正在登录..." value="&nbsp;注&nbsp;&nbsp;册&nbsp;" />
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
	<script src="<%=resourceUrl%>/script/display/register.js" type="text/javascript"></script>
</body>
</html>