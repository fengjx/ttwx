<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/common/inc/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>后台管理</title>
  <jsp:include page="/WEB-INF/view/common/inc/admin.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/view/common/inc/admin-header.jsp"></jsp:include>
<div class="container-fluid">
  <div class="row">
  <jsp:include page="/WEB-INF/view/wechat/admin/inc_menu.jsp"></jsp:include>
    <div id="context" class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
      <ol class="breadcrumb">
        <li><a href="<%=domain %>/admin">后台管理</a></li>
        <li class="active">平台管理</li>
      </ol>

      <div class="row">
        <div class="col-lg-3 col-md-6">
          <div class="panel panel-primary">
            <div class="panel-heading">
              <div class="row">
                <div class="col-xs-3">
                  <i class="icon-comments icon-5x"></i>
                </div>
                <div class="col-xs-9 text-right">
                  <div class="huge">26</div>
                  <div>New Comments!</div>
                </div>
              </div>
            </div>
            <a href="#">
              <div class="panel-footer">
                <span class="pull-left">View Details</span>
                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                <div class="clearfix"></div>
              </div>
            </a>
          </div>
        </div>
        <div class="col-lg-3 col-md-6">
          <div class="panel panel-green">
            <div class="panel-heading">
              <div class="row">
                <div class="col-xs-3">
                  <i class="icon-tasks icon-5x"></i>
                </div>
                <div class="col-xs-9 text-right">
                  <div class="huge">12</div>
                  <div>New Tasks!</div>
                </div>
              </div>
            </div>
            <a href="#">
              <div class="panel-footer">
                <span class="pull-left">View Details</span>
                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                <div class="clearfix"></div>
              </div>
            </a>
          </div>
        </div>
        <div class="col-lg-3 col-md-6">
          <div class="panel panel-yellow">
            <div class="panel-heading">
              <div class="row">
                <div class="col-xs-3">
                  <i class="icon-shopping-cart icon-5x"></i>
                </div>
                <div class="col-xs-9 text-right">
                  <div class="huge">124</div>
                  <div>New Orders!</div>
                </div>
              </div>
            </div>
            <a href="#">
              <div class="panel-footer">
                <span class="pull-left">View Details</span>
                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                <div class="clearfix"></div>
              </div>
            </a>
          </div>
        </div>
        <div class="col-lg-3 col-md-6">
          <div class="panel panel-red">
            <div class="panel-heading">
              <div class="row">
                <div class="col-xs-3">
                  <i class="icon-desktop icon-5x"></i>
                </div>
                <div class="col-xs-9 text-right">
                  <div class="huge">13</div>
                  <div>Support Tickets!</div>
                </div>
              </div>
            </div>
            <a href="#">
              <div class="panel-footer">
                <span class="pull-left">View Details</span>
                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                <div class="clearfix"></div>
              </div>
            </a>
          </div>
        </div>
      </div>

    </div>
  </div>
</div>
</body>
</html>

