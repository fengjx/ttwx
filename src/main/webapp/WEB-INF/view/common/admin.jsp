<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/common/inc/url.jsp" %>
<c:set var="menus" value="${fns:getMenus()}"></c:set>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>后台管理</title>
  <link href="${resourceUrl}/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
  <link href="${resourceUrl}/artDialog/css/ui-dialog.css" rel="stylesheet" type="text/css"/>
  <link href="${resourceUrl}/scojs/css/scojs.css" rel="stylesheet" type="text/css"/>
  <link href="${resourceUrl}/font-awesome/css/font-awesome.css" rel="stylesheet" type="text/css"/>
  <link href="${resourceUrl}/nprogress/nprogress.css" rel="stylesheet" type="text/css"/>
  <link href="${resourceUrl}/theme/ace/css/ace.min.css" rel="stylesheet" type="text/css"/>
  <link href="${resourceUrl}/css/common.css" rel="stylesheet" type="text/css"/>
  <script src="${resourceUrl}/js/jquery-2.1.1.min.js" type="text/javascript"></script>
  <script src="${resourceUrl}/js/jquery.cookie.js" type="text/javascript"></script>
  <script src="${resourceUrl}/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
  <script src="${resourceUrl}/scojs/js/sco.message.js" type="text/javascript"></script>
  <script src="${resourceUrl}/artDialog/dist/dialog-plus-min.js" type="text/javascript"></script>
  <script src="${resourceUrl}/nprogress/nprogress.js" type="text/javascript"></script>
  <script src="${resourceUrl}/js/jquery.json-2.4.min.js" type="text/javascript" charset="UTF-8"></script>
  <script src="${resourceUrl}/js/jquery.xml2json.js" type="text/javascript" charset="UTF-8"></script>
  <script src="${resourceUrl}/script/common/app.js?v=2016012801" type="text/javascript"></script>
  <script src="${resourceUrl}/script/common/init.js?v=2016021501" type="text/javascript"></script>
  <!--[if lte IE 8]>
  <script src="${resourceUrl}/js/excanvas.min.js" type="text/javascript"></script>
  <![endif]-->
  <!-- HTML5 shim and Respond.
  js IE8 support of HTML5 elements and media queries -->
  <!--[if lt IE 9]>
  <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body class="no-skin">
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
  <div class="navbar-container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed pull-left" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <span class="navbar-brand">
        <i class="glyphicon glyphicon-fire"></i>
        ${appName}
      </span>
      <ul id="head-menu" class="nav navbar-nav pull-left">
        <c:forEach var="m" items="${menus}">
          <c:if test="${m.level eq '1' && m.is_show eq '1'}">
              <li>
                  <a class="first-menu" href="javascript:void(-1);" data-id="${m.id}">${m.name}</a>
              </li>
          </c:if>
        </c:forEach>
      </ul>
    </div>
    <div id="navbar" class="navbar-collapse collapse">
      <ul class="nav navbar-nav navbar-right">
        <li>
          <a href="#"><i class="glyphicon glyphicon-question-sign"></i></a>
        </li>
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <i class="glyphicon glyphicon-user"></i>
          </a>
          <ul class="dropdown-menu dropdown-user">
            <li>
              <a href="#"><i class="glyphicon glyphicon-asterisk"></i> 设置</a>
            </li>
            <li class="divider"></li>
            <li>
              <a href="${domain}/logout"><i class="glyphicon glyphicon-off"></i> 退出</a>
            </li>
          </ul>
          <!-- /.dropdown-user -->
        </li>
      </ul>
    </div>
  </div>
</nav>

<div class="main-container">
  <div id="side-menu" class="sidebar responsive">
  </div>
  <div class="main-content">
    <div id="context" class="main-content-inner">
        <iframe id="mainFrame" name="mainFrame" src="" style="overflow:visible;" scrolling="yes" frameborder="no" width="100%"></iframe>
    </div>
  </div>
</div>
<script src="${resourceUrl}/script/common/adminMenu.js?v=2015082001" type="text/javascript"></script>
</body>
</html>

