<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta name="decorator" content="wechat"/>
	<title>授权设置</title>
	<link href="<%=resourceUrl%>/css/setting.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript">
		var valid_state = '${wechatAccount.valid_state}';
	</script>
</head>
<body>
	<ol class="breadcrumb">
		<li><a href="<%=domain %>/admin">后台管理</a></li>
		<li><a href="<%=domain %>/admin/wechat">平台管理</a></li>
		<li class="active">授权配置</li>
	</ol>

	<div>
		<div class="alert alert-info">
			<p>在微信公众平台<strong>“功能”</strong>-&gt;<strong>“高级功能”</strong>-&gt;<strong>“开发模式”，</strong>将<strong>AppId和AppSecret</strong>，然后填入下边表单。</p>
			<p>通过认证的订阅号，拥有“自定义菜单”；通过认证的服务号，拥有“自定义菜单”和所有“高级接口”</p>
			<p><strong>配置步骤：</strong></p>
			<p id="step1">1、<span class="step-info">将授权信息配置到公众平台&nbsp;<a href="javascript:void(-1);"><span class="glyphicon glyphicon-question-sign"></span></a></span></p>
			<p id="step2">2、<span class="step-info">从微信客户端向公众号发送：${wechatAccount.valid_code }&nbsp;<a href="javascript:void(-1);"><span class="glyphicon glyphicon-question-sign"></span></a></span></p>
			<p id="step3">3、<span class="step-info">完成授权</span></p>
		</div>

		<form class="form-horizontal" id="form-auth" method="POST">
			<input type="hidden" id="id" name="id" value="${wechatAccount.id }" />
			<div class="control-group" >
				<label class="control-label" for="url">URL:</label>
				<div class="controls">
					<input readonly="readonly" type="text" class="span4 form-control" id="url" name="url" value="${wechatAccount.url }">
					<span class="text-primary">复制到公众平台<strong>“开发者中心”</strong>-><strong>“服务器配置”</strong>-><strong>“URL”</strong></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="token">TOKEN:</label>
				<div class="controls">
					<input readonly="readonly" type="text" class="span4 form-control" id="token" name="token" value="${wechatAccount.token }">
					<span class="text-primary">复制到公众平台<strong>“开发者中心”</strong>-><strong>“服务器配置”</strong>-><strong>“TOKEN”</strong></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="app_id">AppId:</label>
				<div class="controls">
					<input type="text" class="span4 form-control" id="app_id" name="app_id" value="${wechatAccount.app_id }">
					<span class="text-primary">未认证的订阅号可留空</span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="app_secret">应用密钥:</label>
				<div class="controls">
					<input type="text" class="span4 form-control" id="app_secret" name="app_secret" value="${wechatAccount.app_secret }">
					<span class="text-primary">未认证的订阅号可留空</span>
				</div>
			</div>
			<c:choose>
				<c:when test="${ null == wechatAccount.id || '' eq wechatAccount.id }">
					<input style="margin-left: 180px;" class="btn btn-danger" type="submit" id="btn-submit" value="&nbsp;生&nbsp;成&nbsp;授&nbsp;权&nbsp;" />
				</c:when>
				<c:otherwise>
					<input style="margin-left: 180px;" class="btn btn-info" type="submit" id="btn-submit" value="&nbsp;更&nbsp;新&nbsp;授&nbsp;权&nbsp;" />
				</c:otherwise>
			</c:choose>
		</form>

	</div>
	<script src="<%=resourceUrl%>/js/jquery.form.js" type="text/javascript" charset="UTF-8"></script>
<script src="<%=resourceUrl %>/script/wechat/admin/setting.js?v=20141005" type="text/javascript" charset="UTF-8"></script>
</body>
</html>





