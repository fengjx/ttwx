<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta name="decorator" content="admin"/>
    <title>添加关键字</title>
    <link href="${resourceUrl}/css/menu.css?v=2015060801" rel="stylesheet" type="text/css"/>
    <link href="${resourceUrl}/css/material.css?v=2014030901" rel="stylesheet" type="text/css"/>
    <script>
      var actionId = '${id}';
    </script>
</head>
<body>
    <div class="breadcrumbs" id="breadcrumbs">
        <ol class="breadcrumb">
          <li>微信管理</li>
          <li>关键字回复</li>
          <li class="active">添加规则</li>
        </ol>
    </div>

    <div class="page-content">
      <!-- 文本消息动作 -->
      <jsp:include page="/WEB-INF/view/wechat/admin/inc_action.jsp">
        <jsp:param name="req_type" value="text"/>
        <jsp:param name="btn_return" value="hide"/>
      </jsp:include>
    </div>

    <div id="viewDialog" style="display: none;overflow: hidden;top:50px; min-height:200px; width: 400px;">
      <div id="viewDiv">
        <!-- js加载预览效果 -->
      </div>
    </div>

<script src="${resourceUrl}/script/wechat/admin/material_util.js?v=2014091101" type="text/javascript" charset="UTF-8"></script>
<script src="${resourceUrl}/script/wechat/admin/keyword_action_add.js?v=2015060701" type="text/javascript" charset="UTF-8"></script>
</body>
</html>

