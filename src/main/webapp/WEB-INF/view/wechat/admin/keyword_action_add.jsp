<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>添加关键字</title>
  <jsp:include page="/WEB-INF/view/common/inc/admin.jsp"></jsp:include>
  <link href="<%=resourceUrl%>/css/menu.css?v=2015060801" rel="stylesheet" type="text/css"/>
  <link href="<%=resourceUrl%>/css/material.css?v=2014030901" rel="stylesheet" type="text/css"/>
</head>
<script>
  var actionId = '${id}';
</script>
<body>
<jsp:include page="/WEB-INF/view/common/inc/admin-header.jsp"></jsp:include>
<div class="container-fluid">
  <div class="row">
  <jsp:include page="/WEB-INF/view/wechat/admin/inc_menu.jsp">
    <jsp:param name="index" value="5"/>
  </jsp:include>
    <div id="context" class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
      <ol class="breadcrumb">
        <li><a href="<%=domain %>/admin">后台管理</a></li>
        <li><a href="<%=domain %>/admin/wechat">平台管理</a></li>
        <li><a href="<%=domain %>/admin/wechat/action/keyword">关键字回复</a></li>
        <li class="active">添加规则</li>
      </ol>

      <div>
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

    </div>
  </div>
</div>

<script src="<%=resourceUrl%>/js/jquery.json-2.4.min.js" type="text/javascript" charset="UTF-8"></script>
<script src="<%=resourceUrl%>/js/jquery.xml2json.js" type="text/javascript" charset="UTF-8"></script>
<script src="<%=resourceUrl%>/js/jquery.form.js" type="text/javascript"></script>
<script src="<%=resourceUrl%>/script/wechat/admin/material_util.js?v=2014091101" type="text/javascript" charset="UTF-8"></script>
<script src="<%=resourceUrl %>/script/wechat/admin/keyword_action_add.js?v=2015060701" type="text/javascript" charset="UTF-8"></script>
</body>
</html>

