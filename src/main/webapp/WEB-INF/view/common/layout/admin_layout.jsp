<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<c:set var="menu_pid" value="${admin_menu_pid}"></c:set>
<c:set var="menus" value="${fns:getMenus()}"></c:set>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><sitemesh:title/></title>
    <jsp:include page="/WEB-INF/view/common/inc/admin.jsp"></jsp:include>
    <sitemesh:head/>
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
        天天微信平台
      </span>
            <ul id="head-menu" class="nav navbar-nav pull-left">
                <c:forEach var="m" items="${menus}">
                    <c:if test="${m.level eq '1' && m.is_show eq '1'}">
                    <li><a href="${domain}${m.url}">${m.name}</a></li>
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
        <ul class="nav nav-sidebar nav-list">
            <c:forEach var="m1" items="${menus}">
                <c:if test="${menu_pid eq m1.parent_id && '1' eq m1.is_show}">
                <c:set var="isLeef" value="${m1.isLeef}"></c:set>
                <li>
                    <a href="<c:choose><c:when test="${not empty m1.url}">${domain}${m1.url}</c:when><c:otherwise>javascript:void (0);</c:otherwise></c:choose>">
                        <i class="${m1.icon}"></i>
                        <span class="menu-text"> ${m1.name} </span>
                        <c:if test="${!isLeef}">
                        <b class="arrow icon-angle-down"></b>
                        </c:if>
                    </a>
                    <b class="arrow"></b>
                    <c:if test="${!isLeef}">
                    <ul class="submenu">
                        <c:forEach var="m2" items="${menus}">
                            <c:if test="${m2.parent_id eq m1.id && '1' eq m2.is_show}">
                                <li>
                                    <a href="${domain}${m2.url}">
                                        <i class="menu-icon fa fa-caret-right"></i>
                                        ${m2.name}
                                    </a>
                                    <b class="arrow"></b>
                                </li>
                            </c:if>
                        </c:forEach>
                    </ul>
                    </c:if>
                </li>
                </c:if>
            </c:forEach>
        </ul>
        <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
            <i class="ace-icon icon-arrow-left"></i>
        </div>
    </div>

    <div class="main-content">
        <div id="context" class="main-content-inner">
            <sitemesh:body/>
        </div>
    </div>
</div>
<script src="${resourceUrl}/script/common/leftMenu.js?v=2015082001" type="text/javascript"></script>
</body>
</html>

