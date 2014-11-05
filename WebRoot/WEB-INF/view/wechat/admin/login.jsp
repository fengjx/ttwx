<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <title>登录 - 阿豆微信发布与营销平台</title>
    <%@include file="/WEB-INF/view/common/inc//url.jsp" %>
    <meta charset=utf-8>
    <meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
    <link rel="stylesheet" href="<%=resourceUrl %>/plugin/bootstrap/css/bootstrap.min.css">
    <link href="<%=resourceUrl %>/css/login.css" rel="stylesheet" /> 
    <link href="<%=resourceUrl %>/css/adminia.css" rel="stylesheet" /> 
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
        <script src="<%=resourceUrl %>/js/html5shiv.js"></script>
    <![endif]-->
  </head>
<body>
	
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 				
				</a>
			</div> <!-- /container -->
		</div> <!-- /navbar-inner -->
	</div> <!-- /navbar -->
	
	<div id="login-container">
		<div id="login-header">
			<h3>阿豆微信发布与营销平台</h3>
		</div> <!-- /login-header -->
		
		<div id="login-content" class="clearfix">
		
		<form action="./" />
					<fieldset>
						<div class="control-group">
							<label class="control-label" for="username">账号</label>
							<div class="controls">
								<input type="text" name="username" id="username" value=""/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="pwd">密码</label>
							<div class="controls">
								<input type="password" name="pwd" id="pwd" value=""/>
							</div>
						</div>
					</fieldset>
					
					<div style="margin-top: 10px;">
						<div id="remember-me" class="pull-left">
							<input type="checkbox" name="remember" id="remember" />
							<label id="remember-label" for="remember">记住账号</label>
						</div>
						
						<div class="pull-right" style="margin-right: 15px;">
							<button id="btn-login" type="button" onclick="userlogin();" data-loading-text="正在登录..." class="btn btn-primary btn-large">
								&nbsp;&nbsp;登录&nbsp;&nbsp;
							</button>
						</div>
					</div>
				</form>
			</div> 
	</div> <!-- /login-wrapper -->
    <script src="<%=resourceUrl %>/js/jquery-1.8.2.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=resourceUrl %>/js/jquery.cookie.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=resourceUrl %>/plugin/bootstrap/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" src="<%=curUrl %>/web/wechat/admin/js/login.js?v=2014050804"></script>
  </body>
</html>