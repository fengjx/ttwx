<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>默认消息回复</title>
	<jsp:include page="/WEB-INF/view/common/inc/admin.jsp"></jsp:include>
	<link href="<%=resourceUrl%>/css/menu.css?v=2015060801" rel="stylesheet" type="text/css"/>
	<link href="<%=resourceUrl%>/css/material.css?v=2014030901" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="/WEB-INF/view/common/inc/admin-header.jsp"></jsp:include>
<div class="container-fluid">
	<div class="row">
		<jsp:include page="/WEB-INF/view/wechat/admin/inc_menu.jsp">
			<jsp:param name="index" value="7"/>
		</jsp:include>
		<div id="context" class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<ol class="breadcrumb">
				<li><a href="<%=domain %>/admin">后台管理</a></li>
				<li><a href="<%=domain %>/admin/wechat">平台管理</a></li>
				<li class="active">默认消息回复</li>
			</ol>

			<div class="layout-sub-title alert alert-info">
				<h2>默认消息回复<span id="set-tip" style="color: #2e7dc6"> （尚未设置）</span></h2>
				<p>
					当匹配不到用户发送的消息指令时，回复此内容
				</p>
			</div>

			<div id="resp-setting">
				<!-- 文本消息动作 -->
				<jsp:include page="/WEB-INF/view/wechat/admin/inc_action.jsp">
					<jsp:param name="btn_return" value="hide"/>
					<jsp:param name="extType" value="default"/>
				</jsp:include>
			</div>

			<div class="action_content sended jsMain" id="view" style="display: none;">
				<div style="float: left; width: 100%">
					<div class="action_tips" style="float: left;">
						没有匹配不到用户发送的消息指令时，回复此内容
					</div>
					<div style="float:right;">
						<button onclick="updateMsgView();" type="button" class="btn btn-success">修&nbsp;&nbsp;改</button>
						<button onclick="deleteById(msgAction.id);" type="button" class="btn btn-danger">删&nbsp;&nbsp;除</button>
					</div>
				</div>
				<div class="clear"></div>
				<div class="msg_wrp" id="viewDiv">
					<!-- js加载预览效果 -->
				</div>
			</div>

		</div>
	</div>
</div>

<script src="<%=resourceUrl%>/js/jquery.json-2.4.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="<%=resourceUrl%>/js/jquery.xml2json.js" type="text/javascript" charset="UTF-8"></script>
<script src="<%=resourceUrl%>/js/jquery.form.js" type="text/javascript"></script>
<script src="<%=resourceUrl%>/script/wechat/admin/material_util.js?v=2014091101" type="text/javascript" charset="UTF-8"></script>
<script src="<%=resourceUrl %>/script/wechat/admin/default_action.js?v=2015060701" type="text/javascript" charset="UTF-8"></script>
</body>
</html>






