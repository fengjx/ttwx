<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta name="decorator" content="admin"/>
	<title>默认消息回复</title>
    <link href="${resourceUrl}/css/menu.css?v=2015060801" rel="stylesheet" type="text/css"/>
    <link href="${resourceUrl}/css/material.css?v=2015090301" rel="stylesheet" type="text/css"/>
</head>
<body>
    <div class="breadcrumbs">
        <ol class="breadcrumb">
            <li>微信管理</li>
            <li class="active">默认消息回复</li>
        </ol>
    </div>

    <div class="page-content">
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
                    <button onclick="updateMsgView();" type="button" class="btn btn-info btn-sm">修改</button>
                    <button onclick="deleteById(msgAction.id);" type="button" class="btn btn-danger btn-sm">删除</button>
                </div>
            </div>
            <div class="clear"></div>
            <div class="msg_wrp" id="viewDiv">
                <!-- js加载预览效果 -->
            </div>
        </div>
    </div>


<script src="${resourceUrl}/script/wechat/admin/material_util.js?v=2014091101" type="text/javascript" charset="UTF-8"></script>
<script src="${resourceUrl}/script/wechat/admin/default_action.js?v=2015060701" type="text/javascript" charset="UTF-8"></script>
</body>
</html>






