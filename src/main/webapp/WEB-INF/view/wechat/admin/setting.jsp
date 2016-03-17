<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta name="decorator" content="admin"/>
	<title>授权设置</title>
	<script type="text/javascript">
		var valid_state = '${wechatAccount.validState}';
	</script>
</head>
<body>

	<div class="breadcrumbs" id="breadcrumbs">
		<ol class="breadcrumb">
			<li>微信管理</li>
			<li class="active">授权配置</li>
		</ol>
	</div>

	<div class="page-content">
		<div class="alert alert-info">
			<p>在微信公众平台<strong>“功能”</strong>-&gt;<strong>“高级功能”</strong>-&gt;<strong>“开发模式”，</strong>将<strong>AppId和AppSecret</strong>，然后填入下边表单。</p>
			<p>通过认证的订阅号，拥有“自定义菜单”；通过认证的服务号，拥有“自定义菜单”和所有“高级接口”</p>
			<p><strong>配置步骤：</strong></p>
			<p id="step1">1、<span class="step-info">将授权信息配置到公众平台&nbsp;<a href="javascript:void(-1);"><span class="glyphicon glyphicon-question-sign"></span></a></span></p>
			<p id="step2">2、<span class="step-info">从微信客户端向公众号发送：${wechatAccount.validCode }&nbsp;<a href="javascript:void(-1);"><span class="glyphicon glyphicon-question-sign"></span></a></span></p>
			<p id="step3">3、<span class="step-info">完成授权</span></p>
		</div>

		<form class="form-horizontal" id="form-auth" method="POST">
			<input type="hidden" id="id" name="id" value="${wechatAccount.id }" />
			<div class="control-group" >
				<label class="control-label">URL:</label>
				<div class="controls">
					<input readonly="readonly" type="text" class="span4 form-control" value="${domain}${wechatAccount.url }">
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
				<label class="control-label" for="appId">AppId:</label>
				<div class="controls">
					<input type="text" class="span4 form-control" id="appId" name="appId" value="${wechatAccount.appId }">
					<span class="text-primary">未认证的订阅号可留空</span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="appSecret">应用密钥:</label>
				<div class="controls">
					<input type="text" class="span4 form-control" id="appSecret" name="appSecret" value="${wechatAccount.appSecret }">
					<span class="text-primary">未认证的订阅号可留空</span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="encodingAESKey">EncodingAESKey:</label>
				<div class="controls">
					<input type="text" class="span4 form-control" id="encodingAESKey" name="encodingAESKey" value="${wechatAccount.encodingAESKey }">
					<span class="text-primary">消息加解密密钥，若使用明文模式可以不填</span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="merchantId">商户ID:</label>
				<div class="controls">
					<input type="text" class="span4 form-control" id="merchantId" name="merchantId" value="${wechatAccount.merchantId }">
					<span class="text-primary">如已开通微信支付，请登录商户平台获取</span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="payKey">支付API密钥:</label>
				<div class="controls">
					<input type="text" class="span4 form-control" id="payKey" name="payKey" value="${wechatAccount.payKey }">
					<span class="text-primary">如已开通微信支付，请登录商户平台获取</span>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<input class="btn btn-info" type="submit" id="btn-submit" value="保存" />
					<input class="btn btn-warning" type="button" id="btn-reset" value="重置" />
				</div>
			</div>
		</form>

	</div>
<script src="${resourceUrl}/script/wechat/admin/setting.js?v=20141005" type="text/javascript" charset="UTF-8"></script>
</body>
</html>





