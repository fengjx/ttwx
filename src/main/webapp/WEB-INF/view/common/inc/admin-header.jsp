<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<script>
  $(function () {
    var curUrl = window.location.href;
    var ul = document.getElementById('head-menu');
    var lis = ul.getElementsByTagName("li");
    for (var i = 0; i < lis.length; i++) {
      var href = lis.item(i).getElementsByTagName("a")[0].getAttribute("href");
      if (-1 !== curUrl.indexOf(href)) {
        $(lis.item(i)).find("a").css("color", "#f89406");
        break;
      }
    }
  });
</script>
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
        天天微信平台
      </span>
      <ul id="head-menu" class="nav navbar-nav pull-left">
        <li><a href="${adminPath}/wechat">平台管理</a></li>
        <li><a href="${adminPath}/portal">门户</a></li>
        <li><a href="#">天天小店</a></li>
        <li><a href="#">微社区</a></li>
        <li><a href="#">微门户</a></li>
        <li><a href="${adminPath}/sys">系统管理</a></li>
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