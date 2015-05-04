<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>后台管理</title>
  <!--[if lt IE 9]>
  <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body>
<jsp:include page="/WEB-INF/view/common/inc/admin-header.jsp"></jsp:include>
<div class="container-fluid">
  <div class="row">
  <jsp:include page="/WEB-INF/view/wechat/admin/base/inc_menu.jsp"></jsp:include>
    <div id="context" class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
      <ol class="breadcrumb">
        <li><a href="#">后台管理</a></li>
        <li><a href="#">平台管理</a></li>
        <li class="active">授权配置</li>
      </ol>
      <h1>天天微信后台管理系统</h1>
    </div>
  </div>
</div>
<jsp:include page="/WEB-INF/view/common/inc/admin.jsp"></jsp:include>
</body>
</html>

