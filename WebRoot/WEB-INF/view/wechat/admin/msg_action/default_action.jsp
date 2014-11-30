<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>默认消息回复</title>
	<jsp:include page="/WEB-INF/view/common/inc/admin.jsp"></jsp:include>
	<link href="<%=resourceUrl%>/css/menu.css?v=2014030801" rel="stylesheet" type="text/css"/>
	<link href="<%=resourceUrl%>/css/material.css?v=2014030901" rel="stylesheet" type="text/css"/>
	<script src="<%=resourceUrl%>/js/jquery.json-2.4.min.js" type="text/javascript" charset="UTF-8"></script>
	<script src="<%=resourceUrl%>/js/jquery.xml2json.js" type="text/javascript" charset="UTF-8"></script>
	<script src="<%=resourceUrl %>/script/admin/default_action.js?v=2014052001" type="text/javascript" charset="UTF-8"></script>
	
	<style type="text/css">
		.layout-sub-title {
			padding: 26px 0 26px 30px;
			border-bottom: 1px solid #e6e6e6;
		}
		
	</style>
</head>
<body>

	<div class="layout-sub-title alert alert-info">
		<h2>默认消息回复<span id="set-tip" style="color: #2e7dc6"> （尚未设置）</span></h2>
		<p>
			当匹配不到用户发送的消息指令时，回复此内容
		</p>
	</div>

	<div id="resp-setting">
		<!-- 文本消息动作 -->
		<jsp:include page="/WEB-INF/view/wechat/admin/menu/action_inc.jsp">
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
				<a class="easyui-linkbutton" onclick="updateMsgView();"
					data-options="plain:true,iconCls:'icon-edit'" href="#" id="btn">&nbsp;修&nbsp;&nbsp;改&nbsp;</a>
			</div>
		</div>
		<div class="clear"></div>
		<div class="msg_wrp" id="viewDiv">
			<!-- js加载预览效果 -->
		</div>
	</div>
	
</body>
</html> 