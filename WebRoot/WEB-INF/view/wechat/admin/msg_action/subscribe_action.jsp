<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>粉丝关注回复</title>
	<jsp:include page="/WEB-INF/view/common/inc/admin.jsp"></jsp:include>
	<link href="<%=resourceUrl%>/css/menu.css?v=2014030801" rel="stylesheet" type="text/css"/>
	<link href="<%=resourceUrl%>/css/material.css?v=2014030901" rel="stylesheet" type="text/css"/>
	<script src="<%=resourceUrl%>/js/jquery.json-2.4.min.js" type="text/javascript" charset="UTF-8"></script>
	<script src="<%=resourceUrl%>/js/jquery.xml2json.js" type="text/javascript" charset="UTF-8"></script>
	<script src="<%=resourceUrl %>/script/admin/subscribe_action.js?v=2014052001" type="text/javascript" charset="UTF-8"></script>
	
	<style type="text/css">
		.layout-sub-title {
			padding: 26px 0 26px 30px;
			border-bottom: 1px solid #e6e6e6;
		}
		
	</style>
</head>
<body>
	<div class="layout-sub-title alert alert-info">
		<h2>粉丝关注回复<span id="set-tip" style="color: #2e7dc6"> （尚未设置）</span></h2>
		<p>
			用户在关注你的公众号时，系统会自动发送你设置的回复内容给用户。
		</p>
	</div>

	<div id="resp-setting">
		<!-- 文本消息动作 -->
		<jsp:include page="/WEB-INF/view/wechat/admin/menu/action_inc.jsp">
			<jsp:param name="req_type" value="event"/>
			<jsp:param name="event_type" value="subscribe"/>
			<jsp:param name="btn_return" value="hide"/>
		</jsp:include>
	</div>
	
	<div class="action_content sended jsMain" id="view" style="display: none;">
		<div style="float: left; width: 100%">
			<div class="action_tips" style="float: left;">
				用户关注时会收到以下信息
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